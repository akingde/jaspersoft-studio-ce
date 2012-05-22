package com.jaspersoft.studio.editor.jrexpressions.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJavaJRExpressionLexer extends Lexer {
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int RULE_ID=4;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__29=29;
    public static final int T__65=65;
    public static final int T__28=28;
    public static final int T__62=62;
    public static final int T__27=27;
    public static final int T__63=63;
    public static final int T__26=26;
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
    public static final int RULE_INT=6;
    public static final int RULE_CHAR=10;
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
    public static final int T__48=48;
    public static final int RULE_LONG=7;
    public static final int T__49=49;
    public static final int RULE_HEXPREFIX=18;
    public static final int RULE_BRACED_IDENTIFIER=5;
    public static final int RULE_FLOAT=8;
    public static final int RULE_SL_COMMENT=23;
    public static final int RULE_DOUBLE=9;
    public static final int RULE_ML_COMMENT=22;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_DOUBLESUFFIX=16;
    public static final int T__32=32;
    public static final int RULE_STRING=11;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__70=70;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_EXPONENT=20;
    public static final int RULE_INTEGERNUMBER=12;
    public static final int RULE_WS=24;

    // delegates
    // delegators

    public InternalJavaJRExpressionLexer() {;} 
    public InternalJavaJRExpressionLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalJavaJRExpressionLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g"; }

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:11:7: ( '==' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:11:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:12:7: ( '!=' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:12:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:13:7: ( '+' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:13:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:14:7: ( '-' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:14:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:15:7: ( '*' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:15:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:16:7: ( '/' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:16:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:17:7: ( '%' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:17:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:18:7: ( 'false' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:18:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:19:7: ( 'boolean' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:19:9: 'boolean'
            {
            match("boolean"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:20:7: ( 'char' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:20:9: 'char'
            {
            match("char"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:21:7: ( 'byte' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:21:9: 'byte'
            {
            match("byte"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:22:7: ( 'short' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:22:9: 'short'
            {
            match("short"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:23:7: ( 'int' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:23:9: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:24:7: ( 'long' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:24:9: 'long'
            {
            match("long"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:25:7: ( 'float' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:25:9: 'float'
            {
            match("float"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:26:7: ( 'double' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:26:9: 'double'
            {
            match("double"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:27:7: ( '<=' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:27:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:28:7: ( '<' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:28:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:29:7: ( '>=' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:29:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:30:7: ( '>' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:30:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:31:7: ( ':' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:31:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:32:7: ( '?' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:32:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:33:7: ( '~' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:33:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:34:7: ( '!' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:34:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:35:7: ( '.' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:35:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:36:7: ( 'class' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:36:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:37:7: ( '$F' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:37:9: '$F'
            {
            match("$F"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:38:7: ( '$P' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:38:9: '$P'
            {
            match("$P"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:39:7: ( '$V' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:39:9: '$V'
            {
            match("$V"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:40:7: ( 'null' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:40:9: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:41:7: ( '(' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:41:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:42:7: ( ')' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:42:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:43:7: ( 'new' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:43:9: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:44:7: ( '[' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:44:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:45:7: ( ']' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:45:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:46:7: ( '{' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:46:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:47:7: ( '}' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:47:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:48:7: ( ',' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:48:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:49:7: ( 'extends' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:49:9: 'extends'
            {
            match("extends"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:50:7: ( 'super' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:50:9: 'super'
            {
            match("super"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:51:7: ( '||' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:51:9: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:52:7: ( '&&' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:52:9: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:53:7: ( 'instanceof' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:53:9: 'instanceof'
            {
            match("instanceof"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:54:7: ( 'void' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:54:9: 'void'
            {
            match("void"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:55:7: ( 'true' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:55:9: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "RULE_LONG"
    public final void mRULE_LONG() throws RecognitionException {
        try {
            int _type = RULE_LONG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8311:11: ( RULE_INTEGERNUMBER RULE_LONGSUFFIX )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8311:13: RULE_INTEGERNUMBER RULE_LONGSUFFIX
            {
            mRULE_INTEGERNUMBER(); 
            mRULE_LONGSUFFIX(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_LONG"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8313:10: ( RULE_INTEGERNUMBER )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8313:12: RULE_INTEGERNUMBER
            {
            mRULE_INTEGERNUMBER(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_FLOAT"
    public final void mRULE_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8315:12: ( RULE_NONINTEGERNUMBER RULE_FLOATSUFFIX )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8315:14: RULE_NONINTEGERNUMBER RULE_FLOATSUFFIX
            {
            mRULE_NONINTEGERNUMBER(); 
            mRULE_FLOATSUFFIX(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_FLOAT"

    // $ANTLR start "RULE_DOUBLE"
    public final void mRULE_DOUBLE() throws RecognitionException {
        try {
            int _type = RULE_DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8317:13: ( RULE_NONINTEGERNUMBER ( RULE_DOUBLESUFFIX )? )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8317:15: RULE_NONINTEGERNUMBER ( RULE_DOUBLESUFFIX )?
            {
            mRULE_NONINTEGERNUMBER(); 
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8317:37: ( RULE_DOUBLESUFFIX )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='D'||LA1_0=='d') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8317:37: RULE_DOUBLESUFFIX
                    {
                    mRULE_DOUBLESUFFIX(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DOUBLE"

    // $ANTLR start "RULE_CHAR"
    public final void mRULE_CHAR() throws RecognitionException {
        try {
            int _type = RULE_CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8319:11: ( '\\'' ( RULE_ESCAPESEQUENCE | ~ ( ( '\\'' | '\\\\' | '\\r' | '\\n' ) ) ) '\\'' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8319:13: '\\'' ( RULE_ESCAPESEQUENCE | ~ ( ( '\\'' | '\\\\' | '\\r' | '\\n' ) ) ) '\\''
            {
            match('\''); 
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8319:18: ( RULE_ESCAPESEQUENCE | ~ ( ( '\\'' | '\\\\' | '\\r' | '\\n' ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='\\') ) {
                alt2=1;
            }
            else if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='\f')||(LA2_0>='\u000E' && LA2_0<='&')||(LA2_0>='(' && LA2_0<='[')||(LA2_0>=']' && LA2_0<='\uFFFF')) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8319:19: RULE_ESCAPESEQUENCE
                    {
                    mRULE_ESCAPESEQUENCE(); 

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8319:39: ~ ( ( '\\'' | '\\\\' | '\\r' | '\\n' ) )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_CHAR"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8321:13: ( '\"' ( RULE_ESCAPESEQUENCE | ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) ) )* '\"' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8321:15: '\"' ( RULE_ESCAPESEQUENCE | ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) ) )* '\"'
            {
            match('\"'); 
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8321:19: ( RULE_ESCAPESEQUENCE | ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) ) )*
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\\') ) {
                    alt3=1;
                }
                else if ( ((LA3_0>='\u0000' && LA3_0<='\t')||(LA3_0>='\u000B' && LA3_0<='\f')||(LA3_0>='\u000E' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='[')||(LA3_0>=']' && LA3_0<='\uFFFF')) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8321:20: RULE_ESCAPESEQUENCE
            	    {
            	    mRULE_ESCAPESEQUENCE(); 

            	    }
            	    break;
            	case 2 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8321:40: ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ESCAPESEQUENCE"
    public final void mRULE_ESCAPESEQUENCE() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:30: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' | '0' .. '3' '0' .. '7' '0' .. '7' | '0' .. '7' '0' .. '7' | '0' .. '7' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:32: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' | '0' .. '3' '0' .. '7' '0' .. '7' | '0' .. '7' '0' .. '7' | '0' .. '7' )
            {
            match('\\'); 
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:37: ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' | '0' .. '3' '0' .. '7' '0' .. '7' | '0' .. '7' '0' .. '7' | '0' .. '7' )
            int alt4=11;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:38: 'b'
                    {
                    match('b'); 

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:42: 't'
                    {
                    match('t'); 

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:46: 'n'
                    {
                    match('n'); 

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:50: 'f'
                    {
                    match('f'); 

                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:54: 'r'
                    {
                    match('r'); 

                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:58: '\"'
                    {
                    match('\"'); 

                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:62: '\\''
                    {
                    match('\''); 

                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:67: '\\\\'
                    {
                    match('\\'); 

                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:72: '0' .. '3' '0' .. '7' '0' .. '7'
                    {
                    matchRange('0','3'); 
                    matchRange('0','7'); 
                    matchRange('0','7'); 

                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:99: '0' .. '7' '0' .. '7'
                    {
                    matchRange('0','7'); 
                    matchRange('0','7'); 

                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8323:117: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_ESCAPESEQUENCE"

    // $ANTLR start "RULE_INTEGERNUMBER"
    public final void mRULE_INTEGERNUMBER() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:29: ( ( '0' | '1' .. '9' ( '0' .. '9' )* | '0' ( '0' .. '7' )+ | RULE_HEXPREFIX ( RULE_HEXDIGIT )+ ) )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:31: ( '0' | '1' .. '9' ( '0' .. '9' )* | '0' ( '0' .. '7' )+ | RULE_HEXPREFIX ( RULE_HEXDIGIT )+ )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:31: ( '0' | '1' .. '9' ( '0' .. '9' )* | '0' ( '0' .. '7' )+ | RULE_HEXPREFIX ( RULE_HEXDIGIT )+ )
            int alt8=4;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='0') ) {
                switch ( input.LA(2) ) {
                case 'X':
                case 'x':
                    {
                    alt8=4;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt8=3;
                    }
                    break;
                default:
                    alt8=1;}

            }
            else if ( ((LA8_0>='1' && LA8_0<='9')) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:32: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:36: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:45: ( '0' .. '9' )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:46: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:57: '0' ( '0' .. '7' )+
                    {
                    match('0'); 
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:61: ( '0' .. '7' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='7')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:62: '0' .. '7'
                    	    {
                    	    matchRange('0','7'); 

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
                case 4 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:73: RULE_HEXPREFIX ( RULE_HEXDIGIT )+
                    {
                    mRULE_HEXPREFIX(); 
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:88: ( RULE_HEXDIGIT )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='F')||(LA7_0>='a' && LA7_0<='f')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8325:88: RULE_HEXDIGIT
                    	    {
                    	    mRULE_HEXDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);


                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_INTEGERNUMBER"

    // $ANTLR start "RULE_HEXPREFIX"
    public final void mRULE_HEXPREFIX() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8327:25: ( ( '0x' | '0X' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8327:27: ( '0x' | '0X' )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8327:27: ( '0x' | '0X' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='0') ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1=='x') ) {
                    alt9=1;
                }
                else if ( (LA9_1=='X') ) {
                    alt9=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8327:28: '0x'
                    {
                    match("0x"); 


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8327:33: '0X'
                    {
                    match("0X"); 


                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_HEXPREFIX"

    // $ANTLR start "RULE_HEXDIGIT"
    public final void mRULE_HEXDIGIT() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8329:24: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8329:26: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_HEXDIGIT"

    // $ANTLR start "RULE_LONGSUFFIX"
    public final void mRULE_LONGSUFFIX() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8331:26: ( ( 'l' | 'L' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8331:28: ( 'l' | 'L' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_LONGSUFFIX"

    // $ANTLR start "RULE_NONINTEGERNUMBER"
    public final void mRULE_NONINTEGERNUMBER() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:32: ( ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )? | '.' ( '0' .. '9' )+ ( RULE_EXPONENT )? | ( '0' .. '9' )+ RULE_EXPONENT | ( '0' .. '9' )+ | RULE_HEXPREFIX ( RULE_HEXDIGIT )* '.' ( RULE_HEXDIGIT )* ( 'p' | 'P' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:34: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )? | '.' ( '0' .. '9' )+ ( RULE_EXPONENT )? | ( '0' .. '9' )+ RULE_EXPONENT | ( '0' .. '9' )+ | RULE_HEXPREFIX ( RULE_HEXDIGIT )* '.' ( RULE_HEXDIGIT )* ( 'p' | 'P' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:34: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )? | '.' ( '0' .. '9' )+ ( RULE_EXPONENT )? | ( '0' .. '9' )+ RULE_EXPONENT | ( '0' .. '9' )+ | RULE_HEXPREFIX ( RULE_HEXDIGIT )* '.' ( RULE_HEXDIGIT )* ( 'p' | 'P' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            int alt21=5;
            alt21 = dfa21.predict(input);
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:35: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )?
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:35: ( '0' .. '9' )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:36: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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

                    match('.'); 
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:51: ( '0' .. '9' )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:52: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:63: ( RULE_EXPONENT )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0=='E'||LA12_0=='e') ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:63: RULE_EXPONENT
                            {
                            mRULE_EXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:78: '.' ( '0' .. '9' )+ ( RULE_EXPONENT )?
                    {
                    match('.'); 
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:82: ( '0' .. '9' )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:83: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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

                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:94: ( RULE_EXPONENT )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='E'||LA14_0=='e') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:94: RULE_EXPONENT
                            {
                            mRULE_EXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:109: ( '0' .. '9' )+ RULE_EXPONENT
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:109: ( '0' .. '9' )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:110: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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

                    mRULE_EXPONENT(); 

                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:135: ( '0' .. '9' )+
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:135: ( '0' .. '9' )+
                    int cnt16=0;
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:136: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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
                case 5 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:147: RULE_HEXPREFIX ( RULE_HEXDIGIT )* '.' ( RULE_HEXDIGIT )* ( 'p' | 'P' ) ( '+' | '-' )? ( '0' .. '9' )+
                    {
                    mRULE_HEXPREFIX(); 
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:162: ( RULE_HEXDIGIT )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>='0' && LA17_0<='9')||(LA17_0>='A' && LA17_0<='F')||(LA17_0>='a' && LA17_0<='f')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:162: RULE_HEXDIGIT
                    	    {
                    	    mRULE_HEXDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);

                    match('.'); 
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:181: ( RULE_HEXDIGIT )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>='0' && LA18_0<='9')||(LA18_0>='A' && LA18_0<='F')||(LA18_0>='a' && LA18_0<='f')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:181: RULE_HEXDIGIT
                    	    {
                    	    mRULE_HEXDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:206: ( '+' | '-' )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0=='+'||LA19_0=='-') ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }

                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:217: ( '0' .. '9' )+
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( ((LA20_0>='0' && LA20_0<='9')) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8333:218: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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
        finally {
        }
    }
    // $ANTLR end "RULE_NONINTEGERNUMBER"

    // $ANTLR start "RULE_EXPONENT"
    public final void mRULE_EXPONENT() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8335:24: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8335:26: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8335:36: ( '+' | '-' )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='+'||LA22_0=='-') ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8335:47: ( '0' .. '9' )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>='0' && LA23_0<='9')) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8335:48: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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

        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXPONENT"

    // $ANTLR start "RULE_FLOATSUFFIX"
    public final void mRULE_FLOATSUFFIX() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8337:27: ( ( 'f' | 'F' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8337:29: ( 'f' | 'F' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_FLOATSUFFIX"

    // $ANTLR start "RULE_DOUBLESUFFIX"
    public final void mRULE_DOUBLESUFFIX() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8339:28: ( ( 'd' | 'D' ) )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8339:30: ( 'd' | 'D' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_DOUBLESUFFIX"

    // $ANTLR start "RULE_BRACED_IDENTIFIER"
    public final void mRULE_BRACED_IDENTIFIER() throws RecognitionException {
        try {
            int _type = RULE_BRACED_IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8341:24: ( '{' RULE_EXPOBJIDENTIFIER '}' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8341:26: '{' RULE_EXPOBJIDENTIFIER '}'
            {
            match('{'); 
            mRULE_EXPOBJIDENTIFIER(); 
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BRACED_IDENTIFIER"

    // $ANTLR start "RULE_EXPOBJIDENTIFIER"
    public final void mRULE_EXPOBJIDENTIFIER() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8343:32: ( (~ ( ( '\\r' | '\\n' | '}' ) ) )+ )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8343:34: (~ ( ( '\\r' | '\\n' | '}' ) ) )+
            {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8343:34: (~ ( ( '\\r' | '\\n' | '}' ) ) )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>='\u0000' && LA24_0<='\t')||(LA24_0>='\u000B' && LA24_0<='\f')||(LA24_0>='\u000E' && LA24_0<='|')||(LA24_0>='~' && LA24_0<='\uFFFF')) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8343:34: ~ ( ( '\\r' | '\\n' | '}' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='|')||(input.LA(1)>='~' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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

        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXPOBJIDENTIFIER"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8345:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8345:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8345:11: ( '^' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0=='^') ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8345:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8345:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>='0' && LA26_0<='9')||(LA26_0>='A' && LA26_0<='Z')||LA26_0=='_'||(LA26_0>='a' && LA26_0<='z')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8347:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8347:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8347:24: ( options {greedy=false; } : . )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0=='*') ) {
                    int LA27_1 = input.LA(2);

                    if ( (LA27_1=='/') ) {
                        alt27=2;
                    }
                    else if ( ((LA27_1>='\u0000' && LA27_1<='.')||(LA27_1>='0' && LA27_1<='\uFFFF')) ) {
                        alt27=1;
                    }


                }
                else if ( ((LA27_0>='\u0000' && LA27_0<=')')||(LA27_0>='+' && LA27_0<='\uFFFF')) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8347:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8349:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8349:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8349:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>='\u0000' && LA28_0<='\t')||(LA28_0>='\u000B' && LA28_0<='\f')||(LA28_0>='\u000E' && LA28_0<='\uFFFF')) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8349:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8349:40: ( ( '\\r' )? '\\n' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0=='\n'||LA30_0=='\r') ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8349:41: ( '\\r' )? '\\n'
                    {
                    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8349:41: ( '\\r' )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0=='\r') ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8349:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8351:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8351:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8351:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt31=0;
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>='\t' && LA31_0<='\n')||LA31_0=='\r'||LA31_0==' ') ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt31 >= 1 ) break loop31;
                        EarlyExitException eee =
                            new EarlyExitException(31, input);
                        throw eee;
                }
                cnt31++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8353:16: ( . )
            // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:8353:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:8: ( T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | RULE_LONG | RULE_INT | RULE_FLOAT | RULE_DOUBLE | RULE_CHAR | RULE_STRING | RULE_BRACED_IDENTIFIER | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt32=57;
        alt32 = dfa32.predict(input);
        switch (alt32) {
            case 1 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:10: T__26
                {
                mT__26(); 

                }
                break;
            case 2 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:16: T__27
                {
                mT__27(); 

                }
                break;
            case 3 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:22: T__28
                {
                mT__28(); 

                }
                break;
            case 4 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:28: T__29
                {
                mT__29(); 

                }
                break;
            case 5 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:34: T__30
                {
                mT__30(); 

                }
                break;
            case 6 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:40: T__31
                {
                mT__31(); 

                }
                break;
            case 7 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:46: T__32
                {
                mT__32(); 

                }
                break;
            case 8 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:52: T__33
                {
                mT__33(); 

                }
                break;
            case 9 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:58: T__34
                {
                mT__34(); 

                }
                break;
            case 10 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:64: T__35
                {
                mT__35(); 

                }
                break;
            case 11 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:70: T__36
                {
                mT__36(); 

                }
                break;
            case 12 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:76: T__37
                {
                mT__37(); 

                }
                break;
            case 13 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:82: T__38
                {
                mT__38(); 

                }
                break;
            case 14 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:88: T__39
                {
                mT__39(); 

                }
                break;
            case 15 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:94: T__40
                {
                mT__40(); 

                }
                break;
            case 16 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:100: T__41
                {
                mT__41(); 

                }
                break;
            case 17 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:106: T__42
                {
                mT__42(); 

                }
                break;
            case 18 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:112: T__43
                {
                mT__43(); 

                }
                break;
            case 19 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:118: T__44
                {
                mT__44(); 

                }
                break;
            case 20 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:124: T__45
                {
                mT__45(); 

                }
                break;
            case 21 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:130: T__46
                {
                mT__46(); 

                }
                break;
            case 22 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:136: T__47
                {
                mT__47(); 

                }
                break;
            case 23 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:142: T__48
                {
                mT__48(); 

                }
                break;
            case 24 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:148: T__49
                {
                mT__49(); 

                }
                break;
            case 25 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:154: T__50
                {
                mT__50(); 

                }
                break;
            case 26 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:160: T__51
                {
                mT__51(); 

                }
                break;
            case 27 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:166: T__52
                {
                mT__52(); 

                }
                break;
            case 28 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:172: T__53
                {
                mT__53(); 

                }
                break;
            case 29 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:178: T__54
                {
                mT__54(); 

                }
                break;
            case 30 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:184: T__55
                {
                mT__55(); 

                }
                break;
            case 31 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:190: T__56
                {
                mT__56(); 

                }
                break;
            case 32 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:196: T__57
                {
                mT__57(); 

                }
                break;
            case 33 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:202: T__58
                {
                mT__58(); 

                }
                break;
            case 34 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:208: T__59
                {
                mT__59(); 

                }
                break;
            case 35 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:214: T__60
                {
                mT__60(); 

                }
                break;
            case 36 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:220: T__61
                {
                mT__61(); 

                }
                break;
            case 37 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:226: T__62
                {
                mT__62(); 

                }
                break;
            case 38 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:232: T__63
                {
                mT__63(); 

                }
                break;
            case 39 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:238: T__64
                {
                mT__64(); 

                }
                break;
            case 40 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:244: T__65
                {
                mT__65(); 

                }
                break;
            case 41 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:250: T__66
                {
                mT__66(); 

                }
                break;
            case 42 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:256: T__67
                {
                mT__67(); 

                }
                break;
            case 43 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:262: T__68
                {
                mT__68(); 

                }
                break;
            case 44 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:268: T__69
                {
                mT__69(); 

                }
                break;
            case 45 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:274: T__70
                {
                mT__70(); 

                }
                break;
            case 46 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:280: RULE_LONG
                {
                mRULE_LONG(); 

                }
                break;
            case 47 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:290: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 48 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:299: RULE_FLOAT
                {
                mRULE_FLOAT(); 

                }
                break;
            case 49 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:310: RULE_DOUBLE
                {
                mRULE_DOUBLE(); 

                }
                break;
            case 50 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:322: RULE_CHAR
                {
                mRULE_CHAR(); 

                }
                break;
            case 51 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:332: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 52 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:344: RULE_BRACED_IDENTIFIER
                {
                mRULE_BRACED_IDENTIFIER(); 

                }
                break;
            case 53 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:367: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 54 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:375: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 55 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:391: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 56 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:407: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 57 :
                // ../com.jaspersoft.studio.editor.jrexpressions.ui/src-gen/com/jaspersoft/studio/editor/jrexpressions/ui/contentassist/antlr/internal/InternalJavaJRExpression.g:1:415: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA21 dfa21 = new DFA21(this);
    protected DFA32 dfa32 = new DFA32(this);
    static final String DFA4_eotS =
        "\11\uffff\2\14\1\15\3\uffff";
    static final String DFA4_eofS =
        "\17\uffff";
    static final String DFA4_minS =
        "\1\42\10\uffff\3\60\3\uffff";
    static final String DFA4_maxS =
        "\1\164\10\uffff\3\67\3\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\3\uffff\1\13\1\12\1\11";
    static final String DFA4_specialS =
        "\17\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\6\4\uffff\1\7\10\uffff\4\11\4\12\44\uffff\1\10\5\uffff\1"+
            "\1\3\uffff\1\4\7\uffff\1\3\3\uffff\1\5\1\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\10\13",
            "\10\15",
            "\10\16",
            "",
            "",
            ""
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
            return "8323:37: ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' | '0' .. '3' '0' .. '7' '0' .. '7' | '0' .. '7' '0' .. '7' | '0' .. '7' )";
        }
    }
    static final String DFA21_eotS =
        "\1\uffff\1\5\1\uffff\1\5\4\uffff";
    static final String DFA21_eofS =
        "\10\uffff";
    static final String DFA21_minS =
        "\2\56\1\uffff\1\56\4\uffff";
    static final String DFA21_maxS =
        "\1\71\1\170\1\uffff\1\145\4\uffff";
    static final String DFA21_acceptS =
        "\2\uffff\1\2\1\uffff\1\5\1\4\1\1\1\3";
    static final String DFA21_specialS =
        "\10\uffff}>";
    static final String[] DFA21_transitionS = {
            "\1\2\1\uffff\1\1\11\3",
            "\1\6\1\uffff\12\3\13\uffff\1\7\22\uffff\1\4\14\uffff\1\7\22"+
            "\uffff\1\4",
            "",
            "\1\6\1\uffff\12\3\13\uffff\1\7\37\uffff\1\7",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
    static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
    static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
    static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
    static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
    static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
    static final short[][] DFA21_transition;

    static {
        int numStates = DFA21_transitionS.length;
        DFA21_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
        }
    }

    class DFA21 extends DFA {

        public DFA21(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = DFA21_eot;
            this.eof = DFA21_eof;
            this.min = DFA21_min;
            this.max = DFA21_max;
            this.accept = DFA21_accept;
            this.special = DFA21_special;
            this.transition = DFA21_transition;
        }
        public String getDescription() {
            return "8333:34: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )? | '.' ( '0' .. '9' )+ ( RULE_EXPONENT )? | ( '0' .. '9' )+ RULE_EXPONENT | ( '0' .. '9' )+ | RULE_HEXPREFIX ( RULE_HEXDIGIT )* '.' ( RULE_HEXDIGIT )* ( 'p' | 'P' ) ( '+' | '-' )? ( '0' .. '9' )+ )";
        }
    }
    static final String DFA32_eotS =
        "\1\uffff\1\52\1\55\3\uffff\1\63\1\uffff\7\67\1\102\1\104\3\uffff"+
        "\1\111\1\52\1\67\4\uffff\1\124\2\uffff\1\67\2\52\2\67\2\136\3\52"+
        "\15\uffff\2\67\1\uffff\11\67\7\uffff\1\137\4\uffff\2\67\10\uffff"+
        "\1\67\2\uffff\2\67\4\uffff\1\136\1\137\1\uffff\1\137\2\uffff\1\136"+
        "\3\uffff\10\67\1\u008a\3\67\1\uffff\1\67\1\u0091\3\67\1\136\2\uffff"+
        "\2\137\1\uffff\3\67\1\u009c\1\u009d\3\67\1\uffff\1\67\1\u00a2\1"+
        "\67\1\uffff\1\137\1\u00a4\1\uffff\1\67\1\u00a6\1\u00a7\3\uffff\1"+
        "\137\1\u00aa\1\u00ab\1\67\2\uffff\1\u00ad\1\u00ae\1\u00af\1\67\1"+
        "\uffff\1\67\1\uffff\1\67\3\uffff\1\137\2\uffff\1\67\3\uffff\1\67"+
        "\1\u00b5\1\67\1\u00b7\1\67\1\uffff\1\u00b9\1\uffff\1\67\1\uffff"+
        "\1\67\1\u00bc\1\uffff";
    static final String DFA32_eofS =
        "\u00bd\uffff";
    static final String DFA32_minS =
        "\1\0\2\75\3\uffff\1\52\1\uffff\1\141\1\157\2\150\1\156\2\157\2\75"+
        "\3\uffff\1\60\1\106\1\145\4\uffff\1\0\2\uffff\1\170\1\174\1\46\1"+
        "\157\1\162\2\56\2\0\1\101\15\uffff\1\154\1\157\1\uffff\1\157\1\164"+
        "\2\141\1\157\1\160\1\163\1\156\1\165\7\uffff\1\60\4\uffff\1\154"+
        "\1\167\10\uffff\1\164\2\uffff\1\151\1\165\2\56\2\uffff\2\56\1\53"+
        "\1\60\2\uffff\1\56\3\uffff\1\163\1\141\1\154\1\145\1\162\1\163\1"+
        "\162\1\145\1\60\1\164\1\147\1\142\1\53\1\154\1\60\1\145\1\144\1"+
        "\145\1\56\4\60\1\53\1\145\1\164\1\145\2\60\1\163\1\164\1\162\1\uffff"+
        "\1\141\1\60\1\154\3\60\1\uffff\1\156\3\60\1\53\4\60\1\141\2\uffff"+
        "\3\60\1\156\1\uffff\1\145\1\uffff\1\144\2\uffff\2\60\2\uffff\1\156"+
        "\3\uffff\1\143\1\60\1\163\1\60\1\145\1\uffff\1\60\1\uffff\1\157"+
        "\1\uffff\1\146\1\60\1\uffff";
    static final String DFA32_maxS =
        "\1\uffff\2\75\3\uffff\1\57\1\uffff\1\154\1\171\1\154\1\165\1\156"+
        "\2\157\2\75\3\uffff\1\71\1\126\1\165\4\uffff\1\uffff\2\uffff\1\170"+
        "\1\174\1\46\1\157\1\162\1\170\1\154\2\uffff\1\172\15\uffff\1\154"+
        "\1\157\1\uffff\1\157\1\164\2\141\1\157\1\160\1\164\1\156\1\165\7"+
        "\uffff\1\146\4\uffff\1\154\1\167\10\uffff\1\164\2\uffff\1\151\1"+
        "\165\2\146\2\uffff\1\154\1\146\1\71\1\146\2\uffff\1\154\3\uffff"+
        "\1\163\1\141\1\154\1\145\1\162\1\163\1\162\1\145\1\172\1\164\1\147"+
        "\1\142\1\71\1\154\1\172\1\145\1\144\1\145\1\154\1\160\1\71\2\146"+
        "\1\71\1\145\1\164\1\145\2\172\1\163\1\164\1\162\1\uffff\1\141\1"+
        "\172\1\154\1\71\1\146\1\172\1\uffff\1\156\2\172\1\160\2\71\1\146"+
        "\2\172\1\141\2\uffff\3\172\1\156\1\uffff\1\145\1\uffff\1\144\2\uffff"+
        "\1\71\1\146\2\uffff\1\156\3\uffff\1\143\1\172\1\163\1\172\1\145"+
        "\1\uffff\1\172\1\uffff\1\157\1\uffff\1\146\1\172\1\uffff";
    static final String DFA32_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\uffff\1\7\11\uffff\1\25\1\26\1\27\3\uffff"+
        "\1\37\1\40\1\42\1\43\1\uffff\1\45\1\46\12\uffff\1\65\1\70\1\71\1"+
        "\1\1\2\1\30\1\3\1\4\1\5\1\66\1\67\1\6\1\7\2\uffff\1\65\11\uffff"+
        "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\uffff\1\31\1\33\1\34\1\35"+
        "\2\uffff\1\37\1\40\1\42\1\43\1\64\1\44\1\45\1\46\1\uffff\1\51\1"+
        "\52\4\uffff\1\57\1\61\4\uffff\1\56\1\60\1\uffff\1\62\1\63\1\70\40"+
        "\uffff\1\15\6\uffff\1\41\12\uffff\1\13\1\12\4\uffff\1\16\1\uffff"+
        "\1\36\1\uffff\1\54\1\55\2\uffff\1\10\1\17\1\uffff\1\32\1\14\1\50"+
        "\5\uffff\1\20\1\uffff\1\11\1\uffff\1\47\2\uffff\1\53";
    static final String DFA32_specialS =
        "\1\2\32\uffff\1\1\11\uffff\1\0\1\3\u0096\uffff}>";
    static final String[] DFA32_transitionS = {
            "\11\52\2\51\2\52\1\51\22\52\1\51\1\2\1\46\1\52\1\25\1\7\1\40"+
            "\1\45\1\27\1\30\1\5\1\3\1\35\1\4\1\24\1\6\1\43\11\44\1\21\1"+
            "\52\1\17\1\1\1\20\1\22\1\52\32\50\1\31\1\52\1\32\1\47\1\50\1"+
            "\52\1\50\1\11\1\12\1\16\1\36\1\10\2\50\1\14\2\50\1\15\1\50\1"+
            "\26\4\50\1\13\1\42\1\50\1\41\4\50\1\33\1\37\1\34\1\23\uff81"+
            "\52",
            "\1\53",
            "\1\54",
            "",
            "",
            "",
            "\1\61\4\uffff\1\62",
            "",
            "\1\65\12\uffff\1\66",
            "\1\70\11\uffff\1\71",
            "\1\72\3\uffff\1\73",
            "\1\74\14\uffff\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\103",
            "",
            "",
            "",
            "\12\110",
            "\1\112\11\uffff\1\113\5\uffff\1\114",
            "\1\116\17\uffff\1\115",
            "",
            "",
            "",
            "",
            "\12\123\1\uffff\2\123\1\uffff\157\123\1\uffff\uff82\123",
            "",
            "",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\143\1\uffff\10\140\2\141\12\uffff\1\137\1\142\1\145\5\uffff"+
            "\1\144\13\uffff\1\135\13\uffff\1\137\1\142\1\145\5\uffff\1\144"+
            "\13\uffff\1\134",
            "\1\143\1\uffff\12\146\12\uffff\1\137\1\142\1\145\5\uffff\1"+
            "\144\27\uffff\1\137\1\142\1\145\5\uffff\1\144",
            "\12\147\1\uffff\2\147\1\uffff\31\147\1\uffff\uffd8\147",
            "\12\150\1\uffff\2\150\1\uffff\ufff2\150",
            "\32\67\4\uffff\1\67\1\uffff\32\67",
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
            "\1\152",
            "\1\153",
            "",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\163\1\162",
            "\1\164",
            "\1\165",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\110\13\uffff\1\166\1\145\36\uffff\1\166\1\145",
            "",
            "",
            "",
            "",
            "\1\167",
            "\1\170",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\171",
            "",
            "",
            "\1\172",
            "\1\173",
            "\1\175\1\uffff\12\174\7\uffff\6\174\32\uffff\6\174",
            "\1\175\1\uffff\12\174\7\uffff\6\174\32\uffff\6\174",
            "",
            "",
            "\1\143\1\uffff\10\140\2\141\12\uffff\1\137\1\142\1\145\5\uffff"+
            "\1\144\27\uffff\1\137\1\142\1\145\5\uffff\1\144",
            "\1\143\1\uffff\12\141\13\uffff\1\142\1\145\36\uffff\1\142\1"+
            "\145",
            "\1\176\1\uffff\1\176\2\uffff\12\177",
            "\12\u0080\13\uffff\1\u0081\1\145\36\uffff\1\u0081\1\145",
            "",
            "",
            "\1\143\1\uffff\12\146\12\uffff\1\137\1\142\1\145\5\uffff\1"+
            "\144\27\uffff\1\137\1\142\1\145\5\uffff\1\144",
            "",
            "",
            "",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e\1\uffff\1\u008e\2\uffff\12\u008f",
            "\1\u0090",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\175\1\uffff\12\174\7\uffff\6\174\5\uffff\1\144\24\uffff"+
            "\6\174\5\uffff\1\144",
            "\12\u0095\7\uffff\6\u0095\11\uffff\1\u0096\20\uffff\6\u0095"+
            "\11\uffff\1\u0096",
            "\12\177",
            "\12\177\14\uffff\1\145\37\uffff\1\145",
            "\12\u0080\13\uffff\1\u0081\1\145\36\uffff\1\u0081\1\145",
            "\1\u0097\1\uffff\1\u0097\2\uffff\12\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "",
            "\1\u00a1",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\1\u00a3",
            "\12\u008f",
            "\12\u008f\14\uffff\1\145\37\uffff\1\145",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "",
            "\1\u00a5",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\12\u0095\7\uffff\6\u0095\11\uffff\1\u0096\20\uffff\6\u0095"+
            "\11\uffff\1\u0096",
            "\1\u00a8\1\uffff\1\u00a8\2\uffff\12\u00a9",
            "\12\u0098",
            "\12\u0098\14\uffff\1\145\37\uffff\1\145",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\1\u00ac",
            "",
            "",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\1\u00b0",
            "",
            "\1\u00b1",
            "",
            "\1\u00b2",
            "",
            "",
            "\12\u00a9",
            "\12\u00a9\14\uffff\1\145\37\uffff\1\145",
            "",
            "",
            "\1\u00b3",
            "",
            "",
            "",
            "\1\u00b4",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\1\u00b6",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "\1\u00b8",
            "",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            "",
            "\1\u00ba",
            "",
            "\1\u00bb",
            "\12\67\7\uffff\32\67\4\uffff\1\67\1\uffff\32\67",
            ""
    };

    static final short[] DFA32_eot = DFA.unpackEncodedString(DFA32_eotS);
    static final short[] DFA32_eof = DFA.unpackEncodedString(DFA32_eofS);
    static final char[] DFA32_min = DFA.unpackEncodedStringToUnsignedChars(DFA32_minS);
    static final char[] DFA32_max = DFA.unpackEncodedStringToUnsignedChars(DFA32_maxS);
    static final short[] DFA32_accept = DFA.unpackEncodedString(DFA32_acceptS);
    static final short[] DFA32_special = DFA.unpackEncodedString(DFA32_specialS);
    static final short[][] DFA32_transition;

    static {
        int numStates = DFA32_transitionS.length;
        DFA32_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA32_transition[i] = DFA.unpackEncodedString(DFA32_transitionS[i]);
        }
    }

    class DFA32 extends DFA {

        public DFA32(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 32;
            this.eot = DFA32_eot;
            this.eof = DFA32_eof;
            this.min = DFA32_min;
            this.max = DFA32_max;
            this.accept = DFA32_accept;
            this.special = DFA32_special;
            this.transition = DFA32_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | RULE_LONG | RULE_INT | RULE_FLOAT | RULE_DOUBLE | RULE_CHAR | RULE_STRING | RULE_BRACED_IDENTIFIER | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA32_37 = input.LA(1);

                        s = -1;
                        if ( ((LA32_37>='\u0000' && LA32_37<='\t')||(LA32_37>='\u000B' && LA32_37<='\f')||(LA32_37>='\u000E' && LA32_37<='&')||(LA32_37>='(' && LA32_37<='\uFFFF')) ) {s = 103;}

                        else s = 42;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA32_27 = input.LA(1);

                        s = -1;
                        if ( ((LA32_27>='\u0000' && LA32_27<='\t')||(LA32_27>='\u000B' && LA32_27<='\f')||(LA32_27>='\u000E' && LA32_27<='|')||(LA32_27>='~' && LA32_27<='\uFFFF')) ) {s = 83;}

                        else s = 84;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA32_0 = input.LA(1);

                        s = -1;
                        if ( (LA32_0=='=') ) {s = 1;}

                        else if ( (LA32_0=='!') ) {s = 2;}

                        else if ( (LA32_0=='+') ) {s = 3;}

                        else if ( (LA32_0=='-') ) {s = 4;}

                        else if ( (LA32_0=='*') ) {s = 5;}

                        else if ( (LA32_0=='/') ) {s = 6;}

                        else if ( (LA32_0=='%') ) {s = 7;}

                        else if ( (LA32_0=='f') ) {s = 8;}

                        else if ( (LA32_0=='b') ) {s = 9;}

                        else if ( (LA32_0=='c') ) {s = 10;}

                        else if ( (LA32_0=='s') ) {s = 11;}

                        else if ( (LA32_0=='i') ) {s = 12;}

                        else if ( (LA32_0=='l') ) {s = 13;}

                        else if ( (LA32_0=='d') ) {s = 14;}

                        else if ( (LA32_0=='<') ) {s = 15;}

                        else if ( (LA32_0=='>') ) {s = 16;}

                        else if ( (LA32_0==':') ) {s = 17;}

                        else if ( (LA32_0=='?') ) {s = 18;}

                        else if ( (LA32_0=='~') ) {s = 19;}

                        else if ( (LA32_0=='.') ) {s = 20;}

                        else if ( (LA32_0=='$') ) {s = 21;}

                        else if ( (LA32_0=='n') ) {s = 22;}

                        else if ( (LA32_0=='(') ) {s = 23;}

                        else if ( (LA32_0==')') ) {s = 24;}

                        else if ( (LA32_0=='[') ) {s = 25;}

                        else if ( (LA32_0==']') ) {s = 26;}

                        else if ( (LA32_0=='{') ) {s = 27;}

                        else if ( (LA32_0=='}') ) {s = 28;}

                        else if ( (LA32_0==',') ) {s = 29;}

                        else if ( (LA32_0=='e') ) {s = 30;}

                        else if ( (LA32_0=='|') ) {s = 31;}

                        else if ( (LA32_0=='&') ) {s = 32;}

                        else if ( (LA32_0=='v') ) {s = 33;}

                        else if ( (LA32_0=='t') ) {s = 34;}

                        else if ( (LA32_0=='0') ) {s = 35;}

                        else if ( ((LA32_0>='1' && LA32_0<='9')) ) {s = 36;}

                        else if ( (LA32_0=='\'') ) {s = 37;}

                        else if ( (LA32_0=='\"') ) {s = 38;}

                        else if ( (LA32_0=='^') ) {s = 39;}

                        else if ( ((LA32_0>='A' && LA32_0<='Z')||LA32_0=='_'||LA32_0=='a'||(LA32_0>='g' && LA32_0<='h')||(LA32_0>='j' && LA32_0<='k')||LA32_0=='m'||(LA32_0>='o' && LA32_0<='r')||LA32_0=='u'||(LA32_0>='w' && LA32_0<='z')) ) {s = 40;}

                        else if ( ((LA32_0>='\t' && LA32_0<='\n')||LA32_0=='\r'||LA32_0==' ') ) {s = 41;}

                        else if ( ((LA32_0>='\u0000' && LA32_0<='\b')||(LA32_0>='\u000B' && LA32_0<='\f')||(LA32_0>='\u000E' && LA32_0<='\u001F')||LA32_0=='#'||LA32_0==';'||LA32_0=='@'||LA32_0=='\\'||LA32_0=='`'||(LA32_0>='\u007F' && LA32_0<='\uFFFF')) ) {s = 42;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA32_38 = input.LA(1);

                        s = -1;
                        if ( ((LA32_38>='\u0000' && LA32_38<='\t')||(LA32_38>='\u000B' && LA32_38<='\f')||(LA32_38>='\u000E' && LA32_38<='\uFFFF')) ) {s = 104;}

                        else s = 42;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 32, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}