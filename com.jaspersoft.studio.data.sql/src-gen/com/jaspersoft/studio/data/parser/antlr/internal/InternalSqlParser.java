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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_101", "KEYWORD_102", "KEYWORD_100", "KEYWORD_99", "KEYWORD_98", "KEYWORD_94", "KEYWORD_95", "KEYWORD_96", "KEYWORD_97", "KEYWORD_88", "KEYWORD_89", "KEYWORD_90", "KEYWORD_91", "KEYWORD_92", "KEYWORD_93", "KEYWORD_80", "KEYWORD_81", "KEYWORD_82", "KEYWORD_83", "KEYWORD_84", "KEYWORD_85", "KEYWORD_86", "KEYWORD_87", "KEYWORD_72", "KEYWORD_73", "KEYWORD_74", "KEYWORD_75", "KEYWORD_76", "KEYWORD_77", "KEYWORD_78", "KEYWORD_79", "KEYWORD_67", "KEYWORD_68", "KEYWORD_69", "KEYWORD_70", "KEYWORD_71", "KEYWORD_51", "KEYWORD_52", "KEYWORD_53", "KEYWORD_54", "KEYWORD_55", "KEYWORD_56", "KEYWORD_57", "KEYWORD_58", "KEYWORD_59", "KEYWORD_60", "KEYWORD_61", "KEYWORD_62", "KEYWORD_63", "KEYWORD_64", "KEYWORD_65", "KEYWORD_66", "KEYWORD_34", "KEYWORD_35", "KEYWORD_36", "KEYWORD_37", "KEYWORD_38", "KEYWORD_39", "KEYWORD_40", "KEYWORD_41", "KEYWORD_42", "KEYWORD_43", "KEYWORD_44", "KEYWORD_45", "KEYWORD_46", "KEYWORD_47", "KEYWORD_48", "KEYWORD_49", "KEYWORD_50", "KEYWORD_25", "KEYWORD_26", "KEYWORD_27", "KEYWORD_28", "KEYWORD_29", "KEYWORD_30", "KEYWORD_31", "KEYWORD_32", "KEYWORD_33", "KEYWORD_14", "KEYWORD_15", "KEYWORD_16", "KEYWORD_17", "KEYWORD_18", "KEYWORD_19", "KEYWORD_20", "KEYWORD_21", "KEYWORD_22", "KEYWORD_23", "KEYWORD_24", "KEYWORD_1", "KEYWORD_2", "KEYWORD_3", "KEYWORD_4", "KEYWORD_5", "KEYWORD_6", "KEYWORD_7", "KEYWORD_8", "KEYWORD_9", "KEYWORD_10", "KEYWORD_11", "KEYWORD_12", "KEYWORD_13", "RULE_JRPARAM", "RULE_JRNPARAM", "RULE_STAR", "RULE_INT", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_SIGNED_DOUBLE", "RULE_STRING_", "RULE_STRING", "RULE_DBNAME", "RULE_ID", "RULE_SL_COMMENT", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=117;
    public static final int RULE_JRNPARAM=107;
    public static final int RULE_DATE=110;
    public static final int RULE_ANY_OTHER=121;
    public static final int KEYWORD_56=45;
    public static final int KEYWORD_55=44;
    public static final int KEYWORD_54=43;
    public static final int KEYWORD_53=42;
    public static final int KEYWORD_52=41;
    public static final int KEYWORD_51=40;
    public static final int KEYWORD_50=72;
    public static final int EOF=-1;
    public static final int KEYWORD_59=48;
    public static final int KEYWORD_58=47;
    public static final int KEYWORD_57=46;
    public static final int KEYWORD_65=54;
    public static final int KEYWORD_64=53;
    public static final int KEYWORD_67=35;
    public static final int RULE_SIGNED_DOUBLE=113;
    public static final int KEYWORD_66=55;
    public static final int KEYWORD_61=50;
    public static final int KEYWORD_60=49;
    public static final int KEYWORD_63=52;
    public static final int KEYWORD_62=51;
    public static final int KEYWORD_69=37;
    public static final int KEYWORD_68=36;
    public static final int KEYWORD_30=78;
    public static final int KEYWORD_34=56;
    public static final int KEYWORD_33=81;
    public static final int KEYWORD_32=80;
    public static final int KEYWORD_31=79;
    public static final int KEYWORD_38=60;
    public static final int KEYWORD_37=59;
    public static final int KEYWORD_36=58;
    public static final int KEYWORD_35=57;
    public static final int RULE_ML_COMMENT=119;
    public static final int KEYWORD_39=61;
    public static final int RULE_STRING=115;
    public static final int KEYWORD_41=63;
    public static final int KEYWORD_40=62;
    public static final int KEYWORD_43=65;
    public static final int KEYWORD_42=64;
    public static final int KEYWORD_45=67;
    public static final int KEYWORD_44=66;
    public static final int KEYWORD_47=69;
    public static final int KEYWORD_46=68;
    public static final int KEYWORD_49=71;
    public static final int KEYWORD_48=70;
    public static final int KEYWORD_97=12;
    public static final int KEYWORD_98=8;
    public static final int KEYWORD_99=7;
    public static final int KEYWORD_93=18;
    public static final int KEYWORD_94=9;
    public static final int KEYWORD_95=10;
    public static final int KEYWORD_96=11;
    public static final int KEYWORD_90=15;
    public static final int KEYWORD_19=87;
    public static final int KEYWORD_92=17;
    public static final int KEYWORD_17=85;
    public static final int KEYWORD_91=16;
    public static final int KEYWORD_18=86;
    public static final int KEYWORD_15=83;
    public static final int KEYWORD_16=84;
    public static final int KEYWORD_13=105;
    public static final int RULE_STRING_=114;
    public static final int KEYWORD_14=82;
    public static final int KEYWORD_11=103;
    public static final int KEYWORD_12=104;
    public static final int KEYWORD_10=102;
    public static final int KEYWORD_101=4;
    public static final int KEYWORD_102=5;
    public static final int KEYWORD_100=6;
    public static final int KEYWORD_6=98;
    public static final int KEYWORD_7=99;
    public static final int KEYWORD_8=100;
    public static final int KEYWORD_9=101;
    public static final int RULE_TIME=111;
    public static final int KEYWORD_28=76;
    public static final int KEYWORD_29=77;
    public static final int RULE_TIMESTAMP=112;
    public static final int RULE_INT=109;
    public static final int KEYWORD_24=92;
    public static final int RULE_DBNAME=116;
    public static final int KEYWORD_25=73;
    public static final int KEYWORD_26=74;
    public static final int KEYWORD_27=75;
    public static final int KEYWORD_20=88;
    public static final int KEYWORD_21=89;
    public static final int KEYWORD_22=90;
    public static final int KEYWORD_23=91;
    public static final int KEYWORD_79=34;
    public static final int KEYWORD_71=39;
    public static final int KEYWORD_72=27;
    public static final int KEYWORD_73=28;
    public static final int KEYWORD_74=29;
    public static final int KEYWORD_75=30;
    public static final int KEYWORD_76=31;
    public static final int KEYWORD_77=32;
    public static final int KEYWORD_78=33;
    public static final int KEYWORD_1=93;
    public static final int KEYWORD_5=97;
    public static final int KEYWORD_4=96;
    public static final int KEYWORD_70=38;
    public static final int KEYWORD_3=95;
    public static final int KEYWORD_2=94;
    public static final int RULE_SL_COMMENT=118;
    public static final int RULE_STAR=108;
    public static final int KEYWORD_84=23;
    public static final int KEYWORD_85=24;
    public static final int KEYWORD_82=21;
    public static final int KEYWORD_83=22;
    public static final int KEYWORD_88=13;
    public static final int KEYWORD_89=14;
    public static final int RULE_JRPARAM=106;
    public static final int KEYWORD_86=25;
    public static final int KEYWORD_87=26;
    public static final int KEYWORD_81=20;
    public static final int KEYWORD_80=19;
    public static final int RULE_WS=120;

    // delegates
    // delegators


        public InternalSqlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSqlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSqlParser.tokenNames; }
    public String getGrammarFileName() { return "../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g"; }




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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:62:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:63:2: (iv_ruleModel= ruleModel EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:64:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel67);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel77); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:71:1: ruleModel returns [EObject current=null] : ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_85 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_57 (otherlv_4= KEYWORD_26 | (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? ) ) )? (otherlv_8= KEYWORD_70 this_INT_9= RULE_INT )? (otherlv_10= KEYWORD_95 this_INT_11= RULE_INT otherlv_12= KEYWORD_91 )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token this_INT_5=null;
        Token otherlv_6=null;
        Token this_INT_7=null;
        Token otherlv_8=null;
        Token this_INT_9=null;
        Token otherlv_10=null;
        Token this_INT_11=null;
        Token otherlv_12=null;
        EObject lv_query_0_0 = null;

        EObject lv_orderByEntry_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:74:28: ( ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_85 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_57 (otherlv_4= KEYWORD_26 | (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? ) ) )? (otherlv_8= KEYWORD_70 this_INT_9= RULE_INT )? (otherlv_10= KEYWORD_95 this_INT_11= RULE_INT otherlv_12= KEYWORD_91 )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_85 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_57 (otherlv_4= KEYWORD_26 | (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? ) ) )? (otherlv_8= KEYWORD_70 this_INT_9= RULE_INT )? (otherlv_10= KEYWORD_95 this_INT_11= RULE_INT otherlv_12= KEYWORD_91 )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_85 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_57 (otherlv_4= KEYWORD_26 | (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? ) ) )? (otherlv_8= KEYWORD_70 this_INT_9= RULE_INT )? (otherlv_10= KEYWORD_95 this_INT_11= RULE_INT otherlv_12= KEYWORD_91 )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:2: ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_85 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_57 (otherlv_4= KEYWORD_26 | (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? ) ) )? (otherlv_8= KEYWORD_70 this_INT_9= RULE_INT )? (otherlv_10= KEYWORD_95 this_INT_11= RULE_INT otherlv_12= KEYWORD_91 )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:2: ( (lv_query_0_0= ruleSelectQuery ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:76:1: (lv_query_0_0= ruleSelectQuery )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:76:1: (lv_query_0_0= ruleSelectQuery )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:77:3: lv_query_0_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getModelAccess().getQuerySelectQueryParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleSelectQuery_in_ruleModel123);
            lv_query_0_0=ruleSelectQuery();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getModelRule());
            	        }
                   		set(
                   			current, 
                   			"query",
                    		lv_query_0_0, 
                    		"SelectQuery");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:93:2: (otherlv_1= KEYWORD_85 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==KEYWORD_85) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:94:2: otherlv_1= KEYWORD_85 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) )
                    {
                    otherlv_1=(Token)match(input,KEYWORD_85,FOLLOW_KEYWORD_85_in_ruleModel137); 

                        	newLeafNode(otherlv_1, grammarAccess.getModelAccess().getORDERBYKeyword_1_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:98:1: ( (lv_orderByEntry_2_0= ruleOrderByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:99:1: (lv_orderByEntry_2_0= ruleOrderByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:99:1: (lv_orderByEntry_2_0= ruleOrderByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:100:3: lv_orderByEntry_2_0= ruleOrderByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOrderByColumns_in_ruleModel157);
                    lv_orderByEntry_2_0=ruleOrderByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"orderByEntry",
                            		lv_orderByEntry_2_0, 
                            		"OrderByColumns");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:116:4: (otherlv_3= KEYWORD_57 (otherlv_4= KEYWORD_26 | (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_57) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:117:2: otherlv_3= KEYWORD_57 (otherlv_4= KEYWORD_26 | (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? ) )
                    {
                    otherlv_3=(Token)match(input,KEYWORD_57,FOLLOW_KEYWORD_57_in_ruleModel173); 

                        	newLeafNode(otherlv_3, grammarAccess.getModelAccess().getLIMITKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:121:1: (otherlv_4= KEYWORD_26 | (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? ) )
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==KEYWORD_26) ) {
                        alt3=1;
                    }
                    else if ( (LA3_0==RULE_INT) ) {
                        alt3=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 0, input);

                        throw nvae;
                    }
                    switch (alt3) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:122:2: otherlv_4= KEYWORD_26
                            {
                            otherlv_4=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleModel186); 

                                	newLeafNode(otherlv_4, grammarAccess.getModelAccess().getALLKeyword_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:127:6: (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:127:6: (this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )? )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:127:7: this_INT_5= RULE_INT (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )?
                            {
                            this_INT_5=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleModel203); 
                             
                                newLeafNode(this_INT_5, grammarAccess.getModelAccess().getINTTerminalRuleCall_2_1_1_0()); 
                                
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:131:1: (otherlv_6= KEYWORD_4 this_INT_7= RULE_INT )?
                            int alt2=2;
                            int LA2_0 = input.LA(1);

                            if ( (LA2_0==KEYWORD_4) ) {
                                alt2=1;
                            }
                            switch (alt2) {
                                case 1 :
                                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:132:2: otherlv_6= KEYWORD_4 this_INT_7= RULE_INT
                                    {
                                    otherlv_6=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleModel216); 

                                        	newLeafNode(otherlv_6, grammarAccess.getModelAccess().getCommaKeyword_2_1_1_1_0());
                                        
                                    this_INT_7=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleModel226); 
                                     
                                        newLeafNode(this_INT_7, grammarAccess.getModelAccess().getINTTerminalRuleCall_2_1_1_1_1()); 
                                        

                                    }
                                    break;

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:140:7: (otherlv_8= KEYWORD_70 this_INT_9= RULE_INT )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==KEYWORD_70) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:141:2: otherlv_8= KEYWORD_70 this_INT_9= RULE_INT
                    {
                    otherlv_8=(Token)match(input,KEYWORD_70,FOLLOW_KEYWORD_70_in_ruleModel245); 

                        	newLeafNode(otherlv_8, grammarAccess.getModelAccess().getOFFSETKeyword_3_0());
                        
                    this_INT_9=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleModel255); 
                     
                        newLeafNode(this_INT_9, grammarAccess.getModelAccess().getINTTerminalRuleCall_3_1()); 
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:149:3: (otherlv_10= KEYWORD_95 this_INT_11= RULE_INT otherlv_12= KEYWORD_91 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_95) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:150:2: otherlv_10= KEYWORD_95 this_INT_11= RULE_INT otherlv_12= KEYWORD_91
                    {
                    otherlv_10=(Token)match(input,KEYWORD_95,FOLLOW_KEYWORD_95_in_ruleModel270); 

                        	newLeafNode(otherlv_10, grammarAccess.getModelAccess().getFETCHFIRSTKeyword_4_0());
                        
                    this_INT_11=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleModel280); 
                     
                        newLeafNode(this_INT_11, grammarAccess.getModelAccess().getINTTerminalRuleCall_4_1()); 
                        
                    otherlv_12=(Token)match(input,KEYWORD_91,FOLLOW_KEYWORD_91_in_ruleModel292); 

                        	newLeafNode(otherlv_12, grammarAccess.getModelAccess().getROWSONLYKeyword_4_2());
                        

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
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleSelectQuery"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:171:1: entryRuleSelectQuery returns [EObject current=null] : iv_ruleSelectQuery= ruleSelectQuery EOF ;
    public final EObject entryRuleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectQuery = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:172:2: (iv_ruleSelectQuery= ruleSelectQuery EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:173:2: iv_ruleSelectQuery= ruleSelectQuery EOF
            {
             newCompositeNode(grammarAccess.getSelectQueryRule()); 
            pushFollow(FOLLOW_ruleSelectQuery_in_entryRuleSelectQuery328);
            iv_ruleSelectQuery=ruleSelectQuery();

            state._fsp--;

             current =iv_ruleSelectQuery; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectQuery338); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:180:1: ruleSelectQuery returns [EObject current=null] : (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) ;
    public final EObject ruleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject this_Select_0 = null;

        EObject lv_op_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:183:28: ( (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:184:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:184:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:185:5: this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )*
            {
             
                    newCompositeNode(grammarAccess.getSelectQueryAccess().getSelectParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSelect_in_ruleSelectQuery385);
            this_Select_0=ruleSelect();

            state._fsp--;


                    current = this_Select_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:193:1: ( (lv_op_1_0= ruleSelectSubSet ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==KEYWORD_89||LA7_0==KEYWORD_67||LA7_0==KEYWORD_58||LA7_0==KEYWORD_65) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:194:1: (lv_op_1_0= ruleSelectSubSet )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:194:1: (lv_op_1_0= ruleSelectSubSet )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:195:3: lv_op_1_0= ruleSelectSubSet
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSelectQueryAccess().getOpSelectSubSetParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSelectSubSet_in_ruleSelectQuery405);
            	    lv_op_1_0=ruleSelectSubSet();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSelectQueryRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"op",
            	            		lv_op_1_0, 
            	            		"SelectSubSet");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:219:1: entryRuleSelectSubSet returns [EObject current=null] : iv_ruleSelectSubSet= ruleSelectSubSet EOF ;
    public final EObject entryRuleSelectSubSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectSubSet = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:220:2: (iv_ruleSelectSubSet= ruleSelectSubSet EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:221:2: iv_ruleSelectSubSet= ruleSelectSubSet EOF
            {
             newCompositeNode(grammarAccess.getSelectSubSetRule()); 
            pushFollow(FOLLOW_ruleSelectSubSet_in_entryRuleSelectSubSet441);
            iv_ruleSelectSubSet=ruleSelectSubSet();

            state._fsp--;

             current =iv_ruleSelectSubSet; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectSubSet451); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:228:1: ruleSelectSubSet returns [EObject current=null] : ( ( ( (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:231:28: ( ( ( ( (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:232:1: ( ( ( (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:232:1: ( ( ( (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:232:2: ( ( (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:232:2: ( ( (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:233:1: ( (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:233:1: ( (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:234:1: (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:234:1: (lv_op_0_1= KEYWORD_65 | lv_op_0_2= KEYWORD_89 | lv_op_0_3= KEYWORD_58 | lv_op_0_4= KEYWORD_67 )
            int alt8=4;
            switch ( input.LA(1) ) {
            case KEYWORD_65:
                {
                alt8=1;
                }
                break;
            case KEYWORD_89:
                {
                alt8=2;
                }
                break;
            case KEYWORD_58:
                {
                alt8=3;
                }
                break;
            case KEYWORD_67:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:235:3: lv_op_0_1= KEYWORD_65
                    {
                    lv_op_0_1=(Token)match(input,KEYWORD_65,FOLLOW_KEYWORD_65_in_ruleSelectSubSet497); 

                            newLeafNode(lv_op_0_1, grammarAccess.getSelectSubSetAccess().getOpUNIONKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:248:8: lv_op_0_2= KEYWORD_89
                    {
                    lv_op_0_2=(Token)match(input,KEYWORD_89,FOLLOW_KEYWORD_89_in_ruleSelectSubSet525); 

                            newLeafNode(lv_op_0_2, grammarAccess.getSelectSubSetAccess().getOpINTERSECTKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:261:8: lv_op_0_3= KEYWORD_58
                    {
                    lv_op_0_3=(Token)match(input,KEYWORD_58,FOLLOW_KEYWORD_58_in_ruleSelectSubSet553); 

                            newLeafNode(lv_op_0_3, grammarAccess.getSelectSubSetAccess().getOpMINUSKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:274:8: lv_op_0_4= KEYWORD_67
                    {
                    lv_op_0_4=(Token)match(input,KEYWORD_67,FOLLOW_KEYWORD_67_in_ruleSelectSubSet581); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:290:2: ( (lv_all_1_0= KEYWORD_26 ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==KEYWORD_26) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:291:1: (lv_all_1_0= KEYWORD_26 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:291:1: (lv_all_1_0= KEYWORD_26 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:292:3: lv_all_1_0= KEYWORD_26
                    {
                    lv_all_1_0=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleSelectSubSet614); 

                            newLeafNode(lv_all_1_0, grammarAccess.getSelectSubSetAccess().getAllALLKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "all", lv_all_1_0, "ALL");
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:306:3: ( (lv_query_2_0= ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:307:1: (lv_query_2_0= ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:307:1: (lv_query_2_0= ruleSelect )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:308:3: lv_query_2_0= ruleSelect
            {
             
            	        newCompositeNode(grammarAccess.getSelectSubSetAccess().getQuerySelectParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelect_in_ruleSelectSubSet647);
            lv_query_2_0=ruleSelect();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectSubSetRule());
            	        }
                   		set(
                   			current, 
                   			"query",
                    		lv_query_2_0, 
                    		"Select");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:332:1: entryRuleSelect returns [EObject current=null] : iv_ruleSelect= ruleSelect EOF ;
    public final EObject entryRuleSelect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelect = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:333:2: (iv_ruleSelect= ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:334:2: iv_ruleSelect= ruleSelect EOF
            {
             newCompositeNode(grammarAccess.getSelectRule()); 
            pushFollow(FOLLOW_ruleSelect_in_entryRuleSelect682);
            iv_ruleSelect=ruleSelect();

            state._fsp--;

             current =iv_ruleSelect; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelect692); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:341:1: ruleSelect returns [EObject current=null] : ( ( (lv_select_0_0= KEYWORD_71 ) ) (otherlv_1= KEYWORD_81 )? (otherlv_2= KEYWORD_32 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_78 )? (otherlv_6= KEYWORD_92 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_37 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_66 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_82 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_68 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? ) ;
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
        EObject lv_cols_7_0 = null;

        EObject lv_tbl_9_0 = null;

        EObject lv_whereExpression_11_0 = null;

        EObject lv_groupByEntry_13_0 = null;

        EObject lv_havingEntry_15_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:344:28: ( ( ( (lv_select_0_0= KEYWORD_71 ) ) (otherlv_1= KEYWORD_81 )? (otherlv_2= KEYWORD_32 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_78 )? (otherlv_6= KEYWORD_92 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_37 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_66 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_82 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_68 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:345:1: ( ( (lv_select_0_0= KEYWORD_71 ) ) (otherlv_1= KEYWORD_81 )? (otherlv_2= KEYWORD_32 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_78 )? (otherlv_6= KEYWORD_92 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_37 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_66 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_82 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_68 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:345:1: ( ( (lv_select_0_0= KEYWORD_71 ) ) (otherlv_1= KEYWORD_81 )? (otherlv_2= KEYWORD_32 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_78 )? (otherlv_6= KEYWORD_92 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_37 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_66 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_82 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_68 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:345:2: ( (lv_select_0_0= KEYWORD_71 ) ) (otherlv_1= KEYWORD_81 )? (otherlv_2= KEYWORD_32 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_78 )? (otherlv_6= KEYWORD_92 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_37 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_66 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_82 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_68 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:345:2: ( (lv_select_0_0= KEYWORD_71 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:346:1: (lv_select_0_0= KEYWORD_71 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:346:1: (lv_select_0_0= KEYWORD_71 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:347:3: lv_select_0_0= KEYWORD_71
            {
            lv_select_0_0=(Token)match(input,KEYWORD_71,FOLLOW_KEYWORD_71_in_ruleSelect736); 

                    newLeafNode(lv_select_0_0, grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectRule());
            	        }
                   		setWithLastConsumed(current, "select", lv_select_0_0, "SELECT");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:361:2: (otherlv_1= KEYWORD_81 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==KEYWORD_81) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:362:2: otherlv_1= KEYWORD_81
                    {
                    otherlv_1=(Token)match(input,KEYWORD_81,FOLLOW_KEYWORD_81_in_ruleSelect761); 

                        	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:366:3: (otherlv_2= KEYWORD_32 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_78 )? (otherlv_6= KEYWORD_92 )? )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEYWORD_32) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:367:2: otherlv_2= KEYWORD_32 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_78 )? (otherlv_6= KEYWORD_92 )?
                    {
                    otherlv_2=(Token)match(input,KEYWORD_32,FOLLOW_KEYWORD_32_in_ruleSelect776); 

                        	newLeafNode(otherlv_2, grammarAccess.getSelectAccess().getTOPKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:371:1: (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE )
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==RULE_INT) ) {
                        alt11=1;
                    }
                    else if ( (LA11_0==RULE_SIGNED_DOUBLE) ) {
                        alt11=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:371:2: this_INT_3= RULE_INT
                            {
                            this_INT_3=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleSelect787); 
                             
                                newLeafNode(this_INT_3, grammarAccess.getSelectAccess().getINTTerminalRuleCall_2_1_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:376:6: this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE
                            {
                            this_SIGNED_DOUBLE_4=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleSelect803); 
                             
                                newLeafNode(this_SIGNED_DOUBLE_4, grammarAccess.getSelectAccess().getSIGNED_DOUBLETerminalRuleCall_2_1_1()); 
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:380:2: (otherlv_5= KEYWORD_78 )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==KEYWORD_78) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:381:2: otherlv_5= KEYWORD_78
                            {
                            otherlv_5=(Token)match(input,KEYWORD_78,FOLLOW_KEYWORD_78_in_ruleSelect817); 

                                	newLeafNode(otherlv_5, grammarAccess.getSelectAccess().getPERCENTKeyword_2_2());
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:385:3: (otherlv_6= KEYWORD_92 )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==KEYWORD_92) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:386:2: otherlv_6= KEYWORD_92
                            {
                            otherlv_6=(Token)match(input,KEYWORD_92,FOLLOW_KEYWORD_92_in_ruleSelect832); 

                                	newLeafNode(otherlv_6, grammarAccess.getSelectAccess().getWITHTIESKeyword_2_3());
                                

                            }
                            break;

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:390:5: ( (lv_cols_7_0= ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:391:1: (lv_cols_7_0= ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:391:1: (lv_cols_7_0= ruleColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:392:3: lv_cols_7_0= ruleColumns
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleColumns_in_ruleSelect856);
            lv_cols_7_0=ruleColumns();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectRule());
            	        }
                   		set(
                   			current, 
                   			"cols",
                    		lv_cols_7_0, 
                    		"Columns");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_8=(Token)match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_ruleSelect869); 

                	newLeafNode(otherlv_8, grammarAccess.getSelectAccess().getFROMKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:413:1: ( (lv_tbl_9_0= ruleTables ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:414:1: (lv_tbl_9_0= ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:414:1: (lv_tbl_9_0= ruleTables )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:415:3: lv_tbl_9_0= ruleTables
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleTables_in_ruleSelect889);
            lv_tbl_9_0=ruleTables();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectRule());
            	        }
                   		set(
                   			current, 
                   			"tbl",
                    		lv_tbl_9_0, 
                    		"Tables");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:431:2: (otherlv_10= KEYWORD_66 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==KEYWORD_66) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:432:2: otherlv_10= KEYWORD_66 ( (lv_whereExpression_11_0= ruleFullExpression ) )
                    {
                    otherlv_10=(Token)match(input,KEYWORD_66,FOLLOW_KEYWORD_66_in_ruleSelect903); 

                        	newLeafNode(otherlv_10, grammarAccess.getSelectAccess().getWHEREKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:436:1: ( (lv_whereExpression_11_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:437:1: (lv_whereExpression_11_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:437:1: (lv_whereExpression_11_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:438:3: lv_whereExpression_11_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect923);
                    lv_whereExpression_11_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"whereExpression",
                            		lv_whereExpression_11_0, 
                            		"FullExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:454:4: (otherlv_12= KEYWORD_82 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==KEYWORD_82) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:455:2: otherlv_12= KEYWORD_82 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) )
                    {
                    otherlv_12=(Token)match(input,KEYWORD_82,FOLLOW_KEYWORD_82_in_ruleSelect939); 

                        	newLeafNode(otherlv_12, grammarAccess.getSelectAccess().getGROUPBYKeyword_7_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:459:1: ( (lv_groupByEntry_13_0= ruleGroupByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:460:1: (lv_groupByEntry_13_0= ruleGroupByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:460:1: (lv_groupByEntry_13_0= ruleGroupByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:461:3: lv_groupByEntry_13_0= ruleGroupByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleGroupByColumns_in_ruleSelect959);
                    lv_groupByEntry_13_0=ruleGroupByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"groupByEntry",
                            		lv_groupByEntry_13_0, 
                            		"GroupByColumns");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:477:4: (otherlv_14= KEYWORD_68 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==KEYWORD_68) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:478:2: otherlv_14= KEYWORD_68 ( (lv_havingEntry_15_0= ruleFullExpression ) )
                    {
                    otherlv_14=(Token)match(input,KEYWORD_68,FOLLOW_KEYWORD_68_in_ruleSelect975); 

                        	newLeafNode(otherlv_14, grammarAccess.getSelectAccess().getHAVINGKeyword_8_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:482:1: ( (lv_havingEntry_15_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:483:1: (lv_havingEntry_15_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:483:1: (lv_havingEntry_15_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:484:3: lv_havingEntry_15_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect995);
                    lv_havingEntry_15_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"havingEntry",
                            		lv_havingEntry_15_0, 
                            		"FullExpression");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:508:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:509:2: (iv_ruleColumns= ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:510:2: iv_ruleColumns= ruleColumns EOF
            {
             newCompositeNode(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns1032);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns1042); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:517:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:520:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:521:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:521:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:522:5: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns1089);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;


                    current = this_ColumnOrAlias_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:530:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==KEYWORD_4) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:530:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:530:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:531:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:536:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==KEYWORD_4) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:537:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleColumns1112); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:541:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:542:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:542:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:543:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns1132);
                    	    lv_entries_3_0=ruleColumnOrAlias();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getColumnsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"ColumnOrAlias");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt18 >= 1 ) break loop18;
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:567:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:568:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:569:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias1171);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias1181); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:576:1: ruleColumnOrAlias returns [EObject current=null] : ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_1_0=null;
        Token lv_allCols_3_0=null;
        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_2_0 = null;

        EObject lv_dbAllCols_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:579:28: ( ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:580:1: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:580:1: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            int alt22=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA22_1 = input.LA(2);

                if ( (LA22_1==KEYWORD_6) ) {
                    int LA22_6 = input.LA(3);

                    if ( (LA22_6==RULE_STAR) ) {
                        alt22=3;
                    }
                    else if ( ((LA22_6>=RULE_STRING && LA22_6<=RULE_ID)) ) {
                        alt22=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 22, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA22_1==EOF||LA22_1==KEYWORD_37||LA22_1==KEYWORD_25||LA22_1==KEYWORD_19||(LA22_1>=KEYWORD_24 && LA22_1<=KEYWORD_5)||LA22_1==KEYWORD_7||LA22_1==RULE_STAR||(LA22_1>=RULE_STRING && LA22_1<=RULE_ID)) ) {
                    alt22=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_DBNAME:
                {
                int LA22_2 = input.LA(2);

                if ( (LA22_2==KEYWORD_6) ) {
                    int LA22_6 = input.LA(3);

                    if ( (LA22_6==RULE_STAR) ) {
                        alt22=3;
                    }
                    else if ( ((LA22_6>=RULE_STRING && LA22_6<=RULE_ID)) ) {
                        alt22=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 22, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA22_2==EOF||LA22_2==KEYWORD_37||LA22_2==KEYWORD_25||LA22_2==KEYWORD_19||LA22_2==KEYWORD_24||(LA22_2>=KEYWORD_2 && LA22_2<=KEYWORD_5)||LA22_2==KEYWORD_7||LA22_2==RULE_STAR||(LA22_2>=RULE_STRING && LA22_2<=RULE_ID)) ) {
                    alt22=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
                {
                int LA22_3 = input.LA(2);

                if ( (LA22_3==KEYWORD_6) ) {
                    int LA22_6 = input.LA(3);

                    if ( (LA22_6==RULE_STAR) ) {
                        alt22=3;
                    }
                    else if ( ((LA22_6>=RULE_STRING && LA22_6<=RULE_ID)) ) {
                        alt22=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 22, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA22_3==EOF||LA22_3==KEYWORD_37||LA22_3==KEYWORD_25||LA22_3==KEYWORD_19||LA22_3==KEYWORD_24||(LA22_3>=KEYWORD_2 && LA22_3<=KEYWORD_5)||LA22_3==KEYWORD_7||LA22_3==RULE_STAR||(LA22_3>=RULE_STRING && LA22_3<=RULE_ID)) ) {
                    alt22=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 3, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_51:
            case KEYWORD_34:
            case KEYWORD_1:
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt22=1;
                }
                break;
            case RULE_STAR:
                {
                alt22=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:580:2: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:580:2: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:580:3: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:580:3: ( (lv_ce_0_0= ruleOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:581:1: (lv_ce_0_0= ruleOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:581:1: (lv_ce_0_0= ruleOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:582:3: lv_ce_0_0= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getCeOperandParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleColumnOrAlias1228);
                    lv_ce_0_0=ruleOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"ce",
                            		lv_ce_0_0, 
                            		"Operand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:598:2: ( (lv_alias_1_0= KEYWORD_19 ) )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==KEYWORD_19) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:599:1: (lv_alias_1_0= KEYWORD_19 )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:599:1: (lv_alias_1_0= KEYWORD_19 )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:600:3: lv_alias_1_0= KEYWORD_19
                            {
                            lv_alias_1_0=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleColumnOrAlias1247); 

                                    newLeafNode(lv_alias_1_0, grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getColumnOrAliasRule());
                            	        }
                                   		setWithLastConsumed(current, "alias", lv_alias_1_0, "AS");
                            	    

                            }


                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:614:3: ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( ((LA21_0>=RULE_STRING && LA21_0<=RULE_ID)) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:615:1: (lv_colAlias_2_0= ruleDbObjectName )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:615:1: (lv_colAlias_2_0= ruleDbObjectName )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:616:3: lv_colAlias_2_0= ruleDbObjectName
                            {
                             
                            	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
                            	    
                            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias1280);
                            lv_colAlias_2_0=ruleDbObjectName();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"colAlias",
                                    		lv_colAlias_2_0, 
                                    		"DbObjectName");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:633:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:633:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:634:1: (lv_allCols_3_0= RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:634:1: (lv_allCols_3_0= RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:635:3: lv_allCols_3_0= RULE_STAR
                    {
                    lv_allCols_3_0=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleColumnOrAlias1305); 

                    			newLeafNode(lv_allCols_3_0, grammarAccess.getColumnOrAliasAccess().getAllColsSTARTerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getColumnOrAliasRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"allCols",
                            		lv_allCols_3_0, 
                            		"STAR");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:652:6: ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:652:6: ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:653:1: (lv_dbAllCols_4_0= ruleDbObjectNameAll )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:653:1: (lv_dbAllCols_4_0= ruleDbObjectNameAll )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:654:3: lv_dbAllCols_4_0= ruleDbObjectNameAll
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getDbAllColsDbObjectNameAllParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectNameAll_in_ruleColumnOrAlias1337);
                    lv_dbAllCols_4_0=ruleDbObjectNameAll();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"dbAllCols",
                            		lv_dbAllCols_4_0, 
                            		"DbObjectNameAll");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:678:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:679:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:680:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
             newCompositeNode(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull1372);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull1382); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:687:1: ruleColumnFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:690:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:691:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:691:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:692:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull1429);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:700:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==KEYWORD_6) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:700:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:700:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:701:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnFullAccess().getColEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:706:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt23=0;
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==KEYWORD_6) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:707:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleColumnFull1452); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:711:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:712:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:712:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:713:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull1472);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getColumnFullRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"DbObjectName");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt23 >= 1 ) break loop23;
                                EarlyExitException eee =
                                    new EarlyExitException(23, input);
                                throw eee;
                        }
                        cnt23++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:737:1: entryRuleTables returns [EObject current=null] : iv_ruleTables= ruleTables EOF ;
    public final EObject entryRuleTables() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTables = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:738:2: (iv_ruleTables= ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:739:2: iv_ruleTables= ruleTables EOF
            {
             newCompositeNode(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables1511);
            iv_ruleTables=ruleTables();

            state._fsp--;

             current =iv_ruleTables; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables1521); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:746:1: ruleTables returns [EObject current=null] : (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) ;
    public final EObject ruleTables() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_FromTable_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:749:28: ( (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:750:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:750:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:751:5: this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleFromTable_in_ruleTables1568);
            this_FromTable_0=ruleFromTable();

            state._fsp--;


                    current = this_FromTable_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:759:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==KEYWORD_4) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:759:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:759:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:760:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:765:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==KEYWORD_4) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:766:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleTables1591); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:770:1: ( (lv_entries_3_0= ruleFromTable ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:771:1: (lv_entries_3_0= ruleFromTable )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:771:1: (lv_entries_3_0= ruleFromTable )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:772:3: lv_entries_3_0= ruleFromTable
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleFromTable_in_ruleTables1611);
                    	    lv_entries_3_0=ruleFromTable();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getTablesRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"FromTable");
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
    // $ANTLR end "ruleTables"


    // $ANTLR start "entryRuleFromTable"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:796:1: entryRuleFromTable returns [EObject current=null] : iv_ruleFromTable= ruleFromTable EOF ;
    public final EObject entryRuleFromTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:797:2: (iv_ruleFromTable= ruleFromTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:798:2: iv_ruleFromTable= ruleFromTable EOF
            {
             newCompositeNode(grammarAccess.getFromTableRule()); 
            pushFollow(FOLLOW_ruleFromTable_in_entryRuleFromTable1650);
            iv_ruleFromTable=ruleFromTable();

            state._fsp--;

             current =iv_ruleFromTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTable1660); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:805:1: ruleFromTable returns [EObject current=null] : ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) ;
    public final EObject ruleFromTable() throws RecognitionException {
        EObject current = null;

        EObject lv_table_0_0 = null;

        EObject lv_fjoin_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:808:28: ( ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:809:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:809:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:809:2: ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:809:2: ( (lv_table_0_0= ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:810:1: (lv_table_0_0= ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:810:1: (lv_table_0_0= ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:811:3: lv_table_0_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTable1706);
            lv_table_0_0=ruleTableOrAlias();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFromTableRule());
            	        }
                   		set(
                   			current, 
                   			"table",
                    		lv_table_0_0, 
                    		"TableOrAlias");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:827:2: ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==KEYWORD_99||LA27_0==KEYWORD_77||LA27_0==KEYWORD_52||LA27_0==KEYWORD_55||LA27_0==KEYWORD_64||(LA27_0>=KEYWORD_38 && LA27_0<=KEYWORD_39)||LA27_0==KEYWORD_41) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:828:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:828:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:829:3: lv_fjoin_1_0= ruleFromTableJoin
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFromTableAccess().getFjoinFromTableJoinParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleFromTableJoin_in_ruleFromTable1727);
            	    lv_fjoin_1_0=ruleFromTableJoin();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFromTableRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"fjoin",
            	            		lv_fjoin_1_0, 
            	            		"FromTableJoin");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:853:1: entryRuleFromTableJoin returns [EObject current=null] : iv_ruleFromTableJoin= ruleFromTableJoin EOF ;
    public final EObject entryRuleFromTableJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTableJoin = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:854:2: (iv_ruleFromTableJoin= ruleFromTableJoin EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:855:2: iv_ruleFromTableJoin= ruleFromTableJoin EOF
            {
             newCompositeNode(grammarAccess.getFromTableJoinRule()); 
            pushFollow(FOLLOW_ruleFromTableJoin_in_entryRuleFromTableJoin1763);
            iv_ruleFromTableJoin=ruleFromTableJoin();

            state._fsp--;

             current =iv_ruleFromTableJoin; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTableJoin1773); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:862:1: ruleFromTableJoin returns [EObject current=null] : ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) ;
    public final EObject ruleFromTableJoin() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_join_0_0 = null;

        EObject lv_onTable_1_0 = null;

        EObject lv_joinExpr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:865:28: ( ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:866:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:866:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:866:2: ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:866:2: ( (lv_join_0_0= ruleJoinType ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:867:1: (lv_join_0_0= ruleJoinType )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:867:1: (lv_join_0_0= ruleJoinType )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:868:3: lv_join_0_0= ruleJoinType
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinJoinTypeParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleJoinType_in_ruleFromTableJoin1819);
            lv_join_0_0=ruleJoinType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFromTableJoinRule());
            	        }
                   		set(
                   			current, 
                   			"join",
                    		lv_join_0_0, 
                    		"JoinType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:884:2: ( (lv_onTable_1_0= ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:885:1: (lv_onTable_1_0= ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:885:1: (lv_onTable_1_0= ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:886:3: lv_onTable_1_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getOnTableTableOrAliasParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTableJoin1840);
            lv_onTable_1_0=ruleTableOrAlias();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFromTableJoinRule());
            	        }
                   		set(
                   			current, 
                   			"onTable",
                    		lv_onTable_1_0, 
                    		"TableOrAlias");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_ruleFromTableJoin1853); 

                	newLeafNode(otherlv_2, grammarAccess.getFromTableJoinAccess().getONKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:907:1: ( (lv_joinExpr_3_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:908:1: (lv_joinExpr_3_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:908:1: (lv_joinExpr_3_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:909:3: lv_joinExpr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleFromTableJoin1873);
            lv_joinExpr_3_0=ruleFullExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFromTableJoinRule());
            	        }
                   		set(
                   			current, 
                   			"joinExpr",
                    		lv_joinExpr_3_0, 
                    		"FullExpression");
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
    // $ANTLR end "ruleFromTableJoin"


    // $ANTLR start "entryRuleTableOrAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:933:1: entryRuleTableOrAlias returns [EObject current=null] : iv_ruleTableOrAlias= ruleTableOrAlias EOF ;
    public final EObject entryRuleTableOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:934:2: (iv_ruleTableOrAlias= ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:935:2: iv_ruleTableOrAlias= ruleTableOrAlias EOF
            {
             newCompositeNode(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1908);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;

             current =iv_ruleTableOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias1918); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:942:1: ruleTableOrAlias returns [EObject current=null] : ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:945:28: ( ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:946:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:946:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:946:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:946:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=RULE_STRING && LA28_0<=RULE_ID)) ) {
                alt28=1;
            }
            else if ( (LA28_0==KEYWORD_1) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:946:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:946:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:947:1: (lv_tfull_0_0= ruleTableFull )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:947:1: (lv_tfull_0_0= ruleTableFull )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:948:3: lv_tfull_0_0= ruleTableFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1965);
                    lv_tfull_0_0=ruleTableFull();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"tfull",
                            		lv_tfull_0_0, 
                            		"TableFull");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:965:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:965:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:966:1: (lv_sq_1_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:966:1: (lv_sq_1_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:967:3: lv_sq_1_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getSqSubQueryOperandParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleTableOrAlias1992);
                    lv_sq_1_0=ruleSubQueryOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"sq",
                            		lv_sq_1_0, 
                            		"SubQueryOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:983:3: ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )?
            int alt29=3;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==KEYWORD_62) ) {
                alt29=1;
            }
            else if ( (LA29_0==KEYWORD_79) ) {
                alt29=2;
            }
            switch (alt29) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:983:4: ( (lv_pivot_2_0= rulePivotTable ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:983:4: ( (lv_pivot_2_0= rulePivotTable ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:984:1: (lv_pivot_2_0= rulePivotTable )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:984:1: (lv_pivot_2_0= rulePivotTable )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:985:3: lv_pivot_2_0= rulePivotTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getPivotPivotTableParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulePivotTable_in_ruleTableOrAlias2015);
                    lv_pivot_2_0=rulePivotTable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"pivot",
                            		lv_pivot_2_0, 
                            		"PivotTable");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1002:6: ( (lv_unpivot_3_0= ruleUnpivotTable ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1002:6: ( (lv_unpivot_3_0= ruleUnpivotTable ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1003:1: (lv_unpivot_3_0= ruleUnpivotTable )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1003:1: (lv_unpivot_3_0= ruleUnpivotTable )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1004:3: lv_unpivot_3_0= ruleUnpivotTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getUnpivotUnpivotTableParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleUnpivotTable_in_ruleTableOrAlias2042);
                    lv_unpivot_3_0=ruleUnpivotTable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"unpivot",
                            		lv_unpivot_3_0, 
                            		"UnpivotTable");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1020:4: ( (lv_alias_4_0= KEYWORD_19 ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==KEYWORD_19) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1021:1: (lv_alias_4_0= KEYWORD_19 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1021:1: (lv_alias_4_0= KEYWORD_19 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1022:3: lv_alias_4_0= KEYWORD_19
                    {
                    lv_alias_4_0=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleTableOrAlias2063); 

                            newLeafNode(lv_alias_4_0, grammarAccess.getTableOrAliasAccess().getAliasASKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTableOrAliasRule());
                    	        }
                           		setWithLastConsumed(current, "alias", lv_alias_4_0, "AS");
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1036:3: ( (lv_tblAlias_5_0= ruleDbObjectName ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_STRING && LA31_0<=RULE_ID)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1037:1: (lv_tblAlias_5_0= ruleDbObjectName )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1037:1: (lv_tblAlias_5_0= ruleDbObjectName )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1038:3: lv_tblAlias_5_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableOrAlias2096);
                    lv_tblAlias_5_0=ruleDbObjectName();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"tblAlias",
                            		lv_tblAlias_5_0, 
                            		"DbObjectName");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1062:1: entryRulePivotTable returns [EObject current=null] : iv_rulePivotTable= rulePivotTable EOF ;
    public final EObject entryRulePivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1063:2: (iv_rulePivotTable= rulePivotTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1064:2: iv_rulePivotTable= rulePivotTable EOF
            {
             newCompositeNode(grammarAccess.getPivotTableRule()); 
            pushFollow(FOLLOW_rulePivotTable_in_entryRulePivotTable2132);
            iv_rulePivotTable=rulePivotTable();

            state._fsp--;

             current =iv_rulePivotTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotTable2142); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1071:1: rulePivotTable returns [EObject current=null] : (otherlv_0= KEYWORD_62 (otherlv_1= KEYWORD_33 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1074:28: ( (otherlv_0= KEYWORD_62 (otherlv_1= KEYWORD_33 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1075:1: (otherlv_0= KEYWORD_62 (otherlv_1= KEYWORD_33 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1075:1: (otherlv_0= KEYWORD_62 (otherlv_1= KEYWORD_33 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1076:2: otherlv_0= KEYWORD_62 (otherlv_1= KEYWORD_33 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_62,FOLLOW_KEYWORD_62_in_rulePivotTable2180); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotTableAccess().getPIVOTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1080:1: (otherlv_1= KEYWORD_33 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==KEYWORD_33) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1081:2: otherlv_1= KEYWORD_33
                    {
                    otherlv_1=(Token)match(input,KEYWORD_33,FOLLOW_KEYWORD_33_in_rulePivotTable2193); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotTableAccess().getXMLKeyword_1());
                        

                    }
                    break;

            }

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotTable2207); 

                	newLeafNode(otherlv_2, grammarAccess.getPivotTableAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1090:1: ( (lv_pfun_3_0= rulePivotFunctions ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1091:1: (lv_pfun_3_0= rulePivotFunctions )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1091:1: (lv_pfun_3_0= rulePivotFunctions )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1092:3: lv_pfun_3_0= rulePivotFunctions
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPfunPivotFunctionsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_rulePivotFunctions_in_rulePivotTable2227);
            lv_pfun_3_0=rulePivotFunctions();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPivotTableRule());
            	        }
                   		set(
                   			current, 
                   			"pfun",
                    		lv_pfun_3_0, 
                    		"PivotFunctions");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1108:2: ( (lv_pfor_4_0= rulePivotForClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1109:1: (lv_pfor_4_0= rulePivotForClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1109:1: (lv_pfor_4_0= rulePivotForClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1110:3: lv_pfor_4_0= rulePivotForClause
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_rulePivotForClause_in_rulePivotTable2248);
            lv_pfor_4_0=rulePivotForClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPivotTableRule());
            	        }
                   		set(
                   			current, 
                   			"pfor",
                    		lv_pfor_4_0, 
                    		"PivotForClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1126:2: ( (lv_pin_5_0= rulePivotInClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1127:1: (lv_pin_5_0= rulePivotInClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1127:1: (lv_pin_5_0= rulePivotInClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1128:3: lv_pin_5_0= rulePivotInClause
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPinPivotInClauseParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_rulePivotInClause_in_rulePivotTable2269);
            lv_pin_5_0=rulePivotInClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPivotTableRule());
            	        }
                   		set(
                   			current, 
                   			"pin",
                    		lv_pin_5_0, 
                    		"PivotInClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_6=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotTable2282); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1157:1: entryRulePivotFunctions returns [EObject current=null] : iv_rulePivotFunctions= rulePivotFunctions EOF ;
    public final EObject entryRulePivotFunctions() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotFunctions = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1158:2: (iv_rulePivotFunctions= rulePivotFunctions EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1159:2: iv_rulePivotFunctions= rulePivotFunctions EOF
            {
             newCompositeNode(grammarAccess.getPivotFunctionsRule()); 
            pushFollow(FOLLOW_rulePivotFunctions_in_entryRulePivotFunctions2316);
            iv_rulePivotFunctions=rulePivotFunctions();

            state._fsp--;

             current =iv_rulePivotFunctions; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotFunctions2326); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1166:1: rulePivotFunctions returns [EObject current=null] : ( (lv_abc_0_0= RULE_ID ) ) ;
    public final EObject rulePivotFunctions() throws RecognitionException {
        EObject current = null;

        Token lv_abc_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1169:28: ( ( (lv_abc_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1170:1: ( (lv_abc_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1170:1: ( (lv_abc_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1171:1: (lv_abc_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1171:1: (lv_abc_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1172:3: lv_abc_0_0= RULE_ID
            {
            lv_abc_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_rulePivotFunctions2367); 

            			newLeafNode(lv_abc_0_0, grammarAccess.getPivotFunctionsAccess().getAbcIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getPivotFunctionsRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"abc",
                    		lv_abc_0_0, 
                    		"ID");
            	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1198:1: entryRulePivotInClause returns [EObject current=null] : iv_rulePivotInClause= rulePivotInClause EOF ;
    public final EObject entryRulePivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotInClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1199:2: (iv_rulePivotInClause= rulePivotInClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1200:2: iv_rulePivotInClause= rulePivotInClause EOF
            {
             newCompositeNode(grammarAccess.getPivotInClauseRule()); 
            pushFollow(FOLLOW_rulePivotInClause_in_entryRulePivotInClause2408);
            iv_rulePivotInClause=rulePivotInClause();

            state._fsp--;

             current =iv_rulePivotInClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotInClause2418); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1207:1: rulePivotInClause returns [EObject current=null] : (otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1210:28: ( (otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1211:1: (otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1211:1: (otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1212:2: otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_rulePivotInClause2456); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotInClauseAccess().getINKeyword_0());
                
            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotInClause2468); 

                	newLeafNode(otherlv_1, grammarAccess.getPivotInClauseAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1221:1: ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) )
            int alt33=3;
            switch ( input.LA(1) ) {
            case KEYWORD_1:
                {
                int LA33_1 = input.LA(2);

                if ( ((LA33_1>=RULE_STRING && LA33_1<=RULE_ID)) ) {
                    alt33=2;
                }
                else if ( (LA33_1==KEYWORD_71) ) {
                    alt33=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt33=2;
                }
                break;
            case KEYWORD_28:
                {
                alt33=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1221:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1221:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1222:1: (lv_sq_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1222:1: (lv_sq_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1223:3: lv_sq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getSqSubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_rulePivotInClause2489);
                    lv_sq_2_0=ruleSubQueryOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPivotInClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"sq",
                            		lv_sq_2_0, 
                            		"SubQueryOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1240:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1240:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1241:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1241:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1242:3: lv_args_3_0= ruleUnpivotInClauseArgs
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_rulePivotInClause2516);
                    lv_args_3_0=ruleUnpivotInClauseArgs();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPivotInClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"args",
                            		lv_args_3_0, 
                            		"UnpivotInClauseArgs");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1259:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1259:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1260:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1260:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1261:3: lv_pinany_4_0= rulePivotInClauseAny
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getPinanyPivotInClauseAnyParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulePivotInClauseAny_in_rulePivotInClause2543);
                    lv_pinany_4_0=rulePivotInClauseAny();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPivotInClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"pinany",
                            		lv_pinany_4_0, 
                            		"PivotInClauseAny");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotInClause2557); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1290:1: entryRulePivotInClauseAny returns [String current=null] : iv_rulePivotInClauseAny= rulePivotInClauseAny EOF ;
    public final String entryRulePivotInClauseAny() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePivotInClauseAny = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1291:1: (iv_rulePivotInClauseAny= rulePivotInClauseAny EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1292:2: iv_rulePivotInClauseAny= rulePivotInClauseAny EOF
            {
             newCompositeNode(grammarAccess.getPivotInClauseAnyRule()); 
            pushFollow(FOLLOW_rulePivotInClauseAny_in_entryRulePivotInClauseAny2592);
            iv_rulePivotInClauseAny=rulePivotInClauseAny();

            state._fsp--;

             current =iv_rulePivotInClauseAny.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotInClauseAny2603); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1299:1: rulePivotInClauseAny returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )? ) ;
    public final AntlrDatatypeRuleToken rulePivotInClauseAny() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1303:6: ( (kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1304:1: (kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1304:1: (kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1305:2: kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )?
            {
            kw=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rulePivotInClauseAny2641); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_0()); 
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1310:1: (kw= KEYWORD_4 kw= KEYWORD_28 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==KEYWORD_4) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1311:2: kw= KEYWORD_4 kw= KEYWORD_28
                    {
                    kw=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rulePivotInClauseAny2655); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getCommaKeyword_1_0()); 
                        
                    kw=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rulePivotInClauseAny2668); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_1_1()); 
                        

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
    // $ANTLR end "rulePivotInClauseAny"


    // $ANTLR start "entryRuleUnpivotTable"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1330:1: entryRuleUnpivotTable returns [EObject current=null] : iv_ruleUnpivotTable= ruleUnpivotTable EOF ;
    public final EObject entryRuleUnpivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1331:2: (iv_ruleUnpivotTable= ruleUnpivotTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1332:2: iv_ruleUnpivotTable= ruleUnpivotTable EOF
            {
             newCompositeNode(grammarAccess.getUnpivotTableRule()); 
            pushFollow(FOLLOW_ruleUnpivotTable_in_entryRuleUnpivotTable2709);
            iv_ruleUnpivotTable=ruleUnpivotTable();

            state._fsp--;

             current =iv_ruleUnpivotTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotTable2719); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1339:1: ruleUnpivotTable returns [EObject current=null] : (otherlv_0= KEYWORD_79 ( (otherlv_1= KEYWORD_75 | otherlv_2= KEYWORD_73 ) otherlv_3= KEYWORD_60 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1342:28: ( (otherlv_0= KEYWORD_79 ( (otherlv_1= KEYWORD_75 | otherlv_2= KEYWORD_73 ) otherlv_3= KEYWORD_60 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1343:1: (otherlv_0= KEYWORD_79 ( (otherlv_1= KEYWORD_75 | otherlv_2= KEYWORD_73 ) otherlv_3= KEYWORD_60 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1343:1: (otherlv_0= KEYWORD_79 ( (otherlv_1= KEYWORD_75 | otherlv_2= KEYWORD_73 ) otherlv_3= KEYWORD_60 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1344:2: otherlv_0= KEYWORD_79 ( (otherlv_1= KEYWORD_75 | otherlv_2= KEYWORD_73 ) otherlv_3= KEYWORD_60 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_79,FOLLOW_KEYWORD_79_in_ruleUnpivotTable2757); 

                	newLeafNode(otherlv_0, grammarAccess.getUnpivotTableAccess().getUNPIVOTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1348:1: ( (otherlv_1= KEYWORD_75 | otherlv_2= KEYWORD_73 ) otherlv_3= KEYWORD_60 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==KEYWORD_73||LA36_0==KEYWORD_75) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1348:2: (otherlv_1= KEYWORD_75 | otherlv_2= KEYWORD_73 ) otherlv_3= KEYWORD_60
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1348:2: (otherlv_1= KEYWORD_75 | otherlv_2= KEYWORD_73 )
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==KEYWORD_75) ) {
                        alt35=1;
                    }
                    else if ( (LA35_0==KEYWORD_73) ) {
                        alt35=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 0, input);

                        throw nvae;
                    }
                    switch (alt35) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1349:2: otherlv_1= KEYWORD_75
                            {
                            otherlv_1=(Token)match(input,KEYWORD_75,FOLLOW_KEYWORD_75_in_ruleUnpivotTable2771); 

                                	newLeafNode(otherlv_1, grammarAccess.getUnpivotTableAccess().getINCLUDEKeyword_1_0_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1355:2: otherlv_2= KEYWORD_73
                            {
                            otherlv_2=(Token)match(input,KEYWORD_73,FOLLOW_KEYWORD_73_in_ruleUnpivotTable2789); 

                                	newLeafNode(otherlv_2, grammarAccess.getUnpivotTableAccess().getEXCLUDEKeyword_1_0_1());
                                

                            }
                            break;

                    }

                    otherlv_3=(Token)match(input,KEYWORD_60,FOLLOW_KEYWORD_60_in_ruleUnpivotTable2802); 

                        	newLeafNode(otherlv_3, grammarAccess.getUnpivotTableAccess().getNULLSKeyword_1_1());
                        

                    }
                    break;

            }

            otherlv_4=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleUnpivotTable2816); 

                	newLeafNode(otherlv_4, grammarAccess.getUnpivotTableAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1369:1: ( (lv_pcols_5_0= rulePivotColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1370:1: (lv_pcols_5_0= rulePivotColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1370:1: (lv_pcols_5_0= rulePivotColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1371:3: lv_pcols_5_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPcolsPivotColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotTable2836);
            lv_pcols_5_0=rulePivotColumns();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getUnpivotTableRule());
            	        }
                   		set(
                   			current, 
                   			"pcols",
                    		lv_pcols_5_0, 
                    		"PivotColumns");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1387:2: ( (lv_pfor_6_0= rulePivotForClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1388:1: (lv_pfor_6_0= rulePivotForClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1388:1: (lv_pfor_6_0= rulePivotForClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1389:3: lv_pfor_6_0= rulePivotForClause
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_rulePivotForClause_in_ruleUnpivotTable2857);
            lv_pfor_6_0=rulePivotForClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getUnpivotTableRule());
            	        }
                   		set(
                   			current, 
                   			"pfor",
                    		lv_pfor_6_0, 
                    		"PivotForClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1405:2: ( (lv_inop_7_0= ruleUnpivotInClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1406:1: (lv_inop_7_0= ruleUnpivotInClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1406:1: (lv_inop_7_0= ruleUnpivotInClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1407:3: lv_inop_7_0= ruleUnpivotInClause
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getInopUnpivotInClauseParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleUnpivotInClause_in_ruleUnpivotTable2878);
            lv_inop_7_0=ruleUnpivotInClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getUnpivotTableRule());
            	        }
                   		set(
                   			current, 
                   			"inop",
                    		lv_inop_7_0, 
                    		"UnpivotInClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_8=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleUnpivotTable2891); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1436:1: entryRuleUnpivotInClause returns [EObject current=null] : iv_ruleUnpivotInClause= ruleUnpivotInClause EOF ;
    public final EObject entryRuleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1437:2: (iv_ruleUnpivotInClause= ruleUnpivotInClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1438:2: iv_ruleUnpivotInClause= ruleUnpivotInClause EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseRule()); 
            pushFollow(FOLLOW_ruleUnpivotInClause_in_entryRuleUnpivotInClause2925);
            iv_ruleUnpivotInClause=ruleUnpivotInClause();

            state._fsp--;

             current =iv_ruleUnpivotInClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClause2935); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1445:1: ruleUnpivotInClause returns [EObject current=null] : ( () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2 ) ;
    public final EObject ruleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1448:28: ( ( () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1449:1: ( () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1449:1: ( () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1449:2: () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1449:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1450:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getUnpivotInClauseAccess().getUnipivotInClauseAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1455:2: ( (lv_op_1_0= KEYWORD_20 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1456:1: (lv_op_1_0= KEYWORD_20 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1456:1: (lv_op_1_0= KEYWORD_20 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1457:3: lv_op_1_0= KEYWORD_20
            {
            lv_op_1_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleUnpivotInClause2988); 

                    newLeafNode(lv_op_1_0, grammarAccess.getUnpivotInClauseAccess().getOpINKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUnpivotInClauseRule());
            	        }
                   		setWithLastConsumed(current, "op", lv_op_1_0, "IN");
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleUnpivotInClause3012); 

                	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1476:1: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1477:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1477:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1478:3: lv_args_3_0= ruleUnpivotInClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_ruleUnpivotInClause3032);
            lv_args_3_0=ruleUnpivotInClauseArgs();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getUnpivotInClauseRule());
            	        }
                   		set(
                   			current, 
                   			"args",
                    		lv_args_3_0, 
                    		"UnpivotInClauseArgs");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleUnpivotInClause3045); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1507:1: entryRuleUnpivotInClauseArgs returns [EObject current=null] : iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF ;
    public final EObject entryRuleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArgs = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1508:2: (iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1509:2: iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseArgsRule()); 
            pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_entryRuleUnpivotInClauseArgs3079);
            iv_ruleUnpivotInClauseArgs=ruleUnpivotInClauseArgs();

            state._fsp--;

             current =iv_ruleUnpivotInClauseArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClauseArgs3089); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1516:1: ruleUnpivotInClauseArgs returns [EObject current=null] : (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) ;
    public final EObject ruleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_UnpivotInClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1519:28: ( (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1520:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1520:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1521:5: this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getUnpivotInClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3136);
            this_UnpivotInClauseArg_0=ruleUnpivotInClauseArg();

            state._fsp--;


                    current = this_UnpivotInClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1529:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==KEYWORD_4) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1529:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1529:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1530:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getUnpivotInClauseArgsAccess().getUicargsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1535:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    int cnt37=0;
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==KEYWORD_4) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1536:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleUnpivotInClauseArgs3159); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1540:1: ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1541:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1541:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1542:3: lv_entries_3_0= ruleUnpivotInClauseArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getEntriesUnpivotInClauseArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3179);
                    	    lv_entries_3_0=ruleUnpivotInClauseArg();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getUnpivotInClauseArgsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"UnpivotInClauseArg");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt37 >= 1 ) break loop37;
                                EarlyExitException eee =
                                    new EarlyExitException(37, input);
                                throw eee;
                        }
                        cnt37++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1566:1: entryRuleUnpivotInClauseArg returns [EObject current=null] : iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF ;
    public final EObject entryRuleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1567:2: (iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1568:2: iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseArgRule()); 
            pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_entryRuleUnpivotInClauseArg3218);
            iv_ruleUnpivotInClauseArg=ruleUnpivotInClauseArg();

            state._fsp--;

             current =iv_ruleUnpivotInClauseArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClauseArg3228); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1575:1: ruleUnpivotInClauseArg returns [EObject current=null] : ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) ;
    public final EObject ruleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_pcols_0_0 = null;

        EObject lv_cfuls_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1578:28: ( ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1579:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1579:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1579:2: ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1579:2: ( (lv_pcols_0_0= rulePivotColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1580:1: (lv_pcols_0_0= rulePivotColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1580:1: (lv_pcols_0_0= rulePivotColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1581:3: lv_pcols_0_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getPcolsPivotColumnsParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3274);
            lv_pcols_0_0=rulePivotColumns();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getUnpivotInClauseArgRule());
            	        }
                   		set(
                   			current, 
                   			"pcols",
                    		lv_pcols_0_0, 
                    		"PivotColumns");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1597:2: (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==KEYWORD_19) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1598:2: otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) )
                    {
                    otherlv_1=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleUnpivotInClauseArg3288); 

                        	newLeafNode(otherlv_1, grammarAccess.getUnpivotInClauseArgAccess().getASKeyword_1_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1602:1: ( (lv_cfuls_2_0= rulePivotColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1603:1: (lv_cfuls_2_0= rulePivotColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1603:1: (lv_cfuls_2_0= rulePivotColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1604:3: lv_cfuls_2_0= rulePivotColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getCfulsPivotColumnsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3308);
                    lv_cfuls_2_0=rulePivotColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getUnpivotInClauseArgRule());
                    	        }
                           		set(
                           			current, 
                           			"cfuls",
                            		lv_cfuls_2_0, 
                            		"PivotColumns");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1628:1: entryRulePivotForClause returns [EObject current=null] : iv_rulePivotForClause= rulePivotForClause EOF ;
    public final EObject entryRulePivotForClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotForClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1629:2: (iv_rulePivotForClause= rulePivotForClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1630:2: iv_rulePivotForClause= rulePivotForClause EOF
            {
             newCompositeNode(grammarAccess.getPivotForClauseRule()); 
            pushFollow(FOLLOW_rulePivotForClause_in_entryRulePivotForClause3345);
            iv_rulePivotForClause=rulePivotForClause();

            state._fsp--;

             current =iv_rulePivotForClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotForClause3355); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1637:1: rulePivotForClause returns [EObject current=null] : (otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) ) ;
    public final EObject rulePivotForClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ColumnFull_1 = null;

        EObject this_Columns_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1640:28: ( (otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1641:1: (otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1641:1: (otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1642:2: otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_31,FOLLOW_KEYWORD_31_in_rulePivotForClause3393); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotForClauseAccess().getFORKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1646:1: (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( ((LA40_0>=RULE_STRING && LA40_0<=RULE_ID)) ) {
                alt40=1;
            }
            else if ( (LA40_0==KEYWORD_1) ) {
                alt40=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1647:5: this_ColumnFull_1= ruleColumnFull
                    {
                     
                            newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_rulePivotForClause3415);
                    this_ColumnFull_1=ruleColumnFull();

                    state._fsp--;


                            current = this_ColumnFull_1;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1656:6: (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1656:6: (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1657:2: otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2
                    {
                    otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotForClause3434); 

                        	newLeafNode(otherlv_2, grammarAccess.getPivotForClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_ruleColumns_in_rulePivotForClause3455);
                    this_Columns_3=ruleColumns();

                    state._fsp--;


                            current = this_Columns_3;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotForClause3467); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1683:1: entryRulePivotColumns returns [EObject current=null] : iv_rulePivotColumns= rulePivotColumns EOF ;
    public final EObject entryRulePivotColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1684:2: (iv_rulePivotColumns= rulePivotColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1685:2: iv_rulePivotColumns= rulePivotColumns EOF
            {
             newCompositeNode(grammarAccess.getPivotColumnsRule()); 
            pushFollow(FOLLOW_rulePivotColumns_in_entryRulePivotColumns3503);
            iv_rulePivotColumns=rulePivotColumns();

            state._fsp--;

             current =iv_rulePivotColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotColumns3513); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1692:1: rulePivotColumns returns [EObject current=null] : (this_PivotCol_0= rulePivotCol | (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 ) ) ;
    public final EObject rulePivotColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_PivotCol_0 = null;

        EObject this_PivotCols_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1695:28: ( (this_PivotCol_0= rulePivotCol | (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1696:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1696:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( ((LA41_0>=RULE_STRING && LA41_0<=RULE_ID)) ) {
                alt41=1;
            }
            else if ( (LA41_0==KEYWORD_1) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1697:5: this_PivotCol_0= rulePivotCol
                    {
                     
                            newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_rulePivotCol_in_rulePivotColumns3560);
                    this_PivotCol_0=rulePivotCol();

                    state._fsp--;


                            current = this_PivotCol_0;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1706:6: (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1706:6: (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1707:2: otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2
                    {
                    otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotColumns3579); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotColumnsAccess().getLeftParenthesisKeyword_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColsParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_rulePivotCols_in_rulePivotColumns3600);
                    this_PivotCols_2=rulePivotCols();

                    state._fsp--;


                            current = this_PivotCols_2;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotColumns3612); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1733:1: entryRulePivotCols returns [EObject current=null] : iv_rulePivotCols= rulePivotCols EOF ;
    public final EObject entryRulePivotCols() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCols = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1734:2: (iv_rulePivotCols= rulePivotCols EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1735:2: iv_rulePivotCols= rulePivotCols EOF
            {
             newCompositeNode(grammarAccess.getPivotColsRule()); 
            pushFollow(FOLLOW_rulePivotCols_in_entryRulePivotCols3647);
            iv_rulePivotCols=rulePivotCols();

            state._fsp--;

             current =iv_rulePivotCols; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotCols3657); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1742:1: rulePivotCols returns [EObject current=null] : (this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) ;
    public final EObject rulePivotCols() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PivotCol_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1745:28: ( (this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1746:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1746:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1747:5: this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getPivotColsAccess().getPivotColParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePivotCol_in_rulePivotCols3704);
            this_PivotCol_0=rulePivotCol();

            state._fsp--;


                    current = this_PivotCol_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1755:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==KEYWORD_4) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1755:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1755:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1756:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getPivotColsAccess().getPvcsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1761:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+
                    int cnt42=0;
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==KEYWORD_4) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1762:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rulePivotCols3727); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1766:1: ( (lv_entries_3_0= rulePivotCol ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1767:1: (lv_entries_3_0= rulePivotCol )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1767:1: (lv_entries_3_0= rulePivotCol )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1768:3: lv_entries_3_0= rulePivotCol
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColsAccess().getEntriesPivotColParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePivotCol_in_rulePivotCols3747);
                    	    lv_entries_3_0=rulePivotCol();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getPivotColsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"PivotCol");
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
    // $ANTLR end "rulePivotCols"


    // $ANTLR start "entryRulePivotCol"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1792:1: entryRulePivotCol returns [EObject current=null] : iv_rulePivotCol= rulePivotCol EOF ;
    public final EObject entryRulePivotCol() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCol = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1793:2: (iv_rulePivotCol= rulePivotCol EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1794:2: iv_rulePivotCol= rulePivotCol EOF
            {
             newCompositeNode(grammarAccess.getPivotColRule()); 
            pushFollow(FOLLOW_rulePivotCol_in_entryRulePivotCol3786);
            iv_rulePivotCol=rulePivotCol();

            state._fsp--;

             current =iv_rulePivotCol; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotCol3796); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1801:1: rulePivotCol returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject rulePivotCol() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1804:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1805:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1805:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1806:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getPivotColAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_rulePivotCol3843);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1814:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==KEYWORD_6) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1814:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1814:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1815:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getPivotColAccess().getPcolsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1820:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt44=0;
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==KEYWORD_6) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1821:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rulePivotCol3866); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1825:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1826:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1826:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1827:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_rulePivotCol3886);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getPivotColRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"DbObjectName");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt44 >= 1 ) break loop44;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1851:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1852:2: (iv_ruleTableFull= ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1853:2: iv_ruleTableFull= ruleTableFull EOF
            {
             newCompositeNode(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull3925);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;

             current =iv_ruleTableFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull3935); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1860:1: ruleTableFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1863:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1864:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1864:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1865:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull3982);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1873:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==KEYWORD_6) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1873:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1873:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1874:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1879:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt46=0;
                    loop46:
                    do {
                        int alt46=2;
                        int LA46_0 = input.LA(1);

                        if ( (LA46_0==KEYWORD_6) ) {
                            alt46=1;
                        }


                        switch (alt46) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1880:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleTableFull4005); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1884:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1885:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1885:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1886:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull4025);
                    	    lv_entries_3_0=ruleDbObjectName();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getTableFullRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"DbObjectName");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt46 >= 1 ) break loop46;
                                EarlyExitException eee =
                                    new EarlyExitException(46, input);
                                throw eee;
                        }
                        cnt46++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1910:1: entryRuleDbObjectNameAll returns [EObject current=null] : iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF ;
    public final EObject entryRuleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectNameAll = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1911:2: (iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1912:2: iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameAllRule()); 
            pushFollow(FOLLOW_ruleDbObjectNameAll_in_entryRuleDbObjectNameAll4064);
            iv_ruleDbObjectNameAll=ruleDbObjectNameAll();

            state._fsp--;

             current =iv_ruleDbObjectNameAll; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectNameAll4074); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1919:1: ruleDbObjectNameAll returns [EObject current=null] : ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR ) ;
    public final EObject ruleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_STAR_2=null;
        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1922:28: ( ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1923:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1923:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1923:2: ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1923:2: ( (lv_dbname_0_0= ruleDBID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1924:1: (lv_dbname_0_0= ruleDBID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1924:1: (lv_dbname_0_0= ruleDBID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1925:3: lv_dbname_0_0= ruleDBID
            {
             
            	        newCompositeNode(grammarAccess.getDbObjectNameAllAccess().getDbnameDBIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleDBID_in_ruleDbObjectNameAll4120);
            lv_dbname_0_0=ruleDBID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDbObjectNameAllRule());
            	        }
                   		set(
                   			current, 
                   			"dbname",
                    		lv_dbname_0_0, 
                    		"DBID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleDbObjectNameAll4133); 

                	newLeafNode(otherlv_1, grammarAccess.getDbObjectNameAllAccess().getFullStopKeyword_1());
                
            this_STAR_2=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleDbObjectNameAll4143); 
             
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1958:1: entryRuleDbObjectName returns [EObject current=null] : iv_ruleDbObjectName= ruleDbObjectName EOF ;
    public final EObject entryRuleDbObjectName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectName = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1959:2: (iv_ruleDbObjectName= ruleDbObjectName EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1960:2: iv_ruleDbObjectName= ruleDbObjectName EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameRule()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName4177);
            iv_ruleDbObjectName=ruleDbObjectName();

            state._fsp--;

             current =iv_ruleDbObjectName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectName4187); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1967:1: ruleDbObjectName returns [EObject current=null] : ( (lv_dbname_0_0= ruleDBID ) ) ;
    public final EObject ruleDbObjectName() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1970:28: ( ( (lv_dbname_0_0= ruleDBID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1971:1: ( (lv_dbname_0_0= ruleDBID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1971:1: ( (lv_dbname_0_0= ruleDBID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1972:1: (lv_dbname_0_0= ruleDBID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1972:1: (lv_dbname_0_0= ruleDBID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1973:3: lv_dbname_0_0= ruleDBID
            {
             
            	        newCompositeNode(grammarAccess.getDbObjectNameAccess().getDbnameDBIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleDBID_in_ruleDbObjectName4232);
            lv_dbname_0_0=ruleDBID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDbObjectNameRule());
            	        }
                   		set(
                   			current, 
                   			"dbname",
                    		lv_dbname_0_0, 
                    		"DBID");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1997:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1998:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1999:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns4266);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;

             current =iv_ruleOrderByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns4276); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2006:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2009:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2010:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2010:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2011:5: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4323);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;


                    current = this_OrderByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2019:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==KEYWORD_4) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2019:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2019:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2020:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2025:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt48=0;
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==KEYWORD_4) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2026:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByColumns4346); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2030:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2031:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2031:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2032:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4366);
                    	    lv_entries_3_0=ruleOrderByColumnFull();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getOrderByColumnsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"OrderByColumnFull");
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
    // $ANTLR end "ruleOrderByColumns"


    // $ANTLR start "entryRuleOrderByColumnFull"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2056:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2057:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2058:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull4405);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;

             current =iv_ruleOrderByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull4415); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2065:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 ) ) )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_colOrderInt_1_0=null;
        Token lv_direction_2_1=null;
        Token lv_direction_2_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2068:28: ( ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2069:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2069:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2069:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2069:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( ((LA50_0>=RULE_STRING && LA50_0<=RULE_ID)) ) {
                alt50=1;
            }
            else if ( (LA50_0==RULE_INT) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2069:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2069:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2070:1: (lv_colOrder_0_0= ruleColumnFull )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2070:1: (lv_colOrder_0_0= ruleColumnFull )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2071:3: lv_colOrder_0_0= ruleColumnFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull4462);
                    lv_colOrder_0_0=ruleColumnFull();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOrderByColumnFullRule());
                    	        }
                           		set(
                           			current, 
                           			"colOrder",
                            		lv_colOrder_0_0, 
                            		"ColumnFull");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2088:6: ( (lv_colOrderInt_1_0= RULE_INT ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2088:6: ( (lv_colOrderInt_1_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2089:1: (lv_colOrderInt_1_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2089:1: (lv_colOrderInt_1_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2090:3: lv_colOrderInt_1_0= RULE_INT
                    {
                    lv_colOrderInt_1_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOrderByColumnFull4485); 

                    			newLeafNode(lv_colOrderInt_1_0, grammarAccess.getOrderByColumnFullAccess().getColOrderIntINTTerminalRuleCall_0_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"colOrderInt",
                            		lv_colOrderInt_1_0, 
                            		"INT");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2106:3: ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==KEYWORD_35||LA52_0==KEYWORD_29) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2107:1: ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2107:1: ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2108:1: (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2108:1: (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_35 )
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==KEYWORD_29) ) {
                        alt51=1;
                    }
                    else if ( (LA51_0==KEYWORD_35) ) {
                        alt51=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 51, 0, input);

                        throw nvae;
                    }
                    switch (alt51) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2109:3: lv_direction_2_1= KEYWORD_29
                            {
                            lv_direction_2_1=(Token)match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_ruleOrderByColumnFull4512); 

                                    newLeafNode(lv_direction_2_1, grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                            	        }
                                   		setWithLastConsumed(current, "direction", lv_direction_2_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2122:8: lv_direction_2_2= KEYWORD_35
                            {
                            lv_direction_2_2=(Token)match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_ruleOrderByColumnFull4540); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2146:1: entryRuleGroupByColumns returns [EObject current=null] : iv_ruleGroupByColumns= ruleGroupByColumns EOF ;
    public final EObject entryRuleGroupByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2147:2: (iv_ruleGroupByColumns= ruleGroupByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2148:2: iv_ruleGroupByColumns= ruleGroupByColumns EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnsRule()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns4590);
            iv_ruleGroupByColumns=ruleGroupByColumns();

            state._fsp--;

             current =iv_ruleGroupByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumns4600); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2155:1: ruleGroupByColumns returns [EObject current=null] : (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) ;
    public final EObject ruleGroupByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_GroupByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2158:28: ( (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2159:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2159:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2160:5: this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns4647);
            this_GroupByColumnFull_0=ruleGroupByColumnFull();

            state._fsp--;


                    current = this_GroupByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2168:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==KEYWORD_4) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2168:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2168:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2169:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2174:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    int cnt53=0;
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==KEYWORD_4) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2175:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleGroupByColumns4670); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2179:1: ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2180:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2180:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2181:3: lv_entries_3_0= ruleGroupByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns4690);
                    	    lv_entries_3_0=ruleGroupByColumnFull();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getGroupByColumnsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"GroupByColumnFull");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt53 >= 1 ) break loop53;
                                EarlyExitException eee =
                                    new EarlyExitException(53, input);
                                throw eee;
                        }
                        cnt53++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2205:1: entryRuleGroupByColumnFull returns [EObject current=null] : iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF ;
    public final EObject entryRuleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2206:2: (iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2207:2: iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnFullRule()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull4729);
            iv_ruleGroupByColumnFull=ruleGroupByColumnFull();

            state._fsp--;

             current =iv_ruleGroupByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumnFull4739); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2214:1: ruleGroupByColumnFull returns [EObject current=null] : ( (lv_colGrBy_0_0= ruleColumnFull ) ) ;
    public final EObject ruleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject lv_colGrBy_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2217:28: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2218:1: ( (lv_colGrBy_0_0= ruleColumnFull ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2218:1: ( (lv_colGrBy_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2219:1: (lv_colGrBy_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2219:1: (lv_colGrBy_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2220:3: lv_colGrBy_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getColGrByColumnFullParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleGroupByColumnFull4784);
            lv_colGrBy_0_0=ruleColumnFull();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupByColumnFullRule());
            	        }
                   		set(
                   			current, 
                   			"colGrBy",
                    		lv_colGrBy_0_0, 
                    		"ColumnFull");
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
    // $ANTLR end "ruleGroupByColumnFull"


    // $ANTLR start "entryRuleFullExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2244:1: entryRuleFullExpression returns [EObject current=null] : iv_ruleFullExpression= ruleFullExpression EOF ;
    public final EObject entryRuleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2245:2: (iv_ruleFullExpression= ruleFullExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2246:2: iv_ruleFullExpression= ruleFullExpression EOF
            {
             newCompositeNode(grammarAccess.getFullExpressionRule()); 
            pushFollow(FOLLOW_ruleFullExpression_in_entryRuleFullExpression4818);
            iv_ruleFullExpression=ruleFullExpression();

            state._fsp--;

             current =iv_ruleFullExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullExpression4828); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2253:1: ruleFullExpression returns [EObject current=null] : (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) ;
    public final EObject ruleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExpressionFragment_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2256:28: ( (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2257:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2257:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2258:5: this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleExpressionFragment_in_ruleFullExpression4875);
            this_ExpressionFragment_0=ruleExpressionFragment();

            state._fsp--;


                    current = this_ExpressionFragment_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2266:1: ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==KEYWORD_27||LA56_0==KEYWORD_22||LA56_0==RULE_JRNPARAM) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2266:2: () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2266:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2267:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2272:2: ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    int cnt55=0;
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==KEYWORD_27||LA55_0==KEYWORD_22||LA55_0==RULE_JRNPARAM) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2273:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2273:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2274:3: lv_entries_2_0= ruleExpressionFragmentSecond
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression4905);
                    	    lv_entries_2_0=ruleExpressionFragmentSecond();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getFullExpressionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_2_0, 
                    	            		"ExpressionFragmentSecond");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

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
    // $ANTLR end "ruleFullExpression"


    // $ANTLR start "entryRuleExpressionFragmentSecond"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2298:1: entryRuleExpressionFragmentSecond returns [EObject current=null] : iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF ;
    public final EObject entryRuleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragmentSecond = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2299:2: (iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2300:2: iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentSecondRule()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond4943);
            iv_ruleExpressionFragmentSecond=ruleExpressionFragmentSecond();

            state._fsp--;

             current =iv_ruleExpressionFragmentSecond; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragmentSecond4953); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2307:1: ruleExpressionFragmentSecond returns [EObject current=null] : ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) ;
    public final EObject ruleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        Token lv_c_0_1=null;
        Token lv_c_0_2=null;
        Token lv_notPrm_2_0=null;
        EObject lv_efrag_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2310:28: ( ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2311:1: ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2311:1: ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==KEYWORD_27||LA58_0==KEYWORD_22) ) {
                alt58=1;
            }
            else if ( (LA58_0==RULE_JRNPARAM) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2311:2: ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2311:2: ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2311:3: ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2311:3: ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2312:1: ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2312:1: ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2313:1: (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2313:1: (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 )
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==KEYWORD_27) ) {
                        alt57=1;
                    }
                    else if ( (LA57_0==KEYWORD_22) ) {
                        alt57=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 57, 0, input);

                        throw nvae;
                    }
                    switch (alt57) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2314:3: lv_c_0_1= KEYWORD_27
                            {
                            lv_c_0_1=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleExpressionFragmentSecond5000); 

                                    newLeafNode(lv_c_0_1, grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                            	        }
                                   		setWithLastConsumed(current, "c", lv_c_0_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2327:8: lv_c_0_2= KEYWORD_22
                            {
                            lv_c_0_2=(Token)match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_ruleExpressionFragmentSecond5028); 

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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2343:2: ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2344:1: (lv_efrag_1_0= ruleExpressionFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2344:1: (lv_efrag_1_0= ruleExpressionFragment )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2345:3: lv_efrag_1_0= ruleExpressionFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond5063);
                    lv_efrag_1_0=ruleExpressionFragment();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionFragmentSecondRule());
                    	        }
                           		set(
                           			current, 
                           			"efrag",
                            		lv_efrag_1_0, 
                            		"ExpressionFragment");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2362:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2362:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2363:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2363:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2364:3: lv_notPrm_2_0= RULE_JRNPARAM
                    {
                    lv_notPrm_2_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragmentSecond5087); 

                    			newLeafNode(lv_notPrm_2_0, grammarAccess.getExpressionFragmentSecondAccess().getNotPrmJRNPARAMTerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"notPrm",
                            		lv_notPrm_2_0, 
                            		"JRNPARAM");
                    	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2388:1: entryRuleExpressionFragment returns [EObject current=null] : iv_ruleExpressionFragment= ruleExpressionFragment EOF ;
    public final EObject entryRuleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2389:2: (iv_ruleExpressionFragment= ruleExpressionFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2390:2: iv_ruleExpressionFragment= ruleExpressionFragment EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment5127);
            iv_ruleExpressionFragment=ruleExpressionFragment();

            state._fsp--;

             current =iv_ruleExpressionFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragment5137); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2397:1: ruleExpressionFragment returns [EObject current=null] : ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) ) ;
    public final EObject ruleExpressionFragment() throws RecognitionException {
        EObject current = null;

        Token lv_notPrm_3_0=null;
        EObject lv_expgroup_0_0 = null;

        EObject lv_exp_1_0 = null;

        EObject lv_xexp_2_1 = null;

        EObject lv_xexp_2_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2400:28: ( ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2401:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2401:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) )
            int alt60=4;
            switch ( input.LA(1) ) {
            case KEYWORD_44:
            case KEYWORD_45:
                {
                alt60=1;
                }
                break;
            case KEYWORD_1:
                {
                int LA60_2 = input.LA(2);

                if ( (LA60_2==KEYWORD_51||LA60_2==KEYWORD_34||(LA60_2>=KEYWORD_44 && LA60_2<=KEYWORD_45)||LA60_2==KEYWORD_15||LA60_2==KEYWORD_1||(LA60_2>=RULE_JRPARAM && LA60_2<=RULE_JRNPARAM)||(LA60_2>=RULE_INT && LA60_2<=RULE_ID)) ) {
                    alt60=1;
                }
                else if ( (LA60_2==KEYWORD_71) ) {
                    alt60=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 2, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_51:
            case KEYWORD_34:
            case RULE_JRPARAM:
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt60=2;
                }
                break;
            case RULE_JRNPARAM:
                {
                int LA60_4 = input.LA(2);

                if ( (LA60_4==EOF||LA60_4==KEYWORD_99||LA60_4==KEYWORD_95||LA60_4==KEYWORD_89||LA60_4==KEYWORD_82||LA60_4==KEYWORD_85||LA60_4==KEYWORD_77||(LA60_4>=KEYWORD_67 && LA60_4<=KEYWORD_68)||LA60_4==KEYWORD_70||LA60_4==KEYWORD_52||LA60_4==KEYWORD_55||(LA60_4>=KEYWORD_57 && LA60_4<=KEYWORD_58)||(LA60_4>=KEYWORD_64 && LA60_4<=KEYWORD_66)||(LA60_4>=KEYWORD_38 && LA60_4<=KEYWORD_39)||LA60_4==KEYWORD_41||(LA60_4>=KEYWORD_49 && LA60_4<=KEYWORD_50)||LA60_4==KEYWORD_27||LA60_4==KEYWORD_22||LA60_4==KEYWORD_2||LA60_4==KEYWORD_4||LA60_4==RULE_JRNPARAM) ) {
                    alt60=4;
                }
                else if ( ((LA60_4>=KEYWORD_96 && LA60_4<=KEYWORD_97)||LA60_4==KEYWORD_83||LA60_4==KEYWORD_72||LA60_4==KEYWORD_76||LA60_4==KEYWORD_69||LA60_4==KEYWORD_43||LA60_4==KEYWORD_14||(LA60_4>=KEYWORD_16 && LA60_4<=KEYWORD_18)||LA60_4==KEYWORD_20||(LA60_4>=KEYWORD_23 && LA60_4<=KEYWORD_24)||LA60_4==KEYWORD_3||LA60_4==KEYWORD_5||(LA60_4>=KEYWORD_7 && LA60_4<=KEYWORD_10)||LA60_4==RULE_STAR) ) {
                    alt60=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 4, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_15:
                {
                alt60=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }

            switch (alt60) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2401:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2401:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2402:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2402:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2403:3: lv_expgroup_0_0= ruleExpressionGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment5183);
                    lv_expgroup_0_0=ruleExpressionGroup();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"expgroup",
                            		lv_expgroup_0_0, 
                            		"ExpressionGroup");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2420:6: ( (lv_exp_1_0= ruleExpression ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2420:6: ( (lv_exp_1_0= ruleExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2421:1: (lv_exp_1_0= ruleExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2421:1: (lv_exp_1_0= ruleExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2422:3: lv_exp_1_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleExpressionFragment5210);
                    lv_exp_1_0=ruleExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"exp",
                            		lv_exp_1_0, 
                            		"Expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2439:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2439:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2440:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2440:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2441:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2441:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    int alt59=2;
                    alt59 = dfa59.predict(input);
                    switch (alt59) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2442:3: lv_xexp_2_1= ruleXExpression
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleXExpression_in_ruleExpressionFragment5239);
                            lv_xexp_2_1=ruleXExpression();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"xexp",
                                    		lv_xexp_2_1, 
                                    		"XExpression");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2457:8: lv_xexp_2_2= ruleXExpression_
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpression_ParserRuleCall_2_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleXExpression__in_ruleExpressionFragment5258);
                            lv_xexp_2_2=ruleXExpression_();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"xexp",
                                    		lv_xexp_2_2, 
                                    		"XExpression_");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2476:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2476:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2477:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2477:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2478:3: lv_notPrm_3_0= RULE_JRNPARAM
                    {
                    lv_notPrm_3_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragment5284); 

                    			newLeafNode(lv_notPrm_3_0, grammarAccess.getExpressionFragmentAccess().getNotPrmJRNPARAMTerminalRuleCall_3_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExpressionFragmentRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"notPrm",
                            		lv_notPrm_3_0, 
                            		"JRNPARAM");
                    	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2502:1: entryRuleExpressionGroup returns [EObject current=null] : iv_ruleExpressionGroup= ruleExpressionGroup EOF ;
    public final EObject entryRuleExpressionGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionGroup = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2503:2: (iv_ruleExpressionGroup= ruleExpressionGroup EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2504:2: iv_ruleExpressionGroup= ruleExpressionGroup EOF
            {
             newCompositeNode(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup5324);
            iv_ruleExpressionGroup=ruleExpressionGroup();

            state._fsp--;

             current =iv_ruleExpressionGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionGroup5334); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2511:1: ruleExpressionGroup returns [EObject current=null] : ( () ( ( (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 ) ;
    public final EObject ruleExpressionGroup() throws RecognitionException {
        EObject current = null;

        Token lv_isnot_1_1=null;
        Token lv_isnot_1_2=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2514:28: ( ( () ( ( (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2515:1: ( () ( ( (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2515:1: ( () ( ( (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2515:2: () ( ( (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2515:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2516:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpressionGroupAccess().getExprGroupAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2521:2: ( ( (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( ((LA62_0>=KEYWORD_44 && LA62_0<=KEYWORD_45)) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2522:1: ( (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2522:1: ( (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2523:1: (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2523:1: (lv_isnot_1_1= KEYWORD_45 | lv_isnot_1_2= KEYWORD_44 )
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==KEYWORD_45) ) {
                        alt61=1;
                    }
                    else if ( (LA61_0==KEYWORD_44) ) {
                        alt61=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 61, 0, input);

                        throw nvae;
                    }
                    switch (alt61) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2524:3: lv_isnot_1_1= KEYWORD_45
                            {
                            lv_isnot_1_1=(Token)match(input,KEYWORD_45,FOLLOW_KEYWORD_45_in_ruleExpressionGroup5389); 

                                    newLeafNode(lv_isnot_1_1, grammarAccess.getExpressionGroupAccess().getIsnotNOTKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionGroupRule());
                            	        }
                                   		setWithLastConsumed(current, "isnot", lv_isnot_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2537:8: lv_isnot_1_2= KEYWORD_44
                            {
                            lv_isnot_1_2=(Token)match(input,KEYWORD_44,FOLLOW_KEYWORD_44_in_ruleExpressionGroup5417); 

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

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleExpressionGroup5445); 

                	newLeafNode(otherlv_2, grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2558:1: ( (lv_expr_3_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2559:1: (lv_expr_3_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2559:1: (lv_expr_3_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2560:3: lv_expr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleExpressionGroup5465);
            lv_expr_3_0=ruleFullExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExpressionGroupRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_3_0, 
                    		"FullExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleExpressionGroup5478); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2589:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2590:2: (iv_ruleXExpression= ruleXExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2591:2: iv_ruleXExpression= ruleXExpression EOF
            {
             newCompositeNode(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression5512);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;

             current =iv_ruleXExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression5522); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2598:1: ruleXExpression returns [EObject current=null] : (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2601:28: ( (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2602:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2602:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2603:2: otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13
            {
            otherlv_0=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleXExpression5560); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpressionAccess().getXKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2607:1: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2608:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpressionAccess().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression5581); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpressionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2618:1: ( (lv_xf_3_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2619:1: (lv_xf_3_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2619:1: (lv_xf_3_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2620:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression5601);
            lv_xf_3_0=ruleXFunction();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"xf",
                    		lv_xf_3_0, 
                    		"XFunction");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression5614); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpressionAccess().getCommaKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2641:1: ( (lv_col_5_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2642:1: (lv_col_5_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2642:1: (lv_col_5_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2643:3: lv_col_5_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getColOperandParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleXExpression5634);
            lv_col_5_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"col",
                    		lv_col_5_0, 
                    		"Operand");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2659:2: (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==KEYWORD_4) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2660:2: otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression5648); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpressionAccess().getCommaKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2664:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2665:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2665:1: (lv_prm_7_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2666:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression5668);
                    lv_prm_7_0=ruleXExpressionParams();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"prm",
                            		lv_prm_7_0, 
                            		"XExpressionParams");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleXExpression5683); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2695:1: entryRuleXExpression_ returns [EObject current=null] : iv_ruleXExpression_= ruleXExpression_ EOF ;
    public final EObject entryRuleXExpression_() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression_ = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2696:2: (iv_ruleXExpression_= ruleXExpression_ EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2697:2: iv_ruleXExpression_= ruleXExpression_ EOF
            {
             newCompositeNode(grammarAccess.getXExpression_Rule()); 
            pushFollow(FOLLOW_ruleXExpression__in_entryRuleXExpression_5717);
            iv_ruleXExpression_=ruleXExpression_();

            state._fsp--;

             current =iv_ruleXExpression_; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression_5727); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2704:1: ruleXExpression_ returns [EObject current=null] : (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2707:28: ( (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2708:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2708:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2709:2: otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13
            {
            otherlv_0=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleXExpression_5765); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpression_Access().getXKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2713:1: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2714:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpression_Access().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression_5786); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpression_Access().getLeftCurlyBracketKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2724:1: ( (lv_xf_3_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2725:1: (lv_xf_3_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2725:1: (lv_xf_3_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2726:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression_5806);
            lv_xf_3_0=ruleXFunction();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
            	        }
                   		set(
                   			current, 
                   			"xf",
                    		lv_xf_3_0, 
                    		"XFunction");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleXExpression_5819); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpression_Access().getVerticalLineKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2747:1: ( (lv_col_5_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2748:1: (lv_col_5_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2748:1: (lv_col_5_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2749:3: lv_col_5_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getColOperandParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleXExpression_5839);
            lv_col_5_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
            	        }
                   		set(
                   			current, 
                   			"col",
                    		lv_col_5_0, 
                    		"Operand");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2765:2: (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==KEYWORD_12) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2766:2: otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleXExpression_5853); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpression_Access().getVerticalLineKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2770:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2771:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2771:1: (lv_prm_7_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2772:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpression_Access().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression_5873);
                    lv_prm_7_0=ruleXExpressionParams();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
                    	        }
                           		set(
                           			current, 
                           			"prm",
                            		lv_prm_7_0, 
                            		"XExpressionParams");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleXExpression_5888); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2801:1: entryRuleXExpressionParams returns [EObject current=null] : iv_ruleXExpressionParams= ruleXExpressionParams EOF ;
    public final EObject entryRuleXExpressionParams() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionParams = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2802:2: (iv_ruleXExpressionParams= ruleXExpressionParams EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2803:2: iv_ruleXExpressionParams= ruleXExpressionParams EOF
            {
             newCompositeNode(grammarAccess.getXExpressionParamsRule()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams5922);
            iv_ruleXExpressionParams=ruleXExpressionParams();

            state._fsp--;

             current =iv_ruleXExpressionParams; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionParams5932); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2810:1: ruleXExpressionParams returns [EObject current=null] : (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) ;
    public final EObject ruleXExpressionParams() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_JRParameter_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2813:28: ( (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2814:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2814:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2815:5: this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams5979);
            this_JRParameter_0=ruleJRParameter();

            state._fsp--;


                    current = this_JRParameter_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2823:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==KEYWORD_4) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2823:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2823:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2824:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2829:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    int cnt65=0;
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==KEYWORD_4) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2830:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpressionParams6002); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2834:1: ( (lv_entries_3_0= ruleJRParameter ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2835:1: (lv_entries_3_0= ruleJRParameter )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2835:1: (lv_entries_3_0= ruleJRParameter )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2836:3: lv_entries_3_0= ruleJRParameter
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams6022);
                    	    lv_entries_3_0=ruleJRParameter();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getXExpressionParamsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"JRParameter");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt65 >= 1 ) break loop65;
                                EarlyExitException eee =
                                    new EarlyExitException(65, input);
                                throw eee;
                        }
                        cnt65++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2860:1: entryRuleJRParameter returns [EObject current=null] : iv_ruleJRParameter= ruleJRParameter EOF ;
    public final EObject entryRuleJRParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameter = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2861:2: (iv_ruleJRParameter= ruleJRParameter EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2862:2: iv_ruleJRParameter= ruleJRParameter EOF
            {
             newCompositeNode(grammarAccess.getJRParameterRule()); 
            pushFollow(FOLLOW_ruleJRParameter_in_entryRuleJRParameter6061);
            iv_ruleJRParameter=ruleJRParameter();

            state._fsp--;

             current =iv_ruleJRParameter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRParameter6071); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2869:1: ruleJRParameter returns [EObject current=null] : ( (lv_jrprm_0_0= RULE_ID ) ) ;
    public final EObject ruleJRParameter() throws RecognitionException {
        EObject current = null;

        Token lv_jrprm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2872:28: ( ( (lv_jrprm_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2873:1: ( (lv_jrprm_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2873:1: ( (lv_jrprm_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2874:1: (lv_jrprm_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2874:1: (lv_jrprm_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2875:3: lv_jrprm_0_0= RULE_ID
            {
            lv_jrprm_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJRParameter6112); 

            			newLeafNode(lv_jrprm_0_0, grammarAccess.getJRParameterAccess().getJrprmIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getJRParameterRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"jrprm",
                    		lv_jrprm_0_0, 
                    		"ID");
            	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2899:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2900:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2901:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression6151);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression6161); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2908:1: ruleExpression returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        Token lv_isnull_1_1=null;
        Token lv_isnull_1_2=null;
        EObject lv_op1_0_0 = null;

        EObject lv_in_2_0 = null;

        EObject lv_between_3_0 = null;

        EObject lv_like_4_0 = null;

        EObject lv_comp_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2911:28: ( ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2912:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2912:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2912:2: ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2912:2: ( (lv_op1_0_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2913:1: (lv_op1_0_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2913:1: (lv_op1_0_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2914:3: lv_op1_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleExpression6207);
            lv_op1_0_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"op1",
                    		lv_op1_0_0, 
                    		"Operand");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2930:2: ( ( ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) )
            int alt68=5;
            switch ( input.LA(1) ) {
            case KEYWORD_96:
            case KEYWORD_76:
                {
                alt68=1;
                }
                break;
            case KEYWORD_69:
            case KEYWORD_20:
                {
                alt68=2;
                }
                break;
            case KEYWORD_97:
            case KEYWORD_72:
                {
                alt68=3;
                }
                break;
            case KEYWORD_83:
            case KEYWORD_43:
                {
                alt68=4;
                }
                break;
            case KEYWORD_14:
            case KEYWORD_16:
            case KEYWORD_17:
            case KEYWORD_18:
            case KEYWORD_23:
            case KEYWORD_8:
            case KEYWORD_9:
            case KEYWORD_10:
                {
                alt68=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }

            switch (alt68) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2930:3: ( ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2930:3: ( ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2931:1: ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2931:1: ( (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2932:1: (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2932:1: (lv_isnull_1_1= KEYWORD_76 | lv_isnull_1_2= KEYWORD_96 )
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==KEYWORD_76) ) {
                        alt67=1;
                    }
                    else if ( (LA67_0==KEYWORD_96) ) {
                        alt67=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 67, 0, input);

                        throw nvae;
                    }
                    switch (alt67) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2933:3: lv_isnull_1_1= KEYWORD_76
                            {
                            lv_isnull_1_1=(Token)match(input,KEYWORD_76,FOLLOW_KEYWORD_76_in_ruleExpression6229); 

                                    newLeafNode(lv_isnull_1_1, grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "isnull", lv_isnull_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2946:8: lv_isnull_1_2= KEYWORD_96
                            {
                            lv_isnull_1_2=(Token)match(input,KEYWORD_96,FOLLOW_KEYWORD_96_in_ruleExpression6257); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2963:6: ( (lv_in_2_0= ruleInOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2963:6: ( (lv_in_2_0= ruleInOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2964:1: (lv_in_2_0= ruleInOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2964:1: (lv_in_2_0= ruleInOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2965:3: lv_in_2_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleInOperator_in_ruleExpression6298);
                    lv_in_2_0=ruleInOperator();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"in",
                            		lv_in_2_0, 
                            		"InOperator");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2982:6: ( (lv_between_3_0= ruleBetween ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2982:6: ( (lv_between_3_0= ruleBetween ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2983:1: (lv_between_3_0= ruleBetween )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2983:1: (lv_between_3_0= ruleBetween )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2984:3: lv_between_3_0= ruleBetween
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBetween_in_ruleExpression6325);
                    lv_between_3_0=ruleBetween();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"between",
                            		lv_between_3_0, 
                            		"Between");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3001:6: ( (lv_like_4_0= ruleLike ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3001:6: ( (lv_like_4_0= ruleLike ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3002:1: (lv_like_4_0= ruleLike )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3002:1: (lv_like_4_0= ruleLike )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3003:3: lv_like_4_0= ruleLike
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleLike_in_ruleExpression6352);
                    lv_like_4_0=ruleLike();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"like",
                            		lv_like_4_0, 
                            		"Like");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3020:6: ( (lv_comp_5_0= ruleComparison ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3020:6: ( (lv_comp_5_0= ruleComparison ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3021:1: (lv_comp_5_0= ruleComparison )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3021:1: (lv_comp_5_0= ruleComparison )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3022:3: lv_comp_5_0= ruleComparison
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleComparison_in_ruleExpression6379);
                    lv_comp_5_0=ruleComparison();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"comp",
                            		lv_comp_5_0, 
                            		"Comparison");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3046:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3047:2: (iv_ruleComparison= ruleComparison EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3048:2: iv_ruleComparison= ruleComparison EOF
            {
             newCompositeNode(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_ruleComparison_in_entryRuleComparison6415);
            iv_ruleComparison=ruleComparison();

            state._fsp--;

             current =iv_ruleComparison; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleComparison6425); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3055:1: ruleComparison returns [EObject current=null] : ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3058:28: ( ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3059:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3059:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3059:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 ) ) )? ( (lv_op2_2_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3059:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3060:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3060:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3061:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3061:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 )
            int alt69=8;
            switch ( input.LA(1) ) {
            case KEYWORD_10:
                {
                alt69=1;
                }
                break;
            case KEYWORD_18:
                {
                alt69=2;
                }
                break;
            case KEYWORD_8:
                {
                alt69=3;
                }
                break;
            case KEYWORD_16:
                {
                alt69=4;
                }
                break;
            case KEYWORD_9:
                {
                alt69=5;
                }
                break;
            case KEYWORD_17:
                {
                alt69=6;
                }
                break;
            case KEYWORD_14:
                {
                alt69=7;
                }
                break;
            case KEYWORD_23:
                {
                alt69=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }

            switch (alt69) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3062:3: lv_operator_0_1= KEYWORD_10
                    {
                    lv_operator_0_1=(Token)match(input,KEYWORD_10,FOLLOW_KEYWORD_10_in_ruleComparison6471); 

                            newLeafNode(lv_operator_0_1, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3075:8: lv_operator_0_2= KEYWORD_18
                    {
                    lv_operator_0_2=(Token)match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_ruleComparison6499); 

                            newLeafNode(lv_operator_0_2, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3088:8: lv_operator_0_3= KEYWORD_8
                    {
                    lv_operator_0_3=(Token)match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_ruleComparison6527); 

                            newLeafNode(lv_operator_0_3, grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3101:8: lv_operator_0_4= KEYWORD_16
                    {
                    lv_operator_0_4=(Token)match(input,KEYWORD_16,FOLLOW_KEYWORD_16_in_ruleComparison6555); 

                            newLeafNode(lv_operator_0_4, grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_4, null);
                    	    

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3114:8: lv_operator_0_5= KEYWORD_9
                    {
                    lv_operator_0_5=(Token)match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_ruleComparison6583); 

                            newLeafNode(lv_operator_0_5, grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_5, null);
                    	    

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3127:8: lv_operator_0_6= KEYWORD_17
                    {
                    lv_operator_0_6=(Token)match(input,KEYWORD_17,FOLLOW_KEYWORD_17_in_ruleComparison6611); 

                            newLeafNode(lv_operator_0_6, grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_6, null);
                    	    

                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3140:8: lv_operator_0_7= KEYWORD_14
                    {
                    lv_operator_0_7=(Token)match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_ruleComparison6639); 

                            newLeafNode(lv_operator_0_7, grammarAccess.getComparisonAccess().getOperatorExclamationMarkEqualsSignKeyword_0_0_6());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_7, null);
                    	    

                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3153:8: lv_operator_0_8= KEYWORD_23
                    {
                    lv_operator_0_8=(Token)match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_ruleComparison6667); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3169:2: ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 ) ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==KEYWORD_48||LA71_0==KEYWORD_26||LA71_0==KEYWORD_28) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3170:1: ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3170:1: ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3171:1: (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3171:1: (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_48 )
                    int alt70=3;
                    switch ( input.LA(1) ) {
                    case KEYWORD_28:
                        {
                        alt70=1;
                        }
                        break;
                    case KEYWORD_26:
                        {
                        alt70=2;
                        }
                        break;
                    case KEYWORD_48:
                        {
                        alt70=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 70, 0, input);

                        throw nvae;
                    }

                    switch (alt70) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3172:3: lv_subOperator_1_1= KEYWORD_28
                            {
                            lv_subOperator_1_1=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_ruleComparison6702); 

                                    newLeafNode(lv_subOperator_1_1, grammarAccess.getComparisonAccess().getSubOperatorANYKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3185:8: lv_subOperator_1_2= KEYWORD_26
                            {
                            lv_subOperator_1_2=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleComparison6730); 

                                    newLeafNode(lv_subOperator_1_2, grammarAccess.getComparisonAccess().getSubOperatorALLKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3198:8: lv_subOperator_1_3= KEYWORD_48
                            {
                            lv_subOperator_1_3=(Token)match(input,KEYWORD_48,FOLLOW_KEYWORD_48_in_ruleComparison6758); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3214:3: ( (lv_op2_2_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3215:1: (lv_op2_2_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3215:1: (lv_op2_2_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3216:3: lv_op2_2_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleComparison6794);
            lv_op2_2_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getComparisonRule());
            	        }
                   		set(
                   			current, 
                   			"op2",
                    		lv_op2_2_0, 
                    		"Operand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3240:1: entryRuleLike returns [EObject current=null] : iv_ruleLike= ruleLike EOF ;
    public final EObject entryRuleLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLike = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3241:2: (iv_ruleLike= ruleLike EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3242:2: iv_ruleLike= ruleLike EOF
            {
             newCompositeNode(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_ruleLike_in_entryRuleLike6829);
            iv_ruleLike=ruleLike();

            state._fsp--;

             current =iv_ruleLike; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLike6839); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3249:1: ruleLike returns [EObject current=null] : ( ( ( (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) ;
    public final EObject ruleLike() throws RecognitionException {
        EObject current = null;

        Token lv_opLike_0_1=null;
        Token lv_opLike_0_2=null;
        EObject lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3252:28: ( ( ( ( (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3253:1: ( ( ( (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3253:1: ( ( ( (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3253:2: ( ( (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3253:2: ( ( (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3254:1: ( (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3254:1: ( (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3255:1: (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3255:1: (lv_opLike_0_1= KEYWORD_43 | lv_opLike_0_2= KEYWORD_83 )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==KEYWORD_43) ) {
                alt72=1;
            }
            else if ( (LA72_0==KEYWORD_83) ) {
                alt72=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3256:3: lv_opLike_0_1= KEYWORD_43
                    {
                    lv_opLike_0_1=(Token)match(input,KEYWORD_43,FOLLOW_KEYWORD_43_in_ruleLike6885); 

                            newLeafNode(lv_opLike_0_1, grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLikeRule());
                    	        }
                           		setWithLastConsumed(current, "opLike", lv_opLike_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3269:8: lv_opLike_0_2= KEYWORD_83
                    {
                    lv_opLike_0_2=(Token)match(input,KEYWORD_83,FOLLOW_KEYWORD_83_in_ruleLike6913); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3285:2: ( (lv_op2_1_0= ruleLikeOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3286:1: (lv_op2_1_0= ruleLikeOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3286:1: (lv_op2_1_0= ruleLikeOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3287:3: lv_op2_1_0= ruleLikeOperand
            {
             
            	        newCompositeNode(grammarAccess.getLikeAccess().getOp2LikeOperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLikeOperand_in_ruleLike6948);
            lv_op2_1_0=ruleLikeOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLikeRule());
            	        }
                   		set(
                   			current, 
                   			"op2",
                    		lv_op2_1_0, 
                    		"LikeOperand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3311:1: entryRuleLikeOperand returns [EObject current=null] : iv_ruleLikeOperand= ruleLikeOperand EOF ;
    public final EObject entryRuleLikeOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLikeOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3312:2: (iv_ruleLikeOperand= ruleLikeOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3313:2: iv_ruleLikeOperand= ruleLikeOperand EOF
            {
             newCompositeNode(grammarAccess.getLikeOperandRule()); 
            pushFollow(FOLLOW_ruleLikeOperand_in_entryRuleLikeOperand6983);
            iv_ruleLikeOperand=ruleLikeOperand();

            state._fsp--;

             current =iv_ruleLikeOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLikeOperand6993); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3320:1: ruleLikeOperand returns [EObject current=null] : ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) ;
    public final EObject ruleLikeOperand() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op2_0_0 = null;

        EObject lv_fop2_1_0 = null;

        EObject lv_fcast_2_0 = null;

        EObject lv_fparam_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3323:28: ( ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3324:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3324:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            int alt73=4;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt73=1;
                }
                break;
            case RULE_ID:
                {
                alt73=2;
                }
                break;
            case KEYWORD_51:
                {
                alt73=3;
                }
                break;
            case RULE_JRPARAM:
                {
                alt73=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;
            }

            switch (alt73) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3324:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3324:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3325:1: (lv_op2_0_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3325:1: (lv_op2_0_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3326:3: lv_op2_0_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getOp2StringOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleLikeOperand7039);
                    lv_op2_0_0=ruleStringOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLikeOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"op2",
                            		lv_op2_0_0, 
                            		"StringOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3343:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3343:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3344:1: (lv_fop2_1_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3344:1: (lv_fop2_1_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3345:3: lv_fop2_1_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFop2OperandFunctionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleLikeOperand7066);
                    lv_fop2_1_0=ruleOperandFunction();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLikeOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"fop2",
                            		lv_fop2_1_0, 
                            		"OperandFunction");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3362:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3362:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3363:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3363:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3364:3: lv_fcast_2_0= ruleOpFunctionCast
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFcastOpFunctionCastParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionCast_in_ruleLikeOperand7093);
                    lv_fcast_2_0=ruleOpFunctionCast();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLikeOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"fcast",
                            		lv_fcast_2_0, 
                            		"OpFunctionCast");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3381:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3381:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3382:1: (lv_fparam_3_0= ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3382:1: (lv_fparam_3_0= ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3383:3: lv_fparam_3_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFparamParameterOperandParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParameterOperand_in_ruleLikeOperand7120);
                    lv_fparam_3_0=ruleParameterOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLikeOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"fparam",
                            		lv_fparam_3_0, 
                            		"ParameterOperand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3407:1: entryRuleBetween returns [EObject current=null] : iv_ruleBetween= ruleBetween EOF ;
    public final EObject entryRuleBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBetween = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3408:2: (iv_ruleBetween= ruleBetween EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3409:2: iv_ruleBetween= ruleBetween EOF
            {
             newCompositeNode(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_ruleBetween_in_entryRuleBetween7155);
            iv_ruleBetween=ruleBetween();

            state._fsp--;

             current =iv_ruleBetween; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBetween7165); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3416:1: ruleBetween returns [EObject current=null] : ( ( ( (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) ) ) ;
    public final EObject ruleBetween() throws RecognitionException {
        EObject current = null;

        Token lv_opBetween_0_1=null;
        Token lv_opBetween_0_2=null;
        Token otherlv_2=null;
        EObject lv_op2_1_0 = null;

        EObject lv_op3_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3419:28: ( ( ( ( (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3420:1: ( ( ( (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3420:1: ( ( ( (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3420:2: ( ( (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3420:2: ( ( (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3421:1: ( (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3421:1: ( (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3422:1: (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3422:1: (lv_opBetween_0_1= KEYWORD_72 | lv_opBetween_0_2= KEYWORD_97 )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==KEYWORD_72) ) {
                alt74=1;
            }
            else if ( (LA74_0==KEYWORD_97) ) {
                alt74=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }
            switch (alt74) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3423:3: lv_opBetween_0_1= KEYWORD_72
                    {
                    lv_opBetween_0_1=(Token)match(input,KEYWORD_72,FOLLOW_KEYWORD_72_in_ruleBetween7211); 

                            newLeafNode(lv_opBetween_0_1, grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBetweenRule());
                    	        }
                           		setWithLastConsumed(current, "opBetween", lv_opBetween_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3436:8: lv_opBetween_0_2= KEYWORD_97
                    {
                    lv_opBetween_0_2=(Token)match(input,KEYWORD_97,FOLLOW_KEYWORD_97_in_ruleBetween7239); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3452:2: ( (lv_op2_1_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3453:1: (lv_op2_1_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3453:1: (lv_op2_1_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3454:3: lv_op2_1_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleBetween7274);
            lv_op2_1_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBetweenRule());
            	        }
                   		set(
                   			current, 
                   			"op2",
                    		lv_op2_1_0, 
                    		"Operand");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleBetween7287); 

                	newLeafNode(otherlv_2, grammarAccess.getBetweenAccess().getANDKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3475:1: ( (lv_op3_3_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3476:1: (lv_op3_3_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3476:1: (lv_op3_3_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3477:3: lv_op3_3_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp3OperandParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleBetween7307);
            lv_op3_3_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBetweenRule());
            	        }
                   		set(
                   			current, 
                   			"op3",
                    		lv_op3_3_0, 
                    		"Operand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3501:1: entryRuleInOperator returns [EObject current=null] : iv_ruleInOperator= ruleInOperator EOF ;
    public final EObject entryRuleInOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOperator = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3502:2: (iv_ruleInOperator= ruleInOperator EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3503:2: iv_ruleInOperator= ruleInOperator EOF
            {
             newCompositeNode(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_ruleInOperator_in_entryRuleInOperator7342);
            iv_ruleInOperator=ruleInOperator();

            state._fsp--;

             current =iv_ruleInOperator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInOperator7352); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3510:1: ruleInOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2 ) ;
    public final EObject ruleInOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_subquery_3_0 = null;

        EObject lv_opList_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3513:28: ( ( () ( ( (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3514:1: ( () ( ( (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3514:1: ( () ( ( (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3514:2: () ( ( (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3514:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3515:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInOperatorAccess().getInOperAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3520:2: ( ( (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3521:1: ( (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3521:1: ( (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3522:1: (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3522:1: (lv_op_1_1= KEYWORD_69 | lv_op_1_2= KEYWORD_20 )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==KEYWORD_69) ) {
                alt75=1;
            }
            else if ( (LA75_0==KEYWORD_20) ) {
                alt75=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3523:3: lv_op_1_1= KEYWORD_69
                    {
                    lv_op_1_1=(Token)match(input,KEYWORD_69,FOLLOW_KEYWORD_69_in_ruleInOperator7407); 

                            newLeafNode(lv_op_1_1, grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getInOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3536:8: lv_op_1_2= KEYWORD_20
                    {
                    lv_op_1_2=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleInOperator7435); 

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

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleInOperator7462); 

                	newLeafNode(otherlv_2, grammarAccess.getInOperatorAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3557:1: ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==KEYWORD_1) ) {
                alt76=1;
            }
            else if ( ((LA76_0>=RULE_INT && LA76_0<=RULE_STRING_)) ) {
                alt76=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }
            switch (alt76) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3557:2: ( (lv_subquery_3_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3557:2: ( (lv_subquery_3_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3558:1: (lv_subquery_3_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3558:1: (lv_subquery_3_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3559:3: lv_subquery_3_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleInOperator7483);
                    lv_subquery_3_0=ruleSubQueryOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInOperatorRule());
                    	        }
                           		set(
                           			current, 
                           			"subquery",
                            		lv_subquery_3_0, 
                            		"SubQueryOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3576:6: ( (lv_opList_4_0= ruleOperandList ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3576:6: ( (lv_opList_4_0= ruleOperandList ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3577:1: (lv_opList_4_0= ruleOperandList )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3577:1: (lv_opList_4_0= ruleOperandList )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3578:3: lv_opList_4_0= ruleOperandList
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getOpListOperandListParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandList_in_ruleInOperator7510);
                    lv_opList_4_0=ruleOperandList();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInOperatorRule());
                    	        }
                           		set(
                           			current, 
                           			"opList",
                            		lv_opList_4_0, 
                            		"OperandList");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleInOperator7524); 

                	newLeafNode(otherlv_5, grammarAccess.getInOperatorAccess().getRightParenthesisKeyword_4());
                

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


    // $ANTLR start "entryRuleOperandList"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3607:1: entryRuleOperandList returns [EObject current=null] : iv_ruleOperandList= ruleOperandList EOF ;
    public final EObject entryRuleOperandList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandList = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3608:2: (iv_ruleOperandList= ruleOperandList EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3609:2: iv_ruleOperandList= ruleOperandList EOF
            {
             newCompositeNode(grammarAccess.getOperandListRule()); 
            pushFollow(FOLLOW_ruleOperandList_in_entryRuleOperandList7558);
            iv_ruleOperandList=ruleOperandList();

            state._fsp--;

             current =iv_ruleOperandList; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandList7568); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3616:1: ruleOperandList returns [EObject current=null] : (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) ;
    public final EObject ruleOperandList() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ScalarOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3619:28: ( (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3620:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3620:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3621:5: this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOperandListAccess().getScalarOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleScalarOperand_in_ruleOperandList7615);
            this_ScalarOperand_0=ruleScalarOperand();

            state._fsp--;


                    current = this_ScalarOperand_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3629:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==KEYWORD_4) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3629:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3629:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3630:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3635:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    int cnt77=0;
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==KEYWORD_4) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3636:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOperandList7638); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3640:1: ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3641:1: (lv_entries_3_0= ruleScalarOperand )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3641:1: (lv_entries_3_0= ruleScalarOperand )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3642:3: lv_entries_3_0= ruleScalarOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOperandListAccess().getEntriesScalarOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleScalarOperand_in_ruleOperandList7658);
                    	    lv_entries_3_0=ruleScalarOperand();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getOperandListRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"ScalarOperand");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt77 >= 1 ) break loop77;
                                EarlyExitException eee =
                                    new EarlyExitException(77, input);
                                throw eee;
                        }
                        cnt77++;
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


    // $ANTLR start "entryRuleOperand"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3666:1: entryRuleOperand returns [EObject current=null] : iv_ruleOperand= ruleOperand EOF ;
    public final EObject entryRuleOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3667:2: (iv_ruleOperand= ruleOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3668:2: iv_ruleOperand= ruleOperand EOF
            {
             newCompositeNode(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_ruleOperand_in_entryRuleOperand7697);
            iv_ruleOperand=ruleOperand();

            state._fsp--;

             current =iv_ruleOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperand7707); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3675:1: ruleOperand returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) ;
    public final EObject ruleOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token this_STAR_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_op1_0_0 = null;

        EObject lv_right_11_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3678:28: ( ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3679:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3679:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3679:2: ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3679:2: ( (lv_op1_0_0= ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3680:1: (lv_op1_0_0= ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3680:1: (lv_op1_0_0= ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3681:3: lv_op1_0_0= ruleOperandFragment
            {
             
            	        newCompositeNode(grammarAccess.getOperandAccess().getOp1OperandFragmentParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand7753);
            lv_op1_0_0=ruleOperandFragment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperandRule());
            	        }
                   		set(
                   			current, 
                   			"op1",
                    		lv_op1_0_0, 
                    		"OperandFragment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3697:2: ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==KEYWORD_24||LA80_0==KEYWORD_3||LA80_0==KEYWORD_5||LA80_0==KEYWORD_7||LA80_0==RULE_STAR) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3697:3: ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3697:3: ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) )
            	    int alt79=5;
            	    switch ( input.LA(1) ) {
            	    case KEYWORD_3:
            	        {
            	        alt79=1;
            	        }
            	        break;
            	    case KEYWORD_5:
            	        {
            	        alt79=2;
            	        }
            	        break;
            	    case RULE_STAR:
            	        {
            	        alt79=3;
            	        }
            	        break;
            	    case KEYWORD_7:
            	        {
            	        alt79=4;
            	        }
            	        break;
            	    case KEYWORD_24:
            	        {
            	        alt79=5;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 79, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt79) {
            	        case 1 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3697:4: ( () otherlv_2= KEYWORD_3 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3697:4: ( () otherlv_2= KEYWORD_3 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3697:5: () otherlv_2= KEYWORD_3
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3697:5: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3698:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getPlusLeftAction_1_0_0_0(),
            	                        current);
            	                

            	            }

            	            otherlv_2=(Token)match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_ruleOperand7778); 

            	                	newLeafNode(otherlv_2, grammarAccess.getOperandAccess().getPlusSignKeyword_1_0_0_1());
            	                

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3709:6: ( () otherlv_4= KEYWORD_5 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3709:6: ( () otherlv_4= KEYWORD_5 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3709:7: () otherlv_4= KEYWORD_5
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3709:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3710:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMinusLeftAction_1_0_1_0(),
            	                        current);
            	                

            	            }

            	            otherlv_4=(Token)match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_ruleOperand7807); 

            	                	newLeafNode(otherlv_4, grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_0_1_1());
            	                

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3721:6: ( () this_STAR_6= RULE_STAR )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3721:6: ( () this_STAR_6= RULE_STAR )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3721:7: () this_STAR_6= RULE_STAR
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3721:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3722:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getStarLeftAction_1_0_2_0(),
            	                        current);
            	                

            	            }

            	            this_STAR_6=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperand7834); 
            	             
            	                newLeafNode(this_STAR_6, grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_0_2_1()); 
            	                

            	            }


            	            }
            	            break;
            	        case 4 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3732:6: ( () otherlv_8= KEYWORD_7 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3732:6: ( () otherlv_8= KEYWORD_7 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3732:7: () otherlv_8= KEYWORD_7
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3732:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3733:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getDivLeftAction_1_0_3_0(),
            	                        current);
            	                

            	            }

            	            otherlv_8=(Token)match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_ruleOperand7863); 

            	                	newLeafNode(otherlv_8, grammarAccess.getOperandAccess().getSolidusKeyword_1_0_3_1());
            	                

            	            }


            	            }
            	            break;
            	        case 5 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3744:6: ( () otherlv_10= KEYWORD_24 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3744:6: ( () otherlv_10= KEYWORD_24 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3744:7: () otherlv_10= KEYWORD_24
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3744:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3745:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getConcatLeftAction_1_0_4_0(),
            	                        current);
            	                

            	            }

            	            otherlv_10=(Token)match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_ruleOperand7892); 

            	                	newLeafNode(otherlv_10, grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_0_4_1());
            	                

            	            }


            	            }
            	            break;

            	    }

            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3755:3: ( (lv_right_11_0= ruleOperandFragment ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3756:1: (lv_right_11_0= ruleOperandFragment )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3756:1: (lv_right_11_0= ruleOperandFragment )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3757:3: lv_right_11_0= ruleOperandFragment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOperandAccess().getRightOperandFragmentParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand7914);
            	    lv_right_11_0=ruleOperandFragment();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getOperandRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"right",
            	            		lv_right_11_0, 
            	            		"OperandFragment");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop80;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3781:1: entryRuleOperandFragment returns [EObject current=null] : iv_ruleOperandFragment= ruleOperandFragment EOF ;
    public final EObject entryRuleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3782:2: (iv_ruleOperandFragment= ruleOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3783:2: iv_ruleOperandFragment= ruleOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment7951);
            iv_ruleOperandFragment=ruleOperandFragment();

            state._fsp--;

             current =iv_ruleOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFragment7961); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3790:1: ruleOperandFragment returns [EObject current=null] : ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_func_4_0= ruleOperandFunction ) ) | ( (lv_sqlcase_5_0= ruleSQLCASE ) ) ) ;
    public final EObject ruleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_column_0_0 = null;

        EObject lv_xop_1_0 = null;

        EObject lv_subq_2_0 = null;

        EObject lv_fcast_3_0 = null;

        EObject lv_func_4_0 = null;

        EObject lv_sqlcase_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3793:28: ( ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_func_4_0= ruleOperandFunction ) ) | ( (lv_sqlcase_5_0= ruleSQLCASE ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3794:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_func_4_0= ruleOperandFunction ) ) | ( (lv_sqlcase_5_0= ruleSQLCASE ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3794:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_func_4_0= ruleOperandFunction ) ) | ( (lv_sqlcase_5_0= ruleSQLCASE ) ) )
            int alt81=6;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA81_1 = input.LA(2);

                if ( (LA81_1==EOF||(LA81_1>=KEYWORD_100 && LA81_1<=KEYWORD_99)||(LA81_1>=KEYWORD_95 && LA81_1<=KEYWORD_90)||(LA81_1>=KEYWORD_82 && LA81_1<=KEYWORD_83)||LA81_1==KEYWORD_85||LA81_1==KEYWORD_72||(LA81_1>=KEYWORD_76 && LA81_1<=KEYWORD_77)||(LA81_1>=KEYWORD_67 && LA81_1<=KEYWORD_70)||LA81_1==KEYWORD_52||LA81_1==KEYWORD_55||(LA81_1>=KEYWORD_57 && LA81_1<=KEYWORD_58)||LA81_1==KEYWORD_60||(LA81_1>=KEYWORD_63 && LA81_1<=KEYWORD_66)||(LA81_1>=KEYWORD_35 && LA81_1<=KEYWORD_39)||LA81_1==KEYWORD_41||LA81_1==KEYWORD_43||LA81_1==KEYWORD_47||(LA81_1>=KEYWORD_49 && LA81_1<=KEYWORD_25)||LA81_1==KEYWORD_27||(LA81_1>=KEYWORD_29 && LA81_1<=KEYWORD_30)||LA81_1==KEYWORD_14||(LA81_1>=KEYWORD_16 && LA81_1<=KEYWORD_20)||(LA81_1>=KEYWORD_22 && LA81_1<=KEYWORD_24)||(LA81_1>=KEYWORD_2 && LA81_1<=KEYWORD_10)||(LA81_1>=KEYWORD_12 && LA81_1<=KEYWORD_13)||(LA81_1>=RULE_JRNPARAM && LA81_1<=RULE_STAR)||(LA81_1>=RULE_STRING && LA81_1<=RULE_ID)) ) {
                    alt81=1;
                }
                else if ( (LA81_1==KEYWORD_1) ) {
                    alt81=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt81=1;
                }
                break;
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt81=2;
                }
                break;
            case KEYWORD_1:
                {
                alt81=3;
                }
                break;
            case KEYWORD_51:
                {
                alt81=4;
                }
                break;
            case KEYWORD_34:
                {
                alt81=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }

            switch (alt81) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3794:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3794:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3795:1: (lv_column_0_0= ruleColumnOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3795:1: (lv_column_0_0= ruleColumnOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3796:3: lv_column_0_0= ruleColumnOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnOperand_in_ruleOperandFragment8007);
                    lv_column_0_0=ruleColumnOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"column",
                            		lv_column_0_0, 
                            		"ColumnOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3813:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3813:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3814:1: (lv_xop_1_0= ruleXOperandFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3814:1: (lv_xop_1_0= ruleXOperandFragment )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3815:3: lv_xop_1_0= ruleXOperandFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXOperandFragment_in_ruleOperandFragment8034);
                    lv_xop_1_0=ruleXOperandFragment();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"xop",
                            		lv_xop_1_0, 
                            		"XOperandFragment");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3832:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3832:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3833:1: (lv_subq_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3833:1: (lv_subq_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3834:3: lv_subq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment8061);
                    lv_subq_2_0=ruleSubQueryOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"subq",
                            		lv_subq_2_0, 
                            		"SubQueryOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3851:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3851:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3852:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3852:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3853:3: lv_fcast_3_0= ruleOpFunctionCast
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFcastOpFunctionCastParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionCast_in_ruleOperandFragment8088);
                    lv_fcast_3_0=ruleOpFunctionCast();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"fcast",
                            		lv_fcast_3_0, 
                            		"OpFunctionCast");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3870:6: ( (lv_func_4_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3870:6: ( (lv_func_4_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3871:1: (lv_func_4_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3871:1: (lv_func_4_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3872:3: lv_func_4_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFuncOperandFunctionParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleOperandFragment8115);
                    lv_func_4_0=ruleOperandFunction();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"func",
                            		lv_func_4_0, 
                            		"OperandFunction");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3889:6: ( (lv_sqlcase_5_0= ruleSQLCASE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3889:6: ( (lv_sqlcase_5_0= ruleSQLCASE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3890:1: (lv_sqlcase_5_0= ruleSQLCASE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3890:1: (lv_sqlcase_5_0= ruleSQLCASE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3891:3: lv_sqlcase_5_0= ruleSQLCASE
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSqlcaseSQLCASEParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSQLCASE_in_ruleOperandFragment8142);
                    lv_sqlcase_5_0=ruleSQLCASE();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"sqlcase",
                            		lv_sqlcase_5_0, 
                            		"SQLCASE");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3915:1: entryRuleOperandFunction returns [EObject current=null] : iv_ruleOperandFunction= ruleOperandFunction EOF ;
    public final EObject entryRuleOperandFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFunction = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3916:2: (iv_ruleOperandFunction= ruleOperandFunction EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3917:2: iv_ruleOperandFunction= ruleOperandFunction EOF
            {
             newCompositeNode(grammarAccess.getOperandFunctionRule()); 
            pushFollow(FOLLOW_ruleOperandFunction_in_entryRuleOperandFunction8177);
            iv_ruleOperandFunction=ruleOperandFunction();

            state._fsp--;

             current =iv_ruleOperandFunction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFunction8187); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3924:1: ruleOperandFunction returns [EObject current=null] : ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) ;
    public final EObject ruleOperandFunction() throws RecognitionException {
        EObject current = null;

        Token this_STAR_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_fname_1_0 = null;

        EObject lv_args_3_0 = null;

        EObject lv_fan_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3927:28: ( ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3928:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3928:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3928:2: () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3928:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3929:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperandFunctionAccess().getOpFunctionAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3934:2: ( (lv_fname_1_0= ruleFNAME ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3935:1: (lv_fname_1_0= ruleFNAME )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3935:1: (lv_fname_1_0= ruleFNAME )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3936:3: lv_fname_1_0= ruleFNAME
            {
             
            	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFnameFNAMEParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFNAME_in_ruleOperandFunction8242);
            lv_fname_1_0=ruleFNAME();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperandFunctionRule());
            	        }
                   		set(
                   			current, 
                   			"fname",
                    		lv_fname_1_0, 
                    		"FNAME");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3952:2: (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )?
            int alt82=3;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==RULE_STAR) ) {
                alt82=1;
            }
            else if ( (LA82_0==KEYWORD_81||LA82_0==KEYWORD_51||LA82_0==KEYWORD_34||LA82_0==KEYWORD_26||LA82_0==KEYWORD_1||(LA82_0>=RULE_JRPARAM && LA82_0<=RULE_JRNPARAM)||(LA82_0>=RULE_INT && LA82_0<=RULE_ID)) ) {
                alt82=2;
            }
            switch (alt82) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3952:3: this_STAR_2= RULE_STAR
                    {
                    this_STAR_2=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperandFunction8254); 
                     
                        newLeafNode(this_STAR_2, grammarAccess.getOperandFunctionAccess().getSTARTerminalRuleCall_2_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3957:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3957:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3958:1: (lv_args_3_0= ruleOpFunctionArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3958:1: (lv_args_3_0= ruleOpFunctionArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3959:3: lv_args_3_0= ruleOpFunctionArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getArgsOpFunctionArgParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionArg_in_ruleOperandFunction8280);
                    lv_args_3_0=ruleOpFunctionArg();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFunctionRule());
                    	        }
                           		set(
                           			current, 
                           			"args",
                            		lv_args_3_0, 
                            		"OpFunctionArg");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOperandFunction8295); 

                	newLeafNode(otherlv_4, grammarAccess.getOperandFunctionAccess().getRightParenthesisKeyword_3());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3980:1: ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==KEYWORD_46) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3981:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3981:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3982:3: lv_fan_5_0= ruleFunctionAnalytical
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFanFunctionAnalyticalParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFunctionAnalytical_in_ruleOperandFunction8315);
                    lv_fan_5_0=ruleFunctionAnalytical();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFunctionRule());
                    	        }
                           		set(
                           			current, 
                           			"fan",
                            		lv_fan_5_0, 
                            		"FunctionAnalytical");
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


    // $ANTLR start "entryRuleFunctionAnalytical"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4006:1: entryRuleFunctionAnalytical returns [EObject current=null] : iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF ;
    public final EObject entryRuleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionAnalytical = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4007:2: (iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4008:2: iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF
            {
             newCompositeNode(grammarAccess.getFunctionAnalyticalRule()); 
            pushFollow(FOLLOW_ruleFunctionAnalytical_in_entryRuleFunctionAnalytical8351);
            iv_ruleFunctionAnalytical=ruleFunctionAnalytical();

            state._fsp--;

             current =iv_ruleFunctionAnalytical; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFunctionAnalytical8361); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4015:1: ruleFunctionAnalytical returns [EObject current=null] : (otherlv_0= KEYWORD_46 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_anClause_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4018:28: ( (otherlv_0= KEYWORD_46 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4019:1: (otherlv_0= KEYWORD_46 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4019:1: (otherlv_0= KEYWORD_46 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4020:2: otherlv_0= KEYWORD_46 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_46,FOLLOW_KEYWORD_46_in_ruleFunctionAnalytical8399); 

                	newLeafNode(otherlv_0, grammarAccess.getFunctionAnalyticalAccess().getOVERKeyword_0());
                
            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleFunctionAnalytical8411); 

                	newLeafNode(otherlv_1, grammarAccess.getFunctionAnalyticalAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4029:1: ( (lv_anClause_2_0= ruleAnalyticClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4030:1: (lv_anClause_2_0= ruleAnalyticClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4030:1: (lv_anClause_2_0= ruleAnalyticClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4031:3: lv_anClause_2_0= ruleAnalyticClause
            {
             
            	        newCompositeNode(grammarAccess.getFunctionAnalyticalAccess().getAnClauseAnalyticClauseParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAnalyticClause_in_ruleFunctionAnalytical8431);
            lv_anClause_2_0=ruleAnalyticClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFunctionAnalyticalRule());
            	        }
                   		set(
                   			current, 
                   			"anClause",
                    		lv_anClause_2_0, 
                    		"AnalyticClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleFunctionAnalytical8444); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4060:1: entryRuleAnalyticClause returns [EObject current=null] : iv_ruleAnalyticClause= ruleAnalyticClause EOF ;
    public final EObject entryRuleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4061:2: (iv_ruleAnalyticClause= ruleAnalyticClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4062:2: iv_ruleAnalyticClause= ruleAnalyticClause EOF
            {
             newCompositeNode(grammarAccess.getAnalyticClauseRule()); 
            pushFollow(FOLLOW_ruleAnalyticClause_in_entryRuleAnalyticClause8478);
            iv_ruleAnalyticClause=ruleAnalyticClause();

            state._fsp--;

             current =iv_ruleAnalyticClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticClause8488); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4069:1: ruleAnalyticClause returns [EObject current=null] : (this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )? ) ;
    public final EObject ruleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject this_QueryPartitionClause_0 = null;

        EObject lv_obc_1_0 = null;

        EObject lv_winc_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4072:28: ( (this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4073:1: (this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4073:1: (this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4074:5: this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )?
            {
             
                    newCompositeNode(grammarAccess.getAnalyticClauseAccess().getQueryPartitionClauseParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleQueryPartitionClause_in_ruleAnalyticClause8535);
            this_QueryPartitionClause_0=ruleQueryPartitionClause();

            state._fsp--;


                    current = this_QueryPartitionClause_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4082:1: ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==KEYWORD_100||LA85_0==KEYWORD_85) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4082:2: ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4082:2: ( (lv_obc_1_0= ruleOrderByClause ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4083:1: (lv_obc_1_0= ruleOrderByClause )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4083:1: (lv_obc_1_0= ruleOrderByClause )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4084:3: lv_obc_1_0= ruleOrderByClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getObcOrderByClauseParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOrderByClause_in_ruleAnalyticClause8556);
                    lv_obc_1_0=ruleOrderByClause();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAnalyticClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"obc",
                            		lv_obc_1_0, 
                            		"OrderByClause");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4100:2: ( (lv_winc_2_0= ruleWindowingClause ) )?
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==KEYWORD_63||LA84_0==KEYWORD_47) ) {
                        alt84=1;
                    }
                    switch (alt84) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4101:1: (lv_winc_2_0= ruleWindowingClause )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4101:1: (lv_winc_2_0= ruleWindowingClause )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4102:3: lv_winc_2_0= ruleWindowingClause
                            {
                             
                            	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getWincWindowingClauseParserRuleCall_1_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleWindowingClause_in_ruleAnalyticClause8577);
                            lv_winc_2_0=ruleWindowingClause();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getAnalyticClauseRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"winc",
                                    		lv_winc_2_0, 
                                    		"WindowingClause");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4126:1: entryRuleWindowingClause returns [EObject current=null] : iv_ruleWindowingClause= ruleWindowingClause EOF ;
    public final EObject entryRuleWindowingClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4127:2: (iv_ruleWindowingClause= ruleWindowingClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4128:2: iv_ruleWindowingClause= ruleWindowingClause EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseRule()); 
            pushFollow(FOLLOW_ruleWindowingClause_in_entryRuleWindowingClause8615);
            iv_ruleWindowingClause=ruleWindowingClause();

            state._fsp--;

             current =iv_ruleWindowingClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClause8625); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4135:1: ruleWindowingClause returns [EObject current=null] : ( (otherlv_0= KEYWORD_47 | otherlv_1= KEYWORD_63 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) ;
    public final EObject ruleWindowingClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_WindowingClauseBetween_2 = null;

        EObject this_WindowingClauseOperandPreceding_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4138:28: ( ( (otherlv_0= KEYWORD_47 | otherlv_1= KEYWORD_63 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4139:1: ( (otherlv_0= KEYWORD_47 | otherlv_1= KEYWORD_63 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4139:1: ( (otherlv_0= KEYWORD_47 | otherlv_1= KEYWORD_63 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4139:2: (otherlv_0= KEYWORD_47 | otherlv_1= KEYWORD_63 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4139:2: (otherlv_0= KEYWORD_47 | otherlv_1= KEYWORD_63 )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==KEYWORD_47) ) {
                alt86=1;
            }
            else if ( (LA86_0==KEYWORD_63) ) {
                alt86=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4140:2: otherlv_0= KEYWORD_47
                    {
                    otherlv_0=(Token)match(input,KEYWORD_47,FOLLOW_KEYWORD_47_in_ruleWindowingClause8664); 

                        	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseAccess().getROWSKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4146:2: otherlv_1= KEYWORD_63
                    {
                    otherlv_1=(Token)match(input,KEYWORD_63,FOLLOW_KEYWORD_63_in_ruleWindowingClause8682); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseAccess().getRANGEKeyword_0_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4150:2: (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==KEYWORD_72) ) {
                alt87=1;
            }
            else if ( (LA87_0==KEYWORD_102||LA87_0==KEYWORD_94||LA87_0==KEYWORD_51||LA87_0==KEYWORD_34||LA87_0==KEYWORD_1||(LA87_0>=RULE_JRPARAM && LA87_0<=RULE_JRNPARAM)||(LA87_0>=RULE_INT && LA87_0<=RULE_ID)) ) {
                alt87=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }
            switch (alt87) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4151:5: this_WindowingClauseBetween_2= ruleWindowingClauseBetween
                    {
                     
                            newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseBetweenParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleWindowingClauseBetween_in_ruleWindowingClause8705);
                    this_WindowingClauseBetween_2=ruleWindowingClauseBetween();

                    state._fsp--;


                            current = this_WindowingClauseBetween_2;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4161:5: this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding
                    {
                     
                            newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseOperandPrecedingParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClause8732);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4177:1: entryRuleWindowingClauseBetween returns [EObject current=null] : iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF ;
    public final EObject entryRuleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseBetween = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4178:2: (iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4179:2: iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseBetweenRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseBetween_in_entryRuleWindowingClauseBetween8767);
            iv_ruleWindowingClauseBetween=ruleWindowingClauseBetween();

            state._fsp--;

             current =iv_ruleWindowingClauseBetween; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseBetween8777); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4186:1: ruleWindowingClauseBetween returns [EObject current=null] : (otherlv_0= KEYWORD_72 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) ;
    public final EObject ruleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_wcoP_1_0 = null;

        EObject lv_wcoF_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4189:28: ( (otherlv_0= KEYWORD_72 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4190:1: (otherlv_0= KEYWORD_72 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4190:1: (otherlv_0= KEYWORD_72 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4191:2: otherlv_0= KEYWORD_72 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_72,FOLLOW_KEYWORD_72_in_ruleWindowingClauseBetween8815); 

                	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseBetweenAccess().getBETWEENKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4195:1: ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4196:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4196:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4197:3: lv_wcoP_1_0= ruleWindowingClauseOperandPreceding
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoPWindowingClauseOperandPrecedingParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClauseBetween8835);
            lv_wcoP_1_0=ruleWindowingClauseOperandPreceding();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getWindowingClauseBetweenRule());
            	        }
                   		set(
                   			current, 
                   			"wcoP",
                    		lv_wcoP_1_0, 
                    		"WindowingClauseOperandPreceding");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleWindowingClauseBetween8848); 

                	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseBetweenAccess().getANDKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4218:1: ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4219:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4219:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4220:3: lv_wcoF_3_0= ruleWindowingClauseOperandFollowing
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoFWindowingClauseOperandFollowingParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleWindowingClauseOperandFollowing_in_ruleWindowingClauseBetween8868);
            lv_wcoF_3_0=ruleWindowingClauseOperandFollowing();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getWindowingClauseBetweenRule());
            	        }
                   		set(
                   			current, 
                   			"wcoF",
                    		lv_wcoF_3_0, 
                    		"WindowingClauseOperandFollowing");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4244:1: entryRuleWindowingClauseOperandFollowing returns [EObject current=null] : iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF ;
    public final EObject entryRuleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandFollowing = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4245:2: (iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4246:2: iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseOperandFollowing_in_entryRuleWindowingClauseOperandFollowing8903);
            iv_ruleWindowingClauseOperandFollowing=ruleWindowingClauseOperandFollowing();

            state._fsp--;

             current =iv_ruleWindowingClauseOperandFollowing; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseOperandFollowing8913); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4253:1: ruleWindowingClauseOperandFollowing returns [EObject current=null] : ( () (otherlv_1= KEYWORD_101 | otherlv_2= KEYWORD_94 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_exp_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4256:28: ( ( () (otherlv_1= KEYWORD_101 | otherlv_2= KEYWORD_94 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4257:1: ( () (otherlv_1= KEYWORD_101 | otherlv_2= KEYWORD_94 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4257:1: ( () (otherlv_1= KEYWORD_101 | otherlv_2= KEYWORD_94 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4257:2: () (otherlv_1= KEYWORD_101 | otherlv_2= KEYWORD_94 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4257:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4258:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandFollowingAccess().getWindowingClauseOperandFollowingAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4263:2: (otherlv_1= KEYWORD_101 | otherlv_2= KEYWORD_94 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) )
            int alt89=3;
            switch ( input.LA(1) ) {
            case KEYWORD_101:
                {
                alt89=1;
                }
                break;
            case KEYWORD_94:
                {
                alt89=2;
                }
                break;
            case KEYWORD_51:
            case KEYWORD_34:
            case KEYWORD_1:
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt89=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }

            switch (alt89) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4264:2: otherlv_1= KEYWORD_101
                    {
                    otherlv_1=(Token)match(input,KEYWORD_101,FOLLOW_KEYWORD_101_in_ruleWindowingClauseOperandFollowing8961); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandFollowingAccess().getUNBOUNDEDFOLLOWINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4270:2: otherlv_2= KEYWORD_94
                    {
                    otherlv_2=(Token)match(input,KEYWORD_94,FOLLOW_KEYWORD_94_in_ruleWindowingClauseOperandFollowing8979); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandFollowingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4275:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4275:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4275:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4275:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4276:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4276:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4277:3: lv_exp_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingAccess().getExpAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandFollowing9006);
                    lv_exp_3_0=ruleAnalyticExprArg();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWindowingClauseOperandFollowingRule());
                    	        }
                           		set(
                           			current, 
                           			"exp",
                            		lv_exp_3_0, 
                            		"AnalyticExprArg");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4293:2: (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 )
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==KEYWORD_90) ) {
                        alt88=1;
                    }
                    else if ( (LA88_0==KEYWORD_88) ) {
                        alt88=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 88, 0, input);

                        throw nvae;
                    }
                    switch (alt88) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4294:2: otherlv_4= KEYWORD_90
                            {
                            otherlv_4=(Token)match(input,KEYWORD_90,FOLLOW_KEYWORD_90_in_ruleWindowingClauseOperandFollowing9020); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandFollowingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4300:2: otherlv_5= KEYWORD_88
                            {
                            otherlv_5=(Token)match(input,KEYWORD_88,FOLLOW_KEYWORD_88_in_ruleWindowingClauseOperandFollowing9038); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4312:1: entryRuleWindowingClauseOperandPreceding returns [EObject current=null] : iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF ;
    public final EObject entryRuleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandPreceding = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4313:2: (iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4314:2: iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_entryRuleWindowingClauseOperandPreceding9075);
            iv_ruleWindowingClauseOperandPreceding=ruleWindowingClauseOperandPreceding();

            state._fsp--;

             current =iv_ruleWindowingClauseOperandPreceding; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseOperandPreceding9085); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4321:1: ruleWindowingClauseOperandPreceding returns [EObject current=null] : ( () (otherlv_1= KEYWORD_102 | otherlv_2= KEYWORD_94 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4324:28: ( ( () (otherlv_1= KEYWORD_102 | otherlv_2= KEYWORD_94 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4325:1: ( () (otherlv_1= KEYWORD_102 | otherlv_2= KEYWORD_94 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4325:1: ( () (otherlv_1= KEYWORD_102 | otherlv_2= KEYWORD_94 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4325:2: () (otherlv_1= KEYWORD_102 | otherlv_2= KEYWORD_94 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4325:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4326:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandPrecedingAccess().getWindowingClauseOperandPrecedingAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4331:2: (otherlv_1= KEYWORD_102 | otherlv_2= KEYWORD_94 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) ) )
            int alt91=3;
            switch ( input.LA(1) ) {
            case KEYWORD_102:
                {
                alt91=1;
                }
                break;
            case KEYWORD_94:
                {
                alt91=2;
                }
                break;
            case KEYWORD_51:
            case KEYWORD_34:
            case KEYWORD_1:
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt91=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }

            switch (alt91) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4332:2: otherlv_1= KEYWORD_102
                    {
                    otherlv_1=(Token)match(input,KEYWORD_102,FOLLOW_KEYWORD_102_in_ruleWindowingClauseOperandPreceding9133); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandPrecedingAccess().getUNBOUNDEDPRECEDINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4338:2: otherlv_2= KEYWORD_94
                    {
                    otherlv_2=(Token)match(input,KEYWORD_94,FOLLOW_KEYWORD_94_in_ruleWindowingClauseOperandPreceding9151); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandPrecedingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4343:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4343:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4343:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4343:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4344:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4344:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4345:3: lv_expr_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingAccess().getExprAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandPreceding9178);
                    lv_expr_3_0=ruleAnalyticExprArg();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWindowingClauseOperandPrecedingRule());
                    	        }
                           		set(
                           			current, 
                           			"expr",
                            		lv_expr_3_0, 
                            		"AnalyticExprArg");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4361:2: (otherlv_4= KEYWORD_90 | otherlv_5= KEYWORD_88 )
                    int alt90=2;
                    int LA90_0 = input.LA(1);

                    if ( (LA90_0==KEYWORD_90) ) {
                        alt90=1;
                    }
                    else if ( (LA90_0==KEYWORD_88) ) {
                        alt90=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 90, 0, input);

                        throw nvae;
                    }
                    switch (alt90) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4362:2: otherlv_4= KEYWORD_90
                            {
                            otherlv_4=(Token)match(input,KEYWORD_90,FOLLOW_KEYWORD_90_in_ruleWindowingClauseOperandPreceding9192); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandPrecedingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4368:2: otherlv_5= KEYWORD_88
                            {
                            otherlv_5=(Token)match(input,KEYWORD_88,FOLLOW_KEYWORD_88_in_ruleWindowingClauseOperandPreceding9210); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4380:1: entryRuleOrderByClause returns [EObject current=null] : iv_ruleOrderByClause= ruleOrderByClause EOF ;
    public final EObject entryRuleOrderByClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4381:2: (iv_ruleOrderByClause= ruleOrderByClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4382:2: iv_ruleOrderByClause= ruleOrderByClause EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseRule()); 
            pushFollow(FOLLOW_ruleOrderByClause_in_entryRuleOrderByClause9247);
            iv_ruleOrderByClause=ruleOrderByClause();

            state._fsp--;

             current =iv_ruleOrderByClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClause9257); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4389:1: ruleOrderByClause returns [EObject current=null] : ( (otherlv_0= KEYWORD_85 | otherlv_1= KEYWORD_100 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) ;
    public final EObject ruleOrderByClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_args_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4392:28: ( ( (otherlv_0= KEYWORD_85 | otherlv_1= KEYWORD_100 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4393:1: ( (otherlv_0= KEYWORD_85 | otherlv_1= KEYWORD_100 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4393:1: ( (otherlv_0= KEYWORD_85 | otherlv_1= KEYWORD_100 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4393:2: (otherlv_0= KEYWORD_85 | otherlv_1= KEYWORD_100 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4393:2: (otherlv_0= KEYWORD_85 | otherlv_1= KEYWORD_100 )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==KEYWORD_85) ) {
                alt92=1;
            }
            else if ( (LA92_0==KEYWORD_100) ) {
                alt92=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;
            }
            switch (alt92) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4394:2: otherlv_0= KEYWORD_85
                    {
                    otherlv_0=(Token)match(input,KEYWORD_85,FOLLOW_KEYWORD_85_in_ruleOrderByClause9296); 

                        	newLeafNode(otherlv_0, grammarAccess.getOrderByClauseAccess().getORDERBYKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4400:2: otherlv_1= KEYWORD_100
                    {
                    otherlv_1=(Token)match(input,KEYWORD_100,FOLLOW_KEYWORD_100_in_ruleOrderByClause9314); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseAccess().getORDERSIBLINGSBYKeyword_0_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4404:2: ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4405:1: (lv_args_2_0= ruleOrderByClauseArgs )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4405:1: (lv_args_2_0= ruleOrderByClauseArgs )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4406:3: lv_args_2_0= ruleOrderByClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseAccess().getArgsOrderByClauseArgsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOrderByClauseArgs_in_ruleOrderByClause9335);
            lv_args_2_0=ruleOrderByClauseArgs();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOrderByClauseRule());
            	        }
                   		set(
                   			current, 
                   			"args",
                    		lv_args_2_0, 
                    		"OrderByClauseArgs");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4430:1: entryRuleOrderByClauseArgs returns [EObject current=null] : iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF ;
    public final EObject entryRuleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArgs = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4431:2: (iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4432:2: iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseArgsRule()); 
            pushFollow(FOLLOW_ruleOrderByClauseArgs_in_entryRuleOrderByClauseArgs9370);
            iv_ruleOrderByClauseArgs=ruleOrderByClauseArgs();

            state._fsp--;

             current =iv_ruleOrderByClauseArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClauseArgs9380); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4439:1: ruleOrderByClauseArgs returns [EObject current=null] : (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) ;
    public final EObject ruleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4442:28: ( (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4443:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4443:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4444:5: this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getOrderByClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs9427);
            this_OrderByClauseArg_0=ruleOrderByClauseArg();

            state._fsp--;


                    current = this_OrderByClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4452:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==KEYWORD_4) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4452:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4452:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4453:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByClauseArgsAccess().getOBCArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4458:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    int cnt93=0;
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==KEYWORD_4) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4459:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByClauseArgs9450); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4463:1: ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4464:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4464:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4465:3: lv_entries_3_0= ruleOrderByClauseArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getEntriesOrderByClauseArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs9470);
                    	    lv_entries_3_0=ruleOrderByClauseArg();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getOrderByClauseArgsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"OrderByClauseArg");
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
    // $ANTLR end "ruleOrderByClauseArgs"


    // $ANTLR start "entryRuleOrderByClauseArg"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4489:1: entryRuleOrderByClauseArg returns [EObject current=null] : iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF ;
    public final EObject entryRuleOrderByClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4490:2: (iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4491:2: iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseArgRule()); 
            pushFollow(FOLLOW_ruleOrderByClauseArg_in_entryRuleOrderByClauseArg9509);
            iv_ruleOrderByClauseArg=ruleOrderByClauseArg();

            state._fsp--;

             current =iv_ruleOrderByClauseArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClauseArg9519); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4498:1: ruleOrderByClauseArg returns [EObject current=null] : ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_35 )? (otherlv_3= KEYWORD_60 (otherlv_4= KEYWORD_54 | otherlv_5= KEYWORD_40 ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4501:28: ( ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_35 )? (otherlv_3= KEYWORD_60 (otherlv_4= KEYWORD_54 | otherlv_5= KEYWORD_40 ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4502:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_35 )? (otherlv_3= KEYWORD_60 (otherlv_4= KEYWORD_54 | otherlv_5= KEYWORD_40 ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4502:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_35 )? (otherlv_3= KEYWORD_60 (otherlv_4= KEYWORD_54 | otherlv_5= KEYWORD_40 ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4502:2: ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_35 )? (otherlv_3= KEYWORD_60 (otherlv_4= KEYWORD_54 | otherlv_5= KEYWORD_40 ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4502:2: ( (lv_col_0_0= ruleAnalyticExprArg ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4503:1: (lv_col_0_0= ruleAnalyticExprArg )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4503:1: (lv_col_0_0= ruleAnalyticExprArg )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4504:3: lv_col_0_0= ruleAnalyticExprArg
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseArgAccess().getColAnalyticExprArgParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleOrderByClauseArg9565);
            lv_col_0_0=ruleAnalyticExprArg();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOrderByClauseArgRule());
            	        }
                   		set(
                   			current, 
                   			"col",
                    		lv_col_0_0, 
                    		"AnalyticExprArg");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4520:2: (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_35 )?
            int alt95=3;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==KEYWORD_29) ) {
                alt95=1;
            }
            else if ( (LA95_0==KEYWORD_35) ) {
                alt95=2;
            }
            switch (alt95) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4521:2: otherlv_1= KEYWORD_29
                    {
                    otherlv_1=(Token)match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_ruleOrderByClauseArg9579); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseArgAccess().getASCKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4527:2: otherlv_2= KEYWORD_35
                    {
                    otherlv_2=(Token)match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_ruleOrderByClauseArg9597); 

                        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgAccess().getDESCKeyword_1_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4531:3: (otherlv_3= KEYWORD_60 (otherlv_4= KEYWORD_54 | otherlv_5= KEYWORD_40 ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==KEYWORD_60) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4532:2: otherlv_3= KEYWORD_60 (otherlv_4= KEYWORD_54 | otherlv_5= KEYWORD_40 )
                    {
                    otherlv_3=(Token)match(input,KEYWORD_60,FOLLOW_KEYWORD_60_in_ruleOrderByClauseArg9612); 

                        	newLeafNode(otherlv_3, grammarAccess.getOrderByClauseArgAccess().getNULLSKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4536:1: (otherlv_4= KEYWORD_54 | otherlv_5= KEYWORD_40 )
                    int alt96=2;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==KEYWORD_54) ) {
                        alt96=1;
                    }
                    else if ( (LA96_0==KEYWORD_40) ) {
                        alt96=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 96, 0, input);

                        throw nvae;
                    }
                    switch (alt96) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4537:2: otherlv_4= KEYWORD_54
                            {
                            otherlv_4=(Token)match(input,KEYWORD_54,FOLLOW_KEYWORD_54_in_ruleOrderByClauseArg9625); 

                                	newLeafNode(otherlv_4, grammarAccess.getOrderByClauseArgAccess().getFIRSTKeyword_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4543:2: otherlv_5= KEYWORD_40
                            {
                            otherlv_5=(Token)match(input,KEYWORD_40,FOLLOW_KEYWORD_40_in_ruleOrderByClauseArg9643); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4555:1: entryRuleQueryPartitionClause returns [EObject current=null] : iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF ;
    public final EObject entryRuleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQueryPartitionClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4556:2: (iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4557:2: iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF
            {
             newCompositeNode(grammarAccess.getQueryPartitionClauseRule()); 
            pushFollow(FOLLOW_ruleQueryPartitionClause_in_entryRuleQueryPartitionClause9680);
            iv_ruleQueryPartitionClause=ruleQueryPartitionClause();

            state._fsp--;

             current =iv_ruleQueryPartitionClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQueryPartitionClause9690); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4564:1: ruleQueryPartitionClause returns [EObject current=null] : (otherlv_0= KEYWORD_98 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) ) ;
    public final EObject ruleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_1_0 = null;

        EObject this_AnalyticExprArgs_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4567:28: ( (otherlv_0= KEYWORD_98 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4568:1: (otherlv_0= KEYWORD_98 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4568:1: (otherlv_0= KEYWORD_98 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4569:2: otherlv_0= KEYWORD_98 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_98,FOLLOW_KEYWORD_98_in_ruleQueryPartitionClause9728); 

                	newLeafNode(otherlv_0, grammarAccess.getQueryPartitionClauseAccess().getPARTITIONBYKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4573:1: ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) )
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==KEYWORD_51||LA98_0==KEYWORD_34||(LA98_0>=RULE_JRPARAM && LA98_0<=RULE_JRNPARAM)||(LA98_0>=RULE_INT && LA98_0<=RULE_ID)) ) {
                alt98=1;
            }
            else if ( (LA98_0==KEYWORD_1) ) {
                int LA98_2 = input.LA(2);

                if ( (LA98_2==KEYWORD_71) ) {
                    alt98=1;
                }
                else if ( (LA98_2==KEYWORD_51||LA98_2==KEYWORD_34||LA98_2==KEYWORD_1||(LA98_2>=RULE_JRPARAM && LA98_2<=RULE_JRNPARAM)||(LA98_2>=RULE_INT && LA98_2<=RULE_ID)) ) {
                    alt98=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 98, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }
            switch (alt98) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4573:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4573:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4574:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4574:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4575:3: lv_args_1_0= ruleAnalyticExprArgs
                    {
                     
                    	        newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getArgsAnalyticExprArgsParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause9749);
                    lv_args_1_0=ruleAnalyticExprArgs();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getQueryPartitionClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"args",
                            		lv_args_1_0, 
                            		"AnalyticExprArgs");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4592:6: (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4592:6: (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4593:2: otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2
                    {
                    otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleQueryPartitionClause9769); 

                        	newLeafNode(otherlv_2, grammarAccess.getQueryPartitionClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getAnalyticExprArgsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause9790);
                    this_AnalyticExprArgs_3=ruleAnalyticExprArgs();

                    state._fsp--;


                            current = this_AnalyticExprArgs_3;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleQueryPartitionClause9802); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4619:1: entryRuleAnalyticExprArgs returns [EObject current=null] : iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF ;
    public final EObject entryRuleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArgs = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4620:2: (iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4621:2: iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF
            {
             newCompositeNode(grammarAccess.getAnalyticExprArgsRule()); 
            pushFollow(FOLLOW_ruleAnalyticExprArgs_in_entryRuleAnalyticExprArgs9838);
            iv_ruleAnalyticExprArgs=ruleAnalyticExprArgs();

            state._fsp--;

             current =iv_ruleAnalyticExprArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticExprArgs9848); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4628:1: ruleAnalyticExprArgs returns [EObject current=null] : (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) ;
    public final EObject ruleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AnalyticExprArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4631:28: ( (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4632:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4632:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4633:5: this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getAnalyticExprArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs9895);
            this_AnalyticExprArg_0=ruleAnalyticExprArg();

            state._fsp--;


                    current = this_AnalyticExprArg_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4641:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==KEYWORD_4) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4641:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4641:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4642:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getAnalyticExprArgsAccess().getAExpArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4647:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    int cnt99=0;
                    loop99:
                    do {
                        int alt99=2;
                        int LA99_0 = input.LA(1);

                        if ( (LA99_0==KEYWORD_4) ) {
                            alt99=1;
                        }


                        switch (alt99) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4648:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleAnalyticExprArgs9918); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getAnalyticExprArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4652:1: ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4653:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4653:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4654:3: lv_entries_3_0= ruleAnalyticExprArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getEntriesAnalyticExprArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs9938);
                    	    lv_entries_3_0=ruleAnalyticExprArg();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getAnalyticExprArgsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"AnalyticExprArg");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt99 >= 1 ) break loop99;
                                EarlyExitException eee =
                                    new EarlyExitException(99, input);
                                throw eee;
                        }
                        cnt99++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4678:1: entryRuleAnalyticExprArg returns [EObject current=null] : iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF ;
    public final EObject entryRuleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4679:2: (iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4680:2: iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF
            {
             newCompositeNode(grammarAccess.getAnalyticExprArgRule()); 
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_entryRuleAnalyticExprArg9977);
            iv_ruleAnalyticExprArg=ruleAnalyticExprArg();

            state._fsp--;

             current =iv_ruleAnalyticExprArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticExprArg9987); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4687:1: ruleAnalyticExprArg returns [EObject current=null] : ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4690:28: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4691:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4691:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4691:2: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4691:2: ( (lv_ce_0_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4692:1: (lv_ce_0_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4692:1: (lv_ce_0_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4693:3: lv_ce_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getCeOperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleAnalyticExprArg10033);
            lv_ce_0_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAnalyticExprArgRule());
            	        }
                   		set(
                   			current, 
                   			"ce",
                    		lv_ce_0_0, 
                    		"Operand");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4709:2: ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( ((LA101_0>=RULE_STRING && LA101_0<=RULE_ID)) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4710:1: (lv_colAlias_1_0= ruleDbObjectName )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4710:1: (lv_colAlias_1_0= ruleDbObjectName )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4711:3: lv_colAlias_1_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getColAliasDbObjectNameParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectName_in_ruleAnalyticExprArg10054);
                    lv_colAlias_1_0=ruleDbObjectName();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAnalyticExprArgRule());
                    	        }
                           		set(
                           			current, 
                           			"colAlias",
                            		lv_colAlias_1_0, 
                            		"DbObjectName");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4735:1: entryRuleOpFunctionArg returns [EObject current=null] : iv_ruleOpFunctionArg= ruleOpFunctionArg EOF ;
    public final EObject entryRuleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4736:2: (iv_ruleOpFunctionArg= ruleOpFunctionArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4737:2: iv_ruleOpFunctionArg= ruleOpFunctionArg EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArg_in_entryRuleOpFunctionArg10090);
            iv_ruleOpFunctionArg=ruleOpFunctionArg();

            state._fsp--;

             current =iv_ruleOpFunctionArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArg10100); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4744:1: ruleOpFunctionArg returns [EObject current=null] : (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) ;
    public final EObject ruleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OpFunctionArgOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4747:28: ( (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4748:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4748:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4749:5: this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOpFunctionArgAccess().getOpFunctionArgOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg10147);
            this_OpFunctionArgOperand_0=ruleOpFunctionArgOperand();

            state._fsp--;


                    current = this_OpFunctionArgOperand_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4757:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==KEYWORD_4) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4757:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4757:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4758:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOpFunctionArgAccess().getOpFListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4763:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    int cnt102=0;
                    loop102:
                    do {
                        int alt102=2;
                        int LA102_0 = input.LA(1);

                        if ( (LA102_0==KEYWORD_4) ) {
                            alt102=1;
                        }


                        switch (alt102) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4764:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOpFunctionArg10170); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOpFunctionArgAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4768:1: ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4769:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4769:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4770:3: lv_entries_3_0= ruleOpFunctionArgOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOpFunctionArgAccess().getEntriesOpFunctionArgOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg10190);
                    	    lv_entries_3_0=ruleOpFunctionArgOperand();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getOpFunctionArgRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"OpFunctionArgOperand");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt102 >= 1 ) break loop102;
                                EarlyExitException eee =
                                    new EarlyExitException(102, input);
                                throw eee;
                        }
                        cnt102++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4794:1: entryRuleOpFunctionArgOperand returns [EObject current=null] : iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF ;
    public final EObject entryRuleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4795:2: (iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4796:2: iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgOperandRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_entryRuleOpFunctionArgOperand10229);
            iv_ruleOpFunctionArgOperand=ruleOpFunctionArgOperand();

            state._fsp--;

             current =iv_ruleOpFunctionArgOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArgOperand10239); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4803:1: ruleOpFunctionArgOperand returns [EObject current=null] : ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) ;
    public final EObject ruleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject lv_op_0_1 = null;

        EObject lv_op_0_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4806:28: ( ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4807:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4807:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4808:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4808:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4809:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4809:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==KEYWORD_81||LA104_0==KEYWORD_26) ) {
                alt104=1;
            }
            else if ( (LA104_0==KEYWORD_51||LA104_0==KEYWORD_34||LA104_0==KEYWORD_1||(LA104_0>=RULE_JRPARAM && LA104_0<=RULE_JRNPARAM)||(LA104_0>=RULE_INT && LA104_0<=RULE_ID)) ) {
                alt104=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;
            }
            switch (alt104) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4810:3: lv_op_0_1= ruleOpFunctionArgAgregate
                    {
                     
                    	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOpFunctionArgAgregateParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionArgAgregate_in_ruleOpFunctionArgOperand10286);
                    lv_op_0_1=ruleOpFunctionArgAgregate();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOpFunctionArgOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"op",
                            		lv_op_0_1, 
                            		"OpFunctionArgAgregate");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4825:8: lv_op_0_2= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOperandParserRuleCall_0_1()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionArgOperand10305);
                    lv_op_0_2=ruleOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOpFunctionArgOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"op",
                            		lv_op_0_2, 
                            		"Operand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4851:1: entryRuleOpFunctionCast returns [EObject current=null] : iv_ruleOpFunctionCast= ruleOpFunctionCast EOF ;
    public final EObject entryRuleOpFunctionCast() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionCast = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4852:2: (iv_ruleOpFunctionCast= ruleOpFunctionCast EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4853:2: iv_ruleOpFunctionCast= ruleOpFunctionCast EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionCastRule()); 
            pushFollow(FOLLOW_ruleOpFunctionCast_in_entryRuleOpFunctionCast10342);
            iv_ruleOpFunctionCast=ruleOpFunctionCast();

            state._fsp--;

             current =iv_ruleOpFunctionCast; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionCast10352); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4860:1: ruleOpFunctionCast returns [EObject current=null] : (otherlv_0= KEYWORD_51 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4863:28: ( (otherlv_0= KEYWORD_51 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4864:1: (otherlv_0= KEYWORD_51 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4864:1: (otherlv_0= KEYWORD_51 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4865:2: otherlv_0= KEYWORD_51 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_51,FOLLOW_KEYWORD_51_in_ruleOpFunctionCast10390); 

                	newLeafNode(otherlv_0, grammarAccess.getOpFunctionCastAccess().getCASTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4869:1: ( (lv_op_1_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4870:1: (lv_op_1_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4870:1: (lv_op_1_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4871:3: lv_op_1_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getOpFunctionCastAccess().getOpOperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionCast10410);
            lv_op_1_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOpFunctionCastRule());
            	        }
                   		set(
                   			current, 
                   			"op",
                    		lv_op_1_0, 
                    		"Operand");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleOpFunctionCast10423); 

                	newLeafNode(otherlv_2, grammarAccess.getOpFunctionCastAccess().getASKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4892:1: ( (lv_type_3_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4893:1: (lv_type_3_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4893:1: (lv_type_3_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4894:3: lv_type_3_0= RULE_ID
            {
            lv_type_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOpFunctionCast10439); 

            			newLeafNode(lv_type_3_0, grammarAccess.getOpFunctionCastAccess().getTypeIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getOpFunctionCastRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"type",
                    		lv_type_3_0, 
                    		"ID");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4910:2: (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==KEYWORD_1) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4911:2: otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2
                    {
                    otherlv_4=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleOpFunctionCast10458); 

                        	newLeafNode(otherlv_4, grammarAccess.getOpFunctionCastAccess().getLeftParenthesisKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4915:1: ( (lv_p_5_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4916:1: (lv_p_5_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4916:1: (lv_p_5_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4917:3: lv_p_5_0= RULE_INT
                    {
                    lv_p_5_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOpFunctionCast10474); 

                    			newLeafNode(lv_p_5_0, grammarAccess.getOpFunctionCastAccess().getPINTTerminalRuleCall_4_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOpFunctionCastRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"p",
                            		lv_p_5_0, 
                            		"INT");
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4933:2: (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )?
                    int alt105=2;
                    int LA105_0 = input.LA(1);

                    if ( (LA105_0==KEYWORD_4) ) {
                        alt105=1;
                    }
                    switch (alt105) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4934:2: otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) )
                            {
                            otherlv_6=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOpFunctionCast10493); 

                                	newLeafNode(otherlv_6, grammarAccess.getOpFunctionCastAccess().getCommaKeyword_4_2_0());
                                
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4938:1: ( (lv_p2_7_0= RULE_INT ) )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4939:1: (lv_p2_7_0= RULE_INT )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4939:1: (lv_p2_7_0= RULE_INT )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4940:3: lv_p2_7_0= RULE_INT
                            {
                            lv_p2_7_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOpFunctionCast10509); 

                            			newLeafNode(lv_p2_7_0, grammarAccess.getOpFunctionCastAccess().getP2INTTerminalRuleCall_4_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOpFunctionCastRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"p2",
                                    		lv_p2_7_0, 
                                    		"INT");
                            	    

                            }


                            }


                            }
                            break;

                    }

                    otherlv_8=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOpFunctionCast10529); 

                        	newLeafNode(otherlv_8, grammarAccess.getOpFunctionCastAccess().getRightParenthesisKeyword_4_3());
                        

                    }
                    break;

            }

            otherlv_9=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOpFunctionCast10543); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4974:1: entryRuleOpFunctionArgAgregate returns [EObject current=null] : iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF ;
    public final EObject entryRuleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgAgregate = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4975:2: (iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4976:2: iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgAgregateRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArgAgregate_in_entryRuleOpFunctionArgAgregate10577);
            iv_ruleOpFunctionArgAgregate=ruleOpFunctionArgAgregate();

            state._fsp--;

             current =iv_ruleOpFunctionArgAgregate; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArgAgregate10587); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4983:1: ruleOpFunctionArgAgregate returns [EObject current=null] : ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_81 ) this_Operand_2= ruleOperand ) ;
    public final EObject ruleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4986:28: ( ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_81 ) this_Operand_2= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4987:1: ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_81 ) this_Operand_2= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4987:1: ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_81 ) this_Operand_2= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4987:2: (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_81 ) this_Operand_2= ruleOperand
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4987:2: (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_81 )
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==KEYWORD_26) ) {
                alt107=1;
            }
            else if ( (LA107_0==KEYWORD_81) ) {
                alt107=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 107, 0, input);

                throw nvae;
            }
            switch (alt107) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4988:2: otherlv_0= KEYWORD_26
                    {
                    otherlv_0=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleOpFunctionArgAgregate10626); 

                        	newLeafNode(otherlv_0, grammarAccess.getOpFunctionArgAgregateAccess().getALLKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4994:2: otherlv_1= KEYWORD_81
                    {
                    otherlv_1=(Token)match(input,KEYWORD_81,FOLLOW_KEYWORD_81_in_ruleOpFunctionArgAgregate10644); 

                        	newLeafNode(otherlv_1, grammarAccess.getOpFunctionArgAgregateAccess().getDISTINCTKeyword_0_1());
                        

                    }
                    break;

            }

             
                    newCompositeNode(grammarAccess.getOpFunctionArgAgregateAccess().getOperandParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionArgAgregate10666);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5015:1: entryRuleXOperandFragment returns [EObject current=null] : iv_ruleXOperandFragment= ruleXOperandFragment EOF ;
    public final EObject entryRuleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5016:2: (iv_ruleXOperandFragment= ruleXOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5017:2: iv_ruleXOperandFragment= ruleXOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment10700);
            iv_ruleXOperandFragment=ruleXOperandFragment();

            state._fsp--;

             current =iv_ruleXOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOperandFragment10710); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5024:1: ruleXOperandFragment returns [EObject current=null] : ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) ) ;
    public final EObject ruleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_param_0_0 = null;

        EObject lv_eparam_1_0 = null;

        EObject lv_scalar_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5027:28: ( ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5028:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5028:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) )
            int alt108=3;
            switch ( input.LA(1) ) {
            case RULE_JRPARAM:
                {
                alt108=1;
                }
                break;
            case RULE_JRNPARAM:
                {
                alt108=2;
                }
                break;
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt108=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 108, 0, input);

                throw nvae;
            }

            switch (alt108) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5028:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5028:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5029:1: (lv_param_0_0= ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5029:1: (lv_param_0_0= ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5030:3: lv_param_0_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParameterOperand_in_ruleXOperandFragment10756);
                    lv_param_0_0=ruleParameterOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"param",
                            		lv_param_0_0, 
                            		"ParameterOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5047:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5047:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5048:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5048:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5049:3: lv_eparam_1_0= ruleExclamationParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment10783);
                    lv_eparam_1_0=ruleExclamationParameterOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"eparam",
                            		lv_eparam_1_0, 
                            		"ExclamationParameterOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5066:6: ( (lv_scalar_2_0= ruleScalarOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5066:6: ( (lv_scalar_2_0= ruleScalarOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5067:1: (lv_scalar_2_0= ruleScalarOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5067:1: (lv_scalar_2_0= ruleScalarOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5068:3: lv_scalar_2_0= ruleScalarOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getScalarScalarOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleScalarOperand_in_ruleXOperandFragment10810);
                    lv_scalar_2_0=ruleScalarOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"scalar",
                            		lv_scalar_2_0, 
                            		"ScalarOperand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5092:1: entryRuleParameterOperand returns [EObject current=null] : iv_ruleParameterOperand= ruleParameterOperand EOF ;
    public final EObject entryRuleParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5093:2: (iv_ruleParameterOperand= ruleParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5094:2: iv_ruleParameterOperand= ruleParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand10845);
            iv_ruleParameterOperand=ruleParameterOperand();

            state._fsp--;

             current =iv_ruleParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameterOperand10855); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5101:1: ruleParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRPARAM ) ) ;
    public final EObject ruleParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5104:28: ( ( (lv_prm_0_0= RULE_JRPARAM ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5105:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5105:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5106:1: (lv_prm_0_0= RULE_JRPARAM )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5106:1: (lv_prm_0_0= RULE_JRPARAM )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5107:3: lv_prm_0_0= RULE_JRPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRPARAM,FOLLOW_RULE_JRPARAM_in_ruleParameterOperand10896); 

            			newLeafNode(lv_prm_0_0, grammarAccess.getParameterOperandAccess().getPrmJRPARAMTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getParameterOperandRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"prm",
                    		lv_prm_0_0, 
                    		"JRPARAM");
            	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5131:1: entryRuleExclamationParameterOperand returns [EObject current=null] : iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF ;
    public final EObject entryRuleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclamationParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5132:2: (iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5133:2: iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand10935);
            iv_ruleExclamationParameterOperand=ruleExclamationParameterOperand();

            state._fsp--;

             current =iv_ruleExclamationParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclamationParameterOperand10945); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5140:1: ruleExclamationParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRNPARAM ) ) ;
    public final EObject ruleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5143:28: ( ( (lv_prm_0_0= RULE_JRNPARAM ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5144:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5144:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5145:1: (lv_prm_0_0= RULE_JRNPARAM )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5145:1: (lv_prm_0_0= RULE_JRNPARAM )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5146:3: lv_prm_0_0= RULE_JRNPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExclamationParameterOperand10986); 

            			newLeafNode(lv_prm_0_0, grammarAccess.getExclamationParameterOperandAccess().getPrmJRNPARAMTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getExclamationParameterOperandRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"prm",
                    		lv_prm_0_0, 
                    		"JRNPARAM");
            	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5170:1: entryRuleColumnOperand returns [EObject current=null] : iv_ruleColumnOperand= ruleColumnOperand EOF ;
    public final EObject entryRuleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5171:2: (iv_ruleColumnOperand= ruleColumnOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5172:2: iv_ruleColumnOperand= ruleColumnOperand EOF
            {
             newCompositeNode(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand11025);
            iv_ruleColumnOperand=ruleColumnOperand();

            state._fsp--;

             current =iv_ruleColumnOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOperand11035); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5179:1: ruleColumnOperand returns [EObject current=null] : ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? ) ;
    public final EObject ruleColumnOperand() throws RecognitionException {
        EObject current = null;

        Token lv_ora_1_0=null;
        EObject lv_cfull_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5182:28: ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5183:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5183:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5183:2: ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5183:2: ( (lv_cfull_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5184:1: (lv_cfull_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5184:1: (lv_cfull_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5185:3: lv_cfull_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOperand11081);
            lv_cfull_0_0=ruleColumnFull();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getColumnOperandRule());
            	        }
                   		set(
                   			current, 
                   			"cfull",
                    		lv_cfull_0_0, 
                    		"ColumnFull");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5201:2: ( (lv_ora_1_0= KEYWORD_25 ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==KEYWORD_25) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5202:1: (lv_ora_1_0= KEYWORD_25 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5202:1: (lv_ora_1_0= KEYWORD_25 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5203:3: lv_ora_1_0= KEYWORD_25
                    {
                    lv_ora_1_0=(Token)match(input,KEYWORD_25,FOLLOW_KEYWORD_25_in_ruleColumnOperand11100); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5225:1: entryRuleSubQueryOperand returns [EObject current=null] : iv_ruleSubQueryOperand= ruleSubQueryOperand EOF ;
    public final EObject entryRuleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubQueryOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5226:2: (iv_ruleSubQueryOperand= ruleSubQueryOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5227:2: iv_ruleSubQueryOperand= ruleSubQueryOperand EOF
            {
             newCompositeNode(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand11147);
            iv_ruleSubQueryOperand=ruleSubQueryOperand();

            state._fsp--;

             current =iv_ruleSubQueryOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubQueryOperand11157); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5234:1: ruleSubQueryOperand returns [EObject current=null] : ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_sel_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5237:28: ( ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5238:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5238:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5238:2: () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5238:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5239:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSubQueryOperandAccess().getSubQueryOperandAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleSubQueryOperand11204); 

                	newLeafNode(otherlv_1, grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5249:1: ( (lv_sel_2_0= ruleSelectQuery ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5250:1: (lv_sel_2_0= ruleSelectQuery )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5250:1: (lv_sel_2_0= ruleSelectQuery )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5251:3: lv_sel_2_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getSubQueryOperandAccess().getSelSelectQueryParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelectQuery_in_ruleSubQueryOperand11224);
            lv_sel_2_0=ruleSelectQuery();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSubQueryOperandRule());
            	        }
                   		set(
                   			current, 
                   			"sel",
                    		lv_sel_2_0, 
                    		"SelectQuery");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleSubQueryOperand11237); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5280:1: entryRuleScalarOperand returns [EObject current=null] : iv_ruleScalarOperand= ruleScalarOperand EOF ;
    public final EObject entryRuleScalarOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5281:2: (iv_ruleScalarOperand= ruleScalarOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5282:2: iv_ruleScalarOperand= ruleScalarOperand EOF
            {
             newCompositeNode(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand11271);
            iv_ruleScalarOperand=ruleScalarOperand();

            state._fsp--;

             current =iv_ruleScalarOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarOperand11281); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5289:1: ruleScalarOperand returns [EObject current=null] : ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) ) ;
    public final EObject ruleScalarOperand() throws RecognitionException {
        EObject current = null;

        Token lv_soint_0_0=null;
        Token lv_sodbl_2_0=null;
        Token lv_sodate_3_0=null;
        Token lv_sotime_4_0=null;
        Token lv_sodt_5_0=null;
        AntlrDatatypeRuleToken lv_sostr_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5292:28: ( ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5293:1: ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5293:1: ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) )
            int alt110=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt110=1;
                }
                break;
            case RULE_STRING_:
                {
                alt110=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt110=3;
                }
                break;
            case RULE_DATE:
                {
                alt110=4;
                }
                break;
            case RULE_TIME:
                {
                alt110=5;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt110=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 110, 0, input);

                throw nvae;
            }

            switch (alt110) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5293:2: ( (lv_soint_0_0= RULE_INT ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5293:2: ( (lv_soint_0_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5294:1: (lv_soint_0_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5294:1: (lv_soint_0_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5295:3: lv_soint_0_0= RULE_INT
                    {
                    lv_soint_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleScalarOperand11323); 

                    			newLeafNode(lv_soint_0_0, grammarAccess.getScalarOperandAccess().getSointINTTerminalRuleCall_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"soint",
                            		lv_soint_0_0, 
                            		"INT");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5312:6: ( (lv_sostr_1_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5312:6: ( (lv_sostr_1_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5313:1: (lv_sostr_1_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5313:1: (lv_sostr_1_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5314:3: lv_sostr_1_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleScalarOperand11355);
                    lv_sostr_1_0=ruleStringOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getScalarOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"sostr",
                            		lv_sostr_1_0, 
                            		"StringOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5331:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5331:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5332:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5332:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5333:3: lv_sodbl_2_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_2_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand11378); 

                    			newLeafNode(lv_sodbl_2_0, grammarAccess.getScalarOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sodbl",
                            		lv_sodbl_2_0, 
                            		"SIGNED_DOUBLE");
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5350:6: ( (lv_sodate_3_0= RULE_DATE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5350:6: ( (lv_sodate_3_0= RULE_DATE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5351:1: (lv_sodate_3_0= RULE_DATE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5351:1: (lv_sodate_3_0= RULE_DATE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5352:3: lv_sodate_3_0= RULE_DATE
                    {
                    lv_sodate_3_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleScalarOperand11406); 

                    			newLeafNode(lv_sodate_3_0, grammarAccess.getScalarOperandAccess().getSodateDATETerminalRuleCall_3_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sodate",
                            		lv_sodate_3_0, 
                            		"DATE");
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5369:6: ( (lv_sotime_4_0= RULE_TIME ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5369:6: ( (lv_sotime_4_0= RULE_TIME ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5370:1: (lv_sotime_4_0= RULE_TIME )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5370:1: (lv_sotime_4_0= RULE_TIME )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5371:3: lv_sotime_4_0= RULE_TIME
                    {
                    lv_sotime_4_0=(Token)match(input,RULE_TIME,FOLLOW_RULE_TIME_in_ruleScalarOperand11434); 

                    			newLeafNode(lv_sotime_4_0, grammarAccess.getScalarOperandAccess().getSotimeTIMETerminalRuleCall_4_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sotime",
                            		lv_sotime_4_0, 
                            		"TIME");
                    	    

                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5388:6: ( (lv_sodt_5_0= RULE_TIMESTAMP ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5388:6: ( (lv_sodt_5_0= RULE_TIMESTAMP ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5389:1: (lv_sodt_5_0= RULE_TIMESTAMP )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5389:1: (lv_sodt_5_0= RULE_TIMESTAMP )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5390:3: lv_sodt_5_0= RULE_TIMESTAMP
                    {
                    lv_sodt_5_0=(Token)match(input,RULE_TIMESTAMP,FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand11462); 

                    			newLeafNode(lv_sodt_5_0, grammarAccess.getScalarOperandAccess().getSodtTIMESTAMPTerminalRuleCall_5_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sodt",
                            		lv_sodt_5_0, 
                            		"TIMESTAMP");
                    	    

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


    // $ANTLR start "entryRuleSQLCASE"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5414:1: entryRuleSQLCASE returns [EObject current=null] : iv_ruleSQLCASE= ruleSQLCASE EOF ;
    public final EObject entryRuleSQLCASE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCASE = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5415:2: (iv_ruleSQLCASE= ruleSQLCASE EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5416:2: iv_ruleSQLCASE= ruleSQLCASE EOF
            {
             newCompositeNode(grammarAccess.getSQLCASERule()); 
            pushFollow(FOLLOW_ruleSQLCASE_in_entryRuleSQLCASE11502);
            iv_ruleSQLCASE=ruleSQLCASE();

            state._fsp--;

             current =iv_ruleSQLCASE; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSQLCASE11512); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5423:1: ruleSQLCASE returns [EObject current=null] : (otherlv_0= KEYWORD_34 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30 ) ;
    public final EObject ruleSQLCASE() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_expr_1_0 = null;

        EObject lv_when_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5426:28: ( (otherlv_0= KEYWORD_34 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5427:1: (otherlv_0= KEYWORD_34 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5427:1: (otherlv_0= KEYWORD_34 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5428:2: otherlv_0= KEYWORD_34 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30
            {
            otherlv_0=(Token)match(input,KEYWORD_34,FOLLOW_KEYWORD_34_in_ruleSQLCASE11550); 

                	newLeafNode(otherlv_0, grammarAccess.getSQLCASEAccess().getCASEKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5432:1: ( (lv_expr_1_0= ruleFullExpression ) )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==KEYWORD_51||LA111_0==KEYWORD_34||(LA111_0>=KEYWORD_44 && LA111_0<=KEYWORD_45)||LA111_0==KEYWORD_15||LA111_0==KEYWORD_1||(LA111_0>=RULE_JRPARAM && LA111_0<=RULE_JRNPARAM)||(LA111_0>=RULE_INT && LA111_0<=RULE_ID)) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5433:1: (lv_expr_1_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5433:1: (lv_expr_1_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5434:3: lv_expr_1_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSQLCASEAccess().getExprFullExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSQLCASE11570);
                    lv_expr_1_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSQLCASERule());
                    	        }
                           		set(
                           			current, 
                           			"expr",
                            		lv_expr_1_0, 
                            		"FullExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5450:3: ( (lv_when_2_0= ruleSQLCaseWhens ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5451:1: (lv_when_2_0= ruleSQLCaseWhens )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5451:1: (lv_when_2_0= ruleSQLCaseWhens )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5452:3: lv_when_2_0= ruleSQLCaseWhens
            {
             
            	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWhenSQLCaseWhensParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSQLCaseWhens_in_ruleSQLCASE11592);
            lv_when_2_0=ruleSQLCaseWhens();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSQLCASERule());
            	        }
                   		set(
                   			current, 
                   			"when",
                    		lv_when_2_0, 
                    		"SQLCaseWhens");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_30,FOLLOW_KEYWORD_30_in_ruleSQLCASE11605); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5481:1: entryRuleSQLCaseWhens returns [EObject current=null] : iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF ;
    public final EObject entryRuleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCaseWhens = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5482:2: (iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5483:2: iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF
            {
             newCompositeNode(grammarAccess.getSQLCaseWhensRule()); 
            pushFollow(FOLLOW_ruleSQLCaseWhens_in_entryRuleSQLCaseWhens11639);
            iv_ruleSQLCaseWhens=ruleSQLCaseWhens();

            state._fsp--;

             current =iv_ruleSQLCaseWhens; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSQLCaseWhens11649); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5490:1: ruleSQLCaseWhens returns [EObject current=null] : (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) ;
    public final EObject ruleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject this_SqlCaseWhen_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5493:28: ( (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5494:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5494:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5495:5: this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getSqlCaseWhenParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens11696);
            this_SqlCaseWhen_0=ruleSqlCaseWhen();

            state._fsp--;


                    current = this_SqlCaseWhen_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5503:1: ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==KEYWORD_50) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5503:2: () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5503:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5504:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getSQLCaseWhensAccess().getWhenListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5509:2: ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    int cnt112=0;
                    loop112:
                    do {
                        int alt112=2;
                        int LA112_0 = input.LA(1);

                        if ( (LA112_0==KEYWORD_50) ) {
                            alt112=1;
                        }


                        switch (alt112) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5510:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5510:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5511:3: lv_entries_2_0= ruleSqlCaseWhen
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getEntriesSqlCaseWhenParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens11726);
                    	    lv_entries_2_0=ruleSqlCaseWhen();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getSQLCaseWhensRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_2_0, 
                    	            		"SqlCaseWhen");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt112 >= 1 ) break loop112;
                                EarlyExitException eee =
                                    new EarlyExitException(112, input);
                                throw eee;
                        }
                        cnt112++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5535:1: entryRuleSqlCaseWhen returns [EObject current=null] : iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF ;
    public final EObject entryRuleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSqlCaseWhen = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5536:2: (iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5537:2: iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF
            {
             newCompositeNode(grammarAccess.getSqlCaseWhenRule()); 
            pushFollow(FOLLOW_ruleSqlCaseWhen_in_entryRuleSqlCaseWhen11764);
            iv_ruleSqlCaseWhen=ruleSqlCaseWhen();

            state._fsp--;

             current =iv_ruleSqlCaseWhen; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSqlCaseWhen11774); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5544:1: ruleSqlCaseWhen returns [EObject current=null] : (otherlv_0= KEYWORD_50 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_49 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_36 ( (lv_eexp_5_0= ruleOperand ) ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5547:28: ( (otherlv_0= KEYWORD_50 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_49 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_36 ( (lv_eexp_5_0= ruleOperand ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5548:1: (otherlv_0= KEYWORD_50 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_49 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_36 ( (lv_eexp_5_0= ruleOperand ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5548:1: (otherlv_0= KEYWORD_50 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_49 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_36 ( (lv_eexp_5_0= ruleOperand ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5549:2: otherlv_0= KEYWORD_50 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_49 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_36 ( (lv_eexp_5_0= ruleOperand ) ) )?
            {
            otherlv_0=(Token)match(input,KEYWORD_50,FOLLOW_KEYWORD_50_in_ruleSqlCaseWhen11812); 

                	newLeafNode(otherlv_0, grammarAccess.getSqlCaseWhenAccess().getWHENKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5553:1: ( (lv_expr_1_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5554:1: (lv_expr_1_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5554:1: (lv_expr_1_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5555:3: lv_expr_1_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getExprFullExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleSqlCaseWhen11832);
            lv_expr_1_0=ruleFullExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_1_0, 
                    		"FullExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_49,FOLLOW_KEYWORD_49_in_ruleSqlCaseWhen11845); 

                	newLeafNode(otherlv_2, grammarAccess.getSqlCaseWhenAccess().getTHENKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5576:1: ( (lv_texp_3_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5577:1: (lv_texp_3_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5577:1: (lv_texp_3_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5578:3: lv_texp_3_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getTexpOperandParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleSqlCaseWhen11865);
            lv_texp_3_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
            	        }
                   		set(
                   			current, 
                   			"texp",
                    		lv_texp_3_0, 
                    		"Operand");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5594:2: (otherlv_4= KEYWORD_36 ( (lv_eexp_5_0= ruleOperand ) ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==KEYWORD_36) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5595:2: otherlv_4= KEYWORD_36 ( (lv_eexp_5_0= ruleOperand ) )
                    {
                    otherlv_4=(Token)match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_ruleSqlCaseWhen11879); 

                        	newLeafNode(otherlv_4, grammarAccess.getSqlCaseWhenAccess().getELSEKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5599:1: ( (lv_eexp_5_0= ruleOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5600:1: (lv_eexp_5_0= ruleOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5600:1: (lv_eexp_5_0= ruleOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5601:3: lv_eexp_5_0= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getEexpOperandParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleSqlCaseWhen11899);
                    lv_eexp_5_0=ruleOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
                    	        }
                           		set(
                           			current, 
                           			"eexp",
                            		lv_eexp_5_0, 
                            		"Operand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5625:1: entryRuleJoinType returns [String current=null] : iv_ruleJoinType= ruleJoinType EOF ;
    public final String entryRuleJoinType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleJoinType = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5626:1: (iv_ruleJoinType= ruleJoinType EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5627:2: iv_ruleJoinType= ruleJoinType EOF
            {
             newCompositeNode(grammarAccess.getJoinTypeRule()); 
            pushFollow(FOLLOW_ruleJoinType_in_entryRuleJoinType11937);
            iv_ruleJoinType=ruleJoinType();

            state._fsp--;

             current =iv_ruleJoinType.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJoinType11948); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5634:1: ruleJoinType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= KEYWORD_77 )? (kw= KEYWORD_55 | ( (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )? ) | kw= KEYWORD_52 | kw= KEYWORD_99 )? kw= KEYWORD_39 ) ;
    public final AntlrDatatypeRuleToken ruleJoinType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5638:6: ( ( (kw= KEYWORD_77 )? (kw= KEYWORD_55 | ( (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )? ) | kw= KEYWORD_52 | kw= KEYWORD_99 )? kw= KEYWORD_39 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5639:1: ( (kw= KEYWORD_77 )? (kw= KEYWORD_55 | ( (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )? ) | kw= KEYWORD_52 | kw= KEYWORD_99 )? kw= KEYWORD_39 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5639:1: ( (kw= KEYWORD_77 )? (kw= KEYWORD_55 | ( (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )? ) | kw= KEYWORD_52 | kw= KEYWORD_99 )? kw= KEYWORD_39 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5639:2: (kw= KEYWORD_77 )? (kw= KEYWORD_55 | ( (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )? ) | kw= KEYWORD_52 | kw= KEYWORD_99 )? kw= KEYWORD_39
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5639:2: (kw= KEYWORD_77 )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==KEYWORD_77) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5640:2: kw= KEYWORD_77
                    {
                    kw=(Token)match(input,KEYWORD_77,FOLLOW_KEYWORD_77_in_ruleJoinType11987); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getNATURALKeyword_0()); 
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5645:3: (kw= KEYWORD_55 | ( (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )? ) | kw= KEYWORD_52 | kw= KEYWORD_99 )?
            int alt118=5;
            switch ( input.LA(1) ) {
                case KEYWORD_55:
                    {
                    alt118=1;
                    }
                    break;
                case KEYWORD_64:
                case KEYWORD_38:
                case KEYWORD_41:
                    {
                    alt118=2;
                    }
                    break;
                case KEYWORD_52:
                    {
                    alt118=3;
                    }
                    break;
                case KEYWORD_99:
                    {
                    alt118=4;
                    }
                    break;
            }

            switch (alt118) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5646:2: kw= KEYWORD_55
                    {
                    kw=(Token)match(input,KEYWORD_55,FOLLOW_KEYWORD_55_in_ruleJoinType12003); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getINNERKeyword_1_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5652:6: ( (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5652:6: ( (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5652:7: (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 ) (kw= KEYWORD_61 )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5652:7: (kw= KEYWORD_41 | kw= KEYWORD_64 | kw= KEYWORD_38 )
                    int alt116=3;
                    switch ( input.LA(1) ) {
                    case KEYWORD_41:
                        {
                        alt116=1;
                        }
                        break;
                    case KEYWORD_64:
                        {
                        alt116=2;
                        }
                        break;
                    case KEYWORD_38:
                        {
                        alt116=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 116, 0, input);

                        throw nvae;
                    }

                    switch (alt116) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5653:2: kw= KEYWORD_41
                            {
                            kw=(Token)match(input,KEYWORD_41,FOLLOW_KEYWORD_41_in_ruleJoinType12024); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getLEFTKeyword_1_1_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5660:2: kw= KEYWORD_64
                            {
                            kw=(Token)match(input,KEYWORD_64,FOLLOW_KEYWORD_64_in_ruleJoinType12043); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getRIGHTKeyword_1_1_0_1()); 
                                

                            }
                            break;
                        case 3 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5667:2: kw= KEYWORD_38
                            {
                            kw=(Token)match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_ruleJoinType12062); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getFULLKeyword_1_1_0_2()); 
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5672:2: (kw= KEYWORD_61 )?
                    int alt117=2;
                    int LA117_0 = input.LA(1);

                    if ( (LA117_0==KEYWORD_61) ) {
                        alt117=1;
                    }
                    switch (alt117) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5673:2: kw= KEYWORD_61
                            {
                            kw=(Token)match(input,KEYWORD_61,FOLLOW_KEYWORD_61_in_ruleJoinType12077); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getOUTERKeyword_1_1_1()); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5680:2: kw= KEYWORD_52
                    {
                    kw=(Token)match(input,KEYWORD_52,FOLLOW_KEYWORD_52_in_ruleJoinType12099); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getCROSSKeyword_1_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5687:2: kw= KEYWORD_99
                    {
                    kw=(Token)match(input,KEYWORD_99,FOLLOW_KEYWORD_99_in_ruleJoinType12118); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getSTRAIGHT_JOINKeyword_1_3()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_ruleJoinType12133); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5706:1: entryRuleDBID returns [String current=null] : iv_ruleDBID= ruleDBID EOF ;
    public final String entryRuleDBID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDBID = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5707:1: (iv_ruleDBID= ruleDBID EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5708:2: iv_ruleDBID= ruleDBID EOF
            {
             newCompositeNode(grammarAccess.getDBIDRule()); 
            pushFollow(FOLLOW_ruleDBID_in_entryRuleDBID12173);
            iv_ruleDBID=ruleDBID();

            state._fsp--;

             current =iv_ruleDBID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBID12184); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5715:1: ruleDBID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDBID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_DBNAME_1=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5719:6: ( (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5720:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5720:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            int alt119=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt119=1;
                }
                break;
            case RULE_DBNAME:
                {
                alt119=2;
                }
                break;
            case RULE_STRING:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5720:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDBID12224); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getDBIDAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5728:10: this_DBNAME_1= RULE_DBNAME
                    {
                    this_DBNAME_1=(Token)match(input,RULE_DBNAME,FOLLOW_RULE_DBNAME_in_ruleDBID12250); 

                    		current.merge(this_DBNAME_1);
                        
                     
                        newLeafNode(this_DBNAME_1, grammarAccess.getDBIDAccess().getDBNAMETerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5736:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDBID12276); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5751:1: entryRuleStringOperand returns [String current=null] : iv_ruleStringOperand= ruleStringOperand EOF ;
    public final String entryRuleStringOperand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5752:1: (iv_ruleStringOperand= ruleStringOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5753:2: iv_ruleStringOperand= ruleStringOperand EOF
            {
             newCompositeNode(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_ruleStringOperand_in_entryRuleStringOperand12321);
            iv_ruleStringOperand=ruleStringOperand();

            state._fsp--;

             current =iv_ruleStringOperand.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringOperand12332); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5760:1: ruleStringOperand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING__0= RULE_STRING_ ;
    public final AntlrDatatypeRuleToken ruleStringOperand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING__0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5764:6: (this_STRING__0= RULE_STRING_ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5765:5: this_STRING__0= RULE_STRING_
            {
            this_STRING__0=(Token)match(input,RULE_STRING_,FOLLOW_RULE_STRING__in_ruleStringOperand12371); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5780:1: entryRuleFNAME returns [String current=null] : iv_ruleFNAME= ruleFNAME EOF ;
    public final String entryRuleFNAME() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFNAME = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5781:1: (iv_ruleFNAME= ruleFNAME EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5782:2: iv_ruleFNAME= ruleFNAME EOF
            {
             newCompositeNode(grammarAccess.getFNAMERule()); 
            pushFollow(FOLLOW_ruleFNAME_in_entryRuleFNAME12415);
            iv_ruleFNAME=ruleFNAME();

            state._fsp--;

             current =iv_ruleFNAME.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFNAME12426); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5789:1: ruleFNAME returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= KEYWORD_1 ) ;
    public final AntlrDatatypeRuleToken ruleFNAME() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5793:6: ( (this_ID_0= RULE_ID kw= KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5794:1: (this_ID_0= RULE_ID kw= KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5794:1: (this_ID_0= RULE_ID kw= KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5794:6: this_ID_0= RULE_ID kw= KEYWORD_1
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFNAME12466); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getFNAMEAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleFNAME12484); 

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


    // $ANTLR start "ruleXFunction"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5815:1: ruleXFunction returns [Enumerator current=null] : ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_59 ) | (enumLiteral_2= KEYWORD_53 ) | (enumLiteral_3= KEYWORD_84 ) | (enumLiteral_4= KEYWORD_42 ) | (enumLiteral_5= KEYWORD_74 ) | (enumLiteral_6= KEYWORD_56 ) | (enumLiteral_7= KEYWORD_87 ) | (enumLiteral_8= KEYWORD_72 ) | (enumLiteral_9= KEYWORD_93 ) | (enumLiteral_10= KEYWORD_86 ) | (enumLiteral_11= KEYWORD_80 ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5817:28: ( ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_59 ) | (enumLiteral_2= KEYWORD_53 ) | (enumLiteral_3= KEYWORD_84 ) | (enumLiteral_4= KEYWORD_42 ) | (enumLiteral_5= KEYWORD_74 ) | (enumLiteral_6= KEYWORD_56 ) | (enumLiteral_7= KEYWORD_87 ) | (enumLiteral_8= KEYWORD_72 ) | (enumLiteral_9= KEYWORD_93 ) | (enumLiteral_10= KEYWORD_86 ) | (enumLiteral_11= KEYWORD_80 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5818:1: ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_59 ) | (enumLiteral_2= KEYWORD_53 ) | (enumLiteral_3= KEYWORD_84 ) | (enumLiteral_4= KEYWORD_42 ) | (enumLiteral_5= KEYWORD_74 ) | (enumLiteral_6= KEYWORD_56 ) | (enumLiteral_7= KEYWORD_87 ) | (enumLiteral_8= KEYWORD_72 ) | (enumLiteral_9= KEYWORD_93 ) | (enumLiteral_10= KEYWORD_86 ) | (enumLiteral_11= KEYWORD_80 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5818:1: ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_59 ) | (enumLiteral_2= KEYWORD_53 ) | (enumLiteral_3= KEYWORD_84 ) | (enumLiteral_4= KEYWORD_42 ) | (enumLiteral_5= KEYWORD_74 ) | (enumLiteral_6= KEYWORD_56 ) | (enumLiteral_7= KEYWORD_87 ) | (enumLiteral_8= KEYWORD_72 ) | (enumLiteral_9= KEYWORD_93 ) | (enumLiteral_10= KEYWORD_86 ) | (enumLiteral_11= KEYWORD_80 ) )
            int alt120=12;
            switch ( input.LA(1) ) {
            case KEYWORD_20:
                {
                alt120=1;
                }
                break;
            case KEYWORD_59:
                {
                alt120=2;
                }
                break;
            case KEYWORD_53:
                {
                alt120=3;
                }
                break;
            case KEYWORD_84:
                {
                alt120=4;
                }
                break;
            case KEYWORD_42:
                {
                alt120=5;
                }
                break;
            case KEYWORD_74:
                {
                alt120=6;
                }
                break;
            case KEYWORD_56:
                {
                alt120=7;
                }
                break;
            case KEYWORD_87:
                {
                alt120=8;
                }
                break;
            case KEYWORD_72:
                {
                alt120=9;
                }
                break;
            case KEYWORD_93:
                {
                alt120=10;
                }
                break;
            case KEYWORD_86:
                {
                alt120=11;
                }
                break;
            case KEYWORD_80:
                {
                alt120=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 120, 0, input);

                throw nvae;
            }

            switch (alt120) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5818:2: (enumLiteral_0= KEYWORD_20 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5818:2: (enumLiteral_0= KEYWORD_20 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5818:7: enumLiteral_0= KEYWORD_20
                    {
                    enumLiteral_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleXFunction12541); 

                            current = grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5824:6: (enumLiteral_1= KEYWORD_59 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5824:6: (enumLiteral_1= KEYWORD_59 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5824:11: enumLiteral_1= KEYWORD_59
                    {
                    enumLiteral_1=(Token)match(input,KEYWORD_59,FOLLOW_KEYWORD_59_in_ruleXFunction12563); 

                            current = grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5830:6: (enumLiteral_2= KEYWORD_53 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5830:6: (enumLiteral_2= KEYWORD_53 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5830:11: enumLiteral_2= KEYWORD_53
                    {
                    enumLiteral_2=(Token)match(input,KEYWORD_53,FOLLOW_KEYWORD_53_in_ruleXFunction12585); 

                            current = grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5836:6: (enumLiteral_3= KEYWORD_84 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5836:6: (enumLiteral_3= KEYWORD_84 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5836:11: enumLiteral_3= KEYWORD_84
                    {
                    enumLiteral_3=(Token)match(input,KEYWORD_84,FOLLOW_KEYWORD_84_in_ruleXFunction12607); 

                            current = grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5842:6: (enumLiteral_4= KEYWORD_42 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5842:6: (enumLiteral_4= KEYWORD_42 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5842:11: enumLiteral_4= KEYWORD_42
                    {
                    enumLiteral_4=(Token)match(input,KEYWORD_42,FOLLOW_KEYWORD_42_in_ruleXFunction12629); 

                            current = grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5848:6: (enumLiteral_5= KEYWORD_74 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5848:6: (enumLiteral_5= KEYWORD_74 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5848:11: enumLiteral_5= KEYWORD_74
                    {
                    enumLiteral_5=(Token)match(input,KEYWORD_74,FOLLOW_KEYWORD_74_in_ruleXFunction12651); 

                            current = grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5854:6: (enumLiteral_6= KEYWORD_56 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5854:6: (enumLiteral_6= KEYWORD_56 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5854:11: enumLiteral_6= KEYWORD_56
                    {
                    enumLiteral_6=(Token)match(input,KEYWORD_56,FOLLOW_KEYWORD_56_in_ruleXFunction12673); 

                            current = grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5860:6: (enumLiteral_7= KEYWORD_87 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5860:6: (enumLiteral_7= KEYWORD_87 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5860:11: enumLiteral_7= KEYWORD_87
                    {
                    enumLiteral_7=(Token)match(input,KEYWORD_87,FOLLOW_KEYWORD_87_in_ruleXFunction12695); 

                            current = grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5866:6: (enumLiteral_8= KEYWORD_72 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5866:6: (enumLiteral_8= KEYWORD_72 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5866:11: enumLiteral_8= KEYWORD_72
                    {
                    enumLiteral_8=(Token)match(input,KEYWORD_72,FOLLOW_KEYWORD_72_in_ruleXFunction12717); 

                            current = grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5872:6: (enumLiteral_9= KEYWORD_93 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5872:6: (enumLiteral_9= KEYWORD_93 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5872:11: enumLiteral_9= KEYWORD_93
                    {
                    enumLiteral_9=(Token)match(input,KEYWORD_93,FOLLOW_KEYWORD_93_in_ruleXFunction12739); 

                            current = grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5878:6: (enumLiteral_10= KEYWORD_86 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5878:6: (enumLiteral_10= KEYWORD_86 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5878:11: enumLiteral_10= KEYWORD_86
                    {
                    enumLiteral_10=(Token)match(input,KEYWORD_86,FOLLOW_KEYWORD_86_in_ruleXFunction12761); 

                            current = grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5884:6: (enumLiteral_11= KEYWORD_80 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5884:6: (enumLiteral_11= KEYWORD_80 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5884:11: enumLiteral_11= KEYWORD_80
                    {
                    enumLiteral_11=(Token)match(input,KEYWORD_80,FOLLOW_KEYWORD_80_in_ruleXFunction12783); 

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


    protected DFA59 dfa59 = new DFA59(this);
    static final String DFA59_eotS =
        "\21\uffff";
    static final String DFA59_eofS =
        "\21\uffff";
    static final String DFA59_minS =
        "\1\123\1\147\1\22\14\140\2\uffff";
    static final String DFA59_maxS =
        "\1\123\1\147\1\130\14\150\2\uffff";
    static final String DFA59_acceptS =
        "\17\uffff\1\1\1\2";
    static final String DFA59_specialS =
        "\21\uffff}>";
    static final String[] DFA59_transitionS = {
            "\1\1",
            "\1\2",
            "\1\14\1\16\3\uffff\1\6\1\uffff\1\15\1\12\1\13\1\uffff\1\10"+
            "\14\uffff\1\5\2\uffff\1\11\2\uffff\1\4\17\uffff\1\7\27\uffff"+
            "\1\3",
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

    static final short[] DFA59_eot = DFA.unpackEncodedString(DFA59_eotS);
    static final short[] DFA59_eof = DFA.unpackEncodedString(DFA59_eofS);
    static final char[] DFA59_min = DFA.unpackEncodedStringToUnsignedChars(DFA59_minS);
    static final char[] DFA59_max = DFA.unpackEncodedStringToUnsignedChars(DFA59_maxS);
    static final short[] DFA59_accept = DFA.unpackEncodedString(DFA59_acceptS);
    static final short[] DFA59_special = DFA.unpackEncodedString(DFA59_specialS);
    static final short[][] DFA59_transition;

    static {
        int numStates = DFA59_transitionS.length;
        DFA59_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA59_transition[i] = DFA.unpackEncodedString(DFA59_transitionS[i]);
        }
    }

    class DFA59 extends DFA {

        public DFA59(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 59;
            this.eot = DFA59_eot;
            this.eof = DFA59_eof;
            this.min = DFA59_min;
            this.max = DFA59_max;
            this.accept = DFA59_accept;
            this.special = DFA59_special;
            this.transition = DFA59_transition;
        }
        public String getDescription() {
            return "2441:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_ruleModel123 = new BitSet(new long[]{0x0000404001000402L});
    public static final BitSet FOLLOW_KEYWORD_85_in_ruleModel137 = new BitSet(new long[]{0x0000000000000000L,0x0038200000000000L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_ruleModel157 = new BitSet(new long[]{0x0000404000000402L});
    public static final BitSet FOLLOW_KEYWORD_57_in_ruleModel173 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000400L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleModel186 = new BitSet(new long[]{0x0000004000000402L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleModel203 = new BitSet(new long[]{0x0000004000000402L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleModel216 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleModel226 = new BitSet(new long[]{0x0000004000000402L});
    public static final BitSet FOLLOW_KEYWORD_70_in_ruleModel245 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleModel255 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_KEYWORD_95_in_ruleModel270 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleModel280 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_KEYWORD_91_in_ruleModel292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_entryRuleSelectQuery328 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectQuery338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSelectQuery385 = new BitSet(new long[]{0x0040800800004002L});
    public static final BitSet FOLLOW_ruleSelectSubSet_in_ruleSelectQuery405 = new BitSet(new long[]{0x0040800800004002L});
    public static final BitSet FOLLOW_ruleSelectSubSet_in_entryRuleSelectSubSet441 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectSubSet451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_65_in_ruleSelectSubSet497 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_KEYWORD_89_in_ruleSelectSubSet525 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_KEYWORD_58_in_ruleSelectSubSet553 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_KEYWORD_67_in_ruleSelectSubSet581 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleSelectSubSet614 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSelectSubSet647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_entryRuleSelect682 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelect692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_71_in_ruleSelect736 = new BitSet(new long[]{0x0100010000100000L,0x003FFC0020010000L});
    public static final BitSet FOLLOW_KEYWORD_81_in_ruleSelect761 = new BitSet(new long[]{0x0100010000100000L,0x003FFC0020010000L});
    public static final BitSet FOLLOW_KEYWORD_32_in_ruleSelect776 = new BitSet(new long[]{0x0000000000000000L,0x0002200000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleSelect787 = new BitSet(new long[]{0x0100010200120000L,0x003FFC0020010000L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleSelect803 = new BitSet(new long[]{0x0100010200120000L,0x003FFC0020010000L});
    public static final BitSet FOLLOW_KEYWORD_78_in_ruleSelect817 = new BitSet(new long[]{0x0100010000120000L,0x003FFC0020010000L});
    public static final BitSet FOLLOW_KEYWORD_92_in_ruleSelect832 = new BitSet(new long[]{0x0100010000100000L,0x003FFC0020010000L});
    public static final BitSet FOLLOW_ruleColumns_in_ruleSelect856 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_KEYWORD_37_in_ruleSelect869 = new BitSet(new long[]{0x0000000000000000L,0x0038000020000000L});
    public static final BitSet FOLLOW_ruleTables_in_ruleSelect889 = new BitSet(new long[]{0x0080001000200002L});
    public static final BitSet FOLLOW_KEYWORD_66_in_ruleSelect903 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008000CL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect923 = new BitSet(new long[]{0x0000001000200002L});
    public static final BitSet FOLLOW_KEYWORD_82_in_ruleSelect939 = new BitSet(new long[]{0x0000000000000000L,0x0038000000000000L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_ruleSelect959 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_68_in_ruleSelect975 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008000CL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns1032 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns1089 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleColumns1112 = new BitSet(new long[]{0x0100010000100000L,0x003FFC0020010000L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns1132 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias1171 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias1181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleColumnOrAlias1228 = new BitSet(new long[]{0x0000000000000002L,0x0038000000800000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleColumnOrAlias1247 = new BitSet(new long[]{0x0000000000000002L,0x0038000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias1280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleColumnOrAlias1305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectNameAll_in_ruleColumnOrAlias1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull1372 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull1382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull1429 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleColumnFull1452 = new BitSet(new long[]{0x0000000000000000L,0x0038000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull1472 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables1511 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables1521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables1568 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleTables1591 = new BitSet(new long[]{0x0000000000000000L,0x0038000020000000L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables1611 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleFromTable_in_entryRuleFromTable1650 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTable1660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTable1706 = new BitSet(new long[]{0xB020120100000082L});
    public static final BitSet FOLLOW_ruleFromTableJoin_in_ruleFromTable1727 = new BitSet(new long[]{0xB020120100000082L});
    public static final BitSet FOLLOW_ruleFromTableJoin_in_entryRuleFromTableJoin1763 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTableJoin1773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_ruleFromTableJoin1819 = new BitSet(new long[]{0x0000000000000000L,0x0038000020000000L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTableJoin1840 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_KEYWORD_21_in_ruleFromTableJoin1853 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008000CL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleFromTableJoin1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1908 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1965 = new BitSet(new long[]{0x0008000400000002L,0x0038000000800000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleTableOrAlias1992 = new BitSet(new long[]{0x0008000400000002L,0x0038000000800000L});
    public static final BitSet FOLLOW_rulePivotTable_in_ruleTableOrAlias2015 = new BitSet(new long[]{0x0000000000000002L,0x0038000000800000L});
    public static final BitSet FOLLOW_ruleUnpivotTable_in_ruleTableOrAlias2042 = new BitSet(new long[]{0x0000000000000002L,0x0038000000800000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleTableOrAlias2063 = new BitSet(new long[]{0x0000000000000002L,0x0038000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableOrAlias2096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotTable_in_entryRulePivotTable2132 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotTable2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_62_in_rulePivotTable2180 = new BitSet(new long[]{0x0000000000000000L,0x0000000020020000L});
    public static final BitSet FOLLOW_KEYWORD_33_in_rulePivotTable2193 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotTable2207 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_rulePivotFunctions_in_rulePivotTable2227 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_rulePivotForClause_in_rulePivotTable2248 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_rulePivotInClause_in_rulePivotTable2269 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotTable2282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotFunctions_in_entryRulePivotFunctions2316 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotFunctions2326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rulePivotFunctions2367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotInClause_in_entryRulePivotInClause2408 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotInClause2418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_rulePivotInClause2456 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotInClause2468 = new BitSet(new long[]{0x0000000000000000L,0x0038000020001000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rulePivotInClause2489 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_rulePivotInClause2516 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_rulePivotInClauseAny_in_rulePivotInClause2543 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotInClause2557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotInClauseAny_in_entryRulePivotInClauseAny2592 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotInClauseAny2603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rulePivotInClauseAny2641 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rulePivotInClauseAny2655 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rulePivotInClauseAny2668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotTable_in_entryRuleUnpivotTable2709 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotTable2719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_79_in_ruleUnpivotTable2757 = new BitSet(new long[]{0x0000000050000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_75_in_ruleUnpivotTable2771 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_73_in_ruleUnpivotTable2789 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_60_in_ruleUnpivotTable2802 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleUnpivotTable2816 = new BitSet(new long[]{0x0000000000000000L,0x0038000020000000L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotTable2836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_rulePivotForClause_in_ruleUnpivotTable2857 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClause_in_ruleUnpivotTable2878 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleUnpivotTable2891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClause_in_entryRuleUnpivotInClause2925 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClause2935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleUnpivotInClause2988 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleUnpivotInClause3012 = new BitSet(new long[]{0x0000000000000000L,0x0038000020000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_ruleUnpivotInClause3032 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleUnpivotInClause3045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_entryRuleUnpivotInClauseArgs3079 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClauseArgs3089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3136 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleUnpivotInClauseArgs3159 = new BitSet(new long[]{0x0000000000000000L,0x0038000020000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3179 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_entryRuleUnpivotInClauseArg3218 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClauseArg3228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3274 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleUnpivotInClauseArg3288 = new BitSet(new long[]{0x0000000000000000L,0x0038000020000000L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotForClause_in_entryRulePivotForClause3345 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotForClause3355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_31_in_rulePivotForClause3393 = new BitSet(new long[]{0x0000000000000000L,0x0038000020000000L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rulePivotForClause3415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotForClause3434 = new BitSet(new long[]{0x0100010000100000L,0x003FFC0020010000L});
    public static final BitSet FOLLOW_ruleColumns_in_rulePivotForClause3455 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotForClause3467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotColumns_in_entryRulePivotColumns3503 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotColumns3513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotColumns3560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotColumns3579 = new BitSet(new long[]{0x0000000000000000L,0x0038000000000000L});
    public static final BitSet FOLLOW_rulePivotCols_in_rulePivotColumns3600 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotColumns3612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCols_in_entryRulePivotCols3647 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotCols3657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotCols3704 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rulePivotCols3727 = new BitSet(new long[]{0x0000000000000000L,0x0038000000000000L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotCols3747 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_rulePivotCol_in_entryRulePivotCol3786 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotCol3796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rulePivotCol3843 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rulePivotCol3866 = new BitSet(new long[]{0x0000000000000000L,0x0038000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rulePivotCol3886 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull3925 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull3935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull3982 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleTableFull4005 = new BitSet(new long[]{0x0000000000000000L,0x0038000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull4025 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
    public static final BitSet FOLLOW_ruleDbObjectNameAll_in_entryRuleDbObjectNameAll4064 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectNameAll4074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_ruleDbObjectNameAll4120 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleDbObjectNameAll4133 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleDbObjectNameAll4143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName4177 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectName4187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_ruleDbObjectName4232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns4266 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns4276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4323 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByColumns4346 = new BitSet(new long[]{0x0000000000000000L,0x0038200000000000L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4366 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull4405 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull4415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull4462 = new BitSet(new long[]{0x0200000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOrderByColumnFull4485 = new BitSet(new long[]{0x0200000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_KEYWORD_29_in_ruleOrderByColumnFull4512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_35_in_ruleOrderByColumnFull4540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns4590 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumns4600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns4647 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleGroupByColumns4670 = new BitSet(new long[]{0x0000000000000000L,0x0038000000000000L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns4690 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull4729 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumnFull4739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleGroupByColumnFull4784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_entryRuleFullExpression4818 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullExpression4828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleFullExpression4875 = new BitSet(new long[]{0x0000000000000002L,0x0000080004000800L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression4905 = new BitSet(new long[]{0x0000000000000002L,0x0000080004000800L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond4943 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragmentSecond4953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleExpressionFragmentSecond5000 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008000CL});
    public static final BitSet FOLLOW_KEYWORD_22_in_ruleExpressionFragmentSecond5028 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008000CL});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond5063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragmentSecond5087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment5127 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragment5137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment5183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExpressionFragment5210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleExpressionFragment5239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression__in_ruleExpressionFragment5258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragment5284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup5324 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionGroup5334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_45_in_ruleExpressionGroup5389 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_44_in_ruleExpressionGroup5417 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleExpressionGroup5445 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008000CL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleExpressionGroup5465 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleExpressionGroup5478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression5512 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression5522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleXExpression5560 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression5581 = new BitSet(new long[]{0x000124002E8C0000L,0x0000000001000001L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression5601 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression5614 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleXExpression5634 = new BitSet(new long[]{0x0000000000000000L,0x0000020100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression5648 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression5668 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleXExpression5683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression__in_entryRuleXExpression_5717 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression_5727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleXExpression_5765 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression_5786 = new BitSet(new long[]{0x000124002E8C0000L,0x0000000001000001L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression_5806 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleXExpression_5819 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleXExpression_5839 = new BitSet(new long[]{0x0000000000000000L,0x0000030000000000L});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleXExpression_5853 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression_5873 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleXExpression_5888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams5922 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionParams5932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams5979 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpressionParams6002 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams6022 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleJRParameter_in_entryRuleJRParameter6061 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRParameter6071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJRParameter6112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression6151 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression6161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleExpression6207 = new BitSet(new long[]{0x0000002088401800L,0x0000007009740002L});
    public static final BitSet FOLLOW_KEYWORD_76_in_ruleExpression6229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_96_in_ruleExpression6257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_ruleExpression6298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_ruleExpression6325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_ruleExpression6352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_ruleExpression6379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_entryRuleComparison6415 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComparison6425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_10_in_ruleComparison6471 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020001440L});
    public static final BitSet FOLLOW_KEYWORD_18_in_ruleComparison6499 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020001440L});
    public static final BitSet FOLLOW_KEYWORD_8_in_ruleComparison6527 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020001440L});
    public static final BitSet FOLLOW_KEYWORD_16_in_ruleComparison6555 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020001440L});
    public static final BitSet FOLLOW_KEYWORD_9_in_ruleComparison6583 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020001440L});
    public static final BitSet FOLLOW_KEYWORD_17_in_ruleComparison6611 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020001440L});
    public static final BitSet FOLLOW_KEYWORD_14_in_ruleComparison6639 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020001440L});
    public static final BitSet FOLLOW_KEYWORD_23_in_ruleComparison6667 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020001440L});
    public static final BitSet FOLLOW_KEYWORD_28_in_ruleComparison6702 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleComparison6730 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_48_in_ruleComparison6758 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleComparison6794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_entryRuleLike6829 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLike6839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_43_in_ruleLike6885 = new BitSet(new long[]{0x0000010000000000L,0x0024040000000000L});
    public static final BitSet FOLLOW_KEYWORD_83_in_ruleLike6913 = new BitSet(new long[]{0x0000010000000000L,0x0024040000000000L});
    public static final BitSet FOLLOW_ruleLikeOperand_in_ruleLike6948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLikeOperand_in_entryRuleLikeOperand6983 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLikeOperand6993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleLikeOperand7039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleLikeOperand7066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_ruleLikeOperand7093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_ruleLikeOperand7120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_entryRuleBetween7155 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBetween7165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_72_in_ruleBetween7211 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_97_in_ruleBetween7239 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBetween7274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleBetween7287 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBetween7307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_entryRuleInOperator7342 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInOperator7352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_69_in_ruleInOperator7407 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleInOperator7435 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleInOperator7462 = new BitSet(new long[]{0x0000000000000000L,0x0007EC0020000000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleInOperator7483 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_ruleOperandList_in_ruleInOperator7510 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleInOperator7524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_entryRuleOperandList7558 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandList7568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleOperandList7615 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOperandList7638 = new BitSet(new long[]{0x0000000000000000L,0x0007EC0000000000L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleOperandList7658 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_entryRuleOperand7697 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperand7707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand7753 = new BitSet(new long[]{0x0000000000000002L,0x0000100A90000000L});
    public static final BitSet FOLLOW_KEYWORD_3_in_ruleOperand7778 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_5_in_ruleOperand7807 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperand7834 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_7_in_ruleOperand7863 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_24_in_ruleOperand7892 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand7914 = new BitSet(new long[]{0x0000000000000002L,0x0000100A90000000L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment7951 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFragment7961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_ruleOperandFragment8007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_ruleOperandFragment8034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment8061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_ruleOperandFragment8088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleOperandFragment8115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCASE_in_ruleOperandFragment8142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_entryRuleOperandFunction8177 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFunction8187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFNAME_in_ruleOperandFunction8242 = new BitSet(new long[]{0x0100010000100000L,0x003FFC0060000400L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperandFunction8254 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_ruleOpFunctionArg_in_ruleOperandFunction8280 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOperandFunction8295 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_ruleFunctionAnalytical_in_ruleOperandFunction8315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionAnalytical_in_entryRuleFunctionAnalytical8351 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFunctionAnalytical8361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_46_in_ruleFunctionAnalytical8399 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleFunctionAnalytical8411 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ruleAnalyticClause_in_ruleFunctionAnalytical8431 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleFunctionAnalytical8444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticClause_in_entryRuleAnalyticClause8478 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticClause8488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQueryPartitionClause_in_ruleAnalyticClause8535 = new BitSet(new long[]{0x0000000001000042L});
    public static final BitSet FOLLOW_ruleOrderByClause_in_ruleAnalyticClause8556 = new BitSet(new long[]{0x0010000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_ruleWindowingClause_in_ruleAnalyticClause8577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClause_in_entryRuleWindowingClause8615 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClause8625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_47_in_ruleWindowingClause8664 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_63_in_ruleWindowingClause8682 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleWindowingClauseBetween_in_ruleWindowingClause8705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClause8732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseBetween_in_entryRuleWindowingClauseBetween8767 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseBetween8777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_72_in_ruleWindowingClauseBetween8815 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClauseBetween8835 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleWindowingClauseBetween8848 = new BitSet(new long[]{0x0100010008000230L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandFollowing_in_ruleWindowingClauseBetween8868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandFollowing_in_entryRuleWindowingClauseOperandFollowing8903 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseOperandFollowing8913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_101_in_ruleWindowingClauseOperandFollowing8961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_94_in_ruleWindowingClauseOperandFollowing8979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandFollowing9006 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_KEYWORD_90_in_ruleWindowingClauseOperandFollowing9020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_88_in_ruleWindowingClauseOperandFollowing9038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_entryRuleWindowingClauseOperandPreceding9075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseOperandPreceding9085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_102_in_ruleWindowingClauseOperandPreceding9133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_94_in_ruleWindowingClauseOperandPreceding9151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandPreceding9178 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_KEYWORD_90_in_ruleWindowingClauseOperandPreceding9192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_88_in_ruleWindowingClauseOperandPreceding9210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClause_in_entryRuleOrderByClause9247 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClause9257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_85_in_ruleOrderByClause9296 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_100_in_ruleOrderByClause9314 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOrderByClauseArgs_in_ruleOrderByClause9335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClauseArgs_in_entryRuleOrderByClauseArgs9370 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClauseArgs9380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs9427 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByClauseArgs9450 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs9470 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_entryRuleOrderByClauseArg9509 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClauseArg9519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleOrderByClauseArg9565 = new BitSet(new long[]{0x0202000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_KEYWORD_29_in_ruleOrderByClauseArg9579 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_KEYWORD_35_in_ruleOrderByClauseArg9597 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_KEYWORD_60_in_ruleOrderByClauseArg9612 = new BitSet(new long[]{0x4000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_54_in_ruleOrderByClauseArg9625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_40_in_ruleOrderByClauseArg9643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQueryPartitionClause_in_entryRuleQueryPartitionClause9680 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQueryPartitionClause9690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_98_in_ruleQueryPartitionClause9728 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause9749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleQueryPartitionClause9769 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause9790 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleQueryPartitionClause9802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_entryRuleAnalyticExprArgs9838 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticExprArgs9848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs9895 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleAnalyticExprArgs9918 = new BitSet(new long[]{0x0100010008000220L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs9938 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_entryRuleAnalyticExprArg9977 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticExprArg9987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleAnalyticExprArg10033 = new BitSet(new long[]{0x0000000000000002L,0x0038000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleAnalyticExprArg10054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArg_in_entryRuleOpFunctionArg10090 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArg10100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg10147 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOpFunctionArg10170 = new BitSet(new long[]{0x0100010000100000L,0x003FEC0020000400L});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg10190 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_entryRuleOpFunctionArgOperand10229 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArgOperand10239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgAgregate_in_ruleOpFunctionArgOperand10286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionArgOperand10305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_entryRuleOpFunctionCast10342 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionCast10352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_51_in_ruleOpFunctionCast10390 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionCast10410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleOpFunctionCast10423 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOpFunctionCast10439 = new BitSet(new long[]{0x0000000000000000L,0x0000000060000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleOpFunctionCast10458 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOpFunctionCast10474 = new BitSet(new long[]{0x0000000000000000L,0x0000000140000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOpFunctionCast10493 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOpFunctionCast10509 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOpFunctionCast10529 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOpFunctionCast10543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgAgregate_in_entryRuleOpFunctionArgAgregate10577 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArgAgregate10587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleOpFunctionArgAgregate10626 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_KEYWORD_81_in_ruleOpFunctionArgAgregate10644 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionArgAgregate10666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment10700 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOperandFragment10710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_ruleXOperandFragment10756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment10783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleXOperandFragment10810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand10845 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameterOperand10855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRPARAM_in_ruleParameterOperand10896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand10935 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclamationParameterOperand10945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExclamationParameterOperand10986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand11025 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOperand11035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOperand11081 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_KEYWORD_25_in_ruleColumnOperand11100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand11147 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubQueryOperand11157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleSubQueryOperand11204 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_ruleSubQueryOperand11224 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleSubQueryOperand11237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand11271 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarOperand11281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleScalarOperand11323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleScalarOperand11355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand11378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleScalarOperand11406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIME_in_ruleScalarOperand11434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand11462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCASE_in_entryRuleSQLCASE11502 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSQLCASE11512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_34_in_ruleSQLCASE11550 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008010CL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSQLCASE11570 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008010CL});
    public static final BitSet FOLLOW_ruleSQLCaseWhens_in_ruleSQLCASE11592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_KEYWORD_30_in_ruleSQLCASE11605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCaseWhens_in_entryRuleSQLCaseWhens11639 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSQLCaseWhens11649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens11696 = new BitSet(new long[]{0x0100010000000002L,0x003FEC002008010CL});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens11726 = new BitSet(new long[]{0x0100010000000002L,0x003FEC002008010CL});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_entryRuleSqlCaseWhen11764 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSqlCaseWhen11774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_50_in_ruleSqlCaseWhen11812 = new BitSet(new long[]{0x0100010000000000L,0x003FEC002008000CL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSqlCaseWhen11832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_KEYWORD_49_in_ruleSqlCaseWhen11845 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleSqlCaseWhen11865 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_ruleSqlCaseWhen11879 = new BitSet(new long[]{0x0100010000000000L,0x003FEC0020000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleSqlCaseWhen11899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_entryRuleJoinType11937 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJoinType11948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_77_in_ruleJoinType11987 = new BitSet(new long[]{0xB020120000000080L});
    public static final BitSet FOLLOW_KEYWORD_55_in_ruleJoinType12003 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_41_in_ruleJoinType12024 = new BitSet(new long[]{0x2004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_64_in_ruleJoinType12043 = new BitSet(new long[]{0x2004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_38_in_ruleJoinType12062 = new BitSet(new long[]{0x2004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_61_in_ruleJoinType12077 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_52_in_ruleJoinType12099 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_99_in_ruleJoinType12118 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_39_in_ruleJoinType12133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_entryRuleDBID12173 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBID12184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDBID12224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DBNAME_in_ruleDBID12250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDBID12276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_entryRuleStringOperand12321 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringOperand12332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING__in_ruleStringOperand12371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFNAME_in_entryRuleFNAME12415 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFNAME12426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFNAME12466 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleFNAME12484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleXFunction12541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_59_in_ruleXFunction12563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_53_in_ruleXFunction12585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_84_in_ruleXFunction12607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_42_in_ruleXFunction12629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_74_in_ruleXFunction12651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_56_in_ruleXFunction12673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_87_in_ruleXFunction12695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_72_in_ruleXFunction12717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_93_in_ruleXFunction12739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_86_in_ruleXFunction12761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_80_in_ruleXFunction12783 = new BitSet(new long[]{0x0000000000000002L});

}