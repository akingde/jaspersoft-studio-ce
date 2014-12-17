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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_104", "KEYWORD_105", "KEYWORD_103", "KEYWORD_102", "KEYWORD_101", "KEYWORD_97", "KEYWORD_98", "KEYWORD_99", "KEYWORD_100", "KEYWORD_96", "KEYWORD_91", "KEYWORD_92", "KEYWORD_93", "KEYWORD_94", "KEYWORD_95", "KEYWORD_83", "KEYWORD_84", "KEYWORD_85", "KEYWORD_86", "KEYWORD_87", "KEYWORD_88", "KEYWORD_89", "KEYWORD_90", "KEYWORD_75", "KEYWORD_76", "KEYWORD_77", "KEYWORD_78", "KEYWORD_79", "KEYWORD_80", "KEYWORD_81", "KEYWORD_82", "KEYWORD_69", "KEYWORD_70", "KEYWORD_71", "KEYWORD_72", "KEYWORD_73", "KEYWORD_74", "KEYWORD_53", "KEYWORD_54", "KEYWORD_55", "KEYWORD_56", "KEYWORD_57", "KEYWORD_58", "KEYWORD_59", "KEYWORD_60", "KEYWORD_61", "KEYWORD_62", "KEYWORD_63", "KEYWORD_64", "KEYWORD_65", "KEYWORD_66", "KEYWORD_67", "KEYWORD_68", "KEYWORD_35", "KEYWORD_36", "KEYWORD_37", "KEYWORD_38", "KEYWORD_39", "KEYWORD_40", "KEYWORD_41", "KEYWORD_42", "KEYWORD_43", "KEYWORD_44", "KEYWORD_45", "KEYWORD_46", "KEYWORD_47", "KEYWORD_48", "KEYWORD_49", "KEYWORD_50", "KEYWORD_51", "KEYWORD_52", "KEYWORD_25", "KEYWORD_26", "KEYWORD_27", "KEYWORD_28", "KEYWORD_29", "KEYWORD_30", "KEYWORD_31", "KEYWORD_32", "KEYWORD_33", "KEYWORD_34", "KEYWORD_14", "KEYWORD_15", "KEYWORD_16", "KEYWORD_17", "KEYWORD_18", "KEYWORD_19", "KEYWORD_20", "KEYWORD_21", "KEYWORD_22", "KEYWORD_23", "KEYWORD_24", "KEYWORD_1", "KEYWORD_2", "KEYWORD_3", "KEYWORD_4", "KEYWORD_5", "KEYWORD_6", "KEYWORD_7", "KEYWORD_8", "KEYWORD_9", "KEYWORD_10", "KEYWORD_11", "KEYWORD_12", "KEYWORD_13", "RULE_JRPARAM", "RULE_JRNPARAM", "RULE_STAR", "RULE_INT", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_SIGNED_DOUBLE", "RULE_STRING_", "RULE_STRING", "RULE_DBNAME", "RULE_ID", "RULE_SL_COMMENT", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=120;
    public static final int RULE_JRNPARAM=110;
    public static final int RULE_DATE=113;
    public static final int RULE_ANY_OTHER=124;
    public static final int KEYWORD_56=44;
    public static final int KEYWORD_55=43;
    public static final int KEYWORD_54=42;
    public static final int KEYWORD_53=41;
    public static final int KEYWORD_52=74;
    public static final int KEYWORD_51=73;
    public static final int KEYWORD_50=72;
    public static final int EOF=-1;
    public static final int KEYWORD_59=47;
    public static final int KEYWORD_58=46;
    public static final int KEYWORD_57=45;
    public static final int KEYWORD_65=53;
    public static final int KEYWORD_64=52;
    public static final int KEYWORD_67=55;
    public static final int RULE_SIGNED_DOUBLE=116;
    public static final int KEYWORD_66=54;
    public static final int KEYWORD_61=49;
    public static final int KEYWORD_60=48;
    public static final int KEYWORD_63=51;
    public static final int KEYWORD_62=50;
    public static final int KEYWORD_69=35;
    public static final int KEYWORD_68=56;
    public static final int KEYWORD_30=80;
    public static final int KEYWORD_105=5;
    public static final int KEYWORD_34=84;
    public static final int KEYWORD_33=83;
    public static final int KEYWORD_32=82;
    public static final int KEYWORD_31=81;
    public static final int KEYWORD_38=60;
    public static final int KEYWORD_37=59;
    public static final int KEYWORD_36=58;
    public static final int KEYWORD_35=57;
    public static final int RULE_ML_COMMENT=122;
    public static final int KEYWORD_39=61;
    public static final int RULE_STRING=118;
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
    public static final int KEYWORD_97=9;
    public static final int KEYWORD_98=10;
    public static final int KEYWORD_99=11;
    public static final int KEYWORD_93=16;
    public static final int KEYWORD_94=17;
    public static final int KEYWORD_95=18;
    public static final int KEYWORD_96=13;
    public static final int KEYWORD_90=26;
    public static final int KEYWORD_19=90;
    public static final int KEYWORD_92=15;
    public static final int KEYWORD_17=88;
    public static final int KEYWORD_91=14;
    public static final int KEYWORD_18=89;
    public static final int KEYWORD_15=86;
    public static final int KEYWORD_16=87;
    public static final int KEYWORD_13=108;
    public static final int RULE_STRING_=117;
    public static final int KEYWORD_14=85;
    public static final int KEYWORD_11=106;
    public static final int KEYWORD_12=107;
    public static final int KEYWORD_10=105;
    public static final int KEYWORD_103=6;
    public static final int KEYWORD_104=4;
    public static final int KEYWORD_101=8;
    public static final int KEYWORD_102=7;
    public static final int KEYWORD_100=12;
    public static final int KEYWORD_6=101;
    public static final int KEYWORD_7=102;
    public static final int KEYWORD_8=103;
    public static final int KEYWORD_9=104;
    public static final int RULE_TIME=114;
    public static final int KEYWORD_28=78;
    public static final int KEYWORD_29=79;
    public static final int RULE_TIMESTAMP=115;
    public static final int RULE_INT=112;
    public static final int KEYWORD_24=95;
    public static final int RULE_DBNAME=119;
    public static final int KEYWORD_25=75;
    public static final int KEYWORD_26=76;
    public static final int KEYWORD_27=77;
    public static final int KEYWORD_20=91;
    public static final int KEYWORD_21=92;
    public static final int KEYWORD_22=93;
    public static final int KEYWORD_23=94;
    public static final int KEYWORD_79=31;
    public static final int KEYWORD_71=37;
    public static final int KEYWORD_72=38;
    public static final int KEYWORD_73=39;
    public static final int KEYWORD_74=40;
    public static final int KEYWORD_75=27;
    public static final int KEYWORD_76=28;
    public static final int KEYWORD_77=29;
    public static final int KEYWORD_78=30;
    public static final int KEYWORD_1=96;
    public static final int KEYWORD_5=100;
    public static final int KEYWORD_4=99;
    public static final int KEYWORD_70=36;
    public static final int KEYWORD_3=98;
    public static final int KEYWORD_2=97;
    public static final int RULE_SL_COMMENT=121;
    public static final int RULE_STAR=111;
    public static final int KEYWORD_84=20;
    public static final int KEYWORD_85=21;
    public static final int KEYWORD_82=34;
    public static final int KEYWORD_83=19;
    public static final int KEYWORD_88=24;
    public static final int KEYWORD_89=25;
    public static final int RULE_JRPARAM=109;
    public static final int KEYWORD_86=22;
    public static final int KEYWORD_87=23;
    public static final int KEYWORD_81=33;
    public static final int KEYWORD_80=32;
    public static final int RULE_WS=123;

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:71:1: ruleModel returns [EObject current=null] : ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_88 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_59 ( (lv_lim_4_0= ruleLimit ) ) )? (otherlv_5= KEYWORD_73 ( (lv_offset_6_0= ruleOffset ) ) )? (otherlv_7= KEYWORD_98 ( (lv_fetchFirst_8_0= ruleFetchFirst ) ) )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_query_0_0 = null;

        EObject lv_orderByEntry_2_0 = null;

        EObject lv_lim_4_0 = null;

        EObject lv_offset_6_0 = null;

        EObject lv_fetchFirst_8_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:74:28: ( ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_88 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_59 ( (lv_lim_4_0= ruleLimit ) ) )? (otherlv_5= KEYWORD_73 ( (lv_offset_6_0= ruleOffset ) ) )? (otherlv_7= KEYWORD_98 ( (lv_fetchFirst_8_0= ruleFetchFirst ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_88 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_59 ( (lv_lim_4_0= ruleLimit ) ) )? (otherlv_5= KEYWORD_73 ( (lv_offset_6_0= ruleOffset ) ) )? (otherlv_7= KEYWORD_98 ( (lv_fetchFirst_8_0= ruleFetchFirst ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: ( ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_88 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_59 ( (lv_lim_4_0= ruleLimit ) ) )? (otherlv_5= KEYWORD_73 ( (lv_offset_6_0= ruleOffset ) ) )? (otherlv_7= KEYWORD_98 ( (lv_fetchFirst_8_0= ruleFetchFirst ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:2: ( (lv_query_0_0= ruleSelectQuery ) ) (otherlv_1= KEYWORD_88 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )? (otherlv_3= KEYWORD_59 ( (lv_lim_4_0= ruleLimit ) ) )? (otherlv_5= KEYWORD_73 ( (lv_offset_6_0= ruleOffset ) ) )? (otherlv_7= KEYWORD_98 ( (lv_fetchFirst_8_0= ruleFetchFirst ) ) )?
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:93:2: (otherlv_1= KEYWORD_88 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==KEYWORD_88) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:94:2: otherlv_1= KEYWORD_88 ( (lv_orderByEntry_2_0= ruleOrderByColumns ) )
                    {
                    otherlv_1=(Token)match(input,KEYWORD_88,FOLLOW_KEYWORD_88_in_ruleModel137); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:116:4: (otherlv_3= KEYWORD_59 ( (lv_lim_4_0= ruleLimit ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==KEYWORD_59) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:117:2: otherlv_3= KEYWORD_59 ( (lv_lim_4_0= ruleLimit ) )
                    {
                    otherlv_3=(Token)match(input,KEYWORD_59,FOLLOW_KEYWORD_59_in_ruleModel173); 

                        	newLeafNode(otherlv_3, grammarAccess.getModelAccess().getLIMITKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:121:1: ( (lv_lim_4_0= ruleLimit ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:122:1: (lv_lim_4_0= ruleLimit )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:122:1: (lv_lim_4_0= ruleLimit )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:123:3: lv_lim_4_0= ruleLimit
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getLimLimitParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleLimit_in_ruleModel193);
                    lv_lim_4_0=ruleLimit();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"lim",
                            		lv_lim_4_0, 
                            		"Limit");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:139:4: (otherlv_5= KEYWORD_73 ( (lv_offset_6_0= ruleOffset ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==KEYWORD_73) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:140:2: otherlv_5= KEYWORD_73 ( (lv_offset_6_0= ruleOffset ) )
                    {
                    otherlv_5=(Token)match(input,KEYWORD_73,FOLLOW_KEYWORD_73_in_ruleModel209); 

                        	newLeafNode(otherlv_5, grammarAccess.getModelAccess().getOFFSETKeyword_3_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:144:1: ( (lv_offset_6_0= ruleOffset ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:145:1: (lv_offset_6_0= ruleOffset )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:145:1: (lv_offset_6_0= ruleOffset )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:146:3: lv_offset_6_0= ruleOffset
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getOffsetOffsetParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOffset_in_ruleModel229);
                    lv_offset_6_0=ruleOffset();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"offset",
                            		lv_offset_6_0, 
                            		"Offset");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:162:4: (otherlv_7= KEYWORD_98 ( (lv_fetchFirst_8_0= ruleFetchFirst ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_98) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:163:2: otherlv_7= KEYWORD_98 ( (lv_fetchFirst_8_0= ruleFetchFirst ) )
                    {
                    otherlv_7=(Token)match(input,KEYWORD_98,FOLLOW_KEYWORD_98_in_ruleModel245); 

                        	newLeafNode(otherlv_7, grammarAccess.getModelAccess().getFETCHFIRSTKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:167:1: ( (lv_fetchFirst_8_0= ruleFetchFirst ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:168:1: (lv_fetchFirst_8_0= ruleFetchFirst )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:168:1: (lv_fetchFirst_8_0= ruleFetchFirst )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:169:3: lv_fetchFirst_8_0= ruleFetchFirst
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getFetchFirstFetchFirstParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFetchFirst_in_ruleModel265);
                    lv_fetchFirst_8_0=ruleFetchFirst();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"fetchFirst",
                            		lv_fetchFirst_8_0, 
                            		"FetchFirst");
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


    // $ANTLR start "entryRuleFetchFirst"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:193:1: entryRuleFetchFirst returns [EObject current=null] : iv_ruleFetchFirst= ruleFetchFirst EOF ;
    public final EObject entryRuleFetchFirst() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFetchFirst = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:194:2: (iv_ruleFetchFirst= ruleFetchFirst EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:195:2: iv_ruleFetchFirst= ruleFetchFirst EOF
            {
             newCompositeNode(grammarAccess.getFetchFirstRule()); 
            pushFollow(FOLLOW_ruleFetchFirst_in_entryRuleFetchFirst302);
            iv_ruleFetchFirst=ruleFetchFirst();

            state._fsp--;

             current =iv_ruleFetchFirst; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFetchFirst312); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:202:1: ruleFetchFirst returns [EObject current=null] : ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 ) ) ) otherlv_2= KEYWORD_47 ) ;
    public final EObject ruleFetchFirst() throws RecognitionException {
        EObject current = null;

        Token lv_row_1_1=null;
        Token lv_row_1_2=null;
        Token otherlv_2=null;
        EObject lv_fetchFirst_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:205:28: ( ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 ) ) ) otherlv_2= KEYWORD_47 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:206:1: ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 ) ) ) otherlv_2= KEYWORD_47 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:206:1: ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 ) ) ) otherlv_2= KEYWORD_47 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:206:2: ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 ) ) ) otherlv_2= KEYWORD_47
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:206:2: ( (lv_fetchFirst_0_0= ruleIntegerValue ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_INT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:207:1: (lv_fetchFirst_0_0= ruleIntegerValue )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:207:1: (lv_fetchFirst_0_0= ruleIntegerValue )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:208:3: lv_fetchFirst_0_0= ruleIntegerValue
                    {
                     
                    	        newCompositeNode(grammarAccess.getFetchFirstAccess().getFetchFirstIntegerValueParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleIntegerValue_in_ruleFetchFirst358);
                    lv_fetchFirst_0_0=ruleIntegerValue();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFetchFirstRule());
                    	        }
                           		set(
                           			current, 
                           			"fetchFirst",
                            		lv_fetchFirst_0_0, 
                            		"IntegerValue");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:224:3: ( ( (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:225:1: ( (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:225:1: ( (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:226:1: (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:226:1: (lv_row_1_1= KEYWORD_32 | lv_row_1_2= KEYWORD_49 )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_32) ) {
                alt6=1;
            }
            else if ( (LA6_0==KEYWORD_49) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:227:3: lv_row_1_1= KEYWORD_32
                    {
                    lv_row_1_1=(Token)match(input,KEYWORD_32,FOLLOW_KEYWORD_32_in_ruleFetchFirst380); 

                            newLeafNode(lv_row_1_1, grammarAccess.getFetchFirstAccess().getRowROWKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getFetchFirstRule());
                    	        }
                           		setWithLastConsumed(current, "row", lv_row_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:240:8: lv_row_1_2= KEYWORD_49
                    {
                    lv_row_1_2=(Token)match(input,KEYWORD_49,FOLLOW_KEYWORD_49_in_ruleFetchFirst408); 

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

            otherlv_2=(Token)match(input,KEYWORD_47,FOLLOW_KEYWORD_47_in_ruleFetchFirst435); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:269:1: entryRuleOffset returns [EObject current=null] : iv_ruleOffset= ruleOffset EOF ;
    public final EObject entryRuleOffset() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOffset = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:270:2: (iv_ruleOffset= ruleOffset EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:271:2: iv_ruleOffset= ruleOffset EOF
            {
             newCompositeNode(grammarAccess.getOffsetRule()); 
            pushFollow(FOLLOW_ruleOffset_in_entryRuleOffset469);
            iv_ruleOffset=ruleOffset();

            state._fsp--;

             current =iv_ruleOffset; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOffset479); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:278:1: ruleOffset returns [EObject current=null] : ( (lv_offset_0_0= RULE_INT ) ) ;
    public final EObject ruleOffset() throws RecognitionException {
        EObject current = null;

        Token lv_offset_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:281:28: ( ( (lv_offset_0_0= RULE_INT ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:282:1: ( (lv_offset_0_0= RULE_INT ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:282:1: ( (lv_offset_0_0= RULE_INT ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:283:1: (lv_offset_0_0= RULE_INT )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:283:1: (lv_offset_0_0= RULE_INT )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:284:3: lv_offset_0_0= RULE_INT
            {
            lv_offset_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOffset520); 

            			newLeafNode(lv_offset_0_0, grammarAccess.getOffsetAccess().getOffsetINTTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getOffsetRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"offset",
                    		lv_offset_0_0, 
                    		"INT");
            	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:308:1: entryRuleLimit returns [EObject current=null] : iv_ruleLimit= ruleLimit EOF ;
    public final EObject entryRuleLimit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLimit = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:309:2: (iv_ruleLimit= ruleLimit EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:310:2: iv_ruleLimit= ruleLimit EOF
            {
             newCompositeNode(grammarAccess.getLimitRule()); 
            pushFollow(FOLLOW_ruleLimit_in_entryRuleLimit559);
            iv_ruleLimit=ruleLimit();

            state._fsp--;

             current =iv_ruleLimit; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLimit569); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:317:1: ruleLimit returns [EObject current=null] : ( ( () otherlv_1= KEYWORD_26 ) | ( ( (lv_l1_2_0= RULE_INT ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) ) )? ) ) ;
    public final EObject ruleLimit() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_l1_2_0=null;
        Token otherlv_3=null;
        EObject lv_l2_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:320:28: ( ( ( () otherlv_1= KEYWORD_26 ) | ( ( (lv_l1_2_0= RULE_INT ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) ) )? ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:321:1: ( ( () otherlv_1= KEYWORD_26 ) | ( ( (lv_l1_2_0= RULE_INT ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) ) )? ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:321:1: ( ( () otherlv_1= KEYWORD_26 ) | ( ( (lv_l1_2_0= RULE_INT ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) ) )? ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==KEYWORD_26) ) {
                alt8=1;
            }
            else if ( (LA8_0==RULE_INT) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:321:2: ( () otherlv_1= KEYWORD_26 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:321:2: ( () otherlv_1= KEYWORD_26 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:321:3: () otherlv_1= KEYWORD_26
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:321:3: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:322:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getLimitAccess().getLimitAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleLimit617); 

                        	newLeafNode(otherlv_1, grammarAccess.getLimitAccess().getALLKeyword_0_1());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:333:6: ( ( (lv_l1_2_0= RULE_INT ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) ) )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:333:6: ( ( (lv_l1_2_0= RULE_INT ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) ) )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:333:7: ( (lv_l1_2_0= RULE_INT ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:333:7: ( (lv_l1_2_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:334:1: (lv_l1_2_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:334:1: (lv_l1_2_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:335:3: lv_l1_2_0= RULE_INT
                    {
                    lv_l1_2_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleLimit641); 

                    			newLeafNode(lv_l1_2_0, grammarAccess.getLimitAccess().getL1INTTerminalRuleCall_1_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLimitRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"l1",
                            		lv_l1_2_0, 
                            		"INT");
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:351:2: (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) ) )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==KEYWORD_4) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:352:2: otherlv_3= KEYWORD_4 ( (lv_l2_4_0= ruleIntegerValue ) )
                            {
                            otherlv_3=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleLimit660); 

                                	newLeafNode(otherlv_3, grammarAccess.getLimitAccess().getCommaKeyword_1_1_0());
                                
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:356:1: ( (lv_l2_4_0= ruleIntegerValue ) )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:357:1: (lv_l2_4_0= ruleIntegerValue )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:357:1: (lv_l2_4_0= ruleIntegerValue )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:358:3: lv_l2_4_0= ruleIntegerValue
                            {
                             
                            	        newCompositeNode(grammarAccess.getLimitAccess().getL2IntegerValueParserRuleCall_1_1_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleIntegerValue_in_ruleLimit680);
                            lv_l2_4_0=ruleIntegerValue();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getLimitRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"l2",
                                    		lv_l2_4_0, 
                                    		"IntegerValue");
                            	        afterParserOrEnumRuleCall();
                            	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:382:1: entryRuleSelectQuery returns [EObject current=null] : iv_ruleSelectQuery= ruleSelectQuery EOF ;
    public final EObject entryRuleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectQuery = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:383:2: (iv_ruleSelectQuery= ruleSelectQuery EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:384:2: iv_ruleSelectQuery= ruleSelectQuery EOF
            {
             newCompositeNode(grammarAccess.getSelectQueryRule()); 
            pushFollow(FOLLOW_ruleSelectQuery_in_entryRuleSelectQuery718);
            iv_ruleSelectQuery=ruleSelectQuery();

            state._fsp--;

             current =iv_ruleSelectQuery; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectQuery728); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:391:1: ruleSelectQuery returns [EObject current=null] : (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) ;
    public final EObject ruleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject this_Select_0 = null;

        EObject lv_op_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:394:28: ( (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:395:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:395:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:396:5: this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )*
            {
             
                    newCompositeNode(grammarAccess.getSelectQueryAccess().getSelectParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSelect_in_ruleSelectQuery775);
            this_Select_0=ruleSelect();

            state._fsp--;


                    current = this_Select_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:404:1: ( (lv_op_1_0= ruleSelectSubSet ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==KEYWORD_92||LA9_0==KEYWORD_69||LA9_0==KEYWORD_60||LA9_0==KEYWORD_67) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:405:1: (lv_op_1_0= ruleSelectSubSet )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:405:1: (lv_op_1_0= ruleSelectSubSet )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:406:3: lv_op_1_0= ruleSelectSubSet
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSelectQueryAccess().getOpSelectSubSetParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSelectSubSet_in_ruleSelectQuery795);
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
            	    break loop9;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:430:1: entryRuleSelectSubSet returns [EObject current=null] : iv_ruleSelectSubSet= ruleSelectSubSet EOF ;
    public final EObject entryRuleSelectSubSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectSubSet = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:431:2: (iv_ruleSelectSubSet= ruleSelectSubSet EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:432:2: iv_ruleSelectSubSet= ruleSelectSubSet EOF
            {
             newCompositeNode(grammarAccess.getSelectSubSetRule()); 
            pushFollow(FOLLOW_ruleSelectSubSet_in_entryRuleSelectSubSet831);
            iv_ruleSelectSubSet=ruleSelectSubSet();

            state._fsp--;

             current =iv_ruleSelectSubSet; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectSubSet841); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:439:1: ruleSelectSubSet returns [EObject current=null] : ( ( ( (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:442:28: ( ( ( ( (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:443:1: ( ( ( (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:443:1: ( ( ( (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:443:2: ( ( (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:443:2: ( ( (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:444:1: ( (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:444:1: ( (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:445:1: (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:445:1: (lv_op_0_1= KEYWORD_67 | lv_op_0_2= KEYWORD_92 | lv_op_0_3= KEYWORD_60 | lv_op_0_4= KEYWORD_69 )
            int alt10=4;
            switch ( input.LA(1) ) {
            case KEYWORD_67:
                {
                alt10=1;
                }
                break;
            case KEYWORD_92:
                {
                alt10=2;
                }
                break;
            case KEYWORD_60:
                {
                alt10=3;
                }
                break;
            case KEYWORD_69:
                {
                alt10=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:446:3: lv_op_0_1= KEYWORD_67
                    {
                    lv_op_0_1=(Token)match(input,KEYWORD_67,FOLLOW_KEYWORD_67_in_ruleSelectSubSet887); 

                            newLeafNode(lv_op_0_1, grammarAccess.getSelectSubSetAccess().getOpUNIONKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:459:8: lv_op_0_2= KEYWORD_92
                    {
                    lv_op_0_2=(Token)match(input,KEYWORD_92,FOLLOW_KEYWORD_92_in_ruleSelectSubSet915); 

                            newLeafNode(lv_op_0_2, grammarAccess.getSelectSubSetAccess().getOpINTERSECTKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:472:8: lv_op_0_3= KEYWORD_60
                    {
                    lv_op_0_3=(Token)match(input,KEYWORD_60,FOLLOW_KEYWORD_60_in_ruleSelectSubSet943); 

                            newLeafNode(lv_op_0_3, grammarAccess.getSelectSubSetAccess().getOpMINUSKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:485:8: lv_op_0_4= KEYWORD_69
                    {
                    lv_op_0_4=(Token)match(input,KEYWORD_69,FOLLOW_KEYWORD_69_in_ruleSelectSubSet971); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:501:2: ( (lv_all_1_0= KEYWORD_26 ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==KEYWORD_26) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:502:1: (lv_all_1_0= KEYWORD_26 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:502:1: (lv_all_1_0= KEYWORD_26 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:503:3: lv_all_1_0= KEYWORD_26
                    {
                    lv_all_1_0=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleSelectSubSet1004); 

                            newLeafNode(lv_all_1_0, grammarAccess.getSelectSubSetAccess().getAllALLKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "all", lv_all_1_0, "ALL");
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:517:3: ( (lv_query_2_0= ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:518:1: (lv_query_2_0= ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:518:1: (lv_query_2_0= ruleSelect )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:519:3: lv_query_2_0= ruleSelect
            {
             
            	        newCompositeNode(grammarAccess.getSelectSubSetAccess().getQuerySelectParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelect_in_ruleSelectSubSet1037);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:543:1: entryRuleSelect returns [EObject current=null] : iv_ruleSelect= ruleSelect EOF ;
    public final EObject entryRuleSelect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelect = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:544:2: (iv_ruleSelect= ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:545:2: iv_ruleSelect= ruleSelect EOF
            {
             newCompositeNode(grammarAccess.getSelectRule()); 
            pushFollow(FOLLOW_ruleSelect_in_entryRuleSelect1072);
            iv_ruleSelect=ruleSelect();

            state._fsp--;

             current =iv_ruleSelect; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelect1082); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:552:1: ruleSelect returns [EObject current=null] : ( ( (lv_select_0_0= KEYWORD_74 ) ) (otherlv_1= KEYWORD_84 )? (otherlv_2= KEYWORD_33 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_81 )? (otherlv_6= KEYWORD_94 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_38 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_68 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_85 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_71 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:555:28: ( ( ( (lv_select_0_0= KEYWORD_74 ) ) (otherlv_1= KEYWORD_84 )? (otherlv_2= KEYWORD_33 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_81 )? (otherlv_6= KEYWORD_94 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_38 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_68 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_85 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_71 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:556:1: ( ( (lv_select_0_0= KEYWORD_74 ) ) (otherlv_1= KEYWORD_84 )? (otherlv_2= KEYWORD_33 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_81 )? (otherlv_6= KEYWORD_94 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_38 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_68 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_85 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_71 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:556:1: ( ( (lv_select_0_0= KEYWORD_74 ) ) (otherlv_1= KEYWORD_84 )? (otherlv_2= KEYWORD_33 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_81 )? (otherlv_6= KEYWORD_94 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_38 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_68 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_85 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_71 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:556:2: ( (lv_select_0_0= KEYWORD_74 ) ) (otherlv_1= KEYWORD_84 )? (otherlv_2= KEYWORD_33 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_81 )? (otherlv_6= KEYWORD_94 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_38 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_68 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_85 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_71 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:556:2: ( (lv_select_0_0= KEYWORD_74 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:557:1: (lv_select_0_0= KEYWORD_74 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:557:1: (lv_select_0_0= KEYWORD_74 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:558:3: lv_select_0_0= KEYWORD_74
            {
            lv_select_0_0=(Token)match(input,KEYWORD_74,FOLLOW_KEYWORD_74_in_ruleSelect1126); 

                    newLeafNode(lv_select_0_0, grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectRule());
            	        }
                   		setWithLastConsumed(current, "select", lv_select_0_0, "SELECT");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:572:2: (otherlv_1= KEYWORD_84 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==KEYWORD_84) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:573:2: otherlv_1= KEYWORD_84
                    {
                    otherlv_1=(Token)match(input,KEYWORD_84,FOLLOW_KEYWORD_84_in_ruleSelect1151); 

                        	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:577:3: (otherlv_2= KEYWORD_33 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_81 )? (otherlv_6= KEYWORD_94 )? )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==KEYWORD_33) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:578:2: otherlv_2= KEYWORD_33 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_81 )? (otherlv_6= KEYWORD_94 )?
                    {
                    otherlv_2=(Token)match(input,KEYWORD_33,FOLLOW_KEYWORD_33_in_ruleSelect1166); 

                        	newLeafNode(otherlv_2, grammarAccess.getSelectAccess().getTOPKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:582:1: (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE )
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==RULE_INT) ) {
                        alt13=1;
                    }
                    else if ( (LA13_0==RULE_SIGNED_DOUBLE) ) {
                        alt13=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 0, input);

                        throw nvae;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:582:2: this_INT_3= RULE_INT
                            {
                            this_INT_3=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleSelect1177); 
                             
                                newLeafNode(this_INT_3, grammarAccess.getSelectAccess().getINTTerminalRuleCall_2_1_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:587:6: this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE
                            {
                            this_SIGNED_DOUBLE_4=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleSelect1193); 
                             
                                newLeafNode(this_SIGNED_DOUBLE_4, grammarAccess.getSelectAccess().getSIGNED_DOUBLETerminalRuleCall_2_1_1()); 
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:591:2: (otherlv_5= KEYWORD_81 )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==KEYWORD_81) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:592:2: otherlv_5= KEYWORD_81
                            {
                            otherlv_5=(Token)match(input,KEYWORD_81,FOLLOW_KEYWORD_81_in_ruleSelect1207); 

                                	newLeafNode(otherlv_5, grammarAccess.getSelectAccess().getPERCENTKeyword_2_2());
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:596:3: (otherlv_6= KEYWORD_94 )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==KEYWORD_94) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:597:2: otherlv_6= KEYWORD_94
                            {
                            otherlv_6=(Token)match(input,KEYWORD_94,FOLLOW_KEYWORD_94_in_ruleSelect1222); 

                                	newLeafNode(otherlv_6, grammarAccess.getSelectAccess().getWITHTIESKeyword_2_3());
                                

                            }
                            break;

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:601:5: ( (lv_cols_7_0= ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:602:1: (lv_cols_7_0= ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:602:1: (lv_cols_7_0= ruleColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:603:3: lv_cols_7_0= ruleColumns
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleColumns_in_ruleSelect1246);
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

            otherlv_8=(Token)match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_ruleSelect1259); 

                	newLeafNode(otherlv_8, grammarAccess.getSelectAccess().getFROMKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:624:1: ( (lv_tbl_9_0= ruleTables ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:625:1: (lv_tbl_9_0= ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:625:1: (lv_tbl_9_0= ruleTables )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:626:3: lv_tbl_9_0= ruleTables
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleTables_in_ruleSelect1279);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:642:2: (otherlv_10= KEYWORD_68 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==KEYWORD_68) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:643:2: otherlv_10= KEYWORD_68 ( (lv_whereExpression_11_0= ruleFullExpression ) )
                    {
                    otherlv_10=(Token)match(input,KEYWORD_68,FOLLOW_KEYWORD_68_in_ruleSelect1293); 

                        	newLeafNode(otherlv_10, grammarAccess.getSelectAccess().getWHEREKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:647:1: ( (lv_whereExpression_11_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:648:1: (lv_whereExpression_11_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:648:1: (lv_whereExpression_11_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:649:3: lv_whereExpression_11_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect1313);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:665:4: (otherlv_12= KEYWORD_85 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_85) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:666:2: otherlv_12= KEYWORD_85 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) )
                    {
                    otherlv_12=(Token)match(input,KEYWORD_85,FOLLOW_KEYWORD_85_in_ruleSelect1329); 

                        	newLeafNode(otherlv_12, grammarAccess.getSelectAccess().getGROUPBYKeyword_7_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:670:1: ( (lv_groupByEntry_13_0= ruleGroupByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:671:1: (lv_groupByEntry_13_0= ruleGroupByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:671:1: (lv_groupByEntry_13_0= ruleGroupByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:672:3: lv_groupByEntry_13_0= ruleGroupByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleGroupByColumns_in_ruleSelect1349);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:688:4: (otherlv_14= KEYWORD_71 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==KEYWORD_71) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:689:2: otherlv_14= KEYWORD_71 ( (lv_havingEntry_15_0= ruleFullExpression ) )
                    {
                    otherlv_14=(Token)match(input,KEYWORD_71,FOLLOW_KEYWORD_71_in_ruleSelect1365); 

                        	newLeafNode(otherlv_14, grammarAccess.getSelectAccess().getHAVINGKeyword_8_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:693:1: ( (lv_havingEntry_15_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:694:1: (lv_havingEntry_15_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:694:1: (lv_havingEntry_15_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:695:3: lv_havingEntry_15_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect1385);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:719:1: entryRuleColumns returns [EObject current=null] : iv_ruleColumns= ruleColumns EOF ;
    public final EObject entryRuleColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:720:2: (iv_ruleColumns= ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:721:2: iv_ruleColumns= ruleColumns EOF
            {
             newCompositeNode(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns1422);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns1432); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:728:1: ruleColumns returns [EObject current=null] : (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) ;
    public final EObject ruleColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ColumnOrAlias_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:731:28: ( (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:732:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:732:1: (this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:733:5: this_ColumnOrAlias_0= ruleColumnOrAlias ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns1479);
            this_ColumnOrAlias_0=ruleColumnOrAlias();

            state._fsp--;


                    current = this_ColumnOrAlias_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:741:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+ )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KEYWORD_4) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:741:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:741:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:742:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:747:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) ) )+
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==KEYWORD_4) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:748:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleColumns1502); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:752:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:753:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:753:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:754:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns1522);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:778:1: entryRuleColumnOrAlias returns [EObject current=null] : iv_ruleColumnOrAlias= ruleColumnOrAlias EOF ;
    public final EObject entryRuleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:779:2: (iv_ruleColumnOrAlias= ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:780:2: iv_ruleColumnOrAlias= ruleColumnOrAlias EOF
            {
             newCompositeNode(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias1561);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias1571); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:787:1: ruleColumnOrAlias returns [EObject current=null] : ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_1_0=null;
        Token lv_allCols_3_0=null;
        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_2_0 = null;

        EObject lv_dbAllCols_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:790:28: ( ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:1: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:1: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            int alt24=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==KEYWORD_6) ) {
                    int LA24_6 = input.LA(3);

                    if ( ((LA24_6>=RULE_STRING && LA24_6<=RULE_ID)) ) {
                        alt24=1;
                    }
                    else if ( (LA24_6==RULE_STAR) ) {
                        alt24=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA24_1==EOF||LA24_1==KEYWORD_38||LA24_1==KEYWORD_25||LA24_1==KEYWORD_19||(LA24_1>=KEYWORD_24 && LA24_1<=KEYWORD_5)||LA24_1==KEYWORD_7||LA24_1==RULE_STAR||(LA24_1>=RULE_STRING && LA24_1<=RULE_ID)) ) {
                    alt24=1;
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

                if ( (LA24_2==KEYWORD_6) ) {
                    int LA24_6 = input.LA(3);

                    if ( ((LA24_6>=RULE_STRING && LA24_6<=RULE_ID)) ) {
                        alt24=1;
                    }
                    else if ( (LA24_6==RULE_STAR) ) {
                        alt24=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA24_2==EOF||LA24_2==KEYWORD_38||LA24_2==KEYWORD_25||LA24_2==KEYWORD_19||LA24_2==KEYWORD_24||(LA24_2>=KEYWORD_2 && LA24_2<=KEYWORD_5)||LA24_2==KEYWORD_7||LA24_2==RULE_STAR||(LA24_2>=RULE_STRING && LA24_2<=RULE_ID)) ) {
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

                if ( (LA24_3==KEYWORD_6) ) {
                    int LA24_6 = input.LA(3);

                    if ( ((LA24_6>=RULE_STRING && LA24_6<=RULE_ID)) ) {
                        alt24=1;
                    }
                    else if ( (LA24_6==RULE_STAR) ) {
                        alt24=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 6, input);

                        throw nvae;
                    }
                }
                else if ( (LA24_3==EOF||LA24_3==KEYWORD_38||LA24_3==KEYWORD_25||LA24_3==KEYWORD_19||LA24_3==KEYWORD_24||(LA24_3>=KEYWORD_2 && LA24_3<=KEYWORD_5)||LA24_3==KEYWORD_7||LA24_3==RULE_STAR||(LA24_3>=RULE_STRING && LA24_3<=RULE_ID)) ) {
                    alt24=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 3, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_53:
            case KEYWORD_35:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:2: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:2: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:3: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:3: ( (lv_ce_0_0= ruleOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:792:1: (lv_ce_0_0= ruleOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:792:1: (lv_ce_0_0= ruleOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:793:3: lv_ce_0_0= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getCeOperandParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleColumnOrAlias1618);
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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:809:2: ( (lv_alias_1_0= KEYWORD_19 ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==KEYWORD_19) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:810:1: (lv_alias_1_0= KEYWORD_19 )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:810:1: (lv_alias_1_0= KEYWORD_19 )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:811:3: lv_alias_1_0= KEYWORD_19
                            {
                            lv_alias_1_0=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleColumnOrAlias1637); 

                                    newLeafNode(lv_alias_1_0, grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getColumnOrAliasRule());
                            	        }
                                   		setWithLastConsumed(current, "alias", lv_alias_1_0, "AS");
                            	    

                            }


                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:825:3: ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( ((LA23_0>=RULE_STRING && LA23_0<=RULE_ID)) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:826:1: (lv_colAlias_2_0= ruleDbObjectName )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:826:1: (lv_colAlias_2_0= ruleDbObjectName )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:827:3: lv_colAlias_2_0= ruleDbObjectName
                            {
                             
                            	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
                            	    
                            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias1670);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:844:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:844:6: ( (lv_allCols_3_0= RULE_STAR ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:845:1: (lv_allCols_3_0= RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:845:1: (lv_allCols_3_0= RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:846:3: lv_allCols_3_0= RULE_STAR
                    {
                    lv_allCols_3_0=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleColumnOrAlias1695); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:863:6: ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:863:6: ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:864:1: (lv_dbAllCols_4_0= ruleDbObjectNameAll )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:864:1: (lv_dbAllCols_4_0= ruleDbObjectNameAll )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:865:3: lv_dbAllCols_4_0= ruleDbObjectNameAll
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getDbAllColsDbObjectNameAllParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectNameAll_in_ruleColumnOrAlias1727);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:889:1: entryRuleColumnFull returns [EObject current=null] : iv_ruleColumnFull= ruleColumnFull EOF ;
    public final EObject entryRuleColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:890:2: (iv_ruleColumnFull= ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:891:2: iv_ruleColumnFull= ruleColumnFull EOF
            {
             newCompositeNode(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull1762);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull1772); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:898:1: ruleColumnFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleColumnFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:901:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:902:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:902:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:903:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull1819);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:911:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==KEYWORD_6) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:911:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:911:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:912:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getColumnFullAccess().getColEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:917:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==KEYWORD_6) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:918:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleColumnFull1842); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:922:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:923:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:923:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:924:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull1862);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:948:1: entryRuleTables returns [EObject current=null] : iv_ruleTables= ruleTables EOF ;
    public final EObject entryRuleTables() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTables = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:949:2: (iv_ruleTables= ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:950:2: iv_ruleTables= ruleTables EOF
            {
             newCompositeNode(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables1901);
            iv_ruleTables=ruleTables();

            state._fsp--;

             current =iv_ruleTables; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables1911); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:957:1: ruleTables returns [EObject current=null] : (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) ;
    public final EObject ruleTables() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_FromTable_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:960:28: ( (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:961:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:961:1: (this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:962:5: this_FromTable_0= ruleFromTable ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleFromTable_in_ruleTables1958);
            this_FromTable_0=ruleFromTable();

            state._fsp--;


                    current = this_FromTable_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:970:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+ )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==KEYWORD_4) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:970:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:970:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:971:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:976:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) ) )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==KEYWORD_4) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:977:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleFromTable ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleTables1981); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:981:1: ( (lv_entries_3_0= ruleFromTable ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:982:1: (lv_entries_3_0= ruleFromTable )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:982:1: (lv_entries_3_0= ruleFromTable )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:983:3: lv_entries_3_0= ruleFromTable
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleFromTable_in_ruleTables2001);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1007:1: entryRuleFromTable returns [EObject current=null] : iv_ruleFromTable= ruleFromTable EOF ;
    public final EObject entryRuleFromTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1008:2: (iv_ruleFromTable= ruleFromTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1009:2: iv_ruleFromTable= ruleFromTable EOF
            {
             newCompositeNode(grammarAccess.getFromTableRule()); 
            pushFollow(FOLLOW_ruleFromTable_in_entryRuleFromTable2040);
            iv_ruleFromTable=ruleFromTable();

            state._fsp--;

             current =iv_ruleFromTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTable2050); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1016:1: ruleFromTable returns [EObject current=null] : ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) ;
    public final EObject ruleFromTable() throws RecognitionException {
        EObject current = null;

        EObject lv_table_0_0 = null;

        EObject lv_fjoin_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1019:28: ( ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1020:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1020:1: ( ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1020:2: ( (lv_table_0_0= ruleTableOrAlias ) ) ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1020:2: ( (lv_table_0_0= ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1021:1: (lv_table_0_0= ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1021:1: (lv_table_0_0= ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1022:3: lv_table_0_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTable2096);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1038:2: ( (lv_fjoin_1_0= ruleFromTableJoin ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==KEYWORD_102||LA29_0==KEYWORD_80||LA29_0==KEYWORD_54||LA29_0==KEYWORD_57||LA29_0==KEYWORD_66||(LA29_0>=KEYWORD_39 && LA29_0<=KEYWORD_40)||LA29_0==KEYWORD_42) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1039:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1039:1: (lv_fjoin_1_0= ruleFromTableJoin )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1040:3: lv_fjoin_1_0= ruleFromTableJoin
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFromTableAccess().getFjoinFromTableJoinParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleFromTableJoin_in_ruleFromTable2117);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1064:1: entryRuleFromTableJoin returns [EObject current=null] : iv_ruleFromTableJoin= ruleFromTableJoin EOF ;
    public final EObject entryRuleFromTableJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFromTableJoin = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1065:2: (iv_ruleFromTableJoin= ruleFromTableJoin EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1066:2: iv_ruleFromTableJoin= ruleFromTableJoin EOF
            {
             newCompositeNode(grammarAccess.getFromTableJoinRule()); 
            pushFollow(FOLLOW_ruleFromTableJoin_in_entryRuleFromTableJoin2153);
            iv_ruleFromTableJoin=ruleFromTableJoin();

            state._fsp--;

             current =iv_ruleFromTableJoin; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTableJoin2163); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1073:1: ruleFromTableJoin returns [EObject current=null] : ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) ;
    public final EObject ruleFromTableJoin() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_join_0_0 = null;

        EObject lv_onTable_1_0 = null;

        EObject lv_joinExpr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1076:28: ( ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1077:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1077:1: ( ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1077:2: ( (lv_join_0_0= ruleJoinType ) ) ( (lv_onTable_1_0= ruleTableOrAlias ) ) otherlv_2= KEYWORD_21 ( (lv_joinExpr_3_0= ruleFullExpression ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1077:2: ( (lv_join_0_0= ruleJoinType ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1078:1: (lv_join_0_0= ruleJoinType )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1078:1: (lv_join_0_0= ruleJoinType )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1079:3: lv_join_0_0= ruleJoinType
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinJoinTypeParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleJoinType_in_ruleFromTableJoin2209);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1095:2: ( (lv_onTable_1_0= ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1096:1: (lv_onTable_1_0= ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1096:1: (lv_onTable_1_0= ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1097:3: lv_onTable_1_0= ruleTableOrAlias
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getOnTableTableOrAliasParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTableJoin2230);
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

            otherlv_2=(Token)match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_ruleFromTableJoin2243); 

                	newLeafNode(otherlv_2, grammarAccess.getFromTableJoinAccess().getONKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1118:1: ( (lv_joinExpr_3_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1119:1: (lv_joinExpr_3_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1119:1: (lv_joinExpr_3_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1120:3: lv_joinExpr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleFromTableJoin2263);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1144:1: entryRuleTableOrAlias returns [EObject current=null] : iv_ruleTableOrAlias= ruleTableOrAlias EOF ;
    public final EObject entryRuleTableOrAlias() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableOrAlias = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1145:2: (iv_ruleTableOrAlias= ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1146:2: iv_ruleTableOrAlias= ruleTableOrAlias EOF
            {
             newCompositeNode(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias2298);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;

             current =iv_ruleTableOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias2308); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1153:1: ruleTableOrAlias returns [EObject current=null] : ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1156:28: ( ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1157:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1157:1: ( ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1157:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) ) ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )? ( (lv_alias_4_0= KEYWORD_19 ) )? ( (lv_tblAlias_5_0= ruleDbObjectName ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1157:2: ( ( (lv_tfull_0_0= ruleTableFull ) ) | ( (lv_sq_1_0= ruleSubQueryOperand ) ) )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=RULE_STRING && LA30_0<=RULE_ID)) ) {
                alt30=1;
            }
            else if ( (LA30_0==KEYWORD_1) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1157:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1157:3: ( (lv_tfull_0_0= ruleTableFull ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1158:1: (lv_tfull_0_0= ruleTableFull )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1158:1: (lv_tfull_0_0= ruleTableFull )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1159:3: lv_tfull_0_0= ruleTableFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias2355);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1176:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1176:6: ( (lv_sq_1_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1177:1: (lv_sq_1_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1177:1: (lv_sq_1_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1178:3: lv_sq_1_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getSqSubQueryOperandParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleTableOrAlias2382);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1194:3: ( ( (lv_pivot_2_0= rulePivotTable ) ) | ( (lv_unpivot_3_0= ruleUnpivotTable ) ) )?
            int alt31=3;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==KEYWORD_64) ) {
                alt31=1;
            }
            else if ( (LA31_0==KEYWORD_82) ) {
                alt31=2;
            }
            switch (alt31) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1194:4: ( (lv_pivot_2_0= rulePivotTable ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1194:4: ( (lv_pivot_2_0= rulePivotTable ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1195:1: (lv_pivot_2_0= rulePivotTable )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1195:1: (lv_pivot_2_0= rulePivotTable )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1196:3: lv_pivot_2_0= rulePivotTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getPivotPivotTableParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulePivotTable_in_ruleTableOrAlias2405);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1213:6: ( (lv_unpivot_3_0= ruleUnpivotTable ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1213:6: ( (lv_unpivot_3_0= ruleUnpivotTable ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1214:1: (lv_unpivot_3_0= ruleUnpivotTable )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1214:1: (lv_unpivot_3_0= ruleUnpivotTable )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1215:3: lv_unpivot_3_0= ruleUnpivotTable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getUnpivotUnpivotTableParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleUnpivotTable_in_ruleTableOrAlias2432);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1231:4: ( (lv_alias_4_0= KEYWORD_19 ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==KEYWORD_19) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1232:1: (lv_alias_4_0= KEYWORD_19 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1232:1: (lv_alias_4_0= KEYWORD_19 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1233:3: lv_alias_4_0= KEYWORD_19
                    {
                    lv_alias_4_0=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleTableOrAlias2453); 

                            newLeafNode(lv_alias_4_0, grammarAccess.getTableOrAliasAccess().getAliasASKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTableOrAliasRule());
                    	        }
                           		setWithLastConsumed(current, "alias", lv_alias_4_0, "AS");
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1247:3: ( (lv_tblAlias_5_0= ruleDbObjectName ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=RULE_STRING && LA33_0<=RULE_ID)) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1248:1: (lv_tblAlias_5_0= ruleDbObjectName )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1248:1: (lv_tblAlias_5_0= ruleDbObjectName )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1249:3: lv_tblAlias_5_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableOrAlias2486);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1273:1: entryRulePivotTable returns [EObject current=null] : iv_rulePivotTable= rulePivotTable EOF ;
    public final EObject entryRulePivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1274:2: (iv_rulePivotTable= rulePivotTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1275:2: iv_rulePivotTable= rulePivotTable EOF
            {
             newCompositeNode(grammarAccess.getPivotTableRule()); 
            pushFollow(FOLLOW_rulePivotTable_in_entryRulePivotTable2522);
            iv_rulePivotTable=rulePivotTable();

            state._fsp--;

             current =iv_rulePivotTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotTable2532); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1282:1: rulePivotTable returns [EObject current=null] : (otherlv_0= KEYWORD_64 (otherlv_1= KEYWORD_34 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1285:28: ( (otherlv_0= KEYWORD_64 (otherlv_1= KEYWORD_34 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1286:1: (otherlv_0= KEYWORD_64 (otherlv_1= KEYWORD_34 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1286:1: (otherlv_0= KEYWORD_64 (otherlv_1= KEYWORD_34 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1287:2: otherlv_0= KEYWORD_64 (otherlv_1= KEYWORD_34 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_64,FOLLOW_KEYWORD_64_in_rulePivotTable2570); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotTableAccess().getPIVOTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1291:1: (otherlv_1= KEYWORD_34 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==KEYWORD_34) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1292:2: otherlv_1= KEYWORD_34
                    {
                    otherlv_1=(Token)match(input,KEYWORD_34,FOLLOW_KEYWORD_34_in_rulePivotTable2583); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotTableAccess().getXMLKeyword_1());
                        

                    }
                    break;

            }

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotTable2597); 

                	newLeafNode(otherlv_2, grammarAccess.getPivotTableAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1301:1: ( (lv_pfun_3_0= rulePivotFunctions ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1302:1: (lv_pfun_3_0= rulePivotFunctions )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1302:1: (lv_pfun_3_0= rulePivotFunctions )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1303:3: lv_pfun_3_0= rulePivotFunctions
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPfunPivotFunctionsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_rulePivotFunctions_in_rulePivotTable2617);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1319:2: ( (lv_pfor_4_0= rulePivotForClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1320:1: (lv_pfor_4_0= rulePivotForClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1320:1: (lv_pfor_4_0= rulePivotForClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1321:3: lv_pfor_4_0= rulePivotForClause
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_rulePivotForClause_in_rulePivotTable2638);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1337:2: ( (lv_pin_5_0= rulePivotInClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1338:1: (lv_pin_5_0= rulePivotInClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1338:1: (lv_pin_5_0= rulePivotInClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1339:3: lv_pin_5_0= rulePivotInClause
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPinPivotInClauseParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_rulePivotInClause_in_rulePivotTable2659);
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

            otherlv_6=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotTable2672); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1368:1: entryRulePivotFunctions returns [EObject current=null] : iv_rulePivotFunctions= rulePivotFunctions EOF ;
    public final EObject entryRulePivotFunctions() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotFunctions = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1369:2: (iv_rulePivotFunctions= rulePivotFunctions EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1370:2: iv_rulePivotFunctions= rulePivotFunctions EOF
            {
             newCompositeNode(grammarAccess.getPivotFunctionsRule()); 
            pushFollow(FOLLOW_rulePivotFunctions_in_entryRulePivotFunctions2706);
            iv_rulePivotFunctions=rulePivotFunctions();

            state._fsp--;

             current =iv_rulePivotFunctions; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotFunctions2716); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1377:1: rulePivotFunctions returns [EObject current=null] : ( (lv_abc_0_0= RULE_ID ) ) ;
    public final EObject rulePivotFunctions() throws RecognitionException {
        EObject current = null;

        Token lv_abc_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1380:28: ( ( (lv_abc_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1381:1: ( (lv_abc_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1381:1: ( (lv_abc_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1382:1: (lv_abc_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1382:1: (lv_abc_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1383:3: lv_abc_0_0= RULE_ID
            {
            lv_abc_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_rulePivotFunctions2757); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1409:1: entryRulePivotInClause returns [EObject current=null] : iv_rulePivotInClause= rulePivotInClause EOF ;
    public final EObject entryRulePivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotInClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1410:2: (iv_rulePivotInClause= rulePivotInClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1411:2: iv_rulePivotInClause= rulePivotInClause EOF
            {
             newCompositeNode(grammarAccess.getPivotInClauseRule()); 
            pushFollow(FOLLOW_rulePivotInClause_in_entryRulePivotInClause2798);
            iv_rulePivotInClause=rulePivotInClause();

            state._fsp--;

             current =iv_rulePivotInClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotInClause2808); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1418:1: rulePivotInClause returns [EObject current=null] : (otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1421:28: ( (otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1422:1: (otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1422:1: (otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1423:2: otherlv_0= KEYWORD_20 otherlv_1= KEYWORD_1 ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) ) otherlv_5= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_rulePivotInClause2846); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotInClauseAccess().getINKeyword_0());
                
            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotInClause2858); 

                	newLeafNode(otherlv_1, grammarAccess.getPivotInClauseAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1432:1: ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) )
            int alt35=3;
            switch ( input.LA(1) ) {
            case KEYWORD_1:
                {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==KEYWORD_74) ) {
                    alt35=1;
                }
                else if ( ((LA35_1>=RULE_STRING && LA35_1<=RULE_ID)) ) {
                    alt35=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
            case RULE_ID:
                {
                alt35=2;
                }
                break;
            case KEYWORD_28:
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1432:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1432:2: ( (lv_sq_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1433:1: (lv_sq_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1433:1: (lv_sq_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1434:3: lv_sq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getSqSubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_rulePivotInClause2879);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1451:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1451:6: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1452:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1452:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1453:3: lv_args_3_0= ruleUnpivotInClauseArgs
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_rulePivotInClause2906);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1470:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1470:6: ( (lv_pinany_4_0= rulePivotInClauseAny ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1471:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1471:1: (lv_pinany_4_0= rulePivotInClauseAny )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1472:3: lv_pinany_4_0= rulePivotInClauseAny
                    {
                     
                    	        newCompositeNode(grammarAccess.getPivotInClauseAccess().getPinanyPivotInClauseAnyParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulePivotInClauseAny_in_rulePivotInClause2933);
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

            otherlv_5=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotInClause2947); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1501:1: entryRulePivotInClauseAny returns [String current=null] : iv_rulePivotInClauseAny= rulePivotInClauseAny EOF ;
    public final String entryRulePivotInClauseAny() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePivotInClauseAny = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1502:1: (iv_rulePivotInClauseAny= rulePivotInClauseAny EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1503:2: iv_rulePivotInClauseAny= rulePivotInClauseAny EOF
            {
             newCompositeNode(grammarAccess.getPivotInClauseAnyRule()); 
            pushFollow(FOLLOW_rulePivotInClauseAny_in_entryRulePivotInClauseAny2982);
            iv_rulePivotInClauseAny=rulePivotInClauseAny();

            state._fsp--;

             current =iv_rulePivotInClauseAny.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotInClauseAny2993); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1510:1: rulePivotInClauseAny returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )? ) ;
    public final AntlrDatatypeRuleToken rulePivotInClauseAny() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1514:6: ( (kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1515:1: (kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1515:1: (kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1516:2: kw= KEYWORD_28 (kw= KEYWORD_4 kw= KEYWORD_28 )?
            {
            kw=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rulePivotInClauseAny3031); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getANYKeyword_0()); 
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1521:1: (kw= KEYWORD_4 kw= KEYWORD_28 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==KEYWORD_4) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1522:2: kw= KEYWORD_4 kw= KEYWORD_28
                    {
                    kw=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rulePivotInClauseAny3045); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getCommaKeyword_1_0()); 
                        
                    kw=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rulePivotInClauseAny3058); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1541:1: entryRuleUnpivotTable returns [EObject current=null] : iv_ruleUnpivotTable= ruleUnpivotTable EOF ;
    public final EObject entryRuleUnpivotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotTable = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1542:2: (iv_ruleUnpivotTable= ruleUnpivotTable EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1543:2: iv_ruleUnpivotTable= ruleUnpivotTable EOF
            {
             newCompositeNode(grammarAccess.getUnpivotTableRule()); 
            pushFollow(FOLLOW_ruleUnpivotTable_in_entryRuleUnpivotTable3099);
            iv_ruleUnpivotTable=ruleUnpivotTable();

            state._fsp--;

             current =iv_ruleUnpivotTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotTable3109); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1550:1: ruleUnpivotTable returns [EObject current=null] : (otherlv_0= KEYWORD_82 ( (otherlv_1= KEYWORD_78 | otherlv_2= KEYWORD_76 ) otherlv_3= KEYWORD_62 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1553:28: ( (otherlv_0= KEYWORD_82 ( (otherlv_1= KEYWORD_78 | otherlv_2= KEYWORD_76 ) otherlv_3= KEYWORD_62 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1554:1: (otherlv_0= KEYWORD_82 ( (otherlv_1= KEYWORD_78 | otherlv_2= KEYWORD_76 ) otherlv_3= KEYWORD_62 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1554:1: (otherlv_0= KEYWORD_82 ( (otherlv_1= KEYWORD_78 | otherlv_2= KEYWORD_76 ) otherlv_3= KEYWORD_62 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1555:2: otherlv_0= KEYWORD_82 ( (otherlv_1= KEYWORD_78 | otherlv_2= KEYWORD_76 ) otherlv_3= KEYWORD_62 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_82,FOLLOW_KEYWORD_82_in_ruleUnpivotTable3147); 

                	newLeafNode(otherlv_0, grammarAccess.getUnpivotTableAccess().getUNPIVOTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1559:1: ( (otherlv_1= KEYWORD_78 | otherlv_2= KEYWORD_76 ) otherlv_3= KEYWORD_62 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==KEYWORD_76||LA38_0==KEYWORD_78) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1559:2: (otherlv_1= KEYWORD_78 | otherlv_2= KEYWORD_76 ) otherlv_3= KEYWORD_62
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1559:2: (otherlv_1= KEYWORD_78 | otherlv_2= KEYWORD_76 )
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==KEYWORD_78) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==KEYWORD_76) ) {
                        alt37=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 0, input);

                        throw nvae;
                    }
                    switch (alt37) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1560:2: otherlv_1= KEYWORD_78
                            {
                            otherlv_1=(Token)match(input,KEYWORD_78,FOLLOW_KEYWORD_78_in_ruleUnpivotTable3161); 

                                	newLeafNode(otherlv_1, grammarAccess.getUnpivotTableAccess().getINCLUDEKeyword_1_0_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1566:2: otherlv_2= KEYWORD_76
                            {
                            otherlv_2=(Token)match(input,KEYWORD_76,FOLLOW_KEYWORD_76_in_ruleUnpivotTable3179); 

                                	newLeafNode(otherlv_2, grammarAccess.getUnpivotTableAccess().getEXCLUDEKeyword_1_0_1());
                                

                            }
                            break;

                    }

                    otherlv_3=(Token)match(input,KEYWORD_62,FOLLOW_KEYWORD_62_in_ruleUnpivotTable3192); 

                        	newLeafNode(otherlv_3, grammarAccess.getUnpivotTableAccess().getNULLSKeyword_1_1());
                        

                    }
                    break;

            }

            otherlv_4=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleUnpivotTable3206); 

                	newLeafNode(otherlv_4, grammarAccess.getUnpivotTableAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1580:1: ( (lv_pcols_5_0= rulePivotColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1581:1: (lv_pcols_5_0= rulePivotColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1581:1: (lv_pcols_5_0= rulePivotColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1582:3: lv_pcols_5_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPcolsPivotColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotTable3226);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1598:2: ( (lv_pfor_6_0= rulePivotForClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1599:1: (lv_pfor_6_0= rulePivotForClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1599:1: (lv_pfor_6_0= rulePivotForClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1600:3: lv_pfor_6_0= rulePivotForClause
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPforPivotForClauseParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_rulePivotForClause_in_ruleUnpivotTable3247);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1616:2: ( (lv_inop_7_0= ruleUnpivotInClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1617:1: (lv_inop_7_0= ruleUnpivotInClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1617:1: (lv_inop_7_0= ruleUnpivotInClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1618:3: lv_inop_7_0= ruleUnpivotInClause
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getInopUnpivotInClauseParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleUnpivotInClause_in_ruleUnpivotTable3268);
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

            otherlv_8=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleUnpivotTable3281); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1647:1: entryRuleUnpivotInClause returns [EObject current=null] : iv_ruleUnpivotInClause= ruleUnpivotInClause EOF ;
    public final EObject entryRuleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1648:2: (iv_ruleUnpivotInClause= ruleUnpivotInClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1649:2: iv_ruleUnpivotInClause= ruleUnpivotInClause EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseRule()); 
            pushFollow(FOLLOW_ruleUnpivotInClause_in_entryRuleUnpivotInClause3315);
            iv_ruleUnpivotInClause=ruleUnpivotInClause();

            state._fsp--;

             current =iv_ruleUnpivotInClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClause3325); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1656:1: ruleUnpivotInClause returns [EObject current=null] : ( () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2 ) ;
    public final EObject ruleUnpivotInClause() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1659:28: ( ( () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1660:1: ( () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1660:1: ( () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1660:2: () ( (lv_op_1_0= KEYWORD_20 ) ) otherlv_2= KEYWORD_1 ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) otherlv_4= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1660:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1661:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getUnpivotInClauseAccess().getUnipivotInClauseAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1666:2: ( (lv_op_1_0= KEYWORD_20 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1667:1: (lv_op_1_0= KEYWORD_20 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1667:1: (lv_op_1_0= KEYWORD_20 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1668:3: lv_op_1_0= KEYWORD_20
            {
            lv_op_1_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleUnpivotInClause3378); 

                    newLeafNode(lv_op_1_0, grammarAccess.getUnpivotInClauseAccess().getOpINKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUnpivotInClauseRule());
            	        }
                   		setWithLastConsumed(current, "op", lv_op_1_0, "IN");
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleUnpivotInClause3402); 

                	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1687:1: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1688:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1688:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1689:3: lv_args_3_0= ruleUnpivotInClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_ruleUnpivotInClause3422);
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

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleUnpivotInClause3435); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1718:1: entryRuleUnpivotInClauseArgs returns [EObject current=null] : iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF ;
    public final EObject entryRuleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArgs = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1719:2: (iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1720:2: iv_ruleUnpivotInClauseArgs= ruleUnpivotInClauseArgs EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseArgsRule()); 
            pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_entryRuleUnpivotInClauseArgs3469);
            iv_ruleUnpivotInClauseArgs=ruleUnpivotInClauseArgs();

            state._fsp--;

             current =iv_ruleUnpivotInClauseArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClauseArgs3479); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1727:1: ruleUnpivotInClauseArgs returns [EObject current=null] : (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) ;
    public final EObject ruleUnpivotInClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_UnpivotInClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1730:28: ( (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1731:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1731:1: (this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1732:5: this_UnpivotInClauseArg_0= ruleUnpivotInClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getUnpivotInClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3526);
            this_UnpivotInClauseArg_0=ruleUnpivotInClauseArg();

            state._fsp--;


                    current = this_UnpivotInClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1740:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+ )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==KEYWORD_4) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1740:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1740:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1741:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getUnpivotInClauseArgsAccess().getUicargsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1746:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) ) )+
                    int cnt39=0;
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==KEYWORD_4) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1747:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleUnpivotInClauseArgs3549); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1751:1: ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1752:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1752:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1753:3: lv_entries_3_0= ruleUnpivotInClauseArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getEntriesUnpivotInClauseArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3569);
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
                    	    if ( cnt39 >= 1 ) break loop39;
                                EarlyExitException eee =
                                    new EarlyExitException(39, input);
                                throw eee;
                        }
                        cnt39++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1777:1: entryRuleUnpivotInClauseArg returns [EObject current=null] : iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF ;
    public final EObject entryRuleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnpivotInClauseArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1778:2: (iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1779:2: iv_ruleUnpivotInClauseArg= ruleUnpivotInClauseArg EOF
            {
             newCompositeNode(grammarAccess.getUnpivotInClauseArgRule()); 
            pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_entryRuleUnpivotInClauseArg3608);
            iv_ruleUnpivotInClauseArg=ruleUnpivotInClauseArg();

            state._fsp--;

             current =iv_ruleUnpivotInClauseArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClauseArg3618); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1786:1: ruleUnpivotInClauseArg returns [EObject current=null] : ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) ;
    public final EObject ruleUnpivotInClauseArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_pcols_0_0 = null;

        EObject lv_cfuls_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1789:28: ( ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1790:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1790:1: ( ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1790:2: ( (lv_pcols_0_0= rulePivotColumns ) ) (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1790:2: ( (lv_pcols_0_0= rulePivotColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1791:1: (lv_pcols_0_0= rulePivotColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1791:1: (lv_pcols_0_0= rulePivotColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1792:3: lv_pcols_0_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getPcolsPivotColumnsParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3664);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1808:2: (otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==KEYWORD_19) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1809:2: otherlv_1= KEYWORD_19 ( (lv_cfuls_2_0= rulePivotColumns ) )
                    {
                    otherlv_1=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleUnpivotInClauseArg3678); 

                        	newLeafNode(otherlv_1, grammarAccess.getUnpivotInClauseArgAccess().getASKeyword_1_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1813:1: ( (lv_cfuls_2_0= rulePivotColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1814:1: (lv_cfuls_2_0= rulePivotColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1814:1: (lv_cfuls_2_0= rulePivotColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1815:3: lv_cfuls_2_0= rulePivotColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getCfulsPivotColumnsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3698);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1839:1: entryRulePivotForClause returns [EObject current=null] : iv_rulePivotForClause= rulePivotForClause EOF ;
    public final EObject entryRulePivotForClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotForClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1840:2: (iv_rulePivotForClause= rulePivotForClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1841:2: iv_rulePivotForClause= rulePivotForClause EOF
            {
             newCompositeNode(grammarAccess.getPivotForClauseRule()); 
            pushFollow(FOLLOW_rulePivotForClause_in_entryRulePivotForClause3735);
            iv_rulePivotForClause=rulePivotForClause();

            state._fsp--;

             current =iv_rulePivotForClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotForClause3745); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1848:1: rulePivotForClause returns [EObject current=null] : (otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) ) ;
    public final EObject rulePivotForClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ColumnFull_1 = null;

        EObject this_Columns_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1851:28: ( (otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1852:1: (otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1852:1: (otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1853:2: otherlv_0= KEYWORD_31 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_31,FOLLOW_KEYWORD_31_in_rulePivotForClause3783); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotForClauseAccess().getFORKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1857:1: (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( ((LA42_0>=RULE_STRING && LA42_0<=RULE_ID)) ) {
                alt42=1;
            }
            else if ( (LA42_0==KEYWORD_1) ) {
                alt42=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1858:5: this_ColumnFull_1= ruleColumnFull
                    {
                     
                            newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnFullParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_rulePivotForClause3805);
                    this_ColumnFull_1=ruleColumnFull();

                    state._fsp--;


                            current = this_ColumnFull_1;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1867:6: (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1867:6: (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1868:2: otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2
                    {
                    otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotForClause3824); 

                        	newLeafNode(otherlv_2, grammarAccess.getPivotForClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_ruleColumns_in_rulePivotForClause3845);
                    this_Columns_3=ruleColumns();

                    state._fsp--;


                            current = this_Columns_3;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotForClause3857); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1894:1: entryRulePivotColumns returns [EObject current=null] : iv_rulePivotColumns= rulePivotColumns EOF ;
    public final EObject entryRulePivotColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1895:2: (iv_rulePivotColumns= rulePivotColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1896:2: iv_rulePivotColumns= rulePivotColumns EOF
            {
             newCompositeNode(grammarAccess.getPivotColumnsRule()); 
            pushFollow(FOLLOW_rulePivotColumns_in_entryRulePivotColumns3893);
            iv_rulePivotColumns=rulePivotColumns();

            state._fsp--;

             current =iv_rulePivotColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotColumns3903); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1903:1: rulePivotColumns returns [EObject current=null] : (this_PivotCol_0= rulePivotCol | (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 ) ) ;
    public final EObject rulePivotColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_PivotCol_0 = null;

        EObject this_PivotCols_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1906:28: ( (this_PivotCol_0= rulePivotCol | (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1907:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1907:1: (this_PivotCol_0= rulePivotCol | (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( ((LA43_0>=RULE_STRING && LA43_0<=RULE_ID)) ) {
                alt43=1;
            }
            else if ( (LA43_0==KEYWORD_1) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1908:5: this_PivotCol_0= rulePivotCol
                    {
                     
                            newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_rulePivotCol_in_rulePivotColumns3950);
                    this_PivotCol_0=rulePivotCol();

                    state._fsp--;


                            current = this_PivotCol_0;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1917:6: (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1917:6: (otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1918:2: otherlv_1= KEYWORD_1 this_PivotCols_2= rulePivotCols otherlv_3= KEYWORD_2
                    {
                    otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotColumns3969); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotColumnsAccess().getLeftParenthesisKeyword_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColsParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_rulePivotCols_in_rulePivotColumns3990);
                    this_PivotCols_2=rulePivotCols();

                    state._fsp--;


                            current = this_PivotCols_2;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotColumns4002); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1944:1: entryRulePivotCols returns [EObject current=null] : iv_rulePivotCols= rulePivotCols EOF ;
    public final EObject entryRulePivotCols() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCols = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1945:2: (iv_rulePivotCols= rulePivotCols EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1946:2: iv_rulePivotCols= rulePivotCols EOF
            {
             newCompositeNode(grammarAccess.getPivotColsRule()); 
            pushFollow(FOLLOW_rulePivotCols_in_entryRulePivotCols4037);
            iv_rulePivotCols=rulePivotCols();

            state._fsp--;

             current =iv_rulePivotCols; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotCols4047); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1953:1: rulePivotCols returns [EObject current=null] : (this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) ;
    public final EObject rulePivotCols() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PivotCol_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1956:28: ( (this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1957:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1957:1: (this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1958:5: this_PivotCol_0= rulePivotCol ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getPivotColsAccess().getPivotColParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePivotCol_in_rulePivotCols4094);
            this_PivotCol_0=rulePivotCol();

            state._fsp--;


                    current = this_PivotCol_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1966:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+ )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==KEYWORD_4) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1966:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1966:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1967:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getPivotColsAccess().getPvcsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1972:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) ) )+
                    int cnt44=0;
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==KEYWORD_4) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1973:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= rulePivotCol ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rulePivotCols4117); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1977:1: ( (lv_entries_3_0= rulePivotCol ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1978:1: (lv_entries_3_0= rulePivotCol )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1978:1: (lv_entries_3_0= rulePivotCol )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1979:3: lv_entries_3_0= rulePivotCol
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColsAccess().getEntriesPivotColParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePivotCol_in_rulePivotCols4137);
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
    // $ANTLR end "rulePivotCols"


    // $ANTLR start "entryRulePivotCol"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2003:1: entryRulePivotCol returns [EObject current=null] : iv_rulePivotCol= rulePivotCol EOF ;
    public final EObject entryRulePivotCol() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePivotCol = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2004:2: (iv_rulePivotCol= rulePivotCol EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2005:2: iv_rulePivotCol= rulePivotCol EOF
            {
             newCompositeNode(grammarAccess.getPivotColRule()); 
            pushFollow(FOLLOW_rulePivotCol_in_entryRulePivotCol4176);
            iv_rulePivotCol=rulePivotCol();

            state._fsp--;

             current =iv_rulePivotCol; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotCol4186); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2012:1: rulePivotCol returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject rulePivotCol() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2015:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2016:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2016:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2017:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getPivotColAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_rulePivotCol4233);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2025:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==KEYWORD_6) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2025:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2025:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2026:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getPivotColAccess().getPcolsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2031:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2032:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rulePivotCol4256); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2036:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2037:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2037:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2038:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_rulePivotCol4276);
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
    // $ANTLR end "rulePivotCol"


    // $ANTLR start "entryRuleTableFull"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2062:1: entryRuleTableFull returns [EObject current=null] : iv_ruleTableFull= ruleTableFull EOF ;
    public final EObject entryRuleTableFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTableFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2063:2: (iv_ruleTableFull= ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2064:2: iv_ruleTableFull= ruleTableFull EOF
            {
             newCompositeNode(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull4315);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;

             current =iv_ruleTableFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull4325); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2071:1: ruleTableFull returns [EObject current=null] : (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) ;
    public final EObject ruleTableFull() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_DbObjectName_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2074:28: ( (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2075:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2075:1: (this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2076:5: this_DbObjectName_0= ruleDbObjectName ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull4372);
            this_DbObjectName_0=ruleDbObjectName();

            state._fsp--;


                    current = this_DbObjectName_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2084:1: ( () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+ )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==KEYWORD_6) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2084:2: () (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2084:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2085:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2090:2: (otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) ) )+
                    int cnt48=0;
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==KEYWORD_6) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2091:2: otherlv_2= KEYWORD_6 ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleTableFull4395); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2095:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2096:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2096:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2097:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull4415);
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
    // $ANTLR end "ruleTableFull"


    // $ANTLR start "entryRuleDbObjectNameAll"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2121:1: entryRuleDbObjectNameAll returns [EObject current=null] : iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF ;
    public final EObject entryRuleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectNameAll = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2122:2: (iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2123:2: iv_ruleDbObjectNameAll= ruleDbObjectNameAll EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameAllRule()); 
            pushFollow(FOLLOW_ruleDbObjectNameAll_in_entryRuleDbObjectNameAll4454);
            iv_ruleDbObjectNameAll=ruleDbObjectNameAll();

            state._fsp--;

             current =iv_ruleDbObjectNameAll; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectNameAll4464); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2130:1: ruleDbObjectNameAll returns [EObject current=null] : ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR ) ;
    public final EObject ruleDbObjectNameAll() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_STAR_2=null;
        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2133:28: ( ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2134:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2134:1: ( ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2134:2: ( (lv_dbname_0_0= ruleDBID ) ) otherlv_1= KEYWORD_6 this_STAR_2= RULE_STAR
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2134:2: ( (lv_dbname_0_0= ruleDBID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2135:1: (lv_dbname_0_0= ruleDBID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2135:1: (lv_dbname_0_0= ruleDBID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2136:3: lv_dbname_0_0= ruleDBID
            {
             
            	        newCompositeNode(grammarAccess.getDbObjectNameAllAccess().getDbnameDBIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleDBID_in_ruleDbObjectNameAll4510);
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

            otherlv_1=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleDbObjectNameAll4523); 

                	newLeafNode(otherlv_1, grammarAccess.getDbObjectNameAllAccess().getFullStopKeyword_1());
                
            this_STAR_2=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleDbObjectNameAll4533); 
             
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2169:1: entryRuleDbObjectName returns [EObject current=null] : iv_ruleDbObjectName= ruleDbObjectName EOF ;
    public final EObject entryRuleDbObjectName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDbObjectName = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2170:2: (iv_ruleDbObjectName= ruleDbObjectName EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2171:2: iv_ruleDbObjectName= ruleDbObjectName EOF
            {
             newCompositeNode(grammarAccess.getDbObjectNameRule()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName4567);
            iv_ruleDbObjectName=ruleDbObjectName();

            state._fsp--;

             current =iv_ruleDbObjectName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectName4577); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2178:1: ruleDbObjectName returns [EObject current=null] : ( (lv_dbname_0_0= ruleDBID ) ) ;
    public final EObject ruleDbObjectName() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_dbname_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2181:28: ( ( (lv_dbname_0_0= ruleDBID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2182:1: ( (lv_dbname_0_0= ruleDBID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2182:1: ( (lv_dbname_0_0= ruleDBID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2183:1: (lv_dbname_0_0= ruleDBID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2183:1: (lv_dbname_0_0= ruleDBID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2184:3: lv_dbname_0_0= ruleDBID
            {
             
            	        newCompositeNode(grammarAccess.getDbObjectNameAccess().getDbnameDBIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleDBID_in_ruleDbObjectName4622);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2208:1: entryRuleOrderByColumns returns [EObject current=null] : iv_ruleOrderByColumns= ruleOrderByColumns EOF ;
    public final EObject entryRuleOrderByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2209:2: (iv_ruleOrderByColumns= ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2210:2: iv_ruleOrderByColumns= ruleOrderByColumns EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns4656);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;

             current =iv_ruleOrderByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns4666); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2217:1: ruleOrderByColumns returns [EObject current=null] : (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) ;
    public final EObject ruleOrderByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2220:28: ( (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2221:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2221:1: (this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2222:5: this_OrderByColumnFull_0= ruleOrderByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4713);
            this_OrderByColumnFull_0=ruleOrderByColumnFull();

            state._fsp--;


                    current = this_OrderByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2230:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+ )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==KEYWORD_4) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2230:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2230:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2231:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2236:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) ) )+
                    int cnt50=0;
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==KEYWORD_4) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2237:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByColumns4736); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2241:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2242:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2242:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2243:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4756);
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
    // $ANTLR end "ruleOrderByColumns"


    // $ANTLR start "entryRuleOrderByColumnFull"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2267:1: entryRuleOrderByColumnFull returns [EObject current=null] : iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF ;
    public final EObject entryRuleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2268:2: (iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2269:2: iv_ruleOrderByColumnFull= ruleOrderByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull4795);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;

             current =iv_ruleOrderByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull4805); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2276:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 ) ) )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_colOrderInt_1_0=null;
        Token lv_direction_2_1=null;
        Token lv_direction_2_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2279:28: ( ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_INT ) ) )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=RULE_STRING && LA52_0<=RULE_ID)) ) {
                alt52=1;
            }
            else if ( (LA52_0==RULE_INT) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:3: ( (lv_colOrder_0_0= ruleColumnFull ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2281:1: (lv_colOrder_0_0= ruleColumnFull )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2281:1: (lv_colOrder_0_0= ruleColumnFull )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2282:3: lv_colOrder_0_0= ruleColumnFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull4852);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2299:6: ( (lv_colOrderInt_1_0= RULE_INT ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2299:6: ( (lv_colOrderInt_1_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2300:1: (lv_colOrderInt_1_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2300:1: (lv_colOrderInt_1_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2301:3: lv_colOrderInt_1_0= RULE_INT
                    {
                    lv_colOrderInt_1_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOrderByColumnFull4875); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2317:3: ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==KEYWORD_36||LA54_0==KEYWORD_29) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2318:1: ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2318:1: ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2319:1: (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2319:1: (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_36 )
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==KEYWORD_29) ) {
                        alt53=1;
                    }
                    else if ( (LA53_0==KEYWORD_36) ) {
                        alt53=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 0, input);

                        throw nvae;
                    }
                    switch (alt53) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2320:3: lv_direction_2_1= KEYWORD_29
                            {
                            lv_direction_2_1=(Token)match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_ruleOrderByColumnFull4902); 

                                    newLeafNode(lv_direction_2_1, grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                            	        }
                                   		setWithLastConsumed(current, "direction", lv_direction_2_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2333:8: lv_direction_2_2= KEYWORD_36
                            {
                            lv_direction_2_2=(Token)match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_ruleOrderByColumnFull4930); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2357:1: entryRuleGroupByColumns returns [EObject current=null] : iv_ruleGroupByColumns= ruleGroupByColumns EOF ;
    public final EObject entryRuleGroupByColumns() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumns = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2358:2: (iv_ruleGroupByColumns= ruleGroupByColumns EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2359:2: iv_ruleGroupByColumns= ruleGroupByColumns EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnsRule()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns4980);
            iv_ruleGroupByColumns=ruleGroupByColumns();

            state._fsp--;

             current =iv_ruleGroupByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumns4990); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2366:1: ruleGroupByColumns returns [EObject current=null] : (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) ;
    public final EObject ruleGroupByColumns() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_GroupByColumnFull_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2369:28: ( (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2370:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2370:1: (this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2371:5: this_GroupByColumnFull_0= ruleGroupByColumnFull ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns5037);
            this_GroupByColumnFull_0=ruleGroupByColumnFull();

            state._fsp--;


                    current = this_GroupByColumnFull_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2379:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+ )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==KEYWORD_4) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2379:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2379:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2380:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2385:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) ) )+
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2386:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleGroupByColumns5060); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2390:1: ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2391:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2391:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2392:3: lv_entries_3_0= ruleGroupByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns5080);
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
    // $ANTLR end "ruleGroupByColumns"


    // $ANTLR start "entryRuleGroupByColumnFull"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2416:1: entryRuleGroupByColumnFull returns [EObject current=null] : iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF ;
    public final EObject entryRuleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupByColumnFull = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2417:2: (iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2418:2: iv_ruleGroupByColumnFull= ruleGroupByColumnFull EOF
            {
             newCompositeNode(grammarAccess.getGroupByColumnFullRule()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull5119);
            iv_ruleGroupByColumnFull=ruleGroupByColumnFull();

            state._fsp--;

             current =iv_ruleGroupByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumnFull5129); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2425:1: ruleGroupByColumnFull returns [EObject current=null] : ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) ) ;
    public final EObject ruleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        EObject lv_colGrBy_0_0 = null;

        EObject lv_gbFunction_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2428:28: ( ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) )
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==RULE_ID) ) {
                int LA57_1 = input.LA(2);

                if ( (LA57_1==KEYWORD_1) ) {
                    alt57=2;
                }
                else if ( (LA57_1==EOF||LA57_1==KEYWORD_98||LA57_1==KEYWORD_92||LA57_1==KEYWORD_88||LA57_1==KEYWORD_69||LA57_1==KEYWORD_71||LA57_1==KEYWORD_73||(LA57_1>=KEYWORD_59 && LA57_1<=KEYWORD_60)||LA57_1==KEYWORD_67||LA57_1==KEYWORD_2||LA57_1==KEYWORD_4||LA57_1==KEYWORD_6) ) {
                    alt57=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 57, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA57_0>=RULE_STRING && LA57_0<=RULE_DBNAME)) ) {
                alt57=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }
            switch (alt57) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2430:1: (lv_colGrBy_0_0= ruleColumnFull )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2430:1: (lv_colGrBy_0_0= ruleColumnFull )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2431:3: lv_colGrBy_0_0= ruleColumnFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getColGrByColumnFullParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleGroupByColumnFull5175);
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
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2448:6: ( (lv_gbFunction_1_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2448:6: ( (lv_gbFunction_1_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2449:1: (lv_gbFunction_1_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2449:1: (lv_gbFunction_1_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2450:3: lv_gbFunction_1_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getGbFunctionOperandFunctionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleGroupByColumnFull5202);
                    lv_gbFunction_1_0=ruleOperandFunction();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getGroupByColumnFullRule());
                    	        }
                           		set(
                           			current, 
                           			"gbFunction",
                            		lv_gbFunction_1_0, 
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
    // $ANTLR end "ruleGroupByColumnFull"


    // $ANTLR start "entryRuleFullExpression"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2474:1: entryRuleFullExpression returns [EObject current=null] : iv_ruleFullExpression= ruleFullExpression EOF ;
    public final EObject entryRuleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2475:2: (iv_ruleFullExpression= ruleFullExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2476:2: iv_ruleFullExpression= ruleFullExpression EOF
            {
             newCompositeNode(grammarAccess.getFullExpressionRule()); 
            pushFollow(FOLLOW_ruleFullExpression_in_entryRuleFullExpression5237);
            iv_ruleFullExpression=ruleFullExpression();

            state._fsp--;

             current =iv_ruleFullExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullExpression5247); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2483:1: ruleFullExpression returns [EObject current=null] : (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) ;
    public final EObject ruleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExpressionFragment_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2486:28: ( (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2487:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2487:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2488:5: this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleExpressionFragment_in_ruleFullExpression5294);
            this_ExpressionFragment_0=ruleExpressionFragment();

            state._fsp--;


                    current = this_ExpressionFragment_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2496:1: ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==KEYWORD_27||LA59_0==KEYWORD_22||LA59_0==RULE_JRNPARAM) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2496:2: () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2496:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2497:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2502:2: ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    int cnt58=0;
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==KEYWORD_27||LA58_0==KEYWORD_22||LA58_0==RULE_JRNPARAM) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2503:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2503:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2504:3: lv_entries_2_0= ruleExpressionFragmentSecond
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression5324);
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
                    	    if ( cnt58 >= 1 ) break loop58;
                                EarlyExitException eee =
                                    new EarlyExitException(58, input);
                                throw eee;
                        }
                        cnt58++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2528:1: entryRuleExpressionFragmentSecond returns [EObject current=null] : iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF ;
    public final EObject entryRuleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragmentSecond = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2529:2: (iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2530:2: iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentSecondRule()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond5362);
            iv_ruleExpressionFragmentSecond=ruleExpressionFragmentSecond();

            state._fsp--;

             current =iv_ruleExpressionFragmentSecond; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragmentSecond5372); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2537:1: ruleExpressionFragmentSecond returns [EObject current=null] : ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) ;
    public final EObject ruleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        Token lv_c_0_1=null;
        Token lv_c_0_2=null;
        Token lv_notPrm_2_0=null;
        EObject lv_efrag_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2540:28: ( ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2541:1: ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2541:1: ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==KEYWORD_27||LA61_0==KEYWORD_22) ) {
                alt61=1;
            }
            else if ( (LA61_0==RULE_JRNPARAM) ) {
                alt61=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2541:2: ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2541:2: ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2541:3: ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2541:3: ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2542:1: ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2542:1: ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2543:1: (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2543:1: (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 )
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==KEYWORD_27) ) {
                        alt60=1;
                    }
                    else if ( (LA60_0==KEYWORD_22) ) {
                        alt60=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 60, 0, input);

                        throw nvae;
                    }
                    switch (alt60) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2544:3: lv_c_0_1= KEYWORD_27
                            {
                            lv_c_0_1=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleExpressionFragmentSecond5419); 

                                    newLeafNode(lv_c_0_1, grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                            	        }
                                   		setWithLastConsumed(current, "c", lv_c_0_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2557:8: lv_c_0_2= KEYWORD_22
                            {
                            lv_c_0_2=(Token)match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_ruleExpressionFragmentSecond5447); 

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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2573:2: ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2574:1: (lv_efrag_1_0= ruleExpressionFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2574:1: (lv_efrag_1_0= ruleExpressionFragment )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2575:3: lv_efrag_1_0= ruleExpressionFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond5482);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2592:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2592:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2593:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2593:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2594:3: lv_notPrm_2_0= RULE_JRNPARAM
                    {
                    lv_notPrm_2_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragmentSecond5506); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2618:1: entryRuleExpressionFragment returns [EObject current=null] : iv_ruleExpressionFragment= ruleExpressionFragment EOF ;
    public final EObject entryRuleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2619:2: (iv_ruleExpressionFragment= ruleExpressionFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2620:2: iv_ruleExpressionFragment= ruleExpressionFragment EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment5546);
            iv_ruleExpressionFragment=ruleExpressionFragment();

            state._fsp--;

             current =iv_ruleExpressionFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragment5556); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2627:1: ruleExpressionFragment returns [EObject current=null] : ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2630:28: ( ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2631:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2631:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            int alt63=6;
            switch ( input.LA(1) ) {
            case KEYWORD_45:
            case KEYWORD_46:
                {
                alt63=1;
                }
                break;
            case KEYWORD_1:
                {
                int LA63_2 = input.LA(2);

                if ( (LA63_2==KEYWORD_74) ) {
                    alt63=2;
                }
                else if ( (LA63_2==KEYWORD_96||LA63_2==KEYWORD_70||LA63_2==KEYWORD_72||LA63_2==KEYWORD_53||LA63_2==KEYWORD_35||(LA63_2>=KEYWORD_45 && LA63_2<=KEYWORD_46)||LA63_2==KEYWORD_15||LA63_2==KEYWORD_20||LA63_2==KEYWORD_1||(LA63_2>=RULE_JRPARAM && LA63_2<=RULE_JRNPARAM)||(LA63_2>=RULE_INT && LA63_2<=RULE_ID)) ) {
                    alt63=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 2, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_53:
            case KEYWORD_35:
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
                alt63=2;
                }
                break;
            case RULE_JRNPARAM:
                {
                int LA63_4 = input.LA(2);

                if ( (LA63_4==EOF||LA63_4==KEYWORD_102||LA63_4==KEYWORD_98||LA63_4==KEYWORD_92||LA63_4==KEYWORD_85||LA63_4==KEYWORD_88||LA63_4==KEYWORD_80||LA63_4==KEYWORD_69||LA63_4==KEYWORD_71||LA63_4==KEYWORD_73||LA63_4==KEYWORD_54||LA63_4==KEYWORD_57||(LA63_4>=KEYWORD_59 && LA63_4<=KEYWORD_60)||(LA63_4>=KEYWORD_66 && LA63_4<=KEYWORD_68)||(LA63_4>=KEYWORD_39 && LA63_4<=KEYWORD_40)||LA63_4==KEYWORD_42||(LA63_4>=KEYWORD_51 && LA63_4<=KEYWORD_52)||LA63_4==KEYWORD_27||LA63_4==KEYWORD_22||LA63_4==KEYWORD_2||LA63_4==KEYWORD_4||LA63_4==RULE_JRNPARAM) ) {
                    alt63=4;
                }
                else if ( ((LA63_4>=KEYWORD_99 && LA63_4<=KEYWORD_96)||LA63_4==KEYWORD_86||LA63_4==KEYWORD_75||LA63_4==KEYWORD_79||LA63_4==KEYWORD_70||LA63_4==KEYWORD_72||LA63_4==KEYWORD_44||LA63_4==KEYWORD_14||(LA63_4>=KEYWORD_16 && LA63_4<=KEYWORD_18)||LA63_4==KEYWORD_20||(LA63_4>=KEYWORD_23 && LA63_4<=KEYWORD_24)||LA63_4==KEYWORD_3||LA63_4==KEYWORD_5||(LA63_4>=KEYWORD_7 && LA63_4<=KEYWORD_10)||LA63_4==RULE_STAR) ) {
                    alt63=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 4, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_15:
                {
                alt63=3;
                }
                break;
            case KEYWORD_72:
            case KEYWORD_20:
                {
                alt63=5;
                }
                break;
            case KEYWORD_96:
            case KEYWORD_70:
                {
                alt63=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2631:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2631:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2632:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2632:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2633:3: lv_expgroup_0_0= ruleExpressionGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment5602);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2650:6: ( (lv_exp_1_0= ruleExpression ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2650:6: ( (lv_exp_1_0= ruleExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2651:1: (lv_exp_1_0= ruleExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2651:1: (lv_exp_1_0= ruleExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2652:3: lv_exp_1_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleExpressionFragment5629);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2669:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2669:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2670:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2670:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2671:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2671:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    int alt62=2;
                    alt62 = dfa62.predict(input);
                    switch (alt62) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2672:3: lv_xexp_2_1= ruleXExpression
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleXExpression_in_ruleExpressionFragment5658);
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
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2687:8: lv_xexp_2_2= ruleXExpression_
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpression_ParserRuleCall_2_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleXExpression__in_ruleExpressionFragment5677);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2706:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2706:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2707:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2707:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2708:3: lv_notPrm_3_0= RULE_JRNPARAM
                    {
                    lv_notPrm_3_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragment5703); 

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
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2725:6: ( (lv_in_4_0= ruleInOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2725:6: ( (lv_in_4_0= ruleInOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2726:1: (lv_in_4_0= ruleInOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2726:1: (lv_in_4_0= ruleInOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2727:3: lv_in_4_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getInInOperatorParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleInOperator_in_ruleExpressionFragment5735);
                    lv_in_4_0=ruleInOperator();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"in",
                            		lv_in_4_0, 
                            		"InOperator");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2744:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2744:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2745:1: (lv_exists_5_0= ruleExistsOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2745:1: (lv_exists_5_0= ruleExistsOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2746:3: lv_exists_5_0= ruleExistsOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExistsExistsOperatorParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExistsOperator_in_ruleExpressionFragment5762);
                    lv_exists_5_0=ruleExistsOperator();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"exists",
                            		lv_exists_5_0, 
                            		"ExistsOperator");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2770:1: entryRuleExpressionGroup returns [EObject current=null] : iv_ruleExpressionGroup= ruleExpressionGroup EOF ;
    public final EObject entryRuleExpressionGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionGroup = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2771:2: (iv_ruleExpressionGroup= ruleExpressionGroup EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2772:2: iv_ruleExpressionGroup= ruleExpressionGroup EOF
            {
             newCompositeNode(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup5797);
            iv_ruleExpressionGroup=ruleExpressionGroup();

            state._fsp--;

             current =iv_ruleExpressionGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionGroup5807); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2779:1: ruleExpressionGroup returns [EObject current=null] : ( () ( ( (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 ) ;
    public final EObject ruleExpressionGroup() throws RecognitionException {
        EObject current = null;

        Token lv_isnot_1_1=null;
        Token lv_isnot_1_2=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2782:28: ( ( () ( ( (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2783:1: ( () ( ( (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2783:1: ( () ( ( (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2783:2: () ( ( (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2783:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2784:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpressionGroupAccess().getExprGroupAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2789:2: ( ( (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 ) ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( ((LA65_0>=KEYWORD_45 && LA65_0<=KEYWORD_46)) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2790:1: ( (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2790:1: ( (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2791:1: (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2791:1: (lv_isnot_1_1= KEYWORD_46 | lv_isnot_1_2= KEYWORD_45 )
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==KEYWORD_46) ) {
                        alt64=1;
                    }
                    else if ( (LA64_0==KEYWORD_45) ) {
                        alt64=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 64, 0, input);

                        throw nvae;
                    }
                    switch (alt64) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2792:3: lv_isnot_1_1= KEYWORD_46
                            {
                            lv_isnot_1_1=(Token)match(input,KEYWORD_46,FOLLOW_KEYWORD_46_in_ruleExpressionGroup5862); 

                                    newLeafNode(lv_isnot_1_1, grammarAccess.getExpressionGroupAccess().getIsnotNOTKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionGroupRule());
                            	        }
                                   		setWithLastConsumed(current, "isnot", lv_isnot_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2805:8: lv_isnot_1_2= KEYWORD_45
                            {
                            lv_isnot_1_2=(Token)match(input,KEYWORD_45,FOLLOW_KEYWORD_45_in_ruleExpressionGroup5890); 

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

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleExpressionGroup5918); 

                	newLeafNode(otherlv_2, grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2826:1: ( (lv_expr_3_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2827:1: (lv_expr_3_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2827:1: (lv_expr_3_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2828:3: lv_expr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleExpressionGroup5938);
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

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleExpressionGroup5951); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2857:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2858:2: (iv_ruleXExpression= ruleXExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2859:2: iv_ruleXExpression= ruleXExpression EOF
            {
             newCompositeNode(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression5985);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;

             current =iv_ruleXExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression5995); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2866:1: ruleXExpression returns [EObject current=null] : (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2869:28: ( (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2870:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2870:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2871:2: otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13
            {
            otherlv_0=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleXExpression6033); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpressionAccess().getXKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2875:1: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2876:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpressionAccess().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression6054); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpressionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2886:1: ( (lv_xf_3_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2887:1: (lv_xf_3_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2887:1: (lv_xf_3_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2888:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression6074);
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

            otherlv_4=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression6087); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpressionAccess().getCommaKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2909:1: ( (lv_col_5_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2910:1: (lv_col_5_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2910:1: (lv_col_5_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2911:3: lv_col_5_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getColOperandParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleXExpression6107);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2927:2: (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==KEYWORD_4) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2928:2: otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression6121); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpressionAccess().getCommaKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2932:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2933:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2933:1: (lv_prm_7_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2934:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression6141);
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

            otherlv_8=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleXExpression6156); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2963:1: entryRuleXExpression_ returns [EObject current=null] : iv_ruleXExpression_= ruleXExpression_ EOF ;
    public final EObject entryRuleXExpression_() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression_ = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2964:2: (iv_ruleXExpression_= ruleXExpression_ EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2965:2: iv_ruleXExpression_= ruleXExpression_ EOF
            {
             newCompositeNode(grammarAccess.getXExpression_Rule()); 
            pushFollow(FOLLOW_ruleXExpression__in_entryRuleXExpression_6190);
            iv_ruleXExpression_=ruleXExpression_();

            state._fsp--;

             current =iv_ruleXExpression_; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression_6200); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2972:1: ruleXExpression_ returns [EObject current=null] : (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2975:28: ( (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2976:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2976:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2977:2: otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperand ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13
            {
            otherlv_0=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleXExpression_6238); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpression_Access().getXKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2981:1: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2982:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpression_Access().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression_6259); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpression_Access().getLeftCurlyBracketKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2992:1: ( (lv_xf_3_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2993:1: (lv_xf_3_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2993:1: (lv_xf_3_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2994:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression_6279);
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

            otherlv_4=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleXExpression_6292); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpression_Access().getVerticalLineKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3015:1: ( (lv_col_5_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3016:1: (lv_col_5_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3016:1: (lv_col_5_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3017:3: lv_col_5_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getColOperandParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleXExpression_6312);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3033:2: (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==KEYWORD_12) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3034:2: otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleXExpression_6326); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpression_Access().getVerticalLineKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3038:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3039:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3039:1: (lv_prm_7_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3040:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpression_Access().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression_6346);
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

            otherlv_8=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleXExpression_6361); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3069:1: entryRuleXExpressionParams returns [EObject current=null] : iv_ruleXExpressionParams= ruleXExpressionParams EOF ;
    public final EObject entryRuleXExpressionParams() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionParams = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3070:2: (iv_ruleXExpressionParams= ruleXExpressionParams EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3071:2: iv_ruleXExpressionParams= ruleXExpressionParams EOF
            {
             newCompositeNode(grammarAccess.getXExpressionParamsRule()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams6395);
            iv_ruleXExpressionParams=ruleXExpressionParams();

            state._fsp--;

             current =iv_ruleXExpressionParams; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionParams6405); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3078:1: ruleXExpressionParams returns [EObject current=null] : (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) ;
    public final EObject ruleXExpressionParams() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_JRParameter_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3081:28: ( (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3082:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3082:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3083:5: this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams6452);
            this_JRParameter_0=ruleJRParameter();

            state._fsp--;


                    current = this_JRParameter_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3091:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==KEYWORD_4) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3091:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3091:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3092:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3097:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    int cnt68=0;
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==KEYWORD_4) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3098:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpressionParams6475); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3102:1: ( (lv_entries_3_0= ruleJRParameter ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3103:1: (lv_entries_3_0= ruleJRParameter )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3103:1: (lv_entries_3_0= ruleJRParameter )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3104:3: lv_entries_3_0= ruleJRParameter
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams6495);
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
                    	    if ( cnt68 >= 1 ) break loop68;
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

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3128:1: entryRuleJRParameter returns [EObject current=null] : iv_ruleJRParameter= ruleJRParameter EOF ;
    public final EObject entryRuleJRParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameter = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3129:2: (iv_ruleJRParameter= ruleJRParameter EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3130:2: iv_ruleJRParameter= ruleJRParameter EOF
            {
             newCompositeNode(grammarAccess.getJRParameterRule()); 
            pushFollow(FOLLOW_ruleJRParameter_in_entryRuleJRParameter6534);
            iv_ruleJRParameter=ruleJRParameter();

            state._fsp--;

             current =iv_ruleJRParameter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRParameter6544); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3137:1: ruleJRParameter returns [EObject current=null] : ( (lv_jrprm_0_0= RULE_ID ) ) ;
    public final EObject ruleJRParameter() throws RecognitionException {
        EObject current = null;

        Token lv_jrprm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3140:28: ( ( (lv_jrprm_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3141:1: ( (lv_jrprm_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3141:1: ( (lv_jrprm_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3142:1: (lv_jrprm_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3142:1: (lv_jrprm_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3143:3: lv_jrprm_0_0= RULE_ID
            {
            lv_jrprm_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJRParameter6585); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3167:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3168:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3169:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression6624);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression6634); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3176:1: ruleExpression returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3179:28: ( ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3180:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3180:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3180:2: ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3180:2: ( (lv_op1_0_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3181:1: (lv_op1_0_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3181:1: (lv_op1_0_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3182:3: lv_op1_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleExpression6680);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3198:2: ( ( ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            int alt71=6;
            switch ( input.LA(1) ) {
            case KEYWORD_99:
            case KEYWORD_79:
                {
                alt71=1;
                }
                break;
            case KEYWORD_72:
            case KEYWORD_20:
                {
                alt71=2;
                }
                break;
            case KEYWORD_96:
            case KEYWORD_70:
                {
                alt71=3;
                }
                break;
            case KEYWORD_100:
            case KEYWORD_75:
                {
                alt71=4;
                }
                break;
            case KEYWORD_86:
            case KEYWORD_44:
                {
                alt71=5;
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
                alt71=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }

            switch (alt71) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3198:3: ( ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3198:3: ( ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3199:1: ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3199:1: ( (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3200:1: (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3200:1: (lv_isnull_1_1= KEYWORD_79 | lv_isnull_1_2= KEYWORD_99 )
                    int alt70=2;
                    int LA70_0 = input.LA(1);

                    if ( (LA70_0==KEYWORD_79) ) {
                        alt70=1;
                    }
                    else if ( (LA70_0==KEYWORD_99) ) {
                        alt70=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 70, 0, input);

                        throw nvae;
                    }
                    switch (alt70) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3201:3: lv_isnull_1_1= KEYWORD_79
                            {
                            lv_isnull_1_1=(Token)match(input,KEYWORD_79,FOLLOW_KEYWORD_79_in_ruleExpression6702); 

                                    newLeafNode(lv_isnull_1_1, grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "isnull", lv_isnull_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3214:8: lv_isnull_1_2= KEYWORD_99
                            {
                            lv_isnull_1_2=(Token)match(input,KEYWORD_99,FOLLOW_KEYWORD_99_in_ruleExpression6730); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3231:6: ( (lv_in_2_0= ruleInOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3231:6: ( (lv_in_2_0= ruleInOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3232:1: (lv_in_2_0= ruleInOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3232:1: (lv_in_2_0= ruleInOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3233:3: lv_in_2_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleInOperator_in_ruleExpression6771);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3250:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3250:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3251:1: (lv_exists_3_0= ruleExistsOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3251:1: (lv_exists_3_0= ruleExistsOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3252:3: lv_exists_3_0= ruleExistsOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getExistsExistsOperatorParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExistsOperator_in_ruleExpression6798);
                    lv_exists_3_0=ruleExistsOperator();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"exists",
                            		lv_exists_3_0, 
                            		"ExistsOperator");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3269:6: ( (lv_between_4_0= ruleBetween ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3269:6: ( (lv_between_4_0= ruleBetween ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3270:1: (lv_between_4_0= ruleBetween )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3270:1: (lv_between_4_0= ruleBetween )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3271:3: lv_between_4_0= ruleBetween
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBetween_in_ruleExpression6825);
                    lv_between_4_0=ruleBetween();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"between",
                            		lv_between_4_0, 
                            		"Between");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3288:6: ( (lv_like_5_0= ruleLike ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3288:6: ( (lv_like_5_0= ruleLike ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3289:1: (lv_like_5_0= ruleLike )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3289:1: (lv_like_5_0= ruleLike )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3290:3: lv_like_5_0= ruleLike
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleLike_in_ruleExpression6852);
                    lv_like_5_0=ruleLike();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"like",
                            		lv_like_5_0, 
                            		"Like");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3307:6: ( (lv_comp_6_0= ruleComparison ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3307:6: ( (lv_comp_6_0= ruleComparison ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3308:1: (lv_comp_6_0= ruleComparison )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3308:1: (lv_comp_6_0= ruleComparison )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3309:3: lv_comp_6_0= ruleComparison
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_5_0()); 
                    	    
                    pushFollow(FOLLOW_ruleComparison_in_ruleExpression6879);
                    lv_comp_6_0=ruleComparison();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"comp",
                            		lv_comp_6_0, 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3333:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3334:2: (iv_ruleComparison= ruleComparison EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3335:2: iv_ruleComparison= ruleComparison EOF
            {
             newCompositeNode(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_ruleComparison_in_entryRuleComparison6915);
            iv_ruleComparison=ruleComparison();

            state._fsp--;

             current =iv_ruleComparison; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleComparison6925); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3342:1: ruleComparison returns [EObject current=null] : ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3345:28: ( ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3346:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3346:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3346:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 ) ) )? ( (lv_op2_2_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3346:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3347:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3347:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3348:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3348:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 )
            int alt72=8;
            switch ( input.LA(1) ) {
            case KEYWORD_10:
                {
                alt72=1;
                }
                break;
            case KEYWORD_18:
                {
                alt72=2;
                }
                break;
            case KEYWORD_8:
                {
                alt72=3;
                }
                break;
            case KEYWORD_16:
                {
                alt72=4;
                }
                break;
            case KEYWORD_9:
                {
                alt72=5;
                }
                break;
            case KEYWORD_17:
                {
                alt72=6;
                }
                break;
            case KEYWORD_14:
                {
                alt72=7;
                }
                break;
            case KEYWORD_23:
                {
                alt72=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }

            switch (alt72) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3349:3: lv_operator_0_1= KEYWORD_10
                    {
                    lv_operator_0_1=(Token)match(input,KEYWORD_10,FOLLOW_KEYWORD_10_in_ruleComparison6971); 

                            newLeafNode(lv_operator_0_1, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3362:8: lv_operator_0_2= KEYWORD_18
                    {
                    lv_operator_0_2=(Token)match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_ruleComparison6999); 

                            newLeafNode(lv_operator_0_2, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3375:8: lv_operator_0_3= KEYWORD_8
                    {
                    lv_operator_0_3=(Token)match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_ruleComparison7027); 

                            newLeafNode(lv_operator_0_3, grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3388:8: lv_operator_0_4= KEYWORD_16
                    {
                    lv_operator_0_4=(Token)match(input,KEYWORD_16,FOLLOW_KEYWORD_16_in_ruleComparison7055); 

                            newLeafNode(lv_operator_0_4, grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_4, null);
                    	    

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3401:8: lv_operator_0_5= KEYWORD_9
                    {
                    lv_operator_0_5=(Token)match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_ruleComparison7083); 

                            newLeafNode(lv_operator_0_5, grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_5, null);
                    	    

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3414:8: lv_operator_0_6= KEYWORD_17
                    {
                    lv_operator_0_6=(Token)match(input,KEYWORD_17,FOLLOW_KEYWORD_17_in_ruleComparison7111); 

                            newLeafNode(lv_operator_0_6, grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_6, null);
                    	    

                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3427:8: lv_operator_0_7= KEYWORD_14
                    {
                    lv_operator_0_7=(Token)match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_ruleComparison7139); 

                            newLeafNode(lv_operator_0_7, grammarAccess.getComparisonAccess().getOperatorExclamationMarkEqualsSignKeyword_0_0_6());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_7, null);
                    	    

                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3440:8: lv_operator_0_8= KEYWORD_23
                    {
                    lv_operator_0_8=(Token)match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_ruleComparison7167); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3456:2: ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 ) ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==KEYWORD_50||LA74_0==KEYWORD_26||LA74_0==KEYWORD_28) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3457:1: ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3457:1: ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3458:1: (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3458:1: (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_50 )
                    int alt73=3;
                    switch ( input.LA(1) ) {
                    case KEYWORD_28:
                        {
                        alt73=1;
                        }
                        break;
                    case KEYWORD_26:
                        {
                        alt73=2;
                        }
                        break;
                    case KEYWORD_50:
                        {
                        alt73=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 73, 0, input);

                        throw nvae;
                    }

                    switch (alt73) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3459:3: lv_subOperator_1_1= KEYWORD_28
                            {
                            lv_subOperator_1_1=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_ruleComparison7202); 

                                    newLeafNode(lv_subOperator_1_1, grammarAccess.getComparisonAccess().getSubOperatorANYKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3472:8: lv_subOperator_1_2= KEYWORD_26
                            {
                            lv_subOperator_1_2=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleComparison7230); 

                                    newLeafNode(lv_subOperator_1_2, grammarAccess.getComparisonAccess().getSubOperatorALLKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3485:8: lv_subOperator_1_3= KEYWORD_50
                            {
                            lv_subOperator_1_3=(Token)match(input,KEYWORD_50,FOLLOW_KEYWORD_50_in_ruleComparison7258); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3501:3: ( (lv_op2_2_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3502:1: (lv_op2_2_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3502:1: (lv_op2_2_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3503:3: lv_op2_2_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleComparison7294);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3527:1: entryRuleLike returns [EObject current=null] : iv_ruleLike= ruleLike EOF ;
    public final EObject entryRuleLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLike = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3528:2: (iv_ruleLike= ruleLike EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3529:2: iv_ruleLike= ruleLike EOF
            {
             newCompositeNode(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_ruleLike_in_entryRuleLike7329);
            iv_ruleLike=ruleLike();

            state._fsp--;

             current =iv_ruleLike; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLike7339); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3536:1: ruleLike returns [EObject current=null] : ( ( ( (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) ;
    public final EObject ruleLike() throws RecognitionException {
        EObject current = null;

        Token lv_opLike_0_1=null;
        Token lv_opLike_0_2=null;
        EObject lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3539:28: ( ( ( ( (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3540:1: ( ( ( (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3540:1: ( ( ( (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3540:2: ( ( (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3540:2: ( ( (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3541:1: ( (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3541:1: ( (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3542:1: (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3542:1: (lv_opLike_0_1= KEYWORD_44 | lv_opLike_0_2= KEYWORD_86 )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==KEYWORD_44) ) {
                alt75=1;
            }
            else if ( (LA75_0==KEYWORD_86) ) {
                alt75=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3543:3: lv_opLike_0_1= KEYWORD_44
                    {
                    lv_opLike_0_1=(Token)match(input,KEYWORD_44,FOLLOW_KEYWORD_44_in_ruleLike7385); 

                            newLeafNode(lv_opLike_0_1, grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLikeRule());
                    	        }
                           		setWithLastConsumed(current, "opLike", lv_opLike_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3556:8: lv_opLike_0_2= KEYWORD_86
                    {
                    lv_opLike_0_2=(Token)match(input,KEYWORD_86,FOLLOW_KEYWORD_86_in_ruleLike7413); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3572:2: ( (lv_op2_1_0= ruleLikeOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3573:1: (lv_op2_1_0= ruleLikeOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3573:1: (lv_op2_1_0= ruleLikeOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3574:3: lv_op2_1_0= ruleLikeOperand
            {
             
            	        newCompositeNode(grammarAccess.getLikeAccess().getOp2LikeOperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLikeOperand_in_ruleLike7448);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3598:1: entryRuleLikeOperand returns [EObject current=null] : iv_ruleLikeOperand= ruleLikeOperand EOF ;
    public final EObject entryRuleLikeOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLikeOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3599:2: (iv_ruleLikeOperand= ruleLikeOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3600:2: iv_ruleLikeOperand= ruleLikeOperand EOF
            {
             newCompositeNode(grammarAccess.getLikeOperandRule()); 
            pushFollow(FOLLOW_ruleLikeOperand_in_entryRuleLikeOperand7483);
            iv_ruleLikeOperand=ruleLikeOperand();

            state._fsp--;

             current =iv_ruleLikeOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLikeOperand7493); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3607:1: ruleLikeOperand returns [EObject current=null] : ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) ;
    public final EObject ruleLikeOperand() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op2_0_0 = null;

        EObject lv_fop2_1_0 = null;

        EObject lv_fcast_2_0 = null;

        EObject lv_fparam_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3610:28: ( ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3611:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3611:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            int alt76=4;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt76=1;
                }
                break;
            case RULE_ID:
                {
                alt76=2;
                }
                break;
            case KEYWORD_53:
                {
                alt76=3;
                }
                break;
            case RULE_JRPARAM:
                {
                alt76=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }

            switch (alt76) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3611:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3611:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3612:1: (lv_op2_0_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3612:1: (lv_op2_0_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3613:3: lv_op2_0_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getOp2StringOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleLikeOperand7539);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3630:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3630:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3631:1: (lv_fop2_1_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3631:1: (lv_fop2_1_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3632:3: lv_fop2_1_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFop2OperandFunctionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleLikeOperand7566);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3649:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3649:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3650:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3650:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3651:3: lv_fcast_2_0= ruleOpFunctionCast
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFcastOpFunctionCastParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionCast_in_ruleLikeOperand7593);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3668:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3668:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3669:1: (lv_fparam_3_0= ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3669:1: (lv_fparam_3_0= ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3670:3: lv_fparam_3_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFparamParameterOperandParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParameterOperand_in_ruleLikeOperand7620);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3694:1: entryRuleBetween returns [EObject current=null] : iv_ruleBetween= ruleBetween EOF ;
    public final EObject entryRuleBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBetween = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3695:2: (iv_ruleBetween= ruleBetween EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3696:2: iv_ruleBetween= ruleBetween EOF
            {
             newCompositeNode(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_ruleBetween_in_entryRuleBetween7655);
            iv_ruleBetween=ruleBetween();

            state._fsp--;

             current =iv_ruleBetween; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBetween7665); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3703:1: ruleBetween returns [EObject current=null] : ( ( ( (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) ) ) ;
    public final EObject ruleBetween() throws RecognitionException {
        EObject current = null;

        Token lv_opBetween_0_1=null;
        Token lv_opBetween_0_2=null;
        Token otherlv_2=null;
        EObject lv_op2_1_0 = null;

        EObject lv_op3_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3706:28: ( ( ( ( (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3707:1: ( ( ( (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3707:1: ( ( ( (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3707:2: ( ( (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 ) ) ) ( (lv_op2_1_0= ruleOperand ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3707:2: ( ( (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3708:1: ( (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3708:1: ( (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3709:1: (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3709:1: (lv_opBetween_0_1= KEYWORD_75 | lv_opBetween_0_2= KEYWORD_100 )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==KEYWORD_75) ) {
                alt77=1;
            }
            else if ( (LA77_0==KEYWORD_100) ) {
                alt77=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }
            switch (alt77) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3710:3: lv_opBetween_0_1= KEYWORD_75
                    {
                    lv_opBetween_0_1=(Token)match(input,KEYWORD_75,FOLLOW_KEYWORD_75_in_ruleBetween7711); 

                            newLeafNode(lv_opBetween_0_1, grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBetweenRule());
                    	        }
                           		setWithLastConsumed(current, "opBetween", lv_opBetween_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3723:8: lv_opBetween_0_2= KEYWORD_100
                    {
                    lv_opBetween_0_2=(Token)match(input,KEYWORD_100,FOLLOW_KEYWORD_100_in_ruleBetween7739); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3739:2: ( (lv_op2_1_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3740:1: (lv_op2_1_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3740:1: (lv_op2_1_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3741:3: lv_op2_1_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleBetween7774);
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

            otherlv_2=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleBetween7787); 

                	newLeafNode(otherlv_2, grammarAccess.getBetweenAccess().getANDKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3762:1: ( (lv_op3_3_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3763:1: (lv_op3_3_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3763:1: (lv_op3_3_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3764:3: lv_op3_3_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp3OperandParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleBetween7807);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3788:1: entryRuleInOperator returns [EObject current=null] : iv_ruleInOperator= ruleInOperator EOF ;
    public final EObject entryRuleInOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOperator = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3789:2: (iv_ruleInOperator= ruleInOperator EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3790:2: iv_ruleInOperator= ruleInOperator EOF
            {
             newCompositeNode(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_ruleInOperator_in_entryRuleInOperator7842);
            iv_ruleInOperator=ruleInOperator();

            state._fsp--;

             current =iv_ruleInOperator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInOperator7852); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3797:1: ruleInOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleInOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3800:28: ( ( () ( ( (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3801:1: ( () ( ( (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3801:1: ( () ( ( (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3801:2: () ( ( (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3801:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3802:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInOperatorAccess().getInOperAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3807:2: ( ( (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3808:1: ( (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3808:1: ( (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3809:1: (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3809:1: (lv_op_1_1= KEYWORD_72 | lv_op_1_2= KEYWORD_20 )
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==KEYWORD_72) ) {
                alt78=1;
            }
            else if ( (LA78_0==KEYWORD_20) ) {
                alt78=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }
            switch (alt78) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3810:3: lv_op_1_1= KEYWORD_72
                    {
                    lv_op_1_1=(Token)match(input,KEYWORD_72,FOLLOW_KEYWORD_72_in_ruleInOperator7907); 

                            newLeafNode(lv_op_1_1, grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getInOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3823:8: lv_op_1_2= KEYWORD_20
                    {
                    lv_op_1_2=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleInOperator7935); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3839:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==KEYWORD_1) ) {
                int LA79_1 = input.LA(2);

                if ( (LA79_1==KEYWORD_74) ) {
                    alt79=1;
                }
                else if ( ((LA79_1>=RULE_INT && LA79_1<=RULE_STRING_)) ) {
                    alt79=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 79, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3839:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3839:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3840:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3840:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3841:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleInOperator7971);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3858:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3858:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3859:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3859:1: (lv_opList_3_0= ruleOperandListGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3860:3: lv_opList_3_0= ruleOperandListGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getOpListOperandListGroupParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandListGroup_in_ruleInOperator7998);
                    lv_opList_3_0=ruleOperandListGroup();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInOperatorRule());
                    	        }
                           		set(
                           			current, 
                           			"opList",
                            		lv_opList_3_0, 
                            		"OperandListGroup");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3884:1: entryRuleExistsOperator returns [EObject current=null] : iv_ruleExistsOperator= ruleExistsOperator EOF ;
    public final EObject entryRuleExistsOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistsOperator = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3885:2: (iv_ruleExistsOperator= ruleExistsOperator EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3886:2: iv_ruleExistsOperator= ruleExistsOperator EOF
            {
             newCompositeNode(grammarAccess.getExistsOperatorRule()); 
            pushFollow(FOLLOW_ruleExistsOperator_in_entryRuleExistsOperator8034);
            iv_ruleExistsOperator=ruleExistsOperator();

            state._fsp--;

             current =iv_ruleExistsOperator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExistsOperator8044); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3893:1: ruleExistsOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleExistsOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3896:28: ( ( () ( ( (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3897:1: ( () ( ( (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3897:1: ( () ( ( (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3897:2: () ( ( (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3897:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3898:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExistsOperatorAccess().getExistsOperAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3903:2: ( ( (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3904:1: ( (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3904:1: ( (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3905:1: (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3905:1: (lv_op_1_1= KEYWORD_96 | lv_op_1_2= KEYWORD_70 )
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==KEYWORD_96) ) {
                alt80=1;
            }
            else if ( (LA80_0==KEYWORD_70) ) {
                alt80=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;
            }
            switch (alt80) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3906:3: lv_op_1_1= KEYWORD_96
                    {
                    lv_op_1_1=(Token)match(input,KEYWORD_96,FOLLOW_KEYWORD_96_in_ruleExistsOperator8099); 

                            newLeafNode(lv_op_1_1, grammarAccess.getExistsOperatorAccess().getOpNOTEXISTSKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExistsOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3919:8: lv_op_1_2= KEYWORD_70
                    {
                    lv_op_1_2=(Token)match(input,KEYWORD_70,FOLLOW_KEYWORD_70_in_ruleExistsOperator8127); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3935:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==KEYWORD_1) ) {
                int LA81_1 = input.LA(2);

                if ( ((LA81_1>=RULE_INT && LA81_1<=RULE_STRING_)) ) {
                    alt81=2;
                }
                else if ( (LA81_1==KEYWORD_74) ) {
                    alt81=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3935:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3935:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3936:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3936:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3937:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleExistsOperator8163);
                    lv_subquery_2_0=ruleSubQueryOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExistsOperatorRule());
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3954:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3954:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3955:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3955:1: (lv_opList_3_0= ruleOperandListGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3956:3: lv_opList_3_0= ruleOperandListGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getOpListOperandListGroupParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandListGroup_in_ruleExistsOperator8190);
                    lv_opList_3_0=ruleOperandListGroup();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExistsOperatorRule());
                    	        }
                           		set(
                           			current, 
                           			"opList",
                            		lv_opList_3_0, 
                            		"OperandListGroup");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3980:1: entryRuleOperandListGroup returns [EObject current=null] : iv_ruleOperandListGroup= ruleOperandListGroup EOF ;
    public final EObject entryRuleOperandListGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandListGroup = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3981:2: (iv_ruleOperandListGroup= ruleOperandListGroup EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3982:2: iv_ruleOperandListGroup= ruleOperandListGroup EOF
            {
             newCompositeNode(grammarAccess.getOperandListGroupRule()); 
            pushFollow(FOLLOW_ruleOperandListGroup_in_entryRuleOperandListGroup8226);
            iv_ruleOperandListGroup=ruleOperandListGroup();

            state._fsp--;

             current =iv_ruleOperandListGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandListGroup8236); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3989:1: ruleOperandListGroup returns [EObject current=null] : (otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2 ) ;
    public final EObject ruleOperandListGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_opGroup_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3992:28: ( (otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3993:1: (otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3993:1: (otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3994:2: otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleOperandListGroup8274); 

                	newLeafNode(otherlv_0, grammarAccess.getOperandListGroupAccess().getLeftParenthesisKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3998:1: ( (lv_opGroup_1_0= ruleOperandList ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3999:1: (lv_opGroup_1_0= ruleOperandList )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3999:1: (lv_opGroup_1_0= ruleOperandList )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4000:3: lv_opGroup_1_0= ruleOperandList
            {
             
            	        newCompositeNode(grammarAccess.getOperandListGroupAccess().getOpGroupOperandListParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandList_in_ruleOperandListGroup8294);
            lv_opGroup_1_0=ruleOperandList();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperandListGroupRule());
            	        }
                   		set(
                   			current, 
                   			"opGroup",
                    		lv_opGroup_1_0, 
                    		"OperandList");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOperandListGroup8307); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4029:1: entryRuleOperandList returns [EObject current=null] : iv_ruleOperandList= ruleOperandList EOF ;
    public final EObject entryRuleOperandList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandList = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4030:2: (iv_ruleOperandList= ruleOperandList EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4031:2: iv_ruleOperandList= ruleOperandList EOF
            {
             newCompositeNode(grammarAccess.getOperandListRule()); 
            pushFollow(FOLLOW_ruleOperandList_in_entryRuleOperandList8341);
            iv_ruleOperandList=ruleOperandList();

            state._fsp--;

             current =iv_ruleOperandList; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandList8351); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4038:1: ruleOperandList returns [EObject current=null] : (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) ;
    public final EObject ruleOperandList() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ScalarOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4041:28: ( (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4042:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4042:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4043:5: this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOperandListAccess().getScalarOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleScalarOperand_in_ruleOperandList8398);
            this_ScalarOperand_0=ruleScalarOperand();

            state._fsp--;


                    current = this_ScalarOperand_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4051:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==KEYWORD_4) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4051:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4051:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4052:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4057:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    int cnt82=0;
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==KEYWORD_4) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4058:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOperandList8421); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4062:1: ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4063:1: (lv_entries_3_0= ruleScalarOperand )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4063:1: (lv_entries_3_0= ruleScalarOperand )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4064:3: lv_entries_3_0= ruleScalarOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOperandListAccess().getEntriesScalarOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleScalarOperand_in_ruleOperandList8441);
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
                    	    if ( cnt82 >= 1 ) break loop82;
                                EarlyExitException eee =
                                    new EarlyExitException(82, input);
                                throw eee;
                        }
                        cnt82++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4088:1: entryRuleOperand returns [EObject current=null] : iv_ruleOperand= ruleOperand EOF ;
    public final EObject entryRuleOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4089:2: (iv_ruleOperand= ruleOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4090:2: iv_ruleOperand= ruleOperand EOF
            {
             newCompositeNode(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_ruleOperand_in_entryRuleOperand8480);
            iv_ruleOperand=ruleOperand();

            state._fsp--;

             current =iv_ruleOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperand8490); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4097:1: ruleOperand returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4100:28: ( ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4101:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4101:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4101:2: ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4101:2: ( (lv_op1_0_0= ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4102:1: (lv_op1_0_0= ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4102:1: (lv_op1_0_0= ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4103:3: lv_op1_0_0= ruleOperandFragment
            {
             
            	        newCompositeNode(grammarAccess.getOperandAccess().getOp1OperandFragmentParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand8536);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4119:2: ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            loop85:
            do {
                int alt85=2;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==KEYWORD_24||LA85_0==KEYWORD_3||LA85_0==KEYWORD_5||LA85_0==KEYWORD_7||LA85_0==RULE_STAR) ) {
                    alt85=1;
                }


                switch (alt85) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4119:3: ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) ) ( (lv_right_11_0= ruleOperandFragment ) )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4119:3: ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () this_STAR_6= RULE_STAR ) | ( () otherlv_8= KEYWORD_7 ) | ( () otherlv_10= KEYWORD_24 ) )
            	    int alt84=5;
            	    switch ( input.LA(1) ) {
            	    case KEYWORD_3:
            	        {
            	        alt84=1;
            	        }
            	        break;
            	    case KEYWORD_5:
            	        {
            	        alt84=2;
            	        }
            	        break;
            	    case RULE_STAR:
            	        {
            	        alt84=3;
            	        }
            	        break;
            	    case KEYWORD_7:
            	        {
            	        alt84=4;
            	        }
            	        break;
            	    case KEYWORD_24:
            	        {
            	        alt84=5;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 84, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt84) {
            	        case 1 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4119:4: ( () otherlv_2= KEYWORD_3 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4119:4: ( () otherlv_2= KEYWORD_3 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4119:5: () otherlv_2= KEYWORD_3
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4119:5: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4120:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getPlusLeftAction_1_0_0_0(),
            	                        current);
            	                

            	            }

            	            otherlv_2=(Token)match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_ruleOperand8561); 

            	                	newLeafNode(otherlv_2, grammarAccess.getOperandAccess().getPlusSignKeyword_1_0_0_1());
            	                

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4131:6: ( () otherlv_4= KEYWORD_5 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4131:6: ( () otherlv_4= KEYWORD_5 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4131:7: () otherlv_4= KEYWORD_5
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4131:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4132:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMinusLeftAction_1_0_1_0(),
            	                        current);
            	                

            	            }

            	            otherlv_4=(Token)match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_ruleOperand8590); 

            	                	newLeafNode(otherlv_4, grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_0_1_1());
            	                

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4143:6: ( () this_STAR_6= RULE_STAR )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4143:6: ( () this_STAR_6= RULE_STAR )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4143:7: () this_STAR_6= RULE_STAR
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4143:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4144:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getStarLeftAction_1_0_2_0(),
            	                        current);
            	                

            	            }

            	            this_STAR_6=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperand8617); 
            	             
            	                newLeafNode(this_STAR_6, grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_0_2_1()); 
            	                

            	            }


            	            }
            	            break;
            	        case 4 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4154:6: ( () otherlv_8= KEYWORD_7 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4154:6: ( () otherlv_8= KEYWORD_7 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4154:7: () otherlv_8= KEYWORD_7
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4154:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4155:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getDivLeftAction_1_0_3_0(),
            	                        current);
            	                

            	            }

            	            otherlv_8=(Token)match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_ruleOperand8646); 

            	                	newLeafNode(otherlv_8, grammarAccess.getOperandAccess().getSolidusKeyword_1_0_3_1());
            	                

            	            }


            	            }
            	            break;
            	        case 5 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4166:6: ( () otherlv_10= KEYWORD_24 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4166:6: ( () otherlv_10= KEYWORD_24 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4166:7: () otherlv_10= KEYWORD_24
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4166:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4167:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getConcatLeftAction_1_0_4_0(),
            	                        current);
            	                

            	            }

            	            otherlv_10=(Token)match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_ruleOperand8675); 

            	                	newLeafNode(otherlv_10, grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_0_4_1());
            	                

            	            }


            	            }
            	            break;

            	    }

            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4177:3: ( (lv_right_11_0= ruleOperandFragment ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4178:1: (lv_right_11_0= ruleOperandFragment )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4178:1: (lv_right_11_0= ruleOperandFragment )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4179:3: lv_right_11_0= ruleOperandFragment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOperandAccess().getRightOperandFragmentParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand8697);
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
            	    break loop85;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4203:1: entryRuleOperandFragment returns [EObject current=null] : iv_ruleOperandFragment= ruleOperandFragment EOF ;
    public final EObject entryRuleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4204:2: (iv_ruleOperandFragment= ruleOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4205:2: iv_ruleOperandFragment= ruleOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment8734);
            iv_ruleOperandFragment=ruleOperandFragment();

            state._fsp--;

             current =iv_ruleOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFragment8744); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4212:1: ruleOperandFragment returns [EObject current=null] : ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_func_4_0= ruleOperandFunction ) ) | ( (lv_sqlcase_5_0= ruleSQLCASE ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4215:28: ( ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_func_4_0= ruleOperandFunction ) ) | ( (lv_sqlcase_5_0= ruleSQLCASE ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4216:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_func_4_0= ruleOperandFunction ) ) | ( (lv_sqlcase_5_0= ruleSQLCASE ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4216:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_func_4_0= ruleOperandFunction ) ) | ( (lv_sqlcase_5_0= ruleSQLCASE ) ) )
            int alt86=6;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA86_1 = input.LA(2);

                if ( (LA86_1==KEYWORD_1) ) {
                    alt86=5;
                }
                else if ( (LA86_1==EOF||(LA86_1>=KEYWORD_103 && LA86_1<=KEYWORD_102)||(LA86_1>=KEYWORD_98 && LA86_1<=KEYWORD_93)||(LA86_1>=KEYWORD_85 && LA86_1<=KEYWORD_86)||LA86_1==KEYWORD_88||LA86_1==KEYWORD_75||(LA86_1>=KEYWORD_79 && LA86_1<=KEYWORD_80)||(LA86_1>=KEYWORD_69 && LA86_1<=KEYWORD_73)||LA86_1==KEYWORD_54||LA86_1==KEYWORD_57||(LA86_1>=KEYWORD_59 && LA86_1<=KEYWORD_60)||LA86_1==KEYWORD_62||(LA86_1>=KEYWORD_65 && LA86_1<=KEYWORD_68)||(LA86_1>=KEYWORD_36 && LA86_1<=KEYWORD_40)||LA86_1==KEYWORD_42||LA86_1==KEYWORD_44||LA86_1==KEYWORD_49||(LA86_1>=KEYWORD_51 && LA86_1<=KEYWORD_25)||LA86_1==KEYWORD_27||(LA86_1>=KEYWORD_29 && LA86_1<=KEYWORD_30)||LA86_1==KEYWORD_14||(LA86_1>=KEYWORD_16 && LA86_1<=KEYWORD_20)||(LA86_1>=KEYWORD_22 && LA86_1<=KEYWORD_24)||(LA86_1>=KEYWORD_2 && LA86_1<=KEYWORD_10)||(LA86_1>=KEYWORD_12 && LA86_1<=KEYWORD_13)||(LA86_1>=RULE_JRNPARAM && LA86_1<=RULE_STAR)||(LA86_1>=RULE_STRING && LA86_1<=RULE_ID)) ) {
                    alt86=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 86, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt86=1;
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
                alt86=2;
                }
                break;
            case KEYWORD_1:
                {
                alt86=3;
                }
                break;
            case KEYWORD_53:
                {
                alt86=4;
                }
                break;
            case KEYWORD_35:
                {
                alt86=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }

            switch (alt86) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4216:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4216:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4217:1: (lv_column_0_0= ruleColumnOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4217:1: (lv_column_0_0= ruleColumnOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4218:3: lv_column_0_0= ruleColumnOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnOperand_in_ruleOperandFragment8790);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4235:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4235:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4236:1: (lv_xop_1_0= ruleXOperandFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4236:1: (lv_xop_1_0= ruleXOperandFragment )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4237:3: lv_xop_1_0= ruleXOperandFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXOperandFragment_in_ruleOperandFragment8817);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4254:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4254:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4255:1: (lv_subq_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4255:1: (lv_subq_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4256:3: lv_subq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment8844);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4273:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4273:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4274:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4274:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4275:3: lv_fcast_3_0= ruleOpFunctionCast
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFcastOpFunctionCastParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionCast_in_ruleOperandFragment8871);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4292:6: ( (lv_func_4_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4292:6: ( (lv_func_4_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4293:1: (lv_func_4_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4293:1: (lv_func_4_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4294:3: lv_func_4_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFuncOperandFunctionParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleOperandFragment8898);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4311:6: ( (lv_sqlcase_5_0= ruleSQLCASE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4311:6: ( (lv_sqlcase_5_0= ruleSQLCASE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4312:1: (lv_sqlcase_5_0= ruleSQLCASE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4312:1: (lv_sqlcase_5_0= ruleSQLCASE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4313:3: lv_sqlcase_5_0= ruleSQLCASE
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSqlcaseSQLCASEParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSQLCASE_in_ruleOperandFragment8925);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4337:1: entryRuleOperandFunction returns [EObject current=null] : iv_ruleOperandFunction= ruleOperandFunction EOF ;
    public final EObject entryRuleOperandFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFunction = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4338:2: (iv_ruleOperandFunction= ruleOperandFunction EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4339:2: iv_ruleOperandFunction= ruleOperandFunction EOF
            {
             newCompositeNode(grammarAccess.getOperandFunctionRule()); 
            pushFollow(FOLLOW_ruleOperandFunction_in_entryRuleOperandFunction8960);
            iv_ruleOperandFunction=ruleOperandFunction();

            state._fsp--;

             current =iv_ruleOperandFunction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFunction8970); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4346:1: ruleOperandFunction returns [EObject current=null] : ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) ;
    public final EObject ruleOperandFunction() throws RecognitionException {
        EObject current = null;

        Token this_STAR_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_fname_1_0 = null;

        EObject lv_args_3_0 = null;

        EObject lv_fan_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4349:28: ( ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4350:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4350:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4350:2: () ( (lv_fname_1_0= ruleFNAME ) ) (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4350:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4351:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperandFunctionAccess().getOpFunctionAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4356:2: ( (lv_fname_1_0= ruleFNAME ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4357:1: (lv_fname_1_0= ruleFNAME )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4357:1: (lv_fname_1_0= ruleFNAME )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4358:3: lv_fname_1_0= ruleFNAME
            {
             
            	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFnameFNAMEParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFNAME_in_ruleOperandFunction9025);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4374:2: (this_STAR_2= RULE_STAR | ( (lv_args_3_0= ruleOpFunctionArg ) ) )?
            int alt87=3;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==RULE_STAR) ) {
                alt87=1;
            }
            else if ( (LA87_0==KEYWORD_84||LA87_0==KEYWORD_53||LA87_0==KEYWORD_35||LA87_0==KEYWORD_26||LA87_0==KEYWORD_1||(LA87_0>=RULE_JRPARAM && LA87_0<=RULE_JRNPARAM)||(LA87_0>=RULE_INT && LA87_0<=RULE_ID)) ) {
                alt87=2;
            }
            switch (alt87) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4374:3: this_STAR_2= RULE_STAR
                    {
                    this_STAR_2=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperandFunction9037); 
                     
                        newLeafNode(this_STAR_2, grammarAccess.getOperandFunctionAccess().getSTARTerminalRuleCall_2_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4379:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4379:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4380:1: (lv_args_3_0= ruleOpFunctionArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4380:1: (lv_args_3_0= ruleOpFunctionArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4381:3: lv_args_3_0= ruleOpFunctionArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getArgsOpFunctionArgParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionArg_in_ruleOperandFunction9063);
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

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOperandFunction9078); 

                	newLeafNode(otherlv_4, grammarAccess.getOperandFunctionAccess().getRightParenthesisKeyword_3());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4402:1: ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==KEYWORD_48) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4403:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4403:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4404:3: lv_fan_5_0= ruleFunctionAnalytical
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFanFunctionAnalyticalParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFunctionAnalytical_in_ruleOperandFunction9098);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4428:1: entryRuleFunctionAnalytical returns [EObject current=null] : iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF ;
    public final EObject entryRuleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionAnalytical = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4429:2: (iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4430:2: iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF
            {
             newCompositeNode(grammarAccess.getFunctionAnalyticalRule()); 
            pushFollow(FOLLOW_ruleFunctionAnalytical_in_entryRuleFunctionAnalytical9134);
            iv_ruleFunctionAnalytical=ruleFunctionAnalytical();

            state._fsp--;

             current =iv_ruleFunctionAnalytical; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFunctionAnalytical9144); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4437:1: ruleFunctionAnalytical returns [EObject current=null] : (otherlv_0= KEYWORD_48 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_anClause_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4440:28: ( (otherlv_0= KEYWORD_48 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4441:1: (otherlv_0= KEYWORD_48 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4441:1: (otherlv_0= KEYWORD_48 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4442:2: otherlv_0= KEYWORD_48 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_48,FOLLOW_KEYWORD_48_in_ruleFunctionAnalytical9182); 

                	newLeafNode(otherlv_0, grammarAccess.getFunctionAnalyticalAccess().getOVERKeyword_0());
                
            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleFunctionAnalytical9194); 

                	newLeafNode(otherlv_1, grammarAccess.getFunctionAnalyticalAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4451:1: ( (lv_anClause_2_0= ruleAnalyticClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4452:1: (lv_anClause_2_0= ruleAnalyticClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4452:1: (lv_anClause_2_0= ruleAnalyticClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4453:3: lv_anClause_2_0= ruleAnalyticClause
            {
             
            	        newCompositeNode(grammarAccess.getFunctionAnalyticalAccess().getAnClauseAnalyticClauseParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAnalyticClause_in_ruleFunctionAnalytical9214);
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

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleFunctionAnalytical9227); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4482:1: entryRuleAnalyticClause returns [EObject current=null] : iv_ruleAnalyticClause= ruleAnalyticClause EOF ;
    public final EObject entryRuleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4483:2: (iv_ruleAnalyticClause= ruleAnalyticClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4484:2: iv_ruleAnalyticClause= ruleAnalyticClause EOF
            {
             newCompositeNode(grammarAccess.getAnalyticClauseRule()); 
            pushFollow(FOLLOW_ruleAnalyticClause_in_entryRuleAnalyticClause9261);
            iv_ruleAnalyticClause=ruleAnalyticClause();

            state._fsp--;

             current =iv_ruleAnalyticClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticClause9271); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4491:1: ruleAnalyticClause returns [EObject current=null] : (this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )? ) ;
    public final EObject ruleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject this_QueryPartitionClause_0 = null;

        EObject lv_obc_1_0 = null;

        EObject lv_winc_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4494:28: ( (this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4495:1: (this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4495:1: (this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4496:5: this_QueryPartitionClause_0= ruleQueryPartitionClause ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )?
            {
             
                    newCompositeNode(grammarAccess.getAnalyticClauseAccess().getQueryPartitionClauseParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleQueryPartitionClause_in_ruleAnalyticClause9318);
            this_QueryPartitionClause_0=ruleQueryPartitionClause();

            state._fsp--;


                    current = this_QueryPartitionClause_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4504:1: ( ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )? )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==KEYWORD_103||LA90_0==KEYWORD_88) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4504:2: ( (lv_obc_1_0= ruleOrderByClause ) ) ( (lv_winc_2_0= ruleWindowingClause ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4504:2: ( (lv_obc_1_0= ruleOrderByClause ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4505:1: (lv_obc_1_0= ruleOrderByClause )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4505:1: (lv_obc_1_0= ruleOrderByClause )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4506:3: lv_obc_1_0= ruleOrderByClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getObcOrderByClauseParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOrderByClause_in_ruleAnalyticClause9339);
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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4522:2: ( (lv_winc_2_0= ruleWindowingClause ) )?
                    int alt89=2;
                    int LA89_0 = input.LA(1);

                    if ( (LA89_0==KEYWORD_65||LA89_0==KEYWORD_49) ) {
                        alt89=1;
                    }
                    switch (alt89) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4523:1: (lv_winc_2_0= ruleWindowingClause )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4523:1: (lv_winc_2_0= ruleWindowingClause )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4524:3: lv_winc_2_0= ruleWindowingClause
                            {
                             
                            	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getWincWindowingClauseParserRuleCall_1_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleWindowingClause_in_ruleAnalyticClause9360);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4548:1: entryRuleWindowingClause returns [EObject current=null] : iv_ruleWindowingClause= ruleWindowingClause EOF ;
    public final EObject entryRuleWindowingClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4549:2: (iv_ruleWindowingClause= ruleWindowingClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4550:2: iv_ruleWindowingClause= ruleWindowingClause EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseRule()); 
            pushFollow(FOLLOW_ruleWindowingClause_in_entryRuleWindowingClause9398);
            iv_ruleWindowingClause=ruleWindowingClause();

            state._fsp--;

             current =iv_ruleWindowingClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClause9408); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4557:1: ruleWindowingClause returns [EObject current=null] : ( (otherlv_0= KEYWORD_49 | otherlv_1= KEYWORD_65 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) ;
    public final EObject ruleWindowingClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_WindowingClauseBetween_2 = null;

        EObject this_WindowingClauseOperandPreceding_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4560:28: ( ( (otherlv_0= KEYWORD_49 | otherlv_1= KEYWORD_65 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4561:1: ( (otherlv_0= KEYWORD_49 | otherlv_1= KEYWORD_65 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4561:1: ( (otherlv_0= KEYWORD_49 | otherlv_1= KEYWORD_65 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4561:2: (otherlv_0= KEYWORD_49 | otherlv_1= KEYWORD_65 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4561:2: (otherlv_0= KEYWORD_49 | otherlv_1= KEYWORD_65 )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==KEYWORD_49) ) {
                alt91=1;
            }
            else if ( (LA91_0==KEYWORD_65) ) {
                alt91=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4562:2: otherlv_0= KEYWORD_49
                    {
                    otherlv_0=(Token)match(input,KEYWORD_49,FOLLOW_KEYWORD_49_in_ruleWindowingClause9447); 

                        	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseAccess().getROWSKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4568:2: otherlv_1= KEYWORD_65
                    {
                    otherlv_1=(Token)match(input,KEYWORD_65,FOLLOW_KEYWORD_65_in_ruleWindowingClause9465); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseAccess().getRANGEKeyword_0_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4572:2: (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==KEYWORD_75) ) {
                alt92=1;
            }
            else if ( (LA92_0==KEYWORD_105||LA92_0==KEYWORD_97||LA92_0==KEYWORD_53||LA92_0==KEYWORD_35||LA92_0==KEYWORD_1||(LA92_0>=RULE_JRPARAM && LA92_0<=RULE_JRNPARAM)||(LA92_0>=RULE_INT && LA92_0<=RULE_ID)) ) {
                alt92=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;
            }
            switch (alt92) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4573:5: this_WindowingClauseBetween_2= ruleWindowingClauseBetween
                    {
                     
                            newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseBetweenParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleWindowingClauseBetween_in_ruleWindowingClause9488);
                    this_WindowingClauseBetween_2=ruleWindowingClauseBetween();

                    state._fsp--;


                            current = this_WindowingClauseBetween_2;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4583:5: this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding
                    {
                     
                            newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseOperandPrecedingParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClause9515);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4599:1: entryRuleWindowingClauseBetween returns [EObject current=null] : iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF ;
    public final EObject entryRuleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseBetween = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4600:2: (iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4601:2: iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseBetweenRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseBetween_in_entryRuleWindowingClauseBetween9550);
            iv_ruleWindowingClauseBetween=ruleWindowingClauseBetween();

            state._fsp--;

             current =iv_ruleWindowingClauseBetween; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseBetween9560); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4608:1: ruleWindowingClauseBetween returns [EObject current=null] : (otherlv_0= KEYWORD_75 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) ;
    public final EObject ruleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_wcoP_1_0 = null;

        EObject lv_wcoF_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4611:28: ( (otherlv_0= KEYWORD_75 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4612:1: (otherlv_0= KEYWORD_75 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4612:1: (otherlv_0= KEYWORD_75 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4613:2: otherlv_0= KEYWORD_75 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_75,FOLLOW_KEYWORD_75_in_ruleWindowingClauseBetween9598); 

                	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseBetweenAccess().getBETWEENKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4617:1: ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4618:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4618:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4619:3: lv_wcoP_1_0= ruleWindowingClauseOperandPreceding
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoPWindowingClauseOperandPrecedingParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClauseBetween9618);
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

            otherlv_2=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleWindowingClauseBetween9631); 

                	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseBetweenAccess().getANDKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4640:1: ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4641:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4641:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4642:3: lv_wcoF_3_0= ruleWindowingClauseOperandFollowing
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoFWindowingClauseOperandFollowingParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleWindowingClauseOperandFollowing_in_ruleWindowingClauseBetween9651);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4666:1: entryRuleWindowingClauseOperandFollowing returns [EObject current=null] : iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF ;
    public final EObject entryRuleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandFollowing = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4667:2: (iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4668:2: iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseOperandFollowing_in_entryRuleWindowingClauseOperandFollowing9686);
            iv_ruleWindowingClauseOperandFollowing=ruleWindowingClauseOperandFollowing();

            state._fsp--;

             current =iv_ruleWindowingClauseOperandFollowing; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseOperandFollowing9696); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4675:1: ruleWindowingClauseOperandFollowing returns [EObject current=null] : ( () (otherlv_1= KEYWORD_104 | otherlv_2= KEYWORD_97 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_exp_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4678:28: ( ( () (otherlv_1= KEYWORD_104 | otherlv_2= KEYWORD_97 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4679:1: ( () (otherlv_1= KEYWORD_104 | otherlv_2= KEYWORD_97 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4679:1: ( () (otherlv_1= KEYWORD_104 | otherlv_2= KEYWORD_97 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4679:2: () (otherlv_1= KEYWORD_104 | otherlv_2= KEYWORD_97 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4679:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4680:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandFollowingAccess().getWindowingClauseOperandFollowingAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4685:2: (otherlv_1= KEYWORD_104 | otherlv_2= KEYWORD_97 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) )
            int alt94=3;
            switch ( input.LA(1) ) {
            case KEYWORD_104:
                {
                alt94=1;
                }
                break;
            case KEYWORD_97:
                {
                alt94=2;
                }
                break;
            case KEYWORD_53:
            case KEYWORD_35:
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
                alt94=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }

            switch (alt94) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4686:2: otherlv_1= KEYWORD_104
                    {
                    otherlv_1=(Token)match(input,KEYWORD_104,FOLLOW_KEYWORD_104_in_ruleWindowingClauseOperandFollowing9744); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandFollowingAccess().getUNBOUNDEDFOLLOWINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4692:2: otherlv_2= KEYWORD_97
                    {
                    otherlv_2=(Token)match(input,KEYWORD_97,FOLLOW_KEYWORD_97_in_ruleWindowingClauseOperandFollowing9762); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandFollowingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4697:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4697:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4697:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4697:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4698:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4698:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4699:3: lv_exp_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingAccess().getExpAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandFollowing9789);
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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4715:2: (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 )
                    int alt93=2;
                    int LA93_0 = input.LA(1);

                    if ( (LA93_0==KEYWORD_93) ) {
                        alt93=1;
                    }
                    else if ( (LA93_0==KEYWORD_91) ) {
                        alt93=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 0, input);

                        throw nvae;
                    }
                    switch (alt93) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4716:2: otherlv_4= KEYWORD_93
                            {
                            otherlv_4=(Token)match(input,KEYWORD_93,FOLLOW_KEYWORD_93_in_ruleWindowingClauseOperandFollowing9803); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandFollowingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4722:2: otherlv_5= KEYWORD_91
                            {
                            otherlv_5=(Token)match(input,KEYWORD_91,FOLLOW_KEYWORD_91_in_ruleWindowingClauseOperandFollowing9821); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4734:1: entryRuleWindowingClauseOperandPreceding returns [EObject current=null] : iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF ;
    public final EObject entryRuleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandPreceding = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4735:2: (iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4736:2: iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_entryRuleWindowingClauseOperandPreceding9858);
            iv_ruleWindowingClauseOperandPreceding=ruleWindowingClauseOperandPreceding();

            state._fsp--;

             current =iv_ruleWindowingClauseOperandPreceding; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseOperandPreceding9868); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4743:1: ruleWindowingClauseOperandPreceding returns [EObject current=null] : ( () (otherlv_1= KEYWORD_105 | otherlv_2= KEYWORD_97 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4746:28: ( ( () (otherlv_1= KEYWORD_105 | otherlv_2= KEYWORD_97 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4747:1: ( () (otherlv_1= KEYWORD_105 | otherlv_2= KEYWORD_97 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4747:1: ( () (otherlv_1= KEYWORD_105 | otherlv_2= KEYWORD_97 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4747:2: () (otherlv_1= KEYWORD_105 | otherlv_2= KEYWORD_97 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4747:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4748:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandPrecedingAccess().getWindowingClauseOperandPrecedingAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4753:2: (otherlv_1= KEYWORD_105 | otherlv_2= KEYWORD_97 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) ) )
            int alt96=3;
            switch ( input.LA(1) ) {
            case KEYWORD_105:
                {
                alt96=1;
                }
                break;
            case KEYWORD_97:
                {
                alt96=2;
                }
                break;
            case KEYWORD_53:
            case KEYWORD_35:
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
                alt96=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;
            }

            switch (alt96) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4754:2: otherlv_1= KEYWORD_105
                    {
                    otherlv_1=(Token)match(input,KEYWORD_105,FOLLOW_KEYWORD_105_in_ruleWindowingClauseOperandPreceding9916); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandPrecedingAccess().getUNBOUNDEDPRECEDINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4760:2: otherlv_2= KEYWORD_97
                    {
                    otherlv_2=(Token)match(input,KEYWORD_97,FOLLOW_KEYWORD_97_in_ruleWindowingClauseOperandPreceding9934); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandPrecedingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4765:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4765:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4765:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4765:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4766:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4766:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4767:3: lv_expr_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingAccess().getExprAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandPreceding9961);
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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4783:2: (otherlv_4= KEYWORD_93 | otherlv_5= KEYWORD_91 )
                    int alt95=2;
                    int LA95_0 = input.LA(1);

                    if ( (LA95_0==KEYWORD_93) ) {
                        alt95=1;
                    }
                    else if ( (LA95_0==KEYWORD_91) ) {
                        alt95=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 95, 0, input);

                        throw nvae;
                    }
                    switch (alt95) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4784:2: otherlv_4= KEYWORD_93
                            {
                            otherlv_4=(Token)match(input,KEYWORD_93,FOLLOW_KEYWORD_93_in_ruleWindowingClauseOperandPreceding9975); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandPrecedingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4790:2: otherlv_5= KEYWORD_91
                            {
                            otherlv_5=(Token)match(input,KEYWORD_91,FOLLOW_KEYWORD_91_in_ruleWindowingClauseOperandPreceding9993); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4802:1: entryRuleOrderByClause returns [EObject current=null] : iv_ruleOrderByClause= ruleOrderByClause EOF ;
    public final EObject entryRuleOrderByClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4803:2: (iv_ruleOrderByClause= ruleOrderByClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4804:2: iv_ruleOrderByClause= ruleOrderByClause EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseRule()); 
            pushFollow(FOLLOW_ruleOrderByClause_in_entryRuleOrderByClause10030);
            iv_ruleOrderByClause=ruleOrderByClause();

            state._fsp--;

             current =iv_ruleOrderByClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClause10040); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4811:1: ruleOrderByClause returns [EObject current=null] : ( (otherlv_0= KEYWORD_88 | otherlv_1= KEYWORD_103 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) ;
    public final EObject ruleOrderByClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_args_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4814:28: ( ( (otherlv_0= KEYWORD_88 | otherlv_1= KEYWORD_103 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4815:1: ( (otherlv_0= KEYWORD_88 | otherlv_1= KEYWORD_103 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4815:1: ( (otherlv_0= KEYWORD_88 | otherlv_1= KEYWORD_103 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4815:2: (otherlv_0= KEYWORD_88 | otherlv_1= KEYWORD_103 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4815:2: (otherlv_0= KEYWORD_88 | otherlv_1= KEYWORD_103 )
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==KEYWORD_88) ) {
                alt97=1;
            }
            else if ( (LA97_0==KEYWORD_103) ) {
                alt97=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                throw nvae;
            }
            switch (alt97) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4816:2: otherlv_0= KEYWORD_88
                    {
                    otherlv_0=(Token)match(input,KEYWORD_88,FOLLOW_KEYWORD_88_in_ruleOrderByClause10079); 

                        	newLeafNode(otherlv_0, grammarAccess.getOrderByClauseAccess().getORDERBYKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4822:2: otherlv_1= KEYWORD_103
                    {
                    otherlv_1=(Token)match(input,KEYWORD_103,FOLLOW_KEYWORD_103_in_ruleOrderByClause10097); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseAccess().getORDERSIBLINGSBYKeyword_0_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4826:2: ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4827:1: (lv_args_2_0= ruleOrderByClauseArgs )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4827:1: (lv_args_2_0= ruleOrderByClauseArgs )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4828:3: lv_args_2_0= ruleOrderByClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseAccess().getArgsOrderByClauseArgsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOrderByClauseArgs_in_ruleOrderByClause10118);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4852:1: entryRuleOrderByClauseArgs returns [EObject current=null] : iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF ;
    public final EObject entryRuleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArgs = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4853:2: (iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4854:2: iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseArgsRule()); 
            pushFollow(FOLLOW_ruleOrderByClauseArgs_in_entryRuleOrderByClauseArgs10153);
            iv_ruleOrderByClauseArgs=ruleOrderByClauseArgs();

            state._fsp--;

             current =iv_ruleOrderByClauseArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClauseArgs10163); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4861:1: ruleOrderByClauseArgs returns [EObject current=null] : (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) ;
    public final EObject ruleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4864:28: ( (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4865:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4865:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4866:5: this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getOrderByClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs10210);
            this_OrderByClauseArg_0=ruleOrderByClauseArg();

            state._fsp--;


                    current = this_OrderByClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4874:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==KEYWORD_4) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4874:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4874:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4875:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByClauseArgsAccess().getOBCArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4880:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    int cnt98=0;
                    loop98:
                    do {
                        int alt98=2;
                        int LA98_0 = input.LA(1);

                        if ( (LA98_0==KEYWORD_4) ) {
                            alt98=1;
                        }


                        switch (alt98) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4881:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByClauseArgs10233); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4885:1: ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4886:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4886:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4887:3: lv_entries_3_0= ruleOrderByClauseArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getEntriesOrderByClauseArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs10253);
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
                    	    if ( cnt98 >= 1 ) break loop98;
                                EarlyExitException eee =
                                    new EarlyExitException(98, input);
                                throw eee;
                        }
                        cnt98++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4911:1: entryRuleOrderByClauseArg returns [EObject current=null] : iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF ;
    public final EObject entryRuleOrderByClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4912:2: (iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4913:2: iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseArgRule()); 
            pushFollow(FOLLOW_ruleOrderByClauseArg_in_entryRuleOrderByClauseArg10292);
            iv_ruleOrderByClauseArg=ruleOrderByClauseArg();

            state._fsp--;

             current =iv_ruleOrderByClauseArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClauseArg10302); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4920:1: ruleOrderByClauseArg returns [EObject current=null] : ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_36 )? (otherlv_3= KEYWORD_62 (otherlv_4= KEYWORD_56 | otherlv_5= KEYWORD_41 ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4923:28: ( ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_36 )? (otherlv_3= KEYWORD_62 (otherlv_4= KEYWORD_56 | otherlv_5= KEYWORD_41 ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4924:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_36 )? (otherlv_3= KEYWORD_62 (otherlv_4= KEYWORD_56 | otherlv_5= KEYWORD_41 ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4924:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_36 )? (otherlv_3= KEYWORD_62 (otherlv_4= KEYWORD_56 | otherlv_5= KEYWORD_41 ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4924:2: ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_36 )? (otherlv_3= KEYWORD_62 (otherlv_4= KEYWORD_56 | otherlv_5= KEYWORD_41 ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4924:2: ( (lv_col_0_0= ruleAnalyticExprArg ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4925:1: (lv_col_0_0= ruleAnalyticExprArg )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4925:1: (lv_col_0_0= ruleAnalyticExprArg )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4926:3: lv_col_0_0= ruleAnalyticExprArg
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseArgAccess().getColAnalyticExprArgParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleOrderByClauseArg10348);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4942:2: (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_36 )?
            int alt100=3;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==KEYWORD_29) ) {
                alt100=1;
            }
            else if ( (LA100_0==KEYWORD_36) ) {
                alt100=2;
            }
            switch (alt100) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4943:2: otherlv_1= KEYWORD_29
                    {
                    otherlv_1=(Token)match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_ruleOrderByClauseArg10362); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseArgAccess().getASCKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4949:2: otherlv_2= KEYWORD_36
                    {
                    otherlv_2=(Token)match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_ruleOrderByClauseArg10380); 

                        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgAccess().getDESCKeyword_1_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4953:3: (otherlv_3= KEYWORD_62 (otherlv_4= KEYWORD_56 | otherlv_5= KEYWORD_41 ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==KEYWORD_62) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4954:2: otherlv_3= KEYWORD_62 (otherlv_4= KEYWORD_56 | otherlv_5= KEYWORD_41 )
                    {
                    otherlv_3=(Token)match(input,KEYWORD_62,FOLLOW_KEYWORD_62_in_ruleOrderByClauseArg10395); 

                        	newLeafNode(otherlv_3, grammarAccess.getOrderByClauseArgAccess().getNULLSKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4958:1: (otherlv_4= KEYWORD_56 | otherlv_5= KEYWORD_41 )
                    int alt101=2;
                    int LA101_0 = input.LA(1);

                    if ( (LA101_0==KEYWORD_56) ) {
                        alt101=1;
                    }
                    else if ( (LA101_0==KEYWORD_41) ) {
                        alt101=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 101, 0, input);

                        throw nvae;
                    }
                    switch (alt101) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4959:2: otherlv_4= KEYWORD_56
                            {
                            otherlv_4=(Token)match(input,KEYWORD_56,FOLLOW_KEYWORD_56_in_ruleOrderByClauseArg10408); 

                                	newLeafNode(otherlv_4, grammarAccess.getOrderByClauseArgAccess().getFIRSTKeyword_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4965:2: otherlv_5= KEYWORD_41
                            {
                            otherlv_5=(Token)match(input,KEYWORD_41,FOLLOW_KEYWORD_41_in_ruleOrderByClauseArg10426); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4977:1: entryRuleQueryPartitionClause returns [EObject current=null] : iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF ;
    public final EObject entryRuleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQueryPartitionClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4978:2: (iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4979:2: iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF
            {
             newCompositeNode(grammarAccess.getQueryPartitionClauseRule()); 
            pushFollow(FOLLOW_ruleQueryPartitionClause_in_entryRuleQueryPartitionClause10463);
            iv_ruleQueryPartitionClause=ruleQueryPartitionClause();

            state._fsp--;

             current =iv_ruleQueryPartitionClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQueryPartitionClause10473); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4986:1: ruleQueryPartitionClause returns [EObject current=null] : (otherlv_0= KEYWORD_101 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) ) ;
    public final EObject ruleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_1_0 = null;

        EObject this_AnalyticExprArgs_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4989:28: ( (otherlv_0= KEYWORD_101 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4990:1: (otherlv_0= KEYWORD_101 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4990:1: (otherlv_0= KEYWORD_101 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4991:2: otherlv_0= KEYWORD_101 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_101,FOLLOW_KEYWORD_101_in_ruleQueryPartitionClause10511); 

                	newLeafNode(otherlv_0, grammarAccess.getQueryPartitionClauseAccess().getPARTITIONBYKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4995:1: ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) )
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==KEYWORD_53||LA103_0==KEYWORD_35||(LA103_0>=RULE_JRPARAM && LA103_0<=RULE_JRNPARAM)||(LA103_0>=RULE_INT && LA103_0<=RULE_ID)) ) {
                alt103=1;
            }
            else if ( (LA103_0==KEYWORD_1) ) {
                int LA103_2 = input.LA(2);

                if ( (LA103_2==KEYWORD_53||LA103_2==KEYWORD_35||LA103_2==KEYWORD_1||(LA103_2>=RULE_JRPARAM && LA103_2<=RULE_JRNPARAM)||(LA103_2>=RULE_INT && LA103_2<=RULE_ID)) ) {
                    alt103=2;
                }
                else if ( (LA103_2==KEYWORD_74) ) {
                    alt103=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 103, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }
            switch (alt103) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4995:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4995:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4996:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4996:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4997:3: lv_args_1_0= ruleAnalyticExprArgs
                    {
                     
                    	        newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getArgsAnalyticExprArgsParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause10532);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5014:6: (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5014:6: (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5015:2: otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2
                    {
                    otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleQueryPartitionClause10552); 

                        	newLeafNode(otherlv_2, grammarAccess.getQueryPartitionClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getAnalyticExprArgsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause10573);
                    this_AnalyticExprArgs_3=ruleAnalyticExprArgs();

                    state._fsp--;


                            current = this_AnalyticExprArgs_3;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleQueryPartitionClause10585); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5041:1: entryRuleAnalyticExprArgs returns [EObject current=null] : iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF ;
    public final EObject entryRuleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArgs = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5042:2: (iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5043:2: iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF
            {
             newCompositeNode(grammarAccess.getAnalyticExprArgsRule()); 
            pushFollow(FOLLOW_ruleAnalyticExprArgs_in_entryRuleAnalyticExprArgs10621);
            iv_ruleAnalyticExprArgs=ruleAnalyticExprArgs();

            state._fsp--;

             current =iv_ruleAnalyticExprArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticExprArgs10631); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5050:1: ruleAnalyticExprArgs returns [EObject current=null] : (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) ;
    public final EObject ruleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AnalyticExprArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5053:28: ( (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5054:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5054:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5055:5: this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getAnalyticExprArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs10678);
            this_AnalyticExprArg_0=ruleAnalyticExprArg();

            state._fsp--;


                    current = this_AnalyticExprArg_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5063:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==KEYWORD_4) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5063:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5063:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5064:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getAnalyticExprArgsAccess().getAExpArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5069:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    int cnt104=0;
                    loop104:
                    do {
                        int alt104=2;
                        int LA104_0 = input.LA(1);

                        if ( (LA104_0==KEYWORD_4) ) {
                            alt104=1;
                        }


                        switch (alt104) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5070:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleAnalyticExprArgs10701); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getAnalyticExprArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5074:1: ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5075:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5075:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5076:3: lv_entries_3_0= ruleAnalyticExprArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getEntriesAnalyticExprArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs10721);
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
    // $ANTLR end "ruleAnalyticExprArgs"


    // $ANTLR start "entryRuleAnalyticExprArg"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5100:1: entryRuleAnalyticExprArg returns [EObject current=null] : iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF ;
    public final EObject entryRuleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5101:2: (iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5102:2: iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF
            {
             newCompositeNode(grammarAccess.getAnalyticExprArgRule()); 
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_entryRuleAnalyticExprArg10760);
            iv_ruleAnalyticExprArg=ruleAnalyticExprArg();

            state._fsp--;

             current =iv_ruleAnalyticExprArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticExprArg10770); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5109:1: ruleAnalyticExprArg returns [EObject current=null] : ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5112:28: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5113:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5113:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5113:2: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5113:2: ( (lv_ce_0_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5114:1: (lv_ce_0_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5114:1: (lv_ce_0_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5115:3: lv_ce_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getCeOperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleAnalyticExprArg10816);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5131:2: ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( ((LA106_0>=RULE_STRING && LA106_0<=RULE_ID)) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5132:1: (lv_colAlias_1_0= ruleDbObjectName )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5132:1: (lv_colAlias_1_0= ruleDbObjectName )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5133:3: lv_colAlias_1_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getColAliasDbObjectNameParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectName_in_ruleAnalyticExprArg10837);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5157:1: entryRuleOpFunctionArg returns [EObject current=null] : iv_ruleOpFunctionArg= ruleOpFunctionArg EOF ;
    public final EObject entryRuleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5158:2: (iv_ruleOpFunctionArg= ruleOpFunctionArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5159:2: iv_ruleOpFunctionArg= ruleOpFunctionArg EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArg_in_entryRuleOpFunctionArg10873);
            iv_ruleOpFunctionArg=ruleOpFunctionArg();

            state._fsp--;

             current =iv_ruleOpFunctionArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArg10883); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5166:1: ruleOpFunctionArg returns [EObject current=null] : (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) ;
    public final EObject ruleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OpFunctionArgOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5169:28: ( (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5170:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5170:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5171:5: this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOpFunctionArgAccess().getOpFunctionArgOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg10930);
            this_OpFunctionArgOperand_0=ruleOpFunctionArgOperand();

            state._fsp--;


                    current = this_OpFunctionArgOperand_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5179:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==KEYWORD_4) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5179:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5179:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5180:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOpFunctionArgAccess().getOpFListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5185:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    int cnt107=0;
                    loop107:
                    do {
                        int alt107=2;
                        int LA107_0 = input.LA(1);

                        if ( (LA107_0==KEYWORD_4) ) {
                            alt107=1;
                        }


                        switch (alt107) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5186:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOpFunctionArg10953); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOpFunctionArgAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5190:1: ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5191:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5191:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5192:3: lv_entries_3_0= ruleOpFunctionArgOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOpFunctionArgAccess().getEntriesOpFunctionArgOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg10973);
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
                    	    if ( cnt107 >= 1 ) break loop107;
                                EarlyExitException eee =
                                    new EarlyExitException(107, input);
                                throw eee;
                        }
                        cnt107++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5216:1: entryRuleOpFunctionArgOperand returns [EObject current=null] : iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF ;
    public final EObject entryRuleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5217:2: (iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5218:2: iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgOperandRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_entryRuleOpFunctionArgOperand11012);
            iv_ruleOpFunctionArgOperand=ruleOpFunctionArgOperand();

            state._fsp--;

             current =iv_ruleOpFunctionArgOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArgOperand11022); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5225:1: ruleOpFunctionArgOperand returns [EObject current=null] : ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) ;
    public final EObject ruleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject lv_op_0_1 = null;

        EObject lv_op_0_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5228:28: ( ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5229:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5229:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5230:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5230:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5231:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5231:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==KEYWORD_84||LA109_0==KEYWORD_26) ) {
                alt109=1;
            }
            else if ( (LA109_0==KEYWORD_53||LA109_0==KEYWORD_35||LA109_0==KEYWORD_1||(LA109_0>=RULE_JRPARAM && LA109_0<=RULE_JRNPARAM)||(LA109_0>=RULE_INT && LA109_0<=RULE_ID)) ) {
                alt109=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 109, 0, input);

                throw nvae;
            }
            switch (alt109) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5232:3: lv_op_0_1= ruleOpFunctionArgAgregate
                    {
                     
                    	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOpFunctionArgAgregateParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionArgAgregate_in_ruleOpFunctionArgOperand11069);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5247:8: lv_op_0_2= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOperandParserRuleCall_0_1()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionArgOperand11088);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5273:1: entryRuleOpFunctionCast returns [EObject current=null] : iv_ruleOpFunctionCast= ruleOpFunctionCast EOF ;
    public final EObject entryRuleOpFunctionCast() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionCast = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5274:2: (iv_ruleOpFunctionCast= ruleOpFunctionCast EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5275:2: iv_ruleOpFunctionCast= ruleOpFunctionCast EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionCastRule()); 
            pushFollow(FOLLOW_ruleOpFunctionCast_in_entryRuleOpFunctionCast11125);
            iv_ruleOpFunctionCast=ruleOpFunctionCast();

            state._fsp--;

             current =iv_ruleOpFunctionCast; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionCast11135); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5282:1: ruleOpFunctionCast returns [EObject current=null] : (otherlv_0= KEYWORD_53 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5285:28: ( (otherlv_0= KEYWORD_53 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5286:1: (otherlv_0= KEYWORD_53 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5286:1: (otherlv_0= KEYWORD_53 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5287:2: otherlv_0= KEYWORD_53 ( (lv_op_1_0= ruleOperand ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_53,FOLLOW_KEYWORD_53_in_ruleOpFunctionCast11173); 

                	newLeafNode(otherlv_0, grammarAccess.getOpFunctionCastAccess().getCASTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5291:1: ( (lv_op_1_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5292:1: (lv_op_1_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5292:1: (lv_op_1_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5293:3: lv_op_1_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getOpFunctionCastAccess().getOpOperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionCast11193);
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

            otherlv_2=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleOpFunctionCast11206); 

                	newLeafNode(otherlv_2, grammarAccess.getOpFunctionCastAccess().getASKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5314:1: ( (lv_type_3_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5315:1: (lv_type_3_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5315:1: (lv_type_3_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5316:3: lv_type_3_0= RULE_ID
            {
            lv_type_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOpFunctionCast11222); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5332:2: (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==KEYWORD_1) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5333:2: otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2
                    {
                    otherlv_4=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleOpFunctionCast11241); 

                        	newLeafNode(otherlv_4, grammarAccess.getOpFunctionCastAccess().getLeftParenthesisKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5337:1: ( (lv_p_5_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5338:1: (lv_p_5_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5338:1: (lv_p_5_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5339:3: lv_p_5_0= RULE_INT
                    {
                    lv_p_5_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOpFunctionCast11257); 

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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5355:2: (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )?
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==KEYWORD_4) ) {
                        alt110=1;
                    }
                    switch (alt110) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5356:2: otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) )
                            {
                            otherlv_6=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOpFunctionCast11276); 

                                	newLeafNode(otherlv_6, grammarAccess.getOpFunctionCastAccess().getCommaKeyword_4_2_0());
                                
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5360:1: ( (lv_p2_7_0= RULE_INT ) )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5361:1: (lv_p2_7_0= RULE_INT )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5361:1: (lv_p2_7_0= RULE_INT )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5362:3: lv_p2_7_0= RULE_INT
                            {
                            lv_p2_7_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOpFunctionCast11292); 

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

                    otherlv_8=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOpFunctionCast11312); 

                        	newLeafNode(otherlv_8, grammarAccess.getOpFunctionCastAccess().getRightParenthesisKeyword_4_3());
                        

                    }
                    break;

            }

            otherlv_9=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOpFunctionCast11326); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5396:1: entryRuleOpFunctionArgAgregate returns [EObject current=null] : iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF ;
    public final EObject entryRuleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgAgregate = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5397:2: (iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5398:2: iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgAgregateRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArgAgregate_in_entryRuleOpFunctionArgAgregate11360);
            iv_ruleOpFunctionArgAgregate=ruleOpFunctionArgAgregate();

            state._fsp--;

             current =iv_ruleOpFunctionArgAgregate; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArgAgregate11370); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5405:1: ruleOpFunctionArgAgregate returns [EObject current=null] : ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_84 ) this_Operand_2= ruleOperand ) ;
    public final EObject ruleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5408:28: ( ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_84 ) this_Operand_2= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5409:1: ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_84 ) this_Operand_2= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5409:1: ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_84 ) this_Operand_2= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5409:2: (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_84 ) this_Operand_2= ruleOperand
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5409:2: (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_84 )
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==KEYWORD_26) ) {
                alt112=1;
            }
            else if ( (LA112_0==KEYWORD_84) ) {
                alt112=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 112, 0, input);

                throw nvae;
            }
            switch (alt112) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5410:2: otherlv_0= KEYWORD_26
                    {
                    otherlv_0=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleOpFunctionArgAgregate11409); 

                        	newLeafNode(otherlv_0, grammarAccess.getOpFunctionArgAgregateAccess().getALLKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5416:2: otherlv_1= KEYWORD_84
                    {
                    otherlv_1=(Token)match(input,KEYWORD_84,FOLLOW_KEYWORD_84_in_ruleOpFunctionArgAgregate11427); 

                        	newLeafNode(otherlv_1, grammarAccess.getOpFunctionArgAgregateAccess().getDISTINCTKeyword_0_1());
                        

                    }
                    break;

            }

             
                    newCompositeNode(grammarAccess.getOpFunctionArgAgregateAccess().getOperandParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionArgAgregate11449);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5437:1: entryRuleXOperandFragment returns [EObject current=null] : iv_ruleXOperandFragment= ruleXOperandFragment EOF ;
    public final EObject entryRuleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5438:2: (iv_ruleXOperandFragment= ruleXOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5439:2: iv_ruleXOperandFragment= ruleXOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment11483);
            iv_ruleXOperandFragment=ruleXOperandFragment();

            state._fsp--;

             current =iv_ruleXOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOperandFragment11493); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5446:1: ruleXOperandFragment returns [EObject current=null] : ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) ) ;
    public final EObject ruleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_param_0_0 = null;

        EObject lv_eparam_1_0 = null;

        EObject lv_scalar_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5449:28: ( ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5450:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5450:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarOperand ) ) )
            int alt113=3;
            switch ( input.LA(1) ) {
            case RULE_JRPARAM:
                {
                alt113=1;
                }
                break;
            case RULE_JRNPARAM:
                {
                alt113=2;
                }
                break;
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt113=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 113, 0, input);

                throw nvae;
            }

            switch (alt113) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5450:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5450:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5451:1: (lv_param_0_0= ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5451:1: (lv_param_0_0= ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5452:3: lv_param_0_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParameterOperand_in_ruleXOperandFragment11539);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5469:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5469:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5470:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5470:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5471:3: lv_eparam_1_0= ruleExclamationParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment11566);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5488:6: ( (lv_scalar_2_0= ruleScalarOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5488:6: ( (lv_scalar_2_0= ruleScalarOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5489:1: (lv_scalar_2_0= ruleScalarOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5489:1: (lv_scalar_2_0= ruleScalarOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5490:3: lv_scalar_2_0= ruleScalarOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getScalarScalarOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleScalarOperand_in_ruleXOperandFragment11593);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5514:1: entryRuleParameterOperand returns [EObject current=null] : iv_ruleParameterOperand= ruleParameterOperand EOF ;
    public final EObject entryRuleParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5515:2: (iv_ruleParameterOperand= ruleParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5516:2: iv_ruleParameterOperand= ruleParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand11628);
            iv_ruleParameterOperand=ruleParameterOperand();

            state._fsp--;

             current =iv_ruleParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameterOperand11638); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5523:1: ruleParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRPARAM ) ) ;
    public final EObject ruleParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5526:28: ( ( (lv_prm_0_0= RULE_JRPARAM ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5527:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5527:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5528:1: (lv_prm_0_0= RULE_JRPARAM )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5528:1: (lv_prm_0_0= RULE_JRPARAM )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5529:3: lv_prm_0_0= RULE_JRPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRPARAM,FOLLOW_RULE_JRPARAM_in_ruleParameterOperand11679); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5553:1: entryRuleExclamationParameterOperand returns [EObject current=null] : iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF ;
    public final EObject entryRuleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclamationParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5554:2: (iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5555:2: iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand11718);
            iv_ruleExclamationParameterOperand=ruleExclamationParameterOperand();

            state._fsp--;

             current =iv_ruleExclamationParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclamationParameterOperand11728); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5562:1: ruleExclamationParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRNPARAM ) ) ;
    public final EObject ruleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5565:28: ( ( (lv_prm_0_0= RULE_JRNPARAM ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5566:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5566:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5567:1: (lv_prm_0_0= RULE_JRNPARAM )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5567:1: (lv_prm_0_0= RULE_JRNPARAM )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5568:3: lv_prm_0_0= RULE_JRNPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExclamationParameterOperand11769); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5592:1: entryRuleColumnOperand returns [EObject current=null] : iv_ruleColumnOperand= ruleColumnOperand EOF ;
    public final EObject entryRuleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5593:2: (iv_ruleColumnOperand= ruleColumnOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5594:2: iv_ruleColumnOperand= ruleColumnOperand EOF
            {
             newCompositeNode(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand11808);
            iv_ruleColumnOperand=ruleColumnOperand();

            state._fsp--;

             current =iv_ruleColumnOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOperand11818); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5601:1: ruleColumnOperand returns [EObject current=null] : ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? ) ;
    public final EObject ruleColumnOperand() throws RecognitionException {
        EObject current = null;

        Token lv_ora_1_0=null;
        EObject lv_cfull_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5604:28: ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5605:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5605:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5605:2: ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5605:2: ( (lv_cfull_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5606:1: (lv_cfull_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5606:1: (lv_cfull_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5607:3: lv_cfull_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOperand11864);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5623:2: ( (lv_ora_1_0= KEYWORD_25 ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==KEYWORD_25) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5624:1: (lv_ora_1_0= KEYWORD_25 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5624:1: (lv_ora_1_0= KEYWORD_25 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5625:3: lv_ora_1_0= KEYWORD_25
                    {
                    lv_ora_1_0=(Token)match(input,KEYWORD_25,FOLLOW_KEYWORD_25_in_ruleColumnOperand11883); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5647:1: entryRuleSubQueryOperand returns [EObject current=null] : iv_ruleSubQueryOperand= ruleSubQueryOperand EOF ;
    public final EObject entryRuleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubQueryOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5648:2: (iv_ruleSubQueryOperand= ruleSubQueryOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5649:2: iv_ruleSubQueryOperand= ruleSubQueryOperand EOF
            {
             newCompositeNode(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand11930);
            iv_ruleSubQueryOperand=ruleSubQueryOperand();

            state._fsp--;

             current =iv_ruleSubQueryOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubQueryOperand11940); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5656:1: ruleSubQueryOperand returns [EObject current=null] : ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_sel_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5659:28: ( ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5660:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5660:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5660:2: () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5660:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5661:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSubQueryOperandAccess().getSubQueryOperandAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleSubQueryOperand11987); 

                	newLeafNode(otherlv_1, grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5671:1: ( (lv_sel_2_0= ruleSelectQuery ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5672:1: (lv_sel_2_0= ruleSelectQuery )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5672:1: (lv_sel_2_0= ruleSelectQuery )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5673:3: lv_sel_2_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getSubQueryOperandAccess().getSelSelectQueryParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelectQuery_in_ruleSubQueryOperand12007);
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

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleSubQueryOperand12020); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5702:1: entryRuleScalarOperand returns [EObject current=null] : iv_ruleScalarOperand= ruleScalarOperand EOF ;
    public final EObject entryRuleScalarOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5703:2: (iv_ruleScalarOperand= ruleScalarOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5704:2: iv_ruleScalarOperand= ruleScalarOperand EOF
            {
             newCompositeNode(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand12054);
            iv_ruleScalarOperand=ruleScalarOperand();

            state._fsp--;

             current =iv_ruleScalarOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarOperand12064); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5711:1: ruleScalarOperand returns [EObject current=null] : ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5714:28: ( ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5715:1: ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5715:1: ( ( (lv_soint_0_0= RULE_INT ) ) | ( (lv_sostr_1_0= ruleStringOperand ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_3_0= RULE_DATE ) ) | ( (lv_sotime_4_0= RULE_TIME ) ) | ( (lv_sodt_5_0= RULE_TIMESTAMP ) ) )
            int alt115=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt115=1;
                }
                break;
            case RULE_STRING_:
                {
                alt115=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt115=3;
                }
                break;
            case RULE_DATE:
                {
                alt115=4;
                }
                break;
            case RULE_TIME:
                {
                alt115=5;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt115=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;
            }

            switch (alt115) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5715:2: ( (lv_soint_0_0= RULE_INT ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5715:2: ( (lv_soint_0_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5716:1: (lv_soint_0_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5716:1: (lv_soint_0_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5717:3: lv_soint_0_0= RULE_INT
                    {
                    lv_soint_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleScalarOperand12106); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5734:6: ( (lv_sostr_1_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5734:6: ( (lv_sostr_1_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5735:1: (lv_sostr_1_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5735:1: (lv_sostr_1_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5736:3: lv_sostr_1_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleScalarOperand12138);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5753:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5753:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5754:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5754:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5755:3: lv_sodbl_2_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_2_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand12161); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5772:6: ( (lv_sodate_3_0= RULE_DATE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5772:6: ( (lv_sodate_3_0= RULE_DATE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5773:1: (lv_sodate_3_0= RULE_DATE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5773:1: (lv_sodate_3_0= RULE_DATE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5774:3: lv_sodate_3_0= RULE_DATE
                    {
                    lv_sodate_3_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleScalarOperand12189); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5791:6: ( (lv_sotime_4_0= RULE_TIME ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5791:6: ( (lv_sotime_4_0= RULE_TIME ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5792:1: (lv_sotime_4_0= RULE_TIME )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5792:1: (lv_sotime_4_0= RULE_TIME )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5793:3: lv_sotime_4_0= RULE_TIME
                    {
                    lv_sotime_4_0=(Token)match(input,RULE_TIME,FOLLOW_RULE_TIME_in_ruleScalarOperand12217); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5810:6: ( (lv_sodt_5_0= RULE_TIMESTAMP ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5810:6: ( (lv_sodt_5_0= RULE_TIMESTAMP ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5811:1: (lv_sodt_5_0= RULE_TIMESTAMP )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5811:1: (lv_sodt_5_0= RULE_TIMESTAMP )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5812:3: lv_sodt_5_0= RULE_TIMESTAMP
                    {
                    lv_sodt_5_0=(Token)match(input,RULE_TIMESTAMP,FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand12245); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5836:1: entryRuleSQLCASE returns [EObject current=null] : iv_ruleSQLCASE= ruleSQLCASE EOF ;
    public final EObject entryRuleSQLCASE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCASE = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5837:2: (iv_ruleSQLCASE= ruleSQLCASE EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5838:2: iv_ruleSQLCASE= ruleSQLCASE EOF
            {
             newCompositeNode(grammarAccess.getSQLCASERule()); 
            pushFollow(FOLLOW_ruleSQLCASE_in_entryRuleSQLCASE12285);
            iv_ruleSQLCASE=ruleSQLCASE();

            state._fsp--;

             current =iv_ruleSQLCASE; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSQLCASE12295); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5845:1: ruleSQLCASE returns [EObject current=null] : (otherlv_0= KEYWORD_35 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30 ) ;
    public final EObject ruleSQLCASE() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_expr_1_0 = null;

        EObject lv_when_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5848:28: ( (otherlv_0= KEYWORD_35 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5849:1: (otherlv_0= KEYWORD_35 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5849:1: (otherlv_0= KEYWORD_35 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5850:2: otherlv_0= KEYWORD_35 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_30
            {
            otherlv_0=(Token)match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_ruleSQLCASE12333); 

                	newLeafNode(otherlv_0, grammarAccess.getSQLCASEAccess().getCASEKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5854:1: ( (lv_expr_1_0= ruleFullExpression ) )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==KEYWORD_96||LA116_0==KEYWORD_70||LA116_0==KEYWORD_72||LA116_0==KEYWORD_53||LA116_0==KEYWORD_35||(LA116_0>=KEYWORD_45 && LA116_0<=KEYWORD_46)||LA116_0==KEYWORD_15||LA116_0==KEYWORD_20||LA116_0==KEYWORD_1||(LA116_0>=RULE_JRPARAM && LA116_0<=RULE_JRNPARAM)||(LA116_0>=RULE_INT && LA116_0<=RULE_ID)) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5855:1: (lv_expr_1_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5855:1: (lv_expr_1_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5856:3: lv_expr_1_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSQLCASEAccess().getExprFullExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSQLCASE12353);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5872:3: ( (lv_when_2_0= ruleSQLCaseWhens ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5873:1: (lv_when_2_0= ruleSQLCaseWhens )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5873:1: (lv_when_2_0= ruleSQLCaseWhens )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5874:3: lv_when_2_0= ruleSQLCaseWhens
            {
             
            	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWhenSQLCaseWhensParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSQLCaseWhens_in_ruleSQLCASE12375);
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

            otherlv_3=(Token)match(input,KEYWORD_30,FOLLOW_KEYWORD_30_in_ruleSQLCASE12388); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5903:1: entryRuleSQLCaseWhens returns [EObject current=null] : iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF ;
    public final EObject entryRuleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCaseWhens = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5904:2: (iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5905:2: iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF
            {
             newCompositeNode(grammarAccess.getSQLCaseWhensRule()); 
            pushFollow(FOLLOW_ruleSQLCaseWhens_in_entryRuleSQLCaseWhens12422);
            iv_ruleSQLCaseWhens=ruleSQLCaseWhens();

            state._fsp--;

             current =iv_ruleSQLCaseWhens; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSQLCaseWhens12432); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5912:1: ruleSQLCaseWhens returns [EObject current=null] : (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) ;
    public final EObject ruleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject this_SqlCaseWhen_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5915:28: ( (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5916:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5916:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5917:5: this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getSqlCaseWhenParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens12479);
            this_SqlCaseWhen_0=ruleSqlCaseWhen();

            state._fsp--;


                    current = this_SqlCaseWhen_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5925:1: ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==KEYWORD_52) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5925:2: () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5925:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5926:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getSQLCaseWhensAccess().getWhenListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5931:2: ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    int cnt117=0;
                    loop117:
                    do {
                        int alt117=2;
                        int LA117_0 = input.LA(1);

                        if ( (LA117_0==KEYWORD_52) ) {
                            alt117=1;
                        }


                        switch (alt117) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5932:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5932:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5933:3: lv_entries_2_0= ruleSqlCaseWhen
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getEntriesSqlCaseWhenParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens12509);
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
    // $ANTLR end "ruleSQLCaseWhens"


    // $ANTLR start "entryRuleSqlCaseWhen"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5957:1: entryRuleSqlCaseWhen returns [EObject current=null] : iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF ;
    public final EObject entryRuleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSqlCaseWhen = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5958:2: (iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5959:2: iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF
            {
             newCompositeNode(grammarAccess.getSqlCaseWhenRule()); 
            pushFollow(FOLLOW_ruleSqlCaseWhen_in_entryRuleSqlCaseWhen12547);
            iv_ruleSqlCaseWhen=ruleSqlCaseWhen();

            state._fsp--;

             current =iv_ruleSqlCaseWhen; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSqlCaseWhen12557); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5966:1: ruleSqlCaseWhen returns [EObject current=null] : (otherlv_0= KEYWORD_52 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_51 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_37 ( (lv_eexp_5_0= ruleOperand ) ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5969:28: ( (otherlv_0= KEYWORD_52 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_51 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_37 ( (lv_eexp_5_0= ruleOperand ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5970:1: (otherlv_0= KEYWORD_52 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_51 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_37 ( (lv_eexp_5_0= ruleOperand ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5970:1: (otherlv_0= KEYWORD_52 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_51 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_37 ( (lv_eexp_5_0= ruleOperand ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5971:2: otherlv_0= KEYWORD_52 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_51 ( (lv_texp_3_0= ruleOperand ) ) (otherlv_4= KEYWORD_37 ( (lv_eexp_5_0= ruleOperand ) ) )?
            {
            otherlv_0=(Token)match(input,KEYWORD_52,FOLLOW_KEYWORD_52_in_ruleSqlCaseWhen12595); 

                	newLeafNode(otherlv_0, grammarAccess.getSqlCaseWhenAccess().getWHENKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5975:1: ( (lv_expr_1_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5976:1: (lv_expr_1_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5976:1: (lv_expr_1_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5977:3: lv_expr_1_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getExprFullExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleSqlCaseWhen12615);
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

            otherlv_2=(Token)match(input,KEYWORD_51,FOLLOW_KEYWORD_51_in_ruleSqlCaseWhen12628); 

                	newLeafNode(otherlv_2, grammarAccess.getSqlCaseWhenAccess().getTHENKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5998:1: ( (lv_texp_3_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5999:1: (lv_texp_3_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5999:1: (lv_texp_3_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6000:3: lv_texp_3_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getTexpOperandParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleSqlCaseWhen12648);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6016:2: (otherlv_4= KEYWORD_37 ( (lv_eexp_5_0= ruleOperand ) ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==KEYWORD_37) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6017:2: otherlv_4= KEYWORD_37 ( (lv_eexp_5_0= ruleOperand ) )
                    {
                    otherlv_4=(Token)match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_ruleSqlCaseWhen12662); 

                        	newLeafNode(otherlv_4, grammarAccess.getSqlCaseWhenAccess().getELSEKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6021:1: ( (lv_eexp_5_0= ruleOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6022:1: (lv_eexp_5_0= ruleOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6022:1: (lv_eexp_5_0= ruleOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6023:3: lv_eexp_5_0= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getEexpOperandParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleSqlCaseWhen12682);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6047:1: entryRuleJoinType returns [String current=null] : iv_ruleJoinType= ruleJoinType EOF ;
    public final String entryRuleJoinType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleJoinType = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6048:1: (iv_ruleJoinType= ruleJoinType EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6049:2: iv_ruleJoinType= ruleJoinType EOF
            {
             newCompositeNode(grammarAccess.getJoinTypeRule()); 
            pushFollow(FOLLOW_ruleJoinType_in_entryRuleJoinType12720);
            iv_ruleJoinType=ruleJoinType();

            state._fsp--;

             current =iv_ruleJoinType.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJoinType12731); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6056:1: ruleJoinType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= KEYWORD_80 )? (kw= KEYWORD_57 | ( (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )? ) | kw= KEYWORD_54 | kw= KEYWORD_102 )? kw= KEYWORD_40 ) ;
    public final AntlrDatatypeRuleToken ruleJoinType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6060:6: ( ( (kw= KEYWORD_80 )? (kw= KEYWORD_57 | ( (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )? ) | kw= KEYWORD_54 | kw= KEYWORD_102 )? kw= KEYWORD_40 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6061:1: ( (kw= KEYWORD_80 )? (kw= KEYWORD_57 | ( (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )? ) | kw= KEYWORD_54 | kw= KEYWORD_102 )? kw= KEYWORD_40 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6061:1: ( (kw= KEYWORD_80 )? (kw= KEYWORD_57 | ( (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )? ) | kw= KEYWORD_54 | kw= KEYWORD_102 )? kw= KEYWORD_40 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6061:2: (kw= KEYWORD_80 )? (kw= KEYWORD_57 | ( (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )? ) | kw= KEYWORD_54 | kw= KEYWORD_102 )? kw= KEYWORD_40
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6061:2: (kw= KEYWORD_80 )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==KEYWORD_80) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6062:2: kw= KEYWORD_80
                    {
                    kw=(Token)match(input,KEYWORD_80,FOLLOW_KEYWORD_80_in_ruleJoinType12770); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getNATURALKeyword_0()); 
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6067:3: (kw= KEYWORD_57 | ( (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )? ) | kw= KEYWORD_54 | kw= KEYWORD_102 )?
            int alt123=5;
            switch ( input.LA(1) ) {
                case KEYWORD_57:
                    {
                    alt123=1;
                    }
                    break;
                case KEYWORD_66:
                case KEYWORD_39:
                case KEYWORD_42:
                    {
                    alt123=2;
                    }
                    break;
                case KEYWORD_54:
                    {
                    alt123=3;
                    }
                    break;
                case KEYWORD_102:
                    {
                    alt123=4;
                    }
                    break;
            }

            switch (alt123) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6068:2: kw= KEYWORD_57
                    {
                    kw=(Token)match(input,KEYWORD_57,FOLLOW_KEYWORD_57_in_ruleJoinType12786); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getINNERKeyword_1_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6074:6: ( (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6074:6: ( (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6074:7: (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 ) (kw= KEYWORD_63 )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6074:7: (kw= KEYWORD_42 | kw= KEYWORD_66 | kw= KEYWORD_39 )
                    int alt121=3;
                    switch ( input.LA(1) ) {
                    case KEYWORD_42:
                        {
                        alt121=1;
                        }
                        break;
                    case KEYWORD_66:
                        {
                        alt121=2;
                        }
                        break;
                    case KEYWORD_39:
                        {
                        alt121=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 121, 0, input);

                        throw nvae;
                    }

                    switch (alt121) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6075:2: kw= KEYWORD_42
                            {
                            kw=(Token)match(input,KEYWORD_42,FOLLOW_KEYWORD_42_in_ruleJoinType12807); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getLEFTKeyword_1_1_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6082:2: kw= KEYWORD_66
                            {
                            kw=(Token)match(input,KEYWORD_66,FOLLOW_KEYWORD_66_in_ruleJoinType12826); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getRIGHTKeyword_1_1_0_1()); 
                                

                            }
                            break;
                        case 3 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6089:2: kw= KEYWORD_39
                            {
                            kw=(Token)match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_ruleJoinType12845); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getFULLKeyword_1_1_0_2()); 
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6094:2: (kw= KEYWORD_63 )?
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( (LA122_0==KEYWORD_63) ) {
                        alt122=1;
                    }
                    switch (alt122) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6095:2: kw= KEYWORD_63
                            {
                            kw=(Token)match(input,KEYWORD_63,FOLLOW_KEYWORD_63_in_ruleJoinType12860); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getOUTERKeyword_1_1_1()); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6102:2: kw= KEYWORD_54
                    {
                    kw=(Token)match(input,KEYWORD_54,FOLLOW_KEYWORD_54_in_ruleJoinType12882); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getCROSSKeyword_1_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6109:2: kw= KEYWORD_102
                    {
                    kw=(Token)match(input,KEYWORD_102,FOLLOW_KEYWORD_102_in_ruleJoinType12901); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getSTRAIGHT_JOINKeyword_1_3()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,KEYWORD_40,FOLLOW_KEYWORD_40_in_ruleJoinType12916); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6128:1: entryRuleDBID returns [String current=null] : iv_ruleDBID= ruleDBID EOF ;
    public final String entryRuleDBID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDBID = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6129:1: (iv_ruleDBID= ruleDBID EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6130:2: iv_ruleDBID= ruleDBID EOF
            {
             newCompositeNode(grammarAccess.getDBIDRule()); 
            pushFollow(FOLLOW_ruleDBID_in_entryRuleDBID12956);
            iv_ruleDBID=ruleDBID();

            state._fsp--;

             current =iv_ruleDBID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBID12967); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6137:1: ruleDBID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDBID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_DBNAME_1=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6141:6: ( (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6142:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6142:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            int alt124=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt124=1;
                }
                break;
            case RULE_DBNAME:
                {
                alt124=2;
                }
                break;
            case RULE_STRING:
                {
                alt124=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 124, 0, input);

                throw nvae;
            }

            switch (alt124) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6142:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDBID13007); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getDBIDAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6150:10: this_DBNAME_1= RULE_DBNAME
                    {
                    this_DBNAME_1=(Token)match(input,RULE_DBNAME,FOLLOW_RULE_DBNAME_in_ruleDBID13033); 

                    		current.merge(this_DBNAME_1);
                        
                     
                        newLeafNode(this_DBNAME_1, grammarAccess.getDBIDAccess().getDBNAMETerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6158:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDBID13059); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6173:1: entryRuleStringOperand returns [String current=null] : iv_ruleStringOperand= ruleStringOperand EOF ;
    public final String entryRuleStringOperand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6174:1: (iv_ruleStringOperand= ruleStringOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6175:2: iv_ruleStringOperand= ruleStringOperand EOF
            {
             newCompositeNode(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_ruleStringOperand_in_entryRuleStringOperand13104);
            iv_ruleStringOperand=ruleStringOperand();

            state._fsp--;

             current =iv_ruleStringOperand.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringOperand13115); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6182:1: ruleStringOperand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING__0= RULE_STRING_ ;
    public final AntlrDatatypeRuleToken ruleStringOperand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING__0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6186:6: (this_STRING__0= RULE_STRING_ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6187:5: this_STRING__0= RULE_STRING_
            {
            this_STRING__0=(Token)match(input,RULE_STRING_,FOLLOW_RULE_STRING__in_ruleStringOperand13154); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6202:1: entryRuleFNAME returns [String current=null] : iv_ruleFNAME= ruleFNAME EOF ;
    public final String entryRuleFNAME() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFNAME = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6203:1: (iv_ruleFNAME= ruleFNAME EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6204:2: iv_ruleFNAME= ruleFNAME EOF
            {
             newCompositeNode(grammarAccess.getFNAMERule()); 
            pushFollow(FOLLOW_ruleFNAME_in_entryRuleFNAME13198);
            iv_ruleFNAME=ruleFNAME();

            state._fsp--;

             current =iv_ruleFNAME.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFNAME13209); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6211:1: ruleFNAME returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= KEYWORD_1 ) ;
    public final AntlrDatatypeRuleToken ruleFNAME() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6215:6: ( (this_ID_0= RULE_ID kw= KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6216:1: (this_ID_0= RULE_ID kw= KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6216:1: (this_ID_0= RULE_ID kw= KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6216:6: this_ID_0= RULE_ID kw= KEYWORD_1
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFNAME13249); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getFNAMEAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleFNAME13267); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6237:1: entryRuleIntegerValue returns [EObject current=null] : iv_ruleIntegerValue= ruleIntegerValue EOF ;
    public final EObject entryRuleIntegerValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerValue = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6238:2: (iv_ruleIntegerValue= ruleIntegerValue EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6239:2: iv_ruleIntegerValue= ruleIntegerValue EOF
            {
             newCompositeNode(grammarAccess.getIntegerValueRule()); 
            pushFollow(FOLLOW_ruleIntegerValue_in_entryRuleIntegerValue13306);
            iv_ruleIntegerValue=ruleIntegerValue();

            state._fsp--;

             current =iv_ruleIntegerValue; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerValue13316); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6246:1: ruleIntegerValue returns [EObject current=null] : ( (lv_integer_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerValue() throws RecognitionException {
        EObject current = null;

        Token lv_integer_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6249:28: ( ( (lv_integer_0_0= RULE_INT ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6250:1: ( (lv_integer_0_0= RULE_INT ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6250:1: ( (lv_integer_0_0= RULE_INT ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6251:1: (lv_integer_0_0= RULE_INT )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6251:1: (lv_integer_0_0= RULE_INT )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6252:3: lv_integer_0_0= RULE_INT
            {
            lv_integer_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerValue13357); 

            			newLeafNode(lv_integer_0_0, grammarAccess.getIntegerValueAccess().getIntegerINTTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntegerValueRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"integer",
                    		lv_integer_0_0, 
                    		"INT");
            	    

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


    // $ANTLR start "ruleXFunction"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6276:1: ruleXFunction returns [Enumerator current=null] : ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_61 ) | (enumLiteral_2= KEYWORD_55 ) | (enumLiteral_3= KEYWORD_87 ) | (enumLiteral_4= KEYWORD_43 ) | (enumLiteral_5= KEYWORD_77 ) | (enumLiteral_6= KEYWORD_58 ) | (enumLiteral_7= KEYWORD_90 ) | (enumLiteral_8= KEYWORD_75 ) | (enumLiteral_9= KEYWORD_95 ) | (enumLiteral_10= KEYWORD_89 ) | (enumLiteral_11= KEYWORD_83 ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6278:28: ( ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_61 ) | (enumLiteral_2= KEYWORD_55 ) | (enumLiteral_3= KEYWORD_87 ) | (enumLiteral_4= KEYWORD_43 ) | (enumLiteral_5= KEYWORD_77 ) | (enumLiteral_6= KEYWORD_58 ) | (enumLiteral_7= KEYWORD_90 ) | (enumLiteral_8= KEYWORD_75 ) | (enumLiteral_9= KEYWORD_95 ) | (enumLiteral_10= KEYWORD_89 ) | (enumLiteral_11= KEYWORD_83 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6279:1: ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_61 ) | (enumLiteral_2= KEYWORD_55 ) | (enumLiteral_3= KEYWORD_87 ) | (enumLiteral_4= KEYWORD_43 ) | (enumLiteral_5= KEYWORD_77 ) | (enumLiteral_6= KEYWORD_58 ) | (enumLiteral_7= KEYWORD_90 ) | (enumLiteral_8= KEYWORD_75 ) | (enumLiteral_9= KEYWORD_95 ) | (enumLiteral_10= KEYWORD_89 ) | (enumLiteral_11= KEYWORD_83 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6279:1: ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_61 ) | (enumLiteral_2= KEYWORD_55 ) | (enumLiteral_3= KEYWORD_87 ) | (enumLiteral_4= KEYWORD_43 ) | (enumLiteral_5= KEYWORD_77 ) | (enumLiteral_6= KEYWORD_58 ) | (enumLiteral_7= KEYWORD_90 ) | (enumLiteral_8= KEYWORD_75 ) | (enumLiteral_9= KEYWORD_95 ) | (enumLiteral_10= KEYWORD_89 ) | (enumLiteral_11= KEYWORD_83 ) )
            int alt125=12;
            switch ( input.LA(1) ) {
            case KEYWORD_20:
                {
                alt125=1;
                }
                break;
            case KEYWORD_61:
                {
                alt125=2;
                }
                break;
            case KEYWORD_55:
                {
                alt125=3;
                }
                break;
            case KEYWORD_87:
                {
                alt125=4;
                }
                break;
            case KEYWORD_43:
                {
                alt125=5;
                }
                break;
            case KEYWORD_77:
                {
                alt125=6;
                }
                break;
            case KEYWORD_58:
                {
                alt125=7;
                }
                break;
            case KEYWORD_90:
                {
                alt125=8;
                }
                break;
            case KEYWORD_75:
                {
                alt125=9;
                }
                break;
            case KEYWORD_95:
                {
                alt125=10;
                }
                break;
            case KEYWORD_89:
                {
                alt125=11;
                }
                break;
            case KEYWORD_83:
                {
                alt125=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 125, 0, input);

                throw nvae;
            }

            switch (alt125) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6279:2: (enumLiteral_0= KEYWORD_20 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6279:2: (enumLiteral_0= KEYWORD_20 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6279:7: enumLiteral_0= KEYWORD_20
                    {
                    enumLiteral_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleXFunction13414); 

                            current = grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6285:6: (enumLiteral_1= KEYWORD_61 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6285:6: (enumLiteral_1= KEYWORD_61 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6285:11: enumLiteral_1= KEYWORD_61
                    {
                    enumLiteral_1=(Token)match(input,KEYWORD_61,FOLLOW_KEYWORD_61_in_ruleXFunction13436); 

                            current = grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6291:6: (enumLiteral_2= KEYWORD_55 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6291:6: (enumLiteral_2= KEYWORD_55 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6291:11: enumLiteral_2= KEYWORD_55
                    {
                    enumLiteral_2=(Token)match(input,KEYWORD_55,FOLLOW_KEYWORD_55_in_ruleXFunction13458); 

                            current = grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6297:6: (enumLiteral_3= KEYWORD_87 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6297:6: (enumLiteral_3= KEYWORD_87 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6297:11: enumLiteral_3= KEYWORD_87
                    {
                    enumLiteral_3=(Token)match(input,KEYWORD_87,FOLLOW_KEYWORD_87_in_ruleXFunction13480); 

                            current = grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6303:6: (enumLiteral_4= KEYWORD_43 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6303:6: (enumLiteral_4= KEYWORD_43 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6303:11: enumLiteral_4= KEYWORD_43
                    {
                    enumLiteral_4=(Token)match(input,KEYWORD_43,FOLLOW_KEYWORD_43_in_ruleXFunction13502); 

                            current = grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6309:6: (enumLiteral_5= KEYWORD_77 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6309:6: (enumLiteral_5= KEYWORD_77 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6309:11: enumLiteral_5= KEYWORD_77
                    {
                    enumLiteral_5=(Token)match(input,KEYWORD_77,FOLLOW_KEYWORD_77_in_ruleXFunction13524); 

                            current = grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6315:6: (enumLiteral_6= KEYWORD_58 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6315:6: (enumLiteral_6= KEYWORD_58 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6315:11: enumLiteral_6= KEYWORD_58
                    {
                    enumLiteral_6=(Token)match(input,KEYWORD_58,FOLLOW_KEYWORD_58_in_ruleXFunction13546); 

                            current = grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6321:6: (enumLiteral_7= KEYWORD_90 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6321:6: (enumLiteral_7= KEYWORD_90 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6321:11: enumLiteral_7= KEYWORD_90
                    {
                    enumLiteral_7=(Token)match(input,KEYWORD_90,FOLLOW_KEYWORD_90_in_ruleXFunction13568); 

                            current = grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6327:6: (enumLiteral_8= KEYWORD_75 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6327:6: (enumLiteral_8= KEYWORD_75 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6327:11: enumLiteral_8= KEYWORD_75
                    {
                    enumLiteral_8=(Token)match(input,KEYWORD_75,FOLLOW_KEYWORD_75_in_ruleXFunction13590); 

                            current = grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6333:6: (enumLiteral_9= KEYWORD_95 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6333:6: (enumLiteral_9= KEYWORD_95 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6333:11: enumLiteral_9= KEYWORD_95
                    {
                    enumLiteral_9=(Token)match(input,KEYWORD_95,FOLLOW_KEYWORD_95_in_ruleXFunction13612); 

                            current = grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6339:6: (enumLiteral_10= KEYWORD_89 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6339:6: (enumLiteral_10= KEYWORD_89 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6339:11: enumLiteral_10= KEYWORD_89
                    {
                    enumLiteral_10=(Token)match(input,KEYWORD_89,FOLLOW_KEYWORD_89_in_ruleXFunction13634); 

                            current = grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6345:6: (enumLiteral_11= KEYWORD_83 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6345:6: (enumLiteral_11= KEYWORD_83 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6345:11: enumLiteral_11= KEYWORD_83
                    {
                    enumLiteral_11=(Token)match(input,KEYWORD_83,FOLLOW_KEYWORD_83_in_ruleXFunction13656); 

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


    protected DFA62 dfa62 = new DFA62(this);
    static final String DFA62_eotS =
        "\21\uffff";
    static final String DFA62_eofS =
        "\21\uffff";
    static final String DFA62_minS =
        "\1\126\1\152\1\22\14\143\2\uffff";
    static final String DFA62_maxS =
        "\1\126\1\152\1\133\14\153\2\uffff";
    static final String DFA62_acceptS =
        "\17\uffff\1\2\1\1";
    static final String DFA62_specialS =
        "\21\uffff}>";
    static final String[] DFA62_transitionS = {
            "\1\1",
            "\1\2",
            "\1\14\1\16\3\uffff\1\6\1\uffff\1\15\1\12\1\13\1\uffff\1\10"+
            "\15\uffff\1\5\2\uffff\1\11\2\uffff\1\4\17\uffff\1\7\31\uffff"+
            "\1\3",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "\1\20\7\uffff\1\17",
            "",
            ""
    };

    static final short[] DFA62_eot = DFA.unpackEncodedString(DFA62_eotS);
    static final short[] DFA62_eof = DFA.unpackEncodedString(DFA62_eofS);
    static final char[] DFA62_min = DFA.unpackEncodedStringToUnsignedChars(DFA62_minS);
    static final char[] DFA62_max = DFA.unpackEncodedStringToUnsignedChars(DFA62_maxS);
    static final short[] DFA62_accept = DFA.unpackEncodedString(DFA62_acceptS);
    static final short[] DFA62_special = DFA.unpackEncodedString(DFA62_specialS);
    static final short[][] DFA62_transition;

    static {
        int numStates = DFA62_transitionS.length;
        DFA62_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA62_transition[i] = DFA.unpackEncodedString(DFA62_transitionS[i]);
        }
    }

    class DFA62 extends DFA {

        public DFA62(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 62;
            this.eot = DFA62_eot;
            this.eof = DFA62_eof;
            this.min = DFA62_min;
            this.max = DFA62_max;
            this.accept = DFA62_accept;
            this.special = DFA62_special;
            this.transition = DFA62_transition;
        }
        public String getDescription() {
            return "2671:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_ruleModel123 = new BitSet(new long[]{0x0000808001000402L});
    public static final BitSet FOLLOW_KEYWORD_88_in_ruleModel137 = new BitSet(new long[]{0x0000000000000000L,0x01C1000000000000L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_ruleModel157 = new BitSet(new long[]{0x0000808000000402L});
    public static final BitSet FOLLOW_KEYWORD_59_in_ruleModel173 = new BitSet(new long[]{0x0000000000000000L,0x0001000000001000L});
    public static final BitSet FOLLOW_ruleLimit_in_ruleModel193 = new BitSet(new long[]{0x0000008000000402L});
    public static final BitSet FOLLOW_KEYWORD_73_in_ruleModel209 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_ruleOffset_in_ruleModel229 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_KEYWORD_98_in_ruleModel245 = new BitSet(new long[]{0x0000000000000000L,0x0001000000040080L});
    public static final BitSet FOLLOW_ruleFetchFirst_in_ruleModel265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFetchFirst_in_entryRuleFetchFirst302 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFetchFirst312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerValue_in_ruleFetchFirst358 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040080L});
    public static final BitSet FOLLOW_KEYWORD_32_in_ruleFetchFirst380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_49_in_ruleFetchFirst408 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_47_in_ruleFetchFirst435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOffset_in_entryRuleOffset469 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOffset479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOffset520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLimit_in_entryRuleLimit559 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLimit569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleLimit617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleLimit641 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleLimit660 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_ruleIntegerValue_in_ruleLimit680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_entryRuleSelectQuery718 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectQuery728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSelectQuery775 = new BitSet(new long[]{0x0081000800008002L});
    public static final BitSet FOLLOW_ruleSelectSubSet_in_ruleSelectQuery795 = new BitSet(new long[]{0x0081000800008002L});
    public static final BitSet FOLLOW_ruleSelectSubSet_in_entryRuleSelectSubSet831 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectSubSet841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_67_in_ruleSelectSubSet887 = new BitSet(new long[]{0x0000010000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_92_in_ruleSelectSubSet915 = new BitSet(new long[]{0x0000010000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_60_in_ruleSelectSubSet943 = new BitSet(new long[]{0x0000010000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_69_in_ruleSelectSubSet971 = new BitSet(new long[]{0x0000010000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleSelectSubSet1004 = new BitSet(new long[]{0x0000010000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSelectSubSet1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_entryRuleSelect1072 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelect1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_74_in_ruleSelect1126 = new BitSet(new long[]{0x0200020000100000L,0x01FFE00100080000L});
    public static final BitSet FOLLOW_KEYWORD_84_in_ruleSelect1151 = new BitSet(new long[]{0x0200020000100000L,0x01FFE00100080000L});
    public static final BitSet FOLLOW_KEYWORD_33_in_ruleSelect1166 = new BitSet(new long[]{0x0000000000000000L,0x0011000000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleSelect1177 = new BitSet(new long[]{0x0200020200120000L,0x01FFE00100080000L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleSelect1193 = new BitSet(new long[]{0x0200020200120000L,0x01FFE00100080000L});
    public static final BitSet FOLLOW_KEYWORD_81_in_ruleSelect1207 = new BitSet(new long[]{0x0200020000120000L,0x01FFE00100080000L});
    public static final BitSet FOLLOW_KEYWORD_94_in_ruleSelect1222 = new BitSet(new long[]{0x0200020000100000L,0x01FFE00100080000L});
    public static final BitSet FOLLOW_ruleColumns_in_ruleSelect1246 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_38_in_ruleSelect1259 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100000000L});
    public static final BitSet FOLLOW_ruleTables_in_ruleSelect1279 = new BitSet(new long[]{0x0100002000200002L});
    public static final BitSet FOLLOW_KEYWORD_68_in_ruleSelect1293 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400018L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect1313 = new BitSet(new long[]{0x0000002000200002L});
    public static final BitSet FOLLOW_KEYWORD_85_in_ruleSelect1329 = new BitSet(new long[]{0x0000000000000000L,0x01C0000000000000L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_ruleSelect1349 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_71_in_ruleSelect1365 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400018L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns1422 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns1432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns1479 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleColumns1502 = new BitSet(new long[]{0x0200020000100000L,0x01FFE00100080000L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns1522 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias1561 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias1571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleColumnOrAlias1618 = new BitSet(new long[]{0x0000000000000002L,0x01C0000004000000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleColumnOrAlias1637 = new BitSet(new long[]{0x0000000000000002L,0x01C0000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias1670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleColumnOrAlias1695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectNameAll_in_ruleColumnOrAlias1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull1762 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull1772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull1819 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleColumnFull1842 = new BitSet(new long[]{0x0000000000000000L,0x01C0000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull1862 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables1901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables1911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables1958 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleTables1981 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100000000L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables2001 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleFromTable_in_entryRuleFromTable2040 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTable2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTable2096 = new BitSet(new long[]{0x6040240100000082L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleFromTableJoin_in_ruleFromTable2117 = new BitSet(new long[]{0x6040240100000082L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleFromTableJoin_in_entryRuleFromTableJoin2153 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTableJoin2163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_ruleFromTableJoin2209 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100000000L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTableJoin2230 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_KEYWORD_21_in_ruleFromTableJoin2243 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400018L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleFromTableJoin2263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias2298 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias2308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias2355 = new BitSet(new long[]{0x0010000400000002L,0x01C0000004000000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleTableOrAlias2382 = new BitSet(new long[]{0x0010000400000002L,0x01C0000004000000L});
    public static final BitSet FOLLOW_rulePivotTable_in_ruleTableOrAlias2405 = new BitSet(new long[]{0x0000000000000002L,0x01C0000004000000L});
    public static final BitSet FOLLOW_ruleUnpivotTable_in_ruleTableOrAlias2432 = new BitSet(new long[]{0x0000000000000002L,0x01C0000004000000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleTableOrAlias2453 = new BitSet(new long[]{0x0000000000000002L,0x01C0000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableOrAlias2486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotTable_in_entryRulePivotTable2522 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotTable2532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_64_in_rulePivotTable2570 = new BitSet(new long[]{0x0000000000000000L,0x0000000100100000L});
    public static final BitSet FOLLOW_KEYWORD_34_in_rulePivotTable2583 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotTable2597 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_rulePivotFunctions_in_rulePivotTable2617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_rulePivotForClause_in_rulePivotTable2638 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulePivotInClause_in_rulePivotTable2659 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotTable2672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotFunctions_in_entryRulePivotFunctions2706 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotFunctions2716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rulePivotFunctions2757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotInClause_in_entryRulePivotInClause2798 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotInClause2808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_rulePivotInClause2846 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotInClause2858 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100004000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rulePivotInClause2879 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_rulePivotInClause2906 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_rulePivotInClauseAny_in_rulePivotInClause2933 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotInClause2947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotInClauseAny_in_entryRulePivotInClauseAny2982 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotInClauseAny2993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rulePivotInClauseAny3031 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rulePivotInClauseAny3045 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rulePivotInClauseAny3058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotTable_in_entryRuleUnpivotTable3099 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotTable3109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_82_in_ruleUnpivotTable3147 = new BitSet(new long[]{0x0000000050000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_78_in_ruleUnpivotTable3161 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_76_in_ruleUnpivotTable3179 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_62_in_ruleUnpivotTable3192 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleUnpivotTable3206 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100000000L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotTable3226 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_rulePivotForClause_in_ruleUnpivotTable3247 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClause_in_ruleUnpivotTable3268 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleUnpivotTable3281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClause_in_entryRuleUnpivotInClause3315 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClause3325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleUnpivotInClause3378 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleUnpivotInClause3402 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_ruleUnpivotInClause3422 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleUnpivotInClause3435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_entryRuleUnpivotInClauseArgs3469 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClauseArgs3479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3526 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleUnpivotInClauseArgs3549 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3569 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_entryRuleUnpivotInClauseArg3608 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClauseArg3618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3664 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleUnpivotInClauseArg3678 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100000000L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotForClause_in_entryRulePivotForClause3735 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotForClause3745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_31_in_rulePivotForClause3783 = new BitSet(new long[]{0x0000000000000000L,0x01C0000100000000L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rulePivotForClause3805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotForClause3824 = new BitSet(new long[]{0x0200020000100000L,0x01FFE00100080000L});
    public static final BitSet FOLLOW_ruleColumns_in_rulePivotForClause3845 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotForClause3857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotColumns_in_entryRulePivotColumns3893 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotColumns3903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotColumns3950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotColumns3969 = new BitSet(new long[]{0x0000000000000000L,0x01C0000000000000L});
    public static final BitSet FOLLOW_rulePivotCols_in_rulePivotColumns3990 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotColumns4002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCols_in_entryRulePivotCols4037 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotCols4047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotCols4094 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rulePivotCols4117 = new BitSet(new long[]{0x0000000000000000L,0x01C0000000000000L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotCols4137 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_rulePivotCol_in_entryRulePivotCol4176 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotCol4186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rulePivotCol4233 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rulePivotCol4256 = new BitSet(new long[]{0x0000000000000000L,0x01C0000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rulePivotCol4276 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull4315 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull4325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull4372 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleTableFull4395 = new BitSet(new long[]{0x0000000000000000L,0x01C0000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull4415 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_ruleDbObjectNameAll_in_entryRuleDbObjectNameAll4454 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectNameAll4464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_ruleDbObjectNameAll4510 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleDbObjectNameAll4523 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleDbObjectNameAll4533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName4567 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectName4577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_ruleDbObjectName4622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns4656 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns4666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4713 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByColumns4736 = new BitSet(new long[]{0x0000000000000000L,0x01C1000000000000L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4756 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull4795 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull4805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull4852 = new BitSet(new long[]{0x0400000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOrderByColumnFull4875 = new BitSet(new long[]{0x0400000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_KEYWORD_29_in_ruleOrderByColumnFull4902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_ruleOrderByColumnFull4930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns4980 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumns4990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns5037 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleGroupByColumns5060 = new BitSet(new long[]{0x0000000000000000L,0x01C0000000000000L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns5080 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull5119 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumnFull5129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleGroupByColumnFull5175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleGroupByColumnFull5202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_entryRuleFullExpression5237 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullExpression5247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleFullExpression5294 = new BitSet(new long[]{0x0000000000000002L,0x0000400020002000L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression5324 = new BitSet(new long[]{0x0000000000000002L,0x0000400020002000L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond5362 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragmentSecond5372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleExpressionFragmentSecond5419 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400018L});
    public static final BitSet FOLLOW_KEYWORD_22_in_ruleExpressionFragmentSecond5447 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400018L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond5482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragmentSecond5506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment5546 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragment5556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment5602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExpressionFragment5629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleExpressionFragment5658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression__in_ruleExpressionFragment5677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragment5703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_ruleExpressionFragment5735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExistsOperator_in_ruleExpressionFragment5762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup5797 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionGroup5807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_46_in_ruleExpressionGroup5862 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_45_in_ruleExpressionGroup5890 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleExpressionGroup5918 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400018L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleExpressionGroup5938 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleExpressionGroup5951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression5985 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression5995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleXExpression6033 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression6054 = new BitSet(new long[]{0x000248002E8C0000L,0x0000000008000002L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression6074 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression6087 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleXExpression6107 = new BitSet(new long[]{0x0000000000000000L,0x0000100800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression6121 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression6141 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleXExpression6156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression__in_entryRuleXExpression_6190 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression_6200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleXExpression_6238 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression_6259 = new BitSet(new long[]{0x000248002E8C0000L,0x0000000008000002L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression_6279 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleXExpression_6292 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleXExpression_6312 = new BitSet(new long[]{0x0000000000000000L,0x0000180000000000L});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleXExpression_6326 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression_6346 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleXExpression_6361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams6395 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionParams6405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams6452 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpressionParams6475 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams6495 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleJRParameter_in_entryRuleJRParameter6534 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRParameter6544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJRParameter6585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression6624 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression6634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleExpression6680 = new BitSet(new long[]{0x0200025088403800L,0x01FF63814BE0001CL});
    public static final BitSet FOLLOW_KEYWORD_79_in_ruleExpression6702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_99_in_ruleExpression6730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_ruleExpression6771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExistsOperator_in_ruleExpression6798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_ruleExpression6825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_ruleExpression6852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_ruleExpression6879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_entryRuleComparison6915 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComparison6925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_10_in_ruleComparison6971 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100005100L});
    public static final BitSet FOLLOW_KEYWORD_18_in_ruleComparison6999 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100005100L});
    public static final BitSet FOLLOW_KEYWORD_8_in_ruleComparison7027 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100005100L});
    public static final BitSet FOLLOW_KEYWORD_16_in_ruleComparison7055 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100005100L});
    public static final BitSet FOLLOW_KEYWORD_9_in_ruleComparison7083 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100005100L});
    public static final BitSet FOLLOW_KEYWORD_17_in_ruleComparison7111 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100005100L});
    public static final BitSet FOLLOW_KEYWORD_14_in_ruleComparison7139 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100005100L});
    public static final BitSet FOLLOW_KEYWORD_23_in_ruleComparison7167 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100005100L});
    public static final BitSet FOLLOW_KEYWORD_28_in_ruleComparison7202 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleComparison7230 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_50_in_ruleComparison7258 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleComparison7294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_entryRuleLike7329 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLike7339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_44_in_ruleLike7385 = new BitSet(new long[]{0x0000020000000000L,0x0120200000000000L});
    public static final BitSet FOLLOW_KEYWORD_86_in_ruleLike7413 = new BitSet(new long[]{0x0000020000000000L,0x0120200000000000L});
    public static final BitSet FOLLOW_ruleLikeOperand_in_ruleLike7448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLikeOperand_in_entryRuleLikeOperand7483 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLikeOperand7493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleLikeOperand7539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleLikeOperand7566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_ruleLikeOperand7593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_ruleLikeOperand7620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_entryRuleBetween7655 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBetween7665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_75_in_ruleBetween7711 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_100_in_ruleBetween7739 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBetween7774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleBetween7787 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBetween7807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_entryRuleInOperator7842 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInOperator7852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_72_in_ruleInOperator7907 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleInOperator7935 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleInOperator7971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandListGroup_in_ruleInOperator7998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExistsOperator_in_entryRuleExistsOperator8034 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExistsOperator8044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_96_in_ruleExistsOperator8099 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_70_in_ruleExistsOperator8127 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleExistsOperator8163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandListGroup_in_ruleExistsOperator8190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandListGroup_in_entryRuleOperandListGroup8226 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandListGroup8236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleOperandListGroup8274 = new BitSet(new long[]{0x0000000000000000L,0x003F600000000000L});
    public static final BitSet FOLLOW_ruleOperandList_in_ruleOperandListGroup8294 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOperandListGroup8307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_entryRuleOperandList8341 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandList8351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleOperandList8398 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOperandList8421 = new BitSet(new long[]{0x0000000000000000L,0x003F600000000000L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleOperandList8441 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleOperand_in_entryRuleOperand8480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperand8490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand8536 = new BitSet(new long[]{0x0000000000000002L,0x0000805480000000L});
    public static final BitSet FOLLOW_KEYWORD_3_in_ruleOperand8561 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_5_in_ruleOperand8590 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperand8617 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_7_in_ruleOperand8646 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_24_in_ruleOperand8675 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand8697 = new BitSet(new long[]{0x0000000000000002L,0x0000805480000000L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment8734 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFragment8744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_ruleOperandFragment8790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_ruleOperandFragment8817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment8844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_ruleOperandFragment8871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleOperandFragment8898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCASE_in_ruleOperandFragment8925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_entryRuleOperandFunction8960 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFunction8970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFNAME_in_ruleOperandFunction9025 = new BitSet(new long[]{0x0200020000100000L,0x01FFE00300001000L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperandFunction9037 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_ruleOpFunctionArg_in_ruleOperandFunction9063 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOperandFunction9078 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleFunctionAnalytical_in_ruleOperandFunction9098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionAnalytical_in_entryRuleFunctionAnalytical9134 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFunctionAnalytical9144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_48_in_ruleFunctionAnalytical9182 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleFunctionAnalytical9194 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ruleAnalyticClause_in_ruleFunctionAnalytical9214 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleFunctionAnalytical9227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticClause_in_entryRuleAnalyticClause9261 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticClause9271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQueryPartitionClause_in_ruleAnalyticClause9318 = new BitSet(new long[]{0x0000000001000042L});
    public static final BitSet FOLLOW_ruleOrderByClause_in_ruleAnalyticClause9339 = new BitSet(new long[]{0x0020000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_ruleWindowingClause_in_ruleAnalyticClause9360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClause_in_entryRuleWindowingClause9398 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClause9408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_49_in_ruleWindowingClause9447 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_65_in_ruleWindowingClause9465 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleWindowingClauseBetween_in_ruleWindowingClause9488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClause9515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseBetween_in_entryRuleWindowingClauseBetween9550 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseBetween9560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_75_in_ruleWindowingClauseBetween9598 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClauseBetween9618 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleWindowingClauseBetween9631 = new BitSet(new long[]{0x0200020008000230L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandFollowing_in_ruleWindowingClauseBetween9651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandFollowing_in_entryRuleWindowingClauseOperandFollowing9686 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseOperandFollowing9696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_104_in_ruleWindowingClauseOperandFollowing9744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_97_in_ruleWindowingClauseOperandFollowing9762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandFollowing9789 = new BitSet(new long[]{0x0000000000014000L});
    public static final BitSet FOLLOW_KEYWORD_93_in_ruleWindowingClauseOperandFollowing9803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_91_in_ruleWindowingClauseOperandFollowing9821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_entryRuleWindowingClauseOperandPreceding9858 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseOperandPreceding9868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_105_in_ruleWindowingClauseOperandPreceding9916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_97_in_ruleWindowingClauseOperandPreceding9934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandPreceding9961 = new BitSet(new long[]{0x0000000000014000L});
    public static final BitSet FOLLOW_KEYWORD_93_in_ruleWindowingClauseOperandPreceding9975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_91_in_ruleWindowingClauseOperandPreceding9993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClause_in_entryRuleOrderByClause10030 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClause10040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_88_in_ruleOrderByClause10079 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_103_in_ruleOrderByClause10097 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOrderByClauseArgs_in_ruleOrderByClause10118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClauseArgs_in_entryRuleOrderByClauseArgs10153 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClauseArgs10163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs10210 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByClauseArgs10233 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs10253 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_entryRuleOrderByClauseArg10292 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClauseArg10302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleOrderByClauseArg10348 = new BitSet(new long[]{0x0404000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_KEYWORD_29_in_ruleOrderByClauseArg10362 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_ruleOrderByClauseArg10380 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_KEYWORD_62_in_ruleOrderByClauseArg10395 = new BitSet(new long[]{0x8000100000000000L});
    public static final BitSet FOLLOW_KEYWORD_56_in_ruleOrderByClauseArg10408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_41_in_ruleOrderByClauseArg10426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQueryPartitionClause_in_entryRuleQueryPartitionClause10463 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQueryPartitionClause10473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_101_in_ruleQueryPartitionClause10511 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause10532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleQueryPartitionClause10552 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause10573 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleQueryPartitionClause10585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_entryRuleAnalyticExprArgs10621 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticExprArgs10631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs10678 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleAnalyticExprArgs10701 = new BitSet(new long[]{0x0200020008000220L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs10721 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_entryRuleAnalyticExprArg10760 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticExprArg10770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleAnalyticExprArg10816 = new BitSet(new long[]{0x0000000000000002L,0x01C0000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleAnalyticExprArg10837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArg_in_entryRuleOpFunctionArg10873 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArg10883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg10930 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOpFunctionArg10953 = new BitSet(new long[]{0x0200020000100000L,0x01FF600100001000L});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg10973 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_entryRuleOpFunctionArgOperand11012 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArgOperand11022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgAgregate_in_ruleOpFunctionArgOperand11069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionArgOperand11088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_entryRuleOpFunctionCast11125 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionCast11135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_53_in_ruleOpFunctionCast11173 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionCast11193 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleOpFunctionCast11206 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOpFunctionCast11222 = new BitSet(new long[]{0x0000000000000000L,0x0000000300000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleOpFunctionCast11241 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOpFunctionCast11257 = new BitSet(new long[]{0x0000000000000000L,0x0000000A00000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOpFunctionCast11276 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOpFunctionCast11292 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOpFunctionCast11312 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOpFunctionCast11326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgAgregate_in_entryRuleOpFunctionArgAgregate11360 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArgAgregate11370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleOpFunctionArgAgregate11409 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_KEYWORD_84_in_ruleOpFunctionArgAgregate11427 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionArgAgregate11449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment11483 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOperandFragment11493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_ruleXOperandFragment11539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment11566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleXOperandFragment11593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand11628 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameterOperand11638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRPARAM_in_ruleParameterOperand11679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand11718 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclamationParameterOperand11728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExclamationParameterOperand11769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand11808 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOperand11818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOperand11864 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_KEYWORD_25_in_ruleColumnOperand11883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand11930 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubQueryOperand11940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleSubQueryOperand11987 = new BitSet(new long[]{0x0000010000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_ruleSubQueryOperand12007 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleSubQueryOperand12020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand12054 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarOperand12064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleScalarOperand12106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleScalarOperand12138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand12161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleScalarOperand12189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIME_in_ruleScalarOperand12217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand12245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCASE_in_entryRuleSQLCASE12285 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSQLCASE12295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_35_in_ruleSQLCASE12333 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400418L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSQLCASE12353 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400418L});
    public static final BitSet FOLLOW_ruleSQLCaseWhens_in_ruleSQLCASE12375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_KEYWORD_30_in_ruleSQLCASE12388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCaseWhens_in_entryRuleSQLCaseWhens12422 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSQLCaseWhens12432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens12479 = new BitSet(new long[]{0x0200025000002002L,0x01FF600108400418L});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens12509 = new BitSet(new long[]{0x0200025000002002L,0x01FF600108400418L});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_entryRuleSqlCaseWhen12547 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSqlCaseWhen12557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_52_in_ruleSqlCaseWhen12595 = new BitSet(new long[]{0x0200025000002000L,0x01FF600108400018L});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSqlCaseWhen12615 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_KEYWORD_51_in_ruleSqlCaseWhen12628 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleSqlCaseWhen12648 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_ruleSqlCaseWhen12662 = new BitSet(new long[]{0x0200020000000000L,0x01FF600100000000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleSqlCaseWhen12682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_entryRuleJoinType12720 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJoinType12731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_80_in_ruleJoinType12770 = new BitSet(new long[]{0x6040240000000080L,0x0000000000000001L});
    public static final BitSet FOLLOW_KEYWORD_57_in_ruleJoinType12786 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_42_in_ruleJoinType12807 = new BitSet(new long[]{0x4008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_66_in_ruleJoinType12826 = new BitSet(new long[]{0x4008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_39_in_ruleJoinType12845 = new BitSet(new long[]{0x4008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_63_in_ruleJoinType12860 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_54_in_ruleJoinType12882 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_102_in_ruleJoinType12901 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_40_in_ruleJoinType12916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_entryRuleDBID12956 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBID12967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDBID13007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DBNAME_in_ruleDBID13033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDBID13059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_entryRuleStringOperand13104 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringOperand13115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING__in_ruleStringOperand13154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFNAME_in_entryRuleFNAME13198 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFNAME13209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFNAME13249 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleFNAME13267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerValue_in_entryRuleIntegerValue13306 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerValue13316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerValue13357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleXFunction13414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_61_in_ruleXFunction13436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_55_in_ruleXFunction13458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_87_in_ruleXFunction13480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_43_in_ruleXFunction13502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_77_in_ruleXFunction13524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_58_in_ruleXFunction13546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_90_in_ruleXFunction13568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_75_in_ruleXFunction13590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_95_in_ruleXFunction13612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_89_in_ruleXFunction13634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_83_in_ruleXFunction13656 = new BitSet(new long[]{0x0000000000000002L});

}