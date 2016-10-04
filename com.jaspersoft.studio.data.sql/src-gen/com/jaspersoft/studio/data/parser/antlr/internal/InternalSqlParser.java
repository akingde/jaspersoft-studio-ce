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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "UNBOUNDEDFOLLOWING", "UNBOUNDEDPRECEDING", "MINUTE_MICROSECOND", "SECOND_MICROSECOND", "ORDERSIBLINGSBY", "HOUR_MICROSECOND", "DAY_MICROSECOND", "MINUTE_SECOND", "STRAIGHT_JOIN", "PARTITIONBY", "CURRENTROW", "FETCHFIRST", "HOUR_MINUTE", "HOUR_SECOND", "ISNOTNULL", "MICROSECOND", "NOTBETWEEN", "DAY_MINUTE", "DAY_SECOND", "NOTEXISTS", "YEAR_MONTH", "KW_FOLLOWING", "INTERSECT", "PRECEDING", "WITHTIES", "BETWEEN_3", "BETWEEN_1", "DAY_HOUR", "DISTINCT", "GROUPBY", "NOTLIKE", "NOTEQUAL", "ORDERBY", "BETWEEN_2", "GREATER_1", "BETWEEN", "EXCLUDE", "EXTRACT", "GREATER", "INCLUDE", "ISNULL", "NATURAL", "PERCENT", "QUARTER", "UNPIVOT", "EXCEPT", "EXISTS", "HAVING", "MINUTE", "NOTIN_1", "OFFSET", "SECOND", "SELECT", "VALUES", "CAST", "CROSS", "EQUAL", "FIRST", "INNER", "LESS_1", "LIMIT", "MINUS", "MONTH", "NOTIN", "NULLS", "OUTER", "PIVOT", "RANGE", "RIGHT", "UNION", "USING", "WHERE", "CASE", "DESC", "ELSE", "FROM", "FULL", "HOUR", "JOIN", "LAST", "LEFT", "LESS", "LIKE", "NOT", "NOT_1", "NULL", "ONLY", "OVER", "ROWS", "SOME", "THEN", "WEEK", "WHEN", "YEAR", "LeftParenthesisPlusSignRightParenthesis", "ALL", "AND", "ANY", "ASC", "DAY", "END", "FOR", "ROW", "TOP", "XML", "ExclamationMarkEqualsSign", "X", "LessThanSignEqualsSign", "LessThanSignGreaterThanSign", "GreaterThanSignEqualsSign", "AS", "IN", "ON", "OR", "CircumflexAccentEqualsSign", "VerticalLineVerticalLine", "LeftParenthesis", "RightParenthesis", "PlusSign", "Comma", "HyphenMinus", "FullStop", "Solidus", "LessThanSign", "EqualsSign", "GreaterThanSign", "LeftCurlyBracket", "VerticalLine", "RightCurlyBracket", "RULE_JRPARAM", "RULE_JRNPARAM", "RULE_STAR", "RULE_UNSIGNED", "RULE_INT", "RULE_SIGNED_DOUBLE", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_STRING_", "RULE_STRING", "RULE_DBNAME", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int CAST=58;
    public static final int PIVOT=70;
    public static final int WEEK=95;
    public static final int RULE_ID=145;
    public static final int RULE_JRNPARAM=134;
    public static final int RULE_DATE=139;
    public static final int RightParenthesis=121;
    public static final int ROW=106;
    public static final int RULE_ANY_OTHER=149;
    public static final int CASE=76;
    public static final int LeftParenthesis=120;
    public static final int DAY=103;
    public static final int NOT=87;
    public static final int Solidus=126;
    public static final int EXCEPT=49;
    public static final int EOF=-1;
    public static final int MONTH=66;
    public static final int FullStop=125;
    public static final int NOTEQUAL=35;
    public static final int NOTLIKE=34;
    public static final int FULL=80;
    public static final int GREATER=42;
    public static final int QUARTER=47;
    public static final int USING=74;
    public static final int CircumflexAccentEqualsSign=118;
    public static final int PRECEDING=27;
    public static final int INCLUDE=43;
    public static final int LessThanSign=127;
    public static final int LESS=85;
    public static final int HOUR_MICROSECOND=9;
    public static final int RULE_SIGNED_DOUBLE=138;
    public static final int NOT_1=88;
    public static final int LAST=83;
    public static final int NOTIN_1=53;
    public static final int SELECT=56;
    public static final int GROUPBY=33;
    public static final int SECOND=55;
    public static final int DAY_MICROSECOND=10;
    public static final int ASC=102;
    public static final int NULL=89;
    public static final int ELSE=78;
    public static final int ON=116;
    public static final int LessThanSignEqualsSign=111;
    public static final int DAY_MINUTE=21;
    public static final int LeftCurlyBracket=130;
    public static final int CURRENTROW=14;
    public static final int HOUR_SECOND=17;
    public static final int STRAIGHT_JOIN=12;
    public static final int X=110;
    public static final int RULE_ML_COMMENT=146;
    public static final int INTERSECT=26;
    public static final int RULE_STRING=143;
    public static final int ORDERSIBLINGSBY=8;
    public static final int VerticalLine=131;
    public static final int OR=117;
    public static final int END=104;
    public static final int FROM=79;
    public static final int DISTINCT=32;
    public static final int XML=108;
    public static final int BETWEEN_3=29;
    public static final int BETWEEN_2=37;
    public static final int BETWEEN_1=30;
    public static final int RightCurlyBracket=132;
    public static final int NOTIN=67;
    public static final int OVER=91;
    public static final int WHERE=75;
    public static final int VerticalLineVerticalLine=119;
    public static final int HyphenMinus=124;
    public static final int INNER=62;
    public static final int YEAR=97;
    public static final int RULE_UNSIGNED=136;
    public static final int MICROSECOND=19;
    public static final int LIMIT=64;
    public static final int ONLY=90;
    public static final int UNPIVOT=48;
    public static final int ISNULL=44;
    public static final int FOR=105;
    public static final int ORDERBY=36;
    public static final int RULE_STRING_=142;
    public static final int LessThanSignGreaterThanSign=112;
    public static final int AND=100;
    public static final int NOTEXISTS=23;
    public static final int GreaterThanSign=129;
    public static final int CROSS=59;
    public static final int SECOND_MICROSECOND=7;
    public static final int YEAR_MONTH=24;
    public static final int LESS_1=63;
    public static final int AS=114;
    public static final int DAY_HOUR=31;
    public static final int THEN=94;
    public static final int IN=115;
    public static final int FETCHFIRST=15;
    public static final int OFFSET=54;
    public static final int LEFT=84;
    public static final int SOME=93;
    public static final int EQUAL=60;
    public static final int ALL=99;
    public static final int RULE_TIME=140;
    public static final int RULE_INT=137;
    public static final int RULE_TIMESTAMP=141;
    public static final int RULE_DBNAME=144;
    public static final int EXISTS=50;
    public static final int MINUTE_SECOND=11;
    public static final int EXTRACT=41;
    public static final int WITHTIES=28;
    public static final int LIKE=86;
    public static final int EXCLUDE=40;
    public static final int ExclamationMarkEqualsSign=109;
    public static final int OUTER=69;
    public static final int PARTITIONBY=13;
    public static final int PERCENT=46;
    public static final int UNBOUNDEDFOLLOWING=4;
    public static final int KW_FOLLOWING=25;
    public static final int GREATER_1=38;
    public static final int MINUTE_MICROSECOND=6;
    public static final int VALUES=57;
    public static final int RANGE=71;
    public static final int RIGHT=72;
    public static final int HAVING=51;
    public static final int MINUS=65;
    public static final int HOUR=81;
    public static final int RULE_SL_COMMENT=147;
    public static final int JOIN=82;
    public static final int UNION=73;
    public static final int NOTBETWEEN=20;
    public static final int NULLS=68;
    public static final int ANY=101;
    public static final int PlusSign=122;
    public static final int ISNOTNULL=18;
    public static final int UNBOUNDEDPRECEDING=5;
    public static final int DAY_SECOND=22;
    public static final int RULE_STAR=135;
    public static final int WHEN=96;
    public static final int RULE_JRPARAM=133;
    public static final int HOUR_MINUTE=16;
    public static final int ROWS=92;
    public static final int GreaterThanSignEqualsSign=113;
    public static final int NATURAL=45;
    public static final int DESC=77;
    public static final int LeftParenthesisPlusSignRightParenthesis=98;
    public static final int MINUTE=52;
    public static final int RULE_WS=148;
    public static final int TOP=107;
    public static final int Comma=123;
    public static final int EqualsSign=128;
    public static final int BETWEEN=39;
    public static final int FIRST=61;

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
    // InternalSqlParser.g:114:1: ruleFetchFirst returns [EObject current=null] : ( ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY ) ;
    public final EObject ruleFetchFirst() throws RecognitionException {
        EObject current = null;

        Token lv_row_1_1=null;
        Token lv_row_1_2=null;
        Token otherlv_2=null;
        EObject lv_fetchFirst_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:117:28: ( ( ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY ) )
            // InternalSqlParser.g:118:1: ( ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY )
            {
            // InternalSqlParser.g:118:1: ( ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY )
            // InternalSqlParser.g:118:2: ( (lv_fetchFirst_0_0= ruleUnsignedValue ) ) ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) ) otherlv_2= ONLY
            {
            // InternalSqlParser.g:118:2: ( (lv_fetchFirst_0_0= ruleUnsignedValue ) )
            // InternalSqlParser.g:119:1: (lv_fetchFirst_0_0= ruleUnsignedValue )
            {
            // InternalSqlParser.g:119:1: (lv_fetchFirst_0_0= ruleUnsignedValue )
            // InternalSqlParser.g:120:3: lv_fetchFirst_0_0= ruleUnsignedValue
            {
             
            	        newCompositeNode(grammarAccess.getFetchFirstAccess().getFetchFirstUnsignedValueParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_4);
            lv_fetchFirst_0_0=ruleUnsignedValue();

            state._fsp--;


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

            // InternalSqlParser.g:136:2: ( ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) ) )
            // InternalSqlParser.g:137:1: ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) )
            {
            // InternalSqlParser.g:137:1: ( (lv_row_1_1= ROW | lv_row_1_2= ROWS ) )
            // InternalSqlParser.g:138:1: (lv_row_1_1= ROW | lv_row_1_2= ROWS )
            {
            // InternalSqlParser.g:138:1: (lv_row_1_1= ROW | lv_row_1_2= ROWS )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==ROW) ) {
                alt2=1;
            }
            else if ( (LA2_0==ROWS) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
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
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ALL) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_UNSIGNED) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
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
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==Comma) ) {
                        alt3=1;
                    }
                    switch (alt3) {
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
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==INTERSECT||LA5_0==EXCEPT||LA5_0==MINUS||LA5_0==UNION) ) {
                    alt5=1;
                }


                switch (alt5) {
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
            	    break loop5;
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
            int alt6=4;
            switch ( input.LA(1) ) {
            case UNION:
                {
                alt6=1;
                }
                break;
            case INTERSECT:
                {
                alt6=2;
                }
                break;
            case MINUS:
                {
                alt6=3;
                }
                break;
            case EXCEPT:
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
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
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==ALL) ) {
                alt7=1;
            }
            switch (alt7) {
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
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==DISTINCT) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalSqlParser.g:485:2: otherlv_1= DISTINCT
                    {
                    otherlv_1=(Token)match(input,DISTINCT,FOLLOW_10); 

                        	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                        

                    }
                    break;

            }

            // InternalSqlParser.g:489:3: (otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )? )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==TOP) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalSqlParser.g:490:2: otherlv_2= TOP (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= PERCENT )? (otherlv_6= WITHTIES )?
                    {
                    otherlv_2=(Token)match(input,TOP,FOLLOW_11); 

                        	newLeafNode(otherlv_2, grammarAccess.getSelectAccess().getTOPKeyword_2_0());
                        
                    // InternalSqlParser.g:494:1: (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE )
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==RULE_INT) ) {
                        alt9=1;
                    }
                    else if ( (LA9_0==RULE_SIGNED_DOUBLE) ) {
                        alt9=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 0, input);

                        throw nvae;
                    }
                    switch (alt9) {
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
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==PERCENT) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // InternalSqlParser.g:504:2: otherlv_5= PERCENT
                            {
                            otherlv_5=(Token)match(input,PERCENT,FOLLOW_13); 

                                	newLeafNode(otherlv_5, grammarAccess.getSelectAccess().getPERCENTKeyword_2_2());
                                

                            }
                            break;

                    }

                    // InternalSqlParser.g:508:3: (otherlv_6= WITHTIES )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==WITHTIES) ) {
                        alt11=1;
                    }
                    switch (alt11) {
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
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==WHERE) ) {
                alt13=1;
            }
            switch (alt13) {
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
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==GROUPBY) ) {
                alt14=1;
            }
            switch (alt14) {
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
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==HAVING) ) {
                alt15=1;
            }
            switch (alt15) {
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
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ORDERBY) ) {
                alt16=1;
            }
            switch (alt16) {
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
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==LIMIT) ) {
                alt17=1;
            }
            switch (alt17) {
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
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==OFFSET) ) {
                alt18=1;
            }
            switch (alt18) {
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
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==FETCHFIRST) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalSqlParser.g:693:2: otherlv_22= FETCHFIRST ( (lv_fetchFirst_23_0= ruleFetchFirst ) )
                    {
                    otherlv_22=(Token)match(input,FETCHFIRST,FOLLOW_7); 

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
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==Comma) ) {
                alt21=1;
            }
            switch (alt21) {
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
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==Comma) ) {
                            alt20=1;
                        }


                        switch (alt20) {
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
                    	    if ( cnt20 >= 1 ) break loop20;
                                EarlyExitException eee =
                                    new EarlyExitException(20, input);
                                throw eee;
                        }
                        cnt20++;
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
            int alt24=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==EOF||LA24_1==FROM||LA24_1==LeftParenthesisPlusSignRightParenthesis||LA24_1==AS||(LA24_1>=VerticalLineVerticalLine && LA24_1<=HyphenMinus)||LA24_1==Solidus||LA24_1==RULE_STAR||(LA24_1>=RULE_STRING && LA24_1<=RULE_ID)) ) {
                    alt24=1;
                }
                else if ( (LA24_1==FullStop) ) {
                    int LA24_6 = input.LA(3);

                    if ( (LA24_6==RULE_STAR) ) {
                        alt24=3;
                    }
                    else if ( ((LA24_6>=RULE_STRING && LA24_6<=RULE_ID)) ) {
                        alt24=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 6, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_DBNAME:
                {
                int LA24_2 = input.LA(2);

                if ( (LA24_2==FullStop) ) {
                    int LA24_6 = input.LA(3);

                    if ( (LA24_6==RULE_STAR) ) {
                        alt24=3;
                    }
                    else if ( ((LA24_6>=RULE_STRING && LA24_6<=RULE_ID)) ) {
                        alt24=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA24_2==EOF||LA24_2==FROM||LA24_2==LeftParenthesisPlusSignRightParenthesis||LA24_2==AS||LA24_2==VerticalLineVerticalLine||(LA24_2>=RightParenthesis && LA24_2<=HyphenMinus)||LA24_2==Solidus||LA24_2==RULE_STAR||(LA24_2>=RULE_STRING && LA24_2<=RULE_ID)) ) {
                    alt24=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
                {
                int LA24_3 = input.LA(2);

                if ( (LA24_3==FullStop) ) {
                    int LA24_6 = input.LA(3);

                    if ( (LA24_6==RULE_STAR) ) {
                        alt24=3;
                    }
                    else if ( ((LA24_6>=RULE_STRING && LA24_6<=RULE_ID)) ) {
                        alt24=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA24_3==EOF||LA24_3==FROM||LA24_3==LeftParenthesisPlusSignRightParenthesis||LA24_3==AS||LA24_3==VerticalLineVerticalLine||(LA24_3>=RightParenthesis && LA24_3<=HyphenMinus)||LA24_3==Solidus||LA24_3==RULE_STAR||(LA24_3>=RULE_STRING && LA24_3<=RULE_ID)) ) {
                    alt24=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 3, input);

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
                alt24=1;
                }
                break;
            case RULE_STAR:
                {
                alt24=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
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
                    	    
                    pushFollow(FOLLOW_27);
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
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==AS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // InternalSqlParser.g:814:1: (lv_alias_1_0= AS )
                            {
                            // InternalSqlParser.g:814:1: (lv_alias_1_0= AS )
                            // InternalSqlParser.g:815:3: lv_alias_1_0= AS
                            {
                            lv_alias_1_0=(Token)match(input,AS,FOLLOW_28); 

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
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( ((LA23_0>=RULE_STRING && LA23_0<=RULE_ID)) ) {
                        alt23=1;
                    }
                    switch (alt23) {
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
                
            pushFollow(FOLLOW_29);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:915:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==FullStop) ) {
                alt26=1;
            }
            switch (alt26) {
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
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==FullStop) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // InternalSqlParser.g:922:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_30); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:926:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:927:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:927:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:928:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_29);
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
                    	    if ( cnt25 >= 1 ) break loop25;
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
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
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==Comma) ) {
                alt28=1;
            }
            switch (alt28) {
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
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==Comma) ) {
                            alt27=1;
                        }


                        switch (alt27) {
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
                    	    if ( cnt27 >= 1 ) break loop27;
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
            	    
            pushFollow(FOLLOW_31);
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
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==STRAIGHT_JOIN||LA29_0==NATURAL||LA29_0==CROSS||LA29_0==INNER||LA29_0==RIGHT||LA29_0==FULL||LA29_0==JOIN||LA29_0==LEFT) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalSqlParser.g:1043:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    {
            	    // InternalSqlParser.g:1043:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    // InternalSqlParser.g:1044:3: lv_fjoin_1_0= ruleFromTableJoin
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFromTableAccess().getFjoinFromTableJoinParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_31);
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
            	    break loop29;
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
            	    
            pushFollow(FOLLOW_32);
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
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==ON) ) {
                alt30=1;
            }
            else if ( (LA30_0==USING) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
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
            otherlv_0=(Token)match(input,USING,FOLLOW_33); 

                	newLeafNode(otherlv_0, grammarAccess.getJoinConditionAccess().getUSINGKeyword_0());
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_30); 

                	newLeafNode(otherlv_1, grammarAccess.getJoinConditionAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:1190:1: ( (lv_useCols_2_0= ruleUsingCols ) )
            // InternalSqlParser.g:1191:1: (lv_useCols_2_0= ruleUsingCols )
            {
            // InternalSqlParser.g:1191:1: (lv_useCols_2_0= ruleUsingCols )
            // InternalSqlParser.g:1192:3: lv_useCols_2_0= ruleUsingCols
            {
             
            	        newCompositeNode(grammarAccess.getJoinConditionAccess().getUseColsUsingColsParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_34);
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
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==Comma) ) {
                alt32=1;
            }
            switch (alt32) {
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
                    int cnt31=0;
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==Comma) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // InternalSqlParser.g:1250:2: otherlv_2= Comma ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_30); 

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
                    	    if ( cnt31 >= 1 ) break loop31;
                                EarlyExitException eee =
                                    new EarlyExitException(31, input);
                                throw eee;
                        }
                        cnt31++;
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
    // InternalSqlParser.g:1289:1: ruleTableOrAlias returns [EObject current=null] : ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )? ) ;
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
            // InternalSqlParser.g:1292:28: ( ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )? ) )
            // InternalSqlParser.g:1293:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )? )
            {
            // InternalSqlParser.g:1293:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )? )
            // InternalSqlParser.g:1293:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) ) ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )? ( (lv_alias_5_0= AS ) )? ( (lv_tblAlias_6_0= ruleDbObjectName ) )?
            {
            // InternalSqlParser.g:1293:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) | ( (lv_values_2_0= ruleFromValues ) ) )
            int alt33=3;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=RULE_STRING && LA33_0<=RULE_ID)) ) {
                alt33=1;
            }
            else if ( (LA33_0==LeftParenthesis) ) {
                int LA33_2 = input.LA(2);

                if ( (LA33_2==SELECT) ) {
                    alt33=2;
                }
                else if ( (LA33_2==VALUES) ) {
                    alt33=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
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
                    	    
                    pushFollow(FOLLOW_35);
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
                    	    
                    pushFollow(FOLLOW_35);
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
                case 3 :
                    // InternalSqlParser.g:1331:6: ( (lv_values_2_0= ruleFromValues ) )
                    {
                    // InternalSqlParser.g:1331:6: ( (lv_values_2_0= ruleFromValues ) )
                    // InternalSqlParser.g:1332:1: (lv_values_2_0= ruleFromValues )
                    {
                    // InternalSqlParser.g:1332:1: (lv_values_2_0= ruleFromValues )
                    // InternalSqlParser.g:1333:3: lv_values_2_0= ruleFromValues
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getValuesFromValuesParserRuleCall_0_2_0()); 
                    	    
                    pushFollow(FOLLOW_35);
                    lv_values_2_0=ruleFromValues();

                    state._fsp--;


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
                    break;

            }

            // InternalSqlParser.g:1349:3: ( ( (lv_pivot_3_0= rulePivotTable ) ) | ( (lv_unpivot_4_0= ruleUnpivotTable ) ) )?
            int alt34=3;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==PIVOT) ) {
                alt34=1;
            }
            else if ( (LA34_0==UNPIVOT) ) {
                alt34=2;
            }
            switch (alt34) {
                case 1 :
                    // InternalSqlParser.g:1349:4: ( (lv_pivot_3_0= rulePivotTable ) )
                    {
                    // InternalSqlParser.g:1349:4: ( (lv_pivot_3_0= rulePivotTable ) )
                    // InternalSqlParser.g:1350:1: (lv_pivot_3_0= rulePivotTable )
                    {
                    // InternalSqlParser.g:1350:1: (lv_pivot_3_0= rulePivotTable )
                    // InternalSqlParser.g:1351:3: lv_pivot_3_0= rulePivotTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getPivotPivotTableParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_27);
                    lv_pivot_3_0=rulePivotTable();

                    state._fsp--;


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
                    break;
                case 2 :
                    // InternalSqlParser.g:1368:6: ( (lv_unpivot_4_0= ruleUnpivotTable ) )
                    {
                    // InternalSqlParser.g:1368:6: ( (lv_unpivot_4_0= ruleUnpivotTable ) )
                    // InternalSqlParser.g:1369:1: (lv_unpivot_4_0= ruleUnpivotTable )
                    {
                    // InternalSqlParser.g:1369:1: (lv_unpivot_4_0= ruleUnpivotTable )
                    // InternalSqlParser.g:1370:3: lv_unpivot_4_0= ruleUnpivotTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getUnpivotUnpivotTableParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_27);
                    lv_unpivot_4_0=ruleUnpivotTable();

                    state._fsp--;


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
                    break;

            }

            // InternalSqlParser.g:1386:4: ( (lv_alias_5_0= AS ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==AS) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalSqlParser.g:1387:1: (lv_alias_5_0= AS )
                    {
                    // InternalSqlParser.g:1387:1: (lv_alias_5_0= AS )
                    // InternalSqlParser.g:1388:3: lv_alias_5_0= AS
                    {
                    lv_alias_5_0=(Token)match(input,AS,FOLLOW_28); 

                            newLeafNode(lv_alias_5_0, grammarAccess.getTableOrAliasAccess().getAliasASKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTableOrAliasRule());
                    	        }
                           		setWithLastConsumed(current, "alias", lv_alias_5_0, "AS");
                    	    

                    }


                    }
                    break;

            }

            // InternalSqlParser.g:1402:3: ( (lv_tblAlias_6_0= ruleDbObjectName ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=RULE_STRING && LA36_0<=RULE_ID)) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalSqlParser.g:1403:1: (lv_tblAlias_6_0= ruleDbObjectName )
                    {
                    // InternalSqlParser.g:1403:1: (lv_tblAlias_6_0= ruleDbObjectName )
                    // InternalSqlParser.g:1404:3: lv_tblAlias_6_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_tblAlias_6_0=ruleDbObjectName();

                    state._fsp--;


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


    // $ANTLR start "entryRuleFromValues"
    // InternalSqlParser.g:1428:1: entryRuleFromValues returns [EObject current=null] : iv_ruleFromValues= ruleFromValues EOF ;
    public final EObject entryRuleFromValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromValues = null;


        try {
            // InternalSqlParser.g:1429:2: (iv_ruleFromValues= ruleFromValues EOF )
            // InternalSqlParser.g:1430:2: iv_ruleFromValues= ruleFromValues EOF
            {
             newCompositeNode(grammarAccess.getFromValuesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFromValues=ruleFromValues();

            state._fsp--;

             current =iv_ruleFromValues; 
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
    // $ANTLR end "entryRuleFromValues"


    // $ANTLR start "ruleFromValues"
    // InternalSqlParser.g:1437:1: ruleFromValues returns [EObject current=null] : ( ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )? ) ;
    public final EObject ruleFromValues() throws RecognitionException {
        EObject current = null;

        EObject lv_values_0_0 = null;

        EObject lv_c_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1440:28: ( ( ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )? ) )
            // InternalSqlParser.g:1441:1: ( ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )? )
            {
            // InternalSqlParser.g:1441:1: ( ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )? )
            // InternalSqlParser.g:1441:2: ( (lv_values_0_0= ruleValues ) ) ( (lv_c_1_0= ruleFromValuesColumns ) )?
            {
            // InternalSqlParser.g:1441:2: ( (lv_values_0_0= ruleValues ) )
            // InternalSqlParser.g:1442:1: (lv_values_0_0= ruleValues )
            {
            // InternalSqlParser.g:1442:1: (lv_values_0_0= ruleValues )
            // InternalSqlParser.g:1443:3: lv_values_0_0= ruleValues
            {
             
            	        newCompositeNode(grammarAccess.getFromValuesAccess().getValuesValuesParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_36);
            lv_values_0_0=ruleValues();

            state._fsp--;


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

            // InternalSqlParser.g:1459:2: ( (lv_c_1_0= ruleFromValuesColumns ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==RULE_ID) ) {
                int LA37_1 = input.LA(2);

                if ( (LA37_1==LeftParenthesis) ) {
                    alt37=1;
                }
            }
            switch (alt37) {
                case 1 :
                    // InternalSqlParser.g:1460:1: (lv_c_1_0= ruleFromValuesColumns )
                    {
                    // InternalSqlParser.g:1460:1: (lv_c_1_0= ruleFromValuesColumns )
                    // InternalSqlParser.g:1461:3: lv_c_1_0= ruleFromValuesColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getFromValuesAccess().getCFromValuesColumnsParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_c_1_0=ruleFromValuesColumns();

                    state._fsp--;


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
    // $ANTLR end "ruleFromValues"


    // $ANTLR start "entryRuleFromValuesColumns"
    // InternalSqlParser.g:1485:1: entryRuleFromValuesColumns returns [EObject current=null] : iv_ruleFromValuesColumns= ruleFromValuesColumns EOF ;
    public final EObject entryRuleFromValuesColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromValuesColumns = null;


        try {
            // InternalSqlParser.g:1486:2: (iv_ruleFromValuesColumns= ruleFromValuesColumns EOF )
            // InternalSqlParser.g:1487:2: iv_ruleFromValuesColumns= ruleFromValuesColumns EOF
            {
             newCompositeNode(grammarAccess.getFromValuesColumnsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFromValuesColumns=ruleFromValuesColumns();

            state._fsp--;

             current =iv_ruleFromValuesColumns; 
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
    // $ANTLR end "entryRuleFromValuesColumns"


    // $ANTLR start "ruleFromValuesColumns"
    // InternalSqlParser.g:1494:1: ruleFromValuesColumns returns [EObject current=null] : (this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleFromValuesColumns() throws RecognitionException {
        EObject current = null;

        Token this_ID_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_fvCols_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1497:28: ( (this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:1498:1: (this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:1498:1: (this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:1498:2: this_ID_0= RULE_ID otherlv_1= LeftParenthesis ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) ) otherlv_3= RightParenthesis
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_33); 
             
                newLeafNode(this_ID_0, grammarAccess.getFromValuesColumnsAccess().getIDTerminalRuleCall_0()); 
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_37); 

                	newLeafNode(otherlv_1, grammarAccess.getFromValuesColumnsAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:1507:1: ( (lv_fvCols_2_0= ruleFromValuesColumnNames ) )
            // InternalSqlParser.g:1508:1: (lv_fvCols_2_0= ruleFromValuesColumnNames )
            {
            // InternalSqlParser.g:1508:1: (lv_fvCols_2_0= ruleFromValuesColumnNames )
            // InternalSqlParser.g:1509:3: lv_fvCols_2_0= ruleFromValuesColumnNames
            {
             
            	        newCompositeNode(grammarAccess.getFromValuesColumnsAccess().getFvColsFromValuesColumnNamesParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_34);
            lv_fvCols_2_0=ruleFromValuesColumnNames();

            state._fsp--;


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

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_3, grammarAccess.getFromValuesColumnsAccess().getRightParenthesisKeyword_3());
                

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
    // $ANTLR end "ruleFromValuesColumns"


    // $ANTLR start "entryRuleFromValuesColumnNames"
    // InternalSqlParser.g:1538:1: entryRuleFromValuesColumnNames returns [EObject current=null] : iv_ruleFromValuesColumnNames= ruleFromValuesColumnNames EOF ;
    public final EObject entryRuleFromValuesColumnNames() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromValuesColumnNames = null;


        try {
            // InternalSqlParser.g:1539:2: (iv_ruleFromValuesColumnNames= ruleFromValuesColumnNames EOF )
            // InternalSqlParser.g:1540:2: iv_ruleFromValuesColumnNames= ruleFromValuesColumnNames EOF
            {
             newCompositeNode(grammarAccess.getFromValuesColumnNamesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFromValuesColumnNames=ruleFromValuesColumnNames();

            state._fsp--;

             current =iv_ruleFromValuesColumnNames; 
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
    // $ANTLR end "entryRuleFromValuesColumnNames"


    // $ANTLR start "ruleFromValuesColumnNames"
    // InternalSqlParser.g:1547:1: ruleFromValuesColumnNames returns [EObject current=null] : (this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )? ) ;
    public final EObject ruleFromValuesColumnNames() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1550:28: ( (this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )? ) )
            // InternalSqlParser.g:1551:1: (this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )? )
            {
            // InternalSqlParser.g:1551:1: (this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )? )
            // InternalSqlParser.g:1552:5: this_ColumnName_0= ruleColumnName ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getFromValuesColumnNamesAccess().getColumnNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_ColumnName_0=ruleColumnName();

            state._fsp--;


                    current = this_ColumnName_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:1560:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+ )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==Comma) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalSqlParser.g:1560:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+
                    {
                    // InternalSqlParser.g:1560:2: ()
                    // InternalSqlParser.g:1561:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getFromValuesColumnNamesAccess().getAbcEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:1566:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) ) )+
                    int cnt38=0;
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==Comma) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // InternalSqlParser.g:1567:2: otherlv_2= Comma ( (lv_entries_3_0= ruleColumnName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_37); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getFromValuesColumnNamesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:1571:1: ( (lv_entries_3_0= ruleColumnName ) )
                    	    // InternalSqlParser.g:1572:1: (lv_entries_3_0= ruleColumnName )
                    	    {
                    	    // InternalSqlParser.g:1572:1: (lv_entries_3_0= ruleColumnName )
                    	    // InternalSqlParser.g:1573:3: lv_entries_3_0= ruleColumnName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFromValuesColumnNamesAccess().getEntriesColumnNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleColumnName();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt38 >= 1 ) break loop38;
                                EarlyExitException eee =
                                    new EarlyExitException(38, input);
                                throw eee;
                        }
                        cnt38++;
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
    // $ANTLR end "ruleFromValuesColumnNames"


    // $ANTLR start "entryRuleColumnName"
    // InternalSqlParser.g:1597:1: entryRuleColumnName returns [EObject current=null] : iv_ruleColumnName= ruleColumnName EOF ;
    public final EObject entryRuleColumnName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnName = null;


        try {
            // InternalSqlParser.g:1598:2: (iv_ruleColumnName= ruleColumnName EOF )
            // InternalSqlParser.g:1599:2: iv_ruleColumnName= ruleColumnName EOF
            {
             newCompositeNode(grammarAccess.getColumnNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleColumnName=ruleColumnName();

            state._fsp--;

             current =iv_ruleColumnName; 
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
    // $ANTLR end "entryRuleColumnName"


    // $ANTLR start "ruleColumnName"
    // InternalSqlParser.g:1606:1: ruleColumnName returns [EObject current=null] : ( (lv_colName_0_0= RULE_STRING ) ) ;
    public final EObject ruleColumnName() throws RecognitionException {
        EObject current = null;

        Token lv_colName_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:1609:28: ( ( (lv_colName_0_0= RULE_STRING ) ) )
            // InternalSqlParser.g:1610:1: ( (lv_colName_0_0= RULE_STRING ) )
            {
            // InternalSqlParser.g:1610:1: ( (lv_colName_0_0= RULE_STRING ) )
            // InternalSqlParser.g:1611:1: (lv_colName_0_0= RULE_STRING )
            {
            // InternalSqlParser.g:1611:1: (lv_colName_0_0= RULE_STRING )
            // InternalSqlParser.g:1612:3: lv_colName_0_0= RULE_STRING
            {
            lv_colName_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            			newLeafNode(lv_colName_0_0, grammarAccess.getColumnNameAccess().getColNameSTRINGTerminalRuleCall_0()); 
            		

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
    // $ANTLR end "ruleColumnName"


    // $ANTLR start "entryRuleValues"
    // InternalSqlParser.g:1636:1: entryRuleValues returns [EObject current=null] : iv_ruleValues= ruleValues EOF ;
    public final EObject entryRuleValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValues = null;


        try {
            // InternalSqlParser.g:1637:2: (iv_ruleValues= ruleValues EOF )
            // InternalSqlParser.g:1638:2: iv_ruleValues= ruleValues EOF
            {
             newCompositeNode(grammarAccess.getValuesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValues=ruleValues();

            state._fsp--;

             current =iv_ruleValues; 
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
    // $ANTLR end "entryRuleValues"


    // $ANTLR start "ruleValues"
    // InternalSqlParser.g:1645:1: ruleValues returns [EObject current=null] : (otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleValues() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_rows_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1648:28: ( (otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:1649:1: (otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:1649:1: (otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:1650:2: otherlv_0= LeftParenthesis otherlv_1= VALUES ( (lv_rows_2_0= ruleRows ) ) otherlv_3= RightParenthesis
            {
            otherlv_0=(Token)match(input,LeftParenthesis,FOLLOW_38); 

                	newLeafNode(otherlv_0, grammarAccess.getValuesAccess().getLeftParenthesisKeyword_0());
                
            otherlv_1=(Token)match(input,VALUES,FOLLOW_33); 

                	newLeafNode(otherlv_1, grammarAccess.getValuesAccess().getVALUESKeyword_1());
                
            // InternalSqlParser.g:1659:1: ( (lv_rows_2_0= ruleRows ) )
            // InternalSqlParser.g:1660:1: (lv_rows_2_0= ruleRows )
            {
            // InternalSqlParser.g:1660:1: (lv_rows_2_0= ruleRows )
            // InternalSqlParser.g:1661:3: lv_rows_2_0= ruleRows
            {
             
            	        newCompositeNode(grammarAccess.getValuesAccess().getRowsRowsParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_34);
            lv_rows_2_0=ruleRows();

            state._fsp--;


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

            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_3, grammarAccess.getValuesAccess().getRightParenthesisKeyword_3());
                

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
    // $ANTLR end "ruleValues"


    // $ANTLR start "entryRuleRows"
    // InternalSqlParser.g:1690:1: entryRuleRows returns [EObject current=null] : iv_ruleRows= ruleRows EOF ;
    public final EObject entryRuleRows() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRows = null;


        try {
            // InternalSqlParser.g:1691:2: (iv_ruleRows= ruleRows EOF )
            // InternalSqlParser.g:1692:2: iv_ruleRows= ruleRows EOF
            {
             newCompositeNode(grammarAccess.getRowsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRows=ruleRows();

            state._fsp--;

             current =iv_ruleRows; 
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
    // $ANTLR end "entryRuleRows"


    // $ANTLR start "ruleRows"
    // InternalSqlParser.g:1699:1: ruleRows returns [EObject current=null] : (this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )? ) ;
    public final EObject ruleRows() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Row_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1702:28: ( (this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )? ) )
            // InternalSqlParser.g:1703:1: (this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )? )
            {
            // InternalSqlParser.g:1703:1: (this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )? )
            // InternalSqlParser.g:1704:5: this_Row_0= ruleRow ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getRowsAccess().getRowParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_Row_0=ruleRow();

            state._fsp--;


                    current = this_Row_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:1712:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+ )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==Comma) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalSqlParser.g:1712:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+
                    {
                    // InternalSqlParser.g:1712:2: ()
                    // InternalSqlParser.g:1713:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getRowsAccess().getRowsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:1718:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) ) )+
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
                    	    // InternalSqlParser.g:1719:2: otherlv_2= Comma ( (lv_entries_3_0= ruleRow ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_33); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getRowsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:1723:1: ( (lv_entries_3_0= ruleRow ) )
                    	    // InternalSqlParser.g:1724:1: (lv_entries_3_0= ruleRow )
                    	    {
                    	    // InternalSqlParser.g:1724:1: (lv_entries_3_0= ruleRow )
                    	    // InternalSqlParser.g:1725:3: lv_entries_3_0= ruleRow
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getRowsAccess().getEntriesRowParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleRow();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt40 >= 1 ) break loop40;
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
    // $ANTLR end "ruleRows"


    // $ANTLR start "entryRuleRow"
    // InternalSqlParser.g:1749:1: entryRuleRow returns [EObject current=null] : iv_ruleRow= ruleRow EOF ;
    public final EObject entryRuleRow() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRow = null;


        try {
            // InternalSqlParser.g:1750:2: (iv_ruleRow= ruleRow EOF )
            // InternalSqlParser.g:1751:2: iv_ruleRow= ruleRow EOF
            {
             newCompositeNode(grammarAccess.getRowRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRow=ruleRow();

            state._fsp--;

             current =iv_ruleRow; 
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
    // $ANTLR end "entryRuleRow"


    // $ANTLR start "ruleRow"
    // InternalSqlParser.g:1758:1: ruleRow returns [EObject current=null] : (otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis ) ;
    public final EObject ruleRow() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_rowValues_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1761:28: ( (otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis ) )
            // InternalSqlParser.g:1762:1: (otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis )
            {
            // InternalSqlParser.g:1762:1: (otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis )
            // InternalSqlParser.g:1763:2: otherlv_0= LeftParenthesis ( (lv_rowValues_1_0= ruleRowValues ) ) otherlv_2= RightParenthesis
            {
            otherlv_0=(Token)match(input,LeftParenthesis,FOLLOW_39); 

                	newLeafNode(otherlv_0, grammarAccess.getRowAccess().getLeftParenthesisKeyword_0());
                
            // InternalSqlParser.g:1767:1: ( (lv_rowValues_1_0= ruleRowValues ) )
            // InternalSqlParser.g:1768:1: (lv_rowValues_1_0= ruleRowValues )
            {
            // InternalSqlParser.g:1768:1: (lv_rowValues_1_0= ruleRowValues )
            // InternalSqlParser.g:1769:3: lv_rowValues_1_0= ruleRowValues
            {
             
            	        newCompositeNode(grammarAccess.getRowAccess().getRowValuesRowValuesParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_34);
            lv_rowValues_1_0=ruleRowValues();

            state._fsp--;


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

            otherlv_2=(Token)match(input,RightParenthesis,FOLLOW_2); 

                	newLeafNode(otherlv_2, grammarAccess.getRowAccess().getRightParenthesisKeyword_2());
                

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
    // $ANTLR end "ruleRow"


    // $ANTLR start "entryRuleRowValues"
    // InternalSqlParser.g:1798:1: entryRuleRowValues returns [EObject current=null] : iv_ruleRowValues= ruleRowValues EOF ;
    public final EObject entryRuleRowValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRowValues = null;


        try {
            // InternalSqlParser.g:1799:2: (iv_ruleRowValues= ruleRowValues EOF )
            // InternalSqlParser.g:1800:2: iv_ruleRowValues= ruleRowValues EOF
            {
             newCompositeNode(grammarAccess.getRowValuesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRowValues=ruleRowValues();

            state._fsp--;

             current =iv_ruleRowValues; 
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
    // $ANTLR end "entryRuleRowValues"


    // $ANTLR start "ruleRowValues"
    // InternalSqlParser.g:1807:1: ruleRowValues returns [EObject current=null] : (this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )? ) ;
    public final EObject ruleRowValues() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_RowValue_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1810:28: ( (this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )? ) )
            // InternalSqlParser.g:1811:1: (this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )? )
            {
            // InternalSqlParser.g:1811:1: (this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )? )
            // InternalSqlParser.g:1812:5: this_RowValue_0= ruleRowValue ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getRowValuesAccess().getRowValueParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_RowValue_0=ruleRowValue();

            state._fsp--;


                    current = this_RowValue_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:1820:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+ )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==Comma) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalSqlParser.g:1820:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+
                    {
                    // InternalSqlParser.g:1820:2: ()
                    // InternalSqlParser.g:1821:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getRowValuesAccess().getRowValuesEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:1826:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) ) )+
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
                    	    // InternalSqlParser.g:1827:2: otherlv_2= Comma ( (lv_entries_3_0= ruleRowValue ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_39); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getRowValuesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:1831:1: ( (lv_entries_3_0= ruleRowValue ) )
                    	    // InternalSqlParser.g:1832:1: (lv_entries_3_0= ruleRowValue )
                    	    {
                    	    // InternalSqlParser.g:1832:1: (lv_entries_3_0= ruleRowValue )
                    	    // InternalSqlParser.g:1833:3: lv_entries_3_0= ruleRowValue
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getRowValuesAccess().getEntriesRowValueParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_6);
                    	    lv_entries_3_0=ruleRowValue();

                    	    state._fsp--;


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
                    	    break;

                    	default :
                    	    if ( cnt42 >= 1 ) break loop42;
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
    // $ANTLR end "ruleRowValues"


    // $ANTLR start "entryRuleRowValue"
    // InternalSqlParser.g:1857:1: entryRuleRowValue returns [EObject current=null] : iv_ruleRowValue= ruleRowValue EOF ;
    public final EObject entryRuleRowValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRowValue = null;


        try {
            // InternalSqlParser.g:1858:2: (iv_ruleRowValue= ruleRowValue EOF )
            // InternalSqlParser.g:1859:2: iv_ruleRowValue= ruleRowValue EOF
            {
             newCompositeNode(grammarAccess.getRowValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRowValue=ruleRowValue();

            state._fsp--;

             current =iv_ruleRowValue; 
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
    // $ANTLR end "entryRuleRowValue"


    // $ANTLR start "ruleRowValue"
    // InternalSqlParser.g:1866:1: ruleRowValue returns [EObject current=null] : (this_ScalarNumberOperand_0= ruleScalarNumberOperand | ( (lv_null_1_0= NULL ) ) ) ;
    public final EObject ruleRowValue() throws RecognitionException {
        EObject current = null;

        Token lv_null_1_0=null;
        EObject this_ScalarNumberOperand_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:1869:28: ( (this_ScalarNumberOperand_0= ruleScalarNumberOperand | ( (lv_null_1_0= NULL ) ) ) )
            // InternalSqlParser.g:1870:1: (this_ScalarNumberOperand_0= ruleScalarNumberOperand | ( (lv_null_1_0= NULL ) ) )
            {
            // InternalSqlParser.g:1870:1: (this_ScalarNumberOperand_0= ruleScalarNumberOperand | ( (lv_null_1_0= NULL ) ) )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( ((LA44_0>=RULE_UNSIGNED && LA44_0<=RULE_SIGNED_DOUBLE)||LA44_0==RULE_STRING_) ) {
                alt44=1;
            }
            else if ( (LA44_0==NULL) ) {
                alt44=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // InternalSqlParser.g:1871:5: this_ScalarNumberOperand_0= ruleScalarNumberOperand
                    {
                     
                            newCompositeNode(grammarAccess.getRowValueAccess().getScalarNumberOperandParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_ScalarNumberOperand_0=ruleScalarNumberOperand();

                    state._fsp--;


                            current = this_ScalarNumberOperand_0;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:1880:6: ( (lv_null_1_0= NULL ) )
                    {
                    // InternalSqlParser.g:1880:6: ( (lv_null_1_0= NULL ) )
                    // InternalSqlParser.g:1881:1: (lv_null_1_0= NULL )
                    {
                    // InternalSqlParser.g:1881:1: (lv_null_1_0= NULL )
                    // InternalSqlParser.g:1882:3: lv_null_1_0= NULL
                    {
                    lv_null_1_0=(Token)match(input,NULL,FOLLOW_2); 

                            newLeafNode(lv_null_1_0, grammarAccess.getRowValueAccess().getNullNULLKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRowValueRule());
                    	        }
                           		setWithLastConsumed(current, "null", lv_null_1_0, "NULL");
                    	    

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
    // $ANTLR end "ruleRowValue"


    // $ANTLR start "entryRulePivotTable"
    // InternalSqlParser.g:1904:1: entryRulePivotTable returns [EObject current=null] : iv_rulePivotTable= rulePivotTable EOF ;
    public final EObject entryRulePivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotTable = null;


        try {
            // InternalSqlParser.g:1905:2: (iv_rulePivotTable= rulePivotTable EOF )
            // InternalSqlParser.g:1906:2: iv_rulePivotTable= rulePivotTable EOF
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
    // InternalSqlParser.g:1913:1: rulePivotTable returns [EObject current=null] : (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis ) ;
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
            // InternalSqlParser.g:1916:28: ( (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis ) )
            // InternalSqlParser.g:1917:1: (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis )
            {
            // InternalSqlParser.g:1917:1: (otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis )
            // InternalSqlParser.g:1918:2: otherlv_0= PIVOT (otherlv_1= XML )? otherlv_2= LeftParenthesis ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= RightParenthesis
            {
            otherlv_0=(Token)match(input,PIVOT,FOLLOW_40); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotTableAccess().getPIVOTKeyword_0());
                
            // InternalSqlParser.g:1922:1: (otherlv_1= XML )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==XML) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalSqlParser.g:1923:2: otherlv_1= XML
                    {
                    otherlv_1=(Token)match(input,XML,FOLLOW_33); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotTableAccess().getXMLKeyword_1());
                        

                    }
                    break;

            }

            otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_41); 

                	newLeafNode(otherlv_2, grammarAccess.getPivotTableAccess().getLeftParenthesisKeyword_2());
                
            // InternalSqlParser.g:1932:1: ( (lv_pfun_3_0= rulePivotFunctions ) )
            // InternalSqlParser.g:1933:1: (lv_pfun_3_0= rulePivotFunctions )
            {
            // InternalSqlParser.g:1933:1: (lv_pfun_3_0= rulePivotFunctions )
            // InternalSqlParser.g:1934:3: lv_pfun_3_0= rulePivotFunctions
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPfunPivotFunctionsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_42);
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

            // InternalSqlParser.g:1950:2: ( (lv_pfor_4_0= rulePivotForClause ) )
            // InternalSqlParser.g:1951:1: (lv_pfor_4_0= rulePivotForClause )
            {
            // InternalSqlParser.g:1951:1: (lv_pfor_4_0= rulePivotForClause )
            // InternalSqlParser.g:1952:3: lv_pfor_4_0= rulePivotForClause
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_43);
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

            // InternalSqlParser.g:1968:2: ( (lv_pin_5_0= rulePivotInClause ) )
            // InternalSqlParser.g:1969:1: (lv_pin_5_0= rulePivotInClause )
            {
            // InternalSqlParser.g:1969:1: (lv_pin_5_0= rulePivotInClause )
            // InternalSqlParser.g:1970:3: lv_pin_5_0= rulePivotInClause
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPinPivotInClauseParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:1999:1: entryRulePivotFunctions returns [EObject current=null] : iv_rulePivotFunctions= rulePivotFunctions EOF ;
    public final EObject entryRulePivotFunctions() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotFunctions = null;


        try {
            // InternalSqlParser.g:2000:2: (iv_rulePivotFunctions= rulePivotFunctions EOF )
            // InternalSqlParser.g:2001:2: iv_rulePivotFunctions= rulePivotFunctions EOF
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
    // InternalSqlParser.g:2008:1: rulePivotFunctions returns [EObject current=null] : ( (lv_abc_0_0= RULE_ID ) ) ;
    public final EObject rulePivotFunctions() throws RecognitionException {
        EObject current = null;

        Token lv_abc_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:2011:28: ( ( (lv_abc_0_0= RULE_ID ) ) )
            // InternalSqlParser.g:2012:1: ( (lv_abc_0_0= RULE_ID ) )
            {
            // InternalSqlParser.g:2012:1: ( (lv_abc_0_0= RULE_ID ) )
            // InternalSqlParser.g:2013:1: (lv_abc_0_0= RULE_ID )
            {
            // InternalSqlParser.g:2013:1: (lv_abc_0_0= RULE_ID )
            // InternalSqlParser.g:2014:3: lv_abc_0_0= RULE_ID
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
    // InternalSqlParser.g:2040:1: entryRulePivotInClause returns [EObject current=null] : iv_rulePivotInClause= rulePivotInClause EOF ;
    public final EObject entryRulePivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotInClause = null;


        try {
            // InternalSqlParser.g:2041:2: (iv_rulePivotInClause= rulePivotInClause EOF )
            // InternalSqlParser.g:2042:2: iv_rulePivotInClause= rulePivotInClause EOF
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
    // InternalSqlParser.g:2049:1: rulePivotInClause returns [EObject current=null] : (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis ) ;
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
            // InternalSqlParser.g:2052:28: ( (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis ) )
            // InternalSqlParser.g:2053:1: (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis )
            {
            // InternalSqlParser.g:2053:1: (otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis )
            // InternalSqlParser.g:2054:2: otherlv_0= IN otherlv_1= LeftParenthesis ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= RightParenthesis
            {
            otherlv_0=(Token)match(input,IN,FOLLOW_33); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotInClauseAccess().getINKeyword_0());
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_44); 

                	newLeafNode(otherlv_1, grammarAccess.getPivotInClauseAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:2063:1: ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) )
            int alt46=3;
            switch ( input.LA(1) ) {
            case LeftParenthesis:
                {
                int LA46_1 = input.LA(2);

                if ( (LA46_1==SELECT) ) {
                    alt46=1;
                }
                else if ( ((LA46_1>=RULE_STRING && LA46_1<=RULE_ID)) ) {
                    alt46=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt46=2;
                }
                break;
            case ANY:
                {
                alt46=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // InternalSqlParser.g:2063:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:2063:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:2064:1: (lv_sq_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:2064:1: (lv_sq_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:2065:3: lv_sq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getSqSubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_34);
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
                    // InternalSqlParser.g:2082:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    {
                    // InternalSqlParser.g:2082:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    // InternalSqlParser.g:2083:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    {
                    // InternalSqlParser.g:2083:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    // InternalSqlParser.g:2084:3: lv_args_3_0= ruleUnpivotInClauseArgs
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_34);
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
                    // InternalSqlParser.g:2101:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    {
                    // InternalSqlParser.g:2101:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    // InternalSqlParser.g:2102:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    {
                    // InternalSqlParser.g:2102:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    // InternalSqlParser.g:2103:3: lv_pinany_4_0= rulePivotInClauseAny
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getPinanyPivotInClauseAnyParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:2132:1: entryRulePivotInClauseAny returns [String current=null] : iv_rulePivotInClauseAny= rulePivotInClauseAny EOF ;
    public final String entryRulePivotInClauseAny() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePivotInClauseAny = null;


        try {
            // InternalSqlParser.g:2133:1: (iv_rulePivotInClauseAny= rulePivotInClauseAny EOF )
            // InternalSqlParser.g:2134:2: iv_rulePivotInClauseAny= rulePivotInClauseAny EOF
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
    // InternalSqlParser.g:2141:1: rulePivotInClauseAny returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= ANY (kw= Comma kw= ANY )* ) ;
    public final AntlrDatatypeRuleToken rulePivotInClauseAny() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:2145:6: ( (kw= ANY (kw= Comma kw= ANY )* ) )
            // InternalSqlParser.g:2146:1: (kw= ANY (kw= Comma kw= ANY )* )
            {
            // InternalSqlParser.g:2146:1: (kw= ANY (kw= Comma kw= ANY )* )
            // InternalSqlParser.g:2147:2: kw= ANY (kw= Comma kw= ANY )*
            {
            kw=(Token)match(input,ANY,FOLLOW_6); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_0()); 
                
            // InternalSqlParser.g:2152:1: (kw= Comma kw= ANY )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==Comma) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalSqlParser.g:2153:2: kw= Comma kw= ANY
            	    {
            	    kw=(Token)match(input,Comma,FOLLOW_45); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getCommaKeyword_1_0()); 
            	        
            	    kw=(Token)match(input,ANY,FOLLOW_6); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop47;
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
    // InternalSqlParser.g:2172:1: entryRuleUnpivotTable returns [EObject current=null] : iv_ruleUnpivotTable= ruleUnpivotTable EOF ;
    public final EObject entryRuleUnpivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotTable = null;


        try {
            // InternalSqlParser.g:2173:2: (iv_ruleUnpivotTable= ruleUnpivotTable EOF )
            // InternalSqlParser.g:2174:2: iv_ruleUnpivotTable= ruleUnpivotTable EOF
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
    // InternalSqlParser.g:2181:1: ruleUnpivotTable returns [EObject current=null] : (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis ) ;
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
            // InternalSqlParser.g:2184:28: ( (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis ) )
            // InternalSqlParser.g:2185:1: (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis )
            {
            // InternalSqlParser.g:2185:1: (otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis )
            // InternalSqlParser.g:2186:2: otherlv_0= UNPIVOT ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )? otherlv_4= LeftParenthesis ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= RightParenthesis
            {
            otherlv_0=(Token)match(input,UNPIVOT,FOLLOW_46); 

                	newLeafNode(otherlv_0, grammarAccess.getUnpivotTableAccess().getUNPIVOTKeyword_0());
                
            // InternalSqlParser.g:2190:1: ( (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==EXCLUDE||LA49_0==INCLUDE) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalSqlParser.g:2190:2: (otherlv_1= INCLUDE | otherlv_2= EXCLUDE ) otherlv_3= NULLS
                    {
                    // InternalSqlParser.g:2190:2: (otherlv_1= INCLUDE | otherlv_2= EXCLUDE )
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==INCLUDE) ) {
                        alt48=1;
                    }
                    else if ( (LA48_0==EXCLUDE) ) {
                        alt48=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 48, 0, input);

                        throw nvae;
                    }
                    switch (alt48) {
                        case 1 :
                            // InternalSqlParser.g:2191:2: otherlv_1= INCLUDE
                            {
                            otherlv_1=(Token)match(input,INCLUDE,FOLLOW_47); 

                                	newLeafNode(otherlv_1, grammarAccess.getUnpivotTableAccess().getINCLUDEKeyword_1_0_0());
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:2197:2: otherlv_2= EXCLUDE
                            {
                            otherlv_2=(Token)match(input,EXCLUDE,FOLLOW_47); 

                                	newLeafNode(otherlv_2, grammarAccess.getUnpivotTableAccess().getEXCLUDEKeyword_1_0_1());
                                

                            }
                            break;

                    }

                    otherlv_3=(Token)match(input,NULLS,FOLLOW_33); 

                        	newLeafNode(otherlv_3, grammarAccess.getUnpivotTableAccess().getNULLSKeyword_1_1());
                        

                    }
                    break;

            }

            otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_15); 

                	newLeafNode(otherlv_4, grammarAccess.getUnpivotTableAccess().getLeftParenthesisKeyword_2());
                
            // InternalSqlParser.g:2211:1: ( (lv_pcols_5_0= rulePivotColumns ) )
            // InternalSqlParser.g:2212:1: (lv_pcols_5_0= rulePivotColumns )
            {
            // InternalSqlParser.g:2212:1: (lv_pcols_5_0= rulePivotColumns )
            // InternalSqlParser.g:2213:3: lv_pcols_5_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPcolsPivotColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_42);
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

            // InternalSqlParser.g:2229:2: ( (lv_pfor_6_0= rulePivotForClause ) )
            // InternalSqlParser.g:2230:1: (lv_pfor_6_0= rulePivotForClause )
            {
            // InternalSqlParser.g:2230:1: (lv_pfor_6_0= rulePivotForClause )
            // InternalSqlParser.g:2231:3: lv_pfor_6_0= rulePivotForClause
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_43);
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

            // InternalSqlParser.g:2247:2: ( (lv_inop_7_0= ruleUnpivotInClause ) )
            // InternalSqlParser.g:2248:1: (lv_inop_7_0= ruleUnpivotInClause )
            {
            // InternalSqlParser.g:2248:1: (lv_inop_7_0= ruleUnpivotInClause )
            // InternalSqlParser.g:2249:3: lv_inop_7_0= ruleUnpivotInClause
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getInopUnpivotInClauseParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:2278:1: entryRuleUnpivotInClause returns [EObject current=null] : iv_ruleUnpivotInClause= ruleUnpivotInClause EOF ;
    public final EObject entryRuleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClause = null;


        try {
            // InternalSqlParser.g:2279:2: (iv_ruleUnpivotInClause= ruleUnpivotInClause EOF )
            // InternalSqlParser.g:2280:2: iv_ruleUnpivotInClause= ruleUnpivotInClause EOF
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
    // InternalSqlParser.g:2287:1: ruleUnpivotInClause returns [EObject current=null] : ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis ) ;
    public final EObject ruleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2290:28: ( ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis ) )
            // InternalSqlParser.g:2291:1: ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis )
            {
            // InternalSqlParser.g:2291:1: ( () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis )
            // InternalSqlParser.g:2291:2: () ( (lv_op_1_0= IN ) ) otherlv_2= LeftParenthesis ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= RightParenthesis
            {
            // InternalSqlParser.g:2291:2: ()
            // InternalSqlParser.g:2292:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getUnpivotInClauseAccess().getUnipivotInClauseAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:2297:2: ( (lv_op_1_0= IN ) )
            // InternalSqlParser.g:2298:1: (lv_op_1_0= IN )
            {
            // InternalSqlParser.g:2298:1: (lv_op_1_0= IN )
            // InternalSqlParser.g:2299:3: lv_op_1_0= IN
            {
            lv_op_1_0=(Token)match(input,IN,FOLLOW_33); 

                    newLeafNode(lv_op_1_0, grammarAccess.getUnpivotInClauseAccess().getOpINKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUnpivotInClauseRule());
            	        }
                   		setWithLastConsumed(current, "op", lv_op_1_0, "IN");
            	    

            }


            }

            otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_15); 

                	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseAccess().getLeftParenthesisKeyword_2());
                
            // InternalSqlParser.g:2318:1: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
            // InternalSqlParser.g:2319:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            {
            // InternalSqlParser.g:2319:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            // InternalSqlParser.g:2320:3: lv_args_3_0= ruleUnpivotInClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:2349:1: entryRuleUnpivotInClauseArgs returns [EObject current=null] : iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF ;
    public final EObject entryRuleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArgs = null;


        try {
            // InternalSqlParser.g:2350:2: (iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF )
            // InternalSqlParser.g:2351:2: iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF
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
    // InternalSqlParser.g:2358:1: ruleUnpivotInClauseArgs returns [EObject current=null] : (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) ;
    public final EObject ruleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_UnpivotInClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2361:28: ( (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) )
            // InternalSqlParser.g:2362:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            {
            // InternalSqlParser.g:2362:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            // InternalSqlParser.g:2363:5: this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getUnpivotInClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_UnpivotInClauseArg_0=ruleUnpivotInClauseArg();

            state._fsp--;


                    current = this_UnpivotInClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2371:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==Comma) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalSqlParser.g:2371:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    {
                    // InternalSqlParser.g:2371:2: ()
                    // InternalSqlParser.g:2372:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getUnpivotInClauseArgsAccess().getUicargsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2377:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    int cnt50=0;
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==Comma) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // InternalSqlParser.g:2378:2: otherlv_2= Comma ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_15); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2382:1: ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    // InternalSqlParser.g:2383:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    {
                    	    // InternalSqlParser.g:2383:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    // InternalSqlParser.g:2384:3: lv_entries_3_0= ruleUnpivotInClauseArg
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
    // $ANTLR end "ruleUnpivotInClauseArgs"


    // $ANTLR start "entryRuleUnpivotInClauseArg"
    // InternalSqlParser.g:2408:1: entryRuleUnpivotInClauseArg returns [EObject current=null] : iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF ;
    public final EObject entryRuleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArg = null;


        try {
            // InternalSqlParser.g:2409:2: (iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF )
            // InternalSqlParser.g:2410:2: iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF
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
    // InternalSqlParser.g:2417:1: ruleUnpivotInClauseArg returns [EObject current=null] : ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) ;
    public final EObject ruleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_pcols_0_0 = null;

        EObject lv_cfuls_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2420:28: ( ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) )
            // InternalSqlParser.g:2421:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            {
            // InternalSqlParser.g:2421:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            // InternalSqlParser.g:2421:2: ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            {
            // InternalSqlParser.g:2421:2: ( (lv_pcols_0_0= rulePivotColumns ) )
            // InternalSqlParser.g:2422:1: (lv_pcols_0_0= rulePivotColumns )
            {
            // InternalSqlParser.g:2422:1: (lv_pcols_0_0= rulePivotColumns )
            // InternalSqlParser.g:2423:3: lv_pcols_0_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getPcolsPivotColumnsParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_48);
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

            // InternalSqlParser.g:2439:2: (otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==AS) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalSqlParser.g:2440:2: otherlv_1= AS ( (lv_cfuls_2_0= rulePivotColumns ) )
                    {
                    otherlv_1=(Token)match(input,AS,FOLLOW_15); 

                        	newLeafNode(otherlv_1, grammarAccess.getUnpivotInClauseArgAccess().getASKeyword_1_0());
                        
                    // InternalSqlParser.g:2444:1: ( (lv_cfuls_2_0= rulePivotColumns ) )
                    // InternalSqlParser.g:2445:1: (lv_cfuls_2_0= rulePivotColumns )
                    {
                    // InternalSqlParser.g:2445:1: (lv_cfuls_2_0= rulePivotColumns )
                    // InternalSqlParser.g:2446:3: lv_cfuls_2_0= rulePivotColumns
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
    // InternalSqlParser.g:2470:1: entryRulePivotForClause returns [EObject current=null] : iv_rulePivotForClause= rulePivotForClause EOF ;
    public final EObject entryRulePivotForClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotForClause = null;


        try {
            // InternalSqlParser.g:2471:2: (iv_rulePivotForClause= rulePivotForClause EOF )
            // InternalSqlParser.g:2472:2: iv_rulePivotForClause= rulePivotForClause EOF
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
    // InternalSqlParser.g:2479:1: rulePivotForClause returns [EObject current=null] : (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) ) ;
    public final EObject rulePivotForClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ColumnFull_1 = null;

        EObject this_Columns_3 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2482:28: ( (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) ) )
            // InternalSqlParser.g:2483:1: (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) )
            {
            // InternalSqlParser.g:2483:1: (otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) ) )
            // InternalSqlParser.g:2484:2: otherlv_0= FOR (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) )
            {
            otherlv_0=(Token)match(input,FOR,FOLLOW_15); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotForClauseAccess().getFORKeyword_0());
                
            // InternalSqlParser.g:2488:1: (this_ColumnFull_1= ruleColumnFull | (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis ) )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( ((LA53_0>=RULE_STRING && LA53_0<=RULE_ID)) ) {
                alt53=1;
            }
            else if ( (LA53_0==LeftParenthesis) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // InternalSqlParser.g:2489:5: this_ColumnFull_1= ruleColumnFull
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
                    // InternalSqlParser.g:2498:6: (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis )
                    {
                    // InternalSqlParser.g:2498:6: (otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis )
                    // InternalSqlParser.g:2499:2: otherlv_2= LeftParenthesis this_Columns_3= ruleColumns otherlv_4= RightParenthesis
                    {
                    otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_10); 

                        	newLeafNode(otherlv_2, grammarAccess.getPivotForClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:2525:1: entryRulePivotColumns returns [EObject current=null] : iv_rulePivotColumns= rulePivotColumns EOF ;
    public final EObject entryRulePivotColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotColumns = null;


        try {
            // InternalSqlParser.g:2526:2: (iv_rulePivotColumns= rulePivotColumns EOF )
            // InternalSqlParser.g:2527:2: iv_rulePivotColumns= rulePivotColumns EOF
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
    // InternalSqlParser.g:2534:1: rulePivotColumns returns [EObject current=null] : (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) ) ;
    public final EObject rulePivotColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_PivotCol_0 = null;

        EObject this_PivotCols_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2537:28: ( (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) ) )
            // InternalSqlParser.g:2538:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) )
            {
            // InternalSqlParser.g:2538:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis ) )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( ((LA54_0>=RULE_STRING && LA54_0<=RULE_ID)) ) {
                alt54=1;
            }
            else if ( (LA54_0==LeftParenthesis) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // InternalSqlParser.g:2539:5: this_PivotCol_0= rulePivotCol
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
                    // InternalSqlParser.g:2548:6: (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis )
                    {
                    // InternalSqlParser.g:2548:6: (otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis )
                    // InternalSqlParser.g:2549:2: otherlv_1= LeftParenthesis this_PivotCols_2= rulePivotCols otherlv_3= RightParenthesis
                    {
                    otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_30); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotColumnsAccess().getLeftParenthesisKeyword_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColsParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:2575:1: entryRulePivotCols returns [EObject current=null] : iv_rulePivotCols= rulePivotCols EOF ;
    public final EObject entryRulePivotCols() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCols = null;


        try {
            // InternalSqlParser.g:2576:2: (iv_rulePivotCols= rulePivotCols EOF )
            // InternalSqlParser.g:2577:2: iv_rulePivotCols= rulePivotCols EOF
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
    // InternalSqlParser.g:2584:1: rulePivotCols returns [EObject current=null] : (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) ;
    public final EObject rulePivotCols() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PivotCol_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2587:28: ( (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) )
            // InternalSqlParser.g:2588:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            {
            // InternalSqlParser.g:2588:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            // InternalSqlParser.g:2589:5: this_PivotCol_0= rulePivotCol ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getPivotColsAccess().getPivotColParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_PivotCol_0=rulePivotCol();

            state._fsp--;


                    current = this_PivotCol_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2597:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==Comma) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalSqlParser.g:2597:2: () (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+
                    {
                    // InternalSqlParser.g:2597:2: ()
                    // InternalSqlParser.g:2598:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getPivotColsAccess().getPvcsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2603:2: (otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) ) )+
                    int cnt55=0;
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==Comma) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // InternalSqlParser.g:2604:2: otherlv_2= Comma ( (lv_entries_3_0= rulePivotCol ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_30); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2608:1: ( (lv_entries_3_0= rulePivotCol ) )
                    	    // InternalSqlParser.g:2609:1: (lv_entries_3_0= rulePivotCol )
                    	    {
                    	    // InternalSqlParser.g:2609:1: (lv_entries_3_0= rulePivotCol )
                    	    // InternalSqlParser.g:2610:3: lv_entries_3_0= rulePivotCol
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
                    	    if ( cnt55 >= 1 ) break loop55;
                                EarlyExitException eee =
                                    new EarlyExitException(55, input);
                                throw eee;
                        }
                        cnt55++;
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
    // InternalSqlParser.g:2634:1: entryRulePivotCol returns [EObject current=null] : iv_rulePivotCol= rulePivotCol EOF ;
    public final EObject entryRulePivotCol() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCol = null;


        try {
            // InternalSqlParser.g:2635:2: (iv_rulePivotCol= rulePivotCol EOF )
            // InternalSqlParser.g:2636:2: iv_rulePivotCol= rulePivotCol EOF
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
    // InternalSqlParser.g:2643:1: rulePivotCol returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject rulePivotCol() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2646:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:2647:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:2647:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:2648:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getPivotColAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_29);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2656:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==FullStop) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalSqlParser.g:2656:2: () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:2656:2: ()
                    // InternalSqlParser.g:2657:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getPivotColAccess().getPcolsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2662:2: (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt57=0;
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==FullStop) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // InternalSqlParser.g:2663:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_30); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2667:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:2668:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:2668:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:2669:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_29);
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
                    	    if ( cnt57 >= 1 ) break loop57;
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
    // InternalSqlParser.g:2693:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // InternalSqlParser.g:2694:2: (iv_ruleTableFull= ruleTableFull EOF )
            // InternalSqlParser.g:2695:2: iv_ruleTableFull= ruleTableFull EOF
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
    // InternalSqlParser.g:2702:1: ruleTableFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2705:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // InternalSqlParser.g:2706:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // InternalSqlParser.g:2706:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // InternalSqlParser.g:2707:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_29);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2715:1: ( () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==FullStop) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalSqlParser.g:2715:2: () (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // InternalSqlParser.g:2715:2: ()
                    // InternalSqlParser.g:2716:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2721:2: (otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) ) )+
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
                    	    // InternalSqlParser.g:2722:2: otherlv_2= FullStop ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,FullStop,FOLLOW_30); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2726:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // InternalSqlParser.g:2727:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // InternalSqlParser.g:2727:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // InternalSqlParser.g:2728:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_29);
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
    // $ANTLR end "ruleTableFull"


    // $ANTLR start "entryRuleDbObjectNameAll"
    // InternalSqlParser.g:2752:1: entryRuleDbObjectNameAll returns [EObject current=null] : iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF ;
    public final EObject entryRuleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectNameAll = null;


        try {
            // InternalSqlParser.g:2753:2: (iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF )
            // InternalSqlParser.g:2754:2: iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF
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
    // InternalSqlParser.g:2761:1: ruleDbObjectNameAll returns [EObject current=null] : ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR ) ;
    public final EObject ruleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_STAR_2=null;
        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2764:28: ( ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR ) )
            // InternalSqlParser.g:2765:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR )
            {
            // InternalSqlParser.g:2765:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR )
            // InternalSqlParser.g:2765:2: ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= FullStop this_STAR_2= RULE_STAR
            {
            // InternalSqlParser.g:2765:2: ( (lv_dbname_0_0= ruleDBID ) )
            // InternalSqlParser.g:2766:1: (lv_dbname_0_0= ruleDBID )
            {
            // InternalSqlParser.g:2766:1: (lv_dbname_0_0= ruleDBID )
            // InternalSqlParser.g:2767:3: lv_dbname_0_0= ruleDBID
            {
             
            	        newCompositeNode(grammarAccess.getDbObjectNameAllAccess().getDbnameDBIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_49);
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

            otherlv_1=(Token)match(input,FullStop,FOLLOW_50); 

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
    // InternalSqlParser.g:2800:1: entryRuleDbObjectName returns [EObject current=null] : iv_ruleDbObjectName= ruleDbObjectName EOF ;
    public final EObject entryRuleDbObjectName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectName = null;


        try {
            // InternalSqlParser.g:2801:2: (iv_ruleDbObjectName= ruleDbObjectName EOF )
            // InternalSqlParser.g:2802:2: iv_ruleDbObjectName= ruleDbObjectName EOF
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
    // InternalSqlParser.g:2809:1: ruleDbObjectName returns [EObject current=null] : ( (lv_dbname_0_0= ruleDBID ) ) ;
    public final EObject ruleDbObjectName() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2812:28: ( ( (lv_dbname_0_0= ruleDBID ) ) )
            // InternalSqlParser.g:2813:1: ( (lv_dbname_0_0= ruleDBID ) )
            {
            // InternalSqlParser.g:2813:1: ( (lv_dbname_0_0= ruleDBID ) )
            // InternalSqlParser.g:2814:1: (lv_dbname_0_0= ruleDBID )
            {
            // InternalSqlParser.g:2814:1: (lv_dbname_0_0= ruleDBID )
            // InternalSqlParser.g:2815:3: lv_dbname_0_0= ruleDBID
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
    // InternalSqlParser.g:2839:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // InternalSqlParser.g:2840:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // InternalSqlParser.g:2841:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
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
    // InternalSqlParser.g:2848:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2851:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // InternalSqlParser.g:2852:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // InternalSqlParser.g:2852:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // InternalSqlParser.g:2853:5: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;


                    current = this_OrderByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:2861:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==Comma) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalSqlParser.g:2861:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // InternalSqlParser.g:2861:2: ()
                    // InternalSqlParser.g:2862:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:2867:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt61=0;
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==Comma) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // InternalSqlParser.g:2868:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_19); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:2872:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // InternalSqlParser.g:2873:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // InternalSqlParser.g:2873:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // InternalSqlParser.g:2874:3: lv_entries_3_0= ruleOrderByColumnFull
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
                    	    if ( cnt61 >= 1 ) break loop61;
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
    // InternalSqlParser.g:2898:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // InternalSqlParser.g:2899:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // InternalSqlParser.g:2900:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
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
    // InternalSqlParser.g:2907:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_colOrderInt_1_0=null;
        Token lv_direction_2_1=null;
        Token lv_direction_2_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:2910:28: ( ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? ) )
            // InternalSqlParser.g:2911:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? )
            {
            // InternalSqlParser.g:2911:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )? )
            // InternalSqlParser.g:2911:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )?
            {
            // InternalSqlParser.g:2911:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( ((LA63_0>=RULE_STRING && LA63_0<=RULE_ID)) ) {
                alt63=1;
            }
            else if ( (LA63_0==RULE_UNSIGNED) ) {
                alt63=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // InternalSqlParser.g:2911:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    {
                    // InternalSqlParser.g:2911:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    // InternalSqlParser.g:2912:1: (lv_colOrder_0_0= ruleColumnFull )
                    {
                    // InternalSqlParser.g:2912:1: (lv_colOrder_0_0= ruleColumnFull )
                    // InternalSqlParser.g:2913:3: lv_colOrder_0_0= ruleColumnFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_51);
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
                    // InternalSqlParser.g:2930:6: ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:2930:6: ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:2931:1: (lv_colOrderInt_1_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:2931:1: (lv_colOrderInt_1_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:2932:3: lv_colOrderInt_1_0= RULE_UNSIGNED
                    {
                    lv_colOrderInt_1_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_51); 

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

            // InternalSqlParser.g:2948:3: ( ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==DESC||LA65_0==ASC) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalSqlParser.g:2949:1: ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) )
                    {
                    // InternalSqlParser.g:2949:1: ( (lv_direction_2_1= ASC | lv_direction_2_2= DESC ) )
                    // InternalSqlParser.g:2950:1: (lv_direction_2_1= ASC | lv_direction_2_2= DESC )
                    {
                    // InternalSqlParser.g:2950:1: (lv_direction_2_1= ASC | lv_direction_2_2= DESC )
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==ASC) ) {
                        alt64=1;
                    }
                    else if ( (LA64_0==DESC) ) {
                        alt64=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 64, 0, input);

                        throw nvae;
                    }
                    switch (alt64) {
                        case 1 :
                            // InternalSqlParser.g:2951:3: lv_direction_2_1= ASC
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
                            // InternalSqlParser.g:2964:8: lv_direction_2_2= DESC
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
    // InternalSqlParser.g:2988:1: entryRuleGroupByColumns returns [EObject current=null] : iv_ruleGroupByColumns= ruleGroupByColumns EOF ;
    public final EObject entryRuleGroupByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumns = null;


        try {
            // InternalSqlParser.g:2989:2: (iv_ruleGroupByColumns= ruleGroupByColumns EOF )
            // InternalSqlParser.g:2990:2: iv_ruleGroupByColumns= ruleGroupByColumns EOF
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
    // InternalSqlParser.g:2997:1: ruleGroupByColumns returns [EObject current=null] : (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) ;
    public final EObject ruleGroupByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_GroupByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3000:28: ( (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) )
            // InternalSqlParser.g:3001:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            {
            // InternalSqlParser.g:3001:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            // InternalSqlParser.g:3002:5: this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_GroupByColumnFull_0=ruleGroupByColumnFull();

            state._fsp--;


                    current = this_GroupByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:3010:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==Comma) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalSqlParser.g:3010:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    {
                    // InternalSqlParser.g:3010:2: ()
                    // InternalSqlParser.g:3011:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:3016:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    int cnt66=0;
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==Comma) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // InternalSqlParser.g:3017:2: otherlv_2= Comma ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_19); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:3021:1: ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    // InternalSqlParser.g:3022:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    {
                    	    // InternalSqlParser.g:3022:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    // InternalSqlParser.g:3023:3: lv_entries_3_0= ruleGroupByColumnFull
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
                    	    if ( cnt66 >= 1 ) break loop66;
                                EarlyExitException eee =
                                    new EarlyExitException(66, input);
                                throw eee;
                        }
                        cnt66++;
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
    // InternalSqlParser.g:3047:1: entryRuleGroupByColumnFull returns [EObject current=null] : iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF ;
    public final EObject entryRuleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumnFull = null;


        try {
            // InternalSqlParser.g:3048:2: (iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF )
            // InternalSqlParser.g:3049:2: iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF
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
    // InternalSqlParser.g:3056:1: ruleGroupByColumnFull returns [EObject current=null] : ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) ) ;
    public final EObject ruleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_grByInt_2_0=null;
        EObject lv_colGrBy_0_0 = null;

        EObject lv_gbFunction_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3059:28: ( ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) ) )
            // InternalSqlParser.g:3060:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) )
            {
            // InternalSqlParser.g:3060:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) )
            int alt68=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA68_1 = input.LA(2);

                if ( (LA68_1==LeftParenthesis) ) {
                    alt68=2;
                }
                else if ( (LA68_1==EOF||LA68_1==FETCHFIRST||LA68_1==INTERSECT||LA68_1==ORDERBY||LA68_1==EXCEPT||LA68_1==HAVING||LA68_1==OFFSET||(LA68_1>=LIMIT && LA68_1<=MINUS)||LA68_1==UNION||LA68_1==RightParenthesis||LA68_1==Comma||LA68_1==FullStop) ) {
                    alt68=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt68=1;
                }
                break;
            case RULE_UNSIGNED:
                {
                alt68=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }

            switch (alt68) {
                case 1 :
                    // InternalSqlParser.g:3060:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    {
                    // InternalSqlParser.g:3060:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    // InternalSqlParser.g:3061:1: (lv_colGrBy_0_0= ruleColumnFull )
                    {
                    // InternalSqlParser.g:3061:1: (lv_colGrBy_0_0= ruleColumnFull )
                    // InternalSqlParser.g:3062:3: lv_colGrBy_0_0= ruleColumnFull
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
                    // InternalSqlParser.g:3079:6: ( (lv_gbFunction_1_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:3079:6: ( (lv_gbFunction_1_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:3080:1: (lv_gbFunction_1_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:3080:1: (lv_gbFunction_1_0= ruleOperandFunction )
                    // InternalSqlParser.g:3081:3: lv_gbFunction_1_0= ruleOperandFunction
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
                    // InternalSqlParser.g:3098:6: ( (lv_grByInt_2_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:3098:6: ( (lv_grByInt_2_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:3099:1: (lv_grByInt_2_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:3099:1: (lv_grByInt_2_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:3100:3: lv_grByInt_2_0= RULE_UNSIGNED
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
    // InternalSqlParser.g:3124:1: entryRuleFullExpression returns [EObject current=null] : iv_ruleFullExpression= ruleFullExpression EOF ;
    public final EObject entryRuleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullExpression = null;


        try {
            // InternalSqlParser.g:3125:2: (iv_ruleFullExpression= ruleFullExpression EOF )
            // InternalSqlParser.g:3126:2: iv_ruleFullExpression= ruleFullExpression EOF
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
    // InternalSqlParser.g:3133:1: ruleFullExpression returns [EObject current=null] : (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) ;
    public final EObject ruleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExpressionFragment_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3136:28: ( (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) )
            // InternalSqlParser.g:3137:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            {
            // InternalSqlParser.g:3137:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            // InternalSqlParser.g:3138:5: this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_52);
            this_ExpressionFragment_0=ruleExpressionFragment();

            state._fsp--;


                    current = this_ExpressionFragment_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:3146:1: ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==AND||LA70_0==OR||LA70_0==RULE_JRNPARAM) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalSqlParser.g:3146:2: () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    {
                    // InternalSqlParser.g:3146:2: ()
                    // InternalSqlParser.g:3147:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:3152:2: ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    int cnt69=0;
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==AND||LA69_0==OR||LA69_0==RULE_JRNPARAM) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // InternalSqlParser.g:3153:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    {
                    	    // InternalSqlParser.g:3153:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    // InternalSqlParser.g:3154:3: lv_entries_2_0= ruleExpressionFragmentSecond
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_52);
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
                    	    if ( cnt69 >= 1 ) break loop69;
                                EarlyExitException eee =
                                    new EarlyExitException(69, input);
                                throw eee;
                        }
                        cnt69++;
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
    // InternalSqlParser.g:3178:1: entryRuleExpressionFragmentSecond returns [EObject current=null] : iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF ;
    public final EObject entryRuleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragmentSecond = null;


        try {
            // InternalSqlParser.g:3179:2: (iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF )
            // InternalSqlParser.g:3180:2: iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF
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
    // InternalSqlParser.g:3187:1: ruleExpressionFragmentSecond returns [EObject current=null] : ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) ;
    public final EObject ruleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        Token lv_c_0_1=null;
        Token lv_c_0_2=null;
        Token lv_notPrm_2_0=null;
        EObject lv_efrag_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3190:28: ( ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) )
            // InternalSqlParser.g:3191:1: ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            {
            // InternalSqlParser.g:3191:1: ( ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==AND||LA72_0==OR) ) {
                alt72=1;
            }
            else if ( (LA72_0==RULE_JRNPARAM) ) {
                alt72=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // InternalSqlParser.g:3191:2: ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    {
                    // InternalSqlParser.g:3191:2: ( ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    // InternalSqlParser.g:3191:3: ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    {
                    // InternalSqlParser.g:3191:3: ( ( (lv_c_0_1= AND | lv_c_0_2= OR ) ) )
                    // InternalSqlParser.g:3192:1: ( (lv_c_0_1= AND | lv_c_0_2= OR ) )
                    {
                    // InternalSqlParser.g:3192:1: ( (lv_c_0_1= AND | lv_c_0_2= OR ) )
                    // InternalSqlParser.g:3193:1: (lv_c_0_1= AND | lv_c_0_2= OR )
                    {
                    // InternalSqlParser.g:3193:1: (lv_c_0_1= AND | lv_c_0_2= OR )
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==AND) ) {
                        alt71=1;
                    }
                    else if ( (LA71_0==OR) ) {
                        alt71=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 71, 0, input);

                        throw nvae;
                    }
                    switch (alt71) {
                        case 1 :
                            // InternalSqlParser.g:3194:3: lv_c_0_1= AND
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
                            // InternalSqlParser.g:3207:8: lv_c_0_2= OR
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

                    // InternalSqlParser.g:3223:2: ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    // InternalSqlParser.g:3224:1: (lv_efrag_1_0= ruleExpressionFragment )
                    {
                    // InternalSqlParser.g:3224:1: (lv_efrag_1_0= ruleExpressionFragment )
                    // InternalSqlParser.g:3225:3: lv_efrag_1_0= ruleExpressionFragment
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
                    // InternalSqlParser.g:3242:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    {
                    // InternalSqlParser.g:3242:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    // InternalSqlParser.g:3243:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    {
                    // InternalSqlParser.g:3243:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    // InternalSqlParser.g:3244:3: lv_notPrm_2_0= RULE_JRNPARAM
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
    // InternalSqlParser.g:3268:1: entryRuleExpressionFragment returns [EObject current=null] : iv_ruleExpressionFragment= ruleExpressionFragment EOF ;
    public final EObject entryRuleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragment = null;


        try {
            // InternalSqlParser.g:3269:2: (iv_ruleExpressionFragment= ruleExpressionFragment EOF )
            // InternalSqlParser.g:3270:2: iv_ruleExpressionFragment= ruleExpressionFragment EOF
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
    // InternalSqlParser.g:3277:1: ruleExpressionFragment returns [EObject current=null] : ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) ;
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
            // InternalSqlParser.g:3280:28: ( ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) )
            // InternalSqlParser.g:3281:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            {
            // InternalSqlParser.g:3281:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            int alt74=6;
            switch ( input.LA(1) ) {
            case NOT:
            case NOT_1:
                {
                alt74=1;
                }
                break;
            case LeftParenthesis:
                {
                int LA74_2 = input.LA(2);

                if ( (LA74_2==NOTEXISTS||LA74_2==EXTRACT||LA74_2==EXISTS||LA74_2==NOTIN_1||LA74_2==CAST||LA74_2==CASE||(LA74_2>=NOT && LA74_2<=NOT_1)||LA74_2==X||LA74_2==IN||LA74_2==LeftParenthesis||(LA74_2>=RULE_JRPARAM && LA74_2<=RULE_JRNPARAM)||(LA74_2>=RULE_UNSIGNED && LA74_2<=RULE_SIGNED_DOUBLE)||(LA74_2>=RULE_STRING_ && LA74_2<=RULE_ID)) ) {
                    alt74=1;
                }
                else if ( (LA74_2==SELECT) ) {
                    alt74=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 74, 2, input);

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
                alt74=2;
                }
                break;
            case RULE_JRNPARAM:
                {
                int LA74_4 = input.LA(2);

                if ( (LA74_4==ISNOTNULL||LA74_4==NOTBETWEEN||LA74_4==NOTEXISTS||LA74_4==NOTLIKE||LA74_4==BETWEEN||LA74_4==ISNULL||LA74_4==EXISTS||LA74_4==NOTIN_1||LA74_4==LIKE||LA74_4==ExclamationMarkEqualsSign||(LA74_4>=LessThanSignEqualsSign && LA74_4<=GreaterThanSignEqualsSign)||LA74_4==IN||(LA74_4>=CircumflexAccentEqualsSign && LA74_4<=VerticalLineVerticalLine)||LA74_4==PlusSign||LA74_4==HyphenMinus||(LA74_4>=Solidus && LA74_4<=GreaterThanSign)||LA74_4==RULE_STAR) ) {
                    alt74=2;
                }
                else if ( (LA74_4==EOF||LA74_4==STRAIGHT_JOIN||LA74_4==FETCHFIRST||LA74_4==INTERSECT||LA74_4==GROUPBY||LA74_4==ORDERBY||LA74_4==NATURAL||LA74_4==EXCEPT||LA74_4==HAVING||LA74_4==OFFSET||LA74_4==CROSS||LA74_4==INNER||(LA74_4>=LIMIT && LA74_4<=MINUS)||(LA74_4>=RIGHT && LA74_4<=UNION)||LA74_4==WHERE||LA74_4==FULL||LA74_4==JOIN||LA74_4==LEFT||LA74_4==THEN||LA74_4==WHEN||LA74_4==AND||LA74_4==OR||LA74_4==RightParenthesis||LA74_4==Comma||LA74_4==RULE_JRNPARAM) ) {
                    alt74=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 74, 4, input);

                    throw nvae;
                }
                }
                break;
            case X:
                {
                alt74=3;
                }
                break;
            case NOTIN_1:
            case IN:
                {
                alt74=5;
                }
                break;
            case NOTEXISTS:
            case EXISTS:
                {
                alt74=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }

            switch (alt74) {
                case 1 :
                    // InternalSqlParser.g:3281:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    {
                    // InternalSqlParser.g:3281:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    // InternalSqlParser.g:3282:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    {
                    // InternalSqlParser.g:3282:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    // InternalSqlParser.g:3283:3: lv_expgroup_0_0= ruleExpressionGroup
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
                    // InternalSqlParser.g:3300:6: ( (lv_exp_1_0= ruleExpression ) )
                    {
                    // InternalSqlParser.g:3300:6: ( (lv_exp_1_0= ruleExpression ) )
                    // InternalSqlParser.g:3301:1: (lv_exp_1_0= ruleExpression )
                    {
                    // InternalSqlParser.g:3301:1: (lv_exp_1_0= ruleExpression )
                    // InternalSqlParser.g:3302:3: lv_exp_1_0= ruleExpression
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
                    // InternalSqlParser.g:3319:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    {
                    // InternalSqlParser.g:3319:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    // InternalSqlParser.g:3320:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    {
                    // InternalSqlParser.g:3320:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    // InternalSqlParser.g:3321:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    {
                    // InternalSqlParser.g:3321:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    int alt73=2;
                    alt73 = dfa73.predict(input);
                    switch (alt73) {
                        case 1 :
                            // InternalSqlParser.g:3322:3: lv_xexp_2_1= ruleXExpression
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
                            // InternalSqlParser.g:3337:8: lv_xexp_2_2= ruleXExpression_
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
                    // InternalSqlParser.g:3356:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    {
                    // InternalSqlParser.g:3356:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    // InternalSqlParser.g:3357:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    {
                    // InternalSqlParser.g:3357:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    // InternalSqlParser.g:3358:3: lv_notPrm_3_0= RULE_JRNPARAM
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
                    // InternalSqlParser.g:3375:6: ( (lv_in_4_0= ruleInOperator ) )
                    {
                    // InternalSqlParser.g:3375:6: ( (lv_in_4_0= ruleInOperator ) )
                    // InternalSqlParser.g:3376:1: (lv_in_4_0= ruleInOperator )
                    {
                    // InternalSqlParser.g:3376:1: (lv_in_4_0= ruleInOperator )
                    // InternalSqlParser.g:3377:3: lv_in_4_0= ruleInOperator
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
                    // InternalSqlParser.g:3394:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    {
                    // InternalSqlParser.g:3394:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    // InternalSqlParser.g:3395:1: (lv_exists_5_0= ruleExistsOperator )
                    {
                    // InternalSqlParser.g:3395:1: (lv_exists_5_0= ruleExistsOperator )
                    // InternalSqlParser.g:3396:3: lv_exists_5_0= ruleExistsOperator
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
    // InternalSqlParser.g:3420:1: entryRuleExpressionGroup returns [EObject current=null] : iv_ruleExpressionGroup= ruleExpressionGroup EOF ;
    public final EObject entryRuleExpressionGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionGroup = null;


        try {
            // InternalSqlParser.g:3421:2: (iv_ruleExpressionGroup= ruleExpressionGroup EOF )
            // InternalSqlParser.g:3422:2: iv_ruleExpressionGroup= ruleExpressionGroup EOF
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
    // InternalSqlParser.g:3429:1: ruleExpressionGroup returns [EObject current=null] : ( () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis ) ;
    public final EObject ruleExpressionGroup() throws RecognitionException {
        EObject current = null;

        Token lv_isnot_1_1=null;
        Token lv_isnot_1_2=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3432:28: ( ( () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis ) )
            // InternalSqlParser.g:3433:1: ( () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis )
            {
            // InternalSqlParser.g:3433:1: ( () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis )
            // InternalSqlParser.g:3433:2: () ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )? otherlv_2= LeftParenthesis ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= RightParenthesis
            {
            // InternalSqlParser.g:3433:2: ()
            // InternalSqlParser.g:3434:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpressionGroupAccess().getExprGroupAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:3439:2: ( ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( ((LA76_0>=NOT && LA76_0<=NOT_1)) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalSqlParser.g:3440:1: ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) )
                    {
                    // InternalSqlParser.g:3440:1: ( (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT ) )
                    // InternalSqlParser.g:3441:1: (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT )
                    {
                    // InternalSqlParser.g:3441:1: (lv_isnot_1_1= NOT_1 | lv_isnot_1_2= NOT )
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==NOT_1) ) {
                        alt75=1;
                    }
                    else if ( (LA75_0==NOT) ) {
                        alt75=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 75, 0, input);

                        throw nvae;
                    }
                    switch (alt75) {
                        case 1 :
                            // InternalSqlParser.g:3442:3: lv_isnot_1_1= NOT_1
                            {
                            lv_isnot_1_1=(Token)match(input,NOT_1,FOLLOW_33); 

                                    newLeafNode(lv_isnot_1_1, grammarAccess.getExpressionGroupAccess().getIsnotNOTKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionGroupRule());
                            	        }
                                   		setWithLastConsumed(current, "isnot", lv_isnot_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:3455:8: lv_isnot_1_2= NOT
                            {
                            lv_isnot_1_2=(Token)match(input,NOT,FOLLOW_33); 

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
                
            // InternalSqlParser.g:3476:1: ( (lv_expr_3_0= ruleFullExpression ) )
            // InternalSqlParser.g:3477:1: (lv_expr_3_0= ruleFullExpression )
            {
            // InternalSqlParser.g:3477:1: (lv_expr_3_0= ruleFullExpression )
            // InternalSqlParser.g:3478:3: lv_expr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:3507:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // InternalSqlParser.g:3508:2: (iv_ruleXExpression= ruleXExpression EOF )
            // InternalSqlParser.g:3509:2: iv_ruleXExpression= ruleXExpression EOF
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
    // InternalSqlParser.g:3516:1: ruleXExpression returns [EObject current=null] : (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket ) ;
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
            // InternalSqlParser.g:3519:28: ( (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket ) )
            // InternalSqlParser.g:3520:1: (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket )
            {
            // InternalSqlParser.g:3520:1: (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket )
            // InternalSqlParser.g:3521:2: otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= Comma ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,X,FOLLOW_53); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpressionAccess().getXKeyword_0());
                
            // InternalSqlParser.g:3525:1: ()
            // InternalSqlParser.g:3526:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpressionAccess().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_54); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpressionAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalSqlParser.g:3536:1: ( (lv_xf_3_0= ruleXFunction ) )
            // InternalSqlParser.g:3537:1: (lv_xf_3_0= ruleXFunction )
            {
            // InternalSqlParser.g:3537:1: (lv_xf_3_0= ruleXFunction )
            // InternalSqlParser.g:3538:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_55);
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

            otherlv_4=(Token)match(input,Comma,FOLLOW_56); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpressionAccess().getCommaKeyword_4());
                
            // InternalSqlParser.g:3559:1: ( (lv_col_5_0= ruleOperandGroup ) )
            // InternalSqlParser.g:3560:1: (lv_col_5_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:3560:1: (lv_col_5_0= ruleOperandGroup )
            // InternalSqlParser.g:3561:3: lv_col_5_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getColOperandGroupParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_57);
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

            // InternalSqlParser.g:3577:2: (otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==Comma) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // InternalSqlParser.g:3578:2: otherlv_6= Comma ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,Comma,FOLLOW_41); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpressionAccess().getCommaKeyword_6_0());
                        
                    // InternalSqlParser.g:3582:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // InternalSqlParser.g:3583:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // InternalSqlParser.g:3583:1: (lv_prm_7_0= ruleXExpressionParams )
                    // InternalSqlParser.g:3584:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_58);
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
    // InternalSqlParser.g:3613:1: entryRuleXExpression_ returns [EObject current=null] : iv_ruleXExpression_= ruleXExpression_ EOF ;
    public final EObject entryRuleXExpression_() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression_ = null;


        try {
            // InternalSqlParser.g:3614:2: (iv_ruleXExpression_= ruleXExpression_ EOF )
            // InternalSqlParser.g:3615:2: iv_ruleXExpression_= ruleXExpression_ EOF
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
    // InternalSqlParser.g:3622:1: ruleXExpression_ returns [EObject current=null] : (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket ) ;
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
            // InternalSqlParser.g:3625:28: ( (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket ) )
            // InternalSqlParser.g:3626:1: (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket )
            {
            // InternalSqlParser.g:3626:1: (otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket )
            // InternalSqlParser.g:3627:2: otherlv_0= X () otherlv_2= LeftCurlyBracket ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= VerticalLine ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,X,FOLLOW_53); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpression_Access().getXKeyword_0());
                
            // InternalSqlParser.g:3631:1: ()
            // InternalSqlParser.g:3632:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpression_Access().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_54); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpression_Access().getLeftCurlyBracketKeyword_2());
                
            // InternalSqlParser.g:3642:1: ( (lv_xf_3_0= ruleXFunction ) )
            // InternalSqlParser.g:3643:1: (lv_xf_3_0= ruleXFunction )
            {
            // InternalSqlParser.g:3643:1: (lv_xf_3_0= ruleXFunction )
            // InternalSqlParser.g:3644:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_59);
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

            otherlv_4=(Token)match(input,VerticalLine,FOLLOW_56); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpression_Access().getVerticalLineKeyword_4());
                
            // InternalSqlParser.g:3665:1: ( (lv_col_5_0= ruleOperandGroup ) )
            // InternalSqlParser.g:3666:1: (lv_col_5_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:3666:1: (lv_col_5_0= ruleOperandGroup )
            // InternalSqlParser.g:3667:3: lv_col_5_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getColOperandGroupParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_60);
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

            // InternalSqlParser.g:3683:2: (otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==VerticalLine) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalSqlParser.g:3684:2: otherlv_6= VerticalLine ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,VerticalLine,FOLLOW_41); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpression_Access().getVerticalLineKeyword_6_0());
                        
                    // InternalSqlParser.g:3688:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // InternalSqlParser.g:3689:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // InternalSqlParser.g:3689:1: (lv_prm_7_0= ruleXExpressionParams )
                    // InternalSqlParser.g:3690:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpression_Access().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_58);
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
    // InternalSqlParser.g:3719:1: entryRuleXExpressionParams returns [EObject current=null] : iv_ruleXExpressionParams= ruleXExpressionParams EOF ;
    public final EObject entryRuleXExpressionParams() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionParams = null;


        try {
            // InternalSqlParser.g:3720:2: (iv_ruleXExpressionParams= ruleXExpressionParams EOF )
            // InternalSqlParser.g:3721:2: iv_ruleXExpressionParams= ruleXExpressionParams EOF
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
    // InternalSqlParser.g:3728:1: ruleXExpressionParams returns [EObject current=null] : (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) ;
    public final EObject ruleXExpressionParams() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_JRParameter_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:3731:28: ( (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) )
            // InternalSqlParser.g:3732:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            {
            // InternalSqlParser.g:3732:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            // InternalSqlParser.g:3733:5: this_JRParameter_0= ruleJRParameter ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_JRParameter_0=ruleJRParameter();

            state._fsp--;


                    current = this_JRParameter_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:3741:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==Comma) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // InternalSqlParser.g:3741:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    {
                    // InternalSqlParser.g:3741:2: ()
                    // InternalSqlParser.g:3742:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:3747:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    int cnt79=0;
                    loop79:
                    do {
                        int alt79=2;
                        int LA79_0 = input.LA(1);

                        if ( (LA79_0==Comma) ) {
                            alt79=1;
                        }


                        switch (alt79) {
                    	case 1 :
                    	    // InternalSqlParser.g:3748:2: otherlv_2= Comma ( (lv_entries_3_0= ruleJRParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_41); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:3752:1: ( (lv_entries_3_0= ruleJRParameter ) )
                    	    // InternalSqlParser.g:3753:1: (lv_entries_3_0= ruleJRParameter )
                    	    {
                    	    // InternalSqlParser.g:3753:1: (lv_entries_3_0= ruleJRParameter )
                    	    // InternalSqlParser.g:3754:3: lv_entries_3_0= ruleJRParameter
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
                    	    if ( cnt79 >= 1 ) break loop79;
                                EarlyExitException eee =
                                    new EarlyExitException(79, input);
                                throw eee;
                        }
                        cnt79++;
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
    // InternalSqlParser.g:3778:1: entryRuleJRParameter returns [EObject current=null] : iv_ruleJRParameter= ruleJRParameter EOF ;
    public final EObject entryRuleJRParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameter = null;


        try {
            // InternalSqlParser.g:3779:2: (iv_ruleJRParameter= ruleJRParameter EOF )
            // InternalSqlParser.g:3780:2: iv_ruleJRParameter= ruleJRParameter EOF
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
    // InternalSqlParser.g:3787:1: ruleJRParameter returns [EObject current=null] : ( (lv_jrprm_0_0= RULE_ID ) ) ;
    public final EObject ruleJRParameter() throws RecognitionException {
        EObject current = null;

        Token lv_jrprm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:3790:28: ( ( (lv_jrprm_0_0= RULE_ID ) ) )
            // InternalSqlParser.g:3791:1: ( (lv_jrprm_0_0= RULE_ID ) )
            {
            // InternalSqlParser.g:3791:1: ( (lv_jrprm_0_0= RULE_ID ) )
            // InternalSqlParser.g:3792:1: (lv_jrprm_0_0= RULE_ID )
            {
            // InternalSqlParser.g:3792:1: (lv_jrprm_0_0= RULE_ID )
            // InternalSqlParser.g:3793:3: lv_jrprm_0_0= RULE_ID
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
    // InternalSqlParser.g:3817:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalSqlParser.g:3818:2: (iv_ruleExpression= ruleExpression EOF )
            // InternalSqlParser.g:3819:2: iv_ruleExpression= ruleExpression EOF
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
    // InternalSqlParser.g:3826:1: ruleExpression returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) ;
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
            // InternalSqlParser.g:3829:28: ( ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) )
            // InternalSqlParser.g:3830:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            {
            // InternalSqlParser.g:3830:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            // InternalSqlParser.g:3830:2: ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            {
            // InternalSqlParser.g:3830:2: ( (lv_op1_0_0= ruleOperand ) )
            // InternalSqlParser.g:3831:1: (lv_op1_0_0= ruleOperand )
            {
            // InternalSqlParser.g:3831:1: (lv_op1_0_0= ruleOperand )
            // InternalSqlParser.g:3832:3: lv_op1_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_61);
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

            // InternalSqlParser.g:3848:2: ( ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            int alt82=6;
            switch ( input.LA(1) ) {
            case ISNOTNULL:
            case ISNULL:
                {
                alt82=1;
                }
                break;
            case NOTIN_1:
            case IN:
                {
                alt82=2;
                }
                break;
            case NOTEXISTS:
            case EXISTS:
                {
                alt82=3;
                }
                break;
            case NOTBETWEEN:
            case BETWEEN:
                {
                alt82=4;
                }
                break;
            case NOTLIKE:
            case LIKE:
                {
                alt82=5;
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
                alt82=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }

            switch (alt82) {
                case 1 :
                    // InternalSqlParser.g:3848:3: ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) )
                    {
                    // InternalSqlParser.g:3848:3: ( ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) ) )
                    // InternalSqlParser.g:3849:1: ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) )
                    {
                    // InternalSqlParser.g:3849:1: ( (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL ) )
                    // InternalSqlParser.g:3850:1: (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL )
                    {
                    // InternalSqlParser.g:3850:1: (lv_isnull_1_1= ISNULL | lv_isnull_1_2= ISNOTNULL )
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==ISNULL) ) {
                        alt81=1;
                    }
                    else if ( (LA81_0==ISNOTNULL) ) {
                        alt81=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 81, 0, input);

                        throw nvae;
                    }
                    switch (alt81) {
                        case 1 :
                            // InternalSqlParser.g:3851:3: lv_isnull_1_1= ISNULL
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
                            // InternalSqlParser.g:3864:8: lv_isnull_1_2= ISNOTNULL
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
                    // InternalSqlParser.g:3881:6: ( (lv_in_2_0= ruleInOperator ) )
                    {
                    // InternalSqlParser.g:3881:6: ( (lv_in_2_0= ruleInOperator ) )
                    // InternalSqlParser.g:3882:1: (lv_in_2_0= ruleInOperator )
                    {
                    // InternalSqlParser.g:3882:1: (lv_in_2_0= ruleInOperator )
                    // InternalSqlParser.g:3883:3: lv_in_2_0= ruleInOperator
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
                    // InternalSqlParser.g:3900:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    {
                    // InternalSqlParser.g:3900:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    // InternalSqlParser.g:3901:1: (lv_exists_3_0= ruleExistsOperator )
                    {
                    // InternalSqlParser.g:3901:1: (lv_exists_3_0= ruleExistsOperator )
                    // InternalSqlParser.g:3902:3: lv_exists_3_0= ruleExistsOperator
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
                    // InternalSqlParser.g:3919:6: ( (lv_between_4_0= ruleBetween ) )
                    {
                    // InternalSqlParser.g:3919:6: ( (lv_between_4_0= ruleBetween ) )
                    // InternalSqlParser.g:3920:1: (lv_between_4_0= ruleBetween )
                    {
                    // InternalSqlParser.g:3920:1: (lv_between_4_0= ruleBetween )
                    // InternalSqlParser.g:3921:3: lv_between_4_0= ruleBetween
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
                    // InternalSqlParser.g:3938:6: ( (lv_like_5_0= ruleLike ) )
                    {
                    // InternalSqlParser.g:3938:6: ( (lv_like_5_0= ruleLike ) )
                    // InternalSqlParser.g:3939:1: (lv_like_5_0= ruleLike )
                    {
                    // InternalSqlParser.g:3939:1: (lv_like_5_0= ruleLike )
                    // InternalSqlParser.g:3940:3: lv_like_5_0= ruleLike
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
                    // InternalSqlParser.g:3957:6: ( (lv_comp_6_0= ruleComparison ) )
                    {
                    // InternalSqlParser.g:3957:6: ( (lv_comp_6_0= ruleComparison ) )
                    // InternalSqlParser.g:3958:1: (lv_comp_6_0= ruleComparison )
                    {
                    // InternalSqlParser.g:3958:1: (lv_comp_6_0= ruleComparison )
                    // InternalSqlParser.g:3959:3: lv_comp_6_0= ruleComparison
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
    // InternalSqlParser.g:3983:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // InternalSqlParser.g:3984:2: (iv_ruleComparison= ruleComparison EOF )
            // InternalSqlParser.g:3985:2: iv_ruleComparison= ruleComparison EOF
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
    // InternalSqlParser.g:3992:1: ruleComparison returns [EObject current=null] : ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) ;
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
            // InternalSqlParser.g:3995:28: ( ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) )
            // InternalSqlParser.g:3996:1: ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            {
            // InternalSqlParser.g:3996:1: ( ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            // InternalSqlParser.g:3996:2: ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) ) ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )? ( (lv_op2_2_0= ruleOperand ) )
            {
            // InternalSqlParser.g:3996:2: ( ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) ) )
            // InternalSqlParser.g:3997:1: ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) )
            {
            // InternalSqlParser.g:3997:1: ( (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign ) )
            // InternalSqlParser.g:3998:1: (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign )
            {
            // InternalSqlParser.g:3998:1: (lv_operator_0_1= GreaterThanSign | lv_operator_0_2= GreaterThanSignEqualsSign | lv_operator_0_3= LessThanSign | lv_operator_0_4= LessThanSignEqualsSign | lv_operator_0_5= EqualsSign | lv_operator_0_6= LessThanSignGreaterThanSign | lv_operator_0_7= ExclamationMarkEqualsSign | lv_operator_0_8= CircumflexAccentEqualsSign )
            int alt83=8;
            switch ( input.LA(1) ) {
            case GreaterThanSign:
                {
                alt83=1;
                }
                break;
            case GreaterThanSignEqualsSign:
                {
                alt83=2;
                }
                break;
            case LessThanSign:
                {
                alt83=3;
                }
                break;
            case LessThanSignEqualsSign:
                {
                alt83=4;
                }
                break;
            case EqualsSign:
                {
                alt83=5;
                }
                break;
            case LessThanSignGreaterThanSign:
                {
                alt83=6;
                }
                break;
            case ExclamationMarkEqualsSign:
                {
                alt83=7;
                }
                break;
            case CircumflexAccentEqualsSign:
                {
                alt83=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }

            switch (alt83) {
                case 1 :
                    // InternalSqlParser.g:3999:3: lv_operator_0_1= GreaterThanSign
                    {
                    lv_operator_0_1=(Token)match(input,GreaterThanSign,FOLLOW_62); 

                            newLeafNode(lv_operator_0_1, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4012:8: lv_operator_0_2= GreaterThanSignEqualsSign
                    {
                    lv_operator_0_2=(Token)match(input,GreaterThanSignEqualsSign,FOLLOW_62); 

                            newLeafNode(lv_operator_0_2, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:4025:8: lv_operator_0_3= LessThanSign
                    {
                    lv_operator_0_3=(Token)match(input,LessThanSign,FOLLOW_62); 

                            newLeafNode(lv_operator_0_3, grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:4038:8: lv_operator_0_4= LessThanSignEqualsSign
                    {
                    lv_operator_0_4=(Token)match(input,LessThanSignEqualsSign,FOLLOW_62); 

                            newLeafNode(lv_operator_0_4, grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_4, null);
                    	    

                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:4051:8: lv_operator_0_5= EqualsSign
                    {
                    lv_operator_0_5=(Token)match(input,EqualsSign,FOLLOW_62); 

                            newLeafNode(lv_operator_0_5, grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_5, null);
                    	    

                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:4064:8: lv_operator_0_6= LessThanSignGreaterThanSign
                    {
                    lv_operator_0_6=(Token)match(input,LessThanSignGreaterThanSign,FOLLOW_62); 

                            newLeafNode(lv_operator_0_6, grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_6, null);
                    	    

                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:4077:8: lv_operator_0_7= ExclamationMarkEqualsSign
                    {
                    lv_operator_0_7=(Token)match(input,ExclamationMarkEqualsSign,FOLLOW_62); 

                            newLeafNode(lv_operator_0_7, grammarAccess.getComparisonAccess().getOperatorExclamationMarkEqualsSignKeyword_0_0_6());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_7, null);
                    	    

                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:4090:8: lv_operator_0_8= CircumflexAccentEqualsSign
                    {
                    lv_operator_0_8=(Token)match(input,CircumflexAccentEqualsSign,FOLLOW_62); 

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

            // InternalSqlParser.g:4106:2: ( ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) ) )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==SOME||LA85_0==ALL||LA85_0==ANY) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalSqlParser.g:4107:1: ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) )
                    {
                    // InternalSqlParser.g:4107:1: ( (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME ) )
                    // InternalSqlParser.g:4108:1: (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME )
                    {
                    // InternalSqlParser.g:4108:1: (lv_subOperator_1_1= ANY | lv_subOperator_1_2= ALL | lv_subOperator_1_3= SOME )
                    int alt84=3;
                    switch ( input.LA(1) ) {
                    case ANY:
                        {
                        alt84=1;
                        }
                        break;
                    case ALL:
                        {
                        alt84=2;
                        }
                        break;
                    case SOME:
                        {
                        alt84=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 84, 0, input);

                        throw nvae;
                    }

                    switch (alt84) {
                        case 1 :
                            // InternalSqlParser.g:4109:3: lv_subOperator_1_1= ANY
                            {
                            lv_subOperator_1_1=(Token)match(input,ANY,FOLLOW_56); 

                                    newLeafNode(lv_subOperator_1_1, grammarAccess.getComparisonAccess().getSubOperatorANYKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:4122:8: lv_subOperator_1_2= ALL
                            {
                            lv_subOperator_1_2=(Token)match(input,ALL,FOLLOW_56); 

                                    newLeafNode(lv_subOperator_1_2, grammarAccess.getComparisonAccess().getSubOperatorALLKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // InternalSqlParser.g:4135:8: lv_subOperator_1_3= SOME
                            {
                            lv_subOperator_1_3=(Token)match(input,SOME,FOLLOW_56); 

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

            // InternalSqlParser.g:4151:3: ( (lv_op2_2_0= ruleOperand ) )
            // InternalSqlParser.g:4152:1: (lv_op2_2_0= ruleOperand )
            {
            // InternalSqlParser.g:4152:1: (lv_op2_2_0= ruleOperand )
            // InternalSqlParser.g:4153:3: lv_op2_2_0= ruleOperand
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
    // InternalSqlParser.g:4177:1: entryRuleLike returns [EObject current=null] : iv_ruleLike= ruleLike EOF ;
    public final EObject entryRuleLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLike = null;


        try {
            // InternalSqlParser.g:4178:2: (iv_ruleLike= ruleLike EOF )
            // InternalSqlParser.g:4179:2: iv_ruleLike= ruleLike EOF
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
    // InternalSqlParser.g:4186:1: ruleLike returns [EObject current=null] : ( ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) ;
    public final EObject ruleLike() throws RecognitionException {
        EObject current = null;

        Token lv_opLike_0_1=null;
        Token lv_opLike_0_2=null;
        EObject lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4189:28: ( ( ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) )
            // InternalSqlParser.g:4190:1: ( ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            {
            // InternalSqlParser.g:4190:1: ( ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            // InternalSqlParser.g:4190:2: ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) )
            {
            // InternalSqlParser.g:4190:2: ( ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) ) )
            // InternalSqlParser.g:4191:1: ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) )
            {
            // InternalSqlParser.g:4191:1: ( (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE ) )
            // InternalSqlParser.g:4192:1: (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE )
            {
            // InternalSqlParser.g:4192:1: (lv_opLike_0_1= LIKE | lv_opLike_0_2= NOTLIKE )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==LIKE) ) {
                alt86=1;
            }
            else if ( (LA86_0==NOTLIKE) ) {
                alt86=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // InternalSqlParser.g:4193:3: lv_opLike_0_1= LIKE
                    {
                    lv_opLike_0_1=(Token)match(input,LIKE,FOLLOW_63); 

                            newLeafNode(lv_opLike_0_1, grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLikeRule());
                    	        }
                           		setWithLastConsumed(current, "opLike", lv_opLike_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4206:8: lv_opLike_0_2= NOTLIKE
                    {
                    lv_opLike_0_2=(Token)match(input,NOTLIKE,FOLLOW_63); 

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

            // InternalSqlParser.g:4222:2: ( (lv_op2_1_0= ruleLikeOperand ) )
            // InternalSqlParser.g:4223:1: (lv_op2_1_0= ruleLikeOperand )
            {
            // InternalSqlParser.g:4223:1: (lv_op2_1_0= ruleLikeOperand )
            // InternalSqlParser.g:4224:3: lv_op2_1_0= ruleLikeOperand
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
    // InternalSqlParser.g:4248:1: entryRuleLikeOperand returns [EObject current=null] : iv_ruleLikeOperand= ruleLikeOperand EOF ;
    public final EObject entryRuleLikeOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLikeOperand = null;


        try {
            // InternalSqlParser.g:4249:2: (iv_ruleLikeOperand= ruleLikeOperand EOF )
            // InternalSqlParser.g:4250:2: iv_ruleLikeOperand= ruleLikeOperand EOF
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
    // InternalSqlParser.g:4257:1: ruleLikeOperand returns [EObject current=null] : ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) ;
    public final EObject ruleLikeOperand() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op2_0_0 = null;

        EObject lv_fop2_1_0 = null;

        EObject lv_fcast_2_0 = null;

        EObject lv_fparam_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4260:28: ( ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) )
            // InternalSqlParser.g:4261:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            {
            // InternalSqlParser.g:4261:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            int alt87=4;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt87=1;
                }
                break;
            case RULE_ID:
                {
                alt87=2;
                }
                break;
            case CAST:
                {
                alt87=3;
                }
                break;
            case RULE_JRPARAM:
                {
                alt87=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }

            switch (alt87) {
                case 1 :
                    // InternalSqlParser.g:4261:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:4261:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    // InternalSqlParser.g:4262:1: (lv_op2_0_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:4262:1: (lv_op2_0_0= ruleStringOperand )
                    // InternalSqlParser.g:4263:3: lv_op2_0_0= ruleStringOperand
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
                    // InternalSqlParser.g:4280:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:4280:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:4281:1: (lv_fop2_1_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:4281:1: (lv_fop2_1_0= ruleOperandFunction )
                    // InternalSqlParser.g:4282:3: lv_fop2_1_0= ruleOperandFunction
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
                    // InternalSqlParser.g:4299:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    {
                    // InternalSqlParser.g:4299:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    // InternalSqlParser.g:4300:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    {
                    // InternalSqlParser.g:4300:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    // InternalSqlParser.g:4301:3: lv_fcast_2_0= ruleOpFunctionCast
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
                    // InternalSqlParser.g:4318:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    {
                    // InternalSqlParser.g:4318:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    // InternalSqlParser.g:4319:1: (lv_fparam_3_0= ruleParameterOperand )
                    {
                    // InternalSqlParser.g:4319:1: (lv_fparam_3_0= ruleParameterOperand )
                    // InternalSqlParser.g:4320:3: lv_fparam_3_0= ruleParameterOperand
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
    // InternalSqlParser.g:4344:1: entryRuleBetween returns [EObject current=null] : iv_ruleBetween= ruleBetween EOF ;
    public final EObject entryRuleBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBetween = null;


        try {
            // InternalSqlParser.g:4345:2: (iv_ruleBetween= ruleBetween EOF )
            // InternalSqlParser.g:4346:2: iv_ruleBetween= ruleBetween EOF
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
    // InternalSqlParser.g:4353:1: ruleBetween returns [EObject current=null] : ( ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) ) ;
    public final EObject ruleBetween() throws RecognitionException {
        EObject current = null;

        Token lv_opBetween_0_1=null;
        Token lv_opBetween_0_2=null;
        Token otherlv_2=null;
        EObject lv_op2_1_0 = null;

        EObject lv_op3_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4356:28: ( ( ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) ) )
            // InternalSqlParser.g:4357:1: ( ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) )
            {
            // InternalSqlParser.g:4357:1: ( ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) ) )
            // InternalSqlParser.g:4357:2: ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= AND ( (lv_op3_3_0= ruleOperandGroup ) )
            {
            // InternalSqlParser.g:4357:2: ( ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) ) )
            // InternalSqlParser.g:4358:1: ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) )
            {
            // InternalSqlParser.g:4358:1: ( (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN ) )
            // InternalSqlParser.g:4359:1: (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN )
            {
            // InternalSqlParser.g:4359:1: (lv_opBetween_0_1= BETWEEN | lv_opBetween_0_2= NOTBETWEEN )
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==BETWEEN) ) {
                alt88=1;
            }
            else if ( (LA88_0==NOTBETWEEN) ) {
                alt88=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;
            }
            switch (alt88) {
                case 1 :
                    // InternalSqlParser.g:4360:3: lv_opBetween_0_1= BETWEEN
                    {
                    lv_opBetween_0_1=(Token)match(input,BETWEEN,FOLLOW_56); 

                            newLeafNode(lv_opBetween_0_1, grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBetweenRule());
                    	        }
                           		setWithLastConsumed(current, "opBetween", lv_opBetween_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4373:8: lv_opBetween_0_2= NOTBETWEEN
                    {
                    lv_opBetween_0_2=(Token)match(input,NOTBETWEEN,FOLLOW_56); 

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

            // InternalSqlParser.g:4389:2: ( (lv_op2_1_0= ruleOperandGroup ) )
            // InternalSqlParser.g:4390:1: (lv_op2_1_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:4390:1: (lv_op2_1_0= ruleOperandGroup )
            // InternalSqlParser.g:4391:3: lv_op2_1_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp2OperandGroupParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_64);
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

            otherlv_2=(Token)match(input,AND,FOLLOW_56); 

                	newLeafNode(otherlv_2, grammarAccess.getBetweenAccess().getANDKeyword_2());
                
            // InternalSqlParser.g:4412:1: ( (lv_op3_3_0= ruleOperandGroup ) )
            // InternalSqlParser.g:4413:1: (lv_op3_3_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:4413:1: (lv_op3_3_0= ruleOperandGroup )
            // InternalSqlParser.g:4414:3: lv_op3_3_0= ruleOperandGroup
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
    // InternalSqlParser.g:4438:1: entryRuleInOperator returns [EObject current=null] : iv_ruleInOperator= ruleInOperator EOF ;
    public final EObject entryRuleInOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOperator = null;


        try {
            // InternalSqlParser.g:4439:2: (iv_ruleInOperator= ruleInOperator EOF )
            // InternalSqlParser.g:4440:2: iv_ruleInOperator= ruleInOperator EOF
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
    // InternalSqlParser.g:4447:1: ruleInOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleInOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4450:28: ( ( () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // InternalSqlParser.g:4451:1: ( () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // InternalSqlParser.g:4451:1: ( () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // InternalSqlParser.g:4451:2: () ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // InternalSqlParser.g:4451:2: ()
            // InternalSqlParser.g:4452:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInOperatorAccess().getInOperAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:4457:2: ( ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) ) )
            // InternalSqlParser.g:4458:1: ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) )
            {
            // InternalSqlParser.g:4458:1: ( (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN ) )
            // InternalSqlParser.g:4459:1: (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN )
            {
            // InternalSqlParser.g:4459:1: (lv_op_1_1= NOTIN_1 | lv_op_1_2= IN )
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==NOTIN_1) ) {
                alt89=1;
            }
            else if ( (LA89_0==IN) ) {
                alt89=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }
            switch (alt89) {
                case 1 :
                    // InternalSqlParser.g:4460:3: lv_op_1_1= NOTIN_1
                    {
                    lv_op_1_1=(Token)match(input,NOTIN_1,FOLLOW_33); 

                            newLeafNode(lv_op_1_1, grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getInOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4473:8: lv_op_1_2= IN
                    {
                    lv_op_1_2=(Token)match(input,IN,FOLLOW_33); 

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

            // InternalSqlParser.g:4489:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==LeftParenthesis) ) {
                int LA90_1 = input.LA(2);

                if ( (LA90_1==SELECT) ) {
                    alt90=1;
                }
                else if ( ((LA90_1>=RULE_SIGNED_DOUBLE && LA90_1<=RULE_STRING_)) ) {
                    alt90=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 90, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;
            }
            switch (alt90) {
                case 1 :
                    // InternalSqlParser.g:4489:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:4489:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:4490:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:4490:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:4491:3: lv_subquery_2_0= ruleSubQueryOperand
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
                    // InternalSqlParser.g:4508:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // InternalSqlParser.g:4508:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // InternalSqlParser.g:4509:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // InternalSqlParser.g:4509:1: (lv_opList_3_0= ruleOperandListGroup )
                    // InternalSqlParser.g:4510:3: lv_opList_3_0= ruleOperandListGroup
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
    // InternalSqlParser.g:4534:1: entryRuleExistsOperator returns [EObject current=null] : iv_ruleExistsOperator= ruleExistsOperator EOF ;
    public final EObject entryRuleExistsOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistsOperator = null;


        try {
            // InternalSqlParser.g:4535:2: (iv_ruleExistsOperator= ruleExistsOperator EOF )
            // InternalSqlParser.g:4536:2: iv_ruleExistsOperator= ruleExistsOperator EOF
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
    // InternalSqlParser.g:4543:1: ruleExistsOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleExistsOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4546:28: ( ( () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // InternalSqlParser.g:4547:1: ( () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // InternalSqlParser.g:4547:1: ( () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // InternalSqlParser.g:4547:2: () ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // InternalSqlParser.g:4547:2: ()
            // InternalSqlParser.g:4548:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExistsOperatorAccess().getExistsOperAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:4553:2: ( ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) ) )
            // InternalSqlParser.g:4554:1: ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) )
            {
            // InternalSqlParser.g:4554:1: ( (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS ) )
            // InternalSqlParser.g:4555:1: (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS )
            {
            // InternalSqlParser.g:4555:1: (lv_op_1_1= NOTEXISTS | lv_op_1_2= EXISTS )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==NOTEXISTS) ) {
                alt91=1;
            }
            else if ( (LA91_0==EXISTS) ) {
                alt91=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // InternalSqlParser.g:4556:3: lv_op_1_1= NOTEXISTS
                    {
                    lv_op_1_1=(Token)match(input,NOTEXISTS,FOLLOW_33); 

                            newLeafNode(lv_op_1_1, grammarAccess.getExistsOperatorAccess().getOpNOTEXISTSKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExistsOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:4569:8: lv_op_1_2= EXISTS
                    {
                    lv_op_1_2=(Token)match(input,EXISTS,FOLLOW_33); 

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

            // InternalSqlParser.g:4585:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==LeftParenthesis) ) {
                int LA92_1 = input.LA(2);

                if ( ((LA92_1>=RULE_SIGNED_DOUBLE && LA92_1<=RULE_STRING_)) ) {
                    alt92=2;
                }
                else if ( (LA92_1==SELECT) ) {
                    alt92=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 92, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;
            }
            switch (alt92) {
                case 1 :
                    // InternalSqlParser.g:4585:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:4585:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:4586:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:4586:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:4587:3: lv_subquery_2_0= ruleSubQueryOperand
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
                    // InternalSqlParser.g:4604:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // InternalSqlParser.g:4604:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // InternalSqlParser.g:4605:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // InternalSqlParser.g:4605:1: (lv_opList_3_0= ruleOperandListGroup )
                    // InternalSqlParser.g:4606:3: lv_opList_3_0= ruleOperandListGroup
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
    // InternalSqlParser.g:4630:1: entryRuleOperandListGroup returns [EObject current=null] : iv_ruleOperandListGroup= ruleOperandListGroup EOF ;
    public final EObject entryRuleOperandListGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandListGroup = null;


        try {
            // InternalSqlParser.g:4631:2: (iv_ruleOperandListGroup= ruleOperandListGroup EOF )
            // InternalSqlParser.g:4632:2: iv_ruleOperandListGroup= ruleOperandListGroup EOF
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
    // InternalSqlParser.g:4639:1: ruleOperandListGroup returns [EObject current=null] : (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis ) ;
    public final EObject ruleOperandListGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_opGroup_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4642:28: ( (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis ) )
            // InternalSqlParser.g:4643:1: (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis )
            {
            // InternalSqlParser.g:4643:1: (otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis )
            // InternalSqlParser.g:4644:2: otherlv_0= LeftParenthesis ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= RightParenthesis
            {
            otherlv_0=(Token)match(input,LeftParenthesis,FOLLOW_65); 

                	newLeafNode(otherlv_0, grammarAccess.getOperandListGroupAccess().getLeftParenthesisKeyword_0());
                
            // InternalSqlParser.g:4648:1: ( (lv_opGroup_1_0= ruleOperandList ) )
            // InternalSqlParser.g:4649:1: (lv_opGroup_1_0= ruleOperandList )
            {
            // InternalSqlParser.g:4649:1: (lv_opGroup_1_0= ruleOperandList )
            // InternalSqlParser.g:4650:3: lv_opGroup_1_0= ruleOperandList
            {
             
            	        newCompositeNode(grammarAccess.getOperandListGroupAccess().getOpGroupOperandListParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:4679:1: entryRuleOperandList returns [EObject current=null] : iv_ruleOperandList= ruleOperandList EOF ;
    public final EObject entryRuleOperandList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandList = null;


        try {
            // InternalSqlParser.g:4680:2: (iv_ruleOperandList= ruleOperandList EOF )
            // InternalSqlParser.g:4681:2: iv_ruleOperandList= ruleOperandList EOF
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
    // InternalSqlParser.g:4688:1: ruleOperandList returns [EObject current=null] : (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) ;
    public final EObject ruleOperandList() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ScalarOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4691:28: ( (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) )
            // InternalSqlParser.g:4692:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            {
            // InternalSqlParser.g:4692:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            // InternalSqlParser.g:4693:5: this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOperandListAccess().getScalarOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_ScalarOperand_0=ruleScalarOperand();

            state._fsp--;


                    current = this_ScalarOperand_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:4701:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==Comma) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalSqlParser.g:4701:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    {
                    // InternalSqlParser.g:4701:2: ()
                    // InternalSqlParser.g:4702:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:4707:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    int cnt93=0;
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==Comma) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // InternalSqlParser.g:4708:2: otherlv_2= Comma ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_65); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:4712:1: ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    // InternalSqlParser.g:4713:1: (lv_entries_3_0= ruleScalarOperand )
                    	    {
                    	    // InternalSqlParser.g:4713:1: (lv_entries_3_0= ruleScalarOperand )
                    	    // InternalSqlParser.g:4714:3: lv_entries_3_0= ruleScalarOperand
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
                    	    if ( cnt93 >= 1 ) break loop93;
                                EarlyExitException eee =
                                    new EarlyExitException(93, input);
                                throw eee;
                        }
                        cnt93++;
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
    // InternalSqlParser.g:4738:1: entryRuleOperandGroup returns [EObject current=null] : iv_ruleOperandGroup= ruleOperandGroup EOF ;
    public final EObject entryRuleOperandGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandGroup = null;


        try {
            // InternalSqlParser.g:4739:2: (iv_ruleOperandGroup= ruleOperandGroup EOF )
            // InternalSqlParser.g:4740:2: iv_ruleOperandGroup= ruleOperandGroup EOF
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
    // InternalSqlParser.g:4747:1: ruleOperandGroup returns [EObject current=null] : (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) ) ;
    public final EObject ruleOperandGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_Operand_0 = null;

        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:4750:28: ( (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) ) )
            // InternalSqlParser.g:4751:1: (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) )
            {
            // InternalSqlParser.g:4751:1: (this_Operand_0= ruleOperand | (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis ) )
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==EXTRACT||LA95_0==CAST||LA95_0==CASE||(LA95_0>=RULE_JRPARAM && LA95_0<=RULE_JRNPARAM)||(LA95_0>=RULE_UNSIGNED && LA95_0<=RULE_SIGNED_DOUBLE)||(LA95_0>=RULE_STRING_ && LA95_0<=RULE_ID)) ) {
                alt95=1;
            }
            else if ( (LA95_0==LeftParenthesis) ) {
                int LA95_2 = input.LA(2);

                if ( (LA95_2==SELECT) ) {
                    alt95=1;
                }
                else if ( (LA95_2==EXTRACT||LA95_2==CAST||LA95_2==CASE||LA95_2==LeftParenthesis||(LA95_2>=RULE_JRPARAM && LA95_2<=RULE_JRNPARAM)||(LA95_2>=RULE_UNSIGNED && LA95_2<=RULE_SIGNED_DOUBLE)||(LA95_2>=RULE_STRING_ && LA95_2<=RULE_ID)) ) {
                    alt95=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 95, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // InternalSqlParser.g:4752:5: this_Operand_0= ruleOperand
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
                    // InternalSqlParser.g:4761:6: (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis )
                    {
                    // InternalSqlParser.g:4761:6: (otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis )
                    // InternalSqlParser.g:4762:2: otherlv_1= LeftParenthesis this_Operand_2= ruleOperand otherlv_3= RightParenthesis
                    {
                    otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_56); 

                        	newLeafNode(otherlv_1, grammarAccess.getOperandGroupAccess().getLeftParenthesisKeyword_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getOperandGroupAccess().getOperandParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:4788:1: entryRuleOperand returns [EObject current=null] : iv_ruleOperand= ruleOperand EOF ;
    public final EObject entryRuleOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperand = null;


        try {
            // InternalSqlParser.g:4789:2: (iv_ruleOperand= ruleOperand EOF )
            // InternalSqlParser.g:4790:2: iv_ruleOperand= ruleOperand EOF
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
    // InternalSqlParser.g:4797:1: ruleOperand returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) ;
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
            // InternalSqlParser.g:4800:28: ( ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) )
            // InternalSqlParser.g:4801:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            {
            // InternalSqlParser.g:4801:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            // InternalSqlParser.g:4801:2: ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            {
            // InternalSqlParser.g:4801:2: ( (lv_op1_0_0= ruleOperandFragment ) )
            // InternalSqlParser.g:4802:1: (lv_op1_0_0= ruleOperandFragment )
            {
            // InternalSqlParser.g:4802:1: (lv_op1_0_0= ruleOperandFragment )
            // InternalSqlParser.g:4803:3: lv_op1_0_0= ruleOperandFragment
            {
             
            	        newCompositeNode(grammarAccess.getOperandAccess().getOp1OperandFragmentParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_66);
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

            // InternalSqlParser.g:4819:2: ( ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==VerticalLineVerticalLine||LA97_0==PlusSign||LA97_0==HyphenMinus||LA97_0==Solidus||LA97_0==RULE_STAR) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // InternalSqlParser.g:4819:3: ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) ) ( (lv_right_11_0= ruleOperandFragment ) )
            	    {
            	    // InternalSqlParser.g:4819:3: ( ( () otherlv_2= PlusSign ) | ( () otherlv_4= HyphenMinus ) | ( () otherlv_6= VerticalLineVerticalLine ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= Solidus ) )
            	    int alt96=5;
            	    switch ( input.LA(1) ) {
            	    case PlusSign:
            	        {
            	        alt96=1;
            	        }
            	        break;
            	    case HyphenMinus:
            	        {
            	        alt96=2;
            	        }
            	        break;
            	    case VerticalLineVerticalLine:
            	        {
            	        alt96=3;
            	        }
            	        break;
            	    case RULE_STAR:
            	        {
            	        alt96=4;
            	        }
            	        break;
            	    case Solidus:
            	        {
            	        alt96=5;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 96, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt96) {
            	        case 1 :
            	            // InternalSqlParser.g:4819:4: ( () otherlv_2= PlusSign )
            	            {
            	            // InternalSqlParser.g:4819:4: ( () otherlv_2= PlusSign )
            	            // InternalSqlParser.g:4819:5: () otherlv_2= PlusSign
            	            {
            	            // InternalSqlParser.g:4819:5: ()
            	            // InternalSqlParser.g:4820:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getPlusLeftAction_1_0_0_0(),
            	                        current);
            	                

            	            }

            	            otherlv_2=(Token)match(input,PlusSign,FOLLOW_56); 

            	                	newLeafNode(otherlv_2, grammarAccess.getOperandAccess().getPlusSignKeyword_1_0_0_1());
            	                

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalSqlParser.g:4831:6: ( () otherlv_4= HyphenMinus )
            	            {
            	            // InternalSqlParser.g:4831:6: ( () otherlv_4= HyphenMinus )
            	            // InternalSqlParser.g:4831:7: () otherlv_4= HyphenMinus
            	            {
            	            // InternalSqlParser.g:4831:7: ()
            	            // InternalSqlParser.g:4832:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMinusLeftAction_1_0_1_0(),
            	                        current);
            	                

            	            }

            	            otherlv_4=(Token)match(input,HyphenMinus,FOLLOW_56); 

            	                	newLeafNode(otherlv_4, grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_0_1_1());
            	                

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // InternalSqlParser.g:4843:6: ( () otherlv_6= VerticalLineVerticalLine )
            	            {
            	            // InternalSqlParser.g:4843:6: ( () otherlv_6= VerticalLineVerticalLine )
            	            // InternalSqlParser.g:4843:7: () otherlv_6= VerticalLineVerticalLine
            	            {
            	            // InternalSqlParser.g:4843:7: ()
            	            // InternalSqlParser.g:4844:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getConcatLeftAction_1_0_2_0(),
            	                        current);
            	                

            	            }

            	            otherlv_6=(Token)match(input,VerticalLineVerticalLine,FOLLOW_56); 

            	                	newLeafNode(otherlv_6, grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_0_2_1());
            	                

            	            }


            	            }
            	            break;
            	        case 4 :
            	            // InternalSqlParser.g:4855:6: ( () this_STAR_8= RULE_STAR )
            	            {
            	            // InternalSqlParser.g:4855:6: ( () this_STAR_8= RULE_STAR )
            	            // InternalSqlParser.g:4855:7: () this_STAR_8= RULE_STAR
            	            {
            	            // InternalSqlParser.g:4855:7: ()
            	            // InternalSqlParser.g:4856:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMultiplyLeftAction_1_0_3_0(),
            	                        current);
            	                

            	            }

            	            this_STAR_8=(Token)match(input,RULE_STAR,FOLLOW_56); 
            	             
            	                newLeafNode(this_STAR_8, grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_0_3_1()); 
            	                

            	            }


            	            }
            	            break;
            	        case 5 :
            	            // InternalSqlParser.g:4866:6: ( () otherlv_10= Solidus )
            	            {
            	            // InternalSqlParser.g:4866:6: ( () otherlv_10= Solidus )
            	            // InternalSqlParser.g:4866:7: () otherlv_10= Solidus
            	            {
            	            // InternalSqlParser.g:4866:7: ()
            	            // InternalSqlParser.g:4867:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getDivisionLeftAction_1_0_4_0(),
            	                        current);
            	                

            	            }

            	            otherlv_10=(Token)match(input,Solidus,FOLLOW_56); 

            	                	newLeafNode(otherlv_10, grammarAccess.getOperandAccess().getSolidusKeyword_1_0_4_1());
            	                

            	            }


            	            }
            	            break;

            	    }

            	    // InternalSqlParser.g:4877:3: ( (lv_right_11_0= ruleOperandFragment ) )
            	    // InternalSqlParser.g:4878:1: (lv_right_11_0= ruleOperandFragment )
            	    {
            	    // InternalSqlParser.g:4878:1: (lv_right_11_0= ruleOperandFragment )
            	    // InternalSqlParser.g:4879:3: lv_right_11_0= ruleOperandFragment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOperandAccess().getRightOperandFragmentParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_66);
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
            	    break loop97;
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
    // InternalSqlParser.g:4903:1: entryRuleOperandFragment returns [EObject current=null] : iv_ruleOperandFragment= ruleOperandFragment EOF ;
    public final EObject entryRuleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFragment = null;


        try {
            // InternalSqlParser.g:4904:2: (iv_ruleOperandFragment= ruleOperandFragment EOF )
            // InternalSqlParser.g:4905:2: iv_ruleOperandFragment= ruleOperandFragment EOF
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
    // InternalSqlParser.g:4912:1: ruleOperandFragment returns [EObject current=null] : ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) ) ;
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
            // InternalSqlParser.g:4915:28: ( ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) ) )
            // InternalSqlParser.g:4916:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) )
            {
            // InternalSqlParser.g:4916:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) )
            int alt98=7;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA98_1 = input.LA(2);

                if ( (LA98_1==EOF||LA98_1==ORDERSIBLINGSBY||LA98_1==STRAIGHT_JOIN||LA98_1==FETCHFIRST||LA98_1==ISNOTNULL||LA98_1==NOTBETWEEN||LA98_1==NOTEXISTS||(LA98_1>=KW_FOLLOWING && LA98_1<=PRECEDING)||(LA98_1>=GROUPBY && LA98_1<=NOTLIKE)||LA98_1==ORDERBY||LA98_1==BETWEEN||(LA98_1>=ISNULL && LA98_1<=NATURAL)||(LA98_1>=EXCEPT && LA98_1<=HAVING)||(LA98_1>=NOTIN_1 && LA98_1<=OFFSET)||LA98_1==CROSS||LA98_1==INNER||(LA98_1>=LIMIT && LA98_1<=MINUS)||LA98_1==NULLS||(LA98_1>=RANGE && LA98_1<=UNION)||LA98_1==WHERE||(LA98_1>=DESC && LA98_1<=FULL)||LA98_1==JOIN||LA98_1==LEFT||LA98_1==LIKE||LA98_1==ROWS||LA98_1==THEN||LA98_1==WHEN||LA98_1==LeftParenthesisPlusSignRightParenthesis||LA98_1==AND||LA98_1==ASC||LA98_1==END||LA98_1==ExclamationMarkEqualsSign||(LA98_1>=LessThanSignEqualsSign && LA98_1<=IN)||(LA98_1>=OR && LA98_1<=VerticalLineVerticalLine)||(LA98_1>=RightParenthesis && LA98_1<=GreaterThanSign)||(LA98_1>=VerticalLine && LA98_1<=RightCurlyBracket)||(LA98_1>=RULE_JRNPARAM && LA98_1<=RULE_STAR)||(LA98_1>=RULE_STRING && LA98_1<=RULE_ID)) ) {
                    alt98=1;
                }
                else if ( (LA98_1==LeftParenthesis) ) {
                    alt98=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 98, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt98=1;
                }
                break;
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt98=2;
                }
                break;
            case LeftParenthesis:
                {
                alt98=3;
                }
                break;
            case CAST:
                {
                alt98=4;
                }
                break;
            case EXTRACT:
                {
                alt98=5;
                }
                break;
            case CASE:
                {
                alt98=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }

            switch (alt98) {
                case 1 :
                    // InternalSqlParser.g:4916:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    {
                    // InternalSqlParser.g:4916:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    // InternalSqlParser.g:4917:1: (lv_column_0_0= ruleColumnOperand )
                    {
                    // InternalSqlParser.g:4917:1: (lv_column_0_0= ruleColumnOperand )
                    // InternalSqlParser.g:4918:3: lv_column_0_0= ruleColumnOperand
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
                    // InternalSqlParser.g:4935:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    {
                    // InternalSqlParser.g:4935:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    // InternalSqlParser.g:4936:1: (lv_xop_1_0= ruleXOperandFragment )
                    {
                    // InternalSqlParser.g:4936:1: (lv_xop_1_0= ruleXOperandFragment )
                    // InternalSqlParser.g:4937:3: lv_xop_1_0= ruleXOperandFragment
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
                    // InternalSqlParser.g:4954:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    {
                    // InternalSqlParser.g:4954:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    // InternalSqlParser.g:4955:1: (lv_subq_2_0= ruleSubQueryOperand )
                    {
                    // InternalSqlParser.g:4955:1: (lv_subq_2_0= ruleSubQueryOperand )
                    // InternalSqlParser.g:4956:3: lv_subq_2_0= ruleSubQueryOperand
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
                    // InternalSqlParser.g:4973:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    {
                    // InternalSqlParser.g:4973:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    // InternalSqlParser.g:4974:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    {
                    // InternalSqlParser.g:4974:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    // InternalSqlParser.g:4975:3: lv_fcast_3_0= ruleOpFunctionCast
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
                    // InternalSqlParser.g:4992:6: ( (lv_fext_4_0= ruleFunctionExtract ) )
                    {
                    // InternalSqlParser.g:4992:6: ( (lv_fext_4_0= ruleFunctionExtract ) )
                    // InternalSqlParser.g:4993:1: (lv_fext_4_0= ruleFunctionExtract )
                    {
                    // InternalSqlParser.g:4993:1: (lv_fext_4_0= ruleFunctionExtract )
                    // InternalSqlParser.g:4994:3: lv_fext_4_0= ruleFunctionExtract
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
                    // InternalSqlParser.g:5011:6: ( (lv_func_5_0= ruleOperandFunction ) )
                    {
                    // InternalSqlParser.g:5011:6: ( (lv_func_5_0= ruleOperandFunction ) )
                    // InternalSqlParser.g:5012:1: (lv_func_5_0= ruleOperandFunction )
                    {
                    // InternalSqlParser.g:5012:1: (lv_func_5_0= ruleOperandFunction )
                    // InternalSqlParser.g:5013:3: lv_func_5_0= ruleOperandFunction
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
                    // InternalSqlParser.g:5030:6: ( (lv_sqlcase_6_0= ruleSQLCASE ) )
                    {
                    // InternalSqlParser.g:5030:6: ( (lv_sqlcase_6_0= ruleSQLCASE ) )
                    // InternalSqlParser.g:5031:1: (lv_sqlcase_6_0= ruleSQLCASE )
                    {
                    // InternalSqlParser.g:5031:1: (lv_sqlcase_6_0= ruleSQLCASE )
                    // InternalSqlParser.g:5032:3: lv_sqlcase_6_0= ruleSQLCASE
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
    // InternalSqlParser.g:5056:1: entryRuleOperandFunction returns [EObject current=null] : iv_ruleOperandFunction= ruleOperandFunction EOF ;
    public final EObject entryRuleOperandFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFunction = null;


        try {
            // InternalSqlParser.g:5057:2: (iv_ruleOperandFunction= ruleOperandFunction EOF )
            // InternalSqlParser.g:5058:2: iv_ruleOperandFunction= ruleOperandFunction EOF
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
    // InternalSqlParser.g:5065:1: ruleOperandFunction returns [EObject current=null] : ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) ;
    public final EObject ruleOperandFunction() throws RecognitionException {
        EObject current = null;

        Token lv_star_2_0=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_fname_1_0 = null;

        EObject lv_args_3_0 = null;

        EObject lv_fan_5_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5068:28: ( ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) )
            // InternalSqlParser.g:5069:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            {
            // InternalSqlParser.g:5069:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            // InternalSqlParser.g:5069:2: () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= RightParenthesis ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            {
            // InternalSqlParser.g:5069:2: ()
            // InternalSqlParser.g:5070:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperandFunctionAccess().getOpFunctionAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:5075:2: ( (lv_fname_1_0= ruleFNAME ) )
            // InternalSqlParser.g:5076:1: (lv_fname_1_0= ruleFNAME )
            {
            // InternalSqlParser.g:5076:1: (lv_fname_1_0= ruleFNAME )
            // InternalSqlParser.g:5077:3: lv_fname_1_0= ruleFNAME
            {
             
            	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFnameFNAMEParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_67);
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

            // InternalSqlParser.g:5093:2: ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )?
            int alt99=3;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==RULE_STAR) ) {
                alt99=1;
            }
            else if ( (LA99_0==DISTINCT||LA99_0==EXTRACT||LA99_0==CAST||LA99_0==CASE||LA99_0==ALL||LA99_0==LeftParenthesis||(LA99_0>=RULE_JRPARAM && LA99_0<=RULE_JRNPARAM)||(LA99_0>=RULE_UNSIGNED && LA99_0<=RULE_SIGNED_DOUBLE)||(LA99_0>=RULE_STRING_ && LA99_0<=RULE_ID)) ) {
                alt99=2;
            }
            switch (alt99) {
                case 1 :
                    // InternalSqlParser.g:5093:3: ( (lv_star_2_0= RULE_STAR ) )
                    {
                    // InternalSqlParser.g:5093:3: ( (lv_star_2_0= RULE_STAR ) )
                    // InternalSqlParser.g:5094:1: (lv_star_2_0= RULE_STAR )
                    {
                    // InternalSqlParser.g:5094:1: (lv_star_2_0= RULE_STAR )
                    // InternalSqlParser.g:5095:3: lv_star_2_0= RULE_STAR
                    {
                    lv_star_2_0=(Token)match(input,RULE_STAR,FOLLOW_34); 

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
                    // InternalSqlParser.g:5112:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    {
                    // InternalSqlParser.g:5112:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    // InternalSqlParser.g:5113:1: (lv_args_3_0= ruleOpFunctionArg )
                    {
                    // InternalSqlParser.g:5113:1: (lv_args_3_0= ruleOpFunctionArg )
                    // InternalSqlParser.g:5114:3: lv_args_3_0= ruleOpFunctionArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getArgsOpFunctionArgParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_34);
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

            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_68); 

                	newLeafNode(otherlv_4, grammarAccess.getOperandFunctionAccess().getRightParenthesisKeyword_3());
                
            // InternalSqlParser.g:5135:1: ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==OVER) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // InternalSqlParser.g:5136:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    {
                    // InternalSqlParser.g:5136:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    // InternalSqlParser.g:5137:3: lv_fan_5_0= ruleFunctionAnalytical
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
    // InternalSqlParser.g:5161:1: entryRuleFunctionExtract returns [EObject current=null] : iv_ruleFunctionExtract= ruleFunctionExtract EOF ;
    public final EObject entryRuleFunctionExtract() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionExtract = null;


        try {
            // InternalSqlParser.g:5162:2: (iv_ruleFunctionExtract= ruleFunctionExtract EOF )
            // InternalSqlParser.g:5163:2: iv_ruleFunctionExtract= ruleFunctionExtract EOF
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
    // InternalSqlParser.g:5170:1: ruleFunctionExtract returns [EObject current=null] : (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis ) ;
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
            // InternalSqlParser.g:5173:28: ( (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis ) )
            // InternalSqlParser.g:5174:1: (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis )
            {
            // InternalSqlParser.g:5174:1: (otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis )
            // InternalSqlParser.g:5175:2: otherlv_0= EXTRACT otherlv_1= LeftParenthesis ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= FROM ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= RightParenthesis
            {
            otherlv_0=(Token)match(input,EXTRACT,FOLLOW_33); 

                	newLeafNode(otherlv_0, grammarAccess.getFunctionExtractAccess().getEXTRACTKeyword_0());
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_69); 

                	newLeafNode(otherlv_1, grammarAccess.getFunctionExtractAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:5184:1: ( (lv_v_2_0= ruleEXTRACT_VALUES ) )
            // InternalSqlParser.g:5185:1: (lv_v_2_0= ruleEXTRACT_VALUES )
            {
            // InternalSqlParser.g:5185:1: (lv_v_2_0= ruleEXTRACT_VALUES )
            // InternalSqlParser.g:5186:3: lv_v_2_0= ruleEXTRACT_VALUES
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

            otherlv_3=(Token)match(input,FROM,FOLLOW_56); 

                	newLeafNode(otherlv_3, grammarAccess.getFunctionExtractAccess().getFROMKeyword_3());
                
            // InternalSqlParser.g:5207:1: ( (lv_operand_4_0= ruleOperandGroup ) )
            // InternalSqlParser.g:5208:1: (lv_operand_4_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:5208:1: (lv_operand_4_0= ruleOperandGroup )
            // InternalSqlParser.g:5209:3: lv_operand_4_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getFunctionExtractAccess().getOperandOperandGroupParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:5238:1: entryRuleFunctionAnalytical returns [EObject current=null] : iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF ;
    public final EObject entryRuleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionAnalytical = null;


        try {
            // InternalSqlParser.g:5239:2: (iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF )
            // InternalSqlParser.g:5240:2: iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF
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
    // InternalSqlParser.g:5247:1: ruleFunctionAnalytical returns [EObject current=null] : (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_anClause_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5250:28: ( (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:5251:1: (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:5251:1: (otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:5252:2: otherlv_0= OVER otherlv_1= LeftParenthesis ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= RightParenthesis
            {
            otherlv_0=(Token)match(input,OVER,FOLLOW_33); 

                	newLeafNode(otherlv_0, grammarAccess.getFunctionAnalyticalAccess().getOVERKeyword_0());
                
            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_70); 

                	newLeafNode(otherlv_1, grammarAccess.getFunctionAnalyticalAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:5261:1: ( (lv_anClause_2_0= ruleAnalyticClause ) )
            // InternalSqlParser.g:5262:1: (lv_anClause_2_0= ruleAnalyticClause )
            {
            // InternalSqlParser.g:5262:1: (lv_anClause_2_0= ruleAnalyticClause )
            // InternalSqlParser.g:5263:3: lv_anClause_2_0= ruleAnalyticClause
            {
             
            	        newCompositeNode(grammarAccess.getFunctionAnalyticalAccess().getAnClauseAnalyticClauseParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:5292:1: entryRuleAnalyticClause returns [EObject current=null] : iv_ruleAnalyticClause= ruleAnalyticClause EOF ;
    public final EObject entryRuleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticClause = null;


        try {
            // InternalSqlParser.g:5293:2: (iv_ruleAnalyticClause= ruleAnalyticClause EOF )
            // InternalSqlParser.g:5294:2: iv_ruleAnalyticClause= ruleAnalyticClause EOF
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
    // InternalSqlParser.g:5301:1: ruleAnalyticClause returns [EObject current=null] : ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? ) ;
    public final EObject ruleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject lv_abc_1_0 = null;

        EObject lv_obc_2_0 = null;

        EObject lv_winc_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5304:28: ( ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? ) )
            // InternalSqlParser.g:5305:1: ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? )
            {
            // InternalSqlParser.g:5305:1: ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? )
            // InternalSqlParser.g:5305:2: () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )?
            {
            // InternalSqlParser.g:5305:2: ()
            // InternalSqlParser.g:5306:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getAnalyticClauseAccess().getAnalyticClauseAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:5311:2: ( (lv_abc_1_0= ruleQueryPartitionClause ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==PARTITIONBY) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // InternalSqlParser.g:5312:1: (lv_abc_1_0= ruleQueryPartitionClause )
                    {
                    // InternalSqlParser.g:5312:1: (lv_abc_1_0= ruleQueryPartitionClause )
                    // InternalSqlParser.g:5313:3: lv_abc_1_0= ruleQueryPartitionClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getAbcQueryPartitionClauseParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_71);
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

            // InternalSqlParser.g:5329:3: ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==ORDERSIBLINGSBY||LA103_0==ORDERBY) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // InternalSqlParser.g:5329:4: ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )?
                    {
                    // InternalSqlParser.g:5329:4: ( (lv_obc_2_0= ruleOrderByClause ) )
                    // InternalSqlParser.g:5330:1: (lv_obc_2_0= ruleOrderByClause )
                    {
                    // InternalSqlParser.g:5330:1: (lv_obc_2_0= ruleOrderByClause )
                    // InternalSqlParser.g:5331:3: lv_obc_2_0= ruleOrderByClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getObcOrderByClauseParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_72);
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

                    // InternalSqlParser.g:5347:2: ( (lv_winc_3_0= ruleWindowingClause ) )?
                    int alt102=2;
                    int LA102_0 = input.LA(1);

                    if ( (LA102_0==RANGE||LA102_0==ROWS) ) {
                        alt102=1;
                    }
                    switch (alt102) {
                        case 1 :
                            // InternalSqlParser.g:5348:1: (lv_winc_3_0= ruleWindowingClause )
                            {
                            // InternalSqlParser.g:5348:1: (lv_winc_3_0= ruleWindowingClause )
                            // InternalSqlParser.g:5349:3: lv_winc_3_0= ruleWindowingClause
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
    // InternalSqlParser.g:5373:1: entryRuleWindowingClause returns [EObject current=null] : iv_ruleWindowingClause= ruleWindowingClause EOF ;
    public final EObject entryRuleWindowingClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClause = null;


        try {
            // InternalSqlParser.g:5374:2: (iv_ruleWindowingClause= ruleWindowingClause EOF )
            // InternalSqlParser.g:5375:2: iv_ruleWindowingClause= ruleWindowingClause EOF
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
    // InternalSqlParser.g:5382:1: ruleWindowingClause returns [EObject current=null] : ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) ;
    public final EObject ruleWindowingClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_WindowingClauseBetween_2 = null;

        EObject this_WindowingClauseOperandPreceding_3 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5385:28: ( ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) )
            // InternalSqlParser.g:5386:1: ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            {
            // InternalSqlParser.g:5386:1: ( (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            // InternalSqlParser.g:5386:2: (otherlv_0= ROWS | otherlv_1= RANGE ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            {
            // InternalSqlParser.g:5386:2: (otherlv_0= ROWS | otherlv_1= RANGE )
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==ROWS) ) {
                alt104=1;
            }
            else if ( (LA104_0==RANGE) ) {
                alt104=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;
            }
            switch (alt104) {
                case 1 :
                    // InternalSqlParser.g:5387:2: otherlv_0= ROWS
                    {
                    otherlv_0=(Token)match(input,ROWS,FOLLOW_73); 

                        	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseAccess().getROWSKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5393:2: otherlv_1= RANGE
                    {
                    otherlv_1=(Token)match(input,RANGE,FOLLOW_73); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseAccess().getRANGEKeyword_0_1());
                        

                    }
                    break;

            }

            // InternalSqlParser.g:5397:2: (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==BETWEEN) ) {
                alt105=1;
            }
            else if ( (LA105_0==UNBOUNDEDPRECEDING||LA105_0==CURRENTROW||LA105_0==EXTRACT||LA105_0==CAST||LA105_0==CASE||LA105_0==LeftParenthesis||(LA105_0>=RULE_JRPARAM && LA105_0<=RULE_JRNPARAM)||(LA105_0>=RULE_UNSIGNED && LA105_0<=RULE_SIGNED_DOUBLE)||(LA105_0>=RULE_STRING_ && LA105_0<=RULE_ID)) ) {
                alt105=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;
            }
            switch (alt105) {
                case 1 :
                    // InternalSqlParser.g:5398:5: this_WindowingClauseBetween_2= ruleWindowingClauseBetween
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
                    // InternalSqlParser.g:5408:5: this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding
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
    // InternalSqlParser.g:5424:1: entryRuleWindowingClauseBetween returns [EObject current=null] : iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF ;
    public final EObject entryRuleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseBetween = null;


        try {
            // InternalSqlParser.g:5425:2: (iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF )
            // InternalSqlParser.g:5426:2: iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF
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
    // InternalSqlParser.g:5433:1: ruleWindowingClauseBetween returns [EObject current=null] : (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) ;
    public final EObject ruleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_wcoP_1_0 = null;

        EObject lv_wcoF_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5436:28: ( (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) )
            // InternalSqlParser.g:5437:1: (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            {
            // InternalSqlParser.g:5437:1: (otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            // InternalSqlParser.g:5438:2: otherlv_0= BETWEEN ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= AND ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            {
            otherlv_0=(Token)match(input,BETWEEN,FOLLOW_73); 

                	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseBetweenAccess().getBETWEENKeyword_0());
                
            // InternalSqlParser.g:5442:1: ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) )
            // InternalSqlParser.g:5443:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            {
            // InternalSqlParser.g:5443:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            // InternalSqlParser.g:5444:3: lv_wcoP_1_0= ruleWindowingClauseOperandPreceding
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoPWindowingClauseOperandPrecedingParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_64);
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

            otherlv_2=(Token)match(input,AND,FOLLOW_74); 

                	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseBetweenAccess().getANDKeyword_2());
                
            // InternalSqlParser.g:5465:1: ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            // InternalSqlParser.g:5466:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            {
            // InternalSqlParser.g:5466:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            // InternalSqlParser.g:5467:3: lv_wcoF_3_0= ruleWindowingClauseOperandFollowing
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
    // InternalSqlParser.g:5491:1: entryRuleWindowingClauseOperandFollowing returns [EObject current=null] : iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF ;
    public final EObject entryRuleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandFollowing = null;


        try {
            // InternalSqlParser.g:5492:2: (iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF )
            // InternalSqlParser.g:5493:2: iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF
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
    // InternalSqlParser.g:5500:1: ruleWindowingClauseOperandFollowing returns [EObject current=null] : ( () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_exp_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5503:28: ( ( () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) ) )
            // InternalSqlParser.g:5504:1: ( () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) )
            {
            // InternalSqlParser.g:5504:1: ( () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) )
            // InternalSqlParser.g:5504:2: () (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) )
            {
            // InternalSqlParser.g:5504:2: ()
            // InternalSqlParser.g:5505:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandFollowingAccess().getWindowingClauseOperandFollowingAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:5510:2: (otherlv_1= UNBOUNDEDFOLLOWING | otherlv_2= CURRENTROW | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) )
            int alt107=3;
            switch ( input.LA(1) ) {
            case UNBOUNDEDFOLLOWING:
                {
                alt107=1;
                }
                break;
            case CURRENTROW:
                {
                alt107=2;
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
                alt107=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 107, 0, input);

                throw nvae;
            }

            switch (alt107) {
                case 1 :
                    // InternalSqlParser.g:5511:2: otherlv_1= UNBOUNDEDFOLLOWING
                    {
                    otherlv_1=(Token)match(input,UNBOUNDEDFOLLOWING,FOLLOW_2); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandFollowingAccess().getUNBOUNDEDFOLLOWINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5517:2: otherlv_2= CURRENTROW
                    {
                    otherlv_2=(Token)match(input,CURRENTROW,FOLLOW_2); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandFollowingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:5522:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) )
                    {
                    // InternalSqlParser.g:5522:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) )
                    // InternalSqlParser.g:5522:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING )
                    {
                    // InternalSqlParser.g:5522:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) )
                    // InternalSqlParser.g:5523:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    {
                    // InternalSqlParser.g:5523:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    // InternalSqlParser.g:5524:3: lv_exp_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingAccess().getExpAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_75);
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

                    // InternalSqlParser.g:5540:2: (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING )
                    int alt106=2;
                    int LA106_0 = input.LA(1);

                    if ( (LA106_0==PRECEDING) ) {
                        alt106=1;
                    }
                    else if ( (LA106_0==KW_FOLLOWING) ) {
                        alt106=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 106, 0, input);

                        throw nvae;
                    }
                    switch (alt106) {
                        case 1 :
                            // InternalSqlParser.g:5541:2: otherlv_4= PRECEDING
                            {
                            otherlv_4=(Token)match(input,PRECEDING,FOLLOW_2); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandFollowingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:5547:2: otherlv_5= KW_FOLLOWING
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
    // InternalSqlParser.g:5559:1: entryRuleWindowingClauseOperandPreceding returns [EObject current=null] : iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF ;
    public final EObject entryRuleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandPreceding = null;


        try {
            // InternalSqlParser.g:5560:2: (iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF )
            // InternalSqlParser.g:5561:2: iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF
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
    // InternalSqlParser.g:5568:1: ruleWindowingClauseOperandPreceding returns [EObject current=null] : ( () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5571:28: ( ( () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) ) )
            // InternalSqlParser.g:5572:1: ( () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) )
            {
            // InternalSqlParser.g:5572:1: ( () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) ) )
            // InternalSqlParser.g:5572:2: () (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) )
            {
            // InternalSqlParser.g:5572:2: ()
            // InternalSqlParser.g:5573:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandPrecedingAccess().getWindowingClauseOperandPrecedingAction_0(),
                        current);
                

            }

            // InternalSqlParser.g:5578:2: (otherlv_1= UNBOUNDEDPRECEDING | otherlv_2= CURRENTROW | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) ) )
            int alt109=3;
            switch ( input.LA(1) ) {
            case UNBOUNDEDPRECEDING:
                {
                alt109=1;
                }
                break;
            case CURRENTROW:
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
                NoViableAltException nvae =
                    new NoViableAltException("", 109, 0, input);

                throw nvae;
            }

            switch (alt109) {
                case 1 :
                    // InternalSqlParser.g:5579:2: otherlv_1= UNBOUNDEDPRECEDING
                    {
                    otherlv_1=(Token)match(input,UNBOUNDEDPRECEDING,FOLLOW_2); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandPrecedingAccess().getUNBOUNDEDPRECEDINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5585:2: otherlv_2= CURRENTROW
                    {
                    otherlv_2=(Token)match(input,CURRENTROW,FOLLOW_2); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandPrecedingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:5590:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) )
                    {
                    // InternalSqlParser.g:5590:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING ) )
                    // InternalSqlParser.g:5590:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING )
                    {
                    // InternalSqlParser.g:5590:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) )
                    // InternalSqlParser.g:5591:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    {
                    // InternalSqlParser.g:5591:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    // InternalSqlParser.g:5592:3: lv_expr_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingAccess().getExprAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_75);
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

                    // InternalSqlParser.g:5608:2: (otherlv_4= PRECEDING | otherlv_5= KW_FOLLOWING )
                    int alt108=2;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==PRECEDING) ) {
                        alt108=1;
                    }
                    else if ( (LA108_0==KW_FOLLOWING) ) {
                        alt108=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 108, 0, input);

                        throw nvae;
                    }
                    switch (alt108) {
                        case 1 :
                            // InternalSqlParser.g:5609:2: otherlv_4= PRECEDING
                            {
                            otherlv_4=(Token)match(input,PRECEDING,FOLLOW_2); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandPrecedingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:5615:2: otherlv_5= KW_FOLLOWING
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
    // InternalSqlParser.g:5627:1: entryRuleOrderByClause returns [EObject current=null] : iv_ruleOrderByClause= ruleOrderByClause EOF ;
    public final EObject entryRuleOrderByClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClause = null;


        try {
            // InternalSqlParser.g:5628:2: (iv_ruleOrderByClause= ruleOrderByClause EOF )
            // InternalSqlParser.g:5629:2: iv_ruleOrderByClause= ruleOrderByClause EOF
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
    // InternalSqlParser.g:5636:1: ruleOrderByClause returns [EObject current=null] : ( (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) ;
    public final EObject ruleOrderByClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_args_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5639:28: ( ( (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) )
            // InternalSqlParser.g:5640:1: ( (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            {
            // InternalSqlParser.g:5640:1: ( (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            // InternalSqlParser.g:5640:2: (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY ) ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            {
            // InternalSqlParser.g:5640:2: (otherlv_0= ORDERBY | otherlv_1= ORDERSIBLINGSBY )
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==ORDERBY) ) {
                alt110=1;
            }
            else if ( (LA110_0==ORDERSIBLINGSBY) ) {
                alt110=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 110, 0, input);

                throw nvae;
            }
            switch (alt110) {
                case 1 :
                    // InternalSqlParser.g:5641:2: otherlv_0= ORDERBY
                    {
                    otherlv_0=(Token)match(input,ORDERBY,FOLLOW_73); 

                        	newLeafNode(otherlv_0, grammarAccess.getOrderByClauseAccess().getORDERBYKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5647:2: otherlv_1= ORDERSIBLINGSBY
                    {
                    otherlv_1=(Token)match(input,ORDERSIBLINGSBY,FOLLOW_73); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseAccess().getORDERSIBLINGSBYKeyword_0_1());
                        

                    }
                    break;

            }

            // InternalSqlParser.g:5651:2: ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            // InternalSqlParser.g:5652:1: (lv_args_2_0= ruleOrderByClauseArgs )
            {
            // InternalSqlParser.g:5652:1: (lv_args_2_0= ruleOrderByClauseArgs )
            // InternalSqlParser.g:5653:3: lv_args_2_0= ruleOrderByClauseArgs
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
    // InternalSqlParser.g:5677:1: entryRuleOrderByClauseArgs returns [EObject current=null] : iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF ;
    public final EObject entryRuleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArgs = null;


        try {
            // InternalSqlParser.g:5678:2: (iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF )
            // InternalSqlParser.g:5679:2: iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF
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
    // InternalSqlParser.g:5686:1: ruleOrderByClauseArgs returns [EObject current=null] : (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) ;
    public final EObject ruleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5689:28: ( (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) )
            // InternalSqlParser.g:5690:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            {
            // InternalSqlParser.g:5690:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            // InternalSqlParser.g:5691:5: this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getOrderByClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_OrderByClauseArg_0=ruleOrderByClauseArg();

            state._fsp--;


                    current = this_OrderByClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:5699:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==Comma) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // InternalSqlParser.g:5699:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    {
                    // InternalSqlParser.g:5699:2: ()
                    // InternalSqlParser.g:5700:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByClauseArgsAccess().getOBCArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:5705:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    int cnt111=0;
                    loop111:
                    do {
                        int alt111=2;
                        int LA111_0 = input.LA(1);

                        if ( (LA111_0==Comma) ) {
                            alt111=1;
                        }


                        switch (alt111) {
                    	case 1 :
                    	    // InternalSqlParser.g:5706:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_73); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:5710:1: ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    // InternalSqlParser.g:5711:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    {
                    	    // InternalSqlParser.g:5711:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    // InternalSqlParser.g:5712:3: lv_entries_3_0= ruleOrderByClauseArg
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
                    	    if ( cnt111 >= 1 ) break loop111;
                                EarlyExitException eee =
                                    new EarlyExitException(111, input);
                                throw eee;
                        }
                        cnt111++;
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
    // InternalSqlParser.g:5736:1: entryRuleOrderByClauseArg returns [EObject current=null] : iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF ;
    public final EObject entryRuleOrderByClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArg = null;


        try {
            // InternalSqlParser.g:5737:2: (iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF )
            // InternalSqlParser.g:5738:2: iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF
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
    // InternalSqlParser.g:5745:1: ruleOrderByClauseArg returns [EObject current=null] : ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? ) ;
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
            // InternalSqlParser.g:5748:28: ( ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? ) )
            // InternalSqlParser.g:5749:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? )
            {
            // InternalSqlParser.g:5749:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )? )
            // InternalSqlParser.g:5749:2: ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= ASC | otherlv_2= DESC )? (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )?
            {
            // InternalSqlParser.g:5749:2: ( (lv_col_0_0= ruleAnalyticExprArg ) )
            // InternalSqlParser.g:5750:1: (lv_col_0_0= ruleAnalyticExprArg )
            {
            // InternalSqlParser.g:5750:1: (lv_col_0_0= ruleAnalyticExprArg )
            // InternalSqlParser.g:5751:3: lv_col_0_0= ruleAnalyticExprArg
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseArgAccess().getColAnalyticExprArgParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_76);
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

            // InternalSqlParser.g:5767:2: (otherlv_1= ASC | otherlv_2= DESC )?
            int alt113=3;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==ASC) ) {
                alt113=1;
            }
            else if ( (LA113_0==DESC) ) {
                alt113=2;
            }
            switch (alt113) {
                case 1 :
                    // InternalSqlParser.g:5768:2: otherlv_1= ASC
                    {
                    otherlv_1=(Token)match(input,ASC,FOLLOW_77); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseArgAccess().getASCKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:5774:2: otherlv_2= DESC
                    {
                    otherlv_2=(Token)match(input,DESC,FOLLOW_77); 

                        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgAccess().getDESCKeyword_1_1());
                        

                    }
                    break;

            }

            // InternalSqlParser.g:5778:3: (otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==NULLS) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // InternalSqlParser.g:5779:2: otherlv_3= NULLS (otherlv_4= FIRST | otherlv_5= LAST )
                    {
                    otherlv_3=(Token)match(input,NULLS,FOLLOW_78); 

                        	newLeafNode(otherlv_3, grammarAccess.getOrderByClauseArgAccess().getNULLSKeyword_2_0());
                        
                    // InternalSqlParser.g:5783:1: (otherlv_4= FIRST | otherlv_5= LAST )
                    int alt114=2;
                    int LA114_0 = input.LA(1);

                    if ( (LA114_0==FIRST) ) {
                        alt114=1;
                    }
                    else if ( (LA114_0==LAST) ) {
                        alt114=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 114, 0, input);

                        throw nvae;
                    }
                    switch (alt114) {
                        case 1 :
                            // InternalSqlParser.g:5784:2: otherlv_4= FIRST
                            {
                            otherlv_4=(Token)match(input,FIRST,FOLLOW_2); 

                                	newLeafNode(otherlv_4, grammarAccess.getOrderByClauseArgAccess().getFIRSTKeyword_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:5790:2: otherlv_5= LAST
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
    // InternalSqlParser.g:5802:1: entryRuleQueryPartitionClause returns [EObject current=null] : iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF ;
    public final EObject entryRuleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQueryPartitionClause = null;


        try {
            // InternalSqlParser.g:5803:2: (iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF )
            // InternalSqlParser.g:5804:2: iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF
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
    // InternalSqlParser.g:5811:1: ruleQueryPartitionClause returns [EObject current=null] : (otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) ) ) ;
    public final EObject ruleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_1_0 = null;

        EObject this_AnalyticExprArgs_3 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5814:28: ( (otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) ) ) )
            // InternalSqlParser.g:5815:1: (otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) ) )
            {
            // InternalSqlParser.g:5815:1: (otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) ) )
            // InternalSqlParser.g:5816:2: otherlv_0= PARTITIONBY ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) )
            {
            otherlv_0=(Token)match(input,PARTITIONBY,FOLLOW_73); 

                	newLeafNode(otherlv_0, grammarAccess.getQueryPartitionClauseAccess().getPARTITIONBYKeyword_0());
                
            // InternalSqlParser.g:5820:1: ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis ) )
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==EXTRACT||LA116_0==CAST||LA116_0==CASE||(LA116_0>=RULE_JRPARAM && LA116_0<=RULE_JRNPARAM)||(LA116_0>=RULE_UNSIGNED && LA116_0<=RULE_SIGNED_DOUBLE)||(LA116_0>=RULE_STRING_ && LA116_0<=RULE_ID)) ) {
                alt116=1;
            }
            else if ( (LA116_0==LeftParenthesis) ) {
                int LA116_2 = input.LA(2);

                if ( (LA116_2==EXTRACT||LA116_2==CAST||LA116_2==CASE||LA116_2==LeftParenthesis||(LA116_2>=RULE_JRPARAM && LA116_2<=RULE_JRNPARAM)||(LA116_2>=RULE_UNSIGNED && LA116_2<=RULE_SIGNED_DOUBLE)||(LA116_2>=RULE_STRING_ && LA116_2<=RULE_ID)) ) {
                    alt116=2;
                }
                else if ( (LA116_2==SELECT) ) {
                    alt116=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 116, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 116, 0, input);

                throw nvae;
            }
            switch (alt116) {
                case 1 :
                    // InternalSqlParser.g:5820:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    {
                    // InternalSqlParser.g:5820:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    // InternalSqlParser.g:5821:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    {
                    // InternalSqlParser.g:5821:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    // InternalSqlParser.g:5822:3: lv_args_1_0= ruleAnalyticExprArgs
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
                    // InternalSqlParser.g:5839:6: (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis )
                    {
                    // InternalSqlParser.g:5839:6: (otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis )
                    // InternalSqlParser.g:5840:2: otherlv_2= LeftParenthesis this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= RightParenthesis
                    {
                    otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_73); 

                        	newLeafNode(otherlv_2, grammarAccess.getQueryPartitionClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getAnalyticExprArgsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:5866:1: entryRuleAnalyticExprArgs returns [EObject current=null] : iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF ;
    public final EObject entryRuleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArgs = null;


        try {
            // InternalSqlParser.g:5867:2: (iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF )
            // InternalSqlParser.g:5868:2: iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF
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
    // InternalSqlParser.g:5875:1: ruleAnalyticExprArgs returns [EObject current=null] : (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) ;
    public final EObject ruleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AnalyticExprArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5878:28: ( (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) )
            // InternalSqlParser.g:5879:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            {
            // InternalSqlParser.g:5879:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            // InternalSqlParser.g:5880:5: this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getAnalyticExprArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_AnalyticExprArg_0=ruleAnalyticExprArg();

            state._fsp--;


                    current = this_AnalyticExprArg_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:5888:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==Comma) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // InternalSqlParser.g:5888:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    {
                    // InternalSqlParser.g:5888:2: ()
                    // InternalSqlParser.g:5889:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getAnalyticExprArgsAccess().getAExpArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:5894:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    int cnt117=0;
                    loop117:
                    do {
                        int alt117=2;
                        int LA117_0 = input.LA(1);

                        if ( (LA117_0==Comma) ) {
                            alt117=1;
                        }


                        switch (alt117) {
                    	case 1 :
                    	    // InternalSqlParser.g:5895:2: otherlv_2= Comma ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_73); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getAnalyticExprArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:5899:1: ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    // InternalSqlParser.g:5900:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    {
                    	    // InternalSqlParser.g:5900:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    // InternalSqlParser.g:5901:3: lv_entries_3_0= ruleAnalyticExprArg
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
                    	    if ( cnt117 >= 1 ) break loop117;
                                EarlyExitException eee =
                                    new EarlyExitException(117, input);
                                throw eee;
                        }
                        cnt117++;
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
    // InternalSqlParser.g:5925:1: entryRuleAnalyticExprArg returns [EObject current=null] : iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF ;
    public final EObject entryRuleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArg = null;


        try {
            // InternalSqlParser.g:5926:2: (iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF )
            // InternalSqlParser.g:5927:2: iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF
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
    // InternalSqlParser.g:5934:1: ruleAnalyticExprArg returns [EObject current=null] : ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_1_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5937:28: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) )
            // InternalSqlParser.g:5938:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            {
            // InternalSqlParser.g:5938:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            // InternalSqlParser.g:5938:2: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            {
            // InternalSqlParser.g:5938:2: ( (lv_ce_0_0= ruleOperand ) )
            // InternalSqlParser.g:5939:1: (lv_ce_0_0= ruleOperand )
            {
            // InternalSqlParser.g:5939:1: (lv_ce_0_0= ruleOperand )
            // InternalSqlParser.g:5940:3: lv_ce_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getCeOperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_28);
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

            // InternalSqlParser.g:5956:2: ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( ((LA119_0>=RULE_STRING && LA119_0<=RULE_ID)) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // InternalSqlParser.g:5957:1: (lv_colAlias_1_0= ruleDbObjectName )
                    {
                    // InternalSqlParser.g:5957:1: (lv_colAlias_1_0= ruleDbObjectName )
                    // InternalSqlParser.g:5958:3: lv_colAlias_1_0= ruleDbObjectName
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
    // InternalSqlParser.g:5982:1: entryRuleOpFunctionArg returns [EObject current=null] : iv_ruleOpFunctionArg= ruleOpFunctionArg EOF ;
    public final EObject entryRuleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArg = null;


        try {
            // InternalSqlParser.g:5983:2: (iv_ruleOpFunctionArg= ruleOpFunctionArg EOF )
            // InternalSqlParser.g:5984:2: iv_ruleOpFunctionArg= ruleOpFunctionArg EOF
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
    // InternalSqlParser.g:5991:1: ruleOpFunctionArg returns [EObject current=null] : (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) ;
    public final EObject ruleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OpFunctionArgOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:5994:28: ( (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) )
            // InternalSqlParser.g:5995:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            {
            // InternalSqlParser.g:5995:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            // InternalSqlParser.g:5996:5: this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOpFunctionArgAccess().getOpFunctionArgOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_6);
            this_OpFunctionArgOperand_0=ruleOpFunctionArgOperand();

            state._fsp--;


                    current = this_OpFunctionArgOperand_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:6004:1: ( () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==Comma) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // InternalSqlParser.g:6004:2: () (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    {
                    // InternalSqlParser.g:6004:2: ()
                    // InternalSqlParser.g:6005:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOpFunctionArgAccess().getOpFListEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:6010:2: (otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    int cnt120=0;
                    loop120:
                    do {
                        int alt120=2;
                        int LA120_0 = input.LA(1);

                        if ( (LA120_0==Comma) ) {
                            alt120=1;
                        }


                        switch (alt120) {
                    	case 1 :
                    	    // InternalSqlParser.g:6011:2: otherlv_2= Comma ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Comma,FOLLOW_79); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOpFunctionArgAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // InternalSqlParser.g:6015:1: ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    // InternalSqlParser.g:6016:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    {
                    	    // InternalSqlParser.g:6016:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    // InternalSqlParser.g:6017:3: lv_entries_3_0= ruleOpFunctionArgOperand
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
                    	    if ( cnt120 >= 1 ) break loop120;
                                EarlyExitException eee =
                                    new EarlyExitException(120, input);
                                throw eee;
                        }
                        cnt120++;
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
    // InternalSqlParser.g:6041:1: entryRuleOpFunctionArgOperand returns [EObject current=null] : iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF ;
    public final EObject entryRuleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgOperand = null;


        try {
            // InternalSqlParser.g:6042:2: (iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF )
            // InternalSqlParser.g:6043:2: iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF
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
    // InternalSqlParser.g:6050:1: ruleOpFunctionArgOperand returns [EObject current=null] : ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) ;
    public final EObject ruleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject lv_op_0_1 = null;

        EObject lv_op_0_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6053:28: ( ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) )
            // InternalSqlParser.g:6054:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            {
            // InternalSqlParser.g:6054:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            // InternalSqlParser.g:6055:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            {
            // InternalSqlParser.g:6055:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            // InternalSqlParser.g:6056:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            {
            // InternalSqlParser.g:6056:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==DISTINCT||LA122_0==ALL) ) {
                alt122=1;
            }
            else if ( (LA122_0==EXTRACT||LA122_0==CAST||LA122_0==CASE||LA122_0==LeftParenthesis||(LA122_0>=RULE_JRPARAM && LA122_0<=RULE_JRNPARAM)||(LA122_0>=RULE_UNSIGNED && LA122_0<=RULE_SIGNED_DOUBLE)||(LA122_0>=RULE_STRING_ && LA122_0<=RULE_ID)) ) {
                alt122=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }
            switch (alt122) {
                case 1 :
                    // InternalSqlParser.g:6057:3: lv_op_0_1= ruleOpFunctionArgAgregate
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
                    // InternalSqlParser.g:6072:8: lv_op_0_2= ruleOperand
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
    // InternalSqlParser.g:6098:1: entryRuleOpFunctionCast returns [EObject current=null] : iv_ruleOpFunctionCast= ruleOpFunctionCast EOF ;
    public final EObject entryRuleOpFunctionCast() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionCast = null;


        try {
            // InternalSqlParser.g:6099:2: (iv_ruleOpFunctionCast= ruleOpFunctionCast EOF )
            // InternalSqlParser.g:6100:2: iv_ruleOpFunctionCast= ruleOpFunctionCast EOF
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
    // InternalSqlParser.g:6107:1: ruleOpFunctionCast returns [EObject current=null] : (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis ) ;
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
            // InternalSqlParser.g:6110:28: ( (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis ) )
            // InternalSqlParser.g:6111:1: (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis )
            {
            // InternalSqlParser.g:6111:1: (otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis )
            // InternalSqlParser.g:6112:2: otherlv_0= CAST ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= AS ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )? otherlv_9= RightParenthesis
            {
            otherlv_0=(Token)match(input,CAST,FOLLOW_56); 

                	newLeafNode(otherlv_0, grammarAccess.getOpFunctionCastAccess().getCASTKeyword_0());
                
            // InternalSqlParser.g:6116:1: ( (lv_op_1_0= ruleOperandGroup ) )
            // InternalSqlParser.g:6117:1: (lv_op_1_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:6117:1: (lv_op_1_0= ruleOperandGroup )
            // InternalSqlParser.g:6118:3: lv_op_1_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getOpFunctionCastAccess().getOpOperandGroupParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_80);
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

            otherlv_2=(Token)match(input,AS,FOLLOW_41); 

                	newLeafNode(otherlv_2, grammarAccess.getOpFunctionCastAccess().getASKeyword_2());
                
            // InternalSqlParser.g:6139:1: ( (lv_type_3_0= RULE_ID ) )
            // InternalSqlParser.g:6140:1: (lv_type_3_0= RULE_ID )
            {
            // InternalSqlParser.g:6140:1: (lv_type_3_0= RULE_ID )
            // InternalSqlParser.g:6141:3: lv_type_3_0= RULE_ID
            {
            lv_type_3_0=(Token)match(input,RULE_ID,FOLLOW_81); 

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

            // InternalSqlParser.g:6157:2: (otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==LeftParenthesis) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // InternalSqlParser.g:6158:2: otherlv_4= LeftParenthesis ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= RightParenthesis
                    {
                    otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_25); 

                        	newLeafNode(otherlv_4, grammarAccess.getOpFunctionCastAccess().getLeftParenthesisKeyword_4_0());
                        
                    // InternalSqlParser.g:6162:1: ( (lv_p_5_0= RULE_INT ) )
                    // InternalSqlParser.g:6163:1: (lv_p_5_0= RULE_INT )
                    {
                    // InternalSqlParser.g:6163:1: (lv_p_5_0= RULE_INT )
                    // InternalSqlParser.g:6164:3: lv_p_5_0= RULE_INT
                    {
                    lv_p_5_0=(Token)match(input,RULE_INT,FOLLOW_82); 

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

                    // InternalSqlParser.g:6180:2: (otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) ) )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==Comma) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // InternalSqlParser.g:6181:2: otherlv_6= Comma ( (lv_p2_7_0= RULE_INT ) )
                            {
                            otherlv_6=(Token)match(input,Comma,FOLLOW_25); 

                                	newLeafNode(otherlv_6, grammarAccess.getOpFunctionCastAccess().getCommaKeyword_4_2_0());
                                
                            // InternalSqlParser.g:6185:1: ( (lv_p2_7_0= RULE_INT ) )
                            // InternalSqlParser.g:6186:1: (lv_p2_7_0= RULE_INT )
                            {
                            // InternalSqlParser.g:6186:1: (lv_p2_7_0= RULE_INT )
                            // InternalSqlParser.g:6187:3: lv_p2_7_0= RULE_INT
                            {
                            lv_p2_7_0=(Token)match(input,RULE_INT,FOLLOW_34); 

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

                    otherlv_8=(Token)match(input,RightParenthesis,FOLLOW_34); 

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
    // InternalSqlParser.g:6221:1: entryRuleOpFunctionArgAgregate returns [EObject current=null] : iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF ;
    public final EObject entryRuleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgAgregate = null;


        try {
            // InternalSqlParser.g:6222:2: (iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF )
            // InternalSqlParser.g:6223:2: iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF
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
    // InternalSqlParser.g:6230:1: ruleOpFunctionArgAgregate returns [EObject current=null] : ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand ) ;
    public final EObject ruleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6233:28: ( ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand ) )
            // InternalSqlParser.g:6234:1: ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand )
            {
            // InternalSqlParser.g:6234:1: ( (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand )
            // InternalSqlParser.g:6234:2: (otherlv_0= ALL | otherlv_1= DISTINCT ) this_Operand_2= ruleOperand
            {
            // InternalSqlParser.g:6234:2: (otherlv_0= ALL | otherlv_1= DISTINCT )
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==ALL) ) {
                alt125=1;
            }
            else if ( (LA125_0==DISTINCT) ) {
                alt125=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 125, 0, input);

                throw nvae;
            }
            switch (alt125) {
                case 1 :
                    // InternalSqlParser.g:6235:2: otherlv_0= ALL
                    {
                    otherlv_0=(Token)match(input,ALL,FOLLOW_56); 

                        	newLeafNode(otherlv_0, grammarAccess.getOpFunctionArgAgregateAccess().getALLKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6241:2: otherlv_1= DISTINCT
                    {
                    otherlv_1=(Token)match(input,DISTINCT,FOLLOW_56); 

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
    // InternalSqlParser.g:6262:1: entryRuleXOperandFragment returns [EObject current=null] : iv_ruleXOperandFragment= ruleXOperandFragment EOF ;
    public final EObject entryRuleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOperandFragment = null;


        try {
            // InternalSqlParser.g:6263:2: (iv_ruleXOperandFragment= ruleXOperandFragment EOF )
            // InternalSqlParser.g:6264:2: iv_ruleXOperandFragment= ruleXOperandFragment EOF
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
    // InternalSqlParser.g:6271:1: ruleXOperandFragment returns [EObject current=null] : ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) ) ;
    public final EObject ruleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_param_0_0 = null;

        EObject lv_eparam_1_0 = null;

        EObject lv_scalar_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6274:28: ( ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) ) )
            // InternalSqlParser.g:6275:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) )
            {
            // InternalSqlParser.g:6275:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) )
            int alt126=3;
            switch ( input.LA(1) ) {
            case RULE_JRPARAM:
                {
                alt126=1;
                }
                break;
            case RULE_JRNPARAM:
                {
                alt126=2;
                }
                break;
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt126=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 126, 0, input);

                throw nvae;
            }

            switch (alt126) {
                case 1 :
                    // InternalSqlParser.g:6275:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    {
                    // InternalSqlParser.g:6275:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    // InternalSqlParser.g:6276:1: (lv_param_0_0= ruleParameterOperand )
                    {
                    // InternalSqlParser.g:6276:1: (lv_param_0_0= ruleParameterOperand )
                    // InternalSqlParser.g:6277:3: lv_param_0_0= ruleParameterOperand
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
                    // InternalSqlParser.g:6294:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    {
                    // InternalSqlParser.g:6294:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    // InternalSqlParser.g:6295:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    {
                    // InternalSqlParser.g:6295:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    // InternalSqlParser.g:6296:3: lv_eparam_1_0= ruleExclamationParameterOperand
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
                    // InternalSqlParser.g:6313:6: ( (lv_scalar_2_0= ruleScalarNumberOperand ) )
                    {
                    // InternalSqlParser.g:6313:6: ( (lv_scalar_2_0= ruleScalarNumberOperand ) )
                    // InternalSqlParser.g:6314:1: (lv_scalar_2_0= ruleScalarNumberOperand )
                    {
                    // InternalSqlParser.g:6314:1: (lv_scalar_2_0= ruleScalarNumberOperand )
                    // InternalSqlParser.g:6315:3: lv_scalar_2_0= ruleScalarNumberOperand
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
    // InternalSqlParser.g:6339:1: entryRuleParameterOperand returns [EObject current=null] : iv_ruleParameterOperand= ruleParameterOperand EOF ;
    public final EObject entryRuleParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterOperand = null;


        try {
            // InternalSqlParser.g:6340:2: (iv_ruleParameterOperand= ruleParameterOperand EOF )
            // InternalSqlParser.g:6341:2: iv_ruleParameterOperand= ruleParameterOperand EOF
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
    // InternalSqlParser.g:6348:1: ruleParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRPARAM ) ) ;
    public final EObject ruleParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6351:28: ( ( (lv_prm_0_0= RULE_JRPARAM ) ) )
            // InternalSqlParser.g:6352:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            {
            // InternalSqlParser.g:6352:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            // InternalSqlParser.g:6353:1: (lv_prm_0_0= RULE_JRPARAM )
            {
            // InternalSqlParser.g:6353:1: (lv_prm_0_0= RULE_JRPARAM )
            // InternalSqlParser.g:6354:3: lv_prm_0_0= RULE_JRPARAM
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
    // InternalSqlParser.g:6378:1: entryRuleExclamationParameterOperand returns [EObject current=null] : iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF ;
    public final EObject entryRuleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclamationParameterOperand = null;


        try {
            // InternalSqlParser.g:6379:2: (iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF )
            // InternalSqlParser.g:6380:2: iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF
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
    // InternalSqlParser.g:6387:1: ruleExclamationParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRNPARAM ) ) ;
    public final EObject ruleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6390:28: ( ( (lv_prm_0_0= RULE_JRNPARAM ) ) )
            // InternalSqlParser.g:6391:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            {
            // InternalSqlParser.g:6391:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            // InternalSqlParser.g:6392:1: (lv_prm_0_0= RULE_JRNPARAM )
            {
            // InternalSqlParser.g:6392:1: (lv_prm_0_0= RULE_JRNPARAM )
            // InternalSqlParser.g:6393:3: lv_prm_0_0= RULE_JRNPARAM
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
    // InternalSqlParser.g:6417:1: entryRuleColumnOperand returns [EObject current=null] : iv_ruleColumnOperand= ruleColumnOperand EOF ;
    public final EObject entryRuleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOperand = null;


        try {
            // InternalSqlParser.g:6418:2: (iv_ruleColumnOperand= ruleColumnOperand EOF )
            // InternalSqlParser.g:6419:2: iv_ruleColumnOperand= ruleColumnOperand EOF
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
    // InternalSqlParser.g:6426:1: ruleColumnOperand returns [EObject current=null] : ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? ) ;
    public final EObject ruleColumnOperand() throws RecognitionException {
        EObject current = null;

        Token lv_ora_1_0=null;
        EObject lv_cfull_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6429:28: ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? ) )
            // InternalSqlParser.g:6430:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? )
            {
            // InternalSqlParser.g:6430:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )? )
            // InternalSqlParser.g:6430:2: ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )?
            {
            // InternalSqlParser.g:6430:2: ( (lv_cfull_0_0= ruleColumnFull ) )
            // InternalSqlParser.g:6431:1: (lv_cfull_0_0= ruleColumnFull )
            {
            // InternalSqlParser.g:6431:1: (lv_cfull_0_0= ruleColumnFull )
            // InternalSqlParser.g:6432:3: lv_cfull_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_83);
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

            // InternalSqlParser.g:6448:2: ( (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis ) )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==LeftParenthesisPlusSignRightParenthesis) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // InternalSqlParser.g:6449:1: (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis )
                    {
                    // InternalSqlParser.g:6449:1: (lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis )
                    // InternalSqlParser.g:6450:3: lv_ora_1_0= LeftParenthesisPlusSignRightParenthesis
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
    // InternalSqlParser.g:6472:1: entryRuleSubQueryOperand returns [EObject current=null] : iv_ruleSubQueryOperand= ruleSubQueryOperand EOF ;
    public final EObject entryRuleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubQueryOperand = null;


        try {
            // InternalSqlParser.g:6473:2: (iv_ruleSubQueryOperand= ruleSubQueryOperand EOF )
            // InternalSqlParser.g:6474:2: iv_ruleSubQueryOperand= ruleSubQueryOperand EOF
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
    // InternalSqlParser.g:6481:1: ruleSubQueryOperand returns [EObject current=null] : ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis ) ;
    public final EObject ruleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_sel_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6484:28: ( ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis ) )
            // InternalSqlParser.g:6485:1: ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis )
            {
            // InternalSqlParser.g:6485:1: ( () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis )
            // InternalSqlParser.g:6485:2: () otherlv_1= LeftParenthesis ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= RightParenthesis
            {
            // InternalSqlParser.g:6485:2: ()
            // InternalSqlParser.g:6486:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSubQueryOperandAccess().getSubQueryOperandAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_3); 

                	newLeafNode(otherlv_1, grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1());
                
            // InternalSqlParser.g:6496:1: ( (lv_sel_2_0= ruleSelectQuery ) )
            // InternalSqlParser.g:6497:1: (lv_sel_2_0= ruleSelectQuery )
            {
            // InternalSqlParser.g:6497:1: (lv_sel_2_0= ruleSelectQuery )
            // InternalSqlParser.g:6498:3: lv_sel_2_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getSubQueryOperandAccess().getSelSelectQueryParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_34);
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
    // InternalSqlParser.g:6527:1: entryRuleScalarOperand returns [EObject current=null] : iv_ruleScalarOperand= ruleScalarOperand EOF ;
    public final EObject entryRuleScalarOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarOperand = null;


        try {
            // InternalSqlParser.g:6528:2: (iv_ruleScalarOperand= ruleScalarOperand EOF )
            // InternalSqlParser.g:6529:2: iv_ruleScalarOperand= ruleScalarOperand EOF
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
    // InternalSqlParser.g:6536:1: ruleScalarOperand returns [EObject current=null] : ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) ) ;
    public final EObject ruleScalarOperand() throws RecognitionException {
        EObject current = null;

        Token lv_sodbl_1_0=null;
        Token lv_sodate_2_0=null;
        Token lv_sotime_3_0=null;
        Token lv_sodt_4_0=null;
        AntlrDatatypeRuleToken lv_sostr_0_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6539:28: ( ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) ) )
            // InternalSqlParser.g:6540:1: ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) )
            {
            // InternalSqlParser.g:6540:1: ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) )
            int alt128=5;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt128=1;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt128=2;
                }
                break;
            case RULE_DATE:
                {
                alt128=3;
                }
                break;
            case RULE_TIME:
                {
                alt128=4;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt128=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 128, 0, input);

                throw nvae;
            }

            switch (alt128) {
                case 1 :
                    // InternalSqlParser.g:6540:2: ( (lv_sostr_0_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:6540:2: ( (lv_sostr_0_0= ruleStringOperand ) )
                    // InternalSqlParser.g:6541:1: (lv_sostr_0_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:6541:1: (lv_sostr_0_0= ruleStringOperand )
                    // InternalSqlParser.g:6542:3: lv_sostr_0_0= ruleStringOperand
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
                    // InternalSqlParser.g:6559:6: ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // InternalSqlParser.g:6559:6: ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) )
                    // InternalSqlParser.g:6560:1: (lv_sodbl_1_0= RULE_SIGNED_DOUBLE )
                    {
                    // InternalSqlParser.g:6560:1: (lv_sodbl_1_0= RULE_SIGNED_DOUBLE )
                    // InternalSqlParser.g:6561:3: lv_sodbl_1_0= RULE_SIGNED_DOUBLE
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
                    // InternalSqlParser.g:6578:6: ( (lv_sodate_2_0= RULE_DATE ) )
                    {
                    // InternalSqlParser.g:6578:6: ( (lv_sodate_2_0= RULE_DATE ) )
                    // InternalSqlParser.g:6579:1: (lv_sodate_2_0= RULE_DATE )
                    {
                    // InternalSqlParser.g:6579:1: (lv_sodate_2_0= RULE_DATE )
                    // InternalSqlParser.g:6580:3: lv_sodate_2_0= RULE_DATE
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
                    // InternalSqlParser.g:6597:6: ( (lv_sotime_3_0= RULE_TIME ) )
                    {
                    // InternalSqlParser.g:6597:6: ( (lv_sotime_3_0= RULE_TIME ) )
                    // InternalSqlParser.g:6598:1: (lv_sotime_3_0= RULE_TIME )
                    {
                    // InternalSqlParser.g:6598:1: (lv_sotime_3_0= RULE_TIME )
                    // InternalSqlParser.g:6599:3: lv_sotime_3_0= RULE_TIME
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
                    // InternalSqlParser.g:6616:6: ( (lv_sodt_4_0= RULE_TIMESTAMP ) )
                    {
                    // InternalSqlParser.g:6616:6: ( (lv_sodt_4_0= RULE_TIMESTAMP ) )
                    // InternalSqlParser.g:6617:1: (lv_sodt_4_0= RULE_TIMESTAMP )
                    {
                    // InternalSqlParser.g:6617:1: (lv_sodt_4_0= RULE_TIMESTAMP )
                    // InternalSqlParser.g:6618:3: lv_sodt_4_0= RULE_TIMESTAMP
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
    // InternalSqlParser.g:6642:1: entryRuleScalarNumberOperand returns [EObject current=null] : iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF ;
    public final EObject entryRuleScalarNumberOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarNumberOperand = null;


        try {
            // InternalSqlParser.g:6643:2: (iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF )
            // InternalSqlParser.g:6644:2: iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF
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
    // InternalSqlParser.g:6651:1: ruleScalarNumberOperand returns [EObject current=null] : ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) ) ;
    public final EObject ruleScalarNumberOperand() throws RecognitionException {
        EObject current = null;

        Token lv_soUInt_0_0=null;
        Token lv_soint_1_0=null;
        Token lv_sodbl_2_0=null;
        AntlrDatatypeRuleToken lv_sostr_3_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6654:28: ( ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) ) )
            // InternalSqlParser.g:6655:1: ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) )
            {
            // InternalSqlParser.g:6655:1: ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) )
            int alt129=4;
            switch ( input.LA(1) ) {
            case RULE_UNSIGNED:
                {
                alt129=1;
                }
                break;
            case RULE_INT:
                {
                alt129=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt129=3;
                }
                break;
            case RULE_STRING_:
                {
                alt129=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 129, 0, input);

                throw nvae;
            }

            switch (alt129) {
                case 1 :
                    // InternalSqlParser.g:6655:2: ( (lv_soUInt_0_0= RULE_UNSIGNED ) )
                    {
                    // InternalSqlParser.g:6655:2: ( (lv_soUInt_0_0= RULE_UNSIGNED ) )
                    // InternalSqlParser.g:6656:1: (lv_soUInt_0_0= RULE_UNSIGNED )
                    {
                    // InternalSqlParser.g:6656:1: (lv_soUInt_0_0= RULE_UNSIGNED )
                    // InternalSqlParser.g:6657:3: lv_soUInt_0_0= RULE_UNSIGNED
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
                    // InternalSqlParser.g:6674:6: ( (lv_soint_1_0= RULE_INT ) )
                    {
                    // InternalSqlParser.g:6674:6: ( (lv_soint_1_0= RULE_INT ) )
                    // InternalSqlParser.g:6675:1: (lv_soint_1_0= RULE_INT )
                    {
                    // InternalSqlParser.g:6675:1: (lv_soint_1_0= RULE_INT )
                    // InternalSqlParser.g:6676:3: lv_soint_1_0= RULE_INT
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
                    // InternalSqlParser.g:6693:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // InternalSqlParser.g:6693:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    // InternalSqlParser.g:6694:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    {
                    // InternalSqlParser.g:6694:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    // InternalSqlParser.g:6695:3: lv_sodbl_2_0= RULE_SIGNED_DOUBLE
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
                    // InternalSqlParser.g:6712:6: ( (lv_sostr_3_0= ruleStringOperand ) )
                    {
                    // InternalSqlParser.g:6712:6: ( (lv_sostr_3_0= ruleStringOperand ) )
                    // InternalSqlParser.g:6713:1: (lv_sostr_3_0= ruleStringOperand )
                    {
                    // InternalSqlParser.g:6713:1: (lv_sostr_3_0= ruleStringOperand )
                    // InternalSqlParser.g:6714:3: lv_sostr_3_0= ruleStringOperand
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
    // InternalSqlParser.g:6738:1: entryRuleSQLCASE returns [EObject current=null] : iv_ruleSQLCASE= ruleSQLCASE EOF ;
    public final EObject entryRuleSQLCASE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCASE = null;


        try {
            // InternalSqlParser.g:6739:2: (iv_ruleSQLCASE= ruleSQLCASE EOF )
            // InternalSqlParser.g:6740:2: iv_ruleSQLCASE= ruleSQLCASE EOF
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
    // InternalSqlParser.g:6747:1: ruleSQLCASE returns [EObject current=null] : (otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END ) ;
    public final EObject ruleSQLCASE() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_expr_1_0 = null;

        EObject lv_when_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6750:28: ( (otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END ) )
            // InternalSqlParser.g:6751:1: (otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END )
            {
            // InternalSqlParser.g:6751:1: (otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END )
            // InternalSqlParser.g:6752:2: otherlv_0= CASE ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= END
            {
            otherlv_0=(Token)match(input,CASE,FOLLOW_84); 

                	newLeafNode(otherlv_0, grammarAccess.getSQLCASEAccess().getCASEKeyword_0());
                
            // InternalSqlParser.g:6756:1: ( (lv_expr_1_0= ruleFullExpression ) )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==NOTEXISTS||LA130_0==EXTRACT||LA130_0==EXISTS||LA130_0==NOTIN_1||LA130_0==CAST||LA130_0==CASE||(LA130_0>=NOT && LA130_0<=NOT_1)||LA130_0==X||LA130_0==IN||LA130_0==LeftParenthesis||(LA130_0>=RULE_JRPARAM && LA130_0<=RULE_JRNPARAM)||(LA130_0>=RULE_UNSIGNED && LA130_0<=RULE_SIGNED_DOUBLE)||(LA130_0>=RULE_STRING_ && LA130_0<=RULE_ID)) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // InternalSqlParser.g:6757:1: (lv_expr_1_0= ruleFullExpression )
                    {
                    // InternalSqlParser.g:6757:1: (lv_expr_1_0= ruleFullExpression )
                    // InternalSqlParser.g:6758:3: lv_expr_1_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSQLCASEAccess().getExprFullExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_84);
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

            // InternalSqlParser.g:6774:3: ( (lv_when_2_0= ruleSQLCaseWhens ) )
            // InternalSqlParser.g:6775:1: (lv_when_2_0= ruleSQLCaseWhens )
            {
            // InternalSqlParser.g:6775:1: (lv_when_2_0= ruleSQLCaseWhens )
            // InternalSqlParser.g:6776:3: lv_when_2_0= ruleSQLCaseWhens
            {
             
            	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWhenSQLCaseWhensParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_85);
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
    // InternalSqlParser.g:6805:1: entryRuleSQLCaseWhens returns [EObject current=null] : iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF ;
    public final EObject entryRuleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCaseWhens = null;


        try {
            // InternalSqlParser.g:6806:2: (iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF )
            // InternalSqlParser.g:6807:2: iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF
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
    // InternalSqlParser.g:6814:1: ruleSQLCaseWhens returns [EObject current=null] : (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) ;
    public final EObject ruleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject this_SqlCaseWhen_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // InternalSqlParser.g:6817:28: ( (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) )
            // InternalSqlParser.g:6818:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            {
            // InternalSqlParser.g:6818:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            // InternalSqlParser.g:6819:5: this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getSqlCaseWhenParserRuleCall_0()); 
                
            pushFollow(FOLLOW_86);
            this_SqlCaseWhen_0=ruleSqlCaseWhen();

            state._fsp--;


                    current = this_SqlCaseWhen_0;
                    afterParserOrEnumRuleCall();
                
            // InternalSqlParser.g:6827:1: ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==WHEN) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // InternalSqlParser.g:6827:2: () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    {
                    // InternalSqlParser.g:6827:2: ()
                    // InternalSqlParser.g:6828:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getSQLCaseWhensAccess().getWhenListEntriesAction_1_0(),
                                current);
                        

                    }

                    // InternalSqlParser.g:6833:2: ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    int cnt131=0;
                    loop131:
                    do {
                        int alt131=2;
                        int LA131_0 = input.LA(1);

                        if ( (LA131_0==WHEN) ) {
                            alt131=1;
                        }


                        switch (alt131) {
                    	case 1 :
                    	    // InternalSqlParser.g:6834:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    {
                    	    // InternalSqlParser.g:6834:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    // InternalSqlParser.g:6835:3: lv_entries_2_0= ruleSqlCaseWhen
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getEntriesSqlCaseWhenParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_86);
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
                    	    if ( cnt131 >= 1 ) break loop131;
                                EarlyExitException eee =
                                    new EarlyExitException(131, input);
                                throw eee;
                        }
                        cnt131++;
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
    // InternalSqlParser.g:6859:1: entryRuleSqlCaseWhen returns [EObject current=null] : iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF ;
    public final EObject entryRuleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSqlCaseWhen = null;


        try {
            // InternalSqlParser.g:6860:2: (iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF )
            // InternalSqlParser.g:6861:2: iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF
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
    // InternalSqlParser.g:6868:1: ruleSqlCaseWhen returns [EObject current=null] : (otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )? ) ;
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
            // InternalSqlParser.g:6871:28: ( (otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )? ) )
            // InternalSqlParser.g:6872:1: (otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )? )
            {
            // InternalSqlParser.g:6872:1: (otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )? )
            // InternalSqlParser.g:6873:2: otherlv_0= WHEN ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= THEN ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )?
            {
            otherlv_0=(Token)match(input,WHEN,FOLLOW_17); 

                	newLeafNode(otherlv_0, grammarAccess.getSqlCaseWhenAccess().getWHENKeyword_0());
                
            // InternalSqlParser.g:6877:1: ( (lv_expr_1_0= ruleFullExpression ) )
            // InternalSqlParser.g:6878:1: (lv_expr_1_0= ruleFullExpression )
            {
            // InternalSqlParser.g:6878:1: (lv_expr_1_0= ruleFullExpression )
            // InternalSqlParser.g:6879:3: lv_expr_1_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getExprFullExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_87);
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

            otherlv_2=(Token)match(input,THEN,FOLLOW_56); 

                	newLeafNode(otherlv_2, grammarAccess.getSqlCaseWhenAccess().getTHENKeyword_2());
                
            // InternalSqlParser.g:6900:1: ( (lv_texp_3_0= ruleOperandGroup ) )
            // InternalSqlParser.g:6901:1: (lv_texp_3_0= ruleOperandGroup )
            {
            // InternalSqlParser.g:6901:1: (lv_texp_3_0= ruleOperandGroup )
            // InternalSqlParser.g:6902:3: lv_texp_3_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getTexpOperandGroupParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_88);
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

            // InternalSqlParser.g:6918:2: (otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) ) )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==ELSE) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // InternalSqlParser.g:6919:2: otherlv_4= ELSE ( (lv_eexp_5_0= ruleOperandGroup ) )
                    {
                    otherlv_4=(Token)match(input,ELSE,FOLLOW_56); 

                        	newLeafNode(otherlv_4, grammarAccess.getSqlCaseWhenAccess().getELSEKeyword_4_0());
                        
                    // InternalSqlParser.g:6923:1: ( (lv_eexp_5_0= ruleOperandGroup ) )
                    // InternalSqlParser.g:6924:1: (lv_eexp_5_0= ruleOperandGroup )
                    {
                    // InternalSqlParser.g:6924:1: (lv_eexp_5_0= ruleOperandGroup )
                    // InternalSqlParser.g:6925:3: lv_eexp_5_0= ruleOperandGroup
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
    // InternalSqlParser.g:6949:1: entryRuleJoinType returns [String current=null] : iv_ruleJoinType= ruleJoinType EOF ;
    public final String entryRuleJoinType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleJoinType = null;


        try {
            // InternalSqlParser.g:6950:1: (iv_ruleJoinType= ruleJoinType EOF )
            // InternalSqlParser.g:6951:2: iv_ruleJoinType= ruleJoinType EOF
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
    // InternalSqlParser.g:6958:1: ruleJoinType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN ) ;
    public final AntlrDatatypeRuleToken ruleJoinType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:6962:6: ( ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN ) )
            // InternalSqlParser.g:6963:1: ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN )
            {
            // InternalSqlParser.g:6963:1: ( (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN )
            // InternalSqlParser.g:6963:2: (kw= NATURAL )? (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )? kw= JOIN
            {
            // InternalSqlParser.g:6963:2: (kw= NATURAL )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==NATURAL) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // InternalSqlParser.g:6964:2: kw= NATURAL
                    {
                    kw=(Token)match(input,NATURAL,FOLLOW_89); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getNATURALKeyword_0()); 
                        

                    }
                    break;

            }

            // InternalSqlParser.g:6969:3: (kw= INNER | ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? ) | kw= CROSS | kw= STRAIGHT_JOIN )?
            int alt137=5;
            switch ( input.LA(1) ) {
                case INNER:
                    {
                    alt137=1;
                    }
                    break;
                case RIGHT:
                case FULL:
                case LEFT:
                    {
                    alt137=2;
                    }
                    break;
                case CROSS:
                    {
                    alt137=3;
                    }
                    break;
                case STRAIGHT_JOIN:
                    {
                    alt137=4;
                    }
                    break;
            }

            switch (alt137) {
                case 1 :
                    // InternalSqlParser.g:6970:2: kw= INNER
                    {
                    kw=(Token)match(input,INNER,FOLLOW_90); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getINNERKeyword_1_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:6976:6: ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? )
                    {
                    // InternalSqlParser.g:6976:6: ( (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )? )
                    // InternalSqlParser.g:6976:7: (kw= LEFT | kw= RIGHT | kw= FULL ) (kw= OUTER )?
                    {
                    // InternalSqlParser.g:6976:7: (kw= LEFT | kw= RIGHT | kw= FULL )
                    int alt135=3;
                    switch ( input.LA(1) ) {
                    case LEFT:
                        {
                        alt135=1;
                        }
                        break;
                    case RIGHT:
                        {
                        alt135=2;
                        }
                        break;
                    case FULL:
                        {
                        alt135=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 135, 0, input);

                        throw nvae;
                    }

                    switch (alt135) {
                        case 1 :
                            // InternalSqlParser.g:6977:2: kw= LEFT
                            {
                            kw=(Token)match(input,LEFT,FOLLOW_91); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getLEFTKeyword_1_1_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // InternalSqlParser.g:6984:2: kw= RIGHT
                            {
                            kw=(Token)match(input,RIGHT,FOLLOW_91); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getRIGHTKeyword_1_1_0_1()); 
                                

                            }
                            break;
                        case 3 :
                            // InternalSqlParser.g:6991:2: kw= FULL
                            {
                            kw=(Token)match(input,FULL,FOLLOW_91); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getFULLKeyword_1_1_0_2()); 
                                

                            }
                            break;

                    }

                    // InternalSqlParser.g:6996:2: (kw= OUTER )?
                    int alt136=2;
                    int LA136_0 = input.LA(1);

                    if ( (LA136_0==OUTER) ) {
                        alt136=1;
                    }
                    switch (alt136) {
                        case 1 :
                            // InternalSqlParser.g:6997:2: kw= OUTER
                            {
                            kw=(Token)match(input,OUTER,FOLLOW_90); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getOUTERKeyword_1_1_1()); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7004:2: kw= CROSS
                    {
                    kw=(Token)match(input,CROSS,FOLLOW_90); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getCROSSKeyword_1_2()); 
                        

                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:7011:2: kw= STRAIGHT_JOIN
                    {
                    kw=(Token)match(input,STRAIGHT_JOIN,FOLLOW_90); 

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
    // InternalSqlParser.g:7030:1: entryRuleDBID returns [String current=null] : iv_ruleDBID= ruleDBID EOF ;
    public final String entryRuleDBID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDBID = null;


        try {
            // InternalSqlParser.g:7031:1: (iv_ruleDBID= ruleDBID EOF )
            // InternalSqlParser.g:7032:2: iv_ruleDBID= ruleDBID EOF
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
    // InternalSqlParser.g:7039:1: ruleDBID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDBID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_DBNAME_1=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7043:6: ( (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) )
            // InternalSqlParser.g:7044:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            {
            // InternalSqlParser.g:7044:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            int alt138=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt138=1;
                }
                break;
            case RULE_DBNAME:
                {
                alt138=2;
                }
                break;
            case RULE_STRING:
                {
                alt138=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 138, 0, input);

                throw nvae;
            }

            switch (alt138) {
                case 1 :
                    // InternalSqlParser.g:7044:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getDBIDAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7052:10: this_DBNAME_1= RULE_DBNAME
                    {
                    this_DBNAME_1=(Token)match(input,RULE_DBNAME,FOLLOW_2); 

                    		current.merge(this_DBNAME_1);
                        
                     
                        newLeafNode(this_DBNAME_1, grammarAccess.getDBIDAccess().getDBNAMETerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7060:10: this_STRING_2= RULE_STRING
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
    // InternalSqlParser.g:7075:1: entryRuleStringOperand returns [String current=null] : iv_ruleStringOperand= ruleStringOperand EOF ;
    public final String entryRuleStringOperand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringOperand = null;


        try {
            // InternalSqlParser.g:7076:1: (iv_ruleStringOperand= ruleStringOperand EOF )
            // InternalSqlParser.g:7077:2: iv_ruleStringOperand= ruleStringOperand EOF
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
    // InternalSqlParser.g:7084:1: ruleStringOperand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING__0= RULE_STRING_ ;
    public final AntlrDatatypeRuleToken ruleStringOperand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING__0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7088:6: (this_STRING__0= RULE_STRING_ )
            // InternalSqlParser.g:7089:5: this_STRING__0= RULE_STRING_
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
    // InternalSqlParser.g:7104:1: entryRuleFNAME returns [String current=null] : iv_ruleFNAME= ruleFNAME EOF ;
    public final String entryRuleFNAME() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFNAME = null;


        try {
            // InternalSqlParser.g:7105:1: (iv_ruleFNAME= ruleFNAME EOF )
            // InternalSqlParser.g:7106:2: iv_ruleFNAME= ruleFNAME EOF
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
    // InternalSqlParser.g:7113:1: ruleFNAME returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= LeftParenthesis ) ;
    public final AntlrDatatypeRuleToken ruleFNAME() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7117:6: ( (this_ID_0= RULE_ID kw= LeftParenthesis ) )
            // InternalSqlParser.g:7118:1: (this_ID_0= RULE_ID kw= LeftParenthesis )
            {
            // InternalSqlParser.g:7118:1: (this_ID_0= RULE_ID kw= LeftParenthesis )
            // InternalSqlParser.g:7118:6: this_ID_0= RULE_ID kw= LeftParenthesis
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_33); 

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


    // $ANTLR start "entryRuleUnsignedValue"
    // InternalSqlParser.g:7141:1: entryRuleUnsignedValue returns [EObject current=null] : iv_ruleUnsignedValue= ruleUnsignedValue EOF ;
    public final EObject entryRuleUnsignedValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnsignedValue = null;


        try {
            // InternalSqlParser.g:7142:2: (iv_ruleUnsignedValue= ruleUnsignedValue EOF )
            // InternalSqlParser.g:7143:2: iv_ruleUnsignedValue= ruleUnsignedValue EOF
            {
             newCompositeNode(grammarAccess.getUnsignedValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnsignedValue=ruleUnsignedValue();

            state._fsp--;

             current =iv_ruleUnsignedValue; 
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
    // $ANTLR end "entryRuleUnsignedValue"


    // $ANTLR start "ruleUnsignedValue"
    // InternalSqlParser.g:7150:1: ruleUnsignedValue returns [EObject current=null] : ( (lv_integer_0_0= RULE_UNSIGNED ) ) ;
    public final EObject ruleUnsignedValue() throws RecognitionException {
        EObject current = null;

        Token lv_integer_0_0=null;

         enterRule(); 
            
        try {
            // InternalSqlParser.g:7153:28: ( ( (lv_integer_0_0= RULE_UNSIGNED ) ) )
            // InternalSqlParser.g:7154:1: ( (lv_integer_0_0= RULE_UNSIGNED ) )
            {
            // InternalSqlParser.g:7154:1: ( (lv_integer_0_0= RULE_UNSIGNED ) )
            // InternalSqlParser.g:7155:1: (lv_integer_0_0= RULE_UNSIGNED )
            {
            // InternalSqlParser.g:7155:1: (lv_integer_0_0= RULE_UNSIGNED )
            // InternalSqlParser.g:7156:3: lv_integer_0_0= RULE_UNSIGNED
            {
            lv_integer_0_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_2); 

            			newLeafNode(lv_integer_0_0, grammarAccess.getUnsignedValueAccess().getIntegerUNSIGNEDTerminalRuleCall_0()); 
            		

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
    // $ANTLR end "ruleUnsignedValue"


    // $ANTLR start "ruleEXTRACT_VALUES"
    // InternalSqlParser.g:7180:1: ruleEXTRACT_VALUES returns [Enumerator current=null] : ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) ) ;
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
            // InternalSqlParser.g:7182:28: ( ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) ) )
            // InternalSqlParser.g:7183:1: ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) )
            {
            // InternalSqlParser.g:7183:1: ( (enumLiteral_0= MICROSECOND ) | (enumLiteral_1= SECOND ) | (enumLiteral_2= MINUTE ) | (enumLiteral_3= HOUR ) | (enumLiteral_4= DAY ) | (enumLiteral_5= WEEK ) | (enumLiteral_6= MONTH ) | (enumLiteral_7= QUARTER ) | (enumLiteral_8= YEAR ) | (enumLiteral_9= SECOND_MICROSECOND ) | (enumLiteral_10= MINUTE_MICROSECOND ) | (enumLiteral_11= MINUTE_SECOND ) | (enumLiteral_12= HOUR_MICROSECOND ) | (enumLiteral_13= HOUR_SECOND ) | (enumLiteral_14= HOUR_MINUTE ) | (enumLiteral_15= DAY_MICROSECOND ) | (enumLiteral_16= DAY_SECOND ) | (enumLiteral_17= DAY_MINUTE ) | (enumLiteral_18= DAY_HOUR ) | (enumLiteral_19= YEAR_MONTH ) )
            int alt139=20;
            switch ( input.LA(1) ) {
            case MICROSECOND:
                {
                alt139=1;
                }
                break;
            case SECOND:
                {
                alt139=2;
                }
                break;
            case MINUTE:
                {
                alt139=3;
                }
                break;
            case HOUR:
                {
                alt139=4;
                }
                break;
            case DAY:
                {
                alt139=5;
                }
                break;
            case WEEK:
                {
                alt139=6;
                }
                break;
            case MONTH:
                {
                alt139=7;
                }
                break;
            case QUARTER:
                {
                alt139=8;
                }
                break;
            case YEAR:
                {
                alt139=9;
                }
                break;
            case SECOND_MICROSECOND:
                {
                alt139=10;
                }
                break;
            case MINUTE_MICROSECOND:
                {
                alt139=11;
                }
                break;
            case MINUTE_SECOND:
                {
                alt139=12;
                }
                break;
            case HOUR_MICROSECOND:
                {
                alt139=13;
                }
                break;
            case HOUR_SECOND:
                {
                alt139=14;
                }
                break;
            case HOUR_MINUTE:
                {
                alt139=15;
                }
                break;
            case DAY_MICROSECOND:
                {
                alt139=16;
                }
                break;
            case DAY_SECOND:
                {
                alt139=17;
                }
                break;
            case DAY_MINUTE:
                {
                alt139=18;
                }
                break;
            case DAY_HOUR:
                {
                alt139=19;
                }
                break;
            case YEAR_MONTH:
                {
                alt139=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 139, 0, input);

                throw nvae;
            }

            switch (alt139) {
                case 1 :
                    // InternalSqlParser.g:7183:2: (enumLiteral_0= MICROSECOND )
                    {
                    // InternalSqlParser.g:7183:2: (enumLiteral_0= MICROSECOND )
                    // InternalSqlParser.g:7183:7: enumLiteral_0= MICROSECOND
                    {
                    enumLiteral_0=(Token)match(input,MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMsEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getEXTRACT_VALUESAccess().getMsEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7189:6: (enumLiteral_1= SECOND )
                    {
                    // InternalSqlParser.g:7189:6: (enumLiteral_1= SECOND )
                    // InternalSqlParser.g:7189:11: enumLiteral_1= SECOND
                    {
                    enumLiteral_1=(Token)match(input,SECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getEXTRACT_VALUESAccess().getSEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7195:6: (enumLiteral_2= MINUTE )
                    {
                    // InternalSqlParser.g:7195:6: (enumLiteral_2= MINUTE )
                    // InternalSqlParser.g:7195:11: enumLiteral_2= MINUTE
                    {
                    enumLiteral_2=(Token)match(input,MINUTE,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getEXTRACT_VALUESAccess().getMEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:7201:6: (enumLiteral_3= HOUR )
                    {
                    // InternalSqlParser.g:7201:6: (enumLiteral_3= HOUR )
                    // InternalSqlParser.g:7201:11: enumLiteral_3= HOUR
                    {
                    enumLiteral_3=(Token)match(input,HOUR,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getEXTRACT_VALUESAccess().getHEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:7207:6: (enumLiteral_4= DAY )
                    {
                    // InternalSqlParser.g:7207:6: (enumLiteral_4= DAY )
                    // InternalSqlParser.g:7207:11: enumLiteral_4= DAY
                    {
                    enumLiteral_4=(Token)match(input,DAY,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getEXTRACT_VALUESAccess().getDayEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:7213:6: (enumLiteral_5= WEEK )
                    {
                    // InternalSqlParser.g:7213:6: (enumLiteral_5= WEEK )
                    // InternalSqlParser.g:7213:11: enumLiteral_5= WEEK
                    {
                    enumLiteral_5=(Token)match(input,WEEK,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getWeekEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getEXTRACT_VALUESAccess().getWeekEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:7219:6: (enumLiteral_6= MONTH )
                    {
                    // InternalSqlParser.g:7219:6: (enumLiteral_6= MONTH )
                    // InternalSqlParser.g:7219:11: enumLiteral_6= MONTH
                    {
                    enumLiteral_6=(Token)match(input,MONTH,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMonthEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getEXTRACT_VALUESAccess().getMonthEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:7225:6: (enumLiteral_7= QUARTER )
                    {
                    // InternalSqlParser.g:7225:6: (enumLiteral_7= QUARTER )
                    // InternalSqlParser.g:7225:11: enumLiteral_7= QUARTER
                    {
                    enumLiteral_7=(Token)match(input,QUARTER,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getQuartEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getEXTRACT_VALUESAccess().getQuartEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // InternalSqlParser.g:7231:6: (enumLiteral_8= YEAR )
                    {
                    // InternalSqlParser.g:7231:6: (enumLiteral_8= YEAR )
                    // InternalSqlParser.g:7231:11: enumLiteral_8= YEAR
                    {
                    enumLiteral_8=(Token)match(input,YEAR,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getYearEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getEXTRACT_VALUESAccess().getYearEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // InternalSqlParser.g:7237:6: (enumLiteral_9= SECOND_MICROSECOND )
                    {
                    // InternalSqlParser.g:7237:6: (enumLiteral_9= SECOND_MICROSECOND )
                    // InternalSqlParser.g:7237:11: enumLiteral_9= SECOND_MICROSECOND
                    {
                    enumLiteral_9=(Token)match(input,SECOND_MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMicrosEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getEXTRACT_VALUESAccess().getMicrosEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // InternalSqlParser.g:7243:6: (enumLiteral_10= MINUTE_MICROSECOND )
                    {
                    // InternalSqlParser.g:7243:6: (enumLiteral_10= MINUTE_MICROSECOND )
                    // InternalSqlParser.g:7243:11: enumLiteral_10= MINUTE_MICROSECOND
                    {
                    enumLiteral_10=(Token)match(input,MINUTE_MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMinMicroEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getEXTRACT_VALUESAccess().getMinMicroEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // InternalSqlParser.g:7249:6: (enumLiteral_11= MINUTE_SECOND )
                    {
                    // InternalSqlParser.g:7249:6: (enumLiteral_11= MINUTE_SECOND )
                    // InternalSqlParser.g:7249:11: enumLiteral_11= MINUTE_SECOND
                    {
                    enumLiteral_11=(Token)match(input,MINUTE_SECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMinSecEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_11, grammarAccess.getEXTRACT_VALUESAccess().getMinSecEnumLiteralDeclaration_11()); 
                        

                    }


                    }
                    break;
                case 13 :
                    // InternalSqlParser.g:7255:6: (enumLiteral_12= HOUR_MICROSECOND )
                    {
                    // InternalSqlParser.g:7255:6: (enumLiteral_12= HOUR_MICROSECOND )
                    // InternalSqlParser.g:7255:11: enumLiteral_12= HOUR_MICROSECOND
                    {
                    enumLiteral_12=(Token)match(input,HOUR_MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHmsEnumLiteralDeclaration_12().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_12, grammarAccess.getEXTRACT_VALUESAccess().getHmsEnumLiteralDeclaration_12()); 
                        

                    }


                    }
                    break;
                case 14 :
                    // InternalSqlParser.g:7261:6: (enumLiteral_13= HOUR_SECOND )
                    {
                    // InternalSqlParser.g:7261:6: (enumLiteral_13= HOUR_SECOND )
                    // InternalSqlParser.g:7261:11: enumLiteral_13= HOUR_SECOND
                    {
                    enumLiteral_13=(Token)match(input,HOUR_SECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHsEnumLiteralDeclaration_13().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_13, grammarAccess.getEXTRACT_VALUESAccess().getHsEnumLiteralDeclaration_13()); 
                        

                    }


                    }
                    break;
                case 15 :
                    // InternalSqlParser.g:7267:6: (enumLiteral_14= HOUR_MINUTE )
                    {
                    // InternalSqlParser.g:7267:6: (enumLiteral_14= HOUR_MINUTE )
                    // InternalSqlParser.g:7267:11: enumLiteral_14= HOUR_MINUTE
                    {
                    enumLiteral_14=(Token)match(input,HOUR_MINUTE,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHminEnumLiteralDeclaration_14().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_14, grammarAccess.getEXTRACT_VALUESAccess().getHminEnumLiteralDeclaration_14()); 
                        

                    }


                    }
                    break;
                case 16 :
                    // InternalSqlParser.g:7273:6: (enumLiteral_15= DAY_MICROSECOND )
                    {
                    // InternalSqlParser.g:7273:6: (enumLiteral_15= DAY_MICROSECOND )
                    // InternalSqlParser.g:7273:11: enumLiteral_15= DAY_MICROSECOND
                    {
                    enumLiteral_15=(Token)match(input,DAY_MICROSECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDmsEnumLiteralDeclaration_15().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_15, grammarAccess.getEXTRACT_VALUESAccess().getDmsEnumLiteralDeclaration_15()); 
                        

                    }


                    }
                    break;
                case 17 :
                    // InternalSqlParser.g:7279:6: (enumLiteral_16= DAY_SECOND )
                    {
                    // InternalSqlParser.g:7279:6: (enumLiteral_16= DAY_SECOND )
                    // InternalSqlParser.g:7279:11: enumLiteral_16= DAY_SECOND
                    {
                    enumLiteral_16=(Token)match(input,DAY_SECOND,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDsEnumLiteralDeclaration_16().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_16, grammarAccess.getEXTRACT_VALUESAccess().getDsEnumLiteralDeclaration_16()); 
                        

                    }


                    }
                    break;
                case 18 :
                    // InternalSqlParser.g:7285:6: (enumLiteral_17= DAY_MINUTE )
                    {
                    // InternalSqlParser.g:7285:6: (enumLiteral_17= DAY_MINUTE )
                    // InternalSqlParser.g:7285:11: enumLiteral_17= DAY_MINUTE
                    {
                    enumLiteral_17=(Token)match(input,DAY_MINUTE,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayminEnumLiteralDeclaration_17().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_17, grammarAccess.getEXTRACT_VALUESAccess().getDayminEnumLiteralDeclaration_17()); 
                        

                    }


                    }
                    break;
                case 19 :
                    // InternalSqlParser.g:7291:6: (enumLiteral_18= DAY_HOUR )
                    {
                    // InternalSqlParser.g:7291:6: (enumLiteral_18= DAY_HOUR )
                    // InternalSqlParser.g:7291:11: enumLiteral_18= DAY_HOUR
                    {
                    enumLiteral_18=(Token)match(input,DAY_HOUR,FOLLOW_2); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayhEnumLiteralDeclaration_18().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_18, grammarAccess.getEXTRACT_VALUESAccess().getDayhEnumLiteralDeclaration_18()); 
                        

                    }


                    }
                    break;
                case 20 :
                    // InternalSqlParser.g:7297:6: (enumLiteral_19= YEAR_MONTH )
                    {
                    // InternalSqlParser.g:7297:6: (enumLiteral_19= YEAR_MONTH )
                    // InternalSqlParser.g:7297:11: enumLiteral_19= YEAR_MONTH
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
    // InternalSqlParser.g:7307:1: ruleXFunction returns [Enumerator current=null] : ( (enumLiteral_0= IN ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= GREATER ) | (enumLiteral_6= LESS_1 ) | (enumLiteral_7= GREATER_1 ) | (enumLiteral_8= BETWEEN ) | (enumLiteral_9= BETWEEN_3 ) | (enumLiteral_10= BETWEEN_2 ) | (enumLiteral_11= BETWEEN_1 ) ) ;
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
            // InternalSqlParser.g:7309:28: ( ( (enumLiteral_0= IN ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= GREATER ) | (enumLiteral_6= LESS_1 ) | (enumLiteral_7= GREATER_1 ) | (enumLiteral_8= BETWEEN ) | (enumLiteral_9= BETWEEN_3 ) | (enumLiteral_10= BETWEEN_2 ) | (enumLiteral_11= BETWEEN_1 ) ) )
            // InternalSqlParser.g:7310:1: ( (enumLiteral_0= IN ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= GREATER ) | (enumLiteral_6= LESS_1 ) | (enumLiteral_7= GREATER_1 ) | (enumLiteral_8= BETWEEN ) | (enumLiteral_9= BETWEEN_3 ) | (enumLiteral_10= BETWEEN_2 ) | (enumLiteral_11= BETWEEN_1 ) )
            {
            // InternalSqlParser.g:7310:1: ( (enumLiteral_0= IN ) | (enumLiteral_1= NOTIN ) | (enumLiteral_2= EQUAL ) | (enumLiteral_3= NOTEQUAL ) | (enumLiteral_4= LESS ) | (enumLiteral_5= GREATER ) | (enumLiteral_6= LESS_1 ) | (enumLiteral_7= GREATER_1 ) | (enumLiteral_8= BETWEEN ) | (enumLiteral_9= BETWEEN_3 ) | (enumLiteral_10= BETWEEN_2 ) | (enumLiteral_11= BETWEEN_1 ) )
            int alt140=12;
            switch ( input.LA(1) ) {
            case IN:
                {
                alt140=1;
                }
                break;
            case NOTIN:
                {
                alt140=2;
                }
                break;
            case EQUAL:
                {
                alt140=3;
                }
                break;
            case NOTEQUAL:
                {
                alt140=4;
                }
                break;
            case LESS:
                {
                alt140=5;
                }
                break;
            case GREATER:
                {
                alt140=6;
                }
                break;
            case LESS_1:
                {
                alt140=7;
                }
                break;
            case GREATER_1:
                {
                alt140=8;
                }
                break;
            case BETWEEN:
                {
                alt140=9;
                }
                break;
            case BETWEEN_3:
                {
                alt140=10;
                }
                break;
            case BETWEEN_2:
                {
                alt140=11;
                }
                break;
            case BETWEEN_1:
                {
                alt140=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 140, 0, input);

                throw nvae;
            }

            switch (alt140) {
                case 1 :
                    // InternalSqlParser.g:7310:2: (enumLiteral_0= IN )
                    {
                    // InternalSqlParser.g:7310:2: (enumLiteral_0= IN )
                    // InternalSqlParser.g:7310:7: enumLiteral_0= IN
                    {
                    enumLiteral_0=(Token)match(input,IN,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalSqlParser.g:7316:6: (enumLiteral_1= NOTIN )
                    {
                    // InternalSqlParser.g:7316:6: (enumLiteral_1= NOTIN )
                    // InternalSqlParser.g:7316:11: enumLiteral_1= NOTIN
                    {
                    enumLiteral_1=(Token)match(input,NOTIN,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalSqlParser.g:7322:6: (enumLiteral_2= EQUAL )
                    {
                    // InternalSqlParser.g:7322:6: (enumLiteral_2= EQUAL )
                    // InternalSqlParser.g:7322:11: enumLiteral_2= EQUAL
                    {
                    enumLiteral_2=(Token)match(input,EQUAL,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalSqlParser.g:7328:6: (enumLiteral_3= NOTEQUAL )
                    {
                    // InternalSqlParser.g:7328:6: (enumLiteral_3= NOTEQUAL )
                    // InternalSqlParser.g:7328:11: enumLiteral_3= NOTEQUAL
                    {
                    enumLiteral_3=(Token)match(input,NOTEQUAL,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalSqlParser.g:7334:6: (enumLiteral_4= LESS )
                    {
                    // InternalSqlParser.g:7334:6: (enumLiteral_4= LESS )
                    // InternalSqlParser.g:7334:11: enumLiteral_4= LESS
                    {
                    enumLiteral_4=(Token)match(input,LESS,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalSqlParser.g:7340:6: (enumLiteral_5= GREATER )
                    {
                    // InternalSqlParser.g:7340:6: (enumLiteral_5= GREATER )
                    // InternalSqlParser.g:7340:11: enumLiteral_5= GREATER
                    {
                    enumLiteral_5=(Token)match(input,GREATER,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // InternalSqlParser.g:7346:6: (enumLiteral_6= LESS_1 )
                    {
                    // InternalSqlParser.g:7346:6: (enumLiteral_6= LESS_1 )
                    // InternalSqlParser.g:7346:11: enumLiteral_6= LESS_1
                    {
                    enumLiteral_6=(Token)match(input,LESS_1,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // InternalSqlParser.g:7352:6: (enumLiteral_7= GREATER_1 )
                    {
                    // InternalSqlParser.g:7352:6: (enumLiteral_7= GREATER_1 )
                    // InternalSqlParser.g:7352:11: enumLiteral_7= GREATER_1
                    {
                    enumLiteral_7=(Token)match(input,GREATER_1,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // InternalSqlParser.g:7358:6: (enumLiteral_8= BETWEEN )
                    {
                    // InternalSqlParser.g:7358:6: (enumLiteral_8= BETWEEN )
                    // InternalSqlParser.g:7358:11: enumLiteral_8= BETWEEN
                    {
                    enumLiteral_8=(Token)match(input,BETWEEN,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // InternalSqlParser.g:7364:6: (enumLiteral_9= BETWEEN_3 )
                    {
                    // InternalSqlParser.g:7364:6: (enumLiteral_9= BETWEEN_3 )
                    // InternalSqlParser.g:7364:11: enumLiteral_9= BETWEEN_3
                    {
                    enumLiteral_9=(Token)match(input,BETWEEN_3,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // InternalSqlParser.g:7370:6: (enumLiteral_10= BETWEEN_2 )
                    {
                    // InternalSqlParser.g:7370:6: (enumLiteral_10= BETWEEN_2 )
                    // InternalSqlParser.g:7370:11: enumLiteral_10= BETWEEN_2
                    {
                    enumLiteral_10=(Token)match(input,BETWEEN_2,FOLLOW_2); 

                            current = grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // InternalSqlParser.g:7376:6: (enumLiteral_11= BETWEEN_1 )
                    {
                    // InternalSqlParser.g:7376:6: (enumLiteral_11= BETWEEN_1 )
                    // InternalSqlParser.g:7376:11: enumLiteral_11= BETWEEN_1
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


    protected DFA73 dfa73 = new DFA73(this);
    static final String dfa_1s = "\21\uffff";
    static final String dfa_2s = "\1\156\1\u0082\1\35\14\173\2\uffff";
    static final String dfa_3s = "\1\156\1\u0082\1\163\14\u0083\2\uffff";
    static final String dfa_4s = "\17\uffff\1\1\1\2";
    static final String dfa_5s = "\21\uffff}>";
    static final String[] dfa_6s = {
            "\1\1",
            "\1\2",
            "\1\14\1\16\4\uffff\1\6\1\uffff\1\15\1\12\1\13\2\uffff\1\10\21\uffff\1\5\2\uffff\1\11\3\uffff\1\4\21\uffff\1\7\35\uffff\1\3",
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

    class DFA73 extends DFA {

        public DFA73(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 73;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "3321:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000000L,0x0000040010000000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0002000004000002L,0x0000000000000202L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0100000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0400020100000000L,0x0100080000001000L,0x000000000003C7E0L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000600L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0400420110000000L,0x0100080000001000L,0x000000000003C7E0L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0400020110000000L,0x0100080000001000L,0x000000000003C7E0L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0048001200008002L,0x0000000000000801L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0424020000800000L,0x0108400001801000L,0x000000000003C760L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0048001200008002L,0x0000000000000001L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000038100L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0048001000008002L,0x0000000000000001L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0040001000008002L,0x0000000000000001L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0040000000008002L,0x0000000000000001L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0040000000008002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000002L,0x0004000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000002L,0x2000000000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x4800200000001002L,0x0000000000150100L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000400L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0001000000000002L,0x0004000000000040L,0x0000000000038000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L,0x0000000000004760L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000000L,0x0100100000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0100002000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000090000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000002L,0x0004000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000000002L,0x0000004000002000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000002L,0x0020001000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x900004E860000000L,0x0008000000200008L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0400020000000000L,0x0100000000001000L,0x000000000003C760L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000018L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0424128400940000L,0x814BE00001C01000L,0x000000000003C763L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0400020000000000L,0x0100002820001000L,0x000000000003C760L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0400000000000000L,0x0000000000000000L,0x0000000000024760L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007F60L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0000000000000002L,0x5480000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0400020100000000L,0x0300000800001000L,0x000000000003C7E0L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x00908000816B0EC0L,0x0000008280020004L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000001000002100L,0x0200000000000000L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000001000000102L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000080L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0400028000004020L,0x0100000000001000L,0x000000000003C760L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0400028000004030L,0x0100000000001000L,0x000000000003C760L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x000000000A000000L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000000002L,0x0000004000002010L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x2000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0400020100000000L,0x0100000800001000L,0x000000000003C760L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000000000L,0x0300000000000000L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000000000L,0x0A00000000000000L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0424020000800000L,0x0108400101801000L,0x000000000003C760L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0424020000800002L,0x0108400101801000L,0x000000000003C760L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x4800000000001000L,0x0000000000150100L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040020L});

}