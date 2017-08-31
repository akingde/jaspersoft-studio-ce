package com.jaspersoft.studio.data.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.jaspersoft.studio.data.services.SqlGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalSqlParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "MINUTE_MICROSECOND", "SECOND_MICROSECOND", "HOUR_MICROSECOND", "DAY_MICROSECOND", "MINUTE_SECOND", "STRAIGHT_JOIN", "HOUR_MINUTE", "HOUR_SECOND", "MICROSECOND", "DAY_MINUTE", "DAY_SECOND", "YEAR_MONTH", "BETWEEN_4", "KW_FOLLOWING", "INTERSECT", "PARTITION", "PRECEDING", "UNBOUNDED", "BETWEEN_2", "NOTEQUAL", "BETWEEN_3", "GREATER_1", "DAY_HOUR", "DISTINCT", "SIBLINGS", "BETWEEN_1", "GREATER", "BETWEEN", "CURRENT", "EXCLUDE", "EXTRACT", "INCLUDE", "NATURAL", "PERCENT", "QUARTER", "UNPIVOT", "EXCEPT", "EXISTS", "HAVING", "MINUTE", "OFFSET", "SECOND", "SELECT", "VALUES", "EQUAL", "LESS_1", "NOTIN", "CAST", "CROSS", "FETCH", "FIRST", "GROUP", "INNER", "LIMIT", "MINUS", "MONTH", "NULLS", "ORDER", "OUTER", "PIVOT", "RANGE", "RIGHT", "UNION", "USING", "WHERE", "LESS", "CASE", "DESC", "ELSE", "FROM", "FULL", "HOUR", "JOIN", "LAST", "LEFT", "LIKE", "NOT_1", "NULL", "ONLY", "OVER", "ROWS", "SOME", "THEN", "TIES", "WEEK", "WHEN", "WITH", "YEAR", "LeftParenthesisPlusSignRightParenthesis", "ALL", "AND", "ANY", "ASC", "DAY", "END", "FOR", "NOT", "ROW", "TOP", "XML", "IN_1", "ExclamationMarkEqualsSign", "X", "LessThanSignEqualsSign", "LessThanSignGreaterThanSign", "GreaterThanSignEqualsSign", "AS", "BY", "IN", "IS", "ON", "OR", "CircumflexAccentEqualsSign", "VerticalLineVerticalLine", "LeftParenthesis", "RightParenthesis", "PlusSign", "Comma", "HyphenMinus", "FullStop", "Solidus", "LessThanSign", "EqualsSign", "GreaterThanSign", "VerticalLine", "RightCurlyBracket", "RULE_JRPARAM", "RULE_JRNPARAM", "RULE_STAR", "RULE_UNSIGNED", "RULE_INT", "RULE_SIGNED_DOUBLE", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_STRING_CORE", "RULE_STRING_", "RULE_STRING", "RULE_DBNAME", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int LessThanSignGreaterThanSign=108;
    public static final int MONTH=59;
    public static final int JOIN=76;
    public static final int BETWEEN=31;
    public static final int LessThanSign=125;
    public static final int MINUS=58;
    public static final int LeftParenthesisPlusSignRightParenthesis=92;
    public static final int WHEN=89;
    public static final int QUARTER=38;
    public static final int LeftParenthesis=118;
    public static final int YEAR=91;
    public static final int ELSE=72;
    public static final int RULE_TIME=137;
    public static final int HOUR_MINUTE=10;
    public static final int PARTITION=19;
    public static final int INCLUDE=35;
    public static final int DAY_MINUTE=13;
    public static final int INNER=56;
    public static final int CAST=51;
    public static final int GreaterThanSign=127;
    public static final int LEFT=78;
    public static final int RULE_ID=143;
    public static final int IN=112;
    public static final int DISTINCT=27;
    public static final int IS=113;
    public static final int WHERE=68;
    public static final int CASE=70;
    public static final int GreaterThanSignEqualsSign=109;
    public static final int AS=110;
    public static final int RULE_DATE=136;
    public static final int TOP=102;
    public static final int VerticalLine=128;
    public static final int PlusSign=120;
    public static final int RULE_INT=134;
    public static final int RULE_ML_COMMENT=144;
    public static final int THEN=86;
    public static final int UNPIVOT=39;
    public static final int RULE_JRPARAM=130;
    public static final int MICROSECOND=12;
    public static final int VerticalLineVerticalLine=117;
    public static final int DAY_HOUR=26;
    public static final int RULE_DBNAME=142;
    public static final int GROUP=55;
    public static final int ORDER=61;
    public static final int ASC=96;
    public static final int Comma=121;
    public static final int HyphenMinus=122;
    public static final int STRAIGHT_JOIN=9;
    public static final int BY=111;
    public static final int X=106;
    public static final int OFFSET=44;
    public static final int RIGHT=65;
    public static final int VALUES=47;
    public static final int LessThanSignEqualsSign=107;
    public static final int Solidus=124;
    public static final int RightCurlyBracket=129;
    public static final int RULE_SIGNED_DOUBLE=135;
    public static final int FETCH=53;
    public static final int FullStop=123;
    public static final int RULE_UNSIGNED=133;
    public static final int SIBLINGS=28;
    public static final int GREATER=30;
    public static final int NOTIN=50;
    public static final int SECOND_MICROSECOND=5;
    public static final int FIRST=54;
    public static final int RULE_STRING_=140;
    public static final int SELECT=46;
    public static final int PRECEDING=20;
    public static final int RULE_JRNPARAM=131;
    public static final int PERCENT=37;
    public static final int ExclamationMarkEqualsSign=105;
    public static final int UNION=66;
    public static final int DAY=97;
    public static final int ALL=93;
    public static final int ONLY=82;
    public static final int FROM=73;
    public static final int DESC=71;
    public static final int MINUTE_MICROSECOND=4;
    public static final int UNBOUNDED=21;
    public static final int KW_FOLLOWING=17;
    public static final int MINUTE=43;
    public static final int RULE_STAR=132;
    public static final int HOUR_MICROSECOND=6;
    public static final int EXTRACT=34;
    public static final int NULL=81;
    public static final int DAY_MICROSECOND=7;
    public static final int LESS_1=49;
    public static final int FOR=99;
    public static final int RightParenthesis=119;
    public static final int PIVOT=63;
    public static final int EXCEPT=40;
    public static final int CURRENT=32;
    public static final int FULL=74;
    public static final int NOTEQUAL=23;
    public static final int USING=67;
    public static final int NOT=100;
    public static final int LIKE=79;
    public static final int LAST=77;
    public static final int IN_1=104;
    public static final int EXCLUDE=33;
    public static final int AND=94;
    public static final int CircumflexAccentEqualsSign=116;
    public static final int MINUTE_SECOND=8;
    public static final int YEAR_MONTH=15;
    public static final int LESS=69;
    public static final int END=98;
    public static final int ROW=101;
    public static final int HAVING=42;
    public static final int DAY_SECOND=14;
    public static final int RANGE=64;
    public static final int TIES=87;
    public static final int HOUR=75;
    public static final int LIMIT=57;
    public static final int RULE_STRING=141;
    public static final int ANY=95;
    public static final int RULE_SL_COMMENT=145;
    public static final int NATURAL=36;
    public static final int EqualsSign=126;
    public static final int SOME=85;
    public static final int NOT_1=80;
    public static final int BETWEEN_2=22;
    public static final int GREATER_1=25;
    public static final int BETWEEN_1=29;
    public static final int OUTER=62;
    public static final int WEEK=88;
    public static final int EOF=-1;
    public static final int BETWEEN_4=16;
    public static final int NULLS=60;
    public static final int BETWEEN_3=24;
    public static final int ON=114;
    public static final int OR=115;
    public static final int EXISTS=41;
    public static final int RULE_WS=146;
    public static final int EQUAL=48;
    public static final int RULE_ANY_OTHER=147;
    public static final int INTERSECT=18;
    public static final int WITH=90;
    public static final int OVER=83;
    public static final int CROSS=52;
    public static final int XML=103;
    public static final int SECOND=45;
    public static final int RULE_STRING_CORE=139;
    public static final int HOUR_SECOND=11;
    public static final int RULE_TIMESTAMP=138;
    public static final int ROWS=84;

    // delegates
    // delegators


        public InternalSqlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSqlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSqlParser.tokenNames; }
    public String getGrammarFileName() { return "InternalSqlParser.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */
     

    	private SqlGrammarAccess grammarAccess;
    	 	
    	public InternalSqlParser(TokenStream input, SqlGrammarAccess grammarAccess) {
    		this(input);
    		this.grammarAccess = grammarAccess;
    		registerRules(grammarAccess.getGrammar());
    	}
    	
    	@Override
    	protected String getFirstRuleName() {
    		return "Model";	
    	} 
    	   	   	
    	@Override
    	protected SqlGrammarAccess getGrammarAccess() {
    		return grammarAccess;
    	}



    // $ANTLR start "entryRuleModel"
    // InternalSqlParser.g:68:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalSqlParser.g:69:2: (iv_ruleModel= ruleModel EOF )
            // InternalSqlParser.g:70:2: iv_ruleModel= ruleModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getModelRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleModel; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalSqlParser.g:77:1: ruleModel returns [EObject current=null] : ( (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_wq_1_0= ruleWithQuery ) )? ( (lv_query_2_0= ruleSelectQuery ) ) ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token this_JRNPARAM_0=null;
        EObject lv_wq_1_0 = null;

        EObject lv_query_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:80:28: ( ( (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_wq_1_0= ruleWithQuery ) )? ( (lv_query_2_0= ruleSelectQuery ) ) ) )
            // InternalSqlParser.g:81:1: ( (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_wq_1_0= ruleWithQuery ) )? ( (lv_query_2_0= ruleSelectQuery ) ) )
            {
            // InternalSqlParser.g:81:1: ( (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_wq_1_0= ruleWithQuery ) )? ( (lv_query_2_0= ruleSelectQuery ) ) )
            // InternalSqlParser.g:81:2: (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_wq_1_0= ruleWithQuery ) )? ( (lv_query_2_0= ruleSelectQuery ) )
            {
            // InternalSqlParser.g:81:2: (this_JRNPARAM_0= RULE_JRNPARAM )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_JRNPARAM) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalSqlParser.g:81:3: this_JRNPARAM_0= RULE_JRNPARAM
                    {
                    this_JRNPARAM_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_JRNPARAM_0, grammarAccess.getModelAccess().getJRNPARAMTerminalRuleCall_0()); 
                          
                    }

                    }
                    break;

            }

            // InternalSqlParser.g:85:3: ( (lv_wq_1_0= ruleWithQuery ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==WITH) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalSqlParser.g:86:1: (lv_wq_1_0= ruleWithQuery )
                    {
                    // InternalSqlParser.g:86:1: (lv_wq_1_0= ruleWithQuery )
                    // InternalSqlParser.g:87:3: lv_wq_1_0= ruleWithQuery
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getModelAccess().getWqWithQueryParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_3);
                    lv_wq_1_0=ruleWithQuery();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getModelRule());
                      	        }
                             		set(
                             			current, 
                             			"wq",
                              		lv_wq_1_0, 
                              		"com.jaspersoft.studio.data.Sql.WithQuery");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:103:3: ( (lv_query_2_0= ruleSelectQuery ) )
            // InternalSqlParser.g:104:1: (lv_query_2_0= ruleSelectQuery )
            {
            // InternalSqlParser.g:104:1: (lv_query_2_0= ruleSelectQuery )
            // InternalSqlParser.g:105:3: lv_query_2_0= ruleSelectQuery
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getModelAccess().getQuerySelectQueryParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_query_2_0=ruleSelectQuery();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getModelRule());
              	        }
                     		set(
                     			current, 
                     			"query",
                      		lv_query_2_0, 
                      		"com.jaspersoft.studio.data.Sql.SelectQuery");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleWithQuery"
    // InternalSqlParser.g:129:1: entryRuleWithQuery returns [EObject current=null] : iv_ruleWithQuery= ruleWithQuery EOF ;
    public final EObject entryRuleWithQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWithQuery = null;


        try {
            // InternalSqlParser.g:130:2: (iv_ruleWithQuery= ruleWithQuery EOF )
            // InternalSqlParser.g:131:2: iv_ruleWithQuery= ruleWithQuery EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWithQueryRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWithQuery=ruleWithQuery();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWithQuery; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWithQuery"


    // $ANTLR start "ruleWithQuery"
    // InternalSqlParser.g:138:1: ruleWithQuery returns [EObject current=null] : ( ( (lv_w_0_0= WITH ) ) ( (lv_wname_1_0= ruleDBID ) ) ( (lv_withCols_2_0= ruleWithColumns ) )? otherlv_3= AS otherlv_4= LeftParenthesis ( (lv_query_5_0= ruleSelectQuery ) ) otherlv_6= RightParenthesis ) ;
    public final EObject ruleWithQuery() throws RecognitionException {
        EObject current = null;

        Token lv_w_0_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_wname_1_0 = null;

        EObject lv_withCols_2_0 = null;

        EObject lv_query_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:141:28: ( ( ( (lv_w_0_0= WITH ) ) ( (lv_wname_1_0= ruleDBID ) ) ( (lv_withCols_2_0= ruleWithColumns ) )? otherlv_3= AS otherlv_4= LeftParenthesis ( (lv_query_5_0= ruleSelectQuery ) ) otherlv_6= RightParenthesis ) )
            // InternalSqlParser.g:142:1: ( ( (lv_w_0_0= WITH ) ) ( (lv_wname_1_0= ruleDBID ) ) ( (lv_withCols_2_0= ruleWithColumns ) )? otherlv_3= AS otherlv_4= LeftParenthesis ( (lv_query_5_0= ruleSelectQuery ) ) otherlv_6= RightParenthesis )
            {
            // InternalSqlParser.g:142:1: ( ( (lv_w_0_0= WITH ) ) ( (lv_wname_1_0= ruleDBID ) ) ( (lv_withCols_2_0= ruleWithColumns ) )? otherlv_3= AS otherlv_4= LeftParenthesis ( (lv_query_5_0= ruleSelectQuery ) ) otherlv_6= RightParenthesis )
            // InternalSqlParser.g:142:2: ( (lv_w_0_0= WITH ) ) ( (lv_wname_1_0= ruleDBID ) ) ( (lv_withCols_2_0= ruleWithColumns ) )? otherlv_3= AS otherlv_4= LeftParenthesis ( (lv_query_5_0= ruleSelectQuery ) ) otherlv_6= RightParenthesis
            {
            // InternalSqlParser.g:142:2: ( (lv_w_0_0= WITH ) )
            // InternalSqlParser.g:143:1: (lv_w_0_0= WITH )
            {
            // InternalSqlParser.g:143:1: (lv_w_0_0= WITH )
            // InternalSqlParser.g:144:3: lv_w_0_0= WITH
            {
            lv_w_0_0=(Token)match(input,WITH,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_w_0_0, grammarAccess.getWithQueryAccess().getWWITHKeyword_0_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getWithQueryRule());
              	        }
                     		setWithLastConsumed(current, "w", lv_w_0_0, "WITH");
              	    
            }

            }


            }

            // InternalSqlParser.g:158:2: ( (lv_wname_1_0= ruleDBID ) )
            // InternalSqlParser.g:159:1: (lv_wname_1_0= ruleDBID )
            {
            // InternalSqlParser.g:159:1: (lv_wname_1_0= ruleDBID )
            // InternalSqlParser.g:160:3: lv_wname_1_0= ruleDBID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getWithQueryAccess().getWnameDBIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_5);
            lv_wname_1_0=ruleDBID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getWithQueryRule());
              	        }
                     		set(
                     			current, 
                     			"wname",
                      		lv_wname_1_0, 
                      		"com.jaspersoft.studio.data.Sql.DBID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:176:2: ( (lv_withCols_2_0= ruleWithColumns ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LeftParenthesis) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalSqlParser.g:177:1: (lv_withCols_2_0= ruleWithColumns )
                    {
                    // InternalSqlParser.g:177:1: (lv_withCols_2_0= ruleWithColumns )
                    // InternalSqlParser.g:178:3: lv_withCols_2_0= ruleWithColumns
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getWithQueryAccess().getWithColsWithColumnsParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_6);
                    lv_withCols_2_0=ruleWithColumns();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getWithQueryRule());
                      	        }
                             		set(
                             			current, 
                             			"withCols",
                              		lv_withCols_2_0, 
                              		"com.jaspersoft.studio.data.Sql.WithColumns");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,AS,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getWithQueryAccess().getASKeyword_3());
                  
            }
            otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getWithQueryAccess().getLeftParenthesisKeyword_4());
                  
            }
            // InternalSqlParser.g:204:1: ( (lv_query_5_0= ruleSelectQuery ) )
            // InternalSqlParser.g:205:1: (lv_query_5_0= ruleSelectQuery )
            {
            // InternalSqlParser.g:205:1: (lv_query_5_0= ruleSelectQuery )
            // InternalSqlParser.g:206:3: lv_query_5_0= ruleSelectQuery
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getWithQueryAccess().getQuerySelectQueryParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_query_5_0=ruleSelectQuery();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getWithQueryRule());
              	        }
                     		set(
                     			current, 
                     			"query",
                      		lv_query_5_0, 
                      		"com.jaspersoft.studio.data.Sql.SelectQuery");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_6=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getWithQueryAccess().getRightParenthesisKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWithQuery"


    // $ANTLR start "entryRuleWithColumns"
    // InternalSqlParser.g:235:1: entryRuleWithColumns returns [EObject current=null] : iv_ruleWithColumns= ruleWithColumns EOF ;
    public final EObject entryRuleWithColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWithColumns = null;


        try {
            // InternalSqlParser.g:236:2: (iv_ruleWithColumns= ruleWithColumns EOF )
            // InternalSqlParser.g:237:2: iv_ruleWithColumns= ruleWithColumns EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWithColumnsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWithColumns=ruleWithColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWithColumns; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWithColumns"


    // $ANTLR start "ruleWithColumns"
    // InternalSqlParser.g:244:1: ruleWithColumns returns [EObject current=null] : (otherlv_0= LeftParenthesis this_UsingCols_1= ruleUsingCols otherlv_2= RightParenthesis ) ;
    public final EObject ruleWithColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_UsingCols_1 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:247:28: ( (otherlv_0= LeftParenthesis this_UsingCols_1= ruleUsingCols otherlv_2= RightParenthesis ) )
            // InternalSqlParser.g:248:1: (otherlv_0= LeftParenthesis this_UsingCols_1= ruleUsingCols otherlv_2= RightParenthesis )
            {
            // InternalSqlParser.g:248:1: (otherlv_0= LeftParenthesis this_UsingCols_1= ruleUsingCols otherlv_2= RightParenthesis )
            // InternalSqlParser.g:249:2: otherlv_0= LeftParenthesis this_UsingCols_1= ruleUsingCols otherlv_2= RightParenthesis
            {
            otherlv_0=(Token)match(input,LeftParenthesis,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getWithColumnsAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getWithColumnsAccess().getUsingColsParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_8);
            this_UsingCols_1=ruleUsingCols();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_UsingCols_1;
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getWithColumnsAccess().getRightParenthesisKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWithColumns"


    // $ANTLR start "entryRuleFetchFirst"
    // InternalSqlParser.g:278:1: entryRuleFetchFirst returns [EObject current=null] : iv_ruleFetchFirst= ruleFetchFirst EOF ;
    public final EObject entryRuleFetchFirst() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFetchFirst = null;


        try {
            // InternalSqlParser.g:279:2: (iv_ruleFetchFirst= ruleFetchFirst EOF )
            // InternalSqlParser.g:280:2: iv_ruleFetchFirst= ruleFetchFirst EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFetchFirstRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFetchFirst=ruleFetchFirst();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFetchFirst; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFetchFirst"


    // $ANTLR start "ruleFetchFirst"
    // InternalSqlParser.g:287:1: ruleFetchFirst returns [EObject current=null] : ( ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY ) ;
    public final EObject ruleFetchFirst() throws RecognitionException {
        EObject current = null;

        Token lv_row_1_1=null;
        Token lv_row_1_2=null;
        Token otherlv_2=null;
        EObject lv_fetchFirst_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:290:28: ( ( ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY ) )
            // InternalSqlParser.g:291:1: ( ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY )
            {
            // InternalSqlParser.g:291:1: ( ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY )
            // InternalSqlParser.g:291:2: ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY
            {
            // InternalSqlParser.g:291:2: ( (lv_fetchFirst_0_0= ruleUnsignedValue ) )
            // InternalSqlParser.g:292:1: (lv_fetchFirst_0_0= ruleUnsignedValue )
            {
            // InternalSqlParser.g:292:1: (lv_fetchFirst_0_0= ruleUnsignedValue )
            // InternalSqlParser.g:293:3: lv_fetchFirst_0_0= ruleUnsignedValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFetchFirstAccess().getFetchFirstUnsignedValueParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_9);
            lv_fetchFirst_0_0=ruleUnsignedValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFetchFirstRule());
              	        }
                     		set(
                     			current, 
                     			"fetchFirst",
                      		lv_fetchFirst_0_0, 
                      		"com.jaspersoft.studio.data.Sql.UnsignedValue");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:309:2: ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) )
            // InternalSqlParser.g:310:1: ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) )
            {
            // InternalSqlParser.g:310:1: ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) )
            // InternalSqlParser.g:311:1: (lv_row_1_1= ROW | lv_row_1_2= ROWS )
            {
            // InternalSqlParser.g:311:1: (lv_row_1_1= ROW | lv_row_1_2= ROWS )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ROW) ) {
                alt4=1;
            }
            else if ( (LA4_0==ROWS) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalSqlParser.g:312:3: lv_row_1_1= ROW
                    {
                    lv_row_1_1=(Token)match(input,ROW,FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_row_1_1, grammarAccess.getFetchFirstAccess().getRowROWKeyword_1_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getFetchFirstRule());
                      	        }
                             		setWithLastConsumed(current, "row", lv_row_1_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:325:8: lv_row_1_2= ROWS
                    {
                    lv_row_1_2=(Token)match(input,ROWS,FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_row_1_2, grammarAccess.getFetchFirstAccess().getRowROWSKeyword_1_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getFetchFirstRule());
                      	        }
                             		setWithLastConsumed(current, "row", lv_row_1_2, null);
                      	    
                    }

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,ONLY,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getFetchFirstAccess().getONLYKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFetchFirst"


    // $ANTLR start "entryRuleOffset"
    // InternalSqlParser.g:354:1: entryRuleOffset returns [EObject current=null] : iv_ruleOffset= ruleOffset EOF ;
    public final EObject entryRuleOffset() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOffset = null;


        try {
            // InternalSqlParser.g:355:2: (iv_ruleOffset= ruleOffset EOF )
            // InternalSqlParser.g:356:2: iv_ruleOffset= ruleOffset EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOffsetRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOffset=ruleOffset();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOffset; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOffset"


    // $ANTLR start "ruleOffset"
    // InternalSqlParser.g:363:1: ruleOffset returns [EObject current=null] : ( (lv_offset_0_0= RULE_INT ) ) ;
    public final EObject ruleOffset() throws RecognitionException {
        EObject current = null;

        Token lv_offset_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:366:28: ( ( (lv_offset_0_0= RULE_INT ) ) )
            // InternalSqlParser.g:367:1: ( (lv_offset_0_0= RULE_INT ) )
            {
            // InternalSqlParser.g:367:1: ( (lv_offset_0_0= RULE_INT ) )
            // InternalSqlParser.g:368:1: (lv_offset_0_0= RULE_INT )
            {
            // InternalSqlParser.g:368:1: (lv_offset_0_0= RULE_INT )
            // InternalSqlParser.g:369:3: lv_offset_0_0= RULE_INT
            {
            lv_offset_0_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_offset_0_0, grammarAccess.getOffsetAccess().getOffsetINTTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getOffsetRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"offset",
                      		lv_offset_0_0, 
                      		"com.jaspersoft.studio.data.Sql.INT");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOffset"


    // $ANTLR start "entryRuleLimit"
    // InternalSqlParser.g:393:1: entryRuleLimit returns [EObject current=null] : iv_ruleLimit= ruleLimit EOF ;
    public final EObject entryRuleLimit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLimit = null;


        try {
            // InternalSqlParser.g:394:2: (iv_ruleLimit= ruleLimit EOF )
            // InternalSqlParser.g:395:2: iv_ruleLimit= ruleLimit EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLimitRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLimit=ruleLimit();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLimit; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLimit"


    // $ANTLR start "ruleLimit"
    // InternalSqlParser.g:402:1: ruleLimit returns [EObject current=null] : ( ( () otherlv_1= ALL ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) ) ;
    public final EObject ruleLimit() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_l1_2_0=null;
        Token otherlv_3=null;
        Token lv_l2_4_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:405:28: ( ( ( () otherlv_1= ALL ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) ) )
            // InternalSqlParser.g:406:1: ( ( () otherlv_1= ALL ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) )
            {
            // InternalSqlParser.g:406:1: ( ( () otherlv_1= ALL ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ALL) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_UNSIGNED) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalSqlParser.g:406:2: ( () otherlv_1= ALL )
                    {
                    // InternalSqlParser.g:406:2: ( () otherlv_1= ALL )
                    // InternalSqlParser.g:406:3: () otherlv_1= ALL
                    {
                    // InternalSqlParser.g:406:3: ()
                    // InternalSqlParser.g:407:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getLimitAccess().getLimitAction_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_1=(Token)match(input,ALL,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getLimitAccess().getALLKeyword_0_1());
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:421:6: ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? )
                    {
                    // InternalSqlParser.g:421:6: ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? )
                    // InternalSqlParser.g:421:7: ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )?
                    {
                    // InternalSqlParser.g:421:7: ( (lv_l1_2_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:422:1: (lv_l1_2_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:422:1: (lv_l1_2_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:423:3: lv_l1_2_0= RULE_UNSIGNED
                    {
                    lv_l1_2_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_11); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_l1_2_0, grammarAccess.getLimitAccess().getL1UNSIGNEDTerminalRuleCall_1_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getLimitRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"l1",
                              		lv_l1_2_0, 
                              		"com.jaspersoft.studio.data.Sql.UNSIGNED");
                      	    
                    }

                    }


                    }

                    // InternalSqlParser.g:439:2: (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==Comma) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // InternalSqlParser.g:440:2: otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) )
                            {
                            otherlv_3=(Token)match(input,Comma,FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_3, grammarAccess.getLimitAccess().getCommaKeyword_1_1_0());
                                  
                            }
                            // InternalSqlParser.g:444:1: ( (lv_l2_4_0= RULE_UNSIGNED ) )
                            // InternalSqlParser.g:445:1: (lv_l2_4_0= RULE_UNSIGNED )
                            {
                            // InternalSqlParser.g:445:1: (lv_l2_4_0= RULE_UNSIGNED )
                            // InternalSqlParser.g:446:3: lv_l2_4_0= RULE_UNSIGNED
                            {
                            lv_l2_4_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_l2_4_0, grammarAccess.getLimitAccess().getL2UNSIGNEDTerminalRuleCall_1_1_1_0()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getLimitRule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"l2",
                                      		lv_l2_4_0, 
                                      		"com.jaspersoft.studio.data.Sql.UNSIGNED");
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLimit"


    // $ANTLR start "entryRuleSelectQuery"
    // InternalSqlParser.g:470:1: entryRuleSelectQuery returns [EObject current=null] : iv_ruleSelectQuery= ruleSelectQuery EOF ;
    public final EObject entryRuleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectQuery = null;


        try {
            // InternalSqlParser.g:471:2: (iv_ruleSelectQuery= ruleSelectQuery EOF )
            // InternalSqlParser.g:472:2: iv_ruleSelectQuery= ruleSelectQuery EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSelectQueryRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSelectQuery=ruleSelectQuery();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSelectQuery; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSelectQuery"


    // $ANTLR start "ruleSelectQuery"
    // InternalSqlParser.g:479:1: ruleSelectQuery returns [EObject current=null] : (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) ;
    public final EObject ruleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject this_Select_0 = null;

        EObject lv_op_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:482:28: ( (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) )
            // InternalSqlParser.g:483:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            {
            // InternalSqlParser.g:483:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            // InternalSqlParser.g:484:2: this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getSelectQueryAccess().getSelectParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_13);
            this_Select_0=ruleSelect();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_Select_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:495:1: ( (lv_op_1_0= ruleSelectSubSet ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==INTERSECT||LA7_0==EXCEPT||LA7_0==MINUS||LA7_0==UNION) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalSqlParser.g:496:1: (lv_op_1_0= ruleSelectSubSet )
            	    {
            	    // InternalSqlParser.g:496:1: (lv_op_1_0= ruleSelectSubSet )
            	    // InternalSqlParser.g:497:3: lv_op_1_0= ruleSelectSubSet
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSelectQueryAccess().getOpSelectSubSetParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_13);
            	    lv_op_1_0=ruleSelectSubSet();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getSelectQueryRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"op",
            	              		lv_op_1_0, 
            	              		"com.jaspersoft.studio.data.Sql.SelectSubSet");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelectQuery"


    // $ANTLR start "entryRuleSelectSubSet"
    // InternalSqlParser.g:521:1: entryRuleSelectSubSet returns [EObject current=null] : iv_ruleSelectSubSet= ruleSelectSubSet EOF ;
    public final EObject entryRuleSelectSubSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectSubSet = null;


        try {
            // InternalSqlParser.g:522:2: (iv_ruleSelectSubSet= ruleSelectSubSet EOF )
            // InternalSqlParser.g:523:2: iv_ruleSelectSubSet= ruleSelectSubSet EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSelectSubSetRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSelectSubSet=ruleSelectSubSet();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSelectSubSet; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSelectSubSet"


    // $ANTLR start "ruleSelectSubSet"
    // InternalSqlParser.g:530:1: ruleSelectSubSet returns [EObject current=null] : ( ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) ) ) ;
    public final EObject ruleSelectSubSet() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_1=null;
        Token lv_op_0_2=null;
        Token lv_op_0_3=null;
        Token lv_op_0_4=null;
        Token lv_all_1_0=null;
        EObject lv_query_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:533:28: ( ( ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) ) ) )
            // InternalSqlParser.g:534:1: ( ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) ) )
            {
            // InternalSqlParser.g:534:1: ( ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) ) )
            // InternalSqlParser.g:534:2: ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) )
            {
            // InternalSqlParser.g:534:2: ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) )
            // InternalSqlParser.g:535:1: ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) )
            {
            // InternalSqlParser.g:535:1: ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) )
            // InternalSqlParser.g:536:1: (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT )
            {
            // InternalSqlParser.g:536:1: (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT )
            int alt8=4;
            switch ( input.LA(1) ) {
            case UNION:
                {
                alt8=1;
                }
                break;
            case INTERSECT:
                {
                alt8=2;
                }
                break;
            case MINUS:
                {
                alt8=3;
                }
                break;
            case EXCEPT:
                {
                alt8=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalSqlParser.g:537:3: lv_op_0_1= UNION
                    {
                    lv_op_0_1=(Token)match(input,UNION,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_op_0_1, grammarAccess.getSelectSubSetAccess().getOpUNIONKeyword_0_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                      	        }
                             		setWithLastConsumed(current, "op", lv_op_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:550:8: lv_op_0_2= INTERSECT
                    {
                    lv_op_0_2=(Token)match(input,INTERSECT,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_op_0_2, grammarAccess.getSelectSubSetAccess().getOpINTERSECTKeyword_0_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                      	        }
                             		setWithLastConsumed(current, "op", lv_op_0_2, null);
                      	    
                    }

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:563:8: lv_op_0_3= MINUS
                    {
                    lv_op_0_3=(Token)match(input,MINUS,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_op_0_3, grammarAccess.getSelectSubSetAccess().getOpMINUSKeyword_0_0_2());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                      	        }
                             		setWithLastConsumed(current, "op", lv_op_0_3, null);
                      	    
                    }

                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:576:8: lv_op_0_4= EXCEPT
                    {
                    lv_op_0_4=(Token)match(input,EXCEPT,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_op_0_4, grammarAccess.getSelectSubSetAccess().getOpEXCEPTKeyword_0_0_3());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                      	        }
                             		setWithLastConsumed(current, "op", lv_op_0_4, null);
                      	    
                    }

                    }
                    break;

            }


            }


            }

            // InternalSqlParser.g:592:2: ( (lv_all_1_0= ALL ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ALL) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalSqlParser.g:593:1: (lv_all_1_0= ALL )
                    {
                    // InternalSqlParser.g:593:1: (lv_all_1_0= ALL )
                    // InternalSqlParser.g:594:3: lv_all_1_0= ALL
                    {
                    lv_all_1_0=(Token)match(input,ALL,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_all_1_0, grammarAccess.getSelectSubSetAccess().getAllALLKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                      	        }
                             		setWithLastConsumed(current, "all", lv_all_1_0, "ALL");
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:608:3: ( (lv_query_2_0= ruleSelect ) )
            // InternalSqlParser.g:609:1: (lv_query_2_0= ruleSelect )
            {
            // InternalSqlParser.g:609:1: (lv_query_2_0= ruleSelect )
            // InternalSqlParser.g:610:3: lv_query_2_0= ruleSelect
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSelectSubSetAccess().getQuerySelectParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_query_2_0=ruleSelect();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSelectSubSetRule());
              	        }
                     		set(
                     			current, 
                     			"query",
                      		lv_query_2_0, 
                      		"com.jaspersoft.studio.data.Sql.Select");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelectSubSet"


    // $ANTLR start "entryRuleSelect"
    // InternalSqlParser.g:634:1: entryRuleSelect returns [EObject current=null] : iv_ruleSelect= ruleSelect EOF ;
    public final EObject entryRuleSelect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelect = null;


        try {
            // InternalSqlParser.g:635:2: (iv_ruleSelect= ruleSelect EOF )
            // InternalSqlParser.g:636:2: iv_ruleSelect= ruleSelect EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSelectRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSelect=ruleSelect();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSelect; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSelect"


    // $ANTLR start "ruleSelect"
    // InternalSqlParser.g:643:1: ruleSelect returns [EObject current=null] : ( ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITH otherlv_7= TIES )? )? ( (lv_cols_8_0= ruleColumns ) ) otherlv_9= FROM ( (lv_tbl_10_0= ruleTables ) ) (otherlv_11= WHERE ( (lv_whereExpression_12_0= ruleFullExpression ) ) )? (otherlv_13= GROUP otherlv_14= BY ( (lv_groupByEntry_15_0= ruleGroupByColumns ) ) )? (otherlv_16= HAVING ( (lv_havingEntry_17_0= ruleFullExpression ) ) )? (otherlv_18= ORDER otherlv_19= BY ( (lv_orderByEntry_20_0= ruleOrderByColumns ) ) )? (otherlv_21= LIMIT ( (lv_lim_22_0= ruleLimit ) ) )? (otherlv_23= OFFSET ( (lv_offset_24_0= ruleOffset ) ) )? (otherlv_25= FETCH otherlv_26= FIRST ( (lv_fetchFirst_27_0= ruleFetchFirst ) ) )? ) ;
    public final EObject ruleSelect() throws RecognitionException {
        EObject current = null;

        Token lv_select_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token this_INT_3=null;
        Token this_SIGNED_DOUBLE_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        EObject lv_cols_8_0 = null;

        EObject lv_tbl_10_0 = null;

        EObject lv_whereExpression_12_0 = null;

        EObject lv_groupByEntry_15_0 = null;

        EObject lv_havingEntry_17_0 = null;

        EObject lv_orderByEntry_20_0 = null;

        EObject lv_lim_22_0 = null;

        EObject lv_offset_24_0 = null;

        EObject lv_fetchFirst_27_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:646:28: ( ( ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITH otherlv_7= TIES )? )? ( (lv_cols_8_0= ruleColumns ) ) otherlv_9= FROM ( (lv_tbl_10_0= ruleTables ) ) (otherlv_11= WHERE ( (lv_whereExpression_12_0= ruleFullExpression ) ) )? (otherlv_13= GROUP otherlv_14= BY ( (lv_groupByEntry_15_0= ruleGroupByColumns ) ) )? (otherlv_16= HAVING ( (lv_havingEntry_17_0= ruleFullExpression ) ) )? (otherlv_18= ORDER otherlv_19= BY ( (lv_orderByEntry_20_0= ruleOrderByColumns ) ) )? (otherlv_21= LIMIT ( (lv_lim_22_0= ruleLimit ) ) )? (otherlv_23= OFFSET ( (lv_offset_24_0= ruleOffset ) ) )? (otherlv_25= FETCH otherlv_26= FIRST ( (lv_fetchFirst_27_0= ruleFetchFirst ) ) )? ) )
            // InternalSqlParser.g:647:1: ( ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITH otherlv_7= TIES )? )? ( (lv_cols_8_0= ruleColumns ) ) otherlv_9= FROM ( (lv_tbl_10_0= ruleTables ) ) (otherlv_11= WHERE ( (lv_whereExpression_12_0= ruleFullExpression ) ) )? (otherlv_13= GROUP otherlv_14= BY ( (lv_groupByEntry_15_0= ruleGroupByColumns ) ) )? (otherlv_16= HAVING ( (lv_havingEntry_17_0= ruleFullExpression ) ) )? (otherlv_18= ORDER otherlv_19= BY ( (lv_orderByEntry_20_0= ruleOrderByColumns ) ) )? (otherlv_21= LIMIT ( (lv_lim_22_0= ruleLimit ) ) )? (otherlv_23= OFFSET ( (lv_offset_24_0= ruleOffset ) ) )? (otherlv_25= FETCH otherlv_26= FIRST ( (lv_fetchFirst_27_0= ruleFetchFirst ) ) )? )
            {
            // InternalSqlParser.g:647:1: ( ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITH otherlv_7= TIES )? )? ( (lv_cols_8_0= ruleColumns ) ) otherlv_9= FROM ( (lv_tbl_10_0= ruleTables ) ) (otherlv_11= WHERE ( (lv_whereExpression_12_0= ruleFullExpression ) ) )? (otherlv_13= GROUP otherlv_14= BY ( (lv_groupByEntry_15_0= ruleGroupByColumns ) ) )? (otherlv_16= HAVING ( (lv_havingEntry_17_0= ruleFullExpression ) ) )? (otherlv_18= ORDER otherlv_19= BY ( (lv_orderByEntry_20_0= ruleOrderByColumns ) ) )? (otherlv_21= LIMIT ( (lv_lim_22_0= ruleLimit ) ) )? (otherlv_23= OFFSET ( (lv_offset_24_0= ruleOffset ) ) )? (otherlv_25= FETCH otherlv_26= FIRST ( (lv_fetchFirst_27_0= ruleFetchFirst ) ) )? )
            // InternalSqlParser.g:647:2: ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITH otherlv_7= TIES )? )? ( (lv_cols_8_0= ruleColumns ) ) otherlv_9= FROM ( (lv_tbl_10_0= ruleTables ) ) (otherlv_11= WHERE ( (lv_whereExpression_12_0= ruleFullExpression ) ) )? (otherlv_13= GROUP otherlv_14= BY ( (lv_groupByEntry_15_0= ruleGroupByColumns ) ) )? (otherlv_16= HAVING ( (lv_havingEntry_17_0= ruleFullExpression ) ) )? (otherlv_18= ORDER otherlv_19= BY ( (lv_orderByEntry_20_0= ruleOrderByColumns ) ) )? (otherlv_21= LIMIT ( (lv_lim_22_0= ruleLimit ) ) )? (otherlv_23= OFFSET ( (lv_offset_24_0= ruleOffset ) ) )? (otherlv_25= FETCH otherlv_26= FIRST ( (lv_fetchFirst_27_0= ruleFetchFirst ) ) )?
            {
            // InternalSqlParser.g:647:2: ( (lv_select_0_0= SELECT ) )
            // InternalSqlParser.g:648:1: (lv_select_0_0= SELECT )
            {
            // InternalSqlParser.g:648:1: (lv_select_0_0= SELECT )
            // InternalSqlParser.g:649:3: lv_select_0_0= SELECT
            {
            lv_select_0_0=(Token)match(input,SELECT,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_select_0_0, grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getSelectRule());
              	        }
                     		setWithLastConsumed(current, "select", lv_select_0_0, "SELECT");
              	    
            }

            }


            }

            // InternalSqlParser.g:663:2: (otherlv_1= DISTINCT )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==DISTINCT) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalSqlParser.g:664:2: otherlv_1= DISTINCT
                    {
                    otherlv_1=(Token)match(input,DISTINCT,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                          
                    }

                    }
                    break;

            }

            // InternalSqlParser.g:668:3: (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITH otherlv_7= TIES )? )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==TOP) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalSqlParser.g:669:2: otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITH otherlv_7= TIES )?
                    {
                    otherlv_2=(Token)match(input,TOP,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getSelectAccess().getTOPKeyword_2_0());
                          
                    }
                    // InternalSqlParser.g:673:1: (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE )
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==RULE_INT) ) {
                        alt11=1;
                    }
                    else if ( (LA11_0==RULE_SIGNED_DOUBLE) ) {
                        alt11=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }
                    switch (alt11) {
                        case 1 :
                            // InternalSqlParser.g:673:2: this_INT_3= RULE_INT
                            {
                            this_INT_3=(Token)match(input,RULE_INT,FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {
                               
                                  newLeafNode(this_INT_3, grammarAccess.getSelectAccess().getINTTerminalRuleCall_2_1_0()); 
                                  
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:678:6: this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE
                            {
                            this_SIGNED_DOUBLE_4=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {
                               
                                  newLeafNode(this_SIGNED_DOUBLE_4, grammarAccess.getSelectAccess().getSIGNED_DOUBLETerminalRuleCall_2_1_1()); 
                                  
                            }

                            }
                            break;

                    }

                    // InternalSqlParser.g:682:2: (otherlv_5= PERCENT )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==PERCENT) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // InternalSqlParser.g:683:2: otherlv_5= PERCENT
                            {
                            otherlv_5=(Token)match(input,PERCENT,FOLLOW_18); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getSelectAccess().getPERCENTKeyword_2_2());
                                  
                            }

                            }
                            break;

                    }

                    // InternalSqlParser.g:687:3: (otherlv_6= WITH otherlv_7= TIES )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==WITH) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // InternalSqlParser.g:688:2: otherlv_6= WITH otherlv_7= TIES
                            {
                            otherlv_6=(Token)match(input,WITH,FOLLOW_19); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_6, grammarAccess.getSelectAccess().getWITHKeyword_2_3_0());
                                  
                            }
                            otherlv_7=(Token)match(input,TIES,FOLLOW_15); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_7, grammarAccess.getSelectAccess().getTIESKeyword_2_3_1());
                                  
                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:697:5: ( (lv_cols_8_0= ruleColumns ) )
            // InternalSqlParser.g:698:1: (lv_cols_8_0= ruleColumns )
            {
            // InternalSqlParser.g:698:1: (lv_cols_8_0= ruleColumns )
            // InternalSqlParser.g:699:3: lv_cols_8_0= ruleColumns
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_20);
            lv_cols_8_0=ruleColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSelectRule());
              	        }
                     		set(
                     			current, 
                     			"cols",
                      		lv_cols_8_0, 
                      		"com.jaspersoft.studio.data.Sql.Columns");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_9=(Token)match(input,FROM,FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getSelectAccess().getFROMKeyword_4());
                  
            }
            // InternalSqlParser.g:720:1: ( (lv_tbl_10_0= ruleTables ) )
            // InternalSqlParser.g:721:1: (lv_tbl_10_0= ruleTables )
            {
            // InternalSqlParser.g:721:1: (lv_tbl_10_0= ruleTables )
            // InternalSqlParser.g:722:3: lv_tbl_10_0= ruleTables
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_22);
            lv_tbl_10_0=ruleTables();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSelectRule());
              	        }
                     		set(
                     			current, 
                     			"tbl",
                      		lv_tbl_10_0, 
                      		"com.jaspersoft.studio.data.Sql.Tables");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:738:2: (otherlv_11= WHERE ( (lv_whereExpression_12_0= ruleFullExpression ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==WHERE) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalSqlParser.g:739:2: otherlv_11= WHERE ( (lv_whereExpression_12_0= ruleFullExpression ) )
                    {
                    otherlv_11=(Token)match(input,WHERE,FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getSelectAccess().getWHEREKeyword_6_0());
                          
                    }
                    // InternalSqlParser.g:743:1: ( (lv_whereExpression_12_0= ruleFullExpression ) )
                    // InternalSqlParser.g:744:1: (lv_whereExpression_12_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:744:1: (lv_whereExpression_12_0= ruleFullExpression )
                    // InternalSqlParser.g:745:3: lv_whereExpression_12_0= ruleFullExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_24);
                    lv_whereExpression_12_0=ruleFullExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSelectRule());
                      	        }
                             		set(
                             			current, 
                             			"whereExpression",
                              		lv_whereExpression_12_0, 
                              		"com.jaspersoft.studio.data.Sql.FullExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:761:4: (otherlv_13= GROUP otherlv_14= BY ( (lv_groupByEntry_15_0= ruleGroupByColumns ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==GROUP) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalSqlParser.g:762:2: otherlv_13= GROUP otherlv_14= BY ( (lv_groupByEntry_15_0= ruleGroupByColumns ) )
                    {
                    otherlv_13=(Token)match(input,GROUP,FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getSelectAccess().getGROUPKeyword_7_0());
                          
                    }
                    otherlv_14=(Token)match(input,BY,FOLLOW_26); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getSelectAccess().getBYKeyword_7_1());
                          
                    }
                    // InternalSqlParser.g:771:1: ( (lv_groupByEntry_15_0= ruleGroupByColumns ) )
                    // InternalSqlParser.g:772:1: (lv_groupByEntry_15_0= ruleGroupByColumns )
                    {
                    // InternalSqlParser.g:772:1: (lv_groupByEntry_15_0= ruleGroupByColumns )
                    // InternalSqlParser.g:773:3: lv_groupByEntry_15_0= ruleGroupByColumns
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_7_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_27);
                    lv_groupByEntry_15_0=ruleGroupByColumns();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSelectRule());
                      	        }
                             		set(
                             			current, 
                             			"groupByEntry",
                              		lv_groupByEntry_15_0, 
                              		"com.jaspersoft.studio.data.Sql.GroupByColumns");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:789:4: (otherlv_16= HAVING ( (lv_havingEntry_17_0= ruleFullExpression ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==HAVING) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalSqlParser.g:790:2: otherlv_16= HAVING ( (lv_havingEntry_17_0= ruleFullExpression ) )
                    {
                    otherlv_16=(Token)match(input,HAVING,FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getSelectAccess().getHAVINGKeyword_8_0());
                          
                    }
                    // InternalSqlParser.g:794:1: ( (lv_havingEntry_17_0= ruleFullExpression ) )
                    // InternalSqlParser.g:795:1: (lv_havingEntry_17_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:795:1: (lv_havingEntry_17_0= ruleFullExpression )
                    // InternalSqlParser.g:796:3: lv_havingEntry_17_0= ruleFullExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_8_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_havingEntry_17_0=ruleFullExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSelectRule());
                      	        }
                             		set(
                             			current, 
                             			"havingEntry",
                              		lv_havingEntry_17_0, 
                              		"com.jaspersoft.studio.data.Sql.FullExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:812:4: (otherlv_18= ORDER otherlv_19= BY ( (lv_orderByEntry_20_0= ruleOrderByColumns ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==ORDER) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalSqlParser.g:813:2: otherlv_18= ORDER otherlv_19= BY ( (lv_orderByEntry_20_0= ruleOrderByColumns ) )
                    {
                    otherlv_18=(Token)match(input,ORDER,FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_18, grammarAccess.getSelectAccess().getORDERKeyword_9_0());
                          
                    }
                    otherlv_19=(Token)match(input,BY,FOLLOW_26); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_19, grammarAccess.getSelectAccess().getBYKeyword_9_1());
                          
                    }
                    // InternalSqlParser.g:822:1: ( (lv_orderByEntry_20_0= ruleOrderByColumns ) )
                    // InternalSqlParser.g:823:1: (lv_orderByEntry_20_0= ruleOrderByColumns )
                    {
                    // InternalSqlParser.g:823:1: (lv_orderByEntry_20_0= ruleOrderByColumns )
                    // InternalSqlParser.g:824:3: lv_orderByEntry_20_0= ruleOrderByColumns
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSelectAccess().getOrderByEntryOrderByColumnsParserRuleCall_9_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_29);
                    lv_orderByEntry_20_0=ruleOrderByColumns();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSelectRule());
                      	        }
                             		set(
                             			current, 
                             			"orderByEntry",
                              		lv_orderByEntry_20_0, 
                              		"com.jaspersoft.studio.data.Sql.OrderByColumns");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:840:4: (otherlv_21= LIMIT ( (lv_lim_22_0= ruleLimit ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==LIMIT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalSqlParser.g:841:2: otherlv_21= LIMIT ( (lv_lim_22_0= ruleLimit ) )
                    {
                    otherlv_21=(Token)match(input,LIMIT,FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_21, grammarAccess.getSelectAccess().getLIMITKeyword_10_0());
                          
                    }
                    // InternalSqlParser.g:845:1: ( (lv_lim_22_0= ruleLimit ) )
                    // InternalSqlParser.g:846:1: (lv_lim_22_0= ruleLimit )
                    {
                    // InternalSqlParser.g:846:1: (lv_lim_22_0= ruleLimit )
                    // InternalSqlParser.g:847:3: lv_lim_22_0= ruleLimit
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSelectAccess().getLimLimitParserRuleCall_10_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
                    lv_lim_22_0=ruleLimit();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSelectRule());
                      	        }
                             		set(
                             			current, 
                             			"lim",
                              		lv_lim_22_0, 
                              		"com.jaspersoft.studio.data.Sql.Limit");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:863:4: (otherlv_23= OFFSET ( (lv_offset_24_0= ruleOffset ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==OFFSET) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalSqlParser.g:864:2: otherlv_23= OFFSET ( (lv_offset_24_0= ruleOffset ) )
                    {
                    otherlv_23=(Token)match(input,OFFSET,FOLLOW_32); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_23, grammarAccess.getSelectAccess().getOFFSETKeyword_11_0());
                          
                    }
                    // InternalSqlParser.g:868:1: ( (lv_offset_24_0= ruleOffset ) )
                    // InternalSqlParser.g:869:1: (lv_offset_24_0= ruleOffset )
                    {
                    // InternalSqlParser.g:869:1: (lv_offset_24_0= ruleOffset )
                    // InternalSqlParser.g:870:3: lv_offset_24_0= ruleOffset
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSelectAccess().getOffsetOffsetParserRuleCall_11_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_33);
                    lv_offset_24_0=ruleOffset();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSelectRule());
                      	        }
                             		set(
                             			current, 
                             			"offset",
                              		lv_offset_24_0, 
                              		"com.jaspersoft.studio.data.Sql.Offset");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:886:4: (otherlv_25= FETCH otherlv_26= FIRST ( (lv_fetchFirst_27_0= ruleFetchFirst ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==FETCH) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalSqlParser.g:887:2: otherlv_25= FETCH otherlv_26= FIRST ( (lv_fetchFirst_27_0= ruleFetchFirst ) )
                    {
                    otherlv_25=(Token)match(input,FETCH,FOLLOW_34); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_25, grammarAccess.getSelectAccess().getFETCHKeyword_12_0());
                          
                    }
                    otherlv_26=(Token)match(input,FIRST,FOLLOW_12); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_26, grammarAccess.getSelectAccess().getFIRSTKeyword_12_1());
                          
                    }
                    // InternalSqlParser.g:896:1: ( (lv_fetchFirst_27_0= ruleFetchFirst ) )
                    // InternalSqlParser.g:897:1: (lv_fetchFirst_27_0= ruleFetchFirst )
                    {
                    // InternalSqlParser.g:897:1: (lv_fetchFirst_27_0= ruleFetchFirst )
                    // InternalSqlParser.g:898:3: lv_fetchFirst_27_0= ruleFetchFirst
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSelectAccess().getFetchFirstFetchFirstParserRuleCall_12_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_fetchFirst_27_0=ruleFetchFirst();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSelectRule());
                      	        }
                             		set(
                             			current, 
                             			"fetchFirst",
                              		lv_fetchFirst_27_0, 
                              		"com.jaspersoft.studio.data.Sql.FetchFirst");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelect"


    // $ANTLR start "entryRuleColumns"
    // InternalSqlParser.g:922:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // InternalSqlParser.g:923:2: (iv_ruleColumns= ruleColumns EOF )
            // InternalSqlParser.g:924:2: iv_ruleColumns= ruleColumns EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getColumnsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleColumns=ruleColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleColumns; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleColumns"


    // $ANTLR start "ruleColumns"
    // InternalSqlParser.g:931:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:934:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // InternalSqlParser.g:935:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // InternalSqlParser.g:935:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // InternalSqlParser.g:936:2: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_ColumnOrAlias_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:947:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==Comma) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalSqlParser.g:947:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // InternalSqlParser.g:947:2: ()
                    // InternalSqlParser.g:948:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:956:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt22=0;
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==Comma) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // InternalSqlParser.g:957:2: otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:961:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // InternalSqlParser.g:962:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // InternalSqlParser.g:962:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // InternalSqlParser.g:963:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleColumnOrAlias();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getColumnsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.ColumnOrAlias");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt22 >= 1 ) break loop22;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(22, input);
                                throw eee;
                        }
                        cnt22++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleColumns"


    // $ANTLR start "entryRuleColumnOrAlias"
    // InternalSqlParser.g:987:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // InternalSqlParser.g:988:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // InternalSqlParser.g:989:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleColumnOrAlias; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleColumnOrAlias"


    // $ANTLR start "ruleColumnOrAlias"
    // InternalSqlParser.g:996:1: ruleColumnOrAlias returns [EObject current=null] : ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_1_0=null;
        Token lv_allCols_3_0=null;
        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_2_0 = null;

        EObject lv_dbAllCols_4_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:999:28: ( ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) )
            // InternalSqlParser.g:1000:1: ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            {
            // InternalSqlParser.g:1000:1: ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            int alt26=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==FullStop) ) {
                    int LA26_6 = input.LA(3);

                    if ( (LA26_6==RULE_STAR) ) {
                        alt26=3;
                    }
                    else if ( ((LA26_6>=RULE_STRING && LA26_6<=RULE_ID)) ) {
                        alt26=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA26_1==EOF||LA26_1==FROM||LA26_1==LeftParenthesisPlusSignRightParenthesis||LA26_1==AS||(LA26_1>=VerticalLineVerticalLine && LA26_1<=HyphenMinus)||LA26_1==Solidus||LA26_1==RULE_STAR||(LA26_1>=RULE_STRING && LA26_1<=RULE_ID)) ) {
                    alt26=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_DBNAME:
                {
                int LA26_2 = input.LA(2);

                if ( (LA26_2==FullStop) ) {
                    int LA26_6 = input.LA(3);

                    if ( (LA26_6==RULE_STAR) ) {
                        alt26=3;
                    }
                    else if ( ((LA26_6>=RULE_STRING && LA26_6<=RULE_ID)) ) {
                        alt26=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA26_2==EOF||LA26_2==FROM||LA26_2==LeftParenthesisPlusSignRightParenthesis||LA26_2==AS||LA26_2==VerticalLineVerticalLine||(LA26_2>=RightParenthesis && LA26_2<=HyphenMinus)||LA26_2==Solidus||LA26_2==RULE_STAR||(LA26_2>=RULE_STRING && LA26_2<=RULE_ID)) ) {
                    alt26=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
                {
                int LA26_3 = input.LA(2);

                if ( (LA26_3==FullStop) ) {
                    int LA26_6 = input.LA(3);

                    if ( (LA26_6==RULE_STAR) ) {
                        alt26=3;
                    }
                    else if ( ((LA26_6>=RULE_STRING && LA26_6<=RULE_ID)) ) {
                        alt26=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA26_3==EOF||LA26_3==FROM||LA26_3==LeftParenthesisPlusSignRightParenthesis||LA26_3==AS||LA26_3==VerticalLineVerticalLine||(LA26_3>=RightParenthesis && LA26_3<=HyphenMinus)||LA26_3==Solidus||LA26_3==RULE_STAR||(LA26_3>=RULE_STRING && LA26_3<=RULE_ID)) ) {
                    alt26=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 3, input);

                    throw nvae;
                }
                }
                break;
            case EXTRACT:
            case CAST:
            case CASE:
            case LeftParenthesis:
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt26=1;
                }
                break;
            case RULE_STAR:
                {
                alt26=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // InternalSqlParser.g:1000:2: ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    {
                    // InternalSqlParser.g:1000:2: ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    // InternalSqlParser.g:1000:3: ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    {
                    // InternalSqlParser.g:1000:3: ( (lv_ce_0_0= ruleOperandGroup ) )
                    // InternalSqlParser.g:1001:1: (lv_ce_0_0= ruleOperandGroup )
                    {
                    // InternalSqlParser.g:1001:1: (lv_ce_0_0= ruleOperandGroup )
                    // InternalSqlParser.g:1002:3: lv_ce_0_0= ruleOperandGroup
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getCeOperandGroupParserRuleCall_0_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_35);
                    lv_ce_0_0=ruleOperandGroup();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                      	        }
                             		set(
                             			current, 
                             			"ce",
                              		lv_ce_0_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandGroup");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalSqlParser.g:1018:2: ( (lv_alias_1_0= AS ) )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==AS) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // InternalSqlParser.g:1019:1: (lv_alias_1_0= AS )
                            {
                            // InternalSqlParser.g:1019:1: (lv_alias_1_0= AS )
                            // InternalSqlParser.g:1020:3: lv_alias_1_0= AS
                            {
                            lv_alias_1_0=(Token)match(input,AS,FOLLOW_36); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_alias_1_0, grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getColumnOrAliasRule());
                              	        }
                                     		setWithLastConsumed(current, "alias", lv_alias_1_0, "AS");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    // InternalSqlParser.g:1034:3: ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( ((LA25_0>=RULE_STRING && LA25_0<=RULE_ID)) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // InternalSqlParser.g:1035:1: (lv_colAlias_2_0= ruleDbObjectName )
                            {
                            // InternalSqlParser.g:1035:1: (lv_colAlias_2_0= ruleDbObjectName )
                            // InternalSqlParser.g:1036:3: lv_colAlias_2_0= ruleDbObjectName
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_2);
                            lv_colAlias_2_0=ruleDbObjectName();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"colAlias",
                                      		lv_colAlias_2_0, 
                                      		"com.jaspersoft.studio.data.Sql.DbObjectName");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:1053:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    {
                    // InternalSqlParser.g:1053:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    // InternalSqlParser.g:1054:1: (lv_allCols_3_0= RULE_STAR )
                    {
                    // InternalSqlParser.g:1054:1: (lv_allCols_3_0= RULE_STAR )
                    // InternalSqlParser.g:1055:3: lv_allCols_3_0= RULE_STAR
                    {
                    lv_allCols_3_0=(Token)match(input,RULE_STAR,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_allCols_3_0, grammarAccess.getColumnOrAliasAccess().getAllColsSTARTerminalRuleCall_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getColumnOrAliasRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"allCols",
                              		lv_allCols_3_0, 
                              		"com.jaspersoft.studio.data.Sql.STAR");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:1072:6: ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) )
                    {
                    // InternalSqlParser.g:1072:6: ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) )
                    // InternalSqlParser.g:1073:1: (lv_dbAllCols_4_0= ruleDbObjectNameAll )
                    {
                    // InternalSqlParser.g:1073:1: (lv_dbAllCols_4_0= ruleDbObjectNameAll )
                    // InternalSqlParser.g:1074:3: lv_dbAllCols_4_0= ruleDbObjectNameAll
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getDbAllColsDbObjectNameAllParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_dbAllCols_4_0=ruleDbObjectNameAll();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                      	        }
                             		set(
                             			current, 
                             			"dbAllCols",
                              		lv_dbAllCols_4_0, 
                              		"com.jaspersoft.studio.data.Sql.DbObjectNameAll");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleColumnOrAlias"


    // $ANTLR start "entryRuleColumnFull"
    // InternalSqlParser.g:1098:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // InternalSqlParser.g:1099:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // InternalSqlParser.g:1100:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getColumnFullRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleColumnFull; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleColumnFull"


    // $ANTLR start "ruleColumnFull"
    // InternalSqlParser.g:1107:1: ruleColumnFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1110:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:1111:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:1111:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:1112:2: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_37);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_DbObjectName_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:1123:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==FullStop) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalSqlParser.g:1123:2: () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:1123:2: ()
                    // InternalSqlParser.g:1124:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getColumnFullAccess().getColEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:1132:2: (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==FullStop) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // InternalSqlParser.g:1133:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:1137:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:1138:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:1138:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:1139:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_37);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getColumnFullRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.DbObjectName");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleColumnFull"


    // $ANTLR start "entryRuleTables"
    // InternalSqlParser.g:1163:1: entryRuleTables returns [EObject current=null] : iv_ruleTables= ruleTables EOF ;
    public final EObject entryRuleTables() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTables = null;


        try {
            // InternalSqlParser.g:1164:2: (iv_ruleTables= ruleTables EOF )
            // InternalSqlParser.g:1165:2: iv_ruleTables= ruleTables EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTablesRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTables=ruleTables();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTables; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTables"


    // $ANTLR start "ruleTables"
    // InternalSqlParser.g:1172:1: ruleTables returns [EObject current=null] : (this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) ;
    public final EObject ruleTables() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_FromTable_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1175:28: ( (this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) )
            // InternalSqlParser.g:1176:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            {
            // InternalSqlParser.g:1176:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            // InternalSqlParser.g:1177:2: this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_FromTable_0=ruleFromTable();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_FromTable_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:1188:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==Comma) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalSqlParser.g:1188:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+
                    {
                    // InternalSqlParser.g:1188:2: ()
                    // InternalSqlParser.g:1189:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:1197:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+
                    int cnt29=0;
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==Comma) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // InternalSqlParser.g:1198:2: otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_21); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:1202:1: ( (lv_entries_3_0= ruleFromTable ) )
                    	    // InternalSqlParser.g:1203:1: (lv_entries_3_0= ruleFromTable )
                    	    {
                    	    // InternalSqlParser.g:1203:1: (lv_entries_3_0= ruleFromTable )
                    	    // InternalSqlParser.g:1204:3: lv_entries_3_0= ruleFromTable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleFromTable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTablesRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.FromTable");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt29 >= 1 ) break loop29;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(29, input);
                                throw eee;
                        }
                        cnt29++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTables"


    // $ANTLR start "entryRuleFromTable"
    // InternalSqlParser.g:1228:1: entryRuleFromTable returns [EObject current=null] : iv_ruleFromTable= ruleFromTable EOF ;
    public final EObject entryRuleFromTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTable = null;


        try {
            // InternalSqlParser.g:1229:2: (iv_ruleFromTable= ruleFromTable EOF )
            // InternalSqlParser.g:1230:2: iv_ruleFromTable= ruleFromTable EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFromTableRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFromTable=ruleFromTable();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFromTable; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFromTable"


    // $ANTLR start "ruleFromTable"
    // InternalSqlParser.g:1237:1: ruleFromTable returns [EObject current=null] : ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) ;
    public final EObject ruleFromTable() throws RecognitionException {
        EObject current = null;

        EObject lv_table_0_0 = null;

        EObject lv_fjoin_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1240:28: ( ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) )
            // InternalSqlParser.g:1241:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            {
            // InternalSqlParser.g:1241:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            // InternalSqlParser.g:1241:2: ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            {
            // InternalSqlParser.g:1241:2: ( (lv_table_0_0= ruleTableOrAlias ) )
            // InternalSqlParser.g:1242:1: (lv_table_0_0= ruleTableOrAlias )
            {
            // InternalSqlParser.g:1242:1: (lv_table_0_0= ruleTableOrAlias )
            // InternalSqlParser.g:1243:3: lv_table_0_0= ruleTableOrAlias
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_38);
            lv_table_0_0=ruleTableOrAlias();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFromTableRule());
              	        }
                     		set(
                     			current, 
                     			"table",
                      		lv_table_0_0, 
                      		"com.jaspersoft.studio.data.Sql.TableOrAlias");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:1259:2: ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==STRAIGHT_JOIN||LA31_0==NATURAL||LA31_0==CROSS||LA31_0==INNER||LA31_0==RIGHT||LA31_0==FULL||LA31_0==JOIN||LA31_0==LEFT) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalSqlParser.g:1260:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    {
            	    // InternalSqlParser.g:1260:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    // InternalSqlParser.g:1261:3: lv_fjoin_1_0= ruleFromTableJoin
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getFromTableAccess().getFjoinFromTableJoinParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_38);
            	    lv_fjoin_1_0=ruleFromTableJoin();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getFromTableRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"fjoin",
            	              		lv_fjoin_1_0, 
            	              		"com.jaspersoft.studio.data.Sql.FromTableJoin");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFromTable"


    // $ANTLR start "entryRuleFromTableJoin"
    // InternalSqlParser.g:1285:1: entryRuleFromTableJoin returns [EObject current=null] : iv_ruleFromTableJoin= ruleFromTableJoin EOF ;
    public final EObject entryRuleFromTableJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTableJoin = null;


        try {
            // InternalSqlParser.g:1286:2: (iv_ruleFromTableJoin= ruleFromTableJoin EOF )
            // InternalSqlParser.g:1287:2: iv_ruleFromTableJoin= ruleFromTableJoin EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFromTableJoinRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFromTableJoin=ruleFromTableJoin();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFromTableJoin; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFromTableJoin"


    // $ANTLR start "ruleFromTableJoin"
    // InternalSqlParser.g:1294:1: ruleFromTableJoin returns [EObject current=null] : ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) ) ) ;
    public final EObject ruleFromTableJoin() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_join_0_0 = null;

        EObject lv_onTable_1_0 = null;

        EObject lv_joinExpr_3_0 = null;

        EObject lv_joinCond_4_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1297:28: ( ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) ) ) )
            // InternalSqlParser.g:1298:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) ) )
            {
            // InternalSqlParser.g:1298:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) ) )
            // InternalSqlParser.g:1298:2: ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) )
            {
            // InternalSqlParser.g:1298:2: ( (lv_join_0_0= ruleJoinType ) )
            // InternalSqlParser.g:1299:1: (lv_join_0_0= ruleJoinType )
            {
            // InternalSqlParser.g:1299:1: (lv_join_0_0= ruleJoinType )
            // InternalSqlParser.g:1300:3: lv_join_0_0= ruleJoinType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinJoinTypeParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_21);
            lv_join_0_0=ruleJoinType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFromTableJoinRule());
              	        }
                     		set(
                     			current, 
                     			"join",
                      		lv_join_0_0, 
                      		"com.jaspersoft.studio.data.Sql.JoinType");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:1316:2: ( (lv_onTable_1_0= ruleTableOrAlias ) )
            // InternalSqlParser.g:1317:1: (lv_onTable_1_0= ruleTableOrAlias )
            {
            // InternalSqlParser.g:1317:1: (lv_onTable_1_0= ruleTableOrAlias )
            // InternalSqlParser.g:1318:3: lv_onTable_1_0= ruleTableOrAlias
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getOnTableTableOrAliasParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_39);
            lv_onTable_1_0=ruleTableOrAlias();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFromTableJoinRule());
              	        }
                     		set(
                     			current, 
                     			"onTable",
                      		lv_onTable_1_0, 
                      		"com.jaspersoft.studio.data.Sql.TableOrAlias");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:1334:2: ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==ON) ) {
                alt32=1;
            }
            else if ( (LA32_0==USING) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // InternalSqlParser.g:1334:3: (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
                    {
                    // InternalSqlParser.g:1334:3: (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
                    // InternalSqlParser.g:1335:2: otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) )
                    {
                    otherlv_2=(Token)match(input,ON,FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getFromTableJoinAccess().getONKeyword_2_0_0());
                          
                    }
                    // InternalSqlParser.g:1339:1: ( (lv_joinExpr_3_0= ruleFullExpression ) )
                    // InternalSqlParser.g:1340:1: (lv_joinExpr_3_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:1340:1: (lv_joinExpr_3_0= ruleFullExpression )
                    // InternalSqlParser.g:1341:3: lv_joinExpr_3_0= ruleFullExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinExprFullExpressionParserRuleCall_2_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_joinExpr_3_0=ruleFullExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFromTableJoinRule());
                      	        }
                             		set(
                             			current, 
                             			"joinExpr",
                              		lv_joinExpr_3_0, 
                              		"com.jaspersoft.studio.data.Sql.FullExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:1358:6: ( (lv_joinCond_4_0= ruleJoinCondition ) )
                    {
                    // InternalSqlParser.g:1358:6: ( (lv_joinCond_4_0= ruleJoinCondition ) )
                    // InternalSqlParser.g:1359:1: (lv_joinCond_4_0= ruleJoinCondition )
                    {
                    // InternalSqlParser.g:1359:1: (lv_joinCond_4_0= ruleJoinCondition )
                    // InternalSqlParser.g:1360:3: lv_joinCond_4_0= ruleJoinCondition
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinCondJoinConditionParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_joinCond_4_0=ruleJoinCondition();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFromTableJoinRule());
                      	        }
                             		set(
                             			current, 
                             			"joinCond",
                              		lv_joinCond_4_0, 
                              		"com.jaspersoft.studio.data.Sql.JoinCondition");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFromTableJoin"


    // $ANTLR start "entryRuleJoinCondition"
    // InternalSqlParser.g:1384:1: entryRuleJoinCondition returns [EObject current=null] : iv_ruleJoinCondition= ruleJoinCondition EOF ;
    public final EObject entryRuleJoinCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJoinCondition = null;


        try {
            // InternalSqlParser.g:1385:2: (iv_ruleJoinCondition= ruleJoinCondition EOF )
            // InternalSqlParser.g:1386:2: iv_ruleJoinCondition= ruleJoinCondition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJoinConditionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJoinCondition=ruleJoinCondition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJoinCondition; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJoinCondition"


    // $ANTLR start "ruleJoinCondition"
    // InternalSqlParser.g:1393:1: ruleJoinCondition returns [EObject current=null] : (otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleJoinCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_useCols_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1396:28: ( (otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:1397:1: (otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:1397:1: (otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:1398:2: otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis
            {
            otherlv_0=(Token)match(input,USING,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getJoinConditionAccess().getUSINGKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJoinConditionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalSqlParser.g:1407:1: ( (lv_useCols_2_0= ruleUsingCols ) )
            // InternalSqlParser.g:1408:1: (lv_useCols_2_0= ruleUsingCols )
            {
            // InternalSqlParser.g:1408:1: (lv_useCols_2_0= ruleUsingCols )
            // InternalSqlParser.g:1409:3: lv_useCols_2_0= ruleUsingCols
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJoinConditionAccess().getUseColsUsingColsParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_useCols_2_0=ruleUsingCols();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getJoinConditionRule());
              	        }
                     		set(
                     			current, 
                     			"useCols",
                      		lv_useCols_2_0, 
                      		"com.jaspersoft.studio.data.Sql.UsingCols");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getJoinConditionAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJoinCondition"


    // $ANTLR start "entryRuleUsingCols"
    // InternalSqlParser.g:1438:1: entryRuleUsingCols returns [EObject current=null] : iv_ruleUsingCols= ruleUsingCols EOF ;
    public final EObject entryRuleUsingCols() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUsingCols = null;


        try {
            // InternalSqlParser.g:1439:2: (iv_ruleUsingCols= ruleUsingCols EOF )
            // InternalSqlParser.g:1440:2: iv_ruleUsingCols= ruleUsingCols EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUsingColsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUsingCols=ruleUsingCols();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUsingCols; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUsingCols"


    // $ANTLR start "ruleUsingCols"
    // InternalSqlParser.g:1447:1: ruleUsingCols returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleUsingCols() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1450:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:1451:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:1451:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:1452:2: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getUsingColsAccess().getDbObjectNameParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_DbObjectName_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:1463:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==Comma) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalSqlParser.g:1463:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:1463:2: ()
                    // InternalSqlParser.g:1464:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getUsingColsAccess().getUsingColsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:1472:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt33=0;
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==Comma) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // InternalSqlParser.g:1473:2: otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getUsingColsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:1477:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:1478:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:1478:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:1479:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getUsingColsAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getUsingColsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.DbObjectName");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt33 >= 1 ) break loop33;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(33, input);
                                throw eee;
                        }
                        cnt33++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUsingCols"


    // $ANTLR start "entryRuleTableOrAlias"
    // InternalSqlParser.g:1503:1: entryRuleTableOrAlias returns [EObject current=null] : iv_ruleTableOrAlias= ruleTableOrAlias EOF ;
    public final EObject entryRuleTableOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableOrAlias = null;


        try {
            // InternalSqlParser.g:1504:2: (iv_ruleTableOrAlias= ruleTableOrAlias EOF )
            // InternalSqlParser.g:1505:2: iv_ruleTableOrAlias= ruleTableOrAlias EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTableOrAliasRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTableOrAlias; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTableOrAlias"


    // $ANTLR start "ruleTableOrAlias"
    // InternalSqlParser.g:1512:1: ruleTableOrAlias returns [EObject current=null] : ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleTableOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_5_0=null;
        EObject lv_tfull_0_0 = null;

        EObject lv_sq_1_0 = null;

        EObject lv_values_2_0 = null;

        EObject lv_pivot_3_0 = null;

        EObject lv_unpivot_4_0 = null;

        EObject lv_tblAlias_6_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1515:28: ( ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )? ) )
            // InternalSqlParser.g:1516:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )? )
            {
            // InternalSqlParser.g:1516:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )? )
            // InternalSqlParser.g:1516:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )?
            {
            // InternalSqlParser.g:1516:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) )
            int alt35=3;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=RULE_STRING && LA35_0<=RULE_ID)) ) {
                alt35=1;
            }
            else if ( (LA35_0==LeftParenthesis) ) {
                int LA35_2 = input.LA(2);

                if ( (LA35_2==SELECT) ) {
                    alt35=2;
                }
                else if ( (LA35_2==VALUES) ) {
                    alt35=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // InternalSqlParser.g:1516:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    {
                    // InternalSqlParser.g:1516:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    // InternalSqlParser.g:1517:1: (lv_tfull_0_0= ruleTableFull )
                    {
                    // InternalSqlParser.g:1517:1: (lv_tfull_0_0= ruleTableFull )
                    // InternalSqlParser.g:1518:3: lv_tfull_0_0= ruleTableFull
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_40);
                    lv_tfull_0_0=ruleTableFull();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                      	        }
                             		set(
                             			current, 
                             			"tfull",
                              		lv_tfull_0_0, 
                              		"com.jaspersoft.studio.data.Sql.TableFull");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:1535:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:1535:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:1536:1: (lv_sq_1_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:1536:1: (lv_sq_1_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:1537:3: lv_sq_1_0= ruleSubQueryOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getSqSubQueryOperandParserRuleCall_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_40);
                    lv_sq_1_0=ruleSubQueryOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                      	        }
                             		set(
                             			current, 
                             			"sq",
                              		lv_sq_1_0, 
                              		"com.jaspersoft.studio.data.Sql.SubQueryOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:1554:6: ( (lv_values_2_0= ruleFromValues ) )
                    {
                    // InternalSqlParser.g:1554:6: ( (lv_values_2_0= ruleFromValues ) )
                    // InternalSqlParser.g:1555:1: (lv_values_2_0= ruleFromValues )
                    {
                    // InternalSqlParser.g:1555:1: (lv_values_2_0= ruleFromValues )
                    // InternalSqlParser.g:1556:3: lv_values_2_0= ruleFromValues
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getValuesFromValuesParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_40);
                    lv_values_2_0=ruleFromValues();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                      	        }
                             		set(
                             			current, 
                             			"values",
                              		lv_values_2_0, 
                              		"com.jaspersoft.studio.data.Sql.FromValues");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:1572:3: ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )?
            int alt36=3;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==PIVOT) ) {
                alt36=1;
            }
            else if ( (LA36_0==UNPIVOT) ) {
                alt36=2;
            }
            switch (alt36) {
                case 1 :
                    // InternalSqlParser.g:1572:4: ( (lv_pivot_3_0= rulePivotTable ) )
                    {
                    // InternalSqlParser.g:1572:4: ( (lv_pivot_3_0= rulePivotTable ) )
                    // InternalSqlParser.g:1573:1: (lv_pivot_3_0= rulePivotTable )
                    {
                    // InternalSqlParser.g:1573:1: (lv_pivot_3_0= rulePivotTable )
                    // InternalSqlParser.g:1574:3: lv_pivot_3_0= rulePivotTable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getPivotPivotTableParserRuleCall_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_35);
                    lv_pivot_3_0=rulePivotTable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                      	        }
                             		set(
                             			current, 
                             			"pivot",
                              		lv_pivot_3_0, 
                              		"com.jaspersoft.studio.data.Sql.PivotTable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:1591:6: ( (lv_unpivot_4_0= ruleUnpivotTable ) )
                    {
                    // InternalSqlParser.g:1591:6: ( (lv_unpivot_4_0= ruleUnpivotTable ) )
                    // InternalSqlParser.g:1592:1: (lv_unpivot_4_0= ruleUnpivotTable )
                    {
                    // InternalSqlParser.g:1592:1: (lv_unpivot_4_0= ruleUnpivotTable )
                    // InternalSqlParser.g:1593:3: lv_unpivot_4_0= ruleUnpivotTable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getUnpivotUnpivotTableParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_35);
                    lv_unpivot_4_0=ruleUnpivotTable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                      	        }
                             		set(
                             			current, 
                             			"unpivot",
                              		lv_unpivot_4_0, 
                              		"com.jaspersoft.studio.data.Sql.UnpivotTable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:1609:4: ( (lv_alias_5_0= AS ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==AS) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalSqlParser.g:1610:1: (lv_alias_5_0= AS )
                    {
                    // InternalSqlParser.g:1610:1: (lv_alias_5_0= AS )
                    // InternalSqlParser.g:1611:3: lv_alias_5_0= AS
                    {
                    lv_alias_5_0=(Token)match(input,AS,FOLLOW_36); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_alias_5_0, grammarAccess.getTableOrAliasAccess().getAliasASKeyword_2_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTableOrAliasRule());
                      	        }
                             		setWithLastConsumed(current, "alias", lv_alias_5_0, "AS");
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:1625:3: ( (lv_tblAlias_6_0= ruleDbObjectName ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( ((LA38_0>=RULE_STRING && LA38_0<=RULE_ID)) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalSqlParser.g:1626:1: (lv_tblAlias_6_0= ruleDbObjectName )
                    {
                    // InternalSqlParser.g:1626:1: (lv_tblAlias_6_0= ruleDbObjectName )
                    // InternalSqlParser.g:1627:3: lv_tblAlias_6_0= ruleDbObjectName
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_tblAlias_6_0=ruleDbObjectName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                      	        }
                             		set(
                             			current, 
                             			"tblAlias",
                              		lv_tblAlias_6_0, 
                              		"com.jaspersoft.studio.data.Sql.DbObjectName");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTableOrAlias"


    // $ANTLR start "entryRuleFromValues"
    // InternalSqlParser.g:1651:1: entryRuleFromValues returns [EObject current=null] : iv_ruleFromValues= ruleFromValues EOF ;
    public final EObject entryRuleFromValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromValues = null;


        try {
            // InternalSqlParser.g:1652:2: (iv_ruleFromValues= ruleFromValues EOF )
            // InternalSqlParser.g:1653:2: iv_ruleFromValues= ruleFromValues EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFromValuesRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFromValues=ruleFromValues();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFromValues; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFromValues"


    // $ANTLR start "ruleFromValues"
    // InternalSqlParser.g:1660:1: ruleFromValues returns [EObject current=null] : ( ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )? ) ;
    public final EObject ruleFromValues() throws RecognitionException {
        EObject current = null;

        EObject lv_values_0_0 = null;

        EObject lv_c_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1663:28: ( ( ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )? ) )
            // InternalSqlParser.g:1664:1: ( ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )? )
            {
            // InternalSqlParser.g:1664:1: ( ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )? )
            // InternalSqlParser.g:1664:2: ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )?
            {
            // InternalSqlParser.g:1664:2: ( (lv_values_0_0= ruleValues ) )
            // InternalSqlParser.g:1665:1: (lv_values_0_0= ruleValues )
            {
            // InternalSqlParser.g:1665:1: (lv_values_0_0= ruleValues )
            // InternalSqlParser.g:1666:3: lv_values_0_0= ruleValues
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFromValuesAccess().getValuesValuesParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_41);
            lv_values_0_0=ruleValues();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFromValuesRule());
              	        }
                     		set(
                     			current, 
                     			"values",
                      		lv_values_0_0, 
                      		"com.jaspersoft.studio.data.Sql.Values");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:1682:2: ( (lv_c_1_0= ruleFromValuesColumns ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==RULE_ID) ) {
                int LA39_1 = input.LA(2);

                if ( (LA39_1==LeftParenthesis) ) {
                    alt39=1;
                }
            }
            switch (alt39) {
                case 1 :
                    // InternalSqlParser.g:1683:1: (lv_c_1_0= ruleFromValuesColumns )
                    {
                    // InternalSqlParser.g:1683:1: (lv_c_1_0= ruleFromValuesColumns )
                    // InternalSqlParser.g:1684:3: lv_c_1_0= ruleFromValuesColumns
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFromValuesAccess().getCFromValuesColumnsParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_c_1_0=ruleFromValuesColumns();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFromValuesRule());
                      	        }
                             		set(
                             			current, 
                             			"c",
                              		lv_c_1_0, 
                              		"com.jaspersoft.studio.data.Sql.FromValuesColumns");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFromValues"


    // $ANTLR start "entryRuleFromValuesColumns"
    // InternalSqlParser.g:1708:1: entryRuleFromValuesColumns returns [EObject current=null] : iv_ruleFromValuesColumns= ruleFromValuesColumns EOF ;
    public final EObject entryRuleFromValuesColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromValuesColumns = null;


        try {
            // InternalSqlParser.g:1709:2: (iv_ruleFromValuesColumns= ruleFromValuesColumns EOF )
            // InternalSqlParser.g:1710:2: iv_ruleFromValuesColumns= ruleFromValuesColumns EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFromValuesColumnsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFromValuesColumns=ruleFromValuesColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFromValuesColumns; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFromValuesColumns"


    // $ANTLR start "ruleFromValuesColumns"
    // InternalSqlParser.g:1717:1: ruleFromValuesColumns returns [EObject current=null] : (this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleFromValuesColumns() throws RecognitionException {
        EObject current = null;

        Token this_ID_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_fvCols_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1720:28: ( (this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:1721:1: (this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:1721:1: (this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:1721:2: this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getFromValuesColumnsAccess().getIDTerminalRuleCall_0()); 
                  
            }
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_42); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFromValuesColumnsAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalSqlParser.g:1730:1: ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) )
            // InternalSqlParser.g:1731:1: (lv_fvCols_2_0= ruleFromValuesColumnNames )
            {
            // InternalSqlParser.g:1731:1: (lv_fvCols_2_0= ruleFromValuesColumnNames )
            // InternalSqlParser.g:1732:3: lv_fvCols_2_0= ruleFromValuesColumnNames
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFromValuesColumnsAccess().getFvColsFromValuesColumnNamesParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_fvCols_2_0=ruleFromValuesColumnNames();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFromValuesColumnsRule());
              	        }
                     		set(
                     			current, 
                     			"fvCols",
                      		lv_fvCols_2_0, 
                      		"com.jaspersoft.studio.data.Sql.FromValuesColumnNames");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getFromValuesColumnsAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFromValuesColumns"


    // $ANTLR start "entryRuleFromValuesColumnNames"
    // InternalSqlParser.g:1761:1: entryRuleFromValuesColumnNames returns [EObject current=null] : iv_ruleFromValuesColumnNames= ruleFromValuesColumnNames EOF ;
    public final EObject entryRuleFromValuesColumnNames() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromValuesColumnNames = null;


        try {
            // InternalSqlParser.g:1762:2: (iv_ruleFromValuesColumnNames= ruleFromValuesColumnNames EOF )
            // InternalSqlParser.g:1763:2: iv_ruleFromValuesColumnNames= ruleFromValuesColumnNames EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFromValuesColumnNamesRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFromValuesColumnNames=ruleFromValuesColumnNames();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFromValuesColumnNames; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFromValuesColumnNames"


    // $ANTLR start "ruleFromValuesColumnNames"
    // InternalSqlParser.g:1770:1: ruleFromValuesColumnNames returns [EObject current=null] : (this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )? ) ;
    public final EObject ruleFromValuesColumnNames() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1773:28: ( (this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )? ) )
            // InternalSqlParser.g:1774:1: (this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )? )
            {
            // InternalSqlParser.g:1774:1: (this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )? )
            // InternalSqlParser.g:1775:2: this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getFromValuesColumnNamesAccess().getColumnNameParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_ColumnName_0=ruleColumnName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_ColumnName_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:1786:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==Comma) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalSqlParser.g:1786:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+
                    {
                    // InternalSqlParser.g:1786:2: ()
                    // InternalSqlParser.g:1787:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getFromValuesColumnNamesAccess().getAbcEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:1795:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+
                    int cnt40=0;
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==Comma) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // InternalSqlParser.g:1796:2: otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_42); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getFromValuesColumnNamesAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:1800:1: ( (lv_entries_3_0= ruleColumnName ) )
                    	    // InternalSqlParser.g:1801:1: (lv_entries_3_0= ruleColumnName )
                    	    {
                    	    // InternalSqlParser.g:1801:1: (lv_entries_3_0= ruleColumnName )
                    	    // InternalSqlParser.g:1802:3: lv_entries_3_0= ruleColumnName
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getFromValuesColumnNamesAccess().getEntriesColumnNameParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleColumnName();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getFromValuesColumnNamesRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.ColumnName");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt40 >= 1 ) break loop40;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(40, input);
                                throw eee;
                        }
                        cnt40++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFromValuesColumnNames"


    // $ANTLR start "entryRuleColumnName"
    // InternalSqlParser.g:1826:1: entryRuleColumnName returns [EObject current=null] : iv_ruleColumnName= ruleColumnName EOF ;
    public final EObject entryRuleColumnName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnName = null;


        try {
            // InternalSqlParser.g:1827:2: (iv_ruleColumnName= ruleColumnName EOF )
            // InternalSqlParser.g:1828:2: iv_ruleColumnName= ruleColumnName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getColumnNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleColumnName=ruleColumnName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleColumnName; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleColumnName"


    // $ANTLR start "ruleColumnName"
    // InternalSqlParser.g:1835:1: ruleColumnName returns [EObject current=null] : ( (lv_colName_0_0= RULE_STRING ) ) ;
    public final EObject ruleColumnName() throws RecognitionException {
        EObject current = null;

        Token lv_colName_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:1838:28: ( ( (lv_colName_0_0= RULE_STRING ) ) )
            // InternalSqlParser.g:1839:1: ( (lv_colName_0_0= RULE_STRING ) )
            {
            // InternalSqlParser.g:1839:1: ( (lv_colName_0_0= RULE_STRING ) )
            // InternalSqlParser.g:1840:1: (lv_colName_0_0= RULE_STRING )
            {
            // InternalSqlParser.g:1840:1: (lv_colName_0_0= RULE_STRING )
            // InternalSqlParser.g:1841:3: lv_colName_0_0= RULE_STRING
            {
            lv_colName_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_colName_0_0, grammarAccess.getColumnNameAccess().getColNameSTRINGTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getColumnNameRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"colName",
                      		lv_colName_0_0, 
                      		"com.jaspersoft.studio.data.Sql.STRING");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleColumnName"


    // $ANTLR start "entryRuleValues"
    // InternalSqlParser.g:1865:1: entryRuleValues returns [EObject current=null] : iv_ruleValues= ruleValues EOF ;
    public final EObject entryRuleValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValues = null;


        try {
            // InternalSqlParser.g:1866:2: (iv_ruleValues= ruleValues EOF )
            // InternalSqlParser.g:1867:2: iv_ruleValues= ruleValues EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getValuesRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleValues=ruleValues();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleValues; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValues"


    // $ANTLR start "ruleValues"
    // InternalSqlParser.g:1874:1: ruleValues returns [EObject current=null] : (otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleValues() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_rows_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1877:28: ( (otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:1878:1: (otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:1878:1: (otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:1879:2: otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis
            {
            otherlv_0=(Token)match(input,LeftParenthesis,FOLLOW_43); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getValuesAccess().getLeftParenthesisKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,VALUES,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getValuesAccess().getVALUESKeyword_1());
                  
            }
            // InternalSqlParser.g:1888:1: ( (lv_rows_2_0= ruleRows ) )
            // InternalSqlParser.g:1889:1: (lv_rows_2_0= ruleRows )
            {
            // InternalSqlParser.g:1889:1: (lv_rows_2_0= ruleRows )
            // InternalSqlParser.g:1890:3: lv_rows_2_0= ruleRows
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getValuesAccess().getRowsRowsParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_rows_2_0=ruleRows();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getValuesRule());
              	        }
                     		set(
                     			current, 
                     			"rows",
                      		lv_rows_2_0, 
                      		"com.jaspersoft.studio.data.Sql.Rows");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getValuesAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValues"


    // $ANTLR start "entryRuleRows"
    // InternalSqlParser.g:1919:1: entryRuleRows returns [EObject current=null] : iv_ruleRows= ruleRows EOF ;
    public final EObject entryRuleRows() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRows = null;


        try {
            // InternalSqlParser.g:1920:2: (iv_ruleRows= ruleRows EOF )
            // InternalSqlParser.g:1921:2: iv_ruleRows= ruleRows EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRowsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRows=ruleRows();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRows; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRows"


    // $ANTLR start "ruleRows"
    // InternalSqlParser.g:1928:1: ruleRows returns [EObject current=null] : (this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )? ) ;
    public final EObject ruleRows() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Row_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1931:28: ( (this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )? ) )
            // InternalSqlParser.g:1932:1: (this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )? )
            {
            // InternalSqlParser.g:1932:1: (this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )? )
            // InternalSqlParser.g:1933:2: this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRowsAccess().getRowParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_Row_0=ruleRow();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_Row_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:1944:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==Comma) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalSqlParser.g:1944:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+
                    {
                    // InternalSqlParser.g:1944:2: ()
                    // InternalSqlParser.g:1945:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getRowsAccess().getRowsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:1953:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+
                    int cnt42=0;
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==Comma) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // InternalSqlParser.g:1954:2: otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_7); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getRowsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:1958:1: ( (lv_entries_3_0= ruleRow ) )
                    	    // InternalSqlParser.g:1959:1: (lv_entries_3_0= ruleRow )
                    	    {
                    	    // InternalSqlParser.g:1959:1: (lv_entries_3_0= ruleRow )
                    	    // InternalSqlParser.g:1960:3: lv_entries_3_0= ruleRow
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getRowsAccess().getEntriesRowParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleRow();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getRowsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.Row");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt42 >= 1 ) break loop42;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(42, input);
                                throw eee;
                        }
                        cnt42++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRows"


    // $ANTLR start "entryRuleRow"
    // InternalSqlParser.g:1984:1: entryRuleRow returns [EObject current=null] : iv_ruleRow= ruleRow EOF ;
    public final EObject entryRuleRow() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRow = null;


        try {
            // InternalSqlParser.g:1985:2: (iv_ruleRow= ruleRow EOF )
            // InternalSqlParser.g:1986:2: iv_ruleRow= ruleRow EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRowRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRow=ruleRow();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRow; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRow"


    // $ANTLR start "ruleRow"
    // InternalSqlParser.g:1993:1: ruleRow returns [EObject current=null] : (otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis ) ;
    public final EObject ruleRow() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_rowValues_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1996:28: ( (otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis ) )
            // InternalSqlParser.g:1997:1: (otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis )
            {
            // InternalSqlParser.g:1997:1: (otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis )
            // InternalSqlParser.g:1998:2: otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis
            {
            otherlv_0=(Token)match(input,LeftParenthesis,FOLLOW_44); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getRowAccess().getLeftParenthesisKeyword_0());
                  
            }
            // InternalSqlParser.g:2002:1: ( (lv_rowValues_1_0= ruleRowValues ) )
            // InternalSqlParser.g:2003:1: (lv_rowValues_1_0= ruleRowValues )
            {
            // InternalSqlParser.g:2003:1: (lv_rowValues_1_0= ruleRowValues )
            // InternalSqlParser.g:2004:3: lv_rowValues_1_0= ruleRowValues
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getRowAccess().getRowValuesRowValuesParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_rowValues_1_0=ruleRowValues();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getRowRule());
              	        }
                     		set(
                     			current, 
                     			"rowValues",
                      		lv_rowValues_1_0, 
                      		"com.jaspersoft.studio.data.Sql.RowValues");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getRowAccess().getRightParenthesisKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRow"


    // $ANTLR start "entryRuleRowValues"
    // InternalSqlParser.g:2033:1: entryRuleRowValues returns [EObject current=null] : iv_ruleRowValues= ruleRowValues EOF ;
    public final EObject entryRuleRowValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRowValues = null;


        try {
            // InternalSqlParser.g:2034:2: (iv_ruleRowValues= ruleRowValues EOF )
            // InternalSqlParser.g:2035:2: iv_ruleRowValues= ruleRowValues EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRowValuesRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRowValues=ruleRowValues();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRowValues; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRowValues"


    // $ANTLR start "ruleRowValues"
    // InternalSqlParser.g:2042:1: ruleRowValues returns [EObject current=null] : (this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )? ) ;
    public final EObject ruleRowValues() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_RowValue_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2045:28: ( (this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )? ) )
            // InternalSqlParser.g:2046:1: (this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )? )
            {
            // InternalSqlParser.g:2046:1: (this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )? )
            // InternalSqlParser.g:2047:2: this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRowValuesAccess().getRowValueParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_RowValue_0=ruleRowValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_RowValue_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:2058:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==Comma) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalSqlParser.g:2058:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+
                    {
                    // InternalSqlParser.g:2058:2: ()
                    // InternalSqlParser.g:2059:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getRowValuesAccess().getRowValuesEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:2067:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+
                    int cnt44=0;
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==Comma) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // InternalSqlParser.g:2068:2: otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_44); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getRowValuesAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:2072:1: ( (lv_entries_3_0= ruleRowValue ) )
                    	    // InternalSqlParser.g:2073:1: (lv_entries_3_0= ruleRowValue )
                    	    {
                    	    // InternalSqlParser.g:2073:1: (lv_entries_3_0= ruleRowValue )
                    	    // InternalSqlParser.g:2074:3: lv_entries_3_0= ruleRowValue
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getRowValuesAccess().getEntriesRowValueParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleRowValue();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getRowValuesRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.RowValue");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt44 >= 1 ) break loop44;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(44, input);
                                throw eee;
                        }
                        cnt44++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRowValues"


    // $ANTLR start "entryRuleRowValue"
    // InternalSqlParser.g:2098:1: entryRuleRowValue returns [EObject current=null] : iv_ruleRowValue= ruleRowValue EOF ;
    public final EObject entryRuleRowValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRowValue = null;


        try {
            // InternalSqlParser.g:2099:2: (iv_ruleRowValue= ruleRowValue EOF )
            // InternalSqlParser.g:2100:2: iv_ruleRowValue= ruleRowValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRowValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRowValue=ruleRowValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRowValue; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRowValue"


    // $ANTLR start "ruleRowValue"
    // InternalSqlParser.g:2107:1: ruleRowValue returns [EObject current=null] : (this_ScalarNumberOperand_0= ruleScalarNumberOperand | ( (lv_null_1_0= NULL ) ) ) ;
    public final EObject ruleRowValue() throws RecognitionException {
        EObject current = null;

        Token lv_null_1_0=null;
        EObject this_ScalarNumberOperand_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2110:28: ( (this_ScalarNumberOperand_0= ruleScalarNumberOperand | ( (lv_null_1_0= NULL ) ) ) )
            // InternalSqlParser.g:2111:1: (this_ScalarNumberOperand_0= ruleScalarNumberOperand | ( (lv_null_1_0= NULL ) ) )
            {
            // InternalSqlParser.g:2111:1: (this_ScalarNumberOperand_0= ruleScalarNumberOperand | ( (lv_null_1_0= NULL ) ) )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( ((LA46_0>=RULE_UNSIGNED && LA46_0<=RULE_SIGNED_DOUBLE)||LA46_0==RULE_STRING_) ) {
                alt46=1;
            }
            else if ( (LA46_0==NULL) ) {
                alt46=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // InternalSqlParser.g:2112:2: this_ScalarNumberOperand_0= ruleScalarNumberOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getRowValueAccess().getScalarNumberOperandParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ScalarNumberOperand_0=ruleScalarNumberOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ScalarNumberOperand_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:2124:6: ( (lv_null_1_0= NULL ) )
                    {
                    // InternalSqlParser.g:2124:6: ( (lv_null_1_0= NULL ) )
                    // InternalSqlParser.g:2125:1: (lv_null_1_0= NULL )
                    {
                    // InternalSqlParser.g:2125:1: (lv_null_1_0= NULL )
                    // InternalSqlParser.g:2126:3: lv_null_1_0= NULL
                    {
                    lv_null_1_0=(Token)match(input,NULL,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_null_1_0, grammarAccess.getRowValueAccess().getNullNULLKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getRowValueRule());
                      	        }
                             		setWithLastConsumed(current, "null", lv_null_1_0, "NULL");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRowValue"


    // $ANTLR start "entryRulePivotTable"
    // InternalSqlParser.g:2148:1: entryRulePivotTable returns [EObject current=null] : iv_rulePivotTable= rulePivotTable EOF ;
    public final EObject entryRulePivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotTable = null;


        try {
            // InternalSqlParser.g:2149:2: (iv_rulePivotTable= rulePivotTable EOF )
            // InternalSqlParser.g:2150:2: iv_rulePivotTable= rulePivotTable EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPivotTableRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePivotTable=rulePivotTable();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePivotTable; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePivotTable"


    // $ANTLR start "rulePivotTable"
    // InternalSqlParser.g:2157:1: rulePivotTable returns [EObject current=null] : (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis ) ;
    public final EObject rulePivotTable() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_6=null;
        EObject lv_pfun_3_0 = null;

        EObject lv_pfor_4_0 = null;

        EObject lv_pin_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2160:28: ( (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis ) )
            // InternalSqlParser.g:2161:1: (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis )
            {
            // InternalSqlParser.g:2161:1: (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis )
            // InternalSqlParser.g:2162:2: otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis
            {
            otherlv_0=(Token)match(input,PIVOT,FOLLOW_45); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getPivotTableAccess().getPIVOTKeyword_0());
                  
            }
            // InternalSqlParser.g:2166:1: (otherlv_1= XML )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==XML) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalSqlParser.g:2167:2: otherlv_1= XML
                    {
                    otherlv_1=(Token)match(input,XML,FOLLOW_7); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getPivotTableAccess().getXMLKeyword_1());
                          
                    }

                    }
                    break;

            }

            otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_46); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getPivotTableAccess().getLeftParenthesisKeyword_2());
                  
            }
            // InternalSqlParser.g:2176:1: ( (lv_pfun_3_0= rulePivotFunctions ) )
            // InternalSqlParser.g:2177:1: (lv_pfun_3_0= rulePivotFunctions )
            {
            // InternalSqlParser.g:2177:1: (lv_pfun_3_0= rulePivotFunctions )
            // InternalSqlParser.g:2178:3: lv_pfun_3_0= rulePivotFunctions
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPivotTableAccess().getPfunPivotFunctionsParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_47);
            lv_pfun_3_0=rulePivotFunctions();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPivotTableRule());
              	        }
                     		set(
                     			current, 
                     			"pfun",
                      		lv_pfun_3_0, 
                      		"com.jaspersoft.studio.data.Sql.PivotFunctions");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:2194:2: ( (lv_pfor_4_0= rulePivotForClause ) )
            // InternalSqlParser.g:2195:1: (lv_pfor_4_0= rulePivotForClause )
            {
            // InternalSqlParser.g:2195:1: (lv_pfor_4_0= rulePivotForClause )
            // InternalSqlParser.g:2196:3: lv_pfor_4_0= rulePivotForClause
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_48);
            lv_pfor_4_0=rulePivotForClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPivotTableRule());
              	        }
                     		set(
                     			current, 
                     			"pfor",
                      		lv_pfor_4_0, 
                      		"com.jaspersoft.studio.data.Sql.PivotForClause");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:2212:2: ( (lv_pin_5_0= rulePivotInClause ) )
            // InternalSqlParser.g:2213:1: (lv_pin_5_0= rulePivotInClause )
            {
            // InternalSqlParser.g:2213:1: (lv_pin_5_0= rulePivotInClause )
            // InternalSqlParser.g:2214:3: lv_pin_5_0= rulePivotInClause
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPivotTableAccess().getPinPivotInClauseParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_pin_5_0=rulePivotInClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPivotTableRule());
              	        }
                     		set(
                     			current, 
                     			"pin",
                      		lv_pin_5_0, 
                      		"com.jaspersoft.studio.data.Sql.PivotInClause");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_6=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getPivotTableAccess().getRightParenthesisKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePivotTable"


    // $ANTLR start "entryRulePivotFunctions"
    // InternalSqlParser.g:2243:1: entryRulePivotFunctions returns [EObject current=null] : iv_rulePivotFunctions= rulePivotFunctions EOF ;
    public final EObject entryRulePivotFunctions() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotFunctions = null;


        try {
            // InternalSqlParser.g:2244:2: (iv_rulePivotFunctions= rulePivotFunctions EOF )
            // InternalSqlParser.g:2245:2: iv_rulePivotFunctions= rulePivotFunctions EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPivotFunctionsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePivotFunctions=rulePivotFunctions();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePivotFunctions; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePivotFunctions"


    // $ANTLR start "rulePivotFunctions"
    // InternalSqlParser.g:2252:1: rulePivotFunctions returns [EObject current=null] : ( (lv_abc_0_0= RULE_ID ) ) ;
    public final EObject rulePivotFunctions() throws RecognitionException {
        EObject current = null;

        Token lv_abc_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:2255:28: ( ( (lv_abc_0_0= RULE_ID ) ) )
            // InternalSqlParser.g:2256:1: ( (lv_abc_0_0= RULE_ID ) )
            {
            // InternalSqlParser.g:2256:1: ( (lv_abc_0_0= RULE_ID ) )
            // InternalSqlParser.g:2257:1: (lv_abc_0_0= RULE_ID )
            {
            // InternalSqlParser.g:2257:1: (lv_abc_0_0= RULE_ID )
            // InternalSqlParser.g:2258:3: lv_abc_0_0= RULE_ID
            {
            lv_abc_0_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_abc_0_0, grammarAccess.getPivotFunctionsAccess().getAbcIDTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getPivotFunctionsRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"abc",
                      		lv_abc_0_0, 
                      		"com.jaspersoft.studio.data.Sql.ID");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePivotFunctions"


    // $ANTLR start "entryRulePivotInClause"
    // InternalSqlParser.g:2284:1: entryRulePivotInClause returns [EObject current=null] : iv_rulePivotInClause= rulePivotInClause EOF ;
    public final EObject entryRulePivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotInClause = null;


        try {
            // InternalSqlParser.g:2285:2: (iv_rulePivotInClause= rulePivotInClause EOF )
            // InternalSqlParser.g:2286:2: iv_rulePivotInClause= rulePivotInClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPivotInClauseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePivotInClause=rulePivotInClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePivotInClause; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePivotInClause"


    // $ANTLR start "rulePivotInClause"
    // InternalSqlParser.g:2293:1: rulePivotInClause returns [EObject current=null] : (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis ) ;
    public final EObject rulePivotInClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_5=null;
        EObject lv_sq_2_0 = null;

        EObject lv_args_3_0 = null;

        AntlrDatatypeRuleToken lv_pinany_4_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2296:28: ( (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis ) )
            // InternalSqlParser.g:2297:1: (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis )
            {
            // InternalSqlParser.g:2297:1: (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis )
            // InternalSqlParser.g:2298:2: otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis
            {
            otherlv_0=(Token)match(input,IN,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getPivotInClauseAccess().getINKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_49); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPivotInClauseAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalSqlParser.g:2307:1: ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) )
            int alt48=3;
            switch ( input.LA(1) ) {
            case LeftParenthesis:
                {
                int LA48_1 = input.LA(2);

                if ( (LA48_1==SELECT) ) {
                    alt48=1;
                }
                else if ( ((LA48_1>=RULE_STRING && LA48_1<=RULE_ID)) ) {
                    alt48=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 48, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt48=2;
                }
                break;
            case ANY:
                {
                alt48=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }

            switch (alt48) {
                case 1 :
                    // InternalSqlParser.g:2307:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:2307:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:2308:1: (lv_sq_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:2308:1: (lv_sq_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:2309:3: lv_sq_2_0= ruleSubQueryOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getSqSubQueryOperandParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_8);
                    lv_sq_2_0=ruleSubQueryOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPivotInClauseRule());
                      	        }
                             		set(
                             			current, 
                             			"sq",
                              		lv_sq_2_0, 
                              		"com.jaspersoft.studio.data.Sql.SubQueryOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:2326:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    {
                    // InternalSqlParser.g:2326:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    // InternalSqlParser.g:2327:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    {
                    // InternalSqlParser.g:2327:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    // InternalSqlParser.g:2328:3: lv_args_3_0= ruleUnpivotInClauseArgs
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_8);
                    lv_args_3_0=ruleUnpivotInClauseArgs();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPivotInClauseRule());
                      	        }
                             		set(
                             			current, 
                             			"args",
                              		lv_args_3_0, 
                              		"com.jaspersoft.studio.data.Sql.UnpivotInClauseArgs");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:2345:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    {
                    // InternalSqlParser.g:2345:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    // InternalSqlParser.g:2346:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    {
                    // InternalSqlParser.g:2346:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    // InternalSqlParser.g:2347:3: lv_pinany_4_0= rulePivotInClauseAny
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getPinanyPivotInClauseAnyParserRuleCall_2_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_8);
                    lv_pinany_4_0=rulePivotInClauseAny();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPivotInClauseRule());
                      	        }
                             		set(
                             			current, 
                             			"pinany",
                              		lv_pinany_4_0, 
                              		"com.jaspersoft.studio.data.Sql.PivotInClauseAny");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getPivotInClauseAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePivotInClause"


    // $ANTLR start "entryRulePivotInClauseAny"
    // InternalSqlParser.g:2376:1: entryRulePivotInClauseAny returns [String current=null] : iv_rulePivotInClauseAny= rulePivotInClauseAny EOF ;
    public final String entryRulePivotInClauseAny() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePivotInClauseAny = null;


        try {
            // InternalSqlParser.g:2377:1: (iv_rulePivotInClauseAny= rulePivotInClauseAny EOF )
            // InternalSqlParser.g:2378:2: iv_rulePivotInClauseAny= rulePivotInClauseAny EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPivotInClauseAnyRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePivotInClauseAny=rulePivotInClauseAny();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePivotInClauseAny.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePivotInClauseAny"


    // $ANTLR start "rulePivotInClauseAny"
    // InternalSqlParser.g:2385:1: rulePivotInClauseAny returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= ANY (kw= Comma kw= ANY )* ) ;
    public final AntlrDatatypeRuleToken rulePivotInClauseAny() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:2389:6: ( (kw= ANY (kw= Comma kw= ANY )* ) )
            // InternalSqlParser.g:2390:1: (kw= ANY (kw= Comma kw= ANY )* )
            {
            // InternalSqlParser.g:2390:1: (kw= ANY (kw= Comma kw= ANY )* )
            // InternalSqlParser.g:2391:2: kw= ANY (kw= Comma kw= ANY )*
            {
            kw=(Token)match(input,ANY,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_0()); 
                  
            }
            // InternalSqlParser.g:2396:1: (kw= Comma kw= ANY )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==Comma) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // InternalSqlParser.g:2397:2: kw= Comma kw= ANY
            	    {
            	    kw=(Token)match(input,Comma,FOLLOW_50); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getCommaKeyword_1_0()); 
            	          
            	    }
            	    kw=(Token)match(input,ANY,FOLLOW_11); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_1_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePivotInClauseAny"


    // $ANTLR start "entryRuleUnpivotTable"
    // InternalSqlParser.g:2416:1: entryRuleUnpivotTable returns [EObject current=null] : iv_ruleUnpivotTable= ruleUnpivotTable EOF ;
    public final EObject entryRuleUnpivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotTable = null;


        try {
            // InternalSqlParser.g:2417:2: (iv_ruleUnpivotTable= ruleUnpivotTable EOF )
            // InternalSqlParser.g:2418:2: iv_ruleUnpivotTable= ruleUnpivotTable EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnpivotTableRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnpivotTable=ruleUnpivotTable();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnpivotTable; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnpivotTable"


    // $ANTLR start "ruleUnpivotTable"
    // InternalSqlParser.g:2425:1: ruleUnpivotTable returns [EObject current=null] : (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis ) ;
    public final EObject ruleUnpivotTable() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_8=null;
        EObject lv_pcols_5_0 = null;

        EObject lv_pfor_6_0 = null;

        EObject lv_inop_7_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2428:28: ( (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis ) )
            // InternalSqlParser.g:2429:1: (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis )
            {
            // InternalSqlParser.g:2429:1: (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis )
            // InternalSqlParser.g:2430:2: otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis
            {
            otherlv_0=(Token)match(input,UNPIVOT,FOLLOW_51); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getUnpivotTableAccess().getUNPIVOTKeyword_0());
                  
            }
            // InternalSqlParser.g:2434:1: ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==EXCLUDE||LA51_0==INCLUDE) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalSqlParser.g:2434:2: (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS
                    {
                    // InternalSqlParser.g:2434:2: (otherlv_1= INCLUDE | otherlv_2= EXCLUDE )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==INCLUDE) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==EXCLUDE) ) {
                        alt50=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // InternalSqlParser.g:2435:2: otherlv_1= INCLUDE
                            {
                            otherlv_1=(Token)match(input,INCLUDE,FOLLOW_52); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_1, grammarAccess.getUnpivotTableAccess().getINCLUDEKeyword_1_0_0());
                                  
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:2441:2: otherlv_2= EXCLUDE
                            {
                            otherlv_2=(Token)match(input,EXCLUDE,FOLLOW_52); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_2, grammarAccess.getUnpivotTableAccess().getEXCLUDEKeyword_1_0_1());
                                  
                            }

                            }
                            break;

                    }

                    otherlv_3=(Token)match(input,NULLS,FOLLOW_7); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getUnpivotTableAccess().getNULLSKeyword_1_1());
                          
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getUnpivotTableAccess().getLeftParenthesisKeyword_2());
                  
            }
            // InternalSqlParser.g:2455:1: ( (lv_pcols_5_0= rulePivotColumns ) )
            // InternalSqlParser.g:2456:1: (lv_pcols_5_0= rulePivotColumns )
            {
            // InternalSqlParser.g:2456:1: (lv_pcols_5_0= rulePivotColumns )
            // InternalSqlParser.g:2457:3: lv_pcols_5_0= rulePivotColumns
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPcolsPivotColumnsParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_47);
            lv_pcols_5_0=rulePivotColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUnpivotTableRule());
              	        }
                     		set(
                     			current, 
                     			"pcols",
                      		lv_pcols_5_0, 
                      		"com.jaspersoft.studio.data.Sql.PivotColumns");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:2473:2: ( (lv_pfor_6_0= rulePivotForClause ) )
            // InternalSqlParser.g:2474:1: (lv_pfor_6_0= rulePivotForClause )
            {
            // InternalSqlParser.g:2474:1: (lv_pfor_6_0= rulePivotForClause )
            // InternalSqlParser.g:2475:3: lv_pfor_6_0= rulePivotForClause
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_48);
            lv_pfor_6_0=rulePivotForClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUnpivotTableRule());
              	        }
                     		set(
                     			current, 
                     			"pfor",
                      		lv_pfor_6_0, 
                      		"com.jaspersoft.studio.data.Sql.PivotForClause");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:2491:2: ( (lv_inop_7_0= ruleUnpivotInClause ) )
            // InternalSqlParser.g:2492:1: (lv_inop_7_0= ruleUnpivotInClause )
            {
            // InternalSqlParser.g:2492:1: (lv_inop_7_0= ruleUnpivotInClause )
            // InternalSqlParser.g:2493:3: lv_inop_7_0= ruleUnpivotInClause
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getInopUnpivotInClauseParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_inop_7_0=ruleUnpivotInClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUnpivotTableRule());
              	        }
                     		set(
                     			current, 
                     			"inop",
                      		lv_inop_7_0, 
                      		"com.jaspersoft.studio.data.Sql.UnpivotInClause");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_8=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getUnpivotTableAccess().getRightParenthesisKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnpivotTable"


    // $ANTLR start "entryRuleUnpivotInClause"
    // InternalSqlParser.g:2522:1: entryRuleUnpivotInClause returns [EObject current=null] : iv_ruleUnpivotInClause= ruleUnpivotInClause EOF ;
    public final EObject entryRuleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClause = null;


        try {
            // InternalSqlParser.g:2523:2: (iv_ruleUnpivotInClause= ruleUnpivotInClause EOF )
            // InternalSqlParser.g:2524:2: iv_ruleUnpivotInClause= ruleUnpivotInClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnpivotInClauseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnpivotInClause=ruleUnpivotInClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnpivotInClause; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnpivotInClause"


    // $ANTLR start "ruleUnpivotInClause"
    // InternalSqlParser.g:2531:1: ruleUnpivotInClause returns [EObject current=null] : ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis ) ;
    public final EObject ruleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2534:28: ( ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis ) )
            // InternalSqlParser.g:2535:1: ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis )
            {
            // InternalSqlParser.g:2535:1: ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis )
            // InternalSqlParser.g:2535:2: () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis
            {
            // InternalSqlParser.g:2535:2: ()
            // InternalSqlParser.g:2536:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getUnpivotInClauseAccess().getUnipivotInClauseAction_0(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:2544:2: ( (lv_op_1_0= IN ) )
            // InternalSqlParser.g:2545:1: (lv_op_1_0= IN )
            {
            // InternalSqlParser.g:2545:1: (lv_op_1_0= IN )
            // InternalSqlParser.g:2546:3: lv_op_1_0= IN
            {
            lv_op_1_0=(Token)match(input,IN,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_op_1_0, grammarAccess.getUnpivotInClauseAccess().getOpINKeyword_1_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getUnpivotInClauseRule());
              	        }
                     		setWithLastConsumed(current, "op", lv_op_1_0, "IN");
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseAccess().getLeftParenthesisKeyword_2());
                  
            }
            // InternalSqlParser.g:2565:1: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
            // InternalSqlParser.g:2566:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            {
            // InternalSqlParser.g:2566:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            // InternalSqlParser.g:2567:3: lv_args_3_0= ruleUnpivotInClauseArgs
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnpivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_args_3_0=ruleUnpivotInClauseArgs();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUnpivotInClauseRule());
              	        }
                     		set(
                     			current, 
                     			"args",
                      		lv_args_3_0, 
                      		"com.jaspersoft.studio.data.Sql.UnpivotInClauseArgs");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getUnpivotInClauseAccess().getRightParenthesisKeyword_4());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnpivotInClause"


    // $ANTLR start "entryRuleUnpivotInClauseArgs"
    // InternalSqlParser.g:2596:1: entryRuleUnpivotInClauseArgs returns [EObject current=null] : iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF ;
    public final EObject entryRuleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArgs = null;


        try {
            // InternalSqlParser.g:2597:2: (iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF )
            // InternalSqlParser.g:2598:2: iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnpivotInClauseArgsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnpivotInClauseArgs=ruleUnpivotInClauseArgs();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnpivotInClauseArgs; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnpivotInClauseArgs"


    // $ANTLR start "ruleUnpivotInClauseArgs"
    // InternalSqlParser.g:2605:1: ruleUnpivotInClauseArgs returns [EObject current=null] : (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) ;
    public final EObject ruleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_UnpivotInClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2608:28: ( (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) )
            // InternalSqlParser.g:2609:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            {
            // InternalSqlParser.g:2609:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            // InternalSqlParser.g:2610:2: this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getUnpivotInClauseArgParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_UnpivotInClauseArg_0=ruleUnpivotInClauseArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_UnpivotInClauseArg_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:2621:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==Comma) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // InternalSqlParser.g:2621:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    {
                    // InternalSqlParser.g:2621:2: ()
                    // InternalSqlParser.g:2622:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getUnpivotInClauseArgsAccess().getUicargsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:2630:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    int cnt52=0;
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==Comma) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // InternalSqlParser.g:2631:2: otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_21); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseArgsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:2635:1: ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    // InternalSqlParser.g:2636:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    {
                    	    // InternalSqlParser.g:2636:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    // InternalSqlParser.g:2637:3: lv_entries_3_0= ruleUnpivotInClauseArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getEntriesUnpivotInClauseArgParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleUnpivotInClauseArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getUnpivotInClauseArgsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.UnpivotInClauseArg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt52 >= 1 ) break loop52;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(52, input);
                                throw eee;
                        }
                        cnt52++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnpivotInClauseArgs"


    // $ANTLR start "entryRuleUnpivotInClauseArg"
    // InternalSqlParser.g:2661:1: entryRuleUnpivotInClauseArg returns [EObject current=null] : iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF ;
    public final EObject entryRuleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArg = null;


        try {
            // InternalSqlParser.g:2662:2: (iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF )
            // InternalSqlParser.g:2663:2: iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnpivotInClauseArgRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnpivotInClauseArg=ruleUnpivotInClauseArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnpivotInClauseArg; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnpivotInClauseArg"


    // $ANTLR start "ruleUnpivotInClauseArg"
    // InternalSqlParser.g:2670:1: ruleUnpivotInClauseArg returns [EObject current=null] : ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) ;
    public final EObject ruleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_pcols_0_0 = null;

        EObject lv_cfuls_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2673:28: ( ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) )
            // InternalSqlParser.g:2674:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            {
            // InternalSqlParser.g:2674:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            // InternalSqlParser.g:2674:2: ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            {
            // InternalSqlParser.g:2674:2: ( (lv_pcols_0_0= rulePivotColumns ) )
            // InternalSqlParser.g:2675:1: (lv_pcols_0_0= rulePivotColumns )
            {
            // InternalSqlParser.g:2675:1: (lv_pcols_0_0= rulePivotColumns )
            // InternalSqlParser.g:2676:3: lv_pcols_0_0= rulePivotColumns
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getPcolsPivotColumnsParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_53);
            lv_pcols_0_0=rulePivotColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUnpivotInClauseArgRule());
              	        }
                     		set(
                     			current, 
                     			"pcols",
                      		lv_pcols_0_0, 
                      		"com.jaspersoft.studio.data.Sql.PivotColumns");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:2692:2: (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==AS) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalSqlParser.g:2693:2: otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) )
                    {
                    otherlv_1=(Token)match(input,AS,FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getUnpivotInClauseArgAccess().getASKeyword_1_0());
                          
                    }
                    // InternalSqlParser.g:2697:1: ( (lv_cfuls_2_0= rulePivotColumns ) )
                    // InternalSqlParser.g:2698:1: (lv_cfuls_2_0= rulePivotColumns )
                    {
                    // InternalSqlParser.g:2698:1: (lv_cfuls_2_0= rulePivotColumns )
                    // InternalSqlParser.g:2699:3: lv_cfuls_2_0= rulePivotColumns
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getCfulsPivotColumnsParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_cfuls_2_0=rulePivotColumns();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getUnpivotInClauseArgRule());
                      	        }
                             		set(
                             			current, 
                             			"cfuls",
                              		lv_cfuls_2_0, 
                              		"com.jaspersoft.studio.data.Sql.PivotColumns");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnpivotInClauseArg"


    // $ANTLR start "entryRulePivotForClause"
    // InternalSqlParser.g:2723:1: entryRulePivotForClause returns [EObject current=null] : iv_rulePivotForClause= rulePivotForClause EOF ;
    public final EObject entryRulePivotForClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotForClause = null;


        try {
            // InternalSqlParser.g:2724:2: (iv_rulePivotForClause= rulePivotForClause EOF )
            // InternalSqlParser.g:2725:2: iv_rulePivotForClause= rulePivotForClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPivotForClauseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePivotForClause=rulePivotForClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePivotForClause; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePivotForClause"


    // $ANTLR start "rulePivotForClause"
    // InternalSqlParser.g:2732:1: rulePivotForClause returns [EObject current=null] : (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) ) ;
    public final EObject rulePivotForClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ColumnFull_1 = null;

        EObject this_Columns_3 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2735:28: ( (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) ) )
            // InternalSqlParser.g:2736:1: (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) )
            {
            // InternalSqlParser.g:2736:1: (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) )
            // InternalSqlParser.g:2737:2: otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) )
            {
            otherlv_0=(Token)match(input,FOR,FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getPivotForClauseAccess().getFORKeyword_0());
                  
            }
            // InternalSqlParser.g:2741:1: (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( ((LA55_0>=RULE_STRING && LA55_0<=RULE_ID)) ) {
                alt55=1;
            }
            else if ( (LA55_0==LeftParenthesis) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // InternalSqlParser.g:2742:2: this_ColumnFull_1= ruleColumnFull
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnFullParserRuleCall_1_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ColumnFull_1=ruleColumnFull();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ColumnFull_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:2754:6: (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis )
                    {
                    // InternalSqlParser.g:2754:6: (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis )
                    // InternalSqlParser.g:2755:2: otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis
                    {
                    otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getPivotForClauseAccess().getLeftParenthesisKeyword_1_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnsParserRuleCall_1_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_8);
                    this_Columns_3=ruleColumns();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_Columns_3;
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getPivotForClauseAccess().getRightParenthesisKeyword_1_1_2());
                          
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePivotForClause"


    // $ANTLR start "entryRulePivotColumns"
    // InternalSqlParser.g:2784:1: entryRulePivotColumns returns [EObject current=null] : iv_rulePivotColumns= rulePivotColumns EOF ;
    public final EObject entryRulePivotColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotColumns = null;


        try {
            // InternalSqlParser.g:2785:2: (iv_rulePivotColumns= rulePivotColumns EOF )
            // InternalSqlParser.g:2786:2: iv_rulePivotColumns= rulePivotColumns EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPivotColumnsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePivotColumns=rulePivotColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePivotColumns; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePivotColumns"


    // $ANTLR start "rulePivotColumns"
    // InternalSqlParser.g:2793:1: rulePivotColumns returns [EObject current=null] : (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) ) ;
    public final EObject rulePivotColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_PivotCol_0 = null;

        EObject this_PivotCols_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2796:28: ( (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) ) )
            // InternalSqlParser.g:2797:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) )
            {
            // InternalSqlParser.g:2797:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( ((LA56_0>=RULE_STRING && LA56_0<=RULE_ID)) ) {
                alt56=1;
            }
            else if ( (LA56_0==LeftParenthesis) ) {
                alt56=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // InternalSqlParser.g:2798:2: this_PivotCol_0= rulePivotCol
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_PivotCol_0=rulePivotCol();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PivotCol_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:2810:6: (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis )
                    {
                    // InternalSqlParser.g:2810:6: (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis )
                    // InternalSqlParser.g:2811:2: otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis
                    {
                    otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getPivotColumnsAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColsParserRuleCall_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_8);
                    this_PivotCols_2=rulePivotCols();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PivotCols_2;
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getPivotColumnsAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePivotColumns"


    // $ANTLR start "entryRulePivotCols"
    // InternalSqlParser.g:2840:1: entryRulePivotCols returns [EObject current=null] : iv_rulePivotCols= rulePivotCols EOF ;
    public final EObject entryRulePivotCols() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCols = null;


        try {
            // InternalSqlParser.g:2841:2: (iv_rulePivotCols= rulePivotCols EOF )
            // InternalSqlParser.g:2842:2: iv_rulePivotCols= rulePivotCols EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPivotColsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePivotCols=rulePivotCols();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePivotCols; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePivotCols"


    // $ANTLR start "rulePivotCols"
    // InternalSqlParser.g:2849:1: rulePivotCols returns [EObject current=null] : (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) ;
    public final EObject rulePivotCols() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PivotCol_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2852:28: ( (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) )
            // InternalSqlParser.g:2853:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            {
            // InternalSqlParser.g:2853:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            // InternalSqlParser.g:2854:2: this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getPivotColsAccess().getPivotColParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_PivotCol_0=rulePivotCol();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_PivotCol_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:2865:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==Comma) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalSqlParser.g:2865:2: () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+
                    {
                    // InternalSqlParser.g:2865:2: ()
                    // InternalSqlParser.g:2866:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getPivotColsAccess().getPvcsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:2874:2: (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+
                    int cnt57=0;
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==Comma) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // InternalSqlParser.g:2875:2: otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getPivotColsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:2879:1: ( (lv_entries_3_0= rulePivotCol ) )
                    	    // InternalSqlParser.g:2880:1: (lv_entries_3_0= rulePivotCol )
                    	    {
                    	    // InternalSqlParser.g:2880:1: (lv_entries_3_0= rulePivotCol )
                    	    // InternalSqlParser.g:2881:3: lv_entries_3_0= rulePivotCol
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getPivotColsAccess().getEntriesPivotColParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=rulePivotCol();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getPivotColsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.PivotCol");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt57 >= 1 ) break loop57;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(57, input);
                                throw eee;
                        }
                        cnt57++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePivotCols"


    // $ANTLR start "entryRulePivotCol"
    // InternalSqlParser.g:2905:1: entryRulePivotCol returns [EObject current=null] : iv_rulePivotCol= rulePivotCol EOF ;
    public final EObject entryRulePivotCol() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCol = null;


        try {
            // InternalSqlParser.g:2906:2: (iv_rulePivotCol= rulePivotCol EOF )
            // InternalSqlParser.g:2907:2: iv_rulePivotCol= rulePivotCol EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPivotColRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePivotCol=rulePivotCol();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePivotCol; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePivotCol"


    // $ANTLR start "rulePivotCol"
    // InternalSqlParser.g:2914:1: rulePivotCol returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject rulePivotCol() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2917:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:2918:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:2918:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:2919:2: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getPivotColAccess().getDbObjectNameParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_37);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_DbObjectName_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:2930:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==FullStop) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalSqlParser.g:2930:2: () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:2930:2: ()
                    // InternalSqlParser.g:2931:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getPivotColAccess().getPcolsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:2939:2: (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt59=0;
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==FullStop) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // InternalSqlParser.g:2940:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getPivotColAccess().getFullStopKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:2944:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:2945:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:2945:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:2946:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getPivotColAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_37);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getPivotColRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.DbObjectName");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt59 >= 1 ) break loop59;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(59, input);
                                throw eee;
                        }
                        cnt59++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePivotCol"


    // $ANTLR start "entryRuleTableFull"
    // InternalSqlParser.g:2970:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // InternalSqlParser.g:2971:2: (iv_ruleTableFull= ruleTableFull EOF )
            // InternalSqlParser.g:2972:2: iv_ruleTableFull= ruleTableFull EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTableFullRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTableFull; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTableFull"


    // $ANTLR start "ruleTableFull"
    // InternalSqlParser.g:2979:1: ruleTableFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2982:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:2983:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:2983:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:2984:2: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_37);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_DbObjectName_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:2995:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==FullStop) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalSqlParser.g:2995:2: () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:2995:2: ()
                    // InternalSqlParser.g:2996:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:3004:2: (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt61=0;
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==FullStop) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // InternalSqlParser.g:3005:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:3009:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:3010:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:3010:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:3011:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_37);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTableFullRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.DbObjectName");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt61 >= 1 ) break loop61;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(61, input);
                                throw eee;
                        }
                        cnt61++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTableFull"


    // $ANTLR start "entryRuleDbObjectNameAll"
    // InternalSqlParser.g:3035:1: entryRuleDbObjectNameAll returns [EObject current=null] : iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF ;
    public final EObject entryRuleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectNameAll = null;


        try {
            // InternalSqlParser.g:3036:2: (iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF )
            // InternalSqlParser.g:3037:2: iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDbObjectNameAllRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleDbObjectNameAll=ruleDbObjectNameAll();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDbObjectNameAll; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDbObjectNameAll"


    // $ANTLR start "ruleDbObjectNameAll"
    // InternalSqlParser.g:3044:1: ruleDbObjectNameAll returns [EObject current=null] : ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR ) ;
    public final EObject ruleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_STAR_2=null;
        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3047:28: ( ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR ) )
            // InternalSqlParser.g:3048:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR )
            {
            // InternalSqlParser.g:3048:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR )
            // InternalSqlParser.g:3048:2: ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR
            {
            // InternalSqlParser.g:3048:2: ( (lv_dbname_0_0= ruleDBID ) )
            // InternalSqlParser.g:3049:1: (lv_dbname_0_0= ruleDBID )
            {
            // InternalSqlParser.g:3049:1: (lv_dbname_0_0= ruleDBID )
            // InternalSqlParser.g:3050:3: lv_dbname_0_0= ruleDBID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getDbObjectNameAllAccess().getDbnameDBIDParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_54);
            lv_dbname_0_0=ruleDBID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDbObjectNameAllRule());
              	        }
                     		set(
                     			current, 
                     			"dbname",
                      		lv_dbname_0_0, 
                      		"com.jaspersoft.studio.data.Sql.DBID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,FullStop,FOLLOW_55); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getDbObjectNameAllAccess().getFullStopKeyword_1());
                  
            }
            this_STAR_2=(Token)match(input,RULE_STAR,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_STAR_2, grammarAccess.getDbObjectNameAllAccess().getSTARTerminalRuleCall_2()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDbObjectNameAll"


    // $ANTLR start "entryRuleDbObjectName"
    // InternalSqlParser.g:3083:1: entryRuleDbObjectName returns [EObject current=null] : iv_ruleDbObjectName= ruleDbObjectName EOF ;
    public final EObject entryRuleDbObjectName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectName = null;


        try {
            // InternalSqlParser.g:3084:2: (iv_ruleDbObjectName= ruleDbObjectName EOF )
            // InternalSqlParser.g:3085:2: iv_ruleDbObjectName= ruleDbObjectName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDbObjectNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleDbObjectName=ruleDbObjectName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDbObjectName; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDbObjectName"


    // $ANTLR start "ruleDbObjectName"
    // InternalSqlParser.g:3092:1: ruleDbObjectName returns [EObject current=null] : ( (lv_dbname_0_0= ruleDBID ) ) ;
    public final EObject ruleDbObjectName() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3095:28: ( ( (lv_dbname_0_0= ruleDBID ) ) )
            // InternalSqlParser.g:3096:1: ( (lv_dbname_0_0= ruleDBID ) )
            {
            // InternalSqlParser.g:3096:1: ( (lv_dbname_0_0= ruleDBID ) )
            // InternalSqlParser.g:3097:1: (lv_dbname_0_0= ruleDBID )
            {
            // InternalSqlParser.g:3097:1: (lv_dbname_0_0= ruleDBID )
            // InternalSqlParser.g:3098:3: lv_dbname_0_0= ruleDBID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getDbObjectNameAccess().getDbnameDBIDParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_dbname_0_0=ruleDBID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDbObjectNameRule());
              	        }
                     		set(
                     			current, 
                     			"dbname",
                      		lv_dbname_0_0, 
                      		"com.jaspersoft.studio.data.Sql.DBID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDbObjectName"


    // $ANTLR start "entryRuleOrderByColumns"
    // InternalSqlParser.g:3122:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // InternalSqlParser.g:3123:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // InternalSqlParser.g:3124:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrderByColumnsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrderByColumns; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrderByColumns"


    // $ANTLR start "ruleOrderByColumns"
    // InternalSqlParser.g:3131:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3134:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // InternalSqlParser.g:3135:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // InternalSqlParser.g:3135:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // InternalSqlParser.g:3136:2: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_OrderByColumnFull_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:3147:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==Comma) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // InternalSqlParser.g:3147:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // InternalSqlParser.g:3147:2: ()
                    // InternalSqlParser.g:3148:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:3156:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt63=0;
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==Comma) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // InternalSqlParser.g:3157:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_26); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:3161:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // InternalSqlParser.g:3162:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // InternalSqlParser.g:3162:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // InternalSqlParser.g:3163:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleOrderByColumnFull();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getOrderByColumnsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.OrderByColumnFull");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt63 >= 1 ) break loop63;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(63, input);
                                throw eee;
                        }
                        cnt63++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrderByColumns"


    // $ANTLR start "entryRuleOrderByColumnFull"
    // InternalSqlParser.g:3187:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // InternalSqlParser.g:3188:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // InternalSqlParser.g:3189:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrderByColumnFullRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrderByColumnFull; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrderByColumnFull"


    // $ANTLR start "ruleOrderByColumnFull"
    // InternalSqlParser.g:3196:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_colOrderInt_1_0=null;
        Token lv_direction_2_1=null;
        Token lv_direction_2_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3199:28: ( ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? ) )
            // InternalSqlParser.g:3200:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? )
            {
            // InternalSqlParser.g:3200:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? )
            // InternalSqlParser.g:3200:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )?
            {
            // InternalSqlParser.g:3200:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( ((LA65_0>=RULE_STRING && LA65_0<=RULE_ID)) ) {
                alt65=1;
            }
            else if ( (LA65_0==RULE_UNSIGNED) ) {
                alt65=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }
            switch (alt65) {
                case 1 :
                    // InternalSqlParser.g:3200:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    {
                    // InternalSqlParser.g:3200:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    // InternalSqlParser.g:3201:1: (lv_colOrder_0_0= ruleColumnFull )
                    {
                    // InternalSqlParser.g:3201:1: (lv_colOrder_0_0= ruleColumnFull )
                    // InternalSqlParser.g:3202:3: lv_colOrder_0_0= ruleColumnFull
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_56);
                    lv_colOrder_0_0=ruleColumnFull();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOrderByColumnFullRule());
                      	        }
                             		set(
                             			current, 
                             			"colOrder",
                              		lv_colOrder_0_0, 
                              		"com.jaspersoft.studio.data.Sql.ColumnFull");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3219:6: ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:3219:6: ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:3220:1: (lv_colOrderInt_1_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:3220:1: (lv_colOrderInt_1_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:3221:3: lv_colOrderInt_1_0= RULE_UNSIGNED
                    {
                    lv_colOrderInt_1_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_56); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_colOrderInt_1_0, grammarAccess.getOrderByColumnFullAccess().getColOrderIntUNSIGNEDTerminalRuleCall_0_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"colOrderInt",
                              		lv_colOrderInt_1_0, 
                              		"com.jaspersoft.studio.data.Sql.UNSIGNED");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:3237:3: ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==DESC||LA67_0==ASC) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalSqlParser.g:3238:1: ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) )
                    {
                    // InternalSqlParser.g:3238:1: ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) )
                    // InternalSqlParser.g:3239:1: (lv_direction_2_1= ASC | lv_direction_2_2= DESC )
                    {
                    // InternalSqlParser.g:3239:1: (lv_direction_2_1= ASC | lv_direction_2_2= DESC )
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==ASC) ) {
                        alt66=1;
                    }
                    else if ( (LA66_0==DESC) ) {
                        alt66=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 66, 0, input);

                        throw nvae;
                    }
                    switch (alt66) {
                        case 1 :
                            // InternalSqlParser.g:3240:3: lv_direction_2_1= ASC
                            {
                            lv_direction_2_1=(Token)match(input,ASC,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_direction_2_1, grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                              	        }
                                     		setWithLastConsumed(current, "direction", lv_direction_2_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:3253:8: lv_direction_2_2= DESC
                            {
                            lv_direction_2_2=(Token)match(input,DESC,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_direction_2_2, grammarAccess.getOrderByColumnFullAccess().getDirectionDESCKeyword_1_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                              	        }
                                     		setWithLastConsumed(current, "direction", lv_direction_2_2, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrderByColumnFull"


    // $ANTLR start "entryRuleGroupByColumns"
    // InternalSqlParser.g:3277:1: entryRuleGroupByColumns returns [EObject current=null] : iv_ruleGroupByColumns= ruleGroupByColumns EOF ;
    public final EObject entryRuleGroupByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumns = null;


        try {
            // InternalSqlParser.g:3278:2: (iv_ruleGroupByColumns= ruleGroupByColumns EOF )
            // InternalSqlParser.g:3279:2: iv_ruleGroupByColumns= ruleGroupByColumns EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGroupByColumnsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleGroupByColumns=ruleGroupByColumns();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGroupByColumns; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGroupByColumns"


    // $ANTLR start "ruleGroupByColumns"
    // InternalSqlParser.g:3286:1: ruleGroupByColumns returns [EObject current=null] : (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) ;
    public final EObject ruleGroupByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_GroupByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3289:28: ( (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) )
            // InternalSqlParser.g:3290:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            {
            // InternalSqlParser.g:3290:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            // InternalSqlParser.g:3291:2: this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_GroupByColumnFull_0=ruleGroupByColumnFull();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_GroupByColumnFull_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:3302:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==Comma) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // InternalSqlParser.g:3302:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    {
                    // InternalSqlParser.g:3302:2: ()
                    // InternalSqlParser.g:3303:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:3311:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    int cnt68=0;
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==Comma) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // InternalSqlParser.g:3312:2: otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_26); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:3316:1: ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    // InternalSqlParser.g:3317:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    {
                    	    // InternalSqlParser.g:3317:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    // InternalSqlParser.g:3318:3: lv_entries_3_0= ruleGroupByColumnFull
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleGroupByColumnFull();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getGroupByColumnsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.GroupByColumnFull");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt68 >= 1 ) break loop68;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(68, input);
                                throw eee;
                        }
                        cnt68++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGroupByColumns"


    // $ANTLR start "entryRuleGroupByColumnFull"
    // InternalSqlParser.g:3342:1: entryRuleGroupByColumnFull returns [EObject current=null] : iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF ;
    public final EObject entryRuleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumnFull = null;


        try {
            // InternalSqlParser.g:3343:2: (iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF )
            // InternalSqlParser.g:3344:2: iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGroupByColumnFullRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleGroupByColumnFull=ruleGroupByColumnFull();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGroupByColumnFull; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGroupByColumnFull"


    // $ANTLR start "ruleGroupByColumnFull"
    // InternalSqlParser.g:3351:1: ruleGroupByColumnFull returns [EObject current=null] : ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) ) ;
    public final EObject ruleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_grByInt_2_0=null;
        EObject lv_colGrBy_0_0 = null;

        EObject lv_gbFunction_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3354:28: ( ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) ) )
            // InternalSqlParser.g:3355:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) )
            {
            // InternalSqlParser.g:3355:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) )
            int alt70=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA70_1 = input.LA(2);

                if ( (LA70_1==EOF||LA70_1==INTERSECT||LA70_1==EXCEPT||LA70_1==HAVING||LA70_1==OFFSET||LA70_1==FETCH||(LA70_1>=LIMIT && LA70_1<=MINUS)||LA70_1==ORDER||LA70_1==UNION||LA70_1==RightParenthesis||LA70_1==Comma||LA70_1==FullStop) ) {
                    alt70=1;
                }
                else if ( (LA70_1==LeftParenthesis) ) {
                    alt70=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 70, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt70=1;
                }
                break;
            case RULE_UNSIGNED:
                {
                alt70=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }

            switch (alt70) {
                case 1 :
                    // InternalSqlParser.g:3355:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    {
                    // InternalSqlParser.g:3355:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    // InternalSqlParser.g:3356:1: (lv_colGrBy_0_0= ruleColumnFull )
                    {
                    // InternalSqlParser.g:3356:1: (lv_colGrBy_0_0= ruleColumnFull )
                    // InternalSqlParser.g:3357:3: lv_colGrBy_0_0= ruleColumnFull
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getColGrByColumnFullParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_colGrBy_0_0=ruleColumnFull();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getGroupByColumnFullRule());
                      	        }
                             		set(
                             			current, 
                             			"colGrBy",
                              		lv_colGrBy_0_0, 
                              		"com.jaspersoft.studio.data.Sql.ColumnFull");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3374:6: ( (lv_gbFunction_1_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:3374:6: ( (lv_gbFunction_1_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:3375:1: (lv_gbFunction_1_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:3375:1: (lv_gbFunction_1_0= ruleOperandFunction )
                    // InternalSqlParser.g:3376:3: lv_gbFunction_1_0= ruleOperandFunction
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getGbFunctionOperandFunctionParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_gbFunction_1_0=ruleOperandFunction();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getGroupByColumnFullRule());
                      	        }
                             		set(
                             			current, 
                             			"gbFunction",
                              		lv_gbFunction_1_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandFunction");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:3393:6: ( (lv_grByInt_2_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:3393:6: ( (lv_grByInt_2_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:3394:1: (lv_grByInt_2_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:3394:1: (lv_grByInt_2_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:3395:3: lv_grByInt_2_0= RULE_UNSIGNED
                    {
                    lv_grByInt_2_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_grByInt_2_0, grammarAccess.getGroupByColumnFullAccess().getGrByIntUNSIGNEDTerminalRuleCall_2_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getGroupByColumnFullRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"grByInt",
                              		lv_grByInt_2_0, 
                              		"com.jaspersoft.studio.data.Sql.UNSIGNED");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGroupByColumnFull"


    // $ANTLR start "entryRuleFullExpression"
    // InternalSqlParser.g:3419:1: entryRuleFullExpression returns [EObject current=null] : iv_ruleFullExpression= ruleFullExpression EOF ;
    public final EObject entryRuleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullExpression = null;


        try {
            // InternalSqlParser.g:3420:2: (iv_ruleFullExpression= ruleFullExpression EOF )
            // InternalSqlParser.g:3421:2: iv_ruleFullExpression= ruleFullExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFullExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFullExpression=ruleFullExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFullExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFullExpression"


    // $ANTLR start "ruleFullExpression"
    // InternalSqlParser.g:3428:1: ruleFullExpression returns [EObject current=null] : (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) ;
    public final EObject ruleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExpressionFragment_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3431:28: ( (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) )
            // InternalSqlParser.g:3432:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            {
            // InternalSqlParser.g:3432:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            // InternalSqlParser.g:3433:2: this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_57);
            this_ExpressionFragment_0=ruleExpressionFragment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_ExpressionFragment_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:3444:1: ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==AND||LA72_0==OR||LA72_0==RULE_JRNPARAM) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalSqlParser.g:3444:2: () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    {
                    // InternalSqlParser.g:3444:2: ()
                    // InternalSqlParser.g:3445:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:3453:2: ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    int cnt71=0;
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==AND||LA71_0==OR||LA71_0==RULE_JRNPARAM) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // InternalSqlParser.g:3454:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    {
                    	    // InternalSqlParser.g:3454:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    // InternalSqlParser.g:3455:3: lv_entries_2_0= ruleExpressionFragmentSecond
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_57);
                    	    lv_entries_2_0=ruleExpressionFragmentSecond();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getFullExpressionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_2_0, 
                    	              		"com.jaspersoft.studio.data.Sql.ExpressionFragmentSecond");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt71 >= 1 ) break loop71;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(71, input);
                                throw eee;
                        }
                        cnt71++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFullExpression"


    // $ANTLR start "entryRuleExpressionFragmentSecond"
    // InternalSqlParser.g:3479:1: entryRuleExpressionFragmentSecond returns [EObject current=null] : iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF ;
    public final EObject entryRuleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragmentSecond = null;


        try {
            // InternalSqlParser.g:3480:2: (iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF )
            // InternalSqlParser.g:3481:2: iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionFragmentSecondRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpressionFragmentSecond=ruleExpressionFragmentSecond();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpressionFragmentSecond; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpressionFragmentSecond"


    // $ANTLR start "ruleExpressionFragmentSecond"
    // InternalSqlParser.g:3488:1: ruleExpressionFragmentSecond returns [EObject current=null] : ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) ;
    public final EObject ruleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        Token lv_c_0_1=null;
        Token lv_c_0_2=null;
        Token lv_notPrm_2_0=null;
        EObject lv_efrag_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3491:28: ( ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) )
            // InternalSqlParser.g:3492:1: ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            {
            // InternalSqlParser.g:3492:1: ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==AND||LA74_0==OR) ) {
                alt74=1;
            }
            else if ( (LA74_0==RULE_JRNPARAM) ) {
                alt74=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }
            switch (alt74) {
                case 1 :
                    // InternalSqlParser.g:3492:2: ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    {
                    // InternalSqlParser.g:3492:2: ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    // InternalSqlParser.g:3492:3: ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    {
                    // InternalSqlParser.g:3492:3: ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) )
                    // InternalSqlParser.g:3493:1: ( (lv_c_0_1= AND | lv_c_0_2= OR ) )
                    {
                    // InternalSqlParser.g:3493:1: ( (lv_c_0_1= AND | lv_c_0_2= OR ) )
                    // InternalSqlParser.g:3494:1: (lv_c_0_1= AND | lv_c_0_2= OR )
                    {
                    // InternalSqlParser.g:3494:1: (lv_c_0_1= AND | lv_c_0_2= OR )
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==AND) ) {
                        alt73=1;
                    }
                    else if ( (LA73_0==OR) ) {
                        alt73=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 73, 0, input);

                        throw nvae;
                    }
                    switch (alt73) {
                        case 1 :
                            // InternalSqlParser.g:3495:3: lv_c_0_1= AND
                            {
                            lv_c_0_1=(Token)match(input,AND,FOLLOW_23); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_c_0_1, grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                              	        }
                                     		setWithLastConsumed(current, "c", lv_c_0_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:3508:8: lv_c_0_2= OR
                            {
                            lv_c_0_2=(Token)match(input,OR,FOLLOW_23); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_c_0_2, grammarAccess.getExpressionFragmentSecondAccess().getCORKeyword_0_0_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                              	        }
                                     		setWithLastConsumed(current, "c", lv_c_0_2, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // InternalSqlParser.g:3524:2: ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    // InternalSqlParser.g:3525:1: (lv_efrag_1_0= ruleExpressionFragment )
                    {
                    // InternalSqlParser.g:3525:1: (lv_efrag_1_0= ruleExpressionFragment )
                    // InternalSqlParser.g:3526:3: lv_efrag_1_0= ruleExpressionFragment
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_efrag_1_0=ruleExpressionFragment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionFragmentSecondRule());
                      	        }
                             		set(
                             			current, 
                             			"efrag",
                              		lv_efrag_1_0, 
                              		"com.jaspersoft.studio.data.Sql.ExpressionFragment");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3543:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    {
                    // InternalSqlParser.g:3543:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    // InternalSqlParser.g:3544:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    {
                    // InternalSqlParser.g:3544:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    // InternalSqlParser.g:3545:3: lv_notPrm_2_0= RULE_JRNPARAM
                    {
                    lv_notPrm_2_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_notPrm_2_0, grammarAccess.getExpressionFragmentSecondAccess().getNotPrmJRNPARAMTerminalRuleCall_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"notPrm",
                              		lv_notPrm_2_0, 
                              		"com.jaspersoft.studio.data.Sql.JRNPARAM");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpressionFragmentSecond"


    // $ANTLR start "entryRuleExpressionFragment"
    // InternalSqlParser.g:3569:1: entryRuleExpressionFragment returns [EObject current=null] : iv_ruleExpressionFragment= ruleExpressionFragment EOF ;
    public final EObject entryRuleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragment = null;


        try {
            // InternalSqlParser.g:3570:2: (iv_ruleExpressionFragment= ruleExpressionFragment EOF )
            // InternalSqlParser.g:3571:2: iv_ruleExpressionFragment= ruleExpressionFragment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionFragmentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpressionFragment=ruleExpressionFragment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpressionFragment; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpressionFragment"


    // $ANTLR start "ruleExpressionFragment"
    // InternalSqlParser.g:3578:1: ruleExpressionFragment returns [EObject current=null] : ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) ;
    public final EObject ruleExpressionFragment() throws RecognitionException {
        EObject current = null;

        Token lv_notPrm_3_0=null;
        EObject lv_expgroup_0_0 = null;

        EObject lv_exp_1_0 = null;

        EObject lv_xexp_2_1 = null;

        EObject lv_xexp_2_2 = null;

        EObject lv_in_4_0 = null;

        EObject lv_exists_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3581:28: ( ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) )
            // InternalSqlParser.g:3582:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            {
            // InternalSqlParser.g:3582:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            int alt76=6;
            alt76 = dfa76.predict(input);
            switch (alt76) {
                case 1 :
                    // InternalSqlParser.g:3582:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    {
                    // InternalSqlParser.g:3582:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    // InternalSqlParser.g:3583:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    {
                    // InternalSqlParser.g:3583:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    // InternalSqlParser.g:3584:3: lv_expgroup_0_0= ruleExpressionGroup
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_expgroup_0_0=ruleExpressionGroup();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"expgroup",
                              		lv_expgroup_0_0, 
                              		"com.jaspersoft.studio.data.Sql.ExpressionGroup");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3601:6: ( (lv_exp_1_0= ruleExpression ) )
                    {
                    // InternalSqlParser.g:3601:6: ( (lv_exp_1_0= ruleExpression ) )
                    // InternalSqlParser.g:3602:1: (lv_exp_1_0= ruleExpression )
                    {
                    // InternalSqlParser.g:3602:1: (lv_exp_1_0= ruleExpression )
                    // InternalSqlParser.g:3603:3: lv_exp_1_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_exp_1_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"exp",
                              		lv_exp_1_0, 
                              		"com.jaspersoft.studio.data.Sql.Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:3620:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    {
                    // InternalSqlParser.g:3620:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    // InternalSqlParser.g:3621:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    {
                    // InternalSqlParser.g:3621:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    // InternalSqlParser.g:3622:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    {
                    // InternalSqlParser.g:3622:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    int alt75=2;
                    alt75 = dfa75.predict(input);
                    switch (alt75) {
                        case 1 :
                            // InternalSqlParser.g:3623:3: lv_xexp_2_1= ruleXExpression
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_2);
                            lv_xexp_2_1=ruleXExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"xexp",
                                      		lv_xexp_2_1, 
                                      		"com.jaspersoft.studio.data.Sql.XExpression");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:3638:8: lv_xexp_2_2= ruleXExpression_
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpression_ParserRuleCall_2_0_1()); 
                              	    
                            }
                            pushFollow(FOLLOW_2);
                            lv_xexp_2_2=ruleXExpression_();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"xexp",
                                      		lv_xexp_2_2, 
                                      		"com.jaspersoft.studio.data.Sql.XExpression_");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:3657:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    {
                    // InternalSqlParser.g:3657:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    // InternalSqlParser.g:3658:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    {
                    // InternalSqlParser.g:3658:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    // InternalSqlParser.g:3659:3: lv_notPrm_3_0= RULE_JRNPARAM
                    {
                    lv_notPrm_3_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_notPrm_3_0, grammarAccess.getExpressionFragmentAccess().getNotPrmJRNPARAMTerminalRuleCall_3_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpressionFragmentRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"notPrm",
                              		lv_notPrm_3_0, 
                              		"com.jaspersoft.studio.data.Sql.JRNPARAM");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:3676:6: ( (lv_in_4_0= ruleInOperator ) )
                    {
                    // InternalSqlParser.g:3676:6: ( (lv_in_4_0= ruleInOperator ) )
                    // InternalSqlParser.g:3677:1: (lv_in_4_0= ruleInOperator )
                    {
                    // InternalSqlParser.g:3677:1: (lv_in_4_0= ruleInOperator )
                    // InternalSqlParser.g:3678:3: lv_in_4_0= ruleInOperator
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getInInOperatorParserRuleCall_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_in_4_0=ruleInOperator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"in",
                              		lv_in_4_0, 
                              		"com.jaspersoft.studio.data.Sql.InOperator");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:3695:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    {
                    // InternalSqlParser.g:3695:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    // InternalSqlParser.g:3696:1: (lv_exists_5_0= ruleExistsOperator )
                    {
                    // InternalSqlParser.g:3696:1: (lv_exists_5_0= ruleExistsOperator )
                    // InternalSqlParser.g:3697:3: lv_exists_5_0= ruleExistsOperator
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExistsExistsOperatorParserRuleCall_5_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_exists_5_0=ruleExistsOperator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"exists",
                              		lv_exists_5_0, 
                              		"com.jaspersoft.studio.data.Sql.ExistsOperator");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpressionFragment"


    // $ANTLR start "entryRuleExpressionGroup"
    // InternalSqlParser.g:3721:1: entryRuleExpressionGroup returns [EObject current=null] : iv_ruleExpressionGroup= ruleExpressionGroup EOF ;
    public final EObject entryRuleExpressionGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionGroup = null;


        try {
            // InternalSqlParser.g:3722:2: (iv_ruleExpressionGroup= ruleExpressionGroup EOF )
            // InternalSqlParser.g:3723:2: iv_ruleExpressionGroup= ruleExpressionGroup EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionGroupRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpressionGroup=ruleExpressionGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpressionGroup; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpressionGroup"


    // $ANTLR start "ruleExpressionGroup"
    // InternalSqlParser.g:3730:1: ruleExpressionGroup returns [EObject current=null] : ( () ( ( (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis ) ;
    public final EObject ruleExpressionGroup() throws RecognitionException {
        EObject current = null;

        Token lv_isnot_1_1=null;
        Token lv_isnot_1_2=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3733:28: ( ( () ( ( (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis ) )
            // InternalSqlParser.g:3734:1: ( () ( ( (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis )
            {
            // InternalSqlParser.g:3734:1: ( () ( ( (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis )
            // InternalSqlParser.g:3734:2: () ( ( (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis
            {
            // InternalSqlParser.g:3734:2: ()
            // InternalSqlParser.g:3735:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getExpressionGroupAccess().getExprGroupAction_0(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:3743:2: ( ( (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==NOT_1||LA78_0==NOT) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalSqlParser.g:3744:1: ( (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 ) )
                    {
                    // InternalSqlParser.g:3744:1: ( (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 ) )
                    // InternalSqlParser.g:3745:1: (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 )
                    {
                    // InternalSqlParser.g:3745:1: (lv_isnot_1_1= NOT | lv_isnot_1_2= NOT_1 )
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==NOT) ) {
                        alt77=1;
                    }
                    else if ( (LA77_0==NOT_1) ) {
                        alt77=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 77, 0, input);

                        throw nvae;
                    }
                    switch (alt77) {
                        case 1 :
                            // InternalSqlParser.g:3746:3: lv_isnot_1_1= NOT
                            {
                            lv_isnot_1_1=(Token)match(input,NOT,FOLLOW_7); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_isnot_1_1, grammarAccess.getExpressionGroupAccess().getIsnotNOTKeyword_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpressionGroupRule());
                              	        }
                                     		setWithLastConsumed(current, "isnot", lv_isnot_1_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:3759:8: lv_isnot_1_2= NOT_1
                            {
                            lv_isnot_1_2=(Token)match(input,NOT_1,FOLLOW_7); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_isnot_1_2, grammarAccess.getExpressionGroupAccess().getIsnotNOTKeyword_1_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpressionGroupRule());
                              	        }
                                     		setWithLastConsumed(current, "isnot", lv_isnot_1_2, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_23); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_2());
                  
            }
            // InternalSqlParser.g:3780:1: ( (lv_expr_3_0= ruleFullExpression ) )
            // InternalSqlParser.g:3781:1: (lv_expr_3_0= ruleFullExpression )
            {
            // InternalSqlParser.g:3781:1: (lv_expr_3_0= ruleFullExpression )
            // InternalSqlParser.g:3782:3: lv_expr_3_0= ruleFullExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_expr_3_0=ruleFullExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpressionGroupRule());
              	        }
                     		set(
                     			current, 
                     			"expr",
                      		lv_expr_3_0, 
                      		"com.jaspersoft.studio.data.Sql.FullExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getExpressionGroupAccess().getRightParenthesisKeyword_4());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpressionGroup"


    // $ANTLR start "entryRuleXExpression"
    // InternalSqlParser.g:3811:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // InternalSqlParser.g:3812:2: (iv_ruleXExpression= ruleXExpression EOF )
            // InternalSqlParser.g:3813:2: iv_ruleXExpression= ruleXExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXExpression"


    // $ANTLR start "ruleXExpression"
    // InternalSqlParser.g:3820:1: ruleXExpression returns [EObject current=null] : (otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= Comma ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= Comma ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket ) ;
    public final EObject ruleXExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Enumerator lv_xf_2_0 = null;

        EObject lv_col_4_0 = null;

        EObject lv_prm_6_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3823:28: ( (otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= Comma ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= Comma ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket ) )
            // InternalSqlParser.g:3824:1: (otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= Comma ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= Comma ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket )
            {
            // InternalSqlParser.g:3824:1: (otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= Comma ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= Comma ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket )
            // InternalSqlParser.g:3825:2: otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= Comma ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= Comma ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,X,FOLLOW_58); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getXExpressionAccess().getXKeyword_0());
                  
            }
            // InternalSqlParser.g:3829:1: ()
            // InternalSqlParser.g:3830:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXExpressionAccess().getXExprAction_1(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:3838:2: ( (lv_xf_2_0= ruleXFunction ) )
            // InternalSqlParser.g:3839:1: (lv_xf_2_0= ruleXFunction )
            {
            // InternalSqlParser.g:3839:1: (lv_xf_2_0= ruleXFunction )
            // InternalSqlParser.g:3840:3: lv_xf_2_0= ruleXFunction
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_59);
            lv_xf_2_0=ruleXFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"xf",
                      		lv_xf_2_0, 
                      		"com.jaspersoft.studio.data.Sql.XFunction");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,Comma,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getXExpressionAccess().getCommaKeyword_3());
                  
            }
            // InternalSqlParser.g:3861:1: ( (lv_col_4_0= ruleOperandGroup ) )
            // InternalSqlParser.g:3862:1: (lv_col_4_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:3862:1: (lv_col_4_0= ruleOperandGroup )
            // InternalSqlParser.g:3863:3: lv_col_4_0= ruleOperandGroup
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXExpressionAccess().getColOperandGroupParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_61);
            lv_col_4_0=ruleOperandGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"col",
                      		lv_col_4_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandGroup");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:3879:2: (otherlv_5= Comma ( (lv_prm_6_0= ruleXExpressionParams ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==Comma) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // InternalSqlParser.g:3880:2: otherlv_5= Comma ( (lv_prm_6_0= ruleXExpressionParams ) )
                    {
                    otherlv_5=(Token)match(input,Comma,FOLLOW_46); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getXExpressionAccess().getCommaKeyword_5_0());
                          
                    }
                    // InternalSqlParser.g:3884:1: ( (lv_prm_6_0= ruleXExpressionParams ) )
                    // InternalSqlParser.g:3885:1: (lv_prm_6_0= ruleXExpressionParams )
                    {
                    // InternalSqlParser.g:3885:1: (lv_prm_6_0= ruleXExpressionParams )
                    // InternalSqlParser.g:3886:3: lv_prm_6_0= ruleXExpressionParams
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_62);
                    lv_prm_6_0=ruleXExpressionParams();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"prm",
                              		lv_prm_6_0, 
                              		"com.jaspersoft.studio.data.Sql.XExpressionParams");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXExpression"


    // $ANTLR start "entryRuleXExpression_"
    // InternalSqlParser.g:3915:1: entryRuleXExpression_ returns [EObject current=null] : iv_ruleXExpression_= ruleXExpression_ EOF ;
    public final EObject entryRuleXExpression_() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression_ = null;


        try {
            // InternalSqlParser.g:3916:2: (iv_ruleXExpression_= ruleXExpression_ EOF )
            // InternalSqlParser.g:3917:2: iv_ruleXExpression_= ruleXExpression_ EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpression_Rule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXExpression_=ruleXExpression_();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpression_; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXExpression_"


    // $ANTLR start "ruleXExpression_"
    // InternalSqlParser.g:3924:1: ruleXExpression_ returns [EObject current=null] : (otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= VerticalLine ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= VerticalLine ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket ) ;
    public final EObject ruleXExpression_() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Enumerator lv_xf_2_0 = null;

        EObject lv_col_4_0 = null;

        EObject lv_prm_6_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3927:28: ( (otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= VerticalLine ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= VerticalLine ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket ) )
            // InternalSqlParser.g:3928:1: (otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= VerticalLine ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= VerticalLine ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket )
            {
            // InternalSqlParser.g:3928:1: (otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= VerticalLine ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= VerticalLine ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket )
            // InternalSqlParser.g:3929:2: otherlv_0= X () ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= VerticalLine ( (lv_col_4_0= ruleOperandGroup ) ) (otherlv_5= VerticalLine ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,X,FOLLOW_58); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getXExpression_Access().getXKeyword_0());
                  
            }
            // InternalSqlParser.g:3933:1: ()
            // InternalSqlParser.g:3934:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXExpression_Access().getXExprAction_1(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:3942:2: ( (lv_xf_2_0= ruleXFunction ) )
            // InternalSqlParser.g:3943:1: (lv_xf_2_0= ruleXFunction )
            {
            // InternalSqlParser.g:3943:1: (lv_xf_2_0= ruleXFunction )
            // InternalSqlParser.g:3944:3: lv_xf_2_0= ruleXFunction
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXExpression_Access().getXfXFunctionEnumRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_63);
            lv_xf_2_0=ruleXFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
              	        }
                     		set(
                     			current, 
                     			"xf",
                      		lv_xf_2_0, 
                      		"com.jaspersoft.studio.data.Sql.XFunction");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,VerticalLine,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getXExpression_Access().getVerticalLineKeyword_3());
                  
            }
            // InternalSqlParser.g:3965:1: ( (lv_col_4_0= ruleOperandGroup ) )
            // InternalSqlParser.g:3966:1: (lv_col_4_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:3966:1: (lv_col_4_0= ruleOperandGroup )
            // InternalSqlParser.g:3967:3: lv_col_4_0= ruleOperandGroup
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXExpression_Access().getColOperandGroupParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_64);
            lv_col_4_0=ruleOperandGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
              	        }
                     		set(
                     			current, 
                     			"col",
                      		lv_col_4_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandGroup");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:3983:2: (otherlv_5= VerticalLine ( (lv_prm_6_0= ruleXExpressionParams ) ) )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==VerticalLine) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // InternalSqlParser.g:3984:2: otherlv_5= VerticalLine ( (lv_prm_6_0= ruleXExpressionParams ) )
                    {
                    otherlv_5=(Token)match(input,VerticalLine,FOLLOW_46); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getXExpression_Access().getVerticalLineKeyword_5_0());
                          
                    }
                    // InternalSqlParser.g:3988:1: ( (lv_prm_6_0= ruleXExpressionParams ) )
                    // InternalSqlParser.g:3989:1: (lv_prm_6_0= ruleXExpressionParams )
                    {
                    // InternalSqlParser.g:3989:1: (lv_prm_6_0= ruleXExpressionParams )
                    // InternalSqlParser.g:3990:3: lv_prm_6_0= ruleXExpressionParams
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXExpression_Access().getPrmXExpressionParamsParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_62);
                    lv_prm_6_0=ruleXExpressionParams();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
                      	        }
                             		set(
                             			current, 
                             			"prm",
                              		lv_prm_6_0, 
                              		"com.jaspersoft.studio.data.Sql.XExpressionParams");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getXExpression_Access().getRightCurlyBracketKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXExpression_"


    // $ANTLR start "entryRuleXExpressionParams"
    // InternalSqlParser.g:4019:1: entryRuleXExpressionParams returns [EObject current=null] : iv_ruleXExpressionParams= ruleXExpressionParams EOF ;
    public final EObject entryRuleXExpressionParams() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionParams = null;


        try {
            // InternalSqlParser.g:4020:2: (iv_ruleXExpressionParams= ruleXExpressionParams EOF )
            // InternalSqlParser.g:4021:2: iv_ruleXExpressionParams= ruleXExpressionParams EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpressionParamsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXExpressionParams=ruleXExpressionParams();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpressionParams; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXExpressionParams"


    // $ANTLR start "ruleXExpressionParams"
    // InternalSqlParser.g:4028:1: ruleXExpressionParams returns [EObject current=null] : (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) ;
    public final EObject ruleXExpressionParams() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_JRParameter_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4031:28: ( (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) )
            // InternalSqlParser.g:4032:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            {
            // InternalSqlParser.g:4032:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            // InternalSqlParser.g:4033:2: this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_JRParameter_0=ruleJRParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_JRParameter_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:4044:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==Comma) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // InternalSqlParser.g:4044:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    {
                    // InternalSqlParser.g:4044:2: ()
                    // InternalSqlParser.g:4045:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:4053:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    int cnt81=0;
                    loop81:
                    do {
                        int alt81=2;
                        int LA81_0 = input.LA(1);

                        if ( (LA81_0==Comma) ) {
                            alt81=1;
                        }


                        switch (alt81) {
                    	case 1 :
                    	    // InternalSqlParser.g:4054:2: otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_46); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:4058:1: ( (lv_entries_3_0= ruleJRParameter ) )
                    	    // InternalSqlParser.g:4059:1: (lv_entries_3_0= ruleJRParameter )
                    	    {
                    	    // InternalSqlParser.g:4059:1: (lv_entries_3_0= ruleJRParameter )
                    	    // InternalSqlParser.g:4060:3: lv_entries_3_0= ruleJRParameter
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleJRParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getXExpressionParamsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.JRParameter");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt81 >= 1 ) break loop81;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(81, input);
                                throw eee;
                        }
                        cnt81++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXExpressionParams"


    // $ANTLR start "entryRuleJRParameter"
    // InternalSqlParser.g:4084:1: entryRuleJRParameter returns [EObject current=null] : iv_ruleJRParameter= ruleJRParameter EOF ;
    public final EObject entryRuleJRParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameter = null;


        try {
            // InternalSqlParser.g:4085:2: (iv_ruleJRParameter= ruleJRParameter EOF )
            // InternalSqlParser.g:4086:2: iv_ruleJRParameter= ruleJRParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJRParameter=ruleJRParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRParameter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJRParameter"


    // $ANTLR start "ruleJRParameter"
    // InternalSqlParser.g:4093:1: ruleJRParameter returns [EObject current=null] : ( (lv_jrprm_0_0= RULE_ID ) ) ;
    public final EObject ruleJRParameter() throws RecognitionException {
        EObject current = null;

        Token lv_jrprm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:4096:28: ( ( (lv_jrprm_0_0= RULE_ID ) ) )
            // InternalSqlParser.g:4097:1: ( (lv_jrprm_0_0= RULE_ID ) )
            {
            // InternalSqlParser.g:4097:1: ( (lv_jrprm_0_0= RULE_ID ) )
            // InternalSqlParser.g:4098:1: (lv_jrprm_0_0= RULE_ID )
            {
            // InternalSqlParser.g:4098:1: (lv_jrprm_0_0= RULE_ID )
            // InternalSqlParser.g:4099:3: lv_jrprm_0_0= RULE_ID
            {
            lv_jrprm_0_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_jrprm_0_0, grammarAccess.getJRParameterAccess().getJrprmIDTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getJRParameterRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"jrprm",
                      		lv_jrprm_0_0, 
                      		"com.jaspersoft.studio.data.Sql.ID");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJRParameter"


    // $ANTLR start "entryRuleExpression"
    // InternalSqlParser.g:4123:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalSqlParser.g:4124:2: (iv_ruleExpression= ruleExpression EOF )
            // InternalSqlParser.g:4125:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalSqlParser.g:4132:1: ruleExpression returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperand ) ) ( ( (lv_isnull_1_0= ruleIsNullValue ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject lv_op1_0_0 = null;

        AntlrDatatypeRuleToken lv_isnull_1_0 = null;

        EObject lv_in_2_0 = null;

        EObject lv_exists_3_0 = null;

        EObject lv_between_4_0 = null;

        EObject lv_like_5_0 = null;

        EObject lv_comp_6_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4135:28: ( ( ( (lv_op1_0_0= ruleOperand ) ) ( ( (lv_isnull_1_0= ruleIsNullValue ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) )
            // InternalSqlParser.g:4136:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( (lv_isnull_1_0= ruleIsNullValue ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            {
            // InternalSqlParser.g:4136:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( (lv_isnull_1_0= ruleIsNullValue ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            // InternalSqlParser.g:4136:2: ( (lv_op1_0_0= ruleOperand ) ) ( ( (lv_isnull_1_0= ruleIsNullValue ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            {
            // InternalSqlParser.g:4136:2: ( (lv_op1_0_0= ruleOperand ) )
            // InternalSqlParser.g:4137:1: (lv_op1_0_0= ruleOperand )
            {
            // InternalSqlParser.g:4137:1: (lv_op1_0_0= ruleOperand )
            // InternalSqlParser.g:4138:3: lv_op1_0_0= ruleOperand
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_65);
            lv_op1_0_0=ruleOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"op1",
                      		lv_op1_0_0, 
                      		"com.jaspersoft.studio.data.Sql.Operand");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:4154:2: ( ( (lv_isnull_1_0= ruleIsNullValue ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            int alt83=6;
            switch ( input.LA(1) ) {
            case IS:
                {
                alt83=1;
                }
                break;
            case NOT:
                {
                switch ( input.LA(2) ) {
                case BETWEEN:
                    {
                    alt83=4;
                    }
                    break;
                case EXISTS:
                    {
                    alt83=3;
                    }
                    break;
                case LIKE:
                    {
                    alt83=5;
                    }
                    break;
                case IN:
                    {
                    alt83=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 83, 2, input);

                    throw nvae;
                }

                }
                break;
            case IN:
                {
                alt83=2;
                }
                break;
            case EXISTS:
                {
                alt83=3;
                }
                break;
            case BETWEEN:
                {
                alt83=4;
                }
                break;
            case LIKE:
                {
                alt83=5;
                }
                break;
            case ExclamationMarkEqualsSign:
            case LessThanSignEqualsSign:
            case LessThanSignGreaterThanSign:
            case GreaterThanSignEqualsSign:
            case CircumflexAccentEqualsSign:
            case LessThanSign:
            case EqualsSign:
            case GreaterThanSign:
                {
                alt83=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }

            switch (alt83) {
                case 1 :
                    // InternalSqlParser.g:4154:3: ( (lv_isnull_1_0= ruleIsNullValue ) )
                    {
                    // InternalSqlParser.g:4154:3: ( (lv_isnull_1_0= ruleIsNullValue ) )
                    // InternalSqlParser.g:4155:1: (lv_isnull_1_0= ruleIsNullValue )
                    {
                    // InternalSqlParser.g:4155:1: (lv_isnull_1_0= ruleIsNullValue )
                    // InternalSqlParser.g:4156:3: lv_isnull_1_0= ruleIsNullValue
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionAccess().getIsnullIsNullValueParserRuleCall_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_isnull_1_0=ruleIsNullValue();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"isnull",
                              		lv_isnull_1_0, 
                              		"com.jaspersoft.studio.data.Sql.IsNullValue");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4173:6: ( (lv_in_2_0= ruleInOperator ) )
                    {
                    // InternalSqlParser.g:4173:6: ( (lv_in_2_0= ruleInOperator ) )
                    // InternalSqlParser.g:4174:1: (lv_in_2_0= ruleInOperator )
                    {
                    // InternalSqlParser.g:4174:1: (lv_in_2_0= ruleInOperator )
                    // InternalSqlParser.g:4175:3: lv_in_2_0= ruleInOperator
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_in_2_0=ruleInOperator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"in",
                              		lv_in_2_0, 
                              		"com.jaspersoft.studio.data.Sql.InOperator");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:4192:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    {
                    // InternalSqlParser.g:4192:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    // InternalSqlParser.g:4193:1: (lv_exists_3_0= ruleExistsOperator )
                    {
                    // InternalSqlParser.g:4193:1: (lv_exists_3_0= ruleExistsOperator )
                    // InternalSqlParser.g:4194:3: lv_exists_3_0= ruleExistsOperator
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionAccess().getExistsExistsOperatorParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_exists_3_0=ruleExistsOperator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"exists",
                              		lv_exists_3_0, 
                              		"com.jaspersoft.studio.data.Sql.ExistsOperator");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:4211:6: ( (lv_between_4_0= ruleBetween ) )
                    {
                    // InternalSqlParser.g:4211:6: ( (lv_between_4_0= ruleBetween ) )
                    // InternalSqlParser.g:4212:1: (lv_between_4_0= ruleBetween )
                    {
                    // InternalSqlParser.g:4212:1: (lv_between_4_0= ruleBetween )
                    // InternalSqlParser.g:4213:3: lv_between_4_0= ruleBetween
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_between_4_0=ruleBetween();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"between",
                              		lv_between_4_0, 
                              		"com.jaspersoft.studio.data.Sql.Between");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:4230:6: ( (lv_like_5_0= ruleLike ) )
                    {
                    // InternalSqlParser.g:4230:6: ( (lv_like_5_0= ruleLike ) )
                    // InternalSqlParser.g:4231:1: (lv_like_5_0= ruleLike )
                    {
                    // InternalSqlParser.g:4231:1: (lv_like_5_0= ruleLike )
                    // InternalSqlParser.g:4232:3: lv_like_5_0= ruleLike
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_like_5_0=ruleLike();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"like",
                              		lv_like_5_0, 
                              		"com.jaspersoft.studio.data.Sql.Like");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:4249:6: ( (lv_comp_6_0= ruleComparison ) )
                    {
                    // InternalSqlParser.g:4249:6: ( (lv_comp_6_0= ruleComparison ) )
                    // InternalSqlParser.g:4250:1: (lv_comp_6_0= ruleComparison )
                    {
                    // InternalSqlParser.g:4250:1: (lv_comp_6_0= ruleComparison )
                    // InternalSqlParser.g:4251:3: lv_comp_6_0= ruleComparison
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_5_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_comp_6_0=ruleComparison();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"comp",
                              		lv_comp_6_0, 
                              		"com.jaspersoft.studio.data.Sql.Comparison");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleIsNullValue"
    // InternalSqlParser.g:4275:1: entryRuleIsNullValue returns [String current=null] : iv_ruleIsNullValue= ruleIsNullValue EOF ;
    public final String entryRuleIsNullValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIsNullValue = null;


        try {
            // InternalSqlParser.g:4276:1: (iv_ruleIsNullValue= ruleIsNullValue EOF )
            // InternalSqlParser.g:4277:2: iv_ruleIsNullValue= ruleIsNullValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIsNullValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIsNullValue=ruleIsNullValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIsNullValue.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIsNullValue"


    // $ANTLR start "ruleIsNullValue"
    // InternalSqlParser.g:4284:1: ruleIsNullValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= IS (kw= NOT )? kw= NULL ) ;
    public final AntlrDatatypeRuleToken ruleIsNullValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:4288:6: ( (kw= IS (kw= NOT )? kw= NULL ) )
            // InternalSqlParser.g:4289:1: (kw= IS (kw= NOT )? kw= NULL )
            {
            // InternalSqlParser.g:4289:1: (kw= IS (kw= NOT )? kw= NULL )
            // InternalSqlParser.g:4290:2: kw= IS (kw= NOT )? kw= NULL
            {
            kw=(Token)match(input,IS,FOLLOW_66); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getIsNullValueAccess().getISKeyword_0()); 
                  
            }
            // InternalSqlParser.g:4295:1: (kw= NOT )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==NOT) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // InternalSqlParser.g:4296:2: kw= NOT
                    {
                    kw=(Token)match(input,NOT,FOLLOW_67); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getIsNullValueAccess().getNOTKeyword_1()); 
                          
                    }

                    }
                    break;

            }

            kw=(Token)match(input,NULL,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getIsNullValueAccess().getNULLKeyword_2()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIsNullValue"


    // $ANTLR start "entryRuleComparison"
    // InternalSqlParser.g:4315:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // InternalSqlParser.g:4316:2: (iv_ruleComparison= ruleComparison EOF )
            // InternalSqlParser.g:4317:2: iv_ruleComparison= ruleComparison EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComparisonRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleComparison=ruleComparison();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComparison; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComparison"


    // $ANTLR start "ruleComparison"
    // InternalSqlParser.g:4324:1: ruleComparison returns [EObject current=null] : ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) ;
    public final EObject ruleComparison() throws RecognitionException {
        EObject current = null;

        Token lv_operator_0_1=null;
        Token lv_operator_0_2=null;
        Token lv_operator_0_3=null;
        Token lv_operator_0_4=null;
        Token lv_operator_0_5=null;
        Token lv_operator_0_6=null;
        Token lv_operator_0_7=null;
        Token lv_operator_0_8=null;
        Token lv_subOperator_1_1=null;
        Token lv_subOperator_1_2=null;
        Token lv_subOperator_1_3=null;
        EObject lv_op2_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4327:28: ( ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) )
            // InternalSqlParser.g:4328:1: ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            {
            // InternalSqlParser.g:4328:1: ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            // InternalSqlParser.g:4328:2: ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) )
            {
            // InternalSqlParser.g:4328:2: ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) )
            // InternalSqlParser.g:4329:1: ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) )
            {
            // InternalSqlParser.g:4329:1: ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) )
            // InternalSqlParser.g:4330:1: (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign )
            {
            // InternalSqlParser.g:4330:1: (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign )
            int alt85=8;
            switch ( input.LA(1) ) {
            case GreaterThanSign:
                {
                alt85=1;
                }
                break;
            case GreaterThanSignEqualsSign:
                {
                alt85=2;
                }
                break;
            case LessThanSign:
                {
                alt85=3;
                }
                break;
            case LessThanSignEqualsSign:
                {
                alt85=4;
                }
                break;
            case EqualsSign:
                {
                alt85=5;
                }
                break;
            case LessThanSignGreaterThanSign:
                {
                alt85=6;
                }
                break;
            case ExclamationMarkEqualsSign:
                {
                alt85=7;
                }
                break;
            case CircumflexAccentEqualsSign:
                {
                alt85=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }

            switch (alt85) {
                case 1 :
                    // InternalSqlParser.g:4331:3: lv_operator_0_1= GreaterThanSign
                    {
                    lv_operator_0_1=(Token)match(input,GreaterThanSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_operator_0_1, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getComparisonRule());
                      	        }
                             		setWithLastConsumed(current, "operator", lv_operator_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4344:8: lv_operator_0_2= GreaterThanSignEqualsSign
                    {
                    lv_operator_0_2=(Token)match(input,GreaterThanSignEqualsSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_operator_0_2, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getComparisonRule());
                      	        }
                             		setWithLastConsumed(current, "operator", lv_operator_0_2, null);
                      	    
                    }

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:4357:8: lv_operator_0_3= LessThanSign
                    {
                    lv_operator_0_3=(Token)match(input,LessThanSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_operator_0_3, grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getComparisonRule());
                      	        }
                             		setWithLastConsumed(current, "operator", lv_operator_0_3, null);
                      	    
                    }

                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:4370:8: lv_operator_0_4= LessThanSignEqualsSign
                    {
                    lv_operator_0_4=(Token)match(input,LessThanSignEqualsSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_operator_0_4, grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getComparisonRule());
                      	        }
                             		setWithLastConsumed(current, "operator", lv_operator_0_4, null);
                      	    
                    }

                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:4383:8: lv_operator_0_5= EqualsSign
                    {
                    lv_operator_0_5=(Token)match(input,EqualsSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_operator_0_5, grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getComparisonRule());
                      	        }
                             		setWithLastConsumed(current, "operator", lv_operator_0_5, null);
                      	    
                    }

                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:4396:8: lv_operator_0_6= LessThanSignGreaterThanSign
                    {
                    lv_operator_0_6=(Token)match(input,LessThanSignGreaterThanSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_operator_0_6, grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getComparisonRule());
                      	        }
                             		setWithLastConsumed(current, "operator", lv_operator_0_6, null);
                      	    
                    }

                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:4409:8: lv_operator_0_7= ExclamationMarkEqualsSign
                    {
                    lv_operator_0_7=(Token)match(input,ExclamationMarkEqualsSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_operator_0_7, grammarAccess.getComparisonAccess().getOperatorExclamationMarkEqualsSignKeyword_0_0_6());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getComparisonRule());
                      	        }
                             		setWithLastConsumed(current, "operator", lv_operator_0_7, null);
                      	    
                    }

                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:4422:8: lv_operator_0_8= CircumflexAccentEqualsSign
                    {
                    lv_operator_0_8=(Token)match(input,CircumflexAccentEqualsSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_operator_0_8, grammarAccess.getComparisonAccess().getOperatorCircumflexAccentEqualsSignKeyword_0_0_7());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getComparisonRule());
                      	        }
                             		setWithLastConsumed(current, "operator", lv_operator_0_8, null);
                      	    
                    }

                    }
                    break;

            }


            }


            }

            // InternalSqlParser.g:4438:2: ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==SOME||LA87_0==ALL||LA87_0==ANY) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalSqlParser.g:4439:1: ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) )
                    {
                    // InternalSqlParser.g:4439:1: ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) )
                    // InternalSqlParser.g:4440:1: (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME )
                    {
                    // InternalSqlParser.g:4440:1: (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME )
                    int alt86=3;
                    switch ( input.LA(1) ) {
                    case ANY:
                        {
                        alt86=1;
                        }
                        break;
                    case ALL:
                        {
                        alt86=2;
                        }
                        break;
                    case SOME:
                        {
                        alt86=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 86, 0, input);

                        throw nvae;
                    }

                    switch (alt86) {
                        case 1 :
                            // InternalSqlParser.g:4441:3: lv_subOperator_1_1= ANY
                            {
                            lv_subOperator_1_1=(Token)match(input,ANY,FOLLOW_60); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_subOperator_1_1, grammarAccess.getComparisonAccess().getSubOperatorANYKeyword_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getComparisonRule());
                              	        }
                                     		setWithLastConsumed(current, "subOperator", lv_subOperator_1_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:4454:8: lv_subOperator_1_2= ALL
                            {
                            lv_subOperator_1_2=(Token)match(input,ALL,FOLLOW_60); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_subOperator_1_2, grammarAccess.getComparisonAccess().getSubOperatorALLKeyword_1_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getComparisonRule());
                              	        }
                                     		setWithLastConsumed(current, "subOperator", lv_subOperator_1_2, null);
                              	    
                            }

                            }
                            break;
                        case 3 :
                            // InternalSqlParser.g:4467:8: lv_subOperator_1_3= SOME
                            {
                            lv_subOperator_1_3=(Token)match(input,SOME,FOLLOW_60); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_subOperator_1_3, grammarAccess.getComparisonAccess().getSubOperatorSOMEKeyword_1_0_2());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getComparisonRule());
                              	        }
                                     		setWithLastConsumed(current, "subOperator", lv_subOperator_1_3, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:4483:3: ( (lv_op2_2_0= ruleOperand ) )
            // InternalSqlParser.g:4484:1: (lv_op2_2_0= ruleOperand )
            {
            // InternalSqlParser.g:4484:1: (lv_op2_2_0= ruleOperand )
            // InternalSqlParser.g:4485:3: lv_op2_2_0= ruleOperand
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_op2_2_0=ruleOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getComparisonRule());
              	        }
                     		set(
                     			current, 
                     			"op2",
                      		lv_op2_2_0, 
                      		"com.jaspersoft.studio.data.Sql.Operand");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComparison"


    // $ANTLR start "entryRuleLike"
    // InternalSqlParser.g:4509:1: entryRuleLike returns [EObject current=null] : iv_ruleLike= ruleLike EOF ;
    public final EObject entryRuleLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLike = null;


        try {
            // InternalSqlParser.g:4510:2: (iv_ruleLike= ruleLike EOF )
            // InternalSqlParser.g:4511:2: iv_ruleLike= ruleLike EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLikeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLike=ruleLike();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLike; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLike"


    // $ANTLR start "ruleLike"
    // InternalSqlParser.g:4518:1: ruleLike returns [EObject current=null] : ( ( (lv_opLike_0_0= ruleLikeValue ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) ;
    public final EObject ruleLike() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_opLike_0_0 = null;

        EObject lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4521:28: ( ( ( (lv_opLike_0_0= ruleLikeValue ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) )
            // InternalSqlParser.g:4522:1: ( ( (lv_opLike_0_0= ruleLikeValue ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            {
            // InternalSqlParser.g:4522:1: ( ( (lv_opLike_0_0= ruleLikeValue ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            // InternalSqlParser.g:4522:2: ( (lv_opLike_0_0= ruleLikeValue ) ) ( (lv_op2_1_0= ruleLikeOperand ) )
            {
            // InternalSqlParser.g:4522:2: ( (lv_opLike_0_0= ruleLikeValue ) )
            // InternalSqlParser.g:4523:1: (lv_opLike_0_0= ruleLikeValue )
            {
            // InternalSqlParser.g:4523:1: (lv_opLike_0_0= ruleLikeValue )
            // InternalSqlParser.g:4524:3: lv_opLike_0_0= ruleLikeValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLikeAccess().getOpLikeLikeValueParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_69);
            lv_opLike_0_0=ruleLikeValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLikeRule());
              	        }
                     		set(
                     			current, 
                     			"opLike",
                      		lv_opLike_0_0, 
                      		"com.jaspersoft.studio.data.Sql.LikeValue");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:4540:2: ( (lv_op2_1_0= ruleLikeOperand ) )
            // InternalSqlParser.g:4541:1: (lv_op2_1_0= ruleLikeOperand )
            {
            // InternalSqlParser.g:4541:1: (lv_op2_1_0= ruleLikeOperand )
            // InternalSqlParser.g:4542:3: lv_op2_1_0= ruleLikeOperand
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLikeAccess().getOp2LikeOperandParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_op2_1_0=ruleLikeOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLikeRule());
              	        }
                     		set(
                     			current, 
                     			"op2",
                      		lv_op2_1_0, 
                      		"com.jaspersoft.studio.data.Sql.LikeOperand");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLike"


    // $ANTLR start "entryRuleLikeValue"
    // InternalSqlParser.g:4566:1: entryRuleLikeValue returns [String current=null] : iv_ruleLikeValue= ruleLikeValue EOF ;
    public final String entryRuleLikeValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLikeValue = null;


        try {
            // InternalSqlParser.g:4567:1: (iv_ruleLikeValue= ruleLikeValue EOF )
            // InternalSqlParser.g:4568:2: iv_ruleLikeValue= ruleLikeValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLikeValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLikeValue=ruleLikeValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLikeValue.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLikeValue"


    // $ANTLR start "ruleLikeValue"
    // InternalSqlParser.g:4575:1: ruleLikeValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= NOT )? kw= LIKE ) ;
    public final AntlrDatatypeRuleToken ruleLikeValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:4579:6: ( ( (kw= NOT )? kw= LIKE ) )
            // InternalSqlParser.g:4580:1: ( (kw= NOT )? kw= LIKE )
            {
            // InternalSqlParser.g:4580:1: ( (kw= NOT )? kw= LIKE )
            // InternalSqlParser.g:4580:2: (kw= NOT )? kw= LIKE
            {
            // InternalSqlParser.g:4580:2: (kw= NOT )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==NOT) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalSqlParser.g:4581:2: kw= NOT
                    {
                    kw=(Token)match(input,NOT,FOLLOW_70); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getLikeValueAccess().getNOTKeyword_0()); 
                          
                    }

                    }
                    break;

            }

            kw=(Token)match(input,LIKE,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getLikeValueAccess().getLIKEKeyword_1()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLikeValue"


    // $ANTLR start "entryRuleLikeOperand"
    // InternalSqlParser.g:4600:1: entryRuleLikeOperand returns [EObject current=null] : iv_ruleLikeOperand= ruleLikeOperand EOF ;
    public final EObject entryRuleLikeOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLikeOperand = null;


        try {
            // InternalSqlParser.g:4601:2: (iv_ruleLikeOperand= ruleLikeOperand EOF )
            // InternalSqlParser.g:4602:2: iv_ruleLikeOperand= ruleLikeOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLikeOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLikeOperand=ruleLikeOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLikeOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLikeOperand"


    // $ANTLR start "ruleLikeOperand"
    // InternalSqlParser.g:4609:1: ruleLikeOperand returns [EObject current=null] : ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) ;
    public final EObject ruleLikeOperand() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op2_0_0 = null;

        EObject lv_fop2_1_0 = null;

        EObject lv_fcast_2_0 = null;

        EObject lv_fparam_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4612:28: ( ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) )
            // InternalSqlParser.g:4613:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            {
            // InternalSqlParser.g:4613:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            int alt89=4;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt89=1;
                }
                break;
            case RULE_ID:
                {
                alt89=2;
                }
                break;
            case CAST:
                {
                alt89=3;
                }
                break;
            case RULE_JRPARAM:
                {
                alt89=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }

            switch (alt89) {
                case 1 :
                    // InternalSqlParser.g:4613:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:4613:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    // InternalSqlParser.g:4614:1: (lv_op2_0_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:4614:1: (lv_op2_0_0= ruleStringOperand )
                    // InternalSqlParser.g:4615:3: lv_op2_0_0= ruleStringOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getLikeOperandAccess().getOp2StringOperandParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_op2_0_0=ruleStringOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getLikeOperandRule());
                      	        }
                             		set(
                             			current, 
                             			"op2",
                              		lv_op2_0_0, 
                              		"com.jaspersoft.studio.data.Sql.StringOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4632:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:4632:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:4633:1: (lv_fop2_1_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:4633:1: (lv_fop2_1_0= ruleOperandFunction )
                    // InternalSqlParser.g:4634:3: lv_fop2_1_0= ruleOperandFunction
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFop2OperandFunctionParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_fop2_1_0=ruleOperandFunction();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getLikeOperandRule());
                      	        }
                             		set(
                             			current, 
                             			"fop2",
                              		lv_fop2_1_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandFunction");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:4651:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    {
                    // InternalSqlParser.g:4651:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    // InternalSqlParser.g:4652:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    {
                    // InternalSqlParser.g:4652:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    // InternalSqlParser.g:4653:3: lv_fcast_2_0= ruleOpFunctionCast
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFcastOpFunctionCastParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_fcast_2_0=ruleOpFunctionCast();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getLikeOperandRule());
                      	        }
                             		set(
                             			current, 
                             			"fcast",
                              		lv_fcast_2_0, 
                              		"com.jaspersoft.studio.data.Sql.OpFunctionCast");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:4670:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    {
                    // InternalSqlParser.g:4670:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    // InternalSqlParser.g:4671:1: (lv_fparam_3_0= ruleParameterOperand )
                    {
                    // InternalSqlParser.g:4671:1: (lv_fparam_3_0= ruleParameterOperand )
                    // InternalSqlParser.g:4672:3: lv_fparam_3_0= ruleParameterOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFparamParameterOperandParserRuleCall_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_fparam_3_0=ruleParameterOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getLikeOperandRule());
                      	        }
                             		set(
                             			current, 
                             			"fparam",
                              		lv_fparam_3_0, 
                              		"com.jaspersoft.studio.data.Sql.ParameterOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLikeOperand"


    // $ANTLR start "entryRuleBetween"
    // InternalSqlParser.g:4696:1: entryRuleBetween returns [EObject current=null] : iv_ruleBetween= ruleBetween EOF ;
    public final EObject entryRuleBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBetween = null;


        try {
            // InternalSqlParser.g:4697:2: (iv_ruleBetween= ruleBetween EOF )
            // InternalSqlParser.g:4698:2: iv_ruleBetween= ruleBetween EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBetweenRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBetween=ruleBetween();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBetween; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBetween"


    // $ANTLR start "ruleBetween"
    // InternalSqlParser.g:4705:1: ruleBetween returns [EObject current=null] : ( ( (lv_opBetween_0_0= ruleBetweenValue ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) ) ;
    public final EObject ruleBetween() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_opBetween_0_0 = null;

        EObject lv_op2_1_0 = null;

        EObject lv_op3_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4708:28: ( ( ( (lv_opBetween_0_0= ruleBetweenValue ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) ) )
            // InternalSqlParser.g:4709:1: ( ( (lv_opBetween_0_0= ruleBetweenValue ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) )
            {
            // InternalSqlParser.g:4709:1: ( ( (lv_opBetween_0_0= ruleBetweenValue ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) )
            // InternalSqlParser.g:4709:2: ( (lv_opBetween_0_0= ruleBetweenValue ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) )
            {
            // InternalSqlParser.g:4709:2: ( (lv_opBetween_0_0= ruleBetweenValue ) )
            // InternalSqlParser.g:4710:1: (lv_opBetween_0_0= ruleBetweenValue )
            {
            // InternalSqlParser.g:4710:1: (lv_opBetween_0_0= ruleBetweenValue )
            // InternalSqlParser.g:4711:3: lv_opBetween_0_0= ruleBetweenValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getBetweenAccess().getOpBetweenBetweenValueParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_60);
            lv_opBetween_0_0=ruleBetweenValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getBetweenRule());
              	        }
                     		set(
                     			current, 
                     			"opBetween",
                      		lv_opBetween_0_0, 
                      		"com.jaspersoft.studio.data.Sql.BetweenValue");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:4727:2: ( (lv_op2_1_0= ruleOperandGroup ) )
            // InternalSqlParser.g:4728:1: (lv_op2_1_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:4728:1: (lv_op2_1_0= ruleOperandGroup )
            // InternalSqlParser.g:4729:3: lv_op2_1_0= ruleOperandGroup
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getBetweenAccess().getOp2OperandGroupParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_71);
            lv_op2_1_0=ruleOperandGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getBetweenRule());
              	        }
                     		set(
                     			current, 
                     			"op2",
                      		lv_op2_1_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandGroup");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,AND,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getBetweenAccess().getANDKeyword_2());
                  
            }
            // InternalSqlParser.g:4750:1: ( (lv_op3_3_0= ruleOperandGroup ) )
            // InternalSqlParser.g:4751:1: (lv_op3_3_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:4751:1: (lv_op3_3_0= ruleOperandGroup )
            // InternalSqlParser.g:4752:3: lv_op3_3_0= ruleOperandGroup
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getBetweenAccess().getOp3OperandGroupParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_op3_3_0=ruleOperandGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getBetweenRule());
              	        }
                     		set(
                     			current, 
                     			"op3",
                      		lv_op3_3_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandGroup");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBetween"


    // $ANTLR start "entryRuleBetweenValue"
    // InternalSqlParser.g:4776:1: entryRuleBetweenValue returns [String current=null] : iv_ruleBetweenValue= ruleBetweenValue EOF ;
    public final String entryRuleBetweenValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBetweenValue = null;


        try {
            // InternalSqlParser.g:4777:1: (iv_ruleBetweenValue= ruleBetweenValue EOF )
            // InternalSqlParser.g:4778:2: iv_ruleBetweenValue= ruleBetweenValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBetweenValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBetweenValue=ruleBetweenValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBetweenValue.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBetweenValue"


    // $ANTLR start "ruleBetweenValue"
    // InternalSqlParser.g:4785:1: ruleBetweenValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= NOT )? kw= BETWEEN ) ;
    public final AntlrDatatypeRuleToken ruleBetweenValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:4789:6: ( ( (kw= NOT )? kw= BETWEEN ) )
            // InternalSqlParser.g:4790:1: ( (kw= NOT )? kw= BETWEEN )
            {
            // InternalSqlParser.g:4790:1: ( (kw= NOT )? kw= BETWEEN )
            // InternalSqlParser.g:4790:2: (kw= NOT )? kw= BETWEEN
            {
            // InternalSqlParser.g:4790:2: (kw= NOT )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==NOT) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // InternalSqlParser.g:4791:2: kw= NOT
                    {
                    kw=(Token)match(input,NOT,FOLLOW_72); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBetweenValueAccess().getNOTKeyword_0()); 
                          
                    }

                    }
                    break;

            }

            kw=(Token)match(input,BETWEEN,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getBetweenValueAccess().getBETWEENKeyword_1()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBetweenValue"


    // $ANTLR start "entryRuleInOperator"
    // InternalSqlParser.g:4810:1: entryRuleInOperator returns [EObject current=null] : iv_ruleInOperator= ruleInOperator EOF ;
    public final EObject entryRuleInOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOperator = null;


        try {
            // InternalSqlParser.g:4811:2: (iv_ruleInOperator= ruleInOperator EOF )
            // InternalSqlParser.g:4812:2: iv_ruleInOperator= ruleInOperator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInOperatorRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInOperator=ruleInOperator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInOperator; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInOperator"


    // $ANTLR start "ruleInOperator"
    // InternalSqlParser.g:4819:1: ruleInOperator returns [EObject current=null] : ( () ( (lv_op_1_0= ruleInValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleInOperator() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op_1_0 = null;

        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4822:28: ( ( () ( (lv_op_1_0= ruleInValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // InternalSqlParser.g:4823:1: ( () ( (lv_op_1_0= ruleInValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // InternalSqlParser.g:4823:1: ( () ( (lv_op_1_0= ruleInValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // InternalSqlParser.g:4823:2: () ( (lv_op_1_0= ruleInValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // InternalSqlParser.g:4823:2: ()
            // InternalSqlParser.g:4824:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getInOperatorAccess().getInOperAction_0(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:4832:2: ( (lv_op_1_0= ruleInValue ) )
            // InternalSqlParser.g:4833:1: (lv_op_1_0= ruleInValue )
            {
            // InternalSqlParser.g:4833:1: (lv_op_1_0= ruleInValue )
            // InternalSqlParser.g:4834:3: lv_op_1_0= ruleInValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInOperatorAccess().getOpInValueParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_7);
            lv_op_1_0=ruleInValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getInOperatorRule());
              	        }
                     		set(
                     			current, 
                     			"op",
                      		lv_op_1_0, 
                      		"com.jaspersoft.studio.data.Sql.InValue");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:4850:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==LeftParenthesis) ) {
                int LA91_1 = input.LA(2);

                if ( ((LA91_1>=RULE_SIGNED_DOUBLE && LA91_1<=RULE_TIMESTAMP)||LA91_1==RULE_STRING_) ) {
                    alt91=2;
                }
                else if ( (LA91_1==SELECT) ) {
                    alt91=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 91, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // InternalSqlParser.g:4850:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:4850:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:4851:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:4851:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:4852:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_subquery_2_0=ruleSubQueryOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInOperatorRule());
                      	        }
                             		set(
                             			current, 
                             			"subquery",
                              		lv_subquery_2_0, 
                              		"com.jaspersoft.studio.data.Sql.SubQueryOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4869:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // InternalSqlParser.g:4869:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // InternalSqlParser.g:4870:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // InternalSqlParser.g:4870:1: (lv_opList_3_0= ruleOperandListGroup )
                    // InternalSqlParser.g:4871:3: lv_opList_3_0= ruleOperandListGroup
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getInOperatorAccess().getOpListOperandListGroupParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_opList_3_0=ruleOperandListGroup();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInOperatorRule());
                      	        }
                             		set(
                             			current, 
                             			"opList",
                              		lv_opList_3_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandListGroup");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInOperator"


    // $ANTLR start "entryRuleInValue"
    // InternalSqlParser.g:4895:1: entryRuleInValue returns [String current=null] : iv_ruleInValue= ruleInValue EOF ;
    public final String entryRuleInValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInValue = null;


        try {
            // InternalSqlParser.g:4896:1: (iv_ruleInValue= ruleInValue EOF )
            // InternalSqlParser.g:4897:2: iv_ruleInValue= ruleInValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInValue=ruleInValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInValue.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInValue"


    // $ANTLR start "ruleInValue"
    // InternalSqlParser.g:4904:1: ruleInValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= NOT )? kw= IN ) ;
    public final AntlrDatatypeRuleToken ruleInValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:4908:6: ( ( (kw= NOT )? kw= IN ) )
            // InternalSqlParser.g:4909:1: ( (kw= NOT )? kw= IN )
            {
            // InternalSqlParser.g:4909:1: ( (kw= NOT )? kw= IN )
            // InternalSqlParser.g:4909:2: (kw= NOT )? kw= IN
            {
            // InternalSqlParser.g:4909:2: (kw= NOT )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==NOT) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // InternalSqlParser.g:4910:2: kw= NOT
                    {
                    kw=(Token)match(input,NOT,FOLLOW_48); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getInValueAccess().getNOTKeyword_0()); 
                          
                    }

                    }
                    break;

            }

            kw=(Token)match(input,IN,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getInValueAccess().getINKeyword_1()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInValue"


    // $ANTLR start "entryRuleExistsOperator"
    // InternalSqlParser.g:4929:1: entryRuleExistsOperator returns [EObject current=null] : iv_ruleExistsOperator= ruleExistsOperator EOF ;
    public final EObject entryRuleExistsOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistsOperator = null;


        try {
            // InternalSqlParser.g:4930:2: (iv_ruleExistsOperator= ruleExistsOperator EOF )
            // InternalSqlParser.g:4931:2: iv_ruleExistsOperator= ruleExistsOperator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExistsOperatorRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExistsOperator=ruleExistsOperator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExistsOperator; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExistsOperator"


    // $ANTLR start "ruleExistsOperator"
    // InternalSqlParser.g:4938:1: ruleExistsOperator returns [EObject current=null] : ( () ( (lv_op_1_0= ruleExistsValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleExistsOperator() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op_1_0 = null;

        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4941:28: ( ( () ( (lv_op_1_0= ruleExistsValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // InternalSqlParser.g:4942:1: ( () ( (lv_op_1_0= ruleExistsValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // InternalSqlParser.g:4942:1: ( () ( (lv_op_1_0= ruleExistsValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // InternalSqlParser.g:4942:2: () ( (lv_op_1_0= ruleExistsValue ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // InternalSqlParser.g:4942:2: ()
            // InternalSqlParser.g:4943:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getExistsOperatorAccess().getExistsOperAction_0(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:4951:2: ( (lv_op_1_0= ruleExistsValue ) )
            // InternalSqlParser.g:4952:1: (lv_op_1_0= ruleExistsValue )
            {
            // InternalSqlParser.g:4952:1: (lv_op_1_0= ruleExistsValue )
            // InternalSqlParser.g:4953:3: lv_op_1_0= ruleExistsValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getOpExistsValueParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_7);
            lv_op_1_0=ruleExistsValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExistsOperatorRule());
              	        }
                     		set(
                     			current, 
                     			"op",
                      		lv_op_1_0, 
                      		"com.jaspersoft.studio.data.Sql.ExistsValue");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:4969:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==LeftParenthesis) ) {
                int LA93_1 = input.LA(2);

                if ( (LA93_1==SELECT) ) {
                    alt93=1;
                }
                else if ( ((LA93_1>=RULE_SIGNED_DOUBLE && LA93_1<=RULE_TIMESTAMP)||LA93_1==RULE_STRING_) ) {
                    alt93=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 93, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }
            switch (alt93) {
                case 1 :
                    // InternalSqlParser.g:4969:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:4969:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:4970:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:4970:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:4971:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_subquery_2_0=ruleSubQueryOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExistsOperatorRule());
                      	        }
                             		set(
                             			current, 
                             			"subquery",
                              		lv_subquery_2_0, 
                              		"com.jaspersoft.studio.data.Sql.SubQueryOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4988:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // InternalSqlParser.g:4988:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // InternalSqlParser.g:4989:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // InternalSqlParser.g:4989:1: (lv_opList_3_0= ruleOperandListGroup )
                    // InternalSqlParser.g:4990:3: lv_opList_3_0= ruleOperandListGroup
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getOpListOperandListGroupParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_opList_3_0=ruleOperandListGroup();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExistsOperatorRule());
                      	        }
                             		set(
                             			current, 
                             			"opList",
                              		lv_opList_3_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandListGroup");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExistsOperator"


    // $ANTLR start "entryRuleExistsValue"
    // InternalSqlParser.g:5014:1: entryRuleExistsValue returns [String current=null] : iv_ruleExistsValue= ruleExistsValue EOF ;
    public final String entryRuleExistsValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleExistsValue = null;


        try {
            // InternalSqlParser.g:5015:1: (iv_ruleExistsValue= ruleExistsValue EOF )
            // InternalSqlParser.g:5016:2: iv_ruleExistsValue= ruleExistsValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExistsValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExistsValue=ruleExistsValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExistsValue.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExistsValue"


    // $ANTLR start "ruleExistsValue"
    // InternalSqlParser.g:5023:1: ruleExistsValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= NOT )? kw= EXISTS ) ;
    public final AntlrDatatypeRuleToken ruleExistsValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:5027:6: ( ( (kw= NOT )? kw= EXISTS ) )
            // InternalSqlParser.g:5028:1: ( (kw= NOT )? kw= EXISTS )
            {
            // InternalSqlParser.g:5028:1: ( (kw= NOT )? kw= EXISTS )
            // InternalSqlParser.g:5028:2: (kw= NOT )? kw= EXISTS
            {
            // InternalSqlParser.g:5028:2: (kw= NOT )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==NOT) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalSqlParser.g:5029:2: kw= NOT
                    {
                    kw=(Token)match(input,NOT,FOLLOW_73); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getExistsValueAccess().getNOTKeyword_0()); 
                          
                    }

                    }
                    break;

            }

            kw=(Token)match(input,EXISTS,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getExistsValueAccess().getEXISTSKeyword_1()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExistsValue"


    // $ANTLR start "entryRuleOperandListGroup"
    // InternalSqlParser.g:5048:1: entryRuleOperandListGroup returns [EObject current=null] : iv_ruleOperandListGroup= ruleOperandListGroup EOF ;
    public final EObject entryRuleOperandListGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandListGroup = null;


        try {
            // InternalSqlParser.g:5049:2: (iv_ruleOperandListGroup= ruleOperandListGroup EOF )
            // InternalSqlParser.g:5050:2: iv_ruleOperandListGroup= ruleOperandListGroup EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperandListGroupRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOperandListGroup=ruleOperandListGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperandListGroup; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperandListGroup"


    // $ANTLR start "ruleOperandListGroup"
    // InternalSqlParser.g:5057:1: ruleOperandListGroup returns [EObject current=null] : (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis ) ;
    public final EObject ruleOperandListGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_opGroup_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5060:28: ( (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis ) )
            // InternalSqlParser.g:5061:1: (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis )
            {
            // InternalSqlParser.g:5061:1: (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis )
            // InternalSqlParser.g:5062:2: otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis
            {
            otherlv_0=(Token)match(input,LeftParenthesis,FOLLOW_74); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getOperandListGroupAccess().getLeftParenthesisKeyword_0());
                  
            }
            // InternalSqlParser.g:5066:1: ( (lv_opGroup_1_0= ruleOperandList ) )
            // InternalSqlParser.g:5067:1: (lv_opGroup_1_0= ruleOperandList )
            {
            // InternalSqlParser.g:5067:1: (lv_opGroup_1_0= ruleOperandList )
            // InternalSqlParser.g:5068:3: lv_opGroup_1_0= ruleOperandList
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperandListGroupAccess().getOpGroupOperandListParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_opGroup_1_0=ruleOperandList();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOperandListGroupRule());
              	        }
                     		set(
                     			current, 
                     			"opGroup",
                      		lv_opGroup_1_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandList");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getOperandListGroupAccess().getRightParenthesisKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperandListGroup"


    // $ANTLR start "entryRuleOperandList"
    // InternalSqlParser.g:5097:1: entryRuleOperandList returns [EObject current=null] : iv_ruleOperandList= ruleOperandList EOF ;
    public final EObject entryRuleOperandList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandList = null;


        try {
            // InternalSqlParser.g:5098:2: (iv_ruleOperandList= ruleOperandList EOF )
            // InternalSqlParser.g:5099:2: iv_ruleOperandList= ruleOperandList EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperandListRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOperandList=ruleOperandList();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperandList; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperandList"


    // $ANTLR start "ruleOperandList"
    // InternalSqlParser.g:5106:1: ruleOperandList returns [EObject current=null] : (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) ;
    public final EObject ruleOperandList() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ScalarOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5109:28: ( (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) )
            // InternalSqlParser.g:5110:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            {
            // InternalSqlParser.g:5110:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            // InternalSqlParser.g:5111:2: this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOperandListAccess().getScalarOperandParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_ScalarOperand_0=ruleScalarOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_ScalarOperand_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:5122:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==Comma) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // InternalSqlParser.g:5122:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    {
                    // InternalSqlParser.g:5122:2: ()
                    // InternalSqlParser.g:5123:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:5131:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    int cnt95=0;
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==Comma) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // InternalSqlParser.g:5132:2: otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_74); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:5136:1: ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    // InternalSqlParser.g:5137:1: (lv_entries_3_0= ruleScalarOperand )
                    	    {
                    	    // InternalSqlParser.g:5137:1: (lv_entries_3_0= ruleScalarOperand )
                    	    // InternalSqlParser.g:5138:3: lv_entries_3_0= ruleScalarOperand
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOperandListAccess().getEntriesScalarOperandParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleScalarOperand();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getOperandListRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.ScalarOperand");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt95 >= 1 ) break loop95;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(95, input);
                                throw eee;
                        }
                        cnt95++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperandList"


    // $ANTLR start "entryRuleOperandGroup"
    // InternalSqlParser.g:5162:1: entryRuleOperandGroup returns [EObject current=null] : iv_ruleOperandGroup= ruleOperandGroup EOF ;
    public final EObject entryRuleOperandGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandGroup = null;


        try {
            // InternalSqlParser.g:5163:2: (iv_ruleOperandGroup= ruleOperandGroup EOF )
            // InternalSqlParser.g:5164:2: iv_ruleOperandGroup= ruleOperandGroup EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperandGroupRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOperandGroup=ruleOperandGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperandGroup; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperandGroup"


    // $ANTLR start "ruleOperandGroup"
    // InternalSqlParser.g:5171:1: ruleOperandGroup returns [EObject current=null] : (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) ) ;
    public final EObject ruleOperandGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_Operand_0 = null;

        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5174:28: ( (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) ) )
            // InternalSqlParser.g:5175:1: (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) )
            {
            // InternalSqlParser.g:5175:1: (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) )
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==EXTRACT||LA97_0==CAST||LA97_0==CASE||(LA97_0>=RULE_JRPARAM && LA97_0<=RULE_JRNPARAM)||(LA97_0>=RULE_UNSIGNED && LA97_0<=RULE_SIGNED_DOUBLE)||(LA97_0>=RULE_STRING_ && LA97_0<=RULE_ID)) ) {
                alt97=1;
            }
            else if ( (LA97_0==LeftParenthesis) ) {
                int LA97_2 = input.LA(2);

                if ( (LA97_2==EXTRACT||LA97_2==CAST||LA97_2==CASE||LA97_2==LeftParenthesis||(LA97_2>=RULE_JRPARAM && LA97_2<=RULE_JRNPARAM)||(LA97_2>=RULE_UNSIGNED && LA97_2<=RULE_SIGNED_DOUBLE)||(LA97_2>=RULE_STRING_ && LA97_2<=RULE_ID)) ) {
                    alt97=2;
                }
                else if ( (LA97_2==SELECT) ) {
                    alt97=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 97, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                throw nvae;
            }
            switch (alt97) {
                case 1 :
                    // InternalSqlParser.g:5176:2: this_Operand_0= ruleOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getOperandGroupAccess().getOperandParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_Operand_0=ruleOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_Operand_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5188:6: (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis )
                    {
                    // InternalSqlParser.g:5188:6: (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis )
                    // InternalSqlParser.g:5189:2: otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis
                    {
                    otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_60); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getOperandGroupAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getOperandGroupAccess().getOperandParserRuleCall_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_8);
                    this_Operand_2=ruleOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_Operand_2;
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getOperandGroupAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperandGroup"


    // $ANTLR start "entryRuleOperand"
    // InternalSqlParser.g:5218:1: entryRuleOperand returns [EObject current=null] : iv_ruleOperand= ruleOperand EOF ;
    public final EObject entryRuleOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperand = null;


        try {
            // InternalSqlParser.g:5219:2: (iv_ruleOperand= ruleOperand EOF )
            // InternalSqlParser.g:5220:2: iv_ruleOperand= ruleOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOperand=ruleOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperand"


    // $ANTLR start "ruleOperand"
    // InternalSqlParser.g:5227:1: ruleOperand returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) ;
    public final EObject ruleOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token this_STAR_8=null;
        Token otherlv_10=null;
        EObject lv_op1_0_0 = null;

        EObject lv_right_11_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5230:28: ( ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) )
            // InternalSqlParser.g:5231:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            {
            // InternalSqlParser.g:5231:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            // InternalSqlParser.g:5231:2: ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            {
            // InternalSqlParser.g:5231:2: ( (lv_op1_0_0= ruleOperandFragment ) )
            // InternalSqlParser.g:5232:1: (lv_op1_0_0= ruleOperandFragment )
            {
            // InternalSqlParser.g:5232:1: (lv_op1_0_0= ruleOperandFragment )
            // InternalSqlParser.g:5233:3: lv_op1_0_0= ruleOperandFragment
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperandAccess().getOp1OperandFragmentParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_75);
            lv_op1_0_0=ruleOperandFragment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOperandRule());
              	        }
                     		set(
                     			current, 
                     			"op1",
                      		lv_op1_0_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandFragment");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:5249:2: ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            loop99:
            do {
                int alt99=2;
                int LA99_0 = input.LA(1);

                if ( (LA99_0==VerticalLineVerticalLine||LA99_0==PlusSign||LA99_0==HyphenMinus||LA99_0==Solidus||LA99_0==RULE_STAR) ) {
                    alt99=1;
                }


                switch (alt99) {
            	case 1 :
            	    // InternalSqlParser.g:5249:3: ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) )
            	    {
            	    // InternalSqlParser.g:5249:3: ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) )
            	    int alt98=5;
            	    switch ( input.LA(1) ) {
            	    case PlusSign:
            	        {
            	        alt98=1;
            	        }
            	        break;
            	    case HyphenMinus:
            	        {
            	        alt98=2;
            	        }
            	        break;
            	    case VerticalLineVerticalLine:
            	        {
            	        alt98=3;
            	        }
            	        break;
            	    case RULE_STAR:
            	        {
            	        alt98=4;
            	        }
            	        break;
            	    case Solidus:
            	        {
            	        alt98=5;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 98, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt98) {
            	        case 1 :
            	            // InternalSqlParser.g:5249:4: ( () otherlv_2= PlusSign )
            	            {
            	            // InternalSqlParser.g:5249:4: ( () otherlv_2= PlusSign )
            	            // InternalSqlParser.g:5249:5: () otherlv_2= PlusSign
            	            {
            	            // InternalSqlParser.g:5249:5: ()
            	            // InternalSqlParser.g:5250:2: 
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	  /* */ 
            	              	
            	            }
            	            if ( state.backtracking==0 ) {

            	                      current = forceCreateModelElementAndSet(
            	                          grammarAccess.getOperandAccess().getPlusLeftAction_1_0_0_0(),
            	                          current);
            	                  
            	            }

            	            }

            	            otherlv_2=(Token)match(input,PlusSign,FOLLOW_60); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_2, grammarAccess.getOperandAccess().getPlusSignKeyword_1_0_0_1());
            	                  
            	            }

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalSqlParser.g:5264:6: ( () otherlv_4= HyphenMinus )
            	            {
            	            // InternalSqlParser.g:5264:6: ( () otherlv_4= HyphenMinus )
            	            // InternalSqlParser.g:5264:7: () otherlv_4= HyphenMinus
            	            {
            	            // InternalSqlParser.g:5264:7: ()
            	            // InternalSqlParser.g:5265:2: 
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	  /* */ 
            	              	
            	            }
            	            if ( state.backtracking==0 ) {

            	                      current = forceCreateModelElementAndSet(
            	                          grammarAccess.getOperandAccess().getMinusLeftAction_1_0_1_0(),
            	                          current);
            	                  
            	            }

            	            }

            	            otherlv_4=(Token)match(input,HyphenMinus,FOLLOW_60); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_4, grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_0_1_1());
            	                  
            	            }

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // InternalSqlParser.g:5279:6: ( () otherlv_6= VerticalLineVerticalLine )
            	            {
            	            // InternalSqlParser.g:5279:6: ( () otherlv_6= VerticalLineVerticalLine )
            	            // InternalSqlParser.g:5279:7: () otherlv_6= VerticalLineVerticalLine
            	            {
            	            // InternalSqlParser.g:5279:7: ()
            	            // InternalSqlParser.g:5280:2: 
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	  /* */ 
            	              	
            	            }
            	            if ( state.backtracking==0 ) {

            	                      current = forceCreateModelElementAndSet(
            	                          grammarAccess.getOperandAccess().getConcatLeftAction_1_0_2_0(),
            	                          current);
            	                  
            	            }

            	            }

            	            otherlv_6=(Token)match(input,VerticalLineVerticalLine,FOLLOW_60); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_6, grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_0_2_1());
            	                  
            	            }

            	            }


            	            }
            	            break;
            	        case 4 :
            	            // InternalSqlParser.g:5294:6: ( () this_STAR_8= RULE_STAR )
            	            {
            	            // InternalSqlParser.g:5294:6: ( () this_STAR_8= RULE_STAR )
            	            // InternalSqlParser.g:5294:7: () this_STAR_8= RULE_STAR
            	            {
            	            // InternalSqlParser.g:5294:7: ()
            	            // InternalSqlParser.g:5295:2: 
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	  /* */ 
            	              	
            	            }
            	            if ( state.backtracking==0 ) {

            	                      current = forceCreateModelElementAndSet(
            	                          grammarAccess.getOperandAccess().getMultiplyLeftAction_1_0_3_0(),
            	                          current);
            	                  
            	            }

            	            }

            	            this_STAR_8=(Token)match(input,RULE_STAR,FOLLOW_60); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {
            	               
            	                  newLeafNode(this_STAR_8, grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_0_3_1()); 
            	                  
            	            }

            	            }


            	            }
            	            break;
            	        case 5 :
            	            // InternalSqlParser.g:5308:6: ( () otherlv_10= Solidus )
            	            {
            	            // InternalSqlParser.g:5308:6: ( () otherlv_10= Solidus )
            	            // InternalSqlParser.g:5308:7: () otherlv_10= Solidus
            	            {
            	            // InternalSqlParser.g:5308:7: ()
            	            // InternalSqlParser.g:5309:2: 
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	  /* */ 
            	              	
            	            }
            	            if ( state.backtracking==0 ) {

            	                      current = forceCreateModelElementAndSet(
            	                          grammarAccess.getOperandAccess().getDivisionLeftAction_1_0_4_0(),
            	                          current);
            	                  
            	            }

            	            }

            	            otherlv_10=(Token)match(input,Solidus,FOLLOW_60); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_10, grammarAccess.getOperandAccess().getSolidusKeyword_1_0_4_1());
            	                  
            	            }

            	            }


            	            }
            	            break;

            	    }

            	    // InternalSqlParser.g:5322:3: ( (lv_right_11_0= ruleOperandFragment ) )
            	    // InternalSqlParser.g:5323:1: (lv_right_11_0= ruleOperandFragment )
            	    {
            	    // InternalSqlParser.g:5323:1: (lv_right_11_0= ruleOperandFragment )
            	    // InternalSqlParser.g:5324:3: lv_right_11_0= ruleOperandFragment
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOperandAccess().getRightOperandFragmentParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_75);
            	    lv_right_11_0=ruleOperandFragment();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getOperandRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_11_0, 
            	              		"com.jaspersoft.studio.data.Sql.OperandFragment");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop99;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperand"


    // $ANTLR start "entryRuleOperandFragment"
    // InternalSqlParser.g:5348:1: entryRuleOperandFragment returns [EObject current=null] : iv_ruleOperandFragment= ruleOperandFragment EOF ;
    public final EObject entryRuleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFragment = null;


        try {
            // InternalSqlParser.g:5349:2: (iv_ruleOperandFragment= ruleOperandFragment EOF )
            // InternalSqlParser.g:5350:2: iv_ruleOperandFragment= ruleOperandFragment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperandFragmentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOperandFragment=ruleOperandFragment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperandFragment; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperandFragment"


    // $ANTLR start "ruleOperandFragment"
    // InternalSqlParser.g:5357:1: ruleOperandFragment returns [EObject current=null] : ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) ) ;
    public final EObject ruleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_column_0_0 = null;

        EObject lv_xop_1_0 = null;

        EObject lv_subq_2_0 = null;

        EObject lv_fcast_3_0 = null;

        EObject lv_fext_4_0 = null;

        EObject lv_func_5_0 = null;

        EObject lv_sqlcase_6_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5360:28: ( ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) ) )
            // InternalSqlParser.g:5361:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) )
            {
            // InternalSqlParser.g:5361:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) )
            int alt100=7;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA100_1 = input.LA(2);

                if ( (LA100_1==EOF||LA100_1==STRAIGHT_JOIN||(LA100_1>=KW_FOLLOWING && LA100_1<=INTERSECT)||LA100_1==PRECEDING||LA100_1==BETWEEN||LA100_1==NATURAL||(LA100_1>=EXCEPT && LA100_1<=HAVING)||LA100_1==OFFSET||(LA100_1>=CROSS && LA100_1<=FETCH)||(LA100_1>=GROUP && LA100_1<=MINUS)||(LA100_1>=NULLS && LA100_1<=ORDER)||(LA100_1>=RANGE && LA100_1<=UNION)||LA100_1==WHERE||(LA100_1>=DESC && LA100_1<=FULL)||LA100_1==JOIN||(LA100_1>=LEFT && LA100_1<=LIKE)||LA100_1==ROWS||LA100_1==THEN||LA100_1==WHEN||LA100_1==LeftParenthesisPlusSignRightParenthesis||LA100_1==AND||LA100_1==ASC||LA100_1==END||LA100_1==NOT||LA100_1==ExclamationMarkEqualsSign||(LA100_1>=LessThanSignEqualsSign && LA100_1<=AS)||(LA100_1>=IN && LA100_1<=IS)||(LA100_1>=OR && LA100_1<=VerticalLineVerticalLine)||(LA100_1>=RightParenthesis && LA100_1<=RightCurlyBracket)||(LA100_1>=RULE_JRNPARAM && LA100_1<=RULE_STAR)||(LA100_1>=RULE_STRING && LA100_1<=RULE_ID)) ) {
                    alt100=1;
                }
                else if ( (LA100_1==LeftParenthesis) ) {
                    alt100=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 100, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt100=1;
                }
                break;
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt100=2;
                }
                break;
            case LeftParenthesis:
                {
                alt100=3;
                }
                break;
            case CAST:
                {
                alt100=4;
                }
                break;
            case EXTRACT:
                {
                alt100=5;
                }
                break;
            case CASE:
                {
                alt100=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }

            switch (alt100) {
                case 1 :
                    // InternalSqlParser.g:5361:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    {
                    // InternalSqlParser.g:5361:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    // InternalSqlParser.g:5362:1: (lv_column_0_0= ruleColumnOperand )
                    {
                    // InternalSqlParser.g:5362:1: (lv_column_0_0= ruleColumnOperand )
                    // InternalSqlParser.g:5363:3: lv_column_0_0= ruleColumnOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_column_0_0=ruleColumnOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"column",
                              		lv_column_0_0, 
                              		"com.jaspersoft.studio.data.Sql.ColumnOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5380:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    {
                    // InternalSqlParser.g:5380:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    // InternalSqlParser.g:5381:1: (lv_xop_1_0= ruleXOperandFragment )
                    {
                    // InternalSqlParser.g:5381:1: (lv_xop_1_0= ruleXOperandFragment )
                    // InternalSqlParser.g:5382:3: lv_xop_1_0= ruleXOperandFragment
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_xop_1_0=ruleXOperandFragment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"xop",
                              		lv_xop_1_0, 
                              		"com.jaspersoft.studio.data.Sql.XOperandFragment");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:5399:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:5399:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:5400:1: (lv_subq_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:5400:1: (lv_subq_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:5401:3: lv_subq_2_0= ruleSubQueryOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_subq_2_0=ruleSubQueryOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"subq",
                              		lv_subq_2_0, 
                              		"com.jaspersoft.studio.data.Sql.SubQueryOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:5418:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    {
                    // InternalSqlParser.g:5418:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    // InternalSqlParser.g:5419:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    {
                    // InternalSqlParser.g:5419:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    // InternalSqlParser.g:5420:3: lv_fcast_3_0= ruleOpFunctionCast
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFcastOpFunctionCastParserRuleCall_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_fcast_3_0=ruleOpFunctionCast();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"fcast",
                              		lv_fcast_3_0, 
                              		"com.jaspersoft.studio.data.Sql.OpFunctionCast");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:5437:6: ( (lv_fext_4_0= ruleFunctionExtract ) )
                    {
                    // InternalSqlParser.g:5437:6: ( (lv_fext_4_0= ruleFunctionExtract ) )
                    // InternalSqlParser.g:5438:1: (lv_fext_4_0= ruleFunctionExtract )
                    {
                    // InternalSqlParser.g:5438:1: (lv_fext_4_0= ruleFunctionExtract )
                    // InternalSqlParser.g:5439:3: lv_fext_4_0= ruleFunctionExtract
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFextFunctionExtractParserRuleCall_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_fext_4_0=ruleFunctionExtract();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"fext",
                              		lv_fext_4_0, 
                              		"com.jaspersoft.studio.data.Sql.FunctionExtract");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:5456:6: ( (lv_func_5_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:5456:6: ( (lv_func_5_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:5457:1: (lv_func_5_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:5457:1: (lv_func_5_0= ruleOperandFunction )
                    // InternalSqlParser.g:5458:3: lv_func_5_0= ruleOperandFunction
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFuncOperandFunctionParserRuleCall_5_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_func_5_0=ruleOperandFunction();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"func",
                              		lv_func_5_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandFunction");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:5475:6: ( (lv_sqlcase_6_0= ruleSQLCASE ) )
                    {
                    // InternalSqlParser.g:5475:6: ( (lv_sqlcase_6_0= ruleSQLCASE ) )
                    // InternalSqlParser.g:5476:1: (lv_sqlcase_6_0= ruleSQLCASE )
                    {
                    // InternalSqlParser.g:5476:1: (lv_sqlcase_6_0= ruleSQLCASE )
                    // InternalSqlParser.g:5477:3: lv_sqlcase_6_0= ruleSQLCASE
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSqlcaseSQLCASEParserRuleCall_6_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_sqlcase_6_0=ruleSQLCASE();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"sqlcase",
                              		lv_sqlcase_6_0, 
                              		"com.jaspersoft.studio.data.Sql.SQLCASE");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperandFragment"


    // $ANTLR start "entryRuleOperandFunction"
    // InternalSqlParser.g:5501:1: entryRuleOperandFunction returns [EObject current=null] : iv_ruleOperandFunction= ruleOperandFunction EOF ;
    public final EObject entryRuleOperandFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFunction = null;


        try {
            // InternalSqlParser.g:5502:2: (iv_ruleOperandFunction= ruleOperandFunction EOF )
            // InternalSqlParser.g:5503:2: iv_ruleOperandFunction= ruleOperandFunction EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperandFunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOperandFunction=ruleOperandFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperandFunction; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperandFunction"


    // $ANTLR start "ruleOperandFunction"
    // InternalSqlParser.g:5510:1: ruleOperandFunction returns [EObject current=null] : ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) ;
    public final EObject ruleOperandFunction() throws RecognitionException {
        EObject current = null;

        Token lv_star_2_0=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_fname_1_0 = null;

        EObject lv_args_3_0 = null;

        EObject lv_fan_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5513:28: ( ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) )
            // InternalSqlParser.g:5514:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            {
            // InternalSqlParser.g:5514:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            // InternalSqlParser.g:5514:2: () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            {
            // InternalSqlParser.g:5514:2: ()
            // InternalSqlParser.g:5515:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getOperandFunctionAccess().getOpFunctionAction_0(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:5523:2: ( (lv_fname_1_0= ruleFNAME ) )
            // InternalSqlParser.g:5524:1: (lv_fname_1_0= ruleFNAME )
            {
            // InternalSqlParser.g:5524:1: (lv_fname_1_0= ruleFNAME )
            // InternalSqlParser.g:5525:3: lv_fname_1_0= ruleFNAME
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFnameFNAMEParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_76);
            lv_fname_1_0=ruleFNAME();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOperandFunctionRule());
              	        }
                     		set(
                     			current, 
                     			"fname",
                      		lv_fname_1_0, 
                      		"com.jaspersoft.studio.data.Sql.FNAME");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:5541:2: ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )?
            int alt101=3;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==RULE_STAR) ) {
                alt101=1;
            }
            else if ( (LA101_0==DISTINCT||LA101_0==EXTRACT||LA101_0==CAST||LA101_0==CASE||LA101_0==ALL||LA101_0==LeftParenthesis||(LA101_0>=RULE_JRPARAM && LA101_0<=RULE_JRNPARAM)||(LA101_0>=RULE_UNSIGNED && LA101_0<=RULE_SIGNED_DOUBLE)||(LA101_0>=RULE_STRING_ && LA101_0<=RULE_ID)) ) {
                alt101=2;
            }
            switch (alt101) {
                case 1 :
                    // InternalSqlParser.g:5541:3: ( (lv_star_2_0= RULE_STAR ) )
                    {
                    // InternalSqlParser.g:5541:3: ( (lv_star_2_0= RULE_STAR ) )
                    // InternalSqlParser.g:5542:1: (lv_star_2_0= RULE_STAR )
                    {
                    // InternalSqlParser.g:5542:1: (lv_star_2_0= RULE_STAR )
                    // InternalSqlParser.g:5543:3: lv_star_2_0= RULE_STAR
                    {
                    lv_star_2_0=(Token)match(input,RULE_STAR,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_star_2_0, grammarAccess.getOperandFunctionAccess().getStarSTARTerminalRuleCall_2_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getOperandFunctionRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"star",
                              		lv_star_2_0, 
                              		"com.jaspersoft.studio.data.Sql.STAR");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5560:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    {
                    // InternalSqlParser.g:5560:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    // InternalSqlParser.g:5561:1: (lv_args_3_0= ruleOpFunctionArg )
                    {
                    // InternalSqlParser.g:5561:1: (lv_args_3_0= ruleOpFunctionArg )
                    // InternalSqlParser.g:5562:3: lv_args_3_0= ruleOpFunctionArg
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getArgsOpFunctionArgParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_8);
                    lv_args_3_0=ruleOpFunctionArg();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFunctionRule());
                      	        }
                             		set(
                             			current, 
                             			"args",
                              		lv_args_3_0, 
                              		"com.jaspersoft.studio.data.Sql.OpFunctionArg");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_77); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getOperandFunctionAccess().getRightParenthesisKeyword_3());
                  
            }
            // InternalSqlParser.g:5583:1: ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==OVER) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // InternalSqlParser.g:5584:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    {
                    // InternalSqlParser.g:5584:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    // InternalSqlParser.g:5585:3: lv_fan_5_0= ruleFunctionAnalytical
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFanFunctionAnalyticalParserRuleCall_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_fan_5_0=ruleFunctionAnalytical();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperandFunctionRule());
                      	        }
                             		set(
                             			current, 
                             			"fan",
                              		lv_fan_5_0, 
                              		"com.jaspersoft.studio.data.Sql.FunctionAnalytical");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperandFunction"


    // $ANTLR start "entryRuleFunctionExtract"
    // InternalSqlParser.g:5609:1: entryRuleFunctionExtract returns [EObject current=null] : iv_ruleFunctionExtract= ruleFunctionExtract EOF ;
    public final EObject entryRuleFunctionExtract() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionExtract = null;


        try {
            // InternalSqlParser.g:5610:2: (iv_ruleFunctionExtract= ruleFunctionExtract EOF )
            // InternalSqlParser.g:5611:2: iv_ruleFunctionExtract= ruleFunctionExtract EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionExtractRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFunctionExtract=ruleFunctionExtract();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionExtract; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunctionExtract"


    // $ANTLR start "ruleFunctionExtract"
    // InternalSqlParser.g:5618:1: ruleFunctionExtract returns [EObject current=null] : (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis ) ;
    public final EObject ruleFunctionExtract() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_v_2_0 = null;

        EObject lv_operand_4_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5621:28: ( (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis ) )
            // InternalSqlParser.g:5622:1: (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis )
            {
            // InternalSqlParser.g:5622:1: (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis )
            // InternalSqlParser.g:5623:2: otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis
            {
            otherlv_0=(Token)match(input,EXTRACT,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getFunctionExtractAccess().getEXTRACTKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_78); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFunctionExtractAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalSqlParser.g:5632:1: ( (lv_v_2_0= ruleEXTRACT_VALUES ) )
            // InternalSqlParser.g:5633:1: (lv_v_2_0= ruleEXTRACT_VALUES )
            {
            // InternalSqlParser.g:5633:1: (lv_v_2_0= ruleEXTRACT_VALUES )
            // InternalSqlParser.g:5634:3: lv_v_2_0= ruleEXTRACT_VALUES
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFunctionExtractAccess().getVEXTRACT_VALUESEnumRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_20);
            lv_v_2_0=ruleEXTRACT_VALUES();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFunctionExtractRule());
              	        }
                     		set(
                     			current, 
                     			"v",
                      		lv_v_2_0, 
                      		"com.jaspersoft.studio.data.Sql.EXTRACT_VALUES");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,FROM,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getFunctionExtractAccess().getFROMKeyword_3());
                  
            }
            // InternalSqlParser.g:5655:1: ( (lv_operand_4_0= ruleOperandGroup ) )
            // InternalSqlParser.g:5656:1: (lv_operand_4_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:5656:1: (lv_operand_4_0= ruleOperandGroup )
            // InternalSqlParser.g:5657:3: lv_operand_4_0= ruleOperandGroup
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFunctionExtractAccess().getOperandOperandGroupParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_operand_4_0=ruleOperandGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFunctionExtractRule());
              	        }
                     		set(
                     			current, 
                     			"operand",
                      		lv_operand_4_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandGroup");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_5=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getFunctionExtractAccess().getRightParenthesisKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunctionExtract"


    // $ANTLR start "entryRuleFunctionAnalytical"
    // InternalSqlParser.g:5686:1: entryRuleFunctionAnalytical returns [EObject current=null] : iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF ;
    public final EObject entryRuleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionAnalytical = null;


        try {
            // InternalSqlParser.g:5687:2: (iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF )
            // InternalSqlParser.g:5688:2: iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionAnalyticalRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFunctionAnalytical=ruleFunctionAnalytical();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionAnalytical; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunctionAnalytical"


    // $ANTLR start "ruleFunctionAnalytical"
    // InternalSqlParser.g:5695:1: ruleFunctionAnalytical returns [EObject current=null] : (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_anClause_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5698:28: ( (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:5699:1: (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:5699:1: (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:5700:2: otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis
            {
            otherlv_0=(Token)match(input,OVER,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getFunctionAnalyticalAccess().getOVERKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_79); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFunctionAnalyticalAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalSqlParser.g:5709:1: ( (lv_anClause_2_0= ruleAnalyticClause ) )
            // InternalSqlParser.g:5710:1: (lv_anClause_2_0= ruleAnalyticClause )
            {
            // InternalSqlParser.g:5710:1: (lv_anClause_2_0= ruleAnalyticClause )
            // InternalSqlParser.g:5711:3: lv_anClause_2_0= ruleAnalyticClause
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFunctionAnalyticalAccess().getAnClauseAnalyticClauseParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_anClause_2_0=ruleAnalyticClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFunctionAnalyticalRule());
              	        }
                     		set(
                     			current, 
                     			"anClause",
                      		lv_anClause_2_0, 
                      		"com.jaspersoft.studio.data.Sql.AnalyticClause");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getFunctionAnalyticalAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunctionAnalytical"


    // $ANTLR start "entryRuleAnalyticClause"
    // InternalSqlParser.g:5740:1: entryRuleAnalyticClause returns [EObject current=null] : iv_ruleAnalyticClause= ruleAnalyticClause EOF ;
    public final EObject entryRuleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticClause = null;


        try {
            // InternalSqlParser.g:5741:2: (iv_ruleAnalyticClause= ruleAnalyticClause EOF )
            // InternalSqlParser.g:5742:2: iv_ruleAnalyticClause= ruleAnalyticClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnalyticClauseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAnalyticClause=ruleAnalyticClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnalyticClause; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnalyticClause"


    // $ANTLR start "ruleAnalyticClause"
    // InternalSqlParser.g:5749:1: ruleAnalyticClause returns [EObject current=null] : ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? ) ;
    public final EObject ruleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject lv_abc_1_0 = null;

        EObject lv_obc_2_0 = null;

        EObject lv_winc_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5752:28: ( ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? ) )
            // InternalSqlParser.g:5753:1: ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? )
            {
            // InternalSqlParser.g:5753:1: ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? )
            // InternalSqlParser.g:5753:2: () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )?
            {
            // InternalSqlParser.g:5753:2: ()
            // InternalSqlParser.g:5754:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getAnalyticClauseAccess().getAnalyticClauseAction_0(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:5762:2: ( (lv_abc_1_0= ruleQueryPartitionClause ) )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==PARTITION) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // InternalSqlParser.g:5763:1: (lv_abc_1_0= ruleQueryPartitionClause )
                    {
                    // InternalSqlParser.g:5763:1: (lv_abc_1_0= ruleQueryPartitionClause )
                    // InternalSqlParser.g:5764:3: lv_abc_1_0= ruleQueryPartitionClause
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getAbcQueryPartitionClauseParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_80);
                    lv_abc_1_0=ruleQueryPartitionClause();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAnalyticClauseRule());
                      	        }
                             		set(
                             			current, 
                             			"abc",
                              		lv_abc_1_0, 
                              		"com.jaspersoft.studio.data.Sql.QueryPartitionClause");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:5780:3: ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==ORDER) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // InternalSqlParser.g:5780:4: ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )?
                    {
                    // InternalSqlParser.g:5780:4: ( (lv_obc_2_0= ruleOrderByClause ) )
                    // InternalSqlParser.g:5781:1: (lv_obc_2_0= ruleOrderByClause )
                    {
                    // InternalSqlParser.g:5781:1: (lv_obc_2_0= ruleOrderByClause )
                    // InternalSqlParser.g:5782:3: lv_obc_2_0= ruleOrderByClause
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getObcOrderByClauseParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_81);
                    lv_obc_2_0=ruleOrderByClause();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAnalyticClauseRule());
                      	        }
                             		set(
                             			current, 
                             			"obc",
                              		lv_obc_2_0, 
                              		"com.jaspersoft.studio.data.Sql.OrderByClause");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalSqlParser.g:5798:2: ( (lv_winc_3_0= ruleWindowingClause ) )?
                    int alt104=2;
                    int LA104_0 = input.LA(1);

                    if ( (LA104_0==RANGE||LA104_0==ROWS) ) {
                        alt104=1;
                    }
                    switch (alt104) {
                        case 1 :
                            // InternalSqlParser.g:5799:1: (lv_winc_3_0= ruleWindowingClause )
                            {
                            // InternalSqlParser.g:5799:1: (lv_winc_3_0= ruleWindowingClause )
                            // InternalSqlParser.g:5800:3: lv_winc_3_0= ruleWindowingClause
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getWincWindowingClauseParserRuleCall_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_2);
                            lv_winc_3_0=ruleWindowingClause();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAnalyticClauseRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"winc",
                                      		lv_winc_3_0, 
                                      		"com.jaspersoft.studio.data.Sql.WindowingClause");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnalyticClause"


    // $ANTLR start "entryRuleWindowingClause"
    // InternalSqlParser.g:5824:1: entryRuleWindowingClause returns [EObject current=null] : iv_ruleWindowingClause= ruleWindowingClause EOF ;
    public final EObject entryRuleWindowingClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClause = null;


        try {
            // InternalSqlParser.g:5825:2: (iv_ruleWindowingClause= ruleWindowingClause EOF )
            // InternalSqlParser.g:5826:2: iv_ruleWindowingClause= ruleWindowingClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWindowingClauseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWindowingClause=ruleWindowingClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWindowingClause; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWindowingClause"


    // $ANTLR start "ruleWindowingClause"
    // InternalSqlParser.g:5833:1: ruleWindowingClause returns [EObject current=null] : ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) ;
    public final EObject ruleWindowingClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_WindowingClauseBetween_2 = null;

        EObject this_WindowingClauseOperandPreceding_3 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5836:28: ( ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) )
            // InternalSqlParser.g:5837:1: ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            {
            // InternalSqlParser.g:5837:1: ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            // InternalSqlParser.g:5837:2: (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            {
            // InternalSqlParser.g:5837:2: (otherlv_0= ROWS | otherlv_1= RANGE )
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==ROWS) ) {
                alt106=1;
            }
            else if ( (LA106_0==RANGE) ) {
                alt106=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 106, 0, input);

                throw nvae;
            }
            switch (alt106) {
                case 1 :
                    // InternalSqlParser.g:5838:2: otherlv_0= ROWS
                    {
                    otherlv_0=(Token)match(input,ROWS,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseAccess().getROWSKeyword_0_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5844:2: otherlv_1= RANGE
                    {
                    otherlv_1=(Token)match(input,RANGE,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseAccess().getRANGEKeyword_0_1());
                          
                    }

                    }
                    break;

            }

            // InternalSqlParser.g:5848:2: (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==BETWEEN) ) {
                alt107=1;
            }
            else if ( (LA107_0==UNBOUNDED||LA107_0==CURRENT||LA107_0==EXTRACT||LA107_0==CAST||LA107_0==CASE||LA107_0==LeftParenthesis||(LA107_0>=RULE_JRPARAM && LA107_0<=RULE_JRNPARAM)||(LA107_0>=RULE_UNSIGNED && LA107_0<=RULE_SIGNED_DOUBLE)||(LA107_0>=RULE_STRING_ && LA107_0<=RULE_ID)) ) {
                alt107=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 107, 0, input);

                throw nvae;
            }
            switch (alt107) {
                case 1 :
                    // InternalSqlParser.g:5849:2: this_WindowingClauseBetween_2= ruleWindowingClauseBetween
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseBetweenParserRuleCall_1_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_WindowingClauseBetween_2=ruleWindowingClauseBetween();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_WindowingClauseBetween_2;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5862:2: this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseOperandPrecedingParserRuleCall_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_WindowingClauseOperandPreceding_3=ruleWindowingClauseOperandPreceding();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_WindowingClauseOperandPreceding_3;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWindowingClause"


    // $ANTLR start "entryRuleWindowingClauseBetween"
    // InternalSqlParser.g:5881:1: entryRuleWindowingClauseBetween returns [EObject current=null] : iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF ;
    public final EObject entryRuleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseBetween = null;


        try {
            // InternalSqlParser.g:5882:2: (iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF )
            // InternalSqlParser.g:5883:2: iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWindowingClauseBetweenRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWindowingClauseBetween=ruleWindowingClauseBetween();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWindowingClauseBetween; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWindowingClauseBetween"


    // $ANTLR start "ruleWindowingClauseBetween"
    // InternalSqlParser.g:5890:1: ruleWindowingClauseBetween returns [EObject current=null] : (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) ;
    public final EObject ruleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_wcoP_1_0 = null;

        EObject lv_wcoF_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5893:28: ( (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) )
            // InternalSqlParser.g:5894:1: (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            {
            // InternalSqlParser.g:5894:1: (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            // InternalSqlParser.g:5895:2: otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            {
            otherlv_0=(Token)match(input,BETWEEN,FOLLOW_82); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseBetweenAccess().getBETWEENKeyword_0());
                  
            }
            // InternalSqlParser.g:5899:1: ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) )
            // InternalSqlParser.g:5900:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            {
            // InternalSqlParser.g:5900:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            // InternalSqlParser.g:5901:3: lv_wcoP_1_0= ruleWindowingClauseOperandPreceding
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoPWindowingClauseOperandPrecedingParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_71);
            lv_wcoP_1_0=ruleWindowingClauseOperandPreceding();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getWindowingClauseBetweenRule());
              	        }
                     		set(
                     			current, 
                     			"wcoP",
                      		lv_wcoP_1_0, 
                      		"com.jaspersoft.studio.data.Sql.WindowingClauseOperandPreceding");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,AND,FOLLOW_82); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseBetweenAccess().getANDKeyword_2());
                  
            }
            // InternalSqlParser.g:5922:1: ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            // InternalSqlParser.g:5923:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            {
            // InternalSqlParser.g:5923:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            // InternalSqlParser.g:5924:3: lv_wcoF_3_0= ruleWindowingClauseOperandFollowing
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoFWindowingClauseOperandFollowingParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_wcoF_3_0=ruleWindowingClauseOperandFollowing();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getWindowingClauseBetweenRule());
              	        }
                     		set(
                     			current, 
                     			"wcoF",
                      		lv_wcoF_3_0, 
                      		"com.jaspersoft.studio.data.Sql.WindowingClauseOperandFollowing");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWindowingClauseBetween"


    // $ANTLR start "entryRuleWindowingClauseOperandFollowing"
    // InternalSqlParser.g:5948:1: entryRuleWindowingClauseOperandFollowing returns [EObject current=null] : iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF ;
    public final EObject entryRuleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandFollowing = null;


        try {
            // InternalSqlParser.g:5949:2: (iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF )
            // InternalSqlParser.g:5950:2: iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWindowingClauseOperandFollowing=ruleWindowingClauseOperandFollowing();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWindowingClauseOperandFollowing; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWindowingClauseOperandFollowing"


    // $ANTLR start "ruleWindowingClauseOperandFollowing"
    // InternalSqlParser.g:5957:1: ruleWindowingClauseOperandFollowing returns [EObject current=null] : ( () ( (otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_exp_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5960:28: ( ( () ( (otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) ) ) )
            // InternalSqlParser.g:5961:1: ( () ( (otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) ) )
            {
            // InternalSqlParser.g:5961:1: ( () ( (otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) ) )
            // InternalSqlParser.g:5961:2: () ( (otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) )
            {
            // InternalSqlParser.g:5961:2: ()
            // InternalSqlParser.g:5962:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getWindowingClauseOperandFollowingAccess().getWindowingClauseOperandFollowingAction_0(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:5970:2: ( (otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) )
            int alt109=3;
            switch ( input.LA(1) ) {
            case UNBOUNDED:
                {
                alt109=1;
                }
                break;
            case CURRENT:
                {
                alt109=2;
                }
                break;
            case EXTRACT:
            case CAST:
            case CASE:
            case LeftParenthesis:
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt109=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 109, 0, input);

                throw nvae;
            }

            switch (alt109) {
                case 1 :
                    // InternalSqlParser.g:5970:3: (otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING )
                    {
                    // InternalSqlParser.g:5970:3: (otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING )
                    // InternalSqlParser.g:5971:2: otherlv_1= UNBOUNDED otherlv_2= KW_FOLLOWING
                    {
                    otherlv_1=(Token)match(input,UNBOUNDED,FOLLOW_83); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandFollowingAccess().getUNBOUNDEDKeyword_1_0_0());
                          
                    }
                    otherlv_2=(Token)match(input,KW_FOLLOWING,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandFollowingAccess().getFOLLOWINGKeyword_1_0_1());
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5981:6: (otherlv_3= CURRENT otherlv_4= ROW )
                    {
                    // InternalSqlParser.g:5981:6: (otherlv_3= CURRENT otherlv_4= ROW )
                    // InternalSqlParser.g:5982:2: otherlv_3= CURRENT otherlv_4= ROW
                    {
                    otherlv_3=(Token)match(input,CURRENT,FOLLOW_84); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getWindowingClauseOperandFollowingAccess().getCURRENTKeyword_1_1_0());
                          
                    }
                    otherlv_4=(Token)match(input,ROW,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandFollowingAccess().getROWKeyword_1_1_1());
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:5992:6: ( ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) )
                    {
                    // InternalSqlParser.g:5992:6: ( ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) )
                    // InternalSqlParser.g:5992:7: ( (lv_exp_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING )
                    {
                    // InternalSqlParser.g:5992:7: ( (lv_exp_5_0= ruleAnalyticExprArg ) )
                    // InternalSqlParser.g:5993:1: (lv_exp_5_0= ruleAnalyticExprArg )
                    {
                    // InternalSqlParser.g:5993:1: (lv_exp_5_0= ruleAnalyticExprArg )
                    // InternalSqlParser.g:5994:3: lv_exp_5_0= ruleAnalyticExprArg
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingAccess().getExpAnalyticExprArgParserRuleCall_1_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_85);
                    lv_exp_5_0=ruleAnalyticExprArg();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getWindowingClauseOperandFollowingRule());
                      	        }
                             		set(
                             			current, 
                             			"exp",
                              		lv_exp_5_0, 
                              		"com.jaspersoft.studio.data.Sql.AnalyticExprArg");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalSqlParser.g:6010:2: (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING )
                    int alt108=2;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==PRECEDING) ) {
                        alt108=1;
                    }
                    else if ( (LA108_0==KW_FOLLOWING) ) {
                        alt108=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 108, 0, input);

                        throw nvae;
                    }
                    switch (alt108) {
                        case 1 :
                            // InternalSqlParser.g:6011:2: otherlv_6= PRECEDING
                            {
                            otherlv_6=(Token)match(input,PRECEDING,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_6, grammarAccess.getWindowingClauseOperandFollowingAccess().getPRECEDINGKeyword_1_2_1_0());
                                  
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:6017:2: otherlv_7= KW_FOLLOWING
                            {
                            otherlv_7=(Token)match(input,KW_FOLLOWING,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_7, grammarAccess.getWindowingClauseOperandFollowingAccess().getFOLLOWINGKeyword_1_2_1_1());
                                  
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWindowingClauseOperandFollowing"


    // $ANTLR start "entryRuleWindowingClauseOperandPreceding"
    // InternalSqlParser.g:6029:1: entryRuleWindowingClauseOperandPreceding returns [EObject current=null] : iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF ;
    public final EObject entryRuleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandPreceding = null;


        try {
            // InternalSqlParser.g:6030:2: (iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF )
            // InternalSqlParser.g:6031:2: iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWindowingClauseOperandPreceding=ruleWindowingClauseOperandPreceding();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWindowingClauseOperandPreceding; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWindowingClauseOperandPreceding"


    // $ANTLR start "ruleWindowingClauseOperandPreceding"
    // InternalSqlParser.g:6038:1: ruleWindowingClauseOperandPreceding returns [EObject current=null] : ( () ( (otherlv_1= UNBOUNDED otherlv_2= PRECEDING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_expr_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6041:28: ( ( () ( (otherlv_1= UNBOUNDED otherlv_2= PRECEDING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) ) ) )
            // InternalSqlParser.g:6042:1: ( () ( (otherlv_1= UNBOUNDED otherlv_2= PRECEDING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) ) )
            {
            // InternalSqlParser.g:6042:1: ( () ( (otherlv_1= UNBOUNDED otherlv_2= PRECEDING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) ) )
            // InternalSqlParser.g:6042:2: () ( (otherlv_1= UNBOUNDED otherlv_2= PRECEDING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) )
            {
            // InternalSqlParser.g:6042:2: ()
            // InternalSqlParser.g:6043:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getWindowingClauseOperandPrecedingAccess().getWindowingClauseOperandPrecedingAction_0(),
                          current);
                  
            }

            }

            // InternalSqlParser.g:6051:2: ( (otherlv_1= UNBOUNDED otherlv_2= PRECEDING ) | (otherlv_3= CURRENT otherlv_4= ROW ) | ( ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) ) )
            int alt111=3;
            switch ( input.LA(1) ) {
            case UNBOUNDED:
                {
                alt111=1;
                }
                break;
            case CURRENT:
                {
                alt111=2;
                }
                break;
            case EXTRACT:
            case CAST:
            case CASE:
            case LeftParenthesis:
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt111=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 111, 0, input);

                throw nvae;
            }

            switch (alt111) {
                case 1 :
                    // InternalSqlParser.g:6051:3: (otherlv_1= UNBOUNDED otherlv_2= PRECEDING )
                    {
                    // InternalSqlParser.g:6051:3: (otherlv_1= UNBOUNDED otherlv_2= PRECEDING )
                    // InternalSqlParser.g:6052:2: otherlv_1= UNBOUNDED otherlv_2= PRECEDING
                    {
                    otherlv_1=(Token)match(input,UNBOUNDED,FOLLOW_86); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandPrecedingAccess().getUNBOUNDEDKeyword_1_0_0());
                          
                    }
                    otherlv_2=(Token)match(input,PRECEDING,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandPrecedingAccess().getPRECEDINGKeyword_1_0_1());
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6062:6: (otherlv_3= CURRENT otherlv_4= ROW )
                    {
                    // InternalSqlParser.g:6062:6: (otherlv_3= CURRENT otherlv_4= ROW )
                    // InternalSqlParser.g:6063:2: otherlv_3= CURRENT otherlv_4= ROW
                    {
                    otherlv_3=(Token)match(input,CURRENT,FOLLOW_84); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getWindowingClauseOperandPrecedingAccess().getCURRENTKeyword_1_1_0());
                          
                    }
                    otherlv_4=(Token)match(input,ROW,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandPrecedingAccess().getROWKeyword_1_1_1());
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:6073:6: ( ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) )
                    {
                    // InternalSqlParser.g:6073:6: ( ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING ) )
                    // InternalSqlParser.g:6073:7: ( (lv_expr_5_0= ruleAnalyticExprArg ) ) (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING )
                    {
                    // InternalSqlParser.g:6073:7: ( (lv_expr_5_0= ruleAnalyticExprArg ) )
                    // InternalSqlParser.g:6074:1: (lv_expr_5_0= ruleAnalyticExprArg )
                    {
                    // InternalSqlParser.g:6074:1: (lv_expr_5_0= ruleAnalyticExprArg )
                    // InternalSqlParser.g:6075:3: lv_expr_5_0= ruleAnalyticExprArg
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingAccess().getExprAnalyticExprArgParserRuleCall_1_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_85);
                    lv_expr_5_0=ruleAnalyticExprArg();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getWindowingClauseOperandPrecedingRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_5_0, 
                              		"com.jaspersoft.studio.data.Sql.AnalyticExprArg");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalSqlParser.g:6091:2: (otherlv_6= PRECEDING | otherlv_7= KW_FOLLOWING )
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==PRECEDING) ) {
                        alt110=1;
                    }
                    else if ( (LA110_0==KW_FOLLOWING) ) {
                        alt110=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 110, 0, input);

                        throw nvae;
                    }
                    switch (alt110) {
                        case 1 :
                            // InternalSqlParser.g:6092:2: otherlv_6= PRECEDING
                            {
                            otherlv_6=(Token)match(input,PRECEDING,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_6, grammarAccess.getWindowingClauseOperandPrecedingAccess().getPRECEDINGKeyword_1_2_1_0());
                                  
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:6098:2: otherlv_7= KW_FOLLOWING
                            {
                            otherlv_7=(Token)match(input,KW_FOLLOWING,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_7, grammarAccess.getWindowingClauseOperandPrecedingAccess().getFOLLOWINGKeyword_1_2_1_1());
                                  
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWindowingClauseOperandPreceding"


    // $ANTLR start "entryRuleOrderByClause"
    // InternalSqlParser.g:6110:1: entryRuleOrderByClause returns [EObject current=null] : iv_ruleOrderByClause= ruleOrderByClause EOF ;
    public final EObject entryRuleOrderByClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClause = null;


        try {
            // InternalSqlParser.g:6111:2: (iv_ruleOrderByClause= ruleOrderByClause EOF )
            // InternalSqlParser.g:6112:2: iv_ruleOrderByClause= ruleOrderByClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrderByClauseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOrderByClause=ruleOrderByClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrderByClause; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrderByClause"


    // $ANTLR start "ruleOrderByClause"
    // InternalSqlParser.g:6119:1: ruleOrderByClause returns [EObject current=null] : ( ( (otherlv_0= ORDER otherlv_1= BY ) | (otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY ) ) ( (lv_args_5_0= ruleOrderByClauseArgs ) ) ) ;
    public final EObject ruleOrderByClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_args_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6122:28: ( ( ( (otherlv_0= ORDER otherlv_1= BY ) | (otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY ) ) ( (lv_args_5_0= ruleOrderByClauseArgs ) ) ) )
            // InternalSqlParser.g:6123:1: ( ( (otherlv_0= ORDER otherlv_1= BY ) | (otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY ) ) ( (lv_args_5_0= ruleOrderByClauseArgs ) ) )
            {
            // InternalSqlParser.g:6123:1: ( ( (otherlv_0= ORDER otherlv_1= BY ) | (otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY ) ) ( (lv_args_5_0= ruleOrderByClauseArgs ) ) )
            // InternalSqlParser.g:6123:2: ( (otherlv_0= ORDER otherlv_1= BY ) | (otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY ) ) ( (lv_args_5_0= ruleOrderByClauseArgs ) )
            {
            // InternalSqlParser.g:6123:2: ( (otherlv_0= ORDER otherlv_1= BY ) | (otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY ) )
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==ORDER) ) {
                int LA112_1 = input.LA(2);

                if ( (LA112_1==SIBLINGS) ) {
                    alt112=2;
                }
                else if ( (LA112_1==BY) ) {
                    alt112=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 112, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 112, 0, input);

                throw nvae;
            }
            switch (alt112) {
                case 1 :
                    // InternalSqlParser.g:6123:3: (otherlv_0= ORDER otherlv_1= BY )
                    {
                    // InternalSqlParser.g:6123:3: (otherlv_0= ORDER otherlv_1= BY )
                    // InternalSqlParser.g:6124:2: otherlv_0= ORDER otherlv_1= BY
                    {
                    otherlv_0=(Token)match(input,ORDER,FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getOrderByClauseAccess().getORDERKeyword_0_0_0());
                          
                    }
                    otherlv_1=(Token)match(input,BY,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseAccess().getBYKeyword_0_0_1());
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6134:6: (otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY )
                    {
                    // InternalSqlParser.g:6134:6: (otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY )
                    // InternalSqlParser.g:6135:2: otherlv_2= ORDER otherlv_3= SIBLINGS otherlv_4= BY
                    {
                    otherlv_2=(Token)match(input,ORDER,FOLLOW_87); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseAccess().getORDERKeyword_0_1_0());
                          
                    }
                    otherlv_3=(Token)match(input,SIBLINGS,FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getOrderByClauseAccess().getSIBLINGSKeyword_0_1_1());
                          
                    }
                    otherlv_4=(Token)match(input,BY,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getOrderByClauseAccess().getBYKeyword_0_1_2());
                          
                    }

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:6149:3: ( (lv_args_5_0= ruleOrderByClauseArgs ) )
            // InternalSqlParser.g:6150:1: (lv_args_5_0= ruleOrderByClauseArgs )
            {
            // InternalSqlParser.g:6150:1: (lv_args_5_0= ruleOrderByClauseArgs )
            // InternalSqlParser.g:6151:3: lv_args_5_0= ruleOrderByClauseArgs
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOrderByClauseAccess().getArgsOrderByClauseArgsParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_args_5_0=ruleOrderByClauseArgs();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOrderByClauseRule());
              	        }
                     		set(
                     			current, 
                     			"args",
                      		lv_args_5_0, 
                      		"com.jaspersoft.studio.data.Sql.OrderByClauseArgs");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrderByClause"


    // $ANTLR start "entryRuleOrderByClauseArgs"
    // InternalSqlParser.g:6175:1: entryRuleOrderByClauseArgs returns [EObject current=null] : iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF ;
    public final EObject entryRuleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArgs = null;


        try {
            // InternalSqlParser.g:6176:2: (iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF )
            // InternalSqlParser.g:6177:2: iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrderByClauseArgsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOrderByClauseArgs=ruleOrderByClauseArgs();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrderByClauseArgs; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrderByClauseArgs"


    // $ANTLR start "ruleOrderByClauseArgs"
    // InternalSqlParser.g:6184:1: ruleOrderByClauseArgs returns [EObject current=null] : (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) ;
    public final EObject ruleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6187:28: ( (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) )
            // InternalSqlParser.g:6188:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            {
            // InternalSqlParser.g:6188:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            // InternalSqlParser.g:6189:2: this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getOrderByClauseArgParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_OrderByClauseArg_0=ruleOrderByClauseArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_OrderByClauseArg_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:6200:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==Comma) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // InternalSqlParser.g:6200:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    {
                    // InternalSqlParser.g:6200:2: ()
                    // InternalSqlParser.g:6201:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getOrderByClauseArgsAccess().getOBCArgsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:6209:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    int cnt113=0;
                    loop113:
                    do {
                        int alt113=2;
                        int LA113_0 = input.LA(1);

                        if ( (LA113_0==Comma) ) {
                            alt113=1;
                        }


                        switch (alt113) {
                    	case 1 :
                    	    // InternalSqlParser.g:6210:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_82); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:6214:1: ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    // InternalSqlParser.g:6215:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    {
                    	    // InternalSqlParser.g:6215:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    // InternalSqlParser.g:6216:3: lv_entries_3_0= ruleOrderByClauseArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getEntriesOrderByClauseArgParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleOrderByClauseArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getOrderByClauseArgsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.OrderByClauseArg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt113 >= 1 ) break loop113;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(113, input);
                                throw eee;
                        }
                        cnt113++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrderByClauseArgs"


    // $ANTLR start "entryRuleOrderByClauseArg"
    // InternalSqlParser.g:6240:1: entryRuleOrderByClauseArg returns [EObject current=null] : iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF ;
    public final EObject entryRuleOrderByClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArg = null;


        try {
            // InternalSqlParser.g:6241:2: (iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF )
            // InternalSqlParser.g:6242:2: iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrderByClauseArgRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOrderByClauseArg=ruleOrderByClauseArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrderByClauseArg; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrderByClauseArg"


    // $ANTLR start "ruleOrderByClauseArg"
    // InternalSqlParser.g:6249:1: ruleOrderByClauseArg returns [EObject current=null] : ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? ) ;
    public final EObject ruleOrderByClauseArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_col_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6252:28: ( ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? ) )
            // InternalSqlParser.g:6253:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? )
            {
            // InternalSqlParser.g:6253:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? )
            // InternalSqlParser.g:6253:2: ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )?
            {
            // InternalSqlParser.g:6253:2: ( (lv_col_0_0= ruleAnalyticExprArg ) )
            // InternalSqlParser.g:6254:1: (lv_col_0_0= ruleAnalyticExprArg )
            {
            // InternalSqlParser.g:6254:1: (lv_col_0_0= ruleAnalyticExprArg )
            // InternalSqlParser.g:6255:3: lv_col_0_0= ruleAnalyticExprArg
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOrderByClauseArgAccess().getColAnalyticExprArgParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_88);
            lv_col_0_0=ruleAnalyticExprArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOrderByClauseArgRule());
              	        }
                     		set(
                     			current, 
                     			"col",
                      		lv_col_0_0, 
                      		"com.jaspersoft.studio.data.Sql.AnalyticExprArg");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:6271:2: (otherlv_1= ASC | otherlv_2= DESC )?
            int alt115=3;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==ASC) ) {
                alt115=1;
            }
            else if ( (LA115_0==DESC) ) {
                alt115=2;
            }
            switch (alt115) {
                case 1 :
                    // InternalSqlParser.g:6272:2: otherlv_1= ASC
                    {
                    otherlv_1=(Token)match(input,ASC,FOLLOW_89); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseArgAccess().getASCKeyword_1_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6278:2: otherlv_2= DESC
                    {
                    otherlv_2=(Token)match(input,DESC,FOLLOW_89); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgAccess().getDESCKeyword_1_1());
                          
                    }

                    }
                    break;

            }

            // InternalSqlParser.g:6282:3: (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==NULLS) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // InternalSqlParser.g:6283:2: otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST )
                    {
                    otherlv_3=(Token)match(input,NULLS,FOLLOW_90); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getOrderByClauseArgAccess().getNULLSKeyword_2_0());
                          
                    }
                    // InternalSqlParser.g:6287:1: (otherlv_4= FIRST | otherlv_5= LAST )
                    int alt116=2;
                    int LA116_0 = input.LA(1);

                    if ( (LA116_0==FIRST) ) {
                        alt116=1;
                    }
                    else if ( (LA116_0==LAST) ) {
                        alt116=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 116, 0, input);

                        throw nvae;
                    }
                    switch (alt116) {
                        case 1 :
                            // InternalSqlParser.g:6288:2: otherlv_4= FIRST
                            {
                            otherlv_4=(Token)match(input,FIRST,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getOrderByClauseArgAccess().getFIRSTKeyword_2_1_0());
                                  
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:6294:2: otherlv_5= LAST
                            {
                            otherlv_5=(Token)match(input,LAST,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getOrderByClauseArgAccess().getLASTKeyword_2_1_1());
                                  
                            }

                            }
                            break;

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrderByClauseArg"


    // $ANTLR start "entryRuleQueryPartitionClause"
    // InternalSqlParser.g:6306:1: entryRuleQueryPartitionClause returns [EObject current=null] : iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF ;
    public final EObject entryRuleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQueryPartitionClause = null;


        try {
            // InternalSqlParser.g:6307:2: (iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF )
            // InternalSqlParser.g:6308:2: iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQueryPartitionClauseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQueryPartitionClause=ruleQueryPartitionClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQueryPartitionClause; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQueryPartitionClause"


    // $ANTLR start "ruleQueryPartitionClause"
    // InternalSqlParser.g:6315:1: ruleQueryPartitionClause returns [EObject current=null] : (otherlv_0= PARTITION otherlv_1= BY ( ( (lv_args_2_0= ruleAnalyticExprArgs ) ) | (otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis ) ) ) ;
    public final EObject ruleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_args_2_0 = null;

        EObject this_AnalyticExprArgs_4 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6318:28: ( (otherlv_0= PARTITION otherlv_1= BY ( ( (lv_args_2_0= ruleAnalyticExprArgs ) ) | (otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis ) ) ) )
            // InternalSqlParser.g:6319:1: (otherlv_0= PARTITION otherlv_1= BY ( ( (lv_args_2_0= ruleAnalyticExprArgs ) ) | (otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis ) ) )
            {
            // InternalSqlParser.g:6319:1: (otherlv_0= PARTITION otherlv_1= BY ( ( (lv_args_2_0= ruleAnalyticExprArgs ) ) | (otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis ) ) )
            // InternalSqlParser.g:6320:2: otherlv_0= PARTITION otherlv_1= BY ( ( (lv_args_2_0= ruleAnalyticExprArgs ) ) | (otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis ) )
            {
            otherlv_0=(Token)match(input,PARTITION,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getQueryPartitionClauseAccess().getPARTITIONKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,BY,FOLLOW_82); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getQueryPartitionClauseAccess().getBYKeyword_1());
                  
            }
            // InternalSqlParser.g:6329:1: ( ( (lv_args_2_0= ruleAnalyticExprArgs ) ) | (otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis ) )
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==EXTRACT||LA118_0==CAST||LA118_0==CASE||(LA118_0>=RULE_JRPARAM && LA118_0<=RULE_JRNPARAM)||(LA118_0>=RULE_UNSIGNED && LA118_0<=RULE_SIGNED_DOUBLE)||(LA118_0>=RULE_STRING_ && LA118_0<=RULE_ID)) ) {
                alt118=1;
            }
            else if ( (LA118_0==LeftParenthesis) ) {
                int LA118_2 = input.LA(2);

                if ( (LA118_2==SELECT) ) {
                    alt118=1;
                }
                else if ( (LA118_2==EXTRACT||LA118_2==CAST||LA118_2==CASE||LA118_2==LeftParenthesis||(LA118_2>=RULE_JRPARAM && LA118_2<=RULE_JRNPARAM)||(LA118_2>=RULE_UNSIGNED && LA118_2<=RULE_SIGNED_DOUBLE)||(LA118_2>=RULE_STRING_ && LA118_2<=RULE_ID)) ) {
                    alt118=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 118, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;
            }
            switch (alt118) {
                case 1 :
                    // InternalSqlParser.g:6329:2: ( (lv_args_2_0= ruleAnalyticExprArgs ) )
                    {
                    // InternalSqlParser.g:6329:2: ( (lv_args_2_0= ruleAnalyticExprArgs ) )
                    // InternalSqlParser.g:6330:1: (lv_args_2_0= ruleAnalyticExprArgs )
                    {
                    // InternalSqlParser.g:6330:1: (lv_args_2_0= ruleAnalyticExprArgs )
                    // InternalSqlParser.g:6331:3: lv_args_2_0= ruleAnalyticExprArgs
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getArgsAnalyticExprArgsParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_args_2_0=ruleAnalyticExprArgs();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getQueryPartitionClauseRule());
                      	        }
                             		set(
                             			current, 
                             			"args",
                              		lv_args_2_0, 
                              		"com.jaspersoft.studio.data.Sql.AnalyticExprArgs");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6348:6: (otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis )
                    {
                    // InternalSqlParser.g:6348:6: (otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis )
                    // InternalSqlParser.g:6349:2: otherlv_3= LeftParenthesis this_AnalyticExprArgs_4= ruleAnalyticExprArgs otherlv_5= RightParenthesis
                    {
                    otherlv_3=(Token)match(input,LeftParenthesis,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getQueryPartitionClauseAccess().getLeftParenthesisKeyword_2_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getAnalyticExprArgsParserRuleCall_2_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_8);
                    this_AnalyticExprArgs_4=ruleAnalyticExprArgs();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_AnalyticExprArgs_4;
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_5=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getQueryPartitionClauseAccess().getRightParenthesisKeyword_2_1_2());
                          
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQueryPartitionClause"


    // $ANTLR start "entryRuleAnalyticExprArgs"
    // InternalSqlParser.g:6378:1: entryRuleAnalyticExprArgs returns [EObject current=null] : iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF ;
    public final EObject entryRuleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArgs = null;


        try {
            // InternalSqlParser.g:6379:2: (iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF )
            // InternalSqlParser.g:6380:2: iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnalyticExprArgsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAnalyticExprArgs=ruleAnalyticExprArgs();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnalyticExprArgs; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnalyticExprArgs"


    // $ANTLR start "ruleAnalyticExprArgs"
    // InternalSqlParser.g:6387:1: ruleAnalyticExprArgs returns [EObject current=null] : (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) ;
    public final EObject ruleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AnalyticExprArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6390:28: ( (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) )
            // InternalSqlParser.g:6391:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            {
            // InternalSqlParser.g:6391:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            // InternalSqlParser.g:6392:2: this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getAnalyticExprArgParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_AnalyticExprArg_0=ruleAnalyticExprArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_AnalyticExprArg_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:6403:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==Comma) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // InternalSqlParser.g:6403:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    {
                    // InternalSqlParser.g:6403:2: ()
                    // InternalSqlParser.g:6404:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getAnalyticExprArgsAccess().getAExpArgsEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:6412:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    int cnt119=0;
                    loop119:
                    do {
                        int alt119=2;
                        int LA119_0 = input.LA(1);

                        if ( (LA119_0==Comma) ) {
                            alt119=1;
                        }


                        switch (alt119) {
                    	case 1 :
                    	    // InternalSqlParser.g:6413:2: otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_82); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getAnalyticExprArgsAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:6417:1: ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    // InternalSqlParser.g:6418:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    {
                    	    // InternalSqlParser.g:6418:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    // InternalSqlParser.g:6419:3: lv_entries_3_0= ruleAnalyticExprArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getEntriesAnalyticExprArgParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleAnalyticExprArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getAnalyticExprArgsRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.AnalyticExprArg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt119 >= 1 ) break loop119;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(119, input);
                                throw eee;
                        }
                        cnt119++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnalyticExprArgs"


    // $ANTLR start "entryRuleAnalyticExprArg"
    // InternalSqlParser.g:6443:1: entryRuleAnalyticExprArg returns [EObject current=null] : iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF ;
    public final EObject entryRuleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArg = null;


        try {
            // InternalSqlParser.g:6444:2: (iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF )
            // InternalSqlParser.g:6445:2: iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnalyticExprArgRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAnalyticExprArg=ruleAnalyticExprArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnalyticExprArg; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnalyticExprArg"


    // $ANTLR start "ruleAnalyticExprArg"
    // InternalSqlParser.g:6452:1: ruleAnalyticExprArg returns [EObject current=null] : ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6455:28: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) )
            // InternalSqlParser.g:6456:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            {
            // InternalSqlParser.g:6456:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            // InternalSqlParser.g:6456:2: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            {
            // InternalSqlParser.g:6456:2: ( (lv_ce_0_0= ruleOperand ) )
            // InternalSqlParser.g:6457:1: (lv_ce_0_0= ruleOperand )
            {
            // InternalSqlParser.g:6457:1: (lv_ce_0_0= ruleOperand )
            // InternalSqlParser.g:6458:3: lv_ce_0_0= ruleOperand
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getCeOperandParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_36);
            lv_ce_0_0=ruleOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getAnalyticExprArgRule());
              	        }
                     		set(
                     			current, 
                     			"ce",
                      		lv_ce_0_0, 
                      		"com.jaspersoft.studio.data.Sql.Operand");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:6474:2: ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( ((LA121_0>=RULE_STRING && LA121_0<=RULE_ID)) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // InternalSqlParser.g:6475:1: (lv_colAlias_1_0= ruleDbObjectName )
                    {
                    // InternalSqlParser.g:6475:1: (lv_colAlias_1_0= ruleDbObjectName )
                    // InternalSqlParser.g:6476:3: lv_colAlias_1_0= ruleDbObjectName
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getColAliasDbObjectNameParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_colAlias_1_0=ruleDbObjectName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAnalyticExprArgRule());
                      	        }
                             		set(
                             			current, 
                             			"colAlias",
                              		lv_colAlias_1_0, 
                              		"com.jaspersoft.studio.data.Sql.DbObjectName");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnalyticExprArg"


    // $ANTLR start "entryRuleOpFunctionArg"
    // InternalSqlParser.g:6500:1: entryRuleOpFunctionArg returns [EObject current=null] : iv_ruleOpFunctionArg= ruleOpFunctionArg EOF ;
    public final EObject entryRuleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArg = null;


        try {
            // InternalSqlParser.g:6501:2: (iv_ruleOpFunctionArg= ruleOpFunctionArg EOF )
            // InternalSqlParser.g:6502:2: iv_ruleOpFunctionArg= ruleOpFunctionArg EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpFunctionArgRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpFunctionArg=ruleOpFunctionArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpFunctionArg; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpFunctionArg"


    // $ANTLR start "ruleOpFunctionArg"
    // InternalSqlParser.g:6509:1: ruleOpFunctionArg returns [EObject current=null] : (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) ;
    public final EObject ruleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OpFunctionArgOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6512:28: ( (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) )
            // InternalSqlParser.g:6513:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            {
            // InternalSqlParser.g:6513:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            // InternalSqlParser.g:6514:2: this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOpFunctionArgAccess().getOpFunctionArgOperandParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_OpFunctionArgOperand_0=ruleOpFunctionArgOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_OpFunctionArgOperand_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:6525:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==Comma) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // InternalSqlParser.g:6525:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    {
                    // InternalSqlParser.g:6525:2: ()
                    // InternalSqlParser.g:6526:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getOpFunctionArgAccess().getOpFListEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:6534:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    int cnt122=0;
                    loop122:
                    do {
                        int alt122=2;
                        int LA122_0 = input.LA(1);

                        if ( (LA122_0==Comma) ) {
                            alt122=1;
                        }


                        switch (alt122) {
                    	case 1 :
                    	    // InternalSqlParser.g:6535:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_91); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getOpFunctionArgAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalSqlParser.g:6539:1: ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    // InternalSqlParser.g:6540:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    {
                    	    // InternalSqlParser.g:6540:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    // InternalSqlParser.g:6541:3: lv_entries_3_0= ruleOpFunctionArgOperand
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOpFunctionArgAccess().getEntriesOpFunctionArgOperandParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_entries_3_0=ruleOpFunctionArgOperand();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getOpFunctionArgRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_3_0, 
                    	              		"com.jaspersoft.studio.data.Sql.OpFunctionArgOperand");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt122 >= 1 ) break loop122;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(122, input);
                                throw eee;
                        }
                        cnt122++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpFunctionArg"


    // $ANTLR start "entryRuleOpFunctionArgOperand"
    // InternalSqlParser.g:6565:1: entryRuleOpFunctionArgOperand returns [EObject current=null] : iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF ;
    public final EObject entryRuleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgOperand = null;


        try {
            // InternalSqlParser.g:6566:2: (iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF )
            // InternalSqlParser.g:6567:2: iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpFunctionArgOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpFunctionArgOperand=ruleOpFunctionArgOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpFunctionArgOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpFunctionArgOperand"


    // $ANTLR start "ruleOpFunctionArgOperand"
    // InternalSqlParser.g:6574:1: ruleOpFunctionArgOperand returns [EObject current=null] : ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) ;
    public final EObject ruleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject lv_op_0_1 = null;

        EObject lv_op_0_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6577:28: ( ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) )
            // InternalSqlParser.g:6578:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            {
            // InternalSqlParser.g:6578:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            // InternalSqlParser.g:6579:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            {
            // InternalSqlParser.g:6579:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            // InternalSqlParser.g:6580:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            {
            // InternalSqlParser.g:6580:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==DISTINCT||LA124_0==ALL) ) {
                alt124=1;
            }
            else if ( (LA124_0==EXTRACT||LA124_0==CAST||LA124_0==CASE||LA124_0==LeftParenthesis||(LA124_0>=RULE_JRPARAM && LA124_0<=RULE_JRNPARAM)||(LA124_0>=RULE_UNSIGNED && LA124_0<=RULE_SIGNED_DOUBLE)||(LA124_0>=RULE_STRING_ && LA124_0<=RULE_ID)) ) {
                alt124=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 124, 0, input);

                throw nvae;
            }
            switch (alt124) {
                case 1 :
                    // InternalSqlParser.g:6581:3: lv_op_0_1= ruleOpFunctionArgAgregate
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOpFunctionArgAgregateParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_op_0_1=ruleOpFunctionArgAgregate();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOpFunctionArgOperandRule());
                      	        }
                             		set(
                             			current, 
                             			"op",
                              		lv_op_0_1, 
                              		"com.jaspersoft.studio.data.Sql.OpFunctionArgAgregate");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6596:8: lv_op_0_2= ruleOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOperandParserRuleCall_0_1()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_op_0_2=ruleOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOpFunctionArgOperandRule());
                      	        }
                             		set(
                             			current, 
                             			"op",
                              		lv_op_0_2, 
                              		"com.jaspersoft.studio.data.Sql.Operand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }
                    break;

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpFunctionArgOperand"


    // $ANTLR start "entryRuleOpFunctionCast"
    // InternalSqlParser.g:6622:1: entryRuleOpFunctionCast returns [EObject current=null] : iv_ruleOpFunctionCast= ruleOpFunctionCast EOF ;
    public final EObject entryRuleOpFunctionCast() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionCast = null;


        try {
            // InternalSqlParser.g:6623:2: (iv_ruleOpFunctionCast= ruleOpFunctionCast EOF )
            // InternalSqlParser.g:6624:2: iv_ruleOpFunctionCast= ruleOpFunctionCast EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpFunctionCastRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpFunctionCast=ruleOpFunctionCast();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpFunctionCast; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpFunctionCast"


    // $ANTLR start "ruleOpFunctionCast"
    // InternalSqlParser.g:6631:1: ruleOpFunctionCast returns [EObject current=null] : (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_UNSIGNED ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis ) ;
    public final EObject ruleOpFunctionCast() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_type_3_0=null;
        Token otherlv_4=null;
        Token lv_p_5_0=null;
        Token otherlv_6=null;
        Token lv_p2_7_0=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        EObject lv_op_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6634:28: ( (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_UNSIGNED ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis ) )
            // InternalSqlParser.g:6635:1: (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_UNSIGNED ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis )
            {
            // InternalSqlParser.g:6635:1: (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_UNSIGNED ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis )
            // InternalSqlParser.g:6636:2: otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_UNSIGNED ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis
            {
            otherlv_0=(Token)match(input,CAST,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getOpFunctionCastAccess().getCASTKeyword_0());
                  
            }
            // InternalSqlParser.g:6640:1: ( (lv_op_1_0= ruleOperandGroup ) )
            // InternalSqlParser.g:6641:1: (lv_op_1_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:6641:1: (lv_op_1_0= ruleOperandGroup )
            // InternalSqlParser.g:6642:3: lv_op_1_0= ruleOperandGroup
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOpFunctionCastAccess().getOpOperandGroupParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_6);
            lv_op_1_0=ruleOperandGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOpFunctionCastRule());
              	        }
                     		set(
                     			current, 
                     			"op",
                      		lv_op_1_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandGroup");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,AS,FOLLOW_46); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getOpFunctionCastAccess().getASKeyword_2());
                  
            }
            // InternalSqlParser.g:6663:1: ( (lv_type_3_0= RULE_ID ) )
            // InternalSqlParser.g:6664:1: (lv_type_3_0= RULE_ID )
            {
            // InternalSqlParser.g:6664:1: (lv_type_3_0= RULE_ID )
            // InternalSqlParser.g:6665:3: lv_type_3_0= RULE_ID
            {
            lv_type_3_0=(Token)match(input,RULE_ID,FOLLOW_92); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_type_3_0, grammarAccess.getOpFunctionCastAccess().getTypeIDTerminalRuleCall_3_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getOpFunctionCastRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"type",
                      		lv_type_3_0, 
                      		"com.jaspersoft.studio.data.Sql.ID");
              	    
            }

            }


            }

            // InternalSqlParser.g:6681:2: (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_UNSIGNED ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) ) )? otherlv_8= RightParenthesis )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==LeftParenthesis) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // InternalSqlParser.g:6682:2: otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_UNSIGNED ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) ) )? otherlv_8= RightParenthesis
                    {
                    otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_12); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getOpFunctionCastAccess().getLeftParenthesisKeyword_4_0());
                          
                    }
                    // InternalSqlParser.g:6686:1: ( (lv_p_5_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:6687:1: (lv_p_5_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:6687:1: (lv_p_5_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:6688:3: lv_p_5_0= RULE_UNSIGNED
                    {
                    lv_p_5_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_93); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_p_5_0, grammarAccess.getOpFunctionCastAccess().getPUNSIGNEDTerminalRuleCall_4_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getOpFunctionCastRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"p",
                              		lv_p_5_0, 
                              		"com.jaspersoft.studio.data.Sql.UNSIGNED");
                      	    
                    }

                    }


                    }

                    // InternalSqlParser.g:6704:2: (otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) ) )?
                    int alt125=2;
                    int LA125_0 = input.LA(1);

                    if ( (LA125_0==Comma) ) {
                        alt125=1;
                    }
                    switch (alt125) {
                        case 1 :
                            // InternalSqlParser.g:6705:2: otherlv_6= Comma ( (lv_p2_7_0= RULE_UNSIGNED ) )
                            {
                            otherlv_6=(Token)match(input,Comma,FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_6, grammarAccess.getOpFunctionCastAccess().getCommaKeyword_4_2_0());
                                  
                            }
                            // InternalSqlParser.g:6709:1: ( (lv_p2_7_0= RULE_UNSIGNED ) )
                            // InternalSqlParser.g:6710:1: (lv_p2_7_0= RULE_UNSIGNED )
                            {
                            // InternalSqlParser.g:6710:1: (lv_p2_7_0= RULE_UNSIGNED )
                            // InternalSqlParser.g:6711:3: lv_p2_7_0= RULE_UNSIGNED
                            {
                            lv_p2_7_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_8); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_p2_7_0, grammarAccess.getOpFunctionCastAccess().getP2UNSIGNEDTerminalRuleCall_4_2_1_0()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getOpFunctionCastRule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"p2",
                                      		lv_p2_7_0, 
                                      		"com.jaspersoft.studio.data.Sql.UNSIGNED");
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }

                    otherlv_8=(Token)match(input,RightParenthesis,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getOpFunctionCastAccess().getRightParenthesisKeyword_4_3());
                          
                    }

                    }
                    break;

            }

            otherlv_9=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getOpFunctionCastAccess().getRightParenthesisKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpFunctionCast"


    // $ANTLR start "entryRuleOpFunctionArgAgregate"
    // InternalSqlParser.g:6745:1: entryRuleOpFunctionArgAgregate returns [EObject current=null] : iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF ;
    public final EObject entryRuleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgAgregate = null;


        try {
            // InternalSqlParser.g:6746:2: (iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF )
            // InternalSqlParser.g:6747:2: iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpFunctionArgAgregateRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpFunctionArgAgregate=ruleOpFunctionArgAgregate();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpFunctionArgAgregate; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpFunctionArgAgregate"


    // $ANTLR start "ruleOpFunctionArgAgregate"
    // InternalSqlParser.g:6754:1: ruleOpFunctionArgAgregate returns [EObject current=null] : ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand ) ;
    public final EObject ruleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6757:28: ( ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand ) )
            // InternalSqlParser.g:6758:1: ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand )
            {
            // InternalSqlParser.g:6758:1: ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand )
            // InternalSqlParser.g:6758:2: (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand
            {
            // InternalSqlParser.g:6758:2: (otherlv_0= ALL | otherlv_1= DISTINCT )
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==ALL) ) {
                alt127=1;
            }
            else if ( (LA127_0==DISTINCT) ) {
                alt127=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 127, 0, input);

                throw nvae;
            }
            switch (alt127) {
                case 1 :
                    // InternalSqlParser.g:6759:2: otherlv_0= ALL
                    {
                    otherlv_0=(Token)match(input,ALL,FOLLOW_60); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getOpFunctionArgAgregateAccess().getALLKeyword_0_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6765:2: otherlv_1= DISTINCT
                    {
                    otherlv_1=(Token)match(input,DISTINCT,FOLLOW_60); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getOpFunctionArgAgregateAccess().getDISTINCTKeyword_0_1());
                          
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOpFunctionArgAgregateAccess().getOperandParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_2);
            this_Operand_2=ruleOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_Operand_2;
                      afterParserOrEnumRuleCall();
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpFunctionArgAgregate"


    // $ANTLR start "entryRuleXOperandFragment"
    // InternalSqlParser.g:6789:1: entryRuleXOperandFragment returns [EObject current=null] : iv_ruleXOperandFragment= ruleXOperandFragment EOF ;
    public final EObject entryRuleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOperandFragment = null;


        try {
            // InternalSqlParser.g:6790:2: (iv_ruleXOperandFragment= ruleXOperandFragment EOF )
            // InternalSqlParser.g:6791:2: iv_ruleXOperandFragment= ruleXOperandFragment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXOperandFragmentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXOperandFragment=ruleXOperandFragment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXOperandFragment; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXOperandFragment"


    // $ANTLR start "ruleXOperandFragment"
    // InternalSqlParser.g:6798:1: ruleXOperandFragment returns [EObject current=null] : ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) ) ;
    public final EObject ruleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_param_0_0 = null;

        EObject lv_eparam_1_0 = null;

        EObject lv_scalar_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6801:28: ( ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) ) )
            // InternalSqlParser.g:6802:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) )
            {
            // InternalSqlParser.g:6802:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) )
            int alt128=3;
            switch ( input.LA(1) ) {
            case RULE_JRPARAM:
                {
                alt128=1;
                }
                break;
            case RULE_JRNPARAM:
                {
                alt128=2;
                }
                break;
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt128=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 128, 0, input);

                throw nvae;
            }

            switch (alt128) {
                case 1 :
                    // InternalSqlParser.g:6802:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    {
                    // InternalSqlParser.g:6802:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    // InternalSqlParser.g:6803:1: (lv_param_0_0= ruleParameterOperand )
                    {
                    // InternalSqlParser.g:6803:1: (lv_param_0_0= ruleParameterOperand )
                    // InternalSqlParser.g:6804:3: lv_param_0_0= ruleParameterOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_param_0_0=ruleParameterOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"param",
                              		lv_param_0_0, 
                              		"com.jaspersoft.studio.data.Sql.ParameterOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6821:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    {
                    // InternalSqlParser.g:6821:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    // InternalSqlParser.g:6822:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    {
                    // InternalSqlParser.g:6822:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    // InternalSqlParser.g:6823:3: lv_eparam_1_0= ruleExclamationParameterOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_eparam_1_0=ruleExclamationParameterOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"eparam",
                              		lv_eparam_1_0, 
                              		"com.jaspersoft.studio.data.Sql.ExclamationParameterOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:6840:6: ( (lv_scalar_2_0= ruleScalarNumberOperand ) )
                    {
                    // InternalSqlParser.g:6840:6: ( (lv_scalar_2_0= ruleScalarNumberOperand ) )
                    // InternalSqlParser.g:6841:1: (lv_scalar_2_0= ruleScalarNumberOperand )
                    {
                    // InternalSqlParser.g:6841:1: (lv_scalar_2_0= ruleScalarNumberOperand )
                    // InternalSqlParser.g:6842:3: lv_scalar_2_0= ruleScalarNumberOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getScalarScalarNumberOperandParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_scalar_2_0=ruleScalarNumberOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXOperandFragmentRule());
                      	        }
                             		set(
                             			current, 
                             			"scalar",
                              		lv_scalar_2_0, 
                              		"com.jaspersoft.studio.data.Sql.ScalarNumberOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXOperandFragment"


    // $ANTLR start "entryRuleParameterOperand"
    // InternalSqlParser.g:6866:1: entryRuleParameterOperand returns [EObject current=null] : iv_ruleParameterOperand= ruleParameterOperand EOF ;
    public final EObject entryRuleParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterOperand = null;


        try {
            // InternalSqlParser.g:6867:2: (iv_ruleParameterOperand= ruleParameterOperand EOF )
            // InternalSqlParser.g:6868:2: iv_ruleParameterOperand= ruleParameterOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParameterOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParameterOperand=ruleParameterOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParameterOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterOperand"


    // $ANTLR start "ruleParameterOperand"
    // InternalSqlParser.g:6875:1: ruleParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRPARAM ) ) ;
    public final EObject ruleParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6878:28: ( ( (lv_prm_0_0= RULE_JRPARAM ) ) )
            // InternalSqlParser.g:6879:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            {
            // InternalSqlParser.g:6879:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            // InternalSqlParser.g:6880:1: (lv_prm_0_0= RULE_JRPARAM )
            {
            // InternalSqlParser.g:6880:1: (lv_prm_0_0= RULE_JRPARAM )
            // InternalSqlParser.g:6881:3: lv_prm_0_0= RULE_JRPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRPARAM,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_prm_0_0, grammarAccess.getParameterOperandAccess().getPrmJRPARAMTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getParameterOperandRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"prm",
                      		lv_prm_0_0, 
                      		"com.jaspersoft.studio.data.Sql.JRPARAM");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterOperand"


    // $ANTLR start "entryRuleExclamationParameterOperand"
    // InternalSqlParser.g:6905:1: entryRuleExclamationParameterOperand returns [EObject current=null] : iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF ;
    public final EObject entryRuleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclamationParameterOperand = null;


        try {
            // InternalSqlParser.g:6906:2: (iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF )
            // InternalSqlParser.g:6907:2: iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExclamationParameterOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExclamationParameterOperand=ruleExclamationParameterOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExclamationParameterOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExclamationParameterOperand"


    // $ANTLR start "ruleExclamationParameterOperand"
    // InternalSqlParser.g:6914:1: ruleExclamationParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRNPARAM ) ) ;
    public final EObject ruleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6917:28: ( ( (lv_prm_0_0= RULE_JRNPARAM ) ) )
            // InternalSqlParser.g:6918:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            {
            // InternalSqlParser.g:6918:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            // InternalSqlParser.g:6919:1: (lv_prm_0_0= RULE_JRNPARAM )
            {
            // InternalSqlParser.g:6919:1: (lv_prm_0_0= RULE_JRNPARAM )
            // InternalSqlParser.g:6920:3: lv_prm_0_0= RULE_JRNPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_prm_0_0, grammarAccess.getExclamationParameterOperandAccess().getPrmJRNPARAMTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getExclamationParameterOperandRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"prm",
                      		lv_prm_0_0, 
                      		"com.jaspersoft.studio.data.Sql.JRNPARAM");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExclamationParameterOperand"


    // $ANTLR start "entryRuleColumnOperand"
    // InternalSqlParser.g:6944:1: entryRuleColumnOperand returns [EObject current=null] : iv_ruleColumnOperand= ruleColumnOperand EOF ;
    public final EObject entryRuleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOperand = null;


        try {
            // InternalSqlParser.g:6945:2: (iv_ruleColumnOperand= ruleColumnOperand EOF )
            // InternalSqlParser.g:6946:2: iv_ruleColumnOperand= ruleColumnOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getColumnOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleColumnOperand=ruleColumnOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleColumnOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleColumnOperand"


    // $ANTLR start "ruleColumnOperand"
    // InternalSqlParser.g:6953:1: ruleColumnOperand returns [EObject current=null] : ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? ) ;
    public final EObject ruleColumnOperand() throws RecognitionException {
        EObject current = null;

        Token lv_ora_1_0=null;
        EObject lv_cfull_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6956:28: ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? ) )
            // InternalSqlParser.g:6957:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? )
            {
            // InternalSqlParser.g:6957:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? )
            // InternalSqlParser.g:6957:2: ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )?
            {
            // InternalSqlParser.g:6957:2: ( (lv_cfull_0_0= ruleColumnFull ) )
            // InternalSqlParser.g:6958:1: (lv_cfull_0_0= ruleColumnFull )
            {
            // InternalSqlParser.g:6958:1: (lv_cfull_0_0= ruleColumnFull )
            // InternalSqlParser.g:6959:3: lv_cfull_0_0= ruleColumnFull
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_94);
            lv_cfull_0_0=ruleColumnFull();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getColumnOperandRule());
              	        }
                     		set(
                     			current, 
                     			"cfull",
                      		lv_cfull_0_0, 
                      		"com.jaspersoft.studio.data.Sql.ColumnFull");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:6975:2: ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==LeftParenthesisPlusSignRightParenthesis) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // InternalSqlParser.g:6976:1: (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis )
                    {
                    // InternalSqlParser.g:6976:1: (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis )
                    // InternalSqlParser.g:6977:3: lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis
                    {
                    lv_ora_1_0=(Token)match(input,LeftParenthesisPlusSignRightParenthesis,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_ora_1_0, grammarAccess.getColumnOperandAccess().getOraLeftParenthesisPlusSignRightParenthesisKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getColumnOperandRule());
                      	        }
                             		setWithLastConsumed(current, "ora", lv_ora_1_0, "(+)");
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleColumnOperand"


    // $ANTLR start "entryRuleSubQueryOperand"
    // InternalSqlParser.g:6999:1: entryRuleSubQueryOperand returns [EObject current=null] : iv_ruleSubQueryOperand= ruleSubQueryOperand EOF ;
    public final EObject entryRuleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubQueryOperand = null;


        try {
            // InternalSqlParser.g:7000:2: (iv_ruleSubQueryOperand= ruleSubQueryOperand EOF )
            // InternalSqlParser.g:7001:2: iv_ruleSubQueryOperand= ruleSubQueryOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSubQueryOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSubQueryOperand=ruleSubQueryOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSubQueryOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSubQueryOperand"


    // $ANTLR start "ruleSubQueryOperand"
    // InternalSqlParser.g:7008:1: ruleSubQueryOperand returns [EObject current=null] : ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_sel_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:7011:28: ( ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:7012:1: ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:7012:1: ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:7012:2: () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis
            {
            // InternalSqlParser.g:7012:2: ()
            // InternalSqlParser.g:7013:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getSubQueryOperandAccess().getSubQueryOperandAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalSqlParser.g:7026:1: ( (lv_sel_2_0= ruleSelectQuery ) )
            // InternalSqlParser.g:7027:1: (lv_sel_2_0= ruleSelectQuery )
            {
            // InternalSqlParser.g:7027:1: (lv_sel_2_0= ruleSelectQuery )
            // InternalSqlParser.g:7028:3: lv_sel_2_0= ruleSelectQuery
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSubQueryOperandAccess().getSelSelectQueryParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_8);
            lv_sel_2_0=ruleSelectQuery();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSubQueryOperandRule());
              	        }
                     		set(
                     			current, 
                     			"sel",
                      		lv_sel_2_0, 
                      		"com.jaspersoft.studio.data.Sql.SelectQuery");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getSubQueryOperandAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSubQueryOperand"


    // $ANTLR start "entryRuleScalarOperand"
    // InternalSqlParser.g:7057:1: entryRuleScalarOperand returns [EObject current=null] : iv_ruleScalarOperand= ruleScalarOperand EOF ;
    public final EObject entryRuleScalarOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarOperand = null;


        try {
            // InternalSqlParser.g:7058:2: (iv_ruleScalarOperand= ruleScalarOperand EOF )
            // InternalSqlParser.g:7059:2: iv_ruleScalarOperand= ruleScalarOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScalarOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScalarOperand=ruleScalarOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScalarOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleScalarOperand"


    // $ANTLR start "ruleScalarOperand"
    // InternalSqlParser.g:7066:1: ruleScalarOperand returns [EObject current=null] : ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) ) ;
    public final EObject ruleScalarOperand() throws RecognitionException {
        EObject current = null;

        Token lv_sodbl_1_0=null;
        Token lv_sodate_2_0=null;
        Token lv_sotime_3_0=null;
        Token lv_sodt_4_0=null;
        AntlrDatatypeRuleToken lv_sostr_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:7069:28: ( ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) ) )
            // InternalSqlParser.g:7070:1: ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) )
            {
            // InternalSqlParser.g:7070:1: ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) )
            int alt130=5;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt130=1;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt130=2;
                }
                break;
            case RULE_DATE:
                {
                alt130=3;
                }
                break;
            case RULE_TIME:
                {
                alt130=4;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt130=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 130, 0, input);

                throw nvae;
            }

            switch (alt130) {
                case 1 :
                    // InternalSqlParser.g:7070:2: ( (lv_sostr_0_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:7070:2: ( (lv_sostr_0_0= ruleStringOperand ) )
                    // InternalSqlParser.g:7071:1: (lv_sostr_0_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:7071:1: (lv_sostr_0_0= ruleStringOperand )
                    // InternalSqlParser.g:7072:3: lv_sostr_0_0= ruleStringOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_sostr_0_0=ruleStringOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScalarOperandRule());
                      	        }
                             		set(
                             			current, 
                             			"sostr",
                              		lv_sostr_0_0, 
                              		"com.jaspersoft.studio.data.Sql.StringOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7089:6: ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // InternalSqlParser.g:7089:6: ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) )
                    // InternalSqlParser.g:7090:1: (lv_sodbl_1_0= RULE_SIGNED_DOUBLE )
                    {
                    // InternalSqlParser.g:7090:1: (lv_sodbl_1_0= RULE_SIGNED_DOUBLE )
                    // InternalSqlParser.g:7091:3: lv_sodbl_1_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_1_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_sodbl_1_0, grammarAccess.getScalarOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getScalarOperandRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"sodbl",
                              		lv_sodbl_1_0, 
                              		"com.jaspersoft.studio.data.Sql.SIGNED_DOUBLE");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7108:6: ( (lv_sodate_2_0= RULE_DATE ) )
                    {
                    // InternalSqlParser.g:7108:6: ( (lv_sodate_2_0= RULE_DATE ) )
                    // InternalSqlParser.g:7109:1: (lv_sodate_2_0= RULE_DATE )
                    {
                    // InternalSqlParser.g:7109:1: (lv_sodate_2_0= RULE_DATE )
                    // InternalSqlParser.g:7110:3: lv_sodate_2_0= RULE_DATE
                    {
                    lv_sodate_2_0=(Token)match(input,RULE_DATE,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_sodate_2_0, grammarAccess.getScalarOperandAccess().getSodateDATETerminalRuleCall_2_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getScalarOperandRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"sodate",
                              		lv_sodate_2_0, 
                              		"com.jaspersoft.studio.data.Sql.DATE");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:7127:6: ( (lv_sotime_3_0= RULE_TIME ) )
                    {
                    // InternalSqlParser.g:7127:6: ( (lv_sotime_3_0= RULE_TIME ) )
                    // InternalSqlParser.g:7128:1: (lv_sotime_3_0= RULE_TIME )
                    {
                    // InternalSqlParser.g:7128:1: (lv_sotime_3_0= RULE_TIME )
                    // InternalSqlParser.g:7129:3: lv_sotime_3_0= RULE_TIME
                    {
                    lv_sotime_3_0=(Token)match(input,RULE_TIME,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_sotime_3_0, grammarAccess.getScalarOperandAccess().getSotimeTIMETerminalRuleCall_3_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getScalarOperandRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"sotime",
                              		lv_sotime_3_0, 
                              		"com.jaspersoft.studio.data.Sql.TIME");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:7146:6: ( (lv_sodt_4_0= RULE_TIMESTAMP ) )
                    {
                    // InternalSqlParser.g:7146:6: ( (lv_sodt_4_0= RULE_TIMESTAMP ) )
                    // InternalSqlParser.g:7147:1: (lv_sodt_4_0= RULE_TIMESTAMP )
                    {
                    // InternalSqlParser.g:7147:1: (lv_sodt_4_0= RULE_TIMESTAMP )
                    // InternalSqlParser.g:7148:3: lv_sodt_4_0= RULE_TIMESTAMP
                    {
                    lv_sodt_4_0=(Token)match(input,RULE_TIMESTAMP,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_sodt_4_0, grammarAccess.getScalarOperandAccess().getSodtTIMESTAMPTerminalRuleCall_4_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getScalarOperandRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"sodt",
                              		lv_sodt_4_0, 
                              		"com.jaspersoft.studio.data.Sql.TIMESTAMP");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleScalarOperand"


    // $ANTLR start "entryRuleScalarNumberOperand"
    // InternalSqlParser.g:7172:1: entryRuleScalarNumberOperand returns [EObject current=null] : iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF ;
    public final EObject entryRuleScalarNumberOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarNumberOperand = null;


        try {
            // InternalSqlParser.g:7173:2: (iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF )
            // InternalSqlParser.g:7174:2: iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScalarNumberOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScalarNumberOperand=ruleScalarNumberOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScalarNumberOperand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleScalarNumberOperand"


    // $ANTLR start "ruleScalarNumberOperand"
    // InternalSqlParser.g:7181:1: ruleScalarNumberOperand returns [EObject current=null] : ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) ) ;
    public final EObject ruleScalarNumberOperand() throws RecognitionException {
        EObject current = null;

        Token lv_soUInt_0_0=null;
        Token lv_soint_1_0=null;
        Token lv_sodbl_2_0=null;
        AntlrDatatypeRuleToken lv_sostr_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:7184:28: ( ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) ) )
            // InternalSqlParser.g:7185:1: ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) )
            {
            // InternalSqlParser.g:7185:1: ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) )
            int alt131=4;
            switch ( input.LA(1) ) {
            case RULE_UNSIGNED:
                {
                alt131=1;
                }
                break;
            case RULE_INT:
                {
                alt131=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt131=3;
                }
                break;
            case RULE_STRING_:
                {
                alt131=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 131, 0, input);

                throw nvae;
            }

            switch (alt131) {
                case 1 :
                    // InternalSqlParser.g:7185:2: ( (lv_soUInt_0_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:7185:2: ( (lv_soUInt_0_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:7186:1: (lv_soUInt_0_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:7186:1: (lv_soUInt_0_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:7187:3: lv_soUInt_0_0= RULE_UNSIGNED
                    {
                    lv_soUInt_0_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_soUInt_0_0, grammarAccess.getScalarNumberOperandAccess().getSoUIntUNSIGNEDTerminalRuleCall_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getScalarNumberOperandRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"soUInt",
                              		lv_soUInt_0_0, 
                              		"com.jaspersoft.studio.data.Sql.UNSIGNED");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7204:6: ( (lv_soint_1_0= RULE_INT ) )
                    {
                    // InternalSqlParser.g:7204:6: ( (lv_soint_1_0= RULE_INT ) )
                    // InternalSqlParser.g:7205:1: (lv_soint_1_0= RULE_INT )
                    {
                    // InternalSqlParser.g:7205:1: (lv_soint_1_0= RULE_INT )
                    // InternalSqlParser.g:7206:3: lv_soint_1_0= RULE_INT
                    {
                    lv_soint_1_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_soint_1_0, grammarAccess.getScalarNumberOperandAccess().getSointINTTerminalRuleCall_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getScalarNumberOperandRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"soint",
                              		lv_soint_1_0, 
                              		"com.jaspersoft.studio.data.Sql.INT");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7223:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // InternalSqlParser.g:7223:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    // InternalSqlParser.g:7224:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    {
                    // InternalSqlParser.g:7224:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    // InternalSqlParser.g:7225:3: lv_sodbl_2_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_2_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_sodbl_2_0, grammarAccess.getScalarNumberOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_2_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getScalarNumberOperandRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"sodbl",
                              		lv_sodbl_2_0, 
                              		"com.jaspersoft.studio.data.Sql.SIGNED_DOUBLE");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:7242:6: ( (lv_sostr_3_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:7242:6: ( (lv_sostr_3_0= ruleStringOperand ) )
                    // InternalSqlParser.g:7243:1: (lv_sostr_3_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:7243:1: (lv_sostr_3_0= ruleStringOperand )
                    // InternalSqlParser.g:7244:3: lv_sostr_3_0= ruleStringOperand
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScalarNumberOperandAccess().getSostrStringOperandParserRuleCall_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_sostr_3_0=ruleStringOperand();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScalarNumberOperandRule());
                      	        }
                             		set(
                             			current, 
                             			"sostr",
                              		lv_sostr_3_0, 
                              		"com.jaspersoft.studio.data.Sql.StringOperand");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleScalarNumberOperand"


    // $ANTLR start "entryRuleSQLCASE"
    // InternalSqlParser.g:7268:1: entryRuleSQLCASE returns [EObject current=null] : iv_ruleSQLCASE= ruleSQLCASE EOF ;
    public final EObject entryRuleSQLCASE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCASE = null;


        try {
            // InternalSqlParser.g:7269:2: (iv_ruleSQLCASE= ruleSQLCASE EOF )
            // InternalSqlParser.g:7270:2: iv_ruleSQLCASE= ruleSQLCASE EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSQLCASERule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSQLCASE=ruleSQLCASE();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSQLCASE; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSQLCASE"


    // $ANTLR start "ruleSQLCASE"
    // InternalSqlParser.g:7277:1: ruleSQLCASE returns [EObject current=null] : (otherlv_0= CASE ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )? ( (lv_when_3_0= ruleSQLCaseWhens ) ) otherlv_4= END ) ;
    public final EObject ruleSQLCASE() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_4=null;
        EObject lv_wop_1_0 = null;

        EObject lv_expr_2_0 = null;

        EObject lv_when_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:7280:28: ( (otherlv_0= CASE ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )? ( (lv_when_3_0= ruleSQLCaseWhens ) ) otherlv_4= END ) )
            // InternalSqlParser.g:7281:1: (otherlv_0= CASE ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )? ( (lv_when_3_0= ruleSQLCaseWhens ) ) otherlv_4= END )
            {
            // InternalSqlParser.g:7281:1: (otherlv_0= CASE ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )? ( (lv_when_3_0= ruleSQLCaseWhens ) ) otherlv_4= END )
            // InternalSqlParser.g:7282:2: otherlv_0= CASE ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )? ( (lv_when_3_0= ruleSQLCaseWhens ) ) otherlv_4= END
            {
            otherlv_0=(Token)match(input,CASE,FOLLOW_95); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSQLCASEAccess().getCASEKeyword_0());
                  
            }
            // InternalSqlParser.g:7286:1: ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )?
            int alt132=3;
            alt132 = dfa132.predict(input);
            switch (alt132) {
                case 1 :
                    // InternalSqlParser.g:7286:2: ( (lv_wop_1_0= ruleOperandGroup ) )
                    {
                    // InternalSqlParser.g:7286:2: ( (lv_wop_1_0= ruleOperandGroup ) )
                    // InternalSqlParser.g:7287:1: (lv_wop_1_0= ruleOperandGroup )
                    {
                    // InternalSqlParser.g:7287:1: (lv_wop_1_0= ruleOperandGroup )
                    // InternalSqlParser.g:7288:3: lv_wop_1_0= ruleOperandGroup
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWopOperandGroupParserRuleCall_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_95);
                    lv_wop_1_0=ruleOperandGroup();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSQLCASERule());
                      	        }
                             		set(
                             			current, 
                             			"wop",
                              		lv_wop_1_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandGroup");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7305:6: ( (lv_expr_2_0= ruleFullExpression ) )
                    {
                    // InternalSqlParser.g:7305:6: ( (lv_expr_2_0= ruleFullExpression ) )
                    // InternalSqlParser.g:7306:1: (lv_expr_2_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:7306:1: (lv_expr_2_0= ruleFullExpression )
                    // InternalSqlParser.g:7307:3: lv_expr_2_0= ruleFullExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSQLCASEAccess().getExprFullExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_95);
                    lv_expr_2_0=ruleFullExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSQLCASERule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_2_0, 
                              		"com.jaspersoft.studio.data.Sql.FullExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:7323:4: ( (lv_when_3_0= ruleSQLCaseWhens ) )
            // InternalSqlParser.g:7324:1: (lv_when_3_0= ruleSQLCaseWhens )
            {
            // InternalSqlParser.g:7324:1: (lv_when_3_0= ruleSQLCaseWhens )
            // InternalSqlParser.g:7325:3: lv_when_3_0= ruleSQLCaseWhens
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWhenSQLCaseWhensParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_96);
            lv_when_3_0=ruleSQLCaseWhens();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSQLCASERule());
              	        }
                     		set(
                     			current, 
                     			"when",
                      		lv_when_3_0, 
                      		"com.jaspersoft.studio.data.Sql.SQLCaseWhens");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,END,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSQLCASEAccess().getENDKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSQLCASE"


    // $ANTLR start "entryRuleSQLCaseWhens"
    // InternalSqlParser.g:7354:1: entryRuleSQLCaseWhens returns [EObject current=null] : iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF ;
    public final EObject entryRuleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCaseWhens = null;


        try {
            // InternalSqlParser.g:7355:2: (iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF )
            // InternalSqlParser.g:7356:2: iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSQLCaseWhensRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSQLCaseWhens=ruleSQLCaseWhens();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSQLCaseWhens; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSQLCaseWhens"


    // $ANTLR start "ruleSQLCaseWhens"
    // InternalSqlParser.g:7363:1: ruleSQLCaseWhens returns [EObject current=null] : (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) ;
    public final EObject ruleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject this_SqlCaseWhen_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:7366:28: ( (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) )
            // InternalSqlParser.g:7367:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            {
            // InternalSqlParser.g:7367:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            // InternalSqlParser.g:7368:2: this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getSqlCaseWhenParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_97);
            this_SqlCaseWhen_0=ruleSqlCaseWhen();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_SqlCaseWhen_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalSqlParser.g:7379:1: ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==WHEN) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // InternalSqlParser.g:7379:2: () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    {
                    // InternalSqlParser.g:7379:2: ()
                    // InternalSqlParser.g:7380:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getSQLCaseWhensAccess().getWhenListEntriesAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalSqlParser.g:7388:2: ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    int cnt133=0;
                    loop133:
                    do {
                        int alt133=2;
                        int LA133_0 = input.LA(1);

                        if ( (LA133_0==WHEN) ) {
                            alt133=1;
                        }


                        switch (alt133) {
                    	case 1 :
                    	    // InternalSqlParser.g:7389:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    {
                    	    // InternalSqlParser.g:7389:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    // InternalSqlParser.g:7390:3: lv_entries_2_0= ruleSqlCaseWhen
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getEntriesSqlCaseWhenParserRuleCall_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_97);
                    	    lv_entries_2_0=ruleSqlCaseWhen();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getSQLCaseWhensRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"entries",
                    	              		lv_entries_2_0, 
                    	              		"com.jaspersoft.studio.data.Sql.SqlCaseWhen");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt133 >= 1 ) break loop133;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(133, input);
                                throw eee;
                        }
                        cnt133++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSQLCaseWhens"


    // $ANTLR start "entryRuleSqlCaseWhen"
    // InternalSqlParser.g:7414:1: entryRuleSqlCaseWhen returns [EObject current=null] : iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF ;
    public final EObject entryRuleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSqlCaseWhen = null;


        try {
            // InternalSqlParser.g:7415:2: (iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF )
            // InternalSqlParser.g:7416:2: iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSqlCaseWhenRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSqlCaseWhen=ruleSqlCaseWhen();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSqlCaseWhen; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSqlCaseWhen"


    // $ANTLR start "ruleSqlCaseWhen"
    // InternalSqlParser.g:7423:1: ruleSqlCaseWhen returns [EObject current=null] : (otherlv_0= WHEN ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) ) otherlv_3= THEN ( (lv_texp_4_0= ruleOperandGroup ) ) (otherlv_5= ELSE ( (lv_eexp_6_0= ruleOperandGroup ) ) )? ) ;
    public final EObject ruleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_wop_1_0 = null;

        EObject lv_expr_2_0 = null;

        EObject lv_texp_4_0 = null;

        EObject lv_eexp_6_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:7426:28: ( (otherlv_0= WHEN ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) ) otherlv_3= THEN ( (lv_texp_4_0= ruleOperandGroup ) ) (otherlv_5= ELSE ( (lv_eexp_6_0= ruleOperandGroup ) ) )? ) )
            // InternalSqlParser.g:7427:1: (otherlv_0= WHEN ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) ) otherlv_3= THEN ( (lv_texp_4_0= ruleOperandGroup ) ) (otherlv_5= ELSE ( (lv_eexp_6_0= ruleOperandGroup ) ) )? )
            {
            // InternalSqlParser.g:7427:1: (otherlv_0= WHEN ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) ) otherlv_3= THEN ( (lv_texp_4_0= ruleOperandGroup ) ) (otherlv_5= ELSE ( (lv_eexp_6_0= ruleOperandGroup ) ) )? )
            // InternalSqlParser.g:7428:2: otherlv_0= WHEN ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) ) otherlv_3= THEN ( (lv_texp_4_0= ruleOperandGroup ) ) (otherlv_5= ELSE ( (lv_eexp_6_0= ruleOperandGroup ) ) )?
            {
            otherlv_0=(Token)match(input,WHEN,FOLLOW_23); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSqlCaseWhenAccess().getWHENKeyword_0());
                  
            }
            // InternalSqlParser.g:7432:1: ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )
            int alt135=2;
            alt135 = dfa135.predict(input);
            switch (alt135) {
                case 1 :
                    // InternalSqlParser.g:7432:2: ( (lv_wop_1_0= ruleOperandGroup ) )
                    {
                    // InternalSqlParser.g:7432:2: ( (lv_wop_1_0= ruleOperandGroup ) )
                    // InternalSqlParser.g:7433:1: (lv_wop_1_0= ruleOperandGroup )
                    {
                    // InternalSqlParser.g:7433:1: (lv_wop_1_0= ruleOperandGroup )
                    // InternalSqlParser.g:7434:3: lv_wop_1_0= ruleOperandGroup
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getWopOperandGroupParserRuleCall_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_98);
                    lv_wop_1_0=ruleOperandGroup();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
                      	        }
                             		set(
                             			current, 
                             			"wop",
                              		lv_wop_1_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandGroup");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7451:6: ( (lv_expr_2_0= ruleFullExpression ) )
                    {
                    // InternalSqlParser.g:7451:6: ( (lv_expr_2_0= ruleFullExpression ) )
                    // InternalSqlParser.g:7452:1: (lv_expr_2_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:7452:1: (lv_expr_2_0= ruleFullExpression )
                    // InternalSqlParser.g:7453:3: lv_expr_2_0= ruleFullExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getExprFullExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_98);
                    lv_expr_2_0=ruleFullExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_2_0, 
                              		"com.jaspersoft.studio.data.Sql.FullExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,THEN,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getSqlCaseWhenAccess().getTHENKeyword_2());
                  
            }
            // InternalSqlParser.g:7474:1: ( (lv_texp_4_0= ruleOperandGroup ) )
            // InternalSqlParser.g:7475:1: (lv_texp_4_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:7475:1: (lv_texp_4_0= ruleOperandGroup )
            // InternalSqlParser.g:7476:3: lv_texp_4_0= ruleOperandGroup
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getTexpOperandGroupParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_99);
            lv_texp_4_0=ruleOperandGroup();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
              	        }
                     		set(
                     			current, 
                     			"texp",
                      		lv_texp_4_0, 
                      		"com.jaspersoft.studio.data.Sql.OperandGroup");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalSqlParser.g:7492:2: (otherlv_5= ELSE ( (lv_eexp_6_0= ruleOperandGroup ) ) )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==ELSE) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // InternalSqlParser.g:7493:2: otherlv_5= ELSE ( (lv_eexp_6_0= ruleOperandGroup ) )
                    {
                    otherlv_5=(Token)match(input,ELSE,FOLLOW_60); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getSqlCaseWhenAccess().getELSEKeyword_4_0());
                          
                    }
                    // InternalSqlParser.g:7497:1: ( (lv_eexp_6_0= ruleOperandGroup ) )
                    // InternalSqlParser.g:7498:1: (lv_eexp_6_0= ruleOperandGroup )
                    {
                    // InternalSqlParser.g:7498:1: (lv_eexp_6_0= ruleOperandGroup )
                    // InternalSqlParser.g:7499:3: lv_eexp_6_0= ruleOperandGroup
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getEexpOperandGroupParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_eexp_6_0=ruleOperandGroup();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
                      	        }
                             		set(
                             			current, 
                             			"eexp",
                              		lv_eexp_6_0, 
                              		"com.jaspersoft.studio.data.Sql.OperandGroup");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSqlCaseWhen"


    // $ANTLR start "entryRuleJoinType"
    // InternalSqlParser.g:7523:1: entryRuleJoinType returns [String current=null] : iv_ruleJoinType= ruleJoinType EOF ;
    public final String entryRuleJoinType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleJoinType = null;


        try {
            // InternalSqlParser.g:7524:1: (iv_ruleJoinType= ruleJoinType EOF )
            // InternalSqlParser.g:7525:2: iv_ruleJoinType= ruleJoinType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJoinTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJoinType=ruleJoinType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJoinType.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJoinType"


    // $ANTLR start "ruleJoinType"
    // InternalSqlParser.g:7532:1: ruleJoinType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN ) ;
    public final AntlrDatatypeRuleToken ruleJoinType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7536:6: ( ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN ) )
            // InternalSqlParser.g:7537:1: ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN )
            {
            // InternalSqlParser.g:7537:1: ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN )
            // InternalSqlParser.g:7537:2: (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN
            {
            // InternalSqlParser.g:7537:2: (kw= NATURAL )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==NATURAL) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // InternalSqlParser.g:7538:2: kw= NATURAL
                    {
                    kw=(Token)match(input,NATURAL,FOLLOW_100); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getJoinTypeAccess().getNATURALKeyword_0()); 
                          
                    }

                    }
                    break;

            }

            // InternalSqlParser.g:7543:3: (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )?
            int alt140=5;
            switch ( input.LA(1) ) {
                case INNER:
                    {
                    alt140=1;
                    }
                    break;
                case RIGHT:
                case FULL:
                case LEFT:
                    {
                    alt140=2;
                    }
                    break;
                case CROSS:
                    {
                    alt140=3;
                    }
                    break;
                case STRAIGHT_JOIN:
                    {
                    alt140=4;
                    }
                    break;
            }

            switch (alt140) {
                case 1 :
                    // InternalSqlParser.g:7544:2: kw= INNER
                    {
                    kw=(Token)match(input,INNER,FOLLOW_101); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getJoinTypeAccess().getINNERKeyword_1_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7550:6: ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? )
                    {
                    // InternalSqlParser.g:7550:6: ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? )
                    // InternalSqlParser.g:7550:7: (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )?
                    {
                    // InternalSqlParser.g:7550:7: (kw= LEFT | kw= RIGHT | kw= FULL )
                    int alt138=3;
                    switch ( input.LA(1) ) {
                    case LEFT:
                        {
                        alt138=1;
                        }
                        break;
                    case RIGHT:
                        {
                        alt138=2;
                        }
                        break;
                    case FULL:
                        {
                        alt138=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 138, 0, input);

                        throw nvae;
                    }

                    switch (alt138) {
                        case 1 :
                            // InternalSqlParser.g:7551:2: kw= LEFT
                            {
                            kw=(Token)match(input,LEFT,FOLLOW_102); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getJoinTypeAccess().getLEFTKeyword_1_1_0_0()); 
                                  
                            }

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:7558:2: kw= RIGHT
                            {
                            kw=(Token)match(input,RIGHT,FOLLOW_102); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getJoinTypeAccess().getRIGHTKeyword_1_1_0_1()); 
                                  
                            }

                            }
                            break;
                        case 3 :
                            // InternalSqlParser.g:7565:2: kw= FULL
                            {
                            kw=(Token)match(input,FULL,FOLLOW_102); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getJoinTypeAccess().getFULLKeyword_1_1_0_2()); 
                                  
                            }

                            }
                            break;

                    }

                    // InternalSqlParser.g:7570:2: (kw= OUTER )?
                    int alt139=2;
                    int LA139_0 = input.LA(1);

                    if ( (LA139_0==OUTER) ) {
                        alt139=1;
                    }
                    switch (alt139) {
                        case 1 :
                            // InternalSqlParser.g:7571:2: kw= OUTER
                            {
                            kw=(Token)match(input,OUTER,FOLLOW_101); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getJoinTypeAccess().getOUTERKeyword_1_1_1()); 
                                  
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7578:2: kw= CROSS
                    {
                    kw=(Token)match(input,CROSS,FOLLOW_101); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getJoinTypeAccess().getCROSSKeyword_1_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:7585:2: kw= STRAIGHT_JOIN
                    {
                    kw=(Token)match(input,STRAIGHT_JOIN,FOLLOW_101); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getJoinTypeAccess().getSTRAIGHT_JOINKeyword_1_3()); 
                          
                    }

                    }
                    break;

            }

            kw=(Token)match(input,JOIN,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getJoinTypeAccess().getJOINKeyword_2()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJoinType"


    // $ANTLR start "entryRuleDBID"
    // InternalSqlParser.g:7604:1: entryRuleDBID returns [String current=null] : iv_ruleDBID= ruleDBID EOF ;
    public final String entryRuleDBID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDBID = null;


        try {
            // InternalSqlParser.g:7605:1: (iv_ruleDBID= ruleDBID EOF )
            // InternalSqlParser.g:7606:2: iv_ruleDBID= ruleDBID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDBIDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleDBID=ruleDBID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDBID.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDBID"


    // $ANTLR start "ruleDBID"
    // InternalSqlParser.g:7613:1: ruleDBID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDBID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_DBNAME_1=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7617:6: ( (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) )
            // InternalSqlParser.g:7618:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            {
            // InternalSqlParser.g:7618:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            int alt141=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt141=1;
                }
                break;
            case RULE_DBNAME:
                {
                alt141=2;
                }
                break;
            case RULE_STRING:
                {
                alt141=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 141, 0, input);

                throw nvae;
            }

            switch (alt141) {
                case 1 :
                    // InternalSqlParser.g:7618:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_ID_0);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_ID_0, grammarAccess.getDBIDAccess().getIDTerminalRuleCall_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7626:10: this_DBNAME_1= RULE_DBNAME
                    {
                    this_DBNAME_1=(Token)match(input,RULE_DBNAME,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_DBNAME_1);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_DBNAME_1, grammarAccess.getDBIDAccess().getDBNAMETerminalRuleCall_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7634:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_STRING_2);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_STRING_2, grammarAccess.getDBIDAccess().getSTRINGTerminalRuleCall_2()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDBID"


    // $ANTLR start "entryRuleStringOperand"
    // InternalSqlParser.g:7649:1: entryRuleStringOperand returns [String current=null] : iv_ruleStringOperand= ruleStringOperand EOF ;
    public final String entryRuleStringOperand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringOperand = null;


        try {
            // InternalSqlParser.g:7650:1: (iv_ruleStringOperand= ruleStringOperand EOF )
            // InternalSqlParser.g:7651:2: iv_ruleStringOperand= ruleStringOperand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringOperandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleStringOperand=ruleStringOperand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringOperand.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringOperand"


    // $ANTLR start "ruleStringOperand"
    // InternalSqlParser.g:7658:1: ruleStringOperand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING__0= RULE_STRING_ ;
    public final AntlrDatatypeRuleToken ruleStringOperand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING__0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7662:6: (this_STRING__0= RULE_STRING_ )
            // InternalSqlParser.g:7663:5: this_STRING__0= RULE_STRING_
            {
            this_STRING__0=(Token)match(input,RULE_STRING_,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_STRING__0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_STRING__0, grammarAccess.getStringOperandAccess().getSTRING_TerminalRuleCall()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringOperand"


    // $ANTLR start "entryRuleFNAME"
    // InternalSqlParser.g:7678:1: entryRuleFNAME returns [String current=null] : iv_ruleFNAME= ruleFNAME EOF ;
    public final String entryRuleFNAME() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFNAME = null;


        try {
            // InternalSqlParser.g:7679:1: (iv_ruleFNAME= ruleFNAME EOF )
            // InternalSqlParser.g:7680:2: iv_ruleFNAME= ruleFNAME EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFNAMERule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFNAME=ruleFNAME();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFNAME.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFNAME"


    // $ANTLR start "ruleFNAME"
    // InternalSqlParser.g:7687:1: ruleFNAME returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= LeftParenthesis ) ;
    public final AntlrDatatypeRuleToken ruleFNAME() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7691:6: ( (this_ID_0= RULE_ID kw= LeftParenthesis ) )
            // InternalSqlParser.g:7692:1: (this_ID_0= RULE_ID kw= LeftParenthesis )
            {
            // InternalSqlParser.g:7692:1: (this_ID_0= RULE_ID kw= LeftParenthesis )
            // InternalSqlParser.g:7692:6: this_ID_0= RULE_ID kw= LeftParenthesis
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getFNAMEAccess().getIDTerminalRuleCall_0()); 
                  
            }
            kw=(Token)match(input,LeftParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getFNAMEAccess().getLeftParenthesisKeyword_1()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFNAME"


    // $ANTLR start "entryRuleUnsignedValue"
    // InternalSqlParser.g:7715:1: entryRuleUnsignedValue returns [EObject current=null] : iv_ruleUnsignedValue= ruleUnsignedValue EOF ;
    public final EObject entryRuleUnsignedValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnsignedValue = null;


        try {
            // InternalSqlParser.g:7716:2: (iv_ruleUnsignedValue= ruleUnsignedValue EOF )
            // InternalSqlParser.g:7717:2: iv_ruleUnsignedValue= ruleUnsignedValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnsignedValue=ruleUnsignedValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedValue; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnsignedValue"


    // $ANTLR start "ruleUnsignedValue"
    // InternalSqlParser.g:7724:1: ruleUnsignedValue returns [EObject current=null] : ( (lv_integer_0_0= RULE_UNSIGNED ) ) ;
    public final EObject ruleUnsignedValue() throws RecognitionException {
        EObject current = null;

        Token lv_integer_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7727:28: ( ( (lv_integer_0_0= RULE_UNSIGNED ) ) )
            // InternalSqlParser.g:7728:1: ( (lv_integer_0_0= RULE_UNSIGNED ) )
            {
            // InternalSqlParser.g:7728:1: ( (lv_integer_0_0= RULE_UNSIGNED ) )
            // InternalSqlParser.g:7729:1: (lv_integer_0_0= RULE_UNSIGNED )
            {
            // InternalSqlParser.g:7729:1: (lv_integer_0_0= RULE_UNSIGNED )
            // InternalSqlParser.g:7730:3: lv_integer_0_0= RULE_UNSIGNED
            {
            lv_integer_0_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_integer_0_0, grammarAccess.getUnsignedValueAccess().getIntegerUNSIGNEDTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getUnsignedValueRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"integer",
                      		lv_integer_0_0, 
                      		"com.jaspersoft.studio.data.Sql.UNSIGNED");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnsignedValue"


    // $ANTLR start "ruleXFunction"
    // InternalSqlParser.g:7754:1: ruleXFunction returns [Enumerator current=null] : ( (enumLiteral_0= IN_1 ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= LESS_1 ) | (enumLiteral_6= GREATER_1 ) | (enumLiteral_7= GREATER ) | (enumLiteral_8= BETWEEN_1 ) | (enumLiteral_9= BETWEEN_4 ) | (enumLiteral_10= BETWEEN_3 ) | (enumLiteral_11= BETWEEN_2 ) ) ;
    public final Enumerator ruleXFunction() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;
        Token enumLiteral_11=null;

         enterRule(); 
        try {
            // InternalSqlParser.g:7756:28: ( ( (enumLiteral_0= IN_1 ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= LESS_1 ) | (enumLiteral_6= GREATER_1 ) | (enumLiteral_7= GREATER ) | (enumLiteral_8= BETWEEN_1 ) | (enumLiteral_9= BETWEEN_4 ) | (enumLiteral_10= BETWEEN_3 ) | (enumLiteral_11= BETWEEN_2 ) ) )
            // InternalSqlParser.g:7757:1: ( (enumLiteral_0= IN_1 ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= LESS_1 ) | (enumLiteral_6= GREATER_1 ) | (enumLiteral_7= GREATER ) | (enumLiteral_8= BETWEEN_1 ) | (enumLiteral_9= BETWEEN_4 ) | (enumLiteral_10= BETWEEN_3 ) | (enumLiteral_11= BETWEEN_2 ) )
            {
            // InternalSqlParser.g:7757:1: ( (enumLiteral_0= IN_1 ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= LESS_1 ) | (enumLiteral_6= GREATER_1 ) | (enumLiteral_7= GREATER ) | (enumLiteral_8= BETWEEN_1 ) | (enumLiteral_9= BETWEEN_4 ) | (enumLiteral_10= BETWEEN_3 ) | (enumLiteral_11= BETWEEN_2 ) )
            int alt142=12;
            switch ( input.LA(1) ) {
            case IN_1:
                {
                alt142=1;
                }
                break;
            case NOTIN:
                {
                alt142=2;
                }
                break;
            case EQUAL:
                {
                alt142=3;
                }
                break;
            case NOTEQUAL:
                {
                alt142=4;
                }
                break;
            case LESS:
                {
                alt142=5;
                }
                break;
            case LESS_1:
                {
                alt142=6;
                }
                break;
            case GREATER_1:
                {
                alt142=7;
                }
                break;
            case GREATER:
                {
                alt142=8;
                }
                break;
            case BETWEEN_1:
                {
                alt142=9;
                }
                break;
            case BETWEEN_4:
                {
                alt142=10;
                }
                break;
            case BETWEEN_3:
                {
                alt142=11;
                }
                break;
            case BETWEEN_2:
                {
                alt142=12;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 142, 0, input);

                throw nvae;
            }

            switch (alt142) {
                case 1 :
                    // InternalSqlParser.g:7757:2: (enumLiteral_0= IN_1 )
                    {
                    // InternalSqlParser.g:7757:2: (enumLiteral_0= IN_1 )
                    // InternalSqlParser.g:7757:7: enumLiteral_0= IN_1
                    {
                    enumLiteral_0=(Token)match(input,IN_1,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7763:6: (enumLiteral_1= NOTIN )
                    {
                    // InternalSqlParser.g:7763:6: (enumLiteral_1= NOTIN )
                    // InternalSqlParser.g:7763:11: enumLiteral_1= NOTIN
                    {
                    enumLiteral_1=(Token)match(input,NOTIN,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_1, grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7769:6: (enumLiteral_2= EQUAL )
                    {
                    // InternalSqlParser.g:7769:6: (enumLiteral_2= EQUAL )
                    // InternalSqlParser.g:7769:11: enumLiteral_2= EQUAL
                    {
                    enumLiteral_2=(Token)match(input,EQUAL,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_2, grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                          
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:7775:6: (enumLiteral_3= NOTEQUAL )
                    {
                    // InternalSqlParser.g:7775:6: (enumLiteral_3= NOTEQUAL )
                    // InternalSqlParser.g:7775:11: enumLiteral_3= NOTEQUAL
                    {
                    enumLiteral_3=(Token)match(input,NOTEQUAL,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_3, grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                          
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:7781:6: (enumLiteral_4= LESS )
                    {
                    // InternalSqlParser.g:7781:6: (enumLiteral_4= LESS )
                    // InternalSqlParser.g:7781:11: enumLiteral_4= LESS
                    {
                    enumLiteral_4=(Token)match(input,LESS,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_4, grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                          
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:7787:6: (enumLiteral_5= LESS_1 )
                    {
                    // InternalSqlParser.g:7787:6: (enumLiteral_5= LESS_1 )
                    // InternalSqlParser.g:7787:11: enumLiteral_5= LESS_1
                    {
                    enumLiteral_5=(Token)match(input,LESS_1,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_5, grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_5()); 
                          
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:7793:6: (enumLiteral_6= GREATER_1 )
                    {
                    // InternalSqlParser.g:7793:6: (enumLiteral_6= GREATER_1 )
                    // InternalSqlParser.g:7793:11: enumLiteral_6= GREATER_1
                    {
                    enumLiteral_6=(Token)match(input,GREATER_1,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_6, grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_6()); 
                          
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:7799:6: (enumLiteral_7= GREATER )
                    {
                    // InternalSqlParser.g:7799:6: (enumLiteral_7= GREATER )
                    // InternalSqlParser.g:7799:11: enumLiteral_7= GREATER
                    {
                    enumLiteral_7=(Token)match(input,GREATER,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_7, grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_7()); 
                          
                    }

                    }


                    }
                    break;
                case 9 :
                    // InternalSqlParser.g:7805:6: (enumLiteral_8= BETWEEN_1 )
                    {
                    // InternalSqlParser.g:7805:6: (enumLiteral_8= BETWEEN_1 )
                    // InternalSqlParser.g:7805:11: enumLiteral_8= BETWEEN_1
                    {
                    enumLiteral_8=(Token)match(input,BETWEEN_1,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_8, grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                          
                    }

                    }


                    }
                    break;
                case 10 :
                    // InternalSqlParser.g:7811:6: (enumLiteral_9= BETWEEN_4 )
                    {
                    // InternalSqlParser.g:7811:6: (enumLiteral_9= BETWEEN_4 )
                    // InternalSqlParser.g:7811:11: enumLiteral_9= BETWEEN_4
                    {
                    enumLiteral_9=(Token)match(input,BETWEEN_4,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_9, grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                          
                    }

                    }


                    }
                    break;
                case 11 :
                    // InternalSqlParser.g:7817:6: (enumLiteral_10= BETWEEN_3 )
                    {
                    // InternalSqlParser.g:7817:6: (enumLiteral_10= BETWEEN_3 )
                    // InternalSqlParser.g:7817:11: enumLiteral_10= BETWEEN_3
                    {
                    enumLiteral_10=(Token)match(input,BETWEEN_3,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_10, grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                          
                    }

                    }


                    }
                    break;
                case 12 :
                    // InternalSqlParser.g:7823:6: (enumLiteral_11= BETWEEN_2 )
                    {
                    // InternalSqlParser.g:7823:6: (enumLiteral_11= BETWEEN_2 )
                    // InternalSqlParser.g:7823:11: enumLiteral_11= BETWEEN_2
                    {
                    enumLiteral_11=(Token)match(input,BETWEEN_2,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getXFunctionAccess().getXbwnrEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_11, grammarAccess.getXFunctionAccess().getXbwnrEnumLiteralDeclaration_11()); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFunction"


    // $ANTLR start "ruleEXTRACT_VALUES"
    // InternalSqlParser.g:7833:1: ruleEXTRACT_VALUES returns [Enumerator current=null] : ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) ) ;
    public final Enumerator ruleEXTRACT_VALUES() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;
        Token enumLiteral_11=null;
        Token enumLiteral_12=null;
        Token enumLiteral_13=null;
        Token enumLiteral_14=null;
        Token enumLiteral_15=null;
        Token enumLiteral_16=null;
        Token enumLiteral_17=null;
        Token enumLiteral_18=null;
        Token enumLiteral_19=null;

         enterRule(); 
        try {
            // InternalSqlParser.g:7835:28: ( ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) ) )
            // InternalSqlParser.g:7836:1: ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) )
            {
            // InternalSqlParser.g:7836:1: ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) )
            int alt143=20;
            switch ( input.LA(1) ) {
            case MICROSECOND:
                {
                alt143=1;
                }
                break;
            case SECOND:
                {
                alt143=2;
                }
                break;
            case MINUTE:
                {
                alt143=3;
                }
                break;
            case HOUR:
                {
                alt143=4;
                }
                break;
            case DAY:
                {
                alt143=5;
                }
                break;
            case WEEK:
                {
                alt143=6;
                }
                break;
            case MONTH:
                {
                alt143=7;
                }
                break;
            case QUARTER:
                {
                alt143=8;
                }
                break;
            case YEAR:
                {
                alt143=9;
                }
                break;
            case SECOND_MICROSECOND:
                {
                alt143=10;
                }
                break;
            case MINUTE_MICROSECOND:
                {
                alt143=11;
                }
                break;
            case MINUTE_SECOND:
                {
                alt143=12;
                }
                break;
            case HOUR_MICROSECOND:
                {
                alt143=13;
                }
                break;
            case HOUR_SECOND:
                {
                alt143=14;
                }
                break;
            case HOUR_MINUTE:
                {
                alt143=15;
                }
                break;
            case DAY_MICROSECOND:
                {
                alt143=16;
                }
                break;
            case DAY_SECOND:
                {
                alt143=17;
                }
                break;
            case DAY_MINUTE:
                {
                alt143=18;
                }
                break;
            case DAY_HOUR:
                {
                alt143=19;
                }
                break;
            case YEAR_MONTH:
                {
                alt143=20;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 143, 0, input);

                throw nvae;
            }

            switch (alt143) {
                case 1 :
                    // InternalSqlParser.g:7836:2: (enumLiteral_0= MICROSECOND )
                    {
                    // InternalSqlParser.g:7836:2: (enumLiteral_0= MICROSECOND )
                    // InternalSqlParser.g:7836:7: enumLiteral_0= MICROSECOND
                    {
                    enumLiteral_0=(Token)match(input,MICROSECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getMsEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getEXTRACT_VALUESAccess().getMsEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7842:6: (enumLiteral_1= SECOND )
                    {
                    // InternalSqlParser.g:7842:6: (enumLiteral_1= SECOND )
                    // InternalSqlParser.g:7842:11: enumLiteral_1= SECOND
                    {
                    enumLiteral_1=(Token)match(input,SECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_1, grammarAccess.getEXTRACT_VALUESAccess().getSEnumLiteralDeclaration_1()); 
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7848:6: (enumLiteral_2= MINUTE )
                    {
                    // InternalSqlParser.g:7848:6: (enumLiteral_2= MINUTE )
                    // InternalSqlParser.g:7848:11: enumLiteral_2= MINUTE
                    {
                    enumLiteral_2=(Token)match(input,MINUTE,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getMEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_2, grammarAccess.getEXTRACT_VALUESAccess().getMEnumLiteralDeclaration_2()); 
                          
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:7854:6: (enumLiteral_3= HOUR )
                    {
                    // InternalSqlParser.g:7854:6: (enumLiteral_3= HOUR )
                    // InternalSqlParser.g:7854:11: enumLiteral_3= HOUR
                    {
                    enumLiteral_3=(Token)match(input,HOUR,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getHEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_3, grammarAccess.getEXTRACT_VALUESAccess().getHEnumLiteralDeclaration_3()); 
                          
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:7860:6: (enumLiteral_4= DAY )
                    {
                    // InternalSqlParser.g:7860:6: (enumLiteral_4= DAY )
                    // InternalSqlParser.g:7860:11: enumLiteral_4= DAY
                    {
                    enumLiteral_4=(Token)match(input,DAY,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getDayEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_4, grammarAccess.getEXTRACT_VALUESAccess().getDayEnumLiteralDeclaration_4()); 
                          
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:7866:6: (enumLiteral_5= WEEK )
                    {
                    // InternalSqlParser.g:7866:6: (enumLiteral_5= WEEK )
                    // InternalSqlParser.g:7866:11: enumLiteral_5= WEEK
                    {
                    enumLiteral_5=(Token)match(input,WEEK,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getWeekEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_5, grammarAccess.getEXTRACT_VALUESAccess().getWeekEnumLiteralDeclaration_5()); 
                          
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:7872:6: (enumLiteral_6= MONTH )
                    {
                    // InternalSqlParser.g:7872:6: (enumLiteral_6= MONTH )
                    // InternalSqlParser.g:7872:11: enumLiteral_6= MONTH
                    {
                    enumLiteral_6=(Token)match(input,MONTH,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getMonthEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_6, grammarAccess.getEXTRACT_VALUESAccess().getMonthEnumLiteralDeclaration_6()); 
                          
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:7878:6: (enumLiteral_7= QUARTER )
                    {
                    // InternalSqlParser.g:7878:6: (enumLiteral_7= QUARTER )
                    // InternalSqlParser.g:7878:11: enumLiteral_7= QUARTER
                    {
                    enumLiteral_7=(Token)match(input,QUARTER,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getQuartEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_7, grammarAccess.getEXTRACT_VALUESAccess().getQuartEnumLiteralDeclaration_7()); 
                          
                    }

                    }


                    }
                    break;
                case 9 :
                    // InternalSqlParser.g:7884:6: (enumLiteral_8= YEAR )
                    {
                    // InternalSqlParser.g:7884:6: (enumLiteral_8= YEAR )
                    // InternalSqlParser.g:7884:11: enumLiteral_8= YEAR
                    {
                    enumLiteral_8=(Token)match(input,YEAR,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getYearEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_8, grammarAccess.getEXTRACT_VALUESAccess().getYearEnumLiteralDeclaration_8()); 
                          
                    }

                    }


                    }
                    break;
                case 10 :
                    // InternalSqlParser.g:7890:6: (enumLiteral_9= SECOND_MICROSECOND )
                    {
                    // InternalSqlParser.g:7890:6: (enumLiteral_9= SECOND_MICROSECOND )
                    // InternalSqlParser.g:7890:11: enumLiteral_9= SECOND_MICROSECOND
                    {
                    enumLiteral_9=(Token)match(input,SECOND_MICROSECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getMicrosEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_9, grammarAccess.getEXTRACT_VALUESAccess().getMicrosEnumLiteralDeclaration_9()); 
                          
                    }

                    }


                    }
                    break;
                case 11 :
                    // InternalSqlParser.g:7896:6: (enumLiteral_10= MINUTE_MICROSECOND )
                    {
                    // InternalSqlParser.g:7896:6: (enumLiteral_10= MINUTE_MICROSECOND )
                    // InternalSqlParser.g:7896:11: enumLiteral_10= MINUTE_MICROSECOND
                    {
                    enumLiteral_10=(Token)match(input,MINUTE_MICROSECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getMinMicroEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_10, grammarAccess.getEXTRACT_VALUESAccess().getMinMicroEnumLiteralDeclaration_10()); 
                          
                    }

                    }


                    }
                    break;
                case 12 :
                    // InternalSqlParser.g:7902:6: (enumLiteral_11= MINUTE_SECOND )
                    {
                    // InternalSqlParser.g:7902:6: (enumLiteral_11= MINUTE_SECOND )
                    // InternalSqlParser.g:7902:11: enumLiteral_11= MINUTE_SECOND
                    {
                    enumLiteral_11=(Token)match(input,MINUTE_SECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getMinSecEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_11, grammarAccess.getEXTRACT_VALUESAccess().getMinSecEnumLiteralDeclaration_11()); 
                          
                    }

                    }


                    }
                    break;
                case 13 :
                    // InternalSqlParser.g:7908:6: (enumLiteral_12= HOUR_MICROSECOND )
                    {
                    // InternalSqlParser.g:7908:6: (enumLiteral_12= HOUR_MICROSECOND )
                    // InternalSqlParser.g:7908:11: enumLiteral_12= HOUR_MICROSECOND
                    {
                    enumLiteral_12=(Token)match(input,HOUR_MICROSECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getHmsEnumLiteralDeclaration_12().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_12, grammarAccess.getEXTRACT_VALUESAccess().getHmsEnumLiteralDeclaration_12()); 
                          
                    }

                    }


                    }
                    break;
                case 14 :
                    // InternalSqlParser.g:7914:6: (enumLiteral_13= HOUR_SECOND )
                    {
                    // InternalSqlParser.g:7914:6: (enumLiteral_13= HOUR_SECOND )
                    // InternalSqlParser.g:7914:11: enumLiteral_13= HOUR_SECOND
                    {
                    enumLiteral_13=(Token)match(input,HOUR_SECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getHsEnumLiteralDeclaration_13().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_13, grammarAccess.getEXTRACT_VALUESAccess().getHsEnumLiteralDeclaration_13()); 
                          
                    }

                    }


                    }
                    break;
                case 15 :
                    // InternalSqlParser.g:7920:6: (enumLiteral_14= HOUR_MINUTE )
                    {
                    // InternalSqlParser.g:7920:6: (enumLiteral_14= HOUR_MINUTE )
                    // InternalSqlParser.g:7920:11: enumLiteral_14= HOUR_MINUTE
                    {
                    enumLiteral_14=(Token)match(input,HOUR_MINUTE,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getHminEnumLiteralDeclaration_14().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_14, grammarAccess.getEXTRACT_VALUESAccess().getHminEnumLiteralDeclaration_14()); 
                          
                    }

                    }


                    }
                    break;
                case 16 :
                    // InternalSqlParser.g:7926:6: (enumLiteral_15= DAY_MICROSECOND )
                    {
                    // InternalSqlParser.g:7926:6: (enumLiteral_15= DAY_MICROSECOND )
                    // InternalSqlParser.g:7926:11: enumLiteral_15= DAY_MICROSECOND
                    {
                    enumLiteral_15=(Token)match(input,DAY_MICROSECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getDmsEnumLiteralDeclaration_15().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_15, grammarAccess.getEXTRACT_VALUESAccess().getDmsEnumLiteralDeclaration_15()); 
                          
                    }

                    }


                    }
                    break;
                case 17 :
                    // InternalSqlParser.g:7932:6: (enumLiteral_16= DAY_SECOND )
                    {
                    // InternalSqlParser.g:7932:6: (enumLiteral_16= DAY_SECOND )
                    // InternalSqlParser.g:7932:11: enumLiteral_16= DAY_SECOND
                    {
                    enumLiteral_16=(Token)match(input,DAY_SECOND,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getDsEnumLiteralDeclaration_16().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_16, grammarAccess.getEXTRACT_VALUESAccess().getDsEnumLiteralDeclaration_16()); 
                          
                    }

                    }


                    }
                    break;
                case 18 :
                    // InternalSqlParser.g:7938:6: (enumLiteral_17= DAY_MINUTE )
                    {
                    // InternalSqlParser.g:7938:6: (enumLiteral_17= DAY_MINUTE )
                    // InternalSqlParser.g:7938:11: enumLiteral_17= DAY_MINUTE
                    {
                    enumLiteral_17=(Token)match(input,DAY_MINUTE,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getDayminEnumLiteralDeclaration_17().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_17, grammarAccess.getEXTRACT_VALUESAccess().getDayminEnumLiteralDeclaration_17()); 
                          
                    }

                    }


                    }
                    break;
                case 19 :
                    // InternalSqlParser.g:7944:6: (enumLiteral_18= DAY_HOUR )
                    {
                    // InternalSqlParser.g:7944:6: (enumLiteral_18= DAY_HOUR )
                    // InternalSqlParser.g:7944:11: enumLiteral_18= DAY_HOUR
                    {
                    enumLiteral_18=(Token)match(input,DAY_HOUR,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getDayhEnumLiteralDeclaration_18().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_18, grammarAccess.getEXTRACT_VALUESAccess().getDayhEnumLiteralDeclaration_18()); 
                          
                    }

                    }


                    }
                    break;
                case 20 :
                    // InternalSqlParser.g:7950:6: (enumLiteral_19= YEAR_MONTH )
                    {
                    // InternalSqlParser.g:7950:6: (enumLiteral_19= YEAR_MONTH )
                    // InternalSqlParser.g:7950:11: enumLiteral_19= YEAR_MONTH
                    {
                    enumLiteral_19=(Token)match(input,YEAR_MONTH,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getEXTRACT_VALUESAccess().getYearMonthEnumLiteralDeclaration_19().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_19, grammarAccess.getEXTRACT_VALUESAccess().getYearMonthEnumLiteralDeclaration_19()); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEXTRACT_VALUES"

    // $ANTLR start synpred174_InternalSqlParser
    public final void synpred174_InternalSqlParser_fragment() throws RecognitionException {   
        EObject lv_wop_1_0 = null;


        // InternalSqlParser.g:7286:2: ( ( (lv_wop_1_0= ruleOperandGroup ) ) )
        // InternalSqlParser.g:7286:2: ( (lv_wop_1_0= ruleOperandGroup ) )
        {
        // InternalSqlParser.g:7286:2: ( (lv_wop_1_0= ruleOperandGroup ) )
        // InternalSqlParser.g:7287:1: (lv_wop_1_0= ruleOperandGroup )
        {
        // InternalSqlParser.g:7287:1: (lv_wop_1_0= ruleOperandGroup )
        // InternalSqlParser.g:7288:3: lv_wop_1_0= ruleOperandGroup
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWopOperandGroupParserRuleCall_1_0_0()); 
          	    
        }
        pushFollow(FOLLOW_2);
        lv_wop_1_0=ruleOperandGroup();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred174_InternalSqlParser

    // $ANTLR start synpred175_InternalSqlParser
    public final void synpred175_InternalSqlParser_fragment() throws RecognitionException {   
        EObject lv_expr_2_0 = null;


        // InternalSqlParser.g:7305:6: ( ( (lv_expr_2_0= ruleFullExpression ) ) )
        // InternalSqlParser.g:7305:6: ( (lv_expr_2_0= ruleFullExpression ) )
        {
        // InternalSqlParser.g:7305:6: ( (lv_expr_2_0= ruleFullExpression ) )
        // InternalSqlParser.g:7306:1: (lv_expr_2_0= ruleFullExpression )
        {
        // InternalSqlParser.g:7306:1: (lv_expr_2_0= ruleFullExpression )
        // InternalSqlParser.g:7307:3: lv_expr_2_0= ruleFullExpression
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getSQLCASEAccess().getExprFullExpressionParserRuleCall_1_1_0()); 
          	    
        }
        pushFollow(FOLLOW_2);
        lv_expr_2_0=ruleFullExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred175_InternalSqlParser

    // $ANTLR start synpred178_InternalSqlParser
    public final void synpred178_InternalSqlParser_fragment() throws RecognitionException {   
        EObject lv_wop_1_0 = null;


        // InternalSqlParser.g:7432:2: ( ( (lv_wop_1_0= ruleOperandGroup ) ) )
        // InternalSqlParser.g:7432:2: ( (lv_wop_1_0= ruleOperandGroup ) )
        {
        // InternalSqlParser.g:7432:2: ( (lv_wop_1_0= ruleOperandGroup ) )
        // InternalSqlParser.g:7433:1: (lv_wop_1_0= ruleOperandGroup )
        {
        // InternalSqlParser.g:7433:1: (lv_wop_1_0= ruleOperandGroup )
        // InternalSqlParser.g:7434:3: lv_wop_1_0= ruleOperandGroup
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getWopOperandGroupParserRuleCall_1_0_0()); 
          	    
        }
        pushFollow(FOLLOW_2);
        lv_wop_1_0=ruleOperandGroup();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred178_InternalSqlParser

    // Delegated rules

    public final boolean synpred174_InternalSqlParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred174_InternalSqlParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred175_InternalSqlParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred175_InternalSqlParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred178_InternalSqlParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred178_InternalSqlParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA76 dfa76 = new DFA76(this);
    protected DFA75 dfa75 = new DFA75(this);
    protected DFA132 dfa132 = new DFA132(this);
    protected DFA135 dfa135 = new DFA135(this);
    static final String dfa_1s = "\12\uffff";
    static final String dfa_2s = "\5\uffff\1\11\4\uffff";
    static final String dfa_3s = "\1\42\1\51\1\uffff\1\42\1\uffff\1\11\4\uffff";
    static final String dfa_4s = "\1\u008f\1\166\1\uffff\1\u008f\1\uffff\1\u0084\4\uffff";
    static final String dfa_5s = "\2\uffff\1\1\1\uffff\1\2\1\uffff\1\3\1\5\1\6\1\4";
    static final String dfa_6s = "\12\uffff}>";
    static final String[] dfa_7s = {
            "\1\4\6\uffff\1\10\11\uffff\1\4\22\uffff\1\4\11\uffff\1\2\23\uffff\1\1\5\uffff\1\6\5\uffff\1\7\5\uffff\1\3\13\uffff\1\4\1\5\1\uffff\3\4\4\uffff\4\4",
            "\1\10\106\uffff\1\7\5\uffff\1\2",
            "",
            "\1\2\6\uffff\1\2\4\uffff\1\4\4\uffff\1\2\22\uffff\1\2\11\uffff\1\2\23\uffff\1\2\5\uffff\1\2\5\uffff\1\2\5\uffff\1\2\13\uffff\2\2\1\uffff\3\2\4\uffff\4\2",
            "",
            "\1\11\10\uffff\1\11\14\uffff\1\4\4\uffff\1\11\3\uffff\1\11\1\4\1\11\1\uffff\1\11\7\uffff\2\11\1\uffff\4\11\2\uffff\1\11\3\uffff\2\11\1\uffff\1\11\5\uffff\1\11\1\uffff\1\11\1\uffff\1\11\1\4\6\uffff\1\11\2\uffff\1\11\4\uffff\1\11\5\uffff\1\4\4\uffff\1\4\1\uffff\3\4\2\uffff\2\4\1\uffff\1\11\2\4\1\uffff\1\11\1\4\1\11\1\4\1\uffff\4\4\3\uffff\1\11\1\4",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA76 extends DFA {

        public DFA76(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 76;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "3582:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )";
        }
    }
    static final String dfa_8s = "\20\uffff";
    static final String dfa_9s = "\1\152\1\20\14\171\2\uffff";
    static final String dfa_10s = "\1\152\1\150\14\u0080\2\uffff";
    static final String dfa_11s = "\16\uffff\1\1\1\2";
    static final String dfa_12s = "\20\uffff}>";
    static final String[] dfa_13s = {
            "\1\1",
            "\1\13\5\uffff\1\15\1\5\1\14\1\10\3\uffff\1\12\1\11\21\uffff\1\4\1\7\1\3\22\uffff\1\6\42\uffff\1\2",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "\1\16\6\uffff\1\17",
            "",
            ""
    };

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA75 extends DFA {

        public DFA75(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 75;
            this.eot = dfa_8;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "3622:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )";
        }
    }
    static final String dfa_14s = "\25\uffff";
    static final String dfa_15s = "\1\42\15\0\7\uffff";
    static final String dfa_16s = "\1\u008f\15\0\7\uffff";
    static final String dfa_17s = "\16\uffff\1\2\4\uffff\1\3\1\1";
    static final String dfa_18s = "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\7\uffff}>";
    static final String[] dfa_19s = {
            "\1\14\6\uffff\1\16\11\uffff\1\13\22\uffff\1\15\11\uffff\1\16\10\uffff\1\23\12\uffff\1\16\5\uffff\1\16\5\uffff\1\16\5\uffff\1\12\13\uffff\1\4\1\5\1\uffff\1\6\1\7\1\10\4\uffff\1\11\1\3\1\2\1\1",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_14 = DFA.unpackEncodedString(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[][] dfa_19 = unpackEncodedStringArray(dfa_19s);

    class DFA132 extends DFA {

        public DFA132(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 132;
            this.eot = dfa_14;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "7286:1: ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA132_1 = input.LA(1);

                         
                        int index132_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA132_2 = input.LA(1);

                         
                        int index132_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA132_3 = input.LA(1);

                         
                        int index132_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA132_4 = input.LA(1);

                         
                        int index132_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA132_5 = input.LA(1);

                         
                        int index132_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA132_6 = input.LA(1);

                         
                        int index132_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA132_7 = input.LA(1);

                         
                        int index132_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA132_8 = input.LA(1);

                         
                        int index132_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA132_9 = input.LA(1);

                         
                        int index132_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA132_10 = input.LA(1);

                         
                        int index132_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA132_11 = input.LA(1);

                         
                        int index132_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA132_12 = input.LA(1);

                         
                        int index132_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA132_13 = input.LA(1);

                         
                        int index132_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalSqlParser()) ) {s = 20;}

                        else if ( (synpred175_InternalSqlParser()) ) {s = 14;}

                         
                        input.seek(index132_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 132, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_20s = "\24\uffff";
    static final String dfa_21s = "\1\42\15\0\6\uffff";
    static final String dfa_22s = "\1\u008f\15\0\6\uffff";
    static final String dfa_23s = "\16\uffff\1\2\4\uffff\1\1";
    static final String dfa_24s = "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\6\uffff}>";
    static final String[] dfa_25s = {
            "\1\14\6\uffff\1\16\11\uffff\1\13\22\uffff\1\15\11\uffff\1\16\23\uffff\1\16\5\uffff\1\16\5\uffff\1\16\5\uffff\1\12\13\uffff\1\4\1\5\1\uffff\1\6\1\7\1\10\4\uffff\1\11\1\3\1\2\1\1",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final char[] dfa_21 = DFA.unpackEncodedStringToUnsignedChars(dfa_21s);
    static final char[] dfa_22 = DFA.unpackEncodedStringToUnsignedChars(dfa_22s);
    static final short[] dfa_23 = DFA.unpackEncodedString(dfa_23s);
    static final short[] dfa_24 = DFA.unpackEncodedString(dfa_24s);
    static final short[][] dfa_25 = unpackEncodedStringArray(dfa_25s);

    class DFA135 extends DFA {

        public DFA135(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 135;
            this.eot = dfa_20;
            this.eof = dfa_20;
            this.min = dfa_21;
            this.max = dfa_22;
            this.accept = dfa_23;
            this.special = dfa_24;
            this.transition = dfa_25;
        }
        public String getDescription() {
            return "7432:1: ( ( (lv_wop_1_0= ruleOperandGroup ) ) | ( (lv_expr_2_0= ruleFullExpression ) ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA135_1 = input.LA(1);

                         
                        int index135_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA135_2 = input.LA(1);

                         
                        int index135_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA135_3 = input.LA(1);

                         
                        int index135_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA135_4 = input.LA(1);

                         
                        int index135_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA135_5 = input.LA(1);

                         
                        int index135_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA135_6 = input.LA(1);

                         
                        int index135_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA135_7 = input.LA(1);

                         
                        int index135_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA135_8 = input.LA(1);

                         
                        int index135_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA135_9 = input.LA(1);

                         
                        int index135_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA135_10 = input.LA(1);

                         
                        int index135_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA135_11 = input.LA(1);

                         
                        int index135_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA135_12 = input.LA(1);

                         
                        int index135_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA135_13 = input.LA(1);

                         
                        int index135_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred178_InternalSqlParser()) ) {s = 19;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index135_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 135, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000400000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000000L,0x0040400000000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000000L,0x0000002000100000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000002L,0x0200000000000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0400010000040002L,0x0000000000000004L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000400000000000L,0x0000000024000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0008000408000000L,0x0040004000000040L,0x000000000000F0FCL});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0008002408000000L,0x0040004004000040L,0x000000000000F0FCL});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0008000408000000L,0x0040004004000040L,0x000000000000F0FCL});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x22A0140000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0008020400000000L,0x0041041000010040L,0x000000000000F0ECL});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x22A0140000000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000000E020L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x2220140000000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x2220100000000002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0220100000000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0020100000000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0110001000000202L,0x0000000000005402L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000008L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x8000008000000002L,0x0000400000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L,0x00000000000010ECL});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0040008000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000000L,0x0040000080000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000A00000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000080L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000002L,0x0008000040000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0007000063C10000L,0x0000010000000020L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0008000400000000L,0x0040000000000040L,0x000000000000F0ECL});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0008020480000000L,0xE0533E1000018040L,0x000000000000F0ECL});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0000000000000000L,0x0000001000020000L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0008000400000000L,0x00400000A0200040L,0x000000000000F0ECL});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0008000000000000L,0x0000000000000000L,0x00000000000090ECL});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000017ECL});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000000000002L,0x1520000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0008000408000000L,0x00C0000020000040L,0x000000000000F0FCL});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x080028400400FDF0L,0x0000000209000800L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x2000000000080000L,0x0080000000000000L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100001L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0008000580200000L,0x0040000000000040L,0x000000000000F0ECL});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000120000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x1000000000000002L,0x0000000100000080L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0x0040000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0x0008000408000000L,0x0040000020000040L,0x000000000000F0ECL});
    public static final BitSet FOLLOW_92 = new BitSet(new long[]{0x0000000000000000L,0x00C0000000000000L});
    public static final BitSet FOLLOW_93 = new BitSet(new long[]{0x0000000000000000L,0x0280000000000000L});
    public static final BitSet FOLLOW_94 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_95 = new BitSet(new long[]{0x0008020400000000L,0x0041041002010040L,0x000000000000F0ECL});
    public static final BitSet FOLLOW_96 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_97 = new BitSet(new long[]{0x0008020400000002L,0x0041041002010040L,0x000000000000F0ECL});
    public static final BitSet FOLLOW_98 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_99 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_100 = new BitSet(new long[]{0x0110000000000200L,0x0000000000005402L});
    public static final BitSet FOLLOW_101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_102 = new BitSet(new long[]{0x4000000000000000L,0x0000000000001000L});

}