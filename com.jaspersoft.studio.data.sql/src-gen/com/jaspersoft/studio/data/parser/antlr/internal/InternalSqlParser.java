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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_125", "KEYWORD_126", "KEYWORD_123", "KEYWORD_124", "KEYWORD_122", "KEYWORD_121", "KEYWORD_120", "KEYWORD_118", "KEYWORD_119", "KEYWORD_117", "KEYWORD_110", "KEYWORD_111", "KEYWORD_112", "KEYWORD_113", "KEYWORD_114", "KEYWORD_115", "KEYWORD_116", "KEYWORD_106", "KEYWORD_107", "KEYWORD_108", "KEYWORD_109", "KEYWORD_101", "KEYWORD_102", "KEYWORD_103", "KEYWORD_104", "KEYWORD_105", "KEYWORD_92", "KEYWORD_93", "KEYWORD_94", "KEYWORD_95", "KEYWORD_96", "KEYWORD_97", "KEYWORD_98", "KEYWORD_99", "KEYWORD_100", "KEYWORD_82", "KEYWORD_83", "KEYWORD_84", "KEYWORD_85", "KEYWORD_86", "KEYWORD_87", "KEYWORD_88", "KEYWORD_89", "KEYWORD_90", "KEYWORD_91", "KEYWORD_74", "KEYWORD_75", "KEYWORD_76", "KEYWORD_77", "KEYWORD_78", "KEYWORD_79", "KEYWORD_80", "KEYWORD_81", "KEYWORD_57", "KEYWORD_58", "KEYWORD_59", "KEYWORD_60", "KEYWORD_61", "KEYWORD_62", "KEYWORD_63", "KEYWORD_64", "KEYWORD_65", "KEYWORD_66", "KEYWORD_67", "KEYWORD_68", "KEYWORD_69", "KEYWORD_70", "KEYWORD_71", "KEYWORD_72", "KEYWORD_73", "KEYWORD_36", "KEYWORD_37", "KEYWORD_38", "KEYWORD_39", "KEYWORD_40", "KEYWORD_41", "KEYWORD_42", "KEYWORD_43", "KEYWORD_44", "KEYWORD_45", "KEYWORD_46", "KEYWORD_47", "KEYWORD_48", "KEYWORD_49", "KEYWORD_50", "KEYWORD_51", "KEYWORD_52", "KEYWORD_53", "KEYWORD_54", "KEYWORD_55", "KEYWORD_56", "KEYWORD_25", "KEYWORD_26", "KEYWORD_27", "KEYWORD_28", "KEYWORD_29", "KEYWORD_30", "KEYWORD_31", "KEYWORD_32", "KEYWORD_33", "KEYWORD_34", "KEYWORD_35", "KEYWORD_14", "KEYWORD_15", "KEYWORD_16", "KEYWORD_17", "KEYWORD_18", "KEYWORD_19", "KEYWORD_20", "KEYWORD_21", "KEYWORD_22", "KEYWORD_23", "KEYWORD_24", "KEYWORD_1", "KEYWORD_2", "KEYWORD_3", "KEYWORD_4", "KEYWORD_5", "KEYWORD_6", "KEYWORD_7", "KEYWORD_8", "KEYWORD_9", "KEYWORD_10", "KEYWORD_11", "KEYWORD_12", "KEYWORD_13", "RULE_JRPARAM", "RULE_JRNPARAM", "RULE_STAR", "RULE_UNSIGNED", "RULE_INT", "RULE_SIGNED_DOUBLE", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_STRING_", "RULE_STRING", "RULE_DBNAME", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=142;
    public static final int RULE_JRNPARAM=131;
    public static final int RULE_DATE=136;
    public static final int RULE_ANY_OTHER=146;
    public static final int KEYWORD_56=94;
    public static final int KEYWORD_55=93;
    public static final int KEYWORD_54=92;
    public static final int KEYWORD_53=91;
    public static final int KEYWORD_52=90;
    public static final int KEYWORD_51=89;
    public static final int KEYWORD_50=88;
    public static final int EOF=-1;
    public static final int KEYWORD_59=59;
    public static final int KEYWORD_58=58;
    public static final int KEYWORD_57=57;
    public static final int KEYWORD_65=65;
    public static final int KEYWORD_64=64;
    public static final int KEYWORD_67=67;
    public static final int RULE_SIGNED_DOUBLE=135;
    public static final int KEYWORD_66=66;
    public static final int KEYWORD_61=61;
    public static final int KEYWORD_60=60;
    public static final int KEYWORD_63=63;
    public static final int KEYWORD_62=62;
    public static final int KEYWORD_69=69;
    public static final int KEYWORD_68=68;
    public static final int KEYWORD_111=15;
    public static final int KEYWORD_110=14;
    public static final int KEYWORD_113=17;
    public static final int KEYWORD_112=16;
    public static final int KEYWORD_115=19;
    public static final int KEYWORD_114=18;
    public static final int KEYWORD_105=29;
    public static final int KEYWORD_30=100;
    public static final int KEYWORD_106=21;
    public static final int KEYWORD_107=22;
    public static final int KEYWORD_108=23;
    public static final int KEYWORD_109=24;
    public static final int KEYWORD_34=104;
    public static final int KEYWORD_33=103;
    public static final int KEYWORD_32=102;
    public static final int KEYWORD_31=101;
    public static final int KEYWORD_38=76;
    public static final int KEYWORD_37=75;
    public static final int KEYWORD_36=74;
    public static final int KEYWORD_35=105;
    public static final int RULE_ML_COMMENT=143;
    public static final int KEYWORD_39=77;
    public static final int RULE_STRING=140;
    public static final int KEYWORD_122=8;
    public static final int KEYWORD_121=9;
    public static final int KEYWORD_120=10;
    public static final int KEYWORD_126=5;
    public static final int KEYWORD_125=4;
    public static final int KEYWORD_124=7;
    public static final int KEYWORD_123=6;
    public static final int KEYWORD_118=11;
    public static final int KEYWORD_119=12;
    public static final int KEYWORD_116=20;
    public static final int KEYWORD_41=79;
    public static final int KEYWORD_117=13;
    public static final int KEYWORD_40=78;
    public static final int KEYWORD_43=81;
    public static final int KEYWORD_42=80;
    public static final int KEYWORD_45=83;
    public static final int KEYWORD_44=82;
    public static final int KEYWORD_47=85;
    public static final int KEYWORD_46=84;
    public static final int KEYWORD_49=87;
    public static final int KEYWORD_48=86;
    public static final int KEYWORD_97=35;
    public static final int RULE_UNSIGNED=133;
    public static final int KEYWORD_98=36;
    public static final int KEYWORD_99=37;
    public static final int KEYWORD_93=31;
    public static final int KEYWORD_94=32;
    public static final int KEYWORD_95=33;
    public static final int KEYWORD_96=34;
    public static final int KEYWORD_19=111;
    public static final int KEYWORD_90=47;
    public static final int KEYWORD_17=109;
    public static final int KEYWORD_92=30;
    public static final int KEYWORD_18=110;
    public static final int KEYWORD_91=48;
    public static final int KEYWORD_15=107;
    public static final int KEYWORD_16=108;
    public static final int KEYWORD_13=129;
    public static final int RULE_STRING_=139;
    public static final int KEYWORD_14=106;
    public static final int KEYWORD_11=127;
    public static final int KEYWORD_12=128;
    public static final int KEYWORD_10=126;
    public static final int KEYWORD_103=27;
    public static final int KEYWORD_104=28;
    public static final int KEYWORD_101=25;
    public static final int KEYWORD_102=26;
    public static final int KEYWORD_100=38;
    public static final int KEYWORD_6=122;
    public static final int KEYWORD_7=123;
    public static final int KEYWORD_8=124;
    public static final int KEYWORD_9=125;
    public static final int KEYWORD_28=98;
    public static final int RULE_TIME=137;
    public static final int KEYWORD_29=99;
    public static final int RULE_INT=134;
    public static final int RULE_TIMESTAMP=138;
    public static final int KEYWORD_24=116;
    public static final int KEYWORD_25=95;
    public static final int RULE_DBNAME=141;
    public static final int KEYWORD_26=96;
    public static final int KEYWORD_27=97;
    public static final int KEYWORD_20=112;
    public static final int KEYWORD_21=113;
    public static final int KEYWORD_22=114;
    public static final int KEYWORD_23=115;
    public static final int KEYWORD_79=54;
    public static final int KEYWORD_71=71;
    public static final int KEYWORD_72=72;
    public static final int KEYWORD_73=73;
    public static final int KEYWORD_74=49;
    public static final int KEYWORD_75=50;
    public static final int KEYWORD_76=51;
    public static final int KEYWORD_77=52;
    public static final int KEYWORD_78=53;
    public static final int KEYWORD_1=117;
    public static final int KEYWORD_5=121;
    public static final int KEYWORD_4=120;
    public static final int KEYWORD_70=70;
    public static final int KEYWORD_3=119;
    public static final int KEYWORD_2=118;
    public static final int RULE_SL_COMMENT=144;
    public static final int RULE_STAR=132;
    public static final int KEYWORD_84=41;
    public static final int KEYWORD_85=42;
    public static final int KEYWORD_82=39;
    public static final int KEYWORD_83=40;
    public static final int KEYWORD_88=45;
    public static final int KEYWORD_89=46;
    public static final int RULE_JRPARAM=130;
    public static final int KEYWORD_86=43;
    public static final int KEYWORD_87=44;
    public static final int KEYWORD_81=56;
    public static final int KEYWORD_80=55;
    public static final int RULE_WS=145;

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:71:1: ruleModel returns [EObject current=null] : ( (lv_query_0_0= ruleSelectQuery ) ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_query_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:74:28: ( ( (lv_query_0_0= ruleSelectQuery ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: ( (lv_query_0_0= ruleSelectQuery ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:75:1: ( (lv_query_0_0= ruleSelectQuery ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:76:1: (lv_query_0_0= ruleSelectQuery )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:76:1: (lv_query_0_0= ruleSelectQuery )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:77:3: lv_query_0_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getModelAccess().getQuerySelectQueryParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleSelectQuery_in_ruleModel122);
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


            }

             leaveRule(); 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:101:1: entryRuleFetchFirst returns [EObject current=null] : iv_ruleFetchFirst= ruleFetchFirst EOF ;
    public final EObject entryRuleFetchFirst() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFetchFirst = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:102:2: (iv_ruleFetchFirst= ruleFetchFirst EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:103:2: iv_ruleFetchFirst= ruleFetchFirst EOF
            {
             newCompositeNode(grammarAccess.getFetchFirstRule()); 
            pushFollow(FOLLOW_ruleFetchFirst_in_entryRuleFetchFirst156);
            iv_ruleFetchFirst=ruleFetchFirst();

            state._fsp--;

             current =iv_ruleFetchFirst; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFetchFirst166); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:110:1: ruleFetchFirst returns [EObject current=null] : ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 ) ) ) otherlv_2= KEYWORD_49 ) ;
    public final EObject ruleFetchFirst() throws RecognitionException {
        EObject current = null;

        Token lv_row_1_1=null;
        Token lv_row_1_2=null;
        Token otherlv_2=null;
        EObject lv_fetchFirst_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:113:28: ( ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 ) ) ) otherlv_2= KEYWORD_49 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:114:1: ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 ) ) ) otherlv_2= KEYWORD_49 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:114:1: ( ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 ) ) ) otherlv_2= KEYWORD_49 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:114:2: ( (lv_fetchFirst_0_0= ruleIntegerValue ) )? ( ( (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 ) ) ) otherlv_2= KEYWORD_49
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:114:2: ( (lv_fetchFirst_0_0= ruleIntegerValue ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_INT) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:115:1: (lv_fetchFirst_0_0= ruleIntegerValue )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:115:1: (lv_fetchFirst_0_0= ruleIntegerValue )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:116:3: lv_fetchFirst_0_0= ruleIntegerValue
                    {
                     
                    	        newCompositeNode(grammarAccess.getFetchFirstAccess().getFetchFirstIntegerValueParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleIntegerValue_in_ruleFetchFirst212);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:132:3: ( ( (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:133:1: ( (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:133:1: ( (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:134:1: (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:134:1: (lv_row_1_1= KEYWORD_33 | lv_row_1_2= KEYWORD_51 )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==KEYWORD_33) ) {
                alt2=1;
            }
            else if ( (LA2_0==KEYWORD_51) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:135:3: lv_row_1_1= KEYWORD_33
                    {
                    lv_row_1_1=(Token)match(input,KEYWORD_33,FOLLOW_KEYWORD_33_in_ruleFetchFirst234); 

                            newLeafNode(lv_row_1_1, grammarAccess.getFetchFirstAccess().getRowROWKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getFetchFirstRule());
                    	        }
                           		setWithLastConsumed(current, "row", lv_row_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:148:8: lv_row_1_2= KEYWORD_51
                    {
                    lv_row_1_2=(Token)match(input,KEYWORD_51,FOLLOW_KEYWORD_51_in_ruleFetchFirst262); 

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

            otherlv_2=(Token)match(input,KEYWORD_49,FOLLOW_KEYWORD_49_in_ruleFetchFirst289); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:177:1: entryRuleOffset returns [EObject current=null] : iv_ruleOffset= ruleOffset EOF ;
    public final EObject entryRuleOffset() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOffset = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:178:2: (iv_ruleOffset= ruleOffset EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:179:2: iv_ruleOffset= ruleOffset EOF
            {
             newCompositeNode(grammarAccess.getOffsetRule()); 
            pushFollow(FOLLOW_ruleOffset_in_entryRuleOffset323);
            iv_ruleOffset=ruleOffset();

            state._fsp--;

             current =iv_ruleOffset; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOffset333); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:186:1: ruleOffset returns [EObject current=null] : ( (lv_offset_0_0= RULE_INT ) ) ;
    public final EObject ruleOffset() throws RecognitionException {
        EObject current = null;

        Token lv_offset_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:189:28: ( ( (lv_offset_0_0= RULE_INT ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:190:1: ( (lv_offset_0_0= RULE_INT ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:190:1: ( (lv_offset_0_0= RULE_INT ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:191:1: (lv_offset_0_0= RULE_INT )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:191:1: (lv_offset_0_0= RULE_INT )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:192:3: lv_offset_0_0= RULE_INT
            {
            lv_offset_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOffset374); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:216:1: entryRuleLimit returns [EObject current=null] : iv_ruleLimit= ruleLimit EOF ;
    public final EObject entryRuleLimit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLimit = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:217:2: (iv_ruleLimit= ruleLimit EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:218:2: iv_ruleLimit= ruleLimit EOF
            {
             newCompositeNode(grammarAccess.getLimitRule()); 
            pushFollow(FOLLOW_ruleLimit_in_entryRuleLimit413);
            iv_ruleLimit=ruleLimit();

            state._fsp--;

             current =iv_ruleLimit; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLimit423); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:225:1: ruleLimit returns [EObject current=null] : ( ( () otherlv_1= KEYWORD_26 ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) ) ;
    public final EObject ruleLimit() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_l1_2_0=null;
        Token otherlv_3=null;
        Token lv_l2_4_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:228:28: ( ( ( () otherlv_1= KEYWORD_26 ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:229:1: ( ( () otherlv_1= KEYWORD_26 ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:229:1: ( ( () otherlv_1= KEYWORD_26 ) | ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_26) ) {
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:229:2: ( () otherlv_1= KEYWORD_26 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:229:2: ( () otherlv_1= KEYWORD_26 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:229:3: () otherlv_1= KEYWORD_26
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:229:3: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:230:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getLimitAccess().getLimitAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleLimit471); 

                        	newLeafNode(otherlv_1, grammarAccess.getLimitAccess().getALLKeyword_0_1());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:241:6: ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:241:6: ( ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) ) )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:241:7: ( (lv_l1_2_0= RULE_UNSIGNED ) ) (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:241:7: ( (lv_l1_2_0= RULE_UNSIGNED ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:242:1: (lv_l1_2_0= RULE_UNSIGNED )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:242:1: (lv_l1_2_0= RULE_UNSIGNED )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:243:3: lv_l1_2_0= RULE_UNSIGNED
                    {
                    lv_l1_2_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_RULE_UNSIGNED_in_ruleLimit495); 

                    			newLeafNode(lv_l1_2_0, grammarAccess.getLimitAccess().getL1UNSIGNEDTerminalRuleCall_1_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLimitRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"l1",
                            		lv_l1_2_0, 
                            		"UNSIGNED");
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:259:2: (otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) ) )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==KEYWORD_4) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:260:2: otherlv_3= KEYWORD_4 ( (lv_l2_4_0= RULE_UNSIGNED ) )
                            {
                            otherlv_3=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleLimit514); 

                                	newLeafNode(otherlv_3, grammarAccess.getLimitAccess().getCommaKeyword_1_1_0());
                                
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:264:1: ( (lv_l2_4_0= RULE_UNSIGNED ) )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:265:1: (lv_l2_4_0= RULE_UNSIGNED )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:265:1: (lv_l2_4_0= RULE_UNSIGNED )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:266:3: lv_l2_4_0= RULE_UNSIGNED
                            {
                            lv_l2_4_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_RULE_UNSIGNED_in_ruleLimit530); 

                            			newLeafNode(lv_l2_4_0, grammarAccess.getLimitAccess().getL2UNSIGNEDTerminalRuleCall_1_1_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getLimitRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"l2",
                                    		lv_l2_4_0, 
                                    		"UNSIGNED");
                            	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:290:1: entryRuleSelectQuery returns [EObject current=null] : iv_ruleSelectQuery= ruleSelectQuery EOF ;
    public final EObject entryRuleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectQuery = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:291:2: (iv_ruleSelectQuery= ruleSelectQuery EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:292:2: iv_ruleSelectQuery= ruleSelectQuery EOF
            {
             newCompositeNode(grammarAccess.getSelectQueryRule()); 
            pushFollow(FOLLOW_ruleSelectQuery_in_entryRuleSelectQuery573);
            iv_ruleSelectQuery=ruleSelectQuery();

            state._fsp--;

             current =iv_ruleSelectQuery; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectQuery583); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:299:1: ruleSelectQuery returns [EObject current=null] : (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) ;
    public final EObject ruleSelectQuery() throws RecognitionException {
        EObject current = null;

        EObject this_Select_0 = null;

        EObject lv_op_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:302:28: ( (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:303:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:303:1: (this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:304:5: this_Select_0= ruleSelect ( (lv_op_1_0= ruleSelectSubSet ) )*
            {
             
                    newCompositeNode(grammarAccess.getSelectQueryAccess().getSelectParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSelect_in_ruleSelectQuery630);
            this_Select_0=ruleSelect();

            state._fsp--;


                    current = this_Select_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:312:1: ( (lv_op_1_0= ruleSelectSubSet ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==KEYWORD_102||LA5_0==KEYWORD_74||LA5_0==KEYWORD_64||LA5_0==KEYWORD_72) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:313:1: (lv_op_1_0= ruleSelectSubSet )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:313:1: (lv_op_1_0= ruleSelectSubSet )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:314:3: lv_op_1_0= ruleSelectSubSet
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSelectQueryAccess().getOpSelectSubSetParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSelectSubSet_in_ruleSelectQuery650);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:338:1: entryRuleSelectSubSet returns [EObject current=null] : iv_ruleSelectSubSet= ruleSelectSubSet EOF ;
    public final EObject entryRuleSelectSubSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectSubSet = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:339:2: (iv_ruleSelectSubSet= ruleSelectSubSet EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:340:2: iv_ruleSelectSubSet= ruleSelectSubSet EOF
            {
             newCompositeNode(grammarAccess.getSelectSubSetRule()); 
            pushFollow(FOLLOW_ruleSelectSubSet_in_entryRuleSelectSubSet686);
            iv_ruleSelectSubSet=ruleSelectSubSet();

            state._fsp--;

             current =iv_ruleSelectSubSet; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectSubSet696); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:347:1: ruleSelectSubSet returns [EObject current=null] : ( ( ( (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:350:28: ( ( ( ( (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:351:1: ( ( ( (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:351:1: ( ( ( (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:351:2: ( ( (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 ) ) ) ( (lv_all_1_0= KEYWORD_26 ) )? ( (lv_query_2_0= ruleSelect ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:351:2: ( ( (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:352:1: ( (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:352:1: ( (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:353:1: (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:353:1: (lv_op_0_1= KEYWORD_72 | lv_op_0_2= KEYWORD_102 | lv_op_0_3= KEYWORD_64 | lv_op_0_4= KEYWORD_74 )
            int alt6=4;
            switch ( input.LA(1) ) {
            case KEYWORD_72:
                {
                alt6=1;
                }
                break;
            case KEYWORD_102:
                {
                alt6=2;
                }
                break;
            case KEYWORD_64:
                {
                alt6=3;
                }
                break;
            case KEYWORD_74:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:354:3: lv_op_0_1= KEYWORD_72
                    {
                    lv_op_0_1=(Token)match(input,KEYWORD_72,FOLLOW_KEYWORD_72_in_ruleSelectSubSet742); 

                            newLeafNode(lv_op_0_1, grammarAccess.getSelectSubSetAccess().getOpUNIONKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:367:8: lv_op_0_2= KEYWORD_102
                    {
                    lv_op_0_2=(Token)match(input,KEYWORD_102,FOLLOW_KEYWORD_102_in_ruleSelectSubSet770); 

                            newLeafNode(lv_op_0_2, grammarAccess.getSelectSubSetAccess().getOpINTERSECTKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:380:8: lv_op_0_3= KEYWORD_64
                    {
                    lv_op_0_3=(Token)match(input,KEYWORD_64,FOLLOW_KEYWORD_64_in_ruleSelectSubSet798); 

                            newLeafNode(lv_op_0_3, grammarAccess.getSelectSubSetAccess().getOpMINUSKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:393:8: lv_op_0_4= KEYWORD_74
                    {
                    lv_op_0_4=(Token)match(input,KEYWORD_74,FOLLOW_KEYWORD_74_in_ruleSelectSubSet826); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:409:2: ( (lv_all_1_0= KEYWORD_26 ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==KEYWORD_26) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:410:1: (lv_all_1_0= KEYWORD_26 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:410:1: (lv_all_1_0= KEYWORD_26 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:411:3: lv_all_1_0= KEYWORD_26
                    {
                    lv_all_1_0=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleSelectSubSet859); 

                            newLeafNode(lv_all_1_0, grammarAccess.getSelectSubSetAccess().getAllALLKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectSubSetRule());
                    	        }
                           		setWithLastConsumed(current, "all", lv_all_1_0, "ALL");
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:425:3: ( (lv_query_2_0= ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:426:1: (lv_query_2_0= ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:426:1: (lv_query_2_0= ruleSelect )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:427:3: lv_query_2_0= ruleSelect
            {
             
            	        newCompositeNode(grammarAccess.getSelectSubSetAccess().getQuerySelectParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelect_in_ruleSelectSubSet892);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:451:1: entryRuleSelect returns [EObject current=null] : iv_ruleSelect= ruleSelect EOF ;
    public final EObject entryRuleSelect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelect = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:452:2: (iv_ruleSelect= ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:453:2: iv_ruleSelect= ruleSelect EOF
            {
             newCompositeNode(grammarAccess.getSelectRule()); 
            pushFollow(FOLLOW_ruleSelect_in_entryRuleSelect927);
            iv_ruleSelect=ruleSelect();

            state._fsp--;

             current =iv_ruleSelect; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelect937); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:460:1: ruleSelect returns [EObject current=null] : ( ( (lv_select_0_0= KEYWORD_81 ) ) (otherlv_1= KEYWORD_94 )? (otherlv_2= KEYWORD_34 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_89 )? (otherlv_6= KEYWORD_104 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_39 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_73 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_95 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_76 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= KEYWORD_98 ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= KEYWORD_63 ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= KEYWORD_79 ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= KEYWORD_111 ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:463:28: ( ( ( (lv_select_0_0= KEYWORD_81 ) ) (otherlv_1= KEYWORD_94 )? (otherlv_2= KEYWORD_34 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_89 )? (otherlv_6= KEYWORD_104 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_39 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_73 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_95 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_76 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= KEYWORD_98 ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= KEYWORD_63 ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= KEYWORD_79 ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= KEYWORD_111 ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:464:1: ( ( (lv_select_0_0= KEYWORD_81 ) ) (otherlv_1= KEYWORD_94 )? (otherlv_2= KEYWORD_34 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_89 )? (otherlv_6= KEYWORD_104 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_39 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_73 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_95 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_76 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= KEYWORD_98 ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= KEYWORD_63 ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= KEYWORD_79 ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= KEYWORD_111 ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:464:1: ( ( (lv_select_0_0= KEYWORD_81 ) ) (otherlv_1= KEYWORD_94 )? (otherlv_2= KEYWORD_34 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_89 )? (otherlv_6= KEYWORD_104 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_39 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_73 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_95 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_76 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= KEYWORD_98 ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= KEYWORD_63 ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= KEYWORD_79 ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= KEYWORD_111 ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:464:2: ( (lv_select_0_0= KEYWORD_81 ) ) (otherlv_1= KEYWORD_94 )? (otherlv_2= KEYWORD_34 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_89 )? (otherlv_6= KEYWORD_104 )? )? ( (lv_cols_7_0= ruleColumns ) ) otherlv_8= KEYWORD_39 ( (lv_tbl_9_0= ruleTables ) ) (otherlv_10= KEYWORD_73 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )? (otherlv_12= KEYWORD_95 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )? (otherlv_14= KEYWORD_76 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )? (otherlv_16= KEYWORD_98 ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )? (otherlv_18= KEYWORD_63 ( (lv_lim_19_0= ruleLimit ) ) )? (otherlv_20= KEYWORD_79 ( (lv_offset_21_0= ruleOffset ) ) )? (otherlv_22= KEYWORD_111 ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:464:2: ( (lv_select_0_0= KEYWORD_81 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:465:1: (lv_select_0_0= KEYWORD_81 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:465:1: (lv_select_0_0= KEYWORD_81 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:466:3: lv_select_0_0= KEYWORD_81
            {
            lv_select_0_0=(Token)match(input,KEYWORD_81,FOLLOW_KEYWORD_81_in_ruleSelect981); 

                    newLeafNode(lv_select_0_0, grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectRule());
            	        }
                   		setWithLastConsumed(current, "select", lv_select_0_0, "SELECT");
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:480:2: (otherlv_1= KEYWORD_94 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==KEYWORD_94) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:481:2: otherlv_1= KEYWORD_94
                    {
                    otherlv_1=(Token)match(input,KEYWORD_94,FOLLOW_KEYWORD_94_in_ruleSelect1006); 

                        	newLeafNode(otherlv_1, grammarAccess.getSelectAccess().getDISTINCTKeyword_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:485:3: (otherlv_2= KEYWORD_34 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_89 )? (otherlv_6= KEYWORD_104 )? )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==KEYWORD_34) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:486:2: otherlv_2= KEYWORD_34 (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE ) (otherlv_5= KEYWORD_89 )? (otherlv_6= KEYWORD_104 )?
                    {
                    otherlv_2=(Token)match(input,KEYWORD_34,FOLLOW_KEYWORD_34_in_ruleSelect1021); 

                        	newLeafNode(otherlv_2, grammarAccess.getSelectAccess().getTOPKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:490:1: (this_INT_3= RULE_INT | this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE )
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
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:490:2: this_INT_3= RULE_INT
                            {
                            this_INT_3=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleSelect1032); 
                             
                                newLeafNode(this_INT_3, grammarAccess.getSelectAccess().getINTTerminalRuleCall_2_1_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:495:6: this_SIGNED_DOUBLE_4= RULE_SIGNED_DOUBLE
                            {
                            this_SIGNED_DOUBLE_4=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleSelect1048); 
                             
                                newLeafNode(this_SIGNED_DOUBLE_4, grammarAccess.getSelectAccess().getSIGNED_DOUBLETerminalRuleCall_2_1_1()); 
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:499:2: (otherlv_5= KEYWORD_89 )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==KEYWORD_89) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:500:2: otherlv_5= KEYWORD_89
                            {
                            otherlv_5=(Token)match(input,KEYWORD_89,FOLLOW_KEYWORD_89_in_ruleSelect1062); 

                                	newLeafNode(otherlv_5, grammarAccess.getSelectAccess().getPERCENTKeyword_2_2());
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:504:3: (otherlv_6= KEYWORD_104 )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==KEYWORD_104) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:505:2: otherlv_6= KEYWORD_104
                            {
                            otherlv_6=(Token)match(input,KEYWORD_104,FOLLOW_KEYWORD_104_in_ruleSelect1077); 

                                	newLeafNode(otherlv_6, grammarAccess.getSelectAccess().getWITHTIESKeyword_2_3());
                                

                            }
                            break;

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:509:5: ( (lv_cols_7_0= ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:510:1: (lv_cols_7_0= ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:510:1: (lv_cols_7_0= ruleColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:511:3: lv_cols_7_0= ruleColumns
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleColumns_in_ruleSelect1101);
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

            otherlv_8=(Token)match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_ruleSelect1114); 

                	newLeafNode(otherlv_8, grammarAccess.getSelectAccess().getFROMKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:532:1: ( (lv_tbl_9_0= ruleTables ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:533:1: (lv_tbl_9_0= ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:533:1: (lv_tbl_9_0= ruleTables )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:534:3: lv_tbl_9_0= ruleTables
            {
             
            	        newCompositeNode(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleTables_in_ruleSelect1134);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:550:2: (otherlv_10= KEYWORD_73 ( (lv_whereExpression_11_0= ruleFullExpression ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==KEYWORD_73) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:551:2: otherlv_10= KEYWORD_73 ( (lv_whereExpression_11_0= ruleFullExpression ) )
                    {
                    otherlv_10=(Token)match(input,KEYWORD_73,FOLLOW_KEYWORD_73_in_ruleSelect1148); 

                        	newLeafNode(otherlv_10, grammarAccess.getSelectAccess().getWHEREKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:555:1: ( (lv_whereExpression_11_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:556:1: (lv_whereExpression_11_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:556:1: (lv_whereExpression_11_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:557:3: lv_whereExpression_11_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect1168);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:573:4: (otherlv_12= KEYWORD_95 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEYWORD_95) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:574:2: otherlv_12= KEYWORD_95 ( (lv_groupByEntry_13_0= ruleGroupByColumns ) )
                    {
                    otherlv_12=(Token)match(input,KEYWORD_95,FOLLOW_KEYWORD_95_in_ruleSelect1184); 

                        	newLeafNode(otherlv_12, grammarAccess.getSelectAccess().getGROUPBYKeyword_7_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:578:1: ( (lv_groupByEntry_13_0= ruleGroupByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:579:1: (lv_groupByEntry_13_0= ruleGroupByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:579:1: (lv_groupByEntry_13_0= ruleGroupByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:580:3: lv_groupByEntry_13_0= ruleGroupByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleGroupByColumns_in_ruleSelect1204);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:596:4: (otherlv_14= KEYWORD_76 ( (lv_havingEntry_15_0= ruleFullExpression ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==KEYWORD_76) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:597:2: otherlv_14= KEYWORD_76 ( (lv_havingEntry_15_0= ruleFullExpression ) )
                    {
                    otherlv_14=(Token)match(input,KEYWORD_76,FOLLOW_KEYWORD_76_in_ruleSelect1220); 

                        	newLeafNode(otherlv_14, grammarAccess.getSelectAccess().getHAVINGKeyword_8_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:601:1: ( (lv_havingEntry_15_0= ruleFullExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:602:1: (lv_havingEntry_15_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:602:1: (lv_havingEntry_15_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:603:3: lv_havingEntry_15_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSelect1240);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:619:4: (otherlv_16= KEYWORD_98 ( (lv_orderByEntry_17_0= ruleOrderByColumns ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==KEYWORD_98) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:620:2: otherlv_16= KEYWORD_98 ( (lv_orderByEntry_17_0= ruleOrderByColumns ) )
                    {
                    otherlv_16=(Token)match(input,KEYWORD_98,FOLLOW_KEYWORD_98_in_ruleSelect1256); 

                        	newLeafNode(otherlv_16, grammarAccess.getSelectAccess().getORDERBYKeyword_9_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:624:1: ( (lv_orderByEntry_17_0= ruleOrderByColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:625:1: (lv_orderByEntry_17_0= ruleOrderByColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:625:1: (lv_orderByEntry_17_0= ruleOrderByColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:626:3: lv_orderByEntry_17_0= ruleOrderByColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getOrderByEntryOrderByColumnsParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOrderByColumns_in_ruleSelect1276);
                    lv_orderByEntry_17_0=ruleOrderByColumns();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"orderByEntry",
                            		lv_orderByEntry_17_0, 
                            		"OrderByColumns");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:642:4: (otherlv_18= KEYWORD_63 ( (lv_lim_19_0= ruleLimit ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==KEYWORD_63) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:643:2: otherlv_18= KEYWORD_63 ( (lv_lim_19_0= ruleLimit ) )
                    {
                    otherlv_18=(Token)match(input,KEYWORD_63,FOLLOW_KEYWORD_63_in_ruleSelect1292); 

                        	newLeafNode(otherlv_18, grammarAccess.getSelectAccess().getLIMITKeyword_10_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:647:1: ( (lv_lim_19_0= ruleLimit ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:648:1: (lv_lim_19_0= ruleLimit )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:648:1: (lv_lim_19_0= ruleLimit )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:649:3: lv_lim_19_0= ruleLimit
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getLimLimitParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleLimit_in_ruleSelect1312);
                    lv_lim_19_0=ruleLimit();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"lim",
                            		lv_lim_19_0, 
                            		"Limit");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:665:4: (otherlv_20= KEYWORD_79 ( (lv_offset_21_0= ruleOffset ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_79) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:666:2: otherlv_20= KEYWORD_79 ( (lv_offset_21_0= ruleOffset ) )
                    {
                    otherlv_20=(Token)match(input,KEYWORD_79,FOLLOW_KEYWORD_79_in_ruleSelect1328); 

                        	newLeafNode(otherlv_20, grammarAccess.getSelectAccess().getOFFSETKeyword_11_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:670:1: ( (lv_offset_21_0= ruleOffset ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:671:1: (lv_offset_21_0= ruleOffset )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:671:1: (lv_offset_21_0= ruleOffset )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:672:3: lv_offset_21_0= ruleOffset
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getOffsetOffsetParserRuleCall_11_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOffset_in_ruleSelect1348);
                    lv_offset_21_0=ruleOffset();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"offset",
                            		lv_offset_21_0, 
                            		"Offset");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:688:4: (otherlv_22= KEYWORD_111 ( (lv_fetchFirst_23_0= ruleFetchFirst ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==KEYWORD_111) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:689:2: otherlv_22= KEYWORD_111 ( (lv_fetchFirst_23_0= ruleFetchFirst ) )
                    {
                    otherlv_22=(Token)match(input,KEYWORD_111,FOLLOW_KEYWORD_111_in_ruleSelect1364); 

                        	newLeafNode(otherlv_22, grammarAccess.getSelectAccess().getFETCHFIRSTKeyword_12_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:693:1: ( (lv_fetchFirst_23_0= ruleFetchFirst ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:694:1: (lv_fetchFirst_23_0= ruleFetchFirst )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:694:1: (lv_fetchFirst_23_0= ruleFetchFirst )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:695:3: lv_fetchFirst_23_0= ruleFetchFirst
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectAccess().getFetchFirstFetchFirstParserRuleCall_12_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFetchFirst_in_ruleSelect1384);
                    lv_fetchFirst_23_0=ruleFetchFirst();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectRule());
                    	        }
                           		set(
                           			current, 
                           			"fetchFirst",
                            		lv_fetchFirst_23_0, 
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
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns1421);
            iv_ruleColumns=ruleColumns();

            state._fsp--;

             current =iv_ruleColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns1431); 

            }

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
                
            pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns1478);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleColumns1501); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:752:1: ( (lv_entries_3_0= ruleColumnOrAlias ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:753:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:753:1: (lv_entries_3_0= ruleColumnOrAlias )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:754:3: lv_entries_3_0= ruleColumnOrAlias
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleColumnOrAlias_in_ruleColumns1521);
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
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias1560);
            iv_ruleColumnOrAlias=ruleColumnOrAlias();

            state._fsp--;

             current =iv_ruleColumnOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias1570); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:787:1: ruleColumnOrAlias returns [EObject current=null] : ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) ;
    public final EObject ruleColumnOrAlias() throws RecognitionException {
        EObject current = null;

        Token lv_alias_1_0=null;
        Token lv_allCols_3_0=null;
        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_2_0 = null;

        EObject lv_dbAllCols_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:790:28: ( ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:1: ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:1: ( ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? ) | ( (lv_allCols_3_0= RULE_STAR ) ) | ( (lv_dbAllCols_4_0= ruleDbObjectNameAll ) ) )
            int alt24=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==KEYWORD_6) ) {
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
                else if ( (LA24_1==EOF||LA24_1==KEYWORD_39||LA24_1==KEYWORD_25||LA24_1==KEYWORD_19||(LA24_1>=KEYWORD_24 && LA24_1<=KEYWORD_5)||LA24_1==KEYWORD_7||LA24_1==RULE_STAR||(LA24_1>=RULE_STRING && LA24_1<=RULE_ID)) ) {
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
                else if ( (LA24_2==EOF||LA24_2==KEYWORD_39||LA24_2==KEYWORD_25||LA24_2==KEYWORD_19||LA24_2==KEYWORD_24||(LA24_2>=KEYWORD_2 && LA24_2<=KEYWORD_5)||LA24_2==KEYWORD_7||LA24_2==RULE_STAR||(LA24_2>=RULE_STRING && LA24_2<=RULE_ID)) ) {
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
                else if ( (LA24_3==EOF||LA24_3==KEYWORD_39||LA24_3==KEYWORD_25||LA24_3==KEYWORD_19||LA24_3==KEYWORD_24||(LA24_3>=KEYWORD_2 && LA24_3<=KEYWORD_5)||LA24_3==KEYWORD_7||LA24_3==RULE_STAR||(LA24_3>=RULE_STRING && LA24_3<=RULE_ID)) ) {
                    alt24=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 3, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_84:
            case KEYWORD_57:
            case KEYWORD_36:
            case KEYWORD_1:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:2: ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:2: ( ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:3: ( (lv_ce_0_0= ruleOperandGroup ) ) ( (lv_alias_1_0= KEYWORD_19 ) )? ( (lv_colAlias_2_0= ruleDbObjectName ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:791:3: ( (lv_ce_0_0= ruleOperandGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:792:1: (lv_ce_0_0= ruleOperandGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:792:1: (lv_ce_0_0= ruleOperandGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:793:3: lv_ce_0_0= ruleOperandGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getColumnOrAliasAccess().getCeOperandGroupParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandGroup_in_ruleColumnOrAlias1617);
                    lv_ce_0_0=ruleOperandGroup();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getColumnOrAliasRule());
                    	        }
                           		set(
                           			current, 
                           			"ce",
                            		lv_ce_0_0, 
                            		"OperandGroup");
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
                            lv_alias_1_0=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleColumnOrAlias1636); 

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
                            	    
                            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias1669);
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
                    lv_allCols_3_0=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleColumnOrAlias1694); 

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
                    	    
                    pushFollow(FOLLOW_ruleDbObjectNameAll_in_ruleColumnOrAlias1726);
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
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull1761);
            iv_ruleColumnFull=ruleColumnFull();

            state._fsp--;

             current =iv_ruleColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull1771); 

            }

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
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull1818);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleColumnFull1841); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:922:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:923:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:923:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:924:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleColumnFull1861);
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
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables1900);
            iv_ruleTables=ruleTables();

            state._fsp--;

             current =iv_ruleTables; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables1910); 

            }

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
                
            pushFollow(FOLLOW_ruleFromTable_in_ruleTables1957);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleTables1980); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTablesAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:981:1: ( (lv_entries_3_0= ruleFromTable ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:982:1: (lv_entries_3_0= ruleFromTable )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:982:1: (lv_entries_3_0= ruleFromTable )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:983:3: lv_entries_3_0= ruleFromTable
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleFromTable_in_ruleTables2000);
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
            pushFollow(FOLLOW_ruleFromTable_in_entryRuleFromTable2039);
            iv_ruleFromTable=ruleFromTable();

            state._fsp--;

             current =iv_ruleFromTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTable2049); 

            }

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
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTable2095);
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

                if ( (LA29_0==KEYWORD_119||LA29_0==KEYWORD_88||LA29_0==KEYWORD_58||LA29_0==KEYWORD_61||LA29_0==KEYWORD_71||LA29_0==KEYWORD_40||LA29_0==KEYWORD_42||LA29_0==KEYWORD_44) ) {
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
            	    	    
            	    pushFollow(FOLLOW_ruleFromTableJoin_in_ruleFromTable2116);
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
            pushFollow(FOLLOW_ruleFromTableJoin_in_entryRuleFromTableJoin2152);
            iv_ruleFromTableJoin=ruleFromTableJoin();

            state._fsp--;

             current =iv_ruleFromTableJoin; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTableJoin2162); 

            }

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
            	    
            pushFollow(FOLLOW_ruleJoinType_in_ruleFromTableJoin2208);
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
            	    
            pushFollow(FOLLOW_ruleTableOrAlias_in_ruleFromTableJoin2229);
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

            otherlv_2=(Token)match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_ruleFromTableJoin2242); 

                	newLeafNode(otherlv_2, grammarAccess.getFromTableJoinAccess().getONKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1118:1: ( (lv_joinExpr_3_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1119:1: (lv_joinExpr_3_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1119:1: (lv_joinExpr_3_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1120:3: lv_joinExpr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getFromTableJoinAccess().getJoinExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleFromTableJoin2262);
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
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias2297);
            iv_ruleTableOrAlias=ruleTableOrAlias();

            state._fsp--;

             current =iv_ruleTableOrAlias; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias2307); 

            }

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
                    	    
                    pushFollow(FOLLOW_ruleTableFull_in_ruleTableOrAlias2354);
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
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleTableOrAlias2381);
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

            if ( (LA31_0==KEYWORD_69) ) {
                alt31=1;
            }
            else if ( (LA31_0==KEYWORD_91) ) {
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
                    	    
                    pushFollow(FOLLOW_rulePivotTable_in_ruleTableOrAlias2404);
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
                    	    
                    pushFollow(FOLLOW_ruleUnpivotTable_in_ruleTableOrAlias2431);
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
                    lv_alias_4_0=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleTableOrAlias2452); 

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
                    	    
                    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableOrAlias2485);
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
            pushFollow(FOLLOW_rulePivotTable_in_entryRulePivotTable2521);
            iv_rulePivotTable=rulePivotTable();

            state._fsp--;

             current =iv_rulePivotTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotTable2531); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1282:1: rulePivotTable returns [EObject current=null] : (otherlv_0= KEYWORD_69 (otherlv_1= KEYWORD_35 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1285:28: ( (otherlv_0= KEYWORD_69 (otherlv_1= KEYWORD_35 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1286:1: (otherlv_0= KEYWORD_69 (otherlv_1= KEYWORD_35 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1286:1: (otherlv_0= KEYWORD_69 (otherlv_1= KEYWORD_35 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1287:2: otherlv_0= KEYWORD_69 (otherlv_1= KEYWORD_35 )? otherlv_2= KEYWORD_1 ( (lv_pfun_3_0= rulePivotFunctions ) ) ( (lv_pfor_4_0= rulePivotForClause ) ) ( (lv_pin_5_0= rulePivotInClause ) ) otherlv_6= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_69,FOLLOW_KEYWORD_69_in_rulePivotTable2569); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotTableAccess().getPIVOTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1291:1: (otherlv_1= KEYWORD_35 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==KEYWORD_35) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1292:2: otherlv_1= KEYWORD_35
                    {
                    otherlv_1=(Token)match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_rulePivotTable2582); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotTableAccess().getXMLKeyword_1());
                        

                    }
                    break;

            }

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotTable2596); 

                	newLeafNode(otherlv_2, grammarAccess.getPivotTableAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1301:1: ( (lv_pfun_3_0= rulePivotFunctions ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1302:1: (lv_pfun_3_0= rulePivotFunctions )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1302:1: (lv_pfun_3_0= rulePivotFunctions )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1303:3: lv_pfun_3_0= rulePivotFunctions
            {
             
            	        newCompositeNode(grammarAccess.getPivotTableAccess().getPfunPivotFunctionsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_rulePivotFunctions_in_rulePivotTable2616);
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
            	    
            pushFollow(FOLLOW_rulePivotForClause_in_rulePivotTable2637);
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
            	    
            pushFollow(FOLLOW_rulePivotInClause_in_rulePivotTable2658);
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

            otherlv_6=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotTable2671); 

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
            pushFollow(FOLLOW_rulePivotFunctions_in_entryRulePivotFunctions2705);
            iv_rulePivotFunctions=rulePivotFunctions();

            state._fsp--;

             current =iv_rulePivotFunctions; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotFunctions2715); 

            }

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
            lv_abc_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_rulePivotFunctions2756); 

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
            pushFollow(FOLLOW_rulePivotInClause_in_entryRulePivotInClause2797);
            iv_rulePivotInClause=rulePivotInClause();

            state._fsp--;

             current =iv_rulePivotInClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotInClause2807); 

            }

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
            otherlv_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_rulePivotInClause2845); 

                	newLeafNode(otherlv_0, grammarAccess.getPivotInClauseAccess().getINKeyword_0());
                
            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotInClause2857); 

                	newLeafNode(otherlv_1, grammarAccess.getPivotInClauseAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1432:1: ( ( (lv_sq_2_0= ruleSubQueryOperand ) ) | ( (lv_args_3_0= ruleUnpivotInClauseArgs ) ) | ( (lv_pinany_4_0= rulePivotInClauseAny ) ) )
            int alt35=3;
            switch ( input.LA(1) ) {
            case KEYWORD_1:
                {
                int LA35_1 = input.LA(2);

                if ( ((LA35_1>=RULE_STRING && LA35_1<=RULE_ID)) ) {
                    alt35=2;
                }
                else if ( (LA35_1==KEYWORD_81) ) {
                    alt35=1;
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
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_rulePivotInClause2878);
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
                    	    
                    pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_rulePivotInClause2905);
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
                    	    
                    pushFollow(FOLLOW_rulePivotInClauseAny_in_rulePivotInClause2932);
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

            otherlv_5=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotInClause2946); 

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
            pushFollow(FOLLOW_rulePivotInClauseAny_in_entryRulePivotInClauseAny2981);
            iv_rulePivotInClauseAny=rulePivotInClauseAny();

            state._fsp--;

             current =iv_rulePivotInClauseAny.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotInClauseAny2992); 

            }

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
            kw=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rulePivotInClauseAny3030); 

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
                    kw=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rulePivotInClauseAny3044); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getPivotInClauseAnyAccess().getCommaKeyword_1_0()); 
                        
                    kw=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rulePivotInClauseAny3057); 

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
            pushFollow(FOLLOW_ruleUnpivotTable_in_entryRuleUnpivotTable3098);
            iv_ruleUnpivotTable=ruleUnpivotTable();

            state._fsp--;

             current =iv_ruleUnpivotTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotTable3108); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1550:1: ruleUnpivotTable returns [EObject current=null] : (otherlv_0= KEYWORD_91 ( (otherlv_1= KEYWORD_86 | otherlv_2= KEYWORD_83 ) otherlv_3= KEYWORD_67 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1553:28: ( (otherlv_0= KEYWORD_91 ( (otherlv_1= KEYWORD_86 | otherlv_2= KEYWORD_83 ) otherlv_3= KEYWORD_67 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1554:1: (otherlv_0= KEYWORD_91 ( (otherlv_1= KEYWORD_86 | otherlv_2= KEYWORD_83 ) otherlv_3= KEYWORD_67 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1554:1: (otherlv_0= KEYWORD_91 ( (otherlv_1= KEYWORD_86 | otherlv_2= KEYWORD_83 ) otherlv_3= KEYWORD_67 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1555:2: otherlv_0= KEYWORD_91 ( (otherlv_1= KEYWORD_86 | otherlv_2= KEYWORD_83 ) otherlv_3= KEYWORD_67 )? otherlv_4= KEYWORD_1 ( (lv_pcols_5_0= rulePivotColumns ) ) ( (lv_pfor_6_0= rulePivotForClause ) ) ( (lv_inop_7_0= ruleUnpivotInClause ) ) otherlv_8= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_91,FOLLOW_KEYWORD_91_in_ruleUnpivotTable3146); 

                	newLeafNode(otherlv_0, grammarAccess.getUnpivotTableAccess().getUNPIVOTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1559:1: ( (otherlv_1= KEYWORD_86 | otherlv_2= KEYWORD_83 ) otherlv_3= KEYWORD_67 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==KEYWORD_83||LA38_0==KEYWORD_86) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1559:2: (otherlv_1= KEYWORD_86 | otherlv_2= KEYWORD_83 ) otherlv_3= KEYWORD_67
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1559:2: (otherlv_1= KEYWORD_86 | otherlv_2= KEYWORD_83 )
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==KEYWORD_86) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==KEYWORD_83) ) {
                        alt37=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 0, input);

                        throw nvae;
                    }
                    switch (alt37) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1560:2: otherlv_1= KEYWORD_86
                            {
                            otherlv_1=(Token)match(input,KEYWORD_86,FOLLOW_KEYWORD_86_in_ruleUnpivotTable3160); 

                                	newLeafNode(otherlv_1, grammarAccess.getUnpivotTableAccess().getINCLUDEKeyword_1_0_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1566:2: otherlv_2= KEYWORD_83
                            {
                            otherlv_2=(Token)match(input,KEYWORD_83,FOLLOW_KEYWORD_83_in_ruleUnpivotTable3178); 

                                	newLeafNode(otherlv_2, grammarAccess.getUnpivotTableAccess().getEXCLUDEKeyword_1_0_1());
                                

                            }
                            break;

                    }

                    otherlv_3=(Token)match(input,KEYWORD_67,FOLLOW_KEYWORD_67_in_ruleUnpivotTable3191); 

                        	newLeafNode(otherlv_3, grammarAccess.getUnpivotTableAccess().getNULLSKeyword_1_1());
                        

                    }
                    break;

            }

            otherlv_4=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleUnpivotTable3205); 

                	newLeafNode(otherlv_4, grammarAccess.getUnpivotTableAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1580:1: ( (lv_pcols_5_0= rulePivotColumns ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1581:1: (lv_pcols_5_0= rulePivotColumns )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1581:1: (lv_pcols_5_0= rulePivotColumns )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1582:3: lv_pcols_5_0= rulePivotColumns
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotTableAccess().getPcolsPivotColumnsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotTable3225);
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
            	    
            pushFollow(FOLLOW_rulePivotForClause_in_ruleUnpivotTable3246);
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
            	    
            pushFollow(FOLLOW_ruleUnpivotInClause_in_ruleUnpivotTable3267);
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

            otherlv_8=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleUnpivotTable3280); 

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
            pushFollow(FOLLOW_ruleUnpivotInClause_in_entryRuleUnpivotInClause3314);
            iv_ruleUnpivotInClause=ruleUnpivotInClause();

            state._fsp--;

             current =iv_ruleUnpivotInClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClause3324); 

            }

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
            lv_op_1_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleUnpivotInClause3377); 

                    newLeafNode(lv_op_1_0, grammarAccess.getUnpivotInClauseAccess().getOpINKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUnpivotInClauseRule());
            	        }
                   		setWithLastConsumed(current, "op", lv_op_1_0, "IN");
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleUnpivotInClause3401); 

                	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1687:1: ( (lv_args_3_0= ruleUnpivotInClauseArgs ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1688:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1688:1: (lv_args_3_0= ruleUnpivotInClauseArgs )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1689:3: lv_args_3_0= ruleUnpivotInClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getUnpivotInClauseAccess().getArgsUnpivotInClauseArgsParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_ruleUnpivotInClause3421);
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

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleUnpivotInClause3434); 

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
            pushFollow(FOLLOW_ruleUnpivotInClauseArgs_in_entryRuleUnpivotInClauseArgs3468);
            iv_ruleUnpivotInClauseArgs=ruleUnpivotInClauseArgs();

            state._fsp--;

             current =iv_ruleUnpivotInClauseArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClauseArgs3478); 

            }

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
                
            pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3525);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleUnpivotInClauseArgs3548); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getUnpivotInClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1751:1: ( (lv_entries_3_0= ruleUnpivotInClauseArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1752:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1752:1: (lv_entries_3_0= ruleUnpivotInClauseArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1753:3: lv_entries_3_0= ruleUnpivotInClauseArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getUnpivotInClauseArgsAccess().getEntriesUnpivotInClauseArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3568);
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
            pushFollow(FOLLOW_ruleUnpivotInClauseArg_in_entryRuleUnpivotInClauseArg3607);
            iv_ruleUnpivotInClauseArg=ruleUnpivotInClauseArg();

            state._fsp--;

             current =iv_ruleUnpivotInClauseArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnpivotInClauseArg3617); 

            }

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
            	    
            pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3663);
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
                    otherlv_1=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleUnpivotInClauseArg3677); 

                        	newLeafNode(otherlv_1, grammarAccess.getUnpivotInClauseArgAccess().getASKeyword_1_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1813:1: ( (lv_cfuls_2_0= rulePivotColumns ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1814:1: (lv_cfuls_2_0= rulePivotColumns )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1814:1: (lv_cfuls_2_0= rulePivotColumns )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1815:3: lv_cfuls_2_0= rulePivotColumns
                    {
                     
                    	        newCompositeNode(grammarAccess.getUnpivotInClauseArgAccess().getCfulsPivotColumnsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3697);
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
            pushFollow(FOLLOW_rulePivotForClause_in_entryRulePivotForClause3734);
            iv_rulePivotForClause=rulePivotForClause();

            state._fsp--;

             current =iv_rulePivotForClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotForClause3744); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1848:1: rulePivotForClause returns [EObject current=null] : (otherlv_0= KEYWORD_32 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) ) ;
    public final EObject rulePivotForClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ColumnFull_1 = null;

        EObject this_Columns_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1851:28: ( (otherlv_0= KEYWORD_32 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1852:1: (otherlv_0= KEYWORD_32 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1852:1: (otherlv_0= KEYWORD_32 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1853:2: otherlv_0= KEYWORD_32 (this_ColumnFull_1= ruleColumnFull | (otherlv_2= KEYWORD_1 this_Columns_3= ruleColumns otherlv_4= KEYWORD_2 ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_32,FOLLOW_KEYWORD_32_in_rulePivotForClause3782); 

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
                        
                    pushFollow(FOLLOW_ruleColumnFull_in_rulePivotForClause3804);
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
                    otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotForClause3823); 

                        	newLeafNode(otherlv_2, grammarAccess.getPivotForClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotForClauseAccess().getColumnsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_ruleColumns_in_rulePivotForClause3844);
                    this_Columns_3=ruleColumns();

                    state._fsp--;


                            current = this_Columns_3;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotForClause3856); 

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
            pushFollow(FOLLOW_rulePivotColumns_in_entryRulePivotColumns3892);
            iv_rulePivotColumns=rulePivotColumns();

            state._fsp--;

             current =iv_rulePivotColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotColumns3902); 

            }

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
                        
                    pushFollow(FOLLOW_rulePivotCol_in_rulePivotColumns3949);
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
                    otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rulePivotColumns3968); 

                        	newLeafNode(otherlv_1, grammarAccess.getPivotColumnsAccess().getLeftParenthesisKeyword_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getPivotColumnsAccess().getPivotColsParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_rulePivotCols_in_rulePivotColumns3989);
                    this_PivotCols_2=rulePivotCols();

                    state._fsp--;


                            current = this_PivotCols_2;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rulePivotColumns4001); 

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
            pushFollow(FOLLOW_rulePivotCols_in_entryRulePivotCols4036);
            iv_rulePivotCols=rulePivotCols();

            state._fsp--;

             current =iv_rulePivotCols; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotCols4046); 

            }

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
                
            pushFollow(FOLLOW_rulePivotCol_in_rulePivotCols4093);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rulePivotCols4116); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1977:1: ( (lv_entries_3_0= rulePivotCol ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1978:1: (lv_entries_3_0= rulePivotCol )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1978:1: (lv_entries_3_0= rulePivotCol )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:1979:3: lv_entries_3_0= rulePivotCol
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColsAccess().getEntriesPivotColParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePivotCol_in_rulePivotCols4136);
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
            pushFollow(FOLLOW_rulePivotCol_in_entryRulePivotCol4175);
            iv_rulePivotCol=rulePivotCol();

            state._fsp--;

             current =iv_rulePivotCol; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePivotCol4185); 

            }

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
                
            pushFollow(FOLLOW_ruleDbObjectName_in_rulePivotCol4232);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rulePivotCol4255); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getPivotColAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2036:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2037:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2037:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2038:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPivotColAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_rulePivotCol4275);
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
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull4314);
            iv_ruleTableFull=ruleTableFull();

            state._fsp--;

             current =iv_ruleTableFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull4324); 

            }

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
                
            pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull4371);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleTableFull4394); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2095:1: ( (lv_entries_3_0= ruleDbObjectName ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2096:1: (lv_entries_3_0= ruleDbObjectName )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2096:1: (lv_entries_3_0= ruleDbObjectName )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2097:3: lv_entries_3_0= ruleDbObjectName
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDbObjectName_in_ruleTableFull4414);
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
            pushFollow(FOLLOW_ruleDbObjectNameAll_in_entryRuleDbObjectNameAll4453);
            iv_ruleDbObjectNameAll=ruleDbObjectNameAll();

            state._fsp--;

             current =iv_ruleDbObjectNameAll; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectNameAll4463); 

            }

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
            	    
            pushFollow(FOLLOW_ruleDBID_in_ruleDbObjectNameAll4509);
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

            otherlv_1=(Token)match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_ruleDbObjectNameAll4522); 

                	newLeafNode(otherlv_1, grammarAccess.getDbObjectNameAllAccess().getFullStopKeyword_1());
                
            this_STAR_2=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleDbObjectNameAll4532); 
             
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
            pushFollow(FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName4566);
            iv_ruleDbObjectName=ruleDbObjectName();

            state._fsp--;

             current =iv_ruleDbObjectName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectName4576); 

            }

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
            	    
            pushFollow(FOLLOW_ruleDBID_in_ruleDbObjectName4621);
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
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns4655);
            iv_ruleOrderByColumns=ruleOrderByColumns();

            state._fsp--;

             current =iv_ruleOrderByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns4665); 

            }

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
                
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4712);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByColumns4735); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2241:1: ( (lv_entries_3_0= ruleOrderByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2242:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2242:1: (lv_entries_3_0= ruleOrderByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2243:3: lv_entries_3_0= ruleOrderByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4755);
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
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull4794);
            iv_ruleOrderByColumnFull=ruleOrderByColumnFull();

            state._fsp--;

             current =iv_ruleOrderByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull4804); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2276:1: ruleOrderByColumnFull returns [EObject current=null] : ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 ) ) )? ) ;
    public final EObject ruleOrderByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_colOrderInt_1_0=null;
        Token lv_direction_2_1=null;
        Token lv_direction_2_2=null;
        EObject lv_colOrder_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2279:28: ( ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:1: ( ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) ) ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 ) ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2280:2: ( ( (lv_colOrder_0_0= ruleColumnFull ) ) | ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) ) )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=RULE_STRING && LA52_0<=RULE_ID)) ) {
                alt52=1;
            }
            else if ( (LA52_0==RULE_UNSIGNED) ) {
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
                    	    
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull4851);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2299:6: ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2299:6: ( (lv_colOrderInt_1_0= RULE_UNSIGNED ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2300:1: (lv_colOrderInt_1_0= RULE_UNSIGNED )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2300:1: (lv_colOrderInt_1_0= RULE_UNSIGNED )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2301:3: lv_colOrderInt_1_0= RULE_UNSIGNED
                    {
                    lv_colOrderInt_1_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_RULE_UNSIGNED_in_ruleOrderByColumnFull4874); 

                    			newLeafNode(lv_colOrderInt_1_0, grammarAccess.getOrderByColumnFullAccess().getColOrderIntUNSIGNEDTerminalRuleCall_0_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"colOrderInt",
                            		lv_colOrderInt_1_0, 
                            		"UNSIGNED");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2317:3: ( ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==KEYWORD_37||LA54_0==KEYWORD_29) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2318:1: ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2318:1: ( (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2319:1: (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2319:1: (lv_direction_2_1= KEYWORD_29 | lv_direction_2_2= KEYWORD_37 )
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==KEYWORD_29) ) {
                        alt53=1;
                    }
                    else if ( (LA53_0==KEYWORD_37) ) {
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
                            lv_direction_2_1=(Token)match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_ruleOrderByColumnFull4901); 

                                    newLeafNode(lv_direction_2_1, grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOrderByColumnFullRule());
                            	        }
                                   		setWithLastConsumed(current, "direction", lv_direction_2_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2333:8: lv_direction_2_2= KEYWORD_37
                            {
                            lv_direction_2_2=(Token)match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_ruleOrderByColumnFull4929); 

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
            pushFollow(FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns4979);
            iv_ruleGroupByColumns=ruleGroupByColumns();

            state._fsp--;

             current =iv_ruleGroupByColumns; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumns4989); 

            }

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
                
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns5036);
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
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleGroupByColumns5059); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2390:1: ( (lv_entries_3_0= ruleGroupByColumnFull ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2391:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2391:1: (lv_entries_3_0= ruleGroupByColumnFull )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2392:3: lv_entries_3_0= ruleGroupByColumnFull
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns5079);
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
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull5118);
            iv_ruleGroupByColumnFull=ruleGroupByColumnFull();

            state._fsp--;

             current =iv_ruleGroupByColumnFull; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumnFull5128); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2425:1: ruleGroupByColumnFull returns [EObject current=null] : ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) ) ;
    public final EObject ruleGroupByColumnFull() throws RecognitionException {
        EObject current = null;

        Token lv_grByInt_2_0=null;
        EObject lv_colGrBy_0_0 = null;

        EObject lv_gbFunction_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2428:28: ( ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:1: ( ( (lv_colGrBy_0_0= ruleColumnFull ) ) | ( (lv_gbFunction_1_0= ruleOperandFunction ) ) | ( (lv_grByInt_2_0= RULE_UNSIGNED ) ) )
            int alt57=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA57_1 = input.LA(2);

                if ( (LA57_1==KEYWORD_1) ) {
                    alt57=2;
                }
                else if ( (LA57_1==EOF||LA57_1==KEYWORD_111||LA57_1==KEYWORD_102||LA57_1==KEYWORD_98||LA57_1==KEYWORD_74||LA57_1==KEYWORD_76||LA57_1==KEYWORD_79||(LA57_1>=KEYWORD_63 && LA57_1<=KEYWORD_64)||LA57_1==KEYWORD_72||LA57_1==KEYWORD_2||LA57_1==KEYWORD_4||LA57_1==KEYWORD_6) ) {
                    alt57=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 57, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt57=1;
                }
                break;
            case RULE_UNSIGNED:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2429:2: ( (lv_colGrBy_0_0= ruleColumnFull ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2430:1: (lv_colGrBy_0_0= ruleColumnFull )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2430:1: (lv_colGrBy_0_0= ruleColumnFull )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2431:3: lv_colGrBy_0_0= ruleColumnFull
                    {
                     
                    	        newCompositeNode(grammarAccess.getGroupByColumnFullAccess().getColGrByColumnFullParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnFull_in_ruleGroupByColumnFull5174);
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
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleGroupByColumnFull5201);
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
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2467:6: ( (lv_grByInt_2_0= RULE_UNSIGNED ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2467:6: ( (lv_grByInt_2_0= RULE_UNSIGNED ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2468:1: (lv_grByInt_2_0= RULE_UNSIGNED )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2468:1: (lv_grByInt_2_0= RULE_UNSIGNED )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2469:3: lv_grByInt_2_0= RULE_UNSIGNED
                    {
                    lv_grByInt_2_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_RULE_UNSIGNED_in_ruleGroupByColumnFull5224); 

                    			newLeafNode(lv_grByInt_2_0, grammarAccess.getGroupByColumnFullAccess().getGrByIntUNSIGNEDTerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getGroupByColumnFullRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"grByInt",
                            		lv_grByInt_2_0, 
                            		"UNSIGNED");
                    	    

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2493:1: entryRuleFullExpression returns [EObject current=null] : iv_ruleFullExpression= ruleFullExpression EOF ;
    public final EObject entryRuleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2494:2: (iv_ruleFullExpression= ruleFullExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2495:2: iv_ruleFullExpression= ruleFullExpression EOF
            {
             newCompositeNode(grammarAccess.getFullExpressionRule()); 
            pushFollow(FOLLOW_ruleFullExpression_in_entryRuleFullExpression5264);
            iv_ruleFullExpression=ruleFullExpression();

            state._fsp--;

             current =iv_ruleFullExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullExpression5274); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2502:1: ruleFullExpression returns [EObject current=null] : (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) ;
    public final EObject ruleFullExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExpressionFragment_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2505:28: ( (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2506:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2506:1: (this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2507:5: this_ExpressionFragment_0= ruleExpressionFragment ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleExpressionFragment_in_ruleFullExpression5321);
            this_ExpressionFragment_0=ruleExpressionFragment();

            state._fsp--;


                    current = this_ExpressionFragment_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2515:1: ( () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+ )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==KEYWORD_27||LA59_0==KEYWORD_22||LA59_0==RULE_JRNPARAM) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2515:2: () ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2515:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2516:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2521:2: ( (lv_entries_2_0= ruleExpressionFragmentSecond ) )+
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2522:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2522:1: (lv_entries_2_0= ruleExpressionFragmentSecond )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2523:3: lv_entries_2_0= ruleExpressionFragmentSecond
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression5351);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2547:1: entryRuleExpressionFragmentSecond returns [EObject current=null] : iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF ;
    public final EObject entryRuleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragmentSecond = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2548:2: (iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2549:2: iv_ruleExpressionFragmentSecond= ruleExpressionFragmentSecond EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentSecondRule()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond5389);
            iv_ruleExpressionFragmentSecond=ruleExpressionFragmentSecond();

            state._fsp--;

             current =iv_ruleExpressionFragmentSecond; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragmentSecond5399); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2556:1: ruleExpressionFragmentSecond returns [EObject current=null] : ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) ;
    public final EObject ruleExpressionFragmentSecond() throws RecognitionException {
        EObject current = null;

        Token lv_c_0_1=null;
        Token lv_c_0_2=null;
        Token lv_notPrm_2_0=null;
        EObject lv_efrag_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2559:28: ( ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2560:1: ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2560:1: ( ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) ) | ( (lv_notPrm_2_0= RULE_JRNPARAM ) ) )
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2560:2: ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2560:2: ( ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2560:3: ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) ) ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2560:3: ( ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2561:1: ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2561:1: ( (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2562:1: (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2562:1: (lv_c_0_1= KEYWORD_27 | lv_c_0_2= KEYWORD_22 )
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
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2563:3: lv_c_0_1= KEYWORD_27
                            {
                            lv_c_0_1=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleExpressionFragmentSecond5446); 

                                    newLeafNode(lv_c_0_1, grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionFragmentSecondRule());
                            	        }
                                   		setWithLastConsumed(current, "c", lv_c_0_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2576:8: lv_c_0_2= KEYWORD_22
                            {
                            lv_c_0_2=(Token)match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_ruleExpressionFragmentSecond5474); 

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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2592:2: ( (lv_efrag_1_0= ruleExpressionFragment ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2593:1: (lv_efrag_1_0= ruleExpressionFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2593:1: (lv_efrag_1_0= ruleExpressionFragment )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2594:3: lv_efrag_1_0= ruleExpressionFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond5509);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2611:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2611:6: ( (lv_notPrm_2_0= RULE_JRNPARAM ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2612:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2612:1: (lv_notPrm_2_0= RULE_JRNPARAM )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2613:3: lv_notPrm_2_0= RULE_JRNPARAM
                    {
                    lv_notPrm_2_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragmentSecond5533); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2637:1: entryRuleExpressionFragment returns [EObject current=null] : iv_ruleExpressionFragment= ruleExpressionFragment EOF ;
    public final EObject entryRuleExpressionFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2638:2: (iv_ruleExpressionFragment= ruleExpressionFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2639:2: iv_ruleExpressionFragment= ruleExpressionFragment EOF
            {
             newCompositeNode(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment5573);
            iv_ruleExpressionFragment=ruleExpressionFragment();

            state._fsp--;

             current =iv_ruleExpressionFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragment5583); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2646:1: ruleExpressionFragment returns [EObject current=null] : ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2649:28: ( ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2650:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2650:1: ( ( (lv_expgroup_0_0= ruleExpressionGroup ) ) | ( (lv_exp_1_0= ruleExpression ) ) | ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) ) | ( (lv_notPrm_3_0= RULE_JRNPARAM ) ) | ( (lv_in_4_0= ruleInOperator ) ) | ( (lv_exists_5_0= ruleExistsOperator ) ) )
            int alt63=6;
            switch ( input.LA(1) ) {
            case KEYWORD_47:
            case KEYWORD_48:
                {
                alt63=1;
                }
                break;
            case KEYWORD_1:
                {
                int LA63_2 = input.LA(2);

                if ( (LA63_2==KEYWORD_108||LA63_2==KEYWORD_84||LA63_2==KEYWORD_75||LA63_2==KEYWORD_78||LA63_2==KEYWORD_57||LA63_2==KEYWORD_36||(LA63_2>=KEYWORD_47 && LA63_2<=KEYWORD_48)||LA63_2==KEYWORD_15||LA63_2==KEYWORD_20||LA63_2==KEYWORD_1||(LA63_2>=RULE_JRPARAM && LA63_2<=RULE_JRNPARAM)||(LA63_2>=RULE_UNSIGNED && LA63_2<=RULE_SIGNED_DOUBLE)||(LA63_2>=RULE_STRING_ && LA63_2<=RULE_ID)) ) {
                    alt63=1;
                }
                else if ( (LA63_2==KEYWORD_81) ) {
                    alt63=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 2, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_84:
            case KEYWORD_57:
            case KEYWORD_36:
            case RULE_JRPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
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

                if ( (LA63_4==KEYWORD_114||LA63_4==KEYWORD_116||LA63_4==KEYWORD_108||LA63_4==KEYWORD_96||LA63_4==KEYWORD_82||LA63_4==KEYWORD_87||LA63_4==KEYWORD_75||LA63_4==KEYWORD_78||LA63_4==KEYWORD_46||LA63_4==KEYWORD_14||(LA63_4>=KEYWORD_16 && LA63_4<=KEYWORD_18)||LA63_4==KEYWORD_20||(LA63_4>=KEYWORD_23 && LA63_4<=KEYWORD_24)||LA63_4==KEYWORD_3||LA63_4==KEYWORD_5||(LA63_4>=KEYWORD_7 && LA63_4<=KEYWORD_10)||LA63_4==RULE_STAR) ) {
                    alt63=2;
                }
                else if ( (LA63_4==EOF||LA63_4==KEYWORD_119||LA63_4==KEYWORD_111||LA63_4==KEYWORD_102||LA63_4==KEYWORD_95||LA63_4==KEYWORD_98||LA63_4==KEYWORD_88||LA63_4==KEYWORD_74||LA63_4==KEYWORD_76||LA63_4==KEYWORD_79||LA63_4==KEYWORD_58||LA63_4==KEYWORD_61||(LA63_4>=KEYWORD_63 && LA63_4<=KEYWORD_64)||(LA63_4>=KEYWORD_71 && LA63_4<=KEYWORD_73)||LA63_4==KEYWORD_40||LA63_4==KEYWORD_42||LA63_4==KEYWORD_44||LA63_4==KEYWORD_53||LA63_4==KEYWORD_55||LA63_4==KEYWORD_27||LA63_4==KEYWORD_22||LA63_4==KEYWORD_2||LA63_4==KEYWORD_4||LA63_4==RULE_JRNPARAM) ) {
                    alt63=4;
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
            case KEYWORD_78:
            case KEYWORD_20:
                {
                alt63=5;
                }
                break;
            case KEYWORD_108:
            case KEYWORD_75:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2650:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2650:2: ( (lv_expgroup_0_0= ruleExpressionGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2651:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2651:1: (lv_expgroup_0_0= ruleExpressionGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2652:3: lv_expgroup_0_0= ruleExpressionGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment5629);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2669:6: ( (lv_exp_1_0= ruleExpression ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2669:6: ( (lv_exp_1_0= ruleExpression ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2670:1: (lv_exp_1_0= ruleExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2670:1: (lv_exp_1_0= ruleExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2671:3: lv_exp_1_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleExpressionFragment5656);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2688:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2688:6: ( ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2689:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2689:1: ( (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2690:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2690:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )
                    int alt62=2;
                    alt62 = dfa62.predict(input);
                    switch (alt62) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2691:3: lv_xexp_2_1= ruleXExpression
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleXExpression_in_ruleExpressionFragment5685);
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
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2706:8: lv_xexp_2_2= ruleXExpression_
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getXexpXExpression_ParserRuleCall_2_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleXExpression__in_ruleExpressionFragment5704);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2725:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2725:6: ( (lv_notPrm_3_0= RULE_JRNPARAM ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2726:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2726:1: (lv_notPrm_3_0= RULE_JRNPARAM )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2727:3: lv_notPrm_3_0= RULE_JRNPARAM
                    {
                    lv_notPrm_3_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragment5730); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2744:6: ( (lv_in_4_0= ruleInOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2744:6: ( (lv_in_4_0= ruleInOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2745:1: (lv_in_4_0= ruleInOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2745:1: (lv_in_4_0= ruleInOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2746:3: lv_in_4_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getInInOperatorParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleInOperator_in_ruleExpressionFragment5762);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2763:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2763:6: ( (lv_exists_5_0= ruleExistsOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2764:1: (lv_exists_5_0= ruleExistsOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2764:1: (lv_exists_5_0= ruleExistsOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2765:3: lv_exists_5_0= ruleExistsOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionFragmentAccess().getExistsExistsOperatorParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExistsOperator_in_ruleExpressionFragment5789);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2789:1: entryRuleExpressionGroup returns [EObject current=null] : iv_ruleExpressionGroup= ruleExpressionGroup EOF ;
    public final EObject entryRuleExpressionGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionGroup = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2790:2: (iv_ruleExpressionGroup= ruleExpressionGroup EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2791:2: iv_ruleExpressionGroup= ruleExpressionGroup EOF
            {
             newCompositeNode(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup5824);
            iv_ruleExpressionGroup=ruleExpressionGroup();

            state._fsp--;

             current =iv_ruleExpressionGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionGroup5834); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2798:1: ruleExpressionGroup returns [EObject current=null] : ( () ( ( (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 ) ;
    public final EObject ruleExpressionGroup() throws RecognitionException {
        EObject current = null;

        Token lv_isnot_1_1=null;
        Token lv_isnot_1_2=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2801:28: ( ( () ( ( (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2802:1: ( () ( ( (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2802:1: ( () ( ( (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2802:2: () ( ( (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 ) ) )? otherlv_2= KEYWORD_1 ( (lv_expr_3_0= ruleFullExpression ) ) otherlv_4= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2802:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2803:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpressionGroupAccess().getExprGroupAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2808:2: ( ( (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 ) ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( ((LA65_0>=KEYWORD_47 && LA65_0<=KEYWORD_48)) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2809:1: ( (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2809:1: ( (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2810:1: (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2810:1: (lv_isnot_1_1= KEYWORD_48 | lv_isnot_1_2= KEYWORD_47 )
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==KEYWORD_48) ) {
                        alt64=1;
                    }
                    else if ( (LA64_0==KEYWORD_47) ) {
                        alt64=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 64, 0, input);

                        throw nvae;
                    }
                    switch (alt64) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2811:3: lv_isnot_1_1= KEYWORD_48
                            {
                            lv_isnot_1_1=(Token)match(input,KEYWORD_48,FOLLOW_KEYWORD_48_in_ruleExpressionGroup5889); 

                                    newLeafNode(lv_isnot_1_1, grammarAccess.getExpressionGroupAccess().getIsnotNOTKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionGroupRule());
                            	        }
                                   		setWithLastConsumed(current, "isnot", lv_isnot_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2824:8: lv_isnot_1_2= KEYWORD_47
                            {
                            lv_isnot_1_2=(Token)match(input,KEYWORD_47,FOLLOW_KEYWORD_47_in_ruleExpressionGroup5917); 

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

            otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleExpressionGroup5945); 

                	newLeafNode(otherlv_2, grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2845:1: ( (lv_expr_3_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2846:1: (lv_expr_3_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2846:1: (lv_expr_3_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2847:3: lv_expr_3_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleExpressionGroup5965);
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

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleExpressionGroup5978); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2876:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2877:2: (iv_ruleXExpression= ruleXExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2878:2: iv_ruleXExpression= ruleXExpression EOF
            {
             newCompositeNode(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression6012);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;

             current =iv_ruleXExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression6022); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2885:1: ruleXExpression returns [EObject current=null] : (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2888:28: ( (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2889:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2889:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2890:2: otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_4 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13
            {
            otherlv_0=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleXExpression6060); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpressionAccess().getXKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2894:1: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2895:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpressionAccess().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression6081); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpressionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2905:1: ( (lv_xf_3_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2906:1: (lv_xf_3_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2906:1: (lv_xf_3_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2907:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression6101);
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

            otherlv_4=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression6114); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpressionAccess().getCommaKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2928:1: ( (lv_col_5_0= ruleOperandGroup ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2929:1: (lv_col_5_0= ruleOperandGroup )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2929:1: (lv_col_5_0= ruleOperandGroup )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2930:3: lv_col_5_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getXExpressionAccess().getColOperandGroupParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandGroup_in_ruleXExpression6134);
            lv_col_5_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"col",
                    		lv_col_5_0, 
                    		"OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2946:2: (otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==KEYWORD_4) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2947:2: otherlv_6= KEYWORD_4 ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpression6148); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpressionAccess().getCommaKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2951:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2952:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2952:1: (lv_prm_7_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2953:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression6168);
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

            otherlv_8=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleXExpression6183); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2982:1: entryRuleXExpression_ returns [EObject current=null] : iv_ruleXExpression_= ruleXExpression_ EOF ;
    public final EObject entryRuleXExpression_() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression_ = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2983:2: (iv_ruleXExpression_= ruleXExpression_ EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2984:2: iv_ruleXExpression_= ruleXExpression_ EOF
            {
             newCompositeNode(grammarAccess.getXExpression_Rule()); 
            pushFollow(FOLLOW_ruleXExpression__in_entryRuleXExpression_6217);
            iv_ruleXExpression_=ruleXExpression_();

            state._fsp--;

             current =iv_ruleXExpression_; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression_6227); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2991:1: ruleXExpression_ returns [EObject current=null] : (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2994:28: ( (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2995:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2995:1: (otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:2996:2: otherlv_0= KEYWORD_15 () otherlv_2= KEYWORD_11 ( (lv_xf_3_0= ruleXFunction ) ) otherlv_4= KEYWORD_12 ( (lv_col_5_0= ruleOperandGroup ) ) (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )? otherlv_8= KEYWORD_13
            {
            otherlv_0=(Token)match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_ruleXExpression_6265); 

                	newLeafNode(otherlv_0, grammarAccess.getXExpression_Access().getXKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3000:1: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3001:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getXExpression_Access().getXExprAction_1(),
                        current);
                

            }

            otherlv_2=(Token)match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_ruleXExpression_6286); 

                	newLeafNode(otherlv_2, grammarAccess.getXExpression_Access().getLeftCurlyBracketKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3011:1: ( (lv_xf_3_0= ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3012:1: (lv_xf_3_0= ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3012:1: (lv_xf_3_0= ruleXFunction )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3013:3: lv_xf_3_0= ruleXFunction
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getXfXFunctionEnumRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleXFunction_in_ruleXExpression_6306);
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

            otherlv_4=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleXExpression_6319); 

                	newLeafNode(otherlv_4, grammarAccess.getXExpression_Access().getVerticalLineKeyword_4());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3034:1: ( (lv_col_5_0= ruleOperandGroup ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3035:1: (lv_col_5_0= ruleOperandGroup )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3035:1: (lv_col_5_0= ruleOperandGroup )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3036:3: lv_col_5_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getXExpression_Access().getColOperandGroupParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandGroup_in_ruleXExpression_6339);
            lv_col_5_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getXExpression_Rule());
            	        }
                   		set(
                   			current, 
                   			"col",
                    		lv_col_5_0, 
                    		"OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3052:2: (otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==KEYWORD_12) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3053:2: otherlv_6= KEYWORD_12 ( (lv_prm_7_0= ruleXExpressionParams ) )
                    {
                    otherlv_6=(Token)match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_ruleXExpression_6353); 

                        	newLeafNode(otherlv_6, grammarAccess.getXExpression_Access().getVerticalLineKeyword_6_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3057:1: ( (lv_prm_7_0= ruleXExpressionParams ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3058:1: (lv_prm_7_0= ruleXExpressionParams )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3058:1: (lv_prm_7_0= ruleXExpressionParams )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3059:3: lv_prm_7_0= ruleXExpressionParams
                    {
                     
                    	        newCompositeNode(grammarAccess.getXExpression_Access().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXExpressionParams_in_ruleXExpression_6373);
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

            otherlv_8=(Token)match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_ruleXExpression_6388); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3088:1: entryRuleXExpressionParams returns [EObject current=null] : iv_ruleXExpressionParams= ruleXExpressionParams EOF ;
    public final EObject entryRuleXExpressionParams() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionParams = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3089:2: (iv_ruleXExpressionParams= ruleXExpressionParams EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3090:2: iv_ruleXExpressionParams= ruleXExpressionParams EOF
            {
             newCompositeNode(grammarAccess.getXExpressionParamsRule()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams6422);
            iv_ruleXExpressionParams=ruleXExpressionParams();

            state._fsp--;

             current =iv_ruleXExpressionParams; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionParams6432); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3097:1: ruleXExpressionParams returns [EObject current=null] : (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) ;
    public final EObject ruleXExpressionParams() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_JRParameter_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3100:28: ( (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3101:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3101:1: (this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3102:5: this_JRParameter_0= ruleJRParameter ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams6479);
            this_JRParameter_0=ruleJRParameter();

            state._fsp--;


                    current = this_JRParameter_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3110:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+ )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==KEYWORD_4) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3110:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3110:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3111:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3116:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) ) )+
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3117:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleJRParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleXExpressionParams6502); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3121:1: ( (lv_entries_3_0= ruleJRParameter ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3122:1: (lv_entries_3_0= ruleJRParameter )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3122:1: (lv_entries_3_0= ruleJRParameter )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3123:3: lv_entries_3_0= ruleJRParameter
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleJRParameter_in_ruleXExpressionParams6522);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3147:1: entryRuleJRParameter returns [EObject current=null] : iv_ruleJRParameter= ruleJRParameter EOF ;
    public final EObject entryRuleJRParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameter = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3148:2: (iv_ruleJRParameter= ruleJRParameter EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3149:2: iv_ruleJRParameter= ruleJRParameter EOF
            {
             newCompositeNode(grammarAccess.getJRParameterRule()); 
            pushFollow(FOLLOW_ruleJRParameter_in_entryRuleJRParameter6561);
            iv_ruleJRParameter=ruleJRParameter();

            state._fsp--;

             current =iv_ruleJRParameter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRParameter6571); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3156:1: ruleJRParameter returns [EObject current=null] : ( (lv_jrprm_0_0= RULE_ID ) ) ;
    public final EObject ruleJRParameter() throws RecognitionException {
        EObject current = null;

        Token lv_jrprm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3159:28: ( ( (lv_jrprm_0_0= RULE_ID ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3160:1: ( (lv_jrprm_0_0= RULE_ID ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3160:1: ( (lv_jrprm_0_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3161:1: (lv_jrprm_0_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3161:1: (lv_jrprm_0_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3162:3: lv_jrprm_0_0= RULE_ID
            {
            lv_jrprm_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJRParameter6612); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3186:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3187:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3188:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression6651);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression6661); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3195:1: ruleExpression returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3198:28: ( ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3199:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3199:1: ( ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3199:2: ( (lv_op1_0_0= ruleOperand ) ) ( ( ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3199:2: ( (lv_op1_0_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3200:1: (lv_op1_0_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3200:1: (lv_op1_0_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3201:3: lv_op1_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleExpression6707);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3217:2: ( ( ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) ) ) | ( (lv_in_2_0= ruleInOperator ) ) | ( (lv_exists_3_0= ruleExistsOperator ) ) | ( (lv_between_4_0= ruleBetween ) ) | ( (lv_like_5_0= ruleLike ) ) | ( (lv_comp_6_0= ruleComparison ) ) )
            int alt71=6;
            switch ( input.LA(1) ) {
            case KEYWORD_114:
            case KEYWORD_87:
                {
                alt71=1;
                }
                break;
            case KEYWORD_78:
            case KEYWORD_20:
                {
                alt71=2;
                }
                break;
            case KEYWORD_108:
            case KEYWORD_75:
                {
                alt71=3;
                }
                break;
            case KEYWORD_116:
            case KEYWORD_82:
                {
                alt71=4;
                }
                break;
            case KEYWORD_96:
            case KEYWORD_46:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3217:3: ( ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3217:3: ( ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3218:1: ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3218:1: ( (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3219:1: (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3219:1: (lv_isnull_1_1= KEYWORD_87 | lv_isnull_1_2= KEYWORD_114 )
                    int alt70=2;
                    int LA70_0 = input.LA(1);

                    if ( (LA70_0==KEYWORD_87) ) {
                        alt70=1;
                    }
                    else if ( (LA70_0==KEYWORD_114) ) {
                        alt70=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 70, 0, input);

                        throw nvae;
                    }
                    switch (alt70) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3220:3: lv_isnull_1_1= KEYWORD_87
                            {
                            lv_isnull_1_1=(Token)match(input,KEYWORD_87,FOLLOW_KEYWORD_87_in_ruleExpression6729); 

                                    newLeafNode(lv_isnull_1_1, grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "isnull", lv_isnull_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3233:8: lv_isnull_1_2= KEYWORD_114
                            {
                            lv_isnull_1_2=(Token)match(input,KEYWORD_114,FOLLOW_KEYWORD_114_in_ruleExpression6757); 

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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3250:6: ( (lv_in_2_0= ruleInOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3250:6: ( (lv_in_2_0= ruleInOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3251:1: (lv_in_2_0= ruleInOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3251:1: (lv_in_2_0= ruleInOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3252:3: lv_in_2_0= ruleInOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleInOperator_in_ruleExpression6798);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3269:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3269:6: ( (lv_exists_3_0= ruleExistsOperator ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3270:1: (lv_exists_3_0= ruleExistsOperator )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3270:1: (lv_exists_3_0= ruleExistsOperator )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3271:3: lv_exists_3_0= ruleExistsOperator
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getExistsExistsOperatorParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExistsOperator_in_ruleExpression6825);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3288:6: ( (lv_between_4_0= ruleBetween ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3288:6: ( (lv_between_4_0= ruleBetween ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3289:1: (lv_between_4_0= ruleBetween )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3289:1: (lv_between_4_0= ruleBetween )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3290:3: lv_between_4_0= ruleBetween
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBetween_in_ruleExpression6852);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3307:6: ( (lv_like_5_0= ruleLike ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3307:6: ( (lv_like_5_0= ruleLike ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3308:1: (lv_like_5_0= ruleLike )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3308:1: (lv_like_5_0= ruleLike )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3309:3: lv_like_5_0= ruleLike
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleLike_in_ruleExpression6879);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3326:6: ( (lv_comp_6_0= ruleComparison ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3326:6: ( (lv_comp_6_0= ruleComparison ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3327:1: (lv_comp_6_0= ruleComparison )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3327:1: (lv_comp_6_0= ruleComparison )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3328:3: lv_comp_6_0= ruleComparison
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_5_0()); 
                    	    
                    pushFollow(FOLLOW_ruleComparison_in_ruleExpression6906);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3352:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3353:2: (iv_ruleComparison= ruleComparison EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3354:2: iv_ruleComparison= ruleComparison EOF
            {
             newCompositeNode(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_ruleComparison_in_entryRuleComparison6942);
            iv_ruleComparison=ruleComparison();

            state._fsp--;

             current =iv_ruleComparison; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleComparison6952); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3361:1: ruleComparison returns [EObject current=null] : ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3364:28: ( ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3365:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3365:1: ( ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 ) ) )? ( (lv_op2_2_0= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3365:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) ) ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 ) ) )? ( (lv_op2_2_0= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3365:2: ( ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3366:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3366:1: ( (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3367:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3367:1: (lv_operator_0_1= KEYWORD_10 | lv_operator_0_2= KEYWORD_18 | lv_operator_0_3= KEYWORD_8 | lv_operator_0_4= KEYWORD_16 | lv_operator_0_5= KEYWORD_9 | lv_operator_0_6= KEYWORD_17 | lv_operator_0_7= KEYWORD_14 | lv_operator_0_8= KEYWORD_23 )
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3368:3: lv_operator_0_1= KEYWORD_10
                    {
                    lv_operator_0_1=(Token)match(input,KEYWORD_10,FOLLOW_KEYWORD_10_in_ruleComparison6998); 

                            newLeafNode(lv_operator_0_1, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3381:8: lv_operator_0_2= KEYWORD_18
                    {
                    lv_operator_0_2=(Token)match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_ruleComparison7026); 

                            newLeafNode(lv_operator_0_2, grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3394:8: lv_operator_0_3= KEYWORD_8
                    {
                    lv_operator_0_3=(Token)match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_ruleComparison7054); 

                            newLeafNode(lv_operator_0_3, grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_3, null);
                    	    

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3407:8: lv_operator_0_4= KEYWORD_16
                    {
                    lv_operator_0_4=(Token)match(input,KEYWORD_16,FOLLOW_KEYWORD_16_in_ruleComparison7082); 

                            newLeafNode(lv_operator_0_4, grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_4, null);
                    	    

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3420:8: lv_operator_0_5= KEYWORD_9
                    {
                    lv_operator_0_5=(Token)match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_ruleComparison7110); 

                            newLeafNode(lv_operator_0_5, grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_5, null);
                    	    

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3433:8: lv_operator_0_6= KEYWORD_17
                    {
                    lv_operator_0_6=(Token)match(input,KEYWORD_17,FOLLOW_KEYWORD_17_in_ruleComparison7138); 

                            newLeafNode(lv_operator_0_6, grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_6, null);
                    	    

                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3446:8: lv_operator_0_7= KEYWORD_14
                    {
                    lv_operator_0_7=(Token)match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_ruleComparison7166); 

                            newLeafNode(lv_operator_0_7, grammarAccess.getComparisonAccess().getOperatorExclamationMarkEqualsSignKeyword_0_0_6());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getComparisonRule());
                    	        }
                           		setWithLastConsumed(current, "operator", lv_operator_0_7, null);
                    	    

                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3459:8: lv_operator_0_8= KEYWORD_23
                    {
                    lv_operator_0_8=(Token)match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_ruleComparison7194); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3475:2: ( ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 ) ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==KEYWORD_52||LA74_0==KEYWORD_26||LA74_0==KEYWORD_28) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3476:1: ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3476:1: ( (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3477:1: (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3477:1: (lv_subOperator_1_1= KEYWORD_28 | lv_subOperator_1_2= KEYWORD_26 | lv_subOperator_1_3= KEYWORD_52 )
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
                    case KEYWORD_52:
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
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3478:3: lv_subOperator_1_1= KEYWORD_28
                            {
                            lv_subOperator_1_1=(Token)match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_ruleComparison7229); 

                                    newLeafNode(lv_subOperator_1_1, grammarAccess.getComparisonAccess().getSubOperatorANYKeyword_1_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3491:8: lv_subOperator_1_2= KEYWORD_26
                            {
                            lv_subOperator_1_2=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleComparison7257); 

                                    newLeafNode(lv_subOperator_1_2, grammarAccess.getComparisonAccess().getSubOperatorALLKeyword_1_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getComparisonRule());
                            	        }
                                   		setWithLastConsumed(current, "subOperator", lv_subOperator_1_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3504:8: lv_subOperator_1_3= KEYWORD_52
                            {
                            lv_subOperator_1_3=(Token)match(input,KEYWORD_52,FOLLOW_KEYWORD_52_in_ruleComparison7285); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3520:3: ( (lv_op2_2_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3521:1: (lv_op2_2_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3521:1: (lv_op2_2_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3522:3: lv_op2_2_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleComparison7321);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3546:1: entryRuleLike returns [EObject current=null] : iv_ruleLike= ruleLike EOF ;
    public final EObject entryRuleLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLike = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3547:2: (iv_ruleLike= ruleLike EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3548:2: iv_ruleLike= ruleLike EOF
            {
             newCompositeNode(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_ruleLike_in_entryRuleLike7356);
            iv_ruleLike=ruleLike();

            state._fsp--;

             current =iv_ruleLike; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLike7366); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3555:1: ruleLike returns [EObject current=null] : ( ( ( (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) ;
    public final EObject ruleLike() throws RecognitionException {
        EObject current = null;

        Token lv_opLike_0_1=null;
        Token lv_opLike_0_2=null;
        EObject lv_op2_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3558:28: ( ( ( ( (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3559:1: ( ( ( (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3559:1: ( ( ( (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3559:2: ( ( (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 ) ) ) ( (lv_op2_1_0= ruleLikeOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3559:2: ( ( (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3560:1: ( (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3560:1: ( (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3561:1: (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3561:1: (lv_opLike_0_1= KEYWORD_46 | lv_opLike_0_2= KEYWORD_96 )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==KEYWORD_46) ) {
                alt75=1;
            }
            else if ( (LA75_0==KEYWORD_96) ) {
                alt75=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3562:3: lv_opLike_0_1= KEYWORD_46
                    {
                    lv_opLike_0_1=(Token)match(input,KEYWORD_46,FOLLOW_KEYWORD_46_in_ruleLike7412); 

                            newLeafNode(lv_opLike_0_1, grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLikeRule());
                    	        }
                           		setWithLastConsumed(current, "opLike", lv_opLike_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3575:8: lv_opLike_0_2= KEYWORD_96
                    {
                    lv_opLike_0_2=(Token)match(input,KEYWORD_96,FOLLOW_KEYWORD_96_in_ruleLike7440); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3591:2: ( (lv_op2_1_0= ruleLikeOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3592:1: (lv_op2_1_0= ruleLikeOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3592:1: (lv_op2_1_0= ruleLikeOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3593:3: lv_op2_1_0= ruleLikeOperand
            {
             
            	        newCompositeNode(grammarAccess.getLikeAccess().getOp2LikeOperandParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLikeOperand_in_ruleLike7475);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3617:1: entryRuleLikeOperand returns [EObject current=null] : iv_ruleLikeOperand= ruleLikeOperand EOF ;
    public final EObject entryRuleLikeOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLikeOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3618:2: (iv_ruleLikeOperand= ruleLikeOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3619:2: iv_ruleLikeOperand= ruleLikeOperand EOF
            {
             newCompositeNode(grammarAccess.getLikeOperandRule()); 
            pushFollow(FOLLOW_ruleLikeOperand_in_entryRuleLikeOperand7510);
            iv_ruleLikeOperand=ruleLikeOperand();

            state._fsp--;

             current =iv_ruleLikeOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLikeOperand7520); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3626:1: ruleLikeOperand returns [EObject current=null] : ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) ;
    public final EObject ruleLikeOperand() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op2_0_0 = null;

        EObject lv_fop2_1_0 = null;

        EObject lv_fcast_2_0 = null;

        EObject lv_fparam_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3629:28: ( ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3630:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3630:1: ( ( (lv_op2_0_0= ruleStringOperand ) ) | ( (lv_fop2_1_0= ruleOperandFunction ) ) | ( (lv_fcast_2_0= ruleOpFunctionCast ) ) | ( (lv_fparam_3_0= ruleParameterOperand ) ) )
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
            case KEYWORD_57:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3630:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3630:2: ( (lv_op2_0_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3631:1: (lv_op2_0_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3631:1: (lv_op2_0_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3632:3: lv_op2_0_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getOp2StringOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleLikeOperand7566);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3649:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3649:6: ( (lv_fop2_1_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3650:1: (lv_fop2_1_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3650:1: (lv_fop2_1_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3651:3: lv_fop2_1_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFop2OperandFunctionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleLikeOperand7593);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3668:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3668:6: ( (lv_fcast_2_0= ruleOpFunctionCast ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3669:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3669:1: (lv_fcast_2_0= ruleOpFunctionCast )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3670:3: lv_fcast_2_0= ruleOpFunctionCast
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFcastOpFunctionCastParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionCast_in_ruleLikeOperand7620);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3687:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3687:6: ( (lv_fparam_3_0= ruleParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3688:1: (lv_fparam_3_0= ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3688:1: (lv_fparam_3_0= ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3689:3: lv_fparam_3_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getLikeOperandAccess().getFparamParameterOperandParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParameterOperand_in_ruleLikeOperand7647);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3713:1: entryRuleBetween returns [EObject current=null] : iv_ruleBetween= ruleBetween EOF ;
    public final EObject entryRuleBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBetween = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3714:2: (iv_ruleBetween= ruleBetween EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3715:2: iv_ruleBetween= ruleBetween EOF
            {
             newCompositeNode(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_ruleBetween_in_entryRuleBetween7682);
            iv_ruleBetween=ruleBetween();

            state._fsp--;

             current =iv_ruleBetween; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBetween7692); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3722:1: ruleBetween returns [EObject current=null] : ( ( ( (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperandGroup ) ) ) ;
    public final EObject ruleBetween() throws RecognitionException {
        EObject current = null;

        Token lv_opBetween_0_1=null;
        Token lv_opBetween_0_2=null;
        Token otherlv_2=null;
        EObject lv_op2_1_0 = null;

        EObject lv_op3_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3725:28: ( ( ( ( (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperandGroup ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3726:1: ( ( ( (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperandGroup ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3726:1: ( ( ( (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperandGroup ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3726:2: ( ( (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 ) ) ) ( (lv_op2_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_27 ( (lv_op3_3_0= ruleOperandGroup ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3726:2: ( ( (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3727:1: ( (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3727:1: ( (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3728:1: (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3728:1: (lv_opBetween_0_1= KEYWORD_82 | lv_opBetween_0_2= KEYWORD_116 )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==KEYWORD_82) ) {
                alt77=1;
            }
            else if ( (LA77_0==KEYWORD_116) ) {
                alt77=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }
            switch (alt77) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3729:3: lv_opBetween_0_1= KEYWORD_82
                    {
                    lv_opBetween_0_1=(Token)match(input,KEYWORD_82,FOLLOW_KEYWORD_82_in_ruleBetween7738); 

                            newLeafNode(lv_opBetween_0_1, grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBetweenRule());
                    	        }
                           		setWithLastConsumed(current, "opBetween", lv_opBetween_0_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3742:8: lv_opBetween_0_2= KEYWORD_116
                    {
                    lv_opBetween_0_2=(Token)match(input,KEYWORD_116,FOLLOW_KEYWORD_116_in_ruleBetween7766); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3758:2: ( (lv_op2_1_0= ruleOperandGroup ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3759:1: (lv_op2_1_0= ruleOperandGroup )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3759:1: (lv_op2_1_0= ruleOperandGroup )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3760:3: lv_op2_1_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp2OperandGroupParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandGroup_in_ruleBetween7801);
            lv_op2_1_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBetweenRule());
            	        }
                   		set(
                   			current, 
                   			"op2",
                    		lv_op2_1_0, 
                    		"OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleBetween7814); 

                	newLeafNode(otherlv_2, grammarAccess.getBetweenAccess().getANDKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3781:1: ( (lv_op3_3_0= ruleOperandGroup ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3782:1: (lv_op3_3_0= ruleOperandGroup )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3782:1: (lv_op3_3_0= ruleOperandGroup )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3783:3: lv_op3_3_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getBetweenAccess().getOp3OperandGroupParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandGroup_in_ruleBetween7834);
            lv_op3_3_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBetweenRule());
            	        }
                   		set(
                   			current, 
                   			"op3",
                    		lv_op3_3_0, 
                    		"OperandGroup");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3807:1: entryRuleInOperator returns [EObject current=null] : iv_ruleInOperator= ruleInOperator EOF ;
    public final EObject entryRuleInOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOperator = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3808:2: (iv_ruleInOperator= ruleInOperator EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3809:2: iv_ruleInOperator= ruleInOperator EOF
            {
             newCompositeNode(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_ruleInOperator_in_entryRuleInOperator7869);
            iv_ruleInOperator=ruleInOperator();

            state._fsp--;

             current =iv_ruleInOperator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInOperator7879); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3816:1: ruleInOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleInOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3819:28: ( ( () ( ( (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3820:1: ( () ( ( (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3820:1: ( () ( ( (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3820:2: () ( ( (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3820:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3821:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInOperatorAccess().getInOperAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3826:2: ( ( (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3827:1: ( (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3827:1: ( (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3828:1: (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3828:1: (lv_op_1_1= KEYWORD_78 | lv_op_1_2= KEYWORD_20 )
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==KEYWORD_78) ) {
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3829:3: lv_op_1_1= KEYWORD_78
                    {
                    lv_op_1_1=(Token)match(input,KEYWORD_78,FOLLOW_KEYWORD_78_in_ruleInOperator7934); 

                            newLeafNode(lv_op_1_1, grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getInOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3842:8: lv_op_1_2= KEYWORD_20
                    {
                    lv_op_1_2=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleInOperator7962); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3858:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==KEYWORD_1) ) {
                int LA79_1 = input.LA(2);

                if ( (LA79_1==KEYWORD_81) ) {
                    alt79=1;
                }
                else if ( ((LA79_1>=RULE_SIGNED_DOUBLE && LA79_1<=RULE_STRING_)) ) {
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3858:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3858:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3859:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3859:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3860:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleInOperator7998);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3877:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3877:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3878:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3878:1: (lv_opList_3_0= ruleOperandListGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3879:3: lv_opList_3_0= ruleOperandListGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getInOperatorAccess().getOpListOperandListGroupParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandListGroup_in_ruleInOperator8025);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3903:1: entryRuleExistsOperator returns [EObject current=null] : iv_ruleExistsOperator= ruleExistsOperator EOF ;
    public final EObject entryRuleExistsOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistsOperator = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3904:2: (iv_ruleExistsOperator= ruleExistsOperator EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3905:2: iv_ruleExistsOperator= ruleExistsOperator EOF
            {
             newCompositeNode(grammarAccess.getExistsOperatorRule()); 
            pushFollow(FOLLOW_ruleExistsOperator_in_entryRuleExistsOperator8061);
            iv_ruleExistsOperator=ruleExistsOperator();

            state._fsp--;

             current =iv_ruleExistsOperator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExistsOperator8071); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3912:1: ruleExistsOperator returns [EObject current=null] : ( () ( ( (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) ;
    public final EObject ruleExistsOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_subquery_2_0 = null;

        EObject lv_opList_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3915:28: ( ( () ( ( (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3916:1: ( () ( ( (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3916:1: ( () ( ( (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3916:2: () ( ( (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 ) ) ) ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3916:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3917:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExistsOperatorAccess().getExistsOperAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3922:2: ( ( (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3923:1: ( (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3923:1: ( (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3924:1: (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3924:1: (lv_op_1_1= KEYWORD_108 | lv_op_1_2= KEYWORD_75 )
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==KEYWORD_108) ) {
                alt80=1;
            }
            else if ( (LA80_0==KEYWORD_75) ) {
                alt80=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;
            }
            switch (alt80) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3925:3: lv_op_1_1= KEYWORD_108
                    {
                    lv_op_1_1=(Token)match(input,KEYWORD_108,FOLLOW_KEYWORD_108_in_ruleExistsOperator8126); 

                            newLeafNode(lv_op_1_1, grammarAccess.getExistsOperatorAccess().getOpNOTEXISTSKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getExistsOperatorRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3938:8: lv_op_1_2= KEYWORD_75
                    {
                    lv_op_1_2=(Token)match(input,KEYWORD_75,FOLLOW_KEYWORD_75_in_ruleExistsOperator8154); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3954:2: ( ( (lv_subquery_2_0= ruleSubQueryOperand ) ) | ( (lv_opList_3_0= ruleOperandListGroup ) ) )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==KEYWORD_1) ) {
                int LA81_1 = input.LA(2);

                if ( (LA81_1==KEYWORD_81) ) {
                    alt81=1;
                }
                else if ( ((LA81_1>=RULE_SIGNED_DOUBLE && LA81_1<=RULE_STRING_)) ) {
                    alt81=2;
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3954:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3954:3: ( (lv_subquery_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3955:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3955:1: (lv_subquery_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3956:3: lv_subquery_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleExistsOperator8190);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3973:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3973:6: ( (lv_opList_3_0= ruleOperandListGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3974:1: (lv_opList_3_0= ruleOperandListGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3974:1: (lv_opList_3_0= ruleOperandListGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3975:3: lv_opList_3_0= ruleOperandListGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getExistsOperatorAccess().getOpListOperandListGroupParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandListGroup_in_ruleExistsOperator8217);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:3999:1: entryRuleOperandListGroup returns [EObject current=null] : iv_ruleOperandListGroup= ruleOperandListGroup EOF ;
    public final EObject entryRuleOperandListGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandListGroup = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4000:2: (iv_ruleOperandListGroup= ruleOperandListGroup EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4001:2: iv_ruleOperandListGroup= ruleOperandListGroup EOF
            {
             newCompositeNode(grammarAccess.getOperandListGroupRule()); 
            pushFollow(FOLLOW_ruleOperandListGroup_in_entryRuleOperandListGroup8253);
            iv_ruleOperandListGroup=ruleOperandListGroup();

            state._fsp--;

             current =iv_ruleOperandListGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandListGroup8263); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4008:1: ruleOperandListGroup returns [EObject current=null] : (otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2 ) ;
    public final EObject ruleOperandListGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_opGroup_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4011:28: ( (otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4012:1: (otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4012:1: (otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4013:2: otherlv_0= KEYWORD_1 ( (lv_opGroup_1_0= ruleOperandList ) ) otherlv_2= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleOperandListGroup8301); 

                	newLeafNode(otherlv_0, grammarAccess.getOperandListGroupAccess().getLeftParenthesisKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4017:1: ( (lv_opGroup_1_0= ruleOperandList ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4018:1: (lv_opGroup_1_0= ruleOperandList )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4018:1: (lv_opGroup_1_0= ruleOperandList )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4019:3: lv_opGroup_1_0= ruleOperandList
            {
             
            	        newCompositeNode(grammarAccess.getOperandListGroupAccess().getOpGroupOperandListParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandList_in_ruleOperandListGroup8321);
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

            otherlv_2=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOperandListGroup8334); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4048:1: entryRuleOperandList returns [EObject current=null] : iv_ruleOperandList= ruleOperandList EOF ;
    public final EObject entryRuleOperandList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandList = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4049:2: (iv_ruleOperandList= ruleOperandList EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4050:2: iv_ruleOperandList= ruleOperandList EOF
            {
             newCompositeNode(grammarAccess.getOperandListRule()); 
            pushFollow(FOLLOW_ruleOperandList_in_entryRuleOperandList8368);
            iv_ruleOperandList=ruleOperandList();

            state._fsp--;

             current =iv_ruleOperandList; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandList8378); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4057:1: ruleOperandList returns [EObject current=null] : (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) ;
    public final EObject ruleOperandList() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ScalarOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4060:28: ( (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4061:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4061:1: (this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4062:5: this_ScalarOperand_0= ruleScalarOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOperandListAccess().getScalarOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleScalarOperand_in_ruleOperandList8425);
            this_ScalarOperand_0=ruleScalarOperand();

            state._fsp--;


                    current = this_ScalarOperand_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4070:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+ )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==KEYWORD_4) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4070:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4070:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4071:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4076:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) ) )+
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4077:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOperandList8448); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4081:1: ( (lv_entries_3_0= ruleScalarOperand ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4082:1: (lv_entries_3_0= ruleScalarOperand )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4082:1: (lv_entries_3_0= ruleScalarOperand )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4083:3: lv_entries_3_0= ruleScalarOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOperandListAccess().getEntriesScalarOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleScalarOperand_in_ruleOperandList8468);
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


    // $ANTLR start "entryRuleOperandGroup"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4107:1: entryRuleOperandGroup returns [EObject current=null] : iv_ruleOperandGroup= ruleOperandGroup EOF ;
    public final EObject entryRuleOperandGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandGroup = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4108:2: (iv_ruleOperandGroup= ruleOperandGroup EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4109:2: iv_ruleOperandGroup= ruleOperandGroup EOF
            {
             newCompositeNode(grammarAccess.getOperandGroupRule()); 
            pushFollow(FOLLOW_ruleOperandGroup_in_entryRuleOperandGroup8507);
            iv_ruleOperandGroup=ruleOperandGroup();

            state._fsp--;

             current =iv_ruleOperandGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandGroup8517); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4116:1: ruleOperandGroup returns [EObject current=null] : (this_Operand_0= ruleOperand | (otherlv_1= KEYWORD_1 this_Operand_2= ruleOperand otherlv_3= KEYWORD_2 ) ) ;
    public final EObject ruleOperandGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_Operand_0 = null;

        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4119:28: ( (this_Operand_0= ruleOperand | (otherlv_1= KEYWORD_1 this_Operand_2= ruleOperand otherlv_3= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4120:1: (this_Operand_0= ruleOperand | (otherlv_1= KEYWORD_1 this_Operand_2= ruleOperand otherlv_3= KEYWORD_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4120:1: (this_Operand_0= ruleOperand | (otherlv_1= KEYWORD_1 this_Operand_2= ruleOperand otherlv_3= KEYWORD_2 ) )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==KEYWORD_84||LA84_0==KEYWORD_57||LA84_0==KEYWORD_36||(LA84_0>=RULE_JRPARAM && LA84_0<=RULE_JRNPARAM)||(LA84_0>=RULE_UNSIGNED && LA84_0<=RULE_SIGNED_DOUBLE)||(LA84_0>=RULE_STRING_ && LA84_0<=RULE_ID)) ) {
                alt84=1;
            }
            else if ( (LA84_0==KEYWORD_1) ) {
                int LA84_2 = input.LA(2);

                if ( (LA84_2==KEYWORD_84||LA84_2==KEYWORD_57||LA84_2==KEYWORD_36||LA84_2==KEYWORD_1||(LA84_2>=RULE_JRPARAM && LA84_2<=RULE_JRNPARAM)||(LA84_2>=RULE_UNSIGNED && LA84_2<=RULE_SIGNED_DOUBLE)||(LA84_2>=RULE_STRING_ && LA84_2<=RULE_ID)) ) {
                    alt84=2;
                }
                else if ( (LA84_2==KEYWORD_81) ) {
                    alt84=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4121:5: this_Operand_0= ruleOperand
                    {
                     
                            newCompositeNode(grammarAccess.getOperandGroupAccess().getOperandParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleOperand_in_ruleOperandGroup8564);
                    this_Operand_0=ruleOperand();

                    state._fsp--;


                            current = this_Operand_0;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4130:6: (otherlv_1= KEYWORD_1 this_Operand_2= ruleOperand otherlv_3= KEYWORD_2 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4130:6: (otherlv_1= KEYWORD_1 this_Operand_2= ruleOperand otherlv_3= KEYWORD_2 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4131:2: otherlv_1= KEYWORD_1 this_Operand_2= ruleOperand otherlv_3= KEYWORD_2
                    {
                    otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleOperandGroup8583); 

                        	newLeafNode(otherlv_1, grammarAccess.getOperandGroupAccess().getLeftParenthesisKeyword_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getOperandGroupAccess().getOperandParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_ruleOperand_in_ruleOperandGroup8604);
                    this_Operand_2=ruleOperand();

                    state._fsp--;


                            current = this_Operand_2;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOperandGroup8616); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4157:1: entryRuleOperand returns [EObject current=null] : iv_ruleOperand= ruleOperand EOF ;
    public final EObject entryRuleOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4158:2: (iv_ruleOperand= ruleOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4159:2: iv_ruleOperand= ruleOperand EOF
            {
             newCompositeNode(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_ruleOperand_in_entryRuleOperand8651);
            iv_ruleOperand=ruleOperand();

            state._fsp--;

             current =iv_ruleOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperand8661); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4166:1: ruleOperand returns [EObject current=null] : ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () otherlv_6= KEYWORD_24 ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= KEYWORD_7 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4169:28: ( ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () otherlv_6= KEYWORD_24 ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= KEYWORD_7 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4170:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () otherlv_6= KEYWORD_24 ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= KEYWORD_7 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4170:1: ( ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () otherlv_6= KEYWORD_24 ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= KEYWORD_7 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4170:2: ( (lv_op1_0_0= ruleOperandFragment ) ) ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () otherlv_6= KEYWORD_24 ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= KEYWORD_7 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4170:2: ( (lv_op1_0_0= ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4171:1: (lv_op1_0_0= ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4171:1: (lv_op1_0_0= ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4172:3: lv_op1_0_0= ruleOperandFragment
            {
             
            	        newCompositeNode(grammarAccess.getOperandAccess().getOp1OperandFragmentParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand8707);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4188:2: ( ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () otherlv_6= KEYWORD_24 ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= KEYWORD_7 ) ) ( (lv_right_11_0= ruleOperandFragment ) ) )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( (LA86_0==KEYWORD_24||LA86_0==KEYWORD_3||LA86_0==KEYWORD_5||LA86_0==KEYWORD_7||LA86_0==RULE_STAR) ) {
                    alt86=1;
                }


                switch (alt86) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4188:3: ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () otherlv_6= KEYWORD_24 ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= KEYWORD_7 ) ) ( (lv_right_11_0= ruleOperandFragment ) )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4188:3: ( ( () otherlv_2= KEYWORD_3 ) | ( () otherlv_4= KEYWORD_5 ) | ( () otherlv_6= KEYWORD_24 ) | ( () this_STAR_8= RULE_STAR ) | ( () otherlv_10= KEYWORD_7 ) )
            	    int alt85=5;
            	    switch ( input.LA(1) ) {
            	    case KEYWORD_3:
            	        {
            	        alt85=1;
            	        }
            	        break;
            	    case KEYWORD_5:
            	        {
            	        alt85=2;
            	        }
            	        break;
            	    case KEYWORD_24:
            	        {
            	        alt85=3;
            	        }
            	        break;
            	    case RULE_STAR:
            	        {
            	        alt85=4;
            	        }
            	        break;
            	    case KEYWORD_7:
            	        {
            	        alt85=5;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 85, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt85) {
            	        case 1 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4188:4: ( () otherlv_2= KEYWORD_3 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4188:4: ( () otherlv_2= KEYWORD_3 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4188:5: () otherlv_2= KEYWORD_3
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4188:5: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4189:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getPlusLeftAction_1_0_0_0(),
            	                        current);
            	                

            	            }

            	            otherlv_2=(Token)match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_ruleOperand8732); 

            	                	newLeafNode(otherlv_2, grammarAccess.getOperandAccess().getPlusSignKeyword_1_0_0_1());
            	                

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4200:6: ( () otherlv_4= KEYWORD_5 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4200:6: ( () otherlv_4= KEYWORD_5 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4200:7: () otherlv_4= KEYWORD_5
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4200:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4201:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMinusLeftAction_1_0_1_0(),
            	                        current);
            	                

            	            }

            	            otherlv_4=(Token)match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_ruleOperand8761); 

            	                	newLeafNode(otherlv_4, grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_0_1_1());
            	                

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4212:6: ( () otherlv_6= KEYWORD_24 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4212:6: ( () otherlv_6= KEYWORD_24 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4212:7: () otherlv_6= KEYWORD_24
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4212:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4213:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getConcatLeftAction_1_0_2_0(),
            	                        current);
            	                

            	            }

            	            otherlv_6=(Token)match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_ruleOperand8790); 

            	                	newLeafNode(otherlv_6, grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_0_2_1());
            	                

            	            }


            	            }
            	            break;
            	        case 4 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4224:6: ( () this_STAR_8= RULE_STAR )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4224:6: ( () this_STAR_8= RULE_STAR )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4224:7: () this_STAR_8= RULE_STAR
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4224:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4225:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getMultiplyLeftAction_1_0_3_0(),
            	                        current);
            	                

            	            }

            	            this_STAR_8=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperand8817); 
            	             
            	                newLeafNode(this_STAR_8, grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_0_3_1()); 
            	                

            	            }


            	            }
            	            break;
            	        case 5 :
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4235:6: ( () otherlv_10= KEYWORD_7 )
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4235:6: ( () otherlv_10= KEYWORD_7 )
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4235:7: () otherlv_10= KEYWORD_7
            	            {
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4235:7: ()
            	            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4236:5: 
            	            {

            	                    current = forceCreateModelElementAndSet(
            	                        grammarAccess.getOperandAccess().getDivisionLeftAction_1_0_4_0(),
            	                        current);
            	                

            	            }

            	            otherlv_10=(Token)match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_ruleOperand8846); 

            	                	newLeafNode(otherlv_10, grammarAccess.getOperandAccess().getSolidusKeyword_1_0_4_1());
            	                

            	            }


            	            }
            	            break;

            	    }

            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4246:3: ( (lv_right_11_0= ruleOperandFragment ) )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4247:1: (lv_right_11_0= ruleOperandFragment )
            	    {
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4247:1: (lv_right_11_0= ruleOperandFragment )
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4248:3: lv_right_11_0= ruleOperandFragment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOperandAccess().getRightOperandFragmentParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleOperandFragment_in_ruleOperand8868);
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
            	    break loop86;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4272:1: entryRuleOperandFragment returns [EObject current=null] : iv_ruleOperandFragment= ruleOperandFragment EOF ;
    public final EObject entryRuleOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4273:2: (iv_ruleOperandFragment= ruleOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4274:2: iv_ruleOperandFragment= ruleOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment8905);
            iv_ruleOperandFragment=ruleOperandFragment();

            state._fsp--;

             current =iv_ruleOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFragment8915); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4281:1: ruleOperandFragment returns [EObject current=null] : ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4284:28: ( ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4285:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4285:1: ( ( (lv_column_0_0= ruleColumnOperand ) ) | ( (lv_xop_1_0= ruleXOperandFragment ) ) | ( (lv_subq_2_0= ruleSubQueryOperand ) ) | ( (lv_fcast_3_0= ruleOpFunctionCast ) ) | ( (lv_fext_4_0= ruleFunctionExtract ) ) | ( (lv_func_5_0= ruleOperandFunction ) ) | ( (lv_sqlcase_6_0= ruleSQLCASE ) ) )
            int alt87=7;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA87_1 = input.LA(2);

                if ( (LA87_1==KEYWORD_1) ) {
                    alt87=6;
                }
                else if ( (LA87_1==EOF||LA87_1==KEYWORD_122||LA87_1==KEYWORD_119||LA87_1==KEYWORD_111||LA87_1==KEYWORD_114||LA87_1==KEYWORD_116||LA87_1==KEYWORD_108||(LA87_1>=KEYWORD_101 && LA87_1<=KEYWORD_103)||(LA87_1>=KEYWORD_95 && LA87_1<=KEYWORD_96)||LA87_1==KEYWORD_98||LA87_1==KEYWORD_82||(LA87_1>=KEYWORD_87 && LA87_1<=KEYWORD_88)||(LA87_1>=KEYWORD_74 && LA87_1<=KEYWORD_76)||(LA87_1>=KEYWORD_78 && LA87_1<=KEYWORD_79)||LA87_1==KEYWORD_58||LA87_1==KEYWORD_61||(LA87_1>=KEYWORD_63 && LA87_1<=KEYWORD_64)||LA87_1==KEYWORD_67||(LA87_1>=KEYWORD_70 && LA87_1<=KEYWORD_73)||(LA87_1>=KEYWORD_37 && LA87_1<=KEYWORD_40)||LA87_1==KEYWORD_42||LA87_1==KEYWORD_44||LA87_1==KEYWORD_46||LA87_1==KEYWORD_51||LA87_1==KEYWORD_53||LA87_1==KEYWORD_55||LA87_1==KEYWORD_25||LA87_1==KEYWORD_27||LA87_1==KEYWORD_29||LA87_1==KEYWORD_31||LA87_1==KEYWORD_14||(LA87_1>=KEYWORD_16 && LA87_1<=KEYWORD_20)||(LA87_1>=KEYWORD_22 && LA87_1<=KEYWORD_24)||(LA87_1>=KEYWORD_2 && LA87_1<=KEYWORD_10)||(LA87_1>=KEYWORD_12 && LA87_1<=KEYWORD_13)||(LA87_1>=RULE_JRNPARAM && LA87_1<=RULE_STAR)||(LA87_1>=RULE_STRING && LA87_1<=RULE_ID)) ) {
                    alt87=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 87, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_DBNAME:
                {
                alt87=1;
                }
                break;
            case RULE_JRPARAM:
            case RULE_JRNPARAM:
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt87=2;
                }
                break;
            case KEYWORD_1:
                {
                alt87=3;
                }
                break;
            case KEYWORD_57:
                {
                alt87=4;
                }
                break;
            case KEYWORD_84:
                {
                alt87=5;
                }
                break;
            case KEYWORD_36:
                {
                alt87=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }

            switch (alt87) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4285:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4285:2: ( (lv_column_0_0= ruleColumnOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4286:1: (lv_column_0_0= ruleColumnOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4286:1: (lv_column_0_0= ruleColumnOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4287:3: lv_column_0_0= ruleColumnOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleColumnOperand_in_ruleOperandFragment8961);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4304:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4304:6: ( (lv_xop_1_0= ruleXOperandFragment ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4305:1: (lv_xop_1_0= ruleXOperandFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4305:1: (lv_xop_1_0= ruleXOperandFragment )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4306:3: lv_xop_1_0= ruleXOperandFragment
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleXOperandFragment_in_ruleOperandFragment8988);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4323:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4323:6: ( (lv_subq_2_0= ruleSubQueryOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4324:1: (lv_subq_2_0= ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4324:1: (lv_subq_2_0= ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4325:3: lv_subq_2_0= ruleSubQueryOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment9015);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4342:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4342:6: ( (lv_fcast_3_0= ruleOpFunctionCast ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4343:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4343:1: (lv_fcast_3_0= ruleOpFunctionCast )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4344:3: lv_fcast_3_0= ruleOpFunctionCast
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFcastOpFunctionCastParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionCast_in_ruleOperandFragment9042);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4361:6: ( (lv_fext_4_0= ruleFunctionExtract ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4361:6: ( (lv_fext_4_0= ruleFunctionExtract ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4362:1: (lv_fext_4_0= ruleFunctionExtract )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4362:1: (lv_fext_4_0= ruleFunctionExtract )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4363:3: lv_fext_4_0= ruleFunctionExtract
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFextFunctionExtractParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFunctionExtract_in_ruleOperandFragment9069);
                    lv_fext_4_0=ruleFunctionExtract();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"fext",
                            		lv_fext_4_0, 
                            		"FunctionExtract");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4380:6: ( (lv_func_5_0= ruleOperandFunction ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4380:6: ( (lv_func_5_0= ruleOperandFunction ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4381:1: (lv_func_5_0= ruleOperandFunction )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4381:1: (lv_func_5_0= ruleOperandFunction )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4382:3: lv_func_5_0= ruleOperandFunction
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getFuncOperandFunctionParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandFunction_in_ruleOperandFragment9096);
                    lv_func_5_0=ruleOperandFunction();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"func",
                            		lv_func_5_0, 
                            		"OperandFunction");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4399:6: ( (lv_sqlcase_6_0= ruleSQLCASE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4399:6: ( (lv_sqlcase_6_0= ruleSQLCASE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4400:1: (lv_sqlcase_6_0= ruleSQLCASE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4400:1: (lv_sqlcase_6_0= ruleSQLCASE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4401:3: lv_sqlcase_6_0= ruleSQLCASE
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFragmentAccess().getSqlcaseSQLCASEParserRuleCall_6_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSQLCASE_in_ruleOperandFragment9123);
                    lv_sqlcase_6_0=ruleSQLCASE();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"sqlcase",
                            		lv_sqlcase_6_0, 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4425:1: entryRuleOperandFunction returns [EObject current=null] : iv_ruleOperandFunction= ruleOperandFunction EOF ;
    public final EObject entryRuleOperandFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperandFunction = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4426:2: (iv_ruleOperandFunction= ruleOperandFunction EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4427:2: iv_ruleOperandFunction= ruleOperandFunction EOF
            {
             newCompositeNode(grammarAccess.getOperandFunctionRule()); 
            pushFollow(FOLLOW_ruleOperandFunction_in_entryRuleOperandFunction9158);
            iv_ruleOperandFunction=ruleOperandFunction();

            state._fsp--;

             current =iv_ruleOperandFunction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFunction9168); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4434:1: ruleOperandFunction returns [EObject current=null] : ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) ;
    public final EObject ruleOperandFunction() throws RecognitionException {
        EObject current = null;

        Token lv_star_2_0=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_fname_1_0 = null;

        EObject lv_args_3_0 = null;

        EObject lv_fan_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4437:28: ( ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4438:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4438:1: ( () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4438:2: () ( (lv_fname_1_0= ruleFNAME ) ) ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )? otherlv_4= KEYWORD_2 ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4438:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4439:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperandFunctionAccess().getOpFunctionAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4444:2: ( (lv_fname_1_0= ruleFNAME ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4445:1: (lv_fname_1_0= ruleFNAME )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4445:1: (lv_fname_1_0= ruleFNAME )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4446:3: lv_fname_1_0= ruleFNAME
            {
             
            	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFnameFNAMEParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFNAME_in_ruleOperandFunction9223);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4462:2: ( ( (lv_star_2_0= RULE_STAR ) ) | ( (lv_args_3_0= ruleOpFunctionArg ) ) )?
            int alt88=3;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==RULE_STAR) ) {
                alt88=1;
            }
            else if ( (LA88_0==KEYWORD_94||LA88_0==KEYWORD_84||LA88_0==KEYWORD_57||LA88_0==KEYWORD_36||LA88_0==KEYWORD_26||LA88_0==KEYWORD_1||(LA88_0>=RULE_JRPARAM && LA88_0<=RULE_JRNPARAM)||(LA88_0>=RULE_UNSIGNED && LA88_0<=RULE_SIGNED_DOUBLE)||(LA88_0>=RULE_STRING_ && LA88_0<=RULE_ID)) ) {
                alt88=2;
            }
            switch (alt88) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4462:3: ( (lv_star_2_0= RULE_STAR ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4462:3: ( (lv_star_2_0= RULE_STAR ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4463:1: (lv_star_2_0= RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4463:1: (lv_star_2_0= RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4464:3: lv_star_2_0= RULE_STAR
                    {
                    lv_star_2_0=(Token)match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleOperandFunction9241); 

                    			newLeafNode(lv_star_2_0, grammarAccess.getOperandFunctionAccess().getStarSTARTerminalRuleCall_2_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOperandFunctionRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"star",
                            		lv_star_2_0, 
                            		"STAR");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4481:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4481:6: ( (lv_args_3_0= ruleOpFunctionArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4482:1: (lv_args_3_0= ruleOpFunctionArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4482:1: (lv_args_3_0= ruleOpFunctionArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4483:3: lv_args_3_0= ruleOpFunctionArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getArgsOpFunctionArgParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionArg_in_ruleOperandFunction9273);
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

            otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOperandFunction9288); 

                	newLeafNode(otherlv_4, grammarAccess.getOperandFunctionAccess().getRightParenthesisKeyword_3());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4504:1: ( (lv_fan_5_0= ruleFunctionAnalytical ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==KEYWORD_50) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4505:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4505:1: (lv_fan_5_0= ruleFunctionAnalytical )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4506:3: lv_fan_5_0= ruleFunctionAnalytical
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperandFunctionAccess().getFanFunctionAnalyticalParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFunctionAnalytical_in_ruleOperandFunction9308);
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


    // $ANTLR start "entryRuleFunctionExtract"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4530:1: entryRuleFunctionExtract returns [EObject current=null] : iv_ruleFunctionExtract= ruleFunctionExtract EOF ;
    public final EObject entryRuleFunctionExtract() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionExtract = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4531:2: (iv_ruleFunctionExtract= ruleFunctionExtract EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4532:2: iv_ruleFunctionExtract= ruleFunctionExtract EOF
            {
             newCompositeNode(grammarAccess.getFunctionExtractRule()); 
            pushFollow(FOLLOW_ruleFunctionExtract_in_entryRuleFunctionExtract9344);
            iv_ruleFunctionExtract=ruleFunctionExtract();

            state._fsp--;

             current =iv_ruleFunctionExtract; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFunctionExtract9354); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4539:1: ruleFunctionExtract returns [EObject current=null] : (otherlv_0= KEYWORD_84 otherlv_1= KEYWORD_1 ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= KEYWORD_39 ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4542:28: ( (otherlv_0= KEYWORD_84 otherlv_1= KEYWORD_1 ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= KEYWORD_39 ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4543:1: (otherlv_0= KEYWORD_84 otherlv_1= KEYWORD_1 ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= KEYWORD_39 ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4543:1: (otherlv_0= KEYWORD_84 otherlv_1= KEYWORD_1 ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= KEYWORD_39 ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4544:2: otherlv_0= KEYWORD_84 otherlv_1= KEYWORD_1 ( (lv_v_2_0= ruleEXTRACT_VALUES ) ) otherlv_3= KEYWORD_39 ( (lv_operand_4_0= ruleOperandGroup ) ) otherlv_5= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_84,FOLLOW_KEYWORD_84_in_ruleFunctionExtract9392); 

                	newLeafNode(otherlv_0, grammarAccess.getFunctionExtractAccess().getEXTRACTKeyword_0());
                
            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleFunctionExtract9404); 

                	newLeafNode(otherlv_1, grammarAccess.getFunctionExtractAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4553:1: ( (lv_v_2_0= ruleEXTRACT_VALUES ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4554:1: (lv_v_2_0= ruleEXTRACT_VALUES )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4554:1: (lv_v_2_0= ruleEXTRACT_VALUES )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4555:3: lv_v_2_0= ruleEXTRACT_VALUES
            {
             
            	        newCompositeNode(grammarAccess.getFunctionExtractAccess().getVEXTRACT_VALUESEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleEXTRACT_VALUES_in_ruleFunctionExtract9424);
            lv_v_2_0=ruleEXTRACT_VALUES();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFunctionExtractRule());
            	        }
                   		set(
                   			current, 
                   			"v",
                    		lv_v_2_0, 
                    		"EXTRACT_VALUES");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_ruleFunctionExtract9437); 

                	newLeafNode(otherlv_3, grammarAccess.getFunctionExtractAccess().getFROMKeyword_3());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4576:1: ( (lv_operand_4_0= ruleOperandGroup ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4577:1: (lv_operand_4_0= ruleOperandGroup )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4577:1: (lv_operand_4_0= ruleOperandGroup )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4578:3: lv_operand_4_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getFunctionExtractAccess().getOperandOperandGroupParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandGroup_in_ruleFunctionExtract9457);
            lv_operand_4_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFunctionExtractRule());
            	        }
                   		set(
                   			current, 
                   			"operand",
                    		lv_operand_4_0, 
                    		"OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleFunctionExtract9470); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4607:1: entryRuleFunctionAnalytical returns [EObject current=null] : iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF ;
    public final EObject entryRuleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionAnalytical = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4608:2: (iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4609:2: iv_ruleFunctionAnalytical= ruleFunctionAnalytical EOF
            {
             newCompositeNode(grammarAccess.getFunctionAnalyticalRule()); 
            pushFollow(FOLLOW_ruleFunctionAnalytical_in_entryRuleFunctionAnalytical9504);
            iv_ruleFunctionAnalytical=ruleFunctionAnalytical();

            state._fsp--;

             current =iv_ruleFunctionAnalytical; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFunctionAnalytical9514); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4616:1: ruleFunctionAnalytical returns [EObject current=null] : (otherlv_0= KEYWORD_50 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleFunctionAnalytical() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_anClause_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4619:28: ( (otherlv_0= KEYWORD_50 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4620:1: (otherlv_0= KEYWORD_50 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4620:1: (otherlv_0= KEYWORD_50 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4621:2: otherlv_0= KEYWORD_50 otherlv_1= KEYWORD_1 ( (lv_anClause_2_0= ruleAnalyticClause ) ) otherlv_3= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_50,FOLLOW_KEYWORD_50_in_ruleFunctionAnalytical9552); 

                	newLeafNode(otherlv_0, grammarAccess.getFunctionAnalyticalAccess().getOVERKeyword_0());
                
            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleFunctionAnalytical9564); 

                	newLeafNode(otherlv_1, grammarAccess.getFunctionAnalyticalAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4630:1: ( (lv_anClause_2_0= ruleAnalyticClause ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4631:1: (lv_anClause_2_0= ruleAnalyticClause )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4631:1: (lv_anClause_2_0= ruleAnalyticClause )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4632:3: lv_anClause_2_0= ruleAnalyticClause
            {
             
            	        newCompositeNode(grammarAccess.getFunctionAnalyticalAccess().getAnClauseAnalyticClauseParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAnalyticClause_in_ruleFunctionAnalytical9584);
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

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleFunctionAnalytical9597); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4661:1: entryRuleAnalyticClause returns [EObject current=null] : iv_ruleAnalyticClause= ruleAnalyticClause EOF ;
    public final EObject entryRuleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4662:2: (iv_ruleAnalyticClause= ruleAnalyticClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4663:2: iv_ruleAnalyticClause= ruleAnalyticClause EOF
            {
             newCompositeNode(grammarAccess.getAnalyticClauseRule()); 
            pushFollow(FOLLOW_ruleAnalyticClause_in_entryRuleAnalyticClause9631);
            iv_ruleAnalyticClause=ruleAnalyticClause();

            state._fsp--;

             current =iv_ruleAnalyticClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticClause9641); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4670:1: ruleAnalyticClause returns [EObject current=null] : ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? ) ;
    public final EObject ruleAnalyticClause() throws RecognitionException {
        EObject current = null;

        EObject lv_abc_1_0 = null;

        EObject lv_obc_2_0 = null;

        EObject lv_winc_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4673:28: ( ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4674:1: ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4674:1: ( () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4674:2: () ( (lv_abc_1_0= ruleQueryPartitionClause ) )? ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4674:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4675:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getAnalyticClauseAccess().getAnalyticClauseAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4680:2: ( (lv_abc_1_0= ruleQueryPartitionClause ) )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==KEYWORD_117) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4681:1: (lv_abc_1_0= ruleQueryPartitionClause )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4681:1: (lv_abc_1_0= ruleQueryPartitionClause )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4682:3: lv_abc_1_0= ruleQueryPartitionClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getAbcQueryPartitionClauseParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQueryPartitionClause_in_ruleAnalyticClause9696);
                    lv_abc_1_0=ruleQueryPartitionClause();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAnalyticClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"abc",
                            		lv_abc_1_0, 
                            		"QueryPartitionClause");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4698:3: ( ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )? )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==KEYWORD_122||LA92_0==KEYWORD_98) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4698:4: ( (lv_obc_2_0= ruleOrderByClause ) ) ( (lv_winc_3_0= ruleWindowingClause ) )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4698:4: ( (lv_obc_2_0= ruleOrderByClause ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4699:1: (lv_obc_2_0= ruleOrderByClause )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4699:1: (lv_obc_2_0= ruleOrderByClause )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4700:3: lv_obc_2_0= ruleOrderByClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getObcOrderByClauseParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOrderByClause_in_ruleAnalyticClause9719);
                    lv_obc_2_0=ruleOrderByClause();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAnalyticClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"obc",
                            		lv_obc_2_0, 
                            		"OrderByClause");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4716:2: ( (lv_winc_3_0= ruleWindowingClause ) )?
                    int alt91=2;
                    int LA91_0 = input.LA(1);

                    if ( (LA91_0==KEYWORD_70||LA91_0==KEYWORD_51) ) {
                        alt91=1;
                    }
                    switch (alt91) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4717:1: (lv_winc_3_0= ruleWindowingClause )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4717:1: (lv_winc_3_0= ruleWindowingClause )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4718:3: lv_winc_3_0= ruleWindowingClause
                            {
                             
                            	        newCompositeNode(grammarAccess.getAnalyticClauseAccess().getWincWindowingClauseParserRuleCall_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleWindowingClause_in_ruleAnalyticClause9740);
                            lv_winc_3_0=ruleWindowingClause();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getAnalyticClauseRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"winc",
                                    		lv_winc_3_0, 
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4742:1: entryRuleWindowingClause returns [EObject current=null] : iv_ruleWindowingClause= ruleWindowingClause EOF ;
    public final EObject entryRuleWindowingClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4743:2: (iv_ruleWindowingClause= ruleWindowingClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4744:2: iv_ruleWindowingClause= ruleWindowingClause EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseRule()); 
            pushFollow(FOLLOW_ruleWindowingClause_in_entryRuleWindowingClause9778);
            iv_ruleWindowingClause=ruleWindowingClause();

            state._fsp--;

             current =iv_ruleWindowingClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClause9788); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4751:1: ruleWindowingClause returns [EObject current=null] : ( (otherlv_0= KEYWORD_51 | otherlv_1= KEYWORD_70 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) ;
    public final EObject ruleWindowingClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_WindowingClauseBetween_2 = null;

        EObject this_WindowingClauseOperandPreceding_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4754:28: ( ( (otherlv_0= KEYWORD_51 | otherlv_1= KEYWORD_70 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4755:1: ( (otherlv_0= KEYWORD_51 | otherlv_1= KEYWORD_70 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4755:1: ( (otherlv_0= KEYWORD_51 | otherlv_1= KEYWORD_70 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4755:2: (otherlv_0= KEYWORD_51 | otherlv_1= KEYWORD_70 ) (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4755:2: (otherlv_0= KEYWORD_51 | otherlv_1= KEYWORD_70 )
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==KEYWORD_51) ) {
                alt93=1;
            }
            else if ( (LA93_0==KEYWORD_70) ) {
                alt93=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }
            switch (alt93) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4756:2: otherlv_0= KEYWORD_51
                    {
                    otherlv_0=(Token)match(input,KEYWORD_51,FOLLOW_KEYWORD_51_in_ruleWindowingClause9827); 

                        	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseAccess().getROWSKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4762:2: otherlv_1= KEYWORD_70
                    {
                    otherlv_1=(Token)match(input,KEYWORD_70,FOLLOW_KEYWORD_70_in_ruleWindowingClause9845); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseAccess().getRANGEKeyword_0_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4766:2: (this_WindowingClauseBetween_2= ruleWindowingClauseBetween | this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding )
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==KEYWORD_82) ) {
                alt94=1;
            }
            else if ( (LA94_0==KEYWORD_126||LA94_0==KEYWORD_110||LA94_0==KEYWORD_84||LA94_0==KEYWORD_57||LA94_0==KEYWORD_36||LA94_0==KEYWORD_1||(LA94_0>=RULE_JRPARAM && LA94_0<=RULE_JRNPARAM)||(LA94_0>=RULE_UNSIGNED && LA94_0<=RULE_SIGNED_DOUBLE)||(LA94_0>=RULE_STRING_ && LA94_0<=RULE_ID)) ) {
                alt94=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }
            switch (alt94) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4767:5: this_WindowingClauseBetween_2= ruleWindowingClauseBetween
                    {
                     
                            newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseBetweenParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleWindowingClauseBetween_in_ruleWindowingClause9868);
                    this_WindowingClauseBetween_2=ruleWindowingClauseBetween();

                    state._fsp--;


                            current = this_WindowingClauseBetween_2;
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4777:5: this_WindowingClauseOperandPreceding_3= ruleWindowingClauseOperandPreceding
                    {
                     
                            newCompositeNode(grammarAccess.getWindowingClauseAccess().getWindowingClauseOperandPrecedingParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClause9895);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4793:1: entryRuleWindowingClauseBetween returns [EObject current=null] : iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF ;
    public final EObject entryRuleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseBetween = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4794:2: (iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4795:2: iv_ruleWindowingClauseBetween= ruleWindowingClauseBetween EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseBetweenRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseBetween_in_entryRuleWindowingClauseBetween9930);
            iv_ruleWindowingClauseBetween=ruleWindowingClauseBetween();

            state._fsp--;

             current =iv_ruleWindowingClauseBetween; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseBetween9940); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4802:1: ruleWindowingClauseBetween returns [EObject current=null] : (otherlv_0= KEYWORD_82 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) ;
    public final EObject ruleWindowingClauseBetween() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_wcoP_1_0 = null;

        EObject lv_wcoF_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4805:28: ( (otherlv_0= KEYWORD_82 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4806:1: (otherlv_0= KEYWORD_82 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4806:1: (otherlv_0= KEYWORD_82 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4807:2: otherlv_0= KEYWORD_82 ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) ) otherlv_2= KEYWORD_27 ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_82,FOLLOW_KEYWORD_82_in_ruleWindowingClauseBetween9978); 

                	newLeafNode(otherlv_0, grammarAccess.getWindowingClauseBetweenAccess().getBETWEENKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4811:1: ( (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4812:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4812:1: (lv_wcoP_1_0= ruleWindowingClauseOperandPreceding )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4813:3: lv_wcoP_1_0= ruleWindowingClauseOperandPreceding
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoPWindowingClauseOperandPrecedingParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClauseBetween9998);
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

            otherlv_2=(Token)match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_ruleWindowingClauseBetween10011); 

                	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseBetweenAccess().getANDKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4834:1: ( (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4835:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4835:1: (lv_wcoF_3_0= ruleWindowingClauseOperandFollowing )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4836:3: lv_wcoF_3_0= ruleWindowingClauseOperandFollowing
            {
             
            	        newCompositeNode(grammarAccess.getWindowingClauseBetweenAccess().getWcoFWindowingClauseOperandFollowingParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleWindowingClauseOperandFollowing_in_ruleWindowingClauseBetween10031);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4860:1: entryRuleWindowingClauseOperandFollowing returns [EObject current=null] : iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF ;
    public final EObject entryRuleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandFollowing = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4861:2: (iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4862:2: iv_ruleWindowingClauseOperandFollowing= ruleWindowingClauseOperandFollowing EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseOperandFollowing_in_entryRuleWindowingClauseOperandFollowing10066);
            iv_ruleWindowingClauseOperandFollowing=ruleWindowingClauseOperandFollowing();

            state._fsp--;

             current =iv_ruleWindowingClauseOperandFollowing; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseOperandFollowing10076); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4869:1: ruleWindowingClauseOperandFollowing returns [EObject current=null] : ( () (otherlv_1= KEYWORD_125 | otherlv_2= KEYWORD_110 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandFollowing() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_exp_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4872:28: ( ( () (otherlv_1= KEYWORD_125 | otherlv_2= KEYWORD_110 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4873:1: ( () (otherlv_1= KEYWORD_125 | otherlv_2= KEYWORD_110 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4873:1: ( () (otherlv_1= KEYWORD_125 | otherlv_2= KEYWORD_110 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4873:2: () (otherlv_1= KEYWORD_125 | otherlv_2= KEYWORD_110 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4873:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4874:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandFollowingAccess().getWindowingClauseOperandFollowingAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4879:2: (otherlv_1= KEYWORD_125 | otherlv_2= KEYWORD_110 | ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) )
            int alt96=3;
            switch ( input.LA(1) ) {
            case KEYWORD_125:
                {
                alt96=1;
                }
                break;
            case KEYWORD_110:
                {
                alt96=2;
                }
                break;
            case KEYWORD_84:
            case KEYWORD_57:
            case KEYWORD_36:
            case KEYWORD_1:
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4880:2: otherlv_1= KEYWORD_125
                    {
                    otherlv_1=(Token)match(input,KEYWORD_125,FOLLOW_KEYWORD_125_in_ruleWindowingClauseOperandFollowing10124); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandFollowingAccess().getUNBOUNDEDFOLLOWINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4886:2: otherlv_2= KEYWORD_110
                    {
                    otherlv_2=(Token)match(input,KEYWORD_110,FOLLOW_KEYWORD_110_in_ruleWindowingClauseOperandFollowing10142); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandFollowingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4891:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4891:6: ( ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4891:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4891:7: ( (lv_exp_3_0= ruleAnalyticExprArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4892:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4892:1: (lv_exp_3_0= ruleAnalyticExprArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4893:3: lv_exp_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandFollowingAccess().getExpAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandFollowing10169);
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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4909:2: (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 )
                    int alt95=2;
                    int LA95_0 = input.LA(1);

                    if ( (LA95_0==KEYWORD_103) ) {
                        alt95=1;
                    }
                    else if ( (LA95_0==KEYWORD_101) ) {
                        alt95=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 95, 0, input);

                        throw nvae;
                    }
                    switch (alt95) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4910:2: otherlv_4= KEYWORD_103
                            {
                            otherlv_4=(Token)match(input,KEYWORD_103,FOLLOW_KEYWORD_103_in_ruleWindowingClauseOperandFollowing10183); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandFollowingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4916:2: otherlv_5= KEYWORD_101
                            {
                            otherlv_5=(Token)match(input,KEYWORD_101,FOLLOW_KEYWORD_101_in_ruleWindowingClauseOperandFollowing10201); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4928:1: entryRuleWindowingClauseOperandPreceding returns [EObject current=null] : iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF ;
    public final EObject entryRuleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWindowingClauseOperandPreceding = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4929:2: (iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4930:2: iv_ruleWindowingClauseOperandPreceding= ruleWindowingClauseOperandPreceding EOF
            {
             newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingRule()); 
            pushFollow(FOLLOW_ruleWindowingClauseOperandPreceding_in_entryRuleWindowingClauseOperandPreceding10238);
            iv_ruleWindowingClauseOperandPreceding=ruleWindowingClauseOperandPreceding();

            state._fsp--;

             current =iv_ruleWindowingClauseOperandPreceding; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWindowingClauseOperandPreceding10248); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4937:1: ruleWindowingClauseOperandPreceding returns [EObject current=null] : ( () (otherlv_1= KEYWORD_126 | otherlv_2= KEYWORD_110 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) ) ) ;
    public final EObject ruleWindowingClauseOperandPreceding() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4940:28: ( ( () (otherlv_1= KEYWORD_126 | otherlv_2= KEYWORD_110 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4941:1: ( () (otherlv_1= KEYWORD_126 | otherlv_2= KEYWORD_110 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4941:1: ( () (otherlv_1= KEYWORD_126 | otherlv_2= KEYWORD_110 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4941:2: () (otherlv_1= KEYWORD_126 | otherlv_2= KEYWORD_110 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4941:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4942:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getWindowingClauseOperandPrecedingAccess().getWindowingClauseOperandPrecedingAction_0(),
                        current);
                

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4947:2: (otherlv_1= KEYWORD_126 | otherlv_2= KEYWORD_110 | ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) ) )
            int alt98=3;
            switch ( input.LA(1) ) {
            case KEYWORD_126:
                {
                alt98=1;
                }
                break;
            case KEYWORD_110:
                {
                alt98=2;
                }
                break;
            case KEYWORD_84:
            case KEYWORD_57:
            case KEYWORD_36:
            case KEYWORD_1:
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
                alt98=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }

            switch (alt98) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4948:2: otherlv_1= KEYWORD_126
                    {
                    otherlv_1=(Token)match(input,KEYWORD_126,FOLLOW_KEYWORD_126_in_ruleWindowingClauseOperandPreceding10296); 

                        	newLeafNode(otherlv_1, grammarAccess.getWindowingClauseOperandPrecedingAccess().getUNBOUNDEDPRECEDINGKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4954:2: otherlv_2= KEYWORD_110
                    {
                    otherlv_2=(Token)match(input,KEYWORD_110,FOLLOW_KEYWORD_110_in_ruleWindowingClauseOperandPreceding10314); 

                        	newLeafNode(otherlv_2, grammarAccess.getWindowingClauseOperandPrecedingAccess().getCURRENTROWKeyword_1_1());
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4959:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4959:6: ( ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4959:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) ) (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4959:7: ( (lv_expr_3_0= ruleAnalyticExprArg ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4960:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4960:1: (lv_expr_3_0= ruleAnalyticExprArg )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4961:3: lv_expr_3_0= ruleAnalyticExprArg
                    {
                     
                    	        newCompositeNode(grammarAccess.getWindowingClauseOperandPrecedingAccess().getExprAnalyticExprArgParserRuleCall_1_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandPreceding10341);
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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4977:2: (otherlv_4= KEYWORD_103 | otherlv_5= KEYWORD_101 )
                    int alt97=2;
                    int LA97_0 = input.LA(1);

                    if ( (LA97_0==KEYWORD_103) ) {
                        alt97=1;
                    }
                    else if ( (LA97_0==KEYWORD_101) ) {
                        alt97=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 97, 0, input);

                        throw nvae;
                    }
                    switch (alt97) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4978:2: otherlv_4= KEYWORD_103
                            {
                            otherlv_4=(Token)match(input,KEYWORD_103,FOLLOW_KEYWORD_103_in_ruleWindowingClauseOperandPreceding10355); 

                                	newLeafNode(otherlv_4, grammarAccess.getWindowingClauseOperandPrecedingAccess().getPRECEDINGKeyword_1_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4984:2: otherlv_5= KEYWORD_101
                            {
                            otherlv_5=(Token)match(input,KEYWORD_101,FOLLOW_KEYWORD_101_in_ruleWindowingClauseOperandPreceding10373); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4996:1: entryRuleOrderByClause returns [EObject current=null] : iv_ruleOrderByClause= ruleOrderByClause EOF ;
    public final EObject entryRuleOrderByClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4997:2: (iv_ruleOrderByClause= ruleOrderByClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:4998:2: iv_ruleOrderByClause= ruleOrderByClause EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseRule()); 
            pushFollow(FOLLOW_ruleOrderByClause_in_entryRuleOrderByClause10410);
            iv_ruleOrderByClause=ruleOrderByClause();

            state._fsp--;

             current =iv_ruleOrderByClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClause10420); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5005:1: ruleOrderByClause returns [EObject current=null] : ( (otherlv_0= KEYWORD_98 | otherlv_1= KEYWORD_122 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) ;
    public final EObject ruleOrderByClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_args_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5008:28: ( ( (otherlv_0= KEYWORD_98 | otherlv_1= KEYWORD_122 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5009:1: ( (otherlv_0= KEYWORD_98 | otherlv_1= KEYWORD_122 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5009:1: ( (otherlv_0= KEYWORD_98 | otherlv_1= KEYWORD_122 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5009:2: (otherlv_0= KEYWORD_98 | otherlv_1= KEYWORD_122 ) ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5009:2: (otherlv_0= KEYWORD_98 | otherlv_1= KEYWORD_122 )
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==KEYWORD_98) ) {
                alt99=1;
            }
            else if ( (LA99_0==KEYWORD_122) ) {
                alt99=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 99, 0, input);

                throw nvae;
            }
            switch (alt99) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5010:2: otherlv_0= KEYWORD_98
                    {
                    otherlv_0=(Token)match(input,KEYWORD_98,FOLLOW_KEYWORD_98_in_ruleOrderByClause10459); 

                        	newLeafNode(otherlv_0, grammarAccess.getOrderByClauseAccess().getORDERBYKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5016:2: otherlv_1= KEYWORD_122
                    {
                    otherlv_1=(Token)match(input,KEYWORD_122,FOLLOW_KEYWORD_122_in_ruleOrderByClause10477); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseAccess().getORDERSIBLINGSBYKeyword_0_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5020:2: ( (lv_args_2_0= ruleOrderByClauseArgs ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5021:1: (lv_args_2_0= ruleOrderByClauseArgs )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5021:1: (lv_args_2_0= ruleOrderByClauseArgs )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5022:3: lv_args_2_0= ruleOrderByClauseArgs
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseAccess().getArgsOrderByClauseArgsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOrderByClauseArgs_in_ruleOrderByClause10498);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5046:1: entryRuleOrderByClauseArgs returns [EObject current=null] : iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF ;
    public final EObject entryRuleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArgs = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5047:2: (iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5048:2: iv_ruleOrderByClauseArgs= ruleOrderByClauseArgs EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseArgsRule()); 
            pushFollow(FOLLOW_ruleOrderByClauseArgs_in_entryRuleOrderByClauseArgs10533);
            iv_ruleOrderByClauseArgs=ruleOrderByClauseArgs();

            state._fsp--;

             current =iv_ruleOrderByClauseArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClauseArgs10543); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5055:1: ruleOrderByClauseArgs returns [EObject current=null] : (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) ;
    public final EObject ruleOrderByClauseArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OrderByClauseArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5058:28: ( (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5059:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5059:1: (this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5060:5: this_OrderByClauseArg_0= ruleOrderByClauseArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getOrderByClauseArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs10590);
            this_OrderByClauseArg_0=ruleOrderByClauseArg();

            state._fsp--;


                    current = this_OrderByClauseArg_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5068:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+ )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==KEYWORD_4) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5068:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5068:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5069:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOrderByClauseArgsAccess().getOBCArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5074:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) ) )+
                    int cnt100=0;
                    loop100:
                    do {
                        int alt100=2;
                        int LA100_0 = input.LA(1);

                        if ( (LA100_0==KEYWORD_4) ) {
                            alt100=1;
                        }


                        switch (alt100) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5075:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOrderByClauseArgs10613); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5079:1: ( (lv_entries_3_0= ruleOrderByClauseArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5080:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5080:1: (lv_entries_3_0= ruleOrderByClauseArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5081:3: lv_entries_3_0= ruleOrderByClauseArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrderByClauseArgsAccess().getEntriesOrderByClauseArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs10633);
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
                    	    if ( cnt100 >= 1 ) break loop100;
                                EarlyExitException eee =
                                    new EarlyExitException(100, input);
                                throw eee;
                        }
                        cnt100++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5105:1: entryRuleOrderByClauseArg returns [EObject current=null] : iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF ;
    public final EObject entryRuleOrderByClauseArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrderByClauseArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5106:2: (iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5107:2: iv_ruleOrderByClauseArg= ruleOrderByClauseArg EOF
            {
             newCompositeNode(grammarAccess.getOrderByClauseArgRule()); 
            pushFollow(FOLLOW_ruleOrderByClauseArg_in_entryRuleOrderByClauseArg10672);
            iv_ruleOrderByClauseArg=ruleOrderByClauseArg();

            state._fsp--;

             current =iv_ruleOrderByClauseArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByClauseArg10682); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5114:1: ruleOrderByClauseArg returns [EObject current=null] : ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_37 )? (otherlv_3= KEYWORD_67 (otherlv_4= KEYWORD_60 | otherlv_5= KEYWORD_43 ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5117:28: ( ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_37 )? (otherlv_3= KEYWORD_67 (otherlv_4= KEYWORD_60 | otherlv_5= KEYWORD_43 ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5118:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_37 )? (otherlv_3= KEYWORD_67 (otherlv_4= KEYWORD_60 | otherlv_5= KEYWORD_43 ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5118:1: ( ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_37 )? (otherlv_3= KEYWORD_67 (otherlv_4= KEYWORD_60 | otherlv_5= KEYWORD_43 ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5118:2: ( (lv_col_0_0= ruleAnalyticExprArg ) ) (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_37 )? (otherlv_3= KEYWORD_67 (otherlv_4= KEYWORD_60 | otherlv_5= KEYWORD_43 ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5118:2: ( (lv_col_0_0= ruleAnalyticExprArg ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5119:1: (lv_col_0_0= ruleAnalyticExprArg )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5119:1: (lv_col_0_0= ruleAnalyticExprArg )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5120:3: lv_col_0_0= ruleAnalyticExprArg
            {
             
            	        newCompositeNode(grammarAccess.getOrderByClauseArgAccess().getColAnalyticExprArgParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleOrderByClauseArg10728);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5136:2: (otherlv_1= KEYWORD_29 | otherlv_2= KEYWORD_37 )?
            int alt102=3;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==KEYWORD_29) ) {
                alt102=1;
            }
            else if ( (LA102_0==KEYWORD_37) ) {
                alt102=2;
            }
            switch (alt102) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5137:2: otherlv_1= KEYWORD_29
                    {
                    otherlv_1=(Token)match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_ruleOrderByClauseArg10742); 

                        	newLeafNode(otherlv_1, grammarAccess.getOrderByClauseArgAccess().getASCKeyword_1_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5143:2: otherlv_2= KEYWORD_37
                    {
                    otherlv_2=(Token)match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_ruleOrderByClauseArg10760); 

                        	newLeafNode(otherlv_2, grammarAccess.getOrderByClauseArgAccess().getDESCKeyword_1_1());
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5147:3: (otherlv_3= KEYWORD_67 (otherlv_4= KEYWORD_60 | otherlv_5= KEYWORD_43 ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==KEYWORD_67) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5148:2: otherlv_3= KEYWORD_67 (otherlv_4= KEYWORD_60 | otherlv_5= KEYWORD_43 )
                    {
                    otherlv_3=(Token)match(input,KEYWORD_67,FOLLOW_KEYWORD_67_in_ruleOrderByClauseArg10775); 

                        	newLeafNode(otherlv_3, grammarAccess.getOrderByClauseArgAccess().getNULLSKeyword_2_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5152:1: (otherlv_4= KEYWORD_60 | otherlv_5= KEYWORD_43 )
                    int alt103=2;
                    int LA103_0 = input.LA(1);

                    if ( (LA103_0==KEYWORD_60) ) {
                        alt103=1;
                    }
                    else if ( (LA103_0==KEYWORD_43) ) {
                        alt103=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 103, 0, input);

                        throw nvae;
                    }
                    switch (alt103) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5153:2: otherlv_4= KEYWORD_60
                            {
                            otherlv_4=(Token)match(input,KEYWORD_60,FOLLOW_KEYWORD_60_in_ruleOrderByClauseArg10788); 

                                	newLeafNode(otherlv_4, grammarAccess.getOrderByClauseArgAccess().getFIRSTKeyword_2_1_0());
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5159:2: otherlv_5= KEYWORD_43
                            {
                            otherlv_5=(Token)match(input,KEYWORD_43,FOLLOW_KEYWORD_43_in_ruleOrderByClauseArg10806); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5171:1: entryRuleQueryPartitionClause returns [EObject current=null] : iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF ;
    public final EObject entryRuleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQueryPartitionClause = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5172:2: (iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5173:2: iv_ruleQueryPartitionClause= ruleQueryPartitionClause EOF
            {
             newCompositeNode(grammarAccess.getQueryPartitionClauseRule()); 
            pushFollow(FOLLOW_ruleQueryPartitionClause_in_entryRuleQueryPartitionClause10843);
            iv_ruleQueryPartitionClause=ruleQueryPartitionClause();

            state._fsp--;

             current =iv_ruleQueryPartitionClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQueryPartitionClause10853); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5180:1: ruleQueryPartitionClause returns [EObject current=null] : (otherlv_0= KEYWORD_117 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) ) ;
    public final EObject ruleQueryPartitionClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_args_1_0 = null;

        EObject this_AnalyticExprArgs_3 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5183:28: ( (otherlv_0= KEYWORD_117 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5184:1: (otherlv_0= KEYWORD_117 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5184:1: (otherlv_0= KEYWORD_117 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5185:2: otherlv_0= KEYWORD_117 ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) )
            {
            otherlv_0=(Token)match(input,KEYWORD_117,FOLLOW_KEYWORD_117_in_ruleQueryPartitionClause10891); 

                	newLeafNode(otherlv_0, grammarAccess.getQueryPartitionClauseAccess().getPARTITIONBYKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5189:1: ( ( (lv_args_1_0= ruleAnalyticExprArgs ) ) | (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 ) )
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==KEYWORD_84||LA105_0==KEYWORD_57||LA105_0==KEYWORD_36||(LA105_0>=RULE_JRPARAM && LA105_0<=RULE_JRNPARAM)||(LA105_0>=RULE_UNSIGNED && LA105_0<=RULE_SIGNED_DOUBLE)||(LA105_0>=RULE_STRING_ && LA105_0<=RULE_ID)) ) {
                alt105=1;
            }
            else if ( (LA105_0==KEYWORD_1) ) {
                int LA105_2 = input.LA(2);

                if ( (LA105_2==KEYWORD_84||LA105_2==KEYWORD_57||LA105_2==KEYWORD_36||LA105_2==KEYWORD_1||(LA105_2>=RULE_JRPARAM && LA105_2<=RULE_JRNPARAM)||(LA105_2>=RULE_UNSIGNED && LA105_2<=RULE_SIGNED_DOUBLE)||(LA105_2>=RULE_STRING_ && LA105_2<=RULE_ID)) ) {
                    alt105=2;
                }
                else if ( (LA105_2==KEYWORD_81) ) {
                    alt105=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 105, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;
            }
            switch (alt105) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5189:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5189:2: ( (lv_args_1_0= ruleAnalyticExprArgs ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5190:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5190:1: (lv_args_1_0= ruleAnalyticExprArgs )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5191:3: lv_args_1_0= ruleAnalyticExprArgs
                    {
                     
                    	        newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getArgsAnalyticExprArgsParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause10912);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5208:6: (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5208:6: (otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5209:2: otherlv_2= KEYWORD_1 this_AnalyticExprArgs_3= ruleAnalyticExprArgs otherlv_4= KEYWORD_2
                    {
                    otherlv_2=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleQueryPartitionClause10932); 

                        	newLeafNode(otherlv_2, grammarAccess.getQueryPartitionClauseAccess().getLeftParenthesisKeyword_1_1_0());
                        
                     
                            newCompositeNode(grammarAccess.getQueryPartitionClauseAccess().getAnalyticExprArgsParserRuleCall_1_1_1()); 
                        
                    pushFollow(FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause10953);
                    this_AnalyticExprArgs_3=ruleAnalyticExprArgs();

                    state._fsp--;


                            current = this_AnalyticExprArgs_3;
                            afterParserOrEnumRuleCall();
                        
                    otherlv_4=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleQueryPartitionClause10965); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5235:1: entryRuleAnalyticExprArgs returns [EObject current=null] : iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF ;
    public final EObject entryRuleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArgs = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5236:2: (iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5237:2: iv_ruleAnalyticExprArgs= ruleAnalyticExprArgs EOF
            {
             newCompositeNode(grammarAccess.getAnalyticExprArgsRule()); 
            pushFollow(FOLLOW_ruleAnalyticExprArgs_in_entryRuleAnalyticExprArgs11001);
            iv_ruleAnalyticExprArgs=ruleAnalyticExprArgs();

            state._fsp--;

             current =iv_ruleAnalyticExprArgs; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticExprArgs11011); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5244:1: ruleAnalyticExprArgs returns [EObject current=null] : (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) ;
    public final EObject ruleAnalyticExprArgs() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AnalyticExprArg_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5247:28: ( (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5248:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5248:1: (this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5249:5: this_AnalyticExprArg_0= ruleAnalyticExprArg ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getAnalyticExprArgParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs11058);
            this_AnalyticExprArg_0=ruleAnalyticExprArg();

            state._fsp--;


                    current = this_AnalyticExprArg_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5257:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+ )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==KEYWORD_4) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5257:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5257:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5258:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getAnalyticExprArgsAccess().getAExpArgsEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5263:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) ) )+
                    int cnt106=0;
                    loop106:
                    do {
                        int alt106=2;
                        int LA106_0 = input.LA(1);

                        if ( (LA106_0==KEYWORD_4) ) {
                            alt106=1;
                        }


                        switch (alt106) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5264:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleAnalyticExprArgs11081); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getAnalyticExprArgsAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5268:1: ( (lv_entries_3_0= ruleAnalyticExprArg ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5269:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5269:1: (lv_entries_3_0= ruleAnalyticExprArg )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5270:3: lv_entries_3_0= ruleAnalyticExprArg
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAnalyticExprArgsAccess().getEntriesAnalyticExprArgParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs11101);
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
                    	    if ( cnt106 >= 1 ) break loop106;
                                EarlyExitException eee =
                                    new EarlyExitException(106, input);
                                throw eee;
                        }
                        cnt106++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5294:1: entryRuleAnalyticExprArg returns [EObject current=null] : iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF ;
    public final EObject entryRuleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalyticExprArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5295:2: (iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5296:2: iv_ruleAnalyticExprArg= ruleAnalyticExprArg EOF
            {
             newCompositeNode(grammarAccess.getAnalyticExprArgRule()); 
            pushFollow(FOLLOW_ruleAnalyticExprArg_in_entryRuleAnalyticExprArg11140);
            iv_ruleAnalyticExprArg=ruleAnalyticExprArg();

            state._fsp--;

             current =iv_ruleAnalyticExprArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalyticExprArg11150); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5303:1: ruleAnalyticExprArg returns [EObject current=null] : ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) ;
    public final EObject ruleAnalyticExprArg() throws RecognitionException {
        EObject current = null;

        EObject lv_ce_0_0 = null;

        EObject lv_colAlias_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5306:28: ( ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5307:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5307:1: ( ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5307:2: ( (lv_ce_0_0= ruleOperand ) ) ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5307:2: ( (lv_ce_0_0= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5308:1: (lv_ce_0_0= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5308:1: (lv_ce_0_0= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5309:3: lv_ce_0_0= ruleOperand
            {
             
            	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getCeOperandParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleOperand_in_ruleAnalyticExprArg11196);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5325:2: ( (lv_colAlias_1_0= ruleDbObjectName ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( ((LA108_0>=RULE_STRING && LA108_0<=RULE_ID)) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5326:1: (lv_colAlias_1_0= ruleDbObjectName )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5326:1: (lv_colAlias_1_0= ruleDbObjectName )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5327:3: lv_colAlias_1_0= ruleDbObjectName
                    {
                     
                    	        newCompositeNode(grammarAccess.getAnalyticExprArgAccess().getColAliasDbObjectNameParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDbObjectName_in_ruleAnalyticExprArg11217);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5351:1: entryRuleOpFunctionArg returns [EObject current=null] : iv_ruleOpFunctionArg= ruleOpFunctionArg EOF ;
    public final EObject entryRuleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArg = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5352:2: (iv_ruleOpFunctionArg= ruleOpFunctionArg EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5353:2: iv_ruleOpFunctionArg= ruleOpFunctionArg EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArg_in_entryRuleOpFunctionArg11253);
            iv_ruleOpFunctionArg=ruleOpFunctionArg();

            state._fsp--;

             current =iv_ruleOpFunctionArg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArg11263); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5360:1: ruleOpFunctionArg returns [EObject current=null] : (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) ;
    public final EObject ruleOpFunctionArg() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_OpFunctionArgOperand_0 = null;

        EObject lv_entries_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5363:28: ( (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5364:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5364:1: (this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5365:5: this_OpFunctionArgOperand_0= ruleOpFunctionArgOperand ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getOpFunctionArgAccess().getOpFunctionArgOperandParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg11310);
            this_OpFunctionArgOperand_0=ruleOpFunctionArgOperand();

            state._fsp--;


                    current = this_OpFunctionArgOperand_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5373:1: ( () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+ )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==KEYWORD_4) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5373:2: () (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5373:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5374:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getOpFunctionArgAccess().getOpFListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5379:2: (otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) ) )+
                    int cnt109=0;
                    loop109:
                    do {
                        int alt109=2;
                        int LA109_0 = input.LA(1);

                        if ( (LA109_0==KEYWORD_4) ) {
                            alt109=1;
                        }


                        switch (alt109) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5380:2: otherlv_2= KEYWORD_4 ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    {
                    	    otherlv_2=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOpFunctionArg11333); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getOpFunctionArgAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5384:1: ( (lv_entries_3_0= ruleOpFunctionArgOperand ) )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5385:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5385:1: (lv_entries_3_0= ruleOpFunctionArgOperand )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5386:3: lv_entries_3_0= ruleOpFunctionArgOperand
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOpFunctionArgAccess().getEntriesOpFunctionArgOperandParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg11353);
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
                    	    if ( cnt109 >= 1 ) break loop109;
                                EarlyExitException eee =
                                    new EarlyExitException(109, input);
                                throw eee;
                        }
                        cnt109++;
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5410:1: entryRuleOpFunctionArgOperand returns [EObject current=null] : iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF ;
    public final EObject entryRuleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5411:2: (iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5412:2: iv_ruleOpFunctionArgOperand= ruleOpFunctionArgOperand EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgOperandRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArgOperand_in_entryRuleOpFunctionArgOperand11392);
            iv_ruleOpFunctionArgOperand=ruleOpFunctionArgOperand();

            state._fsp--;

             current =iv_ruleOpFunctionArgOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArgOperand11402); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5419:1: ruleOpFunctionArgOperand returns [EObject current=null] : ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) ;
    public final EObject ruleOpFunctionArgOperand() throws RecognitionException {
        EObject current = null;

        EObject lv_op_0_1 = null;

        EObject lv_op_0_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5422:28: ( ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5423:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5423:1: ( ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5424:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5424:1: ( (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5425:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5425:1: (lv_op_0_1= ruleOpFunctionArgAgregate | lv_op_0_2= ruleOperand )
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==KEYWORD_94||LA111_0==KEYWORD_26) ) {
                alt111=1;
            }
            else if ( (LA111_0==KEYWORD_84||LA111_0==KEYWORD_57||LA111_0==KEYWORD_36||LA111_0==KEYWORD_1||(LA111_0>=RULE_JRPARAM && LA111_0<=RULE_JRNPARAM)||(LA111_0>=RULE_UNSIGNED && LA111_0<=RULE_SIGNED_DOUBLE)||(LA111_0>=RULE_STRING_ && LA111_0<=RULE_ID)) ) {
                alt111=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 111, 0, input);

                throw nvae;
            }
            switch (alt111) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5426:3: lv_op_0_1= ruleOpFunctionArgAgregate
                    {
                     
                    	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOpFunctionArgAgregateParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOpFunctionArgAgregate_in_ruleOpFunctionArgOperand11449);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5441:8: lv_op_0_2= ruleOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getOpFunctionArgOperandAccess().getOpOperandParserRuleCall_0_1()); 
                    	    
                    pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionArgOperand11468);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5467:1: entryRuleOpFunctionCast returns [EObject current=null] : iv_ruleOpFunctionCast= ruleOpFunctionCast EOF ;
    public final EObject entryRuleOpFunctionCast() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionCast = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5468:2: (iv_ruleOpFunctionCast= ruleOpFunctionCast EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5469:2: iv_ruleOpFunctionCast= ruleOpFunctionCast EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionCastRule()); 
            pushFollow(FOLLOW_ruleOpFunctionCast_in_entryRuleOpFunctionCast11505);
            iv_ruleOpFunctionCast=ruleOpFunctionCast();

            state._fsp--;

             current =iv_ruleOpFunctionCast; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionCast11515); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5476:1: ruleOpFunctionCast returns [EObject current=null] : (otherlv_0= KEYWORD_57 ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5479:28: ( (otherlv_0= KEYWORD_57 ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5480:1: (otherlv_0= KEYWORD_57 ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5480:1: (otherlv_0= KEYWORD_57 ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5481:2: otherlv_0= KEYWORD_57 ( (lv_op_1_0= ruleOperandGroup ) ) otherlv_2= KEYWORD_19 ( (lv_type_3_0= RULE_ID ) ) (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )? otherlv_9= KEYWORD_2
            {
            otherlv_0=(Token)match(input,KEYWORD_57,FOLLOW_KEYWORD_57_in_ruleOpFunctionCast11553); 

                	newLeafNode(otherlv_0, grammarAccess.getOpFunctionCastAccess().getCASTKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5485:1: ( (lv_op_1_0= ruleOperandGroup ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5486:1: (lv_op_1_0= ruleOperandGroup )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5486:1: (lv_op_1_0= ruleOperandGroup )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5487:3: lv_op_1_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getOpFunctionCastAccess().getOpOperandGroupParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandGroup_in_ruleOpFunctionCast11573);
            lv_op_1_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOpFunctionCastRule());
            	        }
                   		set(
                   			current, 
                   			"op",
                    		lv_op_1_0, 
                    		"OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_ruleOpFunctionCast11586); 

                	newLeafNode(otherlv_2, grammarAccess.getOpFunctionCastAccess().getASKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5508:1: ( (lv_type_3_0= RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5509:1: (lv_type_3_0= RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5509:1: (lv_type_3_0= RULE_ID )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5510:3: lv_type_3_0= RULE_ID
            {
            lv_type_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOpFunctionCast11602); 

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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5526:2: (otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2 )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==KEYWORD_1) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5527:2: otherlv_4= KEYWORD_1 ( (lv_p_5_0= RULE_INT ) ) (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )? otherlv_8= KEYWORD_2
                    {
                    otherlv_4=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleOpFunctionCast11621); 

                        	newLeafNode(otherlv_4, grammarAccess.getOpFunctionCastAccess().getLeftParenthesisKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5531:1: ( (lv_p_5_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5532:1: (lv_p_5_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5532:1: (lv_p_5_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5533:3: lv_p_5_0= RULE_INT
                    {
                    lv_p_5_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOpFunctionCast11637); 

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

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5549:2: (otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) ) )?
                    int alt112=2;
                    int LA112_0 = input.LA(1);

                    if ( (LA112_0==KEYWORD_4) ) {
                        alt112=1;
                    }
                    switch (alt112) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5550:2: otherlv_6= KEYWORD_4 ( (lv_p2_7_0= RULE_INT ) )
                            {
                            otherlv_6=(Token)match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_ruleOpFunctionCast11656); 

                                	newLeafNode(otherlv_6, grammarAccess.getOpFunctionCastAccess().getCommaKeyword_4_2_0());
                                
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5554:1: ( (lv_p2_7_0= RULE_INT ) )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5555:1: (lv_p2_7_0= RULE_INT )
                            {
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5555:1: (lv_p2_7_0= RULE_INT )
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5556:3: lv_p2_7_0= RULE_INT
                            {
                            lv_p2_7_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleOpFunctionCast11672); 

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

                    otherlv_8=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOpFunctionCast11692); 

                        	newLeafNode(otherlv_8, grammarAccess.getOpFunctionCastAccess().getRightParenthesisKeyword_4_3());
                        

                    }
                    break;

            }

            otherlv_9=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleOpFunctionCast11706); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5590:1: entryRuleOpFunctionArgAgregate returns [EObject current=null] : iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF ;
    public final EObject entryRuleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOpFunctionArgAgregate = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5591:2: (iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5592:2: iv_ruleOpFunctionArgAgregate= ruleOpFunctionArgAgregate EOF
            {
             newCompositeNode(grammarAccess.getOpFunctionArgAgregateRule()); 
            pushFollow(FOLLOW_ruleOpFunctionArgAgregate_in_entryRuleOpFunctionArgAgregate11740);
            iv_ruleOpFunctionArgAgregate=ruleOpFunctionArgAgregate();

            state._fsp--;

             current =iv_ruleOpFunctionArgAgregate; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpFunctionArgAgregate11750); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5599:1: ruleOpFunctionArgAgregate returns [EObject current=null] : ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_94 ) this_Operand_2= ruleOperand ) ;
    public final EObject ruleOpFunctionArgAgregate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject this_Operand_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5602:28: ( ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_94 ) this_Operand_2= ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5603:1: ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_94 ) this_Operand_2= ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5603:1: ( (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_94 ) this_Operand_2= ruleOperand )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5603:2: (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_94 ) this_Operand_2= ruleOperand
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5603:2: (otherlv_0= KEYWORD_26 | otherlv_1= KEYWORD_94 )
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==KEYWORD_26) ) {
                alt114=1;
            }
            else if ( (LA114_0==KEYWORD_94) ) {
                alt114=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 114, 0, input);

                throw nvae;
            }
            switch (alt114) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5604:2: otherlv_0= KEYWORD_26
                    {
                    otherlv_0=(Token)match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_ruleOpFunctionArgAgregate11789); 

                        	newLeafNode(otherlv_0, grammarAccess.getOpFunctionArgAgregateAccess().getALLKeyword_0_0());
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5610:2: otherlv_1= KEYWORD_94
                    {
                    otherlv_1=(Token)match(input,KEYWORD_94,FOLLOW_KEYWORD_94_in_ruleOpFunctionArgAgregate11807); 

                        	newLeafNode(otherlv_1, grammarAccess.getOpFunctionArgAgregateAccess().getDISTINCTKeyword_0_1());
                        

                    }
                    break;

            }

             
                    newCompositeNode(grammarAccess.getOpFunctionArgAgregateAccess().getOperandParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleOperand_in_ruleOpFunctionArgAgregate11829);
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5631:1: entryRuleXOperandFragment returns [EObject current=null] : iv_ruleXOperandFragment= ruleXOperandFragment EOF ;
    public final EObject entryRuleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOperandFragment = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5632:2: (iv_ruleXOperandFragment= ruleXOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5633:2: iv_ruleXOperandFragment= ruleXOperandFragment EOF
            {
             newCompositeNode(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment11863);
            iv_ruleXOperandFragment=ruleXOperandFragment();

            state._fsp--;

             current =iv_ruleXOperandFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOperandFragment11873); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5640:1: ruleXOperandFragment returns [EObject current=null] : ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) ) ;
    public final EObject ruleXOperandFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_param_0_0 = null;

        EObject lv_eparam_1_0 = null;

        EObject lv_scalar_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5643:28: ( ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5644:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5644:1: ( ( (lv_param_0_0= ruleParameterOperand ) ) | ( (lv_eparam_1_0= ruleExclamationParameterOperand ) ) | ( (lv_scalar_2_0= ruleScalarNumberOperand ) ) )
            int alt115=3;
            switch ( input.LA(1) ) {
            case RULE_JRPARAM:
                {
                alt115=1;
                }
                break;
            case RULE_JRNPARAM:
                {
                alt115=2;
                }
                break;
            case RULE_UNSIGNED:
            case RULE_INT:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING_:
                {
                alt115=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;
            }

            switch (alt115) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5644:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5644:2: ( (lv_param_0_0= ruleParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5645:1: (lv_param_0_0= ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5645:1: (lv_param_0_0= ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5646:3: lv_param_0_0= ruleParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParameterOperand_in_ruleXOperandFragment11919);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5663:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5663:6: ( (lv_eparam_1_0= ruleExclamationParameterOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5664:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5664:1: (lv_eparam_1_0= ruleExclamationParameterOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5665:3: lv_eparam_1_0= ruleExclamationParameterOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment11946);
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5682:6: ( (lv_scalar_2_0= ruleScalarNumberOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5682:6: ( (lv_scalar_2_0= ruleScalarNumberOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5683:1: (lv_scalar_2_0= ruleScalarNumberOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5683:1: (lv_scalar_2_0= ruleScalarNumberOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5684:3: lv_scalar_2_0= ruleScalarNumberOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getXOperandFragmentAccess().getScalarScalarNumberOperandParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleScalarNumberOperand_in_ruleXOperandFragment11973);
                    lv_scalar_2_0=ruleScalarNumberOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getXOperandFragmentRule());
                    	        }
                           		set(
                           			current, 
                           			"scalar",
                            		lv_scalar_2_0, 
                            		"ScalarNumberOperand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5708:1: entryRuleParameterOperand returns [EObject current=null] : iv_ruleParameterOperand= ruleParameterOperand EOF ;
    public final EObject entryRuleParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5709:2: (iv_ruleParameterOperand= ruleParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5710:2: iv_ruleParameterOperand= ruleParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand12008);
            iv_ruleParameterOperand=ruleParameterOperand();

            state._fsp--;

             current =iv_ruleParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameterOperand12018); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5717:1: ruleParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRPARAM ) ) ;
    public final EObject ruleParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5720:28: ( ( (lv_prm_0_0= RULE_JRPARAM ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5721:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5721:1: ( (lv_prm_0_0= RULE_JRPARAM ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5722:1: (lv_prm_0_0= RULE_JRPARAM )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5722:1: (lv_prm_0_0= RULE_JRPARAM )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5723:3: lv_prm_0_0= RULE_JRPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRPARAM,FOLLOW_RULE_JRPARAM_in_ruleParameterOperand12059); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5747:1: entryRuleExclamationParameterOperand returns [EObject current=null] : iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF ;
    public final EObject entryRuleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclamationParameterOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5748:2: (iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5749:2: iv_ruleExclamationParameterOperand= ruleExclamationParameterOperand EOF
            {
             newCompositeNode(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand12098);
            iv_ruleExclamationParameterOperand=ruleExclamationParameterOperand();

            state._fsp--;

             current =iv_ruleExclamationParameterOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclamationParameterOperand12108); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5756:1: ruleExclamationParameterOperand returns [EObject current=null] : ( (lv_prm_0_0= RULE_JRNPARAM ) ) ;
    public final EObject ruleExclamationParameterOperand() throws RecognitionException {
        EObject current = null;

        Token lv_prm_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5759:28: ( ( (lv_prm_0_0= RULE_JRNPARAM ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5760:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5760:1: ( (lv_prm_0_0= RULE_JRNPARAM ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5761:1: (lv_prm_0_0= RULE_JRNPARAM )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5761:1: (lv_prm_0_0= RULE_JRNPARAM )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5762:3: lv_prm_0_0= RULE_JRNPARAM
            {
            lv_prm_0_0=(Token)match(input,RULE_JRNPARAM,FOLLOW_RULE_JRNPARAM_in_ruleExclamationParameterOperand12149); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5786:1: entryRuleColumnOperand returns [EObject current=null] : iv_ruleColumnOperand= ruleColumnOperand EOF ;
    public final EObject entryRuleColumnOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5787:2: (iv_ruleColumnOperand= ruleColumnOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5788:2: iv_ruleColumnOperand= ruleColumnOperand EOF
            {
             newCompositeNode(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand12188);
            iv_ruleColumnOperand=ruleColumnOperand();

            state._fsp--;

             current =iv_ruleColumnOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOperand12198); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5795:1: ruleColumnOperand returns [EObject current=null] : ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? ) ;
    public final EObject ruleColumnOperand() throws RecognitionException {
        EObject current = null;

        Token lv_ora_1_0=null;
        EObject lv_cfull_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5798:28: ( ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5799:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5799:1: ( ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5799:2: ( (lv_cfull_0_0= ruleColumnFull ) ) ( (lv_ora_1_0= KEYWORD_25 ) )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5799:2: ( (lv_cfull_0_0= ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5800:1: (lv_cfull_0_0= ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5800:1: (lv_cfull_0_0= ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5801:3: lv_cfull_0_0= ruleColumnFull
            {
             
            	        newCompositeNode(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOperand12244);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5817:2: ( (lv_ora_1_0= KEYWORD_25 ) )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==KEYWORD_25) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5818:1: (lv_ora_1_0= KEYWORD_25 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5818:1: (lv_ora_1_0= KEYWORD_25 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5819:3: lv_ora_1_0= KEYWORD_25
                    {
                    lv_ora_1_0=(Token)match(input,KEYWORD_25,FOLLOW_KEYWORD_25_in_ruleColumnOperand12263); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5841:1: entryRuleSubQueryOperand returns [EObject current=null] : iv_ruleSubQueryOperand= ruleSubQueryOperand EOF ;
    public final EObject entryRuleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubQueryOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5842:2: (iv_ruleSubQueryOperand= ruleSubQueryOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5843:2: iv_ruleSubQueryOperand= ruleSubQueryOperand EOF
            {
             newCompositeNode(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand12310);
            iv_ruleSubQueryOperand=ruleSubQueryOperand();

            state._fsp--;

             current =iv_ruleSubQueryOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubQueryOperand12320); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5850:1: ruleSubQueryOperand returns [EObject current=null] : ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 ) ;
    public final EObject ruleSubQueryOperand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_sel_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5853:28: ( ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5854:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5854:1: ( () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5854:2: () otherlv_1= KEYWORD_1 ( (lv_sel_2_0= ruleSelectQuery ) ) otherlv_3= KEYWORD_2
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5854:2: ()
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5855:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSubQueryOperandAccess().getSubQueryOperandAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleSubQueryOperand12367); 

                	newLeafNode(otherlv_1, grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5865:1: ( (lv_sel_2_0= ruleSelectQuery ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5866:1: (lv_sel_2_0= ruleSelectQuery )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5866:1: (lv_sel_2_0= ruleSelectQuery )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5867:3: lv_sel_2_0= ruleSelectQuery
            {
             
            	        newCompositeNode(grammarAccess.getSubQueryOperandAccess().getSelSelectQueryParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSelectQuery_in_ruleSubQueryOperand12387);
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

            otherlv_3=(Token)match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_ruleSubQueryOperand12400); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5896:1: entryRuleScalarOperand returns [EObject current=null] : iv_ruleScalarOperand= ruleScalarOperand EOF ;
    public final EObject entryRuleScalarOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5897:2: (iv_ruleScalarOperand= ruleScalarOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5898:2: iv_ruleScalarOperand= ruleScalarOperand EOF
            {
             newCompositeNode(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand12434);
            iv_ruleScalarOperand=ruleScalarOperand();

            state._fsp--;

             current =iv_ruleScalarOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarOperand12444); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5905:1: ruleScalarOperand returns [EObject current=null] : ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) ) ;
    public final EObject ruleScalarOperand() throws RecognitionException {
        EObject current = null;

        Token lv_sodbl_1_0=null;
        Token lv_sodate_2_0=null;
        Token lv_sotime_3_0=null;
        Token lv_sodt_4_0=null;
        AntlrDatatypeRuleToken lv_sostr_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5908:28: ( ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5909:1: ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5909:1: ( ( (lv_sostr_0_0= ruleStringOperand ) ) | ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sodate_2_0= RULE_DATE ) ) | ( (lv_sotime_3_0= RULE_TIME ) ) | ( (lv_sodt_4_0= RULE_TIMESTAMP ) ) )
            int alt117=5;
            switch ( input.LA(1) ) {
            case RULE_STRING_:
                {
                alt117=1;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt117=2;
                }
                break;
            case RULE_DATE:
                {
                alt117=3;
                }
                break;
            case RULE_TIME:
                {
                alt117=4;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt117=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 117, 0, input);

                throw nvae;
            }

            switch (alt117) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5909:2: ( (lv_sostr_0_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5909:2: ( (lv_sostr_0_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5910:1: (lv_sostr_0_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5910:1: (lv_sostr_0_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5911:3: lv_sostr_0_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleScalarOperand12490);
                    lv_sostr_0_0=ruleStringOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getScalarOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"sostr",
                            		lv_sostr_0_0, 
                            		"StringOperand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5928:6: ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5928:6: ( (lv_sodbl_1_0= RULE_SIGNED_DOUBLE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5929:1: (lv_sodbl_1_0= RULE_SIGNED_DOUBLE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5929:1: (lv_sodbl_1_0= RULE_SIGNED_DOUBLE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5930:3: lv_sodbl_1_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_1_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand12513); 

                    			newLeafNode(lv_sodbl_1_0, grammarAccess.getScalarOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sodbl",
                            		lv_sodbl_1_0, 
                            		"SIGNED_DOUBLE");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5947:6: ( (lv_sodate_2_0= RULE_DATE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5947:6: ( (lv_sodate_2_0= RULE_DATE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5948:1: (lv_sodate_2_0= RULE_DATE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5948:1: (lv_sodate_2_0= RULE_DATE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5949:3: lv_sodate_2_0= RULE_DATE
                    {
                    lv_sodate_2_0=(Token)match(input,RULE_DATE,FOLLOW_RULE_DATE_in_ruleScalarOperand12541); 

                    			newLeafNode(lv_sodate_2_0, grammarAccess.getScalarOperandAccess().getSodateDATETerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sodate",
                            		lv_sodate_2_0, 
                            		"DATE");
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5966:6: ( (lv_sotime_3_0= RULE_TIME ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5966:6: ( (lv_sotime_3_0= RULE_TIME ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5967:1: (lv_sotime_3_0= RULE_TIME )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5967:1: (lv_sotime_3_0= RULE_TIME )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5968:3: lv_sotime_3_0= RULE_TIME
                    {
                    lv_sotime_3_0=(Token)match(input,RULE_TIME,FOLLOW_RULE_TIME_in_ruleScalarOperand12569); 

                    			newLeafNode(lv_sotime_3_0, grammarAccess.getScalarOperandAccess().getSotimeTIMETerminalRuleCall_3_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sotime",
                            		lv_sotime_3_0, 
                            		"TIME");
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5985:6: ( (lv_sodt_4_0= RULE_TIMESTAMP ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5985:6: ( (lv_sodt_4_0= RULE_TIMESTAMP ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5986:1: (lv_sodt_4_0= RULE_TIMESTAMP )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5986:1: (lv_sodt_4_0= RULE_TIMESTAMP )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:5987:3: lv_sodt_4_0= RULE_TIMESTAMP
                    {
                    lv_sodt_4_0=(Token)match(input,RULE_TIMESTAMP,FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand12597); 

                    			newLeafNode(lv_sodt_4_0, grammarAccess.getScalarOperandAccess().getSodtTIMESTAMPTerminalRuleCall_4_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sodt",
                            		lv_sodt_4_0, 
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


    // $ANTLR start "entryRuleScalarNumberOperand"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6011:1: entryRuleScalarNumberOperand returns [EObject current=null] : iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF ;
    public final EObject entryRuleScalarNumberOperand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalarNumberOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6012:2: (iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6013:2: iv_ruleScalarNumberOperand= ruleScalarNumberOperand EOF
            {
             newCompositeNode(grammarAccess.getScalarNumberOperandRule()); 
            pushFollow(FOLLOW_ruleScalarNumberOperand_in_entryRuleScalarNumberOperand12637);
            iv_ruleScalarNumberOperand=ruleScalarNumberOperand();

            state._fsp--;

             current =iv_ruleScalarNumberOperand; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarNumberOperand12647); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6020:1: ruleScalarNumberOperand returns [EObject current=null] : ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) ) ;
    public final EObject ruleScalarNumberOperand() throws RecognitionException {
        EObject current = null;

        Token lv_soUInt_0_0=null;
        Token lv_soint_1_0=null;
        Token lv_sodbl_2_0=null;
        AntlrDatatypeRuleToken lv_sostr_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6023:28: ( ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6024:1: ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6024:1: ( ( (lv_soUInt_0_0= RULE_UNSIGNED ) ) | ( (lv_soint_1_0= RULE_INT ) ) | ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) ) | ( (lv_sostr_3_0= ruleStringOperand ) ) )
            int alt118=4;
            switch ( input.LA(1) ) {
            case RULE_UNSIGNED:
                {
                alt118=1;
                }
                break;
            case RULE_INT:
                {
                alt118=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt118=3;
                }
                break;
            case RULE_STRING_:
                {
                alt118=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;
            }

            switch (alt118) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6024:2: ( (lv_soUInt_0_0= RULE_UNSIGNED ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6024:2: ( (lv_soUInt_0_0= RULE_UNSIGNED ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6025:1: (lv_soUInt_0_0= RULE_UNSIGNED )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6025:1: (lv_soUInt_0_0= RULE_UNSIGNED )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6026:3: lv_soUInt_0_0= RULE_UNSIGNED
                    {
                    lv_soUInt_0_0=(Token)match(input,RULE_UNSIGNED,FOLLOW_RULE_UNSIGNED_in_ruleScalarNumberOperand12689); 

                    			newLeafNode(lv_soUInt_0_0, grammarAccess.getScalarNumberOperandAccess().getSoUIntUNSIGNEDTerminalRuleCall_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarNumberOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"soUInt",
                            		lv_soUInt_0_0, 
                            		"UNSIGNED");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6043:6: ( (lv_soint_1_0= RULE_INT ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6043:6: ( (lv_soint_1_0= RULE_INT ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6044:1: (lv_soint_1_0= RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6044:1: (lv_soint_1_0= RULE_INT )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6045:3: lv_soint_1_0= RULE_INT
                    {
                    lv_soint_1_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleScalarNumberOperand12717); 

                    			newLeafNode(lv_soint_1_0, grammarAccess.getScalarNumberOperandAccess().getSointINTTerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarNumberOperandRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"soint",
                            		lv_soint_1_0, 
                            		"INT");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6062:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6062:6: ( (lv_sodbl_2_0= RULE_SIGNED_DOUBLE ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6063:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6063:1: (lv_sodbl_2_0= RULE_SIGNED_DOUBLE )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6064:3: lv_sodbl_2_0= RULE_SIGNED_DOUBLE
                    {
                    lv_sodbl_2_0=(Token)match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarNumberOperand12745); 

                    			newLeafNode(lv_sodbl_2_0, grammarAccess.getScalarNumberOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getScalarNumberOperandRule());
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6081:6: ( (lv_sostr_3_0= ruleStringOperand ) )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6081:6: ( (lv_sostr_3_0= ruleStringOperand ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6082:1: (lv_sostr_3_0= ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6082:1: (lv_sostr_3_0= ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6083:3: lv_sostr_3_0= ruleStringOperand
                    {
                     
                    	        newCompositeNode(grammarAccess.getScalarNumberOperandAccess().getSostrStringOperandParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStringOperand_in_ruleScalarNumberOperand12777);
                    lv_sostr_3_0=ruleStringOperand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getScalarNumberOperandRule());
                    	        }
                           		set(
                           			current, 
                           			"sostr",
                            		lv_sostr_3_0, 
                            		"StringOperand");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6107:1: entryRuleSQLCASE returns [EObject current=null] : iv_ruleSQLCASE= ruleSQLCASE EOF ;
    public final EObject entryRuleSQLCASE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCASE = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6108:2: (iv_ruleSQLCASE= ruleSQLCASE EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6109:2: iv_ruleSQLCASE= ruleSQLCASE EOF
            {
             newCompositeNode(grammarAccess.getSQLCASERule()); 
            pushFollow(FOLLOW_ruleSQLCASE_in_entryRuleSQLCASE12812);
            iv_ruleSQLCASE=ruleSQLCASE();

            state._fsp--;

             current =iv_ruleSQLCASE; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSQLCASE12822); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6116:1: ruleSQLCASE returns [EObject current=null] : (otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_31 ) ;
    public final EObject ruleSQLCASE() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_expr_1_0 = null;

        EObject lv_when_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6119:28: ( (otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_31 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6120:1: (otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_31 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6120:1: (otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_31 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6121:2: otherlv_0= KEYWORD_36 ( (lv_expr_1_0= ruleFullExpression ) )? ( (lv_when_2_0= ruleSQLCaseWhens ) ) otherlv_3= KEYWORD_31
            {
            otherlv_0=(Token)match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_ruleSQLCASE12860); 

                	newLeafNode(otherlv_0, grammarAccess.getSQLCASEAccess().getCASEKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6125:1: ( (lv_expr_1_0= ruleFullExpression ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==KEYWORD_108||LA119_0==KEYWORD_84||LA119_0==KEYWORD_75||LA119_0==KEYWORD_78||LA119_0==KEYWORD_57||LA119_0==KEYWORD_36||(LA119_0>=KEYWORD_47 && LA119_0<=KEYWORD_48)||LA119_0==KEYWORD_15||LA119_0==KEYWORD_20||LA119_0==KEYWORD_1||(LA119_0>=RULE_JRPARAM && LA119_0<=RULE_JRNPARAM)||(LA119_0>=RULE_UNSIGNED && LA119_0<=RULE_SIGNED_DOUBLE)||(LA119_0>=RULE_STRING_ && LA119_0<=RULE_ID)) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6126:1: (lv_expr_1_0= ruleFullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6126:1: (lv_expr_1_0= ruleFullExpression )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6127:3: lv_expr_1_0= ruleFullExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSQLCASEAccess().getExprFullExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFullExpression_in_ruleSQLCASE12880);
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6143:3: ( (lv_when_2_0= ruleSQLCaseWhens ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6144:1: (lv_when_2_0= ruleSQLCaseWhens )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6144:1: (lv_when_2_0= ruleSQLCaseWhens )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6145:3: lv_when_2_0= ruleSQLCaseWhens
            {
             
            	        newCompositeNode(grammarAccess.getSQLCASEAccess().getWhenSQLCaseWhensParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSQLCaseWhens_in_ruleSQLCASE12902);
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

            otherlv_3=(Token)match(input,KEYWORD_31,FOLLOW_KEYWORD_31_in_ruleSQLCASE12915); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6174:1: entryRuleSQLCaseWhens returns [EObject current=null] : iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF ;
    public final EObject entryRuleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSQLCaseWhens = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6175:2: (iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6176:2: iv_ruleSQLCaseWhens= ruleSQLCaseWhens EOF
            {
             newCompositeNode(grammarAccess.getSQLCaseWhensRule()); 
            pushFollow(FOLLOW_ruleSQLCaseWhens_in_entryRuleSQLCaseWhens12949);
            iv_ruleSQLCaseWhens=ruleSQLCaseWhens();

            state._fsp--;

             current =iv_ruleSQLCaseWhens; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSQLCaseWhens12959); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6183:1: ruleSQLCaseWhens returns [EObject current=null] : (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) ;
    public final EObject ruleSQLCaseWhens() throws RecognitionException {
        EObject current = null;

        EObject this_SqlCaseWhen_0 = null;

        EObject lv_entries_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6186:28: ( (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6187:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6187:1: (this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6188:5: this_SqlCaseWhen_0= ruleSqlCaseWhen ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            {
             
                    newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getSqlCaseWhenParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens13006);
            this_SqlCaseWhen_0=ruleSqlCaseWhen();

            state._fsp--;


                    current = this_SqlCaseWhen_0;
                    afterParserOrEnumRuleCall();
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6196:1: ( () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+ )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==KEYWORD_55) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6196:2: () ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6196:2: ()
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6197:5: 
                    {

                            current = forceCreateModelElementAndAdd(
                                grammarAccess.getSQLCaseWhensAccess().getWhenListEntriesAction_1_0(),
                                current);
                        

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6202:2: ( (lv_entries_2_0= ruleSqlCaseWhen ) )+
                    int cnt120=0;
                    loop120:
                    do {
                        int alt120=2;
                        int LA120_0 = input.LA(1);

                        if ( (LA120_0==KEYWORD_55) ) {
                            alt120=1;
                        }


                        switch (alt120) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6203:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    {
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6203:1: (lv_entries_2_0= ruleSqlCaseWhen )
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6204:3: lv_entries_2_0= ruleSqlCaseWhen
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSQLCaseWhensAccess().getEntriesSqlCaseWhenParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens13036);
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
    // $ANTLR end "ruleSQLCaseWhens"


    // $ANTLR start "entryRuleSqlCaseWhen"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6228:1: entryRuleSqlCaseWhen returns [EObject current=null] : iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF ;
    public final EObject entryRuleSqlCaseWhen() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSqlCaseWhen = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6229:2: (iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6230:2: iv_ruleSqlCaseWhen= ruleSqlCaseWhen EOF
            {
             newCompositeNode(grammarAccess.getSqlCaseWhenRule()); 
            pushFollow(FOLLOW_ruleSqlCaseWhen_in_entryRuleSqlCaseWhen13074);
            iv_ruleSqlCaseWhen=ruleSqlCaseWhen();

            state._fsp--;

             current =iv_ruleSqlCaseWhen; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSqlCaseWhen13084); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6237:1: ruleSqlCaseWhen returns [EObject current=null] : (otherlv_0= KEYWORD_55 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_53 ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= KEYWORD_38 ( (lv_eexp_5_0= ruleOperandGroup ) ) )? ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6240:28: ( (otherlv_0= KEYWORD_55 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_53 ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= KEYWORD_38 ( (lv_eexp_5_0= ruleOperandGroup ) ) )? ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6241:1: (otherlv_0= KEYWORD_55 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_53 ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= KEYWORD_38 ( (lv_eexp_5_0= ruleOperandGroup ) ) )? )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6241:1: (otherlv_0= KEYWORD_55 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_53 ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= KEYWORD_38 ( (lv_eexp_5_0= ruleOperandGroup ) ) )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6242:2: otherlv_0= KEYWORD_55 ( (lv_expr_1_0= ruleFullExpression ) ) otherlv_2= KEYWORD_53 ( (lv_texp_3_0= ruleOperandGroup ) ) (otherlv_4= KEYWORD_38 ( (lv_eexp_5_0= ruleOperandGroup ) ) )?
            {
            otherlv_0=(Token)match(input,KEYWORD_55,FOLLOW_KEYWORD_55_in_ruleSqlCaseWhen13122); 

                	newLeafNode(otherlv_0, grammarAccess.getSqlCaseWhenAccess().getWHENKeyword_0());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6246:1: ( (lv_expr_1_0= ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6247:1: (lv_expr_1_0= ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6247:1: (lv_expr_1_0= ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6248:3: lv_expr_1_0= ruleFullExpression
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getExprFullExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFullExpression_in_ruleSqlCaseWhen13142);
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

            otherlv_2=(Token)match(input,KEYWORD_53,FOLLOW_KEYWORD_53_in_ruleSqlCaseWhen13155); 

                	newLeafNode(otherlv_2, grammarAccess.getSqlCaseWhenAccess().getTHENKeyword_2());
                
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6269:1: ( (lv_texp_3_0= ruleOperandGroup ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6270:1: (lv_texp_3_0= ruleOperandGroup )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6270:1: (lv_texp_3_0= ruleOperandGroup )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6271:3: lv_texp_3_0= ruleOperandGroup
            {
             
            	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getTexpOperandGroupParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleOperandGroup_in_ruleSqlCaseWhen13175);
            lv_texp_3_0=ruleOperandGroup();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
            	        }
                   		set(
                   			current, 
                   			"texp",
                    		lv_texp_3_0, 
                    		"OperandGroup");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6287:2: (otherlv_4= KEYWORD_38 ( (lv_eexp_5_0= ruleOperandGroup ) ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==KEYWORD_38) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6288:2: otherlv_4= KEYWORD_38 ( (lv_eexp_5_0= ruleOperandGroup ) )
                    {
                    otherlv_4=(Token)match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_ruleSqlCaseWhen13189); 

                        	newLeafNode(otherlv_4, grammarAccess.getSqlCaseWhenAccess().getELSEKeyword_4_0());
                        
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6292:1: ( (lv_eexp_5_0= ruleOperandGroup ) )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6293:1: (lv_eexp_5_0= ruleOperandGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6293:1: (lv_eexp_5_0= ruleOperandGroup )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6294:3: lv_eexp_5_0= ruleOperandGroup
                    {
                     
                    	        newCompositeNode(grammarAccess.getSqlCaseWhenAccess().getEexpOperandGroupParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperandGroup_in_ruleSqlCaseWhen13209);
                    lv_eexp_5_0=ruleOperandGroup();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSqlCaseWhenRule());
                    	        }
                           		set(
                           			current, 
                           			"eexp",
                            		lv_eexp_5_0, 
                            		"OperandGroup");
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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6318:1: entryRuleJoinType returns [String current=null] : iv_ruleJoinType= ruleJoinType EOF ;
    public final String entryRuleJoinType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleJoinType = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6319:1: (iv_ruleJoinType= ruleJoinType EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6320:2: iv_ruleJoinType= ruleJoinType EOF
            {
             newCompositeNode(grammarAccess.getJoinTypeRule()); 
            pushFollow(FOLLOW_ruleJoinType_in_entryRuleJoinType13247);
            iv_ruleJoinType=ruleJoinType();

            state._fsp--;

             current =iv_ruleJoinType.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJoinType13258); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6327:1: ruleJoinType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= KEYWORD_88 )? (kw= KEYWORD_61 | ( (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )? ) | kw= KEYWORD_58 | kw= KEYWORD_119 )? kw= KEYWORD_42 ) ;
    public final AntlrDatatypeRuleToken ruleJoinType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6331:6: ( ( (kw= KEYWORD_88 )? (kw= KEYWORD_61 | ( (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )? ) | kw= KEYWORD_58 | kw= KEYWORD_119 )? kw= KEYWORD_42 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6332:1: ( (kw= KEYWORD_88 )? (kw= KEYWORD_61 | ( (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )? ) | kw= KEYWORD_58 | kw= KEYWORD_119 )? kw= KEYWORD_42 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6332:1: ( (kw= KEYWORD_88 )? (kw= KEYWORD_61 | ( (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )? ) | kw= KEYWORD_58 | kw= KEYWORD_119 )? kw= KEYWORD_42 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6332:2: (kw= KEYWORD_88 )? (kw= KEYWORD_61 | ( (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )? ) | kw= KEYWORD_58 | kw= KEYWORD_119 )? kw= KEYWORD_42
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6332:2: (kw= KEYWORD_88 )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==KEYWORD_88) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6333:2: kw= KEYWORD_88
                    {
                    kw=(Token)match(input,KEYWORD_88,FOLLOW_KEYWORD_88_in_ruleJoinType13297); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getNATURALKeyword_0()); 
                        

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6338:3: (kw= KEYWORD_61 | ( (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )? ) | kw= KEYWORD_58 | kw= KEYWORD_119 )?
            int alt126=5;
            switch ( input.LA(1) ) {
                case KEYWORD_61:
                    {
                    alt126=1;
                    }
                    break;
                case KEYWORD_71:
                case KEYWORD_40:
                case KEYWORD_44:
                    {
                    alt126=2;
                    }
                    break;
                case KEYWORD_58:
                    {
                    alt126=3;
                    }
                    break;
                case KEYWORD_119:
                    {
                    alt126=4;
                    }
                    break;
            }

            switch (alt126) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6339:2: kw= KEYWORD_61
                    {
                    kw=(Token)match(input,KEYWORD_61,FOLLOW_KEYWORD_61_in_ruleJoinType13313); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getINNERKeyword_1_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6345:6: ( (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )? )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6345:6: ( (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )? )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6345:7: (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 ) (kw= KEYWORD_68 )?
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6345:7: (kw= KEYWORD_44 | kw= KEYWORD_71 | kw= KEYWORD_40 )
                    int alt124=3;
                    switch ( input.LA(1) ) {
                    case KEYWORD_44:
                        {
                        alt124=1;
                        }
                        break;
                    case KEYWORD_71:
                        {
                        alt124=2;
                        }
                        break;
                    case KEYWORD_40:
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
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6346:2: kw= KEYWORD_44
                            {
                            kw=(Token)match(input,KEYWORD_44,FOLLOW_KEYWORD_44_in_ruleJoinType13334); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getLEFTKeyword_1_1_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6353:2: kw= KEYWORD_71
                            {
                            kw=(Token)match(input,KEYWORD_71,FOLLOW_KEYWORD_71_in_ruleJoinType13353); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getRIGHTKeyword_1_1_0_1()); 
                                

                            }
                            break;
                        case 3 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6360:2: kw= KEYWORD_40
                            {
                            kw=(Token)match(input,KEYWORD_40,FOLLOW_KEYWORD_40_in_ruleJoinType13372); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getFULLKeyword_1_1_0_2()); 
                                

                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6365:2: (kw= KEYWORD_68 )?
                    int alt125=2;
                    int LA125_0 = input.LA(1);

                    if ( (LA125_0==KEYWORD_68) ) {
                        alt125=1;
                    }
                    switch (alt125) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6366:2: kw= KEYWORD_68
                            {
                            kw=(Token)match(input,KEYWORD_68,FOLLOW_KEYWORD_68_in_ruleJoinType13387); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getJoinTypeAccess().getOUTERKeyword_1_1_1()); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6373:2: kw= KEYWORD_58
                    {
                    kw=(Token)match(input,KEYWORD_58,FOLLOW_KEYWORD_58_in_ruleJoinType13409); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getCROSSKeyword_1_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6380:2: kw= KEYWORD_119
                    {
                    kw=(Token)match(input,KEYWORD_119,FOLLOW_KEYWORD_119_in_ruleJoinType13428); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getJoinTypeAccess().getSTRAIGHT_JOINKeyword_1_3()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,KEYWORD_42,FOLLOW_KEYWORD_42_in_ruleJoinType13443); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6399:1: entryRuleDBID returns [String current=null] : iv_ruleDBID= ruleDBID EOF ;
    public final String entryRuleDBID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDBID = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6400:1: (iv_ruleDBID= ruleDBID EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6401:2: iv_ruleDBID= ruleDBID EOF
            {
             newCompositeNode(grammarAccess.getDBIDRule()); 
            pushFollow(FOLLOW_ruleDBID_in_entryRuleDBID13483);
            iv_ruleDBID=ruleDBID();

            state._fsp--;

             current =iv_ruleDBID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBID13494); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6408:1: ruleDBID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDBID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_DBNAME_1=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6412:6: ( (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6413:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6413:1: (this_ID_0= RULE_ID | this_DBNAME_1= RULE_DBNAME | this_STRING_2= RULE_STRING )
            int alt127=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt127=1;
                }
                break;
            case RULE_DBNAME:
                {
                alt127=2;
                }
                break;
            case RULE_STRING:
                {
                alt127=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 127, 0, input);

                throw nvae;
            }

            switch (alt127) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6413:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDBID13534); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getDBIDAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6421:10: this_DBNAME_1= RULE_DBNAME
                    {
                    this_DBNAME_1=(Token)match(input,RULE_DBNAME,FOLLOW_RULE_DBNAME_in_ruleDBID13560); 

                    		current.merge(this_DBNAME_1);
                        
                     
                        newLeafNode(this_DBNAME_1, grammarAccess.getDBIDAccess().getDBNAMETerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6429:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDBID13586); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6444:1: entryRuleStringOperand returns [String current=null] : iv_ruleStringOperand= ruleStringOperand EOF ;
    public final String entryRuleStringOperand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringOperand = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6445:1: (iv_ruleStringOperand= ruleStringOperand EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6446:2: iv_ruleStringOperand= ruleStringOperand EOF
            {
             newCompositeNode(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_ruleStringOperand_in_entryRuleStringOperand13631);
            iv_ruleStringOperand=ruleStringOperand();

            state._fsp--;

             current =iv_ruleStringOperand.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringOperand13642); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6453:1: ruleStringOperand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING__0= RULE_STRING_ ;
    public final AntlrDatatypeRuleToken ruleStringOperand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING__0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6457:6: (this_STRING__0= RULE_STRING_ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6458:5: this_STRING__0= RULE_STRING_
            {
            this_STRING__0=(Token)match(input,RULE_STRING_,FOLLOW_RULE_STRING__in_ruleStringOperand13681); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6473:1: entryRuleFNAME returns [String current=null] : iv_ruleFNAME= ruleFNAME EOF ;
    public final String entryRuleFNAME() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFNAME = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6474:1: (iv_ruleFNAME= ruleFNAME EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6475:2: iv_ruleFNAME= ruleFNAME EOF
            {
             newCompositeNode(grammarAccess.getFNAMERule()); 
            pushFollow(FOLLOW_ruleFNAME_in_entryRuleFNAME13725);
            iv_ruleFNAME=ruleFNAME();

            state._fsp--;

             current =iv_ruleFNAME.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFNAME13736); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6482:1: ruleFNAME returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= KEYWORD_1 ) ;
    public final AntlrDatatypeRuleToken ruleFNAME() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6486:6: ( (this_ID_0= RULE_ID kw= KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6487:1: (this_ID_0= RULE_ID kw= KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6487:1: (this_ID_0= RULE_ID kw= KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6487:6: this_ID_0= RULE_ID kw= KEYWORD_1
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFNAME13776); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getFNAMEAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_ruleFNAME13794); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6508:1: entryRuleIntegerValue returns [EObject current=null] : iv_ruleIntegerValue= ruleIntegerValue EOF ;
    public final EObject entryRuleIntegerValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerValue = null;


        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6509:2: (iv_ruleIntegerValue= ruleIntegerValue EOF )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6510:2: iv_ruleIntegerValue= ruleIntegerValue EOF
            {
             newCompositeNode(grammarAccess.getIntegerValueRule()); 
            pushFollow(FOLLOW_ruleIntegerValue_in_entryRuleIntegerValue13833);
            iv_ruleIntegerValue=ruleIntegerValue();

            state._fsp--;

             current =iv_ruleIntegerValue; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerValue13843); 

            }

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6517:1: ruleIntegerValue returns [EObject current=null] : ( (lv_integer_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerValue() throws RecognitionException {
        EObject current = null;

        Token lv_integer_0_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6520:28: ( ( (lv_integer_0_0= RULE_INT ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6521:1: ( (lv_integer_0_0= RULE_INT ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6521:1: ( (lv_integer_0_0= RULE_INT ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6522:1: (lv_integer_0_0= RULE_INT )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6522:1: (lv_integer_0_0= RULE_INT )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6523:3: lv_integer_0_0= RULE_INT
            {
            lv_integer_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerValue13884); 

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


    // $ANTLR start "ruleEXTRACT_VALUES"
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6547:1: ruleEXTRACT_VALUES returns [Enumerator current=null] : ( (enumLiteral_0= KEYWORD_115 ) | (enumLiteral_1= KEYWORD_80 ) | (enumLiteral_2= KEYWORD_77 ) | (enumLiteral_3= KEYWORD_41 ) | (enumLiteral_4= KEYWORD_30 ) | (enumLiteral_5= KEYWORD_54 ) | (enumLiteral_6= KEYWORD_65 ) | (enumLiteral_7= KEYWORD_90 ) | (enumLiteral_8= KEYWORD_56 ) | (enumLiteral_9= KEYWORD_124 ) | (enumLiteral_10= KEYWORD_123 ) | (enumLiteral_11= KEYWORD_118 ) | (enumLiteral_12= KEYWORD_121 ) | (enumLiteral_13= KEYWORD_113 ) | (enumLiteral_14= KEYWORD_112 ) | (enumLiteral_15= KEYWORD_120 ) | (enumLiteral_16= KEYWORD_107 ) | (enumLiteral_17= KEYWORD_106 ) | (enumLiteral_18= KEYWORD_93 ) | (enumLiteral_19= KEYWORD_109 ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6549:28: ( ( (enumLiteral_0= KEYWORD_115 ) | (enumLiteral_1= KEYWORD_80 ) | (enumLiteral_2= KEYWORD_77 ) | (enumLiteral_3= KEYWORD_41 ) | (enumLiteral_4= KEYWORD_30 ) | (enumLiteral_5= KEYWORD_54 ) | (enumLiteral_6= KEYWORD_65 ) | (enumLiteral_7= KEYWORD_90 ) | (enumLiteral_8= KEYWORD_56 ) | (enumLiteral_9= KEYWORD_124 ) | (enumLiteral_10= KEYWORD_123 ) | (enumLiteral_11= KEYWORD_118 ) | (enumLiteral_12= KEYWORD_121 ) | (enumLiteral_13= KEYWORD_113 ) | (enumLiteral_14= KEYWORD_112 ) | (enumLiteral_15= KEYWORD_120 ) | (enumLiteral_16= KEYWORD_107 ) | (enumLiteral_17= KEYWORD_106 ) | (enumLiteral_18= KEYWORD_93 ) | (enumLiteral_19= KEYWORD_109 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6550:1: ( (enumLiteral_0= KEYWORD_115 ) | (enumLiteral_1= KEYWORD_80 ) | (enumLiteral_2= KEYWORD_77 ) | (enumLiteral_3= KEYWORD_41 ) | (enumLiteral_4= KEYWORD_30 ) | (enumLiteral_5= KEYWORD_54 ) | (enumLiteral_6= KEYWORD_65 ) | (enumLiteral_7= KEYWORD_90 ) | (enumLiteral_8= KEYWORD_56 ) | (enumLiteral_9= KEYWORD_124 ) | (enumLiteral_10= KEYWORD_123 ) | (enumLiteral_11= KEYWORD_118 ) | (enumLiteral_12= KEYWORD_121 ) | (enumLiteral_13= KEYWORD_113 ) | (enumLiteral_14= KEYWORD_112 ) | (enumLiteral_15= KEYWORD_120 ) | (enumLiteral_16= KEYWORD_107 ) | (enumLiteral_17= KEYWORD_106 ) | (enumLiteral_18= KEYWORD_93 ) | (enumLiteral_19= KEYWORD_109 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6550:1: ( (enumLiteral_0= KEYWORD_115 ) | (enumLiteral_1= KEYWORD_80 ) | (enumLiteral_2= KEYWORD_77 ) | (enumLiteral_3= KEYWORD_41 ) | (enumLiteral_4= KEYWORD_30 ) | (enumLiteral_5= KEYWORD_54 ) | (enumLiteral_6= KEYWORD_65 ) | (enumLiteral_7= KEYWORD_90 ) | (enumLiteral_8= KEYWORD_56 ) | (enumLiteral_9= KEYWORD_124 ) | (enumLiteral_10= KEYWORD_123 ) | (enumLiteral_11= KEYWORD_118 ) | (enumLiteral_12= KEYWORD_121 ) | (enumLiteral_13= KEYWORD_113 ) | (enumLiteral_14= KEYWORD_112 ) | (enumLiteral_15= KEYWORD_120 ) | (enumLiteral_16= KEYWORD_107 ) | (enumLiteral_17= KEYWORD_106 ) | (enumLiteral_18= KEYWORD_93 ) | (enumLiteral_19= KEYWORD_109 ) )
            int alt128=20;
            switch ( input.LA(1) ) {
            case KEYWORD_115:
                {
                alt128=1;
                }
                break;
            case KEYWORD_80:
                {
                alt128=2;
                }
                break;
            case KEYWORD_77:
                {
                alt128=3;
                }
                break;
            case KEYWORD_41:
                {
                alt128=4;
                }
                break;
            case KEYWORD_30:
                {
                alt128=5;
                }
                break;
            case KEYWORD_54:
                {
                alt128=6;
                }
                break;
            case KEYWORD_65:
                {
                alt128=7;
                }
                break;
            case KEYWORD_90:
                {
                alt128=8;
                }
                break;
            case KEYWORD_56:
                {
                alt128=9;
                }
                break;
            case KEYWORD_124:
                {
                alt128=10;
                }
                break;
            case KEYWORD_123:
                {
                alt128=11;
                }
                break;
            case KEYWORD_118:
                {
                alt128=12;
                }
                break;
            case KEYWORD_121:
                {
                alt128=13;
                }
                break;
            case KEYWORD_113:
                {
                alt128=14;
                }
                break;
            case KEYWORD_112:
                {
                alt128=15;
                }
                break;
            case KEYWORD_120:
                {
                alt128=16;
                }
                break;
            case KEYWORD_107:
                {
                alt128=17;
                }
                break;
            case KEYWORD_106:
                {
                alt128=18;
                }
                break;
            case KEYWORD_93:
                {
                alt128=19;
                }
                break;
            case KEYWORD_109:
                {
                alt128=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 128, 0, input);

                throw nvae;
            }

            switch (alt128) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6550:2: (enumLiteral_0= KEYWORD_115 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6550:2: (enumLiteral_0= KEYWORD_115 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6550:7: enumLiteral_0= KEYWORD_115
                    {
                    enumLiteral_0=(Token)match(input,KEYWORD_115,FOLLOW_KEYWORD_115_in_ruleEXTRACT_VALUES13941); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMsEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getEXTRACT_VALUESAccess().getMsEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6556:6: (enumLiteral_1= KEYWORD_80 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6556:6: (enumLiteral_1= KEYWORD_80 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6556:11: enumLiteral_1= KEYWORD_80
                    {
                    enumLiteral_1=(Token)match(input,KEYWORD_80,FOLLOW_KEYWORD_80_in_ruleEXTRACT_VALUES13963); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getEXTRACT_VALUESAccess().getSEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6562:6: (enumLiteral_2= KEYWORD_77 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6562:6: (enumLiteral_2= KEYWORD_77 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6562:11: enumLiteral_2= KEYWORD_77
                    {
                    enumLiteral_2=(Token)match(input,KEYWORD_77,FOLLOW_KEYWORD_77_in_ruleEXTRACT_VALUES13985); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getEXTRACT_VALUESAccess().getMEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6568:6: (enumLiteral_3= KEYWORD_41 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6568:6: (enumLiteral_3= KEYWORD_41 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6568:11: enumLiteral_3= KEYWORD_41
                    {
                    enumLiteral_3=(Token)match(input,KEYWORD_41,FOLLOW_KEYWORD_41_in_ruleEXTRACT_VALUES14007); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getEXTRACT_VALUESAccess().getHEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6574:6: (enumLiteral_4= KEYWORD_30 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6574:6: (enumLiteral_4= KEYWORD_30 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6574:11: enumLiteral_4= KEYWORD_30
                    {
                    enumLiteral_4=(Token)match(input,KEYWORD_30,FOLLOW_KEYWORD_30_in_ruleEXTRACT_VALUES14029); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getEXTRACT_VALUESAccess().getDayEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6580:6: (enumLiteral_5= KEYWORD_54 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6580:6: (enumLiteral_5= KEYWORD_54 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6580:11: enumLiteral_5= KEYWORD_54
                    {
                    enumLiteral_5=(Token)match(input,KEYWORD_54,FOLLOW_KEYWORD_54_in_ruleEXTRACT_VALUES14051); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getWeekEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getEXTRACT_VALUESAccess().getWeekEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6586:6: (enumLiteral_6= KEYWORD_65 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6586:6: (enumLiteral_6= KEYWORD_65 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6586:11: enumLiteral_6= KEYWORD_65
                    {
                    enumLiteral_6=(Token)match(input,KEYWORD_65,FOLLOW_KEYWORD_65_in_ruleEXTRACT_VALUES14073); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMonthEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getEXTRACT_VALUESAccess().getMonthEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6592:6: (enumLiteral_7= KEYWORD_90 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6592:6: (enumLiteral_7= KEYWORD_90 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6592:11: enumLiteral_7= KEYWORD_90
                    {
                    enumLiteral_7=(Token)match(input,KEYWORD_90,FOLLOW_KEYWORD_90_in_ruleEXTRACT_VALUES14095); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getQuartEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getEXTRACT_VALUESAccess().getQuartEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6598:6: (enumLiteral_8= KEYWORD_56 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6598:6: (enumLiteral_8= KEYWORD_56 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6598:11: enumLiteral_8= KEYWORD_56
                    {
                    enumLiteral_8=(Token)match(input,KEYWORD_56,FOLLOW_KEYWORD_56_in_ruleEXTRACT_VALUES14117); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getYearEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getEXTRACT_VALUESAccess().getYearEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6604:6: (enumLiteral_9= KEYWORD_124 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6604:6: (enumLiteral_9= KEYWORD_124 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6604:11: enumLiteral_9= KEYWORD_124
                    {
                    enumLiteral_9=(Token)match(input,KEYWORD_124,FOLLOW_KEYWORD_124_in_ruleEXTRACT_VALUES14139); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMicrosEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getEXTRACT_VALUESAccess().getMicrosEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6610:6: (enumLiteral_10= KEYWORD_123 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6610:6: (enumLiteral_10= KEYWORD_123 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6610:11: enumLiteral_10= KEYWORD_123
                    {
                    enumLiteral_10=(Token)match(input,KEYWORD_123,FOLLOW_KEYWORD_123_in_ruleEXTRACT_VALUES14161); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMinMicroEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getEXTRACT_VALUESAccess().getMinMicroEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6616:6: (enumLiteral_11= KEYWORD_118 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6616:6: (enumLiteral_11= KEYWORD_118 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6616:11: enumLiteral_11= KEYWORD_118
                    {
                    enumLiteral_11=(Token)match(input,KEYWORD_118,FOLLOW_KEYWORD_118_in_ruleEXTRACT_VALUES14183); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getMinSecEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_11, grammarAccess.getEXTRACT_VALUESAccess().getMinSecEnumLiteralDeclaration_11()); 
                        

                    }


                    }
                    break;
                case 13 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6622:6: (enumLiteral_12= KEYWORD_121 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6622:6: (enumLiteral_12= KEYWORD_121 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6622:11: enumLiteral_12= KEYWORD_121
                    {
                    enumLiteral_12=(Token)match(input,KEYWORD_121,FOLLOW_KEYWORD_121_in_ruleEXTRACT_VALUES14205); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHmsEnumLiteralDeclaration_12().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_12, grammarAccess.getEXTRACT_VALUESAccess().getHmsEnumLiteralDeclaration_12()); 
                        

                    }


                    }
                    break;
                case 14 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6628:6: (enumLiteral_13= KEYWORD_113 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6628:6: (enumLiteral_13= KEYWORD_113 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6628:11: enumLiteral_13= KEYWORD_113
                    {
                    enumLiteral_13=(Token)match(input,KEYWORD_113,FOLLOW_KEYWORD_113_in_ruleEXTRACT_VALUES14227); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHsEnumLiteralDeclaration_13().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_13, grammarAccess.getEXTRACT_VALUESAccess().getHsEnumLiteralDeclaration_13()); 
                        

                    }


                    }
                    break;
                case 15 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6634:6: (enumLiteral_14= KEYWORD_112 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6634:6: (enumLiteral_14= KEYWORD_112 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6634:11: enumLiteral_14= KEYWORD_112
                    {
                    enumLiteral_14=(Token)match(input,KEYWORD_112,FOLLOW_KEYWORD_112_in_ruleEXTRACT_VALUES14249); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getHminEnumLiteralDeclaration_14().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_14, grammarAccess.getEXTRACT_VALUESAccess().getHminEnumLiteralDeclaration_14()); 
                        

                    }


                    }
                    break;
                case 16 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6640:6: (enumLiteral_15= KEYWORD_120 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6640:6: (enumLiteral_15= KEYWORD_120 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6640:11: enumLiteral_15= KEYWORD_120
                    {
                    enumLiteral_15=(Token)match(input,KEYWORD_120,FOLLOW_KEYWORD_120_in_ruleEXTRACT_VALUES14271); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDmsEnumLiteralDeclaration_15().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_15, grammarAccess.getEXTRACT_VALUESAccess().getDmsEnumLiteralDeclaration_15()); 
                        

                    }


                    }
                    break;
                case 17 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6646:6: (enumLiteral_16= KEYWORD_107 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6646:6: (enumLiteral_16= KEYWORD_107 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6646:11: enumLiteral_16= KEYWORD_107
                    {
                    enumLiteral_16=(Token)match(input,KEYWORD_107,FOLLOW_KEYWORD_107_in_ruleEXTRACT_VALUES14293); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDsEnumLiteralDeclaration_16().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_16, grammarAccess.getEXTRACT_VALUESAccess().getDsEnumLiteralDeclaration_16()); 
                        

                    }


                    }
                    break;
                case 18 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6652:6: (enumLiteral_17= KEYWORD_106 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6652:6: (enumLiteral_17= KEYWORD_106 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6652:11: enumLiteral_17= KEYWORD_106
                    {
                    enumLiteral_17=(Token)match(input,KEYWORD_106,FOLLOW_KEYWORD_106_in_ruleEXTRACT_VALUES14315); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayminEnumLiteralDeclaration_17().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_17, grammarAccess.getEXTRACT_VALUESAccess().getDayminEnumLiteralDeclaration_17()); 
                        

                    }


                    }
                    break;
                case 19 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6658:6: (enumLiteral_18= KEYWORD_93 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6658:6: (enumLiteral_18= KEYWORD_93 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6658:11: enumLiteral_18= KEYWORD_93
                    {
                    enumLiteral_18=(Token)match(input,KEYWORD_93,FOLLOW_KEYWORD_93_in_ruleEXTRACT_VALUES14337); 

                            current = grammarAccess.getEXTRACT_VALUESAccess().getDayhEnumLiteralDeclaration_18().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_18, grammarAccess.getEXTRACT_VALUESAccess().getDayhEnumLiteralDeclaration_18()); 
                        

                    }


                    }
                    break;
                case 20 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6664:6: (enumLiteral_19= KEYWORD_109 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6664:6: (enumLiteral_19= KEYWORD_109 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6664:11: enumLiteral_19= KEYWORD_109
                    {
                    enumLiteral_19=(Token)match(input,KEYWORD_109,FOLLOW_KEYWORD_109_in_ruleEXTRACT_VALUES14359); 

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
    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6674:1: ruleXFunction returns [Enumerator current=null] : ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_66 ) | (enumLiteral_2= KEYWORD_59 ) | (enumLiteral_3= KEYWORD_97 ) | (enumLiteral_4= KEYWORD_45 ) | (enumLiteral_5= KEYWORD_85 ) | (enumLiteral_6= KEYWORD_62 ) | (enumLiteral_7= KEYWORD_100 ) | (enumLiteral_8= KEYWORD_82 ) | (enumLiteral_9= KEYWORD_105 ) | (enumLiteral_10= KEYWORD_99 ) | (enumLiteral_11= KEYWORD_92 ) ) ;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6676:28: ( ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_66 ) | (enumLiteral_2= KEYWORD_59 ) | (enumLiteral_3= KEYWORD_97 ) | (enumLiteral_4= KEYWORD_45 ) | (enumLiteral_5= KEYWORD_85 ) | (enumLiteral_6= KEYWORD_62 ) | (enumLiteral_7= KEYWORD_100 ) | (enumLiteral_8= KEYWORD_82 ) | (enumLiteral_9= KEYWORD_105 ) | (enumLiteral_10= KEYWORD_99 ) | (enumLiteral_11= KEYWORD_92 ) ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6677:1: ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_66 ) | (enumLiteral_2= KEYWORD_59 ) | (enumLiteral_3= KEYWORD_97 ) | (enumLiteral_4= KEYWORD_45 ) | (enumLiteral_5= KEYWORD_85 ) | (enumLiteral_6= KEYWORD_62 ) | (enumLiteral_7= KEYWORD_100 ) | (enumLiteral_8= KEYWORD_82 ) | (enumLiteral_9= KEYWORD_105 ) | (enumLiteral_10= KEYWORD_99 ) | (enumLiteral_11= KEYWORD_92 ) )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6677:1: ( (enumLiteral_0= KEYWORD_20 ) | (enumLiteral_1= KEYWORD_66 ) | (enumLiteral_2= KEYWORD_59 ) | (enumLiteral_3= KEYWORD_97 ) | (enumLiteral_4= KEYWORD_45 ) | (enumLiteral_5= KEYWORD_85 ) | (enumLiteral_6= KEYWORD_62 ) | (enumLiteral_7= KEYWORD_100 ) | (enumLiteral_8= KEYWORD_82 ) | (enumLiteral_9= KEYWORD_105 ) | (enumLiteral_10= KEYWORD_99 ) | (enumLiteral_11= KEYWORD_92 ) )
            int alt129=12;
            switch ( input.LA(1) ) {
            case KEYWORD_20:
                {
                alt129=1;
                }
                break;
            case KEYWORD_66:
                {
                alt129=2;
                }
                break;
            case KEYWORD_59:
                {
                alt129=3;
                }
                break;
            case KEYWORD_97:
                {
                alt129=4;
                }
                break;
            case KEYWORD_45:
                {
                alt129=5;
                }
                break;
            case KEYWORD_85:
                {
                alt129=6;
                }
                break;
            case KEYWORD_62:
                {
                alt129=7;
                }
                break;
            case KEYWORD_100:
                {
                alt129=8;
                }
                break;
            case KEYWORD_82:
                {
                alt129=9;
                }
                break;
            case KEYWORD_105:
                {
                alt129=10;
                }
                break;
            case KEYWORD_99:
                {
                alt129=11;
                }
                break;
            case KEYWORD_92:
                {
                alt129=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 129, 0, input);

                throw nvae;
            }

            switch (alt129) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6677:2: (enumLiteral_0= KEYWORD_20 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6677:2: (enumLiteral_0= KEYWORD_20 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6677:7: enumLiteral_0= KEYWORD_20
                    {
                    enumLiteral_0=(Token)match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_ruleXFunction14409); 

                            current = grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6683:6: (enumLiteral_1= KEYWORD_66 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6683:6: (enumLiteral_1= KEYWORD_66 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6683:11: enumLiteral_1= KEYWORD_66
                    {
                    enumLiteral_1=(Token)match(input,KEYWORD_66,FOLLOW_KEYWORD_66_in_ruleXFunction14431); 

                            current = grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6689:6: (enumLiteral_2= KEYWORD_59 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6689:6: (enumLiteral_2= KEYWORD_59 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6689:11: enumLiteral_2= KEYWORD_59
                    {
                    enumLiteral_2=(Token)match(input,KEYWORD_59,FOLLOW_KEYWORD_59_in_ruleXFunction14453); 

                            current = grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6695:6: (enumLiteral_3= KEYWORD_97 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6695:6: (enumLiteral_3= KEYWORD_97 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6695:11: enumLiteral_3= KEYWORD_97
                    {
                    enumLiteral_3=(Token)match(input,KEYWORD_97,FOLLOW_KEYWORD_97_in_ruleXFunction14475); 

                            current = grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6701:6: (enumLiteral_4= KEYWORD_45 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6701:6: (enumLiteral_4= KEYWORD_45 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6701:11: enumLiteral_4= KEYWORD_45
                    {
                    enumLiteral_4=(Token)match(input,KEYWORD_45,FOLLOW_KEYWORD_45_in_ruleXFunction14497); 

                            current = grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6707:6: (enumLiteral_5= KEYWORD_85 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6707:6: (enumLiteral_5= KEYWORD_85 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6707:11: enumLiteral_5= KEYWORD_85
                    {
                    enumLiteral_5=(Token)match(input,KEYWORD_85,FOLLOW_KEYWORD_85_in_ruleXFunction14519); 

                            current = grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6713:6: (enumLiteral_6= KEYWORD_62 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6713:6: (enumLiteral_6= KEYWORD_62 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6713:11: enumLiteral_6= KEYWORD_62
                    {
                    enumLiteral_6=(Token)match(input,KEYWORD_62,FOLLOW_KEYWORD_62_in_ruleXFunction14541); 

                            current = grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6719:6: (enumLiteral_7= KEYWORD_100 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6719:6: (enumLiteral_7= KEYWORD_100 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6719:11: enumLiteral_7= KEYWORD_100
                    {
                    enumLiteral_7=(Token)match(input,KEYWORD_100,FOLLOW_KEYWORD_100_in_ruleXFunction14563); 

                            current = grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6725:6: (enumLiteral_8= KEYWORD_82 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6725:6: (enumLiteral_8= KEYWORD_82 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6725:11: enumLiteral_8= KEYWORD_82
                    {
                    enumLiteral_8=(Token)match(input,KEYWORD_82,FOLLOW_KEYWORD_82_in_ruleXFunction14585); 

                            current = grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6731:6: (enumLiteral_9= KEYWORD_105 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6731:6: (enumLiteral_9= KEYWORD_105 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6731:11: enumLiteral_9= KEYWORD_105
                    {
                    enumLiteral_9=(Token)match(input,KEYWORD_105,FOLLOW_KEYWORD_105_in_ruleXFunction14607); 

                            current = grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6737:6: (enumLiteral_10= KEYWORD_99 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6737:6: (enumLiteral_10= KEYWORD_99 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6737:11: enumLiteral_10= KEYWORD_99
                    {
                    enumLiteral_10=(Token)match(input,KEYWORD_99,FOLLOW_KEYWORD_99_in_ruleXFunction14629); 

                            current = grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6743:6: (enumLiteral_11= KEYWORD_92 )
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6743:6: (enumLiteral_11= KEYWORD_92 )
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/internal/InternalSqlParser.g:6743:11: enumLiteral_11= KEYWORD_92
                    {
                    enumLiteral_11=(Token)match(input,KEYWORD_92,FOLLOW_KEYWORD_92_in_ruleXFunction14651); 

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
        "\1\153\1\177\1\35\14\170\2\uffff";
    static final String DFA62_maxS =
        "\1\153\1\177\1\160\14\u0080\2\uffff";
    static final String DFA62_acceptS =
        "\17\uffff\1\1\1\2";
    static final String DFA62_specialS =
        "\21\uffff}>";
    static final String[] DFA62_transitionS = {
            "\1\1",
            "\1\2",
            "\1\14\1\16\4\uffff\1\6\1\uffff\1\15\1\12\1\13\2\uffff\1\10"+
            "\20\uffff\1\5\2\uffff\1\11\3\uffff\1\4\20\uffff\1\7\34\uffff"+
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
            return "2690:1: (lv_xexp_2_1= ruleXExpression | lv_xexp_2_2= ruleXExpression_ )";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_ruleModel122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFetchFirst_in_entryRuleFetchFirst156 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFetchFirst166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerValue_in_ruleFetchFirst212 = new BitSet(new long[]{0x0000000000000000L,0x0000008002000000L});
    public static final BitSet FOLLOW_KEYWORD_33_in_ruleFetchFirst234 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_KEYWORD_51_in_ruleFetchFirst262 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_KEYWORD_49_in_ruleFetchFirst289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOffset_in_entryRuleOffset323 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOffset333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOffset374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLimit_in_entryRuleLimit413 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLimit423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleLimit471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_in_ruleLimit495 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleLimit514 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_in_ruleLimit530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_entryRuleSelectQuery573 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectQuery583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSelectQuery630 = new BitSet(new long[]{0x0002000004000002L,0x0000000000000101L});
    public static final BitSet FOLLOW_ruleSelectSubSet_in_ruleSelectQuery650 = new BitSet(new long[]{0x0002000004000002L,0x0000000000000101L});
    public static final BitSet FOLLOW_ruleSelectSubSet_in_entryRuleSelectSubSet686 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectSubSet696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_72_in_ruleSelectSubSet742 = new BitSet(new long[]{0x0100000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_102_in_ruleSelectSubSet770 = new BitSet(new long[]{0x0100000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_64_in_ruleSelectSubSet798 = new BitSet(new long[]{0x0100000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_74_in_ruleSelectSubSet826 = new BitSet(new long[]{0x0100000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleSelectSubSet859 = new BitSet(new long[]{0x0100000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleSelect_in_ruleSelectSubSet892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_entryRuleSelect927 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelect937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_81_in_ruleSelect981 = new BitSet(new long[]{0x0200020100000000L,0x0020010000000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_KEYWORD_94_in_ruleSelect1006 = new BitSet(new long[]{0x0200020100000000L,0x0020010000000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_KEYWORD_34_in_ruleSelect1021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleSelect1032 = new BitSet(new long[]{0x0200420110000000L,0x0020010000000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleSelect1048 = new BitSet(new long[]{0x0200420110000000L,0x0020010000000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_KEYWORD_89_in_ruleSelect1062 = new BitSet(new long[]{0x0200020110000000L,0x0020010000000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_KEYWORD_104_in_ruleSelect1077 = new BitSet(new long[]{0x0200020100000000L,0x0020010000000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_ruleColumns_in_ruleSelect1101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_KEYWORD_39_in_ruleSelect1114 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleTables_in_ruleSelect1134 = new BitSet(new long[]{0x8048001200008002L,0x0000000000000200L});
    public static final BitSet FOLLOW_KEYWORD_73_in_ruleSelect1148 = new BitSet(new long[]{0x0224020000800000L,0x0021080000600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect1168 = new BitSet(new long[]{0x8048001200008002L});
    public static final BitSet FOLLOW_KEYWORD_95_in_ruleSelect1184 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007020L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_ruleSelect1204 = new BitSet(new long[]{0x8048001000008002L});
    public static final BitSet FOLLOW_KEYWORD_76_in_ruleSelect1220 = new BitSet(new long[]{0x0224020000800000L,0x0021080000600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSelect1240 = new BitSet(new long[]{0x8040001000008002L});
    public static final BitSet FOLLOW_KEYWORD_98_in_ruleSelect1256 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007020L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_ruleSelect1276 = new BitSet(new long[]{0x8040000000008002L});
    public static final BitSet FOLLOW_KEYWORD_63_in_ruleSelect1292 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_ruleLimit_in_ruleSelect1312 = new BitSet(new long[]{0x0040000000008002L});
    public static final BitSet FOLLOW_KEYWORD_79_in_ruleSelect1328 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleOffset_in_ruleSelect1348 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_KEYWORD_111_in_ruleSelect1364 = new BitSet(new long[]{0x0000000000000000L,0x0000008002000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleFetchFirst_in_ruleSelect1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns1421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns1431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns1478 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleColumns1501 = new BitSet(new long[]{0x0200020100000000L,0x0020010000000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_ruleColumns1521 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias1560 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias1570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleColumnOrAlias1617 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleColumnOrAlias1636 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnOrAlias1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleColumnOrAlias1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectNameAll_in_ruleColumnOrAlias1726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull1761 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull1771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull1818 = new BitSet(new long[]{0x0000000000000002L,0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleColumnFull1841 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleColumnFull1861 = new BitSet(new long[]{0x0000000000000002L,0x0400000000000000L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables1900 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables1910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables1957 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleTables1980 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleFromTable_in_ruleTables2000 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleFromTable_in_entryRuleFromTable2039 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTable2049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTable2095 = new BitSet(new long[]{0x2400200000001002L,0x0000000000054080L});
    public static final BitSet FOLLOW_ruleFromTableJoin_in_ruleFromTable2116 = new BitSet(new long[]{0x2400200000001002L,0x0000000000054080L});
    public static final BitSet FOLLOW_ruleFromTableJoin_in_entryRuleFromTableJoin2152 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTableJoin2162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_ruleFromTableJoin2208 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_ruleFromTableJoin2229 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_21_in_ruleFromTableJoin2242 = new BitSet(new long[]{0x0224020000800000L,0x0021080000600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleFromTableJoin2262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias2297 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias2307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_ruleTableOrAlias2354 = new BitSet(new long[]{0x0001000000000002L,0x0000800000000020L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleTableOrAlias2381 = new BitSet(new long[]{0x0001000000000002L,0x0000800000000020L,0x0000000000007000L});
    public static final BitSet FOLLOW_rulePivotTable_in_ruleTableOrAlias2404 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleUnpivotTable_in_ruleTableOrAlias2431 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleTableOrAlias2452 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableOrAlias2485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotTable_in_entryRulePivotTable2521 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotTable2531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_69_in_rulePivotTable2569 = new BitSet(new long[]{0x0000000000000000L,0x0020020000000000L});
    public static final BitSet FOLLOW_KEYWORD_35_in_rulePivotTable2582 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotTable2596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_rulePivotFunctions_in_rulePivotTable2616 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_rulePivotForClause_in_rulePivotTable2637 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_rulePivotInClause_in_rulePivotTable2658 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotTable2671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotFunctions_in_entryRulePivotFunctions2705 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotFunctions2715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rulePivotFunctions2756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotInClause_in_entryRulePivotInClause2797 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotInClause2807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_rulePivotInClause2845 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotInClause2857 = new BitSet(new long[]{0x0000000000000000L,0x0020000400000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rulePivotInClause2878 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_rulePivotInClause2905 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_rulePivotInClauseAny_in_rulePivotInClause2932 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotInClause2946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotInClauseAny_in_entryRulePivotInClauseAny2981 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotInClauseAny2992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rulePivotInClauseAny3030 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rulePivotInClauseAny3044 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rulePivotInClauseAny3057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotTable_in_entryRuleUnpivotTable3098 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotTable3108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_91_in_ruleUnpivotTable3146 = new BitSet(new long[]{0x0000090000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_86_in_ruleUnpivotTable3160 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_83_in_ruleUnpivotTable3178 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_67_in_ruleUnpivotTable3191 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleUnpivotTable3205 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotTable3225 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_rulePivotForClause_in_ruleUnpivotTable3246 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClause_in_ruleUnpivotTable3267 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleUnpivotTable3280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClause_in_entryRuleUnpivotInClause3314 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClause3324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleUnpivotInClause3377 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleUnpivotInClause3401 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_ruleUnpivotInClause3421 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleUnpivotInClause3434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArgs_in_entryRuleUnpivotInClauseArgs3468 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClauseArgs3478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3525 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleUnpivotInClauseArgs3548 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_ruleUnpivotInClauseArgs3568 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleUnpivotInClauseArg_in_entryRuleUnpivotInClauseArg3607 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnpivotInClauseArg3617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3663 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleUnpivotInClauseArg3677 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_rulePivotColumns_in_ruleUnpivotInClauseArg3697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotForClause_in_entryRulePivotForClause3734 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotForClause3744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_32_in_rulePivotForClause3782 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rulePivotForClause3804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotForClause3823 = new BitSet(new long[]{0x0200020100000000L,0x0020010000000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_ruleColumns_in_rulePivotForClause3844 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotForClause3856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotColumns_in_entryRulePivotColumns3892 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotColumns3902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotColumns3949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rulePivotColumns3968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_rulePivotCols_in_rulePivotColumns3989 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rulePivotColumns4001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCols_in_entryRulePivotCols4036 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotCols4046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotCols4093 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rulePivotCols4116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_rulePivotCol_in_rulePivotCols4136 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_rulePivotCol_in_entryRulePivotCol4175 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePivotCol4185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rulePivotCol4232 = new BitSet(new long[]{0x0000000000000002L,0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rulePivotCol4255 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rulePivotCol4275 = new BitSet(new long[]{0x0000000000000002L,0x0400000000000000L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull4314 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull4324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull4371 = new BitSet(new long[]{0x0000000000000002L,0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleTableFull4394 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleTableFull4414 = new BitSet(new long[]{0x0000000000000002L,0x0400000000000000L});
    public static final BitSet FOLLOW_ruleDbObjectNameAll_in_entryRuleDbObjectNameAll4453 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectNameAll4463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_ruleDbObjectNameAll4509 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_6_in_ruleDbObjectNameAll4522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleDbObjectNameAll4532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName4566 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectName4576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_ruleDbObjectName4621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns4655 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns4665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4712 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByColumns4735 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007020L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_ruleOrderByColumns4755 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull4794 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull4804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleOrderByColumnFull4851 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000800L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_in_ruleOrderByColumnFull4874 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000800L});
    public static final BitSet FOLLOW_KEYWORD_29_in_ruleOrderByColumnFull4901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_ruleOrderByColumnFull4929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns4979 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumns4989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns5036 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleGroupByColumns5059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000007020L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_ruleGroupByColumns5079 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull5118 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumnFull5128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleGroupByColumnFull5174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleGroupByColumnFull5201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_in_ruleGroupByColumnFull5224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_entryRuleFullExpression5264 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullExpression5274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleFullExpression5321 = new BitSet(new long[]{0x0000000000000002L,0x0004000200000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_ruleFullExpression5351 = new BitSet(new long[]{0x0000000000000002L,0x0004000200000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond5389 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragmentSecond5399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleExpressionFragmentSecond5446 = new BitSet(new long[]{0x0224020000800000L,0x0021080000600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_22_in_ruleExpressionFragmentSecond5474 = new BitSet(new long[]{0x0224020000800000L,0x0021080000600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_ruleExpressionFragmentSecond5509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragmentSecond5533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment5573 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragment5583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_ruleExpressionFragment5629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExpressionFragment5656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleExpressionFragment5685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression__in_ruleExpressionFragment5704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExpressionFragment5730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_ruleExpressionFragment5762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExistsOperator_in_ruleExpressionFragment5789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup5824 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionGroup5834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_48_in_ruleExpressionGroup5889 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_47_in_ruleExpressionGroup5917 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleExpressionGroup5945 = new BitSet(new long[]{0x0224020000800000L,0x0021080000600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleExpressionGroup5965 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleExpressionGroup5978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression6012 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression6022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleXExpression6060 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression6081 = new BitSet(new long[]{0x480004E860000000L,0x0001000000080004L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression6101 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression6114 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleXExpression6134 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpression6148 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression6168 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleXExpression6183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression__in_entryRuleXExpression_6217 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression_6227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_ruleXExpression_6265 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_11_in_ruleXExpression_6286 = new BitSet(new long[]{0x480004E860000000L,0x0001000000080004L});
    public static final BitSet FOLLOW_ruleXFunction_in_ruleXExpression_6306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleXExpression_6319 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleXExpression_6339 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_KEYWORD_12_in_ruleXExpression_6353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_ruleXExpression_6373 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_13_in_ruleXExpression_6388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams6422 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionParams6432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams6479 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleXExpressionParams6502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ruleJRParameter_in_ruleXExpressionParams6522 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleJRParameter_in_entryRuleJRParameter6561 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRParameter6571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJRParameter6612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression6651 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression6661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleExpression6707 = new BitSet(new long[]{0x0224128400940000L,0x70297C0000700400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_87_in_ruleExpression6729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_114_in_ruleExpression6757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_ruleExpression6798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExistsOperator_in_ruleExpression6825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_ruleExpression6852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_ruleExpression6879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_ruleExpression6906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_entryRuleComparison6942 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComparison6952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_10_in_ruleComparison6998 = new BitSet(new long[]{0x0200020000000000L,0x0020000504000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_18_in_ruleComparison7026 = new BitSet(new long[]{0x0200020000000000L,0x0020000504000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_8_in_ruleComparison7054 = new BitSet(new long[]{0x0200020000000000L,0x0020000504000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_16_in_ruleComparison7082 = new BitSet(new long[]{0x0200020000000000L,0x0020000504000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_9_in_ruleComparison7110 = new BitSet(new long[]{0x0200020000000000L,0x0020000504000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_17_in_ruleComparison7138 = new BitSet(new long[]{0x0200020000000000L,0x0020000504000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_14_in_ruleComparison7166 = new BitSet(new long[]{0x0200020000000000L,0x0020000504000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_23_in_ruleComparison7194 = new BitSet(new long[]{0x0200020000000000L,0x0020000504000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_28_in_ruleComparison7229 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleComparison7257 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_52_in_ruleComparison7285 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperand_in_ruleComparison7321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_entryRuleLike7356 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLike7366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_46_in_ruleLike7412 = new BitSet(new long[]{0x0200000000000000L,0x0000000000000000L,0x00000000000048ECL});
    public static final BitSet FOLLOW_KEYWORD_96_in_ruleLike7440 = new BitSet(new long[]{0x0200000000000000L,0x0000000000000000L,0x00000000000048ECL});
    public static final BitSet FOLLOW_ruleLikeOperand_in_ruleLike7475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLikeOperand_in_entryRuleLikeOperand7510 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLikeOperand7520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleLikeOperand7566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleLikeOperand7593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_ruleLikeOperand7620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_ruleLikeOperand7647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_entryRuleBetween7682 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBetween7692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_82_in_ruleBetween7738 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_116_in_ruleBetween7766 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleBetween7801 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleBetween7814 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleBetween7834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_entryRuleInOperator7869 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInOperator7879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_78_in_ruleInOperator7934 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleInOperator7962 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleInOperator7998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandListGroup_in_ruleInOperator8025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExistsOperator_in_entryRuleExistsOperator8061 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExistsOperator8071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_108_in_ruleExistsOperator8126 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_75_in_ruleExistsOperator8154 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleExistsOperator8190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandListGroup_in_ruleExistsOperator8217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandListGroup_in_entryRuleOperandListGroup8253 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandListGroup8263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleOperandListGroup8301 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000FECL});
    public static final BitSet FOLLOW_ruleOperandList_in_ruleOperandListGroup8321 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOperandListGroup8334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_entryRuleOperandList8368 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandList8378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleOperandList8425 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOperandList8448 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000FECL});
    public static final BitSet FOLLOW_ruleScalarOperand_in_ruleOperandList8468 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleOperandGroup_in_entryRuleOperandGroup8507 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandGroup8517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOperandGroup8564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleOperandGroup8583 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOperandGroup8604 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOperandGroup8616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_entryRuleOperand8651 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperand8661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand8707 = new BitSet(new long[]{0x0000000000000002L,0x0A90000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_KEYWORD_3_in_ruleOperand8732 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_5_in_ruleOperand8761 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_24_in_ruleOperand8790 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperand8817 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_7_in_ruleOperand8846 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandFragment_in_ruleOperand8868 = new BitSet(new long[]{0x0000000000000002L,0x0A90000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment8905 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFragment8915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_ruleOperandFragment8961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_ruleOperandFragment8988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_ruleOperandFragment9015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_ruleOperandFragment9042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionExtract_in_ruleOperandFragment9069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_ruleOperandFragment9096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCASE_in_ruleOperandFragment9123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFunction_in_entryRuleOperandFunction9158 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFunction9168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFNAME_in_ruleOperandFunction9223 = new BitSet(new long[]{0x0200020100000000L,0x0060000100000400L,0x00000000000078FCL});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleOperandFunction9241 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_ruleOpFunctionArg_in_ruleOperandFunction9273 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOperandFunction9288 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_ruleFunctionAnalytical_in_ruleOperandFunction9308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionExtract_in_entryRuleFunctionExtract9344 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFunctionExtract9354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_84_in_ruleFunctionExtract9392 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleFunctionExtract9404 = new BitSet(new long[]{0x00908000816B0EC0L,0x0000001050008002L});
    public static final BitSet FOLLOW_ruleEXTRACT_VALUES_in_ruleFunctionExtract9424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_KEYWORD_39_in_ruleFunctionExtract9437 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleFunctionExtract9457 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleFunctionExtract9470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionAnalytical_in_entryRuleFunctionAnalytical9504 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFunctionAnalytical9514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_50_in_ruleFunctionAnalytical9552 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleFunctionAnalytical9564 = new BitSet(new long[]{0x0000001000002100L,0x0040000000000000L});
    public static final BitSet FOLLOW_ruleAnalyticClause_in_ruleFunctionAnalytical9584 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleFunctionAnalytical9597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticClause_in_entryRuleAnalyticClause9631 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticClause9641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQueryPartitionClause_in_ruleAnalyticClause9696 = new BitSet(new long[]{0x0000001000000102L});
    public static final BitSet FOLLOW_ruleOrderByClause_in_ruleAnalyticClause9719 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000040L});
    public static final BitSet FOLLOW_ruleWindowingClause_in_ruleAnalyticClause9740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClause_in_entryRuleWindowingClause9778 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClause9788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_51_in_ruleWindowingClause9827 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_70_in_ruleWindowingClause9845 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleWindowingClauseBetween_in_ruleWindowingClause9868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClause9895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseBetween_in_entryRuleWindowingClauseBetween9930 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseBetween9940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_82_in_ruleWindowingClauseBetween9978 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_ruleWindowingClauseBetween9998 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_27_in_ruleWindowingClauseBetween10011 = new BitSet(new long[]{0x0200028000004030L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandFollowing_in_ruleWindowingClauseBetween10031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandFollowing_in_entryRuleWindowingClauseOperandFollowing10066 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseOperandFollowing10076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_125_in_ruleWindowingClauseOperandFollowing10124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_110_in_ruleWindowingClauseOperandFollowing10142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandFollowing10169 = new BitSet(new long[]{0x000000000A000000L});
    public static final BitSet FOLLOW_KEYWORD_103_in_ruleWindowingClauseOperandFollowing10183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_101_in_ruleWindowingClauseOperandFollowing10201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWindowingClauseOperandPreceding_in_entryRuleWindowingClauseOperandPreceding10238 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWindowingClauseOperandPreceding10248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_126_in_ruleWindowingClauseOperandPreceding10296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_110_in_ruleWindowingClauseOperandPreceding10314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleWindowingClauseOperandPreceding10341 = new BitSet(new long[]{0x000000000A000000L});
    public static final BitSet FOLLOW_KEYWORD_103_in_ruleWindowingClauseOperandPreceding10355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_101_in_ruleWindowingClauseOperandPreceding10373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClause_in_entryRuleOrderByClause10410 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClause10420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_98_in_ruleOrderByClause10459 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_122_in_ruleOrderByClause10477 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOrderByClauseArgs_in_ruleOrderByClause10498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClauseArgs_in_entryRuleOrderByClauseArgs10533 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClauseArgs10543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs10590 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOrderByClauseArgs10613 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_ruleOrderByClauseArgs10633 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleOrderByClauseArg_in_entryRuleOrderByClauseArg10672 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByClauseArg10682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleOrderByClauseArg10728 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000808L});
    public static final BitSet FOLLOW_KEYWORD_29_in_ruleOrderByClauseArg10742 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_37_in_ruleOrderByClauseArg10760 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_67_in_ruleOrderByClauseArg10775 = new BitSet(new long[]{0x1000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_KEYWORD_60_in_ruleOrderByClauseArg10788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_43_in_ruleOrderByClauseArg10806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQueryPartitionClause_in_entryRuleQueryPartitionClause10843 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQueryPartitionClause10853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_117_in_ruleQueryPartitionClause10891 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause10912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleQueryPartitionClause10932 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_ruleQueryPartitionClause10953 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleQueryPartitionClause10965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArgs_in_entryRuleAnalyticExprArgs11001 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticExprArgs11011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs11058 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleAnalyticExprArgs11081 = new BitSet(new long[]{0x0200028000004020L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_ruleAnalyticExprArgs11101 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleAnalyticExprArg_in_entryRuleAnalyticExprArg11140 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalyticExprArg11150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleAnalyticExprArg11196 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_ruleAnalyticExprArg11217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArg_in_entryRuleOpFunctionArg11253 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArg11263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg11310 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOpFunctionArg11333 = new BitSet(new long[]{0x0200020100000000L,0x0020000100000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_ruleOpFunctionArg11353 = new BitSet(new long[]{0x0000000000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_ruleOpFunctionArgOperand_in_entryRuleOpFunctionArgOperand11392 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArgOperand11402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgAgregate_in_ruleOpFunctionArgOperand11449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionArgOperand11468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionCast_in_entryRuleOpFunctionCast11505 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionCast11515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_57_in_ruleOpFunctionCast11553 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleOpFunctionCast11573 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
    public static final BitSet FOLLOW_KEYWORD_19_in_ruleOpFunctionCast11586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOpFunctionCast11602 = new BitSet(new long[]{0x0000000000000000L,0x0060000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleOpFunctionCast11621 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOpFunctionCast11637 = new BitSet(new long[]{0x0000000000000000L,0x0140000000000000L});
    public static final BitSet FOLLOW_KEYWORD_4_in_ruleOpFunctionCast11656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleOpFunctionCast11672 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOpFunctionCast11692 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleOpFunctionCast11706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpFunctionArgAgregate_in_entryRuleOpFunctionArgAgregate11740 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpFunctionArgAgregate11750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_ruleOpFunctionArgAgregate11789 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_KEYWORD_94_in_ruleOpFunctionArgAgregate11807 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperand_in_ruleOpFunctionArgAgregate11829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment11863 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOperandFragment11873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_ruleXOperandFragment11919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_ruleXOperandFragment11946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarNumberOperand_in_ruleXOperandFragment11973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand12008 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameterOperand12018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRPARAM_in_ruleParameterOperand12059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand12098 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclamationParameterOperand12108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_JRNPARAM_in_ruleExclamationParameterOperand12149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand12188 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOperand12198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOperand12244 = new BitSet(new long[]{0x0000000000000002L,0x0000000080000000L});
    public static final BitSet FOLLOW_KEYWORD_25_in_ruleColumnOperand12263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand12310 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubQueryOperand12320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleSubQueryOperand12367 = new BitSet(new long[]{0x0100000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_ruleSelectQuery_in_ruleSubQueryOperand12387 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_2_in_ruleSubQueryOperand12400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand12434 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarOperand12444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleScalarOperand12490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarOperand12513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_ruleScalarOperand12541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIME_in_ruleScalarOperand12569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIMESTAMP_in_ruleScalarOperand12597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarNumberOperand_in_entryRuleScalarNumberOperand12637 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarNumberOperand12647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_in_ruleScalarNumberOperand12689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleScalarNumberOperand12717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_ruleScalarNumberOperand12745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_ruleScalarNumberOperand12777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCASE_in_entryRuleSQLCASE12812 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSQLCASE12822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_ruleSQLCASE12860 = new BitSet(new long[]{0x0224020000800000L,0x0021080020600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSQLCASE12880 = new BitSet(new long[]{0x0224020000800000L,0x0021080020600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleSQLCaseWhens_in_ruleSQLCASE12902 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_31_in_ruleSQLCASE12915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSQLCaseWhens_in_entryRuleSQLCaseWhens12949 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSQLCaseWhens12959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens13006 = new BitSet(new long[]{0x0224020000800002L,0x0021080020600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_ruleSQLCaseWhens13036 = new BitSet(new long[]{0x0224020000800002L,0x0021080020600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleSqlCaseWhen_in_entryRuleSqlCaseWhen13074 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSqlCaseWhen13084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_55_in_ruleSqlCaseWhen13122 = new BitSet(new long[]{0x0224020000800000L,0x0021080000600400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleFullExpression_in_ruleSqlCaseWhen13142 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_KEYWORD_53_in_ruleSqlCaseWhen13155 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleSqlCaseWhen13175 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_38_in_ruleSqlCaseWhen13189 = new BitSet(new long[]{0x0200020000000000L,0x0020000000000400L,0x00000000000078ECL});
    public static final BitSet FOLLOW_ruleOperandGroup_in_ruleSqlCaseWhen13209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_entryRuleJoinType13247 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJoinType13258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_88_in_ruleJoinType13297 = new BitSet(new long[]{0x2400000000001000L,0x0000000000054080L});
    public static final BitSet FOLLOW_KEYWORD_61_in_ruleJoinType13313 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_KEYWORD_44_in_ruleJoinType13334 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010010L});
    public static final BitSet FOLLOW_KEYWORD_71_in_ruleJoinType13353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010010L});
    public static final BitSet FOLLOW_KEYWORD_40_in_ruleJoinType13372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010010L});
    public static final BitSet FOLLOW_KEYWORD_68_in_ruleJoinType13387 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_KEYWORD_58_in_ruleJoinType13409 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_KEYWORD_119_in_ruleJoinType13428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_KEYWORD_42_in_ruleJoinType13443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBID_in_entryRuleDBID13483 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBID13494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDBID13534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DBNAME_in_ruleDBID13560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDBID13586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_entryRuleStringOperand13631 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringOperand13642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING__in_ruleStringOperand13681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFNAME_in_entryRuleFNAME13725 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFNAME13736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFNAME13776 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_1_in_ruleFNAME13794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerValue_in_entryRuleIntegerValue13833 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerValue13843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerValue13884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_115_in_ruleEXTRACT_VALUES13941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_80_in_ruleEXTRACT_VALUES13963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_77_in_ruleEXTRACT_VALUES13985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_41_in_ruleEXTRACT_VALUES14007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_30_in_ruleEXTRACT_VALUES14029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_54_in_ruleEXTRACT_VALUES14051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_65_in_ruleEXTRACT_VALUES14073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_90_in_ruleEXTRACT_VALUES14095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_56_in_ruleEXTRACT_VALUES14117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_124_in_ruleEXTRACT_VALUES14139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_123_in_ruleEXTRACT_VALUES14161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_118_in_ruleEXTRACT_VALUES14183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_121_in_ruleEXTRACT_VALUES14205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_113_in_ruleEXTRACT_VALUES14227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_112_in_ruleEXTRACT_VALUES14249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_120_in_ruleEXTRACT_VALUES14271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_107_in_ruleEXTRACT_VALUES14293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_106_in_ruleEXTRACT_VALUES14315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_93_in_ruleEXTRACT_VALUES14337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_109_in_ruleEXTRACT_VALUES14359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_ruleXFunction14409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_66_in_ruleXFunction14431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_59_in_ruleXFunction14453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_97_in_ruleXFunction14475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_45_in_ruleXFunction14497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_85_in_ruleXFunction14519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_62_in_ruleXFunction14541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_100_in_ruleXFunction14563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_82_in_ruleXFunction14585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_105_in_ruleXFunction14607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_99_in_ruleXFunction14629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_92_in_ruleXFunction14651 = new BitSet(new long[]{0x0000000000000002L});

}