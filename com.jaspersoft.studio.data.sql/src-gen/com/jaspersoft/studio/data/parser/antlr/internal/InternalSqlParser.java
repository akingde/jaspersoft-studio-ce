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

@SuppressWarnings("all")
public class InternalSqlParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "UNBOUNDEDFOLLOWING", "UNBOUNDEDPRECEDING", "MINUTE_MICROSECOND", "SECOND_MICROSECOND", "ORDERSIBLINGSBY", "HOUR_MICROSECOND", "DAY_MICROSECOND", "MINUTE_SECOND", "STRAIGHT_JOIN", "PARTITIONBY", "CURRENTROW", "FETCHFIRST", "HOUR_MINUTE", "HOUR_SECOND", "ISNOTNULL", "MICROSECOND", "NOTBETWEEN", "DAY_MINUTE", "DAY_SECOND", "NOTEXISTS", "YEAR_MONTH", "KW_FOLLOWING", "INTERSECT", "PRECEDING", "WITHTIES", "BETWEEN_3", "BETWEEN_1", "DAY_HOUR", "DISTINCT", "GROUPBY", "NOTLIKE", "NOTEQUAL", "ORDERBY", "BETWEEN_2", "GREATER_1", "BETWEEN", "EXCLUDE", "EXTRACT", "GREATER", "INCLUDE", "ISNULL", "NATURAL", "PERCENT", "QUARTER", "UNPIVOT", "EXCEPT", "EXISTS", "HAVING", "MINUTE", "NOTIN_1", "OFFSET", "SECOND", "SELECT", "CAST", "CROSS", "EQUAL", "FIRST", "INNER", "LESS_1", "LIMIT", "MINUS", "MONTH", "NOTIN", "NULLS", "OUTER", "PIVOT", "RANGE", "RIGHT", "UNION", "USING", "WHERE", "CASE", "DESC", "ELSE", "FROM", "FULL", "HOUR", "JOIN", "LAST", "LEFT", "LESS", "LIKE", "NOT", "NOT_1", "ONLY", "OVER", "ROWS", "SOME", "THEN", "WEEK", "WHEN", "YEAR", "LeftParenthesisPlusSignRightParenthesis", "ALL", "AND", "ANY", "ASC", "DAY", "END", "FOR", "ROW", "TOP", "XML", "ExclamationMarkEqualsSign", "X", "LessThanSignEqualsSign", "LessThanSignGreaterThanSign", "GreaterThanSignEqualsSign", "AS", "IN", "ON", "OR", "CircumflexAccentEqualsSign", "VerticalLineVerticalLine", "LeftParenthesis", "RightParenthesis", "PlusSign", "Comma", "HyphenMinus", "FullStop", "Solidus", "LessThanSign", "EqualsSign", "GreaterThanSign", "LeftCurlyBracket", "VerticalLine", "RightCurlyBracket", "RULE_JRPARAM", "RULE_JRNPARAM", "RULE_STAR", "RULE_UNSIGNED", "RULE_INT", "RULE_SIGNED_DOUBLE", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_STRING_", "RULE_STRING", "RULE_DBNAME", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int CAST=57;
    public static final int PIVOT=69;
    public static final int WEEK=93;
    public static final int RULE_ID=143;
    public static final int RULE_JRNPARAM=132;
    public static final int RULE_DATE=137;
    public static final int RightParenthesis=119;
    public static final int ROW=104;
    public static final int RULE_ANY_OTHER=147;
    public static final int CASE=75;
    public static final int LeftParenthesis=118;
    public static final int DAY=101;
    public static final int NOT=86;
    public static final int Solidus=124;
    public static final int EXCEPT=49;
    public static final int EOF=-1;
    public static final int MONTH=65;
    public static final int FullStop=123;
    public static final int NOTEQUAL=35;
    public static final int NOTLIKE=34;
    public static final int FULL=79;
    public static final int GREATER=42;
    public static final int QUARTER=47;
    public static final int USING=73;
    public static final int CircumflexAccentEqualsSign=116;
    public static final int PRECEDING=27;
    public static final int INCLUDE=43;
    public static final int LessThanSign=125;
    public static final int LESS=84;
    public static final int HOUR_MICROSECOND=9;
    public static final int RULE_SIGNED_DOUBLE=136;
    public static final int NOT_1=87;
    public static final int LAST=82;
    public static final int NOTIN_1=53;
    public static final int SELECT=56;
    public static final int GROUPBY=33;
    public static final int SECOND=55;
    public static final int DAY_MICROSECOND=10;
    public static final int ASC=100;
    public static final int ELSE=77;
    public static final int ON=114;
    public static final int LessThanSignEqualsSign=109;
    public static final int DAY_MINUTE=21;
    public static final int LeftCurlyBracket=128;
    public static final int CURRENTROW=14;
    public static final int HOUR_SECOND=17;
    public static final int STRAIGHT_JOIN=12;
    public static final int X=108;
    public static final int RULE_ML_COMMENT=144;
    public static final int INTERSECT=26;
    public static final int RULE_STRING=141;
    public static final int ORDERSIBLINGSBY=8;
    public static final int VerticalLine=129;
    public static final int OR=115;
    public static final int END=102;
    public static final int FROM=78;
    public static final int DISTINCT=32;
    public static final int XML=106;
    public static final int BETWEEN_3=29;
    public static final int BETWEEN_2=37;
    public static final int BETWEEN_1=30;
    public static final int RightCurlyBracket=130;
    public static final int NOTIN=66;
    public static final int OVER=89;
    public static final int WHERE=74;
    public static final int VerticalLineVerticalLine=117;
    public static final int HyphenMinus=122;
    public static final int INNER=61;
    public static final int YEAR=95;
    public static final int RULE_UNSIGNED=134;
    public static final int MICROSECOND=19;
    public static final int LIMIT=63;
    public static final int ONLY=88;
    public static final int UNPIVOT=48;
    public static final int ISNULL=44;
    public static final int FOR=103;
    public static final int ORDERBY=36;
    public static final int RULE_STRING_=140;
    public static final int LessThanSignGreaterThanSign=110;
    public static final int AND=98;
    public static final int NOTEXISTS=23;
    public static final int GreaterThanSign=127;
    public static final int CROSS=58;
    public static final int SECOND_MICROSECOND=7;
    public static final int YEAR_MONTH=24;
    public static final int LESS_1=62;
    public static final int AS=112;
    public static final int DAY_HOUR=31;
    public static final int THEN=92;
    public static final int IN=113;
    public static final int FETCHFIRST=15;
    public static final int OFFSET=54;
    public static final int LEFT=83;
    public static final int SOME=91;
    public static final int EQUAL=59;
    public static final int ALL=97;
    public static final int RULE_TIME=138;
    public static final int RULE_INT=135;
    public static final int RULE_TIMESTAMP=139;
    public static final int RULE_DBNAME=142;
    public static final int EXISTS=50;
    public static final int MINUTE_SECOND=11;
    public static final int EXTRACT=41;
    public static final int WITHTIES=28;
    public static final int LIKE=85;
    public static final int EXCLUDE=40;
    public static final int ExclamationMarkEqualsSign=107;
    public static final int OUTER=68;
    public static final int PARTITIONBY=13;
    public static final int PERCENT=46;
    public static final int UNBOUNDEDFOLLOWING=4;
    public static final int KW_FOLLOWING=25;
    public static final int GREATER_1=38;
    public static final int MINUTE_MICROSECOND=6;
    public static final int RANGE=70;
    public static final int RIGHT=71;
    public static final int HAVING=51;
    public static final int MINUS=64;
    public static final int HOUR=80;
    public static final int RULE_SL_COMMENT=145;
    public static final int JOIN=81;
    public static final int UNION=72;
    public static final int NOTBETWEEN=20;
    public static final int NULLS=67;
    public static final int ANY=99;
    public static final int PlusSign=120;
    public static final int ISNOTNULL=18;
    public static final int UNBOUNDEDPRECEDING=5;
    public static final int DAY_SECOND=22;
    public static final int RULE_STAR=133;
    public static final int WHEN=94;
    public static final int RULE_JRPARAM=131;
    public static final int ROWS=90;
    public static final int HOUR_MINUTE=16;
    public static final int GreaterThanSignEqualsSign=111;
    public static final int NATURAL=45;
    public static final int DESC=76;
    public static final int LeftParenthesisPlusSignRightParenthesis=96;
    public static final int MINUTE=52;
    public static final int RULE_WS=146;
    public static final int TOP=105;
    public static final int Comma=121;
    public static final int EqualsSign=126;
    public static final int BETWEEN=39;
    public static final int FIRST=60;

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
    // InternalSqlParser.g:62:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalSqlParser.g:63:2: (iv_ruleModel= ruleModel EOF )
            // InternalSqlParser.g:64:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:71:1: ruleModel returns [EObject current=null] : ( (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_query_1_0= ruleSelectQuery ) ) ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token this_JRNPARAM_0=null;
        EObject lv_query_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:74:28: ( ( (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_query_1_0= ruleSelectQuery ) ) ) )
            // InternalSqlParser.g:75:1: ( (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_query_1_0= ruleSelectQuery ) ) )
            {
            // InternalSqlParser.g:75:1: ( (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_query_1_0= ruleSelectQuery ) ) )
            // InternalSqlParser.g:75:2: (this_JRNPARAM_0= RULE_JRNPARAM )? ( (lv_query_1_0= ruleSelectQuery ) )
            {
            // InternalSqlParser.g:75:2: (this_JRNPARAM_0= RULE_JRNPARAM )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_JRNPARAM) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalSqlParser.g:75:3: this_JRNPARAM_0= RULE_JRNPARAM
                    {
                    this_JRNPARAM_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_3); 
                     
                        newLeafNode(this_JRNPARAM_0, grammarAccess.getModelAccess().getJRNPARAMTerminalRuleCall_0()); 
                        

                    }
                    break;

            }

            // InternalSqlParser.g:79:3: ( (lv_query_1_0= ruleSelectQuery ) )
            // InternalSqlParser.g:80:1: (lv_query_1_0= ruleSelectQuery )
            {
            // InternalSqlParser.g:80:1: (lv_query_1_0= ruleSelectQuery )
            // InternalSqlParser.g:81:3: lv_query_1_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getModelAccess().getQuerySelectQueryParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_query_1_0=ruleSelectQuery();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getModelRule());
            	        }
                   		set(
                   			current, 
                   			"query",
                    		lv_query_1_0, 
                    		"com.jaspersoft.studio.data.Sql.SelectQuery");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleFetchFirst"
    // InternalSqlParser.g:105:1: entryRuleFetchFirst returns [EObject current=null] : iv_ruleFetchFirst= ruleFetchFirst EOF ;
    public final EObject entryRuleFetchFirst() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFetchFirst = null;


        try {
            // InternalSqlParser.g:106:2: (iv_ruleFetchFirst= ruleFetchFirst EOF )
            // InternalSqlParser.g:107:2: iv_ruleFetchFirst= ruleFetchFirst EOF
            {
             newCompositeNode(grammarAccess.getFetchFirstRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFetchFirst=ruleFetchFirst();

            state._fsp--;

             current =iv_ruleFetchFirst; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:114:1: ruleFetchFirst returns [EObject current=null] : ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY ) ;
    public final EObject ruleFetchFirst() throws RecognitionException {
        EObject current = null;

        Token lv_row_1_1=null;
        Token lv_row_1_2=null;
        Token otherlv_2=null;
        EObject lv_fetchFirst_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:117:28: ( ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY ) )
            // InternalSqlParser.g:118:1: ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY )
            {
            // InternalSqlParser.g:118:1: ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY )
            // InternalSqlParser.g:118:2: ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY
            {
            // InternalSqlParser.g:118:2: ( (lv_fetchFirst_0_0= ruleIntegerValue ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_INT) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalSqlParser.g:119:1: (lv_fetchFirst_0_0= ruleIntegerValue )
                    {
                    // InternalSqlParser.g:119:1: (lv_fetchFirst_0_0= ruleIntegerValue )
                    // InternalSqlParser.g:120:3: lv_fetchFirst_0_0= ruleIntegerValue
                    {
                     
                    	        newCompositeNode(grammarAccess.getFetchFirstAccess().getFetchFirstIntegerValueParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_4);
                    lv_fetchFirst_0_0=ruleIntegerValue();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFetchFirstRule());
                    	        }
                           		set(
                           			current, 
                           			"fetchFirst",
                            		lv_fetchFirst_0_0, 
                            		"com.jaspersoft.studio.data.Sql.IntegerValue");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:136:3: ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) )
            // InternalSqlParser.g:137:1: ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) )
            {
            // InternalSqlParser.g:137:1: ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) )
            // InternalSqlParser.g:138:1: (lv_row_1_1= ROW | lv_row_1_2= ROWS )
            {
            // InternalSqlParser.g:138:1: (lv_row_1_1= ROW | lv_row_1_2= ROWS )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==ROW) ) {
                alt3=1;
            }
            else if ( (LA3_0==ROWS) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalSqlParser.g:139:3: lv_row_1_1= ROW
                    {
                    lv_row_1_1=(Token)match(input,ROW,FOLLOW_5); 

                            newLeafNode(lv_row_1_1, grammarAccess.getFetchFirstAccess().getRowROWKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getFetchFirstRule());
                    	        }
                           		setWithLastConsumed(current, "row", lv_row_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:152:8: lv_row_1_2= ROWS
                    {
                    lv_row_1_2=(Token)match(input,ROWS,FOLLOW_5); 

                            newLeafNode(lv_row_1_2, grammarAccess.getFetchFirstAccess().getRowROWSKeyword_1_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getFetchFirstRule());
                    	        }
                           		setWithLastConsumed(current, "row", lv_row_1_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,ONLY,FOLLOW_2); 

                	newLeafNode(otherlv_2, grammarAccess.getFetchFirstAccess().getONLYKeyword_2());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:181:1: entryRuleOffset returns [EObject current=null] : iv_ruleOffset= ruleOffset EOF ;
    public final EObject entryRuleOffset() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOffset = null;


        try {
            // InternalSqlParser.g:182:2: (iv_ruleOffset= ruleOffset EOF )
            // InternalSqlParser.g:183:2: iv_ruleOffset= ruleOffset EOF
            {
             newCompositeNode(grammarAccess.getOffsetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOffset=ruleOffset();

            state._fsp--;

             current =iv_ruleOffset; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:190:1: ruleOffset returns [EObject current=null] : ( (lv_offset_0_0= RULE_INT ) ) ;
    public final EObject ruleOffset() throws RecognitionException {
        EObject current = null;

        Token lv_offset_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:193:28: ( ( (lv_offset_0_0= RULE_INT ) ) )
            // InternalSqlParser.g:194:1: ( (lv_offset_0_0= RULE_INT ) )
            {
            // InternalSqlParser.g:194:1: ( (lv_offset_0_0= RULE_INT ) )
            // InternalSqlParser.g:195:1: (lv_offset_0_0= RULE_INT )
            {
            // InternalSqlParser.g:195:1: (lv_offset_0_0= RULE_INT )
            // InternalSqlParser.g:196:3: lv_offset_0_0= RULE_INT
            {
            lv_offset_0_0=(Token)match(input,RULE_INT,FOLLOW_2); 

            			newLeafNode(lv_offset_0_0, grammarAccess.getOffsetAccess().getOffsetINTTerminalRuleCall_0()); 
            		

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

             leaveRule(); 
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
    // InternalSqlParser.g:220:1: entryRuleLimit returns [EObject current=null] : iv_ruleLimit= ruleLimit EOF ;
    public final EObject entryRuleLimit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLimit = null;


        try {
            // InternalSqlParser.g:221:2: (iv_ruleLimit= ruleLimit EOF )
            // InternalSqlParser.g:222:2: iv_ruleLimit= ruleLimit EOF
            {
             newCompositeNode(grammarAccess.getLimitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLimit=ruleLimit();

            state._fsp--;

             current =iv_ruleLimit; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:229:1: ruleLimit returns [EObject current=null] : ( ( () otherlv_1= ALL ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) ) ;
    public final EObject ruleLimit() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_l1_2_0=null;
        Token otherlv_3=null;
        Token lv_l2_4_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:232:28: ( ( ( () otherlv_1= ALL ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) ) )
            // InternalSqlParser.g:233:1: ( ( () otherlv_1= ALL ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) )
            {
            // InternalSqlParser.g:233:1: ( ( () otherlv_1= ALL ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ALL) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_UNSIGNED) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalSqlParser.g:233:2: ( () otherlv_1= ALL )
                    {
                    // InternalSqlParser.g:233:2: ( () otherlv_1= ALL )
                    // InternalSqlParser.g:233:3: () otherlv_1= ALL
                    {
                    // InternalSqlParser.g:233:3: ()
                    // InternalSqlParser.g:234:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getLimitAccess().getLimitAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,ALL,FOLLOW_2); 

                        	newLeafNode(otherlv_1, grammarAccess.getLimitAccess().getALLKeyword_0_1());
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:245:6: ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? )
                    {
                    // InternalSqlParser.g:245:6: ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? )
                    // InternalSqlParser.g:245:7: ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )?
                    {
                    // InternalSqlParser.g:245:7: ( (lv_l1_2_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:246:1: (lv_l1_2_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:246:1: (lv_l1_2_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:247:3: lv_l1_2_0= RULE_UNSIGNED
                    {
                    lv_l1_2_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_6); 

                    			newLeafNode(lv_l1_2_0, grammarAccess.getLimitAccess().getL1UNSIGNEDTerminalRuleCall_1_0_0()); 
                    		

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

                    // InternalSqlParser.g:263:2: (otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) ) )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==Comma) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // InternalSqlParser.g:264:2: otherlv_3= Comma ( (lv_l2_4_0= RULE_UNSIGNED ) )
                            {
                            otherlv_3=(Token)match(input,Comma,FOLLOW_7); 

                                	newLeafNode(otherlv_3, grammarAccess.getLimitAccess().getCommaKeyword_1_1_0());
                                
                            // InternalSqlParser.g:268:1: ( (lv_l2_4_0= RULE_UNSIGNED ) )
                            // InternalSqlParser.g:269:1: (lv_l2_4_0= RULE_UNSIGNED )
                            {
                            // InternalSqlParser.g:269:1: (lv_l2_4_0= RULE_UNSIGNED )
                            // InternalSqlParser.g:270:3: lv_l2_4_0= RULE_UNSIGNED
                            {
                            lv_l2_4_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_2); 

                            			newLeafNode(lv_l2_4_0, grammarAccess.getLimitAccess().getL2UNSIGNEDTerminalRuleCall_1_1_1_0()); 
                            		

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
                            break;

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:294:1: entryRuleSelectQuery returns [EObject current=null] : iv_ruleSelectQuery= ruleSelectQuery EOF ;
    public final EObject entryRuleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectQuery = null;


        try {
            // InternalSqlParser.g:295:2: (iv_ruleSelectQuery= ruleSelectQuery EOF )
            // InternalSqlParser.g:296:2: iv_ruleSelectQuery= ruleSelectQuery EOF
            {
             newCompositeNode(grammarAccess.getSelectQueryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSelectQuery=ruleSelectQuery();

            state._fsp--;

             current =iv_ruleSelectQuery; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:303:1: ruleSelectQuery returns [EObject current=null] : (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) ;
    public final EObject ruleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject this_Select_0 = null;

        EObject lv_op_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:306:28: ( (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) )
            // InternalSqlParser.g:307:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            {
            // InternalSqlParser.g:307:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            // InternalSqlParser.g:308:5: this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )*
            {
             
                    newCompositeNode(grammarAccess.getSelectQueryAccess().getSelectParserRuleCall_0()); 
                
            pushFollow(FOLLOW_8);
            this_Select_0=ruleSelect();

            state._fsp--;


                    current = this_Select_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:316:1: ( (lv_op_1_0= ruleSelectSubSet ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==INTERSECT||LA6_0==EXCEPT||LA6_0==MINUS||LA6_0==UNION) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalSqlParser.g:317:1: (lv_op_1_0= ruleSelectSubSet )
            	    {
            	    // InternalSqlParser.g:317:1: (lv_op_1_0= ruleSelectSubSet )
            	    // InternalSqlParser.g:318:3: lv_op_1_0= ruleSelectSubSet
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSelectQueryAccess().getOpSelectSubSetParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_8);
            	    lv_op_1_0=ruleSelectSubSet();

            	    state._fsp--;


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
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:342:1: entryRuleSelectSubSet returns [EObject current=null] : iv_ruleSelectSubSet= ruleSelectSubSet EOF ;
    public final EObject entryRuleSelectSubSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectSubSet = null;


        try {
            // InternalSqlParser.g:343:2: (iv_ruleSelectSubSet= ruleSelectSubSet EOF )
            // InternalSqlParser.g:344:2: iv_ruleSelectSubSet= ruleSelectSubSet EOF
            {
             newCompositeNode(grammarAccess.getSelectSubSetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSelectSubSet=ruleSelectSubSet();

            state._fsp--;

             current =iv_ruleSelectSubSet; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:351:1: ruleSelectSubSet returns [EObject current=null] : ( ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) ) ) ;
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
            // InternalSqlParser.g:354:28: ( ( ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) ) ) )
            // InternalSqlParser.g:355:1: ( ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) ) )
            {
            // InternalSqlParser.g:355:1: ( ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) ) )
            // InternalSqlParser.g:355:2: ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) ) ( (lv_all_1_0= ALL ) )? ( (lv_query_2_0= ruleSelect ) )
            {
            // InternalSqlParser.g:355:2: ( ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) ) )
            // InternalSqlParser.g:356:1: ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) )
            {
            // InternalSqlParser.g:356:1: ( (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT ) )
            // InternalSqlParser.g:357:1: (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT )
            {
            // InternalSqlParser.g:357:1: (lv_op_0_1= UNION | lv_op_0_2= INTERSECT | lv_op_0_3= MINUS | lv_op_0_4= EXCEPT )
            int alt7=4;
            switch ( input.LA(1) ) {
            case UNION:
                {
                alt7=1;
                }
                break;
            case INTERSECT:
                {
                alt7=2;
                }
                break;
            case MINUS:
                {
                alt7=3;
                }
                break;
            case EXCEPT:
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalSqlParser.g:358:3: lv_op_0_1= UNION
                    {
                    lv_op_0_1=(Token)match(input,UNION,FOLLOW_9); 

                            newLeafNode(lv_op_0_1, grammarAccess.getSelectSubSetAccess().getOpUNIONKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:371:8: lv_op_0_2= INTERSECT
                    {
                    lv_op_0_2=(Token)match(input,INTERSECT,FOLLOW_9); 

                            newLeafNode(lv_op_0_2, grammarAccess.getSelectSubSetAccess().getOpINTERSECTKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:384:8: lv_op_0_3= MINUS
                    {
                    lv_op_0_3=(Token)match(input,MINUS,FOLLOW_9); 

                            newLeafNode(lv_op_0_3, grammarAccess.getSelectSubSetAccess().getOpMINUSKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:397:8: lv_op_0_4= EXCEPT
                    {
                    lv_op_0_4=(Token)match(input,EXCEPT,FOLLOW_9); 

                            newLeafNode(lv_op_0_4, grammarAccess.getSelectSubSetAccess().getOpEXCEPTKeyword_0_0_3());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_4, null);
                    	    

                    }
                    break;

            }


            }


            }

            // InternalSqlParser.g:413:2: ( (lv_all_1_0= ALL ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ALL) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalSqlParser.g:414:1: (lv_all_1_0= ALL )
                    {
                    // InternalSqlParser.g:414:1: (lv_all_1_0= ALL )
                    // InternalSqlParser.g:415:3: lv_all_1_0= ALL
                    {
                    lv_all_1_0=(Token)match(input,ALL,FOLLOW_3); 

                            newLeafNode(lv_all_1_0, grammarAccess.getSelectSubSetAccess().getAllALLKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "all", lv_all_1_0, "ALL");
                    	    

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:429:3: ( (lv_query_2_0= ruleSelect ) )
            // InternalSqlParser.g:430:1: (lv_query_2_0= ruleSelect )
            {
            // InternalSqlParser.g:430:1: (lv_query_2_0= ruleSelect )
            // InternalSqlParser.g:431:3: lv_query_2_0= ruleSelect
            {
             
            	        newCompositeNode(grammarAccess.getSelectSubSetAccess().getQuerySelectParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_query_2_0=ruleSelect();

            state._fsp--;


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

             leaveRule(); 
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
    // InternalSqlParser.g:455:1: entryRuleSelect returns [EObject current=null] : iv_ruleSelect= ruleSelect EOF ;
    public final EObject entryRuleSelect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelect = null;


        try {
            // InternalSqlParser.g:456:2: (iv_ruleSelect= ruleSelect EOF )
            // InternalSqlParser.g:457:2: iv_ruleSelect= ruleSelect EOF
            {
             newCompositeNode(grammarAccess.getSelectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSelect=ruleSelect();

            state._fsp--;

             current =iv_ruleSelect; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:464:1: ruleSelect returns [EObject current=null] : ( ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= FROM ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= WHERE ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= GROUPBY ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= HAVING ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= ORDERBY ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= LIMIT ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= OFFSET ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= FETCHFIRST ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )? ) ;
    public final EObject ruleSelect() throws RecognitionException {
        EObject current = null;

        Token lv_select_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token this_INT_3=null;
        Token this_SIGNED_DOUBLE_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        EObject lv_cols_7_0 = null;

        EObject lv_tbl_9_0 = null;

        EObject lv_whereExpression_11_0 = null;

        EObject lv_groupByEntry_13_0 = null;

        EObject lv_havingEntry_15_0 = null;

        EObject lv_orderByEntry_17_0 = null;

        EObject lv_lim_19_0 = null;

        EObject lv_offset_21_0 = null;

        EObject lv_fetchFirst_23_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:467:28: ( ( ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= FROM ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= WHERE ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= GROUPBY ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= HAVING ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= ORDERBY ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= LIMIT ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= OFFSET ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= FETCHFIRST ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )? ) )
            // InternalSqlParser.g:468:1: ( ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= FROM ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= WHERE ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= GROUPBY ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= HAVING ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= ORDERBY ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= LIMIT ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= OFFSET ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= FETCHFIRST ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )? )
            {
            // InternalSqlParser.g:468:1: ( ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= FROM ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= WHERE ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= GROUPBY ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= HAVING ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= ORDERBY ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= LIMIT ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= OFFSET ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= FETCHFIRST ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )? )
            // InternalSqlParser.g:468:2: ( (lv_select_0_0= SELECT ) ) (otherlv_1= DISTINCT )? (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= FROM ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= WHERE ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= GROUPBY ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= HAVING ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= ORDERBY ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= LIMIT ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= OFFSET ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= FETCHFIRST ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )?
            {
            // InternalSqlParser.g:468:2: ( (lv_select_0_0= SELECT ) )
            // InternalSqlParser.g:469:1: (lv_select_0_0= SELECT )
            {
            // InternalSqlParser.g:469:1: (lv_select_0_0= SELECT )
            // InternalSqlParser.g:470:3: lv_select_0_0= SELECT
            {
            lv_select_0_0=(Token)match(input,SELECT,FOLLOW_10); 

                    newLeafNode(lv_select_0_0, grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectRule());
            	        }
                   		setWithLastConsumed(current, "select", lv_select_0_0, "SELECT");
            	    

            }


            }

            // InternalSqlParser.g:484:2: (otherlv_1= DISTINCT )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==DISTINCT) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalSqlParser.g:485:2: otherlv_1= DISTINCT
                    {
                    otherlv_1=(Token)match(input,DISTINCT,FOLLOW_10); 

                        	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                        

                    }
                    break;

            }

            // InternalSqlParser.g:489:3: (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )? )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==TOP) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalSqlParser.g:490:2: otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )?
                    {
                    otherlv_2=(Token)match(input,TOP,FOLLOW_11); 

                        	newLeafNode(otherlv_2, grammarAccess.getSelectAccess().getTOPKeyword_2_0());
                        
                    // InternalSqlParser.g:494:1: (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE )
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==RULE_INT) ) {
                        alt10=1;
                    }
                    else if ( (LA10_0==RULE_SIGNED_DOUBLE) ) {
                        alt10=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 0, input);

                        throw nvae;
                    }
                    switch (alt10) {
                        case 1 :
                            // InternalSqlParser.g:494:2: this_INT_3= RULE_INT
                            {
                            this_INT_3=(Token)match(input,RULE_INT,FOLLOW_12); 
                             
                                newLeafNode(this_INT_3, grammarAccess.getSelectAccess().getINTTerminalRuleCall_2_1_0()); 
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:499:6: this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE
                            {
                            this_SIGNED_DOUBLE_4=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_12); 
                             
                                newLeafNode(this_SIGNED_DOUBLE_4, grammarAccess.getSelectAccess().getSIGNED_DOUBLETerminalRuleCall_2_1_1()); 
                                

                            }
                            break;

                    }

                    // InternalSqlParser.g:503:2: (otherlv_5= PERCENT )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==PERCENT) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // InternalSqlParser.g:504:2: otherlv_5= PERCENT
                            {
                            otherlv_5=(Token)match(input,PERCENT,FOLLOW_13); 

                                	newLeafNode(otherlv_5, grammarAccess.getSelectAccess().getPERCENTKeyword_2_2());
                                

                            }
                            break;

                    }

                    // InternalSqlParser.g:508:3: (otherlv_6= WITHTIES )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==WITHTIES) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // InternalSqlParser.g:509:2: otherlv_6= WITHTIES
                            {
                            otherlv_6=(Token)match(input,WITHTIES,FOLLOW_10); 

                                	newLeafNode(otherlv_6, grammarAccess.getSelectAccess().getWITHTIESKeyword_2_3());
                                

                            }
                            break;

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:513:5: ( (lv_cols_7_0= ruleColumns ) )
            // InternalSqlParser.g:514:1: (lv_cols_7_0= ruleColumns )
            {
            // InternalSqlParser.g:514:1: (lv_cols_7_0= ruleColumns )
            // InternalSqlParser.g:515:3: lv_cols_7_0= ruleColumns
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_14);
            lv_cols_7_0=ruleColumns();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectRule());
            	        }
                   		set(
                   			current, 
                   			"cols",
                    		lv_cols_7_0, 
                    		"com.jaspersoft.studio.data.Sql.Columns");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_8=(Token)match(input,FROM,FOLLOW_15); 

                	newLeafNode(otherlv_8, grammarAccess.getSelectAccess().getFROMKeyword_4());
                
            // InternalSqlParser.g:536:1: ( (lv_tbl_9_0= ruleTables ) )
            // InternalSqlParser.g:537:1: (lv_tbl_9_0= ruleTables )
            {
            // InternalSqlParser.g:537:1: (lv_tbl_9_0= ruleTables )
            // InternalSqlParser.g:538:3: lv_tbl_9_0= ruleTables
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_16);
            lv_tbl_9_0=ruleTables();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectRule());
            	        }
                   		set(
                   			current, 
                   			"tbl",
                    		lv_tbl_9_0, 
                    		"com.jaspersoft.studio.data.Sql.Tables");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalSqlParser.g:554:2: (otherlv_10= WHERE ( (lv_whereExpression_11_0= ruleFullExpression ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==WHERE) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalSqlParser.g:555:2: otherlv_10= WHERE ( (lv_whereExpression_11_0= ruleFullExpression ) )
                    {
                    otherlv_10=(Token)match(input,WHERE,FOLLOW_17); 

                        	newLeafNode(otherlv_10, grammarAccess.getSelectAccess().getWHEREKeyword_6_0());
                        
                    // InternalSqlParser.g:559:1: ( (lv_whereExpression_11_0= ruleFullExpression ) )
                    // InternalSqlParser.g:560:1: (lv_whereExpression_11_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:560:1: (lv_whereExpression_11_0= ruleFullExpression )
                    // InternalSqlParser.g:561:3: lv_whereExpression_11_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_18);
                    lv_whereExpression_11_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"whereExpression",
                            		lv_whereExpression_11_0, 
                            		"com.jaspersoft.studio.data.Sql.FullExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:577:4: (otherlv_12= GROUPBY ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==GROUPBY) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalSqlParser.g:578:2: otherlv_12= GROUPBY ( (lv_groupByEntry_13_0= ruleGroupByColumns ) )
                    {
                    otherlv_12=(Token)match(input,GROUPBY,FOLLOW_19); 

                        	newLeafNode(otherlv_12, grammarAccess.getSelectAccess().getGROUPBYKeyword_7_0());
                        
                    // InternalSqlParser.g:582:1: ( (lv_groupByEntry_13_0= ruleGroupByColumns ) )
                    // InternalSqlParser.g:583:1: (lv_groupByEntry_13_0= ruleGroupByColumns )
                    {
                    // InternalSqlParser.g:583:1: (lv_groupByEntry_13_0= ruleGroupByColumns )
                    // InternalSqlParser.g:584:3: lv_groupByEntry_13_0= ruleGroupByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FOLLOW_20);
                    lv_groupByEntry_13_0=ruleGroupByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"groupByEntry",
                            		lv_groupByEntry_13_0, 
                            		"com.jaspersoft.studio.data.Sql.GroupByColumns");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:600:4: (otherlv_14= HAVING ( (lv_havingEntry_15_0= ruleFullExpression ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==HAVING) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalSqlParser.g:601:2: otherlv_14= HAVING ( (lv_havingEntry_15_0= ruleFullExpression ) )
                    {
                    otherlv_14=(Token)match(input,HAVING,FOLLOW_17); 

                        	newLeafNode(otherlv_14, grammarAccess.getSelectAccess().getHAVINGKeyword_8_0());
                        
                    // InternalSqlParser.g:605:1: ( (lv_havingEntry_15_0= ruleFullExpression ) )
                    // InternalSqlParser.g:606:1: (lv_havingEntry_15_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:606:1: (lv_havingEntry_15_0= ruleFullExpression )
                    // InternalSqlParser.g:607:3: lv_havingEntry_15_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FOLLOW_21);
                    lv_havingEntry_15_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"havingEntry",
                            		lv_havingEntry_15_0, 
                            		"com.jaspersoft.studio.data.Sql.FullExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:623:4: (otherlv_16= ORDERBY ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ORDERBY) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalSqlParser.g:624:2: otherlv_16= ORDERBY ( (lv_orderByEntry_17_0= ruleOrderByColumns ) )
                    {
                    otherlv_16=(Token)match(input,ORDERBY,FOLLOW_19); 

                        	newLeafNode(otherlv_16, grammarAccess.getSelectAccess().getORDERBYKeyword_9_0());
                        
                    // InternalSqlParser.g:628:1: ( (lv_orderByEntry_17_0= ruleOrderByColumns ) )
                    // InternalSqlParser.g:629:1: (lv_orderByEntry_17_0= ruleOrderByColumns )
                    {
                    // InternalSqlParser.g:629:1: (lv_orderByEntry_17_0= ruleOrderByColumns )
                    // InternalSqlParser.g:630:3: lv_orderByEntry_17_0= ruleOrderByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getOrderByEntryOrderByColumnsParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FOLLOW_22);
                    lv_orderByEntry_17_0=ruleOrderByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"orderByEntry",
                            		lv_orderByEntry_17_0, 
                            		"com.jaspersoft.studio.data.Sql.OrderByColumns");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:646:4: (otherlv_18= LIMIT ( (lv_lim_19_0= ruleLimit ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LIMIT) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalSqlParser.g:647:2: otherlv_18= LIMIT ( (lv_lim_19_0= ruleLimit ) )
                    {
                    otherlv_18=(Token)match(input,LIMIT,FOLLOW_23); 

                        	newLeafNode(otherlv_18, grammarAccess.getSelectAccess().getLIMITKeyword_10_0());
                        
                    // InternalSqlParser.g:651:1: ( (lv_lim_19_0= ruleLimit ) )
                    // InternalSqlParser.g:652:1: (lv_lim_19_0= ruleLimit )
                    {
                    // InternalSqlParser.g:652:1: (lv_lim_19_0= ruleLimit )
                    // InternalSqlParser.g:653:3: lv_lim_19_0= ruleLimit
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getLimLimitParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FOLLOW_24);
                    lv_lim_19_0=ruleLimit();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"lim",
                            		lv_lim_19_0, 
                            		"com.jaspersoft.studio.data.Sql.Limit");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:669:4: (otherlv_20= OFFSET ( (lv_offset_21_0= ruleOffset ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==OFFSET) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalSqlParser.g:670:2: otherlv_20= OFFSET ( (lv_offset_21_0= ruleOffset ) )
                    {
                    otherlv_20=(Token)match(input,OFFSET,FOLLOW_25); 

                        	newLeafNode(otherlv_20, grammarAccess.getSelectAccess().getOFFSETKeyword_11_0());
                        
                    // InternalSqlParser.g:674:1: ( (lv_offset_21_0= ruleOffset ) )
                    // InternalSqlParser.g:675:1: (lv_offset_21_0= ruleOffset )
                    {
                    // InternalSqlParser.g:675:1: (lv_offset_21_0= ruleOffset )
                    // InternalSqlParser.g:676:3: lv_offset_21_0= ruleOffset
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getOffsetOffsetParserRuleCall_11_1_0()); 
                    	    
                    pushFollow(FOLLOW_26);
                    lv_offset_21_0=ruleOffset();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"offset",
                            		lv_offset_21_0, 
                            		"com.jaspersoft.studio.data.Sql.Offset");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:692:4: (otherlv_22= FETCHFIRST ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==FETCHFIRST) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalSqlParser.g:693:2: otherlv_22= FETCHFIRST ( (lv_fetchFirst_23_0= ruleFetchFirst ) )
                    {
                    otherlv_22=(Token)match(input,FETCHFIRST,FOLLOW_27); 

                        	newLeafNode(otherlv_22, grammarAccess.getSelectAccess().getFETCHFIRSTKeyword_12_0());
                        
                    // InternalSqlParser.g:697:1: ( (lv_fetchFirst_23_0= ruleFetchFirst ) )
                    // InternalSqlParser.g:698:1: (lv_fetchFirst_23_0= ruleFetchFirst )
                    {
                    // InternalSqlParser.g:698:1: (lv_fetchFirst_23_0= ruleFetchFirst )
                    // InternalSqlParser.g:699:3: lv_fetchFirst_23_0= ruleFetchFirst
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getFetchFirstFetchFirstParserRuleCall_12_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_fetchFirst_23_0=ruleFetchFirst();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"fetchFirst",
                            		lv_fetchFirst_23_0, 
                            		"com.jaspersoft.studio.data.Sql.FetchFirst");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:723:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // InternalSqlParser.g:724:2: (iv_ruleColumns= ruleColumns EOF )
            // InternalSqlParser.g:725:2: iv_ruleColumns= ruleColumns EOF
            {
             newCompositeNode(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:732:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:735:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // InternalSqlParser.g:736:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // InternalSqlParser.g:736:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // InternalSqlParser.g:737:5: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;


                    current = this_ColumnOrAlias_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:745:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==Comma) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalSqlParser.g:745:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // InternalSqlParser.g:745:2: ()
                    // InternalSqlParser.g:746:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:751:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt21=0;
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==Comma) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // InternalSqlParser.g:752:2: otherlv_2= Comma ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_10); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:756:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // InternalSqlParser.g:757:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // InternalSqlParser.g:757:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // InternalSqlParser.g:758:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleColumnOrAlias();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt21 >= 1 ) break loop21;
                                EarlyExitException eee =
                                    new EarlyExitException(21, input);
                                throw eee;
                        }
                        cnt21++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:782:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // InternalSqlParser.g:783:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // InternalSqlParser.g:784:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:791:1: ruleColumnOrAlias returns [EObject current=null] : ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_1_0=null;
        Token lv_allCols_3_0=null;
        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_2_0 = null;

        EObject lv_dbAllCols_4_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:794:28: ( ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) )
            // InternalSqlParser.g:795:1: ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            {
            // InternalSqlParser.g:795:1: ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            int alt25=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==EOF||LA25_1==FROM||LA25_1==LeftParenthesisPlusSignRightParenthesis||LA25_1==AS||(LA25_1>=VerticalLineVerticalLine && LA25_1<=HyphenMinus)||LA25_1==Solidus||LA25_1==RULE_STAR||(LA25_1>=RULE_STRING && LA25_1<=RULE_ID)) ) {
                    alt25=1;
                }
                else if ( (LA25_1==FullStop) ) {
                    int LA25_6 = input.LA(3);

                    if ( ((LA25_6>=RULE_STRING && LA25_6<=RULE_ID)) ) {
                        alt25=1;
                    }
                    else if ( (LA25_6==RULE_STAR) ) {
                        alt25=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 6, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_DBNAME:
                {
                int LA25_2 = input.LA(2);

                if ( (LA25_2==FullStop) ) {
                    int LA25_6 = input.LA(3);

                    if ( ((LA25_6>=RULE_STRING && LA25_6<=RULE_ID)) ) {
                        alt25=1;
                    }
                    else if ( (LA25_6==RULE_STAR) ) {
                        alt25=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA25_2==EOF||LA25_2==FROM||LA25_2==LeftParenthesisPlusSignRightParenthesis||LA25_2==AS||LA25_2==VerticalLineVerticalLine||(LA25_2>=RightParenthesis && LA25_2<=HyphenMinus)||LA25_2==Solidus||LA25_2==RULE_STAR||(LA25_2>=RULE_STRING && LA25_2<=RULE_ID)) ) {
                    alt25=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
                {
                int LA25_3 = input.LA(2);

                if ( (LA25_3==FullStop) ) {
                    int LA25_6 = input.LA(3);

                    if ( ((LA25_6>=RULE_STRING && LA25_6<=RULE_ID)) ) {
                        alt25=1;
                    }
                    else if ( (LA25_6==RULE_STAR) ) {
                        alt25=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA25_3==EOF||LA25_3==FROM||LA25_3==LeftParenthesisPlusSignRightParenthesis||LA25_3==AS||LA25_3==VerticalLineVerticalLine||(LA25_3>=RightParenthesis && LA25_3<=HyphenMinus)||LA25_3==Solidus||LA25_3==RULE_STAR||(LA25_3>=RULE_STRING && LA25_3<=RULE_ID)) ) {
                    alt25=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 3, input);

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
                alt25=1;
                }
                break;
            case RULE_STAR:
                {
                alt25=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // InternalSqlParser.g:795:2: ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    {
                    // InternalSqlParser.g:795:2: ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    // InternalSqlParser.g:795:3: ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= AS ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    {
                    // InternalSqlParser.g:795:3: ( (lv_ce_0_0= ruleOperandGroup ) )
                    // InternalSqlParser.g:796:1: (lv_ce_0_0= ruleOperandGroup )
                    {
                    // InternalSqlParser.g:796:1: (lv_ce_0_0= ruleOperandGroup )
                    // InternalSqlParser.g:797:3: lv_ce_0_0= ruleOperandGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getCeOperandGroupParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_28);
                    lv_ce_0_0=ruleOperandGroup();

                    state._fsp--;


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

                    // InternalSqlParser.g:813:2: ( (lv_alias_1_0= AS ) )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==AS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // InternalSqlParser.g:814:1: (lv_alias_1_0= AS )
                            {
                            // InternalSqlParser.g:814:1: (lv_alias_1_0= AS )
                            // InternalSqlParser.g:815:3: lv_alias_1_0= AS
                            {
                            lv_alias_1_0=(Token)match(input,AS,FOLLOW_29); 

                                    newLeafNode(lv_alias_1_0, grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getColumnOrAliasRule());
                            	        }
                                   		setWithLastConsumed(current, "alias", lv_alias_1_0, "AS");
                            	    

                            }


                            }
                            break;

                    }

                    // InternalSqlParser.g:829:3: ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( ((LA24_0>=RULE_STRING && LA24_0<=RULE_ID)) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // InternalSqlParser.g:830:1: (lv_colAlias_2_0= ruleDbObjectName )
                            {
                            // InternalSqlParser.g:830:1: (lv_colAlias_2_0= ruleDbObjectName )
                            // InternalSqlParser.g:831:3: lv_colAlias_2_0= ruleDbObjectName
                            {
                             
                            	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
                            	    
                            pushFollow(FOLLOW_2);
                            lv_colAlias_2_0=ruleDbObjectName();

                            state._fsp--;


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
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:848:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    {
                    // InternalSqlParser.g:848:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    // InternalSqlParser.g:849:1: (lv_allCols_3_0= RULE_STAR )
                    {
                    // InternalSqlParser.g:849:1: (lv_allCols_3_0= RULE_STAR )
                    // InternalSqlParser.g:850:3: lv_allCols_3_0= RULE_STAR
                    {
                    lv_allCols_3_0=(Token)match(input,RULE_STAR,FOLLOW_2); 

                    			newLeafNode(lv_allCols_3_0, grammarAccess.getColumnOrAliasAccess().getAllColsSTARTerminalRuleCall_1_0()); 
                    		

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
                    break;
                case 3 :
                    // InternalSqlParser.g:867:6: ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) )
                    {
                    // InternalSqlParser.g:867:6: ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) )
                    // InternalSqlParser.g:868:1: (lv_dbAllCols_4_0= ruleDbObjectNameAll )
                    {
                    // InternalSqlParser.g:868:1: (lv_dbAllCols_4_0= ruleDbObjectNameAll )
                    // InternalSqlParser.g:869:3: lv_dbAllCols_4_0= ruleDbObjectNameAll
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getDbAllColsDbObjectNameAllParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_dbAllCols_4_0=ruleDbObjectNameAll();

                    state._fsp--;


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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:893:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // InternalSqlParser.g:894:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // InternalSqlParser.g:895:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
             newCompositeNode(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:902:1: ruleColumnFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:905:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:906:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:906:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:907:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_30);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:915:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==FullStop) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalSqlParser.g:915:2: () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:915:2: ()
                    // InternalSqlParser.g:916:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnFullAccess().getColEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:921:2: (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt26=0;
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==FullStop) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // InternalSqlParser.g:922:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_31); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:926:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:927:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:927:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:928:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_30);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt26 >= 1 ) break loop26;
                                EarlyExitException eee =
                                    new EarlyExitException(26, input);
                                throw eee;
                        }
                        cnt26++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:952:1: entryRuleTables returns [EObject current=null] : iv_ruleTables= ruleTables EOF ;
    public final EObject entryRuleTables() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTables = null;


        try {
            // InternalSqlParser.g:953:2: (iv_ruleTables= ruleTables EOF )
            // InternalSqlParser.g:954:2: iv_ruleTables= ruleTables EOF
            {
             newCompositeNode(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTables=ruleTables();

            state._fsp--;

             current =iv_ruleTables; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:961:1: ruleTables returns [EObject current=null] : (this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) ;
    public final EObject ruleTables() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_FromTable_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:964:28: ( (this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) )
            // InternalSqlParser.g:965:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            {
            // InternalSqlParser.g:965:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            // InternalSqlParser.g:966:5: this_FromTable_0= ruleFromTable ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_FromTable_0=ruleFromTable();

            state._fsp--;


                    current = this_FromTable_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:974:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==Comma) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalSqlParser.g:974:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+
                    {
                    // InternalSqlParser.g:974:2: ()
                    // InternalSqlParser.g:975:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:980:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) ) )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==Comma) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // InternalSqlParser.g:981:2: otherlv_2= Comma ( (lv_entries_3_0= ruleFromTable ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_15); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:985:1: ( (lv_entries_3_0= ruleFromTable ) )
                    	    // InternalSqlParser.g:986:1: (lv_entries_3_0= ruleFromTable )
                    	    {
                    	    // InternalSqlParser.g:986:1: (lv_entries_3_0= ruleFromTable )
                    	    // InternalSqlParser.g:987:3: lv_entries_3_0= ruleFromTable
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleFromTable();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt28 >= 1 ) break loop28;
                                EarlyExitException eee =
                                    new EarlyExitException(28, input);
                                throw eee;
                        }
                        cnt28++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1011:1: entryRuleFromTable returns [EObject current=null] : iv_ruleFromTable= ruleFromTable EOF ;
    public final EObject entryRuleFromTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTable = null;


        try {
            // InternalSqlParser.g:1012:2: (iv_ruleFromTable= ruleFromTable EOF )
            // InternalSqlParser.g:1013:2: iv_ruleFromTable= ruleFromTable EOF
            {
             newCompositeNode(grammarAccess.getFromTableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFromTable=ruleFromTable();

            state._fsp--;

             current =iv_ruleFromTable; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1020:1: ruleFromTable returns [EObject current=null] : ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) ;
    public final EObject ruleFromTable() throws RecognitionException {
        EObject current = null;

        EObject lv_table_0_0 = null;

        EObject lv_fjoin_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1023:28: ( ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) )
            // InternalSqlParser.g:1024:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            {
            // InternalSqlParser.g:1024:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            // InternalSqlParser.g:1024:2: ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            {
            // InternalSqlParser.g:1024:2: ( (lv_table_0_0= ruleTableOrAlias ) )
            // InternalSqlParser.g:1025:1: (lv_table_0_0= ruleTableOrAlias )
            {
            // InternalSqlParser.g:1025:1: (lv_table_0_0= ruleTableOrAlias )
            // InternalSqlParser.g:1026:3: lv_table_0_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_32);
            lv_table_0_0=ruleTableOrAlias();

            state._fsp--;


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

            // InternalSqlParser.g:1042:2: ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==STRAIGHT_JOIN||LA30_0==NATURAL||LA30_0==CROSS||LA30_0==INNER||LA30_0==RIGHT||LA30_0==FULL||LA30_0==JOIN||LA30_0==LEFT) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalSqlParser.g:1043:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    {
            	    // InternalSqlParser.g:1043:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    // InternalSqlParser.g:1044:3: lv_fjoin_1_0= ruleFromTableJoin
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFromTableAccess().getFjoinFromTableJoinParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_32);
            	    lv_fjoin_1_0=ruleFromTableJoin();

            	    state._fsp--;


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
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1068:1: entryRuleFromTableJoin returns [EObject current=null] : iv_ruleFromTableJoin= ruleFromTableJoin EOF ;
    public final EObject entryRuleFromTableJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTableJoin = null;


        try {
            // InternalSqlParser.g:1069:2: (iv_ruleFromTableJoin= ruleFromTableJoin EOF )
            // InternalSqlParser.g:1070:2: iv_ruleFromTableJoin= ruleFromTableJoin EOF
            {
             newCompositeNode(grammarAccess.getFromTableJoinRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFromTableJoin=ruleFromTableJoin();

            state._fsp--;

             current =iv_ruleFromTableJoin; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1077:1: ruleFromTableJoin returns [EObject current=null] : ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) ) ) ;
    public final EObject ruleFromTableJoin() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_join_0_0 = null;

        EObject lv_onTable_1_0 = null;

        EObject lv_joinExpr_3_0 = null;

        EObject lv_joinCond_4_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1080:28: ( ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) ) ) )
            // InternalSqlParser.g:1081:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) ) )
            {
            // InternalSqlParser.g:1081:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) ) )
            // InternalSqlParser.g:1081:2: ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) )
            {
            // InternalSqlParser.g:1081:2: ( (lv_join_0_0= ruleJoinType ) )
            // InternalSqlParser.g:1082:1: (lv_join_0_0= ruleJoinType )
            {
            // InternalSqlParser.g:1082:1: (lv_join_0_0= ruleJoinType )
            // InternalSqlParser.g:1083:3: lv_join_0_0= ruleJoinType
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinJoinTypeParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_15);
            lv_join_0_0=ruleJoinType();

            state._fsp--;


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

            // InternalSqlParser.g:1099:2: ( (lv_onTable_1_0= ruleTableOrAlias ) )
            // InternalSqlParser.g:1100:1: (lv_onTable_1_0= ruleTableOrAlias )
            {
            // InternalSqlParser.g:1100:1: (lv_onTable_1_0= ruleTableOrAlias )
            // InternalSqlParser.g:1101:3: lv_onTable_1_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getOnTableTableOrAliasParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_33);
            lv_onTable_1_0=ruleTableOrAlias();

            state._fsp--;


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

            // InternalSqlParser.g:1117:2: ( (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) | ( (lv_joinCond_4_0= ruleJoinCondition ) ) )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==ON) ) {
                alt31=1;
            }
            else if ( (LA31_0==USING) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // InternalSqlParser.g:1117:3: (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
                    {
                    // InternalSqlParser.g:1117:3: (otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
                    // InternalSqlParser.g:1118:2: otherlv_2= ON ( (lv_joinExpr_3_0= ruleFullExpression ) )
                    {
                    otherlv_2=(Token)match(input,ON,FOLLOW_17); 

                        	newLeafNode(otherlv_2, grammarAccess.getFromTableJoinAccess().getONKeyword_2_0_0());
                        
                    // InternalSqlParser.g:1122:1: ( (lv_joinExpr_3_0= ruleFullExpression ) )
                    // InternalSqlParser.g:1123:1: (lv_joinExpr_3_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:1123:1: (lv_joinExpr_3_0= ruleFullExpression )
                    // InternalSqlParser.g:1124:3: lv_joinExpr_3_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinExprFullExpressionParserRuleCall_2_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_joinExpr_3_0=ruleFullExpression();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:1141:6: ( (lv_joinCond_4_0= ruleJoinCondition ) )
                    {
                    // InternalSqlParser.g:1141:6: ( (lv_joinCond_4_0= ruleJoinCondition ) )
                    // InternalSqlParser.g:1142:1: (lv_joinCond_4_0= ruleJoinCondition )
                    {
                    // InternalSqlParser.g:1142:1: (lv_joinCond_4_0= ruleJoinCondition )
                    // InternalSqlParser.g:1143:3: lv_joinCond_4_0= ruleJoinCondition
                    {
                     
                    	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinCondJoinConditionParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_joinCond_4_0=ruleJoinCondition();

                    state._fsp--;


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
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1167:1: entryRuleJoinCondition returns [EObject current=null] : iv_ruleJoinCondition= ruleJoinCondition EOF ;
    public final EObject entryRuleJoinCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJoinCondition = null;


        try {
            // InternalSqlParser.g:1168:2: (iv_ruleJoinCondition= ruleJoinCondition EOF )
            // InternalSqlParser.g:1169:2: iv_ruleJoinCondition= ruleJoinCondition EOF
            {
             newCompositeNode(grammarAccess.getJoinConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleJoinCondition=ruleJoinCondition();

            state._fsp--;

             current =iv_ruleJoinCondition; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1176:1: ruleJoinCondition returns [EObject current=null] : (otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleJoinCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_useCols_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1179:28: ( (otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:1180:1: (otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:1180:1: (otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:1181:2: otherlv_0= USING otherlv_1= LeftParenthesis ( (lv_useCols_2_0= ruleUsingCols ) ) otherlv_3= RightParenthesis
            {
            otherlv_0=(Token)match(input,USING,FOLLOW_34); 

                	newLeafNode(otherlv_0, grammarAccess.getJoinConditionAccess().getUSINGKeyword_0());
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_31); 

                	newLeafNode(otherlv_1, grammarAccess.getJoinConditionAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:1190:1: ( (lv_useCols_2_0= ruleUsingCols ) )
            // InternalSqlParser.g:1191:1: (lv_useCols_2_0= ruleUsingCols )
            {
            // InternalSqlParser.g:1191:1: (lv_useCols_2_0= ruleUsingCols )
            // InternalSqlParser.g:1192:3: lv_useCols_2_0= ruleUsingCols
            {
             
            	        newCompositeNode(grammarAccess.getJoinConditionAccess().getUseColsUsingColsParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_useCols_2_0=ruleUsingCols();

            state._fsp--;


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

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_3, grammarAccess.getJoinConditionAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1221:1: entryRuleUsingCols returns [EObject current=null] : iv_ruleUsingCols= ruleUsingCols EOF ;
    public final EObject entryRuleUsingCols() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUsingCols = null;


        try {
            // InternalSqlParser.g:1222:2: (iv_ruleUsingCols= ruleUsingCols EOF )
            // InternalSqlParser.g:1223:2: iv_ruleUsingCols= ruleUsingCols EOF
            {
             newCompositeNode(grammarAccess.getUsingColsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUsingCols=ruleUsingCols();

            state._fsp--;

             current =iv_ruleUsingCols; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1230:1: ruleUsingCols returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleUsingCols() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1233:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:1234:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:1234:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:1235:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getUsingColsAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:1243:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==Comma) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalSqlParser.g:1243:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:1243:2: ()
                    // InternalSqlParser.g:1244:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getUsingColsAccess().getUsingColsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:1249:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt32=0;
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==Comma) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // InternalSqlParser.g:1250:2: otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_31); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getUsingColsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:1254:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:1255:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:1255:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:1256:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getUsingColsAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt32 >= 1 ) break loop32;
                                EarlyExitException eee =
                                    new EarlyExitException(32, input);
                                throw eee;
                        }
                        cnt32++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1280:1: entryRuleTableOrAlias returns [EObject current=null] : iv_ruleTableOrAlias= ruleTableOrAlias EOF ;
    public final EObject entryRuleTableOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableOrAlias = null;


        try {
            // InternalSqlParser.g:1281:2: (iv_ruleTableOrAlias= ruleTableOrAlias EOF )
            // InternalSqlParser.g:1282:2: iv_ruleTableOrAlias= ruleTableOrAlias EOF
            {
             newCompositeNode(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;

             current =iv_ruleTableOrAlias; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1289:1: ruleTableOrAlias returns [EObject current=null] : ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= AS ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleTableOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_4_0=null;
        EObject lv_tfull_0_0 = null;

        EObject lv_sq_1_0 = null;

        EObject lv_pivot_2_0 = null;

        EObject lv_unpivot_3_0 = null;

        EObject lv_tblAlias_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1292:28: ( ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= AS ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? ) )
            // InternalSqlParser.g:1293:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= AS ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? )
            {
            // InternalSqlParser.g:1293:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= AS ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? )
            // InternalSqlParser.g:1293:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= AS ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )?
            {
            // InternalSqlParser.g:1293:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( ((LA34_0>=RULE_STRING && LA34_0<=RULE_ID)) ) {
                alt34=1;
            }
            else if ( (LA34_0==LeftParenthesis) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // InternalSqlParser.g:1293:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    {
                    // InternalSqlParser.g:1293:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    // InternalSqlParser.g:1294:1: (lv_tfull_0_0= ruleTableFull )
                    {
                    // InternalSqlParser.g:1294:1: (lv_tfull_0_0= ruleTableFull )
                    // InternalSqlParser.g:1295:3: lv_tfull_0_0= ruleTableFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_36);
                    lv_tfull_0_0=ruleTableFull();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:1312:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:1312:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:1313:1: (lv_sq_1_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:1313:1: (lv_sq_1_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:1314:3: lv_sq_1_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getSqSubQueryOperandParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_36);
                    lv_sq_1_0=ruleSubQueryOperand();

                    state._fsp--;


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
                    break;

            }

            // InternalSqlParser.g:1330:3: ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )?
            int alt35=3;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==PIVOT) ) {
                alt35=1;
            }
            else if ( (LA35_0==UNPIVOT) ) {
                alt35=2;
            }
            switch (alt35) {
                case 1 :
                    // InternalSqlParser.g:1330:4: ( (lv_pivot_2_0= rulePivotTable ) )
                    {
                    // InternalSqlParser.g:1330:4: ( (lv_pivot_2_0= rulePivotTable ) )
                    // InternalSqlParser.g:1331:1: (lv_pivot_2_0= rulePivotTable )
                    {
                    // InternalSqlParser.g:1331:1: (lv_pivot_2_0= rulePivotTable )
                    // InternalSqlParser.g:1332:3: lv_pivot_2_0= rulePivotTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getPivotPivotTableParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_28);
                    lv_pivot_2_0=rulePivotTable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"pivot",
                            		lv_pivot_2_0, 
                            		"com.jaspersoft.studio.data.Sql.PivotTable");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:1349:6: ( (lv_unpivot_3_0= ruleUnpivotTable ) )
                    {
                    // InternalSqlParser.g:1349:6: ( (lv_unpivot_3_0= ruleUnpivotTable ) )
                    // InternalSqlParser.g:1350:1: (lv_unpivot_3_0= ruleUnpivotTable )
                    {
                    // InternalSqlParser.g:1350:1: (lv_unpivot_3_0= ruleUnpivotTable )
                    // InternalSqlParser.g:1351:3: lv_unpivot_3_0= ruleUnpivotTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getUnpivotUnpivotTableParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_28);
                    lv_unpivot_3_0=ruleUnpivotTable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"unpivot",
                            		lv_unpivot_3_0, 
                            		"com.jaspersoft.studio.data.Sql.UnpivotTable");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:1367:4: ( (lv_alias_4_0= AS ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==AS) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalSqlParser.g:1368:1: (lv_alias_4_0= AS )
                    {
                    // InternalSqlParser.g:1368:1: (lv_alias_4_0= AS )
                    // InternalSqlParser.g:1369:3: lv_alias_4_0= AS
                    {
                    lv_alias_4_0=(Token)match(input,AS,FOLLOW_29); 

                            newLeafNode(lv_alias_4_0, grammarAccess.getTableOrAliasAccess().getAliasASKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTableOrAliasRule());
                    	        }
                           		setWithLastConsumed(current, "alias", lv_alias_4_0, "AS");
                    	    

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:1383:3: ( (lv_tblAlias_5_0= ruleDbObjectName ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( ((LA37_0>=RULE_STRING && LA37_0<=RULE_ID)) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalSqlParser.g:1384:1: (lv_tblAlias_5_0= ruleDbObjectName )
                    {
                    // InternalSqlParser.g:1384:1: (lv_tblAlias_5_0= ruleDbObjectName )
                    // InternalSqlParser.g:1385:3: lv_tblAlias_5_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_tblAlias_5_0=ruleDbObjectName();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"tblAlias",
                            		lv_tblAlias_5_0, 
                            		"com.jaspersoft.studio.data.Sql.DbObjectName");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRulePivotTable"
    // InternalSqlParser.g:1409:1: entryRulePivotTable returns [EObject current=null] : iv_rulePivotTable= rulePivotTable EOF ;
    public final EObject entryRulePivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotTable = null;


        try {
            // InternalSqlParser.g:1410:2: (iv_rulePivotTable= rulePivotTable EOF )
            // InternalSqlParser.g:1411:2: iv_rulePivotTable= rulePivotTable EOF
            {
             newCompositeNode(grammarAccess.getPivotTableRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePivotTable=rulePivotTable();

            state._fsp--;

             current =iv_rulePivotTable; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1418:1: rulePivotTable returns [EObject current=null] : (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis ) ;
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
            // InternalSqlParser.g:1421:28: ( (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis ) )
            // InternalSqlParser.g:1422:1: (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis )
            {
            // InternalSqlParser.g:1422:1: (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis )
            // InternalSqlParser.g:1423:2: otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis
            {
            otherlv_0=(Token)match(input,PIVOT,FOLLOW_37); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotTableAccess().getPIVOTKeyword_0());
                
            // InternalSqlParser.g:1427:1: (otherlv_1= XML )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==XML) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalSqlParser.g:1428:2: otherlv_1= XML
                    {
                    otherlv_1=(Token)match(input,XML,FOLLOW_34); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotTableAccess().getXMLKeyword_1());
                        

                    }
                    break;

            }

            otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_38); 

                	newLeafNode(otherlv_2, grammarAccess.getPivotTableAccess().getLeftParenthesisKeyword_2());
                
            // InternalSqlParser.g:1437:1: ( (lv_pfun_3_0= rulePivotFunctions ) )
            // InternalSqlParser.g:1438:1: (lv_pfun_3_0= rulePivotFunctions )
            {
            // InternalSqlParser.g:1438:1: (lv_pfun_3_0= rulePivotFunctions )
            // InternalSqlParser.g:1439:3: lv_pfun_3_0= rulePivotFunctions
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPfunPivotFunctionsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_39);
            lv_pfun_3_0=rulePivotFunctions();

            state._fsp--;


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

            // InternalSqlParser.g:1455:2: ( (lv_pfor_4_0= rulePivotForClause ) )
            // InternalSqlParser.g:1456:1: (lv_pfor_4_0= rulePivotForClause )
            {
            // InternalSqlParser.g:1456:1: (lv_pfor_4_0= rulePivotForClause )
            // InternalSqlParser.g:1457:3: lv_pfor_4_0= rulePivotForClause
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_40);
            lv_pfor_4_0=rulePivotForClause();

            state._fsp--;


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

            // InternalSqlParser.g:1473:2: ( (lv_pin_5_0= rulePivotInClause ) )
            // InternalSqlParser.g:1474:1: (lv_pin_5_0= rulePivotInClause )
            {
            // InternalSqlParser.g:1474:1: (lv_pin_5_0= rulePivotInClause )
            // InternalSqlParser.g:1475:3: lv_pin_5_0= rulePivotInClause
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPinPivotInClauseParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_pin_5_0=rulePivotInClause();

            state._fsp--;


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

            otherlv_6=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_6, grammarAccess.getPivotTableAccess().getRightParenthesisKeyword_6());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1504:1: entryRulePivotFunctions returns [EObject current=null] : iv_rulePivotFunctions= rulePivotFunctions EOF ;
    public final EObject entryRulePivotFunctions() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotFunctions = null;


        try {
            // InternalSqlParser.g:1505:2: (iv_rulePivotFunctions= rulePivotFunctions EOF )
            // InternalSqlParser.g:1506:2: iv_rulePivotFunctions= rulePivotFunctions EOF
            {
             newCompositeNode(grammarAccess.getPivotFunctionsRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePivotFunctions=rulePivotFunctions();

            state._fsp--;

             current =iv_rulePivotFunctions; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1513:1: rulePivotFunctions returns [EObject current=null] : ( (lv_abc_0_0= RULE_ID ) ) ;
    public final EObject rulePivotFunctions() throws RecognitionException {
        EObject current = null;

        Token lv_abc_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:1516:28: ( ( (lv_abc_0_0= RULE_ID ) ) )
            // InternalSqlParser.g:1517:1: ( (lv_abc_0_0= RULE_ID ) )
            {
            // InternalSqlParser.g:1517:1: ( (lv_abc_0_0= RULE_ID ) )
            // InternalSqlParser.g:1518:1: (lv_abc_0_0= RULE_ID )
            {
            // InternalSqlParser.g:1518:1: (lv_abc_0_0= RULE_ID )
            // InternalSqlParser.g:1519:3: lv_abc_0_0= RULE_ID
            {
            lv_abc_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            			newLeafNode(lv_abc_0_0, grammarAccess.getPivotFunctionsAccess().getAbcIDTerminalRuleCall_0()); 
            		

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

             leaveRule(); 
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
    // InternalSqlParser.g:1545:1: entryRulePivotInClause returns [EObject current=null] : iv_rulePivotInClause= rulePivotInClause EOF ;
    public final EObject entryRulePivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotInClause = null;


        try {
            // InternalSqlParser.g:1546:2: (iv_rulePivotInClause= rulePivotInClause EOF )
            // InternalSqlParser.g:1547:2: iv_rulePivotInClause= rulePivotInClause EOF
            {
             newCompositeNode(grammarAccess.getPivotInClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePivotInClause=rulePivotInClause();

            state._fsp--;

             current =iv_rulePivotInClause; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1554:1: rulePivotInClause returns [EObject current=null] : (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis ) ;
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
            // InternalSqlParser.g:1557:28: ( (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis ) )
            // InternalSqlParser.g:1558:1: (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis )
            {
            // InternalSqlParser.g:1558:1: (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis )
            // InternalSqlParser.g:1559:2: otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis
            {
            otherlv_0=(Token)match(input,IN,FOLLOW_34); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotInClauseAccess().getINKeyword_0());
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_41); 

                	newLeafNode(otherlv_1, grammarAccess.getPivotInClauseAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:1568:1: ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) )
            int alt39=3;
            switch ( input.LA(1) ) {
            case LeftParenthesis:
                {
                int LA39_1 = input.LA(2);

                if ( ((LA39_1>=RULE_STRING && LA39_1<=RULE_ID)) ) {
                    alt39=2;
                }
                else if ( (LA39_1==SELECT) ) {
                    alt39=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 39, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt39=2;
                }
                break;
            case ANY:
                {
                alt39=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // InternalSqlParser.g:1568:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:1568:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:1569:1: (lv_sq_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:1569:1: (lv_sq_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:1570:3: lv_sq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getSqSubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_35);
                    lv_sq_2_0=ruleSubQueryOperand();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:1587:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    {
                    // InternalSqlParser.g:1587:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    // InternalSqlParser.g:1588:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    {
                    // InternalSqlParser.g:1588:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    // InternalSqlParser.g:1589:3: lv_args_3_0= ruleUnpivotInClauseArgs
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_35);
                    lv_args_3_0=ruleUnpivotInClauseArgs();

                    state._fsp--;


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
                    break;
                case 3 :
                    // InternalSqlParser.g:1606:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    {
                    // InternalSqlParser.g:1606:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    // InternalSqlParser.g:1607:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    {
                    // InternalSqlParser.g:1607:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    // InternalSqlParser.g:1608:3: lv_pinany_4_0= rulePivotInClauseAny
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getPinanyPivotInClauseAnyParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FOLLOW_35);
                    lv_pinany_4_0=rulePivotInClauseAny();

                    state._fsp--;


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
                    break;

            }

            otherlv_5=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_5, grammarAccess.getPivotInClauseAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1637:1: entryRulePivotInClauseAny returns [String current=null] : iv_rulePivotInClauseAny= rulePivotInClauseAny EOF ;
    public final String entryRulePivotInClauseAny() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePivotInClauseAny = null;


        try {
            // InternalSqlParser.g:1638:1: (iv_rulePivotInClauseAny= rulePivotInClauseAny EOF )
            // InternalSqlParser.g:1639:2: iv_rulePivotInClauseAny= rulePivotInClauseAny EOF
            {
             newCompositeNode(grammarAccess.getPivotInClauseAnyRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePivotInClauseAny=rulePivotInClauseAny();

            state._fsp--;

             current =iv_rulePivotInClauseAny.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1646:1: rulePivotInClauseAny returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= ANY (kw= Comma kw= ANY )* ) ;
    public final AntlrDatatypeRuleToken rulePivotInClauseAny() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:1650:6: ( (kw= ANY (kw= Comma kw= ANY )* ) )
            // InternalSqlParser.g:1651:1: (kw= ANY (kw= Comma kw= ANY )* )
            {
            // InternalSqlParser.g:1651:1: (kw= ANY (kw= Comma kw= ANY )* )
            // InternalSqlParser.g:1652:2: kw= ANY (kw= Comma kw= ANY )*
            {
            kw=(Token)match(input,ANY,FOLLOW_6); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_0()); 
                
            // InternalSqlParser.g:1657:1: (kw= Comma kw= ANY )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==Comma) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalSqlParser.g:1658:2: kw= Comma kw= ANY
            	    {
            	    kw=(Token)match(input,Comma,FOLLOW_42); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getCommaKeyword_1_0()); 
            	        
            	    kw=(Token)match(input,ANY,FOLLOW_6); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            }


            }

             leaveRule();
                
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
    // InternalSqlParser.g:1677:1: entryRuleUnpivotTable returns [EObject current=null] : iv_ruleUnpivotTable= ruleUnpivotTable EOF ;
    public final EObject entryRuleUnpivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotTable = null;


        try {
            // InternalSqlParser.g:1678:2: (iv_ruleUnpivotTable= ruleUnpivotTable EOF )
            // InternalSqlParser.g:1679:2: iv_ruleUnpivotTable= ruleUnpivotTable EOF
            {
             newCompositeNode(grammarAccess.getUnpivotTableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnpivotTable=ruleUnpivotTable();

            state._fsp--;

             current =iv_ruleUnpivotTable; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1686:1: ruleUnpivotTable returns [EObject current=null] : (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis ) ;
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
            // InternalSqlParser.g:1689:28: ( (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis ) )
            // InternalSqlParser.g:1690:1: (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis )
            {
            // InternalSqlParser.g:1690:1: (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis )
            // InternalSqlParser.g:1691:2: otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis
            {
            otherlv_0=(Token)match(input,UNPIVOT,FOLLOW_43); 

                	newLeafNode(otherlv_0, grammarAccess.getUnpivotTableAccess().getUNPIVOTKeyword_0());
                
            // InternalSqlParser.g:1695:1: ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==EXCLUDE||LA42_0==INCLUDE) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalSqlParser.g:1695:2: (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS
                    {
                    // InternalSqlParser.g:1695:2: (otherlv_1= INCLUDE | otherlv_2= EXCLUDE )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==INCLUDE) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==EXCLUDE) ) {
                        alt41=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;
                    }
                    switch (alt41) {
                        case 1 :
                            // InternalSqlParser.g:1696:2: otherlv_1= INCLUDE
                            {
                            otherlv_1=(Token)match(input,INCLUDE,FOLLOW_44); 

                                	newLeafNode(otherlv_1, grammarAccess.getUnpivotTableAccess().getINCLUDEKeyword_1_0_0());
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:1702:2: otherlv_2= EXCLUDE
                            {
                            otherlv_2=(Token)match(input,EXCLUDE,FOLLOW_44); 

                                	newLeafNode(otherlv_2, grammarAccess.getUnpivotTableAccess().getEXCLUDEKeyword_1_0_1());
                                

                            }
                            break;

                    }

                    otherlv_3=(Token)match(input,NULLS,FOLLOW_34); 

                        	newLeafNode(otherlv_3, grammarAccess.getUnpivotTableAccess().getNULLSKeyword_1_1());
                        

                    }
                    break;

            }

            otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_15); 

                	newLeafNode(otherlv_4, grammarAccess.getUnpivotTableAccess().getLeftParenthesisKeyword_2());
                
            // InternalSqlParser.g:1716:1: ( (lv_pcols_5_0= rulePivotColumns ) )
            // InternalSqlParser.g:1717:1: (lv_pcols_5_0= rulePivotColumns )
            {
            // InternalSqlParser.g:1717:1: (lv_pcols_5_0= rulePivotColumns )
            // InternalSqlParser.g:1718:3: lv_pcols_5_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPcolsPivotColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_39);
            lv_pcols_5_0=rulePivotColumns();

            state._fsp--;


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

            // InternalSqlParser.g:1734:2: ( (lv_pfor_6_0= rulePivotForClause ) )
            // InternalSqlParser.g:1735:1: (lv_pfor_6_0= rulePivotForClause )
            {
            // InternalSqlParser.g:1735:1: (lv_pfor_6_0= rulePivotForClause )
            // InternalSqlParser.g:1736:3: lv_pfor_6_0= rulePivotForClause
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_40);
            lv_pfor_6_0=rulePivotForClause();

            state._fsp--;


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

            // InternalSqlParser.g:1752:2: ( (lv_inop_7_0= ruleUnpivotInClause ) )
            // InternalSqlParser.g:1753:1: (lv_inop_7_0= ruleUnpivotInClause )
            {
            // InternalSqlParser.g:1753:1: (lv_inop_7_0= ruleUnpivotInClause )
            // InternalSqlParser.g:1754:3: lv_inop_7_0= ruleUnpivotInClause
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getInopUnpivotInClauseParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_inop_7_0=ruleUnpivotInClause();

            state._fsp--;


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

            otherlv_8=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_8, grammarAccess.getUnpivotTableAccess().getRightParenthesisKeyword_6());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1783:1: entryRuleUnpivotInClause returns [EObject current=null] : iv_ruleUnpivotInClause= ruleUnpivotInClause EOF ;
    public final EObject entryRuleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClause = null;


        try {
            // InternalSqlParser.g:1784:2: (iv_ruleUnpivotInClause= ruleUnpivotInClause EOF )
            // InternalSqlParser.g:1785:2: iv_ruleUnpivotInClause= ruleUnpivotInClause EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnpivotInClause=ruleUnpivotInClause();

            state._fsp--;

             current =iv_ruleUnpivotInClause; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1792:1: ruleUnpivotInClause returns [EObject current=null] : ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis ) ;
    public final EObject ruleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1795:28: ( ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis ) )
            // InternalSqlParser.g:1796:1: ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis )
            {
            // InternalSqlParser.g:1796:1: ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis )
            // InternalSqlParser.g:1796:2: () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis
            {
            // InternalSqlParser.g:1796:2: ()
            // InternalSqlParser.g:1797:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getUnpivotInClauseAccess().getUnipivotInClauseAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:1802:2: ( (lv_op_1_0= IN ) )
            // InternalSqlParser.g:1803:1: (lv_op_1_0= IN )
            {
            // InternalSqlParser.g:1803:1: (lv_op_1_0= IN )
            // InternalSqlParser.g:1804:3: lv_op_1_0= IN
            {
            lv_op_1_0=(Token)match(input,IN,FOLLOW_34); 

                    newLeafNode(lv_op_1_0, grammarAccess.getUnpivotInClauseAccess().getOpINKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUnpivotInClauseRule());
            	        }
                   		setWithLastConsumed(current, "op", lv_op_1_0, "IN");
            	    

            }


            }

            otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_15); 

                	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseAccess().getLeftParenthesisKeyword_2());
                
            // InternalSqlParser.g:1823:1: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
            // InternalSqlParser.g:1824:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            {
            // InternalSqlParser.g:1824:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            // InternalSqlParser.g:1825:3: lv_args_3_0= ruleUnpivotInClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_args_3_0=ruleUnpivotInClauseArgs();

            state._fsp--;


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

            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_4, grammarAccess.getUnpivotInClauseAccess().getRightParenthesisKeyword_4());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1854:1: entryRuleUnpivotInClauseArgs returns [EObject current=null] : iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF ;
    public final EObject entryRuleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArgs = null;


        try {
            // InternalSqlParser.g:1855:2: (iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF )
            // InternalSqlParser.g:1856:2: iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseArgsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnpivotInClauseArgs=ruleUnpivotInClauseArgs();

            state._fsp--;

             current =iv_ruleUnpivotInClauseArgs; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1863:1: ruleUnpivotInClauseArgs returns [EObject current=null] : (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) ;
    public final EObject ruleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_UnpivotInClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1866:28: ( (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) )
            // InternalSqlParser.g:1867:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            {
            // InternalSqlParser.g:1867:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            // InternalSqlParser.g:1868:5: this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getUnpivotInClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_UnpivotInClauseArg_0=ruleUnpivotInClauseArg();

            state._fsp--;


                    current = this_UnpivotInClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:1876:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==Comma) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalSqlParser.g:1876:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    {
                    // InternalSqlParser.g:1876:2: ()
                    // InternalSqlParser.g:1877:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getUnpivotInClauseArgsAccess().getUicargsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:1882:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    int cnt43=0;
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==Comma) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // InternalSqlParser.g:1883:2: otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_15); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:1887:1: ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    // InternalSqlParser.g:1888:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    {
                    	    // InternalSqlParser.g:1888:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    // InternalSqlParser.g:1889:3: lv_entries_3_0= ruleUnpivotInClauseArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getEntriesUnpivotInClauseArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleUnpivotInClauseArg();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt43 >= 1 ) break loop43;
                                EarlyExitException eee =
                                    new EarlyExitException(43, input);
                                throw eee;
                        }
                        cnt43++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1913:1: entryRuleUnpivotInClauseArg returns [EObject current=null] : iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF ;
    public final EObject entryRuleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArg = null;


        try {
            // InternalSqlParser.g:1914:2: (iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF )
            // InternalSqlParser.g:1915:2: iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseArgRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnpivotInClauseArg=ruleUnpivotInClauseArg();

            state._fsp--;

             current =iv_ruleUnpivotInClauseArg; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1922:1: ruleUnpivotInClauseArg returns [EObject current=null] : ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) ;
    public final EObject ruleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_pcols_0_0 = null;

        EObject lv_cfuls_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1925:28: ( ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) )
            // InternalSqlParser.g:1926:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            {
            // InternalSqlParser.g:1926:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            // InternalSqlParser.g:1926:2: ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            {
            // InternalSqlParser.g:1926:2: ( (lv_pcols_0_0= rulePivotColumns ) )
            // InternalSqlParser.g:1927:1: (lv_pcols_0_0= rulePivotColumns )
            {
            // InternalSqlParser.g:1927:1: (lv_pcols_0_0= rulePivotColumns )
            // InternalSqlParser.g:1928:3: lv_pcols_0_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getPcolsPivotColumnsParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_45);
            lv_pcols_0_0=rulePivotColumns();

            state._fsp--;


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

            // InternalSqlParser.g:1944:2: (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==AS) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalSqlParser.g:1945:2: otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) )
                    {
                    otherlv_1=(Token)match(input,AS,FOLLOW_15); 

                        	newLeafNode(otherlv_1, grammarAccess.getUnpivotInClauseArgAccess().getASKeyword_1_0());
                        
                    // InternalSqlParser.g:1949:1: ( (lv_cfuls_2_0= rulePivotColumns ) )
                    // InternalSqlParser.g:1950:1: (lv_cfuls_2_0= rulePivotColumns )
                    {
                    // InternalSqlParser.g:1950:1: (lv_cfuls_2_0= rulePivotColumns )
                    // InternalSqlParser.g:1951:3: lv_cfuls_2_0= rulePivotColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getCfulsPivotColumnsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_cfuls_2_0=rulePivotColumns();

                    state._fsp--;


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
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:1975:1: entryRulePivotForClause returns [EObject current=null] : iv_rulePivotForClause= rulePivotForClause EOF ;
    public final EObject entryRulePivotForClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotForClause = null;


        try {
            // InternalSqlParser.g:1976:2: (iv_rulePivotForClause= rulePivotForClause EOF )
            // InternalSqlParser.g:1977:2: iv_rulePivotForClause= rulePivotForClause EOF
            {
             newCompositeNode(grammarAccess.getPivotForClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePivotForClause=rulePivotForClause();

            state._fsp--;

             current =iv_rulePivotForClause; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:1984:1: rulePivotForClause returns [EObject current=null] : (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) ) ;
    public final EObject rulePivotForClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ColumnFull_1 = null;

        EObject this_Columns_3 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1987:28: ( (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) ) )
            // InternalSqlParser.g:1988:1: (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) )
            {
            // InternalSqlParser.g:1988:1: (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) )
            // InternalSqlParser.g:1989:2: otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) )
            {
            otherlv_0=(Token)match(input,FOR,FOLLOW_15); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotForClauseAccess().getFORKeyword_0());
                
            // InternalSqlParser.g:1993:1: (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( ((LA46_0>=RULE_STRING && LA46_0<=RULE_ID)) ) {
                alt46=1;
            }
            else if ( (LA46_0==LeftParenthesis) ) {
                alt46=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // InternalSqlParser.g:1994:5: this_ColumnFull_1= ruleColumnFull
                    {
                     
                            newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_ColumnFull_1=ruleColumnFull();

                    state._fsp--;


                            current = this_ColumnFull_1;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:2003:6: (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis )
                    {
                    // InternalSqlParser.g:2003:6: (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis )
                    // InternalSqlParser.g:2004:2: otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis
                    {
                    otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_10); 

                        	newLeafNode(otherlv_2, grammarAccess.getPivotForClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_35);
                    this_Columns_3=ruleColumns();

                    state._fsp--;


                            current = this_Columns_3;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_2); 

                        	newLeafNode(otherlv_4, grammarAccess.getPivotForClauseAccess().getRightParenthesisKeyword_1_1_2());
                        

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2030:1: entryRulePivotColumns returns [EObject current=null] : iv_rulePivotColumns= rulePivotColumns EOF ;
    public final EObject entryRulePivotColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotColumns = null;


        try {
            // InternalSqlParser.g:2031:2: (iv_rulePivotColumns= rulePivotColumns EOF )
            // InternalSqlParser.g:2032:2: iv_rulePivotColumns= rulePivotColumns EOF
            {
             newCompositeNode(grammarAccess.getPivotColumnsRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePivotColumns=rulePivotColumns();

            state._fsp--;

             current =iv_rulePivotColumns; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2039:1: rulePivotColumns returns [EObject current=null] : (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) ) ;
    public final EObject rulePivotColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_PivotCol_0 = null;

        EObject this_PivotCols_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2042:28: ( (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) ) )
            // InternalSqlParser.g:2043:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) )
            {
            // InternalSqlParser.g:2043:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( ((LA47_0>=RULE_STRING && LA47_0<=RULE_ID)) ) {
                alt47=1;
            }
            else if ( (LA47_0==LeftParenthesis) ) {
                alt47=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // InternalSqlParser.g:2044:5: this_PivotCol_0= rulePivotCol
                    {
                     
                            newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_PivotCol_0=rulePivotCol();

                    state._fsp--;


                            current = this_PivotCol_0;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:2053:6: (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis )
                    {
                    // InternalSqlParser.g:2053:6: (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis )
                    // InternalSqlParser.g:2054:2: otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis
                    {
                    otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_31); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotColumnsAccess().getLeftParenthesisKeyword_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColsParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_35);
                    this_PivotCols_2=rulePivotCols();

                    state._fsp--;


                            current = this_PivotCols_2;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); 

                        	newLeafNode(otherlv_3, grammarAccess.getPivotColumnsAccess().getRightParenthesisKeyword_1_2());
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2080:1: entryRulePivotCols returns [EObject current=null] : iv_rulePivotCols= rulePivotCols EOF ;
    public final EObject entryRulePivotCols() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCols = null;


        try {
            // InternalSqlParser.g:2081:2: (iv_rulePivotCols= rulePivotCols EOF )
            // InternalSqlParser.g:2082:2: iv_rulePivotCols= rulePivotCols EOF
            {
             newCompositeNode(grammarAccess.getPivotColsRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePivotCols=rulePivotCols();

            state._fsp--;

             current =iv_rulePivotCols; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2089:1: rulePivotCols returns [EObject current=null] : (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) ;
    public final EObject rulePivotCols() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PivotCol_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2092:28: ( (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) )
            // InternalSqlParser.g:2093:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            {
            // InternalSqlParser.g:2093:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            // InternalSqlParser.g:2094:5: this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getPivotColsAccess().getPivotColParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_PivotCol_0=rulePivotCol();

            state._fsp--;


                    current = this_PivotCol_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2102:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==Comma) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalSqlParser.g:2102:2: () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+
                    {
                    // InternalSqlParser.g:2102:2: ()
                    // InternalSqlParser.g:2103:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getPivotColsAccess().getPvcsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2108:2: (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+
                    int cnt48=0;
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==Comma) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // InternalSqlParser.g:2109:2: otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_31); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2113:1: ( (lv_entries_3_0= rulePivotCol ) )
                    	    // InternalSqlParser.g:2114:1: (lv_entries_3_0= rulePivotCol )
                    	    {
                    	    // InternalSqlParser.g:2114:1: (lv_entries_3_0= rulePivotCol )
                    	    // InternalSqlParser.g:2115:3: lv_entries_3_0= rulePivotCol
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColsAccess().getEntriesPivotColParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=rulePivotCol();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt48 >= 1 ) break loop48;
                                EarlyExitException eee =
                                    new EarlyExitException(48, input);
                                throw eee;
                        }
                        cnt48++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2139:1: entryRulePivotCol returns [EObject current=null] : iv_rulePivotCol= rulePivotCol EOF ;
    public final EObject entryRulePivotCol() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCol = null;


        try {
            // InternalSqlParser.g:2140:2: (iv_rulePivotCol= rulePivotCol EOF )
            // InternalSqlParser.g:2141:2: iv_rulePivotCol= rulePivotCol EOF
            {
             newCompositeNode(grammarAccess.getPivotColRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePivotCol=rulePivotCol();

            state._fsp--;

             current =iv_rulePivotCol; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2148:1: rulePivotCol returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject rulePivotCol() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2151:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:2152:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:2152:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:2153:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getPivotColAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_30);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2161:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==FullStop) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalSqlParser.g:2161:2: () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:2161:2: ()
                    // InternalSqlParser.g:2162:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getPivotColAccess().getPcolsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2167:2: (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt50=0;
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==FullStop) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // InternalSqlParser.g:2168:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_31); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2172:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:2173:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:2173:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:2174:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_30);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt50 >= 1 ) break loop50;
                                EarlyExitException eee =
                                    new EarlyExitException(50, input);
                                throw eee;
                        }
                        cnt50++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2198:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // InternalSqlParser.g:2199:2: (iv_ruleTableFull= ruleTableFull EOF )
            // InternalSqlParser.g:2200:2: iv_ruleTableFull= ruleTableFull EOF
            {
             newCompositeNode(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;

             current =iv_ruleTableFull; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2207:1: ruleTableFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2210:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:2211:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:2211:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:2212:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_30);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2220:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==FullStop) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // InternalSqlParser.g:2220:2: () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:2220:2: ()
                    // InternalSqlParser.g:2221:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2226:2: (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt52=0;
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==FullStop) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // InternalSqlParser.g:2227:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_31); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2231:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:2232:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:2232:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:2233:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_30);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt52 >= 1 ) break loop52;
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

             leaveRule(); 
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
    // InternalSqlParser.g:2257:1: entryRuleDbObjectNameAll returns [EObject current=null] : iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF ;
    public final EObject entryRuleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectNameAll = null;


        try {
            // InternalSqlParser.g:2258:2: (iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF )
            // InternalSqlParser.g:2259:2: iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameAllRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDbObjectNameAll=ruleDbObjectNameAll();

            state._fsp--;

             current =iv_ruleDbObjectNameAll; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2266:1: ruleDbObjectNameAll returns [EObject current=null] : ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR ) ;
    public final EObject ruleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_STAR_2=null;
        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2269:28: ( ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR ) )
            // InternalSqlParser.g:2270:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR )
            {
            // InternalSqlParser.g:2270:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR )
            // InternalSqlParser.g:2270:2: ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR
            {
            // InternalSqlParser.g:2270:2: ( (lv_dbname_0_0= ruleDBID ) )
            // InternalSqlParser.g:2271:1: (lv_dbname_0_0= ruleDBID )
            {
            // InternalSqlParser.g:2271:1: (lv_dbname_0_0= ruleDBID )
            // InternalSqlParser.g:2272:3: lv_dbname_0_0= ruleDBID
            {
             
            	        newCompositeNode(grammarAccess.getDbObjectNameAllAccess().getDbnameDBIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_46);
            lv_dbname_0_0=ruleDBID();

            state._fsp--;


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

            otherlv_1=(Token)match(input,FullStop,FOLLOW_47); 

                	newLeafNode(otherlv_1, grammarAccess.getDbObjectNameAllAccess().getFullStopKeyword_1());
                
            this_STAR_2=(Token)match(input,RULE_STAR,FOLLOW_2); 
             
                newLeafNode(this_STAR_2, grammarAccess.getDbObjectNameAllAccess().getSTARTerminalRuleCall_2()); 
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2305:1: entryRuleDbObjectName returns [EObject current=null] : iv_ruleDbObjectName= ruleDbObjectName EOF ;
    public final EObject entryRuleDbObjectName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectName = null;


        try {
            // InternalSqlParser.g:2306:2: (iv_ruleDbObjectName= ruleDbObjectName EOF )
            // InternalSqlParser.g:2307:2: iv_ruleDbObjectName= ruleDbObjectName EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDbObjectName=ruleDbObjectName();

            state._fsp--;

             current =iv_ruleDbObjectName; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2314:1: ruleDbObjectName returns [EObject current=null] : ( (lv_dbname_0_0= ruleDBID ) ) ;
    public final EObject ruleDbObjectName() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2317:28: ( ( (lv_dbname_0_0= ruleDBID ) ) )
            // InternalSqlParser.g:2318:1: ( (lv_dbname_0_0= ruleDBID ) )
            {
            // InternalSqlParser.g:2318:1: ( (lv_dbname_0_0= ruleDBID ) )
            // InternalSqlParser.g:2319:1: (lv_dbname_0_0= ruleDBID )
            {
            // InternalSqlParser.g:2319:1: (lv_dbname_0_0= ruleDBID )
            // InternalSqlParser.g:2320:3: lv_dbname_0_0= ruleDBID
            {
             
            	        newCompositeNode(grammarAccess.getDbObjectNameAccess().getDbnameDBIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_dbname_0_0=ruleDBID();

            state._fsp--;


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

             leaveRule(); 
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
    // InternalSqlParser.g:2344:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // InternalSqlParser.g:2345:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // InternalSqlParser.g:2346:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;

             current =iv_ruleOrderByColumns; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2353:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2356:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // InternalSqlParser.g:2357:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // InternalSqlParser.g:2357:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // InternalSqlParser.g:2358:5: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;


                    current = this_OrderByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2366:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==Comma) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // InternalSqlParser.g:2366:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // InternalSqlParser.g:2366:2: ()
                    // InternalSqlParser.g:2367:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2372:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt54=0;
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==Comma) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // InternalSqlParser.g:2373:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_19); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2377:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // InternalSqlParser.g:2378:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // InternalSqlParser.g:2378:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // InternalSqlParser.g:2379:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleOrderByColumnFull();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt54 >= 1 ) break loop54;
                                EarlyExitException eee =
                                    new EarlyExitException(54, input);
                                throw eee;
                        }
                        cnt54++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2403:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // InternalSqlParser.g:2404:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // InternalSqlParser.g:2405:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;

             current =iv_ruleOrderByColumnFull; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2412:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_colOrderInt_1_0=null;
        Token lv_direction_2_1=null;
        Token lv_direction_2_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2415:28: ( ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? ) )
            // InternalSqlParser.g:2416:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? )
            {
            // InternalSqlParser.g:2416:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? )
            // InternalSqlParser.g:2416:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )?
            {
            // InternalSqlParser.g:2416:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( ((LA56_0>=RULE_STRING && LA56_0<=RULE_ID)) ) {
                alt56=1;
            }
            else if ( (LA56_0==RULE_UNSIGNED) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // InternalSqlParser.g:2416:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    {
                    // InternalSqlParser.g:2416:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    // InternalSqlParser.g:2417:1: (lv_colOrder_0_0= ruleColumnFull )
                    {
                    // InternalSqlParser.g:2417:1: (lv_colOrder_0_0= ruleColumnFull )
                    // InternalSqlParser.g:2418:3: lv_colOrder_0_0= ruleColumnFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_48);
                    lv_colOrder_0_0=ruleColumnFull();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:2435:6: ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:2435:6: ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:2436:1: (lv_colOrderInt_1_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:2436:1: (lv_colOrderInt_1_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:2437:3: lv_colOrderInt_1_0= RULE_UNSIGNED
                    {
                    lv_colOrderInt_1_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_48); 

                    			newLeafNode(lv_colOrderInt_1_0, grammarAccess.getOrderByColumnFullAccess().getColOrderIntUNSIGNEDTerminalRuleCall_0_1_0()); 
                    		

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
                    break;

            }

            // InternalSqlParser.g:2453:3: ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==DESC||LA58_0==ASC) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalSqlParser.g:2454:1: ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) )
                    {
                    // InternalSqlParser.g:2454:1: ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) )
                    // InternalSqlParser.g:2455:1: (lv_direction_2_1= ASC | lv_direction_2_2= DESC )
                    {
                    // InternalSqlParser.g:2455:1: (lv_direction_2_1= ASC | lv_direction_2_2= DESC )
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==ASC) ) {
                        alt57=1;
                    }
                    else if ( (LA57_0==DESC) ) {
                        alt57=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 57, 0, input);

                        throw nvae;
                    }
                    switch (alt57) {
                        case 1 :
                            // InternalSqlParser.g:2456:3: lv_direction_2_1= ASC
                            {
                            lv_direction_2_1=(Token)match(input,ASC,FOLLOW_2); 

                                    newLeafNode(lv_direction_2_1, grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                            	        }
                                   		setWithLastConsumed(current, "direction", lv_direction_2_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:2469:8: lv_direction_2_2= DESC
                            {
                            lv_direction_2_2=(Token)match(input,DESC,FOLLOW_2); 

                                    newLeafNode(lv_direction_2_2, grammarAccess.getOrderByColumnFullAccess().getDirectionDESCKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                            	        }
                                   		setWithLastConsumed(current, "direction", lv_direction_2_2, null);
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2493:1: entryRuleGroupByColumns returns [EObject current=null] : iv_ruleGroupByColumns= ruleGroupByColumns EOF ;
    public final EObject entryRuleGroupByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumns = null;


        try {
            // InternalSqlParser.g:2494:2: (iv_ruleGroupByColumns= ruleGroupByColumns EOF )
            // InternalSqlParser.g:2495:2: iv_ruleGroupByColumns= ruleGroupByColumns EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGroupByColumns=ruleGroupByColumns();

            state._fsp--;

             current =iv_ruleGroupByColumns; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2502:1: ruleGroupByColumns returns [EObject current=null] : (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) ;
    public final EObject ruleGroupByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_GroupByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2505:28: ( (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) )
            // InternalSqlParser.g:2506:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            {
            // InternalSqlParser.g:2506:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            // InternalSqlParser.g:2507:5: this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_GroupByColumnFull_0=ruleGroupByColumnFull();

            state._fsp--;


                    current = this_GroupByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2515:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==Comma) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalSqlParser.g:2515:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    {
                    // InternalSqlParser.g:2515:2: ()
                    // InternalSqlParser.g:2516:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2521:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    int cnt59=0;
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==Comma) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // InternalSqlParser.g:2522:2: otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_19); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2526:1: ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    // InternalSqlParser.g:2527:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    {
                    	    // InternalSqlParser.g:2527:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    // InternalSqlParser.g:2528:3: lv_entries_3_0= ruleGroupByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleGroupByColumnFull();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt59 >= 1 ) break loop59;
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

             leaveRule(); 
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
    // InternalSqlParser.g:2552:1: entryRuleGroupByColumnFull returns [EObject current=null] : iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF ;
    public final EObject entryRuleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumnFull = null;


        try {
            // InternalSqlParser.g:2553:2: (iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF )
            // InternalSqlParser.g:2554:2: iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnFullRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGroupByColumnFull=ruleGroupByColumnFull();

            state._fsp--;

             current =iv_ruleGroupByColumnFull; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2561:1: ruleGroupByColumnFull returns [EObject current=null] : ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) ) ;
    public final EObject ruleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_grByInt_2_0=null;
        EObject lv_colGrBy_0_0 = null;

        EObject lv_gbFunction_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2564:28: ( ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) ) )
            // InternalSqlParser.g:2565:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) )
            {
            // InternalSqlParser.g:2565:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) )
            int alt61=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA61_1 = input.LA(2);

                if ( (LA61_1==LeftParenthesis) ) {
                    alt61=2;
                }
                else if ( (LA61_1==EOF||LA61_1==FETCHFIRST||LA61_1==INTERSECT||LA61_1==ORDERBY||LA61_1==EXCEPT||LA61_1==HAVING||LA61_1==OFFSET||(LA61_1>=LIMIT && LA61_1<=MINUS)||LA61_1==UNION||LA61_1==RightParenthesis||LA61_1==Comma||LA61_1==FullStop) ) {
                    alt61=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt61=1;
                }
                break;
            case RULE_UNSIGNED:
                {
                alt61=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // InternalSqlParser.g:2565:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    {
                    // InternalSqlParser.g:2565:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    // InternalSqlParser.g:2566:1: (lv_colGrBy_0_0= ruleColumnFull )
                    {
                    // InternalSqlParser.g:2566:1: (lv_colGrBy_0_0= ruleColumnFull )
                    // InternalSqlParser.g:2567:3: lv_colGrBy_0_0= ruleColumnFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getColGrByColumnFullParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_colGrBy_0_0=ruleColumnFull();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:2584:6: ( (lv_gbFunction_1_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:2584:6: ( (lv_gbFunction_1_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:2585:1: (lv_gbFunction_1_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:2585:1: (lv_gbFunction_1_0= ruleOperandFunction )
                    // InternalSqlParser.g:2586:3: lv_gbFunction_1_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getGbFunctionOperandFunctionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_gbFunction_1_0=ruleOperandFunction();

                    state._fsp--;


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
                    break;
                case 3 :
                    // InternalSqlParser.g:2603:6: ( (lv_grByInt_2_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:2603:6: ( (lv_grByInt_2_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:2604:1: (lv_grByInt_2_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:2604:1: (lv_grByInt_2_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:2605:3: lv_grByInt_2_0= RULE_UNSIGNED
                    {
                    lv_grByInt_2_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_2); 

                    			newLeafNode(lv_grByInt_2_0, grammarAccess.getGroupByColumnFullAccess().getGrByIntUNSIGNEDTerminalRuleCall_2_0()); 
                    		

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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2629:1: entryRuleFullExpression returns [EObject current=null] : iv_ruleFullExpression= ruleFullExpression EOF ;
    public final EObject entryRuleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullExpression = null;


        try {
            // InternalSqlParser.g:2630:2: (iv_ruleFullExpression= ruleFullExpression EOF )
            // InternalSqlParser.g:2631:2: iv_ruleFullExpression= ruleFullExpression EOF
            {
             newCompositeNode(grammarAccess.getFullExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFullExpression=ruleFullExpression();

            state._fsp--;

             current =iv_ruleFullExpression; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2638:1: ruleFullExpression returns [EObject current=null] : (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) ;
    public final EObject ruleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExpressionFragment_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2641:28: ( (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) )
            // InternalSqlParser.g:2642:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            {
            // InternalSqlParser.g:2642:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            // InternalSqlParser.g:2643:5: this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_49);
            this_ExpressionFragment_0=ruleExpressionFragment();

            state._fsp--;


                    current = this_ExpressionFragment_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2651:1: ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==AND||LA63_0==OR||LA63_0==RULE_JRNPARAM) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalSqlParser.g:2651:2: () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    {
                    // InternalSqlParser.g:2651:2: ()
                    // InternalSqlParser.g:2652:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2657:2: ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    int cnt62=0;
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==AND||LA62_0==OR||LA62_0==RULE_JRNPARAM) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // InternalSqlParser.g:2658:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    {
                    	    // InternalSqlParser.g:2658:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    // InternalSqlParser.g:2659:3: lv_entries_2_0= ruleExpressionFragmentSecond
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_49);
                    	    lv_entries_2_0=ruleExpressionFragmentSecond();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt62 >= 1 ) break loop62;
                                EarlyExitException eee =
                                    new EarlyExitException(62, input);
                                throw eee;
                        }
                        cnt62++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2683:1: entryRuleExpressionFragmentSecond returns [EObject current=null] : iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF ;
    public final EObject entryRuleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragmentSecond = null;


        try {
            // InternalSqlParser.g:2684:2: (iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF )
            // InternalSqlParser.g:2685:2: iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentSecondRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpressionFragmentSecond=ruleExpressionFragmentSecond();

            state._fsp--;

             current =iv_ruleExpressionFragmentSecond; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2692:1: ruleExpressionFragmentSecond returns [EObject current=null] : ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) ;
    public final EObject ruleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        Token lv_c_0_1=null;
        Token lv_c_0_2=null;
        Token lv_notPrm_2_0=null;
        EObject lv_efrag_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2695:28: ( ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) )
            // InternalSqlParser.g:2696:1: ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            {
            // InternalSqlParser.g:2696:1: ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==AND||LA65_0==OR) ) {
                alt65=1;
            }
            else if ( (LA65_0==RULE_JRNPARAM) ) {
                alt65=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }
            switch (alt65) {
                case 1 :
                    // InternalSqlParser.g:2696:2: ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    {
                    // InternalSqlParser.g:2696:2: ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    // InternalSqlParser.g:2696:3: ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    {
                    // InternalSqlParser.g:2696:3: ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) )
                    // InternalSqlParser.g:2697:1: ( (lv_c_0_1= AND | lv_c_0_2= OR ) )
                    {
                    // InternalSqlParser.g:2697:1: ( (lv_c_0_1= AND | lv_c_0_2= OR ) )
                    // InternalSqlParser.g:2698:1: (lv_c_0_1= AND | lv_c_0_2= OR )
                    {
                    // InternalSqlParser.g:2698:1: (lv_c_0_1= AND | lv_c_0_2= OR )
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==AND) ) {
                        alt64=1;
                    }
                    else if ( (LA64_0==OR) ) {
                        alt64=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 64, 0, input);

                        throw nvae;
                    }
                    switch (alt64) {
                        case 1 :
                            // InternalSqlParser.g:2699:3: lv_c_0_1= AND
                            {
                            lv_c_0_1=(Token)match(input,AND,FOLLOW_17); 

                                    newLeafNode(lv_c_0_1, grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                            	        }
                                   		setWithLastConsumed(current, "c", lv_c_0_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:2712:8: lv_c_0_2= OR
                            {
                            lv_c_0_2=(Token)match(input,OR,FOLLOW_17); 

                                    newLeafNode(lv_c_0_2, grammarAccess.getExpressionFragmentSecondAccess().getCORKeyword_0_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                            	        }
                                   		setWithLastConsumed(current, "c", lv_c_0_2, null);
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // InternalSqlParser.g:2728:2: ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    // InternalSqlParser.g:2729:1: (lv_efrag_1_0= ruleExpressionFragment )
                    {
                    // InternalSqlParser.g:2729:1: (lv_efrag_1_0= ruleExpressionFragment )
                    // InternalSqlParser.g:2730:3: lv_efrag_1_0= ruleExpressionFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_efrag_1_0=ruleExpressionFragment();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:2747:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    {
                    // InternalSqlParser.g:2747:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    // InternalSqlParser.g:2748:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    {
                    // InternalSqlParser.g:2748:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    // InternalSqlParser.g:2749:3: lv_notPrm_2_0= RULE_JRNPARAM
                    {
                    lv_notPrm_2_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_2); 

                    			newLeafNode(lv_notPrm_2_0, grammarAccess.getExpressionFragmentSecondAccess().getNotPrmJRNPARAMTerminalRuleCall_1_0()); 
                    		

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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2773:1: entryRuleExpressionFragment returns [EObject current=null] : iv_ruleExpressionFragment= ruleExpressionFragment EOF ;
    public final EObject entryRuleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragment = null;


        try {
            // InternalSqlParser.g:2774:2: (iv_ruleExpressionFragment= ruleExpressionFragment EOF )
            // InternalSqlParser.g:2775:2: iv_ruleExpressionFragment= ruleExpressionFragment EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpressionFragment=ruleExpressionFragment();

            state._fsp--;

             current =iv_ruleExpressionFragment; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2782:1: ruleExpressionFragment returns [EObject current=null] : ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) ;
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
            // InternalSqlParser.g:2785:28: ( ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) )
            // InternalSqlParser.g:2786:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            {
            // InternalSqlParser.g:2786:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            int alt67=6;
            switch ( input.LA(1) ) {
            case NOT:
            case NOT_1:
                {
                alt67=1;
                }
                break;
            case LeftParenthesis:
                {
                int LA67_2 = input.LA(2);

                if ( (LA67_2==SELECT) ) {
                    alt67=2;
                }
                else if ( (LA67_2==NOTEXISTS||LA67_2==EXTRACT||LA67_2==EXISTS||LA67_2==NOTIN_1||LA67_2==CAST||LA67_2==CASE||(LA67_2>=NOT && LA67_2<=NOT_1)||LA67_2==X||LA67_2==IN||LA67_2==LeftParenthesis||(LA67_2>=RULE_JRPARAM && LA67_2<=RULE_JRNPARAM)||(LA67_2>=RULE_UNSIGNED && LA67_2<=RULE_SIGNED_DOUBLE)||(LA67_2>=RULE_STRING_ && LA67_2<=RULE_ID)) ) {
                    alt67=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 67, 2, input);

                    throw nvae;
                }
                }
                break;
            case EXTRACT:
            case CAST:
            case CASE:
            case RULE_JRPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt67=2;
                }
                break;
            case RULE_JRNPARAM:
                {
                int LA67_4 = input.LA(2);

                if ( (LA67_4==EOF||LA67_4==STRAIGHT_JOIN||LA67_4==FETCHFIRST||LA67_4==INTERSECT||LA67_4==GROUPBY||LA67_4==ORDERBY||LA67_4==NATURAL||LA67_4==EXCEPT||LA67_4==HAVING||LA67_4==OFFSET||LA67_4==CROSS||LA67_4==INNER||(LA67_4>=LIMIT && LA67_4<=MINUS)||(LA67_4>=RIGHT && LA67_4<=UNION)||LA67_4==WHERE||LA67_4==FULL||LA67_4==JOIN||LA67_4==LEFT||LA67_4==THEN||LA67_4==WHEN||LA67_4==AND||LA67_4==OR||LA67_4==RightParenthesis||LA67_4==Comma||LA67_4==RULE_JRNPARAM) ) {
                    alt67=4;
                }
                else if ( (LA67_4==ISNOTNULL||LA67_4==NOTBETWEEN||LA67_4==NOTEXISTS||LA67_4==NOTLIKE||LA67_4==BETWEEN||LA67_4==ISNULL||LA67_4==EXISTS||LA67_4==NOTIN_1||LA67_4==LIKE||LA67_4==ExclamationMarkEqualsSign||(LA67_4>=LessThanSignEqualsSign && LA67_4<=GreaterThanSignEqualsSign)||LA67_4==IN||(LA67_4>=CircumflexAccentEqualsSign && LA67_4<=VerticalLineVerticalLine)||LA67_4==PlusSign||LA67_4==HyphenMinus||(LA67_4>=Solidus && LA67_4<=GreaterThanSign)||LA67_4==RULE_STAR) ) {
                    alt67=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 67, 4, input);

                    throw nvae;
                }
                }
                break;
            case X:
                {
                alt67=3;
                }
                break;
            case NOTIN_1:
            case IN:
                {
                alt67=5;
                }
                break;
            case NOTEXISTS:
            case EXISTS:
                {
                alt67=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }

            switch (alt67) {
                case 1 :
                    // InternalSqlParser.g:2786:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    {
                    // InternalSqlParser.g:2786:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    // InternalSqlParser.g:2787:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    {
                    // InternalSqlParser.g:2787:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    // InternalSqlParser.g:2788:3: lv_expgroup_0_0= ruleExpressionGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_expgroup_0_0=ruleExpressionGroup();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:2805:6: ( (lv_exp_1_0= ruleExpression ) )
                    {
                    // InternalSqlParser.g:2805:6: ( (lv_exp_1_0= ruleExpression ) )
                    // InternalSqlParser.g:2806:1: (lv_exp_1_0= ruleExpression )
                    {
                    // InternalSqlParser.g:2806:1: (lv_exp_1_0= ruleExpression )
                    // InternalSqlParser.g:2807:3: lv_exp_1_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_exp_1_0=ruleExpression();

                    state._fsp--;


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
                    break;
                case 3 :
                    // InternalSqlParser.g:2824:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    {
                    // InternalSqlParser.g:2824:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    // InternalSqlParser.g:2825:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    {
                    // InternalSqlParser.g:2825:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    // InternalSqlParser.g:2826:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    {
                    // InternalSqlParser.g:2826:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    int alt66=2;
                    alt66 = dfa66.predict(input);
                    switch (alt66) {
                        case 1 :
                            // InternalSqlParser.g:2827:3: lv_xexp_2_1= ruleXExpression
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_2);
                            lv_xexp_2_1=ruleXExpression();

                            state._fsp--;


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
                            break;
                        case 2 :
                            // InternalSqlParser.g:2842:8: lv_xexp_2_2= ruleXExpression_
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpression_ParserRuleCall_2_0_1()); 
                            	    
                            pushFollow(FOLLOW_2);
                            lv_xexp_2_2=ruleXExpression_();

                            state._fsp--;


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
                            break;

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:2861:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    {
                    // InternalSqlParser.g:2861:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    // InternalSqlParser.g:2862:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    {
                    // InternalSqlParser.g:2862:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    // InternalSqlParser.g:2863:3: lv_notPrm_3_0= RULE_JRNPARAM
                    {
                    lv_notPrm_3_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_2); 

                    			newLeafNode(lv_notPrm_3_0, grammarAccess.getExpressionFragmentAccess().getNotPrmJRNPARAMTerminalRuleCall_3_0()); 
                    		

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
                    break;
                case 5 :
                    // InternalSqlParser.g:2880:6: ( (lv_in_4_0= ruleInOperator ) )
                    {
                    // InternalSqlParser.g:2880:6: ( (lv_in_4_0= ruleInOperator ) )
                    // InternalSqlParser.g:2881:1: (lv_in_4_0= ruleInOperator )
                    {
                    // InternalSqlParser.g:2881:1: (lv_in_4_0= ruleInOperator )
                    // InternalSqlParser.g:2882:3: lv_in_4_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getInInOperatorParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_in_4_0=ruleInOperator();

                    state._fsp--;


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
                    break;
                case 6 :
                    // InternalSqlParser.g:2899:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    {
                    // InternalSqlParser.g:2899:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    // InternalSqlParser.g:2900:1: (lv_exists_5_0= ruleExistsOperator )
                    {
                    // InternalSqlParser.g:2900:1: (lv_exists_5_0= ruleExistsOperator )
                    // InternalSqlParser.g:2901:3: lv_exists_5_0= ruleExistsOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExistsExistsOperatorParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_exists_5_0=ruleExistsOperator();

                    state._fsp--;


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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:2925:1: entryRuleExpressionGroup returns [EObject current=null] : iv_ruleExpressionGroup= ruleExpressionGroup EOF ;
    public final EObject entryRuleExpressionGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionGroup = null;


        try {
            // InternalSqlParser.g:2926:2: (iv_ruleExpressionGroup= ruleExpressionGroup EOF )
            // InternalSqlParser.g:2927:2: iv_ruleExpressionGroup= ruleExpressionGroup EOF
            {
             newCompositeNode(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpressionGroup=ruleExpressionGroup();

            state._fsp--;

             current =iv_ruleExpressionGroup; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:2934:1: ruleExpressionGroup returns [EObject current=null] : ( () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis ) ;
    public final EObject ruleExpressionGroup() throws RecognitionException {
        EObject current = null;

        Token lv_isnot_1_1=null;
        Token lv_isnot_1_2=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2937:28: ( ( () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis ) )
            // InternalSqlParser.g:2938:1: ( () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis )
            {
            // InternalSqlParser.g:2938:1: ( () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis )
            // InternalSqlParser.g:2938:2: () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis
            {
            // InternalSqlParser.g:2938:2: ()
            // InternalSqlParser.g:2939:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpressionGroupAccess().getExprGroupAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:2944:2: ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( ((LA69_0>=NOT && LA69_0<=NOT_1)) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // InternalSqlParser.g:2945:1: ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) )
                    {
                    // InternalSqlParser.g:2945:1: ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) )
                    // InternalSqlParser.g:2946:1: (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT )
                    {
                    // InternalSqlParser.g:2946:1: (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT )
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==NOT_1) ) {
                        alt68=1;
                    }
                    else if ( (LA68_0==NOT) ) {
                        alt68=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 68, 0, input);

                        throw nvae;
                    }
                    switch (alt68) {
                        case 1 :
                            // InternalSqlParser.g:2947:3: lv_isnot_1_1= NOT_1
                            {
                            lv_isnot_1_1=(Token)match(input,NOT_1,FOLLOW_34); 

                                    newLeafNode(lv_isnot_1_1, grammarAccess.getExpressionGroupAccess().getIsnotNOTKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionGroupRule());
                            	        }
                                   		setWithLastConsumed(current, "isnot", lv_isnot_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:2960:8: lv_isnot_1_2= NOT
                            {
                            lv_isnot_1_2=(Token)match(input,NOT,FOLLOW_34); 

                                    newLeafNode(lv_isnot_1_2, grammarAccess.getExpressionGroupAccess().getIsnotNOTKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionGroupRule());
                            	        }
                                   		setWithLastConsumed(current, "isnot", lv_isnot_1_2, null);
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_17); 

                	newLeafNode(otherlv_2, grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_2());
                
            // InternalSqlParser.g:2981:1: ( (lv_expr_3_0= ruleFullExpression ) )
            // InternalSqlParser.g:2982:1: (lv_expr_3_0= ruleFullExpression )
            {
            // InternalSqlParser.g:2982:1: (lv_expr_3_0= ruleFullExpression )
            // InternalSqlParser.g:2983:3: lv_expr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_expr_3_0=ruleFullExpression();

            state._fsp--;


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

            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_4, grammarAccess.getExpressionGroupAccess().getRightParenthesisKeyword_4());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:3012:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // InternalSqlParser.g:3013:2: (iv_ruleXExpression= ruleXExpression EOF )
            // InternalSqlParser.g:3014:2: iv_ruleXExpression= ruleXExpression EOF
            {
             newCompositeNode(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;

             current =iv_ruleXExpression; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3021:1: ruleXExpression returns [EObject current=null] : (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket ) ;
    public final EObject ruleXExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_xf_3_0 = null;

        EObject lv_col_5_0 = null;

        EObject lv_prm_7_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3024:28: ( (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket ) )
            // InternalSqlParser.g:3025:1: (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket )
            {
            // InternalSqlParser.g:3025:1: (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket )
            // InternalSqlParser.g:3026:2: otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,X,FOLLOW_50); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpressionAccess().getXKeyword_0());
                
            // InternalSqlParser.g:3030:1: ()
            // InternalSqlParser.g:3031:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpressionAccess().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_51); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpressionAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalSqlParser.g:3041:1: ( (lv_xf_3_0= ruleXFunction ) )
            // InternalSqlParser.g:3042:1: (lv_xf_3_0= ruleXFunction )
            {
            // InternalSqlParser.g:3042:1: (lv_xf_3_0= ruleXFunction )
            // InternalSqlParser.g:3043:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_52);
            lv_xf_3_0=ruleXFunction();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"xf",
                    		lv_xf_3_0, 
                    		"com.jaspersoft.studio.data.Sql.XFunction");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,Comma,FOLLOW_53); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpressionAccess().getCommaKeyword_4());
                
            // InternalSqlParser.g:3064:1: ( (lv_col_5_0= ruleOperandGroup ) )
            // InternalSqlParser.g:3065:1: (lv_col_5_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:3065:1: (lv_col_5_0= ruleOperandGroup )
            // InternalSqlParser.g:3066:3: lv_col_5_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getColOperandGroupParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_54);
            lv_col_5_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"col",
                    		lv_col_5_0, 
                    		"com.jaspersoft.studio.data.Sql.OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalSqlParser.g:3082:2: (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==Comma) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalSqlParser.g:3083:2: otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,Comma,FOLLOW_38); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpressionAccess().getCommaKeyword_6_0());
                        
                    // InternalSqlParser.g:3087:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // InternalSqlParser.g:3088:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // InternalSqlParser.g:3088:1: (lv_prm_7_0= ruleXExpressionParams )
                    // InternalSqlParser.g:3089:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_55);
                    lv_prm_7_0=ruleXExpressionParams();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"prm",
                            		lv_prm_7_0, 
                            		"com.jaspersoft.studio.data.Sql.XExpressionParams");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_8, grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:3118:1: entryRuleXExpression_ returns [EObject current=null] : iv_ruleXExpression_= ruleXExpression_ EOF ;
    public final EObject entryRuleXExpression_() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression_ = null;


        try {
            // InternalSqlParser.g:3119:2: (iv_ruleXExpression_= ruleXExpression_ EOF )
            // InternalSqlParser.g:3120:2: iv_ruleXExpression_= ruleXExpression_ EOF
            {
             newCompositeNode(grammarAccess.getXExpression_Rule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXExpression_=ruleXExpression_();

            state._fsp--;

             current =iv_ruleXExpression_; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3127:1: ruleXExpression_ returns [EObject current=null] : (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket ) ;
    public final EObject ruleXExpression_() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_xf_3_0 = null;

        EObject lv_col_5_0 = null;

        EObject lv_prm_7_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3130:28: ( (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket ) )
            // InternalSqlParser.g:3131:1: (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket )
            {
            // InternalSqlParser.g:3131:1: (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket )
            // InternalSqlParser.g:3132:2: otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,X,FOLLOW_50); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpression_Access().getXKeyword_0());
                
            // InternalSqlParser.g:3136:1: ()
            // InternalSqlParser.g:3137:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpression_Access().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_51); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpression_Access().getLeftCurlyBracketKeyword_2());
                
            // InternalSqlParser.g:3147:1: ( (lv_xf_3_0= ruleXFunction ) )
            // InternalSqlParser.g:3148:1: (lv_xf_3_0= ruleXFunction )
            {
            // InternalSqlParser.g:3148:1: (lv_xf_3_0= ruleXFunction )
            // InternalSqlParser.g:3149:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_56);
            lv_xf_3_0=ruleXFunction();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
            	        }
                   		set(
                   			current, 
                   			"xf",
                    		lv_xf_3_0, 
                    		"com.jaspersoft.studio.data.Sql.XFunction");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,VerticalLine,FOLLOW_53); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpression_Access().getVerticalLineKeyword_4());
                
            // InternalSqlParser.g:3170:1: ( (lv_col_5_0= ruleOperandGroup ) )
            // InternalSqlParser.g:3171:1: (lv_col_5_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:3171:1: (lv_col_5_0= ruleOperandGroup )
            // InternalSqlParser.g:3172:3: lv_col_5_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getColOperandGroupParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_57);
            lv_col_5_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
            	        }
                   		set(
                   			current, 
                   			"col",
                    		lv_col_5_0, 
                    		"com.jaspersoft.studio.data.Sql.OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalSqlParser.g:3188:2: (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==VerticalLine) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // InternalSqlParser.g:3189:2: otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,VerticalLine,FOLLOW_38); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpression_Access().getVerticalLineKeyword_6_0());
                        
                    // InternalSqlParser.g:3193:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // InternalSqlParser.g:3194:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // InternalSqlParser.g:3194:1: (lv_prm_7_0= ruleXExpressionParams )
                    // InternalSqlParser.g:3195:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpression_Access().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_55);
                    lv_prm_7_0=ruleXExpressionParams();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
                    	        }
                           		set(
                           			current, 
                           			"prm",
                            		lv_prm_7_0, 
                            		"com.jaspersoft.studio.data.Sql.XExpressionParams");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_8, grammarAccess.getXExpression_Access().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:3224:1: entryRuleXExpressionParams returns [EObject current=null] : iv_ruleXExpressionParams= ruleXExpressionParams EOF ;
    public final EObject entryRuleXExpressionParams() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionParams = null;


        try {
            // InternalSqlParser.g:3225:2: (iv_ruleXExpressionParams= ruleXExpressionParams EOF )
            // InternalSqlParser.g:3226:2: iv_ruleXExpressionParams= ruleXExpressionParams EOF
            {
             newCompositeNode(grammarAccess.getXExpressionParamsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXExpressionParams=ruleXExpressionParams();

            state._fsp--;

             current =iv_ruleXExpressionParams; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3233:1: ruleXExpressionParams returns [EObject current=null] : (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) ;
    public final EObject ruleXExpressionParams() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_JRParameter_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3236:28: ( (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) )
            // InternalSqlParser.g:3237:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            {
            // InternalSqlParser.g:3237:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            // InternalSqlParser.g:3238:5: this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_JRParameter_0=ruleJRParameter();

            state._fsp--;


                    current = this_JRParameter_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:3246:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==Comma) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // InternalSqlParser.g:3246:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    {
                    // InternalSqlParser.g:3246:2: ()
                    // InternalSqlParser.g:3247:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:3252:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    int cnt72=0;
                    loop72:
                    do {
                        int alt72=2;
                        int LA72_0 = input.LA(1);

                        if ( (LA72_0==Comma) ) {
                            alt72=1;
                        }


                        switch (alt72) {
                    	case 1 :
                    	    // InternalSqlParser.g:3253:2: otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_38); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:3257:1: ( (lv_entries_3_0= ruleJRParameter ) )
                    	    // InternalSqlParser.g:3258:1: (lv_entries_3_0= ruleJRParameter )
                    	    {
                    	    // InternalSqlParser.g:3258:1: (lv_entries_3_0= ruleJRParameter )
                    	    // InternalSqlParser.g:3259:3: lv_entries_3_0= ruleJRParameter
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleJRParameter();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt72 >= 1 ) break loop72;
                                EarlyExitException eee =
                                    new EarlyExitException(72, input);
                                throw eee;
                        }
                        cnt72++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:3283:1: entryRuleJRParameter returns [EObject current=null] : iv_ruleJRParameter= ruleJRParameter EOF ;
    public final EObject entryRuleJRParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameter = null;


        try {
            // InternalSqlParser.g:3284:2: (iv_ruleJRParameter= ruleJRParameter EOF )
            // InternalSqlParser.g:3285:2: iv_ruleJRParameter= ruleJRParameter EOF
            {
             newCompositeNode(grammarAccess.getJRParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleJRParameter=ruleJRParameter();

            state._fsp--;

             current =iv_ruleJRParameter; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3292:1: ruleJRParameter returns [EObject current=null] : ( (lv_jrprm_0_0= RULE_ID ) ) ;
    public final EObject ruleJRParameter() throws RecognitionException {
        EObject current = null;

        Token lv_jrprm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:3295:28: ( ( (lv_jrprm_0_0= RULE_ID ) ) )
            // InternalSqlParser.g:3296:1: ( (lv_jrprm_0_0= RULE_ID ) )
            {
            // InternalSqlParser.g:3296:1: ( (lv_jrprm_0_0= RULE_ID ) )
            // InternalSqlParser.g:3297:1: (lv_jrprm_0_0= RULE_ID )
            {
            // InternalSqlParser.g:3297:1: (lv_jrprm_0_0= RULE_ID )
            // InternalSqlParser.g:3298:3: lv_jrprm_0_0= RULE_ID
            {
            lv_jrprm_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            			newLeafNode(lv_jrprm_0_0, grammarAccess.getJRParameterAccess().getJrprmIDTerminalRuleCall_0()); 
            		

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

             leaveRule(); 
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
    // InternalSqlParser.g:3322:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalSqlParser.g:3323:2: (iv_ruleExpression= ruleExpression EOF )
            // InternalSqlParser.g:3324:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3331:1: ruleExpression returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        Token lv_isnull_1_1=null;
        Token lv_isnull_1_2=null;
        EObject lv_op1_0_0 = null;

        EObject lv_in_2_0 = null;

        EObject lv_exists_3_0 = null;

        EObject lv_between_4_0 = null;

        EObject lv_like_5_0 = null;

        EObject lv_comp_6_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3334:28: ( ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) )
            // InternalSqlParser.g:3335:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            {
            // InternalSqlParser.g:3335:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            // InternalSqlParser.g:3335:2: ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            {
            // InternalSqlParser.g:3335:2: ( (lv_op1_0_0= ruleOperand ) )
            // InternalSqlParser.g:3336:1: (lv_op1_0_0= ruleOperand )
            {
            // InternalSqlParser.g:3336:1: (lv_op1_0_0= ruleOperand )
            // InternalSqlParser.g:3337:3: lv_op1_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_58);
            lv_op1_0_0=ruleOperand();

            state._fsp--;


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

            // InternalSqlParser.g:3353:2: ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            int alt75=6;
            switch ( input.LA(1) ) {
            case ISNOTNULL:
            case ISNULL:
                {
                alt75=1;
                }
                break;
            case NOTIN_1:
            case IN:
                {
                alt75=2;
                }
                break;
            case NOTEXISTS:
            case EXISTS:
                {
                alt75=3;
                }
                break;
            case NOTBETWEEN:
            case BETWEEN:
                {
                alt75=4;
                }
                break;
            case NOTLIKE:
            case LIKE:
                {
                alt75=5;
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
                alt75=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }

            switch (alt75) {
                case 1 :
                    // InternalSqlParser.g:3353:3: ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) )
                    {
                    // InternalSqlParser.g:3353:3: ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) )
                    // InternalSqlParser.g:3354:1: ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) )
                    {
                    // InternalSqlParser.g:3354:1: ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) )
                    // InternalSqlParser.g:3355:1: (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL )
                    {
                    // InternalSqlParser.g:3355:1: (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL )
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==ISNULL) ) {
                        alt74=1;
                    }
                    else if ( (LA74_0==ISNOTNULL) ) {
                        alt74=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 74, 0, input);

                        throw nvae;
                    }
                    switch (alt74) {
                        case 1 :
                            // InternalSqlParser.g:3356:3: lv_isnull_1_1= ISNULL
                            {
                            lv_isnull_1_1=(Token)match(input,ISNULL,FOLLOW_2); 

                                    newLeafNode(lv_isnull_1_1, grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "isnull", lv_isnull_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:3369:8: lv_isnull_1_2= ISNOTNULL
                            {
                            lv_isnull_1_2=(Token)match(input,ISNOTNULL,FOLLOW_2); 

                                    newLeafNode(lv_isnull_1_2, grammarAccess.getExpressionAccess().getIsnullISNOTNULLKeyword_1_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "isnull", lv_isnull_1_2, null);
                            	    

                            }
                            break;

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3386:6: ( (lv_in_2_0= ruleInOperator ) )
                    {
                    // InternalSqlParser.g:3386:6: ( (lv_in_2_0= ruleInOperator ) )
                    // InternalSqlParser.g:3387:1: (lv_in_2_0= ruleInOperator )
                    {
                    // InternalSqlParser.g:3387:1: (lv_in_2_0= ruleInOperator )
                    // InternalSqlParser.g:3388:3: lv_in_2_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_in_2_0=ruleInOperator();

                    state._fsp--;


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
                    break;
                case 3 :
                    // InternalSqlParser.g:3405:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    {
                    // InternalSqlParser.g:3405:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    // InternalSqlParser.g:3406:1: (lv_exists_3_0= ruleExistsOperator )
                    {
                    // InternalSqlParser.g:3406:1: (lv_exists_3_0= ruleExistsOperator )
                    // InternalSqlParser.g:3407:3: lv_exists_3_0= ruleExistsOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getExistsExistsOperatorParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_exists_3_0=ruleExistsOperator();

                    state._fsp--;


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
                    break;
                case 4 :
                    // InternalSqlParser.g:3424:6: ( (lv_between_4_0= ruleBetween ) )
                    {
                    // InternalSqlParser.g:3424:6: ( (lv_between_4_0= ruleBetween ) )
                    // InternalSqlParser.g:3425:1: (lv_between_4_0= ruleBetween )
                    {
                    // InternalSqlParser.g:3425:1: (lv_between_4_0= ruleBetween )
                    // InternalSqlParser.g:3426:3: lv_between_4_0= ruleBetween
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_between_4_0=ruleBetween();

                    state._fsp--;


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
                    break;
                case 5 :
                    // InternalSqlParser.g:3443:6: ( (lv_like_5_0= ruleLike ) )
                    {
                    // InternalSqlParser.g:3443:6: ( (lv_like_5_0= ruleLike ) )
                    // InternalSqlParser.g:3444:1: (lv_like_5_0= ruleLike )
                    {
                    // InternalSqlParser.g:3444:1: (lv_like_5_0= ruleLike )
                    // InternalSqlParser.g:3445:3: lv_like_5_0= ruleLike
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_4_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_like_5_0=ruleLike();

                    state._fsp--;


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
                    break;
                case 6 :
                    // InternalSqlParser.g:3462:6: ( (lv_comp_6_0= ruleComparison ) )
                    {
                    // InternalSqlParser.g:3462:6: ( (lv_comp_6_0= ruleComparison ) )
                    // InternalSqlParser.g:3463:1: (lv_comp_6_0= ruleComparison )
                    {
                    // InternalSqlParser.g:3463:1: (lv_comp_6_0= ruleComparison )
                    // InternalSqlParser.g:3464:3: lv_comp_6_0= ruleComparison
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_5_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_comp_6_0=ruleComparison();

                    state._fsp--;


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
                    break;

            }


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleComparison"
    // InternalSqlParser.g:3488:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // InternalSqlParser.g:3489:2: (iv_ruleComparison= ruleComparison EOF )
            // InternalSqlParser.g:3490:2: iv_ruleComparison= ruleComparison EOF
            {
             newCompositeNode(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComparison=ruleComparison();

            state._fsp--;

             current =iv_ruleComparison; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3497:1: ruleComparison returns [EObject current=null] : ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) ;
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
            // InternalSqlParser.g:3500:28: ( ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) )
            // InternalSqlParser.g:3501:1: ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            {
            // InternalSqlParser.g:3501:1: ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            // InternalSqlParser.g:3501:2: ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) )
            {
            // InternalSqlParser.g:3501:2: ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) )
            // InternalSqlParser.g:3502:1: ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) )
            {
            // InternalSqlParser.g:3502:1: ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) )
            // InternalSqlParser.g:3503:1: (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign )
            {
            // InternalSqlParser.g:3503:1: (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign )
            int alt76=8;
            switch ( input.LA(1) ) {
            case GreaterThanSign:
                {
                alt76=1;
                }
                break;
            case GreaterThanSignEqualsSign:
                {
                alt76=2;
                }
                break;
            case LessThanSign:
                {
                alt76=3;
                }
                break;
            case LessThanSignEqualsSign:
                {
                alt76=4;
                }
                break;
            case EqualsSign:
                {
                alt76=5;
                }
                break;
            case LessThanSignGreaterThanSign:
                {
                alt76=6;
                }
                break;
            case ExclamationMarkEqualsSign:
                {
                alt76=7;
                }
                break;
            case CircumflexAccentEqualsSign:
                {
                alt76=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }

            switch (alt76) {
                case 1 :
                    // InternalSqlParser.g:3504:3: lv_operator_0_1= GreaterThanSign
                    {
                    lv_operator_0_1=(Token)match(input,GreaterThanSign,FOLLOW_59); 

                            newLeafNode(lv_operator_0_1, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3517:8: lv_operator_0_2= GreaterThanSignEqualsSign
                    {
                    lv_operator_0_2=(Token)match(input,GreaterThanSignEqualsSign,FOLLOW_59); 

                            newLeafNode(lv_operator_0_2, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:3530:8: lv_operator_0_3= LessThanSign
                    {
                    lv_operator_0_3=(Token)match(input,LessThanSign,FOLLOW_59); 

                            newLeafNode(lv_operator_0_3, grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:3543:8: lv_operator_0_4= LessThanSignEqualsSign
                    {
                    lv_operator_0_4=(Token)match(input,LessThanSignEqualsSign,FOLLOW_59); 

                            newLeafNode(lv_operator_0_4, grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_4, null);
                    	    

                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:3556:8: lv_operator_0_5= EqualsSign
                    {
                    lv_operator_0_5=(Token)match(input,EqualsSign,FOLLOW_59); 

                            newLeafNode(lv_operator_0_5, grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_5, null);
                    	    

                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:3569:8: lv_operator_0_6= LessThanSignGreaterThanSign
                    {
                    lv_operator_0_6=(Token)match(input,LessThanSignGreaterThanSign,FOLLOW_59); 

                            newLeafNode(lv_operator_0_6, grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_6, null);
                    	    

                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:3582:8: lv_operator_0_7= ExclamationMarkEqualsSign
                    {
                    lv_operator_0_7=(Token)match(input,ExclamationMarkEqualsSign,FOLLOW_59); 

                            newLeafNode(lv_operator_0_7, grammarAccess.getComparisonAccess().getOperatorExclamationMarkEqualsSignKeyword_0_0_6());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_7, null);
                    	    

                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:3595:8: lv_operator_0_8= CircumflexAccentEqualsSign
                    {
                    lv_operator_0_8=(Token)match(input,CircumflexAccentEqualsSign,FOLLOW_59); 

                            newLeafNode(lv_operator_0_8, grammarAccess.getComparisonAccess().getOperatorCircumflexAccentEqualsSignKeyword_0_0_7());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_8, null);
                    	    

                    }
                    break;

            }


            }


            }

            // InternalSqlParser.g:3611:2: ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==SOME||LA78_0==ALL||LA78_0==ANY) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalSqlParser.g:3612:1: ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) )
                    {
                    // InternalSqlParser.g:3612:1: ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) )
                    // InternalSqlParser.g:3613:1: (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME )
                    {
                    // InternalSqlParser.g:3613:1: (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME )
                    int alt77=3;
                    switch ( input.LA(1) ) {
                    case ANY:
                        {
                        alt77=1;
                        }
                        break;
                    case ALL:
                        {
                        alt77=2;
                        }
                        break;
                    case SOME:
                        {
                        alt77=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 77, 0, input);

                        throw nvae;
                    }

                    switch (alt77) {
                        case 1 :
                            // InternalSqlParser.g:3614:3: lv_subOperator_1_1= ANY
                            {
                            lv_subOperator_1_1=(Token)match(input,ANY,FOLLOW_53); 

                                    newLeafNode(lv_subOperator_1_1, grammarAccess.getComparisonAccess().getSubOperatorANYKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:3627:8: lv_subOperator_1_2= ALL
                            {
                            lv_subOperator_1_2=(Token)match(input,ALL,FOLLOW_53); 

                                    newLeafNode(lv_subOperator_1_2, grammarAccess.getComparisonAccess().getSubOperatorALLKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // InternalSqlParser.g:3640:8: lv_subOperator_1_3= SOME
                            {
                            lv_subOperator_1_3=(Token)match(input,SOME,FOLLOW_53); 

                                    newLeafNode(lv_subOperator_1_3, grammarAccess.getComparisonAccess().getSubOperatorSOMEKeyword_1_0_2());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_3, null);
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // InternalSqlParser.g:3656:3: ( (lv_op2_2_0= ruleOperand ) )
            // InternalSqlParser.g:3657:1: (lv_op2_2_0= ruleOperand )
            {
            // InternalSqlParser.g:3657:1: (lv_op2_2_0= ruleOperand )
            // InternalSqlParser.g:3658:3: lv_op2_2_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_op2_2_0=ruleOperand();

            state._fsp--;


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

             leaveRule(); 
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
    // InternalSqlParser.g:3682:1: entryRuleLike returns [EObject current=null] : iv_ruleLike= ruleLike EOF ;
    public final EObject entryRuleLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLike = null;


        try {
            // InternalSqlParser.g:3683:2: (iv_ruleLike= ruleLike EOF )
            // InternalSqlParser.g:3684:2: iv_ruleLike= ruleLike EOF
            {
             newCompositeNode(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLike=ruleLike();

            state._fsp--;

             current =iv_ruleLike; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3691:1: ruleLike returns [EObject current=null] : ( ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) ;
    public final EObject ruleLike() throws RecognitionException {
        EObject current = null;

        Token lv_opLike_0_1=null;
        Token lv_opLike_0_2=null;
        EObject lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3694:28: ( ( ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) )
            // InternalSqlParser.g:3695:1: ( ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            {
            // InternalSqlParser.g:3695:1: ( ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            // InternalSqlParser.g:3695:2: ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) )
            {
            // InternalSqlParser.g:3695:2: ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) )
            // InternalSqlParser.g:3696:1: ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) )
            {
            // InternalSqlParser.g:3696:1: ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) )
            // InternalSqlParser.g:3697:1: (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE )
            {
            // InternalSqlParser.g:3697:1: (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==LIKE) ) {
                alt79=1;
            }
            else if ( (LA79_0==NOTLIKE) ) {
                alt79=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // InternalSqlParser.g:3698:3: lv_opLike_0_1= LIKE
                    {
                    lv_opLike_0_1=(Token)match(input,LIKE,FOLLOW_60); 

                            newLeafNode(lv_opLike_0_1, grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLikeRule());
                    	        }
                           		setWithLastConsumed(current, "opLike", lv_opLike_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3711:8: lv_opLike_0_2= NOTLIKE
                    {
                    lv_opLike_0_2=(Token)match(input,NOTLIKE,FOLLOW_60); 

                            newLeafNode(lv_opLike_0_2, grammarAccess.getLikeAccess().getOpLikeNOTLIKEKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLikeRule());
                    	        }
                           		setWithLastConsumed(current, "opLike", lv_opLike_0_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            // InternalSqlParser.g:3727:2: ( (lv_op2_1_0= ruleLikeOperand ) )
            // InternalSqlParser.g:3728:1: (lv_op2_1_0= ruleLikeOperand )
            {
            // InternalSqlParser.g:3728:1: (lv_op2_1_0= ruleLikeOperand )
            // InternalSqlParser.g:3729:3: lv_op2_1_0= ruleLikeOperand
            {
             
            	        newCompositeNode(grammarAccess.getLikeAccess().getOp2LikeOperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_op2_1_0=ruleLikeOperand();

            state._fsp--;


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

             leaveRule(); 
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


    // $ANTLR start "entryRuleLikeOperand"
    // InternalSqlParser.g:3753:1: entryRuleLikeOperand returns [EObject current=null] : iv_ruleLikeOperand= ruleLikeOperand EOF ;
    public final EObject entryRuleLikeOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLikeOperand = null;


        try {
            // InternalSqlParser.g:3754:2: (iv_ruleLikeOperand= ruleLikeOperand EOF )
            // InternalSqlParser.g:3755:2: iv_ruleLikeOperand= ruleLikeOperand EOF
            {
             newCompositeNode(grammarAccess.getLikeOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLikeOperand=ruleLikeOperand();

            state._fsp--;

             current =iv_ruleLikeOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3762:1: ruleLikeOperand returns [EObject current=null] : ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) ;
    public final EObject ruleLikeOperand() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op2_0_0 = null;

        EObject lv_fop2_1_0 = null;

        EObject lv_fcast_2_0 = null;

        EObject lv_fparam_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3765:28: ( ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) )
            // InternalSqlParser.g:3766:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            {
            // InternalSqlParser.g:3766:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            int alt80=4;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt80=1;
                }
                break;
            case RULE_ID:
                {
                alt80=2;
                }
                break;
            case CAST:
                {
                alt80=3;
                }
                break;
            case RULE_JRPARAM:
                {
                alt80=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;
            }

            switch (alt80) {
                case 1 :
                    // InternalSqlParser.g:3766:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:3766:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    // InternalSqlParser.g:3767:1: (lv_op2_0_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:3767:1: (lv_op2_0_0= ruleStringOperand )
                    // InternalSqlParser.g:3768:3: lv_op2_0_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getOp2StringOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_op2_0_0=ruleStringOperand();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:3785:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:3785:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:3786:1: (lv_fop2_1_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:3786:1: (lv_fop2_1_0= ruleOperandFunction )
                    // InternalSqlParser.g:3787:3: lv_fop2_1_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFop2OperandFunctionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_fop2_1_0=ruleOperandFunction();

                    state._fsp--;


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
                    break;
                case 3 :
                    // InternalSqlParser.g:3804:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    {
                    // InternalSqlParser.g:3804:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    // InternalSqlParser.g:3805:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    {
                    // InternalSqlParser.g:3805:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    // InternalSqlParser.g:3806:3: lv_fcast_2_0= ruleOpFunctionCast
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFcastOpFunctionCastParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_fcast_2_0=ruleOpFunctionCast();

                    state._fsp--;


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
                    break;
                case 4 :
                    // InternalSqlParser.g:3823:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    {
                    // InternalSqlParser.g:3823:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    // InternalSqlParser.g:3824:1: (lv_fparam_3_0= ruleParameterOperand )
                    {
                    // InternalSqlParser.g:3824:1: (lv_fparam_3_0= ruleParameterOperand )
                    // InternalSqlParser.g:3825:3: lv_fparam_3_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFparamParameterOperandParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_fparam_3_0=ruleParameterOperand();

                    state._fsp--;


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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:3849:1: entryRuleBetween returns [EObject current=null] : iv_ruleBetween= ruleBetween EOF ;
    public final EObject entryRuleBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBetween = null;


        try {
            // InternalSqlParser.g:3850:2: (iv_ruleBetween= ruleBetween EOF )
            // InternalSqlParser.g:3851:2: iv_ruleBetween= ruleBetween EOF
            {
             newCompositeNode(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBetween=ruleBetween();

            state._fsp--;

             current =iv_ruleBetween; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3858:1: ruleBetween returns [EObject current=null] : ( ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) ) ;
    public final EObject ruleBetween() throws RecognitionException {
        EObject current = null;

        Token lv_opBetween_0_1=null;
        Token lv_opBetween_0_2=null;
        Token otherlv_2=null;
        EObject lv_op2_1_0 = null;

        EObject lv_op3_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3861:28: ( ( ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) ) )
            // InternalSqlParser.g:3862:1: ( ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) )
            {
            // InternalSqlParser.g:3862:1: ( ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) )
            // InternalSqlParser.g:3862:2: ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) )
            {
            // InternalSqlParser.g:3862:2: ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) )
            // InternalSqlParser.g:3863:1: ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) )
            {
            // InternalSqlParser.g:3863:1: ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) )
            // InternalSqlParser.g:3864:1: (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN )
            {
            // InternalSqlParser.g:3864:1: (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==BETWEEN) ) {
                alt81=1;
            }
            else if ( (LA81_0==NOTBETWEEN) ) {
                alt81=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // InternalSqlParser.g:3865:3: lv_opBetween_0_1= BETWEEN
                    {
                    lv_opBetween_0_1=(Token)match(input,BETWEEN,FOLLOW_53); 

                            newLeafNode(lv_opBetween_0_1, grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBetweenRule());
                    	        }
                           		setWithLastConsumed(current, "opBetween", lv_opBetween_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3878:8: lv_opBetween_0_2= NOTBETWEEN
                    {
                    lv_opBetween_0_2=(Token)match(input,NOTBETWEEN,FOLLOW_53); 

                            newLeafNode(lv_opBetween_0_2, grammarAccess.getBetweenAccess().getOpBetweenNOTBETWEENKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBetweenRule());
                    	        }
                           		setWithLastConsumed(current, "opBetween", lv_opBetween_0_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            // InternalSqlParser.g:3894:2: ( (lv_op2_1_0= ruleOperandGroup ) )
            // InternalSqlParser.g:3895:1: (lv_op2_1_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:3895:1: (lv_op2_1_0= ruleOperandGroup )
            // InternalSqlParser.g:3896:3: lv_op2_1_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp2OperandGroupParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_61);
            lv_op2_1_0=ruleOperandGroup();

            state._fsp--;


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

            otherlv_2=(Token)match(input,AND,FOLLOW_53); 

                	newLeafNode(otherlv_2, grammarAccess.getBetweenAccess().getANDKeyword_2());
                
            // InternalSqlParser.g:3917:1: ( (lv_op3_3_0= ruleOperandGroup ) )
            // InternalSqlParser.g:3918:1: (lv_op3_3_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:3918:1: (lv_op3_3_0= ruleOperandGroup )
            // InternalSqlParser.g:3919:3: lv_op3_3_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp3OperandGroupParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_op3_3_0=ruleOperandGroup();

            state._fsp--;


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

             leaveRule(); 
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


    // $ANTLR start "entryRuleInOperator"
    // InternalSqlParser.g:3943:1: entryRuleInOperator returns [EObject current=null] : iv_ruleInOperator= ruleInOperator EOF ;
    public final EObject entryRuleInOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOperator = null;


        try {
            // InternalSqlParser.g:3944:2: (iv_ruleInOperator= ruleInOperator EOF )
            // InternalSqlParser.g:3945:2: iv_ruleInOperator= ruleInOperator EOF
            {
             newCompositeNode(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInOperator=ruleInOperator();

            state._fsp--;

             current =iv_ruleInOperator; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:3952:1: ruleInOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleInOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3955:28: ( ( () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // InternalSqlParser.g:3956:1: ( () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // InternalSqlParser.g:3956:1: ( () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // InternalSqlParser.g:3956:2: () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // InternalSqlParser.g:3956:2: ()
            // InternalSqlParser.g:3957:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInOperatorAccess().getInOperAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:3962:2: ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) )
            // InternalSqlParser.g:3963:1: ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) )
            {
            // InternalSqlParser.g:3963:1: ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) )
            // InternalSqlParser.g:3964:1: (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN )
            {
            // InternalSqlParser.g:3964:1: (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN )
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==NOTIN_1) ) {
                alt82=1;
            }
            else if ( (LA82_0==IN) ) {
                alt82=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }
            switch (alt82) {
                case 1 :
                    // InternalSqlParser.g:3965:3: lv_op_1_1= NOTIN_1
                    {
                    lv_op_1_1=(Token)match(input,NOTIN_1,FOLLOW_34); 

                            newLeafNode(lv_op_1_1, grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getInOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:3978:8: lv_op_1_2= IN
                    {
                    lv_op_1_2=(Token)match(input,IN,FOLLOW_34); 

                            newLeafNode(lv_op_1_2, grammarAccess.getInOperatorAccess().getOpINKeyword_1_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getInOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            // InternalSqlParser.g:3994:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==LeftParenthesis) ) {
                int LA83_1 = input.LA(2);

                if ( (LA83_1==SELECT) ) {
                    alt83=1;
                }
                else if ( ((LA83_1>=RULE_SIGNED_DOUBLE && LA83_1<=RULE_STRING_)) ) {
                    alt83=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 83, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }
            switch (alt83) {
                case 1 :
                    // InternalSqlParser.g:3994:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:3994:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:3995:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:3995:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:3996:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_subquery_2_0=ruleSubQueryOperand();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:4013:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // InternalSqlParser.g:4013:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // InternalSqlParser.g:4014:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // InternalSqlParser.g:4014:1: (lv_opList_3_0= ruleOperandListGroup )
                    // InternalSqlParser.g:4015:3: lv_opList_3_0= ruleOperandListGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getOpListOperandListGroupParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_opList_3_0=ruleOperandListGroup();

                    state._fsp--;


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
                    break;

            }


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleExistsOperator"
    // InternalSqlParser.g:4039:1: entryRuleExistsOperator returns [EObject current=null] : iv_ruleExistsOperator= ruleExistsOperator EOF ;
    public final EObject entryRuleExistsOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistsOperator = null;


        try {
            // InternalSqlParser.g:4040:2: (iv_ruleExistsOperator= ruleExistsOperator EOF )
            // InternalSqlParser.g:4041:2: iv_ruleExistsOperator= ruleExistsOperator EOF
            {
             newCompositeNode(grammarAccess.getExistsOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExistsOperator=ruleExistsOperator();

            state._fsp--;

             current =iv_ruleExistsOperator; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4048:1: ruleExistsOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleExistsOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4051:28: ( ( () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // InternalSqlParser.g:4052:1: ( () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // InternalSqlParser.g:4052:1: ( () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // InternalSqlParser.g:4052:2: () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // InternalSqlParser.g:4052:2: ()
            // InternalSqlParser.g:4053:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExistsOperatorAccess().getExistsOperAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:4058:2: ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) )
            // InternalSqlParser.g:4059:1: ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) )
            {
            // InternalSqlParser.g:4059:1: ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) )
            // InternalSqlParser.g:4060:1: (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS )
            {
            // InternalSqlParser.g:4060:1: (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==NOTEXISTS) ) {
                alt84=1;
            }
            else if ( (LA84_0==EXISTS) ) {
                alt84=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // InternalSqlParser.g:4061:3: lv_op_1_1= NOTEXISTS
                    {
                    lv_op_1_1=(Token)match(input,NOTEXISTS,FOLLOW_34); 

                            newLeafNode(lv_op_1_1, grammarAccess.getExistsOperatorAccess().getOpNOTEXISTSKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExistsOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4074:8: lv_op_1_2= EXISTS
                    {
                    lv_op_1_2=(Token)match(input,EXISTS,FOLLOW_34); 

                            newLeafNode(lv_op_1_2, grammarAccess.getExistsOperatorAccess().getOpEXISTSKeyword_1_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExistsOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            // InternalSqlParser.g:4090:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==LeftParenthesis) ) {
                int LA85_1 = input.LA(2);

                if ( (LA85_1==SELECT) ) {
                    alt85=1;
                }
                else if ( ((LA85_1>=RULE_SIGNED_DOUBLE && LA85_1<=RULE_STRING_)) ) {
                    alt85=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 85, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }
            switch (alt85) {
                case 1 :
                    // InternalSqlParser.g:4090:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:4090:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:4091:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:4091:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:4092:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_subquery_2_0=ruleSubQueryOperand();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:4109:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // InternalSqlParser.g:4109:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // InternalSqlParser.g:4110:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // InternalSqlParser.g:4110:1: (lv_opList_3_0= ruleOperandListGroup )
                    // InternalSqlParser.g:4111:3: lv_opList_3_0= ruleOperandListGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getOpListOperandListGroupParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_opList_3_0=ruleOperandListGroup();

                    state._fsp--;


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
                    break;

            }


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleOperandListGroup"
    // InternalSqlParser.g:4135:1: entryRuleOperandListGroup returns [EObject current=null] : iv_ruleOperandListGroup= ruleOperandListGroup EOF ;
    public final EObject entryRuleOperandListGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandListGroup = null;


        try {
            // InternalSqlParser.g:4136:2: (iv_ruleOperandListGroup= ruleOperandListGroup EOF )
            // InternalSqlParser.g:4137:2: iv_ruleOperandListGroup= ruleOperandListGroup EOF
            {
             newCompositeNode(grammarAccess.getOperandListGroupRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperandListGroup=ruleOperandListGroup();

            state._fsp--;

             current =iv_ruleOperandListGroup; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4144:1: ruleOperandListGroup returns [EObject current=null] : (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis ) ;
    public final EObject ruleOperandListGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_opGroup_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4147:28: ( (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis ) )
            // InternalSqlParser.g:4148:1: (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis )
            {
            // InternalSqlParser.g:4148:1: (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis )
            // InternalSqlParser.g:4149:2: otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis
            {
            otherlv_0=(Token)match(input,LeftParenthesis,FOLLOW_62); 

                	newLeafNode(otherlv_0, grammarAccess.getOperandListGroupAccess().getLeftParenthesisKeyword_0());
                
            // InternalSqlParser.g:4153:1: ( (lv_opGroup_1_0= ruleOperandList ) )
            // InternalSqlParser.g:4154:1: (lv_opGroup_1_0= ruleOperandList )
            {
            // InternalSqlParser.g:4154:1: (lv_opGroup_1_0= ruleOperandList )
            // InternalSqlParser.g:4155:3: lv_opGroup_1_0= ruleOperandList
            {
             
            	        newCompositeNode(grammarAccess.getOperandListGroupAccess().getOpGroupOperandListParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_opGroup_1_0=ruleOperandList();

            state._fsp--;


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

            otherlv_2=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_2, grammarAccess.getOperandListGroupAccess().getRightParenthesisKeyword_2());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4184:1: entryRuleOperandList returns [EObject current=null] : iv_ruleOperandList= ruleOperandList EOF ;
    public final EObject entryRuleOperandList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandList = null;


        try {
            // InternalSqlParser.g:4185:2: (iv_ruleOperandList= ruleOperandList EOF )
            // InternalSqlParser.g:4186:2: iv_ruleOperandList= ruleOperandList EOF
            {
             newCompositeNode(grammarAccess.getOperandListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperandList=ruleOperandList();

            state._fsp--;

             current =iv_ruleOperandList; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4193:1: ruleOperandList returns [EObject current=null] : (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) ;
    public final EObject ruleOperandList() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ScalarOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4196:28: ( (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) )
            // InternalSqlParser.g:4197:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            {
            // InternalSqlParser.g:4197:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            // InternalSqlParser.g:4198:5: this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOperandListAccess().getScalarOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_ScalarOperand_0=ruleScalarOperand();

            state._fsp--;


                    current = this_ScalarOperand_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:4206:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==Comma) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalSqlParser.g:4206:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    {
                    // InternalSqlParser.g:4206:2: ()
                    // InternalSqlParser.g:4207:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:4212:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    int cnt86=0;
                    loop86:
                    do {
                        int alt86=2;
                        int LA86_0 = input.LA(1);

                        if ( (LA86_0==Comma) ) {
                            alt86=1;
                        }


                        switch (alt86) {
                    	case 1 :
                    	    // InternalSqlParser.g:4213:2: otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_62); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:4217:1: ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    // InternalSqlParser.g:4218:1: (lv_entries_3_0= ruleScalarOperand )
                    	    {
                    	    // InternalSqlParser.g:4218:1: (lv_entries_3_0= ruleScalarOperand )
                    	    // InternalSqlParser.g:4219:3: lv_entries_3_0= ruleScalarOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOperandListAccess().getEntriesScalarOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleScalarOperand();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt86 >= 1 ) break loop86;
                                EarlyExitException eee =
                                    new EarlyExitException(86, input);
                                throw eee;
                        }
                        cnt86++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4243:1: entryRuleOperandGroup returns [EObject current=null] : iv_ruleOperandGroup= ruleOperandGroup EOF ;
    public final EObject entryRuleOperandGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandGroup = null;


        try {
            // InternalSqlParser.g:4244:2: (iv_ruleOperandGroup= ruleOperandGroup EOF )
            // InternalSqlParser.g:4245:2: iv_ruleOperandGroup= ruleOperandGroup EOF
            {
             newCompositeNode(grammarAccess.getOperandGroupRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperandGroup=ruleOperandGroup();

            state._fsp--;

             current =iv_ruleOperandGroup; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4252:1: ruleOperandGroup returns [EObject current=null] : (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) ) ;
    public final EObject ruleOperandGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_Operand_0 = null;

        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4255:28: ( (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) ) )
            // InternalSqlParser.g:4256:1: (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) )
            {
            // InternalSqlParser.g:4256:1: (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) )
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==EXTRACT||LA88_0==CAST||LA88_0==CASE||(LA88_0>=RULE_JRPARAM && LA88_0<=RULE_JRNPARAM)||(LA88_0>=RULE_UNSIGNED && LA88_0<=RULE_SIGNED_DOUBLE)||(LA88_0>=RULE_STRING_ && LA88_0<=RULE_ID)) ) {
                alt88=1;
            }
            else if ( (LA88_0==LeftParenthesis) ) {
                int LA88_2 = input.LA(2);

                if ( (LA88_2==EXTRACT||LA88_2==CAST||LA88_2==CASE||LA88_2==LeftParenthesis||(LA88_2>=RULE_JRPARAM && LA88_2<=RULE_JRNPARAM)||(LA88_2>=RULE_UNSIGNED && LA88_2<=RULE_SIGNED_DOUBLE)||(LA88_2>=RULE_STRING_ && LA88_2<=RULE_ID)) ) {
                    alt88=2;
                }
                else if ( (LA88_2==SELECT) ) {
                    alt88=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 88, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;
            }
            switch (alt88) {
                case 1 :
                    // InternalSqlParser.g:4257:5: this_Operand_0= ruleOperand
                    {
                     
                            newCompositeNode(grammarAccess.getOperandGroupAccess().getOperandParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_Operand_0=ruleOperand();

                    state._fsp--;


                            current = this_Operand_0;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4266:6: (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis )
                    {
                    // InternalSqlParser.g:4266:6: (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis )
                    // InternalSqlParser.g:4267:2: otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis
                    {
                    otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_53); 

                        	newLeafNode(otherlv_1, grammarAccess.getOperandGroupAccess().getLeftParenthesisKeyword_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getOperandGroupAccess().getOperandParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_35);
                    this_Operand_2=ruleOperand();

                    state._fsp--;


                            current = this_Operand_2;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); 

                        	newLeafNode(otherlv_3, grammarAccess.getOperandGroupAccess().getRightParenthesisKeyword_1_2());
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4293:1: entryRuleOperand returns [EObject current=null] : iv_ruleOperand= ruleOperand EOF ;
    public final EObject entryRuleOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperand = null;


        try {
            // InternalSqlParser.g:4294:2: (iv_ruleOperand= ruleOperand EOF )
            // InternalSqlParser.g:4295:2: iv_ruleOperand= ruleOperand EOF
            {
             newCompositeNode(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperand=ruleOperand();

            state._fsp--;

             current =iv_ruleOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4302:1: ruleOperand returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) ;
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
            // InternalSqlParser.g:4305:28: ( ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) )
            // InternalSqlParser.g:4306:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            {
            // InternalSqlParser.g:4306:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            // InternalSqlParser.g:4306:2: ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            {
            // InternalSqlParser.g:4306:2: ( (lv_op1_0_0= ruleOperandFragment ) )
            // InternalSqlParser.g:4307:1: (lv_op1_0_0= ruleOperandFragment )
            {
            // InternalSqlParser.g:4307:1: (lv_op1_0_0= ruleOperandFragment )
            // InternalSqlParser.g:4308:3: lv_op1_0_0= ruleOperandFragment
            {
             
            	        newCompositeNode(grammarAccess.getOperandAccess().getOp1OperandFragmentParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_63);
            lv_op1_0_0=ruleOperandFragment();

            state._fsp--;


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

            // InternalSqlParser.g:4324:2: ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0==VerticalLineVerticalLine||LA90_0==PlusSign||LA90_0==HyphenMinus||LA90_0==Solidus||LA90_0==RULE_STAR) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // InternalSqlParser.g:4324:3: ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) )
            	    {
            	    // InternalSqlParser.g:4324:3: ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) )
            	    int alt89=5;
            	    switch ( input.LA(1) ) {
            	    case PlusSign:
            	        {
            	        alt89=1;
            	        }
            	        break;
            	    case HyphenMinus:
            	        {
            	        alt89=2;
            	        }
            	        break;
            	    case VerticalLineVerticalLine:
            	        {
            	        alt89=3;
            	        }
            	        break;
            	    case RULE_STAR:
            	        {
            	        alt89=4;
            	        }
            	        break;
            	    case Solidus:
            	        {
            	        alt89=5;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 89, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt89) {
            	        case 1 :
            	            // InternalSqlParser.g:4324:4: ( () otherlv_2= PlusSign )
            	            {
            	            // InternalSqlParser.g:4324:4: ( () otherlv_2= PlusSign )
            	            // InternalSqlParser.g:4324:5: () otherlv_2= PlusSign
            	            {
            	            // InternalSqlParser.g:4324:5: ()
            	            // InternalSqlParser.g:4325:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getPlusLeftAction_1_0_0_0(),
            	                        current);
            	                

            	            }

            	            otherlv_2=(Token)match(input,PlusSign,FOLLOW_53); 

            	                	newLeafNode(otherlv_2, grammarAccess.getOperandAccess().getPlusSignKeyword_1_0_0_1());
            	                

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalSqlParser.g:4336:6: ( () otherlv_4= HyphenMinus )
            	            {
            	            // InternalSqlParser.g:4336:6: ( () otherlv_4= HyphenMinus )
            	            // InternalSqlParser.g:4336:7: () otherlv_4= HyphenMinus
            	            {
            	            // InternalSqlParser.g:4336:7: ()
            	            // InternalSqlParser.g:4337:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMinusLeftAction_1_0_1_0(),
            	                        current);
            	                

            	            }

            	            otherlv_4=(Token)match(input,HyphenMinus,FOLLOW_53); 

            	                	newLeafNode(otherlv_4, grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_0_1_1());
            	                

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // InternalSqlParser.g:4348:6: ( () otherlv_6= VerticalLineVerticalLine )
            	            {
            	            // InternalSqlParser.g:4348:6: ( () otherlv_6= VerticalLineVerticalLine )
            	            // InternalSqlParser.g:4348:7: () otherlv_6= VerticalLineVerticalLine
            	            {
            	            // InternalSqlParser.g:4348:7: ()
            	            // InternalSqlParser.g:4349:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getConcatLeftAction_1_0_2_0(),
            	                        current);
            	                

            	            }

            	            otherlv_6=(Token)match(input,VerticalLineVerticalLine,FOLLOW_53); 

            	                	newLeafNode(otherlv_6, grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_0_2_1());
            	                

            	            }


            	            }
            	            break;
            	        case 4 :
            	            // InternalSqlParser.g:4360:6: ( () this_STAR_8= RULE_STAR )
            	            {
            	            // InternalSqlParser.g:4360:6: ( () this_STAR_8= RULE_STAR )
            	            // InternalSqlParser.g:4360:7: () this_STAR_8= RULE_STAR
            	            {
            	            // InternalSqlParser.g:4360:7: ()
            	            // InternalSqlParser.g:4361:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMultiplyLeftAction_1_0_3_0(),
            	                        current);
            	                

            	            }

            	            this_STAR_8=(Token)match(input,RULE_STAR,FOLLOW_53); 
            	             
            	                newLeafNode(this_STAR_8, grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_0_3_1()); 
            	                

            	            }


            	            }
            	            break;
            	        case 5 :
            	            // InternalSqlParser.g:4371:6: ( () otherlv_10= Solidus )
            	            {
            	            // InternalSqlParser.g:4371:6: ( () otherlv_10= Solidus )
            	            // InternalSqlParser.g:4371:7: () otherlv_10= Solidus
            	            {
            	            // InternalSqlParser.g:4371:7: ()
            	            // InternalSqlParser.g:4372:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getDivisionLeftAction_1_0_4_0(),
            	                        current);
            	                

            	            }

            	            otherlv_10=(Token)match(input,Solidus,FOLLOW_53); 

            	                	newLeafNode(otherlv_10, grammarAccess.getOperandAccess().getSolidusKeyword_1_0_4_1());
            	                

            	            }


            	            }
            	            break;

            	    }

            	    // InternalSqlParser.g:4382:3: ( (lv_right_11_0= ruleOperandFragment ) )
            	    // InternalSqlParser.g:4383:1: (lv_right_11_0= ruleOperandFragment )
            	    {
            	    // InternalSqlParser.g:4383:1: (lv_right_11_0= ruleOperandFragment )
            	    // InternalSqlParser.g:4384:3: lv_right_11_0= ruleOperandFragment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOperandAccess().getRightOperandFragmentParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_63);
            	    lv_right_11_0=ruleOperandFragment();

            	    state._fsp--;


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
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4408:1: entryRuleOperandFragment returns [EObject current=null] : iv_ruleOperandFragment= ruleOperandFragment EOF ;
    public final EObject entryRuleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFragment = null;


        try {
            // InternalSqlParser.g:4409:2: (iv_ruleOperandFragment= ruleOperandFragment EOF )
            // InternalSqlParser.g:4410:2: iv_ruleOperandFragment= ruleOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperandFragment=ruleOperandFragment();

            state._fsp--;

             current =iv_ruleOperandFragment; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4417:1: ruleOperandFragment returns [EObject current=null] : ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) ) ;
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
            // InternalSqlParser.g:4420:28: ( ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) ) )
            // InternalSqlParser.g:4421:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) )
            {
            // InternalSqlParser.g:4421:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) )
            int alt91=7;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA91_1 = input.LA(2);

                if ( (LA91_1==LeftParenthesis) ) {
                    alt91=6;
                }
                else if ( (LA91_1==EOF||LA91_1==ORDERSIBLINGSBY||LA91_1==STRAIGHT_JOIN||LA91_1==FETCHFIRST||LA91_1==ISNOTNULL||LA91_1==NOTBETWEEN||LA91_1==NOTEXISTS||(LA91_1>=KW_FOLLOWING && LA91_1<=PRECEDING)||(LA91_1>=GROUPBY && LA91_1<=NOTLIKE)||LA91_1==ORDERBY||LA91_1==BETWEEN||(LA91_1>=ISNULL && LA91_1<=NATURAL)||(LA91_1>=EXCEPT && LA91_1<=HAVING)||(LA91_1>=NOTIN_1 && LA91_1<=OFFSET)||LA91_1==CROSS||LA91_1==INNER||(LA91_1>=LIMIT && LA91_1<=MINUS)||LA91_1==NULLS||(LA91_1>=RANGE && LA91_1<=UNION)||LA91_1==WHERE||(LA91_1>=DESC && LA91_1<=FULL)||LA91_1==JOIN||LA91_1==LEFT||LA91_1==LIKE||LA91_1==ROWS||LA91_1==THEN||LA91_1==WHEN||LA91_1==LeftParenthesisPlusSignRightParenthesis||LA91_1==AND||LA91_1==ASC||LA91_1==END||LA91_1==ExclamationMarkEqualsSign||(LA91_1>=LessThanSignEqualsSign && LA91_1<=IN)||(LA91_1>=OR && LA91_1<=VerticalLineVerticalLine)||(LA91_1>=RightParenthesis && LA91_1<=GreaterThanSign)||(LA91_1>=VerticalLine && LA91_1<=RightCurlyBracket)||(LA91_1>=RULE_JRNPARAM && LA91_1<=RULE_STAR)||(LA91_1>=RULE_STRING && LA91_1<=RULE_ID)) ) {
                    alt91=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 91, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt91=1;
                }
                break;
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt91=2;
                }
                break;
            case LeftParenthesis:
                {
                alt91=3;
                }
                break;
            case CAST:
                {
                alt91=4;
                }
                break;
            case EXTRACT:
                {
                alt91=5;
                }
                break;
            case CASE:
                {
                alt91=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }

            switch (alt91) {
                case 1 :
                    // InternalSqlParser.g:4421:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    {
                    // InternalSqlParser.g:4421:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    // InternalSqlParser.g:4422:1: (lv_column_0_0= ruleColumnOperand )
                    {
                    // InternalSqlParser.g:4422:1: (lv_column_0_0= ruleColumnOperand )
                    // InternalSqlParser.g:4423:3: lv_column_0_0= ruleColumnOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_column_0_0=ruleColumnOperand();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:4440:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    {
                    // InternalSqlParser.g:4440:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    // InternalSqlParser.g:4441:1: (lv_xop_1_0= ruleXOperandFragment )
                    {
                    // InternalSqlParser.g:4441:1: (lv_xop_1_0= ruleXOperandFragment )
                    // InternalSqlParser.g:4442:3: lv_xop_1_0= ruleXOperandFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_xop_1_0=ruleXOperandFragment();

                    state._fsp--;


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
                    break;
                case 3 :
                    // InternalSqlParser.g:4459:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:4459:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:4460:1: (lv_subq_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:4460:1: (lv_subq_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:4461:3: lv_subq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_subq_2_0=ruleSubQueryOperand();

                    state._fsp--;


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
                    break;
                case 4 :
                    // InternalSqlParser.g:4478:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    {
                    // InternalSqlParser.g:4478:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    // InternalSqlParser.g:4479:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    {
                    // InternalSqlParser.g:4479:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    // InternalSqlParser.g:4480:3: lv_fcast_3_0= ruleOpFunctionCast
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFcastOpFunctionCastParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_fcast_3_0=ruleOpFunctionCast();

                    state._fsp--;


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
                    break;
                case 5 :
                    // InternalSqlParser.g:4497:6: ( (lv_fext_4_0= ruleFunctionExtract ) )
                    {
                    // InternalSqlParser.g:4497:6: ( (lv_fext_4_0= ruleFunctionExtract ) )
                    // InternalSqlParser.g:4498:1: (lv_fext_4_0= ruleFunctionExtract )
                    {
                    // InternalSqlParser.g:4498:1: (lv_fext_4_0= ruleFunctionExtract )
                    // InternalSqlParser.g:4499:3: lv_fext_4_0= ruleFunctionExtract
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFextFunctionExtractParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_fext_4_0=ruleFunctionExtract();

                    state._fsp--;


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
                    break;
                case 6 :
                    // InternalSqlParser.g:4516:6: ( (lv_func_5_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:4516:6: ( (lv_func_5_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:4517:1: (lv_func_5_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:4517:1: (lv_func_5_0= ruleOperandFunction )
                    // InternalSqlParser.g:4518:3: lv_func_5_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFuncOperandFunctionParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_func_5_0=ruleOperandFunction();

                    state._fsp--;


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
                    break;
                case 7 :
                    // InternalSqlParser.g:4535:6: ( (lv_sqlcase_6_0= ruleSQLCASE ) )
                    {
                    // InternalSqlParser.g:4535:6: ( (lv_sqlcase_6_0= ruleSQLCASE ) )
                    // InternalSqlParser.g:4536:1: (lv_sqlcase_6_0= ruleSQLCASE )
                    {
                    // InternalSqlParser.g:4536:1: (lv_sqlcase_6_0= ruleSQLCASE )
                    // InternalSqlParser.g:4537:3: lv_sqlcase_6_0= ruleSQLCASE
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSqlcaseSQLCASEParserRuleCall_6_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_sqlcase_6_0=ruleSQLCASE();

                    state._fsp--;


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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4561:1: entryRuleOperandFunction returns [EObject current=null] : iv_ruleOperandFunction= ruleOperandFunction EOF ;
    public final EObject entryRuleOperandFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFunction = null;


        try {
            // InternalSqlParser.g:4562:2: (iv_ruleOperandFunction= ruleOperandFunction EOF )
            // InternalSqlParser.g:4563:2: iv_ruleOperandFunction= ruleOperandFunction EOF
            {
             newCompositeNode(grammarAccess.getOperandFunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperandFunction=ruleOperandFunction();

            state._fsp--;

             current =iv_ruleOperandFunction; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4570:1: ruleOperandFunction returns [EObject current=null] : ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) ;
    public final EObject ruleOperandFunction() throws RecognitionException {
        EObject current = null;

        Token lv_star_2_0=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_fname_1_0 = null;

        EObject lv_args_3_0 = null;

        EObject lv_fan_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4573:28: ( ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) )
            // InternalSqlParser.g:4574:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            {
            // InternalSqlParser.g:4574:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            // InternalSqlParser.g:4574:2: () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            {
            // InternalSqlParser.g:4574:2: ()
            // InternalSqlParser.g:4575:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperandFunctionAccess().getOpFunctionAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:4580:2: ( (lv_fname_1_0= ruleFNAME ) )
            // InternalSqlParser.g:4581:1: (lv_fname_1_0= ruleFNAME )
            {
            // InternalSqlParser.g:4581:1: (lv_fname_1_0= ruleFNAME )
            // InternalSqlParser.g:4582:3: lv_fname_1_0= ruleFNAME
            {
             
            	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFnameFNAMEParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_64);
            lv_fname_1_0=ruleFNAME();

            state._fsp--;


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

            // InternalSqlParser.g:4598:2: ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )?
            int alt92=3;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==RULE_STAR) ) {
                alt92=1;
            }
            else if ( (LA92_0==DISTINCT||LA92_0==EXTRACT||LA92_0==CAST||LA92_0==CASE||LA92_0==ALL||LA92_0==LeftParenthesis||(LA92_0>=RULE_JRPARAM && LA92_0<=RULE_JRNPARAM)||(LA92_0>=RULE_UNSIGNED && LA92_0<=RULE_SIGNED_DOUBLE)||(LA92_0>=RULE_STRING_ && LA92_0<=RULE_ID)) ) {
                alt92=2;
            }
            switch (alt92) {
                case 1 :
                    // InternalSqlParser.g:4598:3: ( (lv_star_2_0= RULE_STAR ) )
                    {
                    // InternalSqlParser.g:4598:3: ( (lv_star_2_0= RULE_STAR ) )
                    // InternalSqlParser.g:4599:1: (lv_star_2_0= RULE_STAR )
                    {
                    // InternalSqlParser.g:4599:1: (lv_star_2_0= RULE_STAR )
                    // InternalSqlParser.g:4600:3: lv_star_2_0= RULE_STAR
                    {
                    lv_star_2_0=(Token)match(input,RULE_STAR,FOLLOW_35); 

                    			newLeafNode(lv_star_2_0, grammarAccess.getOperandFunctionAccess().getStarSTARTerminalRuleCall_2_0_0()); 
                    		

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
                    break;
                case 2 :
                    // InternalSqlParser.g:4617:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    {
                    // InternalSqlParser.g:4617:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    // InternalSqlParser.g:4618:1: (lv_args_3_0= ruleOpFunctionArg )
                    {
                    // InternalSqlParser.g:4618:1: (lv_args_3_0= ruleOpFunctionArg )
                    // InternalSqlParser.g:4619:3: lv_args_3_0= ruleOpFunctionArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getArgsOpFunctionArgParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_35);
                    lv_args_3_0=ruleOpFunctionArg();

                    state._fsp--;


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
                    break;

            }

            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_65); 

                	newLeafNode(otherlv_4, grammarAccess.getOperandFunctionAccess().getRightParenthesisKeyword_3());
                
            // InternalSqlParser.g:4640:1: ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==OVER) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // InternalSqlParser.g:4641:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    {
                    // InternalSqlParser.g:4641:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    // InternalSqlParser.g:4642:3: lv_fan_5_0= ruleFunctionAnalytical
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFanFunctionAnalyticalParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_fan_5_0=ruleFunctionAnalytical();

                    state._fsp--;


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
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4666:1: entryRuleFunctionExtract returns [EObject current=null] : iv_ruleFunctionExtract= ruleFunctionExtract EOF ;
    public final EObject entryRuleFunctionExtract() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionExtract = null;


        try {
            // InternalSqlParser.g:4667:2: (iv_ruleFunctionExtract= ruleFunctionExtract EOF )
            // InternalSqlParser.g:4668:2: iv_ruleFunctionExtract= ruleFunctionExtract EOF
            {
             newCompositeNode(grammarAccess.getFunctionExtractRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunctionExtract=ruleFunctionExtract();

            state._fsp--;

             current =iv_ruleFunctionExtract; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4675:1: ruleFunctionExtract returns [EObject current=null] : (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis ) ;
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
            // InternalSqlParser.g:4678:28: ( (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis ) )
            // InternalSqlParser.g:4679:1: (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis )
            {
            // InternalSqlParser.g:4679:1: (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis )
            // InternalSqlParser.g:4680:2: otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis
            {
            otherlv_0=(Token)match(input,EXTRACT,FOLLOW_34); 

                	newLeafNode(otherlv_0, grammarAccess.getFunctionExtractAccess().getEXTRACTKeyword_0());
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_66); 

                	newLeafNode(otherlv_1, grammarAccess.getFunctionExtractAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:4689:1: ( (lv_v_2_0= ruleEXTRACT_VALUES ) )
            // InternalSqlParser.g:4690:1: (lv_v_2_0= ruleEXTRACT_VALUES )
            {
            // InternalSqlParser.g:4690:1: (lv_v_2_0= ruleEXTRACT_VALUES )
            // InternalSqlParser.g:4691:3: lv_v_2_0= ruleEXTRACT_VALUES
            {
             
            	        newCompositeNode(grammarAccess.getFunctionExtractAccess().getVEXTRACT_VALUESEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_14);
            lv_v_2_0=ruleEXTRACT_VALUES();

            state._fsp--;


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

            otherlv_3=(Token)match(input,FROM,FOLLOW_53); 

                	newLeafNode(otherlv_3, grammarAccess.getFunctionExtractAccess().getFROMKeyword_3());
                
            // InternalSqlParser.g:4712:1: ( (lv_operand_4_0= ruleOperandGroup ) )
            // InternalSqlParser.g:4713:1: (lv_operand_4_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:4713:1: (lv_operand_4_0= ruleOperandGroup )
            // InternalSqlParser.g:4714:3: lv_operand_4_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getFunctionExtractAccess().getOperandOperandGroupParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_operand_4_0=ruleOperandGroup();

            state._fsp--;


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

            otherlv_5=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_5, grammarAccess.getFunctionExtractAccess().getRightParenthesisKeyword_5());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4743:1: entryRuleFunctionAnalytical returns [EObject current=null] : iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF ;
    public final EObject entryRuleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionAnalytical = null;


        try {
            // InternalSqlParser.g:4744:2: (iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF )
            // InternalSqlParser.g:4745:2: iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF
            {
             newCompositeNode(grammarAccess.getFunctionAnalyticalRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunctionAnalytical=ruleFunctionAnalytical();

            state._fsp--;

             current =iv_ruleFunctionAnalytical; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4752:1: ruleFunctionAnalytical returns [EObject current=null] : (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_anClause_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4755:28: ( (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:4756:1: (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:4756:1: (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:4757:2: otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis
            {
            otherlv_0=(Token)match(input,OVER,FOLLOW_34); 

                	newLeafNode(otherlv_0, grammarAccess.getFunctionAnalyticalAccess().getOVERKeyword_0());
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_67); 

                	newLeafNode(otherlv_1, grammarAccess.getFunctionAnalyticalAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:4766:1: ( (lv_anClause_2_0= ruleAnalyticClause ) )
            // InternalSqlParser.g:4767:1: (lv_anClause_2_0= ruleAnalyticClause )
            {
            // InternalSqlParser.g:4767:1: (lv_anClause_2_0= ruleAnalyticClause )
            // InternalSqlParser.g:4768:3: lv_anClause_2_0= ruleAnalyticClause
            {
             
            	        newCompositeNode(grammarAccess.getFunctionAnalyticalAccess().getAnClauseAnalyticClauseParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_anClause_2_0=ruleAnalyticClause();

            state._fsp--;


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

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_3, grammarAccess.getFunctionAnalyticalAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4797:1: entryRuleAnalyticClause returns [EObject current=null] : iv_ruleAnalyticClause= ruleAnalyticClause EOF ;
    public final EObject entryRuleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticClause = null;


        try {
            // InternalSqlParser.g:4798:2: (iv_ruleAnalyticClause= ruleAnalyticClause EOF )
            // InternalSqlParser.g:4799:2: iv_ruleAnalyticClause= ruleAnalyticClause EOF
            {
             newCompositeNode(grammarAccess.getAnalyticClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnalyticClause=ruleAnalyticClause();

            state._fsp--;

             current =iv_ruleAnalyticClause; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4806:1: ruleAnalyticClause returns [EObject current=null] : ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? ) ;
    public final EObject ruleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject lv_abc_1_0 = null;

        EObject lv_obc_2_0 = null;

        EObject lv_winc_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4809:28: ( ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? ) )
            // InternalSqlParser.g:4810:1: ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? )
            {
            // InternalSqlParser.g:4810:1: ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? )
            // InternalSqlParser.g:4810:2: () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )?
            {
            // InternalSqlParser.g:4810:2: ()
            // InternalSqlParser.g:4811:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getAnalyticClauseAccess().getAnalyticClauseAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:4816:2: ( (lv_abc_1_0= ruleQueryPartitionClause ) )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==PARTITIONBY) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalSqlParser.g:4817:1: (lv_abc_1_0= ruleQueryPartitionClause )
                    {
                    // InternalSqlParser.g:4817:1: (lv_abc_1_0= ruleQueryPartitionClause )
                    // InternalSqlParser.g:4818:3: lv_abc_1_0= ruleQueryPartitionClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getAbcQueryPartitionClauseParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_68);
                    lv_abc_1_0=ruleQueryPartitionClause();

                    state._fsp--;


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
                    break;

            }

            // InternalSqlParser.g:4834:3: ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==ORDERSIBLINGSBY||LA96_0==ORDERBY) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // InternalSqlParser.g:4834:4: ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )?
                    {
                    // InternalSqlParser.g:4834:4: ( (lv_obc_2_0= ruleOrderByClause ) )
                    // InternalSqlParser.g:4835:1: (lv_obc_2_0= ruleOrderByClause )
                    {
                    // InternalSqlParser.g:4835:1: (lv_obc_2_0= ruleOrderByClause )
                    // InternalSqlParser.g:4836:3: lv_obc_2_0= ruleOrderByClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getObcOrderByClauseParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_69);
                    lv_obc_2_0=ruleOrderByClause();

                    state._fsp--;


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

                    // InternalSqlParser.g:4852:2: ( (lv_winc_3_0= ruleWindowingClause ) )?
                    int alt95=2;
                    int LA95_0 = input.LA(1);

                    if ( (LA95_0==RANGE||LA95_0==ROWS) ) {
                        alt95=1;
                    }
                    switch (alt95) {
                        case 1 :
                            // InternalSqlParser.g:4853:1: (lv_winc_3_0= ruleWindowingClause )
                            {
                            // InternalSqlParser.g:4853:1: (lv_winc_3_0= ruleWindowingClause )
                            // InternalSqlParser.g:4854:3: lv_winc_3_0= ruleWindowingClause
                            {
                             
                            	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getWincWindowingClauseParserRuleCall_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_2);
                            lv_winc_3_0=ruleWindowingClause();

                            state._fsp--;


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
                            break;

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4878:1: entryRuleWindowingClause returns [EObject current=null] : iv_ruleWindowingClause= ruleWindowingClause EOF ;
    public final EObject entryRuleWindowingClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClause = null;


        try {
            // InternalSqlParser.g:4879:2: (iv_ruleWindowingClause= ruleWindowingClause EOF )
            // InternalSqlParser.g:4880:2: iv_ruleWindowingClause= ruleWindowingClause EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWindowingClause=ruleWindowingClause();

            state._fsp--;

             current =iv_ruleWindowingClause; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4887:1: ruleWindowingClause returns [EObject current=null] : ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) ;
    public final EObject ruleWindowingClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_WindowingClauseBetween_2 = null;

        EObject this_WindowingClauseOperandPreceding_3 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4890:28: ( ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) )
            // InternalSqlParser.g:4891:1: ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            {
            // InternalSqlParser.g:4891:1: ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            // InternalSqlParser.g:4891:2: (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            {
            // InternalSqlParser.g:4891:2: (otherlv_0= ROWS | otherlv_1= RANGE )
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==ROWS) ) {
                alt97=1;
            }
            else if ( (LA97_0==RANGE) ) {
                alt97=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                throw nvae;
            }
            switch (alt97) {
                case 1 :
                    // InternalSqlParser.g:4892:2: otherlv_0= ROWS
                    {
                    otherlv_0=(Token)match(input,ROWS,FOLLOW_70); 

                        	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseAccess().getROWSKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4898:2: otherlv_1= RANGE
                    {
                    otherlv_1=(Token)match(input,RANGE,FOLLOW_70); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseAccess().getRANGEKeyword_0_1());
                        

                    }
                    break;

            }

            // InternalSqlParser.g:4902:2: (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==BETWEEN) ) {
                alt98=1;
            }
            else if ( (LA98_0==UNBOUNDEDPRECEDING||LA98_0==CURRENTROW||LA98_0==EXTRACT||LA98_0==CAST||LA98_0==CASE||LA98_0==LeftParenthesis||(LA98_0>=RULE_JRPARAM && LA98_0<=RULE_JRNPARAM)||(LA98_0>=RULE_UNSIGNED && LA98_0<=RULE_SIGNED_DOUBLE)||(LA98_0>=RULE_STRING_ && LA98_0<=RULE_ID)) ) {
                alt98=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }
            switch (alt98) {
                case 1 :
                    // InternalSqlParser.g:4903:5: this_WindowingClauseBetween_2= ruleWindowingClauseBetween
                    {
                     
                            newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseBetweenParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_WindowingClauseBetween_2=ruleWindowingClauseBetween();

                    state._fsp--;


                            current = this_WindowingClauseBetween_2;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4913:5: this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding
                    {
                     
                            newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseOperandPrecedingParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_2);
                    this_WindowingClauseOperandPreceding_3=ruleWindowingClauseOperandPreceding();

                    state._fsp--;


                            current = this_WindowingClauseOperandPreceding_3;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:4929:1: entryRuleWindowingClauseBetween returns [EObject current=null] : iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF ;
    public final EObject entryRuleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseBetween = null;


        try {
            // InternalSqlParser.g:4930:2: (iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF )
            // InternalSqlParser.g:4931:2: iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseBetweenRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWindowingClauseBetween=ruleWindowingClauseBetween();

            state._fsp--;

             current =iv_ruleWindowingClauseBetween; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:4938:1: ruleWindowingClauseBetween returns [EObject current=null] : (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) ;
    public final EObject ruleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_wcoP_1_0 = null;

        EObject lv_wcoF_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4941:28: ( (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) )
            // InternalSqlParser.g:4942:1: (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            {
            // InternalSqlParser.g:4942:1: (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            // InternalSqlParser.g:4943:2: otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            {
            otherlv_0=(Token)match(input,BETWEEN,FOLLOW_70); 

                	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseBetweenAccess().getBETWEENKeyword_0());
                
            // InternalSqlParser.g:4947:1: ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) )
            // InternalSqlParser.g:4948:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            {
            // InternalSqlParser.g:4948:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            // InternalSqlParser.g:4949:3: lv_wcoP_1_0= ruleWindowingClauseOperandPreceding
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoPWindowingClauseOperandPrecedingParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_61);
            lv_wcoP_1_0=ruleWindowingClauseOperandPreceding();

            state._fsp--;


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

            otherlv_2=(Token)match(input,AND,FOLLOW_71); 

                	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseBetweenAccess().getANDKeyword_2());
                
            // InternalSqlParser.g:4970:1: ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            // InternalSqlParser.g:4971:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            {
            // InternalSqlParser.g:4971:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            // InternalSqlParser.g:4972:3: lv_wcoF_3_0= ruleWindowingClauseOperandFollowing
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoFWindowingClauseOperandFollowingParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_wcoF_3_0=ruleWindowingClauseOperandFollowing();

            state._fsp--;


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

             leaveRule(); 
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
    // InternalSqlParser.g:4996:1: entryRuleWindowingClauseOperandFollowing returns [EObject current=null] : iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF ;
    public final EObject entryRuleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandFollowing = null;


        try {
            // InternalSqlParser.g:4997:2: (iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF )
            // InternalSqlParser.g:4998:2: iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWindowingClauseOperandFollowing=ruleWindowingClauseOperandFollowing();

            state._fsp--;

             current =iv_ruleWindowingClauseOperandFollowing; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5005:1: ruleWindowingClauseOperandFollowing returns [EObject current=null] : ( () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_exp_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5008:28: ( ( () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) ) )
            // InternalSqlParser.g:5009:1: ( () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) )
            {
            // InternalSqlParser.g:5009:1: ( () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) )
            // InternalSqlParser.g:5009:2: () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) )
            {
            // InternalSqlParser.g:5009:2: ()
            // InternalSqlParser.g:5010:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandFollowingAccess().getWindowingClauseOperandFollowingAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:5015:2: (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) )
            int alt100=3;
            switch ( input.LA(1) ) {
            case UNBOUNDEDFOLLOWING:
                {
                alt100=1;
                }
                break;
            case CURRENTROW:
                {
                alt100=2;
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
                alt100=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }

            switch (alt100) {
                case 1 :
                    // InternalSqlParser.g:5016:2: otherlv_1= UNBOUNDEDFOLLOWING
                    {
                    otherlv_1=(Token)match(input,UNBOUNDEDFOLLOWING,FOLLOW_2); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandFollowingAccess().getUNBOUNDEDFOLLOWINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5022:2: otherlv_2= CURRENTROW
                    {
                    otherlv_2=(Token)match(input,CURRENTROW,FOLLOW_2); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandFollowingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:5027:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) )
                    {
                    // InternalSqlParser.g:5027:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) )
                    // InternalSqlParser.g:5027:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING )
                    {
                    // InternalSqlParser.g:5027:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) )
                    // InternalSqlParser.g:5028:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    {
                    // InternalSqlParser.g:5028:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    // InternalSqlParser.g:5029:3: lv_exp_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingAccess().getExpAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_72);
                    lv_exp_3_0=ruleAnalyticExprArg();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWindowingClauseOperandFollowingRule());
                    	        }
                           		set(
                           			current, 
                           			"exp",
                            		lv_exp_3_0, 
                            		"com.jaspersoft.studio.data.Sql.AnalyticExprArg");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalSqlParser.g:5045:2: (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING )
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==PRECEDING) ) {
                        alt99=1;
                    }
                    else if ( (LA99_0==KW_FOLLOWING) ) {
                        alt99=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 99, 0, input);

                        throw nvae;
                    }
                    switch (alt99) {
                        case 1 :
                            // InternalSqlParser.g:5046:2: otherlv_4= PRECEDING
                            {
                            otherlv_4=(Token)match(input,PRECEDING,FOLLOW_2); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandFollowingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:5052:2: otherlv_5= KW_FOLLOWING
                            {
                            otherlv_5=(Token)match(input,KW_FOLLOWING,FOLLOW_2); 

                                	newLeafNode(otherlv_5, grammarAccess.getWindowingClauseOperandFollowingAccess().getFOLLOWINGKeyword_1_2_1_1());
                                

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5064:1: entryRuleWindowingClauseOperandPreceding returns [EObject current=null] : iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF ;
    public final EObject entryRuleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandPreceding = null;


        try {
            // InternalSqlParser.g:5065:2: (iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF )
            // InternalSqlParser.g:5066:2: iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWindowingClauseOperandPreceding=ruleWindowingClauseOperandPreceding();

            state._fsp--;

             current =iv_ruleWindowingClauseOperandPreceding; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5073:1: ruleWindowingClauseOperandPreceding returns [EObject current=null] : ( () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5076:28: ( ( () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) ) )
            // InternalSqlParser.g:5077:1: ( () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) )
            {
            // InternalSqlParser.g:5077:1: ( () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) )
            // InternalSqlParser.g:5077:2: () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) )
            {
            // InternalSqlParser.g:5077:2: ()
            // InternalSqlParser.g:5078:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandPrecedingAccess().getWindowingClauseOperandPrecedingAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:5083:2: (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) )
            int alt102=3;
            switch ( input.LA(1) ) {
            case UNBOUNDEDPRECEDING:
                {
                alt102=1;
                }
                break;
            case CURRENTROW:
                {
                alt102=2;
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
                alt102=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;
            }

            switch (alt102) {
                case 1 :
                    // InternalSqlParser.g:5084:2: otherlv_1= UNBOUNDEDPRECEDING
                    {
                    otherlv_1=(Token)match(input,UNBOUNDEDPRECEDING,FOLLOW_2); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandPrecedingAccess().getUNBOUNDEDPRECEDINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5090:2: otherlv_2= CURRENTROW
                    {
                    otherlv_2=(Token)match(input,CURRENTROW,FOLLOW_2); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandPrecedingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:5095:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) )
                    {
                    // InternalSqlParser.g:5095:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) )
                    // InternalSqlParser.g:5095:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING )
                    {
                    // InternalSqlParser.g:5095:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) )
                    // InternalSqlParser.g:5096:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    {
                    // InternalSqlParser.g:5096:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    // InternalSqlParser.g:5097:3: lv_expr_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingAccess().getExprAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_72);
                    lv_expr_3_0=ruleAnalyticExprArg();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWindowingClauseOperandPrecedingRule());
                    	        }
                           		set(
                           			current, 
                           			"expr",
                            		lv_expr_3_0, 
                            		"com.jaspersoft.studio.data.Sql.AnalyticExprArg");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalSqlParser.g:5113:2: (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING )
                    int alt101=2;
                    int LA101_0 = input.LA(1);

                    if ( (LA101_0==PRECEDING) ) {
                        alt101=1;
                    }
                    else if ( (LA101_0==KW_FOLLOWING) ) {
                        alt101=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 101, 0, input);

                        throw nvae;
                    }
                    switch (alt101) {
                        case 1 :
                            // InternalSqlParser.g:5114:2: otherlv_4= PRECEDING
                            {
                            otherlv_4=(Token)match(input,PRECEDING,FOLLOW_2); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandPrecedingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:5120:2: otherlv_5= KW_FOLLOWING
                            {
                            otherlv_5=(Token)match(input,KW_FOLLOWING,FOLLOW_2); 

                                	newLeafNode(otherlv_5, grammarAccess.getWindowingClauseOperandPrecedingAccess().getFOLLOWINGKeyword_1_2_1_1());
                                

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5132:1: entryRuleOrderByClause returns [EObject current=null] : iv_ruleOrderByClause= ruleOrderByClause EOF ;
    public final EObject entryRuleOrderByClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClause = null;


        try {
            // InternalSqlParser.g:5133:2: (iv_ruleOrderByClause= ruleOrderByClause EOF )
            // InternalSqlParser.g:5134:2: iv_ruleOrderByClause= ruleOrderByClause EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrderByClause=ruleOrderByClause();

            state._fsp--;

             current =iv_ruleOrderByClause; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5141:1: ruleOrderByClause returns [EObject current=null] : ( (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) ;
    public final EObject ruleOrderByClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_args_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5144:28: ( ( (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) )
            // InternalSqlParser.g:5145:1: ( (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            {
            // InternalSqlParser.g:5145:1: ( (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            // InternalSqlParser.g:5145:2: (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            {
            // InternalSqlParser.g:5145:2: (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY )
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==ORDERBY) ) {
                alt103=1;
            }
            else if ( (LA103_0==ORDERSIBLINGSBY) ) {
                alt103=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }
            switch (alt103) {
                case 1 :
                    // InternalSqlParser.g:5146:2: otherlv_0= ORDERBY
                    {
                    otherlv_0=(Token)match(input,ORDERBY,FOLLOW_70); 

                        	newLeafNode(otherlv_0, grammarAccess.getOrderByClauseAccess().getORDERBYKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5152:2: otherlv_1= ORDERSIBLINGSBY
                    {
                    otherlv_1=(Token)match(input,ORDERSIBLINGSBY,FOLLOW_70); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseAccess().getORDERSIBLINGSBYKeyword_0_1());
                        

                    }
                    break;

            }

            // InternalSqlParser.g:5156:2: ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            // InternalSqlParser.g:5157:1: (lv_args_2_0= ruleOrderByClauseArgs )
            {
            // InternalSqlParser.g:5157:1: (lv_args_2_0= ruleOrderByClauseArgs )
            // InternalSqlParser.g:5158:3: lv_args_2_0= ruleOrderByClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseAccess().getArgsOrderByClauseArgsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_args_2_0=ruleOrderByClauseArgs();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOrderByClauseRule());
            	        }
                   		set(
                   			current, 
                   			"args",
                    		lv_args_2_0, 
                    		"com.jaspersoft.studio.data.Sql.OrderByClauseArgs");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5182:1: entryRuleOrderByClauseArgs returns [EObject current=null] : iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF ;
    public final EObject entryRuleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArgs = null;


        try {
            // InternalSqlParser.g:5183:2: (iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF )
            // InternalSqlParser.g:5184:2: iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseArgsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrderByClauseArgs=ruleOrderByClauseArgs();

            state._fsp--;

             current =iv_ruleOrderByClauseArgs; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5191:1: ruleOrderByClauseArgs returns [EObject current=null] : (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) ;
    public final EObject ruleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5194:28: ( (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) )
            // InternalSqlParser.g:5195:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            {
            // InternalSqlParser.g:5195:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            // InternalSqlParser.g:5196:5: this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getOrderByClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_OrderByClauseArg_0=ruleOrderByClauseArg();

            state._fsp--;


                    current = this_OrderByClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:5204:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==Comma) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // InternalSqlParser.g:5204:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    {
                    // InternalSqlParser.g:5204:2: ()
                    // InternalSqlParser.g:5205:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByClauseArgsAccess().getOBCArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:5210:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    int cnt104=0;
                    loop104:
                    do {
                        int alt104=2;
                        int LA104_0 = input.LA(1);

                        if ( (LA104_0==Comma) ) {
                            alt104=1;
                        }


                        switch (alt104) {
                    	case 1 :
                    	    // InternalSqlParser.g:5211:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_70); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:5215:1: ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    // InternalSqlParser.g:5216:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    {
                    	    // InternalSqlParser.g:5216:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    // InternalSqlParser.g:5217:3: lv_entries_3_0= ruleOrderByClauseArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getEntriesOrderByClauseArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleOrderByClauseArg();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt104 >= 1 ) break loop104;
                                EarlyExitException eee =
                                    new EarlyExitException(104, input);
                                throw eee;
                        }
                        cnt104++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5241:1: entryRuleOrderByClauseArg returns [EObject current=null] : iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF ;
    public final EObject entryRuleOrderByClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArg = null;


        try {
            // InternalSqlParser.g:5242:2: (iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF )
            // InternalSqlParser.g:5243:2: iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseArgRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrderByClauseArg=ruleOrderByClauseArg();

            state._fsp--;

             current =iv_ruleOrderByClauseArg; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5250:1: ruleOrderByClauseArg returns [EObject current=null] : ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? ) ;
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
            // InternalSqlParser.g:5253:28: ( ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? ) )
            // InternalSqlParser.g:5254:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? )
            {
            // InternalSqlParser.g:5254:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? )
            // InternalSqlParser.g:5254:2: ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )?
            {
            // InternalSqlParser.g:5254:2: ( (lv_col_0_0= ruleAnalyticExprArg ) )
            // InternalSqlParser.g:5255:1: (lv_col_0_0= ruleAnalyticExprArg )
            {
            // InternalSqlParser.g:5255:1: (lv_col_0_0= ruleAnalyticExprArg )
            // InternalSqlParser.g:5256:3: lv_col_0_0= ruleAnalyticExprArg
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseArgAccess().getColAnalyticExprArgParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_73);
            lv_col_0_0=ruleAnalyticExprArg();

            state._fsp--;


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

            // InternalSqlParser.g:5272:2: (otherlv_1= ASC | otherlv_2= DESC )?
            int alt106=3;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==ASC) ) {
                alt106=1;
            }
            else if ( (LA106_0==DESC) ) {
                alt106=2;
            }
            switch (alt106) {
                case 1 :
                    // InternalSqlParser.g:5273:2: otherlv_1= ASC
                    {
                    otherlv_1=(Token)match(input,ASC,FOLLOW_74); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseArgAccess().getASCKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5279:2: otherlv_2= DESC
                    {
                    otherlv_2=(Token)match(input,DESC,FOLLOW_74); 

                        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgAccess().getDESCKeyword_1_1());
                        

                    }
                    break;

            }

            // InternalSqlParser.g:5283:3: (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==NULLS) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // InternalSqlParser.g:5284:2: otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST )
                    {
                    otherlv_3=(Token)match(input,NULLS,FOLLOW_75); 

                        	newLeafNode(otherlv_3, grammarAccess.getOrderByClauseArgAccess().getNULLSKeyword_2_0());
                        
                    // InternalSqlParser.g:5288:1: (otherlv_4= FIRST | otherlv_5= LAST )
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( (LA107_0==FIRST) ) {
                        alt107=1;
                    }
                    else if ( (LA107_0==LAST) ) {
                        alt107=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 107, 0, input);

                        throw nvae;
                    }
                    switch (alt107) {
                        case 1 :
                            // InternalSqlParser.g:5289:2: otherlv_4= FIRST
                            {
                            otherlv_4=(Token)match(input,FIRST,FOLLOW_2); 

                                	newLeafNode(otherlv_4, grammarAccess.getOrderByClauseArgAccess().getFIRSTKeyword_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:5295:2: otherlv_5= LAST
                            {
                            otherlv_5=(Token)match(input,LAST,FOLLOW_2); 

                                	newLeafNode(otherlv_5, grammarAccess.getOrderByClauseArgAccess().getLASTKeyword_2_1_1());
                                

                            }
                            break;

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5307:1: entryRuleQueryPartitionClause returns [EObject current=null] : iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF ;
    public final EObject entryRuleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQueryPartitionClause = null;


        try {
            // InternalSqlParser.g:5308:2: (iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF )
            // InternalSqlParser.g:5309:2: iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF
            {
             newCompositeNode(grammarAccess.getQueryPartitionClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQueryPartitionClause=ruleQueryPartitionClause();

            state._fsp--;

             current =iv_ruleQueryPartitionClause; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5316:1: ruleQueryPartitionClause returns [EObject current=null] : (otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) ) ) ;
    public final EObject ruleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_1_0 = null;

        EObject this_AnalyticExprArgs_3 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5319:28: ( (otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) ) ) )
            // InternalSqlParser.g:5320:1: (otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) ) )
            {
            // InternalSqlParser.g:5320:1: (otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) ) )
            // InternalSqlParser.g:5321:2: otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) )
            {
            otherlv_0=(Token)match(input,PARTITIONBY,FOLLOW_70); 

                	newLeafNode(otherlv_0, grammarAccess.getQueryPartitionClauseAccess().getPARTITIONBYKeyword_0());
                
            // InternalSqlParser.g:5325:1: ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) )
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==EXTRACT||LA109_0==CAST||LA109_0==CASE||(LA109_0>=RULE_JRPARAM && LA109_0<=RULE_JRNPARAM)||(LA109_0>=RULE_UNSIGNED && LA109_0<=RULE_SIGNED_DOUBLE)||(LA109_0>=RULE_STRING_ && LA109_0<=RULE_ID)) ) {
                alt109=1;
            }
            else if ( (LA109_0==LeftParenthesis) ) {
                int LA109_2 = input.LA(2);

                if ( (LA109_2==EXTRACT||LA109_2==CAST||LA109_2==CASE||LA109_2==LeftParenthesis||(LA109_2>=RULE_JRPARAM && LA109_2<=RULE_JRNPARAM)||(LA109_2>=RULE_UNSIGNED && LA109_2<=RULE_SIGNED_DOUBLE)||(LA109_2>=RULE_STRING_ && LA109_2<=RULE_ID)) ) {
                    alt109=2;
                }
                else if ( (LA109_2==SELECT) ) {
                    alt109=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 109, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 109, 0, input);

                throw nvae;
            }
            switch (alt109) {
                case 1 :
                    // InternalSqlParser.g:5325:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    {
                    // InternalSqlParser.g:5325:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    // InternalSqlParser.g:5326:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    {
                    // InternalSqlParser.g:5326:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    // InternalSqlParser.g:5327:3: lv_args_1_0= ruleAnalyticExprArgs
                    {
                     
                    	        newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getArgsAnalyticExprArgsParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_args_1_0=ruleAnalyticExprArgs();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getQueryPartitionClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"args",
                            		lv_args_1_0, 
                            		"com.jaspersoft.studio.data.Sql.AnalyticExprArgs");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5344:6: (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis )
                    {
                    // InternalSqlParser.g:5344:6: (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis )
                    // InternalSqlParser.g:5345:2: otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis
                    {
                    otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_70); 

                        	newLeafNode(otherlv_2, grammarAccess.getQueryPartitionClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getAnalyticExprArgsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_35);
                    this_AnalyticExprArgs_3=ruleAnalyticExprArgs();

                    state._fsp--;


                            current = this_AnalyticExprArgs_3;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_2); 

                        	newLeafNode(otherlv_4, grammarAccess.getQueryPartitionClauseAccess().getRightParenthesisKeyword_1_1_2());
                        

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5371:1: entryRuleAnalyticExprArgs returns [EObject current=null] : iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF ;
    public final EObject entryRuleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArgs = null;


        try {
            // InternalSqlParser.g:5372:2: (iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF )
            // InternalSqlParser.g:5373:2: iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF
            {
             newCompositeNode(grammarAccess.getAnalyticExprArgsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnalyticExprArgs=ruleAnalyticExprArgs();

            state._fsp--;

             current =iv_ruleAnalyticExprArgs; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5380:1: ruleAnalyticExprArgs returns [EObject current=null] : (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) ;
    public final EObject ruleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AnalyticExprArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5383:28: ( (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) )
            // InternalSqlParser.g:5384:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            {
            // InternalSqlParser.g:5384:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            // InternalSqlParser.g:5385:5: this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getAnalyticExprArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_AnalyticExprArg_0=ruleAnalyticExprArg();

            state._fsp--;


                    current = this_AnalyticExprArg_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:5393:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==Comma) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // InternalSqlParser.g:5393:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    {
                    // InternalSqlParser.g:5393:2: ()
                    // InternalSqlParser.g:5394:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getAnalyticExprArgsAccess().getAExpArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:5399:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    int cnt110=0;
                    loop110:
                    do {
                        int alt110=2;
                        int LA110_0 = input.LA(1);

                        if ( (LA110_0==Comma) ) {
                            alt110=1;
                        }


                        switch (alt110) {
                    	case 1 :
                    	    // InternalSqlParser.g:5400:2: otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_70); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getAnalyticExprArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:5404:1: ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    // InternalSqlParser.g:5405:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    {
                    	    // InternalSqlParser.g:5405:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    // InternalSqlParser.g:5406:3: lv_entries_3_0= ruleAnalyticExprArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getEntriesAnalyticExprArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleAnalyticExprArg();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt110 >= 1 ) break loop110;
                                EarlyExitException eee =
                                    new EarlyExitException(110, input);
                                throw eee;
                        }
                        cnt110++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5430:1: entryRuleAnalyticExprArg returns [EObject current=null] : iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF ;
    public final EObject entryRuleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArg = null;


        try {
            // InternalSqlParser.g:5431:2: (iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF )
            // InternalSqlParser.g:5432:2: iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF
            {
             newCompositeNode(grammarAccess.getAnalyticExprArgRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnalyticExprArg=ruleAnalyticExprArg();

            state._fsp--;

             current =iv_ruleAnalyticExprArg; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5439:1: ruleAnalyticExprArg returns [EObject current=null] : ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5442:28: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) )
            // InternalSqlParser.g:5443:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            {
            // InternalSqlParser.g:5443:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            // InternalSqlParser.g:5443:2: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            {
            // InternalSqlParser.g:5443:2: ( (lv_ce_0_0= ruleOperand ) )
            // InternalSqlParser.g:5444:1: (lv_ce_0_0= ruleOperand )
            {
            // InternalSqlParser.g:5444:1: (lv_ce_0_0= ruleOperand )
            // InternalSqlParser.g:5445:3: lv_ce_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getCeOperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_29);
            lv_ce_0_0=ruleOperand();

            state._fsp--;


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

            // InternalSqlParser.g:5461:2: ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( ((LA112_0>=RULE_STRING && LA112_0<=RULE_ID)) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // InternalSqlParser.g:5462:1: (lv_colAlias_1_0= ruleDbObjectName )
                    {
                    // InternalSqlParser.g:5462:1: (lv_colAlias_1_0= ruleDbObjectName )
                    // InternalSqlParser.g:5463:3: lv_colAlias_1_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getColAliasDbObjectNameParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_colAlias_1_0=ruleDbObjectName();

                    state._fsp--;


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
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5487:1: entryRuleOpFunctionArg returns [EObject current=null] : iv_ruleOpFunctionArg= ruleOpFunctionArg EOF ;
    public final EObject entryRuleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArg = null;


        try {
            // InternalSqlParser.g:5488:2: (iv_ruleOpFunctionArg= ruleOpFunctionArg EOF )
            // InternalSqlParser.g:5489:2: iv_ruleOpFunctionArg= ruleOpFunctionArg EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOpFunctionArg=ruleOpFunctionArg();

            state._fsp--;

             current =iv_ruleOpFunctionArg; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5496:1: ruleOpFunctionArg returns [EObject current=null] : (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) ;
    public final EObject ruleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OpFunctionArgOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5499:28: ( (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) )
            // InternalSqlParser.g:5500:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            {
            // InternalSqlParser.g:5500:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            // InternalSqlParser.g:5501:5: this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOpFunctionArgAccess().getOpFunctionArgOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_OpFunctionArgOperand_0=ruleOpFunctionArgOperand();

            state._fsp--;


                    current = this_OpFunctionArgOperand_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:5509:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==Comma) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // InternalSqlParser.g:5509:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    {
                    // InternalSqlParser.g:5509:2: ()
                    // InternalSqlParser.g:5510:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOpFunctionArgAccess().getOpFListEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:5515:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
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
                    	    // InternalSqlParser.g:5516:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_76); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOpFunctionArgAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:5520:1: ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    // InternalSqlParser.g:5521:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    {
                    	    // InternalSqlParser.g:5521:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    // InternalSqlParser.g:5522:3: lv_entries_3_0= ruleOpFunctionArgOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOpFunctionArgAccess().getEntriesOpFunctionArgOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleOpFunctionArgOperand();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt113 >= 1 ) break loop113;
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

             leaveRule(); 
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
    // InternalSqlParser.g:5546:1: entryRuleOpFunctionArgOperand returns [EObject current=null] : iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF ;
    public final EObject entryRuleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgOperand = null;


        try {
            // InternalSqlParser.g:5547:2: (iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF )
            // InternalSqlParser.g:5548:2: iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOpFunctionArgOperand=ruleOpFunctionArgOperand();

            state._fsp--;

             current =iv_ruleOpFunctionArgOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5555:1: ruleOpFunctionArgOperand returns [EObject current=null] : ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) ;
    public final EObject ruleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject lv_op_0_1 = null;

        EObject lv_op_0_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5558:28: ( ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) )
            // InternalSqlParser.g:5559:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            {
            // InternalSqlParser.g:5559:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            // InternalSqlParser.g:5560:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            {
            // InternalSqlParser.g:5560:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            // InternalSqlParser.g:5561:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            {
            // InternalSqlParser.g:5561:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==DISTINCT||LA115_0==ALL) ) {
                alt115=1;
            }
            else if ( (LA115_0==EXTRACT||LA115_0==CAST||LA115_0==CASE||LA115_0==LeftParenthesis||(LA115_0>=RULE_JRPARAM && LA115_0<=RULE_JRNPARAM)||(LA115_0>=RULE_UNSIGNED && LA115_0<=RULE_SIGNED_DOUBLE)||(LA115_0>=RULE_STRING_ && LA115_0<=RULE_ID)) ) {
                alt115=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;
            }
            switch (alt115) {
                case 1 :
                    // InternalSqlParser.g:5562:3: lv_op_0_1= ruleOpFunctionArgAgregate
                    {
                     
                    	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOpFunctionArgAgregateParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_op_0_1=ruleOpFunctionArgAgregate();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:5577:8: lv_op_0_2= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOperandParserRuleCall_0_1()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_op_0_2=ruleOperand();

                    state._fsp--;


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
                    break;

            }


            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5603:1: entryRuleOpFunctionCast returns [EObject current=null] : iv_ruleOpFunctionCast= ruleOpFunctionCast EOF ;
    public final EObject entryRuleOpFunctionCast() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionCast = null;


        try {
            // InternalSqlParser.g:5604:2: (iv_ruleOpFunctionCast= ruleOpFunctionCast EOF )
            // InternalSqlParser.g:5605:2: iv_ruleOpFunctionCast= ruleOpFunctionCast EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionCastRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOpFunctionCast=ruleOpFunctionCast();

            state._fsp--;

             current =iv_ruleOpFunctionCast; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5612:1: ruleOpFunctionCast returns [EObject current=null] : (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis ) ;
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
            // InternalSqlParser.g:5615:28: ( (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis ) )
            // InternalSqlParser.g:5616:1: (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis )
            {
            // InternalSqlParser.g:5616:1: (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis )
            // InternalSqlParser.g:5617:2: otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis
            {
            otherlv_0=(Token)match(input,CAST,FOLLOW_53); 

                	newLeafNode(otherlv_0, grammarAccess.getOpFunctionCastAccess().getCASTKeyword_0());
                
            // InternalSqlParser.g:5621:1: ( (lv_op_1_0= ruleOperandGroup ) )
            // InternalSqlParser.g:5622:1: (lv_op_1_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:5622:1: (lv_op_1_0= ruleOperandGroup )
            // InternalSqlParser.g:5623:3: lv_op_1_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getOpFunctionCastAccess().getOpOperandGroupParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_77);
            lv_op_1_0=ruleOperandGroup();

            state._fsp--;


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

            otherlv_2=(Token)match(input,AS,FOLLOW_38); 

                	newLeafNode(otherlv_2, grammarAccess.getOpFunctionCastAccess().getASKeyword_2());
                
            // InternalSqlParser.g:5644:1: ( (lv_type_3_0= RULE_ID ) )
            // InternalSqlParser.g:5645:1: (lv_type_3_0= RULE_ID )
            {
            // InternalSqlParser.g:5645:1: (lv_type_3_0= RULE_ID )
            // InternalSqlParser.g:5646:3: lv_type_3_0= RULE_ID
            {
            lv_type_3_0=(Token)match(input,RULE_ID,FOLLOW_78); 

            			newLeafNode(lv_type_3_0, grammarAccess.getOpFunctionCastAccess().getTypeIDTerminalRuleCall_3_0()); 
            		

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

            // InternalSqlParser.g:5662:2: (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==LeftParenthesis) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // InternalSqlParser.g:5663:2: otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis
                    {
                    otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_25); 

                        	newLeafNode(otherlv_4, grammarAccess.getOpFunctionCastAccess().getLeftParenthesisKeyword_4_0());
                        
                    // InternalSqlParser.g:5667:1: ( (lv_p_5_0= RULE_INT ) )
                    // InternalSqlParser.g:5668:1: (lv_p_5_0= RULE_INT )
                    {
                    // InternalSqlParser.g:5668:1: (lv_p_5_0= RULE_INT )
                    // InternalSqlParser.g:5669:3: lv_p_5_0= RULE_INT
                    {
                    lv_p_5_0=(Token)match(input,RULE_INT,FOLLOW_79); 

                    			newLeafNode(lv_p_5_0, grammarAccess.getOpFunctionCastAccess().getPINTTerminalRuleCall_4_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOpFunctionCastRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"p",
                            		lv_p_5_0, 
                            		"com.jaspersoft.studio.data.Sql.INT");
                    	    

                    }


                    }

                    // InternalSqlParser.g:5685:2: (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )?
                    int alt116=2;
                    int LA116_0 = input.LA(1);

                    if ( (LA116_0==Comma) ) {
                        alt116=1;
                    }
                    switch (alt116) {
                        case 1 :
                            // InternalSqlParser.g:5686:2: otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) )
                            {
                            otherlv_6=(Token)match(input,Comma,FOLLOW_25); 

                                	newLeafNode(otherlv_6, grammarAccess.getOpFunctionCastAccess().getCommaKeyword_4_2_0());
                                
                            // InternalSqlParser.g:5690:1: ( (lv_p2_7_0= RULE_INT ) )
                            // InternalSqlParser.g:5691:1: (lv_p2_7_0= RULE_INT )
                            {
                            // InternalSqlParser.g:5691:1: (lv_p2_7_0= RULE_INT )
                            // InternalSqlParser.g:5692:3: lv_p2_7_0= RULE_INT
                            {
                            lv_p2_7_0=(Token)match(input,RULE_INT,FOLLOW_35); 

                            			newLeafNode(lv_p2_7_0, grammarAccess.getOpFunctionCastAccess().getP2INTTerminalRuleCall_4_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOpFunctionCastRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"p2",
                                    		lv_p2_7_0, 
                                    		"com.jaspersoft.studio.data.Sql.INT");
                            	    

                            }


                            }


                            }
                            break;

                    }

                    otherlv_8=(Token)match(input,RightParenthesis,FOLLOW_35); 

                        	newLeafNode(otherlv_8, grammarAccess.getOpFunctionCastAccess().getRightParenthesisKeyword_4_3());
                        

                    }
                    break;

            }

            otherlv_9=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_9, grammarAccess.getOpFunctionCastAccess().getRightParenthesisKeyword_5());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5726:1: entryRuleOpFunctionArgAgregate returns [EObject current=null] : iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF ;
    public final EObject entryRuleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgAgregate = null;


        try {
            // InternalSqlParser.g:5727:2: (iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF )
            // InternalSqlParser.g:5728:2: iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgAgregateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOpFunctionArgAgregate=ruleOpFunctionArgAgregate();

            state._fsp--;

             current =iv_ruleOpFunctionArgAgregate; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5735:1: ruleOpFunctionArgAgregate returns [EObject current=null] : ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand ) ;
    public final EObject ruleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5738:28: ( ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand ) )
            // InternalSqlParser.g:5739:1: ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand )
            {
            // InternalSqlParser.g:5739:1: ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand )
            // InternalSqlParser.g:5739:2: (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand
            {
            // InternalSqlParser.g:5739:2: (otherlv_0= ALL | otherlv_1= DISTINCT )
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==ALL) ) {
                alt118=1;
            }
            else if ( (LA118_0==DISTINCT) ) {
                alt118=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;
            }
            switch (alt118) {
                case 1 :
                    // InternalSqlParser.g:5740:2: otherlv_0= ALL
                    {
                    otherlv_0=(Token)match(input,ALL,FOLLOW_53); 

                        	newLeafNode(otherlv_0, grammarAccess.getOpFunctionArgAgregateAccess().getALLKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5746:2: otherlv_1= DISTINCT
                    {
                    otherlv_1=(Token)match(input,DISTINCT,FOLLOW_53); 

                        	newLeafNode(otherlv_1, grammarAccess.getOpFunctionArgAgregateAccess().getDISTINCTKeyword_0_1());
                        

                    }
                    break;

            }

             
                    newCompositeNode(grammarAccess.getOpFunctionArgAgregateAccess().getOperandParserRuleCall_1()); 
                
            pushFollow(FOLLOW_2);
            this_Operand_2=ruleOperand();

            state._fsp--;


                    current = this_Operand_2;
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5767:1: entryRuleXOperandFragment returns [EObject current=null] : iv_ruleXOperandFragment= ruleXOperandFragment EOF ;
    public final EObject entryRuleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOperandFragment = null;


        try {
            // InternalSqlParser.g:5768:2: (iv_ruleXOperandFragment= ruleXOperandFragment EOF )
            // InternalSqlParser.g:5769:2: iv_ruleXOperandFragment= ruleXOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXOperandFragment=ruleXOperandFragment();

            state._fsp--;

             current =iv_ruleXOperandFragment; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5776:1: ruleXOperandFragment returns [EObject current=null] : ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) ) ;
    public final EObject ruleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_param_0_0 = null;

        EObject lv_eparam_1_0 = null;

        EObject lv_scalar_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5779:28: ( ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) ) )
            // InternalSqlParser.g:5780:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) )
            {
            // InternalSqlParser.g:5780:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) )
            int alt119=3;
            switch ( input.LA(1) ) {
            case RULE_JRPARAM:
                {
                alt119=1;
                }
                break;
            case RULE_JRNPARAM:
                {
                alt119=2;
                }
                break;
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt119=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 119, 0, input);

                throw nvae;
            }

            switch (alt119) {
                case 1 :
                    // InternalSqlParser.g:5780:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    {
                    // InternalSqlParser.g:5780:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    // InternalSqlParser.g:5781:1: (lv_param_0_0= ruleParameterOperand )
                    {
                    // InternalSqlParser.g:5781:1: (lv_param_0_0= ruleParameterOperand )
                    // InternalSqlParser.g:5782:3: lv_param_0_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_param_0_0=ruleParameterOperand();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:5799:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    {
                    // InternalSqlParser.g:5799:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    // InternalSqlParser.g:5800:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    {
                    // InternalSqlParser.g:5800:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    // InternalSqlParser.g:5801:3: lv_eparam_1_0= ruleExclamationParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_eparam_1_0=ruleExclamationParameterOperand();

                    state._fsp--;


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
                    break;
                case 3 :
                    // InternalSqlParser.g:5818:6: ( (lv_scalar_2_0= ruleScalarNumberOperand ) )
                    {
                    // InternalSqlParser.g:5818:6: ( (lv_scalar_2_0= ruleScalarNumberOperand ) )
                    // InternalSqlParser.g:5819:1: (lv_scalar_2_0= ruleScalarNumberOperand )
                    {
                    // InternalSqlParser.g:5819:1: (lv_scalar_2_0= ruleScalarNumberOperand )
                    // InternalSqlParser.g:5820:3: lv_scalar_2_0= ruleScalarNumberOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getScalarScalarNumberOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_scalar_2_0=ruleScalarNumberOperand();

                    state._fsp--;


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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5844:1: entryRuleParameterOperand returns [EObject current=null] : iv_ruleParameterOperand= ruleParameterOperand EOF ;
    public final EObject entryRuleParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterOperand = null;


        try {
            // InternalSqlParser.g:5845:2: (iv_ruleParameterOperand= ruleParameterOperand EOF )
            // InternalSqlParser.g:5846:2: iv_ruleParameterOperand= ruleParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameterOperand=ruleParameterOperand();

            state._fsp--;

             current =iv_ruleParameterOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5853:1: ruleParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRPARAM ) ) ;
    public final EObject ruleParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:5856:28: ( ( (lv_prm_0_0= RULE_JRPARAM ) ) )
            // InternalSqlParser.g:5857:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            {
            // InternalSqlParser.g:5857:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            // InternalSqlParser.g:5858:1: (lv_prm_0_0= RULE_JRPARAM )
            {
            // InternalSqlParser.g:5858:1: (lv_prm_0_0= RULE_JRPARAM )
            // InternalSqlParser.g:5859:3: lv_prm_0_0= RULE_JRPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRPARAM,FOLLOW_2); 

            			newLeafNode(lv_prm_0_0, grammarAccess.getParameterOperandAccess().getPrmJRPARAMTerminalRuleCall_0()); 
            		

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

             leaveRule(); 
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
    // InternalSqlParser.g:5883:1: entryRuleExclamationParameterOperand returns [EObject current=null] : iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF ;
    public final EObject entryRuleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclamationParameterOperand = null;


        try {
            // InternalSqlParser.g:5884:2: (iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF )
            // InternalSqlParser.g:5885:2: iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExclamationParameterOperand=ruleExclamationParameterOperand();

            state._fsp--;

             current =iv_ruleExclamationParameterOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5892:1: ruleExclamationParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRNPARAM ) ) ;
    public final EObject ruleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:5895:28: ( ( (lv_prm_0_0= RULE_JRNPARAM ) ) )
            // InternalSqlParser.g:5896:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            {
            // InternalSqlParser.g:5896:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            // InternalSqlParser.g:5897:1: (lv_prm_0_0= RULE_JRNPARAM )
            {
            // InternalSqlParser.g:5897:1: (lv_prm_0_0= RULE_JRNPARAM )
            // InternalSqlParser.g:5898:3: lv_prm_0_0= RULE_JRNPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_2); 

            			newLeafNode(lv_prm_0_0, grammarAccess.getExclamationParameterOperandAccess().getPrmJRNPARAMTerminalRuleCall_0()); 
            		

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

             leaveRule(); 
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
    // InternalSqlParser.g:5922:1: entryRuleColumnOperand returns [EObject current=null] : iv_ruleColumnOperand= ruleColumnOperand EOF ;
    public final EObject entryRuleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOperand = null;


        try {
            // InternalSqlParser.g:5923:2: (iv_ruleColumnOperand= ruleColumnOperand EOF )
            // InternalSqlParser.g:5924:2: iv_ruleColumnOperand= ruleColumnOperand EOF
            {
             newCompositeNode(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleColumnOperand=ruleColumnOperand();

            state._fsp--;

             current =iv_ruleColumnOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5931:1: ruleColumnOperand returns [EObject current=null] : ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? ) ;
    public final EObject ruleColumnOperand() throws RecognitionException {
        EObject current = null;

        Token lv_ora_1_0=null;
        EObject lv_cfull_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5934:28: ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? ) )
            // InternalSqlParser.g:5935:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? )
            {
            // InternalSqlParser.g:5935:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? )
            // InternalSqlParser.g:5935:2: ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )?
            {
            // InternalSqlParser.g:5935:2: ( (lv_cfull_0_0= ruleColumnFull ) )
            // InternalSqlParser.g:5936:1: (lv_cfull_0_0= ruleColumnFull )
            {
            // InternalSqlParser.g:5936:1: (lv_cfull_0_0= ruleColumnFull )
            // InternalSqlParser.g:5937:3: lv_cfull_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_80);
            lv_cfull_0_0=ruleColumnFull();

            state._fsp--;


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

            // InternalSqlParser.g:5953:2: ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==LeftParenthesisPlusSignRightParenthesis) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // InternalSqlParser.g:5954:1: (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis )
                    {
                    // InternalSqlParser.g:5954:1: (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis )
                    // InternalSqlParser.g:5955:3: lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis
                    {
                    lv_ora_1_0=(Token)match(input,LeftParenthesisPlusSignRightParenthesis,FOLLOW_2); 

                            newLeafNode(lv_ora_1_0, grammarAccess.getColumnOperandAccess().getOraLeftParenthesisPlusSignRightParenthesisKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getColumnOperandRule());
                    	        }
                           		setWithLastConsumed(current, "ora", lv_ora_1_0, "(+)");
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:5977:1: entryRuleSubQueryOperand returns [EObject current=null] : iv_ruleSubQueryOperand= ruleSubQueryOperand EOF ;
    public final EObject entryRuleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubQueryOperand = null;


        try {
            // InternalSqlParser.g:5978:2: (iv_ruleSubQueryOperand= ruleSubQueryOperand EOF )
            // InternalSqlParser.g:5979:2: iv_ruleSubQueryOperand= ruleSubQueryOperand EOF
            {
             newCompositeNode(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSubQueryOperand=ruleSubQueryOperand();

            state._fsp--;

             current =iv_ruleSubQueryOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:5986:1: ruleSubQueryOperand returns [EObject current=null] : ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_sel_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5989:28: ( ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:5990:1: ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:5990:1: ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:5990:2: () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis
            {
            // InternalSqlParser.g:5990:2: ()
            // InternalSqlParser.g:5991:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSubQueryOperandAccess().getSubQueryOperandAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_3); 

                	newLeafNode(otherlv_1, grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:6001:1: ( (lv_sel_2_0= ruleSelectQuery ) )
            // InternalSqlParser.g:6002:1: (lv_sel_2_0= ruleSelectQuery )
            {
            // InternalSqlParser.g:6002:1: (lv_sel_2_0= ruleSelectQuery )
            // InternalSqlParser.g:6003:3: lv_sel_2_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getSubQueryOperandAccess().getSelSelectQueryParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_35);
            lv_sel_2_0=ruleSelectQuery();

            state._fsp--;


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

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_3, grammarAccess.getSubQueryOperandAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:6032:1: entryRuleScalarOperand returns [EObject current=null] : iv_ruleScalarOperand= ruleScalarOperand EOF ;
    public final EObject entryRuleScalarOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarOperand = null;


        try {
            // InternalSqlParser.g:6033:2: (iv_ruleScalarOperand= ruleScalarOperand EOF )
            // InternalSqlParser.g:6034:2: iv_ruleScalarOperand= ruleScalarOperand EOF
            {
             newCompositeNode(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScalarOperand=ruleScalarOperand();

            state._fsp--;

             current =iv_ruleScalarOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6041:1: ruleScalarOperand returns [EObject current=null] : ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) ) ;
    public final EObject ruleScalarOperand() throws RecognitionException {
        EObject current = null;

        Token lv_sodbl_1_0=null;
        Token lv_sodate_2_0=null;
        Token lv_sotime_3_0=null;
        Token lv_sodt_4_0=null;
        AntlrDatatypeRuleToken lv_sostr_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6044:28: ( ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) ) )
            // InternalSqlParser.g:6045:1: ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) )
            {
            // InternalSqlParser.g:6045:1: ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) )
            int alt121=5;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt121=1;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt121=2;
                }
                break;
            case RULE_DATE:
                {
                alt121=3;
                }
                break;
            case RULE_TIME:
                {
                alt121=4;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt121=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 121, 0, input);

                throw nvae;
            }

            switch (alt121) {
                case 1 :
                    // InternalSqlParser.g:6045:2: ( (lv_sostr_0_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:6045:2: ( (lv_sostr_0_0= ruleStringOperand ) )
                    // InternalSqlParser.g:6046:1: (lv_sostr_0_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:6046:1: (lv_sostr_0_0= ruleStringOperand )
                    // InternalSqlParser.g:6047:3: lv_sostr_0_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_sostr_0_0=ruleStringOperand();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:6064:6: ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // InternalSqlParser.g:6064:6: ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) )
                    // InternalSqlParser.g:6065:1: (lv_sodbl_1_0= RULE_SIGNED_DOUBLE )
                    {
                    // InternalSqlParser.g:6065:1: (lv_sodbl_1_0= RULE_SIGNED_DOUBLE )
                    // InternalSqlParser.g:6066:3: lv_sodbl_1_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_1_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_2); 

                    			newLeafNode(lv_sodbl_1_0, grammarAccess.getScalarOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_1_0()); 
                    		

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
                    break;
                case 3 :
                    // InternalSqlParser.g:6083:6: ( (lv_sodate_2_0= RULE_DATE ) )
                    {
                    // InternalSqlParser.g:6083:6: ( (lv_sodate_2_0= RULE_DATE ) )
                    // InternalSqlParser.g:6084:1: (lv_sodate_2_0= RULE_DATE )
                    {
                    // InternalSqlParser.g:6084:1: (lv_sodate_2_0= RULE_DATE )
                    // InternalSqlParser.g:6085:3: lv_sodate_2_0= RULE_DATE
                    {
                    lv_sodate_2_0=(Token)match(input,RULE_DATE,FOLLOW_2); 

                    			newLeafNode(lv_sodate_2_0, grammarAccess.getScalarOperandAccess().getSodateDATETerminalRuleCall_2_0()); 
                    		

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
                    break;
                case 4 :
                    // InternalSqlParser.g:6102:6: ( (lv_sotime_3_0= RULE_TIME ) )
                    {
                    // InternalSqlParser.g:6102:6: ( (lv_sotime_3_0= RULE_TIME ) )
                    // InternalSqlParser.g:6103:1: (lv_sotime_3_0= RULE_TIME )
                    {
                    // InternalSqlParser.g:6103:1: (lv_sotime_3_0= RULE_TIME )
                    // InternalSqlParser.g:6104:3: lv_sotime_3_0= RULE_TIME
                    {
                    lv_sotime_3_0=(Token)match(input,RULE_TIME,FOLLOW_2); 

                    			newLeafNode(lv_sotime_3_0, grammarAccess.getScalarOperandAccess().getSotimeTIMETerminalRuleCall_3_0()); 
                    		

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
                    break;
                case 5 :
                    // InternalSqlParser.g:6121:6: ( (lv_sodt_4_0= RULE_TIMESTAMP ) )
                    {
                    // InternalSqlParser.g:6121:6: ( (lv_sodt_4_0= RULE_TIMESTAMP ) )
                    // InternalSqlParser.g:6122:1: (lv_sodt_4_0= RULE_TIMESTAMP )
                    {
                    // InternalSqlParser.g:6122:1: (lv_sodt_4_0= RULE_TIMESTAMP )
                    // InternalSqlParser.g:6123:3: lv_sodt_4_0= RULE_TIMESTAMP
                    {
                    lv_sodt_4_0=(Token)match(input,RULE_TIMESTAMP,FOLLOW_2); 

                    			newLeafNode(lv_sodt_4_0, grammarAccess.getScalarOperandAccess().getSodtTIMESTAMPTerminalRuleCall_4_0()); 
                    		

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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:6147:1: entryRuleScalarNumberOperand returns [EObject current=null] : iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF ;
    public final EObject entryRuleScalarNumberOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarNumberOperand = null;


        try {
            // InternalSqlParser.g:6148:2: (iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF )
            // InternalSqlParser.g:6149:2: iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF
            {
             newCompositeNode(grammarAccess.getScalarNumberOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScalarNumberOperand=ruleScalarNumberOperand();

            state._fsp--;

             current =iv_ruleScalarNumberOperand; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6156:1: ruleScalarNumberOperand returns [EObject current=null] : ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) ) ;
    public final EObject ruleScalarNumberOperand() throws RecognitionException {
        EObject current = null;

        Token lv_soUInt_0_0=null;
        Token lv_soint_1_0=null;
        Token lv_sodbl_2_0=null;
        AntlrDatatypeRuleToken lv_sostr_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6159:28: ( ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) ) )
            // InternalSqlParser.g:6160:1: ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) )
            {
            // InternalSqlParser.g:6160:1: ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) )
            int alt122=4;
            switch ( input.LA(1) ) {
            case RULE_UNSIGNED:
                {
                alt122=1;
                }
                break;
            case RULE_INT:
                {
                alt122=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt122=3;
                }
                break;
            case RULE_STRING_:
                {
                alt122=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }

            switch (alt122) {
                case 1 :
                    // InternalSqlParser.g:6160:2: ( (lv_soUInt_0_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:6160:2: ( (lv_soUInt_0_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:6161:1: (lv_soUInt_0_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:6161:1: (lv_soUInt_0_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:6162:3: lv_soUInt_0_0= RULE_UNSIGNED
                    {
                    lv_soUInt_0_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_2); 

                    			newLeafNode(lv_soUInt_0_0, grammarAccess.getScalarNumberOperandAccess().getSoUIntUNSIGNEDTerminalRuleCall_0_0()); 
                    		

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
                    break;
                case 2 :
                    // InternalSqlParser.g:6179:6: ( (lv_soint_1_0= RULE_INT ) )
                    {
                    // InternalSqlParser.g:6179:6: ( (lv_soint_1_0= RULE_INT ) )
                    // InternalSqlParser.g:6180:1: (lv_soint_1_0= RULE_INT )
                    {
                    // InternalSqlParser.g:6180:1: (lv_soint_1_0= RULE_INT )
                    // InternalSqlParser.g:6181:3: lv_soint_1_0= RULE_INT
                    {
                    lv_soint_1_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    			newLeafNode(lv_soint_1_0, grammarAccess.getScalarNumberOperandAccess().getSointINTTerminalRuleCall_1_0()); 
                    		

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
                    break;
                case 3 :
                    // InternalSqlParser.g:6198:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // InternalSqlParser.g:6198:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    // InternalSqlParser.g:6199:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    {
                    // InternalSqlParser.g:6199:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    // InternalSqlParser.g:6200:3: lv_sodbl_2_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_2_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_2); 

                    			newLeafNode(lv_sodbl_2_0, grammarAccess.getScalarNumberOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_2_0()); 
                    		

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
                    break;
                case 4 :
                    // InternalSqlParser.g:6217:6: ( (lv_sostr_3_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:6217:6: ( (lv_sostr_3_0= ruleStringOperand ) )
                    // InternalSqlParser.g:6218:1: (lv_sostr_3_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:6218:1: (lv_sostr_3_0= ruleStringOperand )
                    // InternalSqlParser.g:6219:3: lv_sostr_3_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getScalarNumberOperandAccess().getSostrStringOperandParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_sostr_3_0=ruleStringOperand();

                    state._fsp--;


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
                    break;

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:6243:1: entryRuleSQLCASE returns [EObject current=null] : iv_ruleSQLCASE= ruleSQLCASE EOF ;
    public final EObject entryRuleSQLCASE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCASE = null;


        try {
            // InternalSqlParser.g:6244:2: (iv_ruleSQLCASE= ruleSQLCASE EOF )
            // InternalSqlParser.g:6245:2: iv_ruleSQLCASE= ruleSQLCASE EOF
            {
             newCompositeNode(grammarAccess.getSQLCASERule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSQLCASE=ruleSQLCASE();

            state._fsp--;

             current =iv_ruleSQLCASE; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6252:1: ruleSQLCASE returns [EObject current=null] : (otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END ) ;
    public final EObject ruleSQLCASE() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_expr_1_0 = null;

        EObject lv_when_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6255:28: ( (otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END ) )
            // InternalSqlParser.g:6256:1: (otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END )
            {
            // InternalSqlParser.g:6256:1: (otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END )
            // InternalSqlParser.g:6257:2: otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END
            {
            otherlv_0=(Token)match(input,CASE,FOLLOW_81); 

                	newLeafNode(otherlv_0, grammarAccess.getSQLCASEAccess().getCASEKeyword_0());
                
            // InternalSqlParser.g:6261:1: ( (lv_expr_1_0= ruleFullExpression ) )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==NOTEXISTS||LA123_0==EXTRACT||LA123_0==EXISTS||LA123_0==NOTIN_1||LA123_0==CAST||LA123_0==CASE||(LA123_0>=NOT && LA123_0<=NOT_1)||LA123_0==X||LA123_0==IN||LA123_0==LeftParenthesis||(LA123_0>=RULE_JRPARAM && LA123_0<=RULE_JRNPARAM)||(LA123_0>=RULE_UNSIGNED && LA123_0<=RULE_SIGNED_DOUBLE)||(LA123_0>=RULE_STRING_ && LA123_0<=RULE_ID)) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // InternalSqlParser.g:6262:1: (lv_expr_1_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:6262:1: (lv_expr_1_0= ruleFullExpression )
                    // InternalSqlParser.g:6263:3: lv_expr_1_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSQLCASEAccess().getExprFullExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_81);
                    lv_expr_1_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSQLCASERule());
                    	        }
                           		set(
                           			current, 
                           			"expr",
                            		lv_expr_1_0, 
                            		"com.jaspersoft.studio.data.Sql.FullExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:6279:3: ( (lv_when_2_0= ruleSQLCaseWhens ) )
            // InternalSqlParser.g:6280:1: (lv_when_2_0= ruleSQLCaseWhens )
            {
            // InternalSqlParser.g:6280:1: (lv_when_2_0= ruleSQLCaseWhens )
            // InternalSqlParser.g:6281:3: lv_when_2_0= ruleSQLCaseWhens
            {
             
            	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWhenSQLCaseWhensParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_82);
            lv_when_2_0=ruleSQLCaseWhens();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSQLCASERule());
            	        }
                   		set(
                   			current, 
                   			"when",
                    		lv_when_2_0, 
                    		"com.jaspersoft.studio.data.Sql.SQLCaseWhens");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,END,FOLLOW_2); 

                	newLeafNode(otherlv_3, grammarAccess.getSQLCASEAccess().getENDKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:6310:1: entryRuleSQLCaseWhens returns [EObject current=null] : iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF ;
    public final EObject entryRuleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCaseWhens = null;


        try {
            // InternalSqlParser.g:6311:2: (iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF )
            // InternalSqlParser.g:6312:2: iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF
            {
             newCompositeNode(grammarAccess.getSQLCaseWhensRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSQLCaseWhens=ruleSQLCaseWhens();

            state._fsp--;

             current =iv_ruleSQLCaseWhens; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6319:1: ruleSQLCaseWhens returns [EObject current=null] : (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) ;
    public final EObject ruleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject this_SqlCaseWhen_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6322:28: ( (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) )
            // InternalSqlParser.g:6323:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            {
            // InternalSqlParser.g:6323:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            // InternalSqlParser.g:6324:5: this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getSqlCaseWhenParserRuleCall_0()); 
                
            pushFollow(FOLLOW_83);
            this_SqlCaseWhen_0=ruleSqlCaseWhen();

            state._fsp--;


                    current = this_SqlCaseWhen_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:6332:1: ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==WHEN) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // InternalSqlParser.g:6332:2: () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    {
                    // InternalSqlParser.g:6332:2: ()
                    // InternalSqlParser.g:6333:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getSQLCaseWhensAccess().getWhenListEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:6338:2: ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    int cnt124=0;
                    loop124:
                    do {
                        int alt124=2;
                        int LA124_0 = input.LA(1);

                        if ( (LA124_0==WHEN) ) {
                            alt124=1;
                        }


                        switch (alt124) {
                    	case 1 :
                    	    // InternalSqlParser.g:6339:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    {
                    	    // InternalSqlParser.g:6339:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    // InternalSqlParser.g:6340:3: lv_entries_2_0= ruleSqlCaseWhen
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getEntriesSqlCaseWhenParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_83);
                    	    lv_entries_2_0=ruleSqlCaseWhen();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt124 >= 1 ) break loop124;
                                EarlyExitException eee =
                                    new EarlyExitException(124, input);
                                throw eee;
                        }
                        cnt124++;
                    } while (true);


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:6364:1: entryRuleSqlCaseWhen returns [EObject current=null] : iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF ;
    public final EObject entryRuleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSqlCaseWhen = null;


        try {
            // InternalSqlParser.g:6365:2: (iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF )
            // InternalSqlParser.g:6366:2: iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF
            {
             newCompositeNode(grammarAccess.getSqlCaseWhenRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSqlCaseWhen=ruleSqlCaseWhen();

            state._fsp--;

             current =iv_ruleSqlCaseWhen; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6373:1: ruleSqlCaseWhen returns [EObject current=null] : (otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )? ) ;
    public final EObject ruleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expr_1_0 = null;

        EObject lv_texp_3_0 = null;

        EObject lv_eexp_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6376:28: ( (otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )? ) )
            // InternalSqlParser.g:6377:1: (otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )? )
            {
            // InternalSqlParser.g:6377:1: (otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )? )
            // InternalSqlParser.g:6378:2: otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )?
            {
            otherlv_0=(Token)match(input,WHEN,FOLLOW_17); 

                	newLeafNode(otherlv_0, grammarAccess.getSqlCaseWhenAccess().getWHENKeyword_0());
                
            // InternalSqlParser.g:6382:1: ( (lv_expr_1_0= ruleFullExpression ) )
            // InternalSqlParser.g:6383:1: (lv_expr_1_0= ruleFullExpression )
            {
            // InternalSqlParser.g:6383:1: (lv_expr_1_0= ruleFullExpression )
            // InternalSqlParser.g:6384:3: lv_expr_1_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getExprFullExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_84);
            lv_expr_1_0=ruleFullExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_1_0, 
                    		"com.jaspersoft.studio.data.Sql.FullExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,THEN,FOLLOW_53); 

                	newLeafNode(otherlv_2, grammarAccess.getSqlCaseWhenAccess().getTHENKeyword_2());
                
            // InternalSqlParser.g:6405:1: ( (lv_texp_3_0= ruleOperandGroup ) )
            // InternalSqlParser.g:6406:1: (lv_texp_3_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:6406:1: (lv_texp_3_0= ruleOperandGroup )
            // InternalSqlParser.g:6407:3: lv_texp_3_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getTexpOperandGroupParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_85);
            lv_texp_3_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
            	        }
                   		set(
                   			current, 
                   			"texp",
                    		lv_texp_3_0, 
                    		"com.jaspersoft.studio.data.Sql.OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalSqlParser.g:6423:2: (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==ELSE) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // InternalSqlParser.g:6424:2: otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) )
                    {
                    otherlv_4=(Token)match(input,ELSE,FOLLOW_53); 

                        	newLeafNode(otherlv_4, grammarAccess.getSqlCaseWhenAccess().getELSEKeyword_4_0());
                        
                    // InternalSqlParser.g:6428:1: ( (lv_eexp_5_0= ruleOperandGroup ) )
                    // InternalSqlParser.g:6429:1: (lv_eexp_5_0= ruleOperandGroup )
                    {
                    // InternalSqlParser.g:6429:1: (lv_eexp_5_0= ruleOperandGroup )
                    // InternalSqlParser.g:6430:3: lv_eexp_5_0= ruleOperandGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getEexpOperandGroupParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_eexp_5_0=ruleOperandGroup();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
                    	        }
                           		set(
                           			current, 
                           			"eexp",
                            		lv_eexp_5_0, 
                            		"com.jaspersoft.studio.data.Sql.OperandGroup");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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
    // InternalSqlParser.g:6454:1: entryRuleJoinType returns [String current=null] : iv_ruleJoinType= ruleJoinType EOF ;
    public final String entryRuleJoinType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleJoinType = null;


        try {
            // InternalSqlParser.g:6455:1: (iv_ruleJoinType= ruleJoinType EOF )
            // InternalSqlParser.g:6456:2: iv_ruleJoinType= ruleJoinType EOF
            {
             newCompositeNode(grammarAccess.getJoinTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleJoinType=ruleJoinType();

            state._fsp--;

             current =iv_ruleJoinType.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6463:1: ruleJoinType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN ) ;
    public final AntlrDatatypeRuleToken ruleJoinType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6467:6: ( ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN ) )
            // InternalSqlParser.g:6468:1: ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN )
            {
            // InternalSqlParser.g:6468:1: ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN )
            // InternalSqlParser.g:6468:2: (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN
            {
            // InternalSqlParser.g:6468:2: (kw= NATURAL )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==NATURAL) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // InternalSqlParser.g:6469:2: kw= NATURAL
                    {
                    kw=(Token)match(input,NATURAL,FOLLOW_86); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getNATURALKeyword_0()); 
                        

                    }
                    break;

            }

            // InternalSqlParser.g:6474:3: (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )?
            int alt130=5;
            switch ( input.LA(1) ) {
                case INNER:
                    {
                    alt130=1;
                    }
                    break;
                case RIGHT:
                case FULL:
                case LEFT:
                    {
                    alt130=2;
                    }
                    break;
                case CROSS:
                    {
                    alt130=3;
                    }
                    break;
                case STRAIGHT_JOIN:
                    {
                    alt130=4;
                    }
                    break;
            }

            switch (alt130) {
                case 1 :
                    // InternalSqlParser.g:6475:2: kw= INNER
                    {
                    kw=(Token)match(input,INNER,FOLLOW_87); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getINNERKeyword_1_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6481:6: ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? )
                    {
                    // InternalSqlParser.g:6481:6: ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? )
                    // InternalSqlParser.g:6481:7: (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )?
                    {
                    // InternalSqlParser.g:6481:7: (kw= LEFT | kw= RIGHT | kw= FULL )
                    int alt128=3;
                    switch ( input.LA(1) ) {
                    case LEFT:
                        {
                        alt128=1;
                        }
                        break;
                    case RIGHT:
                        {
                        alt128=2;
                        }
                        break;
                    case FULL:
                        {
                        alt128=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 128, 0, input);

                        throw nvae;
                    }

                    switch (alt128) {
                        case 1 :
                            // InternalSqlParser.g:6482:2: kw= LEFT
                            {
                            kw=(Token)match(input,LEFT,FOLLOW_88); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getLEFTKeyword_1_1_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:6489:2: kw= RIGHT
                            {
                            kw=(Token)match(input,RIGHT,FOLLOW_88); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getRIGHTKeyword_1_1_0_1()); 
                                

                            }
                            break;
                        case 3 :
                            // InternalSqlParser.g:6496:2: kw= FULL
                            {
                            kw=(Token)match(input,FULL,FOLLOW_88); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getFULLKeyword_1_1_0_2()); 
                                

                            }
                            break;

                    }

                    // InternalSqlParser.g:6501:2: (kw= OUTER )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==OUTER) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // InternalSqlParser.g:6502:2: kw= OUTER
                            {
                            kw=(Token)match(input,OUTER,FOLLOW_87); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getOUTERKeyword_1_1_1()); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:6509:2: kw= CROSS
                    {
                    kw=(Token)match(input,CROSS,FOLLOW_87); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getCROSSKeyword_1_2()); 
                        

                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:6516:2: kw= STRAIGHT_JOIN
                    {
                    kw=(Token)match(input,STRAIGHT_JOIN,FOLLOW_87); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getSTRAIGHT_JOINKeyword_1_3()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,JOIN,FOLLOW_2); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getJOINKeyword_2()); 
                

            }


            }

             leaveRule();
                
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
    // InternalSqlParser.g:6535:1: entryRuleDBID returns [String current=null] : iv_ruleDBID= ruleDBID EOF ;
    public final String entryRuleDBID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDBID = null;


        try {
            // InternalSqlParser.g:6536:1: (iv_ruleDBID= ruleDBID EOF )
            // InternalSqlParser.g:6537:2: iv_ruleDBID= ruleDBID EOF
            {
             newCompositeNode(grammarAccess.getDBIDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDBID=ruleDBID();

            state._fsp--;

             current =iv_ruleDBID.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6544:1: ruleDBID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDBID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_DBNAME_1=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6548:6: ( (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) )
            // InternalSqlParser.g:6549:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            {
            // InternalSqlParser.g:6549:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            int alt131=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt131=1;
                }
                break;
            case RULE_DBNAME:
                {
                alt131=2;
                }
                break;
            case RULE_STRING:
                {
                alt131=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 131, 0, input);

                throw nvae;
            }

            switch (alt131) {
                case 1 :
                    // InternalSqlParser.g:6549:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getDBIDAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6557:10: this_DBNAME_1= RULE_DBNAME
                    {
                    this_DBNAME_1=(Token)match(input,RULE_DBNAME,FOLLOW_2); 

                    		current.merge(this_DBNAME_1);
                        
                     
                        newLeafNode(this_DBNAME_1, grammarAccess.getDBIDAccess().getDBNAMETerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:6565:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    		current.merge(this_STRING_2);
                        
                     
                        newLeafNode(this_STRING_2, grammarAccess.getDBIDAccess().getSTRINGTerminalRuleCall_2()); 
                        

                    }
                    break;

            }


            }

             leaveRule();
                
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
    // InternalSqlParser.g:6580:1: entryRuleStringOperand returns [String current=null] : iv_ruleStringOperand= ruleStringOperand EOF ;
    public final String entryRuleStringOperand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringOperand = null;


        try {
            // InternalSqlParser.g:6581:1: (iv_ruleStringOperand= ruleStringOperand EOF )
            // InternalSqlParser.g:6582:2: iv_ruleStringOperand= ruleStringOperand EOF
            {
             newCompositeNode(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringOperand=ruleStringOperand();

            state._fsp--;

             current =iv_ruleStringOperand.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6589:1: ruleStringOperand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING__0= RULE_STRING_ ;
    public final AntlrDatatypeRuleToken ruleStringOperand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING__0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6593:6: (this_STRING__0= RULE_STRING_ )
            // InternalSqlParser.g:6594:5: this_STRING__0= RULE_STRING_
            {
            this_STRING__0=(Token)match(input,RULE_STRING_,FOLLOW_2); 

            		current.merge(this_STRING__0);
                
             
                newLeafNode(this_STRING__0, grammarAccess.getStringOperandAccess().getSTRING_TerminalRuleCall()); 
                

            }

             leaveRule();
                
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
    // InternalSqlParser.g:6609:1: entryRuleFNAME returns [String current=null] : iv_ruleFNAME= ruleFNAME EOF ;
    public final String entryRuleFNAME() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFNAME = null;


        try {
            // InternalSqlParser.g:6610:1: (iv_ruleFNAME= ruleFNAME EOF )
            // InternalSqlParser.g:6611:2: iv_ruleFNAME= ruleFNAME EOF
            {
             newCompositeNode(grammarAccess.getFNAMERule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFNAME=ruleFNAME();

            state._fsp--;

             current =iv_ruleFNAME.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalSqlParser.g:6618:1: ruleFNAME returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= LeftParenthesis ) ;
    public final AntlrDatatypeRuleToken ruleFNAME() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6622:6: ( (this_ID_0= RULE_ID kw= LeftParenthesis ) )
            // InternalSqlParser.g:6623:1: (this_ID_0= RULE_ID kw= LeftParenthesis )
            {
            // InternalSqlParser.g:6623:1: (this_ID_0= RULE_ID kw= LeftParenthesis )
            // InternalSqlParser.g:6623:6: this_ID_0= RULE_ID kw= LeftParenthesis
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getFNAMEAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,LeftParenthesis,FOLLOW_2); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getFNAMEAccess().getLeftParenthesisKeyword_1()); 
                

            }


            }

             leaveRule();
                
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


    // $ANTLR start "entryRuleIntegerValue"
    // InternalSqlParser.g:6644:1: entryRuleIntegerValue returns [EObject current=null] : iv_ruleIntegerValue= ruleIntegerValue EOF ;
    public final EObject entryRuleIntegerValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerValue = null;


        try {
            // InternalSqlParser.g:6645:2: (iv_ruleIntegerValue= ruleIntegerValue EOF )
            // InternalSqlParser.g:6646:2: iv_ruleIntegerValue= ruleIntegerValue EOF
            {
             newCompositeNode(grammarAccess.getIntegerValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntegerValue=ruleIntegerValue();

            state._fsp--;

             current =iv_ruleIntegerValue; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleIntegerValue"


    // $ANTLR start "ruleIntegerValue"
    // InternalSqlParser.g:6653:1: ruleIntegerValue returns [EObject current=null] : ( (lv_integer_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerValue() throws RecognitionException {
        EObject current = null;

        Token lv_integer_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6656:28: ( ( (lv_integer_0_0= RULE_INT ) ) )
            // InternalSqlParser.g:6657:1: ( (lv_integer_0_0= RULE_INT ) )
            {
            // InternalSqlParser.g:6657:1: ( (lv_integer_0_0= RULE_INT ) )
            // InternalSqlParser.g:6658:1: (lv_integer_0_0= RULE_INT )
            {
            // InternalSqlParser.g:6658:1: (lv_integer_0_0= RULE_INT )
            // InternalSqlParser.g:6659:3: lv_integer_0_0= RULE_INT
            {
            lv_integer_0_0=(Token)match(input,RULE_INT,FOLLOW_2); 

            			newLeafNode(lv_integer_0_0, grammarAccess.getIntegerValueAccess().getIntegerINTTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntegerValueRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"integer",
                    		lv_integer_0_0, 
                    		"com.jaspersoft.studio.data.Sql.INT");
            	    

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntegerValue"


    // $ANTLR start "ruleEXTRACT_VALUES"
    // InternalSqlParser.g:6683:1: ruleEXTRACT_VALUES returns [Enumerator current=null] : ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) ) ;
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
            // InternalSqlParser.g:6685:28: ( ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) ) )
            // InternalSqlParser.g:6686:1: ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) )
            {
            // InternalSqlParser.g:6686:1: ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) )
            int alt132=20;
            switch ( input.LA(1) ) {
            case MICROSECOND:
                {
                alt132=1;
                }
                break;
            case SECOND:
                {
                alt132=2;
                }
                break;
            case MINUTE:
                {
                alt132=3;
                }
                break;
            case HOUR:
                {
                alt132=4;
                }
                break;
            case DAY:
                {
                alt132=5;
                }
                break;
            case WEEK:
                {
                alt132=6;
                }
                break;
            case MONTH:
                {
                alt132=7;
                }
                break;
            case QUARTER:
                {
                alt132=8;
                }
                break;
            case YEAR:
                {
                alt132=9;
                }
                break;
            case SECOND_MICROSECOND:
                {
                alt132=10;
                }
                break;
            case MINUTE_MICROSECOND:
                {
                alt132=11;
                }
                break;
            case MINUTE_SECOND:
                {
                alt132=12;
                }
                break;
            case HOUR_MICROSECOND:
                {
                alt132=13;
                }
                break;
            case HOUR_SECOND:
                {
                alt132=14;
                }
                break;
            case HOUR_MINUTE:
                {
                alt132=15;
                }
                break;
            case DAY_MICROSECOND:
                {
                alt132=16;
                }
                break;
            case DAY_SECOND:
                {
                alt132=17;
                }
                break;
            case DAY_MINUTE:
                {
                alt132=18;
                }
                break;
            case DAY_HOUR:
                {
                alt132=19;
                }
                break;
            case YEAR_MONTH:
                {
                alt132=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 132, 0, input);

                throw nvae;
            }

            switch (alt132) {
                case 1 :
                    // InternalSqlParser.g:6686:2: (enumLiteral_0= MICROSECOND )
                    {
                    // InternalSqlParser.g:6686:2: (enumLiteral_0= MICROSECOND )
                    // InternalSqlParser.g:6686:7: enumLiteral_0= MICROSECOND
                    {
                    enumLiteral_0=(Token)match(input,MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMsEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getEXTRACT_VALUESAccess().getMsEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6692:6: (enumLiteral_1= SECOND )
                    {
                    // InternalSqlParser.g:6692:6: (enumLiteral_1= SECOND )
                    // InternalSqlParser.g:6692:11: enumLiteral_1= SECOND
                    {
                    enumLiteral_1=(Token)match(input,SECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getEXTRACT_VALUESAccess().getSEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:6698:6: (enumLiteral_2= MINUTE )
                    {
                    // InternalSqlParser.g:6698:6: (enumLiteral_2= MINUTE )
                    // InternalSqlParser.g:6698:11: enumLiteral_2= MINUTE
                    {
                    enumLiteral_2=(Token)match(input,MINUTE,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getEXTRACT_VALUESAccess().getMEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:6704:6: (enumLiteral_3= HOUR )
                    {
                    // InternalSqlParser.g:6704:6: (enumLiteral_3= HOUR )
                    // InternalSqlParser.g:6704:11: enumLiteral_3= HOUR
                    {
                    enumLiteral_3=(Token)match(input,HOUR,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getEXTRACT_VALUESAccess().getHEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:6710:6: (enumLiteral_4= DAY )
                    {
                    // InternalSqlParser.g:6710:6: (enumLiteral_4= DAY )
                    // InternalSqlParser.g:6710:11: enumLiteral_4= DAY
                    {
                    enumLiteral_4=(Token)match(input,DAY,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getEXTRACT_VALUESAccess().getDayEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:6716:6: (enumLiteral_5= WEEK )
                    {
                    // InternalSqlParser.g:6716:6: (enumLiteral_5= WEEK )
                    // InternalSqlParser.g:6716:11: enumLiteral_5= WEEK
                    {
                    enumLiteral_5=(Token)match(input,WEEK,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getWeekEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getEXTRACT_VALUESAccess().getWeekEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:6722:6: (enumLiteral_6= MONTH )
                    {
                    // InternalSqlParser.g:6722:6: (enumLiteral_6= MONTH )
                    // InternalSqlParser.g:6722:11: enumLiteral_6= MONTH
                    {
                    enumLiteral_6=(Token)match(input,MONTH,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMonthEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getEXTRACT_VALUESAccess().getMonthEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:6728:6: (enumLiteral_7= QUARTER )
                    {
                    // InternalSqlParser.g:6728:6: (enumLiteral_7= QUARTER )
                    // InternalSqlParser.g:6728:11: enumLiteral_7= QUARTER
                    {
                    enumLiteral_7=(Token)match(input,QUARTER,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getQuartEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getEXTRACT_VALUESAccess().getQuartEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // InternalSqlParser.g:6734:6: (enumLiteral_8= YEAR )
                    {
                    // InternalSqlParser.g:6734:6: (enumLiteral_8= YEAR )
                    // InternalSqlParser.g:6734:11: enumLiteral_8= YEAR
                    {
                    enumLiteral_8=(Token)match(input,YEAR,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getYearEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getEXTRACT_VALUESAccess().getYearEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // InternalSqlParser.g:6740:6: (enumLiteral_9= SECOND_MICROSECOND )
                    {
                    // InternalSqlParser.g:6740:6: (enumLiteral_9= SECOND_MICROSECOND )
                    // InternalSqlParser.g:6740:11: enumLiteral_9= SECOND_MICROSECOND
                    {
                    enumLiteral_9=(Token)match(input,SECOND_MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMicrosEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getEXTRACT_VALUESAccess().getMicrosEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // InternalSqlParser.g:6746:6: (enumLiteral_10= MINUTE_MICROSECOND )
                    {
                    // InternalSqlParser.g:6746:6: (enumLiteral_10= MINUTE_MICROSECOND )
                    // InternalSqlParser.g:6746:11: enumLiteral_10= MINUTE_MICROSECOND
                    {
                    enumLiteral_10=(Token)match(input,MINUTE_MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMinMicroEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getEXTRACT_VALUESAccess().getMinMicroEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // InternalSqlParser.g:6752:6: (enumLiteral_11= MINUTE_SECOND )
                    {
                    // InternalSqlParser.g:6752:6: (enumLiteral_11= MINUTE_SECOND )
                    // InternalSqlParser.g:6752:11: enumLiteral_11= MINUTE_SECOND
                    {
                    enumLiteral_11=(Token)match(input,MINUTE_SECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMinSecEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_11, grammarAccess.getEXTRACT_VALUESAccess().getMinSecEnumLiteralDeclaration_11()); 
                        

                    }


                    }
                    break;
                case 13 :
                    // InternalSqlParser.g:6758:6: (enumLiteral_12= HOUR_MICROSECOND )
                    {
                    // InternalSqlParser.g:6758:6: (enumLiteral_12= HOUR_MICROSECOND )
                    // InternalSqlParser.g:6758:11: enumLiteral_12= HOUR_MICROSECOND
                    {
                    enumLiteral_12=(Token)match(input,HOUR_MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHmsEnumLiteralDeclaration_12().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_12, grammarAccess.getEXTRACT_VALUESAccess().getHmsEnumLiteralDeclaration_12()); 
                        

                    }


                    }
                    break;
                case 14 :
                    // InternalSqlParser.g:6764:6: (enumLiteral_13= HOUR_SECOND )
                    {
                    // InternalSqlParser.g:6764:6: (enumLiteral_13= HOUR_SECOND )
                    // InternalSqlParser.g:6764:11: enumLiteral_13= HOUR_SECOND
                    {
                    enumLiteral_13=(Token)match(input,HOUR_SECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHsEnumLiteralDeclaration_13().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_13, grammarAccess.getEXTRACT_VALUESAccess().getHsEnumLiteralDeclaration_13()); 
                        

                    }


                    }
                    break;
                case 15 :
                    // InternalSqlParser.g:6770:6: (enumLiteral_14= HOUR_MINUTE )
                    {
                    // InternalSqlParser.g:6770:6: (enumLiteral_14= HOUR_MINUTE )
                    // InternalSqlParser.g:6770:11: enumLiteral_14= HOUR_MINUTE
                    {
                    enumLiteral_14=(Token)match(input,HOUR_MINUTE,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHminEnumLiteralDeclaration_14().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_14, grammarAccess.getEXTRACT_VALUESAccess().getHminEnumLiteralDeclaration_14()); 
                        

                    }


                    }
                    break;
                case 16 :
                    // InternalSqlParser.g:6776:6: (enumLiteral_15= DAY_MICROSECOND )
                    {
                    // InternalSqlParser.g:6776:6: (enumLiteral_15= DAY_MICROSECOND )
                    // InternalSqlParser.g:6776:11: enumLiteral_15= DAY_MICROSECOND
                    {
                    enumLiteral_15=(Token)match(input,DAY_MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDmsEnumLiteralDeclaration_15().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_15, grammarAccess.getEXTRACT_VALUESAccess().getDmsEnumLiteralDeclaration_15()); 
                        

                    }


                    }
                    break;
                case 17 :
                    // InternalSqlParser.g:6782:6: (enumLiteral_16= DAY_SECOND )
                    {
                    // InternalSqlParser.g:6782:6: (enumLiteral_16= DAY_SECOND )
                    // InternalSqlParser.g:6782:11: enumLiteral_16= DAY_SECOND
                    {
                    enumLiteral_16=(Token)match(input,DAY_SECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDsEnumLiteralDeclaration_16().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_16, grammarAccess.getEXTRACT_VALUESAccess().getDsEnumLiteralDeclaration_16()); 
                        

                    }


                    }
                    break;
                case 18 :
                    // InternalSqlParser.g:6788:6: (enumLiteral_17= DAY_MINUTE )
                    {
                    // InternalSqlParser.g:6788:6: (enumLiteral_17= DAY_MINUTE )
                    // InternalSqlParser.g:6788:11: enumLiteral_17= DAY_MINUTE
                    {
                    enumLiteral_17=(Token)match(input,DAY_MINUTE,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayminEnumLiteralDeclaration_17().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_17, grammarAccess.getEXTRACT_VALUESAccess().getDayminEnumLiteralDeclaration_17()); 
                        

                    }


                    }
                    break;
                case 19 :
                    // InternalSqlParser.g:6794:6: (enumLiteral_18= DAY_HOUR )
                    {
                    // InternalSqlParser.g:6794:6: (enumLiteral_18= DAY_HOUR )
                    // InternalSqlParser.g:6794:11: enumLiteral_18= DAY_HOUR
                    {
                    enumLiteral_18=(Token)match(input,DAY_HOUR,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayhEnumLiteralDeclaration_18().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_18, grammarAccess.getEXTRACT_VALUESAccess().getDayhEnumLiteralDeclaration_18()); 
                        

                    }


                    }
                    break;
                case 20 :
                    // InternalSqlParser.g:6800:6: (enumLiteral_19= YEAR_MONTH )
                    {
                    // InternalSqlParser.g:6800:6: (enumLiteral_19= YEAR_MONTH )
                    // InternalSqlParser.g:6800:11: enumLiteral_19= YEAR_MONTH
                    {
                    enumLiteral_19=(Token)match(input,YEAR_MONTH,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getYearMonthEnumLiteralDeclaration_19().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_19, grammarAccess.getEXTRACT_VALUESAccess().getYearMonthEnumLiteralDeclaration_19()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
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


    // $ANTLR start "ruleXFunction"
    // InternalSqlParser.g:6810:1: ruleXFunction returns [Enumerator current=null] : ( (enumLiteral_0= IN ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= GREATER ) | (enumLiteral_6= LESS_1 ) | (enumLiteral_7= GREATER_1 ) | (enumLiteral_8= BETWEEN ) | (enumLiteral_9= BETWEEN_3 ) | (enumLiteral_10= BETWEEN_2 ) | (enumLiteral_11= BETWEEN_1 ) ) ;
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
            // InternalSqlParser.g:6812:28: ( ( (enumLiteral_0= IN ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= GREATER ) | (enumLiteral_6= LESS_1 ) | (enumLiteral_7= GREATER_1 ) | (enumLiteral_8= BETWEEN ) | (enumLiteral_9= BETWEEN_3 ) | (enumLiteral_10= BETWEEN_2 ) | (enumLiteral_11= BETWEEN_1 ) ) )
            // InternalSqlParser.g:6813:1: ( (enumLiteral_0= IN ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= GREATER ) | (enumLiteral_6= LESS_1 ) | (enumLiteral_7= GREATER_1 ) | (enumLiteral_8= BETWEEN ) | (enumLiteral_9= BETWEEN_3 ) | (enumLiteral_10= BETWEEN_2 ) | (enumLiteral_11= BETWEEN_1 ) )
            {
            // InternalSqlParser.g:6813:1: ( (enumLiteral_0= IN ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= GREATER ) | (enumLiteral_6= LESS_1 ) | (enumLiteral_7= GREATER_1 ) | (enumLiteral_8= BETWEEN ) | (enumLiteral_9= BETWEEN_3 ) | (enumLiteral_10= BETWEEN_2 ) | (enumLiteral_11= BETWEEN_1 ) )
            int alt133=12;
            switch ( input.LA(1) ) {
            case IN:
                {
                alt133=1;
                }
                break;
            case NOTIN:
                {
                alt133=2;
                }
                break;
            case EQUAL:
                {
                alt133=3;
                }
                break;
            case NOTEQUAL:
                {
                alt133=4;
                }
                break;
            case LESS:
                {
                alt133=5;
                }
                break;
            case GREATER:
                {
                alt133=6;
                }
                break;
            case LESS_1:
                {
                alt133=7;
                }
                break;
            case GREATER_1:
                {
                alt133=8;
                }
                break;
            case BETWEEN:
                {
                alt133=9;
                }
                break;
            case BETWEEN_3:
                {
                alt133=10;
                }
                break;
            case BETWEEN_2:
                {
                alt133=11;
                }
                break;
            case BETWEEN_1:
                {
                alt133=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 133, 0, input);

                throw nvae;
            }

            switch (alt133) {
                case 1 :
                    // InternalSqlParser.g:6813:2: (enumLiteral_0= IN )
                    {
                    // InternalSqlParser.g:6813:2: (enumLiteral_0= IN )
                    // InternalSqlParser.g:6813:7: enumLiteral_0= IN
                    {
                    enumLiteral_0=(Token)match(input,IN,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6819:6: (enumLiteral_1= NOTIN )
                    {
                    // InternalSqlParser.g:6819:6: (enumLiteral_1= NOTIN )
                    // InternalSqlParser.g:6819:11: enumLiteral_1= NOTIN
                    {
                    enumLiteral_1=(Token)match(input,NOTIN,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:6825:6: (enumLiteral_2= EQUAL )
                    {
                    // InternalSqlParser.g:6825:6: (enumLiteral_2= EQUAL )
                    // InternalSqlParser.g:6825:11: enumLiteral_2= EQUAL
                    {
                    enumLiteral_2=(Token)match(input,EQUAL,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:6831:6: (enumLiteral_3= NOTEQUAL )
                    {
                    // InternalSqlParser.g:6831:6: (enumLiteral_3= NOTEQUAL )
                    // InternalSqlParser.g:6831:11: enumLiteral_3= NOTEQUAL
                    {
                    enumLiteral_3=(Token)match(input,NOTEQUAL,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:6837:6: (enumLiteral_4= LESS )
                    {
                    // InternalSqlParser.g:6837:6: (enumLiteral_4= LESS )
                    // InternalSqlParser.g:6837:11: enumLiteral_4= LESS
                    {
                    enumLiteral_4=(Token)match(input,LESS,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:6843:6: (enumLiteral_5= GREATER )
                    {
                    // InternalSqlParser.g:6843:6: (enumLiteral_5= GREATER )
                    // InternalSqlParser.g:6843:11: enumLiteral_5= GREATER
                    {
                    enumLiteral_5=(Token)match(input,GREATER,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:6849:6: (enumLiteral_6= LESS_1 )
                    {
                    // InternalSqlParser.g:6849:6: (enumLiteral_6= LESS_1 )
                    // InternalSqlParser.g:6849:11: enumLiteral_6= LESS_1
                    {
                    enumLiteral_6=(Token)match(input,LESS_1,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:6855:6: (enumLiteral_7= GREATER_1 )
                    {
                    // InternalSqlParser.g:6855:6: (enumLiteral_7= GREATER_1 )
                    // InternalSqlParser.g:6855:11: enumLiteral_7= GREATER_1
                    {
                    enumLiteral_7=(Token)match(input,GREATER_1,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // InternalSqlParser.g:6861:6: (enumLiteral_8= BETWEEN )
                    {
                    // InternalSqlParser.g:6861:6: (enumLiteral_8= BETWEEN )
                    // InternalSqlParser.g:6861:11: enumLiteral_8= BETWEEN
                    {
                    enumLiteral_8=(Token)match(input,BETWEEN,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // InternalSqlParser.g:6867:6: (enumLiteral_9= BETWEEN_3 )
                    {
                    // InternalSqlParser.g:6867:6: (enumLiteral_9= BETWEEN_3 )
                    // InternalSqlParser.g:6867:11: enumLiteral_9= BETWEEN_3
                    {
                    enumLiteral_9=(Token)match(input,BETWEEN_3,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // InternalSqlParser.g:6873:6: (enumLiteral_10= BETWEEN_2 )
                    {
                    // InternalSqlParser.g:6873:6: (enumLiteral_10= BETWEEN_2 )
                    // InternalSqlParser.g:6873:11: enumLiteral_10= BETWEEN_2
                    {
                    enumLiteral_10=(Token)match(input,BETWEEN_2,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // InternalSqlParser.g:6879:6: (enumLiteral_11= BETWEEN_1 )
                    {
                    // InternalSqlParser.g:6879:6: (enumLiteral_11= BETWEEN_1 )
                    // InternalSqlParser.g:6879:11: enumLiteral_11= BETWEEN_1
                    {
                    enumLiteral_11=(Token)match(input,BETWEEN_1,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXbwnrEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_11, grammarAccess.getXFunctionAccess().getXbwnrEnumLiteralDeclaration_11()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
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

    // Delegated rules


    protected DFA66 dfa66 = new DFA66(this);
    static final String dfa_1s = "\21\uffff";
    static final String dfa_2s = "\1\154\1\u0080\1\35\14\171\2\uffff";
    static final String dfa_3s = "\1\154\1\u0080\1\161\14\u0081\2\uffff";
    static final String dfa_4s = "\17\uffff\1\1\1\2";
    static final String dfa_5s = "\21\uffff}>";
    static final String[] dfa_6s = {
            "\1\1",
            "\1\2",
            "\1\14\1\16\4\uffff\1\6\1\uffff\1\15\1\12\1\13\2\uffff\1\10\20\uffff\1\5\2\uffff\1\11\3\uffff\1\4\21\uffff\1\7\34\uffff\1\3",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "\1\17\7\uffff\1\20",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA66 extends DFA {

        public DFA66(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 66;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "2826:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000000L,0x0000010004000000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000002L,0x0200000000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0002000004000002L,0x0000000000000101L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0100000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0200020100000000L,0x0040020000000800L,0x000000000000F1F8L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0200420110000000L,0x0040020000000800L,0x000000000000F1F8L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0200020110000000L,0x0040020000000800L,0x000000000000F1F8L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x8048001200008002L,0x0000000000000400L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0224020000800000L,0x0042100000C00800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x8048001200008002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000000E040L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x8048001000008002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x8040001000008002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x8040000000008002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0040000000008002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000000L,0x0000010004000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x2400200000001002L,0x00000000000A8080L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000200L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0001000000000002L,0x0001000000000020L,0x000000000000E000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000000000L,0x0040040000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000000L,0x0040000800000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000090000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000002L,0x0000001000001000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000002L,0x0008000400000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x480004E860000000L,0x0002000000100004L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0200020000000000L,0x0040000000000800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0224128400940000L,0xE052F80000E00800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0200020000000000L,0x0040000A08000800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0200000000000000L,0x0000000000000000L,0x00000000000091D8L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001FD8L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000000002L,0x1520000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0200020100000000L,0x00C0000200000800L,0x000000000000F1F8L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x00908000816B0EC0L,0x00000020A0010002L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000001000002100L,0x0080000000000000L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000001000000102L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000040L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0200028000004020L,0x0040000000000800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0200028000004030L,0x0040000000000800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x000000000A000000L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000000002L,0x0000001000001008L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x1000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0200020100000000L,0x0040000200000800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000000000L,0x00C0000000000000L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000000000000L,0x0280000000000000L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0224020000800000L,0x0042100040C00800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0224020000800002L,0x0042100040C00800L,0x000000000000F1D8L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x2400000000001000L,0x00000000000A8080L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020010L});

}