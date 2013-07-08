package com.jaspersoft.studio.data.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_8", "KEYWORD_9", "KEYWORD_7", "KEYWORD_6", "KEYWORD_5", "KEYWORD_4", "KEYWORD_3", "KEYWORD_1", "KEYWORD_2", "RULE_STAR", "RULE_SL_COMMENT", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=15;
    public static final int RULE_STRING=17;
    public static final int RULE_STAR=13;
    public static final int KEYWORD_6=7;
    public static final int RULE_ANY_OTHER=20;
    public static final int KEYWORD_7=6;
    public static final int KEYWORD_8=4;
    public static final int KEYWORD_9=5;
    public static final int KEYWORD_1=11;
    public static final int RULE_INT=16;
    public static final int KEYWORD_5=8;
    public static final int KEYWORD_4=9;
    public static final int KEYWORD_3=10;
    public static final int KEYWORD_2=12;
    public static final int RULE_WS=19;
    public static final int RULE_SL_COMMENT=14;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=18;

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:61:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:62:2: (iv_ruleModel= ruleModel EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:63:2: iv_ruleModel= ruleModel EOF
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:70:1: ruleModel returns [EObject current=null] : (this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_9 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Select_0 = null;

        EObject lv_entries_3_0 = null;

        EObject lv_orderByEntry_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:73:28: ( (this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_9 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:74:1: (this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_9 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:74:1: (this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_9 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:5: this_Select_0= ruleSelect ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) ) )+ )? (otherlv_4= KEYWORD_9 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getModelAccess().getSelectParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSelect_in_ruleModel124);
            this_Select_0=ruleSelect();

            state._fsp--;


                    current = this_Select_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:83:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) ) )+ )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==KEYWORD_6) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:83:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:83:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:84:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getModelAccess().getOrSelectEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:89:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) ) )+
                    int cnt1=0;
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==KEYWORD_6) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:90:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleSelect ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleModel147); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getModelAccess().getUNIONKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:94:1: ( (lv_entries_3_0= ruleSelect ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:95:1: (lv_entries_3_0= ruleSelect )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:95:1: (lv_entries_3_0= ruleSelect )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:96:3: lv_entries_3_0= ruleSelect
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:112:6: (otherlv_4= KEYWORD_9 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==KEYWORD_9) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:113:2: otherlv_4= KEYWORD_9 ( (lv_orderByEntry_5_0= ruleOrderByColumns ) )
                    {
                    otherlv_4=(Token)match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_ruleModel185); 

                        	newLeafNode(otherlv_4, grammarAccess.getModelAccess().getORDERBYKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:117:1: ( (lv_orderByEntry_5_0= ruleOrderByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:118:1: (lv_orderByEntry_5_0= ruleOrderByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:118:1: (lv_orderByEntry_5_0= ruleOrderByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:119:3: lv_orderByEntry_5_0= ruleOrderByColumns
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:143:1: entryRuleSelect returns [EObject current=null] : iv_ruleSelect= ruleSelect EOF ;
    public final EObject entryRuleSelect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelect = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:144:2: (iv_ruleSelect= ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:145:2: iv_ruleSelect= ruleSelect EOF
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:152:1: ruleSelect returns [EObject current=null] : ( ( (lv_select_0_0= KEYWORD_7 ) ) (otherlv_1= KEYWORD_8 )? ( (lv_cols_2_0= ruleColumns ) ) ) ;
    public final EObject ruleSelect() throws RecognitionException {
        EObject current = null;

        Token lv_select_0_0=null;
        Token otherlv_1=null;
        EObject lv_cols_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:155:28: ( ( ( (lv_select_0_0= KEYWORD_7 ) ) (otherlv_1= KEYWORD_8 )? ( (lv_cols_2_0= ruleColumns ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:156:1: ( ( (lv_select_0_0= KEYWORD_7 ) ) (otherlv_1= KEYWORD_8 )? ( (lv_cols_2_0= ruleColumns ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:156:1: ( ( (lv_select_0_0= KEYWORD_7 ) ) (otherlv_1= KEYWORD_8 )? ( (lv_cols_2_0= ruleColumns ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:156:2: ( (lv_select_0_0= KEYWORD_7 ) ) (otherlv_1= KEYWORD_8 )? ( (lv_cols_2_0= ruleColumns ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:156:2: ( (lv_select_0_0= KEYWORD_7 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:157:1: (lv_select_0_0= KEYWORD_7 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:157:1: (lv_select_0_0= KEYWORD_7 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:158:3: lv_select_0_0= KEYWORD_7
            {
            lv_select_0_0=(Token)match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_ruleSelect296); 

                    newLeafNode(lv_select_0_0, grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectRule());
            	        }
                   		setWithLastConsumed(current, "select", lv_select_0_0, "SELECT");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:172:2: (otherlv_1= KEYWORD_8 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_8) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:173:2: otherlv_1= KEYWORD_8
                    {
                    otherlv_1=(Token)match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_ruleSelect321); 

                        	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:177:3: ( (lv_cols_2_0= ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:178:1: (lv_cols_2_0= ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:178:1: (lv_cols_2_0= ruleColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:179:3: lv_cols_2_0= ruleColumns
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


            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:203:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:204:2: (iv_ruleColumns= ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:205:2: iv_ruleColumns= ruleColumns EOF
            {
             newCompositeNode(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns378);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns388); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:212:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:215:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:216:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:216:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:217:5: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns435);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;


                    current = this_ColumnOrAlias_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:225:1: ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_1) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:225:2: () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:225:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:226:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:231:2: (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==KEYWORD_1) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:232:2: otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleColumns458); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:236:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:237:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:237:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:238:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns478);
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
                    	    if ( cnt5 >= 1 ) break loop5;
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:262:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:263:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:264:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias517);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias527); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:271:1: ruleColumnOrAlias returns [EObject current=null] : ( (this_ColumnFull_0= ruleColumnFull (otherlv_1= KEYWORD_3 )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_allCols_3_0=null;
        EObject this_ColumnFull_0 = null;

        EObject lv_colAlias_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:274:28: ( ( (this_ColumnFull_0= ruleColumnFull (otherlv_1= KEYWORD_3 )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:275:1: ( (this_ColumnFull_0= ruleColumnFull (otherlv_1= KEYWORD_3 )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:275:1: ( (this_ColumnFull_0= ruleColumnFull (otherlv_1= KEYWORD_3 )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_ID) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_STAR) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:275:2: (this_ColumnFull_0= ruleColumnFull (otherlv_1= KEYWORD_3 )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:275:2: (this_ColumnFull_0= ruleColumnFull (otherlv_1= KEYWORD_3 )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:276:5: this_ColumnFull_0= ruleColumnFull (otherlv_1= KEYWORD_3 )? ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    {
                     
                            newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOrAlias575);
                    this_ColumnFull_0=ruleColumnFull();

                    state._fsp--;


                            current = this_ColumnFull_0;
                            afterParserOrEnumRuleCall();
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:284:1: (otherlv_1= KEYWORD_3 )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==KEYWORD_3) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:285:2: otherlv_1= KEYWORD_3
                            {
                            otherlv_1=(Token)match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_ruleColumnOrAlias588); 

                                	newLeafNode(otherlv_1, grammarAccess.getColumnOrAliasAccess().getASKeyword_0_1());
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:289:3: ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==RULE_ID) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:290:1: (lv_colAlias_2_0= ruleDbObjectName )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:290:1: (lv_colAlias_2_0= ruleDbObjectName )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:291:3: lv_colAlias_2_0= ruleDbObjectName
                            {
                             
                            	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
                            	    
                            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias610);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:308:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:308:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:309:1: (lv_allCols_3_0= RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:309:1: (lv_allCols_3_0= RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:310:3: lv_allCols_3_0= RULE_STAR
                    {
                    lv_allCols_3_0=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleColumnOrAlias635); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:334:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:335:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:336:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
             newCompositeNode(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull675);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull685); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:343:1: ruleColumnFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:346:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:347:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:347:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:348:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull732);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:356:1: ( () (otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==KEYWORD_2) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:356:2: () (otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:356:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:357:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnFullAccess().getColEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:362:2: (otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==KEYWORD_2) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:363:2: otherlv_2= KEYWORD_2 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleColumnFull755); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:367:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:368:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:368:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:369:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull775);
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
                    	    if ( cnt10 >= 1 ) break loop10;
                                EarlyExitException eee =
                                    new EarlyExitException(10, input);
                                throw eee;
                        }
                        cnt10++;
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


    // $ANTLR start "entryRuleDbObjectName"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:393:1: entryRuleDbObjectName returns [EObject current=null] : iv_ruleDbObjectName= ruleDbObjectName EOF ;
    public final EObject entryRuleDbObjectName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectName = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:394:2: (iv_ruleDbObjectName= ruleDbObjectName EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:395:2: iv_ruleDbObjectName= ruleDbObjectName EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameRule()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName814);
            iv_ruleDbObjectName=ruleDbObjectName();

            state._fsp--;

             current =iv_ruleDbObjectName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectName824); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:402:1: ruleDbObjectName returns [EObject current=null] : ( (lv_dbname_0_0= RULE_ID ) ) ;
    public final EObject ruleDbObjectName() throws RecognitionException {
        EObject current = null;

        Token lv_dbname_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:405:28: ( ( (lv_dbname_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:406:1: ( (lv_dbname_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:406:1: ( (lv_dbname_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:407:1: (lv_dbname_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:407:1: (lv_dbname_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:408:3: lv_dbname_0_0= RULE_ID
            {
            lv_dbname_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDbObjectName865); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:432:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:433:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:434:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns904);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;

             current =iv_ruleOrderByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns914); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:441:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:444:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:445:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:445:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:446:5: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns961);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;


                    current = this_OrderByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:454:1: ( () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==KEYWORD_1) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:454:2: () (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:454:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:455:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:460:2: (otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==KEYWORD_1) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:461:2: otherlv_2= KEYWORD_1 ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleOrderByColumns984); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:465:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:466:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:466:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:467:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns1004);
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
                    	    if ( cnt12 >= 1 ) break loop12;
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:491:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:492:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:493:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull1043);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;

             current =iv_ruleOrderByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull1053); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:500:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( (lv_colOrder_0_0= ruleColumnFull ) ) (otherlv_1= KEYWORD_4 | otherlv_2= KEYWORD_5 )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:503:28: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) (otherlv_1= KEYWORD_4 | otherlv_2= KEYWORD_5 )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:504:1: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) (otherlv_1= KEYWORD_4 | otherlv_2= KEYWORD_5 )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:504:1: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) (otherlv_1= KEYWORD_4 | otherlv_2= KEYWORD_5 )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:504:2: ( (lv_colOrder_0_0= ruleColumnFull ) ) (otherlv_1= KEYWORD_4 | otherlv_2= KEYWORD_5 )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:504:2: ( (lv_colOrder_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:505:1: (lv_colOrder_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:505:1: (lv_colOrder_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:506:3: lv_colOrder_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull1099);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:522:2: (otherlv_1= KEYWORD_4 | otherlv_2= KEYWORD_5 )?
            int alt14=3;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEYWORD_4) ) {
                alt14=1;
            }
            else if ( (LA14_0==KEYWORD_5) ) {
                alt14=2;
            }
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:523:2: otherlv_1= KEYWORD_4
                    {
                    otherlv_1=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByColumnFull1113); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByColumnFullAccess().getASCKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:529:2: otherlv_2= KEYWORD_5
                    {
                    otherlv_2=(Token)match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_ruleOrderByColumnFull1131); 

                        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnFullAccess().getDESCKeyword_1_1());
                        

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

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleModel124 = new BitSet(new long[]{0x00000000000000A2L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleModel147 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleModel167 = new BitSet(new long[]{0x00000000000000A2L});
    public static final BitSet FOLLOW_KEYWORD_9_in_ruleModel185 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_ruleModel205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_entryRuleSelect242 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelect252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_7_in_ruleSelect296 = new BitSet(new long[]{0x000000000000A010L});
    public static final BitSet FOLLOW_KEYWORD_8_in_ruleSelect321 = new BitSet(new long[]{0x000000000000A010L});
    public static final BitSet FOLLOW_ruleColumns_in_ruleSelect343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns378 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns435 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleColumns458 = new BitSet(new long[]{0x000000000000A010L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns478 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias517 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOrAlias575 = new BitSet(new long[]{0x0000000000008402L});
    public static final BitSet FOLLOW_KEYWORD_3_in_ruleColumnOrAlias588 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleColumnOrAlias635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull675 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull732 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleColumnFull755 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull775 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName814 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectName824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDbObjectName865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns904 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns961 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleOrderByColumns984 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns1004 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull1043 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull1099 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByColumnFull1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_5_in_ruleOrderByColumnFull1131 = new BitSet(new long[]{0x0000000000000002L});

}