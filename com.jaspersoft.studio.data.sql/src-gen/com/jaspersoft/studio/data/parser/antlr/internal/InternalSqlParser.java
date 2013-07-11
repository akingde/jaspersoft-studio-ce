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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:71:1: ruleModel returns [EObject current=null] : (this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_46 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Select_0 = null;

        EObject lv_entries_3_0 = null;

        EObject lv_orderByEntry_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:74:28: ( (this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_46 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: (this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_46 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: (this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_46 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:76:5: this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_46 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getModelAccess().getSelectParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSelect_in_ruleModel124);
            this_Select_0=ruleSelect();

            state._fsp--;


                    current = this_Select_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:84:1: ( () (otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) ) )+ )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==KEYWORD_33) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:84:2: () (otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:84:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:85:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getModelAccess().getOrSelectEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:90:2: (otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) ) )+
                    int cnt1=0;
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==KEYWORD_33) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:91:2: otherlv_2= KEYWORD_33 ( (lv_entries_3_0= ruleSelect ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_33,FOLLOW_KEYWORD_33_in_ruleModel147); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getModelAccess().getUNIONKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:95:1: ( (lv_entries_3_0= ruleSelect ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:96:1: (lv_entries_3_0= ruleSelect )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:96:1: (lv_entries_3_0= ruleSelect )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:97:3: lv_entries_3_0= ruleSelect
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getModelAccess().getEntriesSelectParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleSelect_in_ruleModel167);
                    	    lv_entries_3_0=ruleSelect();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"Select");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt1 >= 1 ) break loop1;
                                EarlyExitException eee =
                                    new EarlyExitException(1, input);
                                throw eee;
                        }
                        cnt1++;
                    } while (true);


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:113:6: (otherlv_4= KEYWORD_46 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==KEYWORD_46) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:114:2: otherlv_4= KEYWORD_46 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) )
                    {
                    otherlv_4=(Token)match(input,KEYWORD_46,FOLLOW_KEYWORD_46_in_ruleModel185); 

                        	newLeafNode(otherlv_4, grammarAccess.getModelAccess().getORDERBYKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:118:1: ( (lv_orderByEntry_5_0= ruleOrderByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:119:1: (lv_orderByEntry_5_0= ruleOrderByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:119:1: (lv_orderByEntry_5_0= ruleOrderByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:120:3: lv_orderByEntry_5_0= ruleOrderByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOrderByColumns_in_ruleModel205);
                    lv_orderByEntry_5_0=ruleOrderByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"orderByEntry",
                            		lv_orderByEntry_5_0, 
                            		"OrderByColumns");
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
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleSelect"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:144:1: entryRuleSelect returns [EObject current=null] : iv_ruleSelect= ruleSelect EOF ;
    public final EObject entryRuleSelect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelect = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:145:2: (iv_ruleSelect= ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:146:2: iv_ruleSelect= ruleSelect EOF
            {
             newCompositeNode(grammarAccess.getSelectRule()); 
            pushFollow(FOLLOW_ruleSelect_in_entryRuleSelect242);
            iv_ruleSelect=ruleSelect();

            state._fsp--;

             current =iv_ruleSelect; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelect252); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:153:1: ruleSelect returns [EObject current=null] : ( ( (lv_select_0_0= KEYWORD_36 ) ) (otherlv_1= KEYWORD_41 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_26 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_34 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_42 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_35 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )? ) ;
    public final EObject ruleSelect() throws RecognitionException {
        EObject current = null;

        Token lv_select_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_cols_2_0 = null;

        EObject lv_tbl_4_0 = null;

        EObject lv_whereExpression_6_0 = null;

        EObject lv_groupByEntry_8_0 = null;

        EObject lv_havingEntry_10_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:156:28: ( ( ( (lv_select_0_0= KEYWORD_36 ) ) (otherlv_1= KEYWORD_41 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_26 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_34 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_42 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_35 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:157:1: ( ( (lv_select_0_0= KEYWORD_36 ) ) (otherlv_1= KEYWORD_41 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_26 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_34 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_42 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_35 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:157:1: ( ( (lv_select_0_0= KEYWORD_36 ) ) (otherlv_1= KEYWORD_41 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_26 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_34 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_42 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_35 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:157:2: ( (lv_select_0_0= KEYWORD_36 ) ) (otherlv_1= KEYWORD_41 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_26 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_34 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_42 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_35 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:157:2: ( (lv_select_0_0= KEYWORD_36 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:158:1: (lv_select_0_0= KEYWORD_36 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:158:1: (lv_select_0_0= KEYWORD_36 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:159:3: lv_select_0_0= KEYWORD_36
            {
            lv_select_0_0=(Token)match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_ruleSelect296); 

                    newLeafNode(lv_select_0_0, grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectRule());
            	        }
                   		setWithLastConsumed(current, "select", lv_select_0_0, "SELECT");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:173:2: (otherlv_1= KEYWORD_41 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_41) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:174:2: otherlv_1= KEYWORD_41
                    {
                    otherlv_1=(Token)match(input,KEYWORD_41,FOLLOW_KEYWORD_41_in_ruleSelect321); 

                        	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:178:3: ( (lv_cols_2_0= ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:179:1: (lv_cols_2_0= ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:179:1: (lv_cols_2_0= ruleColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:180:3: lv_cols_2_0= ruleColumns
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleColumns_in_ruleSelect343);
            lv_cols_2_0=ruleColumns();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectRule());
            	        }
                   		set(
                   			current, 
                   			"cols",
                    		lv_cols_2_0, 
                    		"Columns");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleSelect356); 

                	newLeafNode(otherlv_3, grammarAccess.getSelectAccess().getFROMKeyword_3());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:201:1: ( (lv_tbl_4_0= ruleTables ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:202:1: (lv_tbl_4_0= ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:202:1: (lv_tbl_4_0= ruleTables )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:203:3: lv_tbl_4_0= ruleTables
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleTables_in_ruleSelect376);
            lv_tbl_4_0=ruleTables();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectRule());
            	        }
                   		set(
                   			current, 
                   			"tbl",
                    		lv_tbl_4_0, 
                    		"Tables");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:219:2: (otherlv_5= KEYWORD_34 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==KEYWORD_34) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:220:2: otherlv_5= KEYWORD_34 ( (lv_whereExpression_6_0= ruleFullExpression ) )
                    {
                    otherlv_5=(Token)match(input,KEYWORD_34,FOLLOW_KEYWORD_34_in_ruleSelect390); 

                        	newLeafNode(otherlv_5, grammarAccess.getSelectAccess().getWHEREKeyword_5_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:224:1: ( (lv_whereExpression_6_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:225:1: (lv_whereExpression_6_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:225:1: (lv_whereExpression_6_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:226:3: lv_whereExpression_6_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect410);
                    lv_whereExpression_6_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"whereExpression",
                            		lv_whereExpression_6_0, 
                            		"FullExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:242:4: (otherlv_7= KEYWORD_42 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_42) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:243:2: otherlv_7= KEYWORD_42 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) )
                    {
                    otherlv_7=(Token)match(input,KEYWORD_42,FOLLOW_KEYWORD_42_in_ruleSelect426); 

                        	newLeafNode(otherlv_7, grammarAccess.getSelectAccess().getGROUPBYKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:247:1: ( (lv_groupByEntry_8_0= ruleGroupByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:248:1: (lv_groupByEntry_8_0= ruleGroupByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:248:1: (lv_groupByEntry_8_0= ruleGroupByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:249:3: lv_groupByEntry_8_0= ruleGroupByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleGroupByColumns_in_ruleSelect446);
                    lv_groupByEntry_8_0=ruleGroupByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"groupByEntry",
                            		lv_groupByEntry_8_0, 
                            		"GroupByColumns");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:265:4: (otherlv_9= KEYWORD_35 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==KEYWORD_35) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:266:2: otherlv_9= KEYWORD_35 ( (lv_havingEntry_10_0= ruleFullExpression ) )
                    {
                    otherlv_9=(Token)match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_ruleSelect462); 

                        	newLeafNode(otherlv_9, grammarAccess.getSelectAccess().getHAVINGKeyword_7_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:270:1: ( (lv_havingEntry_10_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:271:1: (lv_havingEntry_10_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:271:1: (lv_havingEntry_10_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:272:3: lv_havingEntry_10_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect482);
                    lv_havingEntry_10_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"havingEntry",
                            		lv_havingEntry_10_0, 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:296:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:297:2: (iv_ruleColumns= ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:298:2: iv_ruleColumns= ruleColumns EOF
            {
             newCompositeNode(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns519);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns529); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:305:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:308:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:309:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:309:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:310:5: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns576);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;


                    current = this_ColumnOrAlias_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:318:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==KEYWORD_4) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:318:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:318:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:319:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:324:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==KEYWORD_4) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:325:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleColumns599); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:329:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:330:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:330:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:331:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns619);
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
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:355:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:356:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:357:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias658);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias668); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:364:1: ruleColumnOrAlias returns [EObject current=null] : ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_1_0=null;
        Token lv_allCols_3_0=null;
        EObject lv_cfull_0_0 = null;

        EObject lv_colAlias_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:367:28: ( ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:368:1: ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:368:1: ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                alt12=1;
            }
            else if ( (LA12_0==RULE_STAR) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:368:2: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:368:2: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:368:3: ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:368:3: ( (lv_cfull_0_0= ruleColumnFull ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:369:1: (lv_cfull_0_0= ruleColumnFull )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:369:1: (lv_cfull_0_0= ruleColumnFull )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:370:3: lv_cfull_0_0= ruleColumnFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getCfullColumnFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOrAlias715);
                    lv_cfull_0_0=ruleColumnFull();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"cfull",
                            		lv_cfull_0_0, 
                            		"ColumnFull");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:386:2: ( (lv_alias_1_0= KEYWORD_15 ) )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==KEYWORD_15) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:387:1: (lv_alias_1_0= KEYWORD_15 )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:387:1: (lv_alias_1_0= KEYWORD_15 )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:388:3: lv_alias_1_0= KEYWORD_15
                            {
                            lv_alias_1_0=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleColumnOrAlias734); 

                                    newLeafNode(lv_alias_1_0, grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getColumnOrAliasRule());
                            	        }
                                   		setWithLastConsumed(current, "alias", lv_alias_1_0, "AS");
                            	    

                            }


                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:402:3: ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==RULE_ID) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:403:1: (lv_colAlias_2_0= ruleDbObjectName )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:403:1: (lv_colAlias_2_0= ruleDbObjectName )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:404:3: lv_colAlias_2_0= ruleDbObjectName
                            {
                             
                            	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
                            	    
                            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias767);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:421:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:421:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:422:1: (lv_allCols_3_0= RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:422:1: (lv_allCols_3_0= RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:423:3: lv_allCols_3_0= RULE_STAR
                    {
                    lv_allCols_3_0=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleColumnOrAlias792); 

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

            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:447:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:448:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:449:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
             newCompositeNode(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull832);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull842); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:456:1: ruleColumnFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:459:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:460:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:460:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:461:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull889);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:469:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEYWORD_6) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:469:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:469:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:470:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnFullAccess().getColEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:475:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==KEYWORD_6) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:476:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleColumnFull912); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:480:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:481:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:481:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:482:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull932);
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
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:506:1: entryRuleTables returns [EObject current=null] : iv_ruleTables= ruleTables EOF ;
    public final EObject entryRuleTables() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTables = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:507:2: (iv_ruleTables= ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:508:2: iv_ruleTables= ruleTables EOF
            {
             newCompositeNode(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables971);
            iv_ruleTables=ruleTables();

            state._fsp--;

             current =iv_ruleTables; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables981); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:515:1: ruleTables returns [EObject current=null] : (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) ;
    public final EObject ruleTables() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_FromTable_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:518:28: ( (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:519:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:519:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:520:5: this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleFromTable_in_ruleTables1028);
            this_FromTable_0=ruleFromTable();

            state._fsp--;


                    current = this_FromTable_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:528:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==KEYWORD_4) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:528:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:528:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:529:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:534:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==KEYWORD_4) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:535:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleTables1051); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:539:1: ( (lv_entries_3_0= ruleFromTable ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:540:1: (lv_entries_3_0= ruleFromTable )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:540:1: (lv_entries_3_0= ruleFromTable )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:541:3: lv_entries_3_0= ruleFromTable
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleFromTable_in_ruleTables1071);
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
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:565:1: entryRuleFromTable returns [EObject current=null] : iv_ruleFromTable= ruleFromTable EOF ;
    public final EObject entryRuleFromTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:566:2: (iv_ruleFromTable= ruleFromTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:567:2: iv_ruleFromTable= ruleFromTable EOF
            {
             newCompositeNode(grammarAccess.getFromTableRule()); 
            pushFollow(FOLLOW_ruleFromTable_in_entryRuleFromTable1110);
            iv_ruleFromTable=ruleFromTable();

            state._fsp--;

             current =iv_ruleFromTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTable1120); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:574:1: ruleFromTable returns [EObject current=null] : ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( ( (lv_join_1_0= ruleJoinType ) ) ( (lv_onTable_2_0= ruleTableOrAlias ) ) otherlv_3= KEYWORD_17 ( (lv_joinExpr_4_0= ruleFullExpression ) ) )? ) ;
    public final EObject ruleFromTable() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        EObject lv_table_0_0 = null;

        Enumerator lv_join_1_0 = null;

        EObject lv_onTable_2_0 = null;

        EObject lv_joinExpr_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:577:28: ( ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( ( (lv_join_1_0= ruleJoinType ) ) ( (lv_onTable_2_0= ruleTableOrAlias ) ) otherlv_3= KEYWORD_17 ( (lv_joinExpr_4_0= ruleFullExpression ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:578:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( ( (lv_join_1_0= ruleJoinType ) ) ( (lv_onTable_2_0= ruleTableOrAlias ) ) otherlv_3= KEYWORD_17 ( (lv_joinExpr_4_0= ruleFullExpression ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:578:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( ( (lv_join_1_0= ruleJoinType ) ) ( (lv_onTable_2_0= ruleTableOrAlias ) ) otherlv_3= KEYWORD_17 ( (lv_joinExpr_4_0= ruleFullExpression ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:578:2: ( (lv_table_0_0= ruleTableOrAlias ) ) ( ( (lv_join_1_0= ruleJoinType ) ) ( (lv_onTable_2_0= ruleTableOrAlias ) ) otherlv_3= KEYWORD_17 ( (lv_joinExpr_4_0= ruleFullExpression ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:578:2: ( (lv_table_0_0= ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:579:1: (lv_table_0_0= ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:579:1: (lv_table_0_0= ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:580:3: lv_table_0_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTable1166);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:596:2: ( ( (lv_join_1_0= ruleJoinType ) ) ( (lv_onTable_2_0= ruleTableOrAlias ) ) otherlv_3= KEYWORD_17 ( (lv_joinExpr_4_0= ruleFullExpression ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=KEYWORD_56 && LA17_0<=KEYWORD_55)||(LA17_0>=KEYWORD_50 && LA17_0<=KEYWORD_51)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:596:3: ( (lv_join_1_0= ruleJoinType ) ) ( (lv_onTable_2_0= ruleTableOrAlias ) ) otherlv_3= KEYWORD_17 ( (lv_joinExpr_4_0= ruleFullExpression ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:596:3: ( (lv_join_1_0= ruleJoinType ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:597:1: (lv_join_1_0= ruleJoinType )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:597:1: (lv_join_1_0= ruleJoinType )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:598:3: lv_join_1_0= ruleJoinType
                    {
                     
                    	        newCompositeNode(grammarAccess.getFromTableAccess().getJoinJoinTypeEnumRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleJoinType_in_ruleFromTable1188);
                    lv_join_1_0=ruleJoinType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFromTableRule());
                    	        }
                           		set(
                           			current, 
                           			"join",
                            		lv_join_1_0, 
                            		"JoinType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:614:2: ( (lv_onTable_2_0= ruleTableOrAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:615:1: (lv_onTable_2_0= ruleTableOrAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:615:1: (lv_onTable_2_0= ruleTableOrAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:616:3: lv_onTable_2_0= ruleTableOrAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getFromTableAccess().getOnTableTableOrAliasParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTable1209);
                    lv_onTable_2_0=ruleTableOrAlias();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFromTableRule());
                    	        }
                           		set(
                           			current, 
                           			"onTable",
                            		lv_onTable_2_0, 
                            		"TableOrAlias");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_3=(Token)match(input,KEYWORD_17,FOLLOW_KEYWORD_17_in_ruleFromTable1222); 

                        	newLeafNode(otherlv_3, grammarAccess.getFromTableAccess().getONKeyword_1_2());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:637:1: ( (lv_joinExpr_4_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:638:1: (lv_joinExpr_4_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:638:1: (lv_joinExpr_4_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:639:3: lv_joinExpr_4_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getFromTableAccess().getJoinExprFullExpressionParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleFromTable1242);
                    lv_joinExpr_4_0=ruleFullExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFromTableRule());
                    	        }
                           		set(
                           			current, 
                           			"joinExpr",
                            		lv_joinExpr_4_0, 
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
    // $ANTLR end "ruleFromTable"


    // $ANTLR start "entryRuleTableOrAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:663:1: entryRuleTableOrAlias returns [EObject current=null] : iv_ruleTableOrAlias= ruleTableOrAlias EOF ;
    public final EObject entryRuleTableOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:664:2: (iv_ruleTableOrAlias= ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:665:2: iv_ruleTableOrAlias= ruleTableOrAlias EOF
            {
             newCompositeNode(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1279);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;

             current =iv_ruleTableOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias1289); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:672:1: ruleTableOrAlias returns [EObject current=null] : ( ( (lv_tfull_0_0= ruleTableFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_tblAlias_2_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleTableOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_1_0=null;
        EObject lv_tfull_0_0 = null;

        EObject lv_tblAlias_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:675:28: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_tblAlias_2_0= ruleDbObjectName ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:676:1: ( ( (lv_tfull_0_0= ruleTableFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_tblAlias_2_0= ruleDbObjectName ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:676:1: ( ( (lv_tfull_0_0= ruleTableFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_tblAlias_2_0= ruleDbObjectName ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:676:2: ( (lv_tfull_0_0= ruleTableFull ) ) ( (lv_alias_1_0= KEYWORD_15 ) )? ( (lv_tblAlias_2_0= ruleDbObjectName ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:676:2: ( (lv_tfull_0_0= ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:677:1: (lv_tfull_0_0= ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:677:1: (lv_tfull_0_0= ruleTableFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:678:3: lv_tfull_0_0= ruleTableFull
            {
             
            	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1335);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:694:2: ( (lv_alias_1_0= KEYWORD_15 ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_15) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:695:1: (lv_alias_1_0= KEYWORD_15 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:695:1: (lv_alias_1_0= KEYWORD_15 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:696:3: lv_alias_1_0= KEYWORD_15
                    {
                    lv_alias_1_0=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleTableOrAlias1354); 

                            newLeafNode(lv_alias_1_0, grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTableOrAliasRule());
                    	        }
                           		setWithLastConsumed(current, "alias", lv_alias_1_0, "AS");
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:710:3: ( (lv_tblAlias_2_0= ruleDbObjectName ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_ID) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:711:1: (lv_tblAlias_2_0= ruleDbObjectName )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:711:1: (lv_tblAlias_2_0= ruleDbObjectName )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:712:3: lv_tblAlias_2_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableOrAlias1387);
                    lv_tblAlias_2_0=ruleDbObjectName();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"tblAlias",
                            		lv_tblAlias_2_0, 
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


    // $ANTLR start "entryRuleTableFull"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:736:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:737:2: (iv_ruleTableFull= ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:738:2: iv_ruleTableFull= ruleTableFull EOF
            {
             newCompositeNode(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull1423);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;

             current =iv_ruleTableFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull1433); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:745:1: ruleTableFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:748:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:749:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:749:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:750:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull1480);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:758:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KEYWORD_6) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:758:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:758:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:759:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:764:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==KEYWORD_6) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:765:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleTableFull1503); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:769:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:770:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:770:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:771:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull1523);
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
    // $ANTLR end "ruleTableFull"


    // $ANTLR start "entryRuleDbObjectName"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:795:1: entryRuleDbObjectName returns [EObject current=null] : iv_ruleDbObjectName= ruleDbObjectName EOF ;
    public final EObject entryRuleDbObjectName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectName = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:796:2: (iv_ruleDbObjectName= ruleDbObjectName EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:797:2: iv_ruleDbObjectName= ruleDbObjectName EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameRule()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName1562);
            iv_ruleDbObjectName=ruleDbObjectName();

            state._fsp--;

             current =iv_ruleDbObjectName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectName1572); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:804:1: ruleDbObjectName returns [EObject current=null] : ( (lv_dbname_0_0= RULE_ID ) ) ;
    public final EObject ruleDbObjectName() throws RecognitionException {
        EObject current = null;

        Token lv_dbname_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:807:28: ( ( (lv_dbname_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:808:1: ( (lv_dbname_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:808:1: ( (lv_dbname_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:809:1: (lv_dbname_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:809:1: (lv_dbname_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:810:3: lv_dbname_0_0= RULE_ID
            {
            lv_dbname_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDbObjectName1613); 

            			newLeafNode(lv_dbname_0_0, grammarAccess.getDbObjectNameAccess().getDbnameIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDbObjectNameRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"dbname",
                    		lv_dbname_0_0, 
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
    // $ANTLR end "ruleDbObjectName"


    // $ANTLR start "entryRuleOrderByColumns"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:834:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:835:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:836:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns1652);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;

             current =iv_ruleOrderByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns1662); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:843:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:846:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:847:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:847:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:848:5: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns1709);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;


                    current = this_OrderByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==KEYWORD_4) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:857:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:862:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt22=0;
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==KEYWORD_4) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:863:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByColumns1732); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:867:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:868:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:868:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:869:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns1752);
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
                    	    if ( cnt22 >= 1 ) break loop22;
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

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:893:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:894:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:895:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull1791);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;

             current =iv_ruleOrderByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull1801); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:902:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 ) ) )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_direction_1_1=null;
        Token lv_direction_1_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:905:28: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:906:1: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:906:1: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:906:2: ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:906:2: ( (lv_colOrder_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:907:1: (lv_colOrder_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:907:1: (lv_colOrder_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:908:3: lv_colOrder_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull1847);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:924:2: ( ( (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==KEYWORD_25||LA25_0==KEYWORD_23) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:925:1: ( (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:925:1: ( (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:926:1: (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:926:1: (lv_direction_1_1= KEYWORD_23 | lv_direction_1_2= KEYWORD_25 )
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==KEYWORD_23) ) {
                        alt24=1;
                    }
                    else if ( (LA24_0==KEYWORD_25) ) {
                        alt24=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 0, input);

                        throw nvae;
                    }
                    switch (alt24) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:927:3: lv_direction_1_1= KEYWORD_23
                            {
                            lv_direction_1_1=(Token)match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_ruleOrderByColumnFull1868); 

                                    newLeafNode(lv_direction_1_1, grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                            	        }
                                   		setWithLastConsumed(current, "direction", lv_direction_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:940:8: lv_direction_1_2= KEYWORD_25
                            {
                            lv_direction_1_2=(Token)match(input,KEYWORD_25,FOLLOW_KEYWORD_25_in_ruleOrderByColumnFull1896); 

                                    newLeafNode(lv_direction_1_2, grammarAccess.getOrderByColumnFullAccess().getDirectionDESCKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                            	        }
                                   		setWithLastConsumed(current, "direction", lv_direction_1_2, null);
                            	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:964:1: entryRuleGroupByColumns returns [EObject current=null] : iv_ruleGroupByColumns= ruleGroupByColumns EOF ;
    public final EObject entryRuleGroupByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:965:2: (iv_ruleGroupByColumns= ruleGroupByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:966:2: iv_ruleGroupByColumns= ruleGroupByColumns EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnsRule()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns1946);
            iv_ruleGroupByColumns=ruleGroupByColumns();

            state._fsp--;

             current =iv_ruleGroupByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumns1956); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:973:1: ruleGroupByColumns returns [EObject current=null] : (this_ColumnFull_0= ruleColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) ) )+ )? ) ;
    public final EObject ruleGroupByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:976:28: ( (this_ColumnFull_0= ruleColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:977:1: (this_ColumnFull_0= ruleColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:977:1: (this_ColumnFull_0= ruleColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:978:5: this_ColumnFull_0= ruleColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getGroupByColumnsAccess().getColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleColumnFull_in_ruleGroupByColumns2003);
            this_ColumnFull_0=ruleColumnFull();

            state._fsp--;


                    current = this_ColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:986:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) ) )+ )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==KEYWORD_4) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:986:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:986:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:987:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:992:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) ) )+
                    int cnt26=0;
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==KEYWORD_4) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:993:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleGroupByColumns2026); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:997:1: ( (lv_entries_3_0= ruleColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:998:1: (lv_entries_3_0= ruleColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:998:1: (lv_entries_3_0= ruleColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:999:3: lv_entries_3_0= ruleColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupByColumnsAccess().getEntriesColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnFull_in_ruleGroupByColumns2046);
                    	    lv_entries_3_0=ruleColumnFull();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getGroupByColumnsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"ColumnFull");
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
    // $ANTLR end "ruleGroupByColumns"


    // $ANTLR start "entryRuleFullExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1023:1: entryRuleFullExpression returns [EObject current=null] : iv_ruleFullExpression= ruleFullExpression EOF ;
    public final EObject entryRuleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1024:2: (iv_ruleFullExpression= ruleFullExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1025:2: iv_ruleFullExpression= ruleFullExpression EOF
            {
             newCompositeNode(grammarAccess.getFullExpressionRule()); 
            pushFollow(FOLLOW_ruleFullExpression_in_entryRuleFullExpression2085);
            iv_ruleFullExpression=ruleFullExpression();

            state._fsp--;

             current =iv_ruleFullExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullExpression2095); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1032:1: ruleFullExpression returns [EObject current=null] : (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) ;
    public final EObject ruleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExpressionFragment_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1035:28: ( (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1036:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1036:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1037:5: this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleExpressionFragment_in_ruleFullExpression2142);
            this_ExpressionFragment_0=ruleExpressionFragment();

            state._fsp--;


                    current = this_ExpressionFragment_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1045:1: ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==KEYWORD_22||LA29_0==KEYWORD_18) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1045:2: () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1045:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1046:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1051:2: ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==KEYWORD_22||LA28_0==KEYWORD_18) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1052:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1052:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1053:3: lv_entries_2_0= ruleExpressionFragmentSecond
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression2172);
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
    // $ANTLR end "ruleFullExpression"


    // $ANTLR start "entryRuleExpressionFragmentSecond"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1077:1: entryRuleExpressionFragmentSecond returns [EObject current=null] : iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF ;
    public final EObject entryRuleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragmentSecond = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1078:2: (iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1079:2: iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentSecondRule()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond2210);
            iv_ruleExpressionFragmentSecond=ruleExpressionFragmentSecond();

            state._fsp--;

             current =iv_ruleExpressionFragmentSecond; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragmentSecond2220); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1086:1: ruleExpressionFragmentSecond returns [EObject current=null] : ( ( ( (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) ;
    public final EObject ruleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        Token lv_c_0_1=null;
        Token lv_c_0_2=null;
        EObject lv_efrag_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1089:28: ( ( ( ( (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1090:1: ( ( ( (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1090:1: ( ( ( (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1090:2: ( ( (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1090:2: ( ( (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1091:1: ( (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1091:1: ( (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1092:1: (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1092:1: (lv_c_0_1= KEYWORD_22 | lv_c_0_2= KEYWORD_18 )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==KEYWORD_22) ) {
                alt30=1;
            }
            else if ( (LA30_0==KEYWORD_18) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1093:3: lv_c_0_1= KEYWORD_22
                    {
                    lv_c_0_1=(Token)match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_ruleExpressionFragmentSecond2266); 

                            newLeafNode(lv_c_0_1, grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                    	        }
                           		setWithLastConsumed(current, "c", lv_c_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1106:8: lv_c_0_2= KEYWORD_18
                    {
                    lv_c_0_2=(Token)match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_ruleExpressionFragmentSecond2294); 

                            newLeafNode(lv_c_0_2, grammarAccess.getExpressionFragmentSecondAccess().getCORKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                    	        }
                           		setWithLastConsumed(current, "c", lv_c_0_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1122:2: ( (lv_efrag_1_0= ruleExpressionFragment ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1123:1: (lv_efrag_1_0= ruleExpressionFragment )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1123:1: (lv_efrag_1_0= ruleExpressionFragment )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1124:3: lv_efrag_1_0= ruleExpressionFragment
            {
             
            	        newCompositeNode(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond2329);
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

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1148:1: entryRuleExpressionFragment returns [EObject current=null] : iv_ruleExpressionFragment= ruleExpressionFragment EOF ;
    public final EObject entryRuleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1149:2: (iv_ruleExpressionFragment= ruleExpressionFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1150:2: iv_ruleExpressionFragment= ruleExpressionFragment EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment2364);
            iv_ruleExpressionFragment=ruleExpressionFragment();

            state._fsp--;

             current =iv_ruleExpressionFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragment2374); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1157:1: ruleExpressionFragment returns [EObject current=null] : ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( (lv_xexp_2_0= ruleXExpression ) ) ) ;
    public final EObject ruleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_expgroup_0_0 = null;

        EObject lv_exp_1_0 = null;

        EObject lv_xexp_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1160:28: ( ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( (lv_xexp_2_0= ruleXExpression ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1161:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( (lv_xexp_2_0= ruleXExpression ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1161:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( (lv_xexp_2_0= ruleXExpression ) ) )
            int alt31=3;
            switch ( input.LA(1) ) {
            case KEYWORD_1:
                {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==KEYWORD_24||(LA31_1>=KEYWORD_20 && LA31_1<=KEYWORD_21)||LA31_1==KEYWORD_1||(LA31_1>=RULE_SIGNED_INT && LA31_1<=RULE_SIGNED_DOUBLE)||LA31_1==RULE_ID||LA31_1==RULE_STRING) ) {
                    alt31=1;
                }
                else if ( (LA31_1==KEYWORD_36) ) {
                    alt31=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

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
                alt31=2;
                }
                break;
            case KEYWORD_21:
                {
                alt31=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1161:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1161:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1162:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1162:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1163:3: lv_expgroup_0_0= ruleExpressionGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment2420);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1180:6: ( (lv_exp_1_0= ruleExpression ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1180:6: ( (lv_exp_1_0= ruleExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1181:1: (lv_exp_1_0= ruleExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1181:1: (lv_exp_1_0= ruleExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1182:3: lv_exp_1_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleExpressionFragment2447);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1199:6: ( (lv_xexp_2_0= ruleXExpression ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1199:6: ( (lv_xexp_2_0= ruleXExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1200:1: (lv_xexp_2_0= ruleXExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1200:1: (lv_xexp_2_0= ruleXExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1201:3: lv_xexp_2_0= ruleXExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpression_in_ruleExpressionFragment2474);
                    lv_xexp_2_0=ruleXExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"xexp",
                            		lv_xexp_2_0, 
                            		"XExpression");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1225:1: entryRuleExpressionGroup returns [EObject current=null] : iv_ruleExpressionGroup= ruleExpressionGroup EOF ;
    public final EObject entryRuleExpressionGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionGroup = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1226:2: (iv_ruleExpressionGroup= ruleExpressionGroup EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1227:2: iv_ruleExpressionGroup= ruleExpressionGroup EOF
            {
             newCompositeNode(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup2509);
            iv_ruleExpressionGroup=ruleExpressionGroup();

            state._fsp--;

             current =iv_ruleExpressionGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionGroup2519); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1234:1: ruleExpressionGroup returns [EObject current=null] : ( () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleExpressionGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1237:28: ( ( () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1238:1: ( () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1238:1: ( () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1238:2: () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1238:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1239:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpressionGroupAccess().getExprGroupAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleExpressionGroup2566); 

                	newLeafNode(otherlv_1, grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1249:1: ( (lv_expr_2_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1250:1: (lv_expr_2_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1250:1: (lv_expr_2_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1251:3: lv_expr_2_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleExpressionGroup2586);
            lv_expr_2_0=ruleFullExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExpressionGroupRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_2_0, 
                    		"FullExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleExpressionGroup2599); 

                	newLeafNode(otherlv_3, grammarAccess.getExpressionGroupAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1280:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1281:2: (iv_ruleXExpression= ruleXExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1282:2: iv_ruleXExpression= ruleXExpression EOF
            {
             newCompositeNode(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression2633);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;

             current =iv_ruleXExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression2643); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1289:1: ruleXExpression returns [EObject current=null] : ( () otherlv_1= KEYWORD_21 ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= KEYWORD_4 ( (lv_col_4_0= ruleColumnOperand ) ) (otherlv_5= KEYWORD_4 ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= KEYWORD_11 ) ;
    public final EObject ruleXExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Enumerator lv_xf_2_0 = null;

        EObject lv_col_4_0 = null;

        EObject lv_prm_6_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1292:28: ( ( () otherlv_1= KEYWORD_21 ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= KEYWORD_4 ( (lv_col_4_0= ruleColumnOperand ) ) (otherlv_5= KEYWORD_4 ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1293:1: ( () otherlv_1= KEYWORD_21 ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= KEYWORD_4 ( (lv_col_4_0= ruleColumnOperand ) ) (otherlv_5= KEYWORD_4 ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1293:1: ( () otherlv_1= KEYWORD_21 ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= KEYWORD_4 ( (lv_col_4_0= ruleColumnOperand ) ) (otherlv_5= KEYWORD_4 ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1293:2: () otherlv_1= KEYWORD_21 ( (lv_xf_2_0= ruleXFunction ) ) otherlv_3= KEYWORD_4 ( (lv_col_4_0= ruleColumnOperand ) ) (otherlv_5= KEYWORD_4 ( (lv_prm_6_0= ruleXExpressionParams ) ) )? otherlv_7= KEYWORD_11
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1293:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1294:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpressionAccess().getXExprAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_ruleXExpression2690); 

                	newLeafNode(otherlv_1, grammarAccess.getXExpressionAccess().getXKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1304:1: ( (lv_xf_2_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1305:1: (lv_xf_2_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1305:1: (lv_xf_2_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1306:3: lv_xf_2_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression2710);
            lv_xf_2_0=ruleXFunction();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"xf",
                    		lv_xf_2_0, 
                    		"XFunction");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression2723); 

                	newLeafNode(otherlv_3, grammarAccess.getXExpressionAccess().getCommaKeyword_3());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1327:1: ( (lv_col_4_0= ruleColumnOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1328:1: (lv_col_4_0= ruleColumnOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1328:1: (lv_col_4_0= ruleColumnOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1329:3: lv_col_4_0= ruleColumnOperand
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getColColumnOperandParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnOperand_in_ruleXExpression2743);
            lv_col_4_0=ruleColumnOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"col",
                    		lv_col_4_0, 
                    		"ColumnOperand");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1345:2: (otherlv_5= KEYWORD_4 ( (lv_prm_6_0= ruleXExpressionParams ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==KEYWORD_4) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1346:2: otherlv_5= KEYWORD_4 ( (lv_prm_6_0= ruleXExpressionParams ) )
                    {
                    otherlv_5=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression2757); 

                        	newLeafNode(otherlv_5, grammarAccess.getXExpressionAccess().getCommaKeyword_5_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1350:1: ( (lv_prm_6_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1351:1: (lv_prm_6_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1351:1: (lv_prm_6_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1352:3: lv_prm_6_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression2777);
                    lv_prm_6_0=ruleXExpressionParams();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"prm",
                            		lv_prm_6_0, 
                            		"XExpressionParams");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression2792); 

                	newLeafNode(otherlv_7, grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_6());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleXExpressionParams"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1381:1: entryRuleXExpressionParams returns [EObject current=null] : iv_ruleXExpressionParams= ruleXExpressionParams EOF ;
    public final EObject entryRuleXExpressionParams() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionParams = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1382:2: (iv_ruleXExpressionParams= ruleXExpressionParams EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1383:2: iv_ruleXExpressionParams= ruleXExpressionParams EOF
            {
             newCompositeNode(grammarAccess.getXExpressionParamsRule()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams2826);
            iv_ruleXExpressionParams=ruleXExpressionParams();

            state._fsp--;

             current =iv_ruleXExpressionParams; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionParams2836); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1390:1: ruleXExpressionParams returns [EObject current=null] : (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) ;
    public final EObject ruleXExpressionParams() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_JRParameter_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1393:28: ( (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1394:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1394:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1395:5: this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams2883);
            this_JRParameter_0=ruleJRParameter();

            state._fsp--;


                    current = this_JRParameter_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1403:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==KEYWORD_4) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1403:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1403:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1404:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1409:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    int cnt33=0;
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==KEYWORD_4) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1410:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpressionParams2906); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1414:1: ( (lv_entries_3_0= ruleJRParameter ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1415:1: (lv_entries_3_0= ruleJRParameter )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1415:1: (lv_entries_3_0= ruleJRParameter )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1416:3: lv_entries_3_0= ruleJRParameter
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams2926);
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
                    	    if ( cnt33 >= 1 ) break loop33;
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

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1440:1: entryRuleJRParameter returns [EObject current=null] : iv_ruleJRParameter= ruleJRParameter EOF ;
    public final EObject entryRuleJRParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameter = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1441:2: (iv_ruleJRParameter= ruleJRParameter EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1442:2: iv_ruleJRParameter= ruleJRParameter EOF
            {
             newCompositeNode(grammarAccess.getJRParameterRule()); 
            pushFollow(FOLLOW_ruleJRParameter_in_entryRuleJRParameter2965);
            iv_ruleJRParameter=ruleJRParameter();

            state._fsp--;

             current =iv_ruleJRParameter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRParameter2975); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1449:1: ruleJRParameter returns [EObject current=null] : ( (lv_jrprm_0_0= RULE_ID ) ) ;
    public final EObject ruleJRParameter() throws RecognitionException {
        EObject current = null;

        Token lv_jrprm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1452:28: ( ( (lv_jrprm_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1453:1: ( (lv_jrprm_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1453:1: ( (lv_jrprm_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1454:1: (lv_jrprm_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1454:1: (lv_jrprm_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1455:3: lv_jrprm_0_0= RULE_ID
            {
            lv_jrprm_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJRParameter3016); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1479:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1480:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1481:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression3055);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression3065); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1488:1: ruleExpression returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1491:28: ( ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1492:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1492:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1492:2: ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1492:2: ( (lv_op1_0_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1493:1: (lv_op1_0_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1493:1: (lv_op1_0_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1494:3: lv_op1_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleExpression3111);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1510:2: ( ( ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) )
            int alt36=5;
            switch ( input.LA(1) ) {
            case KEYWORD_52:
            case KEYWORD_39:
                {
                alt36=1;
                }
                break;
            case KEYWORD_43:
            case KEYWORD_27:
                {
                alt36=2;
                }
                break;
            case KEYWORD_53:
            case KEYWORD_37:
                {
                alt36=3;
                }
                break;
            case KEYWORD_44:
            case KEYWORD_29:
                {
                alt36=4;
                }
                break;
            case KEYWORD_12:
            case KEYWORD_13:
            case KEYWORD_14:
            case KEYWORD_8:
            case KEYWORD_9:
            case KEYWORD_10:
                {
                alt36=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1510:3: ( ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1510:3: ( ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1511:1: ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1511:1: ( (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1512:1: (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1512:1: (lv_isnull_1_1= KEYWORD_39 | lv_isnull_1_2= KEYWORD_52 )
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==KEYWORD_39) ) {
                        alt35=1;
                    }
                    else if ( (LA35_0==KEYWORD_52) ) {
                        alt35=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 0, input);

                        throw nvae;
                    }
                    switch (alt35) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1513:3: lv_isnull_1_1= KEYWORD_39
                            {
                            lv_isnull_1_1=(Token)match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_ruleExpression3133); 

                                    newLeafNode(lv_isnull_1_1, grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "isnull", lv_isnull_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1526:8: lv_isnull_1_2= KEYWORD_52
                            {
                            lv_isnull_1_2=(Token)match(input,KEYWORD_52,FOLLOW_KEYWORD_52_in_ruleExpression3161); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1543:6: ( (lv_in_2_0= ruleInOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1543:6: ( (lv_in_2_0= ruleInOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1544:1: (lv_in_2_0= ruleInOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1544:1: (lv_in_2_0= ruleInOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1545:3: lv_in_2_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleInOperator_in_ruleExpression3202);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1562:6: ( (lv_between_3_0= ruleBetween ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1562:6: ( (lv_between_3_0= ruleBetween ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1563:1: (lv_between_3_0= ruleBetween )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1563:1: (lv_between_3_0= ruleBetween )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1564:3: lv_between_3_0= ruleBetween
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBetween_in_ruleExpression3229);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1581:6: ( (lv_like_4_0= ruleLike ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1581:6: ( (lv_like_4_0= ruleLike ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1582:1: (lv_like_4_0= ruleLike )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1582:1: (lv_like_4_0= ruleLike )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1583:3: lv_like_4_0= ruleLike
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleLike_in_ruleExpression3256);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1600:6: ( (lv_comp_5_0= ruleComparison ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1600:6: ( (lv_comp_5_0= ruleComparison ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1601:1: (lv_comp_5_0= ruleComparison )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1601:1: (lv_comp_5_0= ruleComparison )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1602:3: lv_comp_5_0= ruleComparison
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleComparison_in_ruleExpression3283);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1626:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1627:2: (iv_ruleComparison= ruleComparison EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1628:2: iv_ruleComparison= ruleComparison EOF
            {
             newCompositeNode(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_ruleComparison_in_entryRuleComparison3319);
            iv_ruleComparison=ruleComparison();

            state._fsp--;

             current =iv_ruleComparison; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleComparison3329); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1635:1: ruleComparison returns [EObject current=null] : ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) ) ;
    public final EObject ruleComparison() throws RecognitionException {
        EObject current = null;

        Token lv_operator_0_1=null;
        Token lv_operator_0_2=null;
        Token lv_operator_0_3=null;
        Token lv_operator_0_4=null;
        Token lv_operator_0_5=null;
        Token lv_operator_0_6=null;
        EObject lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1638:28: ( ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1639:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1639:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1639:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 ) ) ) ( (lv_op2_1_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1639:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1640:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1640:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1641:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1641:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_14 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_12 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_13 )
            int alt37=6;
            switch ( input.LA(1) ) {
            case KEYWORD_10:
                {
                alt37=1;
                }
                break;
            case KEYWORD_14:
                {
                alt37=2;
                }
                break;
            case KEYWORD_8:
                {
                alt37=3;
                }
                break;
            case KEYWORD_12:
                {
                alt37=4;
                }
                break;
            case KEYWORD_9:
                {
                alt37=5;
                }
                break;
            case KEYWORD_13:
                {
                alt37=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1642:3: lv_operator_0_1= KEYWORD_10
                    {
                    lv_operator_0_1=(Token)match(input,KEYWORD_10,FOLLOW_KEYWORD_10_in_ruleComparison3375); 

                            newLeafNode(lv_operator_0_1, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1655:8: lv_operator_0_2= KEYWORD_14
                    {
                    lv_operator_0_2=(Token)match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_ruleComparison3403); 

                            newLeafNode(lv_operator_0_2, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1668:8: lv_operator_0_3= KEYWORD_8
                    {
                    lv_operator_0_3=(Token)match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_ruleComparison3431); 

                            newLeafNode(lv_operator_0_3, grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1681:8: lv_operator_0_4= KEYWORD_12
                    {
                    lv_operator_0_4=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleComparison3459); 

                            newLeafNode(lv_operator_0_4, grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_4, null);
                    	    

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1694:8: lv_operator_0_5= KEYWORD_9
                    {
                    lv_operator_0_5=(Token)match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_ruleComparison3487); 

                            newLeafNode(lv_operator_0_5, grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_5, null);
                    	    

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1707:8: lv_operator_0_6= KEYWORD_13
                    {
                    lv_operator_0_6=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleComparison3515); 

                            newLeafNode(lv_operator_0_6, grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_6, null);
                    	    

                    }
                    break;

            }


            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1723:2: ( (lv_op2_1_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1724:1: (lv_op2_1_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1724:1: (lv_op2_1_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1725:3: lv_op2_1_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleComparison3550);
            lv_op2_1_0=ruleOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getComparisonRule());
            	        }
                   		set(
                   			current, 
                   			"op2",
                    		lv_op2_1_0, 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1749:1: entryRuleLike returns [EObject current=null] : iv_ruleLike= ruleLike EOF ;
    public final EObject entryRuleLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLike = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1750:2: (iv_ruleLike= ruleLike EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1751:2: iv_ruleLike= ruleLike EOF
            {
             newCompositeNode(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_ruleLike_in_entryRuleLike3585);
            iv_ruleLike=ruleLike();

            state._fsp--;

             current =iv_ruleLike; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLike3595); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1758:1: ruleLike returns [EObject current=null] : ( ( ( (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 ) ) ) ( (lv_op2_1_0= ruleStringOperand ) ) ) ;
    public final EObject ruleLike() throws RecognitionException {
        EObject current = null;

        Token lv_opLike_0_1=null;
        Token lv_opLike_0_2=null;
        AntlrDatatypeRuleToken lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1761:28: ( ( ( ( (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 ) ) ) ( (lv_op2_1_0= ruleStringOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1762:1: ( ( ( (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 ) ) ) ( (lv_op2_1_0= ruleStringOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1762:1: ( ( ( (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 ) ) ) ( (lv_op2_1_0= ruleStringOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1762:2: ( ( (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 ) ) ) ( (lv_op2_1_0= ruleStringOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1762:2: ( ( (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1763:1: ( (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1763:1: ( (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1764:1: (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1764:1: (lv_opLike_0_1= KEYWORD_29 | lv_opLike_0_2= KEYWORD_44 )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==KEYWORD_29) ) {
                alt38=1;
            }
            else if ( (LA38_0==KEYWORD_44) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1765:3: lv_opLike_0_1= KEYWORD_29
                    {
                    lv_opLike_0_1=(Token)match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_ruleLike3641); 

                            newLeafNode(lv_opLike_0_1, grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLikeRule());
                    	        }
                           		setWithLastConsumed(current, "opLike", lv_opLike_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1778:8: lv_opLike_0_2= KEYWORD_44
                    {
                    lv_opLike_0_2=(Token)match(input,KEYWORD_44,FOLLOW_KEYWORD_44_in_ruleLike3669); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1794:2: ( (lv_op2_1_0= ruleStringOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1795:1: (lv_op2_1_0= ruleStringOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1795:1: (lv_op2_1_0= ruleStringOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1796:3: lv_op2_1_0= ruleStringOperand
            {
             
            	        newCompositeNode(grammarAccess.getLikeAccess().getOp2StringOperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleStringOperand_in_ruleLike3704);
            lv_op2_1_0=ruleStringOperand();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLikeRule());
            	        }
                   		set(
                   			current, 
                   			"op2",
                    		lv_op2_1_0, 
                    		"StringOperand");
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


    // $ANTLR start "entryRuleBetween"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1820:1: entryRuleBetween returns [EObject current=null] : iv_ruleBetween= ruleBetween EOF ;
    public final EObject entryRuleBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBetween = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1821:2: (iv_ruleBetween= ruleBetween EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1822:2: iv_ruleBetween= ruleBetween EOF
            {
             newCompositeNode(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_ruleBetween_in_entryRuleBetween3739);
            iv_ruleBetween=ruleBetween();

            state._fsp--;

             current =iv_ruleBetween; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBetween3749); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1829:1: ruleBetween returns [EObject current=null] : ( ( ( (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_22 ( (lv_op3_3_0= ruleOperand ) ) ) ;
    public final EObject ruleBetween() throws RecognitionException {
        EObject current = null;

        Token lv_opBetween_0_1=null;
        Token lv_opBetween_0_2=null;
        Token otherlv_2=null;
        EObject lv_op2_1_0 = null;

        EObject lv_op3_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1832:28: ( ( ( ( (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_22 ( (lv_op3_3_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1833:1: ( ( ( (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_22 ( (lv_op3_3_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1833:1: ( ( ( (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_22 ( (lv_op3_3_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1833:2: ( ( (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_22 ( (lv_op3_3_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1833:2: ( ( (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1834:1: ( (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1834:1: ( (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1835:1: (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1835:1: (lv_opBetween_0_1= KEYWORD_37 | lv_opBetween_0_2= KEYWORD_53 )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==KEYWORD_37) ) {
                alt39=1;
            }
            else if ( (LA39_0==KEYWORD_53) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1836:3: lv_opBetween_0_1= KEYWORD_37
                    {
                    lv_opBetween_0_1=(Token)match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_ruleBetween3795); 

                            newLeafNode(lv_opBetween_0_1, grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBetweenRule());
                    	        }
                           		setWithLastConsumed(current, "opBetween", lv_opBetween_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1849:8: lv_opBetween_0_2= KEYWORD_53
                    {
                    lv_opBetween_0_2=(Token)match(input,KEYWORD_53,FOLLOW_KEYWORD_53_in_ruleBetween3823); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1865:2: ( (lv_op2_1_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1866:1: (lv_op2_1_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1866:1: (lv_op2_1_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1867:3: lv_op2_1_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleBetween3858);
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

            otherlv_2=(Token)match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_ruleBetween3871); 

                	newLeafNode(otherlv_2, grammarAccess.getBetweenAccess().getANDKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1888:1: ( (lv_op3_3_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1889:1: (lv_op3_3_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1889:1: (lv_op3_3_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1890:3: lv_op3_3_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp3OperandParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleBetween3891);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1914:1: entryRuleInOperator returns [EObject current=null] : iv_ruleInOperator= ruleInOperator EOF ;
    public final EObject entryRuleInOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOperator = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1915:2: (iv_ruleInOperator= ruleInOperator EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1916:2: iv_ruleInOperator= ruleInOperator EOF
            {
             newCompositeNode(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_ruleInOperator_in_entryRuleInOperator3926);
            iv_ruleInOperator=ruleInOperator();

            state._fsp--;

             current =iv_ruleInOperator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInOperator3936); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1923:1: ruleInOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandList ) ) ) otherlv_4= KEYWORD_2 ) ;
    public final EObject ruleInOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token otherlv_4=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1926:28: ( ( () ( ( (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandList ) ) ) otherlv_4= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1927:1: ( () ( ( (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandList ) ) ) otherlv_4= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1927:1: ( () ( ( (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandList ) ) ) otherlv_4= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1927:2: () ( ( (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandList ) ) ) otherlv_4= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1927:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1928:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInOperatorAccess().getInOperAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1933:2: ( ( (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1934:1: ( (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1934:1: ( (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1935:1: (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1935:1: (lv_op_1_1= KEYWORD_43 | lv_op_1_2= KEYWORD_27 )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==KEYWORD_43) ) {
                alt40=1;
            }
            else if ( (LA40_0==KEYWORD_27) ) {
                alt40=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1936:3: lv_op_1_1= KEYWORD_43
                    {
                    lv_op_1_1=(Token)match(input,KEYWORD_43,FOLLOW_KEYWORD_43_in_ruleInOperator3991); 

                            newLeafNode(lv_op_1_1, grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getInOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1949:8: lv_op_1_2= KEYWORD_27
                    {
                    lv_op_1_2=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleInOperator4019); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1965:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandList ) ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==KEYWORD_1) ) {
                alt41=1;
            }
            else if ( (LA41_0==KEYWORD_24||LA41_0==KEYWORD_20||(LA41_0>=RULE_SIGNED_INT && LA41_0<=RULE_SIGNED_DOUBLE)||LA41_0==RULE_STRING) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1965:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1965:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1966:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1966:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1967:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleInOperator4055);
                    lv_subquery_2_0=ruleSubQueryOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInOperatorRule());
                    	        }
                           		set(
                           			current, 
                           			"subquery",
                            		lv_subquery_2_0, 
                            		"SubQueryOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1984:6: ( (lv_opList_3_0= ruleOperandList ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1984:6: ( (lv_opList_3_0= ruleOperandList ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1985:1: (lv_opList_3_0= ruleOperandList )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1985:1: (lv_opList_3_0= ruleOperandList )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1986:3: lv_opList_3_0= ruleOperandList
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getOpListOperandListParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandList_in_ruleInOperator4082);
                    lv_opList_3_0=ruleOperandList();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInOperatorRule());
                    	        }
                           		set(
                           			current, 
                           			"opList",
                            		lv_opList_3_0, 
                            		"OperandList");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleInOperator4096); 

                	newLeafNode(otherlv_4, grammarAccess.getInOperatorAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2015:1: entryRuleOperandList returns [EObject current=null] : iv_ruleOperandList= ruleOperandList EOF ;
    public final EObject entryRuleOperandList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandList = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2016:2: (iv_ruleOperandList= ruleOperandList EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2017:2: iv_ruleOperandList= ruleOperandList EOF
            {
             newCompositeNode(grammarAccess.getOperandListRule()); 
            pushFollow(FOLLOW_ruleOperandList_in_entryRuleOperandList4130);
            iv_ruleOperandList=ruleOperandList();

            state._fsp--;

             current =iv_ruleOperandList; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandList4140); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2024:1: ruleOperandList returns [EObject current=null] : (this_XOperandFragment_0= ruleXOperandFragment ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) ) )+ )? ) ;
    public final EObject ruleOperandList() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XOperandFragment_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2027:28: ( (this_XOperandFragment_0= ruleXOperandFragment ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2028:1: (this_XOperandFragment_0= ruleXOperandFragment ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2028:1: (this_XOperandFragment_0= ruleXOperandFragment ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2029:5: this_XOperandFragment_0= ruleXOperandFragment ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOperandListAccess().getXOperandFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleXOperandFragment_in_ruleOperandList4187);
            this_XOperandFragment_0=ruleXOperandFragment();

            state._fsp--;


                    current = this_XOperandFragment_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2037:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) ) )+ )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==KEYWORD_4) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2037:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2037:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2038:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2043:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) ) )+
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2044:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleXOperandFragment ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOperandList4210); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2048:1: ( (lv_entries_3_0= ruleXOperandFragment ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2049:1: (lv_entries_3_0= ruleXOperandFragment )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2049:1: (lv_entries_3_0= ruleXOperandFragment )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2050:3: lv_entries_3_0= ruleXOperandFragment
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOperandListAccess().getEntriesXOperandFragmentParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleXOperandFragment_in_ruleOperandList4230);
                    	    lv_entries_3_0=ruleXOperandFragment();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getOperandListRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"XOperandFragment");
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
    // $ANTLR end "ruleOperandList"


    // $ANTLR start "entryRuleOperand"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2074:1: entryRuleOperand returns [EObject current=null] : iv_ruleOperand= ruleOperand EOF ;
    public final EObject entryRuleOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2075:2: (iv_ruleOperand= ruleOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2076:2: iv_ruleOperand= ruleOperand EOF
            {
             newCompositeNode(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_ruleOperand_in_entryRuleOperand4269);
            iv_ruleOperand=ruleOperand();

            state._fsp--;

             current =iv_ruleOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperand4279); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2083:1: ruleOperand returns [EObject current=null] : (this_OperandFragment_0= ruleOperandFragment ( () ( (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) ) )+ )? ) ;
    public final EObject ruleOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token this_STAR_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject this_OperandFragment_0 = null;

        EObject lv_entries_7_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2086:28: ( (this_OperandFragment_0= ruleOperandFragment ( () ( (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2087:1: (this_OperandFragment_0= ruleOperandFragment ( () ( (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2087:1: (this_OperandFragment_0= ruleOperandFragment ( () ( (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2088:5: this_OperandFragment_0= ruleOperandFragment ( () ( (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOperandAccess().getOperandFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand4326);
            this_OperandFragment_0=ruleOperandFragment();

            state._fsp--;


                    current = this_OperandFragment_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2096:1: ( () ( (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) ) )+ )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==KEYWORD_19||LA46_0==KEYWORD_3||LA46_0==KEYWORD_5||LA46_0==KEYWORD_7||LA46_0==RULE_STAR) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2096:2: () ( (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2096:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2097:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOperandAccess().getOperandsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2102:2: ( (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) ) )+
                    int cnt45=0;
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==KEYWORD_19||LA45_0==KEYWORD_3||LA45_0==KEYWORD_5||LA45_0==KEYWORD_7||LA45_0==RULE_STAR) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2102:3: (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 ) ( (lv_entries_7_0= ruleOperandFragment ) )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2102:3: (otherlv_2= KEYWORD_3 | otherlv_3= KEYWORD_5 | this_STAR_4= RULE_STAR | otherlv_5= KEYWORD_7 | otherlv_6= KEYWORD_19 )
                    	    int alt44=5;
                    	    switch ( input.LA(1) ) {
                    	    case KEYWORD_3:
                    	        {
                    	        alt44=1;
                    	        }
                    	        break;
                    	    case KEYWORD_5:
                    	        {
                    	        alt44=2;
                    	        }
                    	        break;
                    	    case RULE_STAR:
                    	        {
                    	        alt44=3;
                    	        }
                    	        break;
                    	    case KEYWORD_7:
                    	        {
                    	        alt44=4;
                    	        }
                    	        break;
                    	    case KEYWORD_19:
                    	        {
                    	        alt44=5;
                    	        }
                    	        break;
                    	    default:
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 44, 0, input);

                    	        throw nvae;
                    	    }

                    	    switch (alt44) {
                    	        case 1 :
                    	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2103:2: otherlv_2= KEYWORD_3
                    	            {
                    	            otherlv_2=(Token)match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_ruleOperand4350); 

                    	                	newLeafNode(otherlv_2, grammarAccess.getOperandAccess().getPlusSignKeyword_1_1_0_0());
                    	                

                    	            }
                    	            break;
                    	        case 2 :
                    	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2109:2: otherlv_3= KEYWORD_5
                    	            {
                    	            otherlv_3=(Token)match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_ruleOperand4368); 

                    	                	newLeafNode(otherlv_3, grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_1_0_1());
                    	                

                    	            }
                    	            break;
                    	        case 3 :
                    	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2114:6: this_STAR_4= RULE_STAR
                    	            {
                    	            this_STAR_4=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperand4384); 
                    	             
                    	                newLeafNode(this_STAR_4, grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_1_0_2()); 
                    	                

                    	            }
                    	            break;
                    	        case 4 :
                    	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2120:2: otherlv_5= KEYWORD_7
                    	            {
                    	            otherlv_5=(Token)match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_ruleOperand4402); 

                    	                	newLeafNode(otherlv_5, grammarAccess.getOperandAccess().getSolidusKeyword_1_1_0_3());
                    	                

                    	            }
                    	            break;
                    	        case 5 :
                    	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2126:2: otherlv_6= KEYWORD_19
                    	            {
                    	            otherlv_6=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleOperand4420); 

                    	                	newLeafNode(otherlv_6, grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_1_0_4());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2130:2: ( (lv_entries_7_0= ruleOperandFragment ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2131:1: (lv_entries_7_0= ruleOperandFragment )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2131:1: (lv_entries_7_0= ruleOperandFragment )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2132:3: lv_entries_7_0= ruleOperandFragment
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOperandAccess().getEntriesOperandFragmentParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand4441);
                    	    lv_entries_7_0=ruleOperandFragment();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getOperandRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_7_0, 
                    	            		"OperandFragment");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt45 >= 1 ) break loop45;
                                EarlyExitException eee =
                                    new EarlyExitException(45, input);
                                throw eee;
                        }
                        cnt45++;
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
    // $ANTLR end "ruleOperand"


    // $ANTLR start "entryRuleOperandFragment"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2156:1: entryRuleOperandFragment returns [EObject current=null] : iv_ruleOperandFragment= ruleOperandFragment EOF ;
    public final EObject entryRuleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2157:2: (iv_ruleOperandFragment= ruleOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2158:2: iv_ruleOperandFragment= ruleOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment4480);
            iv_ruleOperandFragment=ruleOperandFragment();

            state._fsp--;

             current =iv_ruleOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFragment4490); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2165:1: ruleOperandFragment returns [EObject current=null] : ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) ) ;
    public final EObject ruleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_column_0_0 = null;

        EObject lv_xop_1_0 = null;

        EObject lv_subq_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2168:28: ( ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2169:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2169:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) )
            int alt47=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt47=1;
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
                alt47=2;
                }
                break;
            case KEYWORD_1:
                {
                alt47=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }

            switch (alt47) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2169:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2169:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2170:1: (lv_column_0_0= ruleColumnOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2170:1: (lv_column_0_0= ruleColumnOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2171:3: lv_column_0_0= ruleColumnOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnOperand_in_ruleOperandFragment4536);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2188:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2188:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2189:1: (lv_xop_1_0= ruleXOperandFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2189:1: (lv_xop_1_0= ruleXOperandFragment )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2190:3: lv_xop_1_0= ruleXOperandFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXOperandFragment_in_ruleOperandFragment4563);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2207:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2207:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2208:1: (lv_subq_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2208:1: (lv_subq_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2209:3: lv_subq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment4590);
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

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleXOperandFragment"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2233:1: entryRuleXOperandFragment returns [EObject current=null] : iv_ruleXOperandFragment= ruleXOperandFragment EOF ;
    public final EObject entryRuleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2234:2: (iv_ruleXOperandFragment= ruleXOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2235:2: iv_ruleXOperandFragment= ruleXOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment4625);
            iv_ruleXOperandFragment=ruleXOperandFragment();

            state._fsp--;

             current =iv_ruleXOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOperandFragment4635); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2242:1: ruleXOperandFragment returns [EObject current=null] : ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) ) ;
    public final EObject ruleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_param_0_0 = null;

        EObject lv_eparam_1_0 = null;

        EObject lv_scalar_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2245:28: ( ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2246:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2246:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) )
            int alt48=3;
            switch ( input.LA(1) ) {
            case KEYWORD_20:
                {
                alt48=1;
                }
                break;
            case KEYWORD_24:
                {
                alt48=2;
                }
                break;
            case RULE_SIGNED_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING:
                {
                alt48=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }

            switch (alt48) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2246:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2246:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2247:1: (lv_param_0_0= ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2247:1: (lv_param_0_0= ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2248:3: lv_param_0_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParameterOperand_in_ruleXOperandFragment4681);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2265:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2265:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2266:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2266:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2267:3: lv_eparam_1_0= ruleExclamationParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment4708);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2284:6: ( (lv_scalar_2_0= ruleScalarOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2284:6: ( (lv_scalar_2_0= ruleScalarOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2285:1: (lv_scalar_2_0= ruleScalarOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2285:1: (lv_scalar_2_0= ruleScalarOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2286:3: lv_scalar_2_0= ruleScalarOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getScalarScalarOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleScalarOperand_in_ruleXOperandFragment4735);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2310:1: entryRuleParameterOperand returns [EObject current=null] : iv_ruleParameterOperand= ruleParameterOperand EOF ;
    public final EObject entryRuleParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2311:2: (iv_ruleParameterOperand= ruleParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2312:2: iv_ruleParameterOperand= ruleParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand4770);
            iv_ruleParameterOperand=ruleParameterOperand();

            state._fsp--;

             current =iv_ruleParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameterOperand4780); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2319:1: ruleParameterOperand returns [EObject current=null] : ( () otherlv_1= KEYWORD_20 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11 ) ;
    public final EObject ruleParameterOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_prm_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2322:28: ( ( () otherlv_1= KEYWORD_20 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2323:1: ( () otherlv_1= KEYWORD_20 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2323:1: ( () otherlv_1= KEYWORD_20 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2323:2: () otherlv_1= KEYWORD_20 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2323:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2324:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getParameterOperandAccess().getPOperandAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleParameterOperand4827); 

                	newLeafNode(otherlv_1, grammarAccess.getParameterOperandAccess().getPKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2334:1: ( (lv_prm_2_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2335:1: (lv_prm_2_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2335:1: (lv_prm_2_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2336:3: lv_prm_2_0= RULE_ID
            {
            lv_prm_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleParameterOperand4843); 

            			newLeafNode(lv_prm_2_0, grammarAccess.getParameterOperandAccess().getPrmIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getParameterOperandRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"prm",
                    		lv_prm_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleParameterOperand4861); 

                	newLeafNode(otherlv_3, grammarAccess.getParameterOperandAccess().getRightCurlyBracketKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2365:1: entryRuleExclamationParameterOperand returns [EObject current=null] : iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF ;
    public final EObject entryRuleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclamationParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2366:2: (iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2367:2: iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand4895);
            iv_ruleExclamationParameterOperand=ruleExclamationParameterOperand();

            state._fsp--;

             current =iv_ruleExclamationParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclamationParameterOperand4905); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2374:1: ruleExclamationParameterOperand returns [EObject current=null] : ( () otherlv_1= KEYWORD_24 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11 ) ;
    public final EObject ruleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_prm_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2377:28: ( ( () otherlv_1= KEYWORD_24 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2378:1: ( () otherlv_1= KEYWORD_24 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2378:1: ( () otherlv_1= KEYWORD_24 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2378:2: () otherlv_1= KEYWORD_24 ( (lv_prm_2_0= RULE_ID ) ) otherlv_3= KEYWORD_11
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2378:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2379:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExclamationParameterOperandAccess().getExpPperandAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_ruleExclamationParameterOperand4952); 

                	newLeafNode(otherlv_1, grammarAccess.getExclamationParameterOperandAccess().getPKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2389:1: ( (lv_prm_2_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2390:1: (lv_prm_2_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2390:1: (lv_prm_2_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2391:3: lv_prm_2_0= RULE_ID
            {
            lv_prm_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleExclamationParameterOperand4968); 

            			newLeafNode(lv_prm_2_0, grammarAccess.getExclamationParameterOperandAccess().getPrmIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getExclamationParameterOperandRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"prm",
                    		lv_prm_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleExclamationParameterOperand4986); 

                	newLeafNode(otherlv_3, grammarAccess.getExclamationParameterOperandAccess().getRightCurlyBracketKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2420:1: entryRuleColumnOperand returns [EObject current=null] : iv_ruleColumnOperand= ruleColumnOperand EOF ;
    public final EObject entryRuleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2421:2: (iv_ruleColumnOperand= ruleColumnOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2422:2: iv_ruleColumnOperand= ruleColumnOperand EOF
            {
             newCompositeNode(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand5020);
            iv_ruleColumnOperand=ruleColumnOperand();

            state._fsp--;

             current =iv_ruleColumnOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOperand5030); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:1: ruleColumnOperand returns [EObject current=null] : ( (lv_cfull_0_0= ruleColumnFull ) ) ;
    public final EObject ruleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject lv_cfull_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2432:28: ( ( (lv_cfull_0_0= ruleColumnFull ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2433:1: ( (lv_cfull_0_0= ruleColumnFull ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2433:1: ( (lv_cfull_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2434:1: (lv_cfull_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2434:1: (lv_cfull_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2435:3: lv_cfull_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOperand5075);
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


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2459:1: entryRuleSubQueryOperand returns [EObject current=null] : iv_ruleSubQueryOperand= ruleSubQueryOperand EOF ;
    public final EObject entryRuleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubQueryOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2460:2: (iv_ruleSubQueryOperand= ruleSubQueryOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2461:2: iv_ruleSubQueryOperand= ruleSubQueryOperand EOF
            {
             newCompositeNode(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand5109);
            iv_ruleSubQueryOperand=ruleSubQueryOperand();

            state._fsp--;

             current =iv_ruleSubQueryOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubQueryOperand5119); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2468:1: ruleSubQueryOperand returns [EObject current=null] : ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelect ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_sel_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2471:28: ( ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelect ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2472:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelect ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2472:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelect ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2472:2: () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelect ) ) otherlv_3= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2472:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2473:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSubQueryOperandAccess().getSubqueryAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleSubQueryOperand5166); 

                	newLeafNode(otherlv_1, grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2483:1: ( (lv_sel_2_0= ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2484:1: (lv_sel_2_0= ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2484:1: (lv_sel_2_0= ruleSelect )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2485:3: lv_sel_2_0= ruleSelect
            {
             
            	        newCompositeNode(grammarAccess.getSubQueryOperandAccess().getSelSelectParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelect_in_ruleSubQueryOperand5186);
            lv_sel_2_0=ruleSelect();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSubQueryOperandRule());
            	        }
                   		set(
                   			current, 
                   			"sel",
                    		lv_sel_2_0, 
                    		"Select");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleSubQueryOperand5199); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2514:1: entryRuleScalarOperand returns [EObject current=null] : iv_ruleScalarOperand= ruleScalarOperand EOF ;
    public final EObject entryRuleScalarOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2515:2: (iv_ruleScalarOperand= ruleScalarOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2516:2: iv_ruleScalarOperand= ruleScalarOperand EOF
            {
             newCompositeNode(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand5233);
            iv_ruleScalarOperand=ruleScalarOperand();

            state._fsp--;

             current =iv_ruleScalarOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarOperand5243); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2523:1: ruleScalarOperand returns [EObject current=null] : ( ( (lv_soint_0_0= RULE_SIGNED_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2526:28: ( ( ( (lv_soint_0_0= RULE_SIGNED_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2527:1: ( ( (lv_soint_0_0= RULE_SIGNED_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2527:1: ( ( (lv_soint_0_0= RULE_SIGNED_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) )
            int alt49=6;
            switch ( input.LA(1) ) {
            case RULE_SIGNED_INT:
                {
                alt49=1;
                }
                break;
            case RULE_STRING:
                {
                alt49=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt49=3;
                }
                break;
            case RULE_DATE:
                {
                alt49=4;
                }
                break;
            case RULE_TIME:
                {
                alt49=5;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt49=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }

            switch (alt49) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2527:2: ( (lv_soint_0_0= RULE_SIGNED_INT ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2527:2: ( (lv_soint_0_0= RULE_SIGNED_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2528:1: (lv_soint_0_0= RULE_SIGNED_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2528:1: (lv_soint_0_0= RULE_SIGNED_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2529:3: lv_soint_0_0= RULE_SIGNED_INT
                    {
                    lv_soint_0_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_RULE_SIGNED_INT_in_ruleScalarOperand5285); 

                    			newLeafNode(lv_soint_0_0, grammarAccess.getScalarOperandAccess().getSointSIGNED_INTTerminalRuleCall_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"soint",
                            		lv_soint_0_0, 
                            		"SIGNED_INT");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2546:6: ( (lv_sostr_1_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2546:6: ( (lv_sostr_1_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2547:1: (lv_sostr_1_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2547:1: (lv_sostr_1_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2548:3: lv_sostr_1_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleScalarOperand5317);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2565:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2565:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2566:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2566:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2567:3: lv_sodbl_2_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_2_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand5340); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2584:6: ( (lv_sodate_3_0= RULE_DATE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2584:6: ( (lv_sodate_3_0= RULE_DATE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2585:1: (lv_sodate_3_0= RULE_DATE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2585:1: (lv_sodate_3_0= RULE_DATE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2586:3: lv_sodate_3_0= RULE_DATE
                    {
                    lv_sodate_3_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleScalarOperand5368); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2603:6: ( (lv_sotime_4_0= RULE_TIME ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2603:6: ( (lv_sotime_4_0= RULE_TIME ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2604:1: (lv_sotime_4_0= RULE_TIME )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2604:1: (lv_sotime_4_0= RULE_TIME )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2605:3: lv_sotime_4_0= RULE_TIME
                    {
                    lv_sotime_4_0=(Token)match(input,RULE_TIME,FOLLOW_RULE_TIME_in_ruleScalarOperand5396); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2622:6: ( (lv_sodt_5_0= RULE_TIMESTAMP ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2622:6: ( (lv_sodt_5_0= RULE_TIMESTAMP ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2623:1: (lv_sodt_5_0= RULE_TIMESTAMP )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2623:1: (lv_sodt_5_0= RULE_TIMESTAMP )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2624:3: lv_sodt_5_0= RULE_TIMESTAMP
                    {
                    lv_sodt_5_0=(Token)match(input,RULE_TIMESTAMP,FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand5424); 

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


    // $ANTLR start "entryRuleStringOperand"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2648:1: entryRuleStringOperand returns [String current=null] : iv_ruleStringOperand= ruleStringOperand EOF ;
    public final String entryRuleStringOperand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2649:1: (iv_ruleStringOperand= ruleStringOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2650:2: iv_ruleStringOperand= ruleStringOperand EOF
            {
             newCompositeNode(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_ruleStringOperand_in_entryRuleStringOperand5465);
            iv_ruleStringOperand=ruleStringOperand();

            state._fsp--;

             current =iv_ruleStringOperand.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringOperand5476); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2657:1: ruleStringOperand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleStringOperand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2661:6: (this_STRING_0= RULE_STRING )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2662:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringOperand5515); 

            		current.merge(this_STRING_0);
                
             
                newLeafNode(this_STRING_0, grammarAccess.getStringOperandAccess().getSTRINGTerminalRuleCall()); 
                

            }

             leaveRule();
                
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


    // $ANTLR start "ruleXFunction"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2677:1: ruleXFunction returns [Enumerator current=null] : ( (enumLiteral_0= KEYWORD_16 ) | (enumLiteral_1= KEYWORD_32 ) | (enumLiteral_2= KEYWORD_30 ) | (enumLiteral_3= KEYWORD_45 ) | (enumLiteral_4= KEYWORD_28 ) | (enumLiteral_5= KEYWORD_38 ) | (enumLiteral_6= KEYWORD_31 ) | (enumLiteral_7= KEYWORD_48 ) | (enumLiteral_8= KEYWORD_37 ) | (enumLiteral_9= KEYWORD_49 ) | (enumLiteral_10= KEYWORD_47 ) | (enumLiteral_11= KEYWORD_40 ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2679:28: ( ( (enumLiteral_0= KEYWORD_16 ) | (enumLiteral_1= KEYWORD_32 ) | (enumLiteral_2= KEYWORD_30 ) | (enumLiteral_3= KEYWORD_45 ) | (enumLiteral_4= KEYWORD_28 ) | (enumLiteral_5= KEYWORD_38 ) | (enumLiteral_6= KEYWORD_31 ) | (enumLiteral_7= KEYWORD_48 ) | (enumLiteral_8= KEYWORD_37 ) | (enumLiteral_9= KEYWORD_49 ) | (enumLiteral_10= KEYWORD_47 ) | (enumLiteral_11= KEYWORD_40 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2680:1: ( (enumLiteral_0= KEYWORD_16 ) | (enumLiteral_1= KEYWORD_32 ) | (enumLiteral_2= KEYWORD_30 ) | (enumLiteral_3= KEYWORD_45 ) | (enumLiteral_4= KEYWORD_28 ) | (enumLiteral_5= KEYWORD_38 ) | (enumLiteral_6= KEYWORD_31 ) | (enumLiteral_7= KEYWORD_48 ) | (enumLiteral_8= KEYWORD_37 ) | (enumLiteral_9= KEYWORD_49 ) | (enumLiteral_10= KEYWORD_47 ) | (enumLiteral_11= KEYWORD_40 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2680:1: ( (enumLiteral_0= KEYWORD_16 ) | (enumLiteral_1= KEYWORD_32 ) | (enumLiteral_2= KEYWORD_30 ) | (enumLiteral_3= KEYWORD_45 ) | (enumLiteral_4= KEYWORD_28 ) | (enumLiteral_5= KEYWORD_38 ) | (enumLiteral_6= KEYWORD_31 ) | (enumLiteral_7= KEYWORD_48 ) | (enumLiteral_8= KEYWORD_37 ) | (enumLiteral_9= KEYWORD_49 ) | (enumLiteral_10= KEYWORD_47 ) | (enumLiteral_11= KEYWORD_40 ) )
            int alt50=12;
            switch ( input.LA(1) ) {
            case KEYWORD_16:
                {
                alt50=1;
                }
                break;
            case KEYWORD_32:
                {
                alt50=2;
                }
                break;
            case KEYWORD_30:
                {
                alt50=3;
                }
                break;
            case KEYWORD_45:
                {
                alt50=4;
                }
                break;
            case KEYWORD_28:
                {
                alt50=5;
                }
                break;
            case KEYWORD_38:
                {
                alt50=6;
                }
                break;
            case KEYWORD_31:
                {
                alt50=7;
                }
                break;
            case KEYWORD_48:
                {
                alt50=8;
                }
                break;
            case KEYWORD_37:
                {
                alt50=9;
                }
                break;
            case KEYWORD_49:
                {
                alt50=10;
                }
                break;
            case KEYWORD_47:
                {
                alt50=11;
                }
                break;
            case KEYWORD_40:
                {
                alt50=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }

            switch (alt50) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2680:2: (enumLiteral_0= KEYWORD_16 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2680:2: (enumLiteral_0= KEYWORD_16 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2680:7: enumLiteral_0= KEYWORD_16
                    {
                    enumLiteral_0=(Token)match(input,KEYWORD_16,FOLLOW_KEYWORD_16_in_ruleXFunction5576); 

                            current = grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2686:6: (enumLiteral_1= KEYWORD_32 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2686:6: (enumLiteral_1= KEYWORD_32 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2686:11: enumLiteral_1= KEYWORD_32
                    {
                    enumLiteral_1=(Token)match(input,KEYWORD_32,FOLLOW_KEYWORD_32_in_ruleXFunction5598); 

                            current = grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2692:6: (enumLiteral_2= KEYWORD_30 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2692:6: (enumLiteral_2= KEYWORD_30 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2692:11: enumLiteral_2= KEYWORD_30
                    {
                    enumLiteral_2=(Token)match(input,KEYWORD_30,FOLLOW_KEYWORD_30_in_ruleXFunction5620); 

                            current = grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2698:6: (enumLiteral_3= KEYWORD_45 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2698:6: (enumLiteral_3= KEYWORD_45 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2698:11: enumLiteral_3= KEYWORD_45
                    {
                    enumLiteral_3=(Token)match(input,KEYWORD_45,FOLLOW_KEYWORD_45_in_ruleXFunction5642); 

                            current = grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2704:6: (enumLiteral_4= KEYWORD_28 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2704:6: (enumLiteral_4= KEYWORD_28 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2704:11: enumLiteral_4= KEYWORD_28
                    {
                    enumLiteral_4=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_ruleXFunction5664); 

                            current = grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2710:6: (enumLiteral_5= KEYWORD_38 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2710:6: (enumLiteral_5= KEYWORD_38 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2710:11: enumLiteral_5= KEYWORD_38
                    {
                    enumLiteral_5=(Token)match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_ruleXFunction5686); 

                            current = grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2716:6: (enumLiteral_6= KEYWORD_31 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2716:6: (enumLiteral_6= KEYWORD_31 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2716:11: enumLiteral_6= KEYWORD_31
                    {
                    enumLiteral_6=(Token)match(input,KEYWORD_31,FOLLOW_KEYWORD_31_in_ruleXFunction5708); 

                            current = grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2722:6: (enumLiteral_7= KEYWORD_48 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2722:6: (enumLiteral_7= KEYWORD_48 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2722:11: enumLiteral_7= KEYWORD_48
                    {
                    enumLiteral_7=(Token)match(input,KEYWORD_48,FOLLOW_KEYWORD_48_in_ruleXFunction5730); 

                            current = grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2728:6: (enumLiteral_8= KEYWORD_37 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2728:6: (enumLiteral_8= KEYWORD_37 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2728:11: enumLiteral_8= KEYWORD_37
                    {
                    enumLiteral_8=(Token)match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_ruleXFunction5752); 

                            current = grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2734:6: (enumLiteral_9= KEYWORD_49 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2734:6: (enumLiteral_9= KEYWORD_49 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2734:11: enumLiteral_9= KEYWORD_49
                    {
                    enumLiteral_9=(Token)match(input,KEYWORD_49,FOLLOW_KEYWORD_49_in_ruleXFunction5774); 

                            current = grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2740:6: (enumLiteral_10= KEYWORD_47 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2740:6: (enumLiteral_10= KEYWORD_47 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2740:11: enumLiteral_10= KEYWORD_47
                    {
                    enumLiteral_10=(Token)match(input,KEYWORD_47,FOLLOW_KEYWORD_47_in_ruleXFunction5796); 

                            current = grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2746:6: (enumLiteral_11= KEYWORD_40 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2746:6: (enumLiteral_11= KEYWORD_40 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2746:11: enumLiteral_11= KEYWORD_40
                    {
                    enumLiteral_11=(Token)match(input,KEYWORD_40,FOLLOW_KEYWORD_40_in_ruleXFunction5818); 

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


    // $ANTLR start "ruleJoinType"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2756:1: ruleJoinType returns [Enumerator current=null] : ( (enumLiteral_0= KEYWORD_51 ) | (enumLiteral_1= KEYWORD_55 ) | (enumLiteral_2= KEYWORD_56 ) | (enumLiteral_3= KEYWORD_54 ) | (enumLiteral_4= KEYWORD_50 ) ) ;
    public final Enumerator ruleJoinType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2758:28: ( ( (enumLiteral_0= KEYWORD_51 ) | (enumLiteral_1= KEYWORD_55 ) | (enumLiteral_2= KEYWORD_56 ) | (enumLiteral_3= KEYWORD_54 ) | (enumLiteral_4= KEYWORD_50 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2759:1: ( (enumLiteral_0= KEYWORD_51 ) | (enumLiteral_1= KEYWORD_55 ) | (enumLiteral_2= KEYWORD_56 ) | (enumLiteral_3= KEYWORD_54 ) | (enumLiteral_4= KEYWORD_50 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2759:1: ( (enumLiteral_0= KEYWORD_51 ) | (enumLiteral_1= KEYWORD_55 ) | (enumLiteral_2= KEYWORD_56 ) | (enumLiteral_3= KEYWORD_54 ) | (enumLiteral_4= KEYWORD_50 ) )
            int alt51=5;
            switch ( input.LA(1) ) {
            case KEYWORD_51:
                {
                alt51=1;
                }
                break;
            case KEYWORD_55:
                {
                alt51=2;
                }
                break;
            case KEYWORD_56:
                {
                alt51=3;
                }
                break;
            case KEYWORD_54:
                {
                alt51=4;
                }
                break;
            case KEYWORD_50:
                {
                alt51=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2759:2: (enumLiteral_0= KEYWORD_51 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2759:2: (enumLiteral_0= KEYWORD_51 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2759:7: enumLiteral_0= KEYWORD_51
                    {
                    enumLiteral_0=(Token)match(input,KEYWORD_51,FOLLOW_KEYWORD_51_in_ruleJoinType5868); 

                            current = grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2765:6: (enumLiteral_1= KEYWORD_55 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2765:6: (enumLiteral_1= KEYWORD_55 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2765:11: enumLiteral_1= KEYWORD_55
                    {
                    enumLiteral_1=(Token)match(input,KEYWORD_55,FOLLOW_KEYWORD_55_in_ruleJoinType5890); 

                            current = grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2771:6: (enumLiteral_2= KEYWORD_56 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2771:6: (enumLiteral_2= KEYWORD_56 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2771:11: enumLiteral_2= KEYWORD_56
                    {
                    enumLiteral_2=(Token)match(input,KEYWORD_56,FOLLOW_KEYWORD_56_in_ruleJoinType5912); 

                            current = grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2777:6: (enumLiteral_3= KEYWORD_54 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2777:6: (enumLiteral_3= KEYWORD_54 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2777:11: enumLiteral_3= KEYWORD_54
                    {
                    enumLiteral_3=(Token)match(input,KEYWORD_54,FOLLOW_KEYWORD_54_in_ruleJoinType5934); 

                            current = grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2783:6: (enumLiteral_4= KEYWORD_50 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2783:6: (enumLiteral_4= KEYWORD_50 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2783:11: enumLiteral_4= KEYWORD_50
                    {
                    enumLiteral_4=(Token)match(input,KEYWORD_50,FOLLOW_KEYWORD_50_in_ruleJoinType5956); 

                            current = grammarAccess.getJoinTypeAccess().getCrossJoinEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getJoinTypeAccess().getCrossJoinEnumLiteralDeclaration_4()); 
                        

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
    // $ANTLR end "ruleJoinType"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleModel124 = new BitSet(new long[]{0x0000000020040002L});
    public static final BitSet FOLLOW_KEYWORD_33_in_ruleModel147 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleModel167 = new BitSet(new long[]{0x0000000020040002L});
    public static final BitSet FOLLOW_KEYWORD_46_in_ruleModel185 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_ruleModel205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_entryRuleSelect242 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelect252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_ruleSelect296 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_41_in_ruleSelect321 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleColumns_in_ruleSelect343 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleSelect356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleTables_in_ruleSelect376 = new BitSet(new long[]{0x0000000041004002L});
    public static final BitSet FOLLOW_KEYWORD_34_in_ruleSelect390 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect410 = new BitSet(new long[]{0x0000000001004002L});
    public static final BitSet FOLLOW_KEYWORD_42_in_ruleSelect426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_ruleSelect446 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_KEYWORD_35_in_ruleSelect462 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns519 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns576 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleColumns599 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns619 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias658 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOrAlias715 = new BitSet(new long[]{0x0000100000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleColumnOrAlias734 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleColumnOrAlias792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull832 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull889 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleColumnFull912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull932 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables971 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables1028 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleTables1051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables1071 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_entryRuleFromTable1110 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTable1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTable1166 = new BitSet(new long[]{0x0000000000000672L});
    public static final BitSet FOLLOW_ruleJoinType_in_ruleFromTable1188 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTable1209 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_KEYWORD_17_in_ruleFromTable1222 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleFromTable1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1279 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1335 = new BitSet(new long[]{0x0000100000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleTableOrAlias1354 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableOrAlias1387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull1423 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull1433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull1480 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleTableFull1503 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull1523 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName1562 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectName1572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDbObjectName1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns1652 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns1662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns1709 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByColumns1732 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns1752 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull1791 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull1801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull1847 = new BitSet(new long[]{0x0000010100000002L});
    public static final BitSet FOLLOW_KEYWORD_23_in_ruleOrderByColumnFull1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_25_in_ruleOrderByColumnFull1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns1946 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumns1956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleGroupByColumns2003 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleGroupByColumns2026 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleGroupByColumns2046 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_entryRuleFullExpression2085 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullExpression2095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleFullExpression2142 = new BitSet(new long[]{0x0000808000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression2172 = new BitSet(new long[]{0x0000808000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond2210 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragmentSecond2220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_22_in_ruleExpressionFragmentSecond2266 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_18_in_ruleExpressionFragmentSecond2294 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond2329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment2364 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragment2374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment2420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExpressionFragment2447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleExpressionFragment2474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup2509 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionGroup2519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleExpressionGroup2566 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleExpressionGroup2586 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleExpressionGroup2599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression2633 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression2643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_21_in_ruleXExpression2690 = new BitSet(new long[]{0x000020081C7A1800L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression2710 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression2723 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_ruleXExpression2743 = new BitSet(new long[]{0x0810000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression2757 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression2777 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression2792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams2826 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionParams2836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams2883 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpressionParams2906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams2926 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_entryRuleJRParameter2965 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRParameter2975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJRParameter3016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression3055 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression3065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleExpression3111 = new BitSet(new long[]{0x07000E1400A18180L});
    public static final BitSet FOLLOW_KEYWORD_39_in_ruleExpression3133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_52_in_ruleExpression3161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_ruleExpression3202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_ruleExpression3229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_ruleExpression3256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_ruleExpression3283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_entryRuleComparison3319 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComparison3329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_10_in_ruleComparison3375 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_14_in_ruleComparison3403 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_8_in_ruleComparison3431 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleComparison3459 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_9_in_ruleComparison3487 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleComparison3515 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleOperand_in_ruleComparison3550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_entryRuleLike3585 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLike3595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_29_in_ruleLike3641 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_44_in_ruleLike3669 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleLike3704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_entryRuleBetween3739 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBetween3749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_ruleBetween3795 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_53_in_ruleBetween3823 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBetween3858 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_KEYWORD_22_in_ruleBetween3871 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBetween3891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_entryRuleInOperator3926 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInOperator3936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_43_in_ruleInOperator3991 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleInOperator4019 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleInOperator4055 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_ruleOperandList_in_ruleInOperator4082 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleInOperator4096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_entryRuleOperandList4130 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandList4140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_ruleOperandList4187 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOperandList4210 = new BitSet(new long[]{0xE000002080000000L,0x0000000000000023L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_ruleOperandList4230 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_entryRuleOperand4269 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperand4279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand4326 = new BitSet(new long[]{0x10A9000000000002L});
    public static final BitSet FOLLOW_KEYWORD_3_in_ruleOperand4350 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_5_in_ruleOperand4368 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperand4384 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_7_in_ruleOperand4402 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleOperand4420 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand4441 = new BitSet(new long[]{0x10A9000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment4480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFragment4490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_ruleOperandFragment4536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_ruleOperandFragment4563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment4590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment4625 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOperandFragment4635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_ruleXOperandFragment4681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment4708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleXOperandFragment4735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand4770 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameterOperand4780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleParameterOperand4827 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleParameterOperand4843 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleParameterOperand4861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand4895 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclamationParameterOperand4905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_24_in_ruleExclamationParameterOperand4952 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleExclamationParameterOperand4968 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleExclamationParameterOperand4986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand5020 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOperand5030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOperand5075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand5109 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubQueryOperand5119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleSubQueryOperand5166 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSubQueryOperand5186 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleSubQueryOperand5199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand5233 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarOperand5243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_INT_in_ruleScalarOperand5285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleScalarOperand5317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand5340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleScalarOperand5368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIME_in_ruleScalarOperand5396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand5424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_entryRuleStringOperand5465 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringOperand5476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringOperand5515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_16_in_ruleXFunction5576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_32_in_ruleXFunction5598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_30_in_ruleXFunction5620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_45_in_ruleXFunction5642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_28_in_ruleXFunction5664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_38_in_ruleXFunction5686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_31_in_ruleXFunction5708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_48_in_ruleXFunction5730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_ruleXFunction5752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_49_in_ruleXFunction5774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_47_in_ruleXFunction5796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_40_in_ruleXFunction5818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_51_in_ruleJoinType5868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_55_in_ruleJoinType5890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_56_in_ruleJoinType5912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_54_in_ruleJoinType5934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_50_in_ruleJoinType5956 = new BitSet(new long[]{0x0000000000000002L});

}