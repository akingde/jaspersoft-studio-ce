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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_SIGNED_DOUBLE", "RULE_SINGED_LONG", "RULE_STRING", "RULE_DATE", "RULE_BOOL", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'SELECT'", "'FROM'", "'WHERE'", "','", "'AS'", "'.'", "'OR'", "'AND'", "'('", "')'", "'?'", "'null'", "'true'", "'false'", "'['", "']'", "'in'", "'not in'", "'<'", "'>'", "'<='", "'>='", "'='", "'!='", "'like'", "'not like'"
    };
    public static final int RULE_ID=4;
    public static final int T__40=40;
    public static final int RULE_DATE=8;
    public static final int T__29=29;
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
    public static final int RULE_SINGED_LONG=6;
    public static final int RULE_BOOL=9;
    public static final int RULE_SL_COMMENT=12;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=11;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int RULE_STRING=7;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__16=16;
    public static final int T__34=34;
    public static final int T__15=15;
    public static final int T__35=35;
    public static final int T__18=18;
    public static final int T__36=36;
    public static final int T__17=17;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_INT=10;
    public static final int RULE_SIGNED_DOUBLE=5;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:77:1: ruleModel returns [EObject current=null] : (otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) ) otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_col_1_0 = null;

        EObject lv_tbl_3_0 = null;

        EObject lv_whereEntry_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:80:28: ( (otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) ) otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:81:1: (otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) ) otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:81:1: (otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) ) otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:81:3: otherlv_0= 'SELECT' ( (lv_col_1_0= ruleColumns ) ) otherlv_2= 'FROM' ( (lv_tbl_3_0= ruleTables ) ) (otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) ) )?
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleModel122); 

                	newLeafNode(otherlv_0, grammarAccess.getModelAccess().getSELECTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:85:1: ( (lv_col_1_0= ruleColumns ) )
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

            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleModel155); 

                	newLeafNode(otherlv_2, grammarAccess.getModelAccess().getFROMKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:107:1: ( (lv_tbl_3_0= ruleTables ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:108:1: (lv_tbl_3_0= ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:108:1: (lv_tbl_3_0= ruleTables )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:109:3: lv_tbl_3_0= ruleTables
            {
             
            	        newCompositeNode(grammarAccess.getModelAccess().getTblTablesParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleTables_in_ruleModel176);
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
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==17) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:125:4: otherlv_4= 'WHERE' ( (lv_whereEntry_5_0= ruleWhereEntry ) )
                    {
                    otherlv_4=(Token)match(input,17,FOLLOW_17_in_ruleModel189); 

                        	newLeafNode(otherlv_4, grammarAccess.getModelAccess().getWHEREKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:129:1: ( (lv_whereEntry_5_0= ruleWhereEntry ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:130:1: (lv_whereEntry_5_0= ruleWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:130:1: (lv_whereEntry_5_0= ruleWhereEntry )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:131:3: lv_whereEntry_5_0= ruleWhereEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getWhereEntryWhereEntryParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleWhereEntry_in_ruleModel210);
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


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleColumns"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:155:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:156:2: (iv_ruleColumns= ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:157:2: iv_ruleColumns= ruleColumns EOF
            {
             newCompositeNode(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns248);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns258); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:164:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:167:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:168:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:168:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:169:5: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns305);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;

             
                    current = this_ColumnOrAlias_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:177:1: ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==18) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:177:2: () (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:177:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:178:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:183:2: (otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==18) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:183:4: otherlv_2= ',' ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleColumns327); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:187:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:188:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:188:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:189:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns348);
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
                    	    if ( cnt2 >= 1 ) break loop2;
                                EarlyExitException eee =
                                    new EarlyExitException(2, input);
                                throw eee;
                        }
                        cnt2++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:213:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:214:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:215:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias388);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias398); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:222:1: ruleColumnOrAlias returns [EObject current=null] : (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:225:28: ( (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:226:1: (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:226:1: (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) )
            int alt4=3;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:227:5: this_ColumnFull_0= ruleColumnFull
                    {
                     
                            newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOrAlias445);
                    this_ColumnFull_0=ruleColumnFull();

                    state._fsp--;

                     
                            current = this_ColumnFull_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:236:6: (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:236:6: (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:237:5: this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) )
                    {
                     
                            newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOrAlias473);
                    this_ColumnFull_1=ruleColumnFull();

                    state._fsp--;

                     
                            current = this_ColumnFull_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,19,FOLLOW_19_in_ruleColumnOrAlias484); 

                        	newLeafNode(otherlv_2, grammarAccess.getColumnOrAliasAccess().getASKeyword_1_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:249:1: ( (lv_colAlias_3_0= ruleColumnAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:250:1: (lv_colAlias_3_0= ruleColumnAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:250:1: (lv_colAlias_3_0= ruleColumnAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:251:3: lv_colAlias_3_0= ruleColumnAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnAlias_in_ruleColumnOrAlias505);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:268:6: (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:268:6: (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:269:5: this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) )
                    {
                     
                            newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_2_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOrAlias535);
                    this_ColumnFull_4=ruleColumnFull();

                    state._fsp--;

                     
                            current = this_ColumnFull_4; 
                            afterParserOrEnumRuleCall();
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:277:1: ( (lv_colAlias_5_0= ruleColumnAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:278:1: (lv_colAlias_5_0= ruleColumnAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:278:1: (lv_colAlias_5_0= ruleColumnAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:279:3: lv_colAlias_5_0= ruleColumnAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnAlias_in_ruleColumnOrAlias555);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:303:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:304:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:305:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
             newCompositeNode(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull592);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull602); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:312:1: ruleColumnFull returns [EObject current=null] : ( ( (lv_colName_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) ) ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_colName_0_0 = null;

        EObject this_TableFull_1 = null;

        EObject lv_colName_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:315:28: ( ( ( (lv_colName_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:316:1: ( ( (lv_colName_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:316:1: ( ( (lv_colName_0_0= ruleColumn ) ) | (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==EOF||LA5_1==RULE_ID||LA5_1==16||(LA5_1>=18 && LA5_1<=19)) ) {
                    alt5=1;
                }
                else if ( (LA5_1==20) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:316:2: ( (lv_colName_0_0= ruleColumn ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:316:2: ( (lv_colName_0_0= ruleColumn ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:317:1: (lv_colName_0_0= ruleColumn )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:317:1: (lv_colName_0_0= ruleColumn )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:318:3: lv_colName_0_0= ruleColumn
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumn_in_ruleColumnFull648);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:335:6: (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:335:6: (this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:336:5: this_TableFull_1= ruleTableFull otherlv_2= '.' ( (lv_colName_3_0= ruleColumn ) )
                    {
                     
                            newCompositeNode(grammarAccess.getColumnFullAccess().getTableFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleColumnFull677);
                    this_TableFull_1=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleColumnFull688); 

                        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:348:1: ( (lv_colName_3_0= ruleColumn ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:349:1: (lv_colName_3_0= ruleColumn )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:349:1: (lv_colName_3_0= ruleColumn )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:350:3: lv_colName_3_0= ruleColumn
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumn_in_ruleColumnFull709);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:374:1: entryRuleColumnAlias returns [EObject current=null] : iv_ruleColumnAlias= ruleColumnAlias EOF ;
    public final EObject entryRuleColumnAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:375:2: (iv_ruleColumnAlias= ruleColumnAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:376:2: iv_ruleColumnAlias= ruleColumnAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnAliasRule()); 
            pushFollow(FOLLOW_ruleColumnAlias_in_entryRuleColumnAlias746);
            iv_ruleColumnAlias=ruleColumnAlias();

            state._fsp--;

             current =iv_ruleColumnAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnAlias756); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:383:1: ruleColumnAlias returns [EObject current=null] : ( (lv_colAlias_0_0= RULE_ID ) ) ;
    public final EObject ruleColumnAlias() throws RecognitionException {
        EObject current = null;

        Token lv_colAlias_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:386:28: ( ( (lv_colAlias_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:387:1: ( (lv_colAlias_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:387:1: ( (lv_colAlias_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:388:1: (lv_colAlias_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:388:1: (lv_colAlias_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:389:3: lv_colAlias_0_0= RULE_ID
            {
            lv_colAlias_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleColumnAlias797); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:413:1: entryRuleColumn returns [EObject current=null] : iv_ruleColumn= ruleColumn EOF ;
    public final EObject entryRuleColumn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumn = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:414:2: (iv_ruleColumn= ruleColumn EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:415:2: iv_ruleColumn= ruleColumn EOF
            {
             newCompositeNode(grammarAccess.getColumnRule()); 
            pushFollow(FOLLOW_ruleColumn_in_entryRuleColumn837);
            iv_ruleColumn=ruleColumn();

            state._fsp--;

             current =iv_ruleColumn; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumn847); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:422:1: ruleColumn returns [EObject current=null] : ( (lv_colName_0_0= RULE_ID ) ) ;
    public final EObject ruleColumn() throws RecognitionException {
        EObject current = null;

        Token lv_colName_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:425:28: ( ( (lv_colName_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:426:1: ( (lv_colName_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:426:1: ( (lv_colName_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:427:1: (lv_colName_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:427:1: (lv_colName_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:428:3: lv_colName_0_0= RULE_ID
            {
            lv_colName_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleColumn888); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:452:1: entryRuleTables returns [EObject current=null] : iv_ruleTables= ruleTables EOF ;
    public final EObject entryRuleTables() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTables = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:453:2: (iv_ruleTables= ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:454:2: iv_ruleTables= ruleTables EOF
            {
             newCompositeNode(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables928);
            iv_ruleTables=ruleTables();

            state._fsp--;

             current =iv_ruleTables; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables938); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:461:1: ruleTables returns [EObject current=null] : (this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )? ) ;
    public final EObject ruleTables() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_TableOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:464:28: ( (this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:465:1: (this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:465:1: (this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:466:5: this_TableOrAlias_0= ruleTableOrAlias ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTablesAccess().getTableOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleTables985);
            this_TableOrAlias_0=ruleTableOrAlias();

            state._fsp--;

             
                    current = this_TableOrAlias_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:474:1: ( () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+ )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==18) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:474:2: () (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:474:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:475:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:480:2: (otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) ) )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==18) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:480:4: otherlv_2= ',' ( (lv_entries_3_0= ruleTableOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleTables1007); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:484:1: ( (lv_entries_3_0= ruleTableOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:485:1: (lv_entries_3_0= ruleTableOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:485:1: (lv_entries_3_0= ruleTableOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:486:3: lv_entries_3_0= ruleTableOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesTableOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleTableOrAlias_in_ruleTables1028);
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
    // $ANTLR end "ruleTables"


    // $ANTLR start "entryRuleTableOrAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:510:1: entryRuleTableOrAlias returns [EObject current=null] : iv_ruleTableOrAlias= ruleTableOrAlias EOF ;
    public final EObject entryRuleTableOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:511:2: (iv_ruleTableOrAlias= ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:512:2: iv_ruleTableOrAlias= ruleTableOrAlias EOF
            {
             newCompositeNode(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1068);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;

             current =iv_ruleTableOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias1078); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:519:1: ruleTableOrAlias returns [EObject current=null] : (this_TableFull_0= ruleTableFull | (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) ) | (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:522:28: ( (this_TableFull_0= ruleTableFull | (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) ) | (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:523:1: (this_TableFull_0= ruleTableFull | (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) ) | (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:523:1: (this_TableFull_0= ruleTableFull | (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) ) | (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) ) )
            int alt8=3;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case RULE_ID:
                    {
                    alt8=3;
                    }
                    break;
                case 19:
                    {
                    alt8=2;
                    }
                    break;
                case 20:
                    {
                    int LA8_4 = input.LA(3);

                    if ( (LA8_4==RULE_ID) ) {
                        switch ( input.LA(4) ) {
                        case EOF:
                        case 17:
                        case 18:
                            {
                            alt8=1;
                            }
                            break;
                        case 20:
                            {
                            int LA8_7 = input.LA(5);

                            if ( (LA8_7==RULE_ID) ) {
                                switch ( input.LA(6) ) {
                                case EOF:
                                case 17:
                                case 18:
                                    {
                                    alt8=1;
                                    }
                                    break;
                                case 19:
                                    {
                                    alt8=2;
                                    }
                                    break;
                                case RULE_ID:
                                    {
                                    alt8=3;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 8, 8, input);

                                    throw nvae;
                                }

                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 8, 7, input);

                                throw nvae;
                            }
                            }
                            break;
                        case 19:
                            {
                            alt8=2;
                            }
                            break;
                        case RULE_ID:
                            {
                            alt8=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 8, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case 17:
                case 18:
                    {
                    alt8=1;
                    }
                    break;
                default:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:524:5: this_TableFull_0= ruleTableFull
                    {
                     
                            newCompositeNode(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1125);
                    this_TableFull_0=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:533:6: (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:533:6: (this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:534:5: this_TableFull_1= ruleTableFull otherlv_2= 'AS' ( (lv_tblAlias_3_0= ruleTableAlias ) )
                    {
                     
                            newCompositeNode(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1153);
                    this_TableFull_1=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,19,FOLLOW_19_in_ruleTableOrAlias1164); 

                        	newLeafNode(otherlv_2, grammarAccess.getTableOrAliasAccess().getASKeyword_1_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:546:1: ( (lv_tblAlias_3_0= ruleTableAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:547:1: (lv_tblAlias_3_0= ruleTableAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:547:1: (lv_tblAlias_3_0= ruleTableAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:548:3: lv_tblAlias_3_0= ruleTableAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTableAlias_in_ruleTableOrAlias1185);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:565:6: (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:565:6: (this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:566:5: this_TableFull_4= ruleTableFull ( (lv_tblAlias_5_0= ruleTableAlias ) )
                    {
                     
                            newCompositeNode(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_2_0()); 
                        
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1215);
                    this_TableFull_4=ruleTableFull();

                    state._fsp--;

                     
                            current = this_TableFull_4; 
                            afterParserOrEnumRuleCall();
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:574:1: ( (lv_tblAlias_5_0= ruleTableAlias ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:575:1: (lv_tblAlias_5_0= ruleTableAlias )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:575:1: (lv_tblAlias_5_0= ruleTableAlias )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:576:3: lv_tblAlias_5_0= ruleTableAlias
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTableAlias_in_ruleTableOrAlias1235);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:600:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:601:2: (iv_ruleTableFull= ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:602:2: iv_ruleTableFull= ruleTableFull EOF
            {
             newCompositeNode(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull1272);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;

             current =iv_ruleTableFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull1282); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:609:1: ruleTableFull returns [EObject current=null] : ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_Schema_0 = null;

        EObject lv_tbl_2_0 = null;

        EObject lv_tbl_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:612:28: ( ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:613:1: ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:613:1: ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) )
            int alt9=2;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:613:2: (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:613:2: (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:614:5: this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) )
                    {
                     
                            newCompositeNode(grammarAccess.getTableFullAccess().getSchemaParserRuleCall_0_0()); 
                        
                    pushFollow(FOLLOW_ruleSchema_in_ruleTableFull1330);
                    this_Schema_0=ruleSchema();

                    state._fsp--;

                     
                            current = this_Schema_0; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleTableFull1341); 

                        	newLeafNode(otherlv_1, grammarAccess.getTableFullAccess().getFullStopKeyword_0_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:626:1: ( (lv_tbl_2_0= ruleTable ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:627:1: (lv_tbl_2_0= ruleTable )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:627:1: (lv_tbl_2_0= ruleTable )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:628:3: lv_tbl_2_0= ruleTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_0_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTable_in_ruleTableFull1362);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:645:6: ( (lv_tbl_3_0= ruleTable ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:645:6: ( (lv_tbl_3_0= ruleTable ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:646:1: (lv_tbl_3_0= ruleTable )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:646:1: (lv_tbl_3_0= ruleTable )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:647:3: lv_tbl_3_0= ruleTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTable_in_ruleTableFull1390);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:671:1: entryRuleTable returns [EObject current=null] : iv_ruleTable= ruleTable EOF ;
    public final EObject entryRuleTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:672:2: (iv_ruleTable= ruleTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:673:2: iv_ruleTable= ruleTable EOF
            {
             newCompositeNode(grammarAccess.getTableRule()); 
            pushFollow(FOLLOW_ruleTable_in_entryRuleTable1426);
            iv_ruleTable=ruleTable();

            state._fsp--;

             current =iv_ruleTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTable1436); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:680:1: ruleTable returns [EObject current=null] : ( (lv_tbl_0_0= RULE_ID ) ) ;
    public final EObject ruleTable() throws RecognitionException {
        EObject current = null;

        Token lv_tbl_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:683:28: ( ( (lv_tbl_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:684:1: ( (lv_tbl_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:684:1: ( (lv_tbl_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:685:1: (lv_tbl_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:685:1: (lv_tbl_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:686:3: lv_tbl_0_0= RULE_ID
            {
            lv_tbl_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTable1477); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:710:1: entryRuleTableAlias returns [EObject current=null] : iv_ruleTableAlias= ruleTableAlias EOF ;
    public final EObject entryRuleTableAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:711:2: (iv_ruleTableAlias= ruleTableAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:712:2: iv_ruleTableAlias= ruleTableAlias EOF
            {
             newCompositeNode(grammarAccess.getTableAliasRule()); 
            pushFollow(FOLLOW_ruleTableAlias_in_entryRuleTableAlias1517);
            iv_ruleTableAlias=ruleTableAlias();

            state._fsp--;

             current =iv_ruleTableAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableAlias1527); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:719:1: ruleTableAlias returns [EObject current=null] : ( (lv_tblAlias_0_0= RULE_ID ) ) ;
    public final EObject ruleTableAlias() throws RecognitionException {
        EObject current = null;

        Token lv_tblAlias_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:722:28: ( ( (lv_tblAlias_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:723:1: ( (lv_tblAlias_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:723:1: ( (lv_tblAlias_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:724:1: (lv_tblAlias_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:724:1: (lv_tblAlias_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:725:3: lv_tblAlias_0_0= RULE_ID
            {
            lv_tblAlias_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTableAlias1568); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:749:1: entryRuleSchema returns [EObject current=null] : iv_ruleSchema= ruleSchema EOF ;
    public final EObject entryRuleSchema() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSchema = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:750:2: (iv_ruleSchema= ruleSchema EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:751:2: iv_ruleSchema= ruleSchema EOF
            {
             newCompositeNode(grammarAccess.getSchemaRule()); 
            pushFollow(FOLLOW_ruleSchema_in_entryRuleSchema1608);
            iv_ruleSchema=ruleSchema();

            state._fsp--;

             current =iv_ruleSchema; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSchema1618); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:758:1: ruleSchema returns [EObject current=null] : ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) ) ;
    public final EObject ruleSchema() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_schem_2_0=null;
        Token lv_schem_3_0=null;
        EObject this_Database_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:761:28: ( ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:762:1: ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:762:1: ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) )
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:762:2: (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:762:2: (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:763:5: this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) )
                    {
                     
                            newCompositeNode(grammarAccess.getSchemaAccess().getDatabaseParserRuleCall_0_0()); 
                        
                    pushFollow(FOLLOW_ruleDatabase_in_ruleSchema1666);
                    this_Database_0=ruleDatabase();

                    state._fsp--;

                     
                            current = this_Database_0; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleSchema1677); 

                        	newLeafNode(otherlv_1, grammarAccess.getSchemaAccess().getFullStopKeyword_0_1());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:775:1: ( (lv_schem_2_0= RULE_ID ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:776:1: (lv_schem_2_0= RULE_ID )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:776:1: (lv_schem_2_0= RULE_ID )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:777:3: lv_schem_2_0= RULE_ID
                    {
                    lv_schem_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSchema1694); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:794:6: ( (lv_schem_3_0= RULE_ID ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:794:6: ( (lv_schem_3_0= RULE_ID ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:795:1: (lv_schem_3_0= RULE_ID )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:795:1: (lv_schem_3_0= RULE_ID )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:796:3: lv_schem_3_0= RULE_ID
                    {
                    lv_schem_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSchema1723); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:820:1: entryRuleDatabase returns [EObject current=null] : iv_ruleDatabase= ruleDatabase EOF ;
    public final EObject entryRuleDatabase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatabase = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:821:2: (iv_ruleDatabase= ruleDatabase EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:822:2: iv_ruleDatabase= ruleDatabase EOF
            {
             newCompositeNode(grammarAccess.getDatabaseRule()); 
            pushFollow(FOLLOW_ruleDatabase_in_entryRuleDatabase1764);
            iv_ruleDatabase=ruleDatabase();

            state._fsp--;

             current =iv_ruleDatabase; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDatabase1774); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:829:1: ruleDatabase returns [EObject current=null] : ( (lv_dbName_0_0= RULE_ID ) ) ;
    public final EObject ruleDatabase() throws RecognitionException {
        EObject current = null;

        Token lv_dbName_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:832:28: ( ( (lv_dbName_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:833:1: ( (lv_dbName_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:833:1: ( (lv_dbName_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:834:1: (lv_dbName_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:834:1: (lv_dbName_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:835:3: lv_dbName_0_0= RULE_ID
            {
            lv_dbName_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDatabase1815); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:859:1: entryRuleWhereEntry returns [EObject current=null] : iv_ruleWhereEntry= ruleWhereEntry EOF ;
    public final EObject entryRuleWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:860:2: (iv_ruleWhereEntry= ruleWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:861:2: iv_ruleWhereEntry= ruleWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getWhereEntryRule()); 
            pushFollow(FOLLOW_ruleWhereEntry_in_entryRuleWhereEntry1855);
            iv_ruleWhereEntry=ruleWhereEntry();

            state._fsp--;

             current =iv_ruleWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWhereEntry1865); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:868:1: ruleWhereEntry returns [EObject current=null] : (this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )? ) ;
    public final EObject ruleWhereEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AndWhereEntry_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:871:28: ( (this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:872:1: (this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:872:1: (this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:873:5: this_AndWhereEntry_0= ruleAndWhereEntry ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getWhereEntryAccess().getAndWhereEntryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAndWhereEntry_in_ruleWhereEntry1912);
            this_AndWhereEntry_0=ruleAndWhereEntry();

            state._fsp--;

             
                    current = this_AndWhereEntry_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:881:1: ( () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+ )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:881:2: () (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:881:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:882:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:887:2: (otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) ) )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==21) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:887:4: otherlv_2= 'OR' ( (lv_entries_3_0= ruleAndWhereEntry ) )
                    	    {
                    	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleWhereEntry1934); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getWhereEntryAccess().getORKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:891:1: ( (lv_entries_3_0= ruleAndWhereEntry ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:892:1: (lv_entries_3_0= ruleAndWhereEntry )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:892:1: (lv_entries_3_0= ruleAndWhereEntry )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:893:3: lv_entries_3_0= ruleAndWhereEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getWhereEntryAccess().getEntriesAndWhereEntryParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAndWhereEntry_in_ruleWhereEntry1955);
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
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:917:1: entryRuleAndWhereEntry returns [EObject current=null] : iv_ruleAndWhereEntry= ruleAndWhereEntry EOF ;
    public final EObject entryRuleAndWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:918:2: (iv_ruleAndWhereEntry= ruleAndWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:919:2: iv_ruleAndWhereEntry= ruleAndWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getAndWhereEntryRule()); 
            pushFollow(FOLLOW_ruleAndWhereEntry_in_entryRuleAndWhereEntry1995);
            iv_ruleAndWhereEntry=ruleAndWhereEntry();

            state._fsp--;

             current =iv_ruleAndWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndWhereEntry2005); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:926:1: ruleAndWhereEntry returns [EObject current=null] : (this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )? ) ;
    public final EObject ruleAndWhereEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ConcreteWhereEntry_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:929:28: ( (this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:930:1: (this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:930:1: (this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:931:5: this_ConcreteWhereEntry_0= ruleConcreteWhereEntry ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getAndWhereEntryAccess().getConcreteWhereEntryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_ruleAndWhereEntry2052);
            this_ConcreteWhereEntry_0=ruleConcreteWhereEntry();

            state._fsp--;

             
                    current = this_ConcreteWhereEntry_0; 
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:939:1: ( () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+ )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==22) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:939:2: () (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:939:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:940:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:945:2: (otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) ) )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==22) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:945:4: otherlv_2= 'AND' ( (lv_entries_3_0= ruleConcreteWhereEntry ) )
                    	    {
                    	    otherlv_2=(Token)match(input,22,FOLLOW_22_in_ruleAndWhereEntry2074); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getAndWhereEntryAccess().getANDKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:949:1: ( (lv_entries_3_0= ruleConcreteWhereEntry ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:950:1: (lv_entries_3_0= ruleConcreteWhereEntry )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:950:1: (lv_entries_3_0= ruleConcreteWhereEntry )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:951:3: lv_entries_3_0= ruleConcreteWhereEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAndWhereEntryAccess().getEntriesConcreteWhereEntryParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleConcreteWhereEntry_in_ruleAndWhereEntry2095);
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
    // $ANTLR end "ruleAndWhereEntry"


    // $ANTLR start "entryRuleConcreteWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:975:1: entryRuleConcreteWhereEntry returns [EObject current=null] : iv_ruleConcreteWhereEntry= ruleConcreteWhereEntry EOF ;
    public final EObject entryRuleConcreteWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConcreteWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:976:2: (iv_ruleConcreteWhereEntry= ruleConcreteWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:977:2: iv_ruleConcreteWhereEntry= ruleConcreteWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getConcreteWhereEntryRule()); 
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_entryRuleConcreteWhereEntry2135);
            iv_ruleConcreteWhereEntry=ruleConcreteWhereEntry();

            state._fsp--;

             current =iv_ruleConcreteWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConcreteWhereEntry2145); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:984:1: ruleConcreteWhereEntry returns [EObject current=null] : (this_ParWhereEntry_0= ruleParWhereEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry ) ;
    public final EObject ruleConcreteWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject this_ParWhereEntry_0 = null;

        EObject this_ExpressionWhereEntry_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:987:28: ( (this_ParWhereEntry_0= ruleParWhereEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:988:1: (this_ParWhereEntry_0= ruleParWhereEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:988:1: (this_ParWhereEntry_0= ruleParWhereEntry | this_ExpressionWhereEntry_1= ruleExpressionWhereEntry )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==23) ) {
                alt15=1;
            }
            else if ( (LA15_0==RULE_ID) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:989:5: this_ParWhereEntry_0= ruleParWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getConcreteWhereEntryAccess().getParWhereEntryParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleParWhereEntry_in_ruleConcreteWhereEntry2192);
                    this_ParWhereEntry_0=ruleParWhereEntry();

                    state._fsp--;

                     
                            current = this_ParWhereEntry_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:999:5: this_ExpressionWhereEntry_1= ruleExpressionWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getConcreteWhereEntryAccess().getExpressionWhereEntryParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleExpressionWhereEntry_in_ruleConcreteWhereEntry2219);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1015:1: entryRuleParWhereEntry returns [EObject current=null] : iv_ruleParWhereEntry= ruleParWhereEntry EOF ;
    public final EObject entryRuleParWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1016:2: (iv_ruleParWhereEntry= ruleParWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1017:2: iv_ruleParWhereEntry= ruleParWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getParWhereEntryRule()); 
            pushFollow(FOLLOW_ruleParWhereEntry_in_entryRuleParWhereEntry2254);
            iv_ruleParWhereEntry=ruleParWhereEntry();

            state._fsp--;

             current =iv_ruleParWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParWhereEntry2264); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1024:1: ruleParWhereEntry returns [EObject current=null] : (otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')' ) ;
    public final EObject ruleParWhereEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_WhereEntry_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1027:28: ( (otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1028:1: (otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1028:1: (otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1028:3: otherlv_0= '(' this_WhereEntry_1= ruleWhereEntry otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleParWhereEntry2301); 

                	newLeafNode(otherlv_0, grammarAccess.getParWhereEntryAccess().getLeftParenthesisKeyword_0());
                
             
                    newCompositeNode(grammarAccess.getParWhereEntryAccess().getWhereEntryParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleWhereEntry_in_ruleParWhereEntry2323);
            this_WhereEntry_1=ruleWhereEntry();

            state._fsp--;

             
                    current = this_WhereEntry_1; 
                    afterParserOrEnumRuleCall();
                
            otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleParWhereEntry2334); 

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


    // $ANTLR start "entryRuleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1053:1: entryRuleExpressionWhereEntry returns [EObject current=null] : iv_ruleExpressionWhereEntry= ruleExpressionWhereEntry EOF ;
    public final EObject entryRuleExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1054:2: (iv_ruleExpressionWhereEntry= ruleExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1055:2: iv_ruleExpressionWhereEntry= ruleExpressionWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleExpressionWhereEntry_in_entryRuleExpressionWhereEntry2370);
            iv_ruleExpressionWhereEntry=ruleExpressionWhereEntry();

            state._fsp--;

             current =iv_ruleExpressionWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionWhereEntry2380); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1062:1: ruleExpressionWhereEntry returns [EObject current=null] : (this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry | this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry ) ;
    public final EObject ruleExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject this_SingleExpressionWhereEntry_0 = null;

        EObject this_MultiExpressionWhereEntry_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1065:28: ( (this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry | this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1066:1: (this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry | this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1066:1: (this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry | this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 31:
                    {
                    int LA16_2 = input.LA(3);

                    if ( ((LA16_2>=RULE_SIGNED_DOUBLE && LA16_2<=RULE_DATE)||(LA16_2>=25 && LA16_2<=28)) ) {
                        alt16=1;
                    }
                    else if ( (LA16_2==29) ) {
                        alt16=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case 32:
                    {
                    int LA16_3 = input.LA(3);

                    if ( (LA16_3==29) ) {
                        alt16=2;
                    }
                    else if ( ((LA16_3>=RULE_SIGNED_DOUBLE && LA16_3<=RULE_DATE)||(LA16_3>=25 && LA16_3<=28)) ) {
                        alt16=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                    {
                    alt16=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1067:5: this_SingleExpressionWhereEntry_0= ruleSingleExpressionWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionWhereEntryAccess().getSingleExpressionWhereEntryParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleSingleExpressionWhereEntry_in_ruleExpressionWhereEntry2427);
                    this_SingleExpressionWhereEntry_0=ruleSingleExpressionWhereEntry();

                    state._fsp--;

                     
                            current = this_SingleExpressionWhereEntry_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1077:5: this_MultiExpressionWhereEntry_1= ruleMultiExpressionWhereEntry
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionWhereEntryAccess().getMultiExpressionWhereEntryParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleMultiExpressionWhereEntry_in_ruleExpressionWhereEntry2454);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1093:1: entryRuleSingleExpressionWhereEntry returns [EObject current=null] : iv_ruleSingleExpressionWhereEntry= ruleSingleExpressionWhereEntry EOF ;
    public final EObject entryRuleSingleExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingleExpressionWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1094:2: (iv_ruleSingleExpressionWhereEntry= ruleSingleExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1095:2: iv_ruleSingleExpressionWhereEntry= ruleSingleExpressionWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getSingleExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleSingleExpressionWhereEntry_in_entryRuleSingleExpressionWhereEntry2489);
            iv_ruleSingleExpressionWhereEntry=ruleSingleExpressionWhereEntry();

            state._fsp--;

             current =iv_ruleSingleExpressionWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSingleExpressionWhereEntry2499); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1102:1: ruleSingleExpressionWhereEntry returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) ) ) ;
    public final EObject ruleSingleExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Enumerator lv_operator_1_0 = null;

        EObject lv_rhs_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1105:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1106:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1106:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1106:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleOperator ) ) ( (lv_rhs_2_0= ruleExpression ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1106:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1107:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1107:1: (lv_name_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1108:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSingleExpressionWhereEntry2541); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1124:2: ( (lv_operator_1_0= ruleOperator ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1125:1: (lv_operator_1_0= ruleOperator )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1125:1: (lv_operator_1_0= ruleOperator )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1126:3: lv_operator_1_0= ruleOperator
            {
             
            	        newCompositeNode(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorOperatorEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperator_in_ruleSingleExpressionWhereEntry2567);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1142:2: ( (lv_rhs_2_0= ruleExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1143:1: (lv_rhs_2_0= ruleExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1143:1: (lv_rhs_2_0= ruleExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1144:3: lv_rhs_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleSingleExpressionWhereEntry2588);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1168:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1169:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1170:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression2624);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2634); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1177:1: ruleExpression returns [EObject current=null] : (this_DoubleExpression_0= ruleDoubleExpression | this_LongExpression_1= ruleLongExpression | this_StringExpression_2= ruleStringExpression | this_NullExpression_3= ruleNullExpression | this_DateExpression_4= ruleDateExpression | this_BooleanExpression_5= ruleBooleanExpression | this_ReplacableValue_6= ruleReplacableValue ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1180:28: ( (this_DoubleExpression_0= ruleDoubleExpression | this_LongExpression_1= ruleLongExpression | this_StringExpression_2= ruleStringExpression | this_NullExpression_3= ruleNullExpression | this_DateExpression_4= ruleDateExpression | this_BooleanExpression_5= ruleBooleanExpression | this_ReplacableValue_6= ruleReplacableValue ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1181:1: (this_DoubleExpression_0= ruleDoubleExpression | this_LongExpression_1= ruleLongExpression | this_StringExpression_2= ruleStringExpression | this_NullExpression_3= ruleNullExpression | this_DateExpression_4= ruleDateExpression | this_BooleanExpression_5= ruleBooleanExpression | this_ReplacableValue_6= ruleReplacableValue )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1181:1: (this_DoubleExpression_0= ruleDoubleExpression | this_LongExpression_1= ruleLongExpression | this_StringExpression_2= ruleStringExpression | this_NullExpression_3= ruleNullExpression | this_DateExpression_4= ruleDateExpression | this_BooleanExpression_5= ruleBooleanExpression | this_ReplacableValue_6= ruleReplacableValue )
            int alt17=7;
            switch ( input.LA(1) ) {
            case RULE_SIGNED_DOUBLE:
                {
                alt17=1;
                }
                break;
            case RULE_SINGED_LONG:
                {
                alt17=2;
                }
                break;
            case RULE_STRING:
                {
                alt17=3;
                }
                break;
            case 26:
                {
                alt17=4;
                }
                break;
            case RULE_DATE:
                {
                alt17=5;
                }
                break;
            case 27:
            case 28:
                {
                alt17=6;
                }
                break;
            case 25:
                {
                alt17=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1182:5: this_DoubleExpression_0= ruleDoubleExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getDoubleExpressionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleDoubleExpression_in_ruleExpression2681);
                    this_DoubleExpression_0=ruleDoubleExpression();

                    state._fsp--;

                     
                            current = this_DoubleExpression_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1192:5: this_LongExpression_1= ruleLongExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getLongExpressionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLongExpression_in_ruleExpression2708);
                    this_LongExpression_1=ruleLongExpression();

                    state._fsp--;

                     
                            current = this_LongExpression_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1202:5: this_StringExpression_2= ruleStringExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getStringExpressionParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleStringExpression_in_ruleExpression2735);
                    this_StringExpression_2=ruleStringExpression();

                    state._fsp--;

                     
                            current = this_StringExpression_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1212:5: this_NullExpression_3= ruleNullExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getNullExpressionParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleNullExpression_in_ruleExpression2762);
                    this_NullExpression_3=ruleNullExpression();

                    state._fsp--;

                     
                            current = this_NullExpression_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1222:5: this_DateExpression_4= ruleDateExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getDateExpressionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleDateExpression_in_ruleExpression2789);
                    this_DateExpression_4=ruleDateExpression();

                    state._fsp--;

                     
                            current = this_DateExpression_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1232:5: this_BooleanExpression_5= ruleBooleanExpression
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getBooleanExpressionParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleBooleanExpression_in_ruleExpression2816);
                    this_BooleanExpression_5=ruleBooleanExpression();

                    state._fsp--;

                     
                            current = this_BooleanExpression_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1242:5: this_ReplacableValue_6= ruleReplacableValue
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getReplacableValueParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleReplacableValue_in_ruleExpression2843);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1258:1: entryRuleReplacableValue returns [EObject current=null] : iv_ruleReplacableValue= ruleReplacableValue EOF ;
    public final EObject entryRuleReplacableValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReplacableValue = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1259:2: (iv_ruleReplacableValue= ruleReplacableValue EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1260:2: iv_ruleReplacableValue= ruleReplacableValue EOF
            {
             newCompositeNode(grammarAccess.getReplacableValueRule()); 
            pushFollow(FOLLOW_ruleReplacableValue_in_entryRuleReplacableValue2878);
            iv_ruleReplacableValue=ruleReplacableValue();

            state._fsp--;

             current =iv_ruleReplacableValue; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleReplacableValue2888); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1267:1: ruleReplacableValue returns [EObject current=null] : ( (lv_value_0_0= '?' ) ) ;
    public final EObject ruleReplacableValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1270:28: ( ( (lv_value_0_0= '?' ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1271:1: ( (lv_value_0_0= '?' ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1271:1: ( (lv_value_0_0= '?' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1272:1: (lv_value_0_0= '?' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1272:1: (lv_value_0_0= '?' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1273:3: lv_value_0_0= '?'
            {
            lv_value_0_0=(Token)match(input,25,FOLLOW_25_in_ruleReplacableValue2930); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1294:1: entryRuleDoubleExpression returns [EObject current=null] : iv_ruleDoubleExpression= ruleDoubleExpression EOF ;
    public final EObject entryRuleDoubleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoubleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1295:2: (iv_ruleDoubleExpression= ruleDoubleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1296:2: iv_ruleDoubleExpression= ruleDoubleExpression EOF
            {
             newCompositeNode(grammarAccess.getDoubleExpressionRule()); 
            pushFollow(FOLLOW_ruleDoubleExpression_in_entryRuleDoubleExpression2978);
            iv_ruleDoubleExpression=ruleDoubleExpression();

            state._fsp--;

             current =iv_ruleDoubleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleExpression2988); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1303:1: ruleDoubleExpression returns [EObject current=null] : ( (lv_value_0_0= RULE_SIGNED_DOUBLE ) ) ;
    public final EObject ruleDoubleExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1306:28: ( ( (lv_value_0_0= RULE_SIGNED_DOUBLE ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1307:1: ( (lv_value_0_0= RULE_SIGNED_DOUBLE ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1307:1: ( (lv_value_0_0= RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1308:1: (lv_value_0_0= RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1308:1: (lv_value_0_0= RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1309:3: lv_value_0_0= RULE_SIGNED_DOUBLE
            {
            lv_value_0_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleExpression3029); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1333:1: entryRuleLongExpression returns [EObject current=null] : iv_ruleLongExpression= ruleLongExpression EOF ;
    public final EObject entryRuleLongExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLongExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1334:2: (iv_ruleLongExpression= ruleLongExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1335:2: iv_ruleLongExpression= ruleLongExpression EOF
            {
             newCompositeNode(grammarAccess.getLongExpressionRule()); 
            pushFollow(FOLLOW_ruleLongExpression_in_entryRuleLongExpression3069);
            iv_ruleLongExpression=ruleLongExpression();

            state._fsp--;

             current =iv_ruleLongExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongExpression3079); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1342:1: ruleLongExpression returns [EObject current=null] : ( (lv_value_0_0= RULE_SINGED_LONG ) ) ;
    public final EObject ruleLongExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1345:28: ( ( (lv_value_0_0= RULE_SINGED_LONG ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1346:1: ( (lv_value_0_0= RULE_SINGED_LONG ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1346:1: ( (lv_value_0_0= RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1347:1: (lv_value_0_0= RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1347:1: (lv_value_0_0= RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1348:3: lv_value_0_0= RULE_SINGED_LONG
            {
            lv_value_0_0=(Token)match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_ruleLongExpression3120); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1372:1: entryRuleStringExpression returns [EObject current=null] : iv_ruleStringExpression= ruleStringExpression EOF ;
    public final EObject entryRuleStringExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1373:2: (iv_ruleStringExpression= ruleStringExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1374:2: iv_ruleStringExpression= ruleStringExpression EOF
            {
             newCompositeNode(grammarAccess.getStringExpressionRule()); 
            pushFollow(FOLLOW_ruleStringExpression_in_entryRuleStringExpression3160);
            iv_ruleStringExpression=ruleStringExpression();

            state._fsp--;

             current =iv_ruleStringExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringExpression3170); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1381:1: ruleStringExpression returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1384:28: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1385:1: ( (lv_value_0_0= RULE_STRING ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1385:1: ( (lv_value_0_0= RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1386:1: (lv_value_0_0= RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1386:1: (lv_value_0_0= RULE_STRING )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1387:3: lv_value_0_0= RULE_STRING
            {
            lv_value_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringExpression3211); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1411:1: entryRuleNullExpression returns [EObject current=null] : iv_ruleNullExpression= ruleNullExpression EOF ;
    public final EObject entryRuleNullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1412:2: (iv_ruleNullExpression= ruleNullExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1413:2: iv_ruleNullExpression= ruleNullExpression EOF
            {
             newCompositeNode(grammarAccess.getNullExpressionRule()); 
            pushFollow(FOLLOW_ruleNullExpression_in_entryRuleNullExpression3251);
            iv_ruleNullExpression=ruleNullExpression();

            state._fsp--;

             current =iv_ruleNullExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullExpression3261); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1420:1: ruleNullExpression returns [EObject current=null] : ( (lv_value_0_0= 'null' ) ) ;
    public final EObject ruleNullExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1423:28: ( ( (lv_value_0_0= 'null' ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1424:1: ( (lv_value_0_0= 'null' ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1424:1: ( (lv_value_0_0= 'null' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1425:1: (lv_value_0_0= 'null' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1425:1: (lv_value_0_0= 'null' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1426:3: lv_value_0_0= 'null'
            {
            lv_value_0_0=(Token)match(input,26,FOLLOW_26_in_ruleNullExpression3303); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1447:1: entryRuleDateExpression returns [EObject current=null] : iv_ruleDateExpression= ruleDateExpression EOF ;
    public final EObject entryRuleDateExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDateExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1448:2: (iv_ruleDateExpression= ruleDateExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1449:2: iv_ruleDateExpression= ruleDateExpression EOF
            {
             newCompositeNode(grammarAccess.getDateExpressionRule()); 
            pushFollow(FOLLOW_ruleDateExpression_in_entryRuleDateExpression3351);
            iv_ruleDateExpression=ruleDateExpression();

            state._fsp--;

             current =iv_ruleDateExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDateExpression3361); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1456:1: ruleDateExpression returns [EObject current=null] : ( (lv_value_0_0= RULE_DATE ) ) ;
    public final EObject ruleDateExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1459:28: ( ( (lv_value_0_0= RULE_DATE ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1460:1: ( (lv_value_0_0= RULE_DATE ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1460:1: ( (lv_value_0_0= RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1461:1: (lv_value_0_0= RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1461:1: (lv_value_0_0= RULE_DATE )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1462:3: lv_value_0_0= RULE_DATE
            {
            lv_value_0_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleDateExpression3402); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1486:1: entryRuleBooleanExpression returns [EObject current=null] : iv_ruleBooleanExpression= ruleBooleanExpression EOF ;
    public final EObject entryRuleBooleanExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1487:2: (iv_ruleBooleanExpression= ruleBooleanExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1488:2: iv_ruleBooleanExpression= ruleBooleanExpression EOF
            {
             newCompositeNode(grammarAccess.getBooleanExpressionRule()); 
            pushFollow(FOLLOW_ruleBooleanExpression_in_entryRuleBooleanExpression3442);
            iv_ruleBooleanExpression=ruleBooleanExpression();

            state._fsp--;

             current =iv_ruleBooleanExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanExpression3452); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1495:1: ruleBooleanExpression returns [EObject current=null] : ( ( (lv_true_0_0= 'true' ) ) | ( (lv_true_1_0= 'false' ) ) ) ;
    public final EObject ruleBooleanExpression() throws RecognitionException {
        EObject current = null;

        Token lv_true_0_0=null;
        Token lv_true_1_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1498:28: ( ( ( (lv_true_0_0= 'true' ) ) | ( (lv_true_1_0= 'false' ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1499:1: ( ( (lv_true_0_0= 'true' ) ) | ( (lv_true_1_0= 'false' ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1499:1: ( ( (lv_true_0_0= 'true' ) ) | ( (lv_true_1_0= 'false' ) ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==27) ) {
                alt18=1;
            }
            else if ( (LA18_0==28) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1499:2: ( (lv_true_0_0= 'true' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1499:2: ( (lv_true_0_0= 'true' ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1500:1: (lv_true_0_0= 'true' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1500:1: (lv_true_0_0= 'true' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1501:3: lv_true_0_0= 'true'
                    {
                    lv_true_0_0=(Token)match(input,27,FOLLOW_27_in_ruleBooleanExpression3495); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1515:6: ( (lv_true_1_0= 'false' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1515:6: ( (lv_true_1_0= 'false' ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1516:1: (lv_true_1_0= 'false' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1516:1: (lv_true_1_0= 'false' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1517:3: lv_true_1_0= 'false'
                    {
                    lv_true_1_0=(Token)match(input,28,FOLLOW_28_in_ruleBooleanExpression3532); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1538:1: entryRuleMultiExpressionWhereEntry returns [EObject current=null] : iv_ruleMultiExpressionWhereEntry= ruleMultiExpressionWhereEntry EOF ;
    public final EObject entryRuleMultiExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiExpressionWhereEntry = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1539:2: (iv_ruleMultiExpressionWhereEntry= ruleMultiExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1540:2: iv_ruleMultiExpressionWhereEntry= ruleMultiExpressionWhereEntry EOF
            {
             newCompositeNode(grammarAccess.getMultiExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleMultiExpressionWhereEntry_in_entryRuleMultiExpressionWhereEntry3581);
            iv_ruleMultiExpressionWhereEntry=ruleMultiExpressionWhereEntry();

            state._fsp--;

             current =iv_ruleMultiExpressionWhereEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiExpressionWhereEntry3591); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1547:1: ruleMultiExpressionWhereEntry returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) ) ) ;
    public final EObject ruleMultiExpressionWhereEntry() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Enumerator lv_operator_1_0 = null;

        EObject lv_rhs_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1550:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1551:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1551:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1551:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleArrayOperator ) ) ( (lv_rhs_2_0= ruleArrayExpression ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1551:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1552:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1552:1: (lv_name_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1553:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleMultiExpressionWhereEntry3633); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1569:2: ( (lv_operator_1_0= ruleArrayOperator ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1570:1: (lv_operator_1_0= ruleArrayOperator )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1570:1: (lv_operator_1_0= ruleArrayOperator )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1571:3: lv_operator_1_0= ruleArrayOperator
            {
             
            	        newCompositeNode(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorArrayOperatorEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleArrayOperator_in_ruleMultiExpressionWhereEntry3659);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1587:2: ( (lv_rhs_2_0= ruleArrayExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1588:1: (lv_rhs_2_0= ruleArrayExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1588:1: (lv_rhs_2_0= ruleArrayExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1589:3: lv_rhs_2_0= ruleArrayExpression
            {
             
            	        newCompositeNode(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsArrayExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleArrayExpression_in_ruleMultiExpressionWhereEntry3680);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1613:1: entryRuleArrayExpression returns [EObject current=null] : iv_ruleArrayExpression= ruleArrayExpression EOF ;
    public final EObject entryRuleArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1614:2: (iv_ruleArrayExpression= ruleArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1615:2: iv_ruleArrayExpression= ruleArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleArrayExpression_in_entryRuleArrayExpression3716);
            iv_ruleArrayExpression=ruleArrayExpression();

            state._fsp--;

             current =iv_ruleArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayExpression3726); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1622:1: ruleArrayExpression returns [EObject current=null] : (this_DoubleArrayExpression_0= ruleDoubleArrayExpression | this_LongArrayExpression_1= ruleLongArrayExpression | this_StringArrayExpression_2= ruleStringArrayExpression | this_NullArrayExpression_3= ruleNullArrayExpression | this_DateArrayExpression_4= ruleDateArrayExpression | this_BooleanArrayExpression_5= ruleBooleanArrayExpression ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1625:28: ( (this_DoubleArrayExpression_0= ruleDoubleArrayExpression | this_LongArrayExpression_1= ruleLongArrayExpression | this_StringArrayExpression_2= ruleStringArrayExpression | this_NullArrayExpression_3= ruleNullArrayExpression | this_DateArrayExpression_4= ruleDateArrayExpression | this_BooleanArrayExpression_5= ruleBooleanArrayExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1626:1: (this_DoubleArrayExpression_0= ruleDoubleArrayExpression | this_LongArrayExpression_1= ruleLongArrayExpression | this_StringArrayExpression_2= ruleStringArrayExpression | this_NullArrayExpression_3= ruleNullArrayExpression | this_DateArrayExpression_4= ruleDateArrayExpression | this_BooleanArrayExpression_5= ruleBooleanArrayExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1626:1: (this_DoubleArrayExpression_0= ruleDoubleArrayExpression | this_LongArrayExpression_1= ruleLongArrayExpression | this_StringArrayExpression_2= ruleStringArrayExpression | this_NullArrayExpression_3= ruleNullArrayExpression | this_DateArrayExpression_4= ruleDateArrayExpression | this_BooleanArrayExpression_5= ruleBooleanArrayExpression )
            int alt19=6;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==29) ) {
                switch ( input.LA(2) ) {
                case RULE_DATE:
                    {
                    alt19=5;
                    }
                    break;
                case RULE_SINGED_LONG:
                    {
                    alt19=2;
                    }
                    break;
                case 26:
                    {
                    alt19=4;
                    }
                    break;
                case RULE_SIGNED_DOUBLE:
                    {
                    alt19=1;
                    }
                    break;
                case RULE_STRING:
                    {
                    alt19=3;
                    }
                    break;
                case RULE_BOOL:
                    {
                    alt19=6;
                    }
                    break;
                default:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1627:5: this_DoubleArrayExpression_0= ruleDoubleArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getDoubleArrayExpressionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleDoubleArrayExpression_in_ruleArrayExpression3773);
                    this_DoubleArrayExpression_0=ruleDoubleArrayExpression();

                    state._fsp--;

                     
                            current = this_DoubleArrayExpression_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1637:5: this_LongArrayExpression_1= ruleLongArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getLongArrayExpressionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLongArrayExpression_in_ruleArrayExpression3800);
                    this_LongArrayExpression_1=ruleLongArrayExpression();

                    state._fsp--;

                     
                            current = this_LongArrayExpression_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1647:5: this_StringArrayExpression_2= ruleStringArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getStringArrayExpressionParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleStringArrayExpression_in_ruleArrayExpression3827);
                    this_StringArrayExpression_2=ruleStringArrayExpression();

                    state._fsp--;

                     
                            current = this_StringArrayExpression_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1657:5: this_NullArrayExpression_3= ruleNullArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getNullArrayExpressionParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleNullArrayExpression_in_ruleArrayExpression3854);
                    this_NullArrayExpression_3=ruleNullArrayExpression();

                    state._fsp--;

                     
                            current = this_NullArrayExpression_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1667:5: this_DateArrayExpression_4= ruleDateArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getDateArrayExpressionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleDateArrayExpression_in_ruleArrayExpression3881);
                    this_DateArrayExpression_4=ruleDateArrayExpression();

                    state._fsp--;

                     
                            current = this_DateArrayExpression_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1677:5: this_BooleanArrayExpression_5= ruleBooleanArrayExpression
                    {
                     
                            newCompositeNode(grammarAccess.getArrayExpressionAccess().getBooleanArrayExpressionParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleBooleanArrayExpression_in_ruleArrayExpression3908);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1693:1: entryRuleDoubleArrayExpression returns [EObject current=null] : iv_ruleDoubleArrayExpression= ruleDoubleArrayExpression EOF ;
    public final EObject entryRuleDoubleArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoubleArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1694:2: (iv_ruleDoubleArrayExpression= ruleDoubleArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1695:2: iv_ruleDoubleArrayExpression= ruleDoubleArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getDoubleArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleDoubleArrayExpression_in_entryRuleDoubleArrayExpression3943);
            iv_ruleDoubleArrayExpression=ruleDoubleArrayExpression();

            state._fsp--;

             current =iv_ruleDoubleArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleArrayExpression3953); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1702:1: ruleDoubleArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleDoubleArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1705:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1706:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1706:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1706:3: otherlv_0= '[' ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_29_in_ruleDoubleArrayExpression3990); 

                	newLeafNode(otherlv_0, grammarAccess.getDoubleArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1710:1: ( (lv_values_1_0= RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1711:1: (lv_values_1_0= RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1711:1: (lv_values_1_0= RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1712:3: lv_values_1_0= RULE_SIGNED_DOUBLE
            {
            lv_values_1_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleArrayExpression4007); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1728:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==18) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1728:4: otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) )
            	    {
            	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleDoubleArrayExpression4025); 

            	        	newLeafNode(otherlv_2, grammarAccess.getDoubleArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1732:1: ( (lv_values_3_0= RULE_SIGNED_DOUBLE ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1733:1: (lv_values_3_0= RULE_SIGNED_DOUBLE )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1733:1: (lv_values_3_0= RULE_SIGNED_DOUBLE )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1734:3: lv_values_3_0= RULE_SIGNED_DOUBLE
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleArrayExpression4042); 

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
            	    break loop20;
                }
            } while (true);

            otherlv_4=(Token)match(input,30,FOLLOW_30_in_ruleDoubleArrayExpression4061); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1762:1: entryRuleLongArrayExpression returns [EObject current=null] : iv_ruleLongArrayExpression= ruleLongArrayExpression EOF ;
    public final EObject entryRuleLongArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLongArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1763:2: (iv_ruleLongArrayExpression= ruleLongArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1764:2: iv_ruleLongArrayExpression= ruleLongArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getLongArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleLongArrayExpression_in_entryRuleLongArrayExpression4097);
            iv_ruleLongArrayExpression=ruleLongArrayExpression();

            state._fsp--;

             current =iv_ruleLongArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongArrayExpression4107); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1771:1: ruleLongArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleLongArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1774:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1775:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1775:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1775:3: otherlv_0= '[' ( (lv_values_1_0= RULE_SINGED_LONG ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_29_in_ruleLongArrayExpression4144); 

                	newLeafNode(otherlv_0, grammarAccess.getLongArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1779:1: ( (lv_values_1_0= RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1780:1: (lv_values_1_0= RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1780:1: (lv_values_1_0= RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1781:3: lv_values_1_0= RULE_SINGED_LONG
            {
            lv_values_1_0=(Token)match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_ruleLongArrayExpression4161); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1797:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==18) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1797:4: otherlv_2= ',' ( (lv_values_3_0= RULE_SINGED_LONG ) )
            	    {
            	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleLongArrayExpression4179); 

            	        	newLeafNode(otherlv_2, grammarAccess.getLongArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1801:1: ( (lv_values_3_0= RULE_SINGED_LONG ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1802:1: (lv_values_3_0= RULE_SINGED_LONG )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1802:1: (lv_values_3_0= RULE_SINGED_LONG )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1803:3: lv_values_3_0= RULE_SINGED_LONG
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_ruleLongArrayExpression4196); 

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
            	    break loop21;
                }
            } while (true);

            otherlv_4=(Token)match(input,30,FOLLOW_30_in_ruleLongArrayExpression4215); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1831:1: entryRuleStringArrayExpression returns [EObject current=null] : iv_ruleStringArrayExpression= ruleStringArrayExpression EOF ;
    public final EObject entryRuleStringArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1832:2: (iv_ruleStringArrayExpression= ruleStringArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1833:2: iv_ruleStringArrayExpression= ruleStringArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getStringArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleStringArrayExpression_in_entryRuleStringArrayExpression4251);
            iv_ruleStringArrayExpression=ruleStringArrayExpression();

            state._fsp--;

             current =iv_ruleStringArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringArrayExpression4261); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1840:1: ruleStringArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleStringArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1843:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1844:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1844:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1844:3: otherlv_0= '[' ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_29_in_ruleStringArrayExpression4298); 

                	newLeafNode(otherlv_0, grammarAccess.getStringArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1848:1: ( (lv_values_1_0= RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1849:1: (lv_values_1_0= RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1849:1: (lv_values_1_0= RULE_STRING )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1850:3: lv_values_1_0= RULE_STRING
            {
            lv_values_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringArrayExpression4315); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1866:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==18) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1866:4: otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) )
            	    {
            	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleStringArrayExpression4333); 

            	        	newLeafNode(otherlv_2, grammarAccess.getStringArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1870:1: ( (lv_values_3_0= RULE_STRING ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1871:1: (lv_values_3_0= RULE_STRING )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1871:1: (lv_values_3_0= RULE_STRING )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1872:3: lv_values_3_0= RULE_STRING
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringArrayExpression4350); 

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
            	    break loop22;
                }
            } while (true);

            otherlv_4=(Token)match(input,30,FOLLOW_30_in_ruleStringArrayExpression4369); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1900:1: entryRuleNullArrayExpression returns [EObject current=null] : iv_ruleNullArrayExpression= ruleNullArrayExpression EOF ;
    public final EObject entryRuleNullArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1901:2: (iv_ruleNullArrayExpression= ruleNullArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1902:2: iv_ruleNullArrayExpression= ruleNullArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getNullArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleNullArrayExpression_in_entryRuleNullArrayExpression4405);
            iv_ruleNullArrayExpression=ruleNullArrayExpression();

            state._fsp--;

             current =iv_ruleNullArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullArrayExpression4415); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1909:1: ruleNullArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleNullArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1912:28: ( (otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1913:1: (otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1913:1: (otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1913:3: otherlv_0= '[' ( (lv_values_1_0= 'null' ) ) (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_29_in_ruleNullArrayExpression4452); 

                	newLeafNode(otherlv_0, grammarAccess.getNullArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1917:1: ( (lv_values_1_0= 'null' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1918:1: (lv_values_1_0= 'null' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1918:1: (lv_values_1_0= 'null' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1919:3: lv_values_1_0= 'null'
            {
            lv_values_1_0=(Token)match(input,26,FOLLOW_26_in_ruleNullArrayExpression4470); 

                    newLeafNode(lv_values_1_0, grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNullArrayExpressionRule());
            	        }
                   		addWithLastConsumed(current, "values", lv_values_1_0, "null");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1932:2: (otherlv_2= ',' ( (lv_values_3_0= 'null' ) ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==18) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1932:4: otherlv_2= ',' ( (lv_values_3_0= 'null' ) )
            	    {
            	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleNullArrayExpression4496); 

            	        	newLeafNode(otherlv_2, grammarAccess.getNullArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1936:1: ( (lv_values_3_0= 'null' ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1937:1: (lv_values_3_0= 'null' )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1937:1: (lv_values_3_0= 'null' )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1938:3: lv_values_3_0= 'null'
            	    {
            	    lv_values_3_0=(Token)match(input,26,FOLLOW_26_in_ruleNullArrayExpression4514); 

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
            	    break loop23;
                }
            } while (true);

            otherlv_4=(Token)match(input,30,FOLLOW_30_in_ruleNullArrayExpression4541); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1963:1: entryRuleDateArrayExpression returns [EObject current=null] : iv_ruleDateArrayExpression= ruleDateArrayExpression EOF ;
    public final EObject entryRuleDateArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDateArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1964:2: (iv_ruleDateArrayExpression= ruleDateArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1965:2: iv_ruleDateArrayExpression= ruleDateArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getDateArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleDateArrayExpression_in_entryRuleDateArrayExpression4577);
            iv_ruleDateArrayExpression=ruleDateArrayExpression();

            state._fsp--;

             current =iv_ruleDateArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDateArrayExpression4587); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1972:1: ruleDateArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleDateArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1975:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1976:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1976:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1976:3: otherlv_0= '[' ( (lv_values_1_0= RULE_DATE ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_29_in_ruleDateArrayExpression4624); 

                	newLeafNode(otherlv_0, grammarAccess.getDateArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1980:1: ( (lv_values_1_0= RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1981:1: (lv_values_1_0= RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1981:1: (lv_values_1_0= RULE_DATE )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1982:3: lv_values_1_0= RULE_DATE
            {
            lv_values_1_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleDateArrayExpression4641); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1998:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==18) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:1998:4: otherlv_2= ',' ( (lv_values_3_0= RULE_DATE ) )
            	    {
            	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleDateArrayExpression4659); 

            	        	newLeafNode(otherlv_2, grammarAccess.getDateArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2002:1: ( (lv_values_3_0= RULE_DATE ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2003:1: (lv_values_3_0= RULE_DATE )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2003:1: (lv_values_3_0= RULE_DATE )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2004:3: lv_values_3_0= RULE_DATE
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleDateArrayExpression4676); 

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
            	    break loop24;
                }
            } while (true);

            otherlv_4=(Token)match(input,30,FOLLOW_30_in_ruleDateArrayExpression4695); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2032:1: entryRuleBooleanArrayExpression returns [EObject current=null] : iv_ruleBooleanArrayExpression= ruleBooleanArrayExpression EOF ;
    public final EObject entryRuleBooleanArrayExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanArrayExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2033:2: (iv_ruleBooleanArrayExpression= ruleBooleanArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2034:2: iv_ruleBooleanArrayExpression= ruleBooleanArrayExpression EOF
            {
             newCompositeNode(grammarAccess.getBooleanArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleBooleanArrayExpression_in_entryRuleBooleanArrayExpression4731);
            iv_ruleBooleanArrayExpression=ruleBooleanArrayExpression();

            state._fsp--;

             current =iv_ruleBooleanArrayExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanArrayExpression4741); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2041:1: ruleBooleanArrayExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleBooleanArrayExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2044:28: ( (otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2045:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2045:1: (otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2045:3: otherlv_0= '[' ( (lv_values_1_0= RULE_BOOL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_29_in_ruleBooleanArrayExpression4778); 

                	newLeafNode(otherlv_0, grammarAccess.getBooleanArrayExpressionAccess().getLeftSquareBracketKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2049:1: ( (lv_values_1_0= RULE_BOOL ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2050:1: (lv_values_1_0= RULE_BOOL )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2050:1: (lv_values_1_0= RULE_BOOL )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2051:3: lv_values_1_0= RULE_BOOL
            {
            lv_values_1_0=(Token)match(input,RULE_BOOL,FOLLOW_RULE_BOOL_in_ruleBooleanArrayExpression4795); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2067:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==18) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2067:4: otherlv_2= ',' ( (lv_values_3_0= RULE_BOOL ) )
            	    {
            	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleBooleanArrayExpression4813); 

            	        	newLeafNode(otherlv_2, grammarAccess.getBooleanArrayExpressionAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2071:1: ( (lv_values_3_0= RULE_BOOL ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2072:1: (lv_values_3_0= RULE_BOOL )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2072:1: (lv_values_3_0= RULE_BOOL )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2073:3: lv_values_3_0= RULE_BOOL
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_BOOL,FOLLOW_RULE_BOOL_in_ruleBooleanArrayExpression4830); 

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
            	    break loop25;
                }
            } while (true);

            otherlv_4=(Token)match(input,30,FOLLOW_30_in_ruleBooleanArrayExpression4849); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2101:1: ruleArrayOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'in' ) | (enumLiteral_1= 'not in' ) ) ;
    public final Enumerator ruleArrayOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2103:28: ( ( (enumLiteral_0= 'in' ) | (enumLiteral_1= 'not in' ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2104:1: ( (enumLiteral_0= 'in' ) | (enumLiteral_1= 'not in' ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2104:1: ( (enumLiteral_0= 'in' ) | (enumLiteral_1= 'not in' ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==31) ) {
                alt26=1;
            }
            else if ( (LA26_0==32) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2104:2: (enumLiteral_0= 'in' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2104:2: (enumLiteral_0= 'in' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2104:4: enumLiteral_0= 'in'
                    {
                    enumLiteral_0=(Token)match(input,31,FOLLOW_31_in_ruleArrayOperator4899); 

                            current = grammarAccess.getArrayOperatorAccess().getSql_inEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getArrayOperatorAccess().getSql_inEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2110:6: (enumLiteral_1= 'not in' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2110:6: (enumLiteral_1= 'not in' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2110:8: enumLiteral_1= 'not in'
                    {
                    enumLiteral_1=(Token)match(input,32,FOLLOW_32_in_ruleArrayOperator4916); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2120:1: ruleOperator returns [Enumerator current=null] : ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '=' ) | (enumLiteral_5= '!=' ) | (enumLiteral_6= 'like' ) | (enumLiteral_7= 'not like' ) | (enumLiteral_8= 'not in' ) | (enumLiteral_9= 'in' ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2122:28: ( ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '=' ) | (enumLiteral_5= '!=' ) | (enumLiteral_6= 'like' ) | (enumLiteral_7= 'not like' ) | (enumLiteral_8= 'not in' ) | (enumLiteral_9= 'in' ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2123:1: ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '=' ) | (enumLiteral_5= '!=' ) | (enumLiteral_6= 'like' ) | (enumLiteral_7= 'not like' ) | (enumLiteral_8= 'not in' ) | (enumLiteral_9= 'in' ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2123:1: ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '=' ) | (enumLiteral_5= '!=' ) | (enumLiteral_6= 'like' ) | (enumLiteral_7= 'not like' ) | (enumLiteral_8= 'not in' ) | (enumLiteral_9= 'in' ) )
            int alt27=10;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt27=1;
                }
                break;
            case 34:
                {
                alt27=2;
                }
                break;
            case 35:
                {
                alt27=3;
                }
                break;
            case 36:
                {
                alt27=4;
                }
                break;
            case 37:
                {
                alt27=5;
                }
                break;
            case 38:
                {
                alt27=6;
                }
                break;
            case 39:
                {
                alt27=7;
                }
                break;
            case 40:
                {
                alt27=8;
                }
                break;
            case 32:
                {
                alt27=9;
                }
                break;
            case 31:
                {
                alt27=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2123:2: (enumLiteral_0= '<' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2123:2: (enumLiteral_0= '<' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2123:4: enumLiteral_0= '<'
                    {
                    enumLiteral_0=(Token)match(input,33,FOLLOW_33_in_ruleOperator4961); 

                            current = grammarAccess.getOperatorAccess().getLessThenEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getOperatorAccess().getLessThenEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2129:6: (enumLiteral_1= '>' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2129:6: (enumLiteral_1= '>' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2129:8: enumLiteral_1= '>'
                    {
                    enumLiteral_1=(Token)match(input,34,FOLLOW_34_in_ruleOperator4978); 

                            current = grammarAccess.getOperatorAccess().getGreaterThenEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getOperatorAccess().getGreaterThenEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2135:6: (enumLiteral_2= '<=' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2135:6: (enumLiteral_2= '<=' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2135:8: enumLiteral_2= '<='
                    {
                    enumLiteral_2=(Token)match(input,35,FOLLOW_35_in_ruleOperator4995); 

                            current = grammarAccess.getOperatorAccess().getLessEqualEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getOperatorAccess().getLessEqualEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2141:6: (enumLiteral_3= '>=' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2141:6: (enumLiteral_3= '>=' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2141:8: enumLiteral_3= '>='
                    {
                    enumLiteral_3=(Token)match(input,36,FOLLOW_36_in_ruleOperator5012); 

                            current = grammarAccess.getOperatorAccess().getGreaterEqualEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getOperatorAccess().getGreaterEqualEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2147:6: (enumLiteral_4= '=' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2147:6: (enumLiteral_4= '=' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2147:8: enumLiteral_4= '='
                    {
                    enumLiteral_4=(Token)match(input,37,FOLLOW_37_in_ruleOperator5029); 

                            current = grammarAccess.getOperatorAccess().getEqualEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getOperatorAccess().getEqualEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2153:6: (enumLiteral_5= '!=' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2153:6: (enumLiteral_5= '!=' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2153:8: enumLiteral_5= '!='
                    {
                    enumLiteral_5=(Token)match(input,38,FOLLOW_38_in_ruleOperator5046); 

                            current = grammarAccess.getOperatorAccess().getNotEqualEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getOperatorAccess().getNotEqualEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2159:6: (enumLiteral_6= 'like' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2159:6: (enumLiteral_6= 'like' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2159:8: enumLiteral_6= 'like'
                    {
                    enumLiteral_6=(Token)match(input,39,FOLLOW_39_in_ruleOperator5063); 

                            current = grammarAccess.getOperatorAccess().getLikeEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getOperatorAccess().getLikeEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2165:6: (enumLiteral_7= 'not like' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2165:6: (enumLiteral_7= 'not like' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2165:8: enumLiteral_7= 'not like'
                    {
                    enumLiteral_7=(Token)match(input,40,FOLLOW_40_in_ruleOperator5080); 

                            current = grammarAccess.getOperatorAccess().getNotLikeEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getOperatorAccess().getNotLikeEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2171:6: (enumLiteral_8= 'not in' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2171:6: (enumLiteral_8= 'not in' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2171:8: enumLiteral_8= 'not in'
                    {
                    enumLiteral_8=(Token)match(input,32,FOLLOW_32_in_ruleOperator5097); 

                            current = grammarAccess.getOperatorAccess().getNotInEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getOperatorAccess().getNotInEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2177:6: (enumLiteral_9= 'in' )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2177:6: (enumLiteral_9= 'in' )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSql.g:2177:8: enumLiteral_9= 'in'
                    {
                    enumLiteral_9=(Token)match(input,31,FOLLOW_31_in_ruleOperator5114); 

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


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA4_eotS =
        "\13\uffff";
    static final String DFA4_eofS =
        "\1\uffff\1\5\4\uffff\1\5\1\uffff\1\5\1\uffff\1\5";
    static final String DFA4_minS =
        "\2\4\1\uffff\1\4\2\uffff\5\4";
    static final String DFA4_maxS =
        "\1\4\1\24\1\uffff\1\4\2\uffff\1\24\1\4\1\24\1\4\1\23";
    static final String DFA4_acceptS =
        "\2\uffff\1\3\1\uffff\1\2\1\1\5\uffff";
    static final String DFA4_specialS =
        "\13\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1",
            "\1\2\13\uffff\1\5\1\uffff\1\5\1\4\1\3",
            "",
            "\1\6",
            "",
            "",
            "\1\2\13\uffff\1\5\1\uffff\1\5\1\4\1\7",
            "\1\10",
            "\1\2\13\uffff\1\5\1\uffff\1\5\1\4\1\11",
            "\1\12",
            "\1\2\13\uffff\1\5\1\uffff\1\5\1\4"
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "226:1: (this_ColumnFull_0= ruleColumnFull | (this_ColumnFull_1= ruleColumnFull otherlv_2= 'AS' ( (lv_colAlias_3_0= ruleColumnAlias ) ) ) | (this_ColumnFull_4= ruleColumnFull ( (lv_colAlias_5_0= ruleColumnAlias ) ) ) )";
        }
    }
    static final String DFA9_eotS =
        "\30\uffff";
    static final String DFA9_eofS =
        "\1\uffff\1\3\2\uffff\1\6\1\uffff\1\13\1\uffff\1\15\1\uffff\1\20"+
        "\1\uffff\1\15\1\22\2\uffff\1\25\1\20\1\uffff\2\20\2\uffff\1\20";
    static final String DFA9_minS =
        "\3\4\1\uffff\2\4\1\0\1\4\1\20\1\uffff\1\4\1\uffff\1\20\1\0\2\4\1"+
        "\0\1\20\1\uffff\1\4\1\20\1\uffff\2\4";
    static final String DFA9_maxS =
        "\1\4\1\24\1\4\1\uffff\1\24\1\4\1\0\1\4\1\22\1\uffff\1\24\1\uffff"+
        "\1\22\1\0\2\4\1\0\1\22\1\uffff\1\24\1\22\1\uffff\1\4\1\24";
    static final String DFA9_acceptS =
        "\3\uffff\1\2\5\uffff\1\1\1\uffff\1\1\6\uffff\1\1\2\uffff\1\1\2\uffff";
    static final String DFA9_specialS =
        "\30\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1",
            "\1\3\14\uffff\3\3\1\2",
            "\1\4",
            "",
            "\1\10\13\uffff\1\3\1\11\1\5\1\7\1\11",
            "\1\12",
            "\1\uffff",
            "\1\14",
            "\1\3\1\11\1\5",
            "",
            "\1\21\13\uffff\1\3\1\11\1\5\1\17\1\16",
            "",
            "\1\3\1\13\1\5",
            "\1\uffff",
            "\1\23",
            "\1\24",
            "\1\uffff",
            "\1\3\1\13\1\5",
            "",
            "\1\21\13\uffff\1\3\1\22\1\5\1\17\1\26",
            "\1\3\1\22\1\5",
            "",
            "\1\27",
            "\1\21\13\uffff\1\3\1\25\1\5\1\17\1\3"
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "613:1: ( (this_Schema_0= ruleSchema otherlv_1= '.' ( (lv_tbl_2_0= ruleTable ) ) ) | ( (lv_tbl_3_0= ruleTable ) ) )";
        }
    }
    static final String DFA10_eotS =
        "\34\uffff";
    static final String DFA10_eofS =
        "\1\uffff\1\3\2\uffff\1\6\1\uffff\1\10\1\12\2\uffff\1\17\1\uffff"+
        "\1\21\1\uffff\1\24\1\uffff\1\21\1\26\2\uffff\1\31\1\24\1\uffff\2"+
        "\24\2\uffff\1\24";
    static final String DFA10_minS =
        "\1\4\1\24\1\4\1\uffff\2\4\1\0\1\4\1\uffff\1\4\1\0\1\4\1\20\1\uffff"+
        "\1\4\1\uffff\1\20\1\0\2\4\1\0\1\20\1\uffff\1\4\1\20\1\uffff\2\4";
    static final String DFA10_maxS =
        "\1\4\1\24\1\4\1\uffff\1\24\1\4\1\0\1\24\1\uffff\1\4\1\0\1\4\1\22"+
        "\1\uffff\1\24\1\uffff\1\22\1\0\2\4\1\0\1\22\1\uffff\1\24\1\22\1"+
        "\uffff\1\4\1\24";
    static final String DFA10_acceptS =
        "\3\uffff\1\2\4\uffff\1\1\4\uffff\1\1\1\uffff\1\1\6\uffff\1\1\2\uffff"+
        "\1\1\2\uffff";
    static final String DFA10_specialS =
        "\34\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\1",
            "\1\2",
            "\1\4",
            "",
            "\1\3\14\uffff\3\3\1\5",
            "\1\7",
            "\1\uffff",
            "\1\14\13\uffff\1\3\1\15\1\11\1\13\1\15",
            "",
            "\1\16",
            "\1\uffff",
            "\1\20",
            "\1\3\1\10\1\11",
            "",
            "\1\25\13\uffff\1\3\1\10\1\11\1\23\1\22",
            "",
            "\1\3\1\17\1\11",
            "\1\uffff",
            "\1\27",
            "\1\30",
            "\1\uffff",
            "\1\3\1\17\1\11",
            "",
            "\1\25\13\uffff\1\3\1\26\1\11\1\23\1\32",
            "\1\3\1\26\1\11",
            "",
            "\1\33",
            "\1\25\13\uffff\1\3\1\31\1\11\1\23\1\3"
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "762:1: ( (this_Database_0= ruleDatabase otherlv_1= '.' ( (lv_schem_2_0= RULE_ID ) ) ) | ( (lv_schem_3_0= RULE_ID ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleModel122 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumns_in_ruleModel143 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleModel155 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTables_in_ruleModel176 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_ruleModel189 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_ruleModel210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns248 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns305 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleColumns327 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns348 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias388 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOrAlias445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOrAlias473 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleColumnOrAlias484 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_ruleColumnOrAlias505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOrAlias535 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_ruleColumnOrAlias555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull592 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleColumnFull648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleColumnFull677 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleColumnFull688 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleColumnFull709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_entryRuleColumnAlias746 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnAlias756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleColumnAlias797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_entryRuleColumn837 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumn847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleColumn888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables928 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleTables985 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleTables1007 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleTables1028 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1068 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias1078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1153 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleTableOrAlias1164 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTableAlias_in_ruleTableOrAlias1185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1215 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTableAlias_in_ruleTableOrAlias1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull1272 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchema_in_ruleTableFull1330 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleTableFull1341 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleTable_in_ruleTableFull1362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_ruleTableFull1390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_entryRuleTable1426 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTable1436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTable1477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableAlias_in_entryRuleTableAlias1517 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableAlias1527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTableAlias1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchema_in_entryRuleSchema1608 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSchema1618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatabase_in_ruleSchema1666 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleSchema1677 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSchema1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSchema1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatabase_in_entryRuleDatabase1764 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDatabase1774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDatabase1815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_entryRuleWhereEntry1855 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWhereEntry1865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_ruleWhereEntry1912 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleWhereEntry1934 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_ruleWhereEntry1955 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_entryRuleAndWhereEntry1995 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndWhereEntry2005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_ruleAndWhereEntry2052 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_ruleAndWhereEntry2074 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_ruleAndWhereEntry2095 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_entryRuleConcreteWhereEntry2135 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConcreteWhereEntry2145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParWhereEntry_in_ruleConcreteWhereEntry2192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_ruleConcreteWhereEntry2219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParWhereEntry_in_entryRuleParWhereEntry2254 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParWhereEntry2264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleParWhereEntry2301 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_ruleParWhereEntry2323 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleParWhereEntry2334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_entryRuleExpressionWhereEntry2370 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionWhereEntry2380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleExpressionWhereEntry_in_ruleExpressionWhereEntry2427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiExpressionWhereEntry_in_ruleExpressionWhereEntry2454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleExpressionWhereEntry_in_entryRuleSingleExpressionWhereEntry2489 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSingleExpressionWhereEntry2499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSingleExpressionWhereEntry2541 = new BitSet(new long[]{0x000001FF80000000L});
    public static final BitSet FOLLOW_ruleOperator_in_ruleSingleExpressionWhereEntry2567 = new BitSet(new long[]{0x000000001E0001E0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSingleExpressionWhereEntry2588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression2624 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleExpression_in_ruleExpression2681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongExpression_in_ruleExpression2708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringExpression_in_ruleExpression2735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_ruleExpression2762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateExpression_in_ruleExpression2789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanExpression_in_ruleExpression2816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplacableValue_in_ruleExpression2843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplacableValue_in_entryRuleReplacableValue2878 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReplacableValue2888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleReplacableValue2930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleExpression_in_entryRuleDoubleExpression2978 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleExpression2988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleExpression3029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongExpression_in_entryRuleLongExpression3069 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongExpression3079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_ruleLongExpression3120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringExpression_in_entryRuleStringExpression3160 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringExpression3170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringExpression3211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_entryRuleNullExpression3251 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullExpression3261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleNullExpression3303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateExpression_in_entryRuleDateExpression3351 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDateExpression3361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleDateExpression3402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanExpression_in_entryRuleBooleanExpression3442 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanExpression3452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleBooleanExpression3495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleBooleanExpression3532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiExpressionWhereEntry_in_entryRuleMultiExpressionWhereEntry3581 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiExpressionWhereEntry3591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleMultiExpressionWhereEntry3633 = new BitSet(new long[]{0x0000000180000000L});
    public static final BitSet FOLLOW_ruleArrayOperator_in_ruleMultiExpressionWhereEntry3659 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_ruleArrayExpression_in_ruleMultiExpressionWhereEntry3680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayExpression_in_entryRuleArrayExpression3716 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayExpression3726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleArrayExpression_in_ruleArrayExpression3773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongArrayExpression_in_ruleArrayExpression3800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringArrayExpression_in_ruleArrayExpression3827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullArrayExpression_in_ruleArrayExpression3854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateArrayExpression_in_ruleArrayExpression3881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanArrayExpression_in_ruleArrayExpression3908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleArrayExpression_in_entryRuleDoubleArrayExpression3943 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleArrayExpression3953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleDoubleArrayExpression3990 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleArrayExpression4007 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_18_in_ruleDoubleArrayExpression4025 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleDoubleArrayExpression4042 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_30_in_ruleDoubleArrayExpression4061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongArrayExpression_in_entryRuleLongArrayExpression4097 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongArrayExpression4107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleLongArrayExpression4144 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_ruleLongArrayExpression4161 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_18_in_ruleLongArrayExpression4179 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_ruleLongArrayExpression4196 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_30_in_ruleLongArrayExpression4215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringArrayExpression_in_entryRuleStringArrayExpression4251 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringArrayExpression4261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleStringArrayExpression4298 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringArrayExpression4315 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_18_in_ruleStringArrayExpression4333 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringArrayExpression4350 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_30_in_ruleStringArrayExpression4369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullArrayExpression_in_entryRuleNullArrayExpression4405 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullArrayExpression4415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleNullArrayExpression4452 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleNullArrayExpression4470 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_18_in_ruleNullArrayExpression4496 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleNullArrayExpression4514 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_30_in_ruleNullArrayExpression4541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateArrayExpression_in_entryRuleDateArrayExpression4577 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDateArrayExpression4587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleDateArrayExpression4624 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleDateArrayExpression4641 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_18_in_ruleDateArrayExpression4659 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleDateArrayExpression4676 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_30_in_ruleDateArrayExpression4695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanArrayExpression_in_entryRuleBooleanArrayExpression4731 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanArrayExpression4741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleBooleanArrayExpression4778 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RULE_BOOL_in_ruleBooleanArrayExpression4795 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_18_in_ruleBooleanArrayExpression4813 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RULE_BOOL_in_ruleBooleanArrayExpression4830 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_30_in_ruleBooleanArrayExpression4849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleArrayOperator4899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleArrayOperator4916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleOperator4961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleOperator4978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleOperator4995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleOperator5012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleOperator5029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleOperator5046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleOperator5063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleOperator5080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleOperator5097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleOperator5114 = new BitSet(new long[]{0x0000000000000002L});

}