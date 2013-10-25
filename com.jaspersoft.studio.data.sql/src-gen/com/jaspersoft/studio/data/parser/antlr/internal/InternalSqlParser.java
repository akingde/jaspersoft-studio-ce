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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_66", "KEYWORD_64", "KEYWORD_65", "KEYWORD_62", "KEYWORD_63", "KEYWORD_60", "KEYWORD_61", "KEYWORD_58", "KEYWORD_59", "KEYWORD_50", "KEYWORD_51", "KEYWORD_52", "KEYWORD_53", "KEYWORD_54", "KEYWORD_55", "KEYWORD_56", "KEYWORD_57", "KEYWORD_47", "KEYWORD_48", "KEYWORD_49", "KEYWORD_43", "KEYWORD_44", "KEYWORD_45", "KEYWORD_46", "KEYWORD_37", "KEYWORD_38", "KEYWORD_39", "KEYWORD_40", "KEYWORD_41", "KEYWORD_42", "KEYWORD_28", "KEYWORD_29", "KEYWORD_30", "KEYWORD_31", "KEYWORD_32", "KEYWORD_33", "KEYWORD_34", "KEYWORD_35", "KEYWORD_36", "KEYWORD_23", "KEYWORD_24", "KEYWORD_25", "KEYWORD_26", "KEYWORD_27", "KEYWORD_14", "KEYWORD_15", "KEYWORD_16", "KEYWORD_17", "KEYWORD_18", "KEYWORD_19", "KEYWORD_20", "KEYWORD_21", "KEYWORD_22", "KEYWORD_1", "KEYWORD_2", "KEYWORD_3", "KEYWORD_4", "KEYWORD_5", "KEYWORD_6", "KEYWORD_7", "KEYWORD_8", "KEYWORD_9", "KEYWORD_10", "KEYWORD_11", "KEYWORD_12", "KEYWORD_13", "RULE_JRPARAM", "RULE_JRNPARAM", "RULE_STAR", "RULE_INT", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_SIGNED_DOUBLE", "RULE_STRING_", "RULE_STRING", "RULE_DBNAME", "RULE_ID", "RULE_SL_COMMENT", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=81;
    public static final int RULE_JRNPARAM=71;
    public static final int RULE_DATE=74;
    public static final int RULE_ANY_OTHER=85;
    public static final int KEYWORD_56=19;
    public static final int KEYWORD_19=53;
    public static final int KEYWORD_55=18;
    public static final int KEYWORD_54=17;
    public static final int KEYWORD_17=51;
    public static final int KEYWORD_53=16;
    public static final int KEYWORD_18=52;
    public static final int KEYWORD_52=15;
    public static final int KEYWORD_15=49;
    public static final int KEYWORD_51=14;
    public static final int KEYWORD_16=50;
    public static final int KEYWORD_13=69;
    public static final int KEYWORD_50=13;
    public static final int RULE_STRING_=78;
    public static final int KEYWORD_14=48;
    public static final int KEYWORD_11=67;
    public static final int EOF=-1;
    public static final int KEYWORD_12=68;
    public static final int KEYWORD_10=66;
    public static final int KEYWORD_59=12;
    public static final int KEYWORD_58=11;
    public static final int KEYWORD_57=20;
    public static final int KEYWORD_6=62;
    public static final int KEYWORD_7=63;
    public static final int KEYWORD_8=64;
    public static final int KEYWORD_9=65;
    public static final int RULE_TIME=75;
    public static final int KEYWORD_65=6;
    public static final int KEYWORD_28=34;
    public static final int KEYWORD_64=5;
    public static final int KEYWORD_29=35;
    public static final int RULE_SIGNED_DOUBLE=77;
    public static final int RULE_TIMESTAMP=76;
    public static final int RULE_INT=73;
    public static final int KEYWORD_66=4;
    public static final int KEYWORD_24=44;
    public static final int KEYWORD_61=10;
    public static final int KEYWORD_60=9;
    public static final int KEYWORD_25=45;
    public static final int RULE_DBNAME=80;
    public static final int KEYWORD_63=8;
    public static final int KEYWORD_26=46;
    public static final int KEYWORD_62=7;
    public static final int KEYWORD_27=47;
    public static final int KEYWORD_20=54;
    public static final int KEYWORD_21=55;
    public static final int KEYWORD_22=56;
    public static final int KEYWORD_23=43;
    public static final int KEYWORD_30=36;
    public static final int KEYWORD_1=57;
    public static final int KEYWORD_34=40;
    public static final int KEYWORD_5=61;
    public static final int KEYWORD_33=39;
    public static final int KEYWORD_4=60;
    public static final int KEYWORD_32=38;
    public static final int KEYWORD_3=59;
    public static final int KEYWORD_31=37;
    public static final int KEYWORD_2=58;
    public static final int KEYWORD_38=29;
    public static final int KEYWORD_37=28;
    public static final int RULE_SL_COMMENT=82;
    public static final int KEYWORD_36=42;
    public static final int KEYWORD_35=41;
    public static final int RULE_ML_COMMENT=83;
    public static final int KEYWORD_39=30;
    public static final int RULE_STRING=79;
    public static final int RULE_STAR=72;
    public static final int RULE_JRPARAM=70;
    public static final int KEYWORD_41=32;
    public static final int KEYWORD_40=31;
    public static final int KEYWORD_43=24;
    public static final int KEYWORD_42=33;
    public static final int KEYWORD_45=26;
    public static final int KEYWORD_44=25;
    public static final int KEYWORD_47=21;
    public static final int RULE_WS=84;
    public static final int KEYWORD_46=27;
    public static final int KEYWORD_49=23;
    public static final int KEYWORD_48=22;

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:71:1: ruleModel returns [EObject current=null] : ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_55 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_query_0_0 = null;

        EObject lv_orderByEntry_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:74:28: ( ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_55 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_55 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_55 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:2: ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_55 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )?
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:93:2: (otherlv_1= KEYWORD_55 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==KEYWORD_55) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:94:2: otherlv_1= KEYWORD_55 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) )
                    {
                    otherlv_1=(Token)match(input,KEYWORD_55,FOLLOW_KEYWORD_55_in_ruleModel137); 

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


            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:124:1: entryRuleSelectQuery returns [EObject current=null] : iv_ruleSelectQuery= ruleSelectQuery EOF ;
    public final EObject entryRuleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectQuery = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:125:2: (iv_ruleSelectQuery= ruleSelectQuery EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:126:2: iv_ruleSelectQuery= ruleSelectQuery EOF
            {
             newCompositeNode(grammarAccess.getSelectQueryRule()); 
            pushFollow(FOLLOW_ruleSelectQuery_in_entryRuleSelectQuery194);
            iv_ruleSelectQuery=ruleSelectQuery();

            state._fsp--;

             current =iv_ruleSelectQuery; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectQuery204); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:133:1: ruleSelectQuery returns [EObject current=null] : (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) ;
    public final EObject ruleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject this_Select_0 = null;

        EObject lv_op_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:136:28: ( (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:137:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:137:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:138:5: this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )*
            {
             
                    newCompositeNode(grammarAccess.getSelectQueryAccess().getSelectParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSelect_in_ruleSelectQuery251);
            this_Select_0=ruleSelect();

            state._fsp--;


                    current = this_Select_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:146:1: ( (lv_op_1_0= ruleSelectSubSet ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==KEYWORD_58||LA2_0==KEYWORD_43||LA2_0==KEYWORD_39||LA2_0==KEYWORD_41) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:147:1: (lv_op_1_0= ruleSelectSubSet )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:147:1: (lv_op_1_0= ruleSelectSubSet )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:148:3: lv_op_1_0= ruleSelectSubSet
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSelectQueryAccess().getOpSelectSubSetParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSelectSubSet_in_ruleSelectQuery271);
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
            	    break loop2;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:172:1: entryRuleSelectSubSet returns [EObject current=null] : iv_ruleSelectSubSet= ruleSelectSubSet EOF ;
    public final EObject entryRuleSelectSubSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectSubSet = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:173:2: (iv_ruleSelectSubSet= ruleSelectSubSet EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:174:2: iv_ruleSelectSubSet= ruleSelectSubSet EOF
            {
             newCompositeNode(grammarAccess.getSelectSubSetRule()); 
            pushFollow(FOLLOW_ruleSelectSubSet_in_entryRuleSelectSubSet307);
            iv_ruleSelectSubSet=ruleSelectSubSet();

            state._fsp--;

             current =iv_ruleSelectSubSet; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectSubSet317); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:181:1: ruleSelectSubSet returns [EObject current=null] : ( ( ( (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 ) ) ) ( (lv_all_1_0= KEYWORD_23 ) )? ( (lv_query_2_0= ruleSelect ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:184:28: ( ( ( ( (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 ) ) ) ( (lv_all_1_0= KEYWORD_23 ) )? ( (lv_query_2_0= ruleSelect ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:185:1: ( ( ( (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 ) ) ) ( (lv_all_1_0= KEYWORD_23 ) )? ( (lv_query_2_0= ruleSelect ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:185:1: ( ( ( (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 ) ) ) ( (lv_all_1_0= KEYWORD_23 ) )? ( (lv_query_2_0= ruleSelect ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:185:2: ( ( (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 ) ) ) ( (lv_all_1_0= KEYWORD_23 ) )? ( (lv_query_2_0= ruleSelect ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:185:2: ( ( (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:186:1: ( (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:186:1: ( (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:187:1: (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:187:1: (lv_op_0_1= KEYWORD_41 | lv_op_0_2= KEYWORD_58 | lv_op_0_3= KEYWORD_39 | lv_op_0_4= KEYWORD_43 )
            int alt3=4;
            switch ( input.LA(1) ) {
            case KEYWORD_41:
                {
                alt3=1;
                }
                break;
            case KEYWORD_58:
                {
                alt3=2;
                }
                break;
            case KEYWORD_39:
                {
                alt3=3;
                }
                break;
            case KEYWORD_43:
                {
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:188:3: lv_op_0_1= KEYWORD_41
                    {
                    lv_op_0_1=(Token)match(input,KEYWORD_41,FOLLOW_KEYWORD_41_in_ruleSelectSubSet363); 

                            newLeafNode(lv_op_0_1, grammarAccess.getSelectSubSetAccess().getOpUNIONKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:201:8: lv_op_0_2= KEYWORD_58
                    {
                    lv_op_0_2=(Token)match(input,KEYWORD_58,FOLLOW_KEYWORD_58_in_ruleSelectSubSet391); 

                            newLeafNode(lv_op_0_2, grammarAccess.getSelectSubSetAccess().getOpINTERSECTKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:214:8: lv_op_0_3= KEYWORD_39
                    {
                    lv_op_0_3=(Token)match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_ruleSelectSubSet419); 

                            newLeafNode(lv_op_0_3, grammarAccess.getSelectSubSetAccess().getOpMINUSKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:227:8: lv_op_0_4= KEYWORD_43
                    {
                    lv_op_0_4=(Token)match(input,KEYWORD_43,FOLLOW_KEYWORD_43_in_ruleSelectSubSet447); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:243:2: ( (lv_all_1_0= KEYWORD_23 ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_23) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:244:1: (lv_all_1_0= KEYWORD_23 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:244:1: (lv_all_1_0= KEYWORD_23 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:245:3: lv_all_1_0= KEYWORD_23
                    {
                    lv_all_1_0=(Token)match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_ruleSelectSubSet480); 

                            newLeafNode(lv_all_1_0, grammarAccess.getSelectSubSetAccess().getAllALLKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "all", lv_all_1_0, "ALL");
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:259:3: ( (lv_query_2_0= ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:260:1: (lv_query_2_0= ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:260:1: (lv_query_2_0= ruleSelect )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:261:3: lv_query_2_0= ruleSelect
            {
             
            	        newCompositeNode(grammarAccess.getSelectSubSetAccess().getQuerySelectParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelect_in_ruleSelectSubSet513);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:285:1: entryRuleSelect returns [EObject current=null] : iv_ruleSelect= ruleSelect EOF ;
    public final EObject entryRuleSelect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelect = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:286:2: (iv_ruleSelect= ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:287:2: iv_ruleSelect= ruleSelect EOF
            {
             newCompositeNode(grammarAccess.getSelectRule()); 
            pushFollow(FOLLOW_ruleSelect_in_entryRuleSelect548);
            iv_ruleSelect=ruleSelect();

            state._fsp--;

             current =iv_ruleSelect; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelect558); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:294:1: ruleSelect returns [EObject current=null] : ( ( (lv_select_0_0= KEYWORD_46 ) ) (otherlv_1= KEYWORD_51 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_31 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_42 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_52 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_44 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:297:28: ( ( ( (lv_select_0_0= KEYWORD_46 ) ) (otherlv_1= KEYWORD_51 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_31 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_42 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_52 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_44 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:298:1: ( ( (lv_select_0_0= KEYWORD_46 ) ) (otherlv_1= KEYWORD_51 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_31 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_42 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_52 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_44 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:298:1: ( ( (lv_select_0_0= KEYWORD_46 ) ) (otherlv_1= KEYWORD_51 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_31 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_42 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_52 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_44 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:298:2: ( (lv_select_0_0= KEYWORD_46 ) ) (otherlv_1= KEYWORD_51 )? ( (lv_cols_2_0= ruleColumns ) ) otherlv_3= KEYWORD_31 ( (lv_tbl_4_0= ruleTables ) ) (otherlv_5= KEYWORD_42 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )? (otherlv_7= KEYWORD_52 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )? (otherlv_9= KEYWORD_44 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:298:2: ( (lv_select_0_0= KEYWORD_46 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:299:1: (lv_select_0_0= KEYWORD_46 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:299:1: (lv_select_0_0= KEYWORD_46 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:300:3: lv_select_0_0= KEYWORD_46
            {
            lv_select_0_0=(Token)match(input,KEYWORD_46,FOLLOW_KEYWORD_46_in_ruleSelect602); 

                    newLeafNode(lv_select_0_0, grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectRule());
            	        }
                   		setWithLastConsumed(current, "select", lv_select_0_0, "SELECT");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:314:2: (otherlv_1= KEYWORD_51 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==KEYWORD_51) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:315:2: otherlv_1= KEYWORD_51
                    {
                    otherlv_1=(Token)match(input,KEYWORD_51,FOLLOW_KEYWORD_51_in_ruleSelect627); 

                        	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:319:3: ( (lv_cols_2_0= ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:320:1: (lv_cols_2_0= ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:320:1: (lv_cols_2_0= ruleColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:321:3: lv_cols_2_0= ruleColumns
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleColumns_in_ruleSelect649);
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

            otherlv_3=(Token)match(input,KEYWORD_31,FOLLOW_KEYWORD_31_in_ruleSelect662); 

                	newLeafNode(otherlv_3, grammarAccess.getSelectAccess().getFROMKeyword_3());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:342:1: ( (lv_tbl_4_0= ruleTables ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:343:1: (lv_tbl_4_0= ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:343:1: (lv_tbl_4_0= ruleTables )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:344:3: lv_tbl_4_0= ruleTables
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleTables_in_ruleSelect682);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:360:2: (otherlv_5= KEYWORD_42 ( (lv_whereExpression_6_0= ruleFullExpression ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_42) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:361:2: otherlv_5= KEYWORD_42 ( (lv_whereExpression_6_0= ruleFullExpression ) )
                    {
                    otherlv_5=(Token)match(input,KEYWORD_42,FOLLOW_KEYWORD_42_in_ruleSelect696); 

                        	newLeafNode(otherlv_5, grammarAccess.getSelectAccess().getWHEREKeyword_5_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:365:1: ( (lv_whereExpression_6_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:366:1: (lv_whereExpression_6_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:366:1: (lv_whereExpression_6_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:367:3: lv_whereExpression_6_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect716);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:383:4: (otherlv_7= KEYWORD_52 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==KEYWORD_52) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:384:2: otherlv_7= KEYWORD_52 ( (lv_groupByEntry_8_0= ruleGroupByColumns ) )
                    {
                    otherlv_7=(Token)match(input,KEYWORD_52,FOLLOW_KEYWORD_52_in_ruleSelect732); 

                        	newLeafNode(otherlv_7, grammarAccess.getSelectAccess().getGROUPBYKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:388:1: ( (lv_groupByEntry_8_0= ruleGroupByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:389:1: (lv_groupByEntry_8_0= ruleGroupByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:389:1: (lv_groupByEntry_8_0= ruleGroupByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:390:3: lv_groupByEntry_8_0= ruleGroupByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleGroupByColumns_in_ruleSelect752);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:406:4: (otherlv_9= KEYWORD_44 ( (lv_havingEntry_10_0= ruleFullExpression ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==KEYWORD_44) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:407:2: otherlv_9= KEYWORD_44 ( (lv_havingEntry_10_0= ruleFullExpression ) )
                    {
                    otherlv_9=(Token)match(input,KEYWORD_44,FOLLOW_KEYWORD_44_in_ruleSelect768); 

                        	newLeafNode(otherlv_9, grammarAccess.getSelectAccess().getHAVINGKeyword_7_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:411:1: ( (lv_havingEntry_10_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:412:1: (lv_havingEntry_10_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:412:1: (lv_havingEntry_10_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:413:3: lv_havingEntry_10_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect788);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:437:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:438:2: (iv_ruleColumns= ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:439:2: iv_ruleColumns= ruleColumns EOF
            {
             newCompositeNode(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns825);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns835); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:446:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:449:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:450:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:450:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:451:5: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns882);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;


                    current = this_ColumnOrAlias_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:459:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==KEYWORD_4) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:459:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:459:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:460:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:465:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==KEYWORD_4) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:466:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleColumns905); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:470:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:471:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:471:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:472:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns925);
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
    // $ANTLR end "ruleColumns"


    // $ANTLR start "entryRuleColumnOrAlias"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:496:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:497:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:498:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias964);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias974); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:505:1: ruleColumnOrAlias returns [EObject current=null] : ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_18 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_1_0=null;
        Token lv_allCols_3_0=null;
        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:508:28: ( ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_18 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:509:1: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_18 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:509:1: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_18 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==KEYWORD_28||LA13_0==KEYWORD_1||(LA13_0>=RULE_JRPARAM && LA13_0<=RULE_JRNPARAM)||(LA13_0>=RULE_INT && LA13_0<=RULE_ID)) ) {
                alt13=1;
            }
            else if ( (LA13_0==RULE_STAR) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:509:2: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_18 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:509:2: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_18 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:509:3: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_18 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:509:3: ( (lv_ce_0_0= ruleOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:510:1: (lv_ce_0_0= ruleOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:510:1: (lv_ce_0_0= ruleOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:511:3: lv_ce_0_0= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getCeOperandParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleColumnOrAlias1021);
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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:527:2: ( (lv_alias_1_0= KEYWORD_18 ) )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==KEYWORD_18) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:528:1: (lv_alias_1_0= KEYWORD_18 )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:528:1: (lv_alias_1_0= KEYWORD_18 )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:529:3: lv_alias_1_0= KEYWORD_18
                            {
                            lv_alias_1_0=(Token)match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_ruleColumnOrAlias1040); 

                                    newLeafNode(lv_alias_1_0, grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getColumnOrAliasRule());
                            	        }
                                   		setWithLastConsumed(current, "alias", lv_alias_1_0, "AS");
                            	    

                            }


                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:543:3: ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( ((LA12_0>=RULE_STRING && LA12_0<=RULE_ID)) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:544:1: (lv_colAlias_2_0= ruleDbObjectName )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:544:1: (lv_colAlias_2_0= ruleDbObjectName )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:545:3: lv_colAlias_2_0= ruleDbObjectName
                            {
                             
                            	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
                            	    
                            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias1073);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:562:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:562:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:563:1: (lv_allCols_3_0= RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:563:1: (lv_allCols_3_0= RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:564:3: lv_allCols_3_0= RULE_STAR
                    {
                    lv_allCols_3_0=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleColumnOrAlias1098); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:588:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:589:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:590:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
             newCompositeNode(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull1138);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull1148); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:597:1: ruleColumnFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:600:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:601:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:601:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:602:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull1195);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:610:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==KEYWORD_6) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:610:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:610:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:611:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnFullAccess().getColEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:616:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==KEYWORD_6) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:617:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleColumnFull1218); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:621:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:622:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:622:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:623:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull1238);
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
                    	    if ( cnt14 >= 1 ) break loop14;
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:647:1: entryRuleTables returns [EObject current=null] : iv_ruleTables= ruleTables EOF ;
    public final EObject entryRuleTables() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTables = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:648:2: (iv_ruleTables= ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:649:2: iv_ruleTables= ruleTables EOF
            {
             newCompositeNode(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables1277);
            iv_ruleTables=ruleTables();

            state._fsp--;

             current =iv_ruleTables; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables1287); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:656:1: ruleTables returns [EObject current=null] : (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) ;
    public final EObject ruleTables() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_FromTable_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:659:28: ( (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:660:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:660:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:661:5: this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleFromTable_in_ruleTables1334);
            this_FromTable_0=ruleFromTable();

            state._fsp--;


                    current = this_FromTable_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:669:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==KEYWORD_4) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:669:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:669:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:670:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:675:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+
                    int cnt16=0;
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==KEYWORD_4) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:676:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleTables1357); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:680:1: ( (lv_entries_3_0= ruleFromTable ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:681:1: (lv_entries_3_0= ruleFromTable )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:681:1: (lv_entries_3_0= ruleFromTable )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:682:3: lv_entries_3_0= ruleFromTable
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleFromTable_in_ruleTables1377);
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


    // $ANTLR start "entryRuleFromTable"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:706:1: entryRuleFromTable returns [EObject current=null] : iv_ruleFromTable= ruleFromTable EOF ;
    public final EObject entryRuleFromTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:707:2: (iv_ruleFromTable= ruleFromTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:708:2: iv_ruleFromTable= ruleFromTable EOF
            {
             newCompositeNode(grammarAccess.getFromTableRule()); 
            pushFollow(FOLLOW_ruleFromTable_in_entryRuleFromTable1416);
            iv_ruleFromTable=ruleFromTable();

            state._fsp--;

             current =iv_ruleFromTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTable1426); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:715:1: ruleFromTable returns [EObject current=null] : ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) ;
    public final EObject ruleFromTable() throws RecognitionException {
        EObject current = null;

        EObject lv_table_0_0 = null;

        EObject lv_fjoin_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:718:28: ( ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:719:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:719:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:719:2: ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:719:2: ( (lv_table_0_0= ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:720:1: (lv_table_0_0= ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:720:1: (lv_table_0_0= ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:721:3: lv_table_0_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTable1472);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:737:2: ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=KEYWORD_66 && LA18_0<=KEYWORD_65)||(LA18_0>=KEYWORD_60 && LA18_0<=KEYWORD_61)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:738:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:738:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:739:3: lv_fjoin_1_0= ruleFromTableJoin
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFromTableAccess().getFjoinFromTableJoinParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleFromTableJoin_in_ruleFromTable1493);
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
            	    break loop18;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:763:1: entryRuleFromTableJoin returns [EObject current=null] : iv_ruleFromTableJoin= ruleFromTableJoin EOF ;
    public final EObject entryRuleFromTableJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTableJoin = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:764:2: (iv_ruleFromTableJoin= ruleFromTableJoin EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:765:2: iv_ruleFromTableJoin= ruleFromTableJoin EOF
            {
             newCompositeNode(grammarAccess.getFromTableJoinRule()); 
            pushFollow(FOLLOW_ruleFromTableJoin_in_entryRuleFromTableJoin1529);
            iv_ruleFromTableJoin=ruleFromTableJoin();

            state._fsp--;

             current =iv_ruleFromTableJoin; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTableJoin1539); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:772:1: ruleFromTableJoin returns [EObject current=null] : ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_20 ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) ;
    public final EObject ruleFromTableJoin() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Enumerator lv_join_0_0 = null;

        EObject lv_onTable_1_0 = null;

        EObject lv_joinExpr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:775:28: ( ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_20 ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:776:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_20 ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:776:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_20 ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:776:2: ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_20 ( (lv_joinExpr_3_0= ruleFullExpression ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:776:2: ( (lv_join_0_0= ruleJoinType ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:777:1: (lv_join_0_0= ruleJoinType )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:777:1: (lv_join_0_0= ruleJoinType )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:778:3: lv_join_0_0= ruleJoinType
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinJoinTypeEnumRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleJoinType_in_ruleFromTableJoin1585);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:794:2: ( (lv_onTable_1_0= ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:795:1: (lv_onTable_1_0= ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:795:1: (lv_onTable_1_0= ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:796:3: lv_onTable_1_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getOnTableTableOrAliasParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTableJoin1606);
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

            otherlv_2=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleFromTableJoin1619); 

                	newLeafNode(otherlv_2, grammarAccess.getFromTableJoinAccess().getONKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:817:1: ( (lv_joinExpr_3_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:818:1: (lv_joinExpr_3_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:818:1: (lv_joinExpr_3_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:819:3: lv_joinExpr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleFromTableJoin1639);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:843:1: entryRuleTableOrAlias returns [EObject current=null] : iv_ruleTableOrAlias= ruleTableOrAlias EOF ;
    public final EObject entryRuleTableOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:844:2: (iv_ruleTableOrAlias= ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:845:2: iv_ruleTableOrAlias= ruleTableOrAlias EOF
            {
             newCompositeNode(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1674);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;

             current =iv_ruleTableOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias1684); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:852:1: ruleTableOrAlias returns [EObject current=null] : ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( (lv_alias_2_0= KEYWORD_18 ) )? ( (lv_tblAlias_3_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleTableOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_2_0=null;
        EObject lv_tfull_0_0 = null;

        EObject lv_sq_1_0 = null;

        EObject lv_tblAlias_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:855:28: ( ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( (lv_alias_2_0= KEYWORD_18 ) )? ( (lv_tblAlias_3_0= ruleDbObjectName ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( (lv_alias_2_0= KEYWORD_18 ) )? ( (lv_tblAlias_3_0= ruleDbObjectName ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( (lv_alias_2_0= KEYWORD_18 ) )? ( (lv_tblAlias_3_0= ruleDbObjectName ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( (lv_alias_2_0= KEYWORD_18 ) )? ( (lv_tblAlias_3_0= ruleDbObjectName ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_STRING && LA19_0<=RULE_ID)) ) {
                alt19=1;
            }
            else if ( (LA19_0==KEYWORD_1) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:856:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:857:1: (lv_tfull_0_0= ruleTableFull )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:857:1: (lv_tfull_0_0= ruleTableFull )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:858:3: lv_tfull_0_0= ruleTableFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias1731);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:875:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:875:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:876:1: (lv_sq_1_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:876:1: (lv_sq_1_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:877:3: lv_sq_1_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getSqSubQueryOperandParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleTableOrAlias1758);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:893:3: ( (lv_alias_2_0= KEYWORD_18 ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==KEYWORD_18) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:894:1: (lv_alias_2_0= KEYWORD_18 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:894:1: (lv_alias_2_0= KEYWORD_18 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:895:3: lv_alias_2_0= KEYWORD_18
                    {
                    lv_alias_2_0=(Token)match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_ruleTableOrAlias1778); 

                            newLeafNode(lv_alias_2_0, grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTableOrAliasRule());
                    	        }
                           		setWithLastConsumed(current, "alias", lv_alias_2_0, "AS");
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:909:3: ( (lv_tblAlias_3_0= ruleDbObjectName ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=RULE_STRING && LA21_0<=RULE_ID)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:910:1: (lv_tblAlias_3_0= ruleDbObjectName )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:910:1: (lv_tblAlias_3_0= ruleDbObjectName )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:911:3: lv_tblAlias_3_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableOrAlias1811);
                    lv_tblAlias_3_0=ruleDbObjectName();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTableOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"tblAlias",
                            		lv_tblAlias_3_0, 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:935:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:936:2: (iv_ruleTableFull= ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:937:2: iv_ruleTableFull= ruleTableFull EOF
            {
             newCompositeNode(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull1847);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;

             current =iv_ruleTableFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull1857); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:944:1: ruleTableFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:947:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:948:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:948:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:949:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull1904);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:957:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==KEYWORD_6) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:957:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:957:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:958:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:963:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt22=0;
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==KEYWORD_6) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:964:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleTableFull1927); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:968:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:969:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:969:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:970:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull1947);
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
    // $ANTLR end "ruleTableFull"


    // $ANTLR start "entryRuleDbObjectName"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:994:1: entryRuleDbObjectName returns [EObject current=null] : iv_ruleDbObjectName= ruleDbObjectName EOF ;
    public final EObject entryRuleDbObjectName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectName = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:995:2: (iv_ruleDbObjectName= ruleDbObjectName EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:996:2: iv_ruleDbObjectName= ruleDbObjectName EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameRule()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName1986);
            iv_ruleDbObjectName=ruleDbObjectName();

            state._fsp--;

             current =iv_ruleDbObjectName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectName1996); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1003:1: ruleDbObjectName returns [EObject current=null] : ( (lv_dbname_0_0= ruleDBID ) ) ;
    public final EObject ruleDbObjectName() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1006:28: ( ( (lv_dbname_0_0= ruleDBID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1007:1: ( (lv_dbname_0_0= ruleDBID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1007:1: ( (lv_dbname_0_0= ruleDBID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1008:1: (lv_dbname_0_0= ruleDBID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1008:1: (lv_dbname_0_0= ruleDBID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1009:3: lv_dbname_0_0= ruleDBID
            {
             
            	        newCompositeNode(grammarAccess.getDbObjectNameAccess().getDbnameDBIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleDBID_in_ruleDbObjectName2041);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1033:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1034:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1035:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns2075);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;

             current =iv_ruleOrderByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns2085); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1042:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1045:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1046:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1046:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1047:5: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns2132);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;


                    current = this_OrderByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1055:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==KEYWORD_4) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1055:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1055:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1056:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1061:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt24=0;
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==KEYWORD_4) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1062:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByColumns2155); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1066:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1067:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1067:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1068:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns2175);
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
                    	    if ( cnt24 >= 1 ) break loop24;
                                EarlyExitException eee =
                                    new EarlyExitException(24, input);
                                throw eee;
                        }
                        cnt24++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1092:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1093:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1094:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull2214);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;

             current =iv_ruleOrderByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull2224); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1101:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 ) ) )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_direction_1_1=null;
        Token lv_direction_1_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1104:28: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1105:1: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1105:1: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1105:2: ( (lv_colOrder_0_0= ruleColumnFull ) ) ( ( (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1105:2: ( (lv_colOrder_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1106:1: (lv_colOrder_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1106:1: (lv_colOrder_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1107:3: lv_colOrder_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull2270);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1123:2: ( ( (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==KEYWORD_29||LA27_0==KEYWORD_26) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1124:1: ( (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1124:1: ( (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1125:1: (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1125:1: (lv_direction_1_1= KEYWORD_26 | lv_direction_1_2= KEYWORD_29 )
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==KEYWORD_26) ) {
                        alt26=1;
                    }
                    else if ( (LA26_0==KEYWORD_29) ) {
                        alt26=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 0, input);

                        throw nvae;
                    }
                    switch (alt26) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1126:3: lv_direction_1_1= KEYWORD_26
                            {
                            lv_direction_1_1=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleOrderByColumnFull2291); 

                                    newLeafNode(lv_direction_1_1, grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                            	        }
                                   		setWithLastConsumed(current, "direction", lv_direction_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1139:8: lv_direction_1_2= KEYWORD_29
                            {
                            lv_direction_1_2=(Token)match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_ruleOrderByColumnFull2319); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1163:1: entryRuleGroupByColumns returns [EObject current=null] : iv_ruleGroupByColumns= ruleGroupByColumns EOF ;
    public final EObject entryRuleGroupByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1164:2: (iv_ruleGroupByColumns= ruleGroupByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1165:2: iv_ruleGroupByColumns= ruleGroupByColumns EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnsRule()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns2369);
            iv_ruleGroupByColumns=ruleGroupByColumns();

            state._fsp--;

             current =iv_ruleGroupByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumns2379); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1172:1: ruleGroupByColumns returns [EObject current=null] : (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) ;
    public final EObject ruleGroupByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_GroupByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1175:28: ( (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1176:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1176:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1177:5: this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns2426);
            this_GroupByColumnFull_0=ruleGroupByColumnFull();

            state._fsp--;


                    current = this_GroupByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1185:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==KEYWORD_4) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1185:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1185:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1186:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1191:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==KEYWORD_4) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1192:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleGroupByColumns2449); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1196:1: ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1197:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1197:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1198:3: lv_entries_3_0= ruleGroupByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns2469);
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
    // $ANTLR end "ruleGroupByColumns"


    // $ANTLR start "entryRuleGroupByColumnFull"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1222:1: entryRuleGroupByColumnFull returns [EObject current=null] : iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF ;
    public final EObject entryRuleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1223:2: (iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1224:2: iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnFullRule()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull2508);
            iv_ruleGroupByColumnFull=ruleGroupByColumnFull();

            state._fsp--;

             current =iv_ruleGroupByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumnFull2518); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1231:1: ruleGroupByColumnFull returns [EObject current=null] : ( (lv_colGrBy_0_0= ruleColumnFull ) ) ;
    public final EObject ruleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject lv_colGrBy_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1234:28: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1235:1: ( (lv_colGrBy_0_0= ruleColumnFull ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1235:1: ( (lv_colGrBy_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1236:1: (lv_colGrBy_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1236:1: (lv_colGrBy_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1237:3: lv_colGrBy_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getColGrByColumnFullParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleGroupByColumnFull2563);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1261:1: entryRuleFullExpression returns [EObject current=null] : iv_ruleFullExpression= ruleFullExpression EOF ;
    public final EObject entryRuleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1262:2: (iv_ruleFullExpression= ruleFullExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1263:2: iv_ruleFullExpression= ruleFullExpression EOF
            {
             newCompositeNode(grammarAccess.getFullExpressionRule()); 
            pushFollow(FOLLOW_ruleFullExpression_in_entryRuleFullExpression2597);
            iv_ruleFullExpression=ruleFullExpression();

            state._fsp--;

             current =iv_ruleFullExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullExpression2607); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1270:1: ruleFullExpression returns [EObject current=null] : (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) ;
    public final EObject ruleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExpressionFragment_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1273:28: ( (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1274:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1274:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1275:5: this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleExpressionFragment_in_ruleFullExpression2654);
            this_ExpressionFragment_0=ruleExpressionFragment();

            state._fsp--;


                    current = this_ExpressionFragment_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1283:1: ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==KEYWORD_24||LA31_0==KEYWORD_21) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1283:2: () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1283:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1284:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1289:2: ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    int cnt30=0;
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==KEYWORD_24||LA30_0==KEYWORD_21) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1290:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1290:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1291:3: lv_entries_2_0= ruleExpressionFragmentSecond
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression2684);
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
                    	    if ( cnt30 >= 1 ) break loop30;
                                EarlyExitException eee =
                                    new EarlyExitException(30, input);
                                throw eee;
                        }
                        cnt30++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1315:1: entryRuleExpressionFragmentSecond returns [EObject current=null] : iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF ;
    public final EObject entryRuleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragmentSecond = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1316:2: (iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1317:2: iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentSecondRule()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond2722);
            iv_ruleExpressionFragmentSecond=ruleExpressionFragmentSecond();

            state._fsp--;

             current =iv_ruleExpressionFragmentSecond; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragmentSecond2732); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1324:1: ruleExpressionFragmentSecond returns [EObject current=null] : ( ( ( (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) ;
    public final EObject ruleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        Token lv_c_0_1=null;
        Token lv_c_0_2=null;
        EObject lv_efrag_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1327:28: ( ( ( ( (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1328:1: ( ( ( (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1328:1: ( ( ( (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1328:2: ( ( (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1328:2: ( ( (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1329:1: ( (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1329:1: ( (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1330:1: (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1330:1: (lv_c_0_1= KEYWORD_24 | lv_c_0_2= KEYWORD_21 )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==KEYWORD_24) ) {
                alt32=1;
            }
            else if ( (LA32_0==KEYWORD_21) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1331:3: lv_c_0_1= KEYWORD_24
                    {
                    lv_c_0_1=(Token)match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_ruleExpressionFragmentSecond2778); 

                            newLeafNode(lv_c_0_1, grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                    	        }
                           		setWithLastConsumed(current, "c", lv_c_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1344:8: lv_c_0_2= KEYWORD_21
                    {
                    lv_c_0_2=(Token)match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_ruleExpressionFragmentSecond2806); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1360:2: ( (lv_efrag_1_0= ruleExpressionFragment ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1361:1: (lv_efrag_1_0= ruleExpressionFragment )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1361:1: (lv_efrag_1_0= ruleExpressionFragment )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1362:3: lv_efrag_1_0= ruleExpressionFragment
            {
             
            	        newCompositeNode(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond2841);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1386:1: entryRuleExpressionFragment returns [EObject current=null] : iv_ruleExpressionFragment= ruleExpressionFragment EOF ;
    public final EObject entryRuleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1387:2: (iv_ruleExpressionFragment= ruleExpressionFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1388:2: iv_ruleExpressionFragment= ruleExpressionFragment EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment2876);
            iv_ruleExpressionFragment=ruleExpressionFragment();

            state._fsp--;

             current =iv_ruleExpressionFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragment2886); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1395:1: ruleExpressionFragment returns [EObject current=null] : ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) ) ;
    public final EObject ruleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_expgroup_0_0 = null;

        EObject lv_exp_1_0 = null;

        EObject lv_xexp_2_1 = null;

        EObject lv_xexp_2_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1398:28: ( ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1399:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1399:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) )
            int alt34=3;
            switch ( input.LA(1) ) {
            case KEYWORD_1:
                {
                int LA34_1 = input.LA(2);

                if ( (LA34_1==KEYWORD_46) ) {
                    alt34=2;
                }
                else if ( (LA34_1==KEYWORD_28||LA34_1==KEYWORD_14||LA34_1==KEYWORD_1||(LA34_1>=RULE_JRPARAM && LA34_1<=RULE_JRNPARAM)||(LA34_1>=RULE_INT && LA34_1<=RULE_ID)) ) {
                    alt34=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 1, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_28:
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
                alt34=2;
                }
                break;
            case KEYWORD_14:
                {
                alt34=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1399:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1399:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1400:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1400:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1401:3: lv_expgroup_0_0= ruleExpressionGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment2932);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1418:6: ( (lv_exp_1_0= ruleExpression ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1418:6: ( (lv_exp_1_0= ruleExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1419:1: (lv_exp_1_0= ruleExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1419:1: (lv_exp_1_0= ruleExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1420:3: lv_exp_1_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleExpressionFragment2959);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1437:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1437:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1438:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1438:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1439:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1439:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    int alt33=2;
                    alt33 = dfa33.predict(input);
                    switch (alt33) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1440:3: lv_xexp_2_1= ruleXExpression
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleXExpression_in_ruleExpressionFragment2988);
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
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1455:8: lv_xexp_2_2= ruleXExpression_
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpression_ParserRuleCall_2_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleXExpression__in_ruleExpressionFragment3007);
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

            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1481:1: entryRuleExpressionGroup returns [EObject current=null] : iv_ruleExpressionGroup= ruleExpressionGroup EOF ;
    public final EObject entryRuleExpressionGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionGroup = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1482:2: (iv_ruleExpressionGroup= ruleExpressionGroup EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1483:2: iv_ruleExpressionGroup= ruleExpressionGroup EOF
            {
             newCompositeNode(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup3045);
            iv_ruleExpressionGroup=ruleExpressionGroup();

            state._fsp--;

             current =iv_ruleExpressionGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionGroup3055); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1490:1: ruleExpressionGroup returns [EObject current=null] : ( () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleExpressionGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1493:28: ( ( () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1494:1: ( () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1494:1: ( () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1494:2: () otherlv_1= KEYWORD_1 ( (lv_expr_2_0= ruleFullExpression ) ) otherlv_3= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1494:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1495:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpressionGroupAccess().getExprGroupAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleExpressionGroup3102); 

                	newLeafNode(otherlv_1, grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1505:1: ( (lv_expr_2_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1506:1: (lv_expr_2_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1506:1: (lv_expr_2_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1507:3: lv_expr_2_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleExpressionGroup3122);
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

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleExpressionGroup3135); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1536:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1537:2: (iv_ruleXExpression= ruleXExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1538:2: iv_ruleXExpression= ruleXExpression EOF
            {
             newCompositeNode(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression3169);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;

             current =iv_ruleXExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression3179); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1545:1: ruleXExpression returns [EObject current=null] : (otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1548:28: ( (otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1549:1: (otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1549:1: (otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1550:2: otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13
            {
            otherlv_0=(Token)match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_ruleXExpression3217); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpressionAccess().getXKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1554:1: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1555:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpressionAccess().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression3238); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpressionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1565:1: ( (lv_xf_3_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1566:1: (lv_xf_3_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1566:1: (lv_xf_3_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1567:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression3258);
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

            otherlv_4=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression3271); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpressionAccess().getCommaKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1588:1: ( (lv_col_5_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1589:1: (lv_col_5_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1589:1: (lv_col_5_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1590:3: lv_col_5_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getColOperandParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleXExpression3291);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1606:2: (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==KEYWORD_4) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1607:2: otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression3305); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpressionAccess().getCommaKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1611:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1612:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1612:1: (lv_prm_7_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1613:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression3325);
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

            otherlv_8=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleXExpression3340); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1642:1: entryRuleXExpression_ returns [EObject current=null] : iv_ruleXExpression_= ruleXExpression_ EOF ;
    public final EObject entryRuleXExpression_() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression_ = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1643:2: (iv_ruleXExpression_= ruleXExpression_ EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1644:2: iv_ruleXExpression_= ruleXExpression_ EOF
            {
             newCompositeNode(grammarAccess.getXExpression_Rule()); 
            pushFollow(FOLLOW_ruleXExpression__in_entryRuleXExpression_3374);
            iv_ruleXExpression_=ruleXExpression_();

            state._fsp--;

             current =iv_ruleXExpression_; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression_3384); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1651:1: ruleXExpression_ returns [EObject current=null] : (otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1654:28: ( (otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1655:1: (otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1655:1: (otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1656:2: otherlv_0= KEYWORD_14 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13
            {
            otherlv_0=(Token)match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_ruleXExpression_3422); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpression_Access().getXKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1660:1: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1661:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpression_Access().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression_3443); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpression_Access().getLeftCurlyBracketKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1671:1: ( (lv_xf_3_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1672:1: (lv_xf_3_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1672:1: (lv_xf_3_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1673:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression_3463);
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

            otherlv_4=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleXExpression_3476); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpression_Access().getVerticalLineKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1694:1: ( (lv_col_5_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1695:1: (lv_col_5_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1695:1: (lv_col_5_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1696:3: lv_col_5_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getColOperandParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleXExpression_3496);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1712:2: (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==KEYWORD_12) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1713:2: otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleXExpression_3510); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpression_Access().getVerticalLineKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1717:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1718:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1718:1: (lv_prm_7_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1719:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpression_Access().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression_3530);
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

            otherlv_8=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleXExpression_3545); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1748:1: entryRuleXExpressionParams returns [EObject current=null] : iv_ruleXExpressionParams= ruleXExpressionParams EOF ;
    public final EObject entryRuleXExpressionParams() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionParams = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1749:2: (iv_ruleXExpressionParams= ruleXExpressionParams EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1750:2: iv_ruleXExpressionParams= ruleXExpressionParams EOF
            {
             newCompositeNode(grammarAccess.getXExpressionParamsRule()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams3579);
            iv_ruleXExpressionParams=ruleXExpressionParams();

            state._fsp--;

             current =iv_ruleXExpressionParams; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionParams3589); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1757:1: ruleXExpressionParams returns [EObject current=null] : (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) ;
    public final EObject ruleXExpressionParams() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_JRParameter_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1760:28: ( (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1761:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1761:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1762:5: this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams3636);
            this_JRParameter_0=ruleJRParameter();

            state._fsp--;


                    current = this_JRParameter_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1770:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==KEYWORD_4) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1770:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1770:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1771:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1776:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1777:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpressionParams3659); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1781:1: ( (lv_entries_3_0= ruleJRParameter ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1782:1: (lv_entries_3_0= ruleJRParameter )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1782:1: (lv_entries_3_0= ruleJRParameter )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1783:3: lv_entries_3_0= ruleJRParameter
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams3679);
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
    // $ANTLR end "ruleXExpressionParams"


    // $ANTLR start "entryRuleJRParameter"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1807:1: entryRuleJRParameter returns [EObject current=null] : iv_ruleJRParameter= ruleJRParameter EOF ;
    public final EObject entryRuleJRParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameter = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1808:2: (iv_ruleJRParameter= ruleJRParameter EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1809:2: iv_ruleJRParameter= ruleJRParameter EOF
            {
             newCompositeNode(grammarAccess.getJRParameterRule()); 
            pushFollow(FOLLOW_ruleJRParameter_in_entryRuleJRParameter3718);
            iv_ruleJRParameter=ruleJRParameter();

            state._fsp--;

             current =iv_ruleJRParameter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRParameter3728); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1816:1: ruleJRParameter returns [EObject current=null] : ( (lv_jrprm_0_0= RULE_ID ) ) ;
    public final EObject ruleJRParameter() throws RecognitionException {
        EObject current = null;

        Token lv_jrprm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1819:28: ( ( (lv_jrprm_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1820:1: ( (lv_jrprm_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1820:1: ( (lv_jrprm_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1821:1: (lv_jrprm_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1821:1: (lv_jrprm_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1822:3: lv_jrprm_0_0= RULE_ID
            {
            lv_jrprm_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJRParameter3769); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1846:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1847:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1848:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression3808);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression3818); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1855:1: ruleExpression returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1858:28: ( ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1859:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1859:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1859:2: ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1859:2: ( (lv_op1_0_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1860:1: (lv_op1_0_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1860:1: (lv_op1_0_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1861:3: lv_op1_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleExpression3864);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1877:2: ( ( ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_between_3_0= ruleBetween ) ) | ( (lv_like_4_0= ruleLike ) ) | ( (lv_comp_5_0= ruleComparison ) ) )
            int alt40=5;
            switch ( input.LA(1) ) {
            case KEYWORD_62:
            case KEYWORD_49:
                {
                alt40=1;
                }
                break;
            case KEYWORD_45:
            case KEYWORD_19:
                {
                alt40=2;
                }
                break;
            case KEYWORD_63:
            case KEYWORD_47:
                {
                alt40=3;
                }
                break;
            case KEYWORD_53:
            case KEYWORD_33:
                {
                alt40=4;
                }
                break;
            case KEYWORD_15:
            case KEYWORD_16:
            case KEYWORD_17:
            case KEYWORD_8:
            case KEYWORD_9:
            case KEYWORD_10:
                {
                alt40=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1877:3: ( ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1877:3: ( ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1878:1: ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1878:1: ( (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1879:1: (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1879:1: (lv_isnull_1_1= KEYWORD_49 | lv_isnull_1_2= KEYWORD_62 )
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==KEYWORD_49) ) {
                        alt39=1;
                    }
                    else if ( (LA39_0==KEYWORD_62) ) {
                        alt39=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 39, 0, input);

                        throw nvae;
                    }
                    switch (alt39) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1880:3: lv_isnull_1_1= KEYWORD_49
                            {
                            lv_isnull_1_1=(Token)match(input,KEYWORD_49,FOLLOW_KEYWORD_49_in_ruleExpression3886); 

                                    newLeafNode(lv_isnull_1_1, grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "isnull", lv_isnull_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1893:8: lv_isnull_1_2= KEYWORD_62
                            {
                            lv_isnull_1_2=(Token)match(input,KEYWORD_62,FOLLOW_KEYWORD_62_in_ruleExpression3914); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1910:6: ( (lv_in_2_0= ruleInOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1910:6: ( (lv_in_2_0= ruleInOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1911:1: (lv_in_2_0= ruleInOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1911:1: (lv_in_2_0= ruleInOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1912:3: lv_in_2_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleInOperator_in_ruleExpression3955);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1929:6: ( (lv_between_3_0= ruleBetween ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1929:6: ( (lv_between_3_0= ruleBetween ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1930:1: (lv_between_3_0= ruleBetween )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1930:1: (lv_between_3_0= ruleBetween )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1931:3: lv_between_3_0= ruleBetween
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBetween_in_ruleExpression3982);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1948:6: ( (lv_like_4_0= ruleLike ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1948:6: ( (lv_like_4_0= ruleLike ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1949:1: (lv_like_4_0= ruleLike )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1949:1: (lv_like_4_0= ruleLike )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1950:3: lv_like_4_0= ruleLike
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleLike_in_ruleExpression4009);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1967:6: ( (lv_comp_5_0= ruleComparison ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1967:6: ( (lv_comp_5_0= ruleComparison ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1968:1: (lv_comp_5_0= ruleComparison )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1968:1: (lv_comp_5_0= ruleComparison )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1969:3: lv_comp_5_0= ruleComparison
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleComparison_in_ruleExpression4036);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1993:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1994:2: (iv_ruleComparison= ruleComparison EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1995:2: iv_ruleComparison= ruleComparison EOF
            {
             newCompositeNode(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_ruleComparison_in_entryRuleComparison4072);
            iv_ruleComparison=ruleComparison();

            state._fsp--;

             current =iv_ruleComparison; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleComparison4082); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2002:1: ruleComparison returns [EObject current=null] : ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) ;
    public final EObject ruleComparison() throws RecognitionException {
        EObject current = null;

        Token lv_operator_0_1=null;
        Token lv_operator_0_2=null;
        Token lv_operator_0_3=null;
        Token lv_operator_0_4=null;
        Token lv_operator_0_5=null;
        Token lv_operator_0_6=null;
        Token lv_subOperator_1_1=null;
        Token lv_subOperator_1_2=null;
        Token lv_subOperator_1_3=null;
        EObject lv_op2_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2005:28: ( ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2006:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2006:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2006:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 ) ) )? ( (lv_op2_2_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2006:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2007:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2007:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2008:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2008:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_17 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_15 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_16 )
            int alt41=6;
            switch ( input.LA(1) ) {
            case KEYWORD_10:
                {
                alt41=1;
                }
                break;
            case KEYWORD_17:
                {
                alt41=2;
                }
                break;
            case KEYWORD_8:
                {
                alt41=3;
                }
                break;
            case KEYWORD_15:
                {
                alt41=4;
                }
                break;
            case KEYWORD_9:
                {
                alt41=5;
                }
                break;
            case KEYWORD_16:
                {
                alt41=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }

            switch (alt41) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2009:3: lv_operator_0_1= KEYWORD_10
                    {
                    lv_operator_0_1=(Token)match(input,KEYWORD_10,FOLLOW_KEYWORD_10_in_ruleComparison4128); 

                            newLeafNode(lv_operator_0_1, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2022:8: lv_operator_0_2= KEYWORD_17
                    {
                    lv_operator_0_2=(Token)match(input,KEYWORD_17,FOLLOW_KEYWORD_17_in_ruleComparison4156); 

                            newLeafNode(lv_operator_0_2, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2035:8: lv_operator_0_3= KEYWORD_8
                    {
                    lv_operator_0_3=(Token)match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_ruleComparison4184); 

                            newLeafNode(lv_operator_0_3, grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2048:8: lv_operator_0_4= KEYWORD_15
                    {
                    lv_operator_0_4=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleComparison4212); 

                            newLeafNode(lv_operator_0_4, grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_4, null);
                    	    

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2061:8: lv_operator_0_5= KEYWORD_9
                    {
                    lv_operator_0_5=(Token)match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_ruleComparison4240); 

                            newLeafNode(lv_operator_0_5, grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_5, null);
                    	    

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2074:8: lv_operator_0_6= KEYWORD_16
                    {
                    lv_operator_0_6=(Token)match(input,KEYWORD_16,FOLLOW_KEYWORD_16_in_ruleComparison4268); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2090:2: ( ( (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 ) ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==KEYWORD_34||LA43_0==KEYWORD_23||LA43_0==KEYWORD_25) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2091:1: ( (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2091:1: ( (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2092:1: (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2092:1: (lv_subOperator_1_1= KEYWORD_25 | lv_subOperator_1_2= KEYWORD_23 | lv_subOperator_1_3= KEYWORD_34 )
                    int alt42=3;
                    switch ( input.LA(1) ) {
                    case KEYWORD_25:
                        {
                        alt42=1;
                        }
                        break;
                    case KEYWORD_23:
                        {
                        alt42=2;
                        }
                        break;
                    case KEYWORD_34:
                        {
                        alt42=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 0, input);

                        throw nvae;
                    }

                    switch (alt42) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2093:3: lv_subOperator_1_1= KEYWORD_25
                            {
                            lv_subOperator_1_1=(Token)match(input,KEYWORD_25,FOLLOW_KEYWORD_25_in_ruleComparison4303); 

                                    newLeafNode(lv_subOperator_1_1, grammarAccess.getComparisonAccess().getSubOperatorANYKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2106:8: lv_subOperator_1_2= KEYWORD_23
                            {
                            lv_subOperator_1_2=(Token)match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_ruleComparison4331); 

                                    newLeafNode(lv_subOperator_1_2, grammarAccess.getComparisonAccess().getSubOperatorALLKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2119:8: lv_subOperator_1_3= KEYWORD_34
                            {
                            lv_subOperator_1_3=(Token)match(input,KEYWORD_34,FOLLOW_KEYWORD_34_in_ruleComparison4359); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2135:3: ( (lv_op2_2_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2136:1: (lv_op2_2_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2136:1: (lv_op2_2_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2137:3: lv_op2_2_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleComparison4395);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2161:1: entryRuleLike returns [EObject current=null] : iv_ruleLike= ruleLike EOF ;
    public final EObject entryRuleLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLike = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2162:2: (iv_ruleLike= ruleLike EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2163:2: iv_ruleLike= ruleLike EOF
            {
             newCompositeNode(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_ruleLike_in_entryRuleLike4430);
            iv_ruleLike=ruleLike();

            state._fsp--;

             current =iv_ruleLike; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLike4440); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2170:1: ruleLike returns [EObject current=null] : ( ( ( (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) ;
    public final EObject ruleLike() throws RecognitionException {
        EObject current = null;

        Token lv_opLike_0_1=null;
        Token lv_opLike_0_2=null;
        EObject lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2173:28: ( ( ( ( (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2174:1: ( ( ( (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2174:1: ( ( ( (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2174:2: ( ( (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2174:2: ( ( (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2175:1: ( (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2175:1: ( (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2176:1: (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2176:1: (lv_opLike_0_1= KEYWORD_33 | lv_opLike_0_2= KEYWORD_53 )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==KEYWORD_33) ) {
                alt44=1;
            }
            else if ( (LA44_0==KEYWORD_53) ) {
                alt44=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2177:3: lv_opLike_0_1= KEYWORD_33
                    {
                    lv_opLike_0_1=(Token)match(input,KEYWORD_33,FOLLOW_KEYWORD_33_in_ruleLike4486); 

                            newLeafNode(lv_opLike_0_1, grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLikeRule());
                    	        }
                           		setWithLastConsumed(current, "opLike", lv_opLike_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2190:8: lv_opLike_0_2= KEYWORD_53
                    {
                    lv_opLike_0_2=(Token)match(input,KEYWORD_53,FOLLOW_KEYWORD_53_in_ruleLike4514); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2206:2: ( (lv_op2_1_0= ruleLikeOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2207:1: (lv_op2_1_0= ruleLikeOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2207:1: (lv_op2_1_0= ruleLikeOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2208:3: lv_op2_1_0= ruleLikeOperand
            {
             
            	        newCompositeNode(grammarAccess.getLikeAccess().getOp2LikeOperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLikeOperand_in_ruleLike4549);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2232:1: entryRuleLikeOperand returns [EObject current=null] : iv_ruleLikeOperand= ruleLikeOperand EOF ;
    public final EObject entryRuleLikeOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLikeOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2233:2: (iv_ruleLikeOperand= ruleLikeOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2234:2: iv_ruleLikeOperand= ruleLikeOperand EOF
            {
             newCompositeNode(grammarAccess.getLikeOperandRule()); 
            pushFollow(FOLLOW_ruleLikeOperand_in_entryRuleLikeOperand4584);
            iv_ruleLikeOperand=ruleLikeOperand();

            state._fsp--;

             current =iv_ruleLikeOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLikeOperand4594); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2241:1: ruleLikeOperand returns [EObject current=null] : ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) ) ;
    public final EObject ruleLikeOperand() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op2_0_0 = null;

        EObject lv_fop2_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2244:28: ( ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2245:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2245:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==RULE_STRING_) ) {
                alt45=1;
            }
            else if ( (LA45_0==RULE_ID) ) {
                alt45=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2245:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2245:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2246:1: (lv_op2_0_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2246:1: (lv_op2_0_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2247:3: lv_op2_0_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getOp2StringOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleLikeOperand4640);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2264:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2264:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2265:1: (lv_fop2_1_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2265:1: (lv_fop2_1_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2266:3: lv_fop2_1_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFop2OperandFunctionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleLikeOperand4667);
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

            }


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2290:1: entryRuleBetween returns [EObject current=null] : iv_ruleBetween= ruleBetween EOF ;
    public final EObject entryRuleBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBetween = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2291:2: (iv_ruleBetween= ruleBetween EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2292:2: iv_ruleBetween= ruleBetween EOF
            {
             newCompositeNode(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_ruleBetween_in_entryRuleBetween4702);
            iv_ruleBetween=ruleBetween();

            state._fsp--;

             current =iv_ruleBetween; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBetween4712); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2299:1: ruleBetween returns [EObject current=null] : ( ( ( (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_24 ( (lv_op3_3_0= ruleOperand ) ) ) ;
    public final EObject ruleBetween() throws RecognitionException {
        EObject current = null;

        Token lv_opBetween_0_1=null;
        Token lv_opBetween_0_2=null;
        Token otherlv_2=null;
        EObject lv_op2_1_0 = null;

        EObject lv_op3_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2302:28: ( ( ( ( (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_24 ( (lv_op3_3_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2303:1: ( ( ( (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_24 ( (lv_op3_3_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2303:1: ( ( ( (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_24 ( (lv_op3_3_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2303:2: ( ( (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_24 ( (lv_op3_3_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2303:2: ( ( (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2304:1: ( (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2304:1: ( (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2305:1: (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2305:1: (lv_opBetween_0_1= KEYWORD_47 | lv_opBetween_0_2= KEYWORD_63 )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==KEYWORD_47) ) {
                alt46=1;
            }
            else if ( (LA46_0==KEYWORD_63) ) {
                alt46=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2306:3: lv_opBetween_0_1= KEYWORD_47
                    {
                    lv_opBetween_0_1=(Token)match(input,KEYWORD_47,FOLLOW_KEYWORD_47_in_ruleBetween4758); 

                            newLeafNode(lv_opBetween_0_1, grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBetweenRule());
                    	        }
                           		setWithLastConsumed(current, "opBetween", lv_opBetween_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2319:8: lv_opBetween_0_2= KEYWORD_63
                    {
                    lv_opBetween_0_2=(Token)match(input,KEYWORD_63,FOLLOW_KEYWORD_63_in_ruleBetween4786); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2335:2: ( (lv_op2_1_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2336:1: (lv_op2_1_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2336:1: (lv_op2_1_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2337:3: lv_op2_1_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleBetween4821);
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

            otherlv_2=(Token)match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_ruleBetween4834); 

                	newLeafNode(otherlv_2, grammarAccess.getBetweenAccess().getANDKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2358:1: ( (lv_op3_3_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2359:1: (lv_op3_3_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2359:1: (lv_op3_3_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2360:3: lv_op3_3_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp3OperandParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleBetween4854);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2384:1: entryRuleInOperator returns [EObject current=null] : iv_ruleInOperator= ruleInOperator EOF ;
    public final EObject entryRuleInOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOperator = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2385:2: (iv_ruleInOperator= ruleInOperator EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2386:2: iv_ruleInOperator= ruleInOperator EOF
            {
             newCompositeNode(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_ruleInOperator_in_entryRuleInOperator4889);
            iv_ruleInOperator=ruleInOperator();

            state._fsp--;

             current =iv_ruleInOperator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInOperator4899); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2393:1: ruleInOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2396:28: ( ( () ( ( (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2397:1: ( () ( ( (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2397:1: ( () ( ( (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2397:2: () ( ( (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 ) ) ) otherlv_2= KEYWORD_1 ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) ) otherlv_5= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2397:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2398:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInOperatorAccess().getInOperAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2403:2: ( ( (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2404:1: ( (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2404:1: ( (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2405:1: (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2405:1: (lv_op_1_1= KEYWORD_45 | lv_op_1_2= KEYWORD_19 )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==KEYWORD_45) ) {
                alt47=1;
            }
            else if ( (LA47_0==KEYWORD_19) ) {
                alt47=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2406:3: lv_op_1_1= KEYWORD_45
                    {
                    lv_op_1_1=(Token)match(input,KEYWORD_45,FOLLOW_KEYWORD_45_in_ruleInOperator4954); 

                            newLeafNode(lv_op_1_1, grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getInOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2419:8: lv_op_1_2= KEYWORD_19
                    {
                    lv_op_1_2=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleInOperator4982); 

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

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleInOperator5009); 

                	newLeafNode(otherlv_2, grammarAccess.getInOperatorAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2440:1: ( ( (lv_subquery_3_0= ruleSubQueryOperand ) ) | ( (lv_opList_4_0= ruleOperandList ) ) )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==KEYWORD_1) ) {
                alt48=1;
            }
            else if ( ((LA48_0>=RULE_INT && LA48_0<=RULE_STRING_)) ) {
                alt48=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2440:2: ( (lv_subquery_3_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2440:2: ( (lv_subquery_3_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2441:1: (lv_subquery_3_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2441:1: (lv_subquery_3_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2442:3: lv_subquery_3_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleInOperator5030);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2459:6: ( (lv_opList_4_0= ruleOperandList ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2459:6: ( (lv_opList_4_0= ruleOperandList ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2460:1: (lv_opList_4_0= ruleOperandList )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2460:1: (lv_opList_4_0= ruleOperandList )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2461:3: lv_opList_4_0= ruleOperandList
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getOpListOperandListParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandList_in_ruleInOperator5057);
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

            otherlv_5=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleInOperator5071); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2490:1: entryRuleOperandList returns [EObject current=null] : iv_ruleOperandList= ruleOperandList EOF ;
    public final EObject entryRuleOperandList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandList = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2491:2: (iv_ruleOperandList= ruleOperandList EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2492:2: iv_ruleOperandList= ruleOperandList EOF
            {
             newCompositeNode(grammarAccess.getOperandListRule()); 
            pushFollow(FOLLOW_ruleOperandList_in_entryRuleOperandList5105);
            iv_ruleOperandList=ruleOperandList();

            state._fsp--;

             current =iv_ruleOperandList; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandList5115); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2499:1: ruleOperandList returns [EObject current=null] : (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) ;
    public final EObject ruleOperandList() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ScalarOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2502:28: ( (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2503:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2503:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2504:5: this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOperandListAccess().getScalarOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleScalarOperand_in_ruleOperandList5162);
            this_ScalarOperand_0=ruleScalarOperand();

            state._fsp--;


                    current = this_ScalarOperand_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2512:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==KEYWORD_4) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2512:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2512:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2513:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2518:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    int cnt49=0;
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==KEYWORD_4) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2519:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOperandList5185); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2523:1: ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2524:1: (lv_entries_3_0= ruleScalarOperand )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2524:1: (lv_entries_3_0= ruleScalarOperand )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2525:3: lv_entries_3_0= ruleScalarOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOperandListAccess().getEntriesScalarOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleScalarOperand_in_ruleOperandList5205);
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
                    	    if ( cnt49 >= 1 ) break loop49;
                                EarlyExitException eee =
                                    new EarlyExitException(49, input);
                                throw eee;
                        }
                        cnt49++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2549:1: entryRuleOperand returns [EObject current=null] : iv_ruleOperand= ruleOperand EOF ;
    public final EObject entryRuleOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2550:2: (iv_ruleOperand= ruleOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2551:2: iv_ruleOperand= ruleOperand EOF
            {
             newCompositeNode(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_ruleOperand_in_entryRuleOperand5244);
            iv_ruleOperand=ruleOperand();

            state._fsp--;

             current =iv_ruleOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperand5254); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2558:1: ruleOperand returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_22 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2561:28: ( ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_22 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2562:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_22 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2562:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_22 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2562:2: ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_22 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2562:2: ( (lv_op1_0_0= ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2563:1: (lv_op1_0_0= ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2563:1: (lv_op1_0_0= ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2564:3: lv_op1_0_0= ruleOperandFragment
            {
             
            	        newCompositeNode(grammarAccess.getOperandAccess().getOp1OperandFragmentParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand5300);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2580:2: ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_22 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==KEYWORD_22||LA52_0==KEYWORD_3||LA52_0==KEYWORD_5||LA52_0==KEYWORD_7||LA52_0==RULE_STAR) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2580:3: ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_22 ) ) ( (lv_right_11_0= ruleOperandFragment ) )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2580:3: ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_22 ) )
            	    int alt51=5;
            	    switch ( input.LA(1) ) {
            	    case KEYWORD_3:
            	        {
            	        alt51=1;
            	        }
            	        break;
            	    case KEYWORD_5:
            	        {
            	        alt51=2;
            	        }
            	        break;
            	    case RULE_STAR:
            	        {
            	        alt51=3;
            	        }
            	        break;
            	    case KEYWORD_7:
            	        {
            	        alt51=4;
            	        }
            	        break;
            	    case KEYWORD_22:
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
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2580:4: ( () otherlv_2= KEYWORD_3 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2580:4: ( () otherlv_2= KEYWORD_3 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2580:5: () otherlv_2= KEYWORD_3
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2580:5: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2581:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getPlusLeftAction_1_0_0_0(),
            	                        current);
            	                

            	            }

            	            otherlv_2=(Token)match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_ruleOperand5325); 

            	                	newLeafNode(otherlv_2, grammarAccess.getOperandAccess().getPlusSignKeyword_1_0_0_1());
            	                

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2592:6: ( () otherlv_4= KEYWORD_5 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2592:6: ( () otherlv_4= KEYWORD_5 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2592:7: () otherlv_4= KEYWORD_5
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2592:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2593:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMinusLeftAction_1_0_1_0(),
            	                        current);
            	                

            	            }

            	            otherlv_4=(Token)match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_ruleOperand5354); 

            	                	newLeafNode(otherlv_4, grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_0_1_1());
            	                

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2604:6: ( () this_STAR_6= RULE_STAR )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2604:6: ( () this_STAR_6= RULE_STAR )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2604:7: () this_STAR_6= RULE_STAR
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2604:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2605:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getStarLeftAction_1_0_2_0(),
            	                        current);
            	                

            	            }

            	            this_STAR_6=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperand5381); 
            	             
            	                newLeafNode(this_STAR_6, grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_0_2_1()); 
            	                

            	            }


            	            }
            	            break;
            	        case 4 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2615:6: ( () otherlv_8= KEYWORD_7 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2615:6: ( () otherlv_8= KEYWORD_7 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2615:7: () otherlv_8= KEYWORD_7
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2615:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2616:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getDivLeftAction_1_0_3_0(),
            	                        current);
            	                

            	            }

            	            otherlv_8=(Token)match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_ruleOperand5410); 

            	                	newLeafNode(otherlv_8, grammarAccess.getOperandAccess().getSolidusKeyword_1_0_3_1());
            	                

            	            }


            	            }
            	            break;
            	        case 5 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2627:6: ( () otherlv_10= KEYWORD_22 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2627:6: ( () otherlv_10= KEYWORD_22 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2627:7: () otherlv_10= KEYWORD_22
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2627:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2628:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getConcatLeftAction_1_0_4_0(),
            	                        current);
            	                

            	            }

            	            otherlv_10=(Token)match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_ruleOperand5439); 

            	                	newLeafNode(otherlv_10, grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_0_4_1());
            	                

            	            }


            	            }
            	            break;

            	    }

            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2638:3: ( (lv_right_11_0= ruleOperandFragment ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2639:1: (lv_right_11_0= ruleOperandFragment )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2639:1: (lv_right_11_0= ruleOperandFragment )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2640:3: lv_right_11_0= ruleOperandFragment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOperandAccess().getRightOperandFragmentParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand5461);
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
            	    break loop52;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2664:1: entryRuleOperandFragment returns [EObject current=null] : iv_ruleOperandFragment= ruleOperandFragment EOF ;
    public final EObject entryRuleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2665:2: (iv_ruleOperandFragment= ruleOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2666:2: iv_ruleOperandFragment= ruleOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment5498);
            iv_ruleOperandFragment=ruleOperandFragment();

            state._fsp--;

             current =iv_ruleOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFragment5508); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2673:1: ruleOperandFragment returns [EObject current=null] : ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_func_3_0= ruleOperandFunction ) ) | ( (lv_sqlcase_4_0= ruleSQLCASE ) ) ) ;
    public final EObject ruleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_column_0_0 = null;

        EObject lv_xop_1_0 = null;

        EObject lv_subq_2_0 = null;

        EObject lv_func_3_0 = null;

        EObject lv_sqlcase_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2676:28: ( ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_func_3_0= ruleOperandFunction ) ) | ( (lv_sqlcase_4_0= ruleSQLCASE ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2677:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_func_3_0= ruleOperandFunction ) ) | ( (lv_sqlcase_4_0= ruleSQLCASE ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2677:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_func_3_0= ruleOperandFunction ) ) | ( (lv_sqlcase_4_0= ruleSQLCASE ) ) )
            int alt53=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==KEYWORD_1) ) {
                    alt53=4;
                }
                else if ( (LA53_1==EOF||(LA53_1>=KEYWORD_66 && LA53_1<=KEYWORD_58)||(LA53_1>=KEYWORD_52 && LA53_1<=KEYWORD_53)||LA53_1==KEYWORD_55||LA53_1==KEYWORD_47||(LA53_1>=KEYWORD_49 && LA53_1<=KEYWORD_45)||LA53_1==KEYWORD_39||(LA53_1>=KEYWORD_41 && LA53_1<=KEYWORD_42)||(LA53_1>=KEYWORD_30 && LA53_1<=KEYWORD_31)||LA53_1==KEYWORD_33||(LA53_1>=KEYWORD_35 && LA53_1<=KEYWORD_36)||LA53_1==KEYWORD_24||LA53_1==KEYWORD_27||(LA53_1>=KEYWORD_15 && LA53_1<=KEYWORD_19)||(LA53_1>=KEYWORD_21 && LA53_1<=KEYWORD_22)||(LA53_1>=KEYWORD_2 && LA53_1<=KEYWORD_10)||(LA53_1>=KEYWORD_12 && LA53_1<=KEYWORD_13)||LA53_1==RULE_STAR||(LA53_1>=RULE_STRING && LA53_1<=RULE_ID)) ) {
                    alt53=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt53=1;
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
                alt53=2;
                }
                break;
            case KEYWORD_1:
                {
                alt53=3;
                }
                break;
            case KEYWORD_28:
                {
                alt53=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }

            switch (alt53) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2677:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2677:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2678:1: (lv_column_0_0= ruleColumnOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2678:1: (lv_column_0_0= ruleColumnOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2679:3: lv_column_0_0= ruleColumnOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnOperand_in_ruleOperandFragment5554);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2696:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2696:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2697:1: (lv_xop_1_0= ruleXOperandFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2697:1: (lv_xop_1_0= ruleXOperandFragment )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2698:3: lv_xop_1_0= ruleXOperandFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXOperandFragment_in_ruleOperandFragment5581);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2715:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2715:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2716:1: (lv_subq_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2716:1: (lv_subq_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2717:3: lv_subq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment5608);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2734:6: ( (lv_func_3_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2734:6: ( (lv_func_3_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2735:1: (lv_func_3_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2735:1: (lv_func_3_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2736:3: lv_func_3_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFuncOperandFunctionParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleOperandFragment5635);
                    lv_func_3_0=ruleOperandFunction();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"func",
                            		lv_func_3_0, 
                            		"OperandFunction");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2753:6: ( (lv_sqlcase_4_0= ruleSQLCASE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2753:6: ( (lv_sqlcase_4_0= ruleSQLCASE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2754:1: (lv_sqlcase_4_0= ruleSQLCASE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2754:1: (lv_sqlcase_4_0= ruleSQLCASE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2755:3: lv_sqlcase_4_0= ruleSQLCASE
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSqlcaseSQLCASEParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSQLCASE_in_ruleOperandFragment5662);
                    lv_sqlcase_4_0=ruleSQLCASE();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"sqlcase",
                            		lv_sqlcase_4_0, 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2779:1: entryRuleOperandFunction returns [EObject current=null] : iv_ruleOperandFunction= ruleOperandFunction EOF ;
    public final EObject entryRuleOperandFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFunction = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2780:2: (iv_ruleOperandFunction= ruleOperandFunction EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2781:2: iv_ruleOperandFunction= ruleOperandFunction EOF
            {
             newCompositeNode(grammarAccess.getOperandFunctionRule()); 
            pushFollow(FOLLOW_ruleOperandFunction_in_entryRuleOperandFunction5697);
            iv_ruleOperandFunction=ruleOperandFunction();

            state._fsp--;

             current =iv_ruleOperandFunction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFunction5707); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2788:1: ruleOperandFunction returns [EObject current=null] : ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) ) otherlv_4= KEYWORD_2 ) ;
    public final EObject ruleOperandFunction() throws RecognitionException {
        EObject current = null;

        Token this_STAR_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_fname_1_0 = null;

        EObject lv_args_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2791:28: ( ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) ) otherlv_4= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2792:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) ) otherlv_4= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2792:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) ) otherlv_4= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2792:2: () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) ) otherlv_4= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2792:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2793:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperandFunctionAccess().getOpFunctionAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2798:2: ( (lv_fname_1_0= ruleFNAME ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2799:1: (lv_fname_1_0= ruleFNAME )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2799:1: (lv_fname_1_0= ruleFNAME )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2800:3: lv_fname_1_0= ruleFNAME
            {
             
            	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFnameFNAMEParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFNAME_in_ruleOperandFunction5762);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2816:2: (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==RULE_STAR) ) {
                alt54=1;
            }
            else if ( (LA54_0==KEYWORD_28||LA54_0==KEYWORD_1||(LA54_0>=RULE_JRPARAM && LA54_0<=RULE_JRNPARAM)||(LA54_0>=RULE_INT && LA54_0<=RULE_ID)) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2816:3: this_STAR_2= RULE_STAR
                    {
                    this_STAR_2=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperandFunction5774); 
                     
                        newLeafNode(this_STAR_2, grammarAccess.getOperandFunctionAccess().getSTARTerminalRuleCall_2_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2821:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2821:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2822:1: (lv_args_3_0= ruleOpFunctionArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2822:1: (lv_args_3_0= ruleOpFunctionArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2823:3: lv_args_3_0= ruleOpFunctionArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getArgsOpFunctionArgParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionArg_in_ruleOperandFunction5800);
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

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOperandFunction5814); 

                	newLeafNode(otherlv_4, grammarAccess.getOperandFunctionAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleOpFunctionArg"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2852:1: entryRuleOpFunctionArg returns [EObject current=null] : iv_ruleOpFunctionArg= ruleOpFunctionArg EOF ;
    public final EObject entryRuleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2853:2: (iv_ruleOpFunctionArg= ruleOpFunctionArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2854:2: iv_ruleOpFunctionArg= ruleOpFunctionArg EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArg_in_entryRuleOpFunctionArg5848);
            iv_ruleOpFunctionArg=ruleOpFunctionArg();

            state._fsp--;

             current =iv_ruleOpFunctionArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArg5858); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2861:1: ruleOpFunctionArg returns [EObject current=null] : (this_Operand_0= ruleOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) ) )+ )? ) ;
    public final EObject ruleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Operand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2864:28: ( (this_Operand_0= ruleOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2865:1: (this_Operand_0= ruleOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2865:1: (this_Operand_0= ruleOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2866:5: this_Operand_0= ruleOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOpFunctionArgAccess().getOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionArg5905);
            this_Operand_0=ruleOperand();

            state._fsp--;


                    current = this_Operand_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2874:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) ) )+ )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==KEYWORD_4) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2874:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2874:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2875:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOpFunctionArgAccess().getOpFListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2880:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) ) )+
                    int cnt55=0;
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==KEYWORD_4) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2881:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOpFunctionArg5928); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOpFunctionArgAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2885:1: ( (lv_entries_3_0= ruleOperand ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2886:1: (lv_entries_3_0= ruleOperand )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2886:1: (lv_entries_3_0= ruleOperand )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2887:3: lv_entries_3_0= ruleOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOpFunctionArgAccess().getEntriesOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionArg5948);
                    	    lv_entries_3_0=ruleOperand();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getOpFunctionArgRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entries",
                    	            		lv_entries_3_0, 
                    	            		"Operand");
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
    // $ANTLR end "ruleOpFunctionArg"


    // $ANTLR start "entryRuleXOperandFragment"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2911:1: entryRuleXOperandFragment returns [EObject current=null] : iv_ruleXOperandFragment= ruleXOperandFragment EOF ;
    public final EObject entryRuleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2912:2: (iv_ruleXOperandFragment= ruleXOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2913:2: iv_ruleXOperandFragment= ruleXOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment5987);
            iv_ruleXOperandFragment=ruleXOperandFragment();

            state._fsp--;

             current =iv_ruleXOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOperandFragment5997); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2920:1: ruleXOperandFragment returns [EObject current=null] : ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) ) ;
    public final EObject ruleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_param_0_0 = null;

        EObject lv_eparam_1_0 = null;

        EObject lv_scalar_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2923:28: ( ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2924:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2924:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) )
            int alt57=3;
            switch ( input.LA(1) ) {
            case RULE_JRPARAM:
                {
                alt57=1;
                }
                break;
            case RULE_JRNPARAM:
                {
                alt57=2;
                }
                break;
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt57=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2924:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2924:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2925:1: (lv_param_0_0= ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2925:1: (lv_param_0_0= ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2926:3: lv_param_0_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParameterOperand_in_ruleXOperandFragment6043);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2943:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2943:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2944:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2944:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2945:3: lv_eparam_1_0= ruleExclamationParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment6070);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2962:6: ( (lv_scalar_2_0= ruleScalarOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2962:6: ( (lv_scalar_2_0= ruleScalarOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2963:1: (lv_scalar_2_0= ruleScalarOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2963:1: (lv_scalar_2_0= ruleScalarOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2964:3: lv_scalar_2_0= ruleScalarOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getScalarScalarOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleScalarOperand_in_ruleXOperandFragment6097);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2988:1: entryRuleParameterOperand returns [EObject current=null] : iv_ruleParameterOperand= ruleParameterOperand EOF ;
    public final EObject entryRuleParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2989:2: (iv_ruleParameterOperand= ruleParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2990:2: iv_ruleParameterOperand= ruleParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand6132);
            iv_ruleParameterOperand=ruleParameterOperand();

            state._fsp--;

             current =iv_ruleParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameterOperand6142); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2997:1: ruleParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRPARAM ) ) ;
    public final EObject ruleParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3000:28: ( ( (lv_prm_0_0= RULE_JRPARAM ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3001:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3001:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3002:1: (lv_prm_0_0= RULE_JRPARAM )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3002:1: (lv_prm_0_0= RULE_JRPARAM )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3003:3: lv_prm_0_0= RULE_JRPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRPARAM,FOLLOW_RULE_JRPARAM_in_ruleParameterOperand6183); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3027:1: entryRuleExclamationParameterOperand returns [EObject current=null] : iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF ;
    public final EObject entryRuleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclamationParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3028:2: (iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3029:2: iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand6222);
            iv_ruleExclamationParameterOperand=ruleExclamationParameterOperand();

            state._fsp--;

             current =iv_ruleExclamationParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclamationParameterOperand6232); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3036:1: ruleExclamationParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRNPARAM ) ) ;
    public final EObject ruleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3039:28: ( ( (lv_prm_0_0= RULE_JRNPARAM ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3040:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3040:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3041:1: (lv_prm_0_0= RULE_JRNPARAM )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3041:1: (lv_prm_0_0= RULE_JRNPARAM )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3042:3: lv_prm_0_0= RULE_JRNPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExclamationParameterOperand6273); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3066:1: entryRuleColumnOperand returns [EObject current=null] : iv_ruleColumnOperand= ruleColumnOperand EOF ;
    public final EObject entryRuleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3067:2: (iv_ruleColumnOperand= ruleColumnOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3068:2: iv_ruleColumnOperand= ruleColumnOperand EOF
            {
             newCompositeNode(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand6312);
            iv_ruleColumnOperand=ruleColumnOperand();

            state._fsp--;

             current =iv_ruleColumnOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOperand6322); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3075:1: ruleColumnOperand returns [EObject current=null] : ( (lv_cfull_0_0= ruleColumnFull ) ) ;
    public final EObject ruleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject lv_cfull_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3078:28: ( ( (lv_cfull_0_0= ruleColumnFull ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3079:1: ( (lv_cfull_0_0= ruleColumnFull ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3079:1: ( (lv_cfull_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3080:1: (lv_cfull_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3080:1: (lv_cfull_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3081:3: lv_cfull_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOperand6367);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3105:1: entryRuleSubQueryOperand returns [EObject current=null] : iv_ruleSubQueryOperand= ruleSubQueryOperand EOF ;
    public final EObject entryRuleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubQueryOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3106:2: (iv_ruleSubQueryOperand= ruleSubQueryOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3107:2: iv_ruleSubQueryOperand= ruleSubQueryOperand EOF
            {
             newCompositeNode(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand6401);
            iv_ruleSubQueryOperand=ruleSubQueryOperand();

            state._fsp--;

             current =iv_ruleSubQueryOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubQueryOperand6411); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3114:1: ruleSubQueryOperand returns [EObject current=null] : ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_sel_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3117:28: ( ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3118:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3118:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3118:2: () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3118:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3119:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSubQueryOperandAccess().getSubQueryOperandAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleSubQueryOperand6458); 

                	newLeafNode(otherlv_1, grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3129:1: ( (lv_sel_2_0= ruleSelectQuery ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3130:1: (lv_sel_2_0= ruleSelectQuery )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3130:1: (lv_sel_2_0= ruleSelectQuery )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3131:3: lv_sel_2_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getSubQueryOperandAccess().getSelSelectQueryParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelectQuery_in_ruleSubQueryOperand6478);
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

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleSubQueryOperand6491); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3160:1: entryRuleScalarOperand returns [EObject current=null] : iv_ruleScalarOperand= ruleScalarOperand EOF ;
    public final EObject entryRuleScalarOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3161:2: (iv_ruleScalarOperand= ruleScalarOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3162:2: iv_ruleScalarOperand= ruleScalarOperand EOF
            {
             newCompositeNode(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand6525);
            iv_ruleScalarOperand=ruleScalarOperand();

            state._fsp--;

             current =iv_ruleScalarOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarOperand6535); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3169:1: ruleScalarOperand returns [EObject current=null] : ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3172:28: ( ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3173:1: ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3173:1: ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) )
            int alt58=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt58=1;
                }
                break;
            case RULE_STRING_:
                {
                alt58=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt58=3;
                }
                break;
            case RULE_DATE:
                {
                alt58=4;
                }
                break;
            case RULE_TIME:
                {
                alt58=5;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt58=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3173:2: ( (lv_soint_0_0= RULE_INT ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3173:2: ( (lv_soint_0_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3174:1: (lv_soint_0_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3174:1: (lv_soint_0_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3175:3: lv_soint_0_0= RULE_INT
                    {
                    lv_soint_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleScalarOperand6577); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3192:6: ( (lv_sostr_1_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3192:6: ( (lv_sostr_1_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3193:1: (lv_sostr_1_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3193:1: (lv_sostr_1_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3194:3: lv_sostr_1_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleScalarOperand6609);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3211:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3211:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3212:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3212:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3213:3: lv_sodbl_2_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_2_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand6632); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3230:6: ( (lv_sodate_3_0= RULE_DATE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3230:6: ( (lv_sodate_3_0= RULE_DATE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3231:1: (lv_sodate_3_0= RULE_DATE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3231:1: (lv_sodate_3_0= RULE_DATE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3232:3: lv_sodate_3_0= RULE_DATE
                    {
                    lv_sodate_3_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleScalarOperand6660); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3249:6: ( (lv_sotime_4_0= RULE_TIME ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3249:6: ( (lv_sotime_4_0= RULE_TIME ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3250:1: (lv_sotime_4_0= RULE_TIME )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3250:1: (lv_sotime_4_0= RULE_TIME )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3251:3: lv_sotime_4_0= RULE_TIME
                    {
                    lv_sotime_4_0=(Token)match(input,RULE_TIME,FOLLOW_RULE_TIME_in_ruleScalarOperand6688); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3268:6: ( (lv_sodt_5_0= RULE_TIMESTAMP ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3268:6: ( (lv_sodt_5_0= RULE_TIMESTAMP ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3269:1: (lv_sodt_5_0= RULE_TIMESTAMP )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3269:1: (lv_sodt_5_0= RULE_TIMESTAMP )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3270:3: lv_sodt_5_0= RULE_TIMESTAMP
                    {
                    lv_sodt_5_0=(Token)match(input,RULE_TIMESTAMP,FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand6716); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3294:1: entryRuleSQLCASE returns [EObject current=null] : iv_ruleSQLCASE= ruleSQLCASE EOF ;
    public final EObject entryRuleSQLCASE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCASE = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3295:2: (iv_ruleSQLCASE= ruleSQLCASE EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3296:2: iv_ruleSQLCASE= ruleSQLCASE EOF
            {
             newCompositeNode(grammarAccess.getSQLCASERule()); 
            pushFollow(FOLLOW_ruleSQLCASE_in_entryRuleSQLCASE6756);
            iv_ruleSQLCASE=ruleSQLCASE();

            state._fsp--;

             current =iv_ruleSQLCASE; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSQLCASE6766); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3303:1: ruleSQLCASE returns [EObject current=null] : (otherlv_0= KEYWORD_28 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_27 ) ;
    public final EObject ruleSQLCASE() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_expr_1_0 = null;

        EObject lv_when_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3306:28: ( (otherlv_0= KEYWORD_28 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_27 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3307:1: (otherlv_0= KEYWORD_28 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_27 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3307:1: (otherlv_0= KEYWORD_28 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_27 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3308:2: otherlv_0= KEYWORD_28 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_27
            {
            otherlv_0=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_ruleSQLCASE6804); 

                	newLeafNode(otherlv_0, grammarAccess.getSQLCASEAccess().getCASEKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3312:1: ( (lv_expr_1_0= ruleFullExpression ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==KEYWORD_28||LA59_0==KEYWORD_14||LA59_0==KEYWORD_1||(LA59_0>=RULE_JRPARAM && LA59_0<=RULE_JRNPARAM)||(LA59_0>=RULE_INT && LA59_0<=RULE_ID)) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3313:1: (lv_expr_1_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3313:1: (lv_expr_1_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3314:3: lv_expr_1_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSQLCASEAccess().getExprFullExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSQLCASE6824);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3330:3: ( (lv_when_2_0= ruleSQLCaseWhens ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3331:1: (lv_when_2_0= ruleSQLCaseWhens )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3331:1: (lv_when_2_0= ruleSQLCaseWhens )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3332:3: lv_when_2_0= ruleSQLCaseWhens
            {
             
            	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWhenSQLCaseWhensParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSQLCaseWhens_in_ruleSQLCASE6846);
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

            otherlv_3=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleSQLCASE6859); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3361:1: entryRuleSQLCaseWhens returns [EObject current=null] : iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF ;
    public final EObject entryRuleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCaseWhens = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3362:2: (iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3363:2: iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF
            {
             newCompositeNode(grammarAccess.getSQLCaseWhensRule()); 
            pushFollow(FOLLOW_ruleSQLCaseWhens_in_entryRuleSQLCaseWhens6893);
            iv_ruleSQLCaseWhens=ruleSQLCaseWhens();

            state._fsp--;

             current =iv_ruleSQLCaseWhens; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSQLCaseWhens6903); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3370:1: ruleSQLCaseWhens returns [EObject current=null] : (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) ;
    public final EObject ruleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject this_SqlCaseWhen_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3373:28: ( (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3374:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3374:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3375:5: this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getSqlCaseWhenParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens6950);
            this_SqlCaseWhen_0=ruleSqlCaseWhen();

            state._fsp--;


                    current = this_SqlCaseWhen_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3383:1: ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==KEYWORD_36) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3383:2: () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3383:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3384:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getSQLCaseWhensAccess().getWhenListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3389:2: ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    int cnt60=0;
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==KEYWORD_36) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3390:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3390:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3391:3: lv_entries_2_0= ruleSqlCaseWhen
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getEntriesSqlCaseWhenParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens6980);
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
                    	    if ( cnt60 >= 1 ) break loop60;
                                EarlyExitException eee =
                                    new EarlyExitException(60, input);
                                throw eee;
                        }
                        cnt60++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3415:1: entryRuleSqlCaseWhen returns [EObject current=null] : iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF ;
    public final EObject entryRuleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSqlCaseWhen = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3416:2: (iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3417:2: iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF
            {
             newCompositeNode(grammarAccess.getSqlCaseWhenRule()); 
            pushFollow(FOLLOW_ruleSqlCaseWhen_in_entryRuleSqlCaseWhen7018);
            iv_ruleSqlCaseWhen=ruleSqlCaseWhen();

            state._fsp--;

             current =iv_ruleSqlCaseWhen; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSqlCaseWhen7028); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3424:1: ruleSqlCaseWhen returns [EObject current=null] : (otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_35 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_30 ( (lv_eexp_5_0= ruleOperand ) ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3427:28: ( (otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_35 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_30 ( (lv_eexp_5_0= ruleOperand ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3428:1: (otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_35 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_30 ( (lv_eexp_5_0= ruleOperand ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3428:1: (otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_35 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_30 ( (lv_eexp_5_0= ruleOperand ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3429:2: otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_35 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_30 ( (lv_eexp_5_0= ruleOperand ) ) )?
            {
            otherlv_0=(Token)match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_ruleSqlCaseWhen7066); 

                	newLeafNode(otherlv_0, grammarAccess.getSqlCaseWhenAccess().getWHENKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3433:1: ( (lv_expr_1_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3434:1: (lv_expr_1_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3434:1: (lv_expr_1_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3435:3: lv_expr_1_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getExprFullExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleSqlCaseWhen7086);
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

            otherlv_2=(Token)match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_ruleSqlCaseWhen7099); 

                	newLeafNode(otherlv_2, grammarAccess.getSqlCaseWhenAccess().getTHENKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3456:1: ( (lv_texp_3_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3457:1: (lv_texp_3_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3457:1: (lv_texp_3_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3458:3: lv_texp_3_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getTexpOperandParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleSqlCaseWhen7119);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3474:2: (otherlv_4= KEYWORD_30 ( (lv_eexp_5_0= ruleOperand ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==KEYWORD_30) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3475:2: otherlv_4= KEYWORD_30 ( (lv_eexp_5_0= ruleOperand ) )
                    {
                    otherlv_4=(Token)match(input,KEYWORD_30,FOLLOW_KEYWORD_30_in_ruleSqlCaseWhen7133); 

                        	newLeafNode(otherlv_4, grammarAccess.getSqlCaseWhenAccess().getELSEKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3479:1: ( (lv_eexp_5_0= ruleOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3480:1: (lv_eexp_5_0= ruleOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3480:1: (lv_eexp_5_0= ruleOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3481:3: lv_eexp_5_0= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getEexpOperandParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleSqlCaseWhen7153);
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


    // $ANTLR start "entryRuleDBID"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3505:1: entryRuleDBID returns [String current=null] : iv_ruleDBID= ruleDBID EOF ;
    public final String entryRuleDBID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDBID = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3506:1: (iv_ruleDBID= ruleDBID EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3507:2: iv_ruleDBID= ruleDBID EOF
            {
             newCompositeNode(grammarAccess.getDBIDRule()); 
            pushFollow(FOLLOW_ruleDBID_in_entryRuleDBID7191);
            iv_ruleDBID=ruleDBID();

            state._fsp--;

             current =iv_ruleDBID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBID7202); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3514:1: ruleDBID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDBID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_DBNAME_1=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3518:6: ( (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3519:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3519:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            int alt63=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt63=1;
                }
                break;
            case RULE_DBNAME:
                {
                alt63=2;
                }
                break;
            case RULE_STRING:
                {
                alt63=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3519:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDBID7242); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getDBIDAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3527:10: this_DBNAME_1= RULE_DBNAME
                    {
                    this_DBNAME_1=(Token)match(input,RULE_DBNAME,FOLLOW_RULE_DBNAME_in_ruleDBID7268); 

                    		current.merge(this_DBNAME_1);
                        
                     
                        newLeafNode(this_DBNAME_1, grammarAccess.getDBIDAccess().getDBNAMETerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3535:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDBID7294); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3550:1: entryRuleStringOperand returns [String current=null] : iv_ruleStringOperand= ruleStringOperand EOF ;
    public final String entryRuleStringOperand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3551:1: (iv_ruleStringOperand= ruleStringOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3552:2: iv_ruleStringOperand= ruleStringOperand EOF
            {
             newCompositeNode(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_ruleStringOperand_in_entryRuleStringOperand7339);
            iv_ruleStringOperand=ruleStringOperand();

            state._fsp--;

             current =iv_ruleStringOperand.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringOperand7350); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3559:1: ruleStringOperand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING__0= RULE_STRING_ ;
    public final AntlrDatatypeRuleToken ruleStringOperand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING__0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3563:6: (this_STRING__0= RULE_STRING_ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3564:5: this_STRING__0= RULE_STRING_
            {
            this_STRING__0=(Token)match(input,RULE_STRING_,FOLLOW_RULE_STRING__in_ruleStringOperand7389); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3579:1: entryRuleFNAME returns [String current=null] : iv_ruleFNAME= ruleFNAME EOF ;
    public final String entryRuleFNAME() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFNAME = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3580:1: (iv_ruleFNAME= ruleFNAME EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3581:2: iv_ruleFNAME= ruleFNAME EOF
            {
             newCompositeNode(grammarAccess.getFNAMERule()); 
            pushFollow(FOLLOW_ruleFNAME_in_entryRuleFNAME7433);
            iv_ruleFNAME=ruleFNAME();

            state._fsp--;

             current =iv_ruleFNAME.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFNAME7444); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3588:1: ruleFNAME returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= KEYWORD_1 ) ;
    public final AntlrDatatypeRuleToken ruleFNAME() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3592:6: ( (this_ID_0= RULE_ID kw= KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3593:1: (this_ID_0= RULE_ID kw= KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3593:1: (this_ID_0= RULE_ID kw= KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3593:6: this_ID_0= RULE_ID kw= KEYWORD_1
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFNAME7484); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getFNAMEAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleFNAME7502); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3614:1: ruleXFunction returns [Enumerator current=null] : ( (enumLiteral_0= KEYWORD_19 ) | (enumLiteral_1= KEYWORD_40 ) | (enumLiteral_2= KEYWORD_37 ) | (enumLiteral_3= KEYWORD_54 ) | (enumLiteral_4= KEYWORD_32 ) | (enumLiteral_5= KEYWORD_48 ) | (enumLiteral_6= KEYWORD_38 ) | (enumLiteral_7= KEYWORD_57 ) | (enumLiteral_8= KEYWORD_47 ) | (enumLiteral_9= KEYWORD_59 ) | (enumLiteral_10= KEYWORD_56 ) | (enumLiteral_11= KEYWORD_50 ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3616:28: ( ( (enumLiteral_0= KEYWORD_19 ) | (enumLiteral_1= KEYWORD_40 ) | (enumLiteral_2= KEYWORD_37 ) | (enumLiteral_3= KEYWORD_54 ) | (enumLiteral_4= KEYWORD_32 ) | (enumLiteral_5= KEYWORD_48 ) | (enumLiteral_6= KEYWORD_38 ) | (enumLiteral_7= KEYWORD_57 ) | (enumLiteral_8= KEYWORD_47 ) | (enumLiteral_9= KEYWORD_59 ) | (enumLiteral_10= KEYWORD_56 ) | (enumLiteral_11= KEYWORD_50 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3617:1: ( (enumLiteral_0= KEYWORD_19 ) | (enumLiteral_1= KEYWORD_40 ) | (enumLiteral_2= KEYWORD_37 ) | (enumLiteral_3= KEYWORD_54 ) | (enumLiteral_4= KEYWORD_32 ) | (enumLiteral_5= KEYWORD_48 ) | (enumLiteral_6= KEYWORD_38 ) | (enumLiteral_7= KEYWORD_57 ) | (enumLiteral_8= KEYWORD_47 ) | (enumLiteral_9= KEYWORD_59 ) | (enumLiteral_10= KEYWORD_56 ) | (enumLiteral_11= KEYWORD_50 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3617:1: ( (enumLiteral_0= KEYWORD_19 ) | (enumLiteral_1= KEYWORD_40 ) | (enumLiteral_2= KEYWORD_37 ) | (enumLiteral_3= KEYWORD_54 ) | (enumLiteral_4= KEYWORD_32 ) | (enumLiteral_5= KEYWORD_48 ) | (enumLiteral_6= KEYWORD_38 ) | (enumLiteral_7= KEYWORD_57 ) | (enumLiteral_8= KEYWORD_47 ) | (enumLiteral_9= KEYWORD_59 ) | (enumLiteral_10= KEYWORD_56 ) | (enumLiteral_11= KEYWORD_50 ) )
            int alt64=12;
            switch ( input.LA(1) ) {
            case KEYWORD_19:
                {
                alt64=1;
                }
                break;
            case KEYWORD_40:
                {
                alt64=2;
                }
                break;
            case KEYWORD_37:
                {
                alt64=3;
                }
                break;
            case KEYWORD_54:
                {
                alt64=4;
                }
                break;
            case KEYWORD_32:
                {
                alt64=5;
                }
                break;
            case KEYWORD_48:
                {
                alt64=6;
                }
                break;
            case KEYWORD_38:
                {
                alt64=7;
                }
                break;
            case KEYWORD_57:
                {
                alt64=8;
                }
                break;
            case KEYWORD_47:
                {
                alt64=9;
                }
                break;
            case KEYWORD_59:
                {
                alt64=10;
                }
                break;
            case KEYWORD_56:
                {
                alt64=11;
                }
                break;
            case KEYWORD_50:
                {
                alt64=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3617:2: (enumLiteral_0= KEYWORD_19 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3617:2: (enumLiteral_0= KEYWORD_19 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3617:7: enumLiteral_0= KEYWORD_19
                    {
                    enumLiteral_0=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleXFunction7559); 

                            current = grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3623:6: (enumLiteral_1= KEYWORD_40 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3623:6: (enumLiteral_1= KEYWORD_40 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3623:11: enumLiteral_1= KEYWORD_40
                    {
                    enumLiteral_1=(Token)match(input,KEYWORD_40,FOLLOW_KEYWORD_40_in_ruleXFunction7581); 

                            current = grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3629:6: (enumLiteral_2= KEYWORD_37 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3629:6: (enumLiteral_2= KEYWORD_37 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3629:11: enumLiteral_2= KEYWORD_37
                    {
                    enumLiteral_2=(Token)match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_ruleXFunction7603); 

                            current = grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3635:6: (enumLiteral_3= KEYWORD_54 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3635:6: (enumLiteral_3= KEYWORD_54 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3635:11: enumLiteral_3= KEYWORD_54
                    {
                    enumLiteral_3=(Token)match(input,KEYWORD_54,FOLLOW_KEYWORD_54_in_ruleXFunction7625); 

                            current = grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3641:6: (enumLiteral_4= KEYWORD_32 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3641:6: (enumLiteral_4= KEYWORD_32 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3641:11: enumLiteral_4= KEYWORD_32
                    {
                    enumLiteral_4=(Token)match(input,KEYWORD_32,FOLLOW_KEYWORD_32_in_ruleXFunction7647); 

                            current = grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3647:6: (enumLiteral_5= KEYWORD_48 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3647:6: (enumLiteral_5= KEYWORD_48 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3647:11: enumLiteral_5= KEYWORD_48
                    {
                    enumLiteral_5=(Token)match(input,KEYWORD_48,FOLLOW_KEYWORD_48_in_ruleXFunction7669); 

                            current = grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3653:6: (enumLiteral_6= KEYWORD_38 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3653:6: (enumLiteral_6= KEYWORD_38 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3653:11: enumLiteral_6= KEYWORD_38
                    {
                    enumLiteral_6=(Token)match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_ruleXFunction7691); 

                            current = grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3659:6: (enumLiteral_7= KEYWORD_57 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3659:6: (enumLiteral_7= KEYWORD_57 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3659:11: enumLiteral_7= KEYWORD_57
                    {
                    enumLiteral_7=(Token)match(input,KEYWORD_57,FOLLOW_KEYWORD_57_in_ruleXFunction7713); 

                            current = grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3665:6: (enumLiteral_8= KEYWORD_47 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3665:6: (enumLiteral_8= KEYWORD_47 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3665:11: enumLiteral_8= KEYWORD_47
                    {
                    enumLiteral_8=(Token)match(input,KEYWORD_47,FOLLOW_KEYWORD_47_in_ruleXFunction7735); 

                            current = grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3671:6: (enumLiteral_9= KEYWORD_59 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3671:6: (enumLiteral_9= KEYWORD_59 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3671:11: enumLiteral_9= KEYWORD_59
                    {
                    enumLiteral_9=(Token)match(input,KEYWORD_59,FOLLOW_KEYWORD_59_in_ruleXFunction7757); 

                            current = grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3677:6: (enumLiteral_10= KEYWORD_56 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3677:6: (enumLiteral_10= KEYWORD_56 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3677:11: enumLiteral_10= KEYWORD_56
                    {
                    enumLiteral_10=(Token)match(input,KEYWORD_56,FOLLOW_KEYWORD_56_in_ruleXFunction7779); 

                            current = grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3683:6: (enumLiteral_11= KEYWORD_50 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3683:6: (enumLiteral_11= KEYWORD_50 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3683:11: enumLiteral_11= KEYWORD_50
                    {
                    enumLiteral_11=(Token)match(input,KEYWORD_50,FOLLOW_KEYWORD_50_in_ruleXFunction7801); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3693:1: ruleJoinType returns [Enumerator current=null] : ( (enumLiteral_0= KEYWORD_61 ) | (enumLiteral_1= KEYWORD_65 ) | (enumLiteral_2= KEYWORD_66 ) | (enumLiteral_3= KEYWORD_64 ) | (enumLiteral_4= KEYWORD_60 ) ) ;
    public final Enumerator ruleJoinType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3695:28: ( ( (enumLiteral_0= KEYWORD_61 ) | (enumLiteral_1= KEYWORD_65 ) | (enumLiteral_2= KEYWORD_66 ) | (enumLiteral_3= KEYWORD_64 ) | (enumLiteral_4= KEYWORD_60 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3696:1: ( (enumLiteral_0= KEYWORD_61 ) | (enumLiteral_1= KEYWORD_65 ) | (enumLiteral_2= KEYWORD_66 ) | (enumLiteral_3= KEYWORD_64 ) | (enumLiteral_4= KEYWORD_60 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3696:1: ( (enumLiteral_0= KEYWORD_61 ) | (enumLiteral_1= KEYWORD_65 ) | (enumLiteral_2= KEYWORD_66 ) | (enumLiteral_3= KEYWORD_64 ) | (enumLiteral_4= KEYWORD_60 ) )
            int alt65=5;
            switch ( input.LA(1) ) {
            case KEYWORD_61:
                {
                alt65=1;
                }
                break;
            case KEYWORD_65:
                {
                alt65=2;
                }
                break;
            case KEYWORD_66:
                {
                alt65=3;
                }
                break;
            case KEYWORD_64:
                {
                alt65=4;
                }
                break;
            case KEYWORD_60:
                {
                alt65=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }

            switch (alt65) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3696:2: (enumLiteral_0= KEYWORD_61 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3696:2: (enumLiteral_0= KEYWORD_61 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3696:7: enumLiteral_0= KEYWORD_61
                    {
                    enumLiteral_0=(Token)match(input,KEYWORD_61,FOLLOW_KEYWORD_61_in_ruleJoinType7851); 

                            current = grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3702:6: (enumLiteral_1= KEYWORD_65 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3702:6: (enumLiteral_1= KEYWORD_65 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3702:11: enumLiteral_1= KEYWORD_65
                    {
                    enumLiteral_1=(Token)match(input,KEYWORD_65,FOLLOW_KEYWORD_65_in_ruleJoinType7873); 

                            current = grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3708:6: (enumLiteral_2= KEYWORD_66 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3708:6: (enumLiteral_2= KEYWORD_66 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3708:11: enumLiteral_2= KEYWORD_66
                    {
                    enumLiteral_2=(Token)match(input,KEYWORD_66,FOLLOW_KEYWORD_66_in_ruleJoinType7895); 

                            current = grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3714:6: (enumLiteral_3= KEYWORD_64 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3714:6: (enumLiteral_3= KEYWORD_64 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3714:11: enumLiteral_3= KEYWORD_64
                    {
                    enumLiteral_3=(Token)match(input,KEYWORD_64,FOLLOW_KEYWORD_64_in_ruleJoinType7917); 

                            current = grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3720:6: (enumLiteral_4= KEYWORD_60 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3720:6: (enumLiteral_4= KEYWORD_60 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3720:11: enumLiteral_4= KEYWORD_60
                    {
                    enumLiteral_4=(Token)match(input,KEYWORD_60,FOLLOW_KEYWORD_60_in_ruleJoinType7939); 

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


    protected DFA33 dfa33 = new DFA33(this);
    static final String DFA33_eotS =
        "\21\uffff";
    static final String DFA33_eofS =
        "\21\uffff";
    static final String DFA33_minS =
        "\1\60\1\103\1\14\14\74\2\uffff";
    static final String DFA33_maxS =
        "\1\60\1\103\1\65\14\104\2\uffff";
    static final String DFA33_acceptS =
        "\17\uffff\1\1\1\2";
    static final String DFA33_specialS =
        "\21\uffff}>";
    static final String[] DFA33_transitionS = {
            "\1\1",
            "\1\2",
            "\1\14\1\16\3\uffff\1\6\1\uffff\1\15\1\12\1\13\1\10\5\uffff"+
            "\1\5\1\11\1\uffff\1\4\6\uffff\1\7\16\uffff\1\3",
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

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "1439:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_ruleModel123 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_KEYWORD_55_in_ruleModel137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_ruleModel157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_entryRuleSelectQuery194 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectQuery204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSelectQuery251 = new BitSet(new long[]{0x0000000141000802L});
    public static final BitSet FOLLOW_ruleSelectSubSet_in_ruleSelectQuery271 = new BitSet(new long[]{0x0000000141000802L});
    public static final BitSet FOLLOW_ruleSelectSubSet_in_entryRuleSelectSubSet307 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectSubSet317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_41_in_ruleSelectSubSet363 = new BitSet(new long[]{0x0000080008000000L});
    public static final BitSet FOLLOW_KEYWORD_58_in_ruleSelectSubSet391 = new BitSet(new long[]{0x0000080008000000L});
    public static final BitSet FOLLOW_KEYWORD_39_in_ruleSelectSubSet419 = new BitSet(new long[]{0x0000080008000000L});
    public static final BitSet FOLLOW_KEYWORD_43_in_ruleSelectSubSet447 = new BitSet(new long[]{0x0000080008000000L});
    public static final BitSet FOLLOW_KEYWORD_23_in_ruleSelectSubSet480 = new BitSet(new long[]{0x0000080008000000L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSelectSubSet513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_entryRuleSelect548 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelect558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_46_in_ruleSelect602 = new BitSet(new long[]{0x0200000400004000L,0x000000000003FFC0L});
    public static final BitSet FOLLOW_KEYWORD_51_in_ruleSelect627 = new BitSet(new long[]{0x0200000400004000L,0x000000000003FFC0L});
    public static final BitSet FOLLOW_ruleColumns_in_ruleSelect649 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_31_in_ruleSelect662 = new BitSet(new long[]{0x0200000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleTables_in_ruleSelect682 = new BitSet(new long[]{0x0000000202008002L});
    public static final BitSet FOLLOW_KEYWORD_42_in_ruleSelect696 = new BitSet(new long[]{0x0201000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect716 = new BitSet(new long[]{0x0000000002008002L});
    public static final BitSet FOLLOW_KEYWORD_52_in_ruleSelect732 = new BitSet(new long[]{0x0000000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_ruleSelect752 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_KEYWORD_44_in_ruleSelect768 = new BitSet(new long[]{0x0201000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns825 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns882 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleColumns905 = new BitSet(new long[]{0x0200000400004000L,0x000000000003FFC0L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns925 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias964 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleColumnOrAlias1021 = new BitSet(new long[]{0x0010000000000002L,0x0000000000038000L});
    public static final BitSet FOLLOW_KEYWORD_18_in_ruleColumnOrAlias1040 = new BitSet(new long[]{0x0000000000000002L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleColumnOrAlias1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull1138 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull1195 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleColumnFull1218 = new BitSet(new long[]{0x0000000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull1238 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables1277 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables1287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables1334 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleTables1357 = new BitSet(new long[]{0x0200000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables1377 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_entryRuleFromTable1416 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTable1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTable1472 = new BitSet(new long[]{0x0000000000000672L});
    public static final BitSet FOLLOW_ruleFromTableJoin_in_ruleFromTable1493 = new BitSet(new long[]{0x0000000000000672L});
    public static final BitSet FOLLOW_ruleFromTableJoin_in_entryRuleFromTableJoin1529 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTableJoin1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_ruleFromTableJoin1585 = new BitSet(new long[]{0x0200000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTableJoin1606 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleFromTableJoin1619 = new BitSet(new long[]{0x0201000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleFromTableJoin1639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias1674 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias1684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias1731 = new BitSet(new long[]{0x0010000000000002L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleTableOrAlias1758 = new BitSet(new long[]{0x0010000000000002L,0x0000000000038000L});
    public static final BitSet FOLLOW_KEYWORD_18_in_ruleTableOrAlias1778 = new BitSet(new long[]{0x0000000000000002L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableOrAlias1811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull1847 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull1857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull1904 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleTableFull1927 = new BitSet(new long[]{0x0000000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull1947 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName1986 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectName1996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_ruleDbObjectName2041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns2075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns2085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns2132 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByColumns2155 = new BitSet(new long[]{0x0000000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns2175 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull2214 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull2224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull2270 = new BitSet(new long[]{0x0000400800000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleOrderByColumnFull2291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_29_in_ruleOrderByColumnFull2319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns2369 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumns2379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns2426 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleGroupByColumns2449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns2469 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull2508 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumnFull2518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleGroupByColumnFull2563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_entryRuleFullExpression2597 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullExpression2607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleFullExpression2654 = new BitSet(new long[]{0x0080100000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression2684 = new BitSet(new long[]{0x0080100000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond2722 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragmentSecond2732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_24_in_ruleExpressionFragmentSecond2778 = new BitSet(new long[]{0x0201000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_21_in_ruleExpressionFragmentSecond2806 = new BitSet(new long[]{0x0201000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond2841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment2876 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragment2886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment2932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExpressionFragment2959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleExpressionFragment2988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression__in_ruleExpressionFragment3007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup3045 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionGroup3055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleExpressionGroup3102 = new BitSet(new long[]{0x0201000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleExpressionGroup3122 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleExpressionGroup3135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression3169 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression3179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_14_in_ruleXExpression3217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression3238 = new BitSet(new long[]{0x00200040B07A3000L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression3258 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression3271 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleXExpression3291 = new BitSet(new long[]{0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression3305 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression3325 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleXExpression3340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression__in_entryRuleXExpression_3374 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression_3384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_14_in_ruleXExpression_3422 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression_3443 = new BitSet(new long[]{0x00200040B07A3000L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression_3463 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleXExpression_3476 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleXExpression_3496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleXExpression_3510 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression_3530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleXExpression_3545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams3579 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionParams3589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams3636 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpressionParams3659 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams3679 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_entryRuleJRParameter3718 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRParameter3728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJRParameter3769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression3808 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression3818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleExpression3864 = new BitSet(new long[]{0x002E008004A10180L,0x0000000000000007L});
    public static final BitSet FOLLOW_KEYWORD_49_in_ruleExpression3886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_62_in_ruleExpression3914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_ruleExpression3955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_ruleExpression3982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_ruleExpression4009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_ruleExpression4036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_entryRuleComparison4072 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComparison4082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_10_in_ruleComparison4128 = new BitSet(new long[]{0x0200290400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_17_in_ruleComparison4156 = new BitSet(new long[]{0x0200290400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_8_in_ruleComparison4184 = new BitSet(new long[]{0x0200290400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleComparison4212 = new BitSet(new long[]{0x0200290400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_9_in_ruleComparison4240 = new BitSet(new long[]{0x0200290400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_16_in_ruleComparison4268 = new BitSet(new long[]{0x0200290400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_25_in_ruleComparison4303 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_23_in_ruleComparison4331 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_34_in_ruleComparison4359 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleComparison4395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_entryRuleLike4430 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLike4440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_33_in_ruleLike4486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000024000L});
    public static final BitSet FOLLOW_KEYWORD_53_in_ruleLike4514 = new BitSet(new long[]{0x0000000000000000L,0x0000000000024000L});
    public static final BitSet FOLLOW_ruleLikeOperand_in_ruleLike4549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLikeOperand_in_entryRuleLikeOperand4584 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLikeOperand4594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleLikeOperand4640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleLikeOperand4667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_entryRuleBetween4702 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBetween4712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_47_in_ruleBetween4758 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_63_in_ruleBetween4786 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBetween4821 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_KEYWORD_24_in_ruleBetween4834 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBetween4854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_entryRuleInOperator4889 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInOperator4899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_45_in_ruleInOperator4954 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleInOperator4982 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleInOperator5009 = new BitSet(new long[]{0x0200000000000000L,0x0000000000007EC0L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleInOperator5030 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ruleOperandList_in_ruleInOperator5057 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleInOperator5071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_entryRuleOperandList5105 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandList5115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleOperandList5162 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOperandList5185 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007EC0L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleOperandList5205 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_entryRuleOperand5244 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperand5254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand5300 = new BitSet(new long[]{0xA900000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_KEYWORD_3_in_ruleOperand5325 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_5_in_ruleOperand5354 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperand5381 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_7_in_ruleOperand5410 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_KEYWORD_22_in_ruleOperand5439 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand5461 = new BitSet(new long[]{0xA900000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment5498 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFragment5508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_ruleOperandFragment5554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_ruleOperandFragment5581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment5608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleOperandFragment5635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCASE_in_ruleOperandFragment5662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_entryRuleOperandFunction5697 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFunction5707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFNAME_in_ruleOperandFunction5762 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FFC0L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperandFunction5774 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ruleOpFunctionArg_in_ruleOperandFunction5800 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOperandFunction5814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArg_in_entryRuleOpFunctionArg5848 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArg5858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionArg5905 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOpFunctionArg5928 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionArg5948 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment5987 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOperandFragment5997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_ruleXOperandFragment6043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment6070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleXOperandFragment6097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand6132 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameterOperand6142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRPARAM_in_ruleParameterOperand6183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand6222 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclamationParameterOperand6232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExclamationParameterOperand6273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand6312 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOperand6322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOperand6367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand6401 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubQueryOperand6411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleSubQueryOperand6458 = new BitSet(new long[]{0x0000080008000000L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_ruleSubQueryOperand6478 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleSubQueryOperand6491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand6525 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarOperand6535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleScalarOperand6577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleScalarOperand6609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand6632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleScalarOperand6660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIME_in_ruleScalarOperand6688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand6716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCASE_in_entryRuleSQLCASE6756 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSQLCASE6766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_28_in_ruleSQLCASE6804 = new BitSet(new long[]{0x0201040400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSQLCASE6824 = new BitSet(new long[]{0x0201040400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleSQLCaseWhens_in_ruleSQLCASE6846 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleSQLCASE6859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCaseWhens_in_entryRuleSQLCaseWhens6893 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSQLCaseWhens6903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens6950 = new BitSet(new long[]{0x0201040400000002L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens6980 = new BitSet(new long[]{0x0201040400000002L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_entryRuleSqlCaseWhen7018 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSqlCaseWhen7028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_ruleSqlCaseWhen7066 = new BitSet(new long[]{0x0201000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSqlCaseWhen7086 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_35_in_ruleSqlCaseWhen7099 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleSqlCaseWhen7119 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_30_in_ruleSqlCaseWhen7133 = new BitSet(new long[]{0x0200000400000000L,0x000000000003FEC0L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleSqlCaseWhen7153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_entryRuleDBID7191 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBID7202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDBID7242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DBNAME_in_ruleDBID7268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDBID7294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_entryRuleStringOperand7339 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringOperand7350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING__in_ruleStringOperand7389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFNAME_in_entryRuleFNAME7433 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFNAME7444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFNAME7484 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleFNAME7502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleXFunction7559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_40_in_ruleXFunction7581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_ruleXFunction7603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_54_in_ruleXFunction7625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_32_in_ruleXFunction7647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_48_in_ruleXFunction7669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_38_in_ruleXFunction7691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_57_in_ruleXFunction7713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_47_in_ruleXFunction7735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_59_in_ruleXFunction7757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_56_in_ruleXFunction7779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_50_in_ruleXFunction7801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_61_in_ruleJoinType7851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_65_in_ruleJoinType7873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_66_in_ruleJoinType7895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_64_in_ruleJoinType7917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_60_in_ruleJoinType7939 = new BitSet(new long[]{0x0000000000000002L});

}