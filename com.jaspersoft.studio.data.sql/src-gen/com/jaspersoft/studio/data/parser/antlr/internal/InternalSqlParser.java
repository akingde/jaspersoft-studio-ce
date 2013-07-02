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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_SIGNED_DOUBLE", "RULE_SINGED_LONG", "RULE_STRING", "RULE_DATE", "RULE_BOOL", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'SELECT'", "'FROM'", "'WHERE'", "'GROUP BY'", "'HAVING'", "'ORDER BY'", "','", "'.'", "'AS'", "'OR'", "'AND'", "'('", "')'", "'?'", "'null'", "'true'", "'false'", "'['", "']'", "'in'", "'not in'", "'<'", "'>'", "'<='", "'>='", "'='", "'!='", "'like'", "'not like'"
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
    public String getGrammarFileName() { return "../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g"; }



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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:68:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:69:2: (iv_ruleModel= ruleModel EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:70:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel75);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel85); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:77:1: ruleModel returns [EObject current=null] : (otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) )? otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? (otherlv_6= 'GROUP BY' ( (lv_groupByEntry_7_0= ruleGroupByColumns ) ) )? (otherlv_8= 'HAVING' ( (lv_havingEntry_9_0= ruleHavingEntry ) ) )? (otherlv_10= 'ORDER BY' ( (lv_orderByEntry_11_0= ruleOrderByColumns ) ) )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_col_1_0 = null;

        EObject lv_tbl_3_0 = null;

        EObject lv_whereEntry_5_0 = null;

        EObject lv_groupByEntry_7_0 = null;

        EObject lv_havingEntry_9_0 = null;

        EObject lv_orderByEntry_11_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:80:28: ( (otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) )? otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? (otherlv_6= 'GROUP BY' ( (lv_groupByEntry_7_0= ruleGroupByColumns ) ) )? (otherlv_8= 'HAVING' ( (lv_havingEntry_9_0= ruleHavingEntry ) ) )? (otherlv_10= 'ORDER BY' ( (lv_orderByEntry_11_0= ruleOrderByColumns ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:81:1: (otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) )? otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? (otherlv_6= 'GROUP BY' ( (lv_groupByEntry_7_0= ruleGroupByColumns ) ) )? (otherlv_8= 'HAVING' ( (lv_havingEntry_9_0= ruleHavingEntry ) ) )? (otherlv_10= 'ORDER BY' ( (lv_orderByEntry_11_0= ruleOrderByColumns ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:81:1: (otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) )? otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? (otherlv_6= 'GROUP BY' ( (lv_groupByEntry_7_0= ruleGroupByColumns ) ) )? (otherlv_8= 'HAVING' ( (lv_havingEntry_9_0= ruleHavingEntry ) ) )? (otherlv_10= 'ORDER BY' ( (lv_orderByEntry_11_0= ruleOrderByColumns ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:81:3: otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) )? otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? (otherlv_6= 'GROUP BY' ( (lv_groupByEntry_7_0= ruleGroupByColumns ) ) )? (otherlv_8= 'HAVING' ( (lv_havingEntry_9_0= ruleHavingEntry ) ) )? (otherlv_10= 'ORDER BY' ( (lv_orderByEntry_11_0= ruleOrderByColumns ) ) )?
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleModel122); 

                	newLeafNode(otherlv_0, grammarAccess.getModelAccess().getSELECTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:85:1: ( (lv_col_1_0= ruleColumns ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:86:1: (lv_col_1_0= ruleColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:86:1: (lv_col_1_0= ruleColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:87:3: lv_col_1_0= ruleColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getColColumnsParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumns_in_ruleModel143);
                    lv_col_1_0=ruleColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"col",
                            		lv_col_1_0, 
                            		"Columns");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleModel156); 

                	newLeafNode(otherlv_2, grammarAccess.getModelAccess().getFROMKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:107:1: ( (lv_tbl_3_0= ruleTables ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:108:1: (lv_tbl_3_0= ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:108:1: (lv_tbl_3_0= ruleTables )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:109:3: lv_tbl_3_0= ruleTables
            {
             
            	        newCompositeNode(grammarAccess.getModelAccess().getTblTablesParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleTables_in_ruleModel177);
            lv_tbl_3_0=ruleTables();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getModelRule());
            	        }
                   		set(
                   			current, 
                   			"tbl",
                    		lv_tbl_3_0, 
                    		"Tables");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:125:2: (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==17) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:125:4: otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) )
                    {
                    otherlv_4=(Token)match(input,17,FOLLOW_17_in_ruleModel190); 

                        	newLeafNode(otherlv_4, grammarAccess.getModelAccess().getWHEREKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:129:1: ( (lv_whereEntry_5_0= ruleWhereEntry ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:130:1: (lv_whereEntry_5_0= ruleWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:130:1: (lv_whereEntry_5_0= ruleWhereEntry )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:131:3: lv_whereEntry_5_0= ruleWhereEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getWhereEntryWhereEntryParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleWhereEntry_in_ruleModel211);
                    lv_whereEntry_5_0=ruleWhereEntry();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"whereEntry",
                            		lv_whereEntry_5_0, 
                            		"WhereEntry");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:147:4: (otherlv_6= 'GROUP BY' ( (lv_groupByEntry_7_0= ruleGroupByColumns ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==18) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:147:6: otherlv_6= 'GROUP BY' ( (lv_groupByEntry_7_0= ruleGroupByColumns ) )
                    {
                    otherlv_6=(Token)match(input,18,FOLLOW_18_in_ruleModel226); 

                        	newLeafNode(otherlv_6, grammarAccess.getModelAccess().getGROUPBYKeyword_5_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:151:1: ( (lv_groupByEntry_7_0= ruleGroupByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:152:1: (lv_groupByEntry_7_0= ruleGroupByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:152:1: (lv_groupByEntry_7_0= ruleGroupByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:153:3: lv_groupByEntry_7_0= ruleGroupByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getGroupByEntryGroupByColumnsParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleGroupByColumns_in_ruleModel247);
                    lv_groupByEntry_7_0=ruleGroupByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"groupByEntry",
                            		lv_groupByEntry_7_0, 
                            		"GroupByColumns");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:169:4: (otherlv_8= 'HAVING' ( (lv_havingEntry_9_0= ruleHavingEntry ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==19) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:169:6: otherlv_8= 'HAVING' ( (lv_havingEntry_9_0= ruleHavingEntry ) )
                    {
                    otherlv_8=(Token)match(input,19,FOLLOW_19_in_ruleModel262); 

                        	newLeafNode(otherlv_8, grammarAccess.getModelAccess().getHAVINGKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:173:1: ( (lv_havingEntry_9_0= ruleHavingEntry ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:174:1: (lv_havingEntry_9_0= ruleHavingEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:174:1: (lv_havingEntry_9_0= ruleHavingEntry )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:175:3: lv_havingEntry_9_0= ruleHavingEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getHavingEntryHavingEntryParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleHavingEntry_in_ruleModel283);
                    lv_havingEntry_9_0=ruleHavingEntry();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"havingEntry",
                            		lv_havingEntry_9_0, 
                            		"HavingEntry");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:191:4: (otherlv_10= 'ORDER BY' ( (lv_orderByEntry_11_0= ruleOrderByColumns ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:191:6: otherlv_10= 'ORDER BY' ( (lv_orderByEntry_11_0= ruleOrderByColumns ) )
                    {
                    otherlv_10=(Token)match(input,20,FOLLOW_20_in_ruleModel298); 

                        	newLeafNode(otherlv_10, grammarAccess.getModelAccess().getORDERBYKeyword_7_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:195:1: ( (lv_orderByEntry_11_0= ruleOrderByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:196:1: (lv_orderByEntry_11_0= ruleOrderByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:196:1: (lv_orderByEntry_11_0= ruleOrderByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:197:3: lv_orderByEntry_11_0= ruleOrderByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOrderByColumns_in_ruleModel319);
                    lv_orderByEntry_11_0=ruleOrderByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"orderByEntry",
                            		lv_orderByEntry_11_0, 
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


    // $ANTLR start "entryRuleOrderByColumns"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:221:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:222:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:223:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns357);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;

             current =iv_ruleOrderByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns367); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:230:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:233:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:234:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:234:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:235:5: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns414);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;

             
                    current = this_OrderByColumnFull_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:243:1: ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==21) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:243:2: () (otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:243:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:244:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:249:2: (otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==21) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:249:4: otherlv_2= ',' ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleOrderByColumns436); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:253:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:254:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:254:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:255:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns457);
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
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:279:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:280:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:281:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull497);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;

             current =iv_ruleOrderByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull507); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:288:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( (lv_colOrder_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colOrder_3_0= ruleColumn ) ) ) ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_colOrder_0_0 = null;

        EObject this_TableFull_1 = null;

        EObject lv_colOrder_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:291:28: ( ( ( (lv_colOrder_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colOrder_3_0= ruleColumn ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:292:1: ( ( (lv_colOrder_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colOrder_3_0= ruleColumn ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:292:1: ( ( (lv_colOrder_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colOrder_3_0= ruleColumn ) ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==22) ) {
                    alt8=2;
                }
                else if ( (LA8_1==EOF||LA8_1==21) ) {
                    alt8=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:292:2: ( (lv_colOrder_0_0= ruleColumn ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:292:2: ( (lv_colOrder_0_0= ruleColumn ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:293:1: (lv_colOrder_0_0= ruleColumn )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:293:1: (lv_colOrder_0_0= ruleColumn )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:294:3: lv_colOrder_0_0= ruleColumn
                    {
                     
                    	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumn_in_ruleOrderByColumnFull553);
                    lv_colOrder_0_0=ruleColumn();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOrderByColumnFullRule());
                    	        }
                           		set(
                           			current, 
                           			"colOrder",
                            		lv_colOrder_0_0, 
                            		"Column");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:311:6: (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colOrder_3_0= ruleColumn ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:311:6: (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colOrder_3_0= ruleColumn ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:312:5: this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colOrder_3_0= ruleColumn ) )
                    {
                     
                            newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getTableFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleOrderByColumnFull582);
                    this_TableFull_1=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,22,FOLLOW_22_in_ruleOrderByColumnFull593); 

                        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnFullAccess().getFullStopKeyword_1_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:324:1: ( (lv_colOrder_3_0= ruleColumn ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:325:1: (lv_colOrder_3_0= ruleColumn )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:325:1: (lv_colOrder_3_0= ruleColumn )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:326:3: lv_colOrder_3_0= ruleColumn
                    {
                     
                    	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumn_in_ruleOrderByColumnFull614);
                    lv_colOrder_3_0=ruleColumn();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOrderByColumnFullRule());
                    	        }
                           		set(
                           			current, 
                           			"colOrder",
                            		lv_colOrder_3_0, 
                            		"Column");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


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
    // $ANTLR end "ruleOrderByColumnFull"


    // $ANTLR start "entryRuleGroupByColumns"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:350:1: entryRuleGroupByColumns returns [EObject current=null] : iv_ruleGroupByColumns= ruleGroupByColumns EOF ;
    public final EObject entryRuleGroupByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:351:2: (iv_ruleGroupByColumns= ruleGroupByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:352:2: iv_ruleGroupByColumns= ruleGroupByColumns EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnsRule()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns651);
            iv_ruleGroupByColumns=ruleGroupByColumns();

            state._fsp--;

             current =iv_ruleGroupByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumns661); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:359:1: ruleGroupByColumns returns [EObject current=null] : (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) ;
    public final EObject ruleGroupByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_GroupByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:362:28: ( (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:363:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:363:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:364:5: this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns708);
            this_GroupByColumnFull_0=ruleGroupByColumnFull();

            state._fsp--;

             
                    current = this_GroupByColumnFull_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:372:1: ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==21) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:372:2: () (otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:372:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:373:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:378:2: (otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==21) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:378:4: otherlv_2= ',' ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleGroupByColumns730); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:382:1: ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:383:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:383:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:384:3: lv_entries_3_0= ruleGroupByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns751);
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
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:408:1: entryRuleGroupByColumnFull returns [EObject current=null] : iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF ;
    public final EObject entryRuleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:409:2: (iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:410:2: iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnFullRule()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull791);
            iv_ruleGroupByColumnFull=ruleGroupByColumnFull();

            state._fsp--;

             current =iv_ruleGroupByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumnFull801); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:417:1: ruleGroupByColumnFull returns [EObject current=null] : ( ( (lv_groupByColumn_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_groupByColumn_3_0= ruleColumn ) ) ) ) ;
    public final EObject ruleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_groupByColumn_0_0 = null;

        EObject this_TableFull_1 = null;

        EObject lv_groupByColumn_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:420:28: ( ( ( (lv_groupByColumn_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_groupByColumn_3_0= ruleColumn ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:421:1: ( ( (lv_groupByColumn_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_groupByColumn_3_0= ruleColumn ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:421:1: ( ( (lv_groupByColumn_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_groupByColumn_3_0= ruleColumn ) ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_ID) ) {
                int LA11_1 = input.LA(2);

                if ( (LA11_1==22) ) {
                    alt11=2;
                }
                else if ( (LA11_1==EOF||(LA11_1>=19 && LA11_1<=21)) ) {
                    alt11=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:421:2: ( (lv_groupByColumn_0_0= ruleColumn ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:421:2: ( (lv_groupByColumn_0_0= ruleColumn ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:422:1: (lv_groupByColumn_0_0= ruleColumn )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:422:1: (lv_groupByColumn_0_0= ruleColumn )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:423:3: lv_groupByColumn_0_0= ruleColumn
                    {
                     
                    	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnColumnParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumn_in_ruleGroupByColumnFull847);
                    lv_groupByColumn_0_0=ruleColumn();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getGroupByColumnFullRule());
                    	        }
                           		set(
                           			current, 
                           			"groupByColumn",
                            		lv_groupByColumn_0_0, 
                            		"Column");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:440:6: (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_groupByColumn_3_0= ruleColumn ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:440:6: (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_groupByColumn_3_0= ruleColumn ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:441:5: this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_groupByColumn_3_0= ruleColumn ) )
                    {
                     
                            newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getTableFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleGroupByColumnFull876);
                    this_TableFull_1=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,22,FOLLOW_22_in_ruleGroupByColumnFull887); 

                        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnFullAccess().getFullStopKeyword_1_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:453:1: ( (lv_groupByColumn_3_0= ruleColumn ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:454:1: (lv_groupByColumn_3_0= ruleColumn )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:454:1: (lv_groupByColumn_3_0= ruleColumn )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:455:3: lv_groupByColumn_3_0= ruleColumn
                    {
                     
                    	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnColumnParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumn_in_ruleGroupByColumnFull908);
                    lv_groupByColumn_3_0=ruleColumn();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getGroupByColumnFullRule());
                    	        }
                           		set(
                           			current, 
                           			"groupByColumn",
                            		lv_groupByColumn_3_0, 
                            		"Column");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


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


    // $ANTLR start "entryRuleColumns"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:479:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:480:2: (iv_ruleColumns= ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:481:2: iv_ruleColumns= ruleColumns EOF
            {
             newCompositeNode(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns945);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns955); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:488:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:491:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:492:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:492:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:493:5: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns1002);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;

             
                    current = this_ColumnOrAlias_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:501:1: ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==21) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:501:2: () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:501:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:502:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:507:2: (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==21) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:507:4: otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleColumns1024); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:511:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:512:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:512:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:513:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns1045);
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
    // $ANTLR end "ruleColumns"


    // $ANTLR start "entryRuleColumnOrAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:537:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:538:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:539:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias1085);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias1095); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:546:1: ruleColumnOrAlias returns [EObject current=null] : (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnFull_0 = null;

        EObject this_ColumnFull_1 = null;

        EObject lv_colAlias_3_0 = null;

        EObject this_ColumnFull_4 = null;

        EObject lv_colAlias_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:549:28: ( (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:550:1: (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:550:1: (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) )
            int alt14=3;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:551:5: this_ColumnFull_0= ruleColumnFull
                    {
                     
                            newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOrAlias1142);
                    this_ColumnFull_0=ruleColumnFull();

                    state._fsp--;

                     
                            current = this_ColumnFull_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:560:6: (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:560:6: (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:561:5: this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) )
                    {
                     
                            newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOrAlias1170);
                    this_ColumnFull_1=ruleColumnFull();

                    state._fsp--;

                     
                            current = this_ColumnFull_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,23,FOLLOW_23_in_ruleColumnOrAlias1181); 

                        	newLeafNode(otherlv_2, grammarAccess.getColumnOrAliasAccess().getASKeyword_1_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:573:1: ( (lv_colAlias_3_0= ruleColumnAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:574:1: (lv_colAlias_3_0= ruleColumnAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:574:1: (lv_colAlias_3_0= ruleColumnAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:575:3: lv_colAlias_3_0= ruleColumnAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnAlias_in_ruleColumnOrAlias1202);
                    lv_colAlias_3_0=ruleColumnAlias();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"colAlias",
                            		lv_colAlias_3_0, 
                            		"ColumnAlias");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:592:6: (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:592:6: (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:593:5: this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) )
                    {
                     
                            newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_2_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOrAlias1232);
                    this_ColumnFull_4=ruleColumnFull();

                    state._fsp--;

                     
                            current = this_ColumnFull_4; 
                            afterParserOrEnumRuleCall();
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:601:1: ( (lv_colAlias_5_0= ruleColumnAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:602:1: (lv_colAlias_5_0= ruleColumnAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:602:1: (lv_colAlias_5_0= ruleColumnAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:603:3: lv_colAlias_5_0= ruleColumnAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnAlias_in_ruleColumnOrAlias1252);
                    lv_colAlias_5_0=ruleColumnAlias();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"colAlias",
                            		lv_colAlias_5_0, 
                            		"ColumnAlias");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:627:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:628:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:629:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
             newCompositeNode(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull1289);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull1299); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:636:1: ruleColumnFull returns [EObject current=null] : ( ( (lv_colName_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) ) ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_colName_0_0 = null;

        EObject this_TableFull_1 = null;

        EObject lv_colName_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:639:28: ( ( ( (lv_colName_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:640:1: ( ( (lv_colName_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:640:1: ( ( (lv_colName_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ID) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==22) ) {
                    alt15=2;
                }
                else if ( (LA15_1==EOF||LA15_1==RULE_ID||LA15_1==16||LA15_1==21||LA15_1==23) ) {
                    alt15=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:640:2: ( (lv_colName_0_0= ruleColumn ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:640:2: ( (lv_colName_0_0= ruleColumn ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:641:1: (lv_colName_0_0= ruleColumn )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:641:1: (lv_colName_0_0= ruleColumn )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:642:3: lv_colName_0_0= ruleColumn
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumn_in_ruleColumnFull1345);
                    lv_colName_0_0=ruleColumn();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getColumnFullRule());
                    	        }
                           		set(
                           			current, 
                           			"colName",
                            		lv_colName_0_0, 
                            		"Column");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:659:6: (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:659:6: (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:660:5: this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) )
                    {
                     
                            newCompositeNode(grammarAccess.getColumnFullAccess().getTableFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleColumnFull1374);
                    this_TableFull_1=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,22,FOLLOW_22_in_ruleColumnFull1385); 

                        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:672:1: ( (lv_colName_3_0= ruleColumn ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:673:1: (lv_colName_3_0= ruleColumn )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:673:1: (lv_colName_3_0= ruleColumn )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:674:3: lv_colName_3_0= ruleColumn
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumn_in_ruleColumnFull1406);
                    lv_colName_3_0=ruleColumn();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getColumnFullRule());
                    	        }
                           		set(
                           			current, 
                           			"colName",
                            		lv_colName_3_0, 
                            		"Column");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


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
    // $ANTLR end "ruleColumnFull"


    // $ANTLR start "entryRuleColumnAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:698:1: entryRuleColumnAlias returns [EObject current=null] : iv_ruleColumnAlias= ruleColumnAlias EOF ;
    public final EObject entryRuleColumnAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:699:2: (iv_ruleColumnAlias= ruleColumnAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:700:2: iv_ruleColumnAlias= ruleColumnAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnAliasRule()); 
            pushFollow(FOLLOW_ruleColumnAlias_in_entryRuleColumnAlias1443);
            iv_ruleColumnAlias=ruleColumnAlias();

            state._fsp--;

             current =iv_ruleColumnAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnAlias1453); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleColumnAlias"


    // $ANTLR start "ruleColumnAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:707:1: ruleColumnAlias returns [EObject current=null] : ( (lv_colAlias_0_0= RULE_ID ) ) ;
    public final EObject ruleColumnAlias() throws RecognitionException {
        EObject current = null;

        Token lv_colAlias_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:710:28: ( ( (lv_colAlias_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:711:1: ( (lv_colAlias_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:711:1: ( (lv_colAlias_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:712:1: (lv_colAlias_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:712:1: (lv_colAlias_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:713:3: lv_colAlias_0_0= RULE_ID
            {
            lv_colAlias_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleColumnAlias1494); 

            			newLeafNode(lv_colAlias_0_0, grammarAccess.getColumnAliasAccess().getColAliasIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getColumnAliasRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"colAlias",
                    		lv_colAlias_0_0, 
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
    // $ANTLR end "ruleColumnAlias"


    // $ANTLR start "entryRuleColumn"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:737:1: entryRuleColumn returns [EObject current=null] : iv_ruleColumn= ruleColumn EOF ;
    public final EObject entryRuleColumn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumn = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:738:2: (iv_ruleColumn= ruleColumn EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:739:2: iv_ruleColumn= ruleColumn EOF
            {
             newCompositeNode(grammarAccess.getColumnRule()); 
            pushFollow(FOLLOW_ruleColumn_in_entryRuleColumn1534);
            iv_ruleColumn=ruleColumn();

            state._fsp--;

             current =iv_ruleColumn; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumn1544); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleColumn"


    // $ANTLR start "ruleColumn"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:746:1: ruleColumn returns [EObject current=null] : ( (lv_colName_0_0= RULE_ID ) ) ;
    public final EObject ruleColumn() throws RecognitionException {
        EObject current = null;

        Token lv_colName_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:749:28: ( ( (lv_colName_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:750:1: ( (lv_colName_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:750:1: ( (lv_colName_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:751:1: (lv_colName_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:751:1: (lv_colName_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:752:3: lv_colName_0_0= RULE_ID
            {
            lv_colName_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleColumn1585); 

            			newLeafNode(lv_colName_0_0, grammarAccess.getColumnAccess().getColNameIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getColumnRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"colName",
                    		lv_colName_0_0, 
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
    // $ANTLR end "ruleColumn"


    // $ANTLR start "entryRuleTables"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:776:1: entryRuleTables returns [EObject current=null] : iv_ruleTables= ruleTables EOF ;
    public final EObject entryRuleTables() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTables = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:777:2: (iv_ruleTables= ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:778:2: iv_ruleTables= ruleTables EOF
            {
             newCompositeNode(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables1625);
            iv_ruleTables=ruleTables();

            state._fsp--;

             current =iv_ruleTables; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables1635); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:785:1: ruleTables returns [EObject current=null] : (this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )? ) ;
    public final EObject ruleTables() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_TableOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:788:28: ( (this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:789:1: (this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:789:1: (this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:790:5: this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTablesAccess().getTableOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleTables1682);
            this_TableOrAlias_0=ruleTableOrAlias();

            state._fsp--;

             
                    current = this_TableOrAlias_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:798:1: ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==21) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:798:2: () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:798:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:799:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:804:2: (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+
                    int cnt16=0;
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==21) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:804:4: otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleTables1704); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:808:1: ( (lv_entries_3_0= ruleTableOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:809:1: (lv_entries_3_0= ruleTableOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:809:1: (lv_entries_3_0= ruleTableOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:810:3: lv_entries_3_0= ruleTableOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesTableOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleTableOrAlias_in_ruleTables1725);
                    	    lv_entries_3_0=ruleTableOrAlias();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getTablesRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"TableOrAlias");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt16 >= 1 ) break loop16;
                                EarlyExitException eee =
                                    new EarlyExitException(16, input);
                                throw eee;
                        }
                        cnt16++;
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


    // $ANTLR start "entryRuleTableOrAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:834:1: entryRuleTableOrAlias returns [EObject current=null] : iv_ruleTableOrAlias= ruleTableOrAlias EOF ;
    public final EObject entryRuleTableOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:835:2: (iv_ruleTableOrAlias= ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:836:2: iv_ruleTableOrAlias= ruleTableOrAlias EOF
            {
             newCompositeNode(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1765);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;

             current =iv_ruleTableOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias1775); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:843:1: ruleTableOrAlias returns [EObject current=null] : (this_TableFull_0= ruleTableFull | (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) ) | (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) ) ) ;
    public final EObject ruleTableOrAlias() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_TableFull_0 = null;

        EObject this_TableFull_1 = null;

        EObject lv_tblAlias_3_0 = null;

        EObject this_TableFull_4 = null;

        EObject lv_tblAlias_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:846:28: ( (this_TableFull_0= ruleTableFull | (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) ) | (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:847:1: (this_TableFull_0= ruleTableFull | (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) ) | (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:847:1: (this_TableFull_0= ruleTableFull | (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) ) | (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) ) )
            int alt18=3;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case RULE_ID:
                    {
                    alt18=3;
                    }
                    break;
                case 22:
                    {
                    int LA18_3 = input.LA(3);

                    if ( (LA18_3==RULE_ID) ) {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            int LA18_7 = input.LA(5);

                            if ( (LA18_7==RULE_ID) ) {
                                switch ( input.LA(6) ) {
                                case 23:
                                    {
                                    alt18=2;
                                    }
                                    break;
                                case RULE_ID:
                                    {
                                    alt18=3;
                                    }
                                    break;
                                case EOF:
                                case 17:
                                case 18:
                                case 19:
                                case 20:
                                case 21:
                                    {
                                    alt18=1;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 18, 8, input);

                                    throw nvae;
                                }

                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 18, 7, input);

                                throw nvae;
                            }
                            }
                            break;
                        case 23:
                            {
                            alt18=2;
                            }
                            break;
                        case RULE_ID:
                            {
                            alt18=3;
                            }
                            break;
                        case EOF:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                            {
                            alt18=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 18, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 18, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    {
                    alt18=1;
                    }
                    break;
                case 23:
                    {
                    alt18=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:848:5: this_TableFull_0= ruleTableFull
                    {
                     
                            newCompositeNode(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1822);
                    this_TableFull_0=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:857:6: (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:857:6: (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:858:5: this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) )
                    {
                     
                            newCompositeNode(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1850);
                    this_TableFull_1=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,23,FOLLOW_23_in_ruleTableOrAlias1861); 

                        	newLeafNode(otherlv_2, grammarAccess.getTableOrAliasAccess().getASKeyword_1_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:870:1: ( (lv_tblAlias_3_0= ruleTableAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:871:1: (lv_tblAlias_3_0= ruleTableAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:871:1: (lv_tblAlias_3_0= ruleTableAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:872:3: lv_tblAlias_3_0= ruleTableAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTableAlias_in_ruleTableOrAlias1882);
                    lv_tblAlias_3_0=ruleTableAlias();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"tblAlias",
                            		lv_tblAlias_3_0, 
                            		"TableAlias");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:889:6: (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:889:6: (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:890:5: this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) )
                    {
                     
                            newCompositeNode(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_2_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1912);
                    this_TableFull_4=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_4; 
                            afterParserOrEnumRuleCall();
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:898:1: ( (lv_tblAlias_5_0= ruleTableAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:899:1: (lv_tblAlias_5_0= ruleTableAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:899:1: (lv_tblAlias_5_0= ruleTableAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:900:3: lv_tblAlias_5_0= ruleTableAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTableAlias_in_ruleTableOrAlias1932);
                    lv_tblAlias_5_0=ruleTableAlias();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"tblAlias",
                            		lv_tblAlias_5_0, 
                            		"TableAlias");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


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
    // $ANTLR end "ruleTableOrAlias"


    // $ANTLR start "entryRuleTableFull"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:924:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:925:2: (iv_ruleTableFull= ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:926:2: iv_ruleTableFull= ruleTableFull EOF
            {
             newCompositeNode(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull1969);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;

             current =iv_ruleTableFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull1979); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:933:1: ruleTableFull returns [EObject current=null] : ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_Schema_0 = null;

        EObject lv_tbl_2_0 = null;

        EObject lv_tbl_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:936:28: ( ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:937:1: ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:937:1: ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_ID) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==22) ) {
                    int LA19_2 = input.LA(3);

                    if ( (LA19_2==RULE_ID) ) {
                        alt19=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA19_1==EOF||LA19_1==RULE_ID||(LA19_1>=17 && LA19_1<=21)||LA19_1==23) ) {
                    alt19=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:937:2: (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:937:2: (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:938:5: this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) )
                    {
                     
                            newCompositeNode(grammarAccess.getTableFullAccess().getSchemaParserRuleCall_0_0()); 
                        
                    pushFollow(FOLLOW_ruleSchema_in_ruleTableFull2027);
                    this_Schema_0=ruleSchema();

                    state._fsp--;

                     
                            current = this_Schema_0; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleTableFull2038); 

                        	newLeafNode(otherlv_1, grammarAccess.getTableFullAccess().getFullStopKeyword_0_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:950:1: ( (lv_tbl_2_0= ruleTable ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:951:1: (lv_tbl_2_0= ruleTable )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:951:1: (lv_tbl_2_0= ruleTable )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:952:3: lv_tbl_2_0= ruleTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_0_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTable_in_ruleTableFull2059);
                    lv_tbl_2_0=ruleTable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableFullRule());
                    	        }
                           		set(
                           			current, 
                           			"tbl",
                            		lv_tbl_2_0, 
                            		"Table");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:969:6: ( (lv_tbl_3_0= ruleTable ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:969:6: ( (lv_tbl_3_0= ruleTable ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:970:1: (lv_tbl_3_0= ruleTable )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:970:1: (lv_tbl_3_0= ruleTable )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:971:3: lv_tbl_3_0= ruleTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTable_in_ruleTableFull2087);
                    lv_tbl_3_0=ruleTable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableFullRule());
                    	        }
                           		set(
                           			current, 
                           			"tbl",
                            		lv_tbl_3_0, 
                            		"Table");
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
    // $ANTLR end "ruleTableFull"


    // $ANTLR start "entryRuleTable"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:995:1: entryRuleTable returns [EObject current=null] : iv_ruleTable= ruleTable EOF ;
    public final EObject entryRuleTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:996:2: (iv_ruleTable= ruleTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:997:2: iv_ruleTable= ruleTable EOF
            {
             newCompositeNode(grammarAccess.getTableRule()); 
            pushFollow(FOLLOW_ruleTable_in_entryRuleTable2123);
            iv_ruleTable=ruleTable();

            state._fsp--;

             current =iv_ruleTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTable2133); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTable"


    // $ANTLR start "ruleTable"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1004:1: ruleTable returns [EObject current=null] : ( (lv_tbl_0_0= RULE_ID ) ) ;
    public final EObject ruleTable() throws RecognitionException {
        EObject current = null;

        Token lv_tbl_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1007:28: ( ( (lv_tbl_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1008:1: ( (lv_tbl_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1008:1: ( (lv_tbl_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1009:1: (lv_tbl_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1009:1: (lv_tbl_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1010:3: lv_tbl_0_0= RULE_ID
            {
            lv_tbl_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTable2174); 

            			newLeafNode(lv_tbl_0_0, grammarAccess.getTableAccess().getTblIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTableRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"tbl",
                    		lv_tbl_0_0, 
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
    // $ANTLR end "ruleTable"


    // $ANTLR start "entryRuleTableAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1034:1: entryRuleTableAlias returns [EObject current=null] : iv_ruleTableAlias= ruleTableAlias EOF ;
    public final EObject entryRuleTableAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1035:2: (iv_ruleTableAlias= ruleTableAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1036:2: iv_ruleTableAlias= ruleTableAlias EOF
            {
             newCompositeNode(grammarAccess.getTableAliasRule()); 
            pushFollow(FOLLOW_ruleTableAlias_in_entryRuleTableAlias2214);
            iv_ruleTableAlias=ruleTableAlias();

            state._fsp--;

             current =iv_ruleTableAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableAlias2224); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTableAlias"


    // $ANTLR start "ruleTableAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1043:1: ruleTableAlias returns [EObject current=null] : ( (lv_tblAlias_0_0= RULE_ID ) ) ;
    public final EObject ruleTableAlias() throws RecognitionException {
        EObject current = null;

        Token lv_tblAlias_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1046:28: ( ( (lv_tblAlias_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1047:1: ( (lv_tblAlias_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1047:1: ( (lv_tblAlias_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1048:1: (lv_tblAlias_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1048:1: (lv_tblAlias_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1049:3: lv_tblAlias_0_0= RULE_ID
            {
            lv_tblAlias_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTableAlias2265); 

            			newLeafNode(lv_tblAlias_0_0, grammarAccess.getTableAliasAccess().getTblAliasIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTableAliasRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"tblAlias",
                    		lv_tblAlias_0_0, 
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
    // $ANTLR end "ruleTableAlias"


    // $ANTLR start "entryRuleSchema"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1073:1: entryRuleSchema returns [EObject current=null] : iv_ruleSchema= ruleSchema EOF ;
    public final EObject entryRuleSchema() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSchema = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1074:2: (iv_ruleSchema= ruleSchema EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1075:2: iv_ruleSchema= ruleSchema EOF
            {
             newCompositeNode(grammarAccess.getSchemaRule()); 
            pushFollow(FOLLOW_ruleSchema_in_entryRuleSchema2305);
            iv_ruleSchema=ruleSchema();

            state._fsp--;

             current =iv_ruleSchema; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSchema2315); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSchema"


    // $ANTLR start "ruleSchema"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1082:1: ruleSchema returns [EObject current=null] : ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) ) ;
    public final EObject ruleSchema() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_schem_2_0=null;
        Token lv_schem_3_0=null;
        EObject this_Database_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1085:28: ( ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1086:1: ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1086:1: ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_ID) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==22) ) {
                    int LA20_2 = input.LA(3);

                    if ( (LA20_2==RULE_ID) ) {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            int LA20_5 = input.LA(5);

                            if ( (LA20_5==RULE_ID) ) {
                                alt20=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 20, 5, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EOF:
                            {
                            int LA20_6 = input.LA(5);

                            if ( (LA20_6==EOF) ) {
                                alt20=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 20, 6, input);

                                throw nvae;
                            }
                            }
                            break;
                        case RULE_ID:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 23:
                            {
                            alt20=2;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 20, 4, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA20_1==EOF) ) {
                    alt20=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1086:2: (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1086:2: (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1087:5: this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) )
                    {
                     
                            newCompositeNode(grammarAccess.getSchemaAccess().getDatabaseParserRuleCall_0_0()); 
                        
                    pushFollow(FOLLOW_ruleDatabase_in_ruleSchema2363);
                    this_Database_0=ruleDatabase();

                    state._fsp--;

                     
                            current = this_Database_0; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleSchema2374); 

                        	newLeafNode(otherlv_1, grammarAccess.getSchemaAccess().getFullStopKeyword_0_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1099:1: ( (lv_schem_2_0= RULE_ID ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1100:1: (lv_schem_2_0= RULE_ID )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1100:1: (lv_schem_2_0= RULE_ID )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1101:3: lv_schem_2_0= RULE_ID
                    {
                    lv_schem_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSchema2391); 

                    			newLeafNode(lv_schem_2_0, grammarAccess.getSchemaAccess().getSchemIDTerminalRuleCall_0_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSchemaRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"schem",
                            		lv_schem_2_0, 
                            		"ID");
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1118:6: ( (lv_schem_3_0= RULE_ID ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1118:6: ( (lv_schem_3_0= RULE_ID ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1119:1: (lv_schem_3_0= RULE_ID )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1119:1: (lv_schem_3_0= RULE_ID )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1120:3: lv_schem_3_0= RULE_ID
                    {
                    lv_schem_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSchema2420); 

                    			newLeafNode(lv_schem_3_0, grammarAccess.getSchemaAccess().getSchemIDTerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSchemaRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"schem",
                            		lv_schem_3_0, 
                            		"ID");
                    	    

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
    // $ANTLR end "ruleSchema"


    // $ANTLR start "entryRuleDatabase"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1144:1: entryRuleDatabase returns [EObject current=null] : iv_ruleDatabase= ruleDatabase EOF ;
    public final EObject entryRuleDatabase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatabase = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1145:2: (iv_ruleDatabase= ruleDatabase EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1146:2: iv_ruleDatabase= ruleDatabase EOF
            {
             newCompositeNode(grammarAccess.getDatabaseRule()); 
            pushFollow(FOLLOW_ruleDatabase_in_entryRuleDatabase2461);
            iv_ruleDatabase=ruleDatabase();

            state._fsp--;

             current =iv_ruleDatabase; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDatabase2471); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatabase"


    // $ANTLR start "ruleDatabase"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1153:1: ruleDatabase returns [EObject current=null] : ( (lv_dbName_0_0= RULE_ID ) ) ;
    public final EObject ruleDatabase() throws RecognitionException {
        EObject current = null;

        Token lv_dbName_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1156:28: ( ( (lv_dbName_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1157:1: ( (lv_dbName_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1157:1: ( (lv_dbName_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1158:1: (lv_dbName_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1158:1: (lv_dbName_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1159:3: lv_dbName_0_0= RULE_ID
            {
            lv_dbName_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDatabase2512); 

            			newLeafNode(lv_dbName_0_0, grammarAccess.getDatabaseAccess().getDbNameIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDatabaseRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"dbName",
                    		lv_dbName_0_0, 
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
    // $ANTLR end "ruleDatabase"


    // $ANTLR start "entryRuleWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1183:1: entryRuleWhereEntry returns [EObject current=null] : iv_ruleWhereEntry= ruleWhereEntry EOF ;
    public final EObject entryRuleWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1184:2: (iv_ruleWhereEntry= ruleWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1185:2: iv_ruleWhereEntry= ruleWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getWhereEntryRule()); 
            pushFollow(FOLLOW_ruleWhereEntry_in_entryRuleWhereEntry2552);
            iv_ruleWhereEntry=ruleWhereEntry();

            state._fsp--;

             current =iv_ruleWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWhereEntry2562); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWhereEntry"


    // $ANTLR start "ruleWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1192:1: ruleWhereEntry returns [EObject current=null] : (this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )? ) ;
    public final EObject ruleWhereEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AndWhereEntry_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1195:28: ( (this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1196:1: (this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1196:1: (this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1197:5: this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getWhereEntryAccess().getAndWhereEntryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAndWhereEntry_in_ruleWhereEntry2609);
            this_AndWhereEntry_0=ruleAndWhereEntry();

            state._fsp--;

             
                    current = this_AndWhereEntry_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1205:1: ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==24) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1205:2: () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1205:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1206:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1211:2: (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+
                    int cnt21=0;
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==24) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1211:4: otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) )
                    	    {
                    	    otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleWhereEntry2631); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getWhereEntryAccess().getORKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1215:1: ( (lv_entries_3_0= ruleAndWhereEntry ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1216:1: (lv_entries_3_0= ruleAndWhereEntry )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1216:1: (lv_entries_3_0= ruleAndWhereEntry )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1217:3: lv_entries_3_0= ruleAndWhereEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getWhereEntryAccess().getEntriesAndWhereEntryParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAndWhereEntry_in_ruleWhereEntry2652);
                    	    lv_entries_3_0=ruleAndWhereEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getWhereEntryRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"AndWhereEntry");
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
    // $ANTLR end "ruleWhereEntry"


    // $ANTLR start "entryRuleAndWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1241:1: entryRuleAndWhereEntry returns [EObject current=null] : iv_ruleAndWhereEntry= ruleAndWhereEntry EOF ;
    public final EObject entryRuleAndWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1242:2: (iv_ruleAndWhereEntry= ruleAndWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1243:2: iv_ruleAndWhereEntry= ruleAndWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getAndWhereEntryRule()); 
            pushFollow(FOLLOW_ruleAndWhereEntry_in_entryRuleAndWhereEntry2692);
            iv_ruleAndWhereEntry=ruleAndWhereEntry();

            state._fsp--;

             current =iv_ruleAndWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndWhereEntry2702); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndWhereEntry"


    // $ANTLR start "ruleAndWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1250:1: ruleAndWhereEntry returns [EObject current=null] : (this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )? ) ;
    public final EObject ruleAndWhereEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ConcreteWhereEntry_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1253:28: ( (this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1254:1: (this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1254:1: (this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1255:5: this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getAndWhereEntryAccess().getConcreteWhereEntryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_ruleAndWhereEntry2749);
            this_ConcreteWhereEntry_0=ruleConcreteWhereEntry();

            state._fsp--;

             
                    current = this_ConcreteWhereEntry_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1263:1: ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==25) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1263:2: () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1263:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1264:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1269:2: (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+
                    int cnt23=0;
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==25) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1269:4: otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) )
                    	    {
                    	    otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleAndWhereEntry2771); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getAndWhereEntryAccess().getANDKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1273:1: ( (lv_entries_3_0= ruleConcreteWhereEntry ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1274:1: (lv_entries_3_0= ruleConcreteWhereEntry )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1274:1: (lv_entries_3_0= ruleConcreteWhereEntry )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1275:3: lv_entries_3_0= ruleConcreteWhereEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAndWhereEntryAccess().getEntriesConcreteWhereEntryParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleConcreteWhereEntry_in_ruleAndWhereEntry2792);
                    	    lv_entries_3_0=ruleConcreteWhereEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getAndWhereEntryRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"ConcreteWhereEntry");
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
    // $ANTLR end "ruleAndWhereEntry"


    // $ANTLR start "entryRuleConcreteWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1299:1: entryRuleConcreteWhereEntry returns [EObject current=null] : iv_ruleConcreteWhereEntry= ruleConcreteWhereEntry EOF ;
    public final EObject entryRuleConcreteWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConcreteWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1300:2: (iv_ruleConcreteWhereEntry= ruleConcreteWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1301:2: iv_ruleConcreteWhereEntry= ruleConcreteWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getConcreteWhereEntryRule()); 
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_entryRuleConcreteWhereEntry2832);
            iv_ruleConcreteWhereEntry=ruleConcreteWhereEntry();

            state._fsp--;

             current =iv_ruleConcreteWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConcreteWhereEntry2842); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConcreteWhereEntry"


    // $ANTLR start "ruleConcreteWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1308:1: ruleConcreteWhereEntry returns [EObject current=null] : (this_ParWhereEntry_0= ruleParWhereEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry ) ;
    public final EObject ruleConcreteWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject this_ParWhereEntry_0 = null;

        EObject this_ExpressionWhereEntry_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1311:28: ( (this_ParWhereEntry_0= ruleParWhereEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1312:1: (this_ParWhereEntry_0= ruleParWhereEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1312:1: (this_ParWhereEntry_0= ruleParWhereEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==26) ) {
                alt25=1;
            }
            else if ( (LA25_0==RULE_ID) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1313:5: this_ParWhereEntry_0= ruleParWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getConcreteWhereEntryAccess().getParWhereEntryParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleParWhereEntry_in_ruleConcreteWhereEntry2889);
                    this_ParWhereEntry_0=ruleParWhereEntry();

                    state._fsp--;

                     
                            current = this_ParWhereEntry_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1323:5: this_ExpressionWhereEntry_1= ruleExpressionWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getConcreteWhereEntryAccess().getExpressionWhereEntryParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleExpressionWhereEntry_in_ruleConcreteWhereEntry2916);
                    this_ExpressionWhereEntry_1=ruleExpressionWhereEntry();

                    state._fsp--;

                     
                            current = this_ExpressionWhereEntry_1; 
                            afterParserOrEnumRuleCall();
                        

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
    // $ANTLR end "ruleConcreteWhereEntry"


    // $ANTLR start "entryRuleParWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1339:1: entryRuleParWhereEntry returns [EObject current=null] : iv_ruleParWhereEntry= ruleParWhereEntry EOF ;
    public final EObject entryRuleParWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1340:2: (iv_ruleParWhereEntry= ruleParWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1341:2: iv_ruleParWhereEntry= ruleParWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getParWhereEntryRule()); 
            pushFollow(FOLLOW_ruleParWhereEntry_in_entryRuleParWhereEntry2951);
            iv_ruleParWhereEntry=ruleParWhereEntry();

            state._fsp--;

             current =iv_ruleParWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParWhereEntry2961); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParWhereEntry"


    // $ANTLR start "ruleParWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1348:1: ruleParWhereEntry returns [EObject current=null] : (otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')' ) ;
    public final EObject ruleParWhereEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_WhereEntry_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1351:28: ( (otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1352:1: (otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1352:1: (otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1352:3: otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleParWhereEntry2998); 

                	newLeafNode(otherlv_0, grammarAccess.getParWhereEntryAccess().getLeftParenthesisKeyword_0());
                
             
                    newCompositeNode(grammarAccess.getParWhereEntryAccess().getWhereEntryParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleWhereEntry_in_ruleParWhereEntry3020);
            this_WhereEntry_1=ruleWhereEntry();

            state._fsp--;

             
                    current = this_WhereEntry_1; 
                    afterParserOrEnumRuleCall();
                
            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleParWhereEntry3031); 

                	newLeafNode(otherlv_2, grammarAccess.getParWhereEntryAccess().getRightParenthesisKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParWhereEntry"


    // $ANTLR start "entryRuleHavingEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1377:1: entryRuleHavingEntry returns [EObject current=null] : iv_ruleHavingEntry= ruleHavingEntry EOF ;
    public final EObject entryRuleHavingEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHavingEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1378:2: (iv_ruleHavingEntry= ruleHavingEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1379:2: iv_ruleHavingEntry= ruleHavingEntry EOF
            {
             newCompositeNode(grammarAccess.getHavingEntryRule()); 
            pushFollow(FOLLOW_ruleHavingEntry_in_entryRuleHavingEntry3067);
            iv_ruleHavingEntry=ruleHavingEntry();

            state._fsp--;

             current =iv_ruleHavingEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleHavingEntry3077); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHavingEntry"


    // $ANTLR start "ruleHavingEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1386:1: ruleHavingEntry returns [EObject current=null] : (this_AndHavingEntry_0= ruleAndHavingEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) ) )+ )? ) ;
    public final EObject ruleHavingEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AndHavingEntry_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1389:28: ( (this_AndHavingEntry_0= ruleAndHavingEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1390:1: (this_AndHavingEntry_0= ruleAndHavingEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1390:1: (this_AndHavingEntry_0= ruleAndHavingEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1391:5: this_AndHavingEntry_0= ruleAndHavingEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getHavingEntryAccess().getAndHavingEntryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAndHavingEntry_in_ruleHavingEntry3124);
            this_AndHavingEntry_0=ruleAndHavingEntry();

            state._fsp--;

             
                    current = this_AndHavingEntry_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1399:1: ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) ) )+ )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==24) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1399:2: () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1399:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1400:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getHavingEntryAccess().getOrHavingEntryEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1405:2: (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) ) )+
                    int cnt26=0;
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==24) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1405:4: otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndHavingEntry ) )
                    	    {
                    	    otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleHavingEntry3146); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getHavingEntryAccess().getORKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1409:1: ( (lv_entries_3_0= ruleAndHavingEntry ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1410:1: (lv_entries_3_0= ruleAndHavingEntry )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1410:1: (lv_entries_3_0= ruleAndHavingEntry )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1411:3: lv_entries_3_0= ruleAndHavingEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getHavingEntryAccess().getEntriesAndHavingEntryParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAndHavingEntry_in_ruleHavingEntry3167);
                    	    lv_entries_3_0=ruleAndHavingEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getHavingEntryRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"AndHavingEntry");
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
    // $ANTLR end "ruleHavingEntry"


    // $ANTLR start "entryRuleAndHavingEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1435:1: entryRuleAndHavingEntry returns [EObject current=null] : iv_ruleAndHavingEntry= ruleAndHavingEntry EOF ;
    public final EObject entryRuleAndHavingEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndHavingEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1436:2: (iv_ruleAndHavingEntry= ruleAndHavingEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1437:2: iv_ruleAndHavingEntry= ruleAndHavingEntry EOF
            {
             newCompositeNode(grammarAccess.getAndHavingEntryRule()); 
            pushFollow(FOLLOW_ruleAndHavingEntry_in_entryRuleAndHavingEntry3207);
            iv_ruleAndHavingEntry=ruleAndHavingEntry();

            state._fsp--;

             current =iv_ruleAndHavingEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndHavingEntry3217); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndHavingEntry"


    // $ANTLR start "ruleAndHavingEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1444:1: ruleAndHavingEntry returns [EObject current=null] : (this_ConcreteHavingEntry_0= ruleConcreteHavingEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) ) )+ )? ) ;
    public final EObject ruleAndHavingEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ConcreteHavingEntry_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1447:28: ( (this_ConcreteHavingEntry_0= ruleConcreteHavingEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1448:1: (this_ConcreteHavingEntry_0= ruleConcreteHavingEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1448:1: (this_ConcreteHavingEntry_0= ruleConcreteHavingEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1449:5: this_ConcreteHavingEntry_0= ruleConcreteHavingEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getAndHavingEntryAccess().getConcreteHavingEntryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleConcreteHavingEntry_in_ruleAndHavingEntry3264);
            this_ConcreteHavingEntry_0=ruleConcreteHavingEntry();

            state._fsp--;

             
                    current = this_ConcreteHavingEntry_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1457:1: ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) ) )+ )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==25) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1457:2: () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1457:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1458:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getAndHavingEntryAccess().getAndHavingEntryEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1463:2: (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) ) )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==25) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1463:4: otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteHavingEntry ) )
                    	    {
                    	    otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleAndHavingEntry3286); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getAndHavingEntryAccess().getANDKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1467:1: ( (lv_entries_3_0= ruleConcreteHavingEntry ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1468:1: (lv_entries_3_0= ruleConcreteHavingEntry )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1468:1: (lv_entries_3_0= ruleConcreteHavingEntry )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1469:3: lv_entries_3_0= ruleConcreteHavingEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAndHavingEntryAccess().getEntriesConcreteHavingEntryParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleConcreteHavingEntry_in_ruleAndHavingEntry3307);
                    	    lv_entries_3_0=ruleConcreteHavingEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getAndHavingEntryRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"ConcreteHavingEntry");
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
    // $ANTLR end "ruleAndHavingEntry"


    // $ANTLR start "entryRuleConcreteHavingEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1493:1: entryRuleConcreteHavingEntry returns [EObject current=null] : iv_ruleConcreteHavingEntry= ruleConcreteHavingEntry EOF ;
    public final EObject entryRuleConcreteHavingEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConcreteHavingEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1494:2: (iv_ruleConcreteHavingEntry= ruleConcreteHavingEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1495:2: iv_ruleConcreteHavingEntry= ruleConcreteHavingEntry EOF
            {
             newCompositeNode(grammarAccess.getConcreteHavingEntryRule()); 
            pushFollow(FOLLOW_ruleConcreteHavingEntry_in_entryRuleConcreteHavingEntry3347);
            iv_ruleConcreteHavingEntry=ruleConcreteHavingEntry();

            state._fsp--;

             current =iv_ruleConcreteHavingEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConcreteHavingEntry3357); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConcreteHavingEntry"


    // $ANTLR start "ruleConcreteHavingEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1502:1: ruleConcreteHavingEntry returns [EObject current=null] : (this_ParHavingEntry_0= ruleParHavingEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry ) ;
    public final EObject ruleConcreteHavingEntry() throws RecognitionException {
        EObject current = null;

        EObject this_ParHavingEntry_0 = null;

        EObject this_ExpressionWhereEntry_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1505:28: ( (this_ParHavingEntry_0= ruleParHavingEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1506:1: (this_ParHavingEntry_0= ruleParHavingEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1506:1: (this_ParHavingEntry_0= ruleParHavingEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==26) ) {
                alt30=1;
            }
            else if ( (LA30_0==RULE_ID) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1507:5: this_ParHavingEntry_0= ruleParHavingEntry
                    {
                     
                            newCompositeNode(grammarAccess.getConcreteHavingEntryAccess().getParHavingEntryParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleParHavingEntry_in_ruleConcreteHavingEntry3404);
                    this_ParHavingEntry_0=ruleParHavingEntry();

                    state._fsp--;

                     
                            current = this_ParHavingEntry_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1517:5: this_ExpressionWhereEntry_1= ruleExpressionWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getConcreteHavingEntryAccess().getExpressionWhereEntryParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleExpressionWhereEntry_in_ruleConcreteHavingEntry3431);
                    this_ExpressionWhereEntry_1=ruleExpressionWhereEntry();

                    state._fsp--;

                     
                            current = this_ExpressionWhereEntry_1; 
                            afterParserOrEnumRuleCall();
                        

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
    // $ANTLR end "ruleConcreteHavingEntry"


    // $ANTLR start "entryRuleParHavingEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1533:1: entryRuleParHavingEntry returns [EObject current=null] : iv_ruleParHavingEntry= ruleParHavingEntry EOF ;
    public final EObject entryRuleParHavingEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParHavingEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1534:2: (iv_ruleParHavingEntry= ruleParHavingEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1535:2: iv_ruleParHavingEntry= ruleParHavingEntry EOF
            {
             newCompositeNode(grammarAccess.getParHavingEntryRule()); 
            pushFollow(FOLLOW_ruleParHavingEntry_in_entryRuleParHavingEntry3466);
            iv_ruleParHavingEntry=ruleParHavingEntry();

            state._fsp--;

             current =iv_ruleParHavingEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParHavingEntry3476); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParHavingEntry"


    // $ANTLR start "ruleParHavingEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1542:1: ruleParHavingEntry returns [EObject current=null] : (otherlv_0= '(' this_HavingEntry_1= ruleHavingEntry otherlv_2= ')' ) ;
    public final EObject ruleParHavingEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_HavingEntry_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1545:28: ( (otherlv_0= '(' this_HavingEntry_1= ruleHavingEntry otherlv_2= ')' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1546:1: (otherlv_0= '(' this_HavingEntry_1= ruleHavingEntry otherlv_2= ')' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1546:1: (otherlv_0= '(' this_HavingEntry_1= ruleHavingEntry otherlv_2= ')' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1546:3: otherlv_0= '(' this_HavingEntry_1= ruleHavingEntry otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleParHavingEntry3513); 

                	newLeafNode(otherlv_0, grammarAccess.getParHavingEntryAccess().getLeftParenthesisKeyword_0());
                
             
                    newCompositeNode(grammarAccess.getParHavingEntryAccess().getHavingEntryParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleHavingEntry_in_ruleParHavingEntry3535);
            this_HavingEntry_1=ruleHavingEntry();

            state._fsp--;

             
                    current = this_HavingEntry_1; 
                    afterParserOrEnumRuleCall();
                
            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleParHavingEntry3546); 

                	newLeafNode(otherlv_2, grammarAccess.getParHavingEntryAccess().getRightParenthesisKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParHavingEntry"


    // $ANTLR start "entryRuleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1571:1: entryRuleExpressionWhereEntry returns [EObject current=null] : iv_ruleExpressionWhereEntry= ruleExpressionWhereEntry EOF ;
    public final EObject entryRuleExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1572:2: (iv_ruleExpressionWhereEntry= ruleExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1573:2: iv_ruleExpressionWhereEntry= ruleExpressionWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleExpressionWhereEntry_in_entryRuleExpressionWhereEntry3582);
            iv_ruleExpressionWhereEntry=ruleExpressionWhereEntry();

            state._fsp--;

             current =iv_ruleExpressionWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionWhereEntry3592); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpressionWhereEntry"


    // $ANTLR start "ruleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1580:1: ruleExpressionWhereEntry returns [EObject current=null] : (this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry | this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry ) ;
    public final EObject ruleExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject this_SingleExpressionWhereEntry_0 = null;

        EObject this_MultiExpressionWhereEntry_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1583:28: ( (this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry | this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1584:1: (this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry | this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1584:1: (this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry | this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                    {
                    alt31=1;
                    }
                    break;
                case 35:
                    {
                    int LA31_3 = input.LA(3);

                    if ( ((LA31_3>=RULE_SIGNED_DOUBLE && LA31_3<=RULE_DATE)||(LA31_3>=28 && LA31_3<=31)) ) {
                        alt31=1;
                    }
                    else if ( (LA31_3==32) ) {
                        alt31=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case 34:
                    {
                    int LA31_4 = input.LA(3);

                    if ( ((LA31_4>=RULE_SIGNED_DOUBLE && LA31_4<=RULE_DATE)||(LA31_4>=28 && LA31_4<=31)) ) {
                        alt31=1;
                    }
                    else if ( (LA31_4==32) ) {
                        alt31=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1585:5: this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionWhereEntryAccess().getSingleExpressionWhereEntryParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleSingleExpressionWhereEntry_in_ruleExpressionWhereEntry3639);
                    this_SingleExpressionWhereEntry_0=ruleSingleExpressionWhereEntry();

                    state._fsp--;

                     
                            current = this_SingleExpressionWhereEntry_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1595:5: this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionWhereEntryAccess().getMultiExpressionWhereEntryParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleMultiExpressionWhereEntry_in_ruleExpressionWhereEntry3666);
                    this_MultiExpressionWhereEntry_1=ruleMultiExpressionWhereEntry();

                    state._fsp--;

                     
                            current = this_MultiExpressionWhereEntry_1; 
                            afterParserOrEnumRuleCall();
                        

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
    // $ANTLR end "ruleExpressionWhereEntry"


    // $ANTLR start "entryRuleSingleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1611:1: entryRuleSingleExpressionWhereEntry returns [EObject current=null] : iv_ruleSingleExpressionWhereEntry= ruleSingleExpressionWhereEntry EOF ;
    public final EObject entryRuleSingleExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingleExpressionWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1612:2: (iv_ruleSingleExpressionWhereEntry= ruleSingleExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1613:2: iv_ruleSingleExpressionWhereEntry= ruleSingleExpressionWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getSingleExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleSingleExpressionWhereEntry_in_entryRuleSingleExpressionWhereEntry3701);
            iv_ruleSingleExpressionWhereEntry=ruleSingleExpressionWhereEntry();

            state._fsp--;

             current =iv_ruleSingleExpressionWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSingleExpressionWhereEntry3711); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSingleExpressionWhereEntry"


    // $ANTLR start "ruleSingleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1620:1: ruleSingleExpressionWhereEntry returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) ) ) ;
    public final EObject ruleSingleExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Enumerator lv_operator_1_0 = null;

        EObject lv_rhs_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1623:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1624:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1624:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1624:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1624:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1625:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1625:1: (lv_name_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1626:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSingleExpressionWhereEntry3753); 

            			newLeafNode(lv_name_0_0, grammarAccess.getSingleExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSingleExpressionWhereEntryRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1642:2: ( (lv_operator_1_0= ruleOperator ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1643:1: (lv_operator_1_0= ruleOperator )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1643:1: (lv_operator_1_0= ruleOperator )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1644:3: lv_operator_1_0= ruleOperator
            {
             
            	        newCompositeNode(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorOperatorEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperator_in_ruleSingleExpressionWhereEntry3779);
            lv_operator_1_0=ruleOperator();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSingleExpressionWhereEntryRule());
            	        }
                   		set(
                   			current, 
                   			"operator",
                    		lv_operator_1_0, 
                    		"Operator");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1660:2: ( (lv_rhs_2_0= ruleExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1661:1: (lv_rhs_2_0= ruleExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1661:1: (lv_rhs_2_0= ruleExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1662:3: lv_rhs_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleSingleExpressionWhereEntry3800);
            lv_rhs_2_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSingleExpressionWhereEntryRule());
            	        }
                   		set(
                   			current, 
                   			"rhs",
                    		lv_rhs_2_0, 
                    		"Expression");
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
    // $ANTLR end "ruleSingleExpressionWhereEntry"


    // $ANTLR start "entryRuleExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1686:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1687:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1688:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression3836);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression3846); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1695:1: ruleExpression returns [EObject current=null] : (this_DoubleExpression_0= ruleDoubleExpression | this_LongExpression_1= ruleLongExpression | this_StringExpression_2= ruleStringExpression | this_NullExpression_3= ruleNullExpression | this_DateExpression_4= ruleDateExpression | this_BooleanExpression_5= ruleBooleanExpression | this_ReplacableValue_6= ruleReplacableValue ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_DoubleExpression_0 = null;

        EObject this_LongExpression_1 = null;

        EObject this_StringExpression_2 = null;

        EObject this_NullExpression_3 = null;

        EObject this_DateExpression_4 = null;

        EObject this_BooleanExpression_5 = null;

        EObject this_ReplacableValue_6 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1698:28: ( (this_DoubleExpression_0= ruleDoubleExpression | this_LongExpression_1= ruleLongExpression | this_StringExpression_2= ruleStringExpression | this_NullExpression_3= ruleNullExpression | this_DateExpression_4= ruleDateExpression | this_BooleanExpression_5= ruleBooleanExpression | this_ReplacableValue_6= ruleReplacableValue ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1699:1: (this_DoubleExpression_0= ruleDoubleExpression | this_LongExpression_1= ruleLongExpression | this_StringExpression_2= ruleStringExpression | this_NullExpression_3= ruleNullExpression | this_DateExpression_4= ruleDateExpression | this_BooleanExpression_5= ruleBooleanExpression | this_ReplacableValue_6= ruleReplacableValue )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1699:1: (this_DoubleExpression_0= ruleDoubleExpression | this_LongExpression_1= ruleLongExpression | this_StringExpression_2= ruleStringExpression | this_NullExpression_3= ruleNullExpression | this_DateExpression_4= ruleDateExpression | this_BooleanExpression_5= ruleBooleanExpression | this_ReplacableValue_6= ruleReplacableValue )
            int alt32=7;
            switch ( input.LA(1) ) {
            case RULE_SIGNED_DOUBLE:
                {
                alt32=1;
                }
                break;
            case RULE_SINGED_LONG:
                {
                alt32=2;
                }
                break;
            case RULE_STRING:
                {
                alt32=3;
                }
                break;
            case 29:
                {
                alt32=4;
                }
                break;
            case RULE_DATE:
                {
                alt32=5;
                }
                break;
            case 30:
            case 31:
                {
                alt32=6;
                }
                break;
            case 28:
                {
                alt32=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1700:5: this_DoubleExpression_0= ruleDoubleExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getDoubleExpressionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleDoubleExpression_in_ruleExpression3893);
                    this_DoubleExpression_0=ruleDoubleExpression();

                    state._fsp--;

                     
                            current = this_DoubleExpression_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1710:5: this_LongExpression_1= ruleLongExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getLongExpressionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLongExpression_in_ruleExpression3920);
                    this_LongExpression_1=ruleLongExpression();

                    state._fsp--;

                     
                            current = this_LongExpression_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1720:5: this_StringExpression_2= ruleStringExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getStringExpressionParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleStringExpression_in_ruleExpression3947);
                    this_StringExpression_2=ruleStringExpression();

                    state._fsp--;

                     
                            current = this_StringExpression_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1730:5: this_NullExpression_3= ruleNullExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getNullExpressionParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleNullExpression_in_ruleExpression3974);
                    this_NullExpression_3=ruleNullExpression();

                    state._fsp--;

                     
                            current = this_NullExpression_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1740:5: this_DateExpression_4= ruleDateExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getDateExpressionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleDateExpression_in_ruleExpression4001);
                    this_DateExpression_4=ruleDateExpression();

                    state._fsp--;

                     
                            current = this_DateExpression_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1750:5: this_BooleanExpression_5= ruleBooleanExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getBooleanExpressionParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleBooleanExpression_in_ruleExpression4028);
                    this_BooleanExpression_5=ruleBooleanExpression();

                    state._fsp--;

                     
                            current = this_BooleanExpression_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1760:5: this_ReplacableValue_6= ruleReplacableValue
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getReplacableValueParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleReplacableValue_in_ruleExpression4055);
                    this_ReplacableValue_6=ruleReplacableValue();

                    state._fsp--;

                     
                            current = this_ReplacableValue_6; 
                            afterParserOrEnumRuleCall();
                        

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
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleReplacableValue"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1776:1: entryRuleReplacableValue returns [EObject current=null] : iv_ruleReplacableValue= ruleReplacableValue EOF ;
    public final EObject entryRuleReplacableValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReplacableValue = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1777:2: (iv_ruleReplacableValue= ruleReplacableValue EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1778:2: iv_ruleReplacableValue= ruleReplacableValue EOF
            {
             newCompositeNode(grammarAccess.getReplacableValueRule()); 
            pushFollow(FOLLOW_ruleReplacableValue_in_entryRuleReplacableValue4090);
            iv_ruleReplacableValue=ruleReplacableValue();

            state._fsp--;

             current =iv_ruleReplacableValue; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleReplacableValue4100); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReplacableValue"


    // $ANTLR start "ruleReplacableValue"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1785:1: ruleReplacableValue returns [EObject current=null] : ( (lv_value_0_0= '?' ) ) ;
    public final EObject ruleReplacableValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1788:28: ( ( (lv_value_0_0= '?' ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1789:1: ( (lv_value_0_0= '?' ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1789:1: ( (lv_value_0_0= '?' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1790:1: (lv_value_0_0= '?' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1790:1: (lv_value_0_0= '?' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1791:3: lv_value_0_0= '?'
            {
            lv_value_0_0=(Token)match(input,28,FOLLOW_28_in_ruleReplacableValue4142); 

                    newLeafNode(lv_value_0_0, grammarAccess.getReplacableValueAccess().getValueQuestionMarkKeyword_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getReplacableValueRule());
            	        }
                   		setWithLastConsumed(current, "value", lv_value_0_0, "?");
            	    

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
    // $ANTLR end "ruleReplacableValue"


    // $ANTLR start "entryRuleDoubleExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1812:1: entryRuleDoubleExpression returns [EObject current=null] : iv_ruleDoubleExpression= ruleDoubleExpression EOF ;
    public final EObject entryRuleDoubleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoubleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1813:2: (iv_ruleDoubleExpression= ruleDoubleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1814:2: iv_ruleDoubleExpression= ruleDoubleExpression EOF
            {
             newCompositeNode(grammarAccess.getDoubleExpressionRule()); 
            pushFollow(FOLLOW_ruleDoubleExpression_in_entryRuleDoubleExpression4190);
            iv_ruleDoubleExpression=ruleDoubleExpression();

            state._fsp--;

             current =iv_ruleDoubleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleExpression4200); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDoubleExpression"


    // $ANTLR start "ruleDoubleExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1821:1: ruleDoubleExpression returns [EObject current=null] : ( (lv_value_0_0= RULE_SIGNED_DOUBLE ) ) ;
    public final EObject ruleDoubleExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1824:28: ( ( (lv_value_0_0= RULE_SIGNED_DOUBLE ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1825:1: ( (lv_value_0_0= RULE_SIGNED_DOUBLE ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1825:1: ( (lv_value_0_0= RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1826:1: (lv_value_0_0= RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1826:1: (lv_value_0_0= RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1827:3: lv_value_0_0= RULE_SIGNED_DOUBLE
            {
            lv_value_0_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleExpression4241); 

            			newLeafNode(lv_value_0_0, grammarAccess.getDoubleExpressionAccess().getValueSIGNED_DOUBLETerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDoubleExpressionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_0_0, 
                    		"SIGNED_DOUBLE");
            	    

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
    // $ANTLR end "ruleDoubleExpression"


    // $ANTLR start "entryRuleLongExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1851:1: entryRuleLongExpression returns [EObject current=null] : iv_ruleLongExpression= ruleLongExpression EOF ;
    public final EObject entryRuleLongExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLongExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1852:2: (iv_ruleLongExpression= ruleLongExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1853:2: iv_ruleLongExpression= ruleLongExpression EOF
            {
             newCompositeNode(grammarAccess.getLongExpressionRule()); 
            pushFollow(FOLLOW_ruleLongExpression_in_entryRuleLongExpression4281);
            iv_ruleLongExpression=ruleLongExpression();

            state._fsp--;

             current =iv_ruleLongExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongExpression4291); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLongExpression"


    // $ANTLR start "ruleLongExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1860:1: ruleLongExpression returns [EObject current=null] : ( (lv_value_0_0= RULE_SINGED_LONG ) ) ;
    public final EObject ruleLongExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1863:28: ( ( (lv_value_0_0= RULE_SINGED_LONG ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1864:1: ( (lv_value_0_0= RULE_SINGED_LONG ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1864:1: ( (lv_value_0_0= RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1865:1: (lv_value_0_0= RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1865:1: (lv_value_0_0= RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1866:3: lv_value_0_0= RULE_SINGED_LONG
            {
            lv_value_0_0=(Token)match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_ruleLongExpression4332); 

            			newLeafNode(lv_value_0_0, grammarAccess.getLongExpressionAccess().getValueSINGED_LONGTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLongExpressionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_0_0, 
                    		"SINGED_LONG");
            	    

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
    // $ANTLR end "ruleLongExpression"


    // $ANTLR start "entryRuleStringExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1890:1: entryRuleStringExpression returns [EObject current=null] : iv_ruleStringExpression= ruleStringExpression EOF ;
    public final EObject entryRuleStringExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1891:2: (iv_ruleStringExpression= ruleStringExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1892:2: iv_ruleStringExpression= ruleStringExpression EOF
            {
             newCompositeNode(grammarAccess.getStringExpressionRule()); 
            pushFollow(FOLLOW_ruleStringExpression_in_entryRuleStringExpression4372);
            iv_ruleStringExpression=ruleStringExpression();

            state._fsp--;

             current =iv_ruleStringExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringExpression4382); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringExpression"


    // $ANTLR start "ruleStringExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1899:1: ruleStringExpression returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1902:28: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1903:1: ( (lv_value_0_0= RULE_STRING ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1903:1: ( (lv_value_0_0= RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1904:1: (lv_value_0_0= RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1904:1: (lv_value_0_0= RULE_STRING )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1905:3: lv_value_0_0= RULE_STRING
            {
            lv_value_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringExpression4423); 

            			newLeafNode(lv_value_0_0, grammarAccess.getStringExpressionAccess().getValueSTRINGTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getStringExpressionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_0_0, 
                    		"STRING");
            	    

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
    // $ANTLR end "ruleStringExpression"


    // $ANTLR start "entryRuleNullExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1929:1: entryRuleNullExpression returns [EObject current=null] : iv_ruleNullExpression= ruleNullExpression EOF ;
    public final EObject entryRuleNullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1930:2: (iv_ruleNullExpression= ruleNullExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1931:2: iv_ruleNullExpression= ruleNullExpression EOF
            {
             newCompositeNode(grammarAccess.getNullExpressionRule()); 
            pushFollow(FOLLOW_ruleNullExpression_in_entryRuleNullExpression4463);
            iv_ruleNullExpression=ruleNullExpression();

            state._fsp--;

             current =iv_ruleNullExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullExpression4473); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNullExpression"


    // $ANTLR start "ruleNullExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1938:1: ruleNullExpression returns [EObject current=null] : ( (lv_value_0_0= 'null' ) ) ;
    public final EObject ruleNullExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1941:28: ( ( (lv_value_0_0= 'null' ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1942:1: ( (lv_value_0_0= 'null' ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1942:1: ( (lv_value_0_0= 'null' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1943:1: (lv_value_0_0= 'null' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1943:1: (lv_value_0_0= 'null' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1944:3: lv_value_0_0= 'null'
            {
            lv_value_0_0=(Token)match(input,29,FOLLOW_29_in_ruleNullExpression4515); 

                    newLeafNode(lv_value_0_0, grammarAccess.getNullExpressionAccess().getValueNullKeyword_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNullExpressionRule());
            	        }
                   		setWithLastConsumed(current, "value", lv_value_0_0, "null");
            	    

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
    // $ANTLR end "ruleNullExpression"


    // $ANTLR start "entryRuleDateExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1965:1: entryRuleDateExpression returns [EObject current=null] : iv_ruleDateExpression= ruleDateExpression EOF ;
    public final EObject entryRuleDateExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDateExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1966:2: (iv_ruleDateExpression= ruleDateExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1967:2: iv_ruleDateExpression= ruleDateExpression EOF
            {
             newCompositeNode(grammarAccess.getDateExpressionRule()); 
            pushFollow(FOLLOW_ruleDateExpression_in_entryRuleDateExpression4563);
            iv_ruleDateExpression=ruleDateExpression();

            state._fsp--;

             current =iv_ruleDateExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDateExpression4573); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDateExpression"


    // $ANTLR start "ruleDateExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1974:1: ruleDateExpression returns [EObject current=null] : ( (lv_value_0_0= RULE_DATE ) ) ;
    public final EObject ruleDateExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1977:28: ( ( (lv_value_0_0= RULE_DATE ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1978:1: ( (lv_value_0_0= RULE_DATE ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1978:1: ( (lv_value_0_0= RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1979:1: (lv_value_0_0= RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1979:1: (lv_value_0_0= RULE_DATE )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1980:3: lv_value_0_0= RULE_DATE
            {
            lv_value_0_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleDateExpression4614); 

            			newLeafNode(lv_value_0_0, grammarAccess.getDateExpressionAccess().getValueDATETerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDateExpressionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_0_0, 
                    		"DATE");
            	    

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
    // $ANTLR end "ruleDateExpression"


    // $ANTLR start "entryRuleBooleanExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2004:1: entryRuleBooleanExpression returns [EObject current=null] : iv_ruleBooleanExpression= ruleBooleanExpression EOF ;
    public final EObject entryRuleBooleanExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2005:2: (iv_ruleBooleanExpression= ruleBooleanExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2006:2: iv_ruleBooleanExpression= ruleBooleanExpression EOF
            {
             newCompositeNode(grammarAccess.getBooleanExpressionRule()); 
            pushFollow(FOLLOW_ruleBooleanExpression_in_entryRuleBooleanExpression4654);
            iv_ruleBooleanExpression=ruleBooleanExpression();

            state._fsp--;

             current =iv_ruleBooleanExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanExpression4664); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanExpression"


    // $ANTLR start "ruleBooleanExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2013:1: ruleBooleanExpression returns [EObject current=null] : ( ( (lv_true_0_0= 'true' ) ) | ( (lv_true_1_0= 'false' ) ) ) ;
    public final EObject ruleBooleanExpression() throws RecognitionException {
        EObject current = null;

        Token lv_true_0_0=null;
        Token lv_true_1_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2016:28: ( ( ( (lv_true_0_0= 'true' ) ) | ( (lv_true_1_0= 'false' ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2017:1: ( ( (lv_true_0_0= 'true' ) ) | ( (lv_true_1_0= 'false' ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2017:1: ( ( (lv_true_0_0= 'true' ) ) | ( (lv_true_1_0= 'false' ) ) )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==30) ) {
                alt33=1;
            }
            else if ( (LA33_0==31) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2017:2: ( (lv_true_0_0= 'true' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2017:2: ( (lv_true_0_0= 'true' ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2018:1: (lv_true_0_0= 'true' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2018:1: (lv_true_0_0= 'true' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2019:3: lv_true_0_0= 'true'
                    {
                    lv_true_0_0=(Token)match(input,30,FOLLOW_30_in_ruleBooleanExpression4707); 

                            newLeafNode(lv_true_0_0, grammarAccess.getBooleanExpressionAccess().getTrueTrueKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBooleanExpressionRule());
                    	        }
                           		setWithLastConsumed(current, "true", lv_true_0_0, "true");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2033:6: ( (lv_true_1_0= 'false' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2033:6: ( (lv_true_1_0= 'false' ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2034:1: (lv_true_1_0= 'false' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2034:1: (lv_true_1_0= 'false' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2035:3: lv_true_1_0= 'false'
                    {
                    lv_true_1_0=(Token)match(input,31,FOLLOW_31_in_ruleBooleanExpression4744); 

                            newLeafNode(lv_true_1_0, grammarAccess.getBooleanExpressionAccess().getTrueFalseKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBooleanExpressionRule());
                    	        }
                           		setWithLastConsumed(current, "true", lv_true_1_0, "false");
                    	    

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
    // $ANTLR end "ruleBooleanExpression"


    // $ANTLR start "entryRuleMultiExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2056:1: entryRuleMultiExpressionWhereEntry returns [EObject current=null] : iv_ruleMultiExpressionWhereEntry= ruleMultiExpressionWhereEntry EOF ;
    public final EObject entryRuleMultiExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiExpressionWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2057:2: (iv_ruleMultiExpressionWhereEntry= ruleMultiExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2058:2: iv_ruleMultiExpressionWhereEntry= ruleMultiExpressionWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getMultiExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleMultiExpressionWhereEntry_in_entryRuleMultiExpressionWhereEntry4793);
            iv_ruleMultiExpressionWhereEntry=ruleMultiExpressionWhereEntry();

            state._fsp--;

             current =iv_ruleMultiExpressionWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiExpressionWhereEntry4803); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultiExpressionWhereEntry"


    // $ANTLR start "ruleMultiExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2065:1: ruleMultiExpressionWhereEntry returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) ) ) ;
    public final EObject ruleMultiExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Enumerator lv_operator_1_0 = null;

        EObject lv_rhs_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2068:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2069:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2069:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2069:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2069:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2070:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2070:1: (lv_name_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2071:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleMultiExpressionWhereEntry4845); 

            			newLeafNode(lv_name_0_0, grammarAccess.getMultiExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMultiExpressionWhereEntryRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2087:2: ( (lv_operator_1_0= ruleArrayOperator ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2088:1: (lv_operator_1_0= ruleArrayOperator )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2088:1: (lv_operator_1_0= ruleArrayOperator )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2089:3: lv_operator_1_0= ruleArrayOperator
            {
             
            	        newCompositeNode(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorArrayOperatorEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleArrayOperator_in_ruleMultiExpressionWhereEntry4871);
            lv_operator_1_0=ruleArrayOperator();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMultiExpressionWhereEntryRule());
            	        }
                   		set(
                   			current, 
                   			"operator",
                    		lv_operator_1_0, 
                    		"ArrayOperator");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2105:2: ( (lv_rhs_2_0= ruleArrayExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2106:1: (lv_rhs_2_0= ruleArrayExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2106:1: (lv_rhs_2_0= ruleArrayExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2107:3: lv_rhs_2_0= ruleArrayExpression
            {
             
            	        newCompositeNode(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsArrayExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleArrayExpression_in_ruleMultiExpressionWhereEntry4892);
            lv_rhs_2_0=ruleArrayExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMultiExpressionWhereEntryRule());
            	        }
                   		set(
                   			current, 
                   			"rhs",
                    		lv_rhs_2_0, 
                    		"ArrayExpression");
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
    // $ANTLR end "ruleMultiExpressionWhereEntry"


    // $ANTLR start "entryRuleArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2131:1: entryRuleArrayExpression returns [EObject current=null] : iv_ruleArrayExpression= ruleArrayExpression EOF ;
    public final EObject entryRuleArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2132:2: (iv_ruleArrayExpression= ruleArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2133:2: iv_ruleArrayExpression= ruleArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleArrayExpression_in_entryRuleArrayExpression4928);
            iv_ruleArrayExpression=ruleArrayExpression();

            state._fsp--;

             current =iv_ruleArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayExpression4938); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayExpression"


    // $ANTLR start "ruleArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2140:1: ruleArrayExpression returns [EObject current=null] : (this_DoubleArrayExpression_0= ruleDoubleArrayExpression | this_LongArrayExpression_1= ruleLongArrayExpression | this_StringArrayExpression_2= ruleStringArrayExpression | this_NullArrayExpression_3= ruleNullArrayExpression | this_DateArrayExpression_4= ruleDateArrayExpression | this_BooleanArrayExpression_5= ruleBooleanArrayExpression ) ;
    public final EObject ruleArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject this_DoubleArrayExpression_0 = null;

        EObject this_LongArrayExpression_1 = null;

        EObject this_StringArrayExpression_2 = null;

        EObject this_NullArrayExpression_3 = null;

        EObject this_DateArrayExpression_4 = null;

        EObject this_BooleanArrayExpression_5 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2143:28: ( (this_DoubleArrayExpression_0= ruleDoubleArrayExpression | this_LongArrayExpression_1= ruleLongArrayExpression | this_StringArrayExpression_2= ruleStringArrayExpression | this_NullArrayExpression_3= ruleNullArrayExpression | this_DateArrayExpression_4= ruleDateArrayExpression | this_BooleanArrayExpression_5= ruleBooleanArrayExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2144:1: (this_DoubleArrayExpression_0= ruleDoubleArrayExpression | this_LongArrayExpression_1= ruleLongArrayExpression | this_StringArrayExpression_2= ruleStringArrayExpression | this_NullArrayExpression_3= ruleNullArrayExpression | this_DateArrayExpression_4= ruleDateArrayExpression | this_BooleanArrayExpression_5= ruleBooleanArrayExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2144:1: (this_DoubleArrayExpression_0= ruleDoubleArrayExpression | this_LongArrayExpression_1= ruleLongArrayExpression | this_StringArrayExpression_2= ruleStringArrayExpression | this_NullArrayExpression_3= ruleNullArrayExpression | this_DateArrayExpression_4= ruleDateArrayExpression | this_BooleanArrayExpression_5= ruleBooleanArrayExpression )
            int alt34=6;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==32) ) {
                switch ( input.LA(2) ) {
                case RULE_STRING:
                    {
                    alt34=3;
                    }
                    break;
                case RULE_SIGNED_DOUBLE:
                    {
                    alt34=1;
                    }
                    break;
                case RULE_SINGED_LONG:
                    {
                    alt34=2;
                    }
                    break;
                case 29:
                    {
                    alt34=4;
                    }
                    break;
                case RULE_BOOL:
                    {
                    alt34=6;
                    }
                    break;
                case RULE_DATE:
                    {
                    alt34=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2145:5: this_DoubleArrayExpression_0= ruleDoubleArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getDoubleArrayExpressionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleDoubleArrayExpression_in_ruleArrayExpression4985);
                    this_DoubleArrayExpression_0=ruleDoubleArrayExpression();

                    state._fsp--;

                     
                            current = this_DoubleArrayExpression_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2155:5: this_LongArrayExpression_1= ruleLongArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getLongArrayExpressionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLongArrayExpression_in_ruleArrayExpression5012);
                    this_LongArrayExpression_1=ruleLongArrayExpression();

                    state._fsp--;

                     
                            current = this_LongArrayExpression_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2165:5: this_StringArrayExpression_2= ruleStringArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getStringArrayExpressionParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleStringArrayExpression_in_ruleArrayExpression5039);
                    this_StringArrayExpression_2=ruleStringArrayExpression();

                    state._fsp--;

                     
                            current = this_StringArrayExpression_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2175:5: this_NullArrayExpression_3= ruleNullArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getNullArrayExpressionParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleNullArrayExpression_in_ruleArrayExpression5066);
                    this_NullArrayExpression_3=ruleNullArrayExpression();

                    state._fsp--;

                     
                            current = this_NullArrayExpression_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2185:5: this_DateArrayExpression_4= ruleDateArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getDateArrayExpressionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleDateArrayExpression_in_ruleArrayExpression5093);
                    this_DateArrayExpression_4=ruleDateArrayExpression();

                    state._fsp--;

                     
                            current = this_DateArrayExpression_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2195:5: this_BooleanArrayExpression_5= ruleBooleanArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getBooleanArrayExpressionParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleBooleanArrayExpression_in_ruleArrayExpression5120);
                    this_BooleanArrayExpression_5=ruleBooleanArrayExpression();

                    state._fsp--;

                     
                            current = this_BooleanArrayExpression_5; 
                            afterParserOrEnumRuleCall();
                        

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
    // $ANTLR end "ruleArrayExpression"


    // $ANTLR start "entryRuleDoubleArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2211:1: entryRuleDoubleArrayExpression returns [EObject current=null] : iv_ruleDoubleArrayExpression= ruleDoubleArrayExpression EOF ;
    public final EObject entryRuleDoubleArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoubleArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2212:2: (iv_ruleDoubleArrayExpression= ruleDoubleArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2213:2: iv_ruleDoubleArrayExpression= ruleDoubleArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getDoubleArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleDoubleArrayExpression_in_entryRuleDoubleArrayExpression5155);
            iv_ruleDoubleArrayExpression=ruleDoubleArrayExpression();

            state._fsp--;

             current =iv_ruleDoubleArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleArrayExpression5165); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDoubleArrayExpression"


    // $ANTLR start "ruleDoubleArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2220:1: ruleDoubleArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleDoubleArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2223:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2224:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2224:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2224:3: otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleDoubleArrayExpression5202); 

                	newLeafNode(otherlv_0, grammarAccess.getDoubleArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2228:1: ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2229:1: (lv_values_1_0= RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2229:1: (lv_values_1_0= RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2230:3: lv_values_1_0= RULE_SIGNED_DOUBLE
            {
            lv_values_1_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleArrayExpression5219); 

            			newLeafNode(lv_values_1_0, grammarAccess.getDoubleArrayExpressionAccess().getValuesSIGNED_DOUBLETerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDoubleArrayExpressionRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"SIGNED_DOUBLE");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2246:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==21) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2246:4: otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleDoubleArrayExpression5237); 

            	        	newLeafNode(otherlv_2, grammarAccess.getDoubleArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2250:1: ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2251:1: (lv_values_3_0= RULE_SIGNED_DOUBLE )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2251:1: (lv_values_3_0= RULE_SIGNED_DOUBLE )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2252:3: lv_values_3_0= RULE_SIGNED_DOUBLE
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleArrayExpression5254); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getDoubleArrayExpressionAccess().getValuesSIGNED_DOUBLETerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getDoubleArrayExpressionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"SIGNED_DOUBLE");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleDoubleArrayExpression5273); 

                	newLeafNode(otherlv_4, grammarAccess.getDoubleArrayExpressionAccess().getRightSquareBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDoubleArrayExpression"


    // $ANTLR start "entryRuleLongArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2280:1: entryRuleLongArrayExpression returns [EObject current=null] : iv_ruleLongArrayExpression= ruleLongArrayExpression EOF ;
    public final EObject entryRuleLongArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLongArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2281:2: (iv_ruleLongArrayExpression= ruleLongArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2282:2: iv_ruleLongArrayExpression= ruleLongArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getLongArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleLongArrayExpression_in_entryRuleLongArrayExpression5309);
            iv_ruleLongArrayExpression=ruleLongArrayExpression();

            state._fsp--;

             current =iv_ruleLongArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongArrayExpression5319); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLongArrayExpression"


    // $ANTLR start "ruleLongArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2289:1: ruleLongArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleLongArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2292:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2293:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2293:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2293:3: otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleLongArrayExpression5356); 

                	newLeafNode(otherlv_0, grammarAccess.getLongArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2297:1: ( (lv_values_1_0= RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2298:1: (lv_values_1_0= RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2298:1: (lv_values_1_0= RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2299:3: lv_values_1_0= RULE_SINGED_LONG
            {
            lv_values_1_0=(Token)match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_ruleLongArrayExpression5373); 

            			newLeafNode(lv_values_1_0, grammarAccess.getLongArrayExpressionAccess().getValuesSINGED_LONGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLongArrayExpressionRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"SINGED_LONG");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2315:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==21) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2315:4: otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleLongArrayExpression5391); 

            	        	newLeafNode(otherlv_2, grammarAccess.getLongArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2319:1: ( (lv_values_3_0= RULE_SINGED_LONG ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2320:1: (lv_values_3_0= RULE_SINGED_LONG )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2320:1: (lv_values_3_0= RULE_SINGED_LONG )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2321:3: lv_values_3_0= RULE_SINGED_LONG
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_ruleLongArrayExpression5408); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getLongArrayExpressionAccess().getValuesSINGED_LONGTerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getLongArrayExpressionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"SINGED_LONG");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleLongArrayExpression5427); 

                	newLeafNode(otherlv_4, grammarAccess.getLongArrayExpressionAccess().getRightSquareBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLongArrayExpression"


    // $ANTLR start "entryRuleStringArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2349:1: entryRuleStringArrayExpression returns [EObject current=null] : iv_ruleStringArrayExpression= ruleStringArrayExpression EOF ;
    public final EObject entryRuleStringArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2350:2: (iv_ruleStringArrayExpression= ruleStringArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2351:2: iv_ruleStringArrayExpression= ruleStringArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getStringArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleStringArrayExpression_in_entryRuleStringArrayExpression5463);
            iv_ruleStringArrayExpression=ruleStringArrayExpression();

            state._fsp--;

             current =iv_ruleStringArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringArrayExpression5473); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringArrayExpression"


    // $ANTLR start "ruleStringArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2358:1: ruleStringArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleStringArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2361:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2362:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2362:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2362:3: otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleStringArrayExpression5510); 

                	newLeafNode(otherlv_0, grammarAccess.getStringArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2366:1: ( (lv_values_1_0= RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2367:1: (lv_values_1_0= RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2367:1: (lv_values_1_0= RULE_STRING )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2368:3: lv_values_1_0= RULE_STRING
            {
            lv_values_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringArrayExpression5527); 

            			newLeafNode(lv_values_1_0, grammarAccess.getStringArrayExpressionAccess().getValuesSTRINGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getStringArrayExpressionRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"STRING");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2384:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==21) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2384:4: otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleStringArrayExpression5545); 

            	        	newLeafNode(otherlv_2, grammarAccess.getStringArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2388:1: ( (lv_values_3_0= RULE_STRING ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2389:1: (lv_values_3_0= RULE_STRING )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2389:1: (lv_values_3_0= RULE_STRING )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2390:3: lv_values_3_0= RULE_STRING
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringArrayExpression5562); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getStringArrayExpressionAccess().getValuesSTRINGTerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getStringArrayExpressionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"STRING");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleStringArrayExpression5581); 

                	newLeafNode(otherlv_4, grammarAccess.getStringArrayExpressionAccess().getRightSquareBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringArrayExpression"


    // $ANTLR start "entryRuleNullArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2418:1: entryRuleNullArrayExpression returns [EObject current=null] : iv_ruleNullArrayExpression= ruleNullArrayExpression EOF ;
    public final EObject entryRuleNullArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2419:2: (iv_ruleNullArrayExpression= ruleNullArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2420:2: iv_ruleNullArrayExpression= ruleNullArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getNullArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleNullArrayExpression_in_entryRuleNullArrayExpression5617);
            iv_ruleNullArrayExpression=ruleNullArrayExpression();

            state._fsp--;

             current =iv_ruleNullArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullArrayExpression5627); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNullArrayExpression"


    // $ANTLR start "ruleNullArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2427:1: ruleNullArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleNullArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2430:28: ( (otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2431:1: (otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2431:1: (otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2431:3: otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleNullArrayExpression5664); 

                	newLeafNode(otherlv_0, grammarAccess.getNullArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2435:1: ( (lv_values_1_0= 'null' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2436:1: (lv_values_1_0= 'null' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2436:1: (lv_values_1_0= 'null' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2437:3: lv_values_1_0= 'null'
            {
            lv_values_1_0=(Token)match(input,29,FOLLOW_29_in_ruleNullArrayExpression5682); 

                    newLeafNode(lv_values_1_0, grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNullArrayExpressionRule());
            	        }
                   		addWithLastConsumed(current, "values", lv_values_1_0, "null");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2450:2: (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==21) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2450:4: otherlv_2= ',' ( (lv_values_3_0= 'null' ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleNullArrayExpression5708); 

            	        	newLeafNode(otherlv_2, grammarAccess.getNullArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2454:1: ( (lv_values_3_0= 'null' ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2455:1: (lv_values_3_0= 'null' )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2455:1: (lv_values_3_0= 'null' )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2456:3: lv_values_3_0= 'null'
            	    {
            	    lv_values_3_0=(Token)match(input,29,FOLLOW_29_in_ruleNullArrayExpression5726); 

            	            newLeafNode(lv_values_3_0, grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_2_1_0());
            	        

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getNullArrayExpressionRule());
            	    	        }
            	           		addWithLastConsumed(current, "values", lv_values_3_0, "null");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleNullArrayExpression5753); 

                	newLeafNode(otherlv_4, grammarAccess.getNullArrayExpressionAccess().getRightSquareBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNullArrayExpression"


    // $ANTLR start "entryRuleDateArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2481:1: entryRuleDateArrayExpression returns [EObject current=null] : iv_ruleDateArrayExpression= ruleDateArrayExpression EOF ;
    public final EObject entryRuleDateArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDateArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2482:2: (iv_ruleDateArrayExpression= ruleDateArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2483:2: iv_ruleDateArrayExpression= ruleDateArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getDateArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleDateArrayExpression_in_entryRuleDateArrayExpression5789);
            iv_ruleDateArrayExpression=ruleDateArrayExpression();

            state._fsp--;

             current =iv_ruleDateArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDateArrayExpression5799); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDateArrayExpression"


    // $ANTLR start "ruleDateArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2490:1: ruleDateArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleDateArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2493:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2494:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2494:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2494:3: otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleDateArrayExpression5836); 

                	newLeafNode(otherlv_0, grammarAccess.getDateArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2498:1: ( (lv_values_1_0= RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2499:1: (lv_values_1_0= RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2499:1: (lv_values_1_0= RULE_DATE )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2500:3: lv_values_1_0= RULE_DATE
            {
            lv_values_1_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleDateArrayExpression5853); 

            			newLeafNode(lv_values_1_0, grammarAccess.getDateArrayExpressionAccess().getValuesDATETerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDateArrayExpressionRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"DATE");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2516:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==21) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2516:4: otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleDateArrayExpression5871); 

            	        	newLeafNode(otherlv_2, grammarAccess.getDateArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2520:1: ( (lv_values_3_0= RULE_DATE ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2521:1: (lv_values_3_0= RULE_DATE )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2521:1: (lv_values_3_0= RULE_DATE )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2522:3: lv_values_3_0= RULE_DATE
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleDateArrayExpression5888); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getDateArrayExpressionAccess().getValuesDATETerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getDateArrayExpressionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"DATE");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleDateArrayExpression5907); 

                	newLeafNode(otherlv_4, grammarAccess.getDateArrayExpressionAccess().getRightSquareBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDateArrayExpression"


    // $ANTLR start "entryRuleBooleanArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2550:1: entryRuleBooleanArrayExpression returns [EObject current=null] : iv_ruleBooleanArrayExpression= ruleBooleanArrayExpression EOF ;
    public final EObject entryRuleBooleanArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2551:2: (iv_ruleBooleanArrayExpression= ruleBooleanArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2552:2: iv_ruleBooleanArrayExpression= ruleBooleanArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getBooleanArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleBooleanArrayExpression_in_entryRuleBooleanArrayExpression5943);
            iv_ruleBooleanArrayExpression=ruleBooleanArrayExpression();

            state._fsp--;

             current =iv_ruleBooleanArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanArrayExpression5953); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanArrayExpression"


    // $ANTLR start "ruleBooleanArrayExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2559:1: ruleBooleanArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleBooleanArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2562:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2563:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2563:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2563:3: otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleBooleanArrayExpression5990); 

                	newLeafNode(otherlv_0, grammarAccess.getBooleanArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2567:1: ( (lv_values_1_0= RULE_BOOL ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2568:1: (lv_values_1_0= RULE_BOOL )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2568:1: (lv_values_1_0= RULE_BOOL )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2569:3: lv_values_1_0= RULE_BOOL
            {
            lv_values_1_0=(Token)match(input,RULE_BOOL,FOLLOW_RULE_BOOL_in_ruleBooleanArrayExpression6007); 

            			newLeafNode(lv_values_1_0, grammarAccess.getBooleanArrayExpressionAccess().getValuesBOOLTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getBooleanArrayExpressionRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"BOOL");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2585:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==21) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2585:4: otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleBooleanArrayExpression6025); 

            	        	newLeafNode(otherlv_2, grammarAccess.getBooleanArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2589:1: ( (lv_values_3_0= RULE_BOOL ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2590:1: (lv_values_3_0= RULE_BOOL )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2590:1: (lv_values_3_0= RULE_BOOL )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2591:3: lv_values_3_0= RULE_BOOL
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_BOOL,FOLLOW_RULE_BOOL_in_ruleBooleanArrayExpression6042); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getBooleanArrayExpressionAccess().getValuesBOOLTerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getBooleanArrayExpressionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"BOOL");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleBooleanArrayExpression6061); 

                	newLeafNode(otherlv_4, grammarAccess.getBooleanArrayExpressionAccess().getRightSquareBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanArrayExpression"


    // $ANTLR start "ruleArrayOperator"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2619:1: ruleArrayOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'in' ) | (enumLiteral_1= 'not in' ) ) ;
    public final Enumerator ruleArrayOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2621:28: ( ( (enumLiteral_0= 'in' ) | (enumLiteral_1= 'not in' ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2622:1: ( (enumLiteral_0= 'in' ) | (enumLiteral_1= 'not in' ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2622:1: ( (enumLiteral_0= 'in' ) | (enumLiteral_1= 'not in' ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==34) ) {
                alt41=1;
            }
            else if ( (LA41_0==35) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2622:2: (enumLiteral_0= 'in' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2622:2: (enumLiteral_0= 'in' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2622:4: enumLiteral_0= 'in'
                    {
                    enumLiteral_0=(Token)match(input,34,FOLLOW_34_in_ruleArrayOperator6111); 

                            current = grammarAccess.getArrayOperatorAccess().getSql_inEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getArrayOperatorAccess().getSql_inEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2628:6: (enumLiteral_1= 'not in' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2628:6: (enumLiteral_1= 'not in' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2628:8: enumLiteral_1= 'not in'
                    {
                    enumLiteral_1=(Token)match(input,35,FOLLOW_35_in_ruleArrayOperator6128); 

                            current = grammarAccess.getArrayOperatorAccess().getSql_notInEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getArrayOperatorAccess().getSql_notInEnumLiteralDeclaration_1()); 
                        

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
    // $ANTLR end "ruleArrayOperator"


    // $ANTLR start "ruleOperator"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2638:1: ruleOperator returns [Enumerator current=null] : ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '=' ) | (enumLiteral_5= '!=' ) | (enumLiteral_6= 'like' ) | (enumLiteral_7= 'not like' ) | (enumLiteral_8= 'not in' ) | (enumLiteral_9= 'in' ) ) ;
    public final Enumerator ruleOperator() throws RecognitionException {
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

         enterRule(); 
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2640:28: ( ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '=' ) | (enumLiteral_5= '!=' ) | (enumLiteral_6= 'like' ) | (enumLiteral_7= 'not like' ) | (enumLiteral_8= 'not in' ) | (enumLiteral_9= 'in' ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2641:1: ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '=' ) | (enumLiteral_5= '!=' ) | (enumLiteral_6= 'like' ) | (enumLiteral_7= 'not like' ) | (enumLiteral_8= 'not in' ) | (enumLiteral_9= 'in' ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2641:1: ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '=' ) | (enumLiteral_5= '!=' ) | (enumLiteral_6= 'like' ) | (enumLiteral_7= 'not like' ) | (enumLiteral_8= 'not in' ) | (enumLiteral_9= 'in' ) )
            int alt42=10;
            switch ( input.LA(1) ) {
            case 36:
                {
                alt42=1;
                }
                break;
            case 37:
                {
                alt42=2;
                }
                break;
            case 38:
                {
                alt42=3;
                }
                break;
            case 39:
                {
                alt42=4;
                }
                break;
            case 40:
                {
                alt42=5;
                }
                break;
            case 41:
                {
                alt42=6;
                }
                break;
            case 42:
                {
                alt42=7;
                }
                break;
            case 43:
                {
                alt42=8;
                }
                break;
            case 35:
                {
                alt42=9;
                }
                break;
            case 34:
                {
                alt42=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }

            switch (alt42) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2641:2: (enumLiteral_0= '<' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2641:2: (enumLiteral_0= '<' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2641:4: enumLiteral_0= '<'
                    {
                    enumLiteral_0=(Token)match(input,36,FOLLOW_36_in_ruleOperator6173); 

                            current = grammarAccess.getOperatorAccess().getLessThenEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getOperatorAccess().getLessThenEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2647:6: (enumLiteral_1= '>' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2647:6: (enumLiteral_1= '>' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2647:8: enumLiteral_1= '>'
                    {
                    enumLiteral_1=(Token)match(input,37,FOLLOW_37_in_ruleOperator6190); 

                            current = grammarAccess.getOperatorAccess().getGreaterThenEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getOperatorAccess().getGreaterThenEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2653:6: (enumLiteral_2= '<=' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2653:6: (enumLiteral_2= '<=' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2653:8: enumLiteral_2= '<='
                    {
                    enumLiteral_2=(Token)match(input,38,FOLLOW_38_in_ruleOperator6207); 

                            current = grammarAccess.getOperatorAccess().getLessEqualEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getOperatorAccess().getLessEqualEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2659:6: (enumLiteral_3= '>=' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2659:6: (enumLiteral_3= '>=' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2659:8: enumLiteral_3= '>='
                    {
                    enumLiteral_3=(Token)match(input,39,FOLLOW_39_in_ruleOperator6224); 

                            current = grammarAccess.getOperatorAccess().getGreaterEqualEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getOperatorAccess().getGreaterEqualEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2665:6: (enumLiteral_4= '=' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2665:6: (enumLiteral_4= '=' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2665:8: enumLiteral_4= '='
                    {
                    enumLiteral_4=(Token)match(input,40,FOLLOW_40_in_ruleOperator6241); 

                            current = grammarAccess.getOperatorAccess().getEqualEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getOperatorAccess().getEqualEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2671:6: (enumLiteral_5= '!=' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2671:6: (enumLiteral_5= '!=' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2671:8: enumLiteral_5= '!='
                    {
                    enumLiteral_5=(Token)match(input,41,FOLLOW_41_in_ruleOperator6258); 

                            current = grammarAccess.getOperatorAccess().getNotEqualEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getOperatorAccess().getNotEqualEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2677:6: (enumLiteral_6= 'like' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2677:6: (enumLiteral_6= 'like' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2677:8: enumLiteral_6= 'like'
                    {
                    enumLiteral_6=(Token)match(input,42,FOLLOW_42_in_ruleOperator6275); 

                            current = grammarAccess.getOperatorAccess().getLikeEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getOperatorAccess().getLikeEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2683:6: (enumLiteral_7= 'not like' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2683:6: (enumLiteral_7= 'not like' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2683:8: enumLiteral_7= 'not like'
                    {
                    enumLiteral_7=(Token)match(input,43,FOLLOW_43_in_ruleOperator6292); 

                            current = grammarAccess.getOperatorAccess().getNotLikeEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getOperatorAccess().getNotLikeEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2689:6: (enumLiteral_8= 'not in' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2689:6: (enumLiteral_8= 'not in' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2689:8: enumLiteral_8= 'not in'
                    {
                    enumLiteral_8=(Token)match(input,35,FOLLOW_35_in_ruleOperator6309); 

                            current = grammarAccess.getOperatorAccess().getNotInEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getOperatorAccess().getNotInEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2695:6: (enumLiteral_9= 'in' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2695:6: (enumLiteral_9= 'in' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2695:8: enumLiteral_9= 'in'
                    {
                    enumLiteral_9=(Token)match(input,34,FOLLOW_34_in_ruleOperator6326); 

                            current = grammarAccess.getOperatorAccess().getInEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getOperatorAccess().getInEnumLiteralDeclaration_9()); 
                        

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
    // $ANTLR end "ruleOperator"

    // Delegated rules


    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA14_eotS =
        "\13\uffff";
    static final String DFA14_eofS =
        "\1\uffff\1\4\4\uffff\1\4\1\uffff\1\4\1\uffff\1\4";
    static final String DFA14_minS =
        "\3\4\3\uffff\5\4";
    static final String DFA14_maxS =
        "\1\4\1\27\1\4\3\uffff\1\27\1\4\1\27\1\4\1\27";
    static final String DFA14_acceptS =
        "\3\uffff\1\2\1\1\1\3\5\uffff";
    static final String DFA14_specialS =
        "\13\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\1",
            "\1\5\13\uffff\1\4\4\uffff\1\4\1\2\1\3",
            "\1\6",
            "",
            "",
            "",
            "\1\5\13\uffff\1\4\4\uffff\1\4\1\7\1\3",
            "\1\10",
            "\1\5\13\uffff\1\4\4\uffff\1\4\1\11\1\3",
            "\1\12",
            "\1\5\13\uffff\1\4\4\uffff\1\4\1\uffff\1\3"
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "550:1: (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleModel122 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_ruleColumns_in_ruleModel143 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleModel156 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTables_in_ruleModel177 = new BitSet(new long[]{0x00000000001E0002L});
    public static final BitSet FOLLOW_17_in_ruleModel190 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_ruleModel211 = new BitSet(new long[]{0x00000000001C0002L});
    public static final BitSet FOLLOW_18_in_ruleModel226 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_ruleModel247 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_19_in_ruleModel262 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_ruleHavingEntry_in_ruleModel283 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_ruleModel298 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_ruleModel319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns357 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns414 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleOrderByColumns436 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns457 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull497 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleOrderByColumnFull553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleOrderByColumnFull582 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleOrderByColumnFull593 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleOrderByColumnFull614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns651 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumns661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns708 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleGroupByColumns730 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns751 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull791 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumnFull801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleGroupByColumnFull847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleGroupByColumnFull876 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleGroupByColumnFull887 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleGroupByColumnFull908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns945 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns1002 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleColumns1024 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns1045 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias1085 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOrAlias1142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOrAlias1170 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleColumnOrAlias1181 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_ruleColumnOrAlias1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOrAlias1232 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_ruleColumnOrAlias1252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull1289 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleColumnFull1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleColumnFull1374 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleColumnFull1385 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleColumnFull1406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_entryRuleColumnAlias1443 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnAlias1453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleColumnAlias1494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_entryRuleColumn1534 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumn1544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleColumn1585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables1625 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables1635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleTables1682 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleTables1704 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleTables1725 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1765 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias1775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1850 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleTableOrAlias1861 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTableAlias_in_ruleTableOrAlias1882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1912 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTableAlias_in_ruleTableOrAlias1932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull1969 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull1979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchema_in_ruleTableFull2027 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleTableFull2038 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTable_in_ruleTableFull2059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_ruleTableFull2087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_entryRuleTable2123 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTable2133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTable2174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableAlias_in_entryRuleTableAlias2214 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableAlias2224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTableAlias2265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchema_in_entryRuleSchema2305 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSchema2315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatabase_in_ruleSchema2363 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleSchema2374 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSchema2391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSchema2420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatabase_in_entryRuleDatabase2461 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDatabase2471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDatabase2512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_entryRuleWhereEntry2552 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWhereEntry2562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_ruleWhereEntry2609 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleWhereEntry2631 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_ruleWhereEntry2652 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_entryRuleAndWhereEntry2692 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndWhereEntry2702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_ruleAndWhereEntry2749 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleAndWhereEntry2771 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_ruleAndWhereEntry2792 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_entryRuleConcreteWhereEntry2832 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConcreteWhereEntry2842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParWhereEntry_in_ruleConcreteWhereEntry2889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_ruleConcreteWhereEntry2916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParWhereEntry_in_entryRuleParWhereEntry2951 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParWhereEntry2961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleParWhereEntry2998 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_ruleParWhereEntry3020 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleParWhereEntry3031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleHavingEntry_in_entryRuleHavingEntry3067 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleHavingEntry3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndHavingEntry_in_ruleHavingEntry3124 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleHavingEntry3146 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_ruleAndHavingEntry_in_ruleHavingEntry3167 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_ruleAndHavingEntry_in_entryRuleAndHavingEntry3207 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndHavingEntry3217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteHavingEntry_in_ruleAndHavingEntry3264 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleAndHavingEntry3286 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_ruleConcreteHavingEntry_in_ruleAndHavingEntry3307 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleConcreteHavingEntry_in_entryRuleConcreteHavingEntry3347 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConcreteHavingEntry3357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParHavingEntry_in_ruleConcreteHavingEntry3404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_ruleConcreteHavingEntry3431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParHavingEntry_in_entryRuleParHavingEntry3466 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParHavingEntry3476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleParHavingEntry3513 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_ruleHavingEntry_in_ruleParHavingEntry3535 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleParHavingEntry3546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_entryRuleExpressionWhereEntry3582 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionWhereEntry3592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleExpressionWhereEntry_in_ruleExpressionWhereEntry3639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiExpressionWhereEntry_in_ruleExpressionWhereEntry3666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleExpressionWhereEntry_in_entryRuleSingleExpressionWhereEntry3701 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSingleExpressionWhereEntry3711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSingleExpressionWhereEntry3753 = new BitSet(new long[]{0x00000FFC00000000L});
    public static final BitSet FOLLOW_ruleOperator_in_ruleSingleExpressionWhereEntry3779 = new BitSet(new long[]{0x00000000F00001E0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSingleExpressionWhereEntry3800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression3836 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression3846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleExpression_in_ruleExpression3893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongExpression_in_ruleExpression3920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringExpression_in_ruleExpression3947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_ruleExpression3974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateExpression_in_ruleExpression4001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanExpression_in_ruleExpression4028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplacableValue_in_ruleExpression4055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplacableValue_in_entryRuleReplacableValue4090 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReplacableValue4100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleReplacableValue4142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleExpression_in_entryRuleDoubleExpression4190 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleExpression4200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleExpression4241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongExpression_in_entryRuleLongExpression4281 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongExpression4291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_ruleLongExpression4332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringExpression_in_entryRuleStringExpression4372 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringExpression4382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringExpression4423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_entryRuleNullExpression4463 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullExpression4473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleNullExpression4515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateExpression_in_entryRuleDateExpression4563 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDateExpression4573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleDateExpression4614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanExpression_in_entryRuleBooleanExpression4654 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanExpression4664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleBooleanExpression4707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleBooleanExpression4744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiExpressionWhereEntry_in_entryRuleMultiExpressionWhereEntry4793 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiExpressionWhereEntry4803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleMultiExpressionWhereEntry4845 = new BitSet(new long[]{0x0000000C00000000L});
    public static final BitSet FOLLOW_ruleArrayOperator_in_ruleMultiExpressionWhereEntry4871 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ruleArrayExpression_in_ruleMultiExpressionWhereEntry4892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayExpression_in_entryRuleArrayExpression4928 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayExpression4938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleArrayExpression_in_ruleArrayExpression4985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongArrayExpression_in_ruleArrayExpression5012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringArrayExpression_in_ruleArrayExpression5039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullArrayExpression_in_ruleArrayExpression5066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateArrayExpression_in_ruleArrayExpression5093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanArrayExpression_in_ruleArrayExpression5120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleArrayExpression_in_entryRuleDoubleArrayExpression5155 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleArrayExpression5165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleDoubleArrayExpression5202 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleArrayExpression5219 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_21_in_ruleDoubleArrayExpression5237 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleArrayExpression5254 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_33_in_ruleDoubleArrayExpression5273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongArrayExpression_in_entryRuleLongArrayExpression5309 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongArrayExpression5319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleLongArrayExpression5356 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_ruleLongArrayExpression5373 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_21_in_ruleLongArrayExpression5391 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_ruleLongArrayExpression5408 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_33_in_ruleLongArrayExpression5427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringArrayExpression_in_entryRuleStringArrayExpression5463 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringArrayExpression5473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleStringArrayExpression5510 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringArrayExpression5527 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_21_in_ruleStringArrayExpression5545 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringArrayExpression5562 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_33_in_ruleStringArrayExpression5581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullArrayExpression_in_entryRuleNullArrayExpression5617 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullArrayExpression5627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleNullArrayExpression5664 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleNullArrayExpression5682 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_21_in_ruleNullArrayExpression5708 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleNullArrayExpression5726 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_33_in_ruleNullArrayExpression5753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateArrayExpression_in_entryRuleDateArrayExpression5789 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDateArrayExpression5799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleDateArrayExpression5836 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleDateArrayExpression5853 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_21_in_ruleDateArrayExpression5871 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleDateArrayExpression5888 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_33_in_ruleDateArrayExpression5907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanArrayExpression_in_entryRuleBooleanArrayExpression5943 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanArrayExpression5953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleBooleanArrayExpression5990 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RULE_BOOL_in_ruleBooleanArrayExpression6007 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_21_in_ruleBooleanArrayExpression6025 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RULE_BOOL_in_ruleBooleanArrayExpression6042 = new BitSet(new long[]{0x0000000200200000L});
    public static final BitSet FOLLOW_33_in_ruleBooleanArrayExpression6061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleArrayOperator6111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleArrayOperator6128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleOperator6173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleOperator6190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleOperator6207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleOperator6224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleOperator6241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleOperator6258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleOperator6275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleOperator6292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleOperator6309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleOperator6326 = new BitSet(new long[]{0x0000000000000002L});

}