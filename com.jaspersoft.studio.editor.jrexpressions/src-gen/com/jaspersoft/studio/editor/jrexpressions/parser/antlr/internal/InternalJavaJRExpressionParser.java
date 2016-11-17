/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.jaspersoft.studio.editor.jrexpressions.services.JavaJRExpressionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalJavaJRExpressionParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_BRACED_IDENTIFIER", "RULE_INT", "RULE_LONG", "RULE_FLOAT", "RULE_DOUBLE", "RULE_CHAR", "RULE_STRING", "RULE_ID", "RULE_INTEGERNUMBER", "RULE_LONGSUFFIX", "RULE_NONINTEGERNUMBER", "RULE_FLOATSUFFIX", "RULE_DOUBLESUFFIX", "RULE_ESCAPESEQUENCE", "RULE_HEXDIGIT", "RULE_HEXPREFIX", "RULE_EXPONENT", "RULE_EXPOBJIDENTIFIER", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'?'", "':'", "'||'", "'&&'", "'=='", "'!='", "'instanceof'", "'+'", "'-'", "'*'", "'/'", "'%'", "'~'", "'!'", "'.'", "'$F'", "'$P'", "'$V'", "'$R'", "'new'", "'['", "']'", "'false'", "'true'", "'null'", "'('", "')'", "'{'", "'}'", "','", "'<'", "'>'", "'extends'", "'super'", "'boolean'", "'char'", "'byte'", "'short'", "'int'", "'long'", "'float'", "'double'", "'<='", "'>='"
    };
    public static final int T__50=50;
    public static final int RULE_FLOATSUFFIX=15;
    public static final int T__59=59;
    public static final int RULE_LONGSUFFIX=13;
    public static final int T__55=55;
    public static final int RULE_CHAR=9;
    public static final int T__56=56;
    public static final int RULE_ESCAPESEQUENCE=17;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int RULE_HEXPREFIX=19;
    public static final int T__60=60;
    public static final int RULE_HEXDIGIT=18;
    public static final int T__61=61;
    public static final int RULE_ID=11;
    public static final int RULE_NONINTEGERNUMBER=14;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=22;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int RULE_DOUBLESUFFIX=16;
    public static final int RULE_BRACED_IDENTIFIER=4;
    public static final int RULE_EXPOBJIDENTIFIER=21;
    public static final int RULE_STRING=10;
    public static final int RULE_SL_COMMENT=23;
    public static final int T__37=37;
    public static final int RULE_DOUBLE=8;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=24;
    public static final int RULE_ANY_OTHER=25;
    public static final int RULE_INTEGERNUMBER=12;
    public static final int RULE_EXPONENT=20;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int RULE_FLOAT=7;
    public static final int T__46=46;
    public static final int RULE_LONG=6;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalJavaJRExpressionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalJavaJRExpressionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalJavaJRExpressionParser.tokenNames; }
    public String getGrammarFileName() { return "InternalJavaJRExpression.g"; }



     	private JavaJRExpressionGrammarAccess grammarAccess;
     	
        public InternalJavaJRExpressionParser(TokenStream input, JavaJRExpressionGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "JRExpressionModel";	
       	}
       	
       	@Override
       	protected JavaJRExpressionGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleJRExpressionModel"
    // InternalJavaJRExpression.g:67:1: entryRuleJRExpressionModel returns [EObject current=null] : iv_ruleJRExpressionModel= ruleJRExpressionModel EOF ;
    public final EObject entryRuleJRExpressionModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRExpressionModel = null;


        try {
            // InternalJavaJRExpression.g:68:2: (iv_ruleJRExpressionModel= ruleJRExpressionModel EOF )
            // InternalJavaJRExpression.g:69:2: iv_ruleJRExpressionModel= ruleJRExpressionModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRExpressionModelRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJRExpressionModel=ruleJRExpressionModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRExpressionModel; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJRExpressionModel"


    // $ANTLR start "ruleJRExpressionModel"
    // InternalJavaJRExpression.g:76:1: ruleJRExpressionModel returns [EObject current=null] : ( (lv_expression_0_0= ruleJasperReportsExpression ) )? ;
    public final EObject ruleJRExpressionModel() throws RecognitionException {
        EObject current = null;

        EObject lv_expression_0_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:79:28: ( ( (lv_expression_0_0= ruleJasperReportsExpression ) )? )
            // InternalJavaJRExpression.g:80:1: ( (lv_expression_0_0= ruleJasperReportsExpression ) )?
            {
            // InternalJavaJRExpression.g:80:1: ( (lv_expression_0_0= ruleJasperReportsExpression ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=RULE_INT && LA1_0<=RULE_ID)||(LA1_0>=33 && LA1_0<=34)||(LA1_0>=38 && LA1_0<=39)||(LA1_0>=41 && LA1_0<=45)||(LA1_0>=48 && LA1_0<=51)) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalJavaJRExpression.g:81:1: (lv_expression_0_0= ruleJasperReportsExpression )
                    {
                    // InternalJavaJRExpression.g:81:1: (lv_expression_0_0= ruleJasperReportsExpression )
                    // InternalJavaJRExpression.g:82:3: lv_expression_0_0= ruleJasperReportsExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJRExpressionModelAccess().getExpressionJasperReportsExpressionParserRuleCall_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_expression_0_0=ruleJasperReportsExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getJRExpressionModelRule());
                      	        }
                             		set(
                             			current, 
                             			"expression",
                              		lv_expression_0_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JasperReportsExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJRExpressionModel"


    // $ANTLR start "entryRuleJasperReportsExpression"
    // InternalJavaJRExpression.g:106:1: entryRuleJasperReportsExpression returns [EObject current=null] : iv_ruleJasperReportsExpression= ruleJasperReportsExpression EOF ;
    public final EObject entryRuleJasperReportsExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJasperReportsExpression = null;


        try {
            // InternalJavaJRExpression.g:107:2: (iv_ruleJasperReportsExpression= ruleJasperReportsExpression EOF )
            // InternalJavaJRExpression.g:108:2: iv_ruleJasperReportsExpression= ruleJasperReportsExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJasperReportsExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJasperReportsExpression=ruleJasperReportsExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJasperReportsExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJasperReportsExpression"


    // $ANTLR start "ruleJasperReportsExpression"
    // InternalJavaJRExpression.g:115:1: ruleJasperReportsExpression returns [EObject current=null] : this_ConditionalExpression_0= ruleConditionalExpression ;
    public final EObject ruleJasperReportsExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ConditionalExpression_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:118:28: (this_ConditionalExpression_0= ruleConditionalExpression )
            // InternalJavaJRExpression.g:120:5: this_ConditionalExpression_0= ruleConditionalExpression
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getJasperReportsExpressionAccess().getConditionalExpressionParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_2);
            this_ConditionalExpression_0=ruleConditionalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ConditionalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJasperReportsExpression"


    // $ANTLR start "entryRuleConditionalExpression"
    // InternalJavaJRExpression.g:136:1: entryRuleConditionalExpression returns [EObject current=null] : iv_ruleConditionalExpression= ruleConditionalExpression EOF ;
    public final EObject entryRuleConditionalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalExpression = null;


        try {
            // InternalJavaJRExpression.g:137:2: (iv_ruleConditionalExpression= ruleConditionalExpression EOF )
            // InternalJavaJRExpression.g:138:2: iv_ruleConditionalExpression= ruleConditionalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConditionalExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConditionalExpression=ruleConditionalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConditionalExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalExpression"


    // $ANTLR start "ruleConditionalExpression"
    // InternalJavaJRExpression.g:145:1: ruleConditionalExpression returns [EObject current=null] : (this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )? ) ;
    public final EObject ruleConditionalExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ConditionalOrExpression_0 = null;

        EObject lv_trueStatement_3_0 = null;

        EObject lv_falseStatement_5_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:148:28: ( (this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )? ) )
            // InternalJavaJRExpression.g:149:1: (this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )? )
            {
            // InternalJavaJRExpression.g:149:1: (this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )? )
            // InternalJavaJRExpression.g:150:5: this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getConditionalExpressionAccess().getConditionalOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_3);
            this_ConditionalOrExpression_0=ruleConditionalOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ConditionalOrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:158:1: ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==26) ) {
                int LA2_1 = input.LA(2);

                if ( (synpred1_InternalJavaJRExpression()) ) {
                    alt2=1;
                }
            }
            switch (alt2) {
                case 1 :
                    // InternalJavaJRExpression.g:158:2: ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) )
                    {
                    // InternalJavaJRExpression.g:158:2: ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) )
                    // InternalJavaJRExpression.g:158:3: ( ( () '?' ) )=> ( () otherlv_2= '?' )
                    {
                    // InternalJavaJRExpression.g:160:5: ( () otherlv_2= '?' )
                    // InternalJavaJRExpression.g:160:6: () otherlv_2= '?'
                    {
                    // InternalJavaJRExpression.g:160:6: ()
                    // InternalJavaJRExpression.g:161:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getConditionalExpressionAccess().getTestExpressionConditionAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_2=(Token)match(input,26,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getConditionalExpressionAccess().getQuestionMarkKeyword_1_0_0_1());
                          
                    }

                    }


                    }

                    // InternalJavaJRExpression.g:170:3: ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) )
                    // InternalJavaJRExpression.g:171:1: (lv_trueStatement_3_0= ruleJasperReportsExpression )
                    {
                    // InternalJavaJRExpression.g:171:1: (lv_trueStatement_3_0= ruleJasperReportsExpression )
                    // InternalJavaJRExpression.g:172:3: lv_trueStatement_3_0= ruleJasperReportsExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getConditionalExpressionAccess().getTrueStatementJasperReportsExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_5);
                    lv_trueStatement_3_0=ruleJasperReportsExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getConditionalExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"trueStatement",
                              		lv_trueStatement_3_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JasperReportsExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,27,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getConditionalExpressionAccess().getColonKeyword_1_2());
                          
                    }
                    // InternalJavaJRExpression.g:192:1: ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) )
                    // InternalJavaJRExpression.g:193:1: (lv_falseStatement_5_0= ruleJasperReportsExpression )
                    {
                    // InternalJavaJRExpression.g:193:1: (lv_falseStatement_5_0= ruleJasperReportsExpression )
                    // InternalJavaJRExpression.g:194:3: lv_falseStatement_5_0= ruleJasperReportsExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getConditionalExpressionAccess().getFalseStatementJasperReportsExpressionParserRuleCall_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_falseStatement_5_0=ruleJasperReportsExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getConditionalExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"falseStatement",
                              		lv_falseStatement_5_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JasperReportsExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalExpression"


    // $ANTLR start "entryRuleConditionalOrExpression"
    // InternalJavaJRExpression.g:218:1: entryRuleConditionalOrExpression returns [EObject current=null] : iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF ;
    public final EObject entryRuleConditionalOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalOrExpression = null;


        try {
            // InternalJavaJRExpression.g:219:2: (iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF )
            // InternalJavaJRExpression.g:220:2: iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConditionalOrExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConditionalOrExpression=ruleConditionalOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConditionalOrExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalOrExpression"


    // $ANTLR start "ruleConditionalOrExpression"
    // InternalJavaJRExpression.g:227:1: ruleConditionalOrExpression returns [EObject current=null] : (this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )* ) ;
    public final EObject ruleConditionalOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_ConditionalAndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:230:28: ( (this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )* ) )
            // InternalJavaJRExpression.g:231:1: (this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )* )
            {
            // InternalJavaJRExpression.g:231:1: (this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )* )
            // InternalJavaJRExpression.g:232:5: this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getConditionalOrExpressionAccess().getConditionalAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_6);
            this_ConditionalAndExpression_0=ruleConditionalAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ConditionalAndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:240:1: ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==28) ) {
                    int LA3_2 = input.LA(2);

                    if ( (synpred2_InternalJavaJRExpression()) ) {
                        alt3=1;
                    }


                }


                switch (alt3) {
            	case 1 :
            	    // InternalJavaJRExpression.g:240:2: ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) )
            	    {
            	    // InternalJavaJRExpression.g:240:2: ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) )
            	    // InternalJavaJRExpression.g:240:3: ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) )
            	    {
            	    // InternalJavaJRExpression.g:247:6: ( () ( (lv_op_2_0= '||' ) ) )
            	    // InternalJavaJRExpression.g:247:7: () ( (lv_op_2_0= '||' ) )
            	    {
            	    // InternalJavaJRExpression.g:247:7: ()
            	    // InternalJavaJRExpression.g:248:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getConditionalOrExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalJavaJRExpression.g:253:2: ( (lv_op_2_0= '||' ) )
            	    // InternalJavaJRExpression.g:254:1: (lv_op_2_0= '||' )
            	    {
            	    // InternalJavaJRExpression.g:254:1: (lv_op_2_0= '||' )
            	    // InternalJavaJRExpression.g:255:3: lv_op_2_0= '||'
            	    {
            	    lv_op_2_0=(Token)match(input,28,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_op_2_0, grammarAccess.getConditionalOrExpressionAccess().getOpVerticalLineVerticalLineKeyword_1_0_0_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getConditionalOrExpressionRule());
            	      	        }
            	             		setWithLastConsumed(current, "op", lv_op_2_0, "||");
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalJavaJRExpression.g:268:4: ( (lv_right_3_0= ruleConditionalAndExpression ) )
            	    // InternalJavaJRExpression.g:269:1: (lv_right_3_0= ruleConditionalAndExpression )
            	    {
            	    // InternalJavaJRExpression.g:269:1: (lv_right_3_0= ruleConditionalAndExpression )
            	    // InternalJavaJRExpression.g:270:3: lv_right_3_0= ruleConditionalAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getConditionalOrExpressionAccess().getRightConditionalAndExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_6);
            	    lv_right_3_0=ruleConditionalAndExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getConditionalOrExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.ConditionalAndExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalOrExpression"


    // $ANTLR start "entryRuleConditionalAndExpression"
    // InternalJavaJRExpression.g:294:1: entryRuleConditionalAndExpression returns [EObject current=null] : iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF ;
    public final EObject entryRuleConditionalAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalAndExpression = null;


        try {
            // InternalJavaJRExpression.g:295:2: (iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF )
            // InternalJavaJRExpression.g:296:2: iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConditionalAndExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConditionalAndExpression=ruleConditionalAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConditionalAndExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalAndExpression"


    // $ANTLR start "ruleConditionalAndExpression"
    // InternalJavaJRExpression.g:303:1: ruleConditionalAndExpression returns [EObject current=null] : (this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )* ) ;
    public final EObject ruleConditionalAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_EqualityExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:306:28: ( (this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )* ) )
            // InternalJavaJRExpression.g:307:1: (this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )* )
            {
            // InternalJavaJRExpression.g:307:1: (this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )* )
            // InternalJavaJRExpression.g:308:5: this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getConditionalAndExpressionAccess().getEqualityExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_7);
            this_EqualityExpression_0=ruleEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_EqualityExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:316:1: ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==29) ) {
                    int LA4_2 = input.LA(2);

                    if ( (synpred3_InternalJavaJRExpression()) ) {
                        alt4=1;
                    }


                }


                switch (alt4) {
            	case 1 :
            	    // InternalJavaJRExpression.g:316:2: ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) )
            	    {
            	    // InternalJavaJRExpression.g:316:2: ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) )
            	    // InternalJavaJRExpression.g:316:3: ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) )
            	    {
            	    // InternalJavaJRExpression.g:323:6: ( () ( (lv_op_2_0= '&&' ) ) )
            	    // InternalJavaJRExpression.g:323:7: () ( (lv_op_2_0= '&&' ) )
            	    {
            	    // InternalJavaJRExpression.g:323:7: ()
            	    // InternalJavaJRExpression.g:324:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getConditionalAndExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalJavaJRExpression.g:329:2: ( (lv_op_2_0= '&&' ) )
            	    // InternalJavaJRExpression.g:330:1: (lv_op_2_0= '&&' )
            	    {
            	    // InternalJavaJRExpression.g:330:1: (lv_op_2_0= '&&' )
            	    // InternalJavaJRExpression.g:331:3: lv_op_2_0= '&&'
            	    {
            	    lv_op_2_0=(Token)match(input,29,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_op_2_0, grammarAccess.getConditionalAndExpressionAccess().getOpAmpersandAmpersandKeyword_1_0_0_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getConditionalAndExpressionRule());
            	      	        }
            	             		setWithLastConsumed(current, "op", lv_op_2_0, "&&");
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalJavaJRExpression.g:344:4: ( (lv_right_3_0= ruleEqualityExpression ) )
            	    // InternalJavaJRExpression.g:345:1: (lv_right_3_0= ruleEqualityExpression )
            	    {
            	    // InternalJavaJRExpression.g:345:1: (lv_right_3_0= ruleEqualityExpression )
            	    // InternalJavaJRExpression.g:346:3: lv_right_3_0= ruleEqualityExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getConditionalAndExpressionAccess().getRightEqualityExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_7);
            	    lv_right_3_0=ruleEqualityExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getConditionalAndExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.EqualityExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalAndExpression"


    // $ANTLR start "entryRuleEqualityExpression"
    // InternalJavaJRExpression.g:370:1: entryRuleEqualityExpression returns [EObject current=null] : iv_ruleEqualityExpression= ruleEqualityExpression EOF ;
    public final EObject entryRuleEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEqualityExpression = null;


        try {
            // InternalJavaJRExpression.g:371:2: (iv_ruleEqualityExpression= ruleEqualityExpression EOF )
            // InternalJavaJRExpression.g:372:2: iv_ruleEqualityExpression= ruleEqualityExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEqualityExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleEqualityExpression=ruleEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEqualityExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEqualityExpression"


    // $ANTLR start "ruleEqualityExpression"
    // InternalJavaJRExpression.g:379:1: ruleEqualityExpression returns [EObject current=null] : (this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )* ) ;
    public final EObject ruleEqualityExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_InstanceofExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:382:28: ( (this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )* ) )
            // InternalJavaJRExpression.g:383:1: (this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )* )
            {
            // InternalJavaJRExpression.g:383:1: (this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )* )
            // InternalJavaJRExpression.g:384:5: this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getEqualityExpressionAccess().getInstanceofExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_8);
            this_InstanceofExpression_0=ruleInstanceofExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_InstanceofExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:392:1: ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==30) ) {
                    int LA6_2 = input.LA(2);

                    if ( (synpred4_InternalJavaJRExpression()) ) {
                        alt6=1;
                    }


                }
                else if ( (LA6_0==31) ) {
                    int LA6_3 = input.LA(2);

                    if ( (synpred4_InternalJavaJRExpression()) ) {
                        alt6=1;
                    }


                }


                switch (alt6) {
            	case 1 :
            	    // InternalJavaJRExpression.g:392:2: ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) )
            	    {
            	    // InternalJavaJRExpression.g:392:2: ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) )
            	    // InternalJavaJRExpression.g:392:3: ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) )
            	    {
            	    // InternalJavaJRExpression.g:405:6: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) )
            	    // InternalJavaJRExpression.g:405:7: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    {
            	    // InternalJavaJRExpression.g:405:7: ()
            	    // InternalJavaJRExpression.g:406:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getEqualityExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalJavaJRExpression.g:411:2: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // InternalJavaJRExpression.g:412:1: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // InternalJavaJRExpression.g:412:1: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // InternalJavaJRExpression.g:413:1: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // InternalJavaJRExpression.g:413:1: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    int alt5=2;
            	    int LA5_0 = input.LA(1);

            	    if ( (LA5_0==30) ) {
            	        alt5=1;
            	    }
            	    else if ( (LA5_0==31) ) {
            	        alt5=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 5, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt5) {
            	        case 1 :
            	            // InternalJavaJRExpression.g:414:3: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,30,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_1, grammarAccess.getEqualityExpressionAccess().getOpEqualsSignEqualsSignKeyword_1_0_0_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getEqualityExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalJavaJRExpression.g:426:8: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,31,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_2, grammarAccess.getEqualityExpressionAccess().getOpExclamationMarkEqualsSignKeyword_1_0_0_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getEqualityExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }


            	    }

            	    // InternalJavaJRExpression.g:441:4: ( (lv_right_3_0= ruleInstanceofExpression ) )
            	    // InternalJavaJRExpression.g:442:1: (lv_right_3_0= ruleInstanceofExpression )
            	    {
            	    // InternalJavaJRExpression.g:442:1: (lv_right_3_0= ruleInstanceofExpression )
            	    // InternalJavaJRExpression.g:443:3: lv_right_3_0= ruleInstanceofExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getEqualityExpressionAccess().getRightInstanceofExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_8);
            	    lv_right_3_0=ruleInstanceofExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getEqualityExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.InstanceofExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEqualityExpression"


    // $ANTLR start "entryRuleInstanceofExpression"
    // InternalJavaJRExpression.g:467:1: entryRuleInstanceofExpression returns [EObject current=null] : iv_ruleInstanceofExpression= ruleInstanceofExpression EOF ;
    public final EObject entryRuleInstanceofExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstanceofExpression = null;


        try {
            // InternalJavaJRExpression.g:468:2: (iv_ruleInstanceofExpression= ruleInstanceofExpression EOF )
            // InternalJavaJRExpression.g:469:2: iv_ruleInstanceofExpression= ruleInstanceofExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInstanceofExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInstanceofExpression=ruleInstanceofExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInstanceofExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstanceofExpression"


    // $ANTLR start "ruleInstanceofExpression"
    // InternalJavaJRExpression.g:476:1: ruleInstanceofExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )? ) ;
    public final EObject ruleInstanceofExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:479:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )? ) )
            // InternalJavaJRExpression.g:480:1: (this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )? )
            {
            // InternalJavaJRExpression.g:480:1: (this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )? )
            // InternalJavaJRExpression.g:481:5: this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInstanceofExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_9);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:489:1: ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==32) ) {
                int LA7_1 = input.LA(2);

                if ( (synpred5_InternalJavaJRExpression()) ) {
                    alt7=1;
                }
            }
            switch (alt7) {
                case 1 :
                    // InternalJavaJRExpression.g:489:2: ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) )
                    {
                    // InternalJavaJRExpression.g:489:2: ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) )
                    // InternalJavaJRExpression.g:489:3: ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) )
                    {
                    // InternalJavaJRExpression.g:496:6: ( () ( (lv_op_2_0= 'instanceof' ) ) )
                    // InternalJavaJRExpression.g:496:7: () ( (lv_op_2_0= 'instanceof' ) )
                    {
                    // InternalJavaJRExpression.g:496:7: ()
                    // InternalJavaJRExpression.g:497:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getInstanceofExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    // InternalJavaJRExpression.g:502:2: ( (lv_op_2_0= 'instanceof' ) )
                    // InternalJavaJRExpression.g:503:1: (lv_op_2_0= 'instanceof' )
                    {
                    // InternalJavaJRExpression.g:503:1: (lv_op_2_0= 'instanceof' )
                    // InternalJavaJRExpression.g:504:3: lv_op_2_0= 'instanceof'
                    {
                    lv_op_2_0=(Token)match(input,32,FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_op_2_0, grammarAccess.getInstanceofExpressionAccess().getOpInstanceofKeyword_1_0_0_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getInstanceofExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "op", lv_op_2_0, "instanceof");
                      	    
                    }

                    }


                    }


                    }


                    }

                    // InternalJavaJRExpression.g:517:4: ( (lv_right_3_0= ruleType ) )
                    // InternalJavaJRExpression.g:518:1: (lv_right_3_0= ruleType )
                    {
                    // InternalJavaJRExpression.g:518:1: (lv_right_3_0= ruleType )
                    // InternalJavaJRExpression.g:519:3: lv_right_3_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getInstanceofExpressionAccess().getRightTypeParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_right_3_0=ruleType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInstanceofExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.Type");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInstanceofExpression"


    // $ANTLR start "entryRuleRelationalExpression"
    // InternalJavaJRExpression.g:543:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // InternalJavaJRExpression.g:544:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // InternalJavaJRExpression.g:545:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelationalExpression"


    // $ANTLR start "ruleRelationalExpression"
    // InternalJavaJRExpression.g:552:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
    public final EObject ruleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject this_AdditiveExpression_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:555:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // InternalJavaJRExpression.g:556:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // InternalJavaJRExpression.g:556:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // InternalJavaJRExpression.g:557:5: this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_11);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:565:1: ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop8:
            do {
                int alt8=2;
                switch ( input.LA(1) ) {
                case 68:
                    {
                    int LA8_2 = input.LA(2);

                    if ( (synpred6_InternalJavaJRExpression()) ) {
                        alt8=1;
                    }


                    }
                    break;
                case 56:
                    {
                    int LA8_3 = input.LA(2);

                    if ( (synpred6_InternalJavaJRExpression()) ) {
                        alt8=1;
                    }


                    }
                    break;
                case 69:
                    {
                    int LA8_4 = input.LA(2);

                    if ( (synpred6_InternalJavaJRExpression()) ) {
                        alt8=1;
                    }


                    }
                    break;
                case 57:
                    {
                    int LA8_5 = input.LA(2);

                    if ( (synpred6_InternalJavaJRExpression()) ) {
                        alt8=1;
                    }


                    }
                    break;

                }

                switch (alt8) {
            	case 1 :
            	    // InternalJavaJRExpression.g:565:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // InternalJavaJRExpression.g:565:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) )
            	    // InternalJavaJRExpression.g:565:3: ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
            	    {
            	    // InternalJavaJRExpression.g:570:6: ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
            	    // InternalJavaJRExpression.g:570:7: () ( (lv_op_2_0= ruleRelationalOp ) )
            	    {
            	    // InternalJavaJRExpression.g:570:7: ()
            	    // InternalJavaJRExpression.g:571:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getRelationalExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalJavaJRExpression.g:576:2: ( (lv_op_2_0= ruleRelationalOp ) )
            	    // InternalJavaJRExpression.g:577:1: (lv_op_2_0= ruleRelationalOp )
            	    {
            	    // InternalJavaJRExpression.g:577:1: (lv_op_2_0= ruleRelationalOp )
            	    // InternalJavaJRExpression.g:578:3: lv_op_2_0= ruleRelationalOp
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getOpRelationalOpParserRuleCall_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_4);
            	    lv_op_2_0=ruleRelationalOp();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getRelationalExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"op",
            	              		lv_op_2_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.RelationalOp");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalJavaJRExpression.g:594:4: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // InternalJavaJRExpression.g:595:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // InternalJavaJRExpression.g:595:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // InternalJavaJRExpression.g:596:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_11);
            	    lv_right_3_0=ruleAdditiveExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getRelationalExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.AdditiveExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelationalExpression"


    // $ANTLR start "entryRuleAdditiveExpression"
    // InternalJavaJRExpression.g:620:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // InternalJavaJRExpression.g:621:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // InternalJavaJRExpression.g:622:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdditiveExpression"


    // $ANTLR start "ruleAdditiveExpression"
    // InternalJavaJRExpression.g:629:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:632:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // InternalJavaJRExpression.g:633:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // InternalJavaJRExpression.g:633:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )* )
            // InternalJavaJRExpression.g:634:5: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_12);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:642:1: ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==33) ) {
                    int LA10_2 = input.LA(2);

                    if ( (synpred7_InternalJavaJRExpression()) ) {
                        alt10=1;
                    }


                }
                else if ( (LA10_0==34) ) {
                    int LA10_3 = input.LA(2);

                    if ( (synpred7_InternalJavaJRExpression()) ) {
                        alt10=1;
                    }


                }


                switch (alt10) {
            	case 1 :
            	    // InternalJavaJRExpression.g:642:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // InternalJavaJRExpression.g:642:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) )
            	    // InternalJavaJRExpression.g:642:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    {
            	    // InternalJavaJRExpression.g:655:6: ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    // InternalJavaJRExpression.g:655:7: () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    {
            	    // InternalJavaJRExpression.g:655:7: ()
            	    // InternalJavaJRExpression.g:656:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAdditiveExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalJavaJRExpression.g:661:2: ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    // InternalJavaJRExpression.g:662:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    {
            	    // InternalJavaJRExpression.g:662:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    // InternalJavaJRExpression.g:663:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    {
            	    // InternalJavaJRExpression.g:663:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    int alt9=2;
            	    int LA9_0 = input.LA(1);

            	    if ( (LA9_0==33) ) {
            	        alt9=1;
            	    }
            	    else if ( (LA9_0==34) ) {
            	        alt9=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 9, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt9) {
            	        case 1 :
            	            // InternalJavaJRExpression.g:664:3: lv_op_2_1= '+'
            	            {
            	            lv_op_2_1=(Token)match(input,33,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_1, grammarAccess.getAdditiveExpressionAccess().getOpPlusSignKeyword_1_0_0_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getAdditiveExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalJavaJRExpression.g:676:8: lv_op_2_2= '-'
            	            {
            	            lv_op_2_2=(Token)match(input,34,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_2, grammarAccess.getAdditiveExpressionAccess().getOpHyphenMinusKeyword_1_0_0_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getAdditiveExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }


            	    }

            	    // InternalJavaJRExpression.g:691:4: ( (lv_right_3_0= ruleMultiplicativeExpression ) )
            	    // InternalJavaJRExpression.g:692:1: (lv_right_3_0= ruleMultiplicativeExpression )
            	    {
            	    // InternalJavaJRExpression.g:692:1: (lv_right_3_0= ruleMultiplicativeExpression )
            	    // InternalJavaJRExpression.g:693:3: lv_right_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getRightMultiplicativeExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_12);
            	    lv_right_3_0=ruleMultiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getAdditiveExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.MultiplicativeExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdditiveExpression"


    // $ANTLR start "entryRuleMultiplicativeExpression"
    // InternalJavaJRExpression.g:717:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // InternalJavaJRExpression.g:718:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // InternalJavaJRExpression.g:719:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultiplicativeExpression"


    // $ANTLR start "ruleMultiplicativeExpression"
    // InternalJavaJRExpression.g:726:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        EObject this_UnaryExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:729:28: ( (this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )* ) )
            // InternalJavaJRExpression.g:730:1: (this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )* )
            {
            // InternalJavaJRExpression.g:730:1: (this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )* )
            // InternalJavaJRExpression.g:731:5: this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_13);
            this_UnaryExpression_0=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:739:1: ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )*
            loop12:
            do {
                int alt12=2;
                switch ( input.LA(1) ) {
                case 35:
                    {
                    int LA12_2 = input.LA(2);

                    if ( (synpred8_InternalJavaJRExpression()) ) {
                        alt12=1;
                    }


                    }
                    break;
                case 36:
                    {
                    int LA12_3 = input.LA(2);

                    if ( (synpred8_InternalJavaJRExpression()) ) {
                        alt12=1;
                    }


                    }
                    break;
                case 37:
                    {
                    int LA12_4 = input.LA(2);

                    if ( (synpred8_InternalJavaJRExpression()) ) {
                        alt12=1;
                    }


                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // InternalJavaJRExpression.g:739:2: ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) )
            	    {
            	    // InternalJavaJRExpression.g:739:2: ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) )
            	    // InternalJavaJRExpression.g:739:3: ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) )
            	    {
            	    // InternalJavaJRExpression.g:755:6: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) )
            	    // InternalJavaJRExpression.g:755:7: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) )
            	    {
            	    // InternalJavaJRExpression.g:755:7: ()
            	    // InternalJavaJRExpression.g:756:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getMultiplicativeExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalJavaJRExpression.g:761:2: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) )
            	    // InternalJavaJRExpression.g:762:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) )
            	    {
            	    // InternalJavaJRExpression.g:762:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) )
            	    // InternalJavaJRExpression.g:763:1: (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' )
            	    {
            	    // InternalJavaJRExpression.g:763:1: (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' )
            	    int alt11=3;
            	    switch ( input.LA(1) ) {
            	    case 35:
            	        {
            	        alt11=1;
            	        }
            	        break;
            	    case 36:
            	        {
            	        alt11=2;
            	        }
            	        break;
            	    case 37:
            	        {
            	        alt11=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 11, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt11) {
            	        case 1 :
            	            // InternalJavaJRExpression.g:764:3: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,35,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_1, grammarAccess.getMultiplicativeExpressionAccess().getOpAsteriskKeyword_1_0_0_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getMultiplicativeExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalJavaJRExpression.g:776:8: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,36,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_2, grammarAccess.getMultiplicativeExpressionAccess().getOpSolidusKeyword_1_0_0_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getMultiplicativeExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // InternalJavaJRExpression.g:788:8: lv_op_2_3= '%'
            	            {
            	            lv_op_2_3=(Token)match(input,37,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_3, grammarAccess.getMultiplicativeExpressionAccess().getOpPercentSignKeyword_1_0_0_1_0_2());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getMultiplicativeExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_3, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }


            	    }

            	    // InternalJavaJRExpression.g:803:4: ( (lv_right_3_0= ruleUnaryExpression ) )
            	    // InternalJavaJRExpression.g:804:1: (lv_right_3_0= ruleUnaryExpression )
            	    {
            	    // InternalJavaJRExpression.g:804:1: (lv_right_3_0= ruleUnaryExpression )
            	    // InternalJavaJRExpression.g:805:3: lv_right_3_0= ruleUnaryExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getRightUnaryExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_13);
            	    lv_right_3_0=ruleUnaryExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getMultiplicativeExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.UnaryExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultiplicativeExpression"


    // $ANTLR start "entryRuleUnaryExpression"
    // InternalJavaJRExpression.g:829:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // InternalJavaJRExpression.g:830:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // InternalJavaJRExpression.g:831:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnaryExpression"


    // $ANTLR start "ruleUnaryExpression"
    // InternalJavaJRExpression.g:838:1: ruleUnaryExpression returns [EObject current=null] : ( (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression ) | this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_UnaryExpression_1 = null;

        EObject this_UnaryExpression_3 = null;

        EObject this_UnaryExpressionNotPlusMinus_4 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:841:28: ( ( (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression ) | this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus ) )
            // InternalJavaJRExpression.g:842:1: ( (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression ) | this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus )
            {
            // InternalJavaJRExpression.g:842:1: ( (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression ) | this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus )
            int alt13=3;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt13=1;
                }
                break;
            case 34:
                {
                alt13=2;
                }
                break;
            case RULE_INT:
            case RULE_LONG:
            case RULE_FLOAT:
            case RULE_DOUBLE:
            case RULE_CHAR:
            case RULE_STRING:
            case RULE_ID:
            case 38:
            case 39:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 48:
            case 49:
            case 50:
            case 51:
                {
                alt13=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // InternalJavaJRExpression.g:842:2: (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression )
                    {
                    // InternalJavaJRExpression.g:842:2: (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression )
                    // InternalJavaJRExpression.g:842:4: otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression
                    {
                    otherlv_0=(Token)match(input,33,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getUnaryExpressionAccess().getPlusSignKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionAccess().getUnaryExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnaryExpression_1=ruleUnaryExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_UnaryExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:856:6: (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression )
                    {
                    // InternalJavaJRExpression.g:856:6: (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression )
                    // InternalJavaJRExpression.g:856:8: otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression
                    {
                    otherlv_2=(Token)match(input,34,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getUnaryExpressionAccess().getHyphenMinusKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionAccess().getUnaryExpressionParserRuleCall_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnaryExpression_3=ruleUnaryExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_UnaryExpression_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalJavaJRExpression.g:871:5: this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionAccess().getUnaryExpressionNotPlusMinusParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnaryExpressionNotPlusMinus_4=ruleUnaryExpressionNotPlusMinus();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_UnaryExpressionNotPlusMinus_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryExpression"


    // $ANTLR start "entryRuleUnaryExpressionNotPlusMinus"
    // InternalJavaJRExpression.g:887:1: entryRuleUnaryExpressionNotPlusMinus returns [EObject current=null] : iv_ruleUnaryExpressionNotPlusMinus= ruleUnaryExpressionNotPlusMinus EOF ;
    public final EObject entryRuleUnaryExpressionNotPlusMinus() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpressionNotPlusMinus = null;


        try {
            // InternalJavaJRExpression.g:888:2: (iv_ruleUnaryExpressionNotPlusMinus= ruleUnaryExpressionNotPlusMinus EOF )
            // InternalJavaJRExpression.g:889:2: iv_ruleUnaryExpressionNotPlusMinus= ruleUnaryExpressionNotPlusMinus EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnaryExpressionNotPlusMinus=ruleUnaryExpressionNotPlusMinus();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpressionNotPlusMinus; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnaryExpressionNotPlusMinus"


    // $ANTLR start "ruleUnaryExpressionNotPlusMinus"
    // InternalJavaJRExpression.g:896:1: ruleUnaryExpressionNotPlusMinus returns [EObject current=null] : ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression ) ;
    public final EObject ruleUnaryExpressionNotPlusMinus() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_UnaryExpression_1 = null;

        EObject this_UnaryExpression_3 = null;

        EObject this_CastedExpression_4 = null;

        EObject this_PrimaryExpression_5 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:899:28: ( ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression ) )
            // InternalJavaJRExpression.g:900:1: ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression )
            {
            // InternalJavaJRExpression.g:900:1: ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression )
            int alt14=4;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // InternalJavaJRExpression.g:900:2: (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression )
                    {
                    // InternalJavaJRExpression.g:900:2: (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression )
                    // InternalJavaJRExpression.g:900:4: otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression
                    {
                    otherlv_0=(Token)match(input,38,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getUnaryExpressionNotPlusMinusAccess().getTildeKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusAccess().getUnaryExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnaryExpression_1=ruleUnaryExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_UnaryExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:914:6: (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression )
                    {
                    // InternalJavaJRExpression.g:914:6: (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression )
                    // InternalJavaJRExpression.g:914:8: otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression
                    {
                    otherlv_2=(Token)match(input,39,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getUnaryExpressionNotPlusMinusAccess().getExclamationMarkKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusAccess().getUnaryExpressionParserRuleCall_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnaryExpression_3=ruleUnaryExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_UnaryExpression_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalJavaJRExpression.g:928:6: ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression )
                    {
                    // InternalJavaJRExpression.g:928:6: ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression )
                    // InternalJavaJRExpression.g:928:7: ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusAccess().getCastedExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_CastedExpression_4=ruleCastedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_CastedExpression_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalJavaJRExpression.g:939:5: this_PrimaryExpression_5= rulePrimaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusAccess().getPrimaryExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_PrimaryExpression_5=rulePrimaryExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_PrimaryExpression_5; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryExpressionNotPlusMinus"


    // $ANTLR start "entryRulePrimaryExpression"
    // InternalJavaJRExpression.g:955:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // InternalJavaJRExpression.g:956:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // InternalJavaJRExpression.g:957:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimaryExpression"


    // $ANTLR start "rulePrimaryExpression"
    // InternalJavaJRExpression.g:964:1: rulePrimaryExpression returns [EObject current=null] : (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField ) ;
    public final EObject rulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ParExpression_0 = null;

        EObject this_LiteralExpression_1 = null;

        EObject this_ArrayCreator_2 = null;

        EObject this_BaseJRExpression_3 = null;

        EObject this_MethodsExpression_4 = null;

        EObject this_StaticField_5 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:967:28: ( (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField ) )
            // InternalJavaJRExpression.g:968:1: (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField )
            {
            // InternalJavaJRExpression.g:968:1: (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField )
            int alt15=6;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // InternalJavaJRExpression.g:969:5: this_ParExpression_0= ruleParExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ParExpression_0=ruleParExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ParExpression_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:979:5: this_LiteralExpression_1= ruleLiteralExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_LiteralExpression_1=ruleLiteralExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_LiteralExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalJavaJRExpression.g:989:5: this_ArrayCreator_2= ruleArrayCreator
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getArrayCreatorParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ArrayCreator_2=ruleArrayCreator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ArrayCreator_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalJavaJRExpression.g:998:6: ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression )
                    {
                    // InternalJavaJRExpression.g:998:6: ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression )
                    // InternalJavaJRExpression.g:998:7: ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getBaseJRExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_BaseJRExpression_3=ruleBaseJRExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_BaseJRExpression_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalJavaJRExpression.g:1009:5: this_MethodsExpression_4= ruleMethodsExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getMethodsExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_MethodsExpression_4=ruleMethodsExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_MethodsExpression_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalJavaJRExpression.g:1019:5: this_StaticField_5= ruleStaticField
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getStaticFieldParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_StaticField_5=ruleStaticField();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_StaticField_5; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimaryExpression"


    // $ANTLR start "entryRuleStaticField"
    // InternalJavaJRExpression.g:1035:1: entryRuleStaticField returns [EObject current=null] : iv_ruleStaticField= ruleStaticField EOF ;
    public final EObject entryRuleStaticField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStaticField = null;


        try {
            // InternalJavaJRExpression.g:1036:2: (iv_ruleStaticField= ruleStaticField EOF )
            // InternalJavaJRExpression.g:1037:2: iv_ruleStaticField= ruleStaticField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStaticFieldRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleStaticField=ruleStaticField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStaticField; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStaticField"


    // $ANTLR start "ruleStaticField"
    // InternalJavaJRExpression.g:1044:1: ruleStaticField returns [EObject current=null] : ( () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) ) ) ;
    public final EObject ruleStaticField() throws RecognitionException {
        EObject current = null;

        Token lv_dots_2_0=null;
        AntlrDatatypeRuleToken lv_prefixQMN_1_0 = null;

        AntlrDatatypeRuleToken lv_fieldName_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1047:28: ( ( () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) ) ) )
            // InternalJavaJRExpression.g:1048:1: ( () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) ) )
            {
            // InternalJavaJRExpression.g:1048:1: ( () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) ) )
            // InternalJavaJRExpression.g:1048:2: () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) )
            {
            // InternalJavaJRExpression.g:1048:2: ()
            // InternalJavaJRExpression.g:1049:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getStaticFieldAccess().getStaticFieldAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1054:2: ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1==40) ) {
                        alt16=1;
                    }


                }


                switch (alt16) {
            	case 1 :
            	    // InternalJavaJRExpression.g:1054:3: ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) )
            	    {
            	    // InternalJavaJRExpression.g:1054:3: ( (lv_prefixQMN_1_0= ruleValidID ) )
            	    // InternalJavaJRExpression.g:1055:1: (lv_prefixQMN_1_0= ruleValidID )
            	    {
            	    // InternalJavaJRExpression.g:1055:1: (lv_prefixQMN_1_0= ruleValidID )
            	    // InternalJavaJRExpression.g:1056:3: lv_prefixQMN_1_0= ruleValidID
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getStaticFieldAccess().getPrefixQMNValidIDParserRuleCall_1_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_14);
            	    lv_prefixQMN_1_0=ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getStaticFieldRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"prefixQMN",
            	              		lv_prefixQMN_1_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.ValidID");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // InternalJavaJRExpression.g:1072:2: ( (lv_dots_2_0= '.' ) )
            	    // InternalJavaJRExpression.g:1073:1: (lv_dots_2_0= '.' )
            	    {
            	    // InternalJavaJRExpression.g:1073:1: (lv_dots_2_0= '.' )
            	    // InternalJavaJRExpression.g:1074:3: lv_dots_2_0= '.'
            	    {
            	    lv_dots_2_0=(Token)match(input,40,FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_dots_2_0, grammarAccess.getStaticFieldAccess().getDotsFullStopKeyword_1_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getStaticFieldRule());
            	      	        }
            	             		addWithLastConsumed(current, "dots", lv_dots_2_0, ".");
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // InternalJavaJRExpression.g:1087:4: ( (lv_fieldName_3_0= ruleValidID ) )
            // InternalJavaJRExpression.g:1088:1: (lv_fieldName_3_0= ruleValidID )
            {
            // InternalJavaJRExpression.g:1088:1: (lv_fieldName_3_0= ruleValidID )
            // InternalJavaJRExpression.g:1089:3: lv_fieldName_3_0= ruleValidID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getStaticFieldAccess().getFieldNameValidIDParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_fieldName_3_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getStaticFieldRule());
              	        }
                     		set(
                     			current, 
                     			"fieldName",
                      		lv_fieldName_3_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.ValidID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStaticField"


    // $ANTLR start "entryRuleBaseJRExpression"
    // InternalJavaJRExpression.g:1113:1: entryRuleBaseJRExpression returns [EObject current=null] : iv_ruleBaseJRExpression= ruleBaseJRExpression EOF ;
    public final EObject entryRuleBaseJRExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBaseJRExpression = null;


        try {
            // InternalJavaJRExpression.g:1114:2: (iv_ruleBaseJRExpression= ruleBaseJRExpression EOF )
            // InternalJavaJRExpression.g:1115:2: iv_ruleBaseJRExpression= ruleBaseJRExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBaseJRExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBaseJRExpression=ruleBaseJRExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBaseJRExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBaseJRExpression"


    // $ANTLR start "ruleBaseJRExpression"
    // InternalJavaJRExpression.g:1122:1: ruleBaseJRExpression returns [EObject current=null] : (this_JRFieldObj_0= ruleJRFieldObj | this_JRParameterObj_1= ruleJRParameterObj | this_JRVariableObj_2= ruleJRVariableObj | this_JRResourceBundleKeyObj_3= ruleJRResourceBundleKeyObj ) ;
    public final EObject ruleBaseJRExpression() throws RecognitionException {
        EObject current = null;

        EObject this_JRFieldObj_0 = null;

        EObject this_JRParameterObj_1 = null;

        EObject this_JRVariableObj_2 = null;

        EObject this_JRResourceBundleKeyObj_3 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1125:28: ( (this_JRFieldObj_0= ruleJRFieldObj | this_JRParameterObj_1= ruleJRParameterObj | this_JRVariableObj_2= ruleJRVariableObj | this_JRResourceBundleKeyObj_3= ruleJRResourceBundleKeyObj ) )
            // InternalJavaJRExpression.g:1126:1: (this_JRFieldObj_0= ruleJRFieldObj | this_JRParameterObj_1= ruleJRParameterObj | this_JRVariableObj_2= ruleJRVariableObj | this_JRResourceBundleKeyObj_3= ruleJRResourceBundleKeyObj )
            {
            // InternalJavaJRExpression.g:1126:1: (this_JRFieldObj_0= ruleJRFieldObj | this_JRParameterObj_1= ruleJRParameterObj | this_JRVariableObj_2= ruleJRVariableObj | this_JRResourceBundleKeyObj_3= ruleJRResourceBundleKeyObj )
            int alt17=4;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt17=1;
                }
                break;
            case 42:
                {
                alt17=2;
                }
                break;
            case 43:
                {
                alt17=3;
                }
                break;
            case 44:
                {
                alt17=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // InternalJavaJRExpression.g:1127:5: this_JRFieldObj_0= ruleJRFieldObj
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getBaseJRExpressionAccess().getJRFieldObjParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_JRFieldObj_0=ruleJRFieldObj();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JRFieldObj_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:1137:5: this_JRParameterObj_1= ruleJRParameterObj
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getBaseJRExpressionAccess().getJRParameterObjParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_JRParameterObj_1=ruleJRParameterObj();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JRParameterObj_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalJavaJRExpression.g:1147:5: this_JRVariableObj_2= ruleJRVariableObj
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getBaseJRExpressionAccess().getJRVariableObjParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_JRVariableObj_2=ruleJRVariableObj();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JRVariableObj_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalJavaJRExpression.g:1157:5: this_JRResourceBundleKeyObj_3= ruleJRResourceBundleKeyObj
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getBaseJRExpressionAccess().getJRResourceBundleKeyObjParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_JRResourceBundleKeyObj_3=ruleJRResourceBundleKeyObj();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JRResourceBundleKeyObj_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBaseJRExpression"


    // $ANTLR start "entryRuleJRFieldObj"
    // InternalJavaJRExpression.g:1173:1: entryRuleJRFieldObj returns [EObject current=null] : iv_ruleJRFieldObj= ruleJRFieldObj EOF ;
    public final EObject entryRuleJRFieldObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRFieldObj = null;


        try {
            // InternalJavaJRExpression.g:1174:2: (iv_ruleJRFieldObj= ruleJRFieldObj EOF )
            // InternalJavaJRExpression.g:1175:2: iv_ruleJRFieldObj= ruleJRFieldObj EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRFieldObjRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJRFieldObj=ruleJRFieldObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRFieldObj; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJRFieldObj"


    // $ANTLR start "ruleJRFieldObj"
    // InternalJavaJRExpression.g:1182:1: ruleJRFieldObj returns [EObject current=null] : ( () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) ;
    public final EObject ruleJRFieldObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_bracedIdentifier_2_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1185:28: ( ( () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) )
            // InternalJavaJRExpression.g:1186:1: ( () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            {
            // InternalJavaJRExpression.g:1186:1: ( () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            // InternalJavaJRExpression.g:1186:2: () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            {
            // InternalJavaJRExpression.g:1186:2: ()
            // InternalJavaJRExpression.g:1187:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJRFieldObjAccess().getJRFieldObjAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,41,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJRFieldObjAccess().getFKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:1196:1: ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            // InternalJavaJRExpression.g:1197:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            {
            // InternalJavaJRExpression.g:1197:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            // InternalJavaJRExpression.g:1198:3: lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER
            {
            lv_bracedIdentifier_2_0=(Token)match(input,RULE_BRACED_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_bracedIdentifier_2_0, grammarAccess.getJRFieldObjAccess().getBracedIdentifierBRACED_IDENTIFIERTerminalRuleCall_2_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getJRFieldObjRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"bracedIdentifier",
                      		lv_bracedIdentifier_2_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.BRACED_IDENTIFIER");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJRFieldObj"


    // $ANTLR start "entryRuleJRParameterObj"
    // InternalJavaJRExpression.g:1222:1: entryRuleJRParameterObj returns [EObject current=null] : iv_ruleJRParameterObj= ruleJRParameterObj EOF ;
    public final EObject entryRuleJRParameterObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameterObj = null;


        try {
            // InternalJavaJRExpression.g:1223:2: (iv_ruleJRParameterObj= ruleJRParameterObj EOF )
            // InternalJavaJRExpression.g:1224:2: iv_ruleJRParameterObj= ruleJRParameterObj EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRParameterObjRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJRParameterObj=ruleJRParameterObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRParameterObj; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJRParameterObj"


    // $ANTLR start "ruleJRParameterObj"
    // InternalJavaJRExpression.g:1231:1: ruleJRParameterObj returns [EObject current=null] : ( () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) ;
    public final EObject ruleJRParameterObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_bracedIdentifier_2_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1234:28: ( ( () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) )
            // InternalJavaJRExpression.g:1235:1: ( () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            {
            // InternalJavaJRExpression.g:1235:1: ( () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            // InternalJavaJRExpression.g:1235:2: () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            {
            // InternalJavaJRExpression.g:1235:2: ()
            // InternalJavaJRExpression.g:1236:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJRParameterObjAccess().getJRParameterObjAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,42,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJRParameterObjAccess().getPKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:1245:1: ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            // InternalJavaJRExpression.g:1246:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            {
            // InternalJavaJRExpression.g:1246:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            // InternalJavaJRExpression.g:1247:3: lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER
            {
            lv_bracedIdentifier_2_0=(Token)match(input,RULE_BRACED_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_bracedIdentifier_2_0, grammarAccess.getJRParameterObjAccess().getBracedIdentifierBRACED_IDENTIFIERTerminalRuleCall_2_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getJRParameterObjRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"bracedIdentifier",
                      		lv_bracedIdentifier_2_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.BRACED_IDENTIFIER");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJRParameterObj"


    // $ANTLR start "entryRuleJRVariableObj"
    // InternalJavaJRExpression.g:1271:1: entryRuleJRVariableObj returns [EObject current=null] : iv_ruleJRVariableObj= ruleJRVariableObj EOF ;
    public final EObject entryRuleJRVariableObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRVariableObj = null;


        try {
            // InternalJavaJRExpression.g:1272:2: (iv_ruleJRVariableObj= ruleJRVariableObj EOF )
            // InternalJavaJRExpression.g:1273:2: iv_ruleJRVariableObj= ruleJRVariableObj EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRVariableObjRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJRVariableObj=ruleJRVariableObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRVariableObj; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJRVariableObj"


    // $ANTLR start "ruleJRVariableObj"
    // InternalJavaJRExpression.g:1280:1: ruleJRVariableObj returns [EObject current=null] : ( () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) ;
    public final EObject ruleJRVariableObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_bracedIdentifier_2_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1283:28: ( ( () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) )
            // InternalJavaJRExpression.g:1284:1: ( () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            {
            // InternalJavaJRExpression.g:1284:1: ( () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            // InternalJavaJRExpression.g:1284:2: () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            {
            // InternalJavaJRExpression.g:1284:2: ()
            // InternalJavaJRExpression.g:1285:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJRVariableObjAccess().getJRVariableObjAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,43,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJRVariableObjAccess().getVKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:1294:1: ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            // InternalJavaJRExpression.g:1295:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            {
            // InternalJavaJRExpression.g:1295:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            // InternalJavaJRExpression.g:1296:3: lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER
            {
            lv_bracedIdentifier_2_0=(Token)match(input,RULE_BRACED_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_bracedIdentifier_2_0, grammarAccess.getJRVariableObjAccess().getBracedIdentifierBRACED_IDENTIFIERTerminalRuleCall_2_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getJRVariableObjRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"bracedIdentifier",
                      		lv_bracedIdentifier_2_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.BRACED_IDENTIFIER");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJRVariableObj"


    // $ANTLR start "entryRuleJRResourceBundleKeyObj"
    // InternalJavaJRExpression.g:1320:1: entryRuleJRResourceBundleKeyObj returns [EObject current=null] : iv_ruleJRResourceBundleKeyObj= ruleJRResourceBundleKeyObj EOF ;
    public final EObject entryRuleJRResourceBundleKeyObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRResourceBundleKeyObj = null;


        try {
            // InternalJavaJRExpression.g:1321:2: (iv_ruleJRResourceBundleKeyObj= ruleJRResourceBundleKeyObj EOF )
            // InternalJavaJRExpression.g:1322:2: iv_ruleJRResourceBundleKeyObj= ruleJRResourceBundleKeyObj EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRResourceBundleKeyObjRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJRResourceBundleKeyObj=ruleJRResourceBundleKeyObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRResourceBundleKeyObj; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJRResourceBundleKeyObj"


    // $ANTLR start "ruleJRResourceBundleKeyObj"
    // InternalJavaJRExpression.g:1329:1: ruleJRResourceBundleKeyObj returns [EObject current=null] : ( () otherlv_1= '$R' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) ;
    public final EObject ruleJRResourceBundleKeyObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_bracedIdentifier_2_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1332:28: ( ( () otherlv_1= '$R' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) )
            // InternalJavaJRExpression.g:1333:1: ( () otherlv_1= '$R' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            {
            // InternalJavaJRExpression.g:1333:1: ( () otherlv_1= '$R' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            // InternalJavaJRExpression.g:1333:2: () otherlv_1= '$R' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            {
            // InternalJavaJRExpression.g:1333:2: ()
            // InternalJavaJRExpression.g:1334:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJRResourceBundleKeyObjAccess().getJRResourceBundleKeyObjAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,44,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJRResourceBundleKeyObjAccess().getRKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:1343:1: ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            // InternalJavaJRExpression.g:1344:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            {
            // InternalJavaJRExpression.g:1344:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            // InternalJavaJRExpression.g:1345:3: lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER
            {
            lv_bracedIdentifier_2_0=(Token)match(input,RULE_BRACED_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_bracedIdentifier_2_0, grammarAccess.getJRResourceBundleKeyObjAccess().getBracedIdentifierBRACED_IDENTIFIERTerminalRuleCall_2_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getJRResourceBundleKeyObjRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"bracedIdentifier",
                      		lv_bracedIdentifier_2_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.BRACED_IDENTIFIER");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJRResourceBundleKeyObj"


    // $ANTLR start "entryRuleMethodsExpression"
    // InternalJavaJRExpression.g:1369:1: entryRuleMethodsExpression returns [EObject current=null] : iv_ruleMethodsExpression= ruleMethodsExpression EOF ;
    public final EObject entryRuleMethodsExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethodsExpression = null;


        try {
            // InternalJavaJRExpression.g:1370:2: (iv_ruleMethodsExpression= ruleMethodsExpression EOF )
            // InternalJavaJRExpression.g:1371:2: iv_ruleMethodsExpression= ruleMethodsExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMethodsExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMethodsExpression=ruleMethodsExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMethodsExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethodsExpression"


    // $ANTLR start "ruleMethodsExpression"
    // InternalJavaJRExpression.g:1378:1: ruleMethodsExpression returns [EObject current=null] : ( () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )* ) ;
    public final EObject ruleMethodsExpression() throws RecognitionException {
        EObject current = null;

        Token lv_includeObjectInstatiation_1_0=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_methodInvocations_2_0 = null;

        EObject lv_methodInvocations_4_0 = null;

        EObject lv_objectExpression_5_1 = null;

        EObject lv_objectExpression_5_2 = null;

        EObject lv_methodInvocations_7_0 = null;

        EObject lv_arrayIndexes_9_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1381:28: ( ( () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )* ) )
            // InternalJavaJRExpression.g:1382:1: ( () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )* )
            {
            // InternalJavaJRExpression.g:1382:1: ( () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )* )
            // InternalJavaJRExpression.g:1382:2: () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )*
            {
            // InternalJavaJRExpression.g:1382:2: ()
            // InternalJavaJRExpression.g:1383:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getMethodsExpressionAccess().getMethodsExpressionAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1388:2: ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==RULE_ID||LA22_0==45) ) {
                alt22=1;
            }
            else if ( (LA22_0==RULE_STRING||(LA22_0>=41 && LA22_0<=44)) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalJavaJRExpression.g:1388:3: ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* )
                    {
                    // InternalJavaJRExpression.g:1388:3: ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* )
                    // InternalJavaJRExpression.g:1388:4: ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )*
                    {
                    // InternalJavaJRExpression.g:1388:4: ( (lv_includeObjectInstatiation_1_0= 'new' ) )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==45) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // InternalJavaJRExpression.g:1389:1: (lv_includeObjectInstatiation_1_0= 'new' )
                            {
                            // InternalJavaJRExpression.g:1389:1: (lv_includeObjectInstatiation_1_0= 'new' )
                            // InternalJavaJRExpression.g:1390:3: lv_includeObjectInstatiation_1_0= 'new'
                            {
                            lv_includeObjectInstatiation_1_0=(Token)match(input,45,FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_includeObjectInstatiation_1_0, grammarAccess.getMethodsExpressionAccess().getIncludeObjectInstatiationNewKeyword_1_0_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getMethodsExpressionRule());
                              	        }
                                     		setWithLastConsumed(current, "includeObjectInstatiation", true, "new");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    // InternalJavaJRExpression.g:1403:3: ( (lv_methodInvocations_2_0= ruleMethodInvocation ) )
                    // InternalJavaJRExpression.g:1404:1: (lv_methodInvocations_2_0= ruleMethodInvocation )
                    {
                    // InternalJavaJRExpression.g:1404:1: (lv_methodInvocations_2_0= ruleMethodInvocation )
                    // InternalJavaJRExpression.g:1405:3: lv_methodInvocations_2_0= ruleMethodInvocation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getMethodInvocationsMethodInvocationParserRuleCall_1_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_18);
                    lv_methodInvocations_2_0=ruleMethodInvocation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getMethodsExpressionRule());
                      	        }
                             		add(
                             			current, 
                             			"methodInvocations",
                              		lv_methodInvocations_2_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.MethodInvocation");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalJavaJRExpression.g:1421:2: (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==40) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalJavaJRExpression.g:1421:4: otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) )
                    	    {
                    	    otherlv_3=(Token)match(input,40,FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getMethodsExpressionAccess().getFullStopKeyword_1_0_2_0());
                    	          
                    	    }
                    	    // InternalJavaJRExpression.g:1425:1: ( (lv_methodInvocations_4_0= ruleMethodInvocation ) )
                    	    // InternalJavaJRExpression.g:1426:1: (lv_methodInvocations_4_0= ruleMethodInvocation )
                    	    {
                    	    // InternalJavaJRExpression.g:1426:1: (lv_methodInvocations_4_0= ruleMethodInvocation )
                    	    // InternalJavaJRExpression.g:1427:3: lv_methodInvocations_4_0= ruleMethodInvocation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getMethodInvocationsMethodInvocationParserRuleCall_1_0_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_18);
                    	    lv_methodInvocations_4_0=ruleMethodInvocation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getMethodsExpressionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"methodInvocations",
                    	              		lv_methodInvocations_4_0, 
                    	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.MethodInvocation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:1444:6: ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ )
                    {
                    // InternalJavaJRExpression.g:1444:6: ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ )
                    // InternalJavaJRExpression.g:1444:7: ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+
                    {
                    // InternalJavaJRExpression.g:1444:7: ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) )
                    // InternalJavaJRExpression.g:1445:1: ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) )
                    {
                    // InternalJavaJRExpression.g:1445:1: ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) )
                    // InternalJavaJRExpression.g:1446:1: (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral )
                    {
                    // InternalJavaJRExpression.g:1446:1: (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral )
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( ((LA20_0>=41 && LA20_0<=44)) ) {
                        alt20=1;
                    }
                    else if ( (LA20_0==RULE_STRING) ) {
                        alt20=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 0, input);

                        throw nvae;
                    }
                    switch (alt20) {
                        case 1 :
                            // InternalJavaJRExpression.g:1447:3: lv_objectExpression_5_1= ruleBaseJRExpression
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getObjectExpressionBaseJRExpressionParserRuleCall_1_1_0_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_14);
                            lv_objectExpression_5_1=ruleBaseJRExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getMethodsExpressionRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"objectExpression",
                                      		lv_objectExpression_5_1, 
                                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.BaseJRExpression");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // InternalJavaJRExpression.g:1462:8: lv_objectExpression_5_2= ruleStringLiteral
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getObjectExpressionStringLiteralParserRuleCall_1_1_0_0_1()); 
                              	    
                            }
                            pushFollow(FOLLOW_14);
                            lv_objectExpression_5_2=ruleStringLiteral();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getMethodsExpressionRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"objectExpression",
                                      		lv_objectExpression_5_2, 
                                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.StringLiteral");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // InternalJavaJRExpression.g:1480:2: (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+
                    int cnt21=0;
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==40) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // InternalJavaJRExpression.g:1480:4: otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) )
                    	    {
                    	    otherlv_6=(Token)match(input,40,FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_6, grammarAccess.getMethodsExpressionAccess().getFullStopKeyword_1_1_1_0());
                    	          
                    	    }
                    	    // InternalJavaJRExpression.g:1484:1: ( (lv_methodInvocations_7_0= ruleMethodInvocation ) )
                    	    // InternalJavaJRExpression.g:1485:1: (lv_methodInvocations_7_0= ruleMethodInvocation )
                    	    {
                    	    // InternalJavaJRExpression.g:1485:1: (lv_methodInvocations_7_0= ruleMethodInvocation )
                    	    // InternalJavaJRExpression.g:1486:3: lv_methodInvocations_7_0= ruleMethodInvocation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getMethodInvocationsMethodInvocationParserRuleCall_1_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_18);
                    	    lv_methodInvocations_7_0=ruleMethodInvocation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getMethodsExpressionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"methodInvocations",
                    	              		lv_methodInvocations_7_0, 
                    	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.MethodInvocation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt21 >= 1 ) break loop21;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(21, input);
                                throw eee;
                        }
                        cnt21++;
                    } while (true);


                    }


                    }
                    break;

            }

            // InternalJavaJRExpression.g:1502:6: (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==46) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalJavaJRExpression.g:1502:8: otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']'
            	    {
            	    otherlv_8=(Token)match(input,46,FOLLOW_19); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getMethodsExpressionAccess().getLeftSquareBracketKeyword_2_0());
            	          
            	    }
            	    // InternalJavaJRExpression.g:1506:1: ( (lv_arrayIndexes_9_0= ruleIntLiteral ) )
            	    // InternalJavaJRExpression.g:1507:1: (lv_arrayIndexes_9_0= ruleIntLiteral )
            	    {
            	    // InternalJavaJRExpression.g:1507:1: (lv_arrayIndexes_9_0= ruleIntLiteral )
            	    // InternalJavaJRExpression.g:1508:3: lv_arrayIndexes_9_0= ruleIntLiteral
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getArrayIndexesIntLiteralParserRuleCall_2_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_20);
            	    lv_arrayIndexes_9_0=ruleIntLiteral();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getMethodsExpressionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"arrayIndexes",
            	              		lv_arrayIndexes_9_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.IntLiteral");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_10=(Token)match(input,47,FOLLOW_21); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getMethodsExpressionAccess().getRightSquareBracketKeyword_2_2());
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethodsExpression"


    // $ANTLR start "entryRuleLiteralExpression"
    // InternalJavaJRExpression.g:1536:1: entryRuleLiteralExpression returns [EObject current=null] : iv_ruleLiteralExpression= ruleLiteralExpression EOF ;
    public final EObject entryRuleLiteralExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteralExpression = null;


        try {
            // InternalJavaJRExpression.g:1537:2: (iv_ruleLiteralExpression= ruleLiteralExpression EOF )
            // InternalJavaJRExpression.g:1538:2: iv_ruleLiteralExpression= ruleLiteralExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLiteralExpression=ruleLiteralExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteralExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLiteralExpression"


    // $ANTLR start "ruleLiteralExpression"
    // InternalJavaJRExpression.g:1545:1: ruleLiteralExpression returns [EObject current=null] : (this_IntLiteral_0= ruleIntLiteral | this_LongLiteral_1= ruleLongLiteral | this_FloatLiteral_2= ruleFloatLiteral | this_DoubleLiteral_3= ruleDoubleLiteral | this_CharLiteral_4= ruleCharLiteral | this_StringLiteral_5= ruleStringLiteral | this_BooleanLiteral_6= ruleBooleanLiteral | this_NullLiteral_7= ruleNullLiteral ) ;
    public final EObject ruleLiteralExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IntLiteral_0 = null;

        EObject this_LongLiteral_1 = null;

        EObject this_FloatLiteral_2 = null;

        EObject this_DoubleLiteral_3 = null;

        EObject this_CharLiteral_4 = null;

        EObject this_StringLiteral_5 = null;

        EObject this_BooleanLiteral_6 = null;

        EObject this_NullLiteral_7 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1548:28: ( (this_IntLiteral_0= ruleIntLiteral | this_LongLiteral_1= ruleLongLiteral | this_FloatLiteral_2= ruleFloatLiteral | this_DoubleLiteral_3= ruleDoubleLiteral | this_CharLiteral_4= ruleCharLiteral | this_StringLiteral_5= ruleStringLiteral | this_BooleanLiteral_6= ruleBooleanLiteral | this_NullLiteral_7= ruleNullLiteral ) )
            // InternalJavaJRExpression.g:1549:1: (this_IntLiteral_0= ruleIntLiteral | this_LongLiteral_1= ruleLongLiteral | this_FloatLiteral_2= ruleFloatLiteral | this_DoubleLiteral_3= ruleDoubleLiteral | this_CharLiteral_4= ruleCharLiteral | this_StringLiteral_5= ruleStringLiteral | this_BooleanLiteral_6= ruleBooleanLiteral | this_NullLiteral_7= ruleNullLiteral )
            {
            // InternalJavaJRExpression.g:1549:1: (this_IntLiteral_0= ruleIntLiteral | this_LongLiteral_1= ruleLongLiteral | this_FloatLiteral_2= ruleFloatLiteral | this_DoubleLiteral_3= ruleDoubleLiteral | this_CharLiteral_4= ruleCharLiteral | this_StringLiteral_5= ruleStringLiteral | this_BooleanLiteral_6= ruleBooleanLiteral | this_NullLiteral_7= ruleNullLiteral )
            int alt24=8;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt24=1;
                }
                break;
            case RULE_LONG:
                {
                alt24=2;
                }
                break;
            case RULE_FLOAT:
                {
                alt24=3;
                }
                break;
            case RULE_DOUBLE:
                {
                alt24=4;
                }
                break;
            case RULE_CHAR:
                {
                alt24=5;
                }
                break;
            case RULE_STRING:
                {
                alt24=6;
                }
                break;
            case 48:
            case 49:
                {
                alt24=7;
                }
                break;
            case 50:
                {
                alt24=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // InternalJavaJRExpression.g:1550:5: this_IntLiteral_0= ruleIntLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getIntLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_IntLiteral_0=ruleIntLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_IntLiteral_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:1560:5: this_LongLiteral_1= ruleLongLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getLongLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_LongLiteral_1=ruleLongLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_LongLiteral_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalJavaJRExpression.g:1570:5: this_FloatLiteral_2= ruleFloatLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getFloatLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_FloatLiteral_2=ruleFloatLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_FloatLiteral_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalJavaJRExpression.g:1580:5: this_DoubleLiteral_3= ruleDoubleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getDoubleLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_DoubleLiteral_3=ruleDoubleLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_DoubleLiteral_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalJavaJRExpression.g:1590:5: this_CharLiteral_4= ruleCharLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getCharLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_CharLiteral_4=ruleCharLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_CharLiteral_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalJavaJRExpression.g:1600:5: this_StringLiteral_5= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getStringLiteralParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_StringLiteral_5=ruleStringLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_StringLiteral_5; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 7 :
                    // InternalJavaJRExpression.g:1610:5: this_BooleanLiteral_6= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getBooleanLiteralParserRuleCall_6()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_BooleanLiteral_6=ruleBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_BooleanLiteral_6; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 8 :
                    // InternalJavaJRExpression.g:1620:5: this_NullLiteral_7= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getNullLiteralParserRuleCall_7()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_NullLiteral_7=ruleNullLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_NullLiteral_7; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLiteralExpression"


    // $ANTLR start "entryRuleIntLiteral"
    // InternalJavaJRExpression.g:1636:1: entryRuleIntLiteral returns [EObject current=null] : iv_ruleIntLiteral= ruleIntLiteral EOF ;
    public final EObject entryRuleIntLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntLiteral = null;


        try {
            // InternalJavaJRExpression.g:1637:2: (iv_ruleIntLiteral= ruleIntLiteral EOF )
            // InternalJavaJRExpression.g:1638:2: iv_ruleIntLiteral= ruleIntLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIntLiteral=ruleIntLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntLiteral"


    // $ANTLR start "ruleIntLiteral"
    // InternalJavaJRExpression.g:1645:1: ruleIntLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_INT ) ) ) ;
    public final EObject ruleIntLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1648:28: ( ( () ( (lv_value_1_0= RULE_INT ) ) ) )
            // InternalJavaJRExpression.g:1649:1: ( () ( (lv_value_1_0= RULE_INT ) ) )
            {
            // InternalJavaJRExpression.g:1649:1: ( () ( (lv_value_1_0= RULE_INT ) ) )
            // InternalJavaJRExpression.g:1649:2: () ( (lv_value_1_0= RULE_INT ) )
            {
            // InternalJavaJRExpression.g:1649:2: ()
            // InternalJavaJRExpression.g:1650:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getIntLiteralAccess().getIntLiteralAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1655:2: ( (lv_value_1_0= RULE_INT ) )
            // InternalJavaJRExpression.g:1656:1: (lv_value_1_0= RULE_INT )
            {
            // InternalJavaJRExpression.g:1656:1: (lv_value_1_0= RULE_INT )
            // InternalJavaJRExpression.g:1657:3: lv_value_1_0= RULE_INT
            {
            lv_value_1_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_1_0, grammarAccess.getIntLiteralAccess().getValueINTTerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getIntLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.INT");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntLiteral"


    // $ANTLR start "entryRuleLongLiteral"
    // InternalJavaJRExpression.g:1681:1: entryRuleLongLiteral returns [EObject current=null] : iv_ruleLongLiteral= ruleLongLiteral EOF ;
    public final EObject entryRuleLongLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLongLiteral = null;


        try {
            // InternalJavaJRExpression.g:1682:2: (iv_ruleLongLiteral= ruleLongLiteral EOF )
            // InternalJavaJRExpression.g:1683:2: iv_ruleLongLiteral= ruleLongLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLongLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLongLiteral=ruleLongLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLongLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLongLiteral"


    // $ANTLR start "ruleLongLiteral"
    // InternalJavaJRExpression.g:1690:1: ruleLongLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_LONG ) ) ) ;
    public final EObject ruleLongLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1693:28: ( ( () ( (lv_value_1_0= RULE_LONG ) ) ) )
            // InternalJavaJRExpression.g:1694:1: ( () ( (lv_value_1_0= RULE_LONG ) ) )
            {
            // InternalJavaJRExpression.g:1694:1: ( () ( (lv_value_1_0= RULE_LONG ) ) )
            // InternalJavaJRExpression.g:1694:2: () ( (lv_value_1_0= RULE_LONG ) )
            {
            // InternalJavaJRExpression.g:1694:2: ()
            // InternalJavaJRExpression.g:1695:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getLongLiteralAccess().getLongLiteralAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1700:2: ( (lv_value_1_0= RULE_LONG ) )
            // InternalJavaJRExpression.g:1701:1: (lv_value_1_0= RULE_LONG )
            {
            // InternalJavaJRExpression.g:1701:1: (lv_value_1_0= RULE_LONG )
            // InternalJavaJRExpression.g:1702:3: lv_value_1_0= RULE_LONG
            {
            lv_value_1_0=(Token)match(input,RULE_LONG,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_1_0, grammarAccess.getLongLiteralAccess().getValueLONGTerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getLongLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.LONG");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLongLiteral"


    // $ANTLR start "entryRuleFloatLiteral"
    // InternalJavaJRExpression.g:1726:1: entryRuleFloatLiteral returns [EObject current=null] : iv_ruleFloatLiteral= ruleFloatLiteral EOF ;
    public final EObject entryRuleFloatLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatLiteral = null;


        try {
            // InternalJavaJRExpression.g:1727:2: (iv_ruleFloatLiteral= ruleFloatLiteral EOF )
            // InternalJavaJRExpression.g:1728:2: iv_ruleFloatLiteral= ruleFloatLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFloatLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFloatLiteral=ruleFloatLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFloatLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFloatLiteral"


    // $ANTLR start "ruleFloatLiteral"
    // InternalJavaJRExpression.g:1735:1: ruleFloatLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_FLOAT ) ) ) ;
    public final EObject ruleFloatLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1738:28: ( ( () ( (lv_value_1_0= RULE_FLOAT ) ) ) )
            // InternalJavaJRExpression.g:1739:1: ( () ( (lv_value_1_0= RULE_FLOAT ) ) )
            {
            // InternalJavaJRExpression.g:1739:1: ( () ( (lv_value_1_0= RULE_FLOAT ) ) )
            // InternalJavaJRExpression.g:1739:2: () ( (lv_value_1_0= RULE_FLOAT ) )
            {
            // InternalJavaJRExpression.g:1739:2: ()
            // InternalJavaJRExpression.g:1740:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getFloatLiteralAccess().getFloatLiteralAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1745:2: ( (lv_value_1_0= RULE_FLOAT ) )
            // InternalJavaJRExpression.g:1746:1: (lv_value_1_0= RULE_FLOAT )
            {
            // InternalJavaJRExpression.g:1746:1: (lv_value_1_0= RULE_FLOAT )
            // InternalJavaJRExpression.g:1747:3: lv_value_1_0= RULE_FLOAT
            {
            lv_value_1_0=(Token)match(input,RULE_FLOAT,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_1_0, grammarAccess.getFloatLiteralAccess().getValueFLOATTerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getFloatLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.FLOAT");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFloatLiteral"


    // $ANTLR start "entryRuleDoubleLiteral"
    // InternalJavaJRExpression.g:1771:1: entryRuleDoubleLiteral returns [EObject current=null] : iv_ruleDoubleLiteral= ruleDoubleLiteral EOF ;
    public final EObject entryRuleDoubleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoubleLiteral = null;


        try {
            // InternalJavaJRExpression.g:1772:2: (iv_ruleDoubleLiteral= ruleDoubleLiteral EOF )
            // InternalJavaJRExpression.g:1773:2: iv_ruleDoubleLiteral= ruleDoubleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDoubleLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleDoubleLiteral=ruleDoubleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDoubleLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDoubleLiteral"


    // $ANTLR start "ruleDoubleLiteral"
    // InternalJavaJRExpression.g:1780:1: ruleDoubleLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleDoubleLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1783:28: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) )
            // InternalJavaJRExpression.g:1784:1: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
            {
            // InternalJavaJRExpression.g:1784:1: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
            // InternalJavaJRExpression.g:1784:2: () ( (lv_value_1_0= RULE_DOUBLE ) )
            {
            // InternalJavaJRExpression.g:1784:2: ()
            // InternalJavaJRExpression.g:1785:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getDoubleLiteralAccess().getDoubleLiteralAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1790:2: ( (lv_value_1_0= RULE_DOUBLE ) )
            // InternalJavaJRExpression.g:1791:1: (lv_value_1_0= RULE_DOUBLE )
            {
            // InternalJavaJRExpression.g:1791:1: (lv_value_1_0= RULE_DOUBLE )
            // InternalJavaJRExpression.g:1792:3: lv_value_1_0= RULE_DOUBLE
            {
            lv_value_1_0=(Token)match(input,RULE_DOUBLE,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_1_0, grammarAccess.getDoubleLiteralAccess().getValueDOUBLETerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getDoubleLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.DOUBLE");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDoubleLiteral"


    // $ANTLR start "entryRuleCharLiteral"
    // InternalJavaJRExpression.g:1816:1: entryRuleCharLiteral returns [EObject current=null] : iv_ruleCharLiteral= ruleCharLiteral EOF ;
    public final EObject entryRuleCharLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCharLiteral = null;


        try {
            // InternalJavaJRExpression.g:1817:2: (iv_ruleCharLiteral= ruleCharLiteral EOF )
            // InternalJavaJRExpression.g:1818:2: iv_ruleCharLiteral= ruleCharLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCharLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCharLiteral=ruleCharLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCharLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCharLiteral"


    // $ANTLR start "ruleCharLiteral"
    // InternalJavaJRExpression.g:1825:1: ruleCharLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_CHAR ) ) ) ;
    public final EObject ruleCharLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1828:28: ( ( () ( (lv_value_1_0= RULE_CHAR ) ) ) )
            // InternalJavaJRExpression.g:1829:1: ( () ( (lv_value_1_0= RULE_CHAR ) ) )
            {
            // InternalJavaJRExpression.g:1829:1: ( () ( (lv_value_1_0= RULE_CHAR ) ) )
            // InternalJavaJRExpression.g:1829:2: () ( (lv_value_1_0= RULE_CHAR ) )
            {
            // InternalJavaJRExpression.g:1829:2: ()
            // InternalJavaJRExpression.g:1830:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getCharLiteralAccess().getCharLiteralAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1835:2: ( (lv_value_1_0= RULE_CHAR ) )
            // InternalJavaJRExpression.g:1836:1: (lv_value_1_0= RULE_CHAR )
            {
            // InternalJavaJRExpression.g:1836:1: (lv_value_1_0= RULE_CHAR )
            // InternalJavaJRExpression.g:1837:3: lv_value_1_0= RULE_CHAR
            {
            lv_value_1_0=(Token)match(input,RULE_CHAR,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_1_0, grammarAccess.getCharLiteralAccess().getValueCHARTerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getCharLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.CHAR");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCharLiteral"


    // $ANTLR start "entryRuleStringLiteral"
    // InternalJavaJRExpression.g:1861:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // InternalJavaJRExpression.g:1862:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalJavaJRExpression.g:1863:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringLiteral"


    // $ANTLR start "ruleStringLiteral"
    // InternalJavaJRExpression.g:1870:1: ruleStringLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1873:28: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) )
            // InternalJavaJRExpression.g:1874:1: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            {
            // InternalJavaJRExpression.g:1874:1: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            // InternalJavaJRExpression.g:1874:2: () ( (lv_value_1_0= RULE_STRING ) )
            {
            // InternalJavaJRExpression.g:1874:2: ()
            // InternalJavaJRExpression.g:1875:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getStringLiteralAccess().getStringLiteralAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1880:2: ( (lv_value_1_0= RULE_STRING ) )
            // InternalJavaJRExpression.g:1881:1: (lv_value_1_0= RULE_STRING )
            {
            // InternalJavaJRExpression.g:1881:1: (lv_value_1_0= RULE_STRING )
            // InternalJavaJRExpression.g:1882:3: lv_value_1_0= RULE_STRING
            {
            lv_value_1_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_1_0, grammarAccess.getStringLiteralAccess().getValueSTRINGTerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getStringLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.STRING");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringLiteral"


    // $ANTLR start "entryRuleBooleanLiteral"
    // InternalJavaJRExpression.g:1906:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // InternalJavaJRExpression.g:1907:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // InternalJavaJRExpression.g:1908:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanLiteral"


    // $ANTLR start "ruleBooleanLiteral"
    // InternalJavaJRExpression.g:1915:1: ruleBooleanLiteral returns [EObject current=null] : ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_isTrue_2_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1918:28: ( ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) ) )
            // InternalJavaJRExpression.g:1919:1: ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) )
            {
            // InternalJavaJRExpression.g:1919:1: ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) )
            // InternalJavaJRExpression.g:1919:2: () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) )
            {
            // InternalJavaJRExpression.g:1919:2: ()
            // InternalJavaJRExpression.g:1920:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getBooleanLiteralAccess().getBooleanLiteralAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:1925:2: (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==48) ) {
                alt25=1;
            }
            else if ( (LA25_0==49) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalJavaJRExpression.g:1925:4: otherlv_1= 'false'
                    {
                    otherlv_1=(Token)match(input,48,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getBooleanLiteralAccess().getFalseKeyword_1_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:1930:6: ( (lv_isTrue_2_0= 'true' ) )
                    {
                    // InternalJavaJRExpression.g:1930:6: ( (lv_isTrue_2_0= 'true' ) )
                    // InternalJavaJRExpression.g:1931:1: (lv_isTrue_2_0= 'true' )
                    {
                    // InternalJavaJRExpression.g:1931:1: (lv_isTrue_2_0= 'true' )
                    // InternalJavaJRExpression.g:1932:3: lv_isTrue_2_0= 'true'
                    {
                    lv_isTrue_2_0=(Token)match(input,49,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isTrue_2_0, grammarAccess.getBooleanLiteralAccess().getIsTrueTrueKeyword_1_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBooleanLiteralRule());
                      	        }
                             		setWithLastConsumed(current, "isTrue", true, "true");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanLiteral"


    // $ANTLR start "entryRuleNullLiteral"
    // InternalJavaJRExpression.g:1953:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // InternalJavaJRExpression.g:1954:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // InternalJavaJRExpression.g:1955:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNullLiteral"


    // $ANTLR start "ruleNullLiteral"
    // InternalJavaJRExpression.g:1962:1: ruleNullLiteral returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1965:28: ( ( () otherlv_1= 'null' ) )
            // InternalJavaJRExpression.g:1966:1: ( () otherlv_1= 'null' )
            {
            // InternalJavaJRExpression.g:1966:1: ( () otherlv_1= 'null' )
            // InternalJavaJRExpression.g:1966:2: () otherlv_1= 'null'
            {
            // InternalJavaJRExpression.g:1966:2: ()
            // InternalJavaJRExpression.g:1967:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getNullLiteralAccess().getNullLiteralAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,50,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNullLiteralAccess().getNullKeyword_1());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNullLiteral"


    // $ANTLR start "entryRuleParExpression"
    // InternalJavaJRExpression.g:1984:1: entryRuleParExpression returns [EObject current=null] : iv_ruleParExpression= ruleParExpression EOF ;
    public final EObject entryRuleParExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParExpression = null;


        try {
            // InternalJavaJRExpression.g:1985:2: (iv_ruleParExpression= ruleParExpression EOF )
            // InternalJavaJRExpression.g:1986:2: iv_ruleParExpression= ruleParExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParExpression=ruleParExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParExpression"


    // $ANTLR start "ruleParExpression"
    // InternalJavaJRExpression.g:1993:1: ruleParExpression returns [EObject current=null] : (otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')' ) ;
    public final EObject ruleParExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_JasperReportsExpression_1 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:1996:28: ( (otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')' ) )
            // InternalJavaJRExpression.g:1997:1: (otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')' )
            {
            // InternalJavaJRExpression.g:1997:1: (otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')' )
            // InternalJavaJRExpression.g:1997:3: otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,51,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParExpressionAccess().getJasperReportsExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_22);
            this_JasperReportsExpression_1=ruleJasperReportsExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_JasperReportsExpression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,52,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getParExpressionAccess().getRightParenthesisKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParExpression"


    // $ANTLR start "entryRuleCastedExpression"
    // InternalJavaJRExpression.g:2022:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // InternalJavaJRExpression.g:2023:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // InternalJavaJRExpression.g:2024:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCastedExpression"


    // $ANTLR start "ruleCastedExpression"
    // InternalJavaJRExpression.g:2031:1: ruleCastedExpression returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_castType_2_0 = null;

        EObject lv_castedExpr_4_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2034:28: ( ( () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) ) ) )
            // InternalJavaJRExpression.g:2035:1: ( () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) ) )
            {
            // InternalJavaJRExpression.g:2035:1: ( () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) ) )
            // InternalJavaJRExpression.g:2035:2: () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) )
            {
            // InternalJavaJRExpression.g:2035:2: ()
            // InternalJavaJRExpression.g:2036:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getCastedExpressionAccess().getCastedExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,51,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:2045:1: ( (lv_castType_2_0= ruleType ) )
            // InternalJavaJRExpression.g:2046:1: (lv_castType_2_0= ruleType )
            {
            // InternalJavaJRExpression.g:2046:1: (lv_castType_2_0= ruleType )
            // InternalJavaJRExpression.g:2047:3: lv_castType_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getCastTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_22);
            lv_castType_2_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCastedExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"castType",
                      		lv_castType_2_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,52,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_3());
                  
            }
            // InternalJavaJRExpression.g:2067:1: ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) )
            // InternalJavaJRExpression.g:2068:1: (lv_castedExpr_4_0= ruleJasperReportsExpression )
            {
            // InternalJavaJRExpression.g:2068:1: (lv_castedExpr_4_0= ruleJasperReportsExpression )
            // InternalJavaJRExpression.g:2069:3: lv_castedExpr_4_0= ruleJasperReportsExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getCastedExprJasperReportsExpressionParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_castedExpr_4_0=ruleJasperReportsExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCastedExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"castedExpr",
                      		lv_castedExpr_4_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JasperReportsExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCastedExpression"


    // $ANTLR start "entryRuleType"
    // InternalJavaJRExpression.g:2093:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalJavaJRExpression.g:2094:2: (iv_ruleType= ruleType EOF )
            // InternalJavaJRExpression.g:2095:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalJavaJRExpression.g:2102:1: ruleType returns [EObject current=null] : ( () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) ) ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_primitiveType_1_0 = null;

        EObject lv_jvmType_2_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2105:28: ( ( () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) ) ) )
            // InternalJavaJRExpression.g:2106:1: ( () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) ) )
            {
            // InternalJavaJRExpression.g:2106:1: ( () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) ) )
            // InternalJavaJRExpression.g:2106:2: () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) )
            {
            // InternalJavaJRExpression.g:2106:2: ()
            // InternalJavaJRExpression.g:2107:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTypeAccess().getTypeAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:2112:2: ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=60 && LA26_0<=67)) ) {
                alt26=1;
            }
            else if ( (LA26_0==RULE_ID) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // InternalJavaJRExpression.g:2112:3: ( (lv_primitiveType_1_0= rulePrimitiveType ) )
                    {
                    // InternalJavaJRExpression.g:2112:3: ( (lv_primitiveType_1_0= rulePrimitiveType ) )
                    // InternalJavaJRExpression.g:2113:1: (lv_primitiveType_1_0= rulePrimitiveType )
                    {
                    // InternalJavaJRExpression.g:2113:1: (lv_primitiveType_1_0= rulePrimitiveType )
                    // InternalJavaJRExpression.g:2114:3: lv_primitiveType_1_0= rulePrimitiveType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeAccess().getPrimitiveTypePrimitiveTypeParserRuleCall_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_primitiveType_1_0=rulePrimitiveType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeRule());
                      	        }
                             		set(
                             			current, 
                             			"primitiveType",
                              		true, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.PrimitiveType");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:2131:6: ( (lv_jvmType_2_0= ruleJvmTypeReference ) )
                    {
                    // InternalJavaJRExpression.g:2131:6: ( (lv_jvmType_2_0= ruleJvmTypeReference ) )
                    // InternalJavaJRExpression.g:2132:1: (lv_jvmType_2_0= ruleJvmTypeReference )
                    {
                    // InternalJavaJRExpression.g:2132:1: (lv_jvmType_2_0= ruleJvmTypeReference )
                    // InternalJavaJRExpression.g:2133:3: lv_jvmType_2_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeAccess().getJvmTypeJvmTypeReferenceParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_jvmType_2_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeRule());
                      	        }
                             		set(
                             			current, 
                             			"jvmType",
                              		lv_jvmType_2_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JvmTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleArrayCreator"
    // InternalJavaJRExpression.g:2157:1: entryRuleArrayCreator returns [EObject current=null] : iv_ruleArrayCreator= ruleArrayCreator EOF ;
    public final EObject entryRuleArrayCreator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayCreator = null;


        try {
            // InternalJavaJRExpression.g:2158:2: (iv_ruleArrayCreator= ruleArrayCreator EOF )
            // InternalJavaJRExpression.g:2159:2: iv_ruleArrayCreator= ruleArrayCreator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayCreatorRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleArrayCreator=ruleArrayCreator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayCreator; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayCreator"


    // $ANTLR start "ruleArrayCreator"
    // InternalJavaJRExpression.g:2166:1: ruleArrayCreator returns [EObject current=null] : ( () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) ) ) ;
    public final EObject ruleArrayCreator() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_type_2_0 = null;

        EObject lv_size_4_0 = null;

        EObject lv_initialization_8_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2169:28: ( ( () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) ) ) )
            // InternalJavaJRExpression.g:2170:1: ( () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) ) )
            {
            // InternalJavaJRExpression.g:2170:1: ( () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) ) )
            // InternalJavaJRExpression.g:2170:2: () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) )
            {
            // InternalJavaJRExpression.g:2170:2: ()
            // InternalJavaJRExpression.g:2171:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrayCreatorAccess().getArrayCreatorAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,45,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrayCreatorAccess().getNewKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:2180:1: ( (lv_type_2_0= ruleType ) )
            // InternalJavaJRExpression.g:2181:1: (lv_type_2_0= ruleType )
            {
            // InternalJavaJRExpression.g:2181:1: (lv_type_2_0= ruleType )
            // InternalJavaJRExpression.g:2182:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArrayCreatorAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_23);
            lv_type_2_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getArrayCreatorRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_2_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalJavaJRExpression.g:2198:2: ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==46) ) {
                int LA29_1 = input.LA(2);

                if ( (LA29_1==RULE_INT) ) {
                    alt29=1;
                }
                else if ( (LA29_1==47) ) {
                    alt29=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // InternalJavaJRExpression.g:2198:3: (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+
                    {
                    // InternalJavaJRExpression.g:2198:3: (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==46) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // InternalJavaJRExpression.g:2198:5: otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']'
                    	    {
                    	    otherlv_3=(Token)match(input,46,FOLLOW_19); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getArrayCreatorAccess().getLeftSquareBracketKeyword_3_0_0());
                    	          
                    	    }
                    	    // InternalJavaJRExpression.g:2202:1: ( (lv_size_4_0= ruleIntLiteral ) )
                    	    // InternalJavaJRExpression.g:2203:1: (lv_size_4_0= ruleIntLiteral )
                    	    {
                    	    // InternalJavaJRExpression.g:2203:1: (lv_size_4_0= ruleIntLiteral )
                    	    // InternalJavaJRExpression.g:2204:3: lv_size_4_0= ruleIntLiteral
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getArrayCreatorAccess().getSizeIntLiteralParserRuleCall_3_0_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_20);
                    	    lv_size_4_0=ruleIntLiteral();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getArrayCreatorRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"size",
                    	              		lv_size_4_0, 
                    	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.IntLiteral");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_5=(Token)match(input,47,FOLLOW_21); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getArrayCreatorAccess().getRightSquareBracketKeyword_3_0_2());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:2225:6: ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) )
                    {
                    // InternalJavaJRExpression.g:2225:6: ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) )
                    // InternalJavaJRExpression.g:2225:7: (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) )
                    {
                    // InternalJavaJRExpression.g:2225:7: (otherlv_6= '[' otherlv_7= ']' )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==46) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // InternalJavaJRExpression.g:2225:9: otherlv_6= '[' otherlv_7= ']'
                    	    {
                    	    otherlv_6=(Token)match(input,46,FOLLOW_20); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_6, grammarAccess.getArrayCreatorAccess().getLeftSquareBracketKeyword_3_1_0_0());
                    	          
                    	    }
                    	    otherlv_7=(Token)match(input,47,FOLLOW_24); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_7, grammarAccess.getArrayCreatorAccess().getRightSquareBracketKeyword_3_1_0_1());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt28 >= 1 ) break loop28;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(28, input);
                                throw eee;
                        }
                        cnt28++;
                    } while (true);

                    // InternalJavaJRExpression.g:2233:3: ( (lv_initialization_8_0= ruleArrayInitializer ) )
                    // InternalJavaJRExpression.g:2234:1: (lv_initialization_8_0= ruleArrayInitializer )
                    {
                    // InternalJavaJRExpression.g:2234:1: (lv_initialization_8_0= ruleArrayInitializer )
                    // InternalJavaJRExpression.g:2235:3: lv_initialization_8_0= ruleArrayInitializer
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArrayCreatorAccess().getInitializationArrayInitializerParserRuleCall_3_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_initialization_8_0=ruleArrayInitializer();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getArrayCreatorRule());
                      	        }
                             		set(
                             			current, 
                             			"initialization",
                              		lv_initialization_8_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.ArrayInitializer");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayCreator"


    // $ANTLR start "entryRuleArrayInitializer"
    // InternalJavaJRExpression.g:2259:1: entryRuleArrayInitializer returns [EObject current=null] : iv_ruleArrayInitializer= ruleArrayInitializer EOF ;
    public final EObject entryRuleArrayInitializer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayInitializer = null;


        try {
            // InternalJavaJRExpression.g:2260:2: (iv_ruleArrayInitializer= ruleArrayInitializer EOF )
            // InternalJavaJRExpression.g:2261:2: iv_ruleArrayInitializer= ruleArrayInitializer EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayInitializerRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleArrayInitializer=ruleArrayInitializer();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayInitializer; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayInitializer"


    // $ANTLR start "ruleArrayInitializer"
    // InternalJavaJRExpression.g:2268:1: ruleArrayInitializer returns [EObject current=null] : ( () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}' ) ;
    public final EObject ruleArrayInitializer() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_initialization_2_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2271:28: ( ( () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}' ) )
            // InternalJavaJRExpression.g:2272:1: ( () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}' )
            {
            // InternalJavaJRExpression.g:2272:1: ( () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}' )
            // InternalJavaJRExpression.g:2272:2: () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}'
            {
            // InternalJavaJRExpression.g:2272:2: ()
            // InternalJavaJRExpression.g:2273:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrayInitializerAccess().getArrayInitializerAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,53,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrayInitializerAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:2282:1: ( (lv_initialization_2_0= ruleExpressionList ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=RULE_INT && LA30_0<=RULE_ID)||(LA30_0>=33 && LA30_0<=34)||(LA30_0>=38 && LA30_0<=39)||(LA30_0>=41 && LA30_0<=45)||(LA30_0>=48 && LA30_0<=51)) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalJavaJRExpression.g:2283:1: (lv_initialization_2_0= ruleExpressionList )
                    {
                    // InternalJavaJRExpression.g:2283:1: (lv_initialization_2_0= ruleExpressionList )
                    // InternalJavaJRExpression.g:2284:3: lv_initialization_2_0= ruleExpressionList
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArrayInitializerAccess().getInitializationExpressionListParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_26);
                    lv_initialization_2_0=ruleExpressionList();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getArrayInitializerRule());
                      	        }
                             		set(
                             			current, 
                             			"initialization",
                              		lv_initialization_2_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.ExpressionList");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,54,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getArrayInitializerAccess().getRightCurlyBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayInitializer"


    // $ANTLR start "entryRuleMethodInvocation"
    // InternalJavaJRExpression.g:2312:1: entryRuleMethodInvocation returns [EObject current=null] : iv_ruleMethodInvocation= ruleMethodInvocation EOF ;
    public final EObject entryRuleMethodInvocation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethodInvocation = null;


        try {
            // InternalJavaJRExpression.g:2313:2: (iv_ruleMethodInvocation= ruleMethodInvocation EOF )
            // InternalJavaJRExpression.g:2314:2: iv_ruleMethodInvocation= ruleMethodInvocation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMethodInvocationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMethodInvocation=ruleMethodInvocation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMethodInvocation; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethodInvocation"


    // $ANTLR start "ruleMethodInvocation"
    // InternalJavaJRExpression.g:2321:1: ruleMethodInvocation returns [EObject current=null] : ( () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) ) ) ;
    public final EObject ruleMethodInvocation() throws RecognitionException {
        EObject current = null;

        EObject lv_fullyQualifiedMethodName_1_0 = null;

        EObject lv_args_2_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2324:28: ( ( () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) ) ) )
            // InternalJavaJRExpression.g:2325:1: ( () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) ) )
            {
            // InternalJavaJRExpression.g:2325:1: ( () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) ) )
            // InternalJavaJRExpression.g:2325:2: () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) )
            {
            // InternalJavaJRExpression.g:2325:2: ()
            // InternalJavaJRExpression.g:2326:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getMethodInvocationAccess().getMethodInvocationAction_0(),
                          current);
                  
            }

            }

            // InternalJavaJRExpression.g:2331:2: ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) )
            // InternalJavaJRExpression.g:2332:1: (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName )
            {
            // InternalJavaJRExpression.g:2332:1: (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName )
            // InternalJavaJRExpression.g:2333:3: lv_fullyQualifiedMethodName_1_0= ruleFullMethodName
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMethodInvocationAccess().getFullyQualifiedMethodNameFullMethodNameParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_27);
            lv_fullyQualifiedMethodName_1_0=ruleFullMethodName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMethodInvocationRule());
              	        }
                     		set(
                     			current, 
                     			"fullyQualifiedMethodName",
                      		lv_fullyQualifiedMethodName_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.FullMethodName");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalJavaJRExpression.g:2349:2: ( (lv_args_2_0= ruleArguments ) )
            // InternalJavaJRExpression.g:2350:1: (lv_args_2_0= ruleArguments )
            {
            // InternalJavaJRExpression.g:2350:1: (lv_args_2_0= ruleArguments )
            // InternalJavaJRExpression.g:2351:3: lv_args_2_0= ruleArguments
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMethodInvocationAccess().getArgsArgumentsParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_args_2_0=ruleArguments();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMethodInvocationRule());
              	        }
                     		set(
                     			current, 
                     			"args",
                      		lv_args_2_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.Arguments");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethodInvocation"


    // $ANTLR start "entryRuleFullMethodName"
    // InternalJavaJRExpression.g:2375:1: entryRuleFullMethodName returns [EObject current=null] : iv_ruleFullMethodName= ruleFullMethodName EOF ;
    public final EObject entryRuleFullMethodName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullMethodName = null;


        try {
            // InternalJavaJRExpression.g:2376:2: (iv_ruleFullMethodName= ruleFullMethodName EOF )
            // InternalJavaJRExpression.g:2377:2: iv_ruleFullMethodName= ruleFullMethodName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFullMethodNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFullMethodName=ruleFullMethodName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFullMethodName; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFullMethodName"


    // $ANTLR start "ruleFullMethodName"
    // InternalJavaJRExpression.g:2384:1: ruleFullMethodName returns [EObject current=null] : ( ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) ) ) ;
    public final EObject ruleFullMethodName() throws RecognitionException {
        EObject current = null;

        Token lv_dots_1_0=null;
        AntlrDatatypeRuleToken lv_prefixQMN_0_0 = null;

        AntlrDatatypeRuleToken lv_methodName_2_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2387:28: ( ( ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) ) ) )
            // InternalJavaJRExpression.g:2388:1: ( ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) ) )
            {
            // InternalJavaJRExpression.g:2388:1: ( ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) ) )
            // InternalJavaJRExpression.g:2388:2: ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) )
            {
            // InternalJavaJRExpression.g:2388:2: ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==RULE_ID) ) {
                    int LA31_1 = input.LA(2);

                    if ( (LA31_1==40) ) {
                        alt31=1;
                    }


                }


                switch (alt31) {
            	case 1 :
            	    // InternalJavaJRExpression.g:2388:3: ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) )
            	    {
            	    // InternalJavaJRExpression.g:2388:3: ( (lv_prefixQMN_0_0= ruleValidID ) )
            	    // InternalJavaJRExpression.g:2389:1: (lv_prefixQMN_0_0= ruleValidID )
            	    {
            	    // InternalJavaJRExpression.g:2389:1: (lv_prefixQMN_0_0= ruleValidID )
            	    // InternalJavaJRExpression.g:2390:3: lv_prefixQMN_0_0= ruleValidID
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getFullMethodNameAccess().getPrefixQMNValidIDParserRuleCall_0_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_14);
            	    lv_prefixQMN_0_0=ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getFullMethodNameRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"prefixQMN",
            	              		lv_prefixQMN_0_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.ValidID");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // InternalJavaJRExpression.g:2406:2: ( (lv_dots_1_0= '.' ) )
            	    // InternalJavaJRExpression.g:2407:1: (lv_dots_1_0= '.' )
            	    {
            	    // InternalJavaJRExpression.g:2407:1: (lv_dots_1_0= '.' )
            	    // InternalJavaJRExpression.g:2408:3: lv_dots_1_0= '.'
            	    {
            	    lv_dots_1_0=(Token)match(input,40,FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_dots_1_0, grammarAccess.getFullMethodNameAccess().getDotsFullStopKeyword_0_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getFullMethodNameRule());
            	      	        }
            	             		addWithLastConsumed(current, "dots", lv_dots_1_0, ".");
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            // InternalJavaJRExpression.g:2421:4: ( (lv_methodName_2_0= ruleValidID ) )
            // InternalJavaJRExpression.g:2422:1: (lv_methodName_2_0= ruleValidID )
            {
            // InternalJavaJRExpression.g:2422:1: (lv_methodName_2_0= ruleValidID )
            // InternalJavaJRExpression.g:2423:3: lv_methodName_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFullMethodNameAccess().getMethodNameValidIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_methodName_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFullMethodNameRule());
              	        }
                     		set(
                     			current, 
                     			"methodName",
                      		lv_methodName_2_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.ValidID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFullMethodName"


    // $ANTLR start "entryRuleArguments"
    // InternalJavaJRExpression.g:2447:1: entryRuleArguments returns [EObject current=null] : iv_ruleArguments= ruleArguments EOF ;
    public final EObject entryRuleArguments() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArguments = null;


        try {
            // InternalJavaJRExpression.g:2448:2: (iv_ruleArguments= ruleArguments EOF )
            // InternalJavaJRExpression.g:2449:2: iv_ruleArguments= ruleArguments EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArgumentsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleArguments=ruleArguments();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArguments; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArguments"


    // $ANTLR start "ruleArguments"
    // InternalJavaJRExpression.g:2456:1: ruleArguments returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')' ) ;
    public final EObject ruleArguments() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_exprLst_2_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2459:28: ( ( () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')' ) )
            // InternalJavaJRExpression.g:2460:1: ( () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')' )
            {
            // InternalJavaJRExpression.g:2460:1: ( () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')' )
            // InternalJavaJRExpression.g:2460:2: () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')'
            {
            // InternalJavaJRExpression.g:2460:2: ()
            // InternalJavaJRExpression.g:2461:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArgumentsAccess().getArgumentsAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,51,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArgumentsAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:2470:1: ( (lv_exprLst_2_0= ruleExpressionList ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=RULE_INT && LA32_0<=RULE_ID)||(LA32_0>=33 && LA32_0<=34)||(LA32_0>=38 && LA32_0<=39)||(LA32_0>=41 && LA32_0<=45)||(LA32_0>=48 && LA32_0<=51)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalJavaJRExpression.g:2471:1: (lv_exprLst_2_0= ruleExpressionList )
                    {
                    // InternalJavaJRExpression.g:2471:1: (lv_exprLst_2_0= ruleExpressionList )
                    // InternalJavaJRExpression.g:2472:3: lv_exprLst_2_0= ruleExpressionList
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArgumentsAccess().getExprLstExpressionListParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_22);
                    lv_exprLst_2_0=ruleExpressionList();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getArgumentsRule());
                      	        }
                             		set(
                             			current, 
                             			"exprLst",
                              		lv_exprLst_2_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.ExpressionList");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,52,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getArgumentsAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArguments"


    // $ANTLR start "entryRuleExpressionList"
    // InternalJavaJRExpression.g:2500:1: entryRuleExpressionList returns [EObject current=null] : iv_ruleExpressionList= ruleExpressionList EOF ;
    public final EObject entryRuleExpressionList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionList = null;


        try {
            // InternalJavaJRExpression.g:2501:2: (iv_ruleExpressionList= ruleExpressionList EOF )
            // InternalJavaJRExpression.g:2502:2: iv_ruleExpressionList= ruleExpressionList EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionListRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpressionList=ruleExpressionList();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpressionList; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpressionList"


    // $ANTLR start "ruleExpressionList"
    // InternalJavaJRExpression.g:2509:1: ruleExpressionList returns [EObject current=null] : ( ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )* ) ;
    public final EObject ruleExpressionList() throws RecognitionException {
        EObject current = null;

        Token lv_commas_1_0=null;
        EObject lv_expressions_0_0 = null;

        EObject lv_expressions_2_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2512:28: ( ( ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )* ) )
            // InternalJavaJRExpression.g:2513:1: ( ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )* )
            {
            // InternalJavaJRExpression.g:2513:1: ( ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )* )
            // InternalJavaJRExpression.g:2513:2: ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )*
            {
            // InternalJavaJRExpression.g:2513:2: ( (lv_expressions_0_0= ruleJasperReportsExpression ) )
            // InternalJavaJRExpression.g:2514:1: (lv_expressions_0_0= ruleJasperReportsExpression )
            {
            // InternalJavaJRExpression.g:2514:1: (lv_expressions_0_0= ruleJasperReportsExpression )
            // InternalJavaJRExpression.g:2515:3: lv_expressions_0_0= ruleJasperReportsExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpressionListAccess().getExpressionsJasperReportsExpressionParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_29);
            lv_expressions_0_0=ruleJasperReportsExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpressionListRule());
              	        }
                     		add(
                     			current, 
                     			"expressions",
                      		lv_expressions_0_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JasperReportsExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalJavaJRExpression.g:2531:2: ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==55) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalJavaJRExpression.g:2531:3: ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) )
            	    {
            	    // InternalJavaJRExpression.g:2531:3: ( (lv_commas_1_0= ',' ) )
            	    // InternalJavaJRExpression.g:2532:1: (lv_commas_1_0= ',' )
            	    {
            	    // InternalJavaJRExpression.g:2532:1: (lv_commas_1_0= ',' )
            	    // InternalJavaJRExpression.g:2533:3: lv_commas_1_0= ','
            	    {
            	    lv_commas_1_0=(Token)match(input,55,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_commas_1_0, grammarAccess.getExpressionListAccess().getCommasCommaKeyword_1_0_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getExpressionListRule());
            	      	        }
            	             		addWithLastConsumed(current, "commas", lv_commas_1_0, ",");
            	      	    
            	    }

            	    }


            	    }

            	    // InternalJavaJRExpression.g:2546:2: ( (lv_expressions_2_0= ruleJasperReportsExpression ) )
            	    // InternalJavaJRExpression.g:2547:1: (lv_expressions_2_0= ruleJasperReportsExpression )
            	    {
            	    // InternalJavaJRExpression.g:2547:1: (lv_expressions_2_0= ruleJasperReportsExpression )
            	    // InternalJavaJRExpression.g:2548:3: lv_expressions_2_0= ruleJasperReportsExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExpressionListAccess().getExpressionsJasperReportsExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_29);
            	    lv_expressions_2_0=ruleJasperReportsExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExpressionListRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"expressions",
            	              		lv_expressions_2_0, 
            	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JasperReportsExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpressionList"


    // $ANTLR start "entryRuleJvmTypeReference"
    // InternalJavaJRExpression.g:2572:1: entryRuleJvmTypeReference returns [EObject current=null] : iv_ruleJvmTypeReference= ruleJvmTypeReference EOF ;
    public final EObject entryRuleJvmTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmTypeReference = null;


        try {
            // InternalJavaJRExpression.g:2573:2: (iv_ruleJvmTypeReference= ruleJvmTypeReference EOF )
            // InternalJavaJRExpression.g:2574:2: iv_ruleJvmTypeReference= ruleJvmTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmTypeReference=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmTypeReference; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmTypeReference"


    // $ANTLR start "ruleJvmTypeReference"
    // InternalJavaJRExpression.g:2581:1: ruleJvmTypeReference returns [EObject current=null] : (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* ) ;
    public final EObject ruleJvmTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject this_JvmParameterizedTypeReference_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2584:28: ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* ) )
            // InternalJavaJRExpression.g:2585:1: (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* )
            {
            // InternalJavaJRExpression.g:2585:1: (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* )
            // InternalJavaJRExpression.g:2586:5: this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getJvmTypeReferenceAccess().getJvmParameterizedTypeReferenceParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_21);
            this_JvmParameterizedTypeReference_0=ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_JvmParameterizedTypeReference_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:2594:1: ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==46) ) {
                    int LA34_2 = input.LA(2);

                    if ( (LA34_2==47) ) {
                        int LA34_3 = input.LA(3);

                        if ( (synpred11_InternalJavaJRExpression()) ) {
                            alt34=1;
                        }


                    }


                }


                switch (alt34) {
            	case 1 :
            	    // InternalJavaJRExpression.g:2594:2: ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' )
            	    {
            	    // InternalJavaJRExpression.g:2597:5: ( () otherlv_2= '[' otherlv_3= ']' )
            	    // InternalJavaJRExpression.g:2597:6: () otherlv_2= '[' otherlv_3= ']'
            	    {
            	    // InternalJavaJRExpression.g:2597:6: ()
            	    // InternalJavaJRExpression.g:2598:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getJvmTypeReferenceAccess().getJvmGenericArrayTypeReferenceComponentTypeAction_1_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,46,FOLLOW_20); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getJvmTypeReferenceAccess().getLeftSquareBracketKeyword_1_0_1());
            	          
            	    }
            	    otherlv_3=(Token)match(input,47,FOLLOW_21); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_3, grammarAccess.getJvmTypeReferenceAccess().getRightSquareBracketKeyword_1_0_2());
            	          
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmTypeReference"


    // $ANTLR start "entryRuleJvmParameterizedTypeReference"
    // InternalJavaJRExpression.g:2619:1: entryRuleJvmParameterizedTypeReference returns [EObject current=null] : iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF ;
    public final EObject entryRuleJvmParameterizedTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmParameterizedTypeReference = null;


        try {
            // InternalJavaJRExpression.g:2620:2: (iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF )
            // InternalJavaJRExpression.g:2621:2: iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmParameterizedTypeReference=ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmParameterizedTypeReference; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmParameterizedTypeReference"


    // $ANTLR start "ruleJvmParameterizedTypeReference"
    // InternalJavaJRExpression.g:2628:1: ruleJvmParameterizedTypeReference returns [EObject current=null] : ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ) ;
    public final EObject ruleJvmParameterizedTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_arguments_2_0 = null;

        EObject lv_arguments_4_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2631:28: ( ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ) )
            // InternalJavaJRExpression.g:2632:1: ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? )
            {
            // InternalJavaJRExpression.g:2632:1: ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? )
            // InternalJavaJRExpression.g:2632:2: ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?
            {
            // InternalJavaJRExpression.g:2632:2: ( ( ruleQualifiedName ) )
            // InternalJavaJRExpression.g:2633:1: ( ruleQualifiedName )
            {
            // InternalJavaJRExpression.g:2633:1: ( ruleQualifiedName )
            // InternalJavaJRExpression.g:2634:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getJvmParameterizedTypeReferenceRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_30);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalJavaJRExpression.g:2647:2: ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?
            int alt36=2;
            alt36 = dfa36.predict(input);
            switch (alt36) {
                case 1 :
                    // InternalJavaJRExpression.g:2647:3: ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>'
                    {
                    // InternalJavaJRExpression.g:2647:3: ( ( '<' )=>otherlv_1= '<' )
                    // InternalJavaJRExpression.g:2647:4: ( '<' )=>otherlv_1= '<'
                    {
                    otherlv_1=(Token)match(input,56,FOLLOW_31); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_0());
                          
                    }

                    }

                    // InternalJavaJRExpression.g:2652:2: ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) )
                    // InternalJavaJRExpression.g:2653:1: (lv_arguments_2_0= ruleJvmArgumentTypeReference )
                    {
                    // InternalJavaJRExpression.g:2653:1: (lv_arguments_2_0= ruleJvmArgumentTypeReference )
                    // InternalJavaJRExpression.g:2654:3: lv_arguments_2_0= ruleJvmArgumentTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_32);
                    lv_arguments_2_0=ruleJvmArgumentTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getJvmParameterizedTypeReferenceRule());
                      	        }
                             		add(
                             			current, 
                             			"arguments",
                              		lv_arguments_2_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JvmArgumentTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalJavaJRExpression.g:2670:2: (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==55) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // InternalJavaJRExpression.g:2670:4: otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    {
                    	    otherlv_3=(Token)match(input,55,FOLLOW_31); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_2_0());
                    	          
                    	    }
                    	    // InternalJavaJRExpression.g:2674:1: ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    // InternalJavaJRExpression.g:2675:1: (lv_arguments_4_0= ruleJvmArgumentTypeReference )
                    	    {
                    	    // InternalJavaJRExpression.g:2675:1: (lv_arguments_4_0= ruleJvmArgumentTypeReference )
                    	    // InternalJavaJRExpression.g:2676:3: lv_arguments_4_0= ruleJvmArgumentTypeReference
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_32);
                    	    lv_arguments_4_0=ruleJvmArgumentTypeReference();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getJvmParameterizedTypeReferenceRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"arguments",
                    	              		lv_arguments_4_0, 
                    	              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JvmArgumentTypeReference");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,57,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getJvmParameterizedTypeReferenceAccess().getGreaterThanSignKeyword_1_3());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmParameterizedTypeReference"


    // $ANTLR start "entryRuleJvmArgumentTypeReference"
    // InternalJavaJRExpression.g:2704:1: entryRuleJvmArgumentTypeReference returns [EObject current=null] : iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF ;
    public final EObject entryRuleJvmArgumentTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmArgumentTypeReference = null;


        try {
            // InternalJavaJRExpression.g:2705:2: (iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF )
            // InternalJavaJRExpression.g:2706:2: iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmArgumentTypeReference=ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmArgumentTypeReference; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmArgumentTypeReference"


    // $ANTLR start "ruleJvmArgumentTypeReference"
    // InternalJavaJRExpression.g:2713:1: ruleJvmArgumentTypeReference returns [EObject current=null] : (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference ) ;
    public final EObject ruleJvmArgumentTypeReference() throws RecognitionException {
        EObject current = null;

        EObject this_JvmTypeReference_0 = null;

        EObject this_JvmWildcardTypeReference_1 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2716:28: ( (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference ) )
            // InternalJavaJRExpression.g:2717:1: (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference )
            {
            // InternalJavaJRExpression.g:2717:1: (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==RULE_ID) ) {
                alt37=1;
            }
            else if ( (LA37_0==26) ) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // InternalJavaJRExpression.g:2718:5: this_JvmTypeReference_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmTypeReferenceParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_JvmTypeReference_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JvmTypeReference_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:2728:5: this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmWildcardTypeReferenceParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_JvmWildcardTypeReference_1=ruleJvmWildcardTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JvmWildcardTypeReference_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmArgumentTypeReference"


    // $ANTLR start "entryRuleJvmWildcardTypeReference"
    // InternalJavaJRExpression.g:2744:1: entryRuleJvmWildcardTypeReference returns [EObject current=null] : iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF ;
    public final EObject entryRuleJvmWildcardTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmWildcardTypeReference = null;


        try {
            // InternalJavaJRExpression.g:2745:2: (iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF )
            // InternalJavaJRExpression.g:2746:2: iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmWildcardTypeReference=ruleJvmWildcardTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmWildcardTypeReference; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmWildcardTypeReference"


    // $ANTLR start "ruleJvmWildcardTypeReference"
    // InternalJavaJRExpression.g:2753:1: ruleJvmWildcardTypeReference returns [EObject current=null] : ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? ) ;
    public final EObject ruleJvmWildcardTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_constraints_2_0 = null;

        EObject lv_constraints_3_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2756:28: ( ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? ) )
            // InternalJavaJRExpression.g:2757:1: ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? )
            {
            // InternalJavaJRExpression.g:2757:1: ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? )
            // InternalJavaJRExpression.g:2757:2: () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )?
            {
            // InternalJavaJRExpression.g:2757:2: ()
            // InternalJavaJRExpression.g:2758:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJvmWildcardTypeReferenceAccess().getJvmWildcardTypeReferenceAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,26,FOLLOW_33); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJvmWildcardTypeReferenceAccess().getQuestionMarkKeyword_1());
                  
            }
            // InternalJavaJRExpression.g:2767:1: ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )?
            int alt38=3;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==58) ) {
                alt38=1;
            }
            else if ( (LA38_0==59) ) {
                alt38=2;
            }
            switch (alt38) {
                case 1 :
                    // InternalJavaJRExpression.g:2767:2: ( (lv_constraints_2_0= ruleJvmUpperBound ) )
                    {
                    // InternalJavaJRExpression.g:2767:2: ( (lv_constraints_2_0= ruleJvmUpperBound ) )
                    // InternalJavaJRExpression.g:2768:1: (lv_constraints_2_0= ruleJvmUpperBound )
                    {
                    // InternalJavaJRExpression.g:2768:1: (lv_constraints_2_0= ruleJvmUpperBound )
                    // InternalJavaJRExpression.g:2769:3: lv_constraints_2_0= ruleJvmUpperBound
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_constraints_2_0=ruleJvmUpperBound();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getJvmWildcardTypeReferenceRule());
                      	        }
                             		add(
                             			current, 
                             			"constraints",
                              		lv_constraints_2_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JvmUpperBound");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:2786:6: ( (lv_constraints_3_0= ruleJvmLowerBound ) )
                    {
                    // InternalJavaJRExpression.g:2786:6: ( (lv_constraints_3_0= ruleJvmLowerBound ) )
                    // InternalJavaJRExpression.g:2787:1: (lv_constraints_3_0= ruleJvmLowerBound )
                    {
                    // InternalJavaJRExpression.g:2787:1: (lv_constraints_3_0= ruleJvmLowerBound )
                    // InternalJavaJRExpression.g:2788:3: lv_constraints_3_0= ruleJvmLowerBound
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_constraints_3_0=ruleJvmLowerBound();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getJvmWildcardTypeReferenceRule());
                      	        }
                             		add(
                             			current, 
                             			"constraints",
                              		lv_constraints_3_0, 
                              		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JvmLowerBound");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmWildcardTypeReference"


    // $ANTLR start "entryRuleJvmUpperBound"
    // InternalJavaJRExpression.g:2812:1: entryRuleJvmUpperBound returns [EObject current=null] : iv_ruleJvmUpperBound= ruleJvmUpperBound EOF ;
    public final EObject entryRuleJvmUpperBound() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmUpperBound = null;


        try {
            // InternalJavaJRExpression.g:2813:2: (iv_ruleJvmUpperBound= ruleJvmUpperBound EOF )
            // InternalJavaJRExpression.g:2814:2: iv_ruleJvmUpperBound= ruleJvmUpperBound EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmUpperBoundRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmUpperBound=ruleJvmUpperBound();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmUpperBound; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmUpperBound"


    // $ANTLR start "ruleJvmUpperBound"
    // InternalJavaJRExpression.g:2821:1: ruleJvmUpperBound returns [EObject current=null] : (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmUpperBound() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2824:28: ( (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // InternalJavaJRExpression.g:2825:1: (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // InternalJavaJRExpression.g:2825:1: (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // InternalJavaJRExpression.g:2825:3: otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,58,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getJvmUpperBoundAccess().getExtendsKeyword_0());
                  
            }
            // InternalJavaJRExpression.g:2829:1: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // InternalJavaJRExpression.g:2830:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // InternalJavaJRExpression.g:2830:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            // InternalJavaJRExpression.g:2831:3: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmUpperBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getJvmUpperBoundRule());
              	        }
                     		set(
                     			current, 
                     			"typeReference",
                      		lv_typeReference_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JvmTypeReference");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmUpperBound"


    // $ANTLR start "entryRuleJvmLowerBound"
    // InternalJavaJRExpression.g:2855:1: entryRuleJvmLowerBound returns [EObject current=null] : iv_ruleJvmLowerBound= ruleJvmLowerBound EOF ;
    public final EObject entryRuleJvmLowerBound() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmLowerBound = null;


        try {
            // InternalJavaJRExpression.g:2856:2: (iv_ruleJvmLowerBound= ruleJvmLowerBound EOF )
            // InternalJavaJRExpression.g:2857:2: iv_ruleJvmLowerBound= ruleJvmLowerBound EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmLowerBoundRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmLowerBound=ruleJvmLowerBound();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmLowerBound; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmLowerBound"


    // $ANTLR start "ruleJvmLowerBound"
    // InternalJavaJRExpression.g:2864:1: ruleJvmLowerBound returns [EObject current=null] : (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmLowerBound() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2867:28: ( (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // InternalJavaJRExpression.g:2868:1: (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // InternalJavaJRExpression.g:2868:1: (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // InternalJavaJRExpression.g:2868:3: otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,59,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getJvmLowerBoundAccess().getSuperKeyword_0());
                  
            }
            // InternalJavaJRExpression.g:2872:1: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // InternalJavaJRExpression.g:2873:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // InternalJavaJRExpression.g:2873:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            // InternalJavaJRExpression.g:2874:3: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmLowerBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getJvmLowerBoundRule());
              	        }
                     		set(
                     			current, 
                     			"typeReference",
                      		lv_typeReference_1_0, 
                      		"com.jaspersoft.studio.editor.jrexpressions.JavaJRExpression.JvmTypeReference");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmLowerBound"


    // $ANTLR start "entryRulePrimitiveType"
    // InternalJavaJRExpression.g:2898:1: entryRulePrimitiveType returns [String current=null] : iv_rulePrimitiveType= rulePrimitiveType EOF ;
    public final String entryRulePrimitiveType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePrimitiveType = null;


        try {
            // InternalJavaJRExpression.g:2899:2: (iv_rulePrimitiveType= rulePrimitiveType EOF )
            // InternalJavaJRExpression.g:2900:2: iv_rulePrimitiveType= rulePrimitiveType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimitiveTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePrimitiveType=rulePrimitiveType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimitiveType.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimitiveType"


    // $ANTLR start "rulePrimitiveType"
    // InternalJavaJRExpression.g:2907:1: rulePrimitiveType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'boolean' | kw= 'char' | kw= 'byte' | kw= 'short' | kw= 'int' | kw= 'long' | kw= 'float' | kw= 'double' ) ;
    public final AntlrDatatypeRuleToken rulePrimitiveType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2910:28: ( (kw= 'boolean' | kw= 'char' | kw= 'byte' | kw= 'short' | kw= 'int' | kw= 'long' | kw= 'float' | kw= 'double' ) )
            // InternalJavaJRExpression.g:2911:1: (kw= 'boolean' | kw= 'char' | kw= 'byte' | kw= 'short' | kw= 'int' | kw= 'long' | kw= 'float' | kw= 'double' )
            {
            // InternalJavaJRExpression.g:2911:1: (kw= 'boolean' | kw= 'char' | kw= 'byte' | kw= 'short' | kw= 'int' | kw= 'long' | kw= 'float' | kw= 'double' )
            int alt39=8;
            switch ( input.LA(1) ) {
            case 60:
                {
                alt39=1;
                }
                break;
            case 61:
                {
                alt39=2;
                }
                break;
            case 62:
                {
                alt39=3;
                }
                break;
            case 63:
                {
                alt39=4;
                }
                break;
            case 64:
                {
                alt39=5;
                }
                break;
            case 65:
                {
                alt39=6;
                }
                break;
            case 66:
                {
                alt39=7;
                }
                break;
            case 67:
                {
                alt39=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // InternalJavaJRExpression.g:2912:2: kw= 'boolean'
                    {
                    kw=(Token)match(input,60,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getBooleanKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:2919:2: kw= 'char'
                    {
                    kw=(Token)match(input,61,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getCharKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalJavaJRExpression.g:2926:2: kw= 'byte'
                    {
                    kw=(Token)match(input,62,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getByteKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalJavaJRExpression.g:2933:2: kw= 'short'
                    {
                    kw=(Token)match(input,63,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getShortKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalJavaJRExpression.g:2940:2: kw= 'int'
                    {
                    kw=(Token)match(input,64,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getIntKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalJavaJRExpression.g:2947:2: kw= 'long'
                    {
                    kw=(Token)match(input,65,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getLongKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // InternalJavaJRExpression.g:2954:2: kw= 'float'
                    {
                    kw=(Token)match(input,66,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getFloatKeyword_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // InternalJavaJRExpression.g:2961:2: kw= 'double'
                    {
                    kw=(Token)match(input,67,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getDoubleKeyword_7()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimitiveType"


    // $ANTLR start "entryRuleRelationalOp"
    // InternalJavaJRExpression.g:2974:1: entryRuleRelationalOp returns [String current=null] : iv_ruleRelationalOp= ruleRelationalOp EOF ;
    public final String entryRuleRelationalOp() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelationalOp = null;


        try {
            // InternalJavaJRExpression.g:2975:2: (iv_ruleRelationalOp= ruleRelationalOp EOF )
            // InternalJavaJRExpression.g:2976:2: iv_ruleRelationalOp= ruleRelationalOp EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalOpRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRelationalOp=ruleRelationalOp();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalOp.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelationalOp"


    // $ANTLR start "ruleRelationalOp"
    // InternalJavaJRExpression.g:2983:1: ruleRelationalOp returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<=' | kw= '<' | kw= '>=' | kw= '>' ) ;
    public final AntlrDatatypeRuleToken ruleRelationalOp() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:2986:28: ( (kw= '<=' | kw= '<' | kw= '>=' | kw= '>' ) )
            // InternalJavaJRExpression.g:2987:1: (kw= '<=' | kw= '<' | kw= '>=' | kw= '>' )
            {
            // InternalJavaJRExpression.g:2987:1: (kw= '<=' | kw= '<' | kw= '>=' | kw= '>' )
            int alt40=4;
            switch ( input.LA(1) ) {
            case 68:
                {
                alt40=1;
                }
                break;
            case 56:
                {
                alt40=2;
                }
                break;
            case 69:
                {
                alt40=3;
                }
                break;
            case 57:
                {
                alt40=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // InternalJavaJRExpression.g:2988:2: kw= '<='
                    {
                    kw=(Token)match(input,68,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignEqualsSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalJavaJRExpression.g:2995:2: kw= '<'
                    {
                    kw=(Token)match(input,56,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalJavaJRExpression.g:3002:2: kw= '>='
                    {
                    kw=(Token)match(input,69,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignEqualsSignKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalJavaJRExpression.g:3009:2: kw= '>'
                    {
                    kw=(Token)match(input,57,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignKeyword_3()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelationalOp"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalJavaJRExpression.g:3022:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalJavaJRExpression.g:3023:2: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalJavaJRExpression.g:3024:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedName.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalJavaJRExpression.g:3031:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;

        AntlrDatatypeRuleToken this_ValidID_2 = null;


         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:3034:28: ( (this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )* ) )
            // InternalJavaJRExpression.g:3035:1: (this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )* )
            {
            // InternalJavaJRExpression.g:3035:1: (this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )* )
            // InternalJavaJRExpression.g:3036:5: this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_34);
            this_ValidID_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ValidID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalJavaJRExpression.g:3046:1: (kw= '.' this_ValidID_2= ruleValidID )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==40) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalJavaJRExpression.g:3047:2: kw= '.' this_ValidID_2= ruleValidID
            	    {
            	    kw=(Token)match(input,40,FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_34);
            	    this_ValidID_2=ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ValidID_2);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              afterParserOrEnumRuleCall();
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleValidID"
    // InternalJavaJRExpression.g:3071:1: entryRuleValidID returns [String current=null] : iv_ruleValidID= ruleValidID EOF ;
    public final String entryRuleValidID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleValidID = null;


        try {
            // InternalJavaJRExpression.g:3072:2: (iv_ruleValidID= ruleValidID EOF )
            // InternalJavaJRExpression.g:3073:2: iv_ruleValidID= ruleValidID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getValidIDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleValidID=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleValidID.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValidID"


    // $ANTLR start "ruleValidID"
    // InternalJavaJRExpression.g:3080:1: ruleValidID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleValidID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // InternalJavaJRExpression.g:3083:28: (this_ID_0= RULE_ID )
            // InternalJavaJRExpression.g:3084:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getValidIDAccess().getIDTerminalRuleCall()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValidID"

    // $ANTLR start synpred1_InternalJavaJRExpression
    public final void synpred1_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:158:3: ( ( () '?' ) )
        // InternalJavaJRExpression.g:158:4: ( () '?' )
        {
        // InternalJavaJRExpression.g:158:4: ( () '?' )
        // InternalJavaJRExpression.g:158:5: () '?'
        {
        // InternalJavaJRExpression.g:158:5: ()
        // InternalJavaJRExpression.g:159:1: 
        {
        }

        match(input,26,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred1_InternalJavaJRExpression

    // $ANTLR start synpred2_InternalJavaJRExpression
    public final void synpred2_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:240:3: ( ( () ( ( '||' ) ) ) )
        // InternalJavaJRExpression.g:240:4: ( () ( ( '||' ) ) )
        {
        // InternalJavaJRExpression.g:240:4: ( () ( ( '||' ) ) )
        // InternalJavaJRExpression.g:240:5: () ( ( '||' ) )
        {
        // InternalJavaJRExpression.g:240:5: ()
        // InternalJavaJRExpression.g:241:1: 
        {
        }

        // InternalJavaJRExpression.g:241:2: ( ( '||' ) )
        // InternalJavaJRExpression.g:242:1: ( '||' )
        {
        // InternalJavaJRExpression.g:242:1: ( '||' )
        // InternalJavaJRExpression.g:243:2: '||'
        {
        match(input,28,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred2_InternalJavaJRExpression

    // $ANTLR start synpred3_InternalJavaJRExpression
    public final void synpred3_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:316:3: ( ( () ( ( '&&' ) ) ) )
        // InternalJavaJRExpression.g:316:4: ( () ( ( '&&' ) ) )
        {
        // InternalJavaJRExpression.g:316:4: ( () ( ( '&&' ) ) )
        // InternalJavaJRExpression.g:316:5: () ( ( '&&' ) )
        {
        // InternalJavaJRExpression.g:316:5: ()
        // InternalJavaJRExpression.g:317:1: 
        {
        }

        // InternalJavaJRExpression.g:317:2: ( ( '&&' ) )
        // InternalJavaJRExpression.g:318:1: ( '&&' )
        {
        // InternalJavaJRExpression.g:318:1: ( '&&' )
        // InternalJavaJRExpression.g:319:2: '&&'
        {
        match(input,29,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred3_InternalJavaJRExpression

    // $ANTLR start synpred4_InternalJavaJRExpression
    public final void synpred4_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:392:3: ( ( () ( ( ( '==' | '!=' ) ) ) ) )
        // InternalJavaJRExpression.g:392:4: ( () ( ( ( '==' | '!=' ) ) ) )
        {
        // InternalJavaJRExpression.g:392:4: ( () ( ( ( '==' | '!=' ) ) ) )
        // InternalJavaJRExpression.g:392:5: () ( ( ( '==' | '!=' ) ) )
        {
        // InternalJavaJRExpression.g:392:5: ()
        // InternalJavaJRExpression.g:393:1: 
        {
        }

        // InternalJavaJRExpression.g:393:2: ( ( ( '==' | '!=' ) ) )
        // InternalJavaJRExpression.g:394:1: ( ( '==' | '!=' ) )
        {
        // InternalJavaJRExpression.g:394:1: ( ( '==' | '!=' ) )
        // InternalJavaJRExpression.g:395:1: ( '==' | '!=' )
        {
        if ( (input.LA(1)>=30 && input.LA(1)<=31) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred4_InternalJavaJRExpression

    // $ANTLR start synpred5_InternalJavaJRExpression
    public final void synpred5_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:489:3: ( ( () ( ( 'instanceof' ) ) ) )
        // InternalJavaJRExpression.g:489:4: ( () ( ( 'instanceof' ) ) )
        {
        // InternalJavaJRExpression.g:489:4: ( () ( ( 'instanceof' ) ) )
        // InternalJavaJRExpression.g:489:5: () ( ( 'instanceof' ) )
        {
        // InternalJavaJRExpression.g:489:5: ()
        // InternalJavaJRExpression.g:490:1: 
        {
        }

        // InternalJavaJRExpression.g:490:2: ( ( 'instanceof' ) )
        // InternalJavaJRExpression.g:491:1: ( 'instanceof' )
        {
        // InternalJavaJRExpression.g:491:1: ( 'instanceof' )
        // InternalJavaJRExpression.g:492:2: 'instanceof'
        {
        match(input,32,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred5_InternalJavaJRExpression

    // $ANTLR start synpred6_InternalJavaJRExpression
    public final void synpred6_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:565:3: ( ( () ( ( ruleRelationalOp ) ) ) )
        // InternalJavaJRExpression.g:565:4: ( () ( ( ruleRelationalOp ) ) )
        {
        // InternalJavaJRExpression.g:565:4: ( () ( ( ruleRelationalOp ) ) )
        // InternalJavaJRExpression.g:565:5: () ( ( ruleRelationalOp ) )
        {
        // InternalJavaJRExpression.g:565:5: ()
        // InternalJavaJRExpression.g:566:1: 
        {
        }

        // InternalJavaJRExpression.g:566:2: ( ( ruleRelationalOp ) )
        // InternalJavaJRExpression.g:567:1: ( ruleRelationalOp )
        {
        // InternalJavaJRExpression.g:567:1: ( ruleRelationalOp )
        // InternalJavaJRExpression.g:568:1: ruleRelationalOp
        {
        pushFollow(FOLLOW_2);
        ruleRelationalOp();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred6_InternalJavaJRExpression

    // $ANTLR start synpred7_InternalJavaJRExpression
    public final void synpred7_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:642:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )
        // InternalJavaJRExpression.g:642:4: ( () ( ( ( '+' | '-' ) ) ) )
        {
        // InternalJavaJRExpression.g:642:4: ( () ( ( ( '+' | '-' ) ) ) )
        // InternalJavaJRExpression.g:642:5: () ( ( ( '+' | '-' ) ) )
        {
        // InternalJavaJRExpression.g:642:5: ()
        // InternalJavaJRExpression.g:643:1: 
        {
        }

        // InternalJavaJRExpression.g:643:2: ( ( ( '+' | '-' ) ) )
        // InternalJavaJRExpression.g:644:1: ( ( '+' | '-' ) )
        {
        // InternalJavaJRExpression.g:644:1: ( ( '+' | '-' ) )
        // InternalJavaJRExpression.g:645:1: ( '+' | '-' )
        {
        if ( (input.LA(1)>=33 && input.LA(1)<=34) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred7_InternalJavaJRExpression

    // $ANTLR start synpred8_InternalJavaJRExpression
    public final void synpred8_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:739:3: ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )
        // InternalJavaJRExpression.g:739:4: ( () ( ( ( '*' | '/' | '%' ) ) ) )
        {
        // InternalJavaJRExpression.g:739:4: ( () ( ( ( '*' | '/' | '%' ) ) ) )
        // InternalJavaJRExpression.g:739:5: () ( ( ( '*' | '/' | '%' ) ) )
        {
        // InternalJavaJRExpression.g:739:5: ()
        // InternalJavaJRExpression.g:740:1: 
        {
        }

        // InternalJavaJRExpression.g:740:2: ( ( ( '*' | '/' | '%' ) ) )
        // InternalJavaJRExpression.g:741:1: ( ( '*' | '/' | '%' ) )
        {
        // InternalJavaJRExpression.g:741:1: ( ( '*' | '/' | '%' ) )
        // InternalJavaJRExpression.g:742:1: ( '*' | '/' | '%' )
        {
        if ( (input.LA(1)>=35 && input.LA(1)<=37) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred8_InternalJavaJRExpression

    // $ANTLR start synpred9_InternalJavaJRExpression
    public final void synpred9_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:928:7: ( ruleCastedExpression )
        // InternalJavaJRExpression.g:928:9: ruleCastedExpression
        {
        pushFollow(FOLLOW_2);
        ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_InternalJavaJRExpression

    // $ANTLR start synpred10_InternalJavaJRExpression
    public final void synpred10_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:998:7: ( ruleBaseJRExpression )
        // InternalJavaJRExpression.g:998:9: ruleBaseJRExpression
        {
        pushFollow(FOLLOW_2);
        ruleBaseJRExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_InternalJavaJRExpression

    // $ANTLR start synpred11_InternalJavaJRExpression
    public final void synpred11_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:2594:2: ( ( () '[' ']' ) )
        // InternalJavaJRExpression.g:2594:3: ( () '[' ']' )
        {
        // InternalJavaJRExpression.g:2594:3: ( () '[' ']' )
        // InternalJavaJRExpression.g:2594:4: () '[' ']'
        {
        // InternalJavaJRExpression.g:2594:4: ()
        // InternalJavaJRExpression.g:2595:1: 
        {
        }

        match(input,46,FOLLOW_20); if (state.failed) return ;
        match(input,47,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred11_InternalJavaJRExpression

    // $ANTLR start synpred12_InternalJavaJRExpression
    public final void synpred12_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // InternalJavaJRExpression.g:2647:4: ( '<' )
        // InternalJavaJRExpression.g:2647:6: '<'
        {
        match(input,56,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_InternalJavaJRExpression

    // Delegated rules

    public final boolean synpred12_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_InternalJavaJRExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_InternalJavaJRExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA14 dfa14 = new DFA14(this);
    protected DFA15 dfa15 = new DFA15(this);
    protected DFA36 dfa36 = new DFA36(this);
    static final String dfa_1s = "\24\uffff";
    static final String dfa_2s = "\1\5\2\uffff\1\0\20\uffff";
    static final String dfa_3s = "\1\63\2\uffff\1\0\20\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\2\1\uffff\1\4\16\uffff\1\3";
    static final String dfa_5s = "\3\uffff\1\0\20\uffff}>";
    static final String[] dfa_6s = {
            "\7\4\32\uffff\1\1\1\2\1\uffff\5\4\2\uffff\3\4\1\3",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "900:1: ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA14_3 = input.LA(1);

                         
                        int index14_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred9_InternalJavaJRExpression()) ) {s = 19;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index14_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 14, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_7s = "\51\uffff";
    static final String dfa_8s = "\3\uffff\1\2\5\uffff\1\22\3\uffff\4\43\30\uffff";
    static final String dfa_9s = "\1\5\2\uffff\1\32\1\13\4\4\1\32\1\uffff\1\50\1\uffff\4\32\1\13\1\uffff\1\13\24\uffff\1\50";
    static final String dfa_10s = "\1\63\2\uffff\1\105\1\103\4\4\1\105\1\uffff\1\70\1\uffff\4\105\1\13\1\uffff\1\13\24\uffff\1\70";
    static final String dfa_11s = "\1\uffff\1\1\1\2\7\uffff\1\5\1\uffff\1\3\5\uffff\1\6\1\uffff\24\4\1\uffff";
    static final String dfa_12s = "\15\uffff\1\1\1\0\1\3\1\2\30\uffff}>";
    static final String[] dfa_13s = {
            "\5\2\1\3\1\11\35\uffff\1\5\1\6\1\7\1\10\1\4\2\uffff\3\2\1\1",
            "",
            "",
            "\14\2\2\uffff\1\12\13\uffff\1\2\1\uffff\4\2\12\uffff\2\2",
            "\1\13\60\uffff\10\14",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "\14\22\2\uffff\1\21\12\uffff\1\12\1\22\1\uffff\4\22\12\uffff\2\22",
            "",
            "\1\23\5\uffff\1\14\4\uffff\1\12\4\uffff\1\14",
            "",
            "\1\42\1\44\1\41\1\40\1\36\1\37\1\35\1\27\1\30\1\24\1\25\1\26\2\uffff\1\12\13\uffff\1\45\1\uffff\1\47\1\46\1\32\1\34\12\uffff\1\31\1\33",
            "\1\42\1\44\1\41\1\40\1\36\1\37\1\35\1\27\1\30\1\24\1\25\1\26\2\uffff\1\12\13\uffff\1\45\1\uffff\1\47\1\46\1\32\1\34\12\uffff\1\31\1\33",
            "\1\42\1\44\1\41\1\40\1\36\1\37\1\35\1\27\1\30\1\24\1\25\1\26\2\uffff\1\12\13\uffff\1\45\1\uffff\1\47\1\46\1\32\1\34\12\uffff\1\31\1\33",
            "\1\42\1\44\1\41\1\40\1\36\1\37\1\35\1\27\1\30\1\24\1\25\1\26\2\uffff\1\12\13\uffff\1\45\1\uffff\1\47\1\46\1\32\1\34\12\uffff\1\31\1\33",
            "\1\11",
            "",
            "\1\50",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\23\5\uffff\1\14\4\uffff\1\12\4\uffff\1\14"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "968:1: (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_14 = input.LA(1);

                         
                        int index15_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA15_14==40) ) {s = 10;}

                        else if ( (LA15_14==35) && (synpred10_InternalJavaJRExpression())) {s = 20;}

                        else if ( (LA15_14==36) && (synpred10_InternalJavaJRExpression())) {s = 21;}

                        else if ( (LA15_14==37) && (synpred10_InternalJavaJRExpression())) {s = 22;}

                        else if ( (LA15_14==33) && (synpred10_InternalJavaJRExpression())) {s = 23;}

                        else if ( (LA15_14==34) && (synpred10_InternalJavaJRExpression())) {s = 24;}

                        else if ( (LA15_14==68) && (synpred10_InternalJavaJRExpression())) {s = 25;}

                        else if ( (LA15_14==56) && (synpred10_InternalJavaJRExpression())) {s = 26;}

                        else if ( (LA15_14==69) && (synpred10_InternalJavaJRExpression())) {s = 27;}

                        else if ( (LA15_14==57) && (synpred10_InternalJavaJRExpression())) {s = 28;}

                        else if ( (LA15_14==32) && (synpred10_InternalJavaJRExpression())) {s = 29;}

                        else if ( (LA15_14==30) && (synpred10_InternalJavaJRExpression())) {s = 30;}

                        else if ( (LA15_14==31) && (synpred10_InternalJavaJRExpression())) {s = 31;}

                        else if ( (LA15_14==29) && (synpred10_InternalJavaJRExpression())) {s = 32;}

                        else if ( (LA15_14==28) && (synpred10_InternalJavaJRExpression())) {s = 33;}

                        else if ( (LA15_14==26) && (synpred10_InternalJavaJRExpression())) {s = 34;}

                        else if ( (LA15_14==EOF) && (synpred10_InternalJavaJRExpression())) {s = 35;}

                        else if ( (LA15_14==27) && (synpred10_InternalJavaJRExpression())) {s = 36;}

                        else if ( (LA15_14==52) && (synpred10_InternalJavaJRExpression())) {s = 37;}

                        else if ( (LA15_14==55) && (synpred10_InternalJavaJRExpression())) {s = 38;}

                        else if ( (LA15_14==54) && (synpred10_InternalJavaJRExpression())) {s = 39;}

                         
                        input.seek(index15_14);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA15_13 = input.LA(1);

                         
                        int index15_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA15_13==40) ) {s = 10;}

                        else if ( (LA15_13==35) && (synpred10_InternalJavaJRExpression())) {s = 20;}

                        else if ( (LA15_13==36) && (synpred10_InternalJavaJRExpression())) {s = 21;}

                        else if ( (LA15_13==37) && (synpred10_InternalJavaJRExpression())) {s = 22;}

                        else if ( (LA15_13==33) && (synpred10_InternalJavaJRExpression())) {s = 23;}

                        else if ( (LA15_13==34) && (synpred10_InternalJavaJRExpression())) {s = 24;}

                        else if ( (LA15_13==68) && (synpred10_InternalJavaJRExpression())) {s = 25;}

                        else if ( (LA15_13==56) && (synpred10_InternalJavaJRExpression())) {s = 26;}

                        else if ( (LA15_13==69) && (synpred10_InternalJavaJRExpression())) {s = 27;}

                        else if ( (LA15_13==57) && (synpred10_InternalJavaJRExpression())) {s = 28;}

                        else if ( (LA15_13==32) && (synpred10_InternalJavaJRExpression())) {s = 29;}

                        else if ( (LA15_13==30) && (synpred10_InternalJavaJRExpression())) {s = 30;}

                        else if ( (LA15_13==31) && (synpred10_InternalJavaJRExpression())) {s = 31;}

                        else if ( (LA15_13==29) && (synpred10_InternalJavaJRExpression())) {s = 32;}

                        else if ( (LA15_13==28) && (synpred10_InternalJavaJRExpression())) {s = 33;}

                        else if ( (LA15_13==26) && (synpred10_InternalJavaJRExpression())) {s = 34;}

                        else if ( (LA15_13==EOF) && (synpred10_InternalJavaJRExpression())) {s = 35;}

                        else if ( (LA15_13==27) && (synpred10_InternalJavaJRExpression())) {s = 36;}

                        else if ( (LA15_13==52) && (synpred10_InternalJavaJRExpression())) {s = 37;}

                        else if ( (LA15_13==55) && (synpred10_InternalJavaJRExpression())) {s = 38;}

                        else if ( (LA15_13==54) && (synpred10_InternalJavaJRExpression())) {s = 39;}

                         
                        input.seek(index15_13);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA15_16 = input.LA(1);

                         
                        int index15_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA15_16==35) && (synpred10_InternalJavaJRExpression())) {s = 20;}

                        else if ( (LA15_16==36) && (synpred10_InternalJavaJRExpression())) {s = 21;}

                        else if ( (LA15_16==37) && (synpred10_InternalJavaJRExpression())) {s = 22;}

                        else if ( (LA15_16==33) && (synpred10_InternalJavaJRExpression())) {s = 23;}

                        else if ( (LA15_16==34) && (synpred10_InternalJavaJRExpression())) {s = 24;}

                        else if ( (LA15_16==68) && (synpred10_InternalJavaJRExpression())) {s = 25;}

                        else if ( (LA15_16==56) && (synpred10_InternalJavaJRExpression())) {s = 26;}

                        else if ( (LA15_16==69) && (synpred10_InternalJavaJRExpression())) {s = 27;}

                        else if ( (LA15_16==57) && (synpred10_InternalJavaJRExpression())) {s = 28;}

                        else if ( (LA15_16==32) && (synpred10_InternalJavaJRExpression())) {s = 29;}

                        else if ( (LA15_16==30) && (synpred10_InternalJavaJRExpression())) {s = 30;}

                        else if ( (LA15_16==31) && (synpred10_InternalJavaJRExpression())) {s = 31;}

                        else if ( (LA15_16==29) && (synpred10_InternalJavaJRExpression())) {s = 32;}

                        else if ( (LA15_16==28) && (synpred10_InternalJavaJRExpression())) {s = 33;}

                        else if ( (LA15_16==26) && (synpred10_InternalJavaJRExpression())) {s = 34;}

                        else if ( (LA15_16==EOF) && (synpred10_InternalJavaJRExpression())) {s = 35;}

                        else if ( (LA15_16==27) && (synpred10_InternalJavaJRExpression())) {s = 36;}

                        else if ( (LA15_16==52) && (synpred10_InternalJavaJRExpression())) {s = 37;}

                        else if ( (LA15_16==55) && (synpred10_InternalJavaJRExpression())) {s = 38;}

                        else if ( (LA15_16==54) && (synpred10_InternalJavaJRExpression())) {s = 39;}

                        else if ( (LA15_16==40) ) {s = 10;}

                         
                        input.seek(index15_16);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA15_15 = input.LA(1);

                         
                        int index15_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA15_15==40) ) {s = 10;}

                        else if ( (LA15_15==35) && (synpred10_InternalJavaJRExpression())) {s = 20;}

                        else if ( (LA15_15==36) && (synpred10_InternalJavaJRExpression())) {s = 21;}

                        else if ( (LA15_15==37) && (synpred10_InternalJavaJRExpression())) {s = 22;}

                        else if ( (LA15_15==33) && (synpred10_InternalJavaJRExpression())) {s = 23;}

                        else if ( (LA15_15==34) && (synpred10_InternalJavaJRExpression())) {s = 24;}

                        else if ( (LA15_15==68) && (synpred10_InternalJavaJRExpression())) {s = 25;}

                        else if ( (LA15_15==56) && (synpred10_InternalJavaJRExpression())) {s = 26;}

                        else if ( (LA15_15==69) && (synpred10_InternalJavaJRExpression())) {s = 27;}

                        else if ( (LA15_15==57) && (synpred10_InternalJavaJRExpression())) {s = 28;}

                        else if ( (LA15_15==32) && (synpred10_InternalJavaJRExpression())) {s = 29;}

                        else if ( (LA15_15==30) && (synpred10_InternalJavaJRExpression())) {s = 30;}

                        else if ( (LA15_15==31) && (synpred10_InternalJavaJRExpression())) {s = 31;}

                        else if ( (LA15_15==29) && (synpred10_InternalJavaJRExpression())) {s = 32;}

                        else if ( (LA15_15==28) && (synpred10_InternalJavaJRExpression())) {s = 33;}

                        else if ( (LA15_15==26) && (synpred10_InternalJavaJRExpression())) {s = 34;}

                        else if ( (LA15_15==EOF) && (synpred10_InternalJavaJRExpression())) {s = 35;}

                        else if ( (LA15_15==27) && (synpred10_InternalJavaJRExpression())) {s = 36;}

                        else if ( (LA15_15==52) && (synpred10_InternalJavaJRExpression())) {s = 37;}

                        else if ( (LA15_15==55) && (synpred10_InternalJavaJRExpression())) {s = 38;}

                        else if ( (LA15_15==54) && (synpred10_InternalJavaJRExpression())) {s = 39;}

                         
                        input.seek(index15_15);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_14s = "\27\uffff";
    static final String dfa_15s = "\1\2\26\uffff";
    static final String dfa_16s = "\1\32\1\0\25\uffff";
    static final String dfa_17s = "\1\105\1\0\25\uffff";
    static final String dfa_18s = "\2\uffff\1\2\23\uffff\1\1";
    static final String dfa_19s = "\1\uffff\1\0\25\uffff}>";
    static final String[] dfa_20s = {
            "\14\2\10\uffff\1\2\5\uffff\1\2\1\uffff\2\2\1\1\1\2\12\uffff\2\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_14 = DFA.unpackEncodedString(dfa_14s);
    static final short[] dfa_15 = DFA.unpackEncodedString(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final char[] dfa_17 = DFA.unpackEncodedStringToUnsignedChars(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[][] dfa_20 = unpackEncodedStringArray(dfa_20s);

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "2647:2: ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_1 = input.LA(1);

                         
                        int index36_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_InternalJavaJRExpression()) ) {s = 22;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index36_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x000F3EC600000FE0L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000C0000002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0xF000000000000800L,0x000000000000000FL});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0300000000000002L,0x0000000000000030L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000003800000002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000200000000800L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000410000000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0020400000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x004F3EC600000FE0L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x001F3EC600000FE0L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0xF000000004000800L,0x000000000000000FL});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0280000000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0C00000000000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000010000000002L});

}
