package com.jaspersoft.studio.data.parser.antlr.lexer;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSqlLexer extends Lexer {
    public static final int RULE_ID=78;
    public static final int RULE_DATE=73;
    public static final int RULE_ANY_OTHER=83;
    public static final int KEYWORD_19=45;
    public static final int KEYWORD_56=18;
    public static final int KEYWORD_55=17;
    public static final int KEYWORD_17=70;
    public static final int KEYWORD_54=16;
    public static final int KEYWORD_18=44;
    public static final int KEYWORD_53=15;
    public static final int KEYWORD_15=68;
    public static final int KEYWORD_52=14;
    public static final int KEYWORD_16=69;
    public static final int KEYWORD_51=13;
    public static final int KEYWORD_13=66;
    public static final int KEYWORD_50=23;
    public static final int KEYWORD_14=67;
    public static final int KEYWORD_11=64;
    public static final int KEYWORD_12=65;
    public static final int EOF=-1;
    public static final int KEYWORD_10=63;
    public static final int RULE_STRINGID=77;
    public static final int KEYWORD_59=11;
    public static final int KEYWORD_58=20;
    public static final int KEYWORD_57=19;
    public static final int KEYWORD_6=59;
    public static final int KEYWORD_7=60;
    public static final int KEYWORD_8=61;
    public static final int KEYWORD_9=62;
    public static final int RULE_TIME=74;
    public static final int KEYWORD_65=5;
    public static final int KEYWORD_28=39;
    public static final int KEYWORD_64=8;
    public static final int KEYWORD_29=40;
    public static final int RULE_SIGNED_DOUBLE=76;
    public static final int RULE_TIMESTAMP=75;
    public static final int RULE_INT=72;
    public static final int KEYWORD_67=4;
    public static final int KEYWORD_66=6;
    public static final int KEYWORD_24=50;
    public static final int KEYWORD_61=9;
    public static final int KEYWORD_25=51;
    public static final int KEYWORD_60=12;
    public static final int KEYWORD_26=52;
    public static final int KEYWORD_63=7;
    public static final int KEYWORD_27=53;
    public static final int KEYWORD_62=10;
    public static final int KEYWORD_20=46;
    public static final int KEYWORD_21=47;
    public static final int KEYWORD_22=48;
    public static final int KEYWORD_23=49;
    public static final int KEYWORD_1=54;
    public static final int KEYWORD_30=41;
    public static final int KEYWORD_5=58;
    public static final int KEYWORD_34=35;
    public static final int KEYWORD_4=57;
    public static final int KEYWORD_33=34;
    public static final int KEYWORD_3=56;
    public static final int KEYWORD_32=43;
    public static final int KEYWORD_2=55;
    public static final int KEYWORD_31=42;
    public static final int KEYWORD_38=28;
    public static final int RULE_SL_COMMENT=79;
    public static final int KEYWORD_37=38;
    public static final int KEYWORD_36=37;
    public static final int KEYWORD_35=36;
    public static final int RULE_ML_COMMENT=81;
    public static final int KEYWORD_39=29;
    public static final int RULE_STRING=80;
    public static final int RULE_STAR=71;
    public static final int KEYWORD_41=31;
    public static final int KEYWORD_40=30;
    public static final int KEYWORD_43=33;
    public static final int KEYWORD_42=32;
    public static final int KEYWORD_45=25;
    public static final int KEYWORD_44=24;
    public static final int RULE_WS=82;
    public static final int KEYWORD_47=27;
    public static final int KEYWORD_46=26;
    public static final int KEYWORD_49=22;
    public static final int KEYWORD_48=21;

    // delegates
    // delegators

    public InternalSqlLexer() {;} 
    public InternalSqlLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalSqlLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g"; }

    // $ANTLR start "KEYWORD_67"
    public final void mKEYWORD_67() throws RecognitionException {
        try {
            int _type = KEYWORD_67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:19:12: ( ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:19:14: ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_67"

    // $ANTLR start "KEYWORD_65"
    public final void mKEYWORD_65() throws RecognitionException {
        try {
            int _type = KEYWORD_65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:21:12: ( ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:21:14: ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_65"

    // $ANTLR start "KEYWORD_66"
    public final void mKEYWORD_66() throws RecognitionException {
        try {
            int _type = KEYWORD_66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:23:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:23:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' ) ' ' ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_66"

    // $ANTLR start "KEYWORD_63"
    public final void mKEYWORD_63() throws RecognitionException {
        try {
            int _type = KEYWORD_63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:25:12: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:25:14: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_63"

    // $ANTLR start "KEYWORD_64"
    public final void mKEYWORD_64() throws RecognitionException {
        try {
            int _type = KEYWORD_64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:27:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:27:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_64"

    // $ANTLR start "KEYWORD_61"
    public final void mKEYWORD_61() throws RecognitionException {
        try {
            int _type = KEYWORD_61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:29:12: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:29:14: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_61"

    // $ANTLR start "KEYWORD_62"
    public final void mKEYWORD_62() throws RecognitionException {
        try {
            int _type = KEYWORD_62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:31:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:31:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_62"

    // $ANTLR start "KEYWORD_59"
    public final void mKEYWORD_59() throws RecognitionException {
        try {
            int _type = KEYWORD_59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:33:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:33:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_59"

    // $ANTLR start "KEYWORD_60"
    public final void mKEYWORD_60() throws RecognitionException {
        try {
            int _type = KEYWORD_60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:35:12: ( '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:35:14: '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
            {
            match('['); 
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

            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_60"

    // $ANTLR start "KEYWORD_51"
    public final void mKEYWORD_51() throws RecognitionException {
        try {
            int _type = KEYWORD_51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:37:12: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:37:14: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
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

            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_51"

    // $ANTLR start "KEYWORD_52"
    public final void mKEYWORD_52() throws RecognitionException {
        try {
            int _type = KEYWORD_52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:39:12: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:39:14: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_52"

    // $ANTLR start "KEYWORD_53"
    public final void mKEYWORD_53() throws RecognitionException {
        try {
            int _type = KEYWORD_53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:41:12: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:41:14: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_53"

    // $ANTLR start "KEYWORD_54"
    public final void mKEYWORD_54() throws RecognitionException {
        try {
            int _type = KEYWORD_54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:43:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:43:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_54"

    // $ANTLR start "KEYWORD_55"
    public final void mKEYWORD_55() throws RecognitionException {
        try {
            int _type = KEYWORD_55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:45:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:45:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
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

            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
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
    // $ANTLR end "KEYWORD_55"

    // $ANTLR start "KEYWORD_56"
    public final void mKEYWORD_56() throws RecognitionException {
        try {
            int _type = KEYWORD_56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:47:12: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:47:14: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_56"

    // $ANTLR start "KEYWORD_57"
    public final void mKEYWORD_57() throws RecognitionException {
        try {
            int _type = KEYWORD_57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:49:12: ( '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:49:14: '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
            {
            match('['); 
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
    // $ANTLR end "KEYWORD_57"

    // $ANTLR start "KEYWORD_58"
    public final void mKEYWORD_58() throws RecognitionException {
        try {
            int _type = KEYWORD_58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:51:12: ( '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:51:14: '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
            {
            match('['); 
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

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_58"

    // $ANTLR start "KEYWORD_48"
    public final void mKEYWORD_48() throws RecognitionException {
        try {
            int _type = KEYWORD_48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:53:12: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:53:14: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_48"

    // $ANTLR start "KEYWORD_49"
    public final void mKEYWORD_49() throws RecognitionException {
        try {
            int _type = KEYWORD_49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:55:12: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:55:14: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_49"

    // $ANTLR start "KEYWORD_50"
    public final void mKEYWORD_50() throws RecognitionException {
        try {
            int _type = KEYWORD_50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:57:12: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:57:14: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_50"

    // $ANTLR start "KEYWORD_44"
    public final void mKEYWORD_44() throws RecognitionException {
        try {
            int _type = KEYWORD_44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:59:12: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:59:14: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
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

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
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
    // $ANTLR end "KEYWORD_44"

    // $ANTLR start "KEYWORD_45"
    public final void mKEYWORD_45() throws RecognitionException {
        try {
            int _type = KEYWORD_45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:61:12: ( ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:61:14: ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "KEYWORD_45"

    // $ANTLR start "KEYWORD_46"
    public final void mKEYWORD_46() throws RecognitionException {
        try {
            int _type = KEYWORD_46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:63:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:63:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_46"

    // $ANTLR start "KEYWORD_47"
    public final void mKEYWORD_47() throws RecognitionException {
        try {
            int _type = KEYWORD_47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:65:12: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:65:14: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_47"

    // $ANTLR start "KEYWORD_38"
    public final void mKEYWORD_38() throws RecognitionException {
        try {
            int _type = KEYWORD_38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:67:12: ( ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:67:14: ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
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

            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
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
    // $ANTLR end "KEYWORD_38"

    // $ANTLR start "KEYWORD_39"
    public final void mKEYWORD_39() throws RecognitionException {
        try {
            int _type = KEYWORD_39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:69:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:69:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']'
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

            match(']'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:71:12: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:71:14: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
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

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
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
    // $ANTLR end "KEYWORD_40"

    // $ANTLR start "KEYWORD_41"
    public final void mKEYWORD_41() throws RecognitionException {
        try {
            int _type = KEYWORD_41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:73:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:73:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_41"

    // $ANTLR start "KEYWORD_42"
    public final void mKEYWORD_42() throws RecognitionException {
        try {
            int _type = KEYWORD_42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:75:12: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:75:14: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_42"

    // $ANTLR start "KEYWORD_43"
    public final void mKEYWORD_43() throws RecognitionException {
        try {
            int _type = KEYWORD_43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:77:12: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:77:14: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_43"

    // $ANTLR start "KEYWORD_33"
    public final void mKEYWORD_33() throws RecognitionException {
        try {
            int _type = KEYWORD_33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:79:12: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:79:14: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
    // $ANTLR end "KEYWORD_33"

    // $ANTLR start "KEYWORD_34"
    public final void mKEYWORD_34() throws RecognitionException {
        try {
            int _type = KEYWORD_34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:81:12: ( ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:81:14: ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' )
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
    // $ANTLR end "KEYWORD_34"

    // $ANTLR start "KEYWORD_35"
    public final void mKEYWORD_35() throws RecognitionException {
        try {
            int _type = KEYWORD_35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:83:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:83:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:85:12: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:85:14: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_36"

    // $ANTLR start "KEYWORD_37"
    public final void mKEYWORD_37() throws RecognitionException {
        try {
            int _type = KEYWORD_37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:87:12: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:87:14: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
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

    // $ANTLR start "KEYWORD_28"
    public final void mKEYWORD_28() throws RecognitionException {
        try {
            int _type = KEYWORD_28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:89:12: ( '$' ( 'P' | 'p' ) '!' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:89:14: '$' ( 'P' | 'p' ) '!'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:91:12: ( ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:91:14: ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
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
    // $ANTLR end "KEYWORD_29"

    // $ANTLR start "KEYWORD_30"
    public final void mKEYWORD_30() throws RecognitionException {
        try {
            int _type = KEYWORD_30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:93:12: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:93:14: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "KEYWORD_30"

    // $ANTLR start "KEYWORD_31"
    public final void mKEYWORD_31() throws RecognitionException {
        try {
            int _type = KEYWORD_31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:95:12: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:95:14: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_31"

    // $ANTLR start "KEYWORD_32"
    public final void mKEYWORD_32() throws RecognitionException {
        try {
            int _type = KEYWORD_32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:97:12: ( ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:97:14: ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
    // $ANTLR end "KEYWORD_32"

    // $ANTLR start "KEYWORD_18"
    public final void mKEYWORD_18() throws RecognitionException {
        try {
            int _type = KEYWORD_18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:99:12: ( '$' ( 'P' | 'p' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:99:14: '$' ( 'P' | 'p' )
            {
            match('$'); 
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
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
    // $ANTLR end "KEYWORD_18"

    // $ANTLR start "KEYWORD_19"
    public final void mKEYWORD_19() throws RecognitionException {
        try {
            int _type = KEYWORD_19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:101:12: ( '$' ( 'X' | 'x' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:101:14: '$' ( 'X' | 'x' )
            {
            match('$'); 
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
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
    // $ANTLR end "KEYWORD_19"

    // $ANTLR start "KEYWORD_20"
    public final void mKEYWORD_20() throws RecognitionException {
        try {
            int _type = KEYWORD_20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:103:12: ( '<' '=' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:103:14: '<' '='
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
    // $ANTLR end "KEYWORD_20"

    // $ANTLR start "KEYWORD_21"
    public final void mKEYWORD_21() throws RecognitionException {
        try {
            int _type = KEYWORD_21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:105:12: ( '<' '>' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:105:14: '<' '>'
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
    // $ANTLR end "KEYWORD_21"

    // $ANTLR start "KEYWORD_22"
    public final void mKEYWORD_22() throws RecognitionException {
        try {
            int _type = KEYWORD_22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:107:12: ( '>' '=' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:107:14: '>' '='
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
    // $ANTLR end "KEYWORD_22"

    // $ANTLR start "KEYWORD_23"
    public final void mKEYWORD_23() throws RecognitionException {
        try {
            int _type = KEYWORD_23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:109:12: ( ( 'A' | 'a' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:109:14: ( 'A' | 'a' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_23"

    // $ANTLR start "KEYWORD_24"
    public final void mKEYWORD_24() throws RecognitionException {
        try {
            int _type = KEYWORD_24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:111:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:111:14: ( 'I' | 'i' ) ( 'N' | 'n' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:113:12: ( ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:113:14: ( 'O' | 'o' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_25"

    // $ANTLR start "KEYWORD_26"
    public final void mKEYWORD_26() throws RecognitionException {
        try {
            int _type = KEYWORD_26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:115:12: ( ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:115:14: ( 'O' | 'o' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_26"

    // $ANTLR start "KEYWORD_27"
    public final void mKEYWORD_27() throws RecognitionException {
        try {
            int _type = KEYWORD_27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:117:12: ( '|' '|' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:117:14: '|' '|'
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
    // $ANTLR end "KEYWORD_27"

    // $ANTLR start "KEYWORD_1"
    public final void mKEYWORD_1() throws RecognitionException {
        try {
            int _type = KEYWORD_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:119:11: ( '\"' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:119:13: '\"'
            {
            match('\"'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:121:11: ( '\\'' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:121:13: '\\''
            {
            match('\''); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:123:11: ( '(' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:123:13: '('
            {
            match('('); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:125:11: ( ')' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:125:13: ')'
            {
            match(')'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:127:11: ( '+' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:127:13: '+'
            {
            match('+'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:129:11: ( ',' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:129:13: ','
            {
            match(','); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:131:11: ( '-' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:131:13: '-'
            {
            match('-'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:133:11: ( '.' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:133:13: '.'
            {
            match('.'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:135:11: ( '/' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:135:13: '/'
            {
            match('/'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:137:12: ( '<' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:137:14: '<'
            {
            match('<'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:139:12: ( '=' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:139:14: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_11"

    // $ANTLR start "KEYWORD_12"
    public final void mKEYWORD_12() throws RecognitionException {
        try {
            int _type = KEYWORD_12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:141:12: ( '>' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:141:14: '>'
            {
            match('>'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:143:12: ( '[' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:143:14: '['
            {
            match('['); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:145:12: ( ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:145:14: ']'
            {
            match(']'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:147:12: ( '`' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:147:14: '`'
            {
            match('`'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:149:12: ( '{' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:149:14: '{'
            {
            match('{'); 

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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:151:12: ( '}' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:151:14: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_17"

    // $ANTLR start "RULE_STAR"
    public final void mRULE_STAR() throws RecognitionException {
        try {
            int _type = RULE_STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:155:11: ( '*' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:155:13: '*'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:157:10: ( ( '-' )? ( '0' .. '9' )+ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:157:12: ( '-' )? ( '0' .. '9' )+
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:157:12: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:157:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:157:17: ( '0' .. '9' )+
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
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:157:18: '0' .. '9'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:159:16: ( RULE_DATE ' ' RULE_TIME )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:159:18: RULE_DATE ' ' RULE_TIME
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:161:11: ( '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\'' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:161:13: '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\''
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:163:11: ( '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '1' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\'' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:163:13: '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '1' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\''
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:20: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:22: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:22: ( '-' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:22: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:27: ( '0' .. '9' )+
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
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:28: '0' .. '9'
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

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:39: ( '.' ( '0' .. '9' )+ )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:40: '.' ( '0' .. '9' )+
                    {
                    match('.'); 
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:44: ( '0' .. '9' )+
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:45: '0' .. '9'
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

    // $ANTLR start "RULE_STRINGID"
    public final void mRULE_STRINGID() throws RecognitionException {
        try {
            int _type = RULE_STRINGID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:167:15: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:167:17: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:167:41: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='Z')||LA7_0=='_'||(LA7_0>='a' && LA7_0<='z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:
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
            	    break loop7;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRINGID"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:169:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '#' | '-' )+ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:169:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '#' | '-' )+
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:169:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '#' | '-' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='#'||LA8_0=='-'||(LA8_0>='0' && LA8_0<='9')||(LA8_0>='A' && LA8_0<='Z')||LA8_0=='_'||(LA8_0>='a' && LA8_0<='z')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:
            	    {
            	    if ( input.LA(1)=='#'||input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:17: ( ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:19: ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:19: ( '--' | '#' | '//' )
            int alt9=3;
            switch ( input.LA(1) ) {
            case '-':
                {
                alt9=1;
                }
                break;
            case '#':
                {
                alt9=2;
                }
                break;
            case '/':
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:20: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:25: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:29: '//'
                    {
                    match("//"); 


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:35: (~ ( ( '\\n' | '\\r' ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:35: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop10;
                }
            } while (true);

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:51: ( ( '\\r' )? '\\n' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\n'||LA12_0=='\r') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:52: ( '\\r' )? '\\n'
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:52: ( '\\r' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='\r') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:52: '\\r'
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

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:66: ~ ( ( '\\\\' | '\"' ) )
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
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:137: ~ ( ( '\\\\' | '\\'' ) )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:175:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:175:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:175:24: ( options {greedy=false; } : . )*
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
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:175:52: .
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:177:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:177:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:177:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:179:16: ( . )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:179:18: .
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
        // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:8: ( KEYWORD_67 | KEYWORD_65 | KEYWORD_66 | KEYWORD_63 | KEYWORD_64 | KEYWORD_61 | KEYWORD_62 | KEYWORD_59 | KEYWORD_60 | KEYWORD_51 | KEYWORD_52 | KEYWORD_53 | KEYWORD_54 | KEYWORD_55 | KEYWORD_56 | KEYWORD_57 | KEYWORD_58 | KEYWORD_48 | KEYWORD_49 | KEYWORD_50 | KEYWORD_44 | KEYWORD_45 | KEYWORD_46 | KEYWORD_47 | KEYWORD_38 | KEYWORD_39 | KEYWORD_40 | KEYWORD_41 | KEYWORD_42 | KEYWORD_43 | KEYWORD_33 | KEYWORD_34 | KEYWORD_35 | KEYWORD_36 | KEYWORD_37 | KEYWORD_28 | KEYWORD_29 | KEYWORD_30 | KEYWORD_31 | KEYWORD_32 | KEYWORD_18 | KEYWORD_19 | KEYWORD_20 | KEYWORD_21 | KEYWORD_22 | KEYWORD_23 | KEYWORD_24 | KEYWORD_25 | KEYWORD_26 | KEYWORD_27 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_11 | KEYWORD_12 | KEYWORD_13 | KEYWORD_14 | KEYWORD_15 | KEYWORD_16 | KEYWORD_17 | RULE_STAR | RULE_INT | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_SIGNED_DOUBLE | RULE_STRINGID | RULE_ID | RULE_SL_COMMENT | RULE_STRING | RULE_ML_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt18=80;
        alt18 = dfa18.predict(input);
        switch (alt18) {
            case 1 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:10: KEYWORD_67
                {
                mKEYWORD_67(); 

                }
                break;
            case 2 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:21: KEYWORD_65
                {
                mKEYWORD_65(); 

                }
                break;
            case 3 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:32: KEYWORD_66
                {
                mKEYWORD_66(); 

                }
                break;
            case 4 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:43: KEYWORD_63
                {
                mKEYWORD_63(); 

                }
                break;
            case 5 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:54: KEYWORD_64
                {
                mKEYWORD_64(); 

                }
                break;
            case 6 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:65: KEYWORD_61
                {
                mKEYWORD_61(); 

                }
                break;
            case 7 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:76: KEYWORD_62
                {
                mKEYWORD_62(); 

                }
                break;
            case 8 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:87: KEYWORD_59
                {
                mKEYWORD_59(); 

                }
                break;
            case 9 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:98: KEYWORD_60
                {
                mKEYWORD_60(); 

                }
                break;
            case 10 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:109: KEYWORD_51
                {
                mKEYWORD_51(); 

                }
                break;
            case 11 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:120: KEYWORD_52
                {
                mKEYWORD_52(); 

                }
                break;
            case 12 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:131: KEYWORD_53
                {
                mKEYWORD_53(); 

                }
                break;
            case 13 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:142: KEYWORD_54
                {
                mKEYWORD_54(); 

                }
                break;
            case 14 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:153: KEYWORD_55
                {
                mKEYWORD_55(); 

                }
                break;
            case 15 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:164: KEYWORD_56
                {
                mKEYWORD_56(); 

                }
                break;
            case 16 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:175: KEYWORD_57
                {
                mKEYWORD_57(); 

                }
                break;
            case 17 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:186: KEYWORD_58
                {
                mKEYWORD_58(); 

                }
                break;
            case 18 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:197: KEYWORD_48
                {
                mKEYWORD_48(); 

                }
                break;
            case 19 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:208: KEYWORD_49
                {
                mKEYWORD_49(); 

                }
                break;
            case 20 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:219: KEYWORD_50
                {
                mKEYWORD_50(); 

                }
                break;
            case 21 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:230: KEYWORD_44
                {
                mKEYWORD_44(); 

                }
                break;
            case 22 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:241: KEYWORD_45
                {
                mKEYWORD_45(); 

                }
                break;
            case 23 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:252: KEYWORD_46
                {
                mKEYWORD_46(); 

                }
                break;
            case 24 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:263: KEYWORD_47
                {
                mKEYWORD_47(); 

                }
                break;
            case 25 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:274: KEYWORD_38
                {
                mKEYWORD_38(); 

                }
                break;
            case 26 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:285: KEYWORD_39
                {
                mKEYWORD_39(); 

                }
                break;
            case 27 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:296: KEYWORD_40
                {
                mKEYWORD_40(); 

                }
                break;
            case 28 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:307: KEYWORD_41
                {
                mKEYWORD_41(); 

                }
                break;
            case 29 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:318: KEYWORD_42
                {
                mKEYWORD_42(); 

                }
                break;
            case 30 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:329: KEYWORD_43
                {
                mKEYWORD_43(); 

                }
                break;
            case 31 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:340: KEYWORD_33
                {
                mKEYWORD_33(); 

                }
                break;
            case 32 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:351: KEYWORD_34
                {
                mKEYWORD_34(); 

                }
                break;
            case 33 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:362: KEYWORD_35
                {
                mKEYWORD_35(); 

                }
                break;
            case 34 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:373: KEYWORD_36
                {
                mKEYWORD_36(); 

                }
                break;
            case 35 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:384: KEYWORD_37
                {
                mKEYWORD_37(); 

                }
                break;
            case 36 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:395: KEYWORD_28
                {
                mKEYWORD_28(); 

                }
                break;
            case 37 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:406: KEYWORD_29
                {
                mKEYWORD_29(); 

                }
                break;
            case 38 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:417: KEYWORD_30
                {
                mKEYWORD_30(); 

                }
                break;
            case 39 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:428: KEYWORD_31
                {
                mKEYWORD_31(); 

                }
                break;
            case 40 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:439: KEYWORD_32
                {
                mKEYWORD_32(); 

                }
                break;
            case 41 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:450: KEYWORD_18
                {
                mKEYWORD_18(); 

                }
                break;
            case 42 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:461: KEYWORD_19
                {
                mKEYWORD_19(); 

                }
                break;
            case 43 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:472: KEYWORD_20
                {
                mKEYWORD_20(); 

                }
                break;
            case 44 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:483: KEYWORD_21
                {
                mKEYWORD_21(); 

                }
                break;
            case 45 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:494: KEYWORD_22
                {
                mKEYWORD_22(); 

                }
                break;
            case 46 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:505: KEYWORD_23
                {
                mKEYWORD_23(); 

                }
                break;
            case 47 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:516: KEYWORD_24
                {
                mKEYWORD_24(); 

                }
                break;
            case 48 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:527: KEYWORD_25
                {
                mKEYWORD_25(); 

                }
                break;
            case 49 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:538: KEYWORD_26
                {
                mKEYWORD_26(); 

                }
                break;
            case 50 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:549: KEYWORD_27
                {
                mKEYWORD_27(); 

                }
                break;
            case 51 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:560: KEYWORD_1
                {
                mKEYWORD_1(); 

                }
                break;
            case 52 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:570: KEYWORD_2
                {
                mKEYWORD_2(); 

                }
                break;
            case 53 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:580: KEYWORD_3
                {
                mKEYWORD_3(); 

                }
                break;
            case 54 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:590: KEYWORD_4
                {
                mKEYWORD_4(); 

                }
                break;
            case 55 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:600: KEYWORD_5
                {
                mKEYWORD_5(); 

                }
                break;
            case 56 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:610: KEYWORD_6
                {
                mKEYWORD_6(); 

                }
                break;
            case 57 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:620: KEYWORD_7
                {
                mKEYWORD_7(); 

                }
                break;
            case 58 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:630: KEYWORD_8
                {
                mKEYWORD_8(); 

                }
                break;
            case 59 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:640: KEYWORD_9
                {
                mKEYWORD_9(); 

                }
                break;
            case 60 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:650: KEYWORD_10
                {
                mKEYWORD_10(); 

                }
                break;
            case 61 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:661: KEYWORD_11
                {
                mKEYWORD_11(); 

                }
                break;
            case 62 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:672: KEYWORD_12
                {
                mKEYWORD_12(); 

                }
                break;
            case 63 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:683: KEYWORD_13
                {
                mKEYWORD_13(); 

                }
                break;
            case 64 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:694: KEYWORD_14
                {
                mKEYWORD_14(); 

                }
                break;
            case 65 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:705: KEYWORD_15
                {
                mKEYWORD_15(); 

                }
                break;
            case 66 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:716: KEYWORD_16
                {
                mKEYWORD_16(); 

                }
                break;
            case 67 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:727: KEYWORD_17
                {
                mKEYWORD_17(); 

                }
                break;
            case 68 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:738: RULE_STAR
                {
                mRULE_STAR(); 

                }
                break;
            case 69 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:748: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 70 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:757: RULE_TIMESTAMP
                {
                mRULE_TIMESTAMP(); 

                }
                break;
            case 71 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:772: RULE_DATE
                {
                mRULE_DATE(); 

                }
                break;
            case 72 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:782: RULE_TIME
                {
                mRULE_TIME(); 

                }
                break;
            case 73 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:792: RULE_SIGNED_DOUBLE
                {
                mRULE_SIGNED_DOUBLE(); 

                }
                break;
            case 74 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:811: RULE_STRINGID
                {
                mRULE_STRINGID(); 

                }
                break;
            case 75 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:825: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 76 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:833: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 77 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:849: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 78 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:861: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 79 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:877: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 80 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:885: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA18_eotS =
        "\1\uffff\6\55\1\71\12\55\1\52\1\55\1\117\1\121\1\52\1\124\1\126"+
        "\4\uffff\1\134\1\uffff\1\141\6\uffff\1\151\1\55\1\56\2\uffff\2\55"+
        "\2\uffff\5\55\1\165\2\55\3\uffff\4\55\1\177\1\u0080\10\55\1\u008a"+
        "\1\uffff\2\55\1\u008f\16\uffff\1\56\1\uffff\1\151\14\uffff\1\56"+
        "\1\uffff\6\55\1\uffff\2\55\1\uffff\2\55\1\uffff\6\55\2\uffff\10"+
        "\55\2\uffff\1\u00ad\1\u00ae\1\u00af\1\u00b0\2\uffff\2\55\1\u00b5"+
        "\1\55\1\u00b8\1\u00b9\1\uffff\2\55\1\uffff\3\55\1\uffff\2\55\1\u00c7"+
        "\7\55\1\u00cf\3\55\6\uffff\1\55\10\uffff\2\55\3\uffff\1\55\1\u00d9"+
        "\1\55\1\uffff\2\55\1\uffff\4\55\1\u00e2\2\55\1\uffff\1\u00e5\1\u00e6"+
        "\1\u00e7\4\uffff\2\55\3\uffff\2\55\1\uffff\1\55\1\uffff\1\u00f0"+
        "\1\uffff\1\u00f1\1\u00f2\5\uffff\2\55\1\uffff\1\u00f9\1\55\1\u00fb"+
        "\5\uffff\1\55\1\u00ff\1\u0101\2\uffff\1\u0102\3\uffff\1\u0105\13"+
        "\uffff\1\u010c\6\uffff";
    static final String DFA18_eofS =
        "\u0111\uffff";
    static final String DFA18_minS =
        "\1\0\6\43\1\102\12\43\1\120\1\43\2\75\1\174\2\0\4\uffff\1\43\1\uffff"+
        "\1\52\6\uffff\2\43\1\0\2\uffff\2\43\2\uffff\4\43\1\40\3\43\1\105"+
        "\2\uffff\16\43\1\41\1\uffff\3\43\10\uffff\1\0\5\uffff\1\0\1\uffff"+
        "\1\43\14\uffff\1\0\1\uffff\6\43\1\116\2\43\1\uffff\1\40\1\43\1\124"+
        "\6\43\2\uffff\10\43\2\uffff\4\43\1\uffff\1\0\1\43\1\40\1\43\1\40"+
        "\2\43\1\117\2\43\1\102\3\43\1\127\16\43\4\uffff\2\0\1\40\10\uffff"+
        "\1\40\1\43\3\uffff\2\43\1\40\1\105\2\43\1\uffff\1\40\1\43\1\40\4"+
        "\43\1\uffff\3\43\2\0\2\uffff\2\43\2\uffff\1\105\2\43\1\uffff\1\43"+
        "\1\uffff\1\43\1\uffff\2\43\3\uffff\2\0\2\43\1\116\3\43\3\uffff\2"+
        "\0\2\43\1\135\2\uffff\1\43\1\uffff\2\0\1\43\4\uffff\2\0\1\uffff"+
        "\4\0\1\40\1\0\2\uffff\1\0\2\uffff";
    static final String DFA18_maxS =
        "\1\uffff\6\172\1\147\12\172\1\170\1\172\1\76\1\75\1\174\2\uffff"+
        "\4\uffff\1\172\1\uffff\1\57\6\uffff\2\172\1\uffff\2\uffff\2\172"+
        "\2\uffff\10\172\1\145\2\uffff\16\172\1\41\1\uffff\3\172\10\uffff"+
        "\1\uffff\5\uffff\1\uffff\1\uffff\1\172\14\uffff\1\uffff\1\uffff"+
        "\6\172\1\156\2\172\1\uffff\2\172\1\164\6\172\2\uffff\10\172\2\uffff"+
        "\4\172\1\uffff\1\uffff\6\172\1\165\2\172\1\154\3\172\1\167\16\172"+
        "\4\uffff\2\uffff\1\172\10\uffff\2\172\3\uffff\3\172\1\145\2\172"+
        "\1\uffff\7\172\1\uffff\3\172\2\uffff\2\uffff\2\172\2\uffff\1\145"+
        "\2\172\1\uffff\1\172\1\uffff\1\172\1\uffff\2\172\3\uffff\2\uffff"+
        "\2\172\1\156\3\172\3\uffff\2\uffff\2\172\1\135\2\uffff\1\172\1\uffff"+
        "\2\uffff\1\172\4\uffff\2\uffff\1\uffff\4\uffff\1\40\1\uffff\2\uffff"+
        "\1\uffff\2\uffff";
    static final String DFA18_acceptS =
        "\31\uffff\1\65\1\66\1\67\1\70\1\uffff\1\72\1\uffff\1\75\1\100\1"+
        "\101\1\102\1\103\1\104\3\uffff\1\117\1\120\2\uffff\1\112\1\113\11"+
        "\uffff\1\21\1\77\17\uffff\1\52\3\uffff\1\53\1\54\1\74\1\55\1\76"+
        "\1\62\1\115\1\63\1\uffff\1\64\1\65\1\66\1\67\1\70\1\uffff\1\71\1"+
        "\uffff\1\72\1\114\1\116\1\73\1\75\1\100\1\101\1\102\1\103\1\104"+
        "\1\111\1\105\1\uffff\1\117\11\uffff\1\57\11\uffff\1\61\1\60\10\uffff"+
        "\1\44\1\51\4\uffff\1\56\35\uffff\1\45\1\46\1\47\1\50\3\uffff\1\2"+
        "\1\40\1\3\1\32\1\41\1\42\1\4\1\24\2\uffff\1\5\1\15\1\27\6\uffff"+
        "\1\37\7\uffff\1\43\5\uffff\1\1\1\7\2\uffff\1\34\1\6\3\uffff\1\14"+
        "\1\uffff\1\17\1\uffff\1\31\2\uffff\1\33\1\35\1\36\10\uffff\1\25"+
        "\1\26\1\30\5\uffff\1\12\1\22\1\uffff\1\23\3\uffff\1\16\1\11\1\20"+
        "\1\13\2\uffff\1\10\6\uffff\1\107\1\106\1\uffff\2\110";
    static final String DFA18_specialS =
        "\1\23\26\uffff\1\31\1\27\17\uffff\1\24\54\uffff\1\26\5\uffff\1\14"+
        "\16\uffff\1\30\45\uffff\1\25\40\uffff\1\12\1\16\40\uffff\1\11\1"+
        "\22\23\uffff\1\10\1\20\11\uffff\1\7\1\0\7\uffff\1\21\1\1\5\uffff"+
        "\1\17\1\2\1\uffff\1\15\1\3\1\13\1\4\1\uffff\1\5\2\uffff\1\6\2\uffff}>";
    static final String[] DFA18_transitionS = {
            "\11\52\2\51\2\52\1\51\22\52\1\51\1\52\1\27\1\50\1\22\2\52\1"+
            "\30\1\31\1\32\1\45\1\33\1\34\1\35\1\36\1\37\12\46\2\52\1\24"+
            "\1\40\1\25\2\52\1\23\1\10\1\6\1\11\1\14\1\2\1\12\1\15\1\4\2"+
            "\47\1\3\1\17\1\5\1\13\2\47\1\1\1\16\1\47\1\20\1\47\1\21\3\47"+
            "\1\7\1\52\1\41\1\52\1\47\1\42\1\23\1\10\1\6\1\11\1\14\1\2\1"+
            "\12\1\15\1\4\2\47\1\3\1\17\1\5\1\13\2\47\1\1\1\16\1\47\1\20"+
            "\1\47\1\21\3\47\1\43\1\26\1\44\uff82\52",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\10\54\1\53\21\54"+
            "\4\uffff\1\54\1\uffff\10\54\1\53\21\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\21\54\1\60\2\54\1"+
            "\57\5\54\4\uffff\1\54\1\uffff\21\54\1\60\2\54\1\57\5\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\61\3\54\1"+
            "\62\21\54\4\uffff\1\54\1\uffff\4\54\1\61\3\54\1\62\21\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\64\4\54\1"+
            "\63\7\54\4\uffff\1\54\1\uffff\15\54\1\64\4\54\1\63\7\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\16\54\1\65\13\54"+
            "\4\uffff\1\54\1\uffff\16\54\1\65\13\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\21\54\1\66\10\54"+
            "\4\uffff\1\54\1\uffff\21\54\1\66\10\54",
            "\1\67\4\uffff\1\70\32\uffff\1\67\4\uffff\1\70",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\72\25\54\4"+
            "\uffff\1\54\1\uffff\4\54\1\72\25\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\74\3\54\1"+
            "\73\21\54\4\uffff\1\54\1\uffff\4\54\1\74\3\54\1\73\21\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\21\54\1\75\10\54"+
            "\4\uffff\1\54\1\uffff\21\54\1\75\10\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\77\3\54\1"+
            "\76\10\54\4\uffff\1\54\1\uffff\15\54\1\77\3\54\1\76\10\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\20\54\1\101\6\54"+
            "\1\100\2\54\4\uffff\1\54\1\uffff\20\54\1\101\6\54\1\100\2\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\1\102\31\54\4\uffff"+
            "\1\54\1\uffff\1\102\31\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\103\11\54"+
            "\1\104\13\54\4\uffff\1\54\1\uffff\4\54\1\103\11\54\1\104\13"+
            "\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\10\54\1\105\21\54"+
            "\4\uffff\1\54\1\uffff\10\54\1\105\21\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\106\14\54"+
            "\4\uffff\1\54\1\uffff\15\54\1\106\14\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\7\54\1\107\22\54"+
            "\4\uffff\1\54\1\uffff\7\54\1\107\22\54",
            "\1\110\7\uffff\1\111\27\uffff\1\110\7\uffff\1\111",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\13\54\1\112\1\54"+
            "\1\113\4\54\1\114\7\54\4\uffff\1\54\1\uffff\13\54\1\112\1\54"+
            "\1\113\4\54\1\114\7\54",
            "\1\115\1\116",
            "\1\120",
            "\1\122",
            "\0\123",
            "\60\123\12\125\uffc6\123",
            "",
            "",
            "",
            "",
            "\1\56\11\uffff\1\133\2\uffff\12\135\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "",
            "\1\140\4\uffff\1\137",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\56\11\uffff\1\56\1\150\1\uffff\12\135\7\uffff\32\56\4\uffff"+
            "\1\56\1\uffff\32\56",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\43\137\1\152\11\137\1\152\2\137\12\152\7\137\32\152\4\137"+
            "\1\152\1\137\32\152\uff85\137",
            "",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\6\54\1\154\23\54"+
            "\4\uffff\1\54\1\uffff\6\54\1\154\23\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\13\54\1\155\16\54"+
            "\4\uffff\1\54\1\uffff\13\54\1\155\16\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\16\54\1\156\13\54"+
            "\4\uffff\1\54\1\uffff\16\54\1\156\13\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\5\54\1\157\14\54"+
            "\1\160\7\54\4\uffff\1\54\1\uffff\5\54\1\157\14\54\1\160\7\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\12\54\1\161\17\54"+
            "\4\uffff\1\54\1\uffff\12\54\1\161\17\54",
            "\1\162\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32"+
            "\54\4\uffff\1\54\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\163\5\54"+
            "\1\164\6\54\4\uffff\1\54\1\uffff\15\54\1\163\5\54\1\164\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\166\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\166\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\16\54\1\167\13\54"+
            "\4\uffff\1\54\1\uffff\16\54\1\167\13\54",
            "\1\170\37\uffff\1\170",
            "",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\171\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\171\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\22\54\1\172\7\54"+
            "\4\uffff\1\54\1\uffff\22\54\1\172\7\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\22\54\1\173\7\54"+
            "\4\uffff\1\54\1\uffff\22\54\1\173\7\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\175\11\54"+
            "\1\174\13\54\4\uffff\1\54\1\uffff\4\54\1\175\11\54\1\174\13"+
            "\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\3\54\1\176\26\54"+
            "\4\uffff\1\54\1\uffff\3\54\1\176\26\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\2\54\1\u0081\27\54"+
            "\4\uffff\1\54\1\uffff\2\54\1\u0081\27\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\24\54\1\u0082\5\54"+
            "\4\uffff\1\54\1\uffff\24\54\1\u0082\5\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\25\54\1\u0083\4\54"+
            "\4\uffff\1\54\1\uffff\25\54\1\u0083\4\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\13\54\1\u0084\16"+
            "\54\4\uffff\1\54\1\uffff\13\54\1\u0084\16\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\14\54\1\u0085\15"+
            "\54\4\uffff\1\54\1\uffff\14\54\1\u0085\15\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\u0086\14"+
            "\54\4\uffff\1\54\1\uffff\15\54\1\u0086\14\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\10\54\1\u0087\21"+
            "\54\4\uffff\1\54\1\uffff\10\54\1\u0087\21\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u0088\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u0088\25\54",
            "\1\u0089",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\13\54\1\u008b\16"+
            "\54\4\uffff\1\54\1\uffff\13\54\1\u008b\16\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\3\54\1\u008c\24\54"+
            "\1\u008d\1\54\4\uffff\1\54\1\uffff\3\54\1\u008c\24\54\1\u008d"+
            "\1\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\2\54\1\u008e\27\54"+
            "\4\uffff\1\54\1\uffff\2\54\1\u008e\27\54",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\60\123\12\u0090\uffc6\123",
            "",
            "",
            "",
            "",
            "",
            "\43\137\1\152\11\137\1\152\2\137\12\152\7\137\32\152\4\137"+
            "\1\152\1\137\32\152\uff85\137",
            "",
            "\1\56\11\uffff\1\56\1\150\1\uffff\12\135\7\uffff\32\56\4\uffff"+
            "\1\56\1\uffff\32\56",
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
            "\43\137\1\152\11\137\1\152\2\137\12\152\7\137\32\152\4\137"+
            "\1\152\1\137\32\152\uff85\137",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\7\54\1\u0091\22\54"+
            "\4\uffff\1\54\1\uffff\7\54\1\u0091\22\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\13\54\1\u0092\16"+
            "\54\4\uffff\1\54\1\uffff\13\54\1\u0092\16\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\14\54\1\u0093\15"+
            "\54\4\uffff\1\54\1\uffff\14\54\1\u0093\15\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\u0094\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\u0094\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\22\54\1\u0095\7\54"+
            "\4\uffff\1\54\1\uffff\22\54\1\u0095\7\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u0096\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u0096\25\54",
            "\1\u0097\37\uffff\1\u0097",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u0098\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u0098\25\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u0099\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u0099\25\54",
            "",
            "\1\u009a\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4"+
            "\54\1\u009b\3\54\1\u009c\21\54\4\uffff\1\54\1\uffff\4\54\1\u009b"+
            "\3\54\1\u009c\21\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\22\54\1\u009d\7\54"+
            "\4\uffff\1\54\1\uffff\22\54\1\u009d\7\54",
            "\1\u009e\37\uffff\1\u009e",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\26\54\1\u009f\3\54"+
            "\4\uffff\1\54\1\uffff\26\54\1\u009f\3\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\u00a0\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\u00a0\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\2\54\1\u00a1\27\54"+
            "\4\uffff\1\54\1\uffff\2\54\1\u00a1\27\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\24\54\1\u00a2\5\54"+
            "\4\uffff\1\54\1\uffff\24\54\1\u00a2\5\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\1\u00a3\31\54\4\uffff"+
            "\1\54\1\uffff\1\u00a3\31\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00a4\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00a4\25\54",
            "",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00a5\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00a5\25\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\1\u00a6\31\54\4\uffff"+
            "\1\54\1\uffff\1\u00a6\31\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\10\54\1\u00a7\21"+
            "\54\4\uffff\1\54\1\uffff\10\54\1\u00a7\21\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00a8\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00a8\25\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00a9\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00a9\25\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\24\54\1\u00aa\5\54"+
            "\4\uffff\1\54\1\uffff\24\54\1\u00aa\5\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\16\54\1\u00ab\13"+
            "\54\4\uffff\1\54\1\uffff\16\54\1\u00ab\13\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\21\54\1\u00ac\10"+
            "\54\4\uffff\1\54\1\uffff\21\54\1\u00ac\10\54",
            "",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "",
            "\60\123\12\u00b1\1\u00b2\uffc5\123",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\u00b3\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\u00b3\6\54",
            "\1\u00b4\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32"+
            "\54\4\uffff\1\54\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\u00b6\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32"+
            "\54\4\uffff\1\54\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\2\uffff\1\u00b7"+
            "\1\uffff\1\54\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\u00ba\5\uffff\1\u00bb\31\uffff\1\u00ba\5\uffff\1\u00bb",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\21\54\1\u00bc\10"+
            "\54\4\uffff\1\54\1\uffff\21\54\1\u00bc\10\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\21\54\1\u00bd\10"+
            "\54\4\uffff\1\54\1\uffff\21\54\1\u00bd\10\54",
            "\1\u00be\6\uffff\1\u00c0\2\uffff\1\u00bf\25\uffff\1\u00be\6"+
            "\uffff\1\u00c0\2\uffff\1\u00bf",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\20\54\1\u00c1\11"+
            "\54\4\uffff\1\54\1\uffff\20\54\1\u00c1\11\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\u00c2\14"+
            "\54\4\uffff\1\54\1\uffff\15\54\1\u00c2\14\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\22\54\1\u00c3\7\54"+
            "\4\uffff\1\54\1\uffff\22\54\1\u00c3\7\54",
            "\1\u00c4\37\uffff\1\u00c4",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00c5\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00c5\25\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\10\54\1\u00c6\21"+
            "\54\4\uffff\1\54\1\uffff\10\54\1\u00c6\21\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\17\54\1\u00c8\12"+
            "\54\4\uffff\1\54\1\uffff\17\54\1\u00c8\12\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\u00c9\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\u00c9\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\21\54\1\u00ca\10"+
            "\54\4\uffff\1\54\1\uffff\21\54\1\u00ca\10\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\17\54\1\u00cb\12"+
            "\54\4\uffff\1\54\1\uffff\17\54\1\u00cb\12\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\13\54\1\u00cc\16"+
            "\54\4\uffff\1\54\1\uffff\13\54\1\u00cc\16\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\u00cd\14"+
            "\54\4\uffff\1\54\1\uffff\15\54\1\u00cd\14\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\2\54\1\u00ce\27\54"+
            "\4\uffff\1\54\1\uffff\2\54\1\u00ce\27\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\22\54\1\u00d0\7\54"+
            "\4\uffff\1\54\1\uffff\22\54\1\u00d0\7\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\u00d1\14"+
            "\54\4\uffff\1\54\1\uffff\15\54\1\u00d1\14\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00d2\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00d2\25\54",
            "",
            "",
            "",
            "",
            "\60\123\12\u00d3\uffc6\123",
            "\60\123\12\u00d4\uffc6\123",
            "\1\u00d5\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32"+
            "\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00d6\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32"+
            "\54\4\uffff\1\54\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\22\54\1\u00d7\7\54"+
            "\4\uffff\1\54\1\uffff\22\54\1\u00d7\7\54",
            "",
            "",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\24\54\1\u00d8\5\54"+
            "\4\uffff\1\54\1\uffff\24\54\1\u00d8\5\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\u00da\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32"+
            "\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00db\37\uffff\1\u00db",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00dc\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00dc\25\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\u00dd\14"+
            "\54\4\uffff\1\54\1\uffff\15\54\1\u00dd\14\54",
            "",
            "\1\u00de\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32"+
            "\54\4\uffff\1\54\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00df\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00df\25\54",
            "\1\u00e0\2\uffff\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32"+
            "\54\4\uffff\1\54\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\u00e1\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\u00e1\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\6\54\1\u00e3\23\54"+
            "\4\uffff\1\54\1\uffff\6\54\1\u00e3\23\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\u00e4\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\u00e4\6\54",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\55\123\1\u00e8\uffd2\123",
            "\60\123\12\u00e9\uffc6\123",
            "",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\4\54\1\u00ea\25\54"+
            "\4\uffff\1\54\1\uffff\4\54\1\u00ea\25\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\1\u00eb\31\54\4\uffff"+
            "\1\54\1\uffff\1\u00eb\31\54",
            "",
            "",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\15\54\1\u00ed\14"+
            "\54\4\uffff\1\54\1\uffff\15\54\1\u00ed\14\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\2\54\1\u00ee\27\54"+
            "\4\uffff\1\54\1\uffff\2\54\1\u00ee\27\54",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\21\54\1\u00ef\10"+
            "\54\4\uffff\1\54\1\uffff\21\54\1\u00ef\10\54",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "",
            "",
            "",
            "\60\123\2\u00f3\uffce\123",
            "\72\123\1\u00f4\uffc5\123",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\2\54\1\u00f5\27\54"+
            "\4\uffff\1\54\1\uffff\2\54\1\u00f5\27\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\13\54\1\u00f6\16"+
            "\54\4\uffff\1\54\1\uffff\13\54\1\u00f6\16\54",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\2\uffff\1\u00f8"+
            "\1\uffff\1\54\1\uffff\32\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\u00fa\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\u00fa\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "",
            "",
            "",
            "\60\123\12\u00fc\uffc6\123",
            "\60\123\2\u00fd\uffce\123",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\23\54\1\u00fe\6\54"+
            "\4\uffff\1\54\1\uffff\23\54\1\u00fe\6\54",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "\1\u0100",
            "",
            "",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "",
            "\55\123\1\u0103\uffd2\123",
            "\60\123\12\u0104\uffc6\123",
            "\1\56\11\uffff\1\56\2\uffff\12\54\7\uffff\32\54\4\uffff\1\54"+
            "\1\uffff\32\54",
            "",
            "",
            "",
            "",
            "\60\123\4\u0106\uffcc\123",
            "\56\123\1\u0107\uffd1\123",
            "",
            "\60\123\12\u0108\uffc6\123",
            "\60\123\12\u0109\uffc6\123",
            "\47\123\1\u010a\uffd8\123",
            "\60\123\12\u010b\uffc6\123",
            "\1\u010d",
            "\60\123\12\u010e\uffc6\123",
            "",
            "",
            "\47\123\1\u010f\uffd8\123",
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
            return "1:1: Tokens : ( KEYWORD_67 | KEYWORD_65 | KEYWORD_66 | KEYWORD_63 | KEYWORD_64 | KEYWORD_61 | KEYWORD_62 | KEYWORD_59 | KEYWORD_60 | KEYWORD_51 | KEYWORD_52 | KEYWORD_53 | KEYWORD_54 | KEYWORD_55 | KEYWORD_56 | KEYWORD_57 | KEYWORD_58 | KEYWORD_48 | KEYWORD_49 | KEYWORD_50 | KEYWORD_44 | KEYWORD_45 | KEYWORD_46 | KEYWORD_47 | KEYWORD_38 | KEYWORD_39 | KEYWORD_40 | KEYWORD_41 | KEYWORD_42 | KEYWORD_43 | KEYWORD_33 | KEYWORD_34 | KEYWORD_35 | KEYWORD_36 | KEYWORD_37 | KEYWORD_28 | KEYWORD_29 | KEYWORD_30 | KEYWORD_31 | KEYWORD_32 | KEYWORD_18 | KEYWORD_19 | KEYWORD_20 | KEYWORD_21 | KEYWORD_22 | KEYWORD_23 | KEYWORD_24 | KEYWORD_25 | KEYWORD_26 | KEYWORD_27 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_11 | KEYWORD_12 | KEYWORD_13 | KEYWORD_14 | KEYWORD_15 | KEYWORD_16 | KEYWORD_17 | RULE_STAR | RULE_INT | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_SIGNED_DOUBLE | RULE_STRINGID | RULE_ID | RULE_SL_COMMENT | RULE_STRING | RULE_ML_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA18_244 = input.LA(1);

                        s = -1;
                        if ( ((LA18_244>='0' && LA18_244<='1')) ) {s = 253;}

                        else if ( ((LA18_244>='\u0000' && LA18_244<='/')||(LA18_244>='2' && LA18_244<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA18_253 = input.LA(1);

                        s = -1;
                        if ( ((LA18_253>='0' && LA18_253<='9')) ) {s = 260;}

                        else if ( ((LA18_253>='\u0000' && LA18_253<='/')||(LA18_253>=':' && LA18_253<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA18_260 = input.LA(1);

                        s = -1;
                        if ( (LA18_260=='.') ) {s = 263;}

                        else if ( ((LA18_260>='\u0000' && LA18_260<='-')||(LA18_260>='/' && LA18_260<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA18_263 = input.LA(1);

                        s = -1;
                        if ( ((LA18_263>='0' && LA18_263<='9')) ) {s = 265;}

                        else if ( ((LA18_263>='\u0000' && LA18_263<='/')||(LA18_263>=':' && LA18_263<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA18_265 = input.LA(1);

                        s = -1;
                        if ( ((LA18_265>='0' && LA18_265<='9')) ) {s = 267;}

                        else if ( ((LA18_265>='\u0000' && LA18_265<='/')||(LA18_265>=':' && LA18_265<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA18_267 = input.LA(1);

                        s = -1;
                        if ( ((LA18_267>='0' && LA18_267<='9')) ) {s = 270;}

                        else if ( ((LA18_267>='\u0000' && LA18_267<='/')||(LA18_267>=':' && LA18_267<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA18_270 = input.LA(1);

                        s = -1;
                        if ( (LA18_270=='\'') ) {s = 271;}

                        else if ( ((LA18_270>='\u0000' && LA18_270<='&')||(LA18_270>='(' && LA18_270<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA18_243 = input.LA(1);

                        s = -1;
                        if ( ((LA18_243>='0' && LA18_243<='9')) ) {s = 252;}

                        else if ( ((LA18_243>='\u0000' && LA18_243<='/')||(LA18_243>=':' && LA18_243<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA18_232 = input.LA(1);

                        s = -1;
                        if ( ((LA18_232>='0' && LA18_232<='1')) ) {s = 243;}

                        else if ( ((LA18_232>='\u0000' && LA18_232<='/')||(LA18_232>='2' && LA18_232<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA18_211 = input.LA(1);

                        s = -1;
                        if ( (LA18_211=='-') ) {s = 232;}

                        else if ( ((LA18_211>='\u0000' && LA18_211<=',')||(LA18_211>='.' && LA18_211<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA18_177 = input.LA(1);

                        s = -1;
                        if ( ((LA18_177>='0' && LA18_177<='9')) ) {s = 211;}

                        else if ( ((LA18_177>='\u0000' && LA18_177<='/')||(LA18_177>=':' && LA18_177<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA18_264 = input.LA(1);

                        s = -1;
                        if ( (LA18_264=='\'') ) {s = 266;}

                        else if ( ((LA18_264>='\u0000' && LA18_264<='&')||(LA18_264>='(' && LA18_264<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA18_91 = input.LA(1);

                        s = -1;
                        if ( (LA18_91=='#'||LA18_91=='-'||(LA18_91>='0' && LA18_91<='9')||(LA18_91>='A' && LA18_91<='Z')||LA18_91=='_'||(LA18_91>='a' && LA18_91<='z')) ) {s = 106;}

                        else if ( ((LA18_91>='\u0000' && LA18_91<='\"')||(LA18_91>='$' && LA18_91<=',')||(LA18_91>='.' && LA18_91<='/')||(LA18_91>=':' && LA18_91<='@')||(LA18_91>='[' && LA18_91<='^')||LA18_91=='`'||(LA18_91>='{' && LA18_91<='\uFFFF')) ) {s = 95;}

                        else s = 46;

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA18_262 = input.LA(1);

                        s = -1;
                        if ( ((LA18_262>='0' && LA18_262<='9')) ) {s = 264;}

                        else if ( ((LA18_262>='\u0000' && LA18_262<='/')||(LA18_262>=':' && LA18_262<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA18_178 = input.LA(1);

                        s = -1;
                        if ( ((LA18_178>='0' && LA18_178<='9')) ) {s = 212;}

                        else if ( ((LA18_178>='\u0000' && LA18_178<='/')||(LA18_178>=':' && LA18_178<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA18_259 = input.LA(1);

                        s = -1;
                        if ( ((LA18_259>='0' && LA18_259<='3')) ) {s = 262;}

                        else if ( ((LA18_259>='\u0000' && LA18_259<='/')||(LA18_259>='4' && LA18_259<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA18_233 = input.LA(1);

                        s = -1;
                        if ( (LA18_233==':') ) {s = 244;}

                        else if ( ((LA18_233>='\u0000' && LA18_233<='9')||(LA18_233>=';' && LA18_233<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA18_252 = input.LA(1);

                        s = -1;
                        if ( (LA18_252=='-') ) {s = 259;}

                        else if ( ((LA18_252>='\u0000' && LA18_252<=',')||(LA18_252>='.' && LA18_252<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA18_212 = input.LA(1);

                        s = -1;
                        if ( ((LA18_212>='0' && LA18_212<='9')) ) {s = 233;}

                        else if ( ((LA18_212>='\u0000' && LA18_212<='/')||(LA18_212>=':' && LA18_212<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA18_0 = input.LA(1);

                        s = -1;
                        if ( (LA18_0=='R'||LA18_0=='r') ) {s = 1;}

                        else if ( (LA18_0=='F'||LA18_0=='f') ) {s = 2;}

                        else if ( (LA18_0=='L'||LA18_0=='l') ) {s = 3;}

                        else if ( (LA18_0=='I'||LA18_0=='i') ) {s = 4;}

                        else if ( (LA18_0=='N'||LA18_0=='n') ) {s = 5;}

                        else if ( (LA18_0=='C'||LA18_0=='c') ) {s = 6;}

                        else if ( (LA18_0=='[') ) {s = 7;}

                        else if ( (LA18_0=='B'||LA18_0=='b') ) {s = 8;}

                        else if ( (LA18_0=='D'||LA18_0=='d') ) {s = 9;}

                        else if ( (LA18_0=='G'||LA18_0=='g') ) {s = 10;}

                        else if ( (LA18_0=='O'||LA18_0=='o') ) {s = 11;}

                        else if ( (LA18_0=='E'||LA18_0=='e') ) {s = 12;}

                        else if ( (LA18_0=='H'||LA18_0=='h') ) {s = 13;}

                        else if ( (LA18_0=='S'||LA18_0=='s') ) {s = 14;}

                        else if ( (LA18_0=='M'||LA18_0=='m') ) {s = 15;}

                        else if ( (LA18_0=='U'||LA18_0=='u') ) {s = 16;}

                        else if ( (LA18_0=='W'||LA18_0=='w') ) {s = 17;}

                        else if ( (LA18_0=='$') ) {s = 18;}

                        else if ( (LA18_0=='A'||LA18_0=='a') ) {s = 19;}

                        else if ( (LA18_0=='<') ) {s = 20;}

                        else if ( (LA18_0=='>') ) {s = 21;}

                        else if ( (LA18_0=='|') ) {s = 22;}

                        else if ( (LA18_0=='\"') ) {s = 23;}

                        else if ( (LA18_0=='\'') ) {s = 24;}

                        else if ( (LA18_0=='(') ) {s = 25;}

                        else if ( (LA18_0==')') ) {s = 26;}

                        else if ( (LA18_0=='+') ) {s = 27;}

                        else if ( (LA18_0==',') ) {s = 28;}

                        else if ( (LA18_0=='-') ) {s = 29;}

                        else if ( (LA18_0=='.') ) {s = 30;}

                        else if ( (LA18_0=='/') ) {s = 31;}

                        else if ( (LA18_0=='=') ) {s = 32;}

                        else if ( (LA18_0==']') ) {s = 33;}

                        else if ( (LA18_0=='`') ) {s = 34;}

                        else if ( (LA18_0=='{') ) {s = 35;}

                        else if ( (LA18_0=='}') ) {s = 36;}

                        else if ( (LA18_0=='*') ) {s = 37;}

                        else if ( ((LA18_0>='0' && LA18_0<='9')) ) {s = 38;}

                        else if ( ((LA18_0>='J' && LA18_0<='K')||(LA18_0>='P' && LA18_0<='Q')||LA18_0=='T'||LA18_0=='V'||(LA18_0>='X' && LA18_0<='Z')||LA18_0=='_'||(LA18_0>='j' && LA18_0<='k')||(LA18_0>='p' && LA18_0<='q')||LA18_0=='t'||LA18_0=='v'||(LA18_0>='x' && LA18_0<='z')) ) {s = 39;}

                        else if ( (LA18_0=='#') ) {s = 40;}

                        else if ( ((LA18_0>='\t' && LA18_0<='\n')||LA18_0=='\r'||LA18_0==' ') ) {s = 41;}

                        else if ( ((LA18_0>='\u0000' && LA18_0<='\b')||(LA18_0>='\u000B' && LA18_0<='\f')||(LA18_0>='\u000E' && LA18_0<='\u001F')||LA18_0=='!'||(LA18_0>='%' && LA18_0<='&')||(LA18_0>=':' && LA18_0<=';')||(LA18_0>='?' && LA18_0<='@')||LA18_0=='\\'||LA18_0=='^'||(LA18_0>='~' && LA18_0<='\uFFFF')) ) {s = 42;}

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA18_40 = input.LA(1);

                        s = -1;
                        if ( (LA18_40=='#'||LA18_40=='-'||(LA18_40>='0' && LA18_40<='9')||(LA18_40>='A' && LA18_40<='Z')||LA18_40=='_'||(LA18_40>='a' && LA18_40<='z')) ) {s = 106;}

                        else if ( ((LA18_40>='\u0000' && LA18_40<='\"')||(LA18_40>='$' && LA18_40<=',')||(LA18_40>='.' && LA18_40<='/')||(LA18_40>=':' && LA18_40<='@')||(LA18_40>='[' && LA18_40<='^')||LA18_40=='`'||(LA18_40>='{' && LA18_40<='\uFFFF')) ) {s = 95;}

                        else s = 46;

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA18_144 = input.LA(1);

                        s = -1;
                        if ( ((LA18_144>='0' && LA18_144<='9')) ) {s = 177;}

                        else if ( (LA18_144==':') ) {s = 178;}

                        else if ( ((LA18_144>='\u0000' && LA18_144<='/')||(LA18_144>=';' && LA18_144<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA18_85 = input.LA(1);

                        s = -1;
                        if ( ((LA18_85>='0' && LA18_85<='9')) ) {s = 144;}

                        else if ( ((LA18_85>='\u0000' && LA18_85<='/')||(LA18_85>=':' && LA18_85<='\uFFFF')) ) {s = 83;}

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA18_24 = input.LA(1);

                        s = -1;
                        if ( ((LA18_24>='0' && LA18_24<='9')) ) {s = 85;}

                        else if ( ((LA18_24>='\u0000' && LA18_24<='/')||(LA18_24>=':' && LA18_24<='\uFFFF')) ) {s = 83;}

                        else s = 86;

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA18_106 = input.LA(1);

                        s = -1;
                        if ( (LA18_106=='#'||LA18_106=='-'||(LA18_106>='0' && LA18_106<='9')||(LA18_106>='A' && LA18_106<='Z')||LA18_106=='_'||(LA18_106>='a' && LA18_106<='z')) ) {s = 106;}

                        else if ( ((LA18_106>='\u0000' && LA18_106<='\"')||(LA18_106>='$' && LA18_106<=',')||(LA18_106>='.' && LA18_106<='/')||(LA18_106>=':' && LA18_106<='@')||(LA18_106>='[' && LA18_106<='^')||LA18_106=='`'||(LA18_106>='{' && LA18_106<='\uFFFF')) ) {s = 95;}

                        else s = 46;

                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA18_23 = input.LA(1);

                        s = -1;
                        if ( ((LA18_23>='\u0000' && LA18_23<='\uFFFF')) ) {s = 83;}

                        else s = 84;

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