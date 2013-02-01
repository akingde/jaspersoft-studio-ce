/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.parser.antlr.internal; 

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;

import com.jaspersoft.studio.editor.jrexpressions.services.JavaJRExpressionGrammarAccess;
@SuppressWarnings("all")
public class InternalJavaJRExpressionParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_BRACED_IDENTIFIER", "RULE_INT", "RULE_LONG", "RULE_FLOAT", "RULE_DOUBLE", "RULE_CHAR", "RULE_STRING", "RULE_ID", "RULE_INTEGERNUMBER", "RULE_LONGSUFFIX", "RULE_NONINTEGERNUMBER", "RULE_FLOATSUFFIX", "RULE_DOUBLESUFFIX", "RULE_ESCAPESEQUENCE", "RULE_HEXPREFIX", "RULE_HEXDIGIT", "RULE_EXPONENT", "RULE_EXPOBJIDENTIFIER", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'?'", "':'", "'||'", "'&&'", "'=='", "'!='", "'instanceof'", "'+'", "'-'", "'*'", "'/'", "'%'", "'~'", "'!'", "'.'", "'$F'", "'$P'", "'$V'", "'new'", "'['", "']'", "'false'", "'true'", "'null'", "'('", "')'", "'{'", "'}'", "','", "'<'", "'>'", "'extends'", "'super'", "'boolean'", "'char'", "'byte'", "'short'", "'int'", "'long'", "'float'", "'double'", "'<='", "'>='"
    };
    public static final int T__68=68;
    public static final int RULE_ID=11;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__29=29;
    public static final int T__64=64;
    public static final int T__28=28;
    public static final int T__65=65;
    public static final int T__27=27;
    public static final int T__62=62;
    public static final int T__26=26;
    public static final int T__63=63;
    public static final int RULE_ANY_OTHER=25;
    public static final int RULE_HEXDIGIT=19;
    public static final int T__61=61;
    public static final int EOF=-1;
    public static final int T__60=60;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int RULE_NONINTEGERNUMBER=14;
    public static final int T__59=59;
    public static final int RULE_FLOATSUFFIX=15;
    public static final int RULE_INT=5;
    public static final int RULE_CHAR=9;
    public static final int RULE_LONGSUFFIX=13;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int RULE_ESCAPESEQUENCE=17;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int RULE_EXPOBJIDENTIFIER=21;
    public static final int RULE_LONG=6;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_HEXPREFIX=18;
    public static final int RULE_BRACED_IDENTIFIER=4;
    public static final int RULE_FLOAT=7;
    public static final int RULE_SL_COMMENT=23;
    public static final int RULE_DOUBLE=8;
    public static final int RULE_ML_COMMENT=22;
    public static final int T__30=30;
    public static final int RULE_DOUBLESUFFIX=16;
    public static final int T__31=31;
    public static final int RULE_STRING=10;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int RULE_EXPONENT=20;
    public static final int T__39=39;
    public static final int RULE_INTEGERNUMBER=12;
    public static final int RULE_WS=24;

    // delegates
    // delegators


        public InternalJavaJRExpressionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalJavaJRExpressionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalJavaJRExpressionParser.tokenNames; }
    public String getGrammarFileName() { return "../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g"; }



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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:67:1: entryRuleJRExpressionModel returns [EObject current=null] : iv_ruleJRExpressionModel= ruleJRExpressionModel EOF ;
    public final EObject entryRuleJRExpressionModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRExpressionModel = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:68:2: (iv_ruleJRExpressionModel= ruleJRExpressionModel EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:69:2: iv_ruleJRExpressionModel= ruleJRExpressionModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRExpressionModelRule()); 
            }
            pushFollow(FOLLOW_ruleJRExpressionModel_in_entryRuleJRExpressionModel75);
            iv_ruleJRExpressionModel=ruleJRExpressionModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRExpressionModel; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRExpressionModel85); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:76:1: ruleJRExpressionModel returns [EObject current=null] : ( (lv_expression_0_0= ruleJasperReportsExpression ) )? ;
    public final EObject ruleJRExpressionModel() throws RecognitionException {
        EObject current = null;

        EObject lv_expression_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:79:28: ( ( (lv_expression_0_0= ruleJasperReportsExpression ) )? )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:80:1: ( (lv_expression_0_0= ruleJasperReportsExpression ) )?
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:80:1: ( (lv_expression_0_0= ruleJasperReportsExpression ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=RULE_INT && LA1_0<=RULE_ID)||(LA1_0>=33 && LA1_0<=34)||(LA1_0>=38 && LA1_0<=39)||(LA1_0>=41 && LA1_0<=44)||(LA1_0>=47 && LA1_0<=50)) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:81:1: (lv_expression_0_0= ruleJasperReportsExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:81:1: (lv_expression_0_0= ruleJasperReportsExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:82:3: lv_expression_0_0= ruleJasperReportsExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJRExpressionModelAccess().getExpressionJasperReportsExpressionParserRuleCall_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJasperReportsExpression_in_ruleJRExpressionModel130);
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
                              		"JasperReportsExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:106:1: entryRuleJasperReportsExpression returns [EObject current=null] : iv_ruleJasperReportsExpression= ruleJasperReportsExpression EOF ;
    public final EObject entryRuleJasperReportsExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJasperReportsExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:107:2: (iv_ruleJasperReportsExpression= ruleJasperReportsExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:108:2: iv_ruleJasperReportsExpression= ruleJasperReportsExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJasperReportsExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleJasperReportsExpression_in_entryRuleJasperReportsExpression166);
            iv_ruleJasperReportsExpression=ruleJasperReportsExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJasperReportsExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJasperReportsExpression176); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:115:1: ruleJasperReportsExpression returns [EObject current=null] : this_ConditionalExpression_0= ruleConditionalExpression ;
    public final EObject ruleJasperReportsExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ConditionalExpression_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:118:28: (this_ConditionalExpression_0= ruleConditionalExpression )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:120:5: this_ConditionalExpression_0= ruleConditionalExpression
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getJasperReportsExpressionAccess().getConditionalExpressionParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleConditionalExpression_in_ruleJasperReportsExpression222);
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:136:1: entryRuleConditionalExpression returns [EObject current=null] : iv_ruleConditionalExpression= ruleConditionalExpression EOF ;
    public final EObject entryRuleConditionalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:137:2: (iv_ruleConditionalExpression= ruleConditionalExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:138:2: iv_ruleConditionalExpression= ruleConditionalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConditionalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConditionalExpression_in_entryRuleConditionalExpression256);
            iv_ruleConditionalExpression=ruleConditionalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConditionalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConditionalExpression266); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:145:1: ruleConditionalExpression returns [EObject current=null] : (this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )? ) ;
    public final EObject ruleConditionalExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ConditionalOrExpression_0 = null;

        EObject lv_trueStatement_3_0 = null;

        EObject lv_falseStatement_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:148:28: ( (this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )? ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:149:1: (this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )? )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:149:1: (this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )? )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:150:5: this_ConditionalOrExpression_0= ruleConditionalOrExpression ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getConditionalExpressionAccess().getConditionalOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleConditionalOrExpression_in_ruleConditionalExpression313);
            this_ConditionalOrExpression_0=ruleConditionalOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ConditionalOrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:1: ( ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) ) )?
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:2: ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) ) ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) ) otherlv_4= ':' ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:2: ( ( ( () '?' ) )=> ( () otherlv_2= '?' ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:3: ( ( () '?' ) )=> ( () otherlv_2= '?' )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:160:5: ( () otherlv_2= '?' )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:160:6: () otherlv_2= '?'
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:160:6: ()
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:161:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getConditionalExpressionAccess().getTestExpressionConditionAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_2=(Token)match(input,26,FOLLOW_26_in_ruleConditionalExpression348); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getConditionalExpressionAccess().getQuestionMarkKeyword_1_0_0_1());
                          
                    }

                    }


                    }

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:170:3: ( (lv_trueStatement_3_0= ruleJasperReportsExpression ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:171:1: (lv_trueStatement_3_0= ruleJasperReportsExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:171:1: (lv_trueStatement_3_0= ruleJasperReportsExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:172:3: lv_trueStatement_3_0= ruleJasperReportsExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getConditionalExpressionAccess().getTrueStatementJasperReportsExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJasperReportsExpression_in_ruleConditionalExpression371);
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
                              		"JasperReportsExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,27,FOLLOW_27_in_ruleConditionalExpression383); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getConditionalExpressionAccess().getColonKeyword_1_2());
                          
                    }
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:192:1: ( (lv_falseStatement_5_0= ruleJasperReportsExpression ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:193:1: (lv_falseStatement_5_0= ruleJasperReportsExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:193:1: (lv_falseStatement_5_0= ruleJasperReportsExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:194:3: lv_falseStatement_5_0= ruleJasperReportsExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getConditionalExpressionAccess().getFalseStatementJasperReportsExpressionParserRuleCall_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJasperReportsExpression_in_ruleConditionalExpression404);
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
                              		"JasperReportsExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:218:1: entryRuleConditionalOrExpression returns [EObject current=null] : iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF ;
    public final EObject entryRuleConditionalOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalOrExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:219:2: (iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:220:2: iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConditionalOrExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConditionalOrExpression_in_entryRuleConditionalOrExpression442);
            iv_ruleConditionalOrExpression=ruleConditionalOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConditionalOrExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConditionalOrExpression452); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:227:1: ruleConditionalOrExpression returns [EObject current=null] : (this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )* ) ;
    public final EObject ruleConditionalOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_ConditionalAndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:230:28: ( (this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:231:1: (this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:231:1: (this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:232:5: this_ConditionalAndExpression_0= ruleConditionalAndExpression ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getConditionalOrExpressionAccess().getConditionalAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleConditionalAndExpression_in_ruleConditionalOrExpression499);
            this_ConditionalAndExpression_0=ruleConditionalAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ConditionalAndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:1: ( ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) ) )*
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
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:2: ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) ) ( (lv_right_3_0= ruleConditionalAndExpression ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:2: ( ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:3: ( ( () ( ( '||' ) ) ) )=> ( () ( (lv_op_2_0= '||' ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:247:6: ( () ( (lv_op_2_0= '||' ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:247:7: () ( (lv_op_2_0= '||' ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:247:7: ()
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:248:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getConditionalOrExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:253:2: ( (lv_op_2_0= '||' ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:254:1: (lv_op_2_0= '||' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:254:1: (lv_op_2_0= '||' )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:255:3: lv_op_2_0= '||'
            	    {
            	    lv_op_2_0=(Token)match(input,28,FOLLOW_28_in_ruleConditionalOrExpression550); if (state.failed) return current;
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

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:268:4: ( (lv_right_3_0= ruleConditionalAndExpression ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:269:1: (lv_right_3_0= ruleConditionalAndExpression )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:269:1: (lv_right_3_0= ruleConditionalAndExpression )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:270:3: lv_right_3_0= ruleConditionalAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getConditionalOrExpressionAccess().getRightConditionalAndExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleConditionalAndExpression_in_ruleConditionalOrExpression586);
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
            	              		"ConditionalAndExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:294:1: entryRuleConditionalAndExpression returns [EObject current=null] : iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF ;
    public final EObject entryRuleConditionalAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalAndExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:295:2: (iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:296:2: iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConditionalAndExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConditionalAndExpression_in_entryRuleConditionalAndExpression624);
            iv_ruleConditionalAndExpression=ruleConditionalAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConditionalAndExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConditionalAndExpression634); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:303:1: ruleConditionalAndExpression returns [EObject current=null] : (this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )* ) ;
    public final EObject ruleConditionalAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_EqualityExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:306:28: ( (this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:307:1: (this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:307:1: (this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:308:5: this_EqualityExpression_0= ruleEqualityExpression ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getConditionalAndExpressionAccess().getEqualityExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleEqualityExpression_in_ruleConditionalAndExpression681);
            this_EqualityExpression_0=ruleEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_EqualityExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:1: ( ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) ) )*
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
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:2: ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) ) ( (lv_right_3_0= ruleEqualityExpression ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:2: ( ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:3: ( ( () ( ( '&&' ) ) ) )=> ( () ( (lv_op_2_0= '&&' ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:323:6: ( () ( (lv_op_2_0= '&&' ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:323:7: () ( (lv_op_2_0= '&&' ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:323:7: ()
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:324:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getConditionalAndExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:329:2: ( (lv_op_2_0= '&&' ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:330:1: (lv_op_2_0= '&&' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:330:1: (lv_op_2_0= '&&' )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:331:3: lv_op_2_0= '&&'
            	    {
            	    lv_op_2_0=(Token)match(input,29,FOLLOW_29_in_ruleConditionalAndExpression732); if (state.failed) return current;
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

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:344:4: ( (lv_right_3_0= ruleEqualityExpression ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:345:1: (lv_right_3_0= ruleEqualityExpression )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:345:1: (lv_right_3_0= ruleEqualityExpression )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:346:3: lv_right_3_0= ruleEqualityExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getConditionalAndExpressionAccess().getRightEqualityExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleEqualityExpression_in_ruleConditionalAndExpression768);
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
            	              		"EqualityExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:370:1: entryRuleEqualityExpression returns [EObject current=null] : iv_ruleEqualityExpression= ruleEqualityExpression EOF ;
    public final EObject entryRuleEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEqualityExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:371:2: (iv_ruleEqualityExpression= ruleEqualityExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:372:2: iv_ruleEqualityExpression= ruleEqualityExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEqualityExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleEqualityExpression_in_entryRuleEqualityExpression806);
            iv_ruleEqualityExpression=ruleEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEqualityExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleEqualityExpression816); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:379:1: ruleEqualityExpression returns [EObject current=null] : (this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )* ) ;
    public final EObject ruleEqualityExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_InstanceofExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:382:28: ( (this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:383:1: (this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:383:1: (this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:384:5: this_InstanceofExpression_0= ruleInstanceofExpression ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getEqualityExpressionAccess().getInstanceofExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleInstanceofExpression_in_ruleEqualityExpression863);
            this_InstanceofExpression_0=ruleInstanceofExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_InstanceofExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:1: ( ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) ) )*
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
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:2: ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) ) ( (lv_right_3_0= ruleInstanceofExpression ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:2: ( ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:3: ( ( () ( ( ( '==' | '!=' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:405:6: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:405:7: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:405:7: ()
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:406:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getEqualityExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:411:2: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:412:1: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:412:1: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:413:1: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:413:1: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
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
            	            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:414:3: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,30,FOLLOW_30_in_ruleEqualityExpression935); if (state.failed) return current;
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
            	            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:426:8: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,31,FOLLOW_31_in_ruleEqualityExpression964); if (state.failed) return current;
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

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:441:4: ( (lv_right_3_0= ruleInstanceofExpression ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:442:1: (lv_right_3_0= ruleInstanceofExpression )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:442:1: (lv_right_3_0= ruleInstanceofExpression )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:443:3: lv_right_3_0= ruleInstanceofExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getEqualityExpressionAccess().getRightInstanceofExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleInstanceofExpression_in_ruleEqualityExpression1003);
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
            	              		"InstanceofExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:467:1: entryRuleInstanceofExpression returns [EObject current=null] : iv_ruleInstanceofExpression= ruleInstanceofExpression EOF ;
    public final EObject entryRuleInstanceofExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstanceofExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:468:2: (iv_ruleInstanceofExpression= ruleInstanceofExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:469:2: iv_ruleInstanceofExpression= ruleInstanceofExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInstanceofExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInstanceofExpression_in_entryRuleInstanceofExpression1041);
            iv_ruleInstanceofExpression=ruleInstanceofExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInstanceofExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInstanceofExpression1051); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:476:1: ruleInstanceofExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )? ) ;
    public final EObject ruleInstanceofExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:479:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )? ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:480:1: (this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )? )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:480:1: (this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )? )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:481:5: this_RelationalExpression_0= ruleRelationalExpression ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInstanceofExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleInstanceofExpression1098);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:1: ( ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) ) )?
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:2: ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) ) ( (lv_right_3_0= ruleType ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:2: ( ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:3: ( ( () ( ( 'instanceof' ) ) ) )=> ( () ( (lv_op_2_0= 'instanceof' ) ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:496:6: ( () ( (lv_op_2_0= 'instanceof' ) ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:496:7: () ( (lv_op_2_0= 'instanceof' ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:496:7: ()
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:497:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getInstanceofExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:502:2: ( (lv_op_2_0= 'instanceof' ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:503:1: (lv_op_2_0= 'instanceof' )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:503:1: (lv_op_2_0= 'instanceof' )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:504:3: lv_op_2_0= 'instanceof'
                    {
                    lv_op_2_0=(Token)match(input,32,FOLLOW_32_in_ruleInstanceofExpression1149); if (state.failed) return current;
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

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:517:4: ( (lv_right_3_0= ruleType ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:518:1: (lv_right_3_0= ruleType )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:518:1: (lv_right_3_0= ruleType )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:519:3: lv_right_3_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getInstanceofExpressionAccess().getRightTypeParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleInstanceofExpression1185);
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
                              		"Type");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:543:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:544:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:545:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression1223);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression1233); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:552:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
    public final EObject ruleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject this_AdditiveExpression_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:555:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:556:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:556:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:557:5: this_AdditiveExpression_0= ruleAdditiveExpression ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression1280);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:1: ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop8:
            do {
                int alt8=2;
                switch ( input.LA(1) ) {
                case 67:
                    {
                    int LA8_2 = input.LA(2);

                    if ( (synpred6_InternalJavaJRExpression()) ) {
                        alt8=1;
                    }


                    }
                    break;
                case 55:
                    {
                    int LA8_3 = input.LA(2);

                    if ( (synpred6_InternalJavaJRExpression()) ) {
                        alt8=1;
                    }


                    }
                    break;
                case 68:
                    {
                    int LA8_4 = input.LA(2);

                    if ( (synpred6_InternalJavaJRExpression()) ) {
                        alt8=1;
                    }


                    }
                    break;
                case 56:
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
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:3: ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:570:6: ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:570:7: () ( (lv_op_2_0= ruleRelationalOp ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:570:7: ()
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:571:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getRelationalExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:576:2: ( (lv_op_2_0= ruleRelationalOp ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:577:1: (lv_op_2_0= ruleRelationalOp )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:577:1: (lv_op_2_0= ruleRelationalOp )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:578:3: lv_op_2_0= ruleRelationalOp
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getOpRelationalOpParserRuleCall_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalOp_in_ruleRelationalExpression1329);
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
            	              		"RelationalOp");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:594:4: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:595:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:595:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:596:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression1352);
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
            	              		"AdditiveExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:620:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:621:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:622:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression1390);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression1400); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:629:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:632:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:633:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:633:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:634:5: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression1447);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:1: ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) ) )*
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
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:655:6: ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:655:7: () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:655:7: ()
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:656:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAdditiveExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:661:2: ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:662:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:662:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:663:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:663:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
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
            	            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:664:3: lv_op_2_1= '+'
            	            {
            	            lv_op_2_1=(Token)match(input,33,FOLLOW_33_in_ruleAdditiveExpression1519); if (state.failed) return current;
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
            	            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:676:8: lv_op_2_2= '-'
            	            {
            	            lv_op_2_2=(Token)match(input,34,FOLLOW_34_in_ruleAdditiveExpression1548); if (state.failed) return current;
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

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:691:4: ( (lv_right_3_0= ruleMultiplicativeExpression ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:692:1: (lv_right_3_0= ruleMultiplicativeExpression )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:692:1: (lv_right_3_0= ruleMultiplicativeExpression )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:693:3: lv_right_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getRightMultiplicativeExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression1587);
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
            	              		"MultiplicativeExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:717:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:718:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:719:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression1625);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression1635); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:726:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        EObject this_UnaryExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:729:28: ( (this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:730:1: (this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:730:1: (this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:731:5: this_UnaryExpression_0= ruleUnaryExpression ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleUnaryExpression_in_ruleMultiplicativeExpression1682);
            this_UnaryExpression_0=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:1: ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) ) )*
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
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:2: ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= ruleUnaryExpression ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:2: ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:3: ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:755:6: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:755:7: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:755:7: ()
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:756:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getMultiplicativeExpressionAccess().getBinaryExpressionLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:761:2: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:762:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:762:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:763:1: (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:763:1: (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' )
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
            	            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:764:3: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,35,FOLLOW_35_in_ruleMultiplicativeExpression1768); if (state.failed) return current;
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
            	            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:776:8: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,36,FOLLOW_36_in_ruleMultiplicativeExpression1797); if (state.failed) return current;
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
            	            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:788:8: lv_op_2_3= '%'
            	            {
            	            lv_op_2_3=(Token)match(input,37,FOLLOW_37_in_ruleMultiplicativeExpression1826); if (state.failed) return current;
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

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:803:4: ( (lv_right_3_0= ruleUnaryExpression ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:804:1: (lv_right_3_0= ruleUnaryExpression )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:804:1: (lv_right_3_0= ruleUnaryExpression )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:805:3: lv_right_3_0= ruleUnaryExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getRightUnaryExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleMultiplicativeExpression1865);
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
            	              		"UnaryExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:829:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:830:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:831:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression1903);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression1913); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:838:1: ruleUnaryExpression returns [EObject current=null] : ( (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression ) | this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_UnaryExpression_1 = null;

        EObject this_UnaryExpression_3 = null;

        EObject this_UnaryExpressionNotPlusMinus_4 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:841:28: ( ( (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression ) | this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:842:1: ( (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression ) | this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:842:1: ( (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression ) | this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus )
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
            case 47:
            case 48:
            case 49:
            case 50:
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:842:2: (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:842:2: (otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:842:4: otherlv_0= '+' this_UnaryExpression_1= ruleUnaryExpression
                    {
                    otherlv_0=(Token)match(input,33,FOLLOW_33_in_ruleUnaryExpression1951); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getUnaryExpressionAccess().getPlusSignKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionAccess().getUnaryExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryExpression1973);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:856:6: (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:856:6: (otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:856:8: otherlv_2= '-' this_UnaryExpression_3= ruleUnaryExpression
                    {
                    otherlv_2=(Token)match(input,34,FOLLOW_34_in_ruleUnaryExpression1992); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getUnaryExpressionAccess().getHyphenMinusKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionAccess().getUnaryExpressionParserRuleCall_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryExpression2014);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:871:5: this_UnaryExpressionNotPlusMinus_4= ruleUnaryExpressionNotPlusMinus
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionAccess().getUnaryExpressionNotPlusMinusParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpressionNotPlusMinus_in_ruleUnaryExpression2042);
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:887:1: entryRuleUnaryExpressionNotPlusMinus returns [EObject current=null] : iv_ruleUnaryExpressionNotPlusMinus= ruleUnaryExpressionNotPlusMinus EOF ;
    public final EObject entryRuleUnaryExpressionNotPlusMinus() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpressionNotPlusMinus = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:888:2: (iv_ruleUnaryExpressionNotPlusMinus= ruleUnaryExpressionNotPlusMinus EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:889:2: iv_ruleUnaryExpressionNotPlusMinus= ruleUnaryExpressionNotPlusMinus EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryExpressionNotPlusMinus_in_entryRuleUnaryExpressionNotPlusMinus2077);
            iv_ruleUnaryExpressionNotPlusMinus=ruleUnaryExpressionNotPlusMinus();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpressionNotPlusMinus; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpressionNotPlusMinus2087); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:896:1: ruleUnaryExpressionNotPlusMinus returns [EObject current=null] : ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression ) ;
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
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:899:28: ( ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:900:1: ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:900:1: ( (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression ) | (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression ) | ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression ) | this_PrimaryExpression_5= rulePrimaryExpression )
            int alt14=4;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:900:2: (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:900:2: (otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:900:4: otherlv_0= '~' this_UnaryExpression_1= ruleUnaryExpression
                    {
                    otherlv_0=(Token)match(input,38,FOLLOW_38_in_ruleUnaryExpressionNotPlusMinus2125); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getUnaryExpressionNotPlusMinusAccess().getTildeKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusAccess().getUnaryExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryExpressionNotPlusMinus2147);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:914:6: (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:914:6: (otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:914:8: otherlv_2= '!' this_UnaryExpression_3= ruleUnaryExpression
                    {
                    otherlv_2=(Token)match(input,39,FOLLOW_39_in_ruleUnaryExpressionNotPlusMinus2166); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getUnaryExpressionNotPlusMinusAccess().getExclamationMarkKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusAccess().getUnaryExpressionParserRuleCall_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryExpressionNotPlusMinus2188);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:928:6: ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:928:6: ( ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:928:7: ( ruleCastedExpression )=>this_CastedExpression_4= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusAccess().getCastedExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCastedExpression_in_ruleUnaryExpressionNotPlusMinus2222);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:939:5: this_PrimaryExpression_5= rulePrimaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryExpressionNotPlusMinusAccess().getPrimaryExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_rulePrimaryExpression_in_ruleUnaryExpressionNotPlusMinus2250);
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:955:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:956:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:957:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression2285);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression2295); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:964:1: rulePrimaryExpression returns [EObject current=null] : (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField ) ;
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
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:967:28: ( (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:968:1: (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:968:1: (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField )
            int alt15=6;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:969:5: this_ParExpression_0= ruleParExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleParExpression_in_rulePrimaryExpression2342);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:979:5: this_LiteralExpression_1= ruleLiteralExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteralExpression_in_rulePrimaryExpression2369);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:989:5: this_ArrayCreator_2= ruleArrayCreator
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getArrayCreatorParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleArrayCreator_in_rulePrimaryExpression2396);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:998:6: ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:998:6: ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:998:7: ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getBaseJRExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBaseJRExpression_in_rulePrimaryExpression2429);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1009:5: this_MethodsExpression_4= ruleMethodsExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getMethodsExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleMethodsExpression_in_rulePrimaryExpression2457);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1019:5: this_StaticField_5= ruleStaticField
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getStaticFieldParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStaticField_in_rulePrimaryExpression2484);
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1035:1: entryRuleStaticField returns [EObject current=null] : iv_ruleStaticField= ruleStaticField EOF ;
    public final EObject entryRuleStaticField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStaticField = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1036:2: (iv_ruleStaticField= ruleStaticField EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1037:2: iv_ruleStaticField= ruleStaticField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStaticFieldRule()); 
            }
            pushFollow(FOLLOW_ruleStaticField_in_entryRuleStaticField2519);
            iv_ruleStaticField=ruleStaticField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStaticField; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStaticField2529); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1044:1: ruleStaticField returns [EObject current=null] : ( () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) ) ) ;
    public final EObject ruleStaticField() throws RecognitionException {
        EObject current = null;

        Token lv_dots_2_0=null;
        AntlrDatatypeRuleToken lv_prefixQMN_1_0 = null;

        AntlrDatatypeRuleToken lv_fieldName_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1047:28: ( ( () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1048:1: ( () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1048:1: ( () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1048:2: () ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )* ( (lv_fieldName_3_0= ruleValidID ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1048:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1049:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getStaticFieldAccess().getStaticFieldAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1054:2: ( ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) ) )*
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
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1054:3: ( (lv_prefixQMN_1_0= ruleValidID ) ) ( (lv_dots_2_0= '.' ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1054:3: ( (lv_prefixQMN_1_0= ruleValidID ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1055:1: (lv_prefixQMN_1_0= ruleValidID )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1055:1: (lv_prefixQMN_1_0= ruleValidID )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1056:3: lv_prefixQMN_1_0= ruleValidID
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getStaticFieldAccess().getPrefixQMNValidIDParserRuleCall_1_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleValidID_in_ruleStaticField2585);
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
            	              		"ValidID");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1072:2: ( (lv_dots_2_0= '.' ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1073:1: (lv_dots_2_0= '.' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1073:1: (lv_dots_2_0= '.' )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1074:3: lv_dots_2_0= '.'
            	    {
            	    lv_dots_2_0=(Token)match(input,40,FOLLOW_40_in_ruleStaticField2603); if (state.failed) return current;
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

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1087:4: ( (lv_fieldName_3_0= ruleValidID ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1088:1: (lv_fieldName_3_0= ruleValidID )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1088:1: (lv_fieldName_3_0= ruleValidID )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1089:3: lv_fieldName_3_0= ruleValidID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getStaticFieldAccess().getFieldNameValidIDParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleValidID_in_ruleStaticField2639);
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
                      		"ValidID");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1113:1: entryRuleBaseJRExpression returns [EObject current=null] : iv_ruleBaseJRExpression= ruleBaseJRExpression EOF ;
    public final EObject entryRuleBaseJRExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBaseJRExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1114:2: (iv_ruleBaseJRExpression= ruleBaseJRExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1115:2: iv_ruleBaseJRExpression= ruleBaseJRExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBaseJRExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleBaseJRExpression_in_entryRuleBaseJRExpression2675);
            iv_ruleBaseJRExpression=ruleBaseJRExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBaseJRExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBaseJRExpression2685); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1122:1: ruleBaseJRExpression returns [EObject current=null] : (this_JRFieldObj_0= ruleJRFieldObj | this_JRParameterObj_1= ruleJRParameterObj | this_JRVariableObj_2= ruleJRVariableObj ) ;
    public final EObject ruleBaseJRExpression() throws RecognitionException {
        EObject current = null;

        EObject this_JRFieldObj_0 = null;

        EObject this_JRParameterObj_1 = null;

        EObject this_JRVariableObj_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1125:28: ( (this_JRFieldObj_0= ruleJRFieldObj | this_JRParameterObj_1= ruleJRParameterObj | this_JRVariableObj_2= ruleJRVariableObj ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1126:1: (this_JRFieldObj_0= ruleJRFieldObj | this_JRParameterObj_1= ruleJRParameterObj | this_JRVariableObj_2= ruleJRVariableObj )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1126:1: (this_JRFieldObj_0= ruleJRFieldObj | this_JRParameterObj_1= ruleJRParameterObj | this_JRVariableObj_2= ruleJRVariableObj )
            int alt17=3;
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
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1127:5: this_JRFieldObj_0= ruleJRFieldObj
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getBaseJRExpressionAccess().getJRFieldObjParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleJRFieldObj_in_ruleBaseJRExpression2732);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1137:5: this_JRParameterObj_1= ruleJRParameterObj
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getBaseJRExpressionAccess().getJRParameterObjParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleJRParameterObj_in_ruleBaseJRExpression2759);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1147:5: this_JRVariableObj_2= ruleJRVariableObj
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getBaseJRExpressionAccess().getJRVariableObjParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleJRVariableObj_in_ruleBaseJRExpression2786);
                    this_JRVariableObj_2=ruleJRVariableObj();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JRVariableObj_2; 
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1163:1: entryRuleJRFieldObj returns [EObject current=null] : iv_ruleJRFieldObj= ruleJRFieldObj EOF ;
    public final EObject entryRuleJRFieldObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRFieldObj = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1164:2: (iv_ruleJRFieldObj= ruleJRFieldObj EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1165:2: iv_ruleJRFieldObj= ruleJRFieldObj EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRFieldObjRule()); 
            }
            pushFollow(FOLLOW_ruleJRFieldObj_in_entryRuleJRFieldObj2821);
            iv_ruleJRFieldObj=ruleJRFieldObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRFieldObj; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRFieldObj2831); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1172:1: ruleJRFieldObj returns [EObject current=null] : ( () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) ;
    public final EObject ruleJRFieldObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_bracedIdentifier_2_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1175:28: ( ( () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1176:1: ( () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1176:1: ( () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1176:2: () otherlv_1= '$F' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1176:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1177:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJRFieldObjAccess().getJRFieldObjAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,41,FOLLOW_41_in_ruleJRFieldObj2877); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJRFieldObjAccess().getFKeyword_1());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1186:1: ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1187:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1187:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1188:3: lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER
            {
            lv_bracedIdentifier_2_0=(Token)match(input,RULE_BRACED_IDENTIFIER,FOLLOW_RULE_BRACED_IDENTIFIER_in_ruleJRFieldObj2894); if (state.failed) return current;
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
                      		"BRACED_IDENTIFIER");
              	    
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1212:1: entryRuleJRParameterObj returns [EObject current=null] : iv_ruleJRParameterObj= ruleJRParameterObj EOF ;
    public final EObject entryRuleJRParameterObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRParameterObj = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1213:2: (iv_ruleJRParameterObj= ruleJRParameterObj EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1214:2: iv_ruleJRParameterObj= ruleJRParameterObj EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRParameterObjRule()); 
            }
            pushFollow(FOLLOW_ruleJRParameterObj_in_entryRuleJRParameterObj2935);
            iv_ruleJRParameterObj=ruleJRParameterObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRParameterObj; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRParameterObj2945); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1221:1: ruleJRParameterObj returns [EObject current=null] : ( () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) ;
    public final EObject ruleJRParameterObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_bracedIdentifier_2_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1224:28: ( ( () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1225:1: ( () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1225:1: ( () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1225:2: () otherlv_1= '$P' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1225:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1226:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJRParameterObjAccess().getJRParameterObjAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,42,FOLLOW_42_in_ruleJRParameterObj2991); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJRParameterObjAccess().getPKeyword_1());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1235:1: ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1236:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1236:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1237:3: lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER
            {
            lv_bracedIdentifier_2_0=(Token)match(input,RULE_BRACED_IDENTIFIER,FOLLOW_RULE_BRACED_IDENTIFIER_in_ruleJRParameterObj3008); if (state.failed) return current;
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
                      		"BRACED_IDENTIFIER");
              	    
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1261:1: entryRuleJRVariableObj returns [EObject current=null] : iv_ruleJRVariableObj= ruleJRVariableObj EOF ;
    public final EObject entryRuleJRVariableObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJRVariableObj = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1262:2: (iv_ruleJRVariableObj= ruleJRVariableObj EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1263:2: iv_ruleJRVariableObj= ruleJRVariableObj EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJRVariableObjRule()); 
            }
            pushFollow(FOLLOW_ruleJRVariableObj_in_entryRuleJRVariableObj3049);
            iv_ruleJRVariableObj=ruleJRVariableObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJRVariableObj; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRVariableObj3059); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1270:1: ruleJRVariableObj returns [EObject current=null] : ( () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) ;
    public final EObject ruleJRVariableObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_bracedIdentifier_2_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1273:28: ( ( () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1274:1: ( () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1274:1: ( () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1274:2: () otherlv_1= '$V' ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1274:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1275:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJRVariableObjAccess().getJRVariableObjAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,43,FOLLOW_43_in_ruleJRVariableObj3105); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJRVariableObjAccess().getVKeyword_1());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1284:1: ( (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1285:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1285:1: (lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1286:3: lv_bracedIdentifier_2_0= RULE_BRACED_IDENTIFIER
            {
            lv_bracedIdentifier_2_0=(Token)match(input,RULE_BRACED_IDENTIFIER,FOLLOW_RULE_BRACED_IDENTIFIER_in_ruleJRVariableObj3122); if (state.failed) return current;
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
                      		"BRACED_IDENTIFIER");
              	    
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


    // $ANTLR start "entryRuleMethodsExpression"
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1310:1: entryRuleMethodsExpression returns [EObject current=null] : iv_ruleMethodsExpression= ruleMethodsExpression EOF ;
    public final EObject entryRuleMethodsExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethodsExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1311:2: (iv_ruleMethodsExpression= ruleMethodsExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1312:2: iv_ruleMethodsExpression= ruleMethodsExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMethodsExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMethodsExpression_in_entryRuleMethodsExpression3163);
            iv_ruleMethodsExpression=ruleMethodsExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMethodsExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMethodsExpression3173); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1319:1: ruleMethodsExpression returns [EObject current=null] : ( () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )* ) ;
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
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1322:28: ( ( () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1323:1: ( () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1323:1: ( () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1323:2: () ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) ) (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )*
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1323:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1324:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getMethodsExpressionAccess().getMethodsExpressionAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1329:2: ( ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* ) | ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==RULE_ID||LA22_0==44) ) {
                alt22=1;
            }
            else if ( (LA22_0==RULE_STRING||(LA22_0>=41 && LA22_0<=43)) ) {
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1329:3: ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1329:3: ( ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )* )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1329:4: ( (lv_includeObjectInstatiation_1_0= 'new' ) )? ( (lv_methodInvocations_2_0= ruleMethodInvocation ) ) (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )*
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1329:4: ( (lv_includeObjectInstatiation_1_0= 'new' ) )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==44) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1330:1: (lv_includeObjectInstatiation_1_0= 'new' )
                            {
                            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1330:1: (lv_includeObjectInstatiation_1_0= 'new' )
                            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1331:3: lv_includeObjectInstatiation_1_0= 'new'
                            {
                            lv_includeObjectInstatiation_1_0=(Token)match(input,44,FOLLOW_44_in_ruleMethodsExpression3227); if (state.failed) return current;
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

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1344:3: ( (lv_methodInvocations_2_0= ruleMethodInvocation ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1345:1: (lv_methodInvocations_2_0= ruleMethodInvocation )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1345:1: (lv_methodInvocations_2_0= ruleMethodInvocation )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1346:3: lv_methodInvocations_2_0= ruleMethodInvocation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getMethodInvocationsMethodInvocationParserRuleCall_1_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleMethodInvocation_in_ruleMethodsExpression3262);
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
                              		"MethodInvocation");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1362:2: (otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==40) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1362:4: otherlv_3= '.' ( (lv_methodInvocations_4_0= ruleMethodInvocation ) )
                    	    {
                    	    otherlv_3=(Token)match(input,40,FOLLOW_40_in_ruleMethodsExpression3275); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getMethodsExpressionAccess().getFullStopKeyword_1_0_2_0());
                    	          
                    	    }
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1366:1: ( (lv_methodInvocations_4_0= ruleMethodInvocation ) )
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1367:1: (lv_methodInvocations_4_0= ruleMethodInvocation )
                    	    {
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1367:1: (lv_methodInvocations_4_0= ruleMethodInvocation )
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1368:3: lv_methodInvocations_4_0= ruleMethodInvocation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getMethodInvocationsMethodInvocationParserRuleCall_1_0_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleMethodInvocation_in_ruleMethodsExpression3296);
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
                    	              		"MethodInvocation");
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1385:6: ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1385:6: ( ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+ )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1385:7: ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) ) (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1385:7: ( ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1386:1: ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1386:1: ( (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1387:1: (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1387:1: (lv_objectExpression_5_1= ruleBaseJRExpression | lv_objectExpression_5_2= ruleStringLiteral )
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( ((LA20_0>=41 && LA20_0<=43)) ) {
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
                            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1388:3: lv_objectExpression_5_1= ruleBaseJRExpression
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getObjectExpressionBaseJRExpressionParserRuleCall_1_1_0_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleBaseJRExpression_in_ruleMethodsExpression3329);
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
                                      		"BaseJRExpression");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1403:8: lv_objectExpression_5_2= ruleStringLiteral
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getObjectExpressionStringLiteralParserRuleCall_1_1_0_0_1()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleStringLiteral_in_ruleMethodsExpression3348);
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
                                      		"StringLiteral");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1421:2: (otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) ) )+
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
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1421:4: otherlv_6= '.' ( (lv_methodInvocations_7_0= ruleMethodInvocation ) )
                    	    {
                    	    otherlv_6=(Token)match(input,40,FOLLOW_40_in_ruleMethodsExpression3364); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_6, grammarAccess.getMethodsExpressionAccess().getFullStopKeyword_1_1_1_0());
                    	          
                    	    }
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1425:1: ( (lv_methodInvocations_7_0= ruleMethodInvocation ) )
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1426:1: (lv_methodInvocations_7_0= ruleMethodInvocation )
                    	    {
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1426:1: (lv_methodInvocations_7_0= ruleMethodInvocation )
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1427:3: lv_methodInvocations_7_0= ruleMethodInvocation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getMethodInvocationsMethodInvocationParserRuleCall_1_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleMethodInvocation_in_ruleMethodsExpression3385);
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
                    	              		"MethodInvocation");
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

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1443:6: (otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']' )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==45) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1443:8: otherlv_8= '[' ( (lv_arrayIndexes_9_0= ruleIntLiteral ) ) otherlv_10= ']'
            	    {
            	    otherlv_8=(Token)match(input,45,FOLLOW_45_in_ruleMethodsExpression3402); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getMethodsExpressionAccess().getLeftSquareBracketKeyword_2_0());
            	          
            	    }
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1447:1: ( (lv_arrayIndexes_9_0= ruleIntLiteral ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1448:1: (lv_arrayIndexes_9_0= ruleIntLiteral )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1448:1: (lv_arrayIndexes_9_0= ruleIntLiteral )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1449:3: lv_arrayIndexes_9_0= ruleIntLiteral
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMethodsExpressionAccess().getArrayIndexesIntLiteralParserRuleCall_2_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIntLiteral_in_ruleMethodsExpression3423);
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
            	              		"IntLiteral");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_10=(Token)match(input,46,FOLLOW_46_in_ruleMethodsExpression3435); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1477:1: entryRuleLiteralExpression returns [EObject current=null] : iv_ruleLiteralExpression= ruleLiteralExpression EOF ;
    public final EObject entryRuleLiteralExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteralExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1478:2: (iv_ruleLiteralExpression= ruleLiteralExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1479:2: iv_ruleLiteralExpression= ruleLiteralExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLiteralExpression_in_entryRuleLiteralExpression3473);
            iv_ruleLiteralExpression=ruleLiteralExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteralExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteralExpression3483); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1486:1: ruleLiteralExpression returns [EObject current=null] : (this_IntLiteral_0= ruleIntLiteral | this_LongLiteral_1= ruleLongLiteral | this_FloatLiteral_2= ruleFloatLiteral | this_DoubleLiteral_3= ruleDoubleLiteral | this_CharLiteral_4= ruleCharLiteral | this_StringLiteral_5= ruleStringLiteral | this_BooleanLiteral_6= ruleBooleanLiteral | this_NullLiteral_7= ruleNullLiteral ) ;
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
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1489:28: ( (this_IntLiteral_0= ruleIntLiteral | this_LongLiteral_1= ruleLongLiteral | this_FloatLiteral_2= ruleFloatLiteral | this_DoubleLiteral_3= ruleDoubleLiteral | this_CharLiteral_4= ruleCharLiteral | this_StringLiteral_5= ruleStringLiteral | this_BooleanLiteral_6= ruleBooleanLiteral | this_NullLiteral_7= ruleNullLiteral ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1490:1: (this_IntLiteral_0= ruleIntLiteral | this_LongLiteral_1= ruleLongLiteral | this_FloatLiteral_2= ruleFloatLiteral | this_DoubleLiteral_3= ruleDoubleLiteral | this_CharLiteral_4= ruleCharLiteral | this_StringLiteral_5= ruleStringLiteral | this_BooleanLiteral_6= ruleBooleanLiteral | this_NullLiteral_7= ruleNullLiteral )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1490:1: (this_IntLiteral_0= ruleIntLiteral | this_LongLiteral_1= ruleLongLiteral | this_FloatLiteral_2= ruleFloatLiteral | this_DoubleLiteral_3= ruleDoubleLiteral | this_CharLiteral_4= ruleCharLiteral | this_StringLiteral_5= ruleStringLiteral | this_BooleanLiteral_6= ruleBooleanLiteral | this_NullLiteral_7= ruleNullLiteral )
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
            case 47:
            case 48:
                {
                alt24=7;
                }
                break;
            case 49:
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1491:5: this_IntLiteral_0= ruleIntLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getIntLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntLiteral_in_ruleLiteralExpression3530);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1501:5: this_LongLiteral_1= ruleLongLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getLongLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLongLiteral_in_ruleLiteralExpression3557);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1511:5: this_FloatLiteral_2= ruleFloatLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getFloatLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFloatLiteral_in_ruleLiteralExpression3584);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1521:5: this_DoubleLiteral_3= ruleDoubleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getDoubleLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleDoubleLiteral_in_ruleLiteralExpression3611);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1531:5: this_CharLiteral_4= ruleCharLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getCharLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCharLiteral_in_ruleLiteralExpression3638);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1541:5: this_StringLiteral_5= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getStringLiteralParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteralExpression3665);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1551:5: this_BooleanLiteral_6= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getBooleanLiteralParserRuleCall_6()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteralExpression3692);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1561:5: this_NullLiteral_7= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralExpressionAccess().getNullLiteralParserRuleCall_7()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteralExpression3719);
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1577:1: entryRuleIntLiteral returns [EObject current=null] : iv_ruleIntLiteral= ruleIntLiteral EOF ;
    public final EObject entryRuleIntLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntLiteral = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1578:2: (iv_ruleIntLiteral= ruleIntLiteral EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1579:2: iv_ruleIntLiteral= ruleIntLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleIntLiteral_in_entryRuleIntLiteral3754);
            iv_ruleIntLiteral=ruleIntLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntLiteral3764); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1586:1: ruleIntLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_INT ) ) ) ;
    public final EObject ruleIntLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1589:28: ( ( () ( (lv_value_1_0= RULE_INT ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1590:1: ( () ( (lv_value_1_0= RULE_INT ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1590:1: ( () ( (lv_value_1_0= RULE_INT ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1590:2: () ( (lv_value_1_0= RULE_INT ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1590:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1591:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getIntLiteralAccess().getIntLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1596:2: ( (lv_value_1_0= RULE_INT ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1597:1: (lv_value_1_0= RULE_INT )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1597:1: (lv_value_1_0= RULE_INT )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1598:3: lv_value_1_0= RULE_INT
            {
            lv_value_1_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntLiteral3815); if (state.failed) return current;
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
                      		"INT");
              	    
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1622:1: entryRuleLongLiteral returns [EObject current=null] : iv_ruleLongLiteral= ruleLongLiteral EOF ;
    public final EObject entryRuleLongLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLongLiteral = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1623:2: (iv_ruleLongLiteral= ruleLongLiteral EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1624:2: iv_ruleLongLiteral= ruleLongLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLongLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleLongLiteral_in_entryRuleLongLiteral3856);
            iv_ruleLongLiteral=ruleLongLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLongLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongLiteral3866); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1631:1: ruleLongLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_LONG ) ) ) ;
    public final EObject ruleLongLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1634:28: ( ( () ( (lv_value_1_0= RULE_LONG ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1635:1: ( () ( (lv_value_1_0= RULE_LONG ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1635:1: ( () ( (lv_value_1_0= RULE_LONG ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1635:2: () ( (lv_value_1_0= RULE_LONG ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1635:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1636:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getLongLiteralAccess().getLongLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1641:2: ( (lv_value_1_0= RULE_LONG ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1642:1: (lv_value_1_0= RULE_LONG )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1642:1: (lv_value_1_0= RULE_LONG )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1643:3: lv_value_1_0= RULE_LONG
            {
            lv_value_1_0=(Token)match(input,RULE_LONG,FOLLOW_RULE_LONG_in_ruleLongLiteral3917); if (state.failed) return current;
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
                      		"LONG");
              	    
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1667:1: entryRuleFloatLiteral returns [EObject current=null] : iv_ruleFloatLiteral= ruleFloatLiteral EOF ;
    public final EObject entryRuleFloatLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatLiteral = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1668:2: (iv_ruleFloatLiteral= ruleFloatLiteral EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1669:2: iv_ruleFloatLiteral= ruleFloatLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFloatLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleFloatLiteral_in_entryRuleFloatLiteral3958);
            iv_ruleFloatLiteral=ruleFloatLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFloatLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloatLiteral3968); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1676:1: ruleFloatLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_FLOAT ) ) ) ;
    public final EObject ruleFloatLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1679:28: ( ( () ( (lv_value_1_0= RULE_FLOAT ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1680:1: ( () ( (lv_value_1_0= RULE_FLOAT ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1680:1: ( () ( (lv_value_1_0= RULE_FLOAT ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1680:2: () ( (lv_value_1_0= RULE_FLOAT ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1680:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1681:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getFloatLiteralAccess().getFloatLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1686:2: ( (lv_value_1_0= RULE_FLOAT ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1687:1: (lv_value_1_0= RULE_FLOAT )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1687:1: (lv_value_1_0= RULE_FLOAT )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1688:3: lv_value_1_0= RULE_FLOAT
            {
            lv_value_1_0=(Token)match(input,RULE_FLOAT,FOLLOW_RULE_FLOAT_in_ruleFloatLiteral4019); if (state.failed) return current;
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
                      		"FLOAT");
              	    
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1712:1: entryRuleDoubleLiteral returns [EObject current=null] : iv_ruleDoubleLiteral= ruleDoubleLiteral EOF ;
    public final EObject entryRuleDoubleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoubleLiteral = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1713:2: (iv_ruleDoubleLiteral= ruleDoubleLiteral EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1714:2: iv_ruleDoubleLiteral= ruleDoubleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDoubleLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleDoubleLiteral_in_entryRuleDoubleLiteral4060);
            iv_ruleDoubleLiteral=ruleDoubleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDoubleLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleLiteral4070); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1721:1: ruleDoubleLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleDoubleLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1724:28: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1725:1: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1725:1: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1725:2: () ( (lv_value_1_0= RULE_DOUBLE ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1725:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1726:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getDoubleLiteralAccess().getDoubleLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1731:2: ( (lv_value_1_0= RULE_DOUBLE ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1732:1: (lv_value_1_0= RULE_DOUBLE )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1732:1: (lv_value_1_0= RULE_DOUBLE )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1733:3: lv_value_1_0= RULE_DOUBLE
            {
            lv_value_1_0=(Token)match(input,RULE_DOUBLE,FOLLOW_RULE_DOUBLE_in_ruleDoubleLiteral4121); if (state.failed) return current;
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
                      		"DOUBLE");
              	    
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1757:1: entryRuleCharLiteral returns [EObject current=null] : iv_ruleCharLiteral= ruleCharLiteral EOF ;
    public final EObject entryRuleCharLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCharLiteral = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1758:2: (iv_ruleCharLiteral= ruleCharLiteral EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1759:2: iv_ruleCharLiteral= ruleCharLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCharLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleCharLiteral_in_entryRuleCharLiteral4162);
            iv_ruleCharLiteral=ruleCharLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCharLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCharLiteral4172); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1766:1: ruleCharLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_CHAR ) ) ) ;
    public final EObject ruleCharLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1769:28: ( ( () ( (lv_value_1_0= RULE_CHAR ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1770:1: ( () ( (lv_value_1_0= RULE_CHAR ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1770:1: ( () ( (lv_value_1_0= RULE_CHAR ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1770:2: () ( (lv_value_1_0= RULE_CHAR ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1770:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1771:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getCharLiteralAccess().getCharLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1776:2: ( (lv_value_1_0= RULE_CHAR ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1777:1: (lv_value_1_0= RULE_CHAR )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1777:1: (lv_value_1_0= RULE_CHAR )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1778:3: lv_value_1_0= RULE_CHAR
            {
            lv_value_1_0=(Token)match(input,RULE_CHAR,FOLLOW_RULE_CHAR_in_ruleCharLiteral4223); if (state.failed) return current;
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
                      		"CHAR");
              	    
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1802:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1803:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1804:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral4264);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral4274); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1811:1: ruleStringLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1814:28: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1815:1: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1815:1: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1815:2: () ( (lv_value_1_0= RULE_STRING ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1815:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1816:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getStringLiteralAccess().getStringLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1821:2: ( (lv_value_1_0= RULE_STRING ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1822:1: (lv_value_1_0= RULE_STRING )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1822:1: (lv_value_1_0= RULE_STRING )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1823:3: lv_value_1_0= RULE_STRING
            {
            lv_value_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral4325); if (state.failed) return current;
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
                      		"STRING");
              	    
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1847:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1848:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1849:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral4366);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral4376); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1856:1: ruleBooleanLiteral returns [EObject current=null] : ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_isTrue_2_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1859:28: ( ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1860:1: ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1860:1: ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1860:2: () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1860:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1861:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getBooleanLiteralAccess().getBooleanLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1866:2: (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==47) ) {
                alt25=1;
            }
            else if ( (LA25_0==48) ) {
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1866:4: otherlv_1= 'false'
                    {
                    otherlv_1=(Token)match(input,47,FOLLOW_47_in_ruleBooleanLiteral4423); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getBooleanLiteralAccess().getFalseKeyword_1_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1871:6: ( (lv_isTrue_2_0= 'true' ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1871:6: ( (lv_isTrue_2_0= 'true' ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1872:1: (lv_isTrue_2_0= 'true' )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1872:1: (lv_isTrue_2_0= 'true' )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1873:3: lv_isTrue_2_0= 'true'
                    {
                    lv_isTrue_2_0=(Token)match(input,48,FOLLOW_48_in_ruleBooleanLiteral4447); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1894:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1895:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1896:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral4497);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral4507); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1903:1: ruleNullLiteral returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1906:28: ( ( () otherlv_1= 'null' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1907:1: ( () otherlv_1= 'null' )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1907:1: ( () otherlv_1= 'null' )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1907:2: () otherlv_1= 'null'
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1907:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1908:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getNullLiteralAccess().getNullLiteralAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,49,FOLLOW_49_in_ruleNullLiteral4553); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1925:1: entryRuleParExpression returns [EObject current=null] : iv_ruleParExpression= ruleParExpression EOF ;
    public final EObject entryRuleParExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1926:2: (iv_ruleParExpression= ruleParExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1927:2: iv_ruleParExpression= ruleParExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleParExpression_in_entryRuleParExpression4589);
            iv_ruleParExpression=ruleParExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleParExpression4599); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1934:1: ruleParExpression returns [EObject current=null] : (otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')' ) ;
    public final EObject ruleParExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_JasperReportsExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1937:28: ( (otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1938:1: (otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')' )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1938:1: (otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')' )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1938:3: otherlv_0= '(' this_JasperReportsExpression_1= ruleJasperReportsExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,50,FOLLOW_50_in_ruleParExpression4636); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParExpressionAccess().getJasperReportsExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_ruleJasperReportsExpression_in_ruleParExpression4658);
            this_JasperReportsExpression_1=ruleJasperReportsExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_JasperReportsExpression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,51,FOLLOW_51_in_ruleParExpression4669); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1963:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1964:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1965:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression4705);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCastedExpression4715); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1972:1: ruleCastedExpression returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_castType_2_0 = null;

        EObject lv_castedExpr_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1975:28: ( ( () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1976:1: ( () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1976:1: ( () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1976:2: () otherlv_1= '(' ( (lv_castType_2_0= ruleType ) ) otherlv_3= ')' ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1976:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1977:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getCastedExpressionAccess().getCastedExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,50,FOLLOW_50_in_ruleCastedExpression4761); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1986:1: ( (lv_castType_2_0= ruleType ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1987:1: (lv_castType_2_0= ruleType )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1987:1: (lv_castType_2_0= ruleType )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:1988:3: lv_castType_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getCastTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleCastedExpression4782);
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
                      		"Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,51,FOLLOW_51_in_ruleCastedExpression4794); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_3());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2008:1: ( (lv_castedExpr_4_0= ruleJasperReportsExpression ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2009:1: (lv_castedExpr_4_0= ruleJasperReportsExpression )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2009:1: (lv_castedExpr_4_0= ruleJasperReportsExpression )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2010:3: lv_castedExpr_4_0= ruleJasperReportsExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getCastedExprJasperReportsExpressionParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJasperReportsExpression_in_ruleCastedExpression4815);
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
                      		"JasperReportsExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2034:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2035:2: (iv_ruleType= ruleType EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2036:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_ruleType_in_entryRuleType4851);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleType4861); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2043:1: ruleType returns [EObject current=null] : ( () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) ) ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_primitiveType_1_0 = null;

        EObject lv_jvmType_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2046:28: ( ( () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2047:1: ( () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2047:1: ( () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2047:2: () ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2047:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2048:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTypeAccess().getTypeAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2053:2: ( ( (lv_primitiveType_1_0= rulePrimitiveType ) ) | ( (lv_jvmType_2_0= ruleJvmTypeReference ) ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=59 && LA26_0<=66)) ) {
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2053:3: ( (lv_primitiveType_1_0= rulePrimitiveType ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2053:3: ( (lv_primitiveType_1_0= rulePrimitiveType ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2054:1: (lv_primitiveType_1_0= rulePrimitiveType )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2054:1: (lv_primitiveType_1_0= rulePrimitiveType )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2055:3: lv_primitiveType_1_0= rulePrimitiveType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeAccess().getPrimitiveTypePrimitiveTypeParserRuleCall_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePrimitiveType_in_ruleType4917);
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
                              		"PrimitiveType");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2072:6: ( (lv_jvmType_2_0= ruleJvmTypeReference ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2072:6: ( (lv_jvmType_2_0= ruleJvmTypeReference ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2073:1: (lv_jvmType_2_0= ruleJvmTypeReference )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2073:1: (lv_jvmType_2_0= ruleJvmTypeReference )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2074:3: lv_jvmType_2_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeAccess().getJvmTypeJvmTypeReferenceParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleType4944);
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
                              		"JvmTypeReference");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2098:1: entryRuleArrayCreator returns [EObject current=null] : iv_ruleArrayCreator= ruleArrayCreator EOF ;
    public final EObject entryRuleArrayCreator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayCreator = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2099:2: (iv_ruleArrayCreator= ruleArrayCreator EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2100:2: iv_ruleArrayCreator= ruleArrayCreator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayCreatorRule()); 
            }
            pushFollow(FOLLOW_ruleArrayCreator_in_entryRuleArrayCreator4981);
            iv_ruleArrayCreator=ruleArrayCreator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayCreator; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayCreator4991); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2107:1: ruleArrayCreator returns [EObject current=null] : ( () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) ) ) ;
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
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2110:28: ( ( () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2111:1: ( () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2111:1: ( () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2111:2: () otherlv_1= 'new' ( (lv_type_2_0= ruleType ) ) ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2111:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2112:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrayCreatorAccess().getArrayCreatorAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,44,FOLLOW_44_in_ruleArrayCreator5037); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrayCreatorAccess().getNewKeyword_1());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2121:1: ( (lv_type_2_0= ruleType ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2122:1: (lv_type_2_0= ruleType )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2122:1: (lv_type_2_0= ruleType )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2123:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArrayCreatorAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleArrayCreator5058);
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
                      		"Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2139:2: ( (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+ | ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==45) ) {
                int LA29_1 = input.LA(2);

                if ( (LA29_1==46) ) {
                    alt29=2;
                }
                else if ( (LA29_1==RULE_INT) ) {
                    alt29=1;
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2139:3: (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2139:3: (otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']' )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==45) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2139:5: otherlv_3= '[' ( (lv_size_4_0= ruleIntLiteral ) ) otherlv_5= ']'
                    	    {
                    	    otherlv_3=(Token)match(input,45,FOLLOW_45_in_ruleArrayCreator5072); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getArrayCreatorAccess().getLeftSquareBracketKeyword_3_0_0());
                    	          
                    	    }
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2143:1: ( (lv_size_4_0= ruleIntLiteral ) )
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2144:1: (lv_size_4_0= ruleIntLiteral )
                    	    {
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2144:1: (lv_size_4_0= ruleIntLiteral )
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2145:3: lv_size_4_0= ruleIntLiteral
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getArrayCreatorAccess().getSizeIntLiteralParserRuleCall_3_0_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleIntLiteral_in_ruleArrayCreator5093);
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
                    	              		"IntLiteral");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_5=(Token)match(input,46,FOLLOW_46_in_ruleArrayCreator5105); if (state.failed) return current;
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2166:6: ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2166:6: ( (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2166:7: (otherlv_6= '[' otherlv_7= ']' )+ ( (lv_initialization_8_0= ruleArrayInitializer ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2166:7: (otherlv_6= '[' otherlv_7= ']' )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==45) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2166:9: otherlv_6= '[' otherlv_7= ']'
                    	    {
                    	    otherlv_6=(Token)match(input,45,FOLLOW_45_in_ruleArrayCreator5127); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_6, grammarAccess.getArrayCreatorAccess().getLeftSquareBracketKeyword_3_1_0_0());
                    	          
                    	    }
                    	    otherlv_7=(Token)match(input,46,FOLLOW_46_in_ruleArrayCreator5139); if (state.failed) return current;
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

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2174:3: ( (lv_initialization_8_0= ruleArrayInitializer ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2175:1: (lv_initialization_8_0= ruleArrayInitializer )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2175:1: (lv_initialization_8_0= ruleArrayInitializer )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2176:3: lv_initialization_8_0= ruleArrayInitializer
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArrayCreatorAccess().getInitializationArrayInitializerParserRuleCall_3_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleArrayInitializer_in_ruleArrayCreator5162);
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
                              		"ArrayInitializer");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2200:1: entryRuleArrayInitializer returns [EObject current=null] : iv_ruleArrayInitializer= ruleArrayInitializer EOF ;
    public final EObject entryRuleArrayInitializer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayInitializer = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2201:2: (iv_ruleArrayInitializer= ruleArrayInitializer EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2202:2: iv_ruleArrayInitializer= ruleArrayInitializer EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayInitializerRule()); 
            }
            pushFollow(FOLLOW_ruleArrayInitializer_in_entryRuleArrayInitializer5200);
            iv_ruleArrayInitializer=ruleArrayInitializer();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayInitializer; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayInitializer5210); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2209:1: ruleArrayInitializer returns [EObject current=null] : ( () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}' ) ;
    public final EObject ruleArrayInitializer() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_initialization_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2212:28: ( ( () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2213:1: ( () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}' )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2213:1: ( () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}' )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2213:2: () otherlv_1= '{' ( (lv_initialization_2_0= ruleExpressionList ) )? otherlv_3= '}'
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2213:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2214:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrayInitializerAccess().getArrayInitializerAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,52,FOLLOW_52_in_ruleArrayInitializer5256); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrayInitializerAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2223:1: ( (lv_initialization_2_0= ruleExpressionList ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=RULE_INT && LA30_0<=RULE_ID)||(LA30_0>=33 && LA30_0<=34)||(LA30_0>=38 && LA30_0<=39)||(LA30_0>=41 && LA30_0<=44)||(LA30_0>=47 && LA30_0<=50)) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2224:1: (lv_initialization_2_0= ruleExpressionList )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2224:1: (lv_initialization_2_0= ruleExpressionList )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2225:3: lv_initialization_2_0= ruleExpressionList
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArrayInitializerAccess().getInitializationExpressionListParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpressionList_in_ruleArrayInitializer5277);
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
                              		"ExpressionList");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,53,FOLLOW_53_in_ruleArrayInitializer5290); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2253:1: entryRuleMethodInvocation returns [EObject current=null] : iv_ruleMethodInvocation= ruleMethodInvocation EOF ;
    public final EObject entryRuleMethodInvocation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethodInvocation = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2254:2: (iv_ruleMethodInvocation= ruleMethodInvocation EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2255:2: iv_ruleMethodInvocation= ruleMethodInvocation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMethodInvocationRule()); 
            }
            pushFollow(FOLLOW_ruleMethodInvocation_in_entryRuleMethodInvocation5326);
            iv_ruleMethodInvocation=ruleMethodInvocation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMethodInvocation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMethodInvocation5336); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2262:1: ruleMethodInvocation returns [EObject current=null] : ( () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) ) ) ;
    public final EObject ruleMethodInvocation() throws RecognitionException {
        EObject current = null;

        EObject lv_fullyQualifiedMethodName_1_0 = null;

        EObject lv_args_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2265:28: ( ( () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2266:1: ( () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2266:1: ( () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2266:2: () ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) ) ( (lv_args_2_0= ruleArguments ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2266:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2267:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getMethodInvocationAccess().getMethodInvocationAction_0(),
                          current);
                  
            }

            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2272:2: ( (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2273:1: (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2273:1: (lv_fullyQualifiedMethodName_1_0= ruleFullMethodName )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2274:3: lv_fullyQualifiedMethodName_1_0= ruleFullMethodName
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMethodInvocationAccess().getFullyQualifiedMethodNameFullMethodNameParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleFullMethodName_in_ruleMethodInvocation5391);
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
                      		"FullMethodName");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2290:2: ( (lv_args_2_0= ruleArguments ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2291:1: (lv_args_2_0= ruleArguments )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2291:1: (lv_args_2_0= ruleArguments )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2292:3: lv_args_2_0= ruleArguments
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMethodInvocationAccess().getArgsArgumentsParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleArguments_in_ruleMethodInvocation5412);
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
                      		"Arguments");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2316:1: entryRuleFullMethodName returns [EObject current=null] : iv_ruleFullMethodName= ruleFullMethodName EOF ;
    public final EObject entryRuleFullMethodName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullMethodName = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2317:2: (iv_ruleFullMethodName= ruleFullMethodName EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2318:2: iv_ruleFullMethodName= ruleFullMethodName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFullMethodNameRule()); 
            }
            pushFollow(FOLLOW_ruleFullMethodName_in_entryRuleFullMethodName5448);
            iv_ruleFullMethodName=ruleFullMethodName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFullMethodName; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullMethodName5458); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2325:1: ruleFullMethodName returns [EObject current=null] : ( ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) ) ) ;
    public final EObject ruleFullMethodName() throws RecognitionException {
        EObject current = null;

        Token lv_dots_1_0=null;
        AntlrDatatypeRuleToken lv_prefixQMN_0_0 = null;

        AntlrDatatypeRuleToken lv_methodName_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2328:28: ( ( ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2329:1: ( ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2329:1: ( ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2329:2: ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )* ( (lv_methodName_2_0= ruleValidID ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2329:2: ( ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) ) )*
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
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2329:3: ( (lv_prefixQMN_0_0= ruleValidID ) ) ( (lv_dots_1_0= '.' ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2329:3: ( (lv_prefixQMN_0_0= ruleValidID ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2330:1: (lv_prefixQMN_0_0= ruleValidID )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2330:1: (lv_prefixQMN_0_0= ruleValidID )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2331:3: lv_prefixQMN_0_0= ruleValidID
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getFullMethodNameAccess().getPrefixQMNValidIDParserRuleCall_0_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleValidID_in_ruleFullMethodName5505);
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
            	              		"ValidID");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2347:2: ( (lv_dots_1_0= '.' ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2348:1: (lv_dots_1_0= '.' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2348:1: (lv_dots_1_0= '.' )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2349:3: lv_dots_1_0= '.'
            	    {
            	    lv_dots_1_0=(Token)match(input,40,FOLLOW_40_in_ruleFullMethodName5523); if (state.failed) return current;
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

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2362:4: ( (lv_methodName_2_0= ruleValidID ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2363:1: (lv_methodName_2_0= ruleValidID )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2363:1: (lv_methodName_2_0= ruleValidID )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2364:3: lv_methodName_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFullMethodNameAccess().getMethodNameValidIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleValidID_in_ruleFullMethodName5559);
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
                      		"ValidID");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2388:1: entryRuleArguments returns [EObject current=null] : iv_ruleArguments= ruleArguments EOF ;
    public final EObject entryRuleArguments() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArguments = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2389:2: (iv_ruleArguments= ruleArguments EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2390:2: iv_ruleArguments= ruleArguments EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArgumentsRule()); 
            }
            pushFollow(FOLLOW_ruleArguments_in_entryRuleArguments5595);
            iv_ruleArguments=ruleArguments();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArguments; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArguments5605); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2397:1: ruleArguments returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')' ) ;
    public final EObject ruleArguments() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_exprLst_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2400:28: ( ( () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2401:1: ( () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')' )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2401:1: ( () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')' )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2401:2: () otherlv_1= '(' ( (lv_exprLst_2_0= ruleExpressionList ) )? otherlv_3= ')'
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2401:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2402:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArgumentsAccess().getArgumentsAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,50,FOLLOW_50_in_ruleArguments5651); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArgumentsAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2411:1: ( (lv_exprLst_2_0= ruleExpressionList ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=RULE_INT && LA32_0<=RULE_ID)||(LA32_0>=33 && LA32_0<=34)||(LA32_0>=38 && LA32_0<=39)||(LA32_0>=41 && LA32_0<=44)||(LA32_0>=47 && LA32_0<=50)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2412:1: (lv_exprLst_2_0= ruleExpressionList )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2412:1: (lv_exprLst_2_0= ruleExpressionList )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2413:3: lv_exprLst_2_0= ruleExpressionList
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArgumentsAccess().getExprLstExpressionListParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpressionList_in_ruleArguments5672);
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
                              		"ExpressionList");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,51,FOLLOW_51_in_ruleArguments5685); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2441:1: entryRuleExpressionList returns [EObject current=null] : iv_ruleExpressionList= ruleExpressionList EOF ;
    public final EObject entryRuleExpressionList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionList = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2442:2: (iv_ruleExpressionList= ruleExpressionList EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2443:2: iv_ruleExpressionList= ruleExpressionList EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionListRule()); 
            }
            pushFollow(FOLLOW_ruleExpressionList_in_entryRuleExpressionList5721);
            iv_ruleExpressionList=ruleExpressionList();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpressionList; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionList5731); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2450:1: ruleExpressionList returns [EObject current=null] : ( ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )* ) ;
    public final EObject ruleExpressionList() throws RecognitionException {
        EObject current = null;

        Token lv_commas_1_0=null;
        EObject lv_expressions_0_0 = null;

        EObject lv_expressions_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2453:28: ( ( ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2454:1: ( ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2454:1: ( ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2454:2: ( (lv_expressions_0_0= ruleJasperReportsExpression ) ) ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )*
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2454:2: ( (lv_expressions_0_0= ruleJasperReportsExpression ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2455:1: (lv_expressions_0_0= ruleJasperReportsExpression )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2455:1: (lv_expressions_0_0= ruleJasperReportsExpression )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2456:3: lv_expressions_0_0= ruleJasperReportsExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpressionListAccess().getExpressionsJasperReportsExpressionParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJasperReportsExpression_in_ruleExpressionList5777);
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
                      		"JasperReportsExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2472:2: ( ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==54) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2472:3: ( (lv_commas_1_0= ',' ) ) ( (lv_expressions_2_0= ruleJasperReportsExpression ) )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2472:3: ( (lv_commas_1_0= ',' ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2473:1: (lv_commas_1_0= ',' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2473:1: (lv_commas_1_0= ',' )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2474:3: lv_commas_1_0= ','
            	    {
            	    lv_commas_1_0=(Token)match(input,54,FOLLOW_54_in_ruleExpressionList5796); if (state.failed) return current;
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

            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2487:2: ( (lv_expressions_2_0= ruleJasperReportsExpression ) )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2488:1: (lv_expressions_2_0= ruleJasperReportsExpression )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2488:1: (lv_expressions_2_0= ruleJasperReportsExpression )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2489:3: lv_expressions_2_0= ruleJasperReportsExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExpressionListAccess().getExpressionsJasperReportsExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleJasperReportsExpression_in_ruleExpressionList5830);
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
            	              		"JasperReportsExpression");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2513:1: entryRuleJvmTypeReference returns [EObject current=null] : iv_ruleJvmTypeReference= ruleJvmTypeReference EOF ;
    public final EObject entryRuleJvmTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmTypeReference = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2514:2: (iv_ruleJvmTypeReference= ruleJvmTypeReference EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2515:2: iv_ruleJvmTypeReference= ruleJvmTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_entryRuleJvmTypeReference5868);
            iv_ruleJvmTypeReference=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmTypeReference; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmTypeReference5878); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2522:1: ruleJvmTypeReference returns [EObject current=null] : (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* ) ;
    public final EObject ruleJvmTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject this_JvmParameterizedTypeReference_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2525:28: ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2526:1: (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2526:1: (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2527:5: this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getJvmTypeReferenceAccess().getJvmParameterizedTypeReferenceParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleJvmParameterizedTypeReference_in_ruleJvmTypeReference5925);
            this_JvmParameterizedTypeReference_0=ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_JvmParameterizedTypeReference_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2535:1: ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==45) ) {
                    int LA34_2 = input.LA(2);

                    if ( (LA34_2==46) ) {
                        int LA34_3 = input.LA(3);

                        if ( (synpred11_InternalJavaJRExpression()) ) {
                            alt34=1;
                        }


                    }


                }


                switch (alt34) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2535:2: ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' )
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2538:5: ( () otherlv_2= '[' otherlv_3= ']' )
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2538:6: () otherlv_2= '[' otherlv_3= ']'
            	    {
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2538:6: ()
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2539:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getJvmTypeReferenceAccess().getJvmGenericArrayTypeReferenceComponentTypeAction_1_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,45,FOLLOW_45_in_ruleJvmTypeReference5963); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getJvmTypeReferenceAccess().getLeftSquareBracketKeyword_1_0_1());
            	          
            	    }
            	    otherlv_3=(Token)match(input,46,FOLLOW_46_in_ruleJvmTypeReference5975); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2560:1: entryRuleJvmParameterizedTypeReference returns [EObject current=null] : iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF ;
    public final EObject entryRuleJvmParameterizedTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmParameterizedTypeReference = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2561:2: (iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2562:2: iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_ruleJvmParameterizedTypeReference_in_entryRuleJvmParameterizedTypeReference6014);
            iv_ruleJvmParameterizedTypeReference=ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmParameterizedTypeReference; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmParameterizedTypeReference6024); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2569:1: ruleJvmParameterizedTypeReference returns [EObject current=null] : ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ) ;
    public final EObject ruleJvmParameterizedTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_arguments_2_0 = null;

        EObject lv_arguments_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2572:28: ( ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2573:1: ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2573:1: ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2573:2: ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2573:2: ( ( ruleQualifiedName ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2574:1: ( ruleQualifiedName )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2574:1: ( ruleQualifiedName )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2575:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getJvmParameterizedTypeReferenceRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleJvmParameterizedTypeReference6072);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2588:2: ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?
            int alt36=2;
            alt36 = dfa36.predict(input);
            switch (alt36) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2588:3: ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>'
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2588:3: ( ( '<' )=>otherlv_1= '<' )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2588:4: ( '<' )=>otherlv_1= '<'
                    {
                    otherlv_1=(Token)match(input,55,FOLLOW_55_in_ruleJvmParameterizedTypeReference6093); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_0());
                          
                    }

                    }

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2593:2: ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2594:1: (lv_arguments_2_0= ruleJvmArgumentTypeReference )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2594:1: (lv_arguments_2_0= ruleJvmArgumentTypeReference )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2595:3: lv_arguments_2_0= ruleJvmArgumentTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleJvmParameterizedTypeReference6115);
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
                              		"JvmArgumentTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2611:2: (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==54) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2611:4: otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    {
                    	    otherlv_3=(Token)match(input,54,FOLLOW_54_in_ruleJvmParameterizedTypeReference6128); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_2_0());
                    	          
                    	    }
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2615:1: ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2616:1: (lv_arguments_4_0= ruleJvmArgumentTypeReference )
                    	    {
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2616:1: (lv_arguments_4_0= ruleJvmArgumentTypeReference )
                    	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2617:3: lv_arguments_4_0= ruleJvmArgumentTypeReference
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleJvmParameterizedTypeReference6149);
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
                    	              		"JvmArgumentTypeReference");
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

                    otherlv_5=(Token)match(input,56,FOLLOW_56_in_ruleJvmParameterizedTypeReference6163); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2645:1: entryRuleJvmArgumentTypeReference returns [EObject current=null] : iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF ;
    public final EObject entryRuleJvmArgumentTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmArgumentTypeReference = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2646:2: (iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2647:2: iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_entryRuleJvmArgumentTypeReference6201);
            iv_ruleJvmArgumentTypeReference=ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmArgumentTypeReference; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmArgumentTypeReference6211); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2654:1: ruleJvmArgumentTypeReference returns [EObject current=null] : (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference ) ;
    public final EObject ruleJvmArgumentTypeReference() throws RecognitionException {
        EObject current = null;

        EObject this_JvmTypeReference_0 = null;

        EObject this_JvmWildcardTypeReference_1 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2657:28: ( (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2658:1: (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2658:1: (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference )
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2659:5: this_JvmTypeReference_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmTypeReferenceParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleJvmArgumentTypeReference6258);
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2669:5: this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmWildcardTypeReferenceParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleJvmWildcardTypeReference_in_ruleJvmArgumentTypeReference6285);
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2685:1: entryRuleJvmWildcardTypeReference returns [EObject current=null] : iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF ;
    public final EObject entryRuleJvmWildcardTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmWildcardTypeReference = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2686:2: (iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2687:2: iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_ruleJvmWildcardTypeReference_in_entryRuleJvmWildcardTypeReference6320);
            iv_ruleJvmWildcardTypeReference=ruleJvmWildcardTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmWildcardTypeReference; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmWildcardTypeReference6330); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2694:1: ruleJvmWildcardTypeReference returns [EObject current=null] : ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? ) ;
    public final EObject ruleJvmWildcardTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_constraints_2_0 = null;

        EObject lv_constraints_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2697:28: ( ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2698:1: ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2698:1: ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2698:2: () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )?
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2698:2: ()
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2699:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJvmWildcardTypeReferenceAccess().getJvmWildcardTypeReferenceAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleJvmWildcardTypeReference6376); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJvmWildcardTypeReferenceAccess().getQuestionMarkKeyword_1());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2708:1: ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )?
            int alt38=3;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==57) ) {
                alt38=1;
            }
            else if ( (LA38_0==58) ) {
                alt38=2;
            }
            switch (alt38) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2708:2: ( (lv_constraints_2_0= ruleJvmUpperBound ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2708:2: ( (lv_constraints_2_0= ruleJvmUpperBound ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2709:1: (lv_constraints_2_0= ruleJvmUpperBound )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2709:1: (lv_constraints_2_0= ruleJvmUpperBound )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2710:3: lv_constraints_2_0= ruleJvmUpperBound
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmUpperBound_in_ruleJvmWildcardTypeReference6398);
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
                              		"JvmUpperBound");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2727:6: ( (lv_constraints_3_0= ruleJvmLowerBound ) )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2727:6: ( (lv_constraints_3_0= ruleJvmLowerBound ) )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2728:1: (lv_constraints_3_0= ruleJvmLowerBound )
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2728:1: (lv_constraints_3_0= ruleJvmLowerBound )
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2729:3: lv_constraints_3_0= ruleJvmLowerBound
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmLowerBound_in_ruleJvmWildcardTypeReference6425);
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
                              		"JvmLowerBound");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2753:1: entryRuleJvmUpperBound returns [EObject current=null] : iv_ruleJvmUpperBound= ruleJvmUpperBound EOF ;
    public final EObject entryRuleJvmUpperBound() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmUpperBound = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2754:2: (iv_ruleJvmUpperBound= ruleJvmUpperBound EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2755:2: iv_ruleJvmUpperBound= ruleJvmUpperBound EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmUpperBoundRule()); 
            }
            pushFollow(FOLLOW_ruleJvmUpperBound_in_entryRuleJvmUpperBound6463);
            iv_ruleJvmUpperBound=ruleJvmUpperBound();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmUpperBound; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmUpperBound6473); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2762:1: ruleJvmUpperBound returns [EObject current=null] : (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmUpperBound() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2765:28: ( (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2766:1: (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2766:1: (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2766:3: otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,57,FOLLOW_57_in_ruleJvmUpperBound6510); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getJvmUpperBoundAccess().getExtendsKeyword_0());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2770:1: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2771:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2771:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2772:3: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmUpperBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleJvmUpperBound6531);
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
                      		"JvmTypeReference");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2796:1: entryRuleJvmLowerBound returns [EObject current=null] : iv_ruleJvmLowerBound= ruleJvmLowerBound EOF ;
    public final EObject entryRuleJvmLowerBound() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmLowerBound = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2797:2: (iv_ruleJvmLowerBound= ruleJvmLowerBound EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2798:2: iv_ruleJvmLowerBound= ruleJvmLowerBound EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmLowerBoundRule()); 
            }
            pushFollow(FOLLOW_ruleJvmLowerBound_in_entryRuleJvmLowerBound6567);
            iv_ruleJvmLowerBound=ruleJvmLowerBound();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmLowerBound; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmLowerBound6577); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2805:1: ruleJvmLowerBound returns [EObject current=null] : (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmLowerBound() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2808:28: ( (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2809:1: (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2809:1: (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2809:3: otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,58,FOLLOW_58_in_ruleJvmLowerBound6614); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getJvmLowerBoundAccess().getSuperKeyword_0());
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2813:1: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2814:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2814:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2815:3: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmLowerBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleJvmLowerBound6635);
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
                      		"JvmTypeReference");
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2839:1: entryRulePrimitiveType returns [String current=null] : iv_rulePrimitiveType= rulePrimitiveType EOF ;
    public final String entryRulePrimitiveType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePrimitiveType = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2840:2: (iv_rulePrimitiveType= rulePrimitiveType EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2841:2: iv_rulePrimitiveType= rulePrimitiveType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimitiveTypeRule()); 
            }
            pushFollow(FOLLOW_rulePrimitiveType_in_entryRulePrimitiveType6672);
            iv_rulePrimitiveType=rulePrimitiveType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimitiveType.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimitiveType6683); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2848:1: rulePrimitiveType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'boolean' | kw= 'char' | kw= 'byte' | kw= 'short' | kw= 'int' | kw= 'long' | kw= 'float' | kw= 'double' ) ;
    public final AntlrDatatypeRuleToken rulePrimitiveType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2851:28: ( (kw= 'boolean' | kw= 'char' | kw= 'byte' | kw= 'short' | kw= 'int' | kw= 'long' | kw= 'float' | kw= 'double' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2852:1: (kw= 'boolean' | kw= 'char' | kw= 'byte' | kw= 'short' | kw= 'int' | kw= 'long' | kw= 'float' | kw= 'double' )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2852:1: (kw= 'boolean' | kw= 'char' | kw= 'byte' | kw= 'short' | kw= 'int' | kw= 'long' | kw= 'float' | kw= 'double' )
            int alt39=8;
            switch ( input.LA(1) ) {
            case 59:
                {
                alt39=1;
                }
                break;
            case 60:
                {
                alt39=2;
                }
                break;
            case 61:
                {
                alt39=3;
                }
                break;
            case 62:
                {
                alt39=4;
                }
                break;
            case 63:
                {
                alt39=5;
                }
                break;
            case 64:
                {
                alt39=6;
                }
                break;
            case 65:
                {
                alt39=7;
                }
                break;
            case 66:
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2853:2: kw= 'boolean'
                    {
                    kw=(Token)match(input,59,FOLLOW_59_in_rulePrimitiveType6721); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getBooleanKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2860:2: kw= 'char'
                    {
                    kw=(Token)match(input,60,FOLLOW_60_in_rulePrimitiveType6740); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getCharKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2867:2: kw= 'byte'
                    {
                    kw=(Token)match(input,61,FOLLOW_61_in_rulePrimitiveType6759); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getByteKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2874:2: kw= 'short'
                    {
                    kw=(Token)match(input,62,FOLLOW_62_in_rulePrimitiveType6778); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getShortKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2881:2: kw= 'int'
                    {
                    kw=(Token)match(input,63,FOLLOW_63_in_rulePrimitiveType6797); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getIntKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2888:2: kw= 'long'
                    {
                    kw=(Token)match(input,64,FOLLOW_64_in_rulePrimitiveType6816); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getLongKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2895:2: kw= 'float'
                    {
                    kw=(Token)match(input,65,FOLLOW_65_in_rulePrimitiveType6835); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeAccess().getFloatKeyword_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2902:2: kw= 'double'
                    {
                    kw=(Token)match(input,66,FOLLOW_66_in_rulePrimitiveType6854); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2915:1: entryRuleRelationalOp returns [String current=null] : iv_ruleRelationalOp= ruleRelationalOp EOF ;
    public final String entryRuleRelationalOp() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelationalOp = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2916:2: (iv_ruleRelationalOp= ruleRelationalOp EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2917:2: iv_ruleRelationalOp= ruleRelationalOp EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalOpRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp6895);
            iv_ruleRelationalOp=ruleRelationalOp();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalOp.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalOp6906); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2924:1: ruleRelationalOp returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<=' | kw= '<' | kw= '>=' | kw= '>' ) ;
    public final AntlrDatatypeRuleToken ruleRelationalOp() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2927:28: ( (kw= '<=' | kw= '<' | kw= '>=' | kw= '>' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2928:1: (kw= '<=' | kw= '<' | kw= '>=' | kw= '>' )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2928:1: (kw= '<=' | kw= '<' | kw= '>=' | kw= '>' )
            int alt40=4;
            switch ( input.LA(1) ) {
            case 67:
                {
                alt40=1;
                }
                break;
            case 55:
                {
                alt40=2;
                }
                break;
            case 68:
                {
                alt40=3;
                }
                break;
            case 56:
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
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2929:2: kw= '<='
                    {
                    kw=(Token)match(input,67,FOLLOW_67_in_ruleRelationalOp6944); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignEqualsSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2936:2: kw= '<'
                    {
                    kw=(Token)match(input,55,FOLLOW_55_in_ruleRelationalOp6963); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2943:2: kw= '>='
                    {
                    kw=(Token)match(input,68,FOLLOW_68_in_ruleRelationalOp6982); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignEqualsSignKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2950:2: kw= '>'
                    {
                    kw=(Token)match(input,56,FOLLOW_56_in_ruleRelationalOp7001); if (state.failed) return current;
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2963:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2964:2: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2965:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName7042);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedName.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedName7053); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2972:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;

        AntlrDatatypeRuleToken this_ValidID_2 = null;


         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2975:28: ( (this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )* ) )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2976:1: (this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )* )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2976:1: (this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )* )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2977:5: this_ValidID_0= ruleValidID (kw= '.' this_ValidID_2= ruleValidID )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleValidID_in_ruleQualifiedName7100);
            this_ValidID_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ValidID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2987:1: (kw= '.' this_ValidID_2= ruleValidID )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==40) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2988:2: kw= '.' this_ValidID_2= ruleValidID
            	    {
            	    kw=(Token)match(input,40,FOLLOW_40_in_ruleQualifiedName7119); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_ruleValidID_in_ruleQualifiedName7141);
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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:3012:1: entryRuleValidID returns [String current=null] : iv_ruleValidID= ruleValidID EOF ;
    public final String entryRuleValidID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleValidID = null;


        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:3013:2: (iv_ruleValidID= ruleValidID EOF )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:3014:2: iv_ruleValidID= ruleValidID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getValidIDRule()); 
            }
            pushFollow(FOLLOW_ruleValidID_in_entryRuleValidID7189);
            iv_ruleValidID=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleValidID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleValidID7200); if (state.failed) return current;

            }

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
    // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:3021:1: ruleValidID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleValidID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:3024:28: (this_ID_0= RULE_ID )
            // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:3025:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleValidID7239); if (state.failed) return current;
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
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:3: ( ( () '?' ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:4: ( () '?' )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:4: ( () '?' )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:5: () '?'
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:158:5: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:159:1: 
        {
        }

        match(input,26,FOLLOW_26_in_synpred1_InternalJavaJRExpression329); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred1_InternalJavaJRExpression

    // $ANTLR start synpred2_InternalJavaJRExpression
    public final void synpred2_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:3: ( ( () ( ( '||' ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:4: ( () ( ( '||' ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:4: ( () ( ( '||' ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:5: () ( ( '||' ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:240:5: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:241:1: 
        {
        }

        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:241:2: ( ( '||' ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:242:1: ( '||' )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:242:1: ( '||' )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:243:2: '||'
        {
        match(input,28,FOLLOW_28_in_synpred2_InternalJavaJRExpression519); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred2_InternalJavaJRExpression

    // $ANTLR start synpred3_InternalJavaJRExpression
    public final void synpred3_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:3: ( ( () ( ( '&&' ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:4: ( () ( ( '&&' ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:4: ( () ( ( '&&' ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:5: () ( ( '&&' ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:316:5: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:317:1: 
        {
        }

        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:317:2: ( ( '&&' ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:318:1: ( '&&' )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:318:1: ( '&&' )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:319:2: '&&'
        {
        match(input,29,FOLLOW_29_in_synpred3_InternalJavaJRExpression701); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred3_InternalJavaJRExpression

    // $ANTLR start synpred4_InternalJavaJRExpression
    public final void synpred4_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:3: ( ( () ( ( ( '==' | '!=' ) ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:4: ( () ( ( ( '==' | '!=' ) ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:4: ( () ( ( ( '==' | '!=' ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:5: () ( ( ( '==' | '!=' ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:392:5: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:393:1: 
        {
        }

        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:393:2: ( ( ( '==' | '!=' ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:394:1: ( ( '==' | '!=' ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:394:1: ( ( '==' | '!=' ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:395:1: ( '==' | '!=' )
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
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:3: ( ( () ( ( 'instanceof' ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:4: ( () ( ( 'instanceof' ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:4: ( () ( ( 'instanceof' ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:5: () ( ( 'instanceof' ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:489:5: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:490:1: 
        {
        }

        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:490:2: ( ( 'instanceof' ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:491:1: ( 'instanceof' )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:491:1: ( 'instanceof' )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:492:2: 'instanceof'
        {
        match(input,32,FOLLOW_32_in_synpred5_InternalJavaJRExpression1118); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred5_InternalJavaJRExpression

    // $ANTLR start synpred6_InternalJavaJRExpression
    public final void synpred6_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:3: ( ( () ( ( ruleRelationalOp ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:4: ( () ( ( ruleRelationalOp ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:4: ( () ( ( ruleRelationalOp ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:5: () ( ( ruleRelationalOp ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:565:5: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:566:1: 
        {
        }

        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:566:2: ( ( ruleRelationalOp ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:567:1: ( ruleRelationalOp )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:567:1: ( ruleRelationalOp )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:568:1: ruleRelationalOp
        {
        pushFollow(FOLLOW_ruleRelationalOp_in_synpred6_InternalJavaJRExpression1299);
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
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:4: ( () ( ( ( '+' | '-' ) ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:4: ( () ( ( ( '+' | '-' ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:5: () ( ( ( '+' | '-' ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:642:5: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:643:1: 
        {
        }

        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:643:2: ( ( ( '+' | '-' ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:644:1: ( ( '+' | '-' ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:644:1: ( ( '+' | '-' ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:645:1: ( '+' | '-' )
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
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:3: ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:4: ( () ( ( ( '*' | '/' | '%' ) ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:4: ( () ( ( ( '*' | '/' | '%' ) ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:5: () ( ( ( '*' | '/' | '%' ) ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:739:5: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:740:1: 
        {
        }

        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:740:2: ( ( ( '*' | '/' | '%' ) ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:741:1: ( ( '*' | '/' | '%' ) )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:741:1: ( ( '*' | '/' | '%' ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:742:1: ( '*' | '/' | '%' )
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
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:928:7: ( ruleCastedExpression )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:928:9: ruleCastedExpression
        {
        pushFollow(FOLLOW_ruleCastedExpression_in_synpred9_InternalJavaJRExpression2206);
        ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_InternalJavaJRExpression

    // $ANTLR start synpred10_InternalJavaJRExpression
    public final void synpred10_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:998:7: ( ruleBaseJRExpression )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:998:9: ruleBaseJRExpression
        {
        pushFollow(FOLLOW_ruleBaseJRExpression_in_synpred10_InternalJavaJRExpression2413);
        ruleBaseJRExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_InternalJavaJRExpression

    // $ANTLR start synpred11_InternalJavaJRExpression
    public final void synpred11_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2535:2: ( ( () '[' ']' ) )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2535:3: ( () '[' ']' )
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2535:3: ( () '[' ']' )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2535:4: () '[' ']'
        {
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2535:4: ()
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2536:1: 
        {
        }

        match(input,45,FOLLOW_45_in_synpred11_InternalJavaJRExpression5940); if (state.failed) return ;
        match(input,46,FOLLOW_46_in_synpred11_InternalJavaJRExpression5944); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred11_InternalJavaJRExpression

    // $ANTLR start synpred12_InternalJavaJRExpression
    public final void synpred12_InternalJavaJRExpression_fragment() throws RecognitionException {   
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2588:4: ( '<' )
        // ../com.jaspersoft.studio.editor.jrexpressions/src-gen/com/jaspersoft/studio/editor/jrexpressions/parser/antlr/internal/InternalJavaJRExpression.g:2588:6: '<'
        {
        match(input,55,FOLLOW_55_in_synpred12_InternalJavaJRExpression6085); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_InternalJavaJRExpression

    // Delegated rules

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


    protected DFA14 dfa14 = new DFA14(this);
    protected DFA15 dfa15 = new DFA15(this);
    protected DFA36 dfa36 = new DFA36(this);
    static final String DFA14_eotS =
        "\23\uffff";
    static final String DFA14_eofS =
        "\23\uffff";
    static final String DFA14_minS =
        "\1\5\2\uffff\1\0\17\uffff";
    static final String DFA14_maxS =
        "\1\62\2\uffff\1\0\17\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\15\uffff\1\3";
    static final String DFA14_specialS =
        "\3\uffff\1\0\17\uffff}>";
    static final String[] DFA14_transitionS = {
            "\7\4\32\uffff\1\1\1\2\1\uffff\4\4\2\uffff\3\4\1\3",
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
            ""
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
                        if ( (synpred9_InternalJavaJRExpression()) ) {s = 18;}

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
    static final String DFA15_eotS =
        "\47\uffff";
    static final String DFA15_eofS =
        "\3\uffff\1\2\4\uffff\1\20\3\uffff\3\41\30\uffff";
    static final String DFA15_minS =
        "\1\5\2\uffff\1\32\1\13\3\4\1\32\2\uffff\1\50\3\32\1\13\1\uffff\1"+
        "\13\24\uffff\1\50";
    static final String DFA15_maxS =
        "\1\62\2\uffff\1\104\1\102\3\4\1\104\2\uffff\1\67\3\104\1\13\1\uffff"+
        "\1\13\24\uffff\1\67";
    static final String DFA15_acceptS =
        "\1\uffff\1\1\1\2\6\uffff\1\5\1\3\5\uffff\1\6\1\uffff\24\4\1\uffff";
    static final String DFA15_specialS =
        "\14\uffff\1\0\1\1\1\2\30\uffff}>";
    static final String[] DFA15_transitionS = {
            "\5\2\1\3\1\10\35\uffff\1\5\1\6\1\7\1\4\2\uffff\3\2\1\1",
            "",
            "",
            "\14\2\2\uffff\1\11\12\uffff\1\2\1\uffff\4\2\12\uffff\2\2",
            "\1\13\57\uffff\10\12",
            "\1\14",
            "\1\15",
            "\1\16",
            "\14\20\2\uffff\1\17\11\uffff\1\11\1\20\1\uffff\4\20\12\uffff"+
            "\2\20",
            "",
            "",
            "\1\21\4\uffff\1\12\4\uffff\1\11\4\uffff\1\12",
            "\1\40\1\42\1\37\1\36\1\34\1\35\1\33\1\25\1\26\1\22\1\23\1\24"+
            "\2\uffff\1\11\12\uffff\1\43\1\uffff\1\45\1\44\1\30\1\32\12\uffff"+
            "\1\27\1\31",
            "\1\40\1\42\1\37\1\36\1\34\1\35\1\33\1\25\1\26\1\22\1\23\1\24"+
            "\2\uffff\1\11\12\uffff\1\43\1\uffff\1\45\1\44\1\30\1\32\12\uffff"+
            "\1\27\1\31",
            "\1\40\1\42\1\37\1\36\1\34\1\35\1\33\1\25\1\26\1\22\1\23\1\24"+
            "\2\uffff\1\11\12\uffff\1\43\1\uffff\1\45\1\44\1\30\1\32\12\uffff"+
            "\1\27\1\31",
            "\1\10",
            "",
            "\1\46",
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
            "\1\21\4\uffff\1\12\4\uffff\1\11\4\uffff\1\12"
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "968:1: (this_ParExpression_0= ruleParExpression | this_LiteralExpression_1= ruleLiteralExpression | this_ArrayCreator_2= ruleArrayCreator | ( ( ruleBaseJRExpression )=>this_BaseJRExpression_3= ruleBaseJRExpression ) | this_MethodsExpression_4= ruleMethodsExpression | this_StaticField_5= ruleStaticField )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_12 = input.LA(1);

                         
                        int index15_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA15_12==40) ) {s = 9;}

                        else if ( (LA15_12==35) && (synpred10_InternalJavaJRExpression())) {s = 18;}

                        else if ( (LA15_12==36) && (synpred10_InternalJavaJRExpression())) {s = 19;}

                        else if ( (LA15_12==37) && (synpred10_InternalJavaJRExpression())) {s = 20;}

                        else if ( (LA15_12==33) && (synpred10_InternalJavaJRExpression())) {s = 21;}

                        else if ( (LA15_12==34) && (synpred10_InternalJavaJRExpression())) {s = 22;}

                        else if ( (LA15_12==67) && (synpred10_InternalJavaJRExpression())) {s = 23;}

                        else if ( (LA15_12==55) && (synpred10_InternalJavaJRExpression())) {s = 24;}

                        else if ( (LA15_12==68) && (synpred10_InternalJavaJRExpression())) {s = 25;}

                        else if ( (LA15_12==56) && (synpred10_InternalJavaJRExpression())) {s = 26;}

                        else if ( (LA15_12==32) && (synpred10_InternalJavaJRExpression())) {s = 27;}

                        else if ( (LA15_12==30) && (synpred10_InternalJavaJRExpression())) {s = 28;}

                        else if ( (LA15_12==31) && (synpred10_InternalJavaJRExpression())) {s = 29;}

                        else if ( (LA15_12==29) && (synpred10_InternalJavaJRExpression())) {s = 30;}

                        else if ( (LA15_12==28) && (synpred10_InternalJavaJRExpression())) {s = 31;}

                        else if ( (LA15_12==26) && (synpred10_InternalJavaJRExpression())) {s = 32;}

                        else if ( (LA15_12==EOF) && (synpred10_InternalJavaJRExpression())) {s = 33;}

                        else if ( (LA15_12==27) && (synpred10_InternalJavaJRExpression())) {s = 34;}

                        else if ( (LA15_12==51) && (synpred10_InternalJavaJRExpression())) {s = 35;}

                        else if ( (LA15_12==54) && (synpred10_InternalJavaJRExpression())) {s = 36;}

                        else if ( (LA15_12==53) && (synpred10_InternalJavaJRExpression())) {s = 37;}

                         
                        input.seek(index15_12);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA15_13 = input.LA(1);

                         
                        int index15_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA15_13==35) && (synpred10_InternalJavaJRExpression())) {s = 18;}

                        else if ( (LA15_13==36) && (synpred10_InternalJavaJRExpression())) {s = 19;}

                        else if ( (LA15_13==37) && (synpred10_InternalJavaJRExpression())) {s = 20;}

                        else if ( (LA15_13==33) && (synpred10_InternalJavaJRExpression())) {s = 21;}

                        else if ( (LA15_13==34) && (synpred10_InternalJavaJRExpression())) {s = 22;}

                        else if ( (LA15_13==67) && (synpred10_InternalJavaJRExpression())) {s = 23;}

                        else if ( (LA15_13==55) && (synpred10_InternalJavaJRExpression())) {s = 24;}

                        else if ( (LA15_13==68) && (synpred10_InternalJavaJRExpression())) {s = 25;}

                        else if ( (LA15_13==56) && (synpred10_InternalJavaJRExpression())) {s = 26;}

                        else if ( (LA15_13==32) && (synpred10_InternalJavaJRExpression())) {s = 27;}

                        else if ( (LA15_13==30) && (synpred10_InternalJavaJRExpression())) {s = 28;}

                        else if ( (LA15_13==31) && (synpred10_InternalJavaJRExpression())) {s = 29;}

                        else if ( (LA15_13==29) && (synpred10_InternalJavaJRExpression())) {s = 30;}

                        else if ( (LA15_13==28) && (synpred10_InternalJavaJRExpression())) {s = 31;}

                        else if ( (LA15_13==26) && (synpred10_InternalJavaJRExpression())) {s = 32;}

                        else if ( (LA15_13==EOF) && (synpred10_InternalJavaJRExpression())) {s = 33;}

                        else if ( (LA15_13==27) && (synpred10_InternalJavaJRExpression())) {s = 34;}

                        else if ( (LA15_13==51) && (synpred10_InternalJavaJRExpression())) {s = 35;}

                        else if ( (LA15_13==54) && (synpred10_InternalJavaJRExpression())) {s = 36;}

                        else if ( (LA15_13==53) && (synpred10_InternalJavaJRExpression())) {s = 37;}

                        else if ( (LA15_13==40) ) {s = 9;}

                         
                        input.seek(index15_13);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA15_14 = input.LA(1);

                         
                        int index15_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA15_14==40) ) {s = 9;}

                        else if ( (LA15_14==35) && (synpred10_InternalJavaJRExpression())) {s = 18;}

                        else if ( (LA15_14==36) && (synpred10_InternalJavaJRExpression())) {s = 19;}

                        else if ( (LA15_14==37) && (synpred10_InternalJavaJRExpression())) {s = 20;}

                        else if ( (LA15_14==33) && (synpred10_InternalJavaJRExpression())) {s = 21;}

                        else if ( (LA15_14==34) && (synpred10_InternalJavaJRExpression())) {s = 22;}

                        else if ( (LA15_14==67) && (synpred10_InternalJavaJRExpression())) {s = 23;}

                        else if ( (LA15_14==55) && (synpred10_InternalJavaJRExpression())) {s = 24;}

                        else if ( (LA15_14==68) && (synpred10_InternalJavaJRExpression())) {s = 25;}

                        else if ( (LA15_14==56) && (synpred10_InternalJavaJRExpression())) {s = 26;}

                        else if ( (LA15_14==32) && (synpred10_InternalJavaJRExpression())) {s = 27;}

                        else if ( (LA15_14==30) && (synpred10_InternalJavaJRExpression())) {s = 28;}

                        else if ( (LA15_14==31) && (synpred10_InternalJavaJRExpression())) {s = 29;}

                        else if ( (LA15_14==29) && (synpred10_InternalJavaJRExpression())) {s = 30;}

                        else if ( (LA15_14==28) && (synpred10_InternalJavaJRExpression())) {s = 31;}

                        else if ( (LA15_14==26) && (synpred10_InternalJavaJRExpression())) {s = 32;}

                        else if ( (LA15_14==EOF) && (synpred10_InternalJavaJRExpression())) {s = 33;}

                        else if ( (LA15_14==27) && (synpred10_InternalJavaJRExpression())) {s = 34;}

                        else if ( (LA15_14==51) && (synpred10_InternalJavaJRExpression())) {s = 35;}

                        else if ( (LA15_14==54) && (synpred10_InternalJavaJRExpression())) {s = 36;}

                        else if ( (LA15_14==53) && (synpred10_InternalJavaJRExpression())) {s = 37;}

                         
                        input.seek(index15_14);
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
    static final String DFA36_eotS =
        "\27\uffff";
    static final String DFA36_eofS =
        "\1\2\26\uffff";
    static final String DFA36_minS =
        "\1\32\1\0\25\uffff";
    static final String DFA36_maxS =
        "\1\104\1\0\25\uffff";
    static final String DFA36_acceptS =
        "\2\uffff\1\2\23\uffff\1\1";
    static final String DFA36_specialS =
        "\1\uffff\1\0\25\uffff}>";
    static final String[] DFA36_transitionS = {
            "\14\2\7\uffff\1\2\5\uffff\1\2\1\uffff\2\2\1\1\1\2\12\uffff\2"+
            "\2",
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

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "2588:2: ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?";
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
 

    public static final BitSet FOLLOW_ruleJRExpressionModel_in_entryRuleJRExpressionModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRExpressionModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJasperReportsExpression_in_ruleJRExpressionModel130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJasperReportsExpression_in_entryRuleJasperReportsExpression166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJasperReportsExpression176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalExpression_in_ruleJasperReportsExpression222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalExpression_in_entryRuleConditionalExpression256 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConditionalExpression266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalOrExpression_in_ruleConditionalExpression313 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_ruleConditionalExpression348 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleJasperReportsExpression_in_ruleConditionalExpression371 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleConditionalExpression383 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleJasperReportsExpression_in_ruleConditionalExpression404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalOrExpression_in_entryRuleConditionalOrExpression442 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConditionalOrExpression452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalAndExpression_in_ruleConditionalOrExpression499 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleConditionalOrExpression550 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleConditionalAndExpression_in_ruleConditionalOrExpression586 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_ruleConditionalAndExpression_in_entryRuleConditionalAndExpression624 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConditionalAndExpression634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEqualityExpression_in_ruleConditionalAndExpression681 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_ruleConditionalAndExpression732 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleEqualityExpression_in_ruleConditionalAndExpression768 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ruleEqualityExpression_in_entryRuleEqualityExpression806 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEqualityExpression816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceofExpression_in_ruleEqualityExpression863 = new BitSet(new long[]{0x00000000C0000002L});
    public static final BitSet FOLLOW_30_in_ruleEqualityExpression935 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_31_in_ruleEqualityExpression964 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleInstanceofExpression_in_ruleEqualityExpression1003 = new BitSet(new long[]{0x00000000C0000002L});
    public static final BitSet FOLLOW_ruleInstanceofExpression_in_entryRuleInstanceofExpression1041 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInstanceofExpression1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleInstanceofExpression1098 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32_in_ruleInstanceofExpression1149 = new BitSet(new long[]{0xF800000000000800L,0x0000000000000007L});
    public static final BitSet FOLLOW_ruleType_in_ruleInstanceofExpression1185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression1223 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression1233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression1280 = new BitSet(new long[]{0x0180000000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_ruleRelationalExpression1329 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression1352 = new BitSet(new long[]{0x0180000000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression1390 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression1447 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_33_in_ruleAdditiveExpression1519 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_34_in_ruleAdditiveExpression1548 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression1587 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression1625 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression1635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleMultiplicativeExpression1682 = new BitSet(new long[]{0x0000003800000002L});
    public static final BitSet FOLLOW_35_in_ruleMultiplicativeExpression1768 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_36_in_ruleMultiplicativeExpression1797 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_37_in_ruleMultiplicativeExpression1826 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleMultiplicativeExpression1865 = new BitSet(new long[]{0x0000003800000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression1903 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression1913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleUnaryExpression1951 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryExpression1973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleUnaryExpression1992 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryExpression2014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpressionNotPlusMinus_in_ruleUnaryExpression2042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpressionNotPlusMinus_in_entryRuleUnaryExpressionNotPlusMinus2077 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpressionNotPlusMinus2087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleUnaryExpressionNotPlusMinus2125 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryExpressionNotPlusMinus2147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleUnaryExpressionNotPlusMinus2166 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryExpressionNotPlusMinus2188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_ruleUnaryExpressionNotPlusMinus2222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleUnaryExpressionNotPlusMinus2250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression2285 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression2295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParExpression_in_rulePrimaryExpression2342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralExpression_in_rulePrimaryExpression2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayCreator_in_rulePrimaryExpression2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBaseJRExpression_in_rulePrimaryExpression2429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethodsExpression_in_rulePrimaryExpression2457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStaticField_in_rulePrimaryExpression2484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStaticField_in_entryRuleStaticField2519 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStaticField2529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleStaticField2585 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleStaticField2603 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleStaticField2639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBaseJRExpression_in_entryRuleBaseJRExpression2675 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBaseJRExpression2685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRFieldObj_in_ruleBaseJRExpression2732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameterObj_in_ruleBaseJRExpression2759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRVariableObj_in_ruleBaseJRExpression2786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRFieldObj_in_entryRuleJRFieldObj2821 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRFieldObj2831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleJRFieldObj2877 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_BRACED_IDENTIFIER_in_ruleJRFieldObj2894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameterObj_in_entryRuleJRParameterObj2935 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRParameterObj2945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleJRParameterObj2991 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_BRACED_IDENTIFIER_in_ruleJRParameterObj3008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRVariableObj_in_entryRuleJRVariableObj3049 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRVariableObj3059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleJRVariableObj3105 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_BRACED_IDENTIFIER_in_ruleJRVariableObj3122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethodsExpression_in_entryRuleMethodsExpression3163 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMethodsExpression3173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleMethodsExpression3227 = new BitSet(new long[]{0x0000100000000800L});
    public static final BitSet FOLLOW_ruleMethodInvocation_in_ruleMethodsExpression3262 = new BitSet(new long[]{0x0000210000000002L});
    public static final BitSet FOLLOW_40_in_ruleMethodsExpression3275 = new BitSet(new long[]{0x0000100000000800L});
    public static final BitSet FOLLOW_ruleMethodInvocation_in_ruleMethodsExpression3296 = new BitSet(new long[]{0x0000210000000002L});
    public static final BitSet FOLLOW_ruleBaseJRExpression_in_ruleMethodsExpression3329 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleMethodsExpression3348 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleMethodsExpression3364 = new BitSet(new long[]{0x0000100000000800L});
    public static final BitSet FOLLOW_ruleMethodInvocation_in_ruleMethodsExpression3385 = new BitSet(new long[]{0x0000210000000002L});
    public static final BitSet FOLLOW_45_in_ruleMethodsExpression3402 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIntLiteral_in_ruleMethodsExpression3423 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_ruleMethodsExpression3435 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_ruleLiteralExpression_in_entryRuleLiteralExpression3473 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteralExpression3483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntLiteral_in_ruleLiteralExpression3530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongLiteral_in_ruleLiteralExpression3557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloatLiteral_in_ruleLiteralExpression3584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleLiteral_in_ruleLiteralExpression3611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCharLiteral_in_ruleLiteralExpression3638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteralExpression3665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteralExpression3692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteralExpression3719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntLiteral_in_entryRuleIntLiteral3754 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntLiteral3764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntLiteral3815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongLiteral_in_entryRuleLongLiteral3856 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongLiteral3866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LONG_in_ruleLongLiteral3917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloatLiteral_in_entryRuleFloatLiteral3958 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloatLiteral3968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_FLOAT_in_ruleFloatLiteral4019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleLiteral_in_entryRuleDoubleLiteral4060 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleLiteral4070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DOUBLE_in_ruleDoubleLiteral4121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCharLiteral_in_entryRuleCharLiteral4162 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCharLiteral4172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_CHAR_in_ruleCharLiteral4223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral4264 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral4274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral4325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral4366 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral4376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleBooleanLiteral4423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleBooleanLiteral4447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral4497 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral4507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleNullLiteral4553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParExpression_in_entryRuleParExpression4589 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParExpression4599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleParExpression4636 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleJasperReportsExpression_in_ruleParExpression4658 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleParExpression4669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression4705 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCastedExpression4715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleCastedExpression4761 = new BitSet(new long[]{0xF800000000000800L,0x0000000000000007L});
    public static final BitSet FOLLOW_ruleType_in_ruleCastedExpression4782 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleCastedExpression4794 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleJasperReportsExpression_in_ruleCastedExpression4815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType4851 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType4861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimitiveType_in_ruleType4917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleType4944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayCreator_in_entryRuleArrayCreator4981 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayCreator4991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleArrayCreator5037 = new BitSet(new long[]{0xF800000000000800L,0x0000000000000007L});
    public static final BitSet FOLLOW_ruleType_in_ruleArrayCreator5058 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleArrayCreator5072 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIntLiteral_in_ruleArrayCreator5093 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_ruleArrayCreator5105 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleArrayCreator5127 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_ruleArrayCreator5139 = new BitSet(new long[]{0x0010200000000000L});
    public static final BitSet FOLLOW_ruleArrayInitializer_in_ruleArrayCreator5162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayInitializer_in_entryRuleArrayInitializer5200 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayInitializer5210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleArrayInitializer5256 = new BitSet(new long[]{0x00279EC600000FE0L});
    public static final BitSet FOLLOW_ruleExpressionList_in_ruleArrayInitializer5277 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_ruleArrayInitializer5290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethodInvocation_in_entryRuleMethodInvocation5326 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMethodInvocation5336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullMethodName_in_ruleMethodInvocation5391 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_ruleArguments_in_ruleMethodInvocation5412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullMethodName_in_entryRuleFullMethodName5448 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullMethodName5458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleFullMethodName5505 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleFullMethodName5523 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleFullMethodName5559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArguments_in_entryRuleArguments5595 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArguments5605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleArguments5651 = new BitSet(new long[]{0x000F9EC600000FE0L});
    public static final BitSet FOLLOW_ruleExpressionList_in_ruleArguments5672 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleArguments5685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionList_in_entryRuleExpressionList5721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionList5731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJasperReportsExpression_in_ruleExpressionList5777 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_54_in_ruleExpressionList5796 = new BitSet(new long[]{0x00079EC600000FE0L});
    public static final BitSet FOLLOW_ruleJasperReportsExpression_in_ruleExpressionList5830 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_entryRuleJvmTypeReference5868 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmTypeReference5878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmParameterizedTypeReference_in_ruleJvmTypeReference5925 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleJvmTypeReference5963 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_ruleJvmTypeReference5975 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_ruleJvmParameterizedTypeReference_in_entryRuleJvmParameterizedTypeReference6014 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmParameterizedTypeReference6024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleJvmParameterizedTypeReference6072 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_55_in_ruleJvmParameterizedTypeReference6093 = new BitSet(new long[]{0xF800000004000800L,0x0000000000000007L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleJvmParameterizedTypeReference6115 = new BitSet(new long[]{0x0140000000000000L});
    public static final BitSet FOLLOW_54_in_ruleJvmParameterizedTypeReference6128 = new BitSet(new long[]{0xF800000004000800L,0x0000000000000007L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleJvmParameterizedTypeReference6149 = new BitSet(new long[]{0x0140000000000000L});
    public static final BitSet FOLLOW_56_in_ruleJvmParameterizedTypeReference6163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_entryRuleJvmArgumentTypeReference6201 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmArgumentTypeReference6211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleJvmArgumentTypeReference6258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmWildcardTypeReference_in_ruleJvmArgumentTypeReference6285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmWildcardTypeReference_in_entryRuleJvmWildcardTypeReference6320 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmWildcardTypeReference6330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleJvmWildcardTypeReference6376 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_ruleJvmUpperBound_in_ruleJvmWildcardTypeReference6398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmLowerBound_in_ruleJvmWildcardTypeReference6425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmUpperBound_in_entryRuleJvmUpperBound6463 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmUpperBound6473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleJvmUpperBound6510 = new BitSet(new long[]{0xF800000000000800L,0x0000000000000007L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleJvmUpperBound6531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmLowerBound_in_entryRuleJvmLowerBound6567 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmLowerBound6577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ruleJvmLowerBound6614 = new BitSet(new long[]{0xF800000000000800L,0x0000000000000007L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleJvmLowerBound6635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimitiveType_in_entryRulePrimitiveType6672 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimitiveType6683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_rulePrimitiveType6721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_rulePrimitiveType6740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_rulePrimitiveType6759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_rulePrimitiveType6778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_rulePrimitiveType6797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_rulePrimitiveType6816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_rulePrimitiveType6835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_rulePrimitiveType6854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp6895 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalOp6906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_ruleRelationalOp6944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleRelationalOp6963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleRelationalOp6982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleRelationalOp7001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName7042 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedName7053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleQualifiedName7100 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_40_in_ruleQualifiedName7119 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleQualifiedName7141 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_entryRuleValidID7189 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleValidID7200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleValidID7239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_synpred1_InternalJavaJRExpression329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_synpred2_InternalJavaJRExpression519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_synpred3_InternalJavaJRExpression701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred4_InternalJavaJRExpression882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_synpred5_InternalJavaJRExpression1118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_synpred6_InternalJavaJRExpression1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred7_InternalJavaJRExpression1466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred8_InternalJavaJRExpression1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_synpred9_InternalJavaJRExpression2206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBaseJRExpression_in_synpred10_InternalJavaJRExpression2413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_synpred11_InternalJavaJRExpression5940 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_synpred11_InternalJavaJRExpression5944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_synpred12_InternalJavaJRExpression6085 = new BitSet(new long[]{0x0000000000000002L});

}
