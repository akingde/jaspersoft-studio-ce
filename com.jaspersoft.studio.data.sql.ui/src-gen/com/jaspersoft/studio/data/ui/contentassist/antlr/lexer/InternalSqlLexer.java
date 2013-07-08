package com.jaspersoft.studio.data.ui.contentassist.antlr.lexer;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSqlLexer extends Lexer {
    public static final int RULE_ID=55;
    public static final int RULE_DATE=50;
    public static final int RULE_ANY_OTHER=59;
    public static final int KEYWORD_19=26;
    public static final int KEYWORD_17=35;
    public static final int KEYWORD_18=36;
    public static final int KEYWORD_15=33;
    public static final int KEYWORD_16=34;
    public static final int KEYWORD_13=31;
    public static final int KEYWORD_14=32;
    public static final int KEYWORD_11=47;
    public static final int EOF=-1;
    public static final int KEYWORD_12=30;
    public static final int KEYWORD_10=46;
    public static final int KEYWORD_6=42;
    public static final int KEYWORD_7=43;
    public static final int KEYWORD_8=44;
    public static final int KEYWORD_9=45;
    public static final int RULE_TIME=51;
    public static final int KEYWORD_28=19;
    public static final int KEYWORD_29=20;
    public static final int RULE_SIGNED_DOUBLE=53;
    public static final int RULE_TIMESTAMP=52;
    public static final int RULE_INT=49;
    public static final int KEYWORD_24=22;
    public static final int KEYWORD_25=23;
    public static final int KEYWORD_26=24;
    public static final int KEYWORD_27=25;
    public static final int KEYWORD_20=27;
    public static final int KEYWORD_21=28;
    public static final int KEYWORD_22=29;
    public static final int KEYWORD_23=21;
    public static final int KEYWORD_30=17;
    public static final int KEYWORD_1=37;
    public static final int KEYWORD_34=10;
    public static final int KEYWORD_5=41;
    public static final int KEYWORD_33=16;
    public static final int KEYWORD_4=40;
    public static final int KEYWORD_32=15;
    public static final int KEYWORD_3=39;
    public static final int KEYWORD_31=18;
    public static final int KEYWORD_2=38;
    public static final int KEYWORD_38=14;
    public static final int RULE_SL_COMMENT=54;
    public static final int KEYWORD_37=13;
    public static final int KEYWORD_36=12;
    public static final int KEYWORD_35=11;
    public static final int RULE_ML_COMMENT=57;
    public static final int KEYWORD_39=8;
    public static final int RULE_STRING=56;
    public static final int RULE_STAR=48;
    public static final int KEYWORD_41=7;
    public static final int KEYWORD_40=9;
    public static final int KEYWORD_43=6;
    public static final int KEYWORD_42=5;
    public static final int KEYWORD_44=4;
    public static final int RULE_WS=58;

    // delegates
    // delegators

    public InternalSqlLexer() {;} 
    public InternalSqlLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalSqlLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g"; }

    // $ANTLR start "KEYWORD_44"
    public final void mKEYWORD_44() throws RecognitionException {
        try {
            int _type = KEYWORD_44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:19:12: ( ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:19:14: ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_44"

    // $ANTLR start "KEYWORD_42"
    public final void mKEYWORD_42() throws RecognitionException {
        try {
            int _type = KEYWORD_42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:21:12: ( ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:21:14: ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_42"

    // $ANTLR start "KEYWORD_43"
    public final void mKEYWORD_43() throws RecognitionException {
        try {
            int _type = KEYWORD_43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:23:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:23:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_43"

    // $ANTLR start "KEYWORD_41"
    public final void mKEYWORD_41() throws RecognitionException {
        try {
            int _type = KEYWORD_41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:25:12: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:25:14: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_41"

    // $ANTLR start "KEYWORD_39"
    public final void mKEYWORD_39() throws RecognitionException {
        try {
            int _type = KEYWORD_39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:27:12: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:27:14: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_39"

    // $ANTLR start "KEYWORD_40"
    public final void mKEYWORD_40() throws RecognitionException {
        try {
            int _type = KEYWORD_40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:29:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:29:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_40"

    // $ANTLR start "KEYWORD_34"
    public final void mKEYWORD_34() throws RecognitionException {
        try {
            int _type = KEYWORD_34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:31:12: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:31:14: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_34"

    // $ANTLR start "KEYWORD_35"
    public final void mKEYWORD_35() throws RecognitionException {
        try {
            int _type = KEYWORD_35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:33:12: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:33:14: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_35"

    // $ANTLR start "KEYWORD_36"
    public final void mKEYWORD_36() throws RecognitionException {
        try {
            int _type = KEYWORD_36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:35:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' ) ' ' '(' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:35:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' ) ' ' '('
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_36"

    // $ANTLR start "KEYWORD_37"
    public final void mKEYWORD_37() throws RecognitionException {
        try {
            int _type = KEYWORD_37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:37:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:37:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_37"

    // $ANTLR start "KEYWORD_38"
    public final void mKEYWORD_38() throws RecognitionException {
        try {
            int _type = KEYWORD_38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:39:12: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:39:14: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_38"

    // $ANTLR start "KEYWORD_32"
    public final void mKEYWORD_32() throws RecognitionException {
        try {
            int _type = KEYWORD_32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:41:12: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:41:14: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_32"

    // $ANTLR start "KEYWORD_33"
    public final void mKEYWORD_33() throws RecognitionException {
        try {
            int _type = KEYWORD_33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:43:12: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:43:14: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_33"

    // $ANTLR start "KEYWORD_30"
    public final void mKEYWORD_30() throws RecognitionException {
        try {
            int _type = KEYWORD_30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:45:12: ( ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:45:14: ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_30"

    // $ANTLR start "KEYWORD_31"
    public final void mKEYWORD_31() throws RecognitionException {
        try {
            int _type = KEYWORD_31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:47:12: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:47:14: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_31"

    // $ANTLR start "KEYWORD_28"
    public final void mKEYWORD_28() throws RecognitionException {
        try {
            int _type = KEYWORD_28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:49:12: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:49:14: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_28"

    // $ANTLR start "KEYWORD_29"
    public final void mKEYWORD_29() throws RecognitionException {
        try {
            int _type = KEYWORD_29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:51:12: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:51:14: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_29"

    // $ANTLR start "KEYWORD_23"
    public final void mKEYWORD_23() throws RecognitionException {
        try {
            int _type = KEYWORD_23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:53:12: ( '$' ( 'P' | 'p' ) '!' '{' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:53:14: '$' ( 'P' | 'p' ) '!' '{'
            {
            match('$'); 
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('!'); 
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_23"

    // $ANTLR start "KEYWORD_24"
    public final void mKEYWORD_24() throws RecognitionException {
        try {
            int _type = KEYWORD_24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:55:12: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:55:14: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_24"

    // $ANTLR start "KEYWORD_25"
    public final void mKEYWORD_25() throws RecognitionException {
        try {
            int _type = KEYWORD_25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:57:12: ( ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:57:14: ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_25"

    // $ANTLR start "KEYWORD_26"
    public final void mKEYWORD_26() throws RecognitionException {
        try {
            int _type = KEYWORD_26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:59:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ' ' '(' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:59:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ' ' '('
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match(' '); 
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_26"

    // $ANTLR start "KEYWORD_27"
    public final void mKEYWORD_27() throws RecognitionException {
        try {
            int _type = KEYWORD_27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:61:12: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:61:14: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_27"

    // $ANTLR start "KEYWORD_19"
    public final void mKEYWORD_19() throws RecognitionException {
        try {
            int _type = KEYWORD_19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:63:12: ( '$' ( 'P' | 'p' ) '{' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:63:14: '$' ( 'P' | 'p' ) '{'
            {
            match('$'); 
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_19"

    // $ANTLR start "KEYWORD_20"
    public final void mKEYWORD_20() throws RecognitionException {
        try {
            int _type = KEYWORD_20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:65:12: ( '$' ( 'X' | 'x' ) '{' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:65:14: '$' ( 'X' | 'x' ) '{'
            {
            match('$'); 
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_20"

    // $ANTLR start "KEYWORD_21"
    public final void mKEYWORD_21() throws RecognitionException {
        try {
            int _type = KEYWORD_21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:67:12: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:67:14: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_21"

    // $ANTLR start "KEYWORD_22"
    public final void mKEYWORD_22() throws RecognitionException {
        try {
            int _type = KEYWORD_22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:69:12: ( ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:69:14: ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_22"

    // $ANTLR start "KEYWORD_12"
    public final void mKEYWORD_12() throws RecognitionException {
        try {
            int _type = KEYWORD_12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:71:12: ( '<' '=' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:71:14: '<' '='
            {
            match('<'); 
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_12"

    // $ANTLR start "KEYWORD_13"
    public final void mKEYWORD_13() throws RecognitionException {
        try {
            int _type = KEYWORD_13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:73:12: ( '<' '>' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:73:14: '<' '>'
            {
            match('<'); 
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_13"

    // $ANTLR start "KEYWORD_14"
    public final void mKEYWORD_14() throws RecognitionException {
        try {
            int _type = KEYWORD_14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:75:12: ( '>' '=' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:75:14: '>' '='
            {
            match('>'); 
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_14"

    // $ANTLR start "KEYWORD_15"
    public final void mKEYWORD_15() throws RecognitionException {
        try {
            int _type = KEYWORD_15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:77:12: ( ( 'A' | 'a' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:77:14: ( 'A' | 'a' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_15"

    // $ANTLR start "KEYWORD_16"
    public final void mKEYWORD_16() throws RecognitionException {
        try {
            int _type = KEYWORD_16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:79:12: ( ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:79:14: ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_16"

    // $ANTLR start "KEYWORD_17"
    public final void mKEYWORD_17() throws RecognitionException {
        try {
            int _type = KEYWORD_17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:81:12: ( ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:81:14: ( 'O' | 'o' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_17"

    // $ANTLR start "KEYWORD_18"
    public final void mKEYWORD_18() throws RecognitionException {
        try {
            int _type = KEYWORD_18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:83:12: ( '|' '|' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:83:14: '|' '|'
            {
            match('|'); 
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_18"

    // $ANTLR start "KEYWORD_1"
    public final void mKEYWORD_1() throws RecognitionException {
        try {
            int _type = KEYWORD_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:85:11: ( '(' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:85:13: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_1"

    // $ANTLR start "KEYWORD_2"
    public final void mKEYWORD_2() throws RecognitionException {
        try {
            int _type = KEYWORD_2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:87:11: ( ')' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:87:13: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_2"

    // $ANTLR start "KEYWORD_3"
    public final void mKEYWORD_3() throws RecognitionException {
        try {
            int _type = KEYWORD_3;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:89:11: ( '+' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:89:13: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_3"

    // $ANTLR start "KEYWORD_4"
    public final void mKEYWORD_4() throws RecognitionException {
        try {
            int _type = KEYWORD_4;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:91:11: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:91:13: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_4"

    // $ANTLR start "KEYWORD_5"
    public final void mKEYWORD_5() throws RecognitionException {
        try {
            int _type = KEYWORD_5;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:93:11: ( '-' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:93:13: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_5"

    // $ANTLR start "KEYWORD_6"
    public final void mKEYWORD_6() throws RecognitionException {
        try {
            int _type = KEYWORD_6;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:95:11: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:95:13: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_6"

    // $ANTLR start "KEYWORD_7"
    public final void mKEYWORD_7() throws RecognitionException {
        try {
            int _type = KEYWORD_7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:97:11: ( '/' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:97:13: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_7"

    // $ANTLR start "KEYWORD_8"
    public final void mKEYWORD_8() throws RecognitionException {
        try {
            int _type = KEYWORD_8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:99:11: ( '<' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:99:13: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_8"

    // $ANTLR start "KEYWORD_9"
    public final void mKEYWORD_9() throws RecognitionException {
        try {
            int _type = KEYWORD_9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:101:11: ( '=' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:101:13: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_9"

    // $ANTLR start "KEYWORD_10"
    public final void mKEYWORD_10() throws RecognitionException {
        try {
            int _type = KEYWORD_10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:103:12: ( '>' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:103:14: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_10"

    // $ANTLR start "KEYWORD_11"
    public final void mKEYWORD_11() throws RecognitionException {
        try {
            int _type = KEYWORD_11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:105:12: ( '}' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:105:14: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_11"

    // $ANTLR start "RULE_STAR"
    public final void mRULE_STAR() throws RecognitionException {
        try {
            int _type = RULE_STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:109:11: ( '*' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:109:13: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STAR"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:111:10: ( ( '-' )? ( '0' .. '9' )+ )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:111:12: ( '-' )? ( '0' .. '9' )+
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:111:12: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:111:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:111:17: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:111:18: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_TIMESTAMP"
    public final void mRULE_TIMESTAMP() throws RecognitionException {
        try {
            int _type = RULE_TIMESTAMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:113:16: ( RULE_DATE ' ' RULE_TIME )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:113:18: RULE_DATE ' ' RULE_TIME
            {
            mRULE_DATE(); 
            match(' '); 
            mRULE_TIME(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_TIMESTAMP"

    // $ANTLR start "RULE_DATE"
    public final void mRULE_DATE() throws RecognitionException {
        try {
            int _type = RULE_DATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:115:11: ( '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\'' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:115:13: '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\''
            {
            match('\''); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            match('-'); 
            matchRange('0','1'); 
            matchRange('0','9'); 
            match('-'); 
            matchRange('0','3'); 
            matchRange('0','9'); 
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DATE"

    // $ANTLR start "RULE_TIME"
    public final void mRULE_TIME() throws RecognitionException {
        try {
            int _type = RULE_TIME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:117:11: ( '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '1' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\'' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:117:13: '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '1' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\''
            {
            match('\''); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            match(':'); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            match(':'); 
            matchRange('0','1'); 
            matchRange('0','9'); 
            match('.'); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_TIME"

    // $ANTLR start "RULE_SIGNED_DOUBLE"
    public final void mRULE_SIGNED_DOUBLE() throws RecognitionException {
        try {
            int _type = RULE_SIGNED_DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:20: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:22: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:22: ( '-' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:22: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:27: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:28: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:39: ( '.' ( '0' .. '9' )+ )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:40: '.' ( '0' .. '9' )+
                    {
                    match('.'); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:44: ( '0' .. '9' )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:45: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SIGNED_DOUBLE"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:17: ( ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:19: ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:19: ( '--' | '#' | '//' )
            int alt7=3;
            switch ( input.LA(1) ) {
            case '-':
                {
                alt7=1;
                }
                break;
            case '#':
                {
                alt7=2;
                }
                break;
            case '/':
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:20: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:25: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:29: '//'
                    {
                    match("//"); 


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:35: (~ ( ( '\\n' | '\\r' ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:35: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop8;
                }
            } while (true);

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:51: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:52: ( '\\r' )? '\\n'
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:52: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:52: '\\r'
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

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:123:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:123:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:123:11: ( '^' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='^') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:123:11: '^'
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

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:123:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='0' && LA12_0<='9')||(LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:
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
            	    break loop12;
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

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='\"') ) {
                alt15=1;
            }
            else if ( (LA15_0=='\'') ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop13:
                    do {
                        int alt13=3;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0=='\\') ) {
                            alt13=1;
                        }
                        else if ( ((LA13_0>='\u0000' && LA13_0<='!')||(LA13_0>='#' && LA13_0<='[')||(LA13_0>=']' && LA13_0<='\uFFFF')) ) {
                            alt13=2;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:66: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop14:
                    do {
                        int alt14=3;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0=='\\') ) {
                            alt14=1;
                        }
                        else if ( ((LA14_0>='\u0000' && LA14_0<='&')||(LA14_0>='(' && LA14_0<='[')||(LA14_0>=']' && LA14_0<='\uFFFF')) ) {
                            alt14=2;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:137: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    match('\''); 

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
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:127:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:127:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:127:24: ( options {greedy=false; } : . )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0=='*') ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1=='/') ) {
                        alt16=2;
                    }
                    else if ( ((LA16_1>='\u0000' && LA16_1<='.')||(LA16_1>='0' && LA16_1<='\uFFFF')) ) {
                        alt16=1;
                    }


                }
                else if ( ((LA16_0>='\u0000' && LA16_0<=')')||(LA16_0>='+' && LA16_0<='\uFFFF')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:127:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop16;
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

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:129:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:129:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:129:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='\t' && LA17_0<='\n')||LA17_0=='\r'||LA17_0==' ') ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:
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
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:131:16: ( . )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:131:18: .
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
        // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:8: ( KEYWORD_44 | KEYWORD_42 | KEYWORD_43 | KEYWORD_41 | KEYWORD_39 | KEYWORD_40 | KEYWORD_34 | KEYWORD_35 | KEYWORD_36 | KEYWORD_37 | KEYWORD_38 | KEYWORD_32 | KEYWORD_33 | KEYWORD_30 | KEYWORD_31 | KEYWORD_28 | KEYWORD_29 | KEYWORD_23 | KEYWORD_24 | KEYWORD_25 | KEYWORD_26 | KEYWORD_27 | KEYWORD_19 | KEYWORD_20 | KEYWORD_21 | KEYWORD_22 | KEYWORD_12 | KEYWORD_13 | KEYWORD_14 | KEYWORD_15 | KEYWORD_16 | KEYWORD_17 | KEYWORD_18 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_11 | RULE_STAR | RULE_INT | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_SIGNED_DOUBLE | RULE_SL_COMMENT | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt18=56;
        alt18 = dfa18.predict(input);
        switch (alt18) {
            case 1 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:10: KEYWORD_44
                {
                mKEYWORD_44(); 

                }
                break;
            case 2 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:21: KEYWORD_42
                {
                mKEYWORD_42(); 

                }
                break;
            case 3 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:32: KEYWORD_43
                {
                mKEYWORD_43(); 

                }
                break;
            case 4 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:43: KEYWORD_41
                {
                mKEYWORD_41(); 

                }
                break;
            case 5 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:54: KEYWORD_39
                {
                mKEYWORD_39(); 

                }
                break;
            case 6 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:65: KEYWORD_40
                {
                mKEYWORD_40(); 

                }
                break;
            case 7 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:76: KEYWORD_34
                {
                mKEYWORD_34(); 

                }
                break;
            case 8 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:87: KEYWORD_35
                {
                mKEYWORD_35(); 

                }
                break;
            case 9 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:98: KEYWORD_36
                {
                mKEYWORD_36(); 

                }
                break;
            case 10 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:109: KEYWORD_37
                {
                mKEYWORD_37(); 

                }
                break;
            case 11 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:120: KEYWORD_38
                {
                mKEYWORD_38(); 

                }
                break;
            case 12 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:131: KEYWORD_32
                {
                mKEYWORD_32(); 

                }
                break;
            case 13 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:142: KEYWORD_33
                {
                mKEYWORD_33(); 

                }
                break;
            case 14 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:153: KEYWORD_30
                {
                mKEYWORD_30(); 

                }
                break;
            case 15 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:164: KEYWORD_31
                {
                mKEYWORD_31(); 

                }
                break;
            case 16 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:175: KEYWORD_28
                {
                mKEYWORD_28(); 

                }
                break;
            case 17 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:186: KEYWORD_29
                {
                mKEYWORD_29(); 

                }
                break;
            case 18 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:197: KEYWORD_23
                {
                mKEYWORD_23(); 

                }
                break;
            case 19 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:208: KEYWORD_24
                {
                mKEYWORD_24(); 

                }
                break;
            case 20 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:219: KEYWORD_25
                {
                mKEYWORD_25(); 

                }
                break;
            case 21 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:230: KEYWORD_26
                {
                mKEYWORD_26(); 

                }
                break;
            case 22 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:241: KEYWORD_27
                {
                mKEYWORD_27(); 

                }
                break;
            case 23 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:252: KEYWORD_19
                {
                mKEYWORD_19(); 

                }
                break;
            case 24 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:263: KEYWORD_20
                {
                mKEYWORD_20(); 

                }
                break;
            case 25 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:274: KEYWORD_21
                {
                mKEYWORD_21(); 

                }
                break;
            case 26 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:285: KEYWORD_22
                {
                mKEYWORD_22(); 

                }
                break;
            case 27 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:296: KEYWORD_12
                {
                mKEYWORD_12(); 

                }
                break;
            case 28 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:307: KEYWORD_13
                {
                mKEYWORD_13(); 

                }
                break;
            case 29 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:318: KEYWORD_14
                {
                mKEYWORD_14(); 

                }
                break;
            case 30 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:329: KEYWORD_15
                {
                mKEYWORD_15(); 

                }
                break;
            case 31 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:340: KEYWORD_16
                {
                mKEYWORD_16(); 

                }
                break;
            case 32 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:351: KEYWORD_17
                {
                mKEYWORD_17(); 

                }
                break;
            case 33 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:362: KEYWORD_18
                {
                mKEYWORD_18(); 

                }
                break;
            case 34 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:373: KEYWORD_1
                {
                mKEYWORD_1(); 

                }
                break;
            case 35 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:383: KEYWORD_2
                {
                mKEYWORD_2(); 

                }
                break;
            case 36 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:393: KEYWORD_3
                {
                mKEYWORD_3(); 

                }
                break;
            case 37 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:403: KEYWORD_4
                {
                mKEYWORD_4(); 

                }
                break;
            case 38 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:413: KEYWORD_5
                {
                mKEYWORD_5(); 

                }
                break;
            case 39 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:423: KEYWORD_6
                {
                mKEYWORD_6(); 

                }
                break;
            case 40 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:433: KEYWORD_7
                {
                mKEYWORD_7(); 

                }
                break;
            case 41 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:443: KEYWORD_8
                {
                mKEYWORD_8(); 

                }
                break;
            case 42 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:453: KEYWORD_9
                {
                mKEYWORD_9(); 

                }
                break;
            case 43 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:463: KEYWORD_10
                {
                mKEYWORD_10(); 

                }
                break;
            case 44 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:474: KEYWORD_11
                {
                mKEYWORD_11(); 

                }
                break;
            case 45 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:485: RULE_STAR
                {
                mRULE_STAR(); 

                }
                break;
            case 46 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:495: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 47 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:504: RULE_TIMESTAMP
                {
                mRULE_TIMESTAMP(); 

                }
                break;
            case 48 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:519: RULE_DATE
                {
                mRULE_DATE(); 

                }
                break;
            case 49 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:529: RULE_TIME
                {
                mRULE_TIME(); 

                }
                break;
            case 50 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:539: RULE_SIGNED_DOUBLE
                {
                mRULE_SIGNED_DOUBLE(); 

                }
                break;
            case 51 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:558: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 52 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:574: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 53 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:582: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 54 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:594: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 55 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:610: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 56 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:618: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA18_eotS =
        "\1\uffff\16\47\1\45\1\47\1\100\1\102\1\45\4\uffff\1\111\1\uffff"+
        "\1\115\3\uffff\1\121\1\45\1\uffff\1\45\1\uffff\1\45\2\uffff\1\47"+
        "\1\uffff\13\47\1\144\1\145\5\47\2\uffff\1\47\1\157\14\uffff\1\121"+
        "\13\uffff\5\47\1\uffff\1\47\1\uffff\6\47\2\uffff\5\47\2\uffff\1"+
        "\u0083\1\u0084\2\uffff\2\47\1\u0089\1\47\1\u008b\1\uffff\3\47\1"+
        "\u0091\1\47\1\uffff\6\47\4\uffff\1\47\6\uffff\3\47\1\uffff\1\47"+
        "\2\uffff\4\47\1\u00a6\1\u00a7\5\uffff\1\47\2\uffff\1\47\1\u00ac"+
        "\1\u00ad\4\uffff\1\47\1\u00b1\4\uffff\1\u00b4\12\uffff\1\u00bd\6"+
        "\uffff";
    static final String DFA18_eofS =
        "\u00c2\uffff";
    static final String DFA18_minS =
        "\1\0\1\111\1\122\1\105\1\116\1\122\1\105\1\122\1\117\1\116\1\105"+
        "\1\101\1\105\1\116\1\110\1\120\1\116\2\75\1\174\4\uffff\1\55\1\uffff"+
        "\1\52\3\uffff\1\56\1\0\1\uffff\1\101\1\uffff\1\0\2\uffff\1\107\1"+
        "\uffff\1\114\1\117\1\106\1\113\2\40\1\117\2\123\1\117\1\124\2\60"+
        "\1\124\1\126\1\114\1\111\1\105\1\41\1\uffff\1\104\1\60\14\uffff"+
        "\1\56\10\uffff\1\0\2\uffff\1\110\1\114\1\115\1\124\1\105\1\116\1"+
        "\105\1\uffff\1\123\1\124\1\103\1\125\1\40\1\105\2\uffff\1\127\1"+
        "\111\1\105\1\117\1\122\2\uffff\2\60\1\uffff\1\0\1\124\1\40\1\60"+
        "\1\40\1\60\1\117\1\122\1\123\1\111\1\60\1\120\1\111\1\122\1\105"+
        "\1\116\1\103\1\116\1\105\2\uffff\2\0\1\40\6\uffff\2\40\1\116\1\uffff"+
        "\1\40\2\uffff\1\40\1\105\1\107\1\124\2\60\2\0\3\uffff\1\103\2\uffff"+
        "\1\116\2\60\2\uffff\2\0\1\124\1\60\2\uffff\2\0\1\60\1\uffff\2\0"+
        "\1\uffff\6\0\1\40\1\0\2\uffff\1\0\2\uffff";
    static final String DFA18_maxS =
        "\1\uffff\1\151\1\165\1\151\1\163\1\162\1\151\1\162\1\157\1\162\1"+
        "\145\1\141\1\145\1\156\1\150\1\170\1\163\1\76\1\75\1\174\4\uffff"+
        "\1\71\1\uffff\1\57\3\uffff\1\71\1\uffff\1\uffff\1\172\1\uffff\1"+
        "\uffff\2\uffff\1\147\1\uffff\1\154\1\157\1\146\1\153\1\40\1\156"+
        "\1\157\2\163\1\157\1\164\2\172\1\164\1\166\1\154\1\151\1\145\1\173"+
        "\1\uffff\1\144\1\172\14\uffff\1\71\10\uffff\1\uffff\2\uffff\1\150"+
        "\1\154\1\155\1\164\1\145\1\156\1\145\1\uffff\1\163\1\164\1\143\1"+
        "\165\1\40\1\145\2\uffff\1\167\1\151\1\145\1\157\1\162\2\uffff\2"+
        "\172\1\uffff\1\uffff\1\164\1\40\1\172\1\40\1\172\1\165\1\162\1\163"+
        "\1\151\1\172\1\160\1\154\1\162\1\145\1\156\1\143\1\156\1\145\2\uffff"+
        "\2\uffff\1\40\6\uffff\2\40\1\156\1\uffff\1\40\2\uffff\1\40\1\145"+
        "\1\147\1\164\2\172\2\uffff\3\uffff\1\143\2\uffff\1\156\2\172\2\uffff"+
        "\2\uffff\1\164\1\172\2\uffff\2\uffff\1\172\1\uffff\2\uffff\1\uffff"+
        "\6\uffff\1\40\1\uffff\2\uffff\1\uffff\2\uffff";
    static final String DFA18_acceptS =
        "\24\uffff\1\42\1\43\1\44\1\45\1\uffff\1\47\1\uffff\1\52\1\54\1\55"+
        "\2\uffff\1\63\1\uffff\1\64\1\uffff\1\67\1\70\1\uffff\1\64\23\uffff"+
        "\1\30\2\uffff\1\33\1\34\1\51\1\35\1\53\1\41\1\42\1\43\1\44\1\45"+
        "\1\63\1\46\1\uffff\1\47\1\66\1\50\1\52\1\54\1\55\1\56\1\62\1\uffff"+
        "\1\65\1\67\7\uffff\1\25\6\uffff\1\40\1\37\5\uffff\1\22\1\27\2\uffff"+
        "\1\36\23\uffff\1\31\1\32\3\uffff\1\2\1\24\1\3\1\26\1\4\1\15\3\uffff"+
        "\1\23\1\uffff\1\11\1\12\10\uffff\1\1\1\6\1\5\1\uffff\1\10\1\13\3"+
        "\uffff\1\20\1\21\4\uffff\1\16\1\17\3\uffff\1\14\2\uffff\1\7\10\uffff"+
        "\1\60\1\57\1\uffff\2\61";
    static final String DFA18_specialS =
        "\1\7\36\uffff\1\2\3\uffff\1\3\57\uffff\1\1\34\uffff\1\0\24\uffff"+
        "\1\5\1\26\24\uffff\1\4\1\25\13\uffff\1\10\1\22\4\uffff\1\6\1\21"+
        "\2\uffff\1\12\1\24\1\uffff\1\11\1\23\1\14\1\16\1\13\1\15\1\uffff"+
        "\1\20\2\uffff\1\17\2\uffff}>";
    static final String[] DFA18_transitionS = {
            "\11\45\2\44\2\45\1\44\22\45\1\44\1\45\1\43\1\40\1\17\2\45\1"+
            "\37\1\24\1\25\1\35\1\26\1\27\1\30\1\31\1\32\12\36\2\45\1\21"+
            "\1\33\1\22\2\45\1\20\1\12\1\5\1\6\1\42\1\2\1\7\1\13\1\4\2\42"+
            "\1\3\1\42\1\10\1\11\2\42\1\1\1\14\1\42\1\15\1\42\1\16\3\42\3"+
            "\45\1\41\1\42\1\45\1\20\1\12\1\5\1\6\1\42\1\2\1\7\1\13\1\4\2"+
            "\42\1\3\1\42\1\10\1\11\2\42\1\1\1\14\1\42\1\15\1\42\1\16\3\42"+
            "\1\45\1\23\1\34\uff82\45",
            "\1\46\37\uffff\1\46",
            "\1\51\2\uffff\1\50\34\uffff\1\51\2\uffff\1\50",
            "\1\52\3\uffff\1\53\33\uffff\1\52\3\uffff\1\53",
            "\1\55\4\uffff\1\54\32\uffff\1\55\4\uffff\1\54",
            "\1\56\37\uffff\1\56",
            "\1\60\3\uffff\1\57\33\uffff\1\60\3\uffff\1\57",
            "\1\61\37\uffff\1\61",
            "\1\62\37\uffff\1\62",
            "\1\64\3\uffff\1\63\33\uffff\1\64\3\uffff\1\63",
            "\1\65\37\uffff\1\65",
            "\1\66\37\uffff\1\66",
            "\1\67\37\uffff\1\67",
            "\1\70\37\uffff\1\70",
            "\1\71\37\uffff\1\71",
            "\1\72\7\uffff\1\73\27\uffff\1\72\7\uffff\1\73",
            "\1\74\4\uffff\1\75\32\uffff\1\74\4\uffff\1\75",
            "\1\76\1\77",
            "\1\101",
            "\1\103",
            "",
            "",
            "",
            "",
            "\1\110\2\uffff\12\112",
            "",
            "\1\114\4\uffff\1\110",
            "",
            "",
            "",
            "\1\122\1\uffff\12\112",
            "\60\124\12\123\uffc6\124",
            "",
            "\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "\0\124",
            "",
            "",
            "\1\126\37\uffff\1\126",
            "",
            "\1\127\37\uffff\1\127",
            "\1\130\37\uffff\1\130",
            "\1\131\37\uffff\1\131",
            "\1\132\37\uffff\1\132",
            "\1\133",
            "\1\135\55\uffff\1\134\37\uffff\1\134",
            "\1\136\37\uffff\1\136",
            "\1\137\37\uffff\1\137",
            "\1\140\37\uffff\1\140",
            "\1\141\37\uffff\1\141",
            "\1\142\37\uffff\1\142",
            "\12\47\7\uffff\3\47\1\143\26\47\4\uffff\1\47\1\uffff\3\47\1"+
            "\143\26\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\146\37\uffff\1\146",
            "\1\147\37\uffff\1\147",
            "\1\150\37\uffff\1\150",
            "\1\151\37\uffff\1\151",
            "\1\152\37\uffff\1\152",
            "\1\153\131\uffff\1\154",
            "",
            "\1\155\37\uffff\1\155",
            "\12\47\7\uffff\2\47\1\156\27\47\4\uffff\1\47\1\uffff\2\47\1"+
            "\156\27\47",
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
            "\1\122\1\uffff\12\112",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\60\124\12\160\uffc6\124",
            "",
            "",
            "\1\161\37\uffff\1\161",
            "\1\162\37\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "\1\164\37\uffff\1\164",
            "\1\165\37\uffff\1\165",
            "\1\166\37\uffff\1\166",
            "\1\167\37\uffff\1\167",
            "",
            "\1\170\37\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\1\172\37\uffff\1\172",
            "\1\173\37\uffff\1\173",
            "\1\174",
            "\1\175\37\uffff\1\175",
            "",
            "",
            "\1\176\37\uffff\1\176",
            "\1\177\37\uffff\1\177",
            "\1\u0080\37\uffff\1\u0080",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "\60\124\12\u0085\1\u0086\uffc5\124",
            "\1\u0087\37\uffff\1\u0087",
            "\1\u0088",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u008a",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u008c\5\uffff\1\u008d\31\uffff\1\u008c\5\uffff\1\u008d",
            "\1\u008e\37\uffff\1\u008e",
            "\1\u008f\37\uffff\1\u008f",
            "\1\u0090\37\uffff\1\u0090",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\2\uffff\1\u0094\34\uffff\1\u0093\2\uffff\1\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\37\uffff\1\u009a",
            "",
            "",
            "\60\124\12\u009b\uffc6\124",
            "\60\124\12\u009c\uffc6\124",
            "\1\u009d",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "",
            "\1\u00a1",
            "",
            "",
            "\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\37\uffff\1\u00a4",
            "\1\u00a5\37\uffff\1\u00a5",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\55\124\1\u00a8\uffd2\124",
            "\60\124\12\u00a9\uffc6\124",
            "",
            "",
            "",
            "\1\u00aa\37\uffff\1\u00aa",
            "",
            "",
            "\1\u00ab\37\uffff\1\u00ab",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "",
            "\60\124\2\u00ae\uffce\124",
            "\72\124\1\u00af\uffc5\124",
            "\1\u00b0\37\uffff\1\u00b0",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "",
            "\60\124\12\u00b2\uffc6\124",
            "\60\124\2\u00b3\uffce\124",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "\55\124\1\u00b5\uffd2\124",
            "\60\124\12\u00b6\uffc6\124",
            "",
            "\60\124\4\u00b7\uffcc\124",
            "\56\124\1\u00b8\uffd1\124",
            "\60\124\12\u00b9\uffc6\124",
            "\60\124\12\u00ba\uffc6\124",
            "\47\124\1\u00bb\uffd8\124",
            "\60\124\12\u00bc\uffc6\124",
            "\1\u00be",
            "\60\124\12\u00bf\uffc6\124",
            "",
            "",
            "\47\124\1\u00c0\uffd8\124",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( KEYWORD_44 | KEYWORD_42 | KEYWORD_43 | KEYWORD_41 | KEYWORD_39 | KEYWORD_40 | KEYWORD_34 | KEYWORD_35 | KEYWORD_36 | KEYWORD_37 | KEYWORD_38 | KEYWORD_32 | KEYWORD_33 | KEYWORD_30 | KEYWORD_31 | KEYWORD_28 | KEYWORD_29 | KEYWORD_23 | KEYWORD_24 | KEYWORD_25 | KEYWORD_26 | KEYWORD_27 | KEYWORD_19 | KEYWORD_20 | KEYWORD_21 | KEYWORD_22 | KEYWORD_12 | KEYWORD_13 | KEYWORD_14 | KEYWORD_15 | KEYWORD_16 | KEYWORD_17 | KEYWORD_18 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_11 | RULE_STAR | RULE_INT | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_SIGNED_DOUBLE | RULE_SL_COMMENT | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA18_112 = input.LA(1);

                        s = -1;
                        if ( ((LA18_112>='0' && LA18_112<='9')) ) {s = 133;}

                        else if ( (LA18_112==':') ) {s = 134;}

                        else if ( ((LA18_112>='\u0000' && LA18_112<='/')||(LA18_112>=';' && LA18_112<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA18_83 = input.LA(1);

                        s = -1;
                        if ( ((LA18_83>='0' && LA18_83<='9')) ) {s = 112;}

                        else if ( ((LA18_83>='\u0000' && LA18_83<='/')||(LA18_83>=':' && LA18_83<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA18_31 = input.LA(1);

                        s = -1;
                        if ( ((LA18_31>='0' && LA18_31<='9')) ) {s = 83;}

                        else if ( ((LA18_31>='\u0000' && LA18_31<='/')||(LA18_31>=':' && LA18_31<='\uFFFF')) ) {s = 84;}

                        else s = 37;

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA18_35 = input.LA(1);

                        s = -1;
                        if ( ((LA18_35>='\u0000' && LA18_35<='\uFFFF')) ) {s = 84;}

                        else s = 37;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA18_155 = input.LA(1);

                        s = -1;
                        if ( (LA18_155=='-') ) {s = 168;}

                        else if ( ((LA18_155>='\u0000' && LA18_155<=',')||(LA18_155>='.' && LA18_155<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA18_133 = input.LA(1);

                        s = -1;
                        if ( ((LA18_133>='0' && LA18_133<='9')) ) {s = 155;}

                        else if ( ((LA18_133>='\u0000' && LA18_133<='/')||(LA18_133>=':' && LA18_133<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA18_174 = input.LA(1);

                        s = -1;
                        if ( ((LA18_174>='0' && LA18_174<='9')) ) {s = 178;}

                        else if ( ((LA18_174>='\u0000' && LA18_174<='/')||(LA18_174>=':' && LA18_174<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA18_0 = input.LA(1);

                        s = -1;
                        if ( (LA18_0=='R'||LA18_0=='r') ) {s = 1;}

                        else if ( (LA18_0=='F'||LA18_0=='f') ) {s = 2;}

                        else if ( (LA18_0=='L'||LA18_0=='l') ) {s = 3;}

                        else if ( (LA18_0=='I'||LA18_0=='i') ) {s = 4;}

                        else if ( (LA18_0=='C'||LA18_0=='c') ) {s = 5;}

                        else if ( (LA18_0=='D'||LA18_0=='d') ) {s = 6;}

                        else if ( (LA18_0=='G'||LA18_0=='g') ) {s = 7;}

                        else if ( (LA18_0=='N'||LA18_0=='n') ) {s = 8;}

                        else if ( (LA18_0=='O'||LA18_0=='o') ) {s = 9;}

                        else if ( (LA18_0=='B'||LA18_0=='b') ) {s = 10;}

                        else if ( (LA18_0=='H'||LA18_0=='h') ) {s = 11;}

                        else if ( (LA18_0=='S'||LA18_0=='s') ) {s = 12;}

                        else if ( (LA18_0=='U'||LA18_0=='u') ) {s = 13;}

                        else if ( (LA18_0=='W'||LA18_0=='w') ) {s = 14;}

                        else if ( (LA18_0=='$') ) {s = 15;}

                        else if ( (LA18_0=='A'||LA18_0=='a') ) {s = 16;}

                        else if ( (LA18_0=='<') ) {s = 17;}

                        else if ( (LA18_0=='>') ) {s = 18;}

                        else if ( (LA18_0=='|') ) {s = 19;}

                        else if ( (LA18_0=='(') ) {s = 20;}

                        else if ( (LA18_0==')') ) {s = 21;}

                        else if ( (LA18_0=='+') ) {s = 22;}

                        else if ( (LA18_0==',') ) {s = 23;}

                        else if ( (LA18_0=='-') ) {s = 24;}

                        else if ( (LA18_0=='.') ) {s = 25;}

                        else if ( (LA18_0=='/') ) {s = 26;}

                        else if ( (LA18_0=='=') ) {s = 27;}

                        else if ( (LA18_0=='}') ) {s = 28;}

                        else if ( (LA18_0=='*') ) {s = 29;}

                        else if ( ((LA18_0>='0' && LA18_0<='9')) ) {s = 30;}

                        else if ( (LA18_0=='\'') ) {s = 31;}

                        else if ( (LA18_0=='#') ) {s = 32;}

                        else if ( (LA18_0=='^') ) {s = 33;}

                        else if ( (LA18_0=='E'||(LA18_0>='J' && LA18_0<='K')||LA18_0=='M'||(LA18_0>='P' && LA18_0<='Q')||LA18_0=='T'||LA18_0=='V'||(LA18_0>='X' && LA18_0<='Z')||LA18_0=='_'||LA18_0=='e'||(LA18_0>='j' && LA18_0<='k')||LA18_0=='m'||(LA18_0>='p' && LA18_0<='q')||LA18_0=='t'||LA18_0=='v'||(LA18_0>='x' && LA18_0<='z')) ) {s = 34;}

                        else if ( (LA18_0=='\"') ) {s = 35;}

                        else if ( ((LA18_0>='\t' && LA18_0<='\n')||LA18_0=='\r'||LA18_0==' ') ) {s = 36;}

                        else if ( ((LA18_0>='\u0000' && LA18_0<='\b')||(LA18_0>='\u000B' && LA18_0<='\f')||(LA18_0>='\u000E' && LA18_0<='\u001F')||LA18_0=='!'||(LA18_0>='%' && LA18_0<='&')||(LA18_0>=':' && LA18_0<=';')||(LA18_0>='?' && LA18_0<='@')||(LA18_0>='[' && LA18_0<=']')||LA18_0=='`'||LA18_0=='{'||(LA18_0>='~' && LA18_0<='\uFFFF')) ) {s = 37;}

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA18_168 = input.LA(1);

                        s = -1;
                        if ( ((LA18_168>='0' && LA18_168<='1')) ) {s = 174;}

                        else if ( ((LA18_168>='\u0000' && LA18_168<='/')||(LA18_168>='2' && LA18_168<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA18_181 = input.LA(1);

                        s = -1;
                        if ( ((LA18_181>='0' && LA18_181<='3')) ) {s = 183;}

                        else if ( ((LA18_181>='\u0000' && LA18_181<='/')||(LA18_181>='4' && LA18_181<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA18_178 = input.LA(1);

                        s = -1;
                        if ( (LA18_178=='-') ) {s = 181;}

                        else if ( ((LA18_178>='\u0000' && LA18_178<=',')||(LA18_178>='.' && LA18_178<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA18_185 = input.LA(1);

                        s = -1;
                        if ( (LA18_185=='\'') ) {s = 187;}

                        else if ( ((LA18_185>='\u0000' && LA18_185<='&')||(LA18_185>='(' && LA18_185<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA18_183 = input.LA(1);

                        s = -1;
                        if ( ((LA18_183>='0' && LA18_183<='9')) ) {s = 185;}

                        else if ( ((LA18_183>='\u0000' && LA18_183<='/')||(LA18_183>=':' && LA18_183<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA18_186 = input.LA(1);

                        s = -1;
                        if ( ((LA18_186>='0' && LA18_186<='9')) ) {s = 188;}

                        else if ( ((LA18_186>='\u0000' && LA18_186<='/')||(LA18_186>=':' && LA18_186<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA18_184 = input.LA(1);

                        s = -1;
                        if ( ((LA18_184>='0' && LA18_184<='9')) ) {s = 186;}

                        else if ( ((LA18_184>='\u0000' && LA18_184<='/')||(LA18_184>=':' && LA18_184<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA18_191 = input.LA(1);

                        s = -1;
                        if ( (LA18_191=='\'') ) {s = 192;}

                        else if ( ((LA18_191>='\u0000' && LA18_191<='&')||(LA18_191>='(' && LA18_191<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA18_188 = input.LA(1);

                        s = -1;
                        if ( ((LA18_188>='0' && LA18_188<='9')) ) {s = 191;}

                        else if ( ((LA18_188>='\u0000' && LA18_188<='/')||(LA18_188>=':' && LA18_188<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA18_175 = input.LA(1);

                        s = -1;
                        if ( ((LA18_175>='0' && LA18_175<='1')) ) {s = 179;}

                        else if ( ((LA18_175>='\u0000' && LA18_175<='/')||(LA18_175>='2' && LA18_175<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA18_169 = input.LA(1);

                        s = -1;
                        if ( (LA18_169==':') ) {s = 175;}

                        else if ( ((LA18_169>='\u0000' && LA18_169<='9')||(LA18_169>=';' && LA18_169<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA18_182 = input.LA(1);

                        s = -1;
                        if ( (LA18_182=='.') ) {s = 184;}

                        else if ( ((LA18_182>='\u0000' && LA18_182<='-')||(LA18_182>='/' && LA18_182<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA18_179 = input.LA(1);

                        s = -1;
                        if ( ((LA18_179>='0' && LA18_179<='9')) ) {s = 182;}

                        else if ( ((LA18_179>='\u0000' && LA18_179<='/')||(LA18_179>=':' && LA18_179<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA18_156 = input.LA(1);

                        s = -1;
                        if ( ((LA18_156>='0' && LA18_156<='9')) ) {s = 169;}

                        else if ( ((LA18_156>='\u0000' && LA18_156<='/')||(LA18_156>=':' && LA18_156<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA18_134 = input.LA(1);

                        s = -1;
                        if ( ((LA18_134>='0' && LA18_134<='9')) ) {s = 156;}

                        else if ( ((LA18_134>='\u0000' && LA18_134<='/')||(LA18_134>=':' && LA18_134<='\uFFFF')) ) {s = 84;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 18, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}