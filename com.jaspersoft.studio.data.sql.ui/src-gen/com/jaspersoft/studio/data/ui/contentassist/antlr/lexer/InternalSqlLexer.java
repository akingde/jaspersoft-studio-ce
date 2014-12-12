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
    public static final int RULE_ID=118;
    public static final int RULE_JRNPARAM=108;
    public static final int RULE_DATE=111;
    public static final int RULE_ANY_OTHER=122;
    public static final int KEYWORD_56=42;
    public static final int KEYWORD_55=41;
    public static final int KEYWORD_54=40;
    public static final int KEYWORD_53=39;
    public static final int KEYWORD_52=72;
    public static final int KEYWORD_51=71;
    public static final int KEYWORD_50=70;
    public static final int EOF=-1;
    public static final int KEYWORD_59=45;
    public static final int KEYWORD_58=44;
    public static final int KEYWORD_57=43;
    public static final int KEYWORD_65=51;
    public static final int KEYWORD_64=50;
    public static final int RULE_SIGNED_DOUBLE=114;
    public static final int KEYWORD_67=53;
    public static final int KEYWORD_66=52;
    public static final int KEYWORD_61=47;
    public static final int KEYWORD_60=46;
    public static final int KEYWORD_63=49;
    public static final int KEYWORD_62=48;
    public static final int KEYWORD_69=34;
    public static final int KEYWORD_68=54;
    public static final int KEYWORD_30=78;
    public static final int KEYWORD_34=82;
    public static final int KEYWORD_33=81;
    public static final int KEYWORD_32=80;
    public static final int KEYWORD_31=79;
    public static final int KEYWORD_38=58;
    public static final int KEYWORD_37=57;
    public static final int KEYWORD_36=56;
    public static final int KEYWORD_35=55;
    public static final int RULE_ML_COMMENT=120;
    public static final int KEYWORD_39=59;
    public static final int RULE_STRING=116;
    public static final int KEYWORD_41=61;
    public static final int KEYWORD_40=60;
    public static final int KEYWORD_43=63;
    public static final int KEYWORD_42=62;
    public static final int KEYWORD_45=65;
    public static final int KEYWORD_44=64;
    public static final int KEYWORD_47=67;
    public static final int KEYWORD_46=66;
    public static final int KEYWORD_49=69;
    public static final int KEYWORD_48=68;
    public static final int KEYWORD_97=11;
    public static final int KEYWORD_98=12;
    public static final int KEYWORD_99=8;
    public static final int KEYWORD_93=16;
    public static final int KEYWORD_94=17;
    public static final int KEYWORD_95=9;
    public static final int KEYWORD_96=10;
    public static final int KEYWORD_90=13;
    public static final int KEYWORD_19=88;
    public static final int KEYWORD_92=15;
    public static final int KEYWORD_17=86;
    public static final int KEYWORD_91=14;
    public static final int KEYWORD_18=87;
    public static final int KEYWORD_15=84;
    public static final int KEYWORD_16=85;
    public static final int KEYWORD_13=106;
    public static final int RULE_STRING_=115;
    public static final int KEYWORD_14=83;
    public static final int KEYWORD_11=104;
    public static final int KEYWORD_12=105;
    public static final int KEYWORD_10=103;
    public static final int KEYWORD_103=5;
    public static final int KEYWORD_101=6;
    public static final int KEYWORD_102=4;
    public static final int KEYWORD_100=7;
    public static final int KEYWORD_6=99;
    public static final int KEYWORD_7=100;
    public static final int KEYWORD_8=101;
    public static final int KEYWORD_9=102;
    public static final int RULE_TIME=112;
    public static final int KEYWORD_28=76;
    public static final int KEYWORD_29=77;
    public static final int RULE_TIMESTAMP=113;
    public static final int RULE_INT=110;
    public static final int KEYWORD_24=93;
    public static final int RULE_DBNAME=117;
    public static final int KEYWORD_25=73;
    public static final int KEYWORD_26=74;
    public static final int KEYWORD_27=75;
    public static final int KEYWORD_20=89;
    public static final int KEYWORD_21=90;
    public static final int KEYWORD_22=91;
    public static final int KEYWORD_23=92;
    public static final int KEYWORD_79=31;
    public static final int KEYWORD_71=36;
    public static final int KEYWORD_72=37;
    public static final int KEYWORD_73=38;
    public static final int KEYWORD_74=26;
    public static final int KEYWORD_75=27;
    public static final int KEYWORD_76=28;
    public static final int KEYWORD_77=29;
    public static final int KEYWORD_78=30;
    public static final int KEYWORD_1=94;
    public static final int KEYWORD_5=98;
    public static final int KEYWORD_4=97;
    public static final int KEYWORD_3=96;
    public static final int KEYWORD_70=35;
    public static final int KEYWORD_2=95;
    public static final int RULE_SL_COMMENT=119;
    public static final int RULE_STAR=109;
    public static final int KEYWORD_84=20;
    public static final int KEYWORD_85=21;
    public static final int KEYWORD_82=18;
    public static final int KEYWORD_83=19;
    public static final int KEYWORD_88=24;
    public static final int RULE_JRPARAM=107;
    public static final int KEYWORD_89=25;
    public static final int KEYWORD_86=22;
    public static final int KEYWORD_87=23;
    public static final int KEYWORD_81=33;
    public static final int KEYWORD_80=32;
    public static final int RULE_WS=121;

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

    // $ANTLR start "KEYWORD_102"
    public final void mKEYWORD_102() throws RecognitionException {
        try {
            int _type = KEYWORD_102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:19:13: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:19:15: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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

            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
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

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
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

            match(' '); 
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
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

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
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
    // $ANTLR end "KEYWORD_102"

    // $ANTLR start "KEYWORD_103"
    public final void mKEYWORD_103() throws RecognitionException {
        try {
            int _type = KEYWORD_103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:21:13: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:21:15: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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

            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
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

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
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

            match(' '); 
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
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
    // $ANTLR end "KEYWORD_103"

    // $ANTLR start "KEYWORD_101"
    public final void mKEYWORD_101() throws RecognitionException {
        try {
            int _type = KEYWORD_101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:23:13: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'S' | 's' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'S' | 's' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:23:15: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'S' | 's' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'S' | 's' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
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

            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
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

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
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
    // $ANTLR end "KEYWORD_101"

    // $ANTLR start "KEYWORD_100"
    public final void mKEYWORD_100() throws RecognitionException {
        try {
            int _type = KEYWORD_100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:25:13: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) '_' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:25:15: ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) '_' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
            {
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

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
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

            match('_'); 
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
    // $ANTLR end "KEYWORD_100"

    // $ANTLR start "KEYWORD_99"
    public final void mKEYWORD_99() throws RecognitionException {
        try {
            int _type = KEYWORD_99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:27:12: ( ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:27:14: ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
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

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
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
    // $ANTLR end "KEYWORD_99"

    // $ANTLR start "KEYWORD_95"
    public final void mKEYWORD_95() throws RecognitionException {
        try {
            int _type = KEYWORD_95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:29:12: ( ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ' ' ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:29:14: ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ' ' ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
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

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
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

            match(' '); 
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

            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
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
    // $ANTLR end "KEYWORD_95"

    // $ANTLR start "KEYWORD_96"
    public final void mKEYWORD_96() throws RecognitionException {
        try {
            int _type = KEYWORD_96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:31:12: ( ( 'F' | 'f' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) ' ' ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:31:14: ( 'F' | 'f' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) ' ' ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
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

            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
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

            match(' '); 
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
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
    // $ANTLR end "KEYWORD_96"

    // $ANTLR start "KEYWORD_97"
    public final void mKEYWORD_97() throws RecognitionException {
        try {
            int _type = KEYWORD_97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:33:12: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:33:14: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_97"

    // $ANTLR start "KEYWORD_98"
    public final void mKEYWORD_98() throws RecognitionException {
        try {
            int _type = KEYWORD_98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:35:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:35:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_98"

    // $ANTLR start "KEYWORD_90"
    public final void mKEYWORD_90() throws RecognitionException {
        try {
            int _type = KEYWORD_90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:37:12: ( ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:37:14: ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
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

            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
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
    // $ANTLR end "KEYWORD_90"

    // $ANTLR start "KEYWORD_91"
    public final void mKEYWORD_91() throws RecognitionException {
        try {
            int _type = KEYWORD_91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:39:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:39:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_91"

    // $ANTLR start "KEYWORD_92"
    public final void mKEYWORD_92() throws RecognitionException {
        try {
            int _type = KEYWORD_92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:41:12: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:41:14: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
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
    // $ANTLR end "KEYWORD_92"

    // $ANTLR start "KEYWORD_93"
    public final void mKEYWORD_93() throws RecognitionException {
        try {
            int _type = KEYWORD_93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:43:12: ( ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'H' | 'h' ) ' ' ( 'T' | 't' ) ( 'I' | 'i' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:43:14: ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'H' | 'h' ) ' ' ( 'T' | 't' ) ( 'I' | 'i' ) ( 'E' | 'e' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
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

            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
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

            match(' '); 
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_93"

    // $ANTLR start "KEYWORD_94"
    public final void mKEYWORD_94() throws RecognitionException {
        try {
            int _type = KEYWORD_94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:45:12: ( '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:45:14: '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
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
    // $ANTLR end "KEYWORD_94"

    // $ANTLR start "KEYWORD_82"
    public final void mKEYWORD_82() throws RecognitionException {
        try {
            int _type = KEYWORD_82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:47:12: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:47:14: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
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
    // $ANTLR end "KEYWORD_82"

    // $ANTLR start "KEYWORD_83"
    public final void mKEYWORD_83() throws RecognitionException {
        try {
            int _type = KEYWORD_83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:49:12: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:49:14: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_83"

    // $ANTLR start "KEYWORD_84"
    public final void mKEYWORD_84() throws RecognitionException {
        try {
            int _type = KEYWORD_84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:51:12: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:51:14: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_84"

    // $ANTLR start "KEYWORD_85"
    public final void mKEYWORD_85() throws RecognitionException {
        try {
            int _type = KEYWORD_85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:53:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:53:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_85"

    // $ANTLR start "KEYWORD_86"
    public final void mKEYWORD_86() throws RecognitionException {
        try {
            int _type = KEYWORD_86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:55:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:55:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_86"

    // $ANTLR start "KEYWORD_87"
    public final void mKEYWORD_87() throws RecognitionException {
        try {
            int _type = KEYWORD_87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:57:12: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:57:14: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_87"

    // $ANTLR start "KEYWORD_88"
    public final void mKEYWORD_88() throws RecognitionException {
        try {
            int _type = KEYWORD_88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:59:12: ( '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:59:14: '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_88"

    // $ANTLR start "KEYWORD_89"
    public final void mKEYWORD_89() throws RecognitionException {
        try {
            int _type = KEYWORD_89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:61:12: ( '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:61:14: '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_89"

    // $ANTLR start "KEYWORD_74"
    public final void mKEYWORD_74() throws RecognitionException {
        try {
            int _type = KEYWORD_74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:63:12: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:63:14: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_74"

    // $ANTLR start "KEYWORD_75"
    public final void mKEYWORD_75() throws RecognitionException {
        try {
            int _type = KEYWORD_75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:65:12: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:65:14: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' )
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

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_75"

    // $ANTLR start "KEYWORD_76"
    public final void mKEYWORD_76() throws RecognitionException {
        try {
            int _type = KEYWORD_76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:67:12: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:67:14: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_76"

    // $ANTLR start "KEYWORD_77"
    public final void mKEYWORD_77() throws RecognitionException {
        try {
            int _type = KEYWORD_77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:69:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:69:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' )
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

            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
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

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_77"

    // $ANTLR start "KEYWORD_78"
    public final void mKEYWORD_78() throws RecognitionException {
        try {
            int _type = KEYWORD_78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:71:12: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:71:14: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_78"

    // $ANTLR start "KEYWORD_79"
    public final void mKEYWORD_79() throws RecognitionException {
        try {
            int _type = KEYWORD_79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:73:12: ( ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:73:14: ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
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

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
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
    // $ANTLR end "KEYWORD_79"

    // $ANTLR start "KEYWORD_80"
    public final void mKEYWORD_80() throws RecognitionException {
        try {
            int _type = KEYWORD_80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:75:12: ( ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:75:14: ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_80"

    // $ANTLR start "KEYWORD_81"
    public final void mKEYWORD_81() throws RecognitionException {
        try {
            int _type = KEYWORD_81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:77:12: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:77:14: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' )
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

            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
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

            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_81"

    // $ANTLR start "KEYWORD_69"
    public final void mKEYWORD_69() throws RecognitionException {
        try {
            int _type = KEYWORD_69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:79:12: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:79:14: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_69"

    // $ANTLR start "KEYWORD_70"
    public final void mKEYWORD_70() throws RecognitionException {
        try {
            int _type = KEYWORD_70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:81:12: ( ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:81:14: ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "KEYWORD_70"

    // $ANTLR start "KEYWORD_71"
    public final void mKEYWORD_71() throws RecognitionException {
        try {
            int _type = KEYWORD_71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:83:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:83:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_71"

    // $ANTLR start "KEYWORD_72"
    public final void mKEYWORD_72() throws RecognitionException {
        try {
            int _type = KEYWORD_72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:85:12: ( ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:85:14: ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
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

            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
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
    // $ANTLR end "KEYWORD_72"

    // $ANTLR start "KEYWORD_73"
    public final void mKEYWORD_73() throws RecognitionException {
        try {
            int _type = KEYWORD_73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:87:12: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:87:14: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_73"

    // $ANTLR start "KEYWORD_53"
    public final void mKEYWORD_53() throws RecognitionException {
        try {
            int _type = KEYWORD_53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:89:12: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) '(' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:89:14: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) '('
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
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

            match('('); 

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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:91:12: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:91:14: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' )
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:93:12: ( ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:93:14: ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_55"

    // $ANTLR start "KEYWORD_56"
    public final void mKEYWORD_56() throws RecognitionException {
        try {
            int _type = KEYWORD_56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:95:12: ( ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:95:14: ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
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
    // $ANTLR end "KEYWORD_56"

    // $ANTLR start "KEYWORD_57"
    public final void mKEYWORD_57() throws RecognitionException {
        try {
            int _type = KEYWORD_57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:97:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:97:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:99:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:99:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']'
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
    // $ANTLR end "KEYWORD_58"

    // $ANTLR start "KEYWORD_59"
    public final void mKEYWORD_59() throws RecognitionException {
        try {
            int _type = KEYWORD_59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:101:12: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:101:14: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' | 't' )
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:103:12: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:103:14: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_60"

    // $ANTLR start "KEYWORD_61"
    public final void mKEYWORD_61() throws RecognitionException {
        try {
            int _type = KEYWORD_61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:105:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:105:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_61"

    // $ANTLR start "KEYWORD_62"
    public final void mKEYWORD_62() throws RecognitionException {
        try {
            int _type = KEYWORD_62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:107:12: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:107:14: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'S' | 's' )
            {
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
    // $ANTLR end "KEYWORD_62"

    // $ANTLR start "KEYWORD_63"
    public final void mKEYWORD_63() throws RecognitionException {
        try {
            int _type = KEYWORD_63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:109:12: ( ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:109:14: ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
            {
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:111:12: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:111:14: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
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

            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_64"

    // $ANTLR start "KEYWORD_65"
    public final void mKEYWORD_65() throws RecognitionException {
        try {
            int _type = KEYWORD_65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:113:12: ( ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:113:14: ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
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
    // $ANTLR end "KEYWORD_65"

    // $ANTLR start "KEYWORD_66"
    public final void mKEYWORD_66() throws RecognitionException {
        try {
            int _type = KEYWORD_66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:115:12: ( ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:115:14: ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_66"

    // $ANTLR start "KEYWORD_67"
    public final void mKEYWORD_67() throws RecognitionException {
        try {
            int _type = KEYWORD_67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:117:12: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:117:14: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_67"

    // $ANTLR start "KEYWORD_68"
    public final void mKEYWORD_68() throws RecognitionException {
        try {
            int _type = KEYWORD_68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:12: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:119:14: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_68"

    // $ANTLR start "KEYWORD_35"
    public final void mKEYWORD_35() throws RecognitionException {
        try {
            int _type = KEYWORD_35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:12: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:121:14: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:123:12: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:123:14: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
    // $ANTLR end "KEYWORD_36"

    // $ANTLR start "KEYWORD_37"
    public final void mKEYWORD_37() throws RecognitionException {
        try {
            int _type = KEYWORD_37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:12: ( ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:125:14: ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' )
            {
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:127:12: ( ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:127:14: ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' )
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
    // $ANTLR end "KEYWORD_38"

    // $ANTLR start "KEYWORD_39"
    public final void mKEYWORD_39() throws RecognitionException {
        try {
            int _type = KEYWORD_39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:129:12: ( ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:129:14: ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:131:12: ( ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:131:14: ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
            {
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

    // $ANTLR start "KEYWORD_41"
    public final void mKEYWORD_41() throws RecognitionException {
        try {
            int _type = KEYWORD_41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:133:12: ( ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:133:14: ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:135:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:135:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' )
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:137:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:137:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_43"

    // $ANTLR start "KEYWORD_44"
    public final void mKEYWORD_44() throws RecognitionException {
        try {
            int _type = KEYWORD_44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:139:12: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:139:14: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_44"

    // $ANTLR start "KEYWORD_45"
    public final void mKEYWORD_45() throws RecognitionException {
        try {
            int _type = KEYWORD_45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:141:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) '\\n' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:141:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) '\\n'
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

            match('\n'); 

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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:143:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:143:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' '
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:145:12: ( ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:145:14: ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'Y' | 'y' )
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

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
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
    // $ANTLR end "KEYWORD_47"

    // $ANTLR start "KEYWORD_48"
    public final void mKEYWORD_48() throws RecognitionException {
        try {
            int _type = KEYWORD_48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:147:12: ( ( 'O' | 'o' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:147:14: ( 'O' | 'o' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
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
    // $ANTLR end "KEYWORD_48"

    // $ANTLR start "KEYWORD_49"
    public final void mKEYWORD_49() throws RecognitionException {
        try {
            int _type = KEYWORD_49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:149:12: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:149:14: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'S' | 's' )
            {
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

            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
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
    // $ANTLR end "KEYWORD_49"

    // $ANTLR start "KEYWORD_50"
    public final void mKEYWORD_50() throws RecognitionException {
        try {
            int _type = KEYWORD_50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:151:12: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:151:14: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_50"

    // $ANTLR start "KEYWORD_51"
    public final void mKEYWORD_51() throws RecognitionException {
        try {
            int _type = KEYWORD_51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:153:12: ( ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:153:14: ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
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
    // $ANTLR end "KEYWORD_51"

    // $ANTLR start "KEYWORD_52"
    public final void mKEYWORD_52() throws RecognitionException {
        try {
            int _type = KEYWORD_52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:155:12: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:155:14: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_52"

    // $ANTLR start "KEYWORD_25"
    public final void mKEYWORD_25() throws RecognitionException {
        try {
            int _type = KEYWORD_25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:157:12: ( '(' '+' ')' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:157:14: '(' '+' ')'
            {
            match('('); 
            match('+'); 
            match(')'); 

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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:159:12: ( ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:159:14: ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_26"

    // $ANTLR start "KEYWORD_27"
    public final void mKEYWORD_27() throws RecognitionException {
        try {
            int _type = KEYWORD_27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:161:12: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:161:14: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "KEYWORD_27"

    // $ANTLR start "KEYWORD_28"
    public final void mKEYWORD_28() throws RecognitionException {
        try {
            int _type = KEYWORD_28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:163:12: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:163:14: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_28"

    // $ANTLR start "KEYWORD_29"
    public final void mKEYWORD_29() throws RecognitionException {
        try {
            int _type = KEYWORD_29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:165:12: ( ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:165:14: ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
    // $ANTLR end "KEYWORD_29"

    // $ANTLR start "KEYWORD_30"
    public final void mKEYWORD_30() throws RecognitionException {
        try {
            int _type = KEYWORD_30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:167:12: ( ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:167:14: ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'D' | 'd' )
            {
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:169:12: ( ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:169:14: ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
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
    // $ANTLR end "KEYWORD_31"

    // $ANTLR start "KEYWORD_32"
    public final void mKEYWORD_32() throws RecognitionException {
        try {
            int _type = KEYWORD_32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:171:12: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:171:14: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' )
            {
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

            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:173:12: ( ( 'T' | 't' ) ( 'O' | 'o' ) ( 'P' | 'p' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:173:14: ( 'T' | 't' ) ( 'O' | 'o' ) ( 'P' | 'p' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
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
    // $ANTLR end "KEYWORD_33"

    // $ANTLR start "KEYWORD_34"
    public final void mKEYWORD_34() throws RecognitionException {
        try {
            int _type = KEYWORD_34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:175:12: ( ( 'X' | 'x' ) ( 'M' | 'm' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:175:14: ( 'X' | 'x' ) ( 'M' | 'm' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
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
    // $ANTLR end "KEYWORD_34"

    // $ANTLR start "KEYWORD_14"
    public final void mKEYWORD_14() throws RecognitionException {
        try {
            int _type = KEYWORD_14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:177:12: ( '!' '=' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:177:14: '!' '='
            {
            match('!'); 
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:179:12: ( '$' ( 'X' | 'x' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:179:14: '$' ( 'X' | 'x' )
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
    // $ANTLR end "KEYWORD_15"

    // $ANTLR start "KEYWORD_16"
    public final void mKEYWORD_16() throws RecognitionException {
        try {
            int _type = KEYWORD_16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:181:12: ( '<' '=' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:181:14: '<' '='
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
    // $ANTLR end "KEYWORD_16"

    // $ANTLR start "KEYWORD_17"
    public final void mKEYWORD_17() throws RecognitionException {
        try {
            int _type = KEYWORD_17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:183:12: ( '<' '>' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:183:14: '<' '>'
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
    // $ANTLR end "KEYWORD_17"

    // $ANTLR start "KEYWORD_18"
    public final void mKEYWORD_18() throws RecognitionException {
        try {
            int _type = KEYWORD_18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:185:12: ( '>' '=' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:185:14: '>' '='
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
    // $ANTLR end "KEYWORD_18"

    // $ANTLR start "KEYWORD_19"
    public final void mKEYWORD_19() throws RecognitionException {
        try {
            int _type = KEYWORD_19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:187:12: ( ( 'A' | 'a' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:187:14: ( 'A' | 'a' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_19"

    // $ANTLR start "KEYWORD_20"
    public final void mKEYWORD_20() throws RecognitionException {
        try {
            int _type = KEYWORD_20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:189:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:189:14: ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_20"

    // $ANTLR start "KEYWORD_21"
    public final void mKEYWORD_21() throws RecognitionException {
        try {
            int _type = KEYWORD_21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:191:12: ( ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:191:14: ( 'O' | 'o' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_21"

    // $ANTLR start "KEYWORD_22"
    public final void mKEYWORD_22() throws RecognitionException {
        try {
            int _type = KEYWORD_22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:193:12: ( ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:193:14: ( 'O' | 'o' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_22"

    // $ANTLR start "KEYWORD_23"
    public final void mKEYWORD_23() throws RecognitionException {
        try {
            int _type = KEYWORD_23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:195:12: ( '^' '=' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:195:14: '^' '='
            {
            match('^'); 
            match('='); 

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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:197:12: ( '|' '|' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:197:14: '|' '|'
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
    // $ANTLR end "KEYWORD_24"

    // $ANTLR start "KEYWORD_1"
    public final void mKEYWORD_1() throws RecognitionException {
        try {
            int _type = KEYWORD_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:199:11: ( '(' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:199:13: '('
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:201:11: ( ')' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:201:13: ')'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:203:11: ( '+' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:203:13: '+'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:205:11: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:205:13: ','
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:207:11: ( '-' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:207:13: '-'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:209:11: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:209:13: '.'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:211:11: ( '/' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:211:13: '/'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:213:11: ( '<' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:213:13: '<'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:215:11: ( '=' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:215:13: '='
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:217:12: ( '>' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:217:14: '>'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:219:12: ( '{' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:219:14: '{'
            {
            match('{'); 

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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:221:12: ( '|' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:221:14: '|'
            {
            match('|'); 

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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:223:12: ( '}' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:223:14: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_13"

    // $ANTLR start "RULE_JRPARAM"
    public final void mRULE_JRPARAM() throws RecognitionException {
        try {
            int _type = RULE_JRPARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:227:14: ( '$P{' ( options {greedy=false; } : . )* '}' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:227:16: '$P{' ( options {greedy=false; } : . )* '}'
            {
            match("$P{"); 

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:227:22: ( options {greedy=false; } : . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='}') ) {
                    alt1=2;
                }
                else if ( ((LA1_0>='\u0000' && LA1_0<='|')||(LA1_0>='~' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:227:50: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_JRPARAM"

    // $ANTLR start "RULE_JRNPARAM"
    public final void mRULE_JRNPARAM() throws RecognitionException {
        try {
            int _type = RULE_JRNPARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:229:15: ( '$P!{' ( options {greedy=false; } : . )* '}' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:229:17: '$P!{' ( options {greedy=false; } : . )* '}'
            {
            match("$P!{"); 

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:229:24: ( options {greedy=false; } : . )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='}') ) {
                    alt2=2;
                }
                else if ( ((LA2_0>='\u0000' && LA2_0<='|')||(LA2_0>='~' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:229:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_JRNPARAM"

    // $ANTLR start "RULE_STAR"
    public final void mRULE_STAR() throws RecognitionException {
        try {
            int _type = RULE_STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:231:11: ( '*' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:231:13: '*'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:233:10: ( ( '-' )? ( '0' .. '9' )+ )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:233:12: ( '-' )? ( '0' .. '9' )+
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:233:12: ( '-' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:233:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:233:17: ( '0' .. '9' )+
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
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:233:18: '0' .. '9'
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:235:16: ( RULE_DATE ' ' RULE_TIME )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:235:18: RULE_DATE ' ' RULE_TIME
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:237:11: ( '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\'' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:237:13: '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\''
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:239:11: ( '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '1' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\'' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:239:13: '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '1' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\''
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:20: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:22: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:22: ( '-' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='-') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:22: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:27: ( '0' .. '9' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:28: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:39: ( '.' ( '0' .. '9' )+ )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='.') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:40: '.' ( '0' .. '9' )+
                    {
                    match('.'); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:44: ( '0' .. '9' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:241:45: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SIGNED_DOUBLE"

    // $ANTLR start "RULE_STRING_"
    public final void mRULE_STRING_() throws RecognitionException {
        try {
            int _type = RULE_STRING_;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:243:14: ( '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:243:16: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
            {
            match('\''); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:243:21: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
            loop9:
            do {
                int alt9=3;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\\') ) {
                    alt9=1;
                }
                else if ( ((LA9_0>='\u0000' && LA9_0<='&')||(LA9_0>='(' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='\uFFFF')) ) {
                    alt9=2;
                }


                switch (alt9) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:243:22: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:243:67: ~ ( ( '\\\\' | '\\'' ) )
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
            	    break loop9;
                }
            } while (true);

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING_"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:245:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:245:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:245:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
            loop10:
            do {
                int alt10=3;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='\\') ) {
                    alt10=1;
                }
                else if ( ((LA10_0>='\u0000' && LA10_0<='!')||(LA10_0>='#' && LA10_0<='[')||(LA10_0>=']' && LA10_0<='\uFFFF')) ) {
                    alt10=2;
                }


                switch (alt10) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:245:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:245:65: ~ ( ( '\\\\' | '\"' ) )
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
            	    break loop10;
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

    // $ANTLR start "RULE_DBNAME"
    public final void mRULE_DBNAME() throws RecognitionException {
        try {
            int _type = RULE_DBNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:13: ( ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:15: ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:15: ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='`') ) {
                alt13=1;
            }
            else if ( (LA13_0=='[') ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:16: '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`'
                    {
                    match('`'); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )*
                    loop11:
                    do {
                        int alt11=3;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0=='\\') ) {
                            alt11=1;
                        }
                        else if ( ((LA11_0>='\u0000' && LA11_0<='[')||(LA11_0>=']' && LA11_0<='_')||(LA11_0>='a' && LA11_0<='\uFFFF')) ) {
                            alt11=2;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:66: ~ ( ( '\\\\' | '`' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='_')||(input.LA(1)>='a' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    match('`'); 

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:86: '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']'
                    {
                    match('['); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:90: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )*
                    loop12:
                    do {
                        int alt12=3;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0=='\\') ) {
                            alt12=1;
                        }
                        else if ( ((LA12_0>='\u0000' && LA12_0<='[')||(LA12_0>='^' && LA12_0<='\uFFFF')) ) {
                            alt12=2;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:91: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:247:136: ~ ( ( '\\\\' | ']' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='[')||(input.LA(1)>='^' && input.LA(1)<='\uFFFF') ) {
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

                    match(']'); 

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
    // $ANTLR end "RULE_DBNAME"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:249:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:249:11: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )*
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:249:11: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0=='-'||(LA14_0>='0' && LA14_0<='9')||(LA14_0>='A' && LA14_0<='Z')||LA14_0=='_'||(LA14_0>='a' && LA14_0<='z')||(LA14_0>='\u00C0' && LA14_0<='\u024F')||(LA14_0>='\u0410' && LA14_0<='\u044F')||(LA14_0>='\u3041' && LA14_0<='\u30FF')||(LA14_0>='\u31F0' && LA14_0<='\u31FF')||(LA14_0>='\u3400' && LA14_0<='\u4DBF')||(LA14_0>='\u4E00' && LA14_0<='\u9FFF')||(LA14_0>='\uF900' && LA14_0<='\uFAFF')||LA14_0=='\uFF3F') ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u024F')||(input.LA(1)>='\u0410' && input.LA(1)<='\u044F')||(input.LA(1)>='\u3041' && input.LA(1)<='\u30FF')||(input.LA(1)>='\u31F0' && input.LA(1)<='\u31FF')||(input.LA(1)>='\u3400' && input.LA(1)<='\u4DBF')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF')||input.LA(1)=='\uFF3F' ) {
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:17: ( ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:19: ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:19: ( '--' | '#' | '//' )
            int alt15=3;
            switch ( input.LA(1) ) {
            case '-':
                {
                alt15=1;
                }
                break;
            case '#':
                {
                alt15=2;
                }
                break;
            case '/':
                {
                alt15=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:20: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:25: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:29: '//'
                    {
                    match("//"); 


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:35: (~ ( ( '\\n' | '\\r' ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='\u0000' && LA16_0<='\t')||(LA16_0>='\u000B' && LA16_0<='\f')||(LA16_0>='\u000E' && LA16_0<='\uFFFF')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:35: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop16;
                }
            } while (true);

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:51: ( ( '\\r' )? '\\n' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='\n'||LA18_0=='\r') ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:52: ( '\\r' )? '\\n'
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:52: ( '\\r' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='\r') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:251:52: '\\r'
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

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:253:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:253:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:253:24: ( options {greedy=false; } : . )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0=='*') ) {
                    int LA19_1 = input.LA(2);

                    if ( (LA19_1=='/') ) {
                        alt19=2;
                    }
                    else if ( ((LA19_1>='\u0000' && LA19_1<='.')||(LA19_1>='0' && LA19_1<='\uFFFF')) ) {
                        alt19=1;
                    }


                }
                else if ( ((LA19_0>='\u0000' && LA19_0<=')')||(LA19_0>='+' && LA19_0<='\uFFFF')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:253:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop19;
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:255:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:255:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:255:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>='\t' && LA20_0<='\n')||LA20_0=='\r'||LA20_0==' ') ) {
                    alt20=1;
                }


                switch (alt20) {
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
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
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
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:257:16: ( . )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:257:18: .
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
        // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:8: ( KEYWORD_102 | KEYWORD_103 | KEYWORD_101 | KEYWORD_100 | KEYWORD_99 | KEYWORD_95 | KEYWORD_96 | KEYWORD_97 | KEYWORD_98 | KEYWORD_90 | KEYWORD_91 | KEYWORD_92 | KEYWORD_93 | KEYWORD_94 | KEYWORD_82 | KEYWORD_83 | KEYWORD_84 | KEYWORD_85 | KEYWORD_86 | KEYWORD_87 | KEYWORD_88 | KEYWORD_89 | KEYWORD_74 | KEYWORD_75 | KEYWORD_76 | KEYWORD_77 | KEYWORD_78 | KEYWORD_79 | KEYWORD_80 | KEYWORD_81 | KEYWORD_69 | KEYWORD_70 | KEYWORD_71 | KEYWORD_72 | KEYWORD_73 | KEYWORD_53 | KEYWORD_54 | KEYWORD_55 | KEYWORD_56 | KEYWORD_57 | KEYWORD_58 | KEYWORD_59 | KEYWORD_60 | KEYWORD_61 | KEYWORD_62 | KEYWORD_63 | KEYWORD_64 | KEYWORD_65 | KEYWORD_66 | KEYWORD_67 | KEYWORD_68 | KEYWORD_35 | KEYWORD_36 | KEYWORD_37 | KEYWORD_38 | KEYWORD_39 | KEYWORD_40 | KEYWORD_41 | KEYWORD_42 | KEYWORD_43 | KEYWORD_44 | KEYWORD_45 | KEYWORD_46 | KEYWORD_47 | KEYWORD_48 | KEYWORD_49 | KEYWORD_50 | KEYWORD_51 | KEYWORD_52 | KEYWORD_25 | KEYWORD_26 | KEYWORD_27 | KEYWORD_28 | KEYWORD_29 | KEYWORD_30 | KEYWORD_31 | KEYWORD_32 | KEYWORD_33 | KEYWORD_34 | KEYWORD_14 | KEYWORD_15 | KEYWORD_16 | KEYWORD_17 | KEYWORD_18 | KEYWORD_19 | KEYWORD_20 | KEYWORD_21 | KEYWORD_22 | KEYWORD_23 | KEYWORD_24 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_11 | KEYWORD_12 | KEYWORD_13 | RULE_JRPARAM | RULE_JRNPARAM | RULE_STAR | RULE_INT | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_SIGNED_DOUBLE | RULE_STRING_ | RULE_STRING | RULE_DBNAME | RULE_ID | RULE_SL_COMMENT | RULE_ML_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt21=119;
        alt21 = dfa21.predict(input);
        switch (alt21) {
            case 1 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:10: KEYWORD_102
                {
                mKEYWORD_102(); 

                }
                break;
            case 2 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:22: KEYWORD_103
                {
                mKEYWORD_103(); 

                }
                break;
            case 3 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:34: KEYWORD_101
                {
                mKEYWORD_101(); 

                }
                break;
            case 4 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:46: KEYWORD_100
                {
                mKEYWORD_100(); 

                }
                break;
            case 5 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:58: KEYWORD_99
                {
                mKEYWORD_99(); 

                }
                break;
            case 6 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:69: KEYWORD_95
                {
                mKEYWORD_95(); 

                }
                break;
            case 7 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:80: KEYWORD_96
                {
                mKEYWORD_96(); 

                }
                break;
            case 8 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:91: KEYWORD_97
                {
                mKEYWORD_97(); 

                }
                break;
            case 9 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:102: KEYWORD_98
                {
                mKEYWORD_98(); 

                }
                break;
            case 10 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:113: KEYWORD_90
                {
                mKEYWORD_90(); 

                }
                break;
            case 11 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:124: KEYWORD_91
                {
                mKEYWORD_91(); 

                }
                break;
            case 12 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:135: KEYWORD_92
                {
                mKEYWORD_92(); 

                }
                break;
            case 13 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:146: KEYWORD_93
                {
                mKEYWORD_93(); 

                }
                break;
            case 14 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:157: KEYWORD_94
                {
                mKEYWORD_94(); 

                }
                break;
            case 15 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:168: KEYWORD_82
                {
                mKEYWORD_82(); 

                }
                break;
            case 16 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:179: KEYWORD_83
                {
                mKEYWORD_83(); 

                }
                break;
            case 17 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:190: KEYWORD_84
                {
                mKEYWORD_84(); 

                }
                break;
            case 18 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:201: KEYWORD_85
                {
                mKEYWORD_85(); 

                }
                break;
            case 19 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:212: KEYWORD_86
                {
                mKEYWORD_86(); 

                }
                break;
            case 20 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:223: KEYWORD_87
                {
                mKEYWORD_87(); 

                }
                break;
            case 21 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:234: KEYWORD_88
                {
                mKEYWORD_88(); 

                }
                break;
            case 22 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:245: KEYWORD_89
                {
                mKEYWORD_89(); 

                }
                break;
            case 23 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:256: KEYWORD_74
                {
                mKEYWORD_74(); 

                }
                break;
            case 24 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:267: KEYWORD_75
                {
                mKEYWORD_75(); 

                }
                break;
            case 25 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:278: KEYWORD_76
                {
                mKEYWORD_76(); 

                }
                break;
            case 26 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:289: KEYWORD_77
                {
                mKEYWORD_77(); 

                }
                break;
            case 27 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:300: KEYWORD_78
                {
                mKEYWORD_78(); 

                }
                break;
            case 28 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:311: KEYWORD_79
                {
                mKEYWORD_79(); 

                }
                break;
            case 29 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:322: KEYWORD_80
                {
                mKEYWORD_80(); 

                }
                break;
            case 30 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:333: KEYWORD_81
                {
                mKEYWORD_81(); 

                }
                break;
            case 31 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:344: KEYWORD_69
                {
                mKEYWORD_69(); 

                }
                break;
            case 32 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:355: KEYWORD_70
                {
                mKEYWORD_70(); 

                }
                break;
            case 33 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:366: KEYWORD_71
                {
                mKEYWORD_71(); 

                }
                break;
            case 34 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:377: KEYWORD_72
                {
                mKEYWORD_72(); 

                }
                break;
            case 35 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:388: KEYWORD_73
                {
                mKEYWORD_73(); 

                }
                break;
            case 36 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:399: KEYWORD_53
                {
                mKEYWORD_53(); 

                }
                break;
            case 37 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:410: KEYWORD_54
                {
                mKEYWORD_54(); 

                }
                break;
            case 38 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:421: KEYWORD_55
                {
                mKEYWORD_55(); 

                }
                break;
            case 39 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:432: KEYWORD_56
                {
                mKEYWORD_56(); 

                }
                break;
            case 40 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:443: KEYWORD_57
                {
                mKEYWORD_57(); 

                }
                break;
            case 41 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:454: KEYWORD_58
                {
                mKEYWORD_58(); 

                }
                break;
            case 42 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:465: KEYWORD_59
                {
                mKEYWORD_59(); 

                }
                break;
            case 43 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:476: KEYWORD_60
                {
                mKEYWORD_60(); 

                }
                break;
            case 44 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:487: KEYWORD_61
                {
                mKEYWORD_61(); 

                }
                break;
            case 45 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:498: KEYWORD_62
                {
                mKEYWORD_62(); 

                }
                break;
            case 46 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:509: KEYWORD_63
                {
                mKEYWORD_63(); 

                }
                break;
            case 47 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:520: KEYWORD_64
                {
                mKEYWORD_64(); 

                }
                break;
            case 48 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:531: KEYWORD_65
                {
                mKEYWORD_65(); 

                }
                break;
            case 49 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:542: KEYWORD_66
                {
                mKEYWORD_66(); 

                }
                break;
            case 50 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:553: KEYWORD_67
                {
                mKEYWORD_67(); 

                }
                break;
            case 51 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:564: KEYWORD_68
                {
                mKEYWORD_68(); 

                }
                break;
            case 52 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:575: KEYWORD_35
                {
                mKEYWORD_35(); 

                }
                break;
            case 53 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:586: KEYWORD_36
                {
                mKEYWORD_36(); 

                }
                break;
            case 54 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:597: KEYWORD_37
                {
                mKEYWORD_37(); 

                }
                break;
            case 55 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:608: KEYWORD_38
                {
                mKEYWORD_38(); 

                }
                break;
            case 56 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:619: KEYWORD_39
                {
                mKEYWORD_39(); 

                }
                break;
            case 57 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:630: KEYWORD_40
                {
                mKEYWORD_40(); 

                }
                break;
            case 58 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:641: KEYWORD_41
                {
                mKEYWORD_41(); 

                }
                break;
            case 59 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:652: KEYWORD_42
                {
                mKEYWORD_42(); 

                }
                break;
            case 60 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:663: KEYWORD_43
                {
                mKEYWORD_43(); 

                }
                break;
            case 61 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:674: KEYWORD_44
                {
                mKEYWORD_44(); 

                }
                break;
            case 62 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:685: KEYWORD_45
                {
                mKEYWORD_45(); 

                }
                break;
            case 63 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:696: KEYWORD_46
                {
                mKEYWORD_46(); 

                }
                break;
            case 64 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:707: KEYWORD_47
                {
                mKEYWORD_47(); 

                }
                break;
            case 65 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:718: KEYWORD_48
                {
                mKEYWORD_48(); 

                }
                break;
            case 66 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:729: KEYWORD_49
                {
                mKEYWORD_49(); 

                }
                break;
            case 67 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:740: KEYWORD_50
                {
                mKEYWORD_50(); 

                }
                break;
            case 68 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:751: KEYWORD_51
                {
                mKEYWORD_51(); 

                }
                break;
            case 69 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:762: KEYWORD_52
                {
                mKEYWORD_52(); 

                }
                break;
            case 70 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:773: KEYWORD_25
                {
                mKEYWORD_25(); 

                }
                break;
            case 71 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:784: KEYWORD_26
                {
                mKEYWORD_26(); 

                }
                break;
            case 72 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:795: KEYWORD_27
                {
                mKEYWORD_27(); 

                }
                break;
            case 73 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:806: KEYWORD_28
                {
                mKEYWORD_28(); 

                }
                break;
            case 74 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:817: KEYWORD_29
                {
                mKEYWORD_29(); 

                }
                break;
            case 75 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:828: KEYWORD_30
                {
                mKEYWORD_30(); 

                }
                break;
            case 76 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:839: KEYWORD_31
                {
                mKEYWORD_31(); 

                }
                break;
            case 77 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:850: KEYWORD_32
                {
                mKEYWORD_32(); 

                }
                break;
            case 78 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:861: KEYWORD_33
                {
                mKEYWORD_33(); 

                }
                break;
            case 79 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:872: KEYWORD_34
                {
                mKEYWORD_34(); 

                }
                break;
            case 80 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:883: KEYWORD_14
                {
                mKEYWORD_14(); 

                }
                break;
            case 81 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:894: KEYWORD_15
                {
                mKEYWORD_15(); 

                }
                break;
            case 82 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:905: KEYWORD_16
                {
                mKEYWORD_16(); 

                }
                break;
            case 83 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:916: KEYWORD_17
                {
                mKEYWORD_17(); 

                }
                break;
            case 84 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:927: KEYWORD_18
                {
                mKEYWORD_18(); 

                }
                break;
            case 85 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:938: KEYWORD_19
                {
                mKEYWORD_19(); 

                }
                break;
            case 86 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:949: KEYWORD_20
                {
                mKEYWORD_20(); 

                }
                break;
            case 87 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:960: KEYWORD_21
                {
                mKEYWORD_21(); 

                }
                break;
            case 88 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:971: KEYWORD_22
                {
                mKEYWORD_22(); 

                }
                break;
            case 89 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:982: KEYWORD_23
                {
                mKEYWORD_23(); 

                }
                break;
            case 90 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:993: KEYWORD_24
                {
                mKEYWORD_24(); 

                }
                break;
            case 91 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1004: KEYWORD_1
                {
                mKEYWORD_1(); 

                }
                break;
            case 92 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1014: KEYWORD_2
                {
                mKEYWORD_2(); 

                }
                break;
            case 93 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1024: KEYWORD_3
                {
                mKEYWORD_3(); 

                }
                break;
            case 94 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1034: KEYWORD_4
                {
                mKEYWORD_4(); 

                }
                break;
            case 95 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1044: KEYWORD_5
                {
                mKEYWORD_5(); 

                }
                break;
            case 96 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1054: KEYWORD_6
                {
                mKEYWORD_6(); 

                }
                break;
            case 97 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1064: KEYWORD_7
                {
                mKEYWORD_7(); 

                }
                break;
            case 98 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1074: KEYWORD_8
                {
                mKEYWORD_8(); 

                }
                break;
            case 99 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1084: KEYWORD_9
                {
                mKEYWORD_9(); 

                }
                break;
            case 100 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1094: KEYWORD_10
                {
                mKEYWORD_10(); 

                }
                break;
            case 101 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1105: KEYWORD_11
                {
                mKEYWORD_11(); 

                }
                break;
            case 102 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1116: KEYWORD_12
                {
                mKEYWORD_12(); 

                }
                break;
            case 103 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1127: KEYWORD_13
                {
                mKEYWORD_13(); 

                }
                break;
            case 104 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1138: RULE_JRPARAM
                {
                mRULE_JRPARAM(); 

                }
                break;
            case 105 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1151: RULE_JRNPARAM
                {
                mRULE_JRNPARAM(); 

                }
                break;
            case 106 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1165: RULE_STAR
                {
                mRULE_STAR(); 

                }
                break;
            case 107 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1175: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 108 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1184: RULE_TIMESTAMP
                {
                mRULE_TIMESTAMP(); 

                }
                break;
            case 109 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1199: RULE_DATE
                {
                mRULE_DATE(); 

                }
                break;
            case 110 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1209: RULE_TIME
                {
                mRULE_TIME(); 

                }
                break;
            case 111 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1219: RULE_SIGNED_DOUBLE
                {
                mRULE_SIGNED_DOUBLE(); 

                }
                break;
            case 112 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1238: RULE_STRING_
                {
                mRULE_STRING_(); 

                }
                break;
            case 113 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1251: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 114 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1263: RULE_DBNAME
                {
                mRULE_DBNAME(); 

                }
                break;
            case 115 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1275: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 116 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1283: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 117 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1299: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 118 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1315: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 119 :
                // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/lexer/InternalSqlLexer.g:1:1323: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA21 dfa21 = new DFA21(this);
    static final String DFA21_eotS =
        "\12\55\1\60\12\55\1\144\2\55\2\60\1\156\1\160\1\60\1\163\3\uffff"+
        "\1\170\1\uffff\1\175\4\uffff\1\u0083\3\60\5\uffff\1\55\1\u008c\2"+
        "\55\1\u0090\21\55\1\u00a6\5\55\3\uffff\23\55\2\uffff\2\55\1\u00c8"+
        "\1\55\16\uffff\1\55\1\uffff\1\u0083\16\uffff\4\55\1\uffff\3\55\1"+
        "\uffff\15\55\1\u00e3\3\55\1\uffff\3\55\1\uffff\5\55\2\uffff\10\55"+
        "\1\u00ff\11\55\1\u010a\2\55\1\u010d\1\u010e\1\u010f\1\u0110\1\u0111"+
        "\1\uffff\1\u0112\2\uffff\1\55\1\uffff\6\55\1\u011b\1\u011c\2\55"+
        "\1\u011f\6\55\1\u0126\3\55\1\uffff\1\55\1\u012b\1\u012c\1\uffff"+
        "\3\55\1\u0135\2\55\1\uffff\4\55\1\u013c\2\uffff\2\55\1\u0141\5\55"+
        "\1\u0147\1\uffff\1\55\1\u014a\1\u014b\1\55\1\u014d\1\u014e\3\55"+
        "\1\u0152\1\uffff\1\u0153\1\u0154\10\uffff\2\55\1\u0159\2\55\1\u015c"+
        "\2\uffff\2\55\1\uffff\3\55\1\u0162\1\55\2\uffff\1\u0164\2\55\1\u0167"+
        "\4\uffff\2\55\1\u016a\4\uffff\1\55\1\u016c\1\55\1\u016e\1\uffff"+
        "\1\u016f\3\uffff\2\55\1\uffff\4\55\1\u0178\1\uffff\1\55\3\uffff"+
        "\1\u017a\2\uffff\1\u017b\1\u017c\1\u017d\5\uffff\2\55\2\uffff\1"+
        "\u0184\1\uffff\1\55\1\u0186\3\55\1\uffff\1\55\2\uffff\1\55\1\uffff"+
        "\2\55\1\uffff\1\55\1\uffff\1\55\4\uffff\2\55\1\uffff\2\55\1\u0196"+
        "\1\uffff\1\u0197\6\uffff\1\55\1\u019b\3\uffff\1\55\1\uffff\2\55"+
        "\1\u019f\3\55\1\u01a3\1\55\1\u01a5\2\uffff\1\u01a9\1\55\1\u01ab"+
        "\1\u01ac\4\uffff\1\55\1\uffff\3\55\2\uffff\2\55\1\uffff\1\u01b5"+
        "\1\uffff\1\u01b7\1\u01b8\2\uffff\1\u01b9\4\uffff\3\55\1\u01bf\1"+
        "\u01c0\1\u01c1\10\uffff\1\55\11\uffff\1\55\2\uffff\1\55\1\u01ce"+
        "\1\uffff\1\u01d1\6\uffff";
    static final String DFA21_eofS =
        "\u01d4\uffff";
    static final String DFA21_minS =
        "\1\0\1\116\1\106\1\105\2\101\1\105\1\116\1\101\1\110\1\0\2\105\1"+
        "\122\1\114\2\101\1\111\1\101\1\117\1\110\1\53\1\114\1\115\1\75\1"+
        "\120\3\75\1\174\3\uffff\1\55\1\uffff\1\52\4\uffff\1\55\3\0\5\uffff"+
        "\1\102\1\55\1\106\1\124\1\55\1\105\1\122\1\114\1\115\1\122\1\105"+
        "\1\122\1\126\1\122\1\123\1\117\1\124\1\114\1\122\1\117\1\114\1\40"+
        "\1\55\2\124\1\114\1\124\1\105\2\0\1\uffff\1\124\2\123\1\105\1\103"+
        "\1\125\1\123\1\104\1\126\1\106\1\113\1\123\2\116\1\107\1\127\1\111"+
        "\1\105\1\120\2\uffff\1\114\1\104\1\55\1\114\2\uffff\1\41\13\uffff"+
        "\1\0\1\uffff\1\55\12\uffff\1\0\3\uffff\1\117\1\111\1\117\1\105\1"+
        "\uffff\1\123\1\105\1\131\1\uffff\1\122\1\101\2\105\1\124\2\103\1"+
        "\117\1\122\1\105\1\123\1\103\1\114\1\55\1\123\1\115\1\114\1\116"+
        "\1\105\1\114\1\105\1\uffff\1\12\1\125\1\114\1\110\1\116\2\0\1\127"+
        "\1\124\1\103\1\125\1\101\1\105\1\101\1\105\1\55\1\111\1\123\1\124"+
        "\1\111\1\105\1\124\1\125\1\107\1\110\1\55\2\116\5\55\1\uffff\1\55"+
        "\2\uffff\2\0\1\125\1\126\1\116\1\122\1\105\1\122\2\55\1\111\1\103"+
        "\1\55\1\111\2\105\1\124\1\105\1\50\1\55\1\123\1\110\1\117\1\uffff"+
        "\1\124\2\55\1\117\1\122\1\125\1\122\1\102\1\121\1\116\1\uffff\1"+
        "\122\1\123\1\40\1\105\1\55\2\0\1\105\1\111\1\55\1\120\1\124\1\125"+
        "\1\120\1\114\1\55\1\uffff\1\116\2\55\1\124\2\55\1\123\1\105\1\124"+
        "\1\55\1\uffff\2\55\6\uffff\2\0\1\116\1\117\1\55\1\40\1\124\1\55"+
        "\2\uffff\1\107\1\124\1\uffff\1\124\1\104\1\116\1\55\1\116\2\uffff"+
        "\1\55\1\40\1\127\1\55\4\uffff\1\123\1\104\1\55\4\uffff\1\125\1\55"+
        "\1\101\1\55\1\uffff\1\55\1\uffff\2\0\1\105\1\116\1\uffff\1\40\1"+
        "\105\1\104\1\124\1\55\1\uffff\1\107\3\uffff\1\55\2\uffff\3\55\3"+
        "\uffff\2\0\1\104\1\124\1\uffff\1\102\1\55\1\uffff\1\110\1\55\2\111"+
        "\1\124\1\uffff\1\124\2\uffff\1\111\1\uffff\2\105\1\uffff\1\101\1"+
        "\uffff\1\114\2\uffff\2\0\1\116\1\103\1\uffff\1\122\1\105\1\55\1"+
        "\uffff\1\55\4\uffff\2\0\1\105\1\55\3\uffff\1\124\1\uffff\1\117\1"+
        "\116\1\55\1\40\1\116\1\103\1\55\1\114\1\55\2\0\1\55\1\124\2\55\2"+
        "\uffff\2\0\1\104\1\uffff\1\137\1\116\1\107\2\uffff\1\107\1\124\1"+
        "\uffff\1\55\1\uffff\2\0\2\uffff\1\55\2\uffff\2\0\1\40\1\112\1\40"+
        "\3\55\5\uffff\2\0\1\106\1\117\5\uffff\2\0\2\uffff\1\111\2\0\1\116"+
        "\1\40\1\0\1\55\2\uffff\1\0\3\uffff";
    static final String DFA21_maxS =
        "\1\uffff\1\156\1\166\1\164\1\162\2\165\1\163\1\165\1\151\1\uffff"+
        "\1\145\1\151\1\162\1\170\1\141\2\151\3\157\1\53\1\163\1\155\1\75"+
        "\1\170\1\76\2\75\1\174\3\uffff\1\uff3f\1\uffff\1\57\4\uffff\1\uff3f"+
        "\3\uffff\5\uffff\1\160\1\uff3f\1\146\1\164\1\uff3f\1\145\1\162\1"+
        "\154\1\155\1\162\1\145\1\162\1\166\1\162\1\163\1\157\1\164\2\162"+
        "\1\157\1\154\1\40\1\uff3f\2\164\1\154\1\164\1\145\2\uffff\1\uffff"+
        "\1\164\2\163\1\157\1\143\1\165\1\163\1\144\1\166\1\163\1\155\1\163"+
        "\2\156\1\147\1\167\1\151\1\145\1\160\2\uffff\1\154\1\171\1\uff3f"+
        "\1\154\2\uffff\1\173\13\uffff\1\uffff\1\uffff\1\uff3f\12\uffff\1"+
        "\uffff\3\uffff\1\157\1\151\1\157\1\145\1\uffff\1\163\1\145\1\171"+
        "\1\uffff\1\162\1\141\2\145\1\164\2\143\1\157\1\162\1\164\1\163\1"+
        "\143\1\154\1\uff3f\1\163\1\155\1\154\1\156\1\145\1\154\1\145\1\uffff"+
        "\1\151\1\165\1\154\1\150\1\162\2\uffff\1\167\1\164\1\143\1\165\1"+
        "\141\1\154\1\141\1\145\1\uff3f\1\151\1\163\1\164\1\151\1\145\1\164"+
        "\1\165\1\147\1\150\1\uff3f\2\156\5\uff3f\1\uffff\1\uff3f\2\uffff"+
        "\2\uffff\1\165\1\166\1\156\1\162\1\145\1\162\2\uff3f\1\151\1\143"+
        "\1\uff3f\1\151\2\145\1\164\1\145\1\50\1\uff3f\1\163\1\150\1\157"+
        "\1\uffff\1\164\2\uff3f\1\165\1\162\1\165\1\162\1\154\1\161\1\156"+
        "\1\uffff\1\162\1\163\1\40\1\145\1\uff3f\2\uffff\1\145\1\151\1\uff3f"+
        "\1\160\1\164\1\165\1\160\1\154\1\uff3f\1\uffff\1\156\2\uff3f\1\164"+
        "\2\uff3f\1\163\1\145\1\164\1\uff3f\1\uffff\2\uff3f\6\uffff\2\uffff"+
        "\1\156\1\157\1\uff3f\1\40\1\164\1\uff3f\2\uffff\1\147\1\164\1\uffff"+
        "\1\164\1\144\1\156\1\uff3f\1\156\2\uffff\1\uff3f\1\40\1\167\1\uff3f"+
        "\4\uffff\1\163\1\144\1\uff3f\4\uffff\1\165\1\uff3f\1\141\1\uff3f"+
        "\1\uffff\1\uff3f\1\uffff\2\uffff\1\145\1\156\1\uffff\1\40\1\145"+
        "\1\144\1\164\1\uff3f\1\uffff\1\147\3\uffff\1\uff3f\2\uffff\3\uff3f"+
        "\3\uffff\2\uffff\1\144\1\164\1\uffff\1\163\1\uff3f\1\uffff\1\150"+
        "\1\uff3f\2\151\1\164\1\uffff\1\164\2\uffff\1\151\1\uffff\2\145\1"+
        "\uffff\1\141\1\uffff\1\154\2\uffff\2\uffff\1\156\1\143\1\uffff\1"+
        "\162\1\145\1\uff3f\1\uffff\1\uff3f\4\uffff\2\uffff\1\145\1\uff3f"+
        "\3\uffff\1\164\1\uffff\1\157\1\156\1\uff3f\1\40\1\156\1\143\1\uff3f"+
        "\1\154\1\uff3f\2\uffff\1\uff3f\1\164\2\uff3f\2\uffff\2\uffff\1\144"+
        "\1\uffff\1\137\1\156\1\147\2\uffff\1\147\1\164\1\uffff\1\uff3f\1"+
        "\uffff\2\uffff\2\uffff\1\uff3f\2\uffff\2\uffff\1\40\1\152\1\40\3"+
        "\uff3f\5\uffff\2\uffff\1\160\1\157\5\uffff\2\uffff\2\uffff\1\151"+
        "\2\uffff\1\156\1\40\1\uffff\1\uff3f\2\uffff\1\uffff\3\uffff";
    static final String DFA21_acceptS =
        "\36\uffff\1\134\1\135\1\136\1\uffff\1\140\1\uffff\1\143\1\145\1"+
        "\147\1\152\4\uffff\2\163\1\164\1\166\1\167\36\uffff\1\162\23\uffff"+
        "\1\106\1\133\4\uffff\1\120\1\121\1\uffff\1\122\1\123\1\142\1\124"+
        "\1\144\1\131\1\132\1\146\1\134\1\135\1\136\1\uffff\1\137\1\uffff"+
        "\1\140\1\164\1\165\1\141\1\143\1\145\1\147\1\152\1\157\1\153\1\uffff"+
        "\1\160\1\161\1\166\4\uffff\1\130\3\uffff\1\127\25\uffff\1\126\41"+
        "\uffff\1\125\1\uffff\1\150\1\151\27\uffff\1\114\12\uffff\1\76\20"+
        "\uffff\1\113\12\uffff\1\115\2\uffff\1\116\1\107\1\110\1\111\1\112"+
        "\1\117\10\uffff\1\100\1\101\2\uffff\1\103\5\uffff\1\44\1\64\4\uffff"+
        "\1\67\1\70\1\10\1\33\3\uffff\1\11\1\22\1\41\1\77\4\uffff\1\15\1"+
        "\uffff\1\105\4\uffff\1\65\5\uffff\1\66\1\uffff\1\51\1\74\1\73\1"+
        "\uffff\1\75\1\72\3\uffff\1\102\1\71\1\104\4\uffff\1\62\2\uffff\1"+
        "\56\5\uffff\1\57\1\uffff\1\45\1\7\1\uffff\1\47\2\uffff\1\50\1\uffff"+
        "\1\54\1\uffff\1\55\1\63\4\uffff\1\21\3\uffff\1\46\1\uffff\1\52\1"+
        "\53\1\60\1\61\4\uffff\1\3\1\24\1\42\1\uffff\1\43\17\uffff\1\37\1"+
        "\40\3\uffff\1\36\3\uffff\1\35\1\6\2\uffff\1\32\1\uffff\1\34\2\uffff"+
        "\1\17\1\27\1\uffff\1\31\1\30\10\uffff\1\23\1\16\1\25\1\26\1\20\4"+
        "\uffff\1\5\1\14\1\12\1\13\1\16\2\uffff\1\1\1\2\7\uffff\1\155\1\154"+
        "\1\uffff\1\4\2\156";
    static final String DFA21_specialS =
        "\1\40\11\uffff\1\36\36\uffff\1\41\1\25\1\46\41\uffff\1\43\1\17\50"+
        "\uffff\1\37\14\uffff\1\0\47\uffff\1\42\1\15\36\uffff\1\23\1\1\46"+
        "\uffff\1\50\1\24\35\uffff\1\31\1\14\50\uffff\1\47\1\22\26\uffff"+
        "\1\30\1\13\31\uffff\1\45\1\21\14\uffff\1\27\1\12\20\uffff\1\44\1"+
        "\20\6\uffff\1\26\1\11\14\uffff\1\5\1\16\5\uffff\1\35\1\10\13\uffff"+
        "\1\34\1\7\7\uffff\1\33\1\6\3\uffff\1\32\1\2\2\uffff\1\3\3\uffff"+
        "\1\4\3\uffff}>";
    static final String[] DFA21_transitionS = {
            "\11\60\2\57\2\60\1\57\22\60\1\57\1\30\1\52\1\56\1\31\2\60\1"+
            "\51\1\25\1\36\1\47\1\37\1\40\1\41\1\42\1\43\12\50\2\60\1\32"+
            "\1\44\1\33\2\60\1\26\1\13\1\5\1\14\1\16\1\6\1\15\1\17\1\7\1"+
            "\23\1\54\1\20\1\21\1\10\1\2\1\4\1\54\1\22\1\3\1\24\1\1\1\54"+
            "\1\11\1\27\2\54\1\12\2\60\1\34\1\54\1\53\1\26\1\13\1\5\1\14"+
            "\1\16\1\6\1\15\1\17\1\7\1\23\1\54\1\20\1\21\1\10\1\2\1\4\1\54"+
            "\1\22\1\3\1\24\1\1\1\54\1\11\1\27\2\54\1\45\1\35\1\46\102\60"+
            "\u0190\54\u01c0\60\100\54\u2bf1\60\u00bf\54\u00f0\60\20\54\u0200"+
            "\60\u19c0\54\100\60\u5200\54\u5900\60\u0200\54\u043f\60\1\54"+
            "\u00c0\60",
            "\1\61\37\uffff\1\61",
            "\1\63\7\uffff\1\65\3\uffff\1\62\2\uffff\1\64\1\66\17\uffff"+
            "\1\63\7\uffff\1\65\3\uffff\1\62\2\uffff\1\64\1\66",
            "\1\70\11\uffff\1\71\4\uffff\1\67\20\uffff\1\70\11\uffff\1\71"+
            "\4\uffff\1\67",
            "\1\72\3\uffff\1\74\3\uffff\1\75\10\uffff\1\73\16\uffff\1\72"+
            "\3\uffff\1\74\3\uffff\1\75\10\uffff\1\73",
            "\1\77\20\uffff\1\100\2\uffff\1\76\13\uffff\1\77\20\uffff\1"+
            "\100\2\uffff\1\76",
            "\1\101\3\uffff\1\103\5\uffff\1\102\2\uffff\1\104\2\uffff\1"+
            "\105\17\uffff\1\101\3\uffff\1\103\5\uffff\1\102\2\uffff\1\104"+
            "\2\uffff\1\105",
            "\1\107\4\uffff\1\106\32\uffff\1\107\4\uffff\1\106",
            "\1\111\15\uffff\1\110\5\uffff\1\112\13\uffff\1\111\15\uffff"+
            "\1\110\5\uffff\1\112",
            "\1\114\1\113\36\uffff\1\114\1\113",
            "\102\117\1\115\4\117\1\116\32\117\1\115\4\117\1\116\uff98\117",
            "\1\120\37\uffff\1\120",
            "\1\122\3\uffff\1\121\33\uffff\1\122\3\uffff\1\121",
            "\1\123\37\uffff\1\123",
            "\1\126\1\uffff\1\127\2\uffff\1\125\6\uffff\1\124\23\uffff\1"+
            "\126\1\uffff\1\127\2\uffff\1\125\6\uffff\1\124",
            "\1\130\37\uffff\1\130",
            "\1\133\3\uffff\1\131\3\uffff\1\132\27\uffff\1\133\3\uffff\1"+
            "\131\3\uffff\1\132",
            "\1\134\37\uffff\1\134",
            "\1\135\7\uffff\1\136\5\uffff\1\137\21\uffff\1\135\7\uffff\1"+
            "\136\5\uffff\1\137",
            "\1\140\37\uffff\1\140",
            "\1\141\6\uffff\1\142\30\uffff\1\141\6\uffff\1\142",
            "\1\143",
            "\1\145\1\uffff\1\146\4\uffff\1\147\30\uffff\1\145\1\uffff\1"+
            "\146\4\uffff\1\147",
            "\1\150\37\uffff\1\150",
            "\1\151",
            "\1\153\7\uffff\1\152\37\uffff\1\152",
            "\1\154\1\155",
            "\1\157",
            "\1\161",
            "\1\162",
            "",
            "",
            "",
            "\1\167\2\uffff\12\171\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf"+
            "\55\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55"+
            "\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\1\174\4\uffff\1\173",
            "",
            "",
            "",
            "",
            "\1\55\1\u0082\1\uffff\12\171\7\uffff\32\55\4\uffff\1\55\1\uffff"+
            "\32\55\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf"+
            "\55\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55"+
            "\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "\60\u0085\12\u0084\uffc6\u0085",
            "\0\u0086",
            "\0\117",
            "",
            "",
            "",
            "",
            "",
            "\1\u0088\6\uffff\1\u008a\6\uffff\1\u0089\21\uffff\1\u0088\6"+
            "\uffff\1\u008a\6\uffff\1\u0089",
            "\1\55\2\uffff\12\55\7\uffff\3\55\1\u008b\26\55\4\uffff\1\55"+
            "\1\uffff\3\55\1\u008b\26\55\105\uffff\u0190\55\u01c0\uffff\100"+
            "\55\u2bf1\uffff\u00bf\55\u00f0\uffff\20\55\u0200\uffff\u19c0"+
            "\55\100\uffff\u5200\55\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u008d\37\uffff\1\u008d",
            "\1\u008e\37\uffff\1\u008e",
            "\1\55\2\uffff\12\55\7\uffff\13\55\1\u008f\16\55\4\uffff\1\55"+
            "\1\uffff\13\55\1\u008f\16\55\105\uffff\u0190\55\u01c0\uffff"+
            "\100\55\u2bf1\uffff\u00bf\55\u00f0\uffff\20\55\u0200\uffff\u19c0"+
            "\55\100\uffff\u5200\55\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u0091\37\uffff\1\u0091",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\37\uffff\1\u009a",
            "\1\u009b\37\uffff\1\u009b",
            "\1\u009c\37\uffff\1\u009c",
            "\1\u009d\5\uffff\1\u009e\31\uffff\1\u009d\5\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2",
            "\1\55\2\uffff\12\55\7\uffff\2\55\1\u00a4\12\55\1\u00a5\5\55"+
            "\1\u00a3\6\55\4\uffff\1\55\1\uffff\2\55\1\u00a4\12\55\1\u00a5"+
            "\5\55\1\u00a3\6\55\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1"+
            "\uffff\u00bf\55\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff"+
            "\u5200\55\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u00a7\37\uffff\1\u00a7",
            "\1\u00a8\37\uffff\1\u00a8",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab\37\uffff\1\u00ab",
            "\105\117\1\u00ac\37\117\1\u00ac\uff9a\117",
            "\122\117\1\u00ad\37\117\1\u00ad\uff8d\117",
            "",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "\1\u00b2\11\uffff\1\u00b1\25\uffff\1\u00b2\11\uffff\1\u00b1",
            "\1\u00b3\37\uffff\1\u00b3",
            "\1\u00b4\37\uffff\1\u00b4",
            "\1\u00b5\37\uffff\1\u00b5",
            "\1\u00b6\37\uffff\1\u00b6",
            "\1\u00b7\37\uffff\1\u00b7",
            "\1\u00b9\14\uffff\1\u00b8\22\uffff\1\u00b9\14\uffff\1\u00b8",
            "\1\u00bb\1\uffff\1\u00ba\35\uffff\1\u00bb\1\uffff\1\u00ba",
            "\1\u00bc\37\uffff\1\u00bc",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "\1\u00bf\37\uffff\1\u00bf",
            "\1\u00c0\37\uffff\1\u00c0",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "\1\u00c3\37\uffff\1\u00c3",
            "",
            "",
            "\1\u00c4\37\uffff\1\u00c4",
            "\1\u00c5\24\uffff\1\u00c6\12\uffff\1\u00c5\24\uffff\1\u00c6",
            "\1\55\2\uffff\12\55\7\uffff\2\55\1\u00c7\27\55\4\uffff\1\55"+
            "\1\uffff\2\55\1\u00c7\27\55\105\uffff\u0190\55\u01c0\uffff\100"+
            "\55\u2bf1\uffff\u00bf\55\u00f0\uffff\20\55\u0200\uffff\u19c0"+
            "\55\100\uffff\u5200\55\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u00c9\37\uffff\1\u00c9",
            "",
            "",
            "\1\u00cb\131\uffff\1\u00ca",
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
            "\55\173\1\u00cc\2\173\12\u00cc\7\173\32\u00cc\4\173\1\u00cc"+
            "\1\173\32\u00cc\105\173\u0190\u00cc\u01c0\173\100\u00cc\u2bf1"+
            "\173\u00bf\u00cc\u00f0\173\20\u00cc\u0200\173\u19c0\u00cc\100"+
            "\173\u5200\u00cc\u5900\173\u0200\u00cc\u043f\173\1\u00cc\u00c0"+
            "\173",
            "",
            "\1\55\1\u0082\1\uffff\12\171\7\uffff\32\55\4\uffff\1\55\1\uffff"+
            "\32\55\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf"+
            "\55\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55"+
            "\u5900\uffff\u0200\55\u043f\uffff\1\55",
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
            "\60\u0085\12\u00cd\uffc6\u0085",
            "",
            "",
            "",
            "\1\u00ce\37\uffff\1\u00ce",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\u00d1\37\uffff\1\u00d1",
            "",
            "\1\u00d2\37\uffff\1\u00d2",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\u00d4\37\uffff\1\u00d4",
            "",
            "\1\u00d5\37\uffff\1\u00d5",
            "\1\u00d6\37\uffff\1\u00d6",
            "\1\u00d7\37\uffff\1\u00d7",
            "\1\u00d8\37\uffff\1\u00d8",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\u00da\37\uffff\1\u00da",
            "\1\u00db\37\uffff\1\u00db",
            "\1\u00dc\37\uffff\1\u00dc",
            "\1\u00dd\37\uffff\1\u00dd",
            "\1\u00df\16\uffff\1\u00de\20\uffff\1\u00df\16\uffff\1\u00de",
            "\1\u00e0\37\uffff\1\u00e0",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\u00e2\37\uffff\1\u00e2",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u00e4\37\uffff\1\u00e4",
            "\1\u00e5\37\uffff\1\u00e5",
            "\1\u00e6\37\uffff\1\u00e6",
            "\1\u00e7\37\uffff\1\u00e7",
            "\1\u00e8\37\uffff\1\u00e8",
            "\1\u00e9\37\uffff\1\u00e9",
            "\1\u00ea\37\uffff\1\u00ea",
            "",
            "\1\u00ee\25\uffff\1\u00eb\44\uffff\1\u00ec\3\uffff\1\u00ed"+
            "\33\uffff\1\u00ec\3\uffff\1\u00ed",
            "\1\u00ef\37\uffff\1\u00ef",
            "\1\u00f0\37\uffff\1\u00f0",
            "\1\u00f1\37\uffff\1\u00f1",
            "\1\u00f3\3\uffff\1\u00f2\33\uffff\1\u00f3\3\uffff\1\u00f2",
            "\124\117\1\u00f4\37\117\1\u00f4\uff8b\117",
            "\105\117\1\u00f5\37\117\1\u00f5\uff9a\117",
            "\1\u00f6\37\uffff\1\u00f6",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\u00f8\37\uffff\1\u00f8",
            "\1\u00f9\37\uffff\1\u00f9",
            "\1\u00fa\37\uffff\1\u00fa",
            "\1\u00fc\6\uffff\1\u00fb\30\uffff\1\u00fc\6\uffff\1\u00fb",
            "\1\u00fd\37\uffff\1\u00fd",
            "\1\u00fe\37\uffff\1\u00fe",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u0100\37\uffff\1\u0100",
            "\1\u0101\37\uffff\1\u0101",
            "\1\u0102\37\uffff\1\u0102",
            "\1\u0103\37\uffff\1\u0103",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "\1\u0106\37\uffff\1\u0106",
            "\1\u0107\37\uffff\1\u0107",
            "\1\u0108\37\uffff\1\u0108",
            "\1\55\2\uffff\12\55\7\uffff\22\55\1\u0109\7\55\4\uffff\1\55"+
            "\1\uffff\22\55\1\u0109\7\55\105\uffff\u0190\55\u01c0\uffff\100"+
            "\55\u2bf1\uffff\u00bf\55\u00f0\uffff\20\55\u0200\uffff\u19c0"+
            "\55\100\uffff\u5200\55\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u010b\37\uffff\1\u010b",
            "\1\u010c\37\uffff\1\u010c",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "\55\173\1\u00cc\2\173\12\u00cc\7\173\32\u00cc\4\173\1\u00cc"+
            "\1\173\32\u00cc\105\173\u0190\u00cc\u01c0\173\100\u00cc\u2bf1"+
            "\173\u00bf\u00cc\u00f0\173\20\u00cc\u0200\173\u19c0\u00cc\100"+
            "\173\u5200\u00cc\u5900\173\u0200\u00cc\u043f\173\1\u00cc\u00c0"+
            "\173",
            "\60\u0085\12\u0113\1\u0114\uffc5\u0085",
            "\1\u0115\37\uffff\1\u0115",
            "\1\u0116\37\uffff\1\u0116",
            "\1\u0117\37\uffff\1\u0117",
            "\1\u0118\37\uffff\1\u0118",
            "\1\u0119\37\uffff\1\u0119",
            "\1\u011a\37\uffff\1\u011a",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u011d\37\uffff\1\u011d",
            "\1\u011e\37\uffff\1\u011e",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u0120\37\uffff\1\u0120",
            "\1\u0121\37\uffff\1\u0121",
            "\1\u0122\37\uffff\1\u0122",
            "\1\u0123\37\uffff\1\u0123",
            "\1\u0124\37\uffff\1\u0124",
            "\1\u0125",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u0127\37\uffff\1\u0127",
            "\1\u0128\37\uffff\1\u0128",
            "\1\u0129\37\uffff\1\u0129",
            "",
            "\1\u012a\37\uffff\1\u012a",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u012d\5\uffff\1\u012e\31\uffff\1\u012d\5\uffff\1\u012e",
            "\1\u012f\37\uffff\1\u012f",
            "\1\u0130\37\uffff\1\u0130",
            "\1\u0131\37\uffff\1\u0131",
            "\1\u0132\6\uffff\1\u0134\2\uffff\1\u0133\25\uffff\1\u0132\6"+
            "\uffff\1\u0134\2\uffff\1\u0133",
            "\1\u0136\37\uffff\1\u0136",
            "\1\u0137\37\uffff\1\u0137",
            "",
            "\1\u0138\37\uffff\1\u0138",
            "\1\u0139\37\uffff\1\u0139",
            "\1\u013a",
            "\1\u013b\37\uffff\1\u013b",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\127\117\1\u013d\37\117\1\u013d\uff88\117",
            "\101\117\1\u013e\37\117\1\u013e\uff9e\117",
            "\1\u013f\37\uffff\1\u013f",
            "\1\u0140\37\uffff\1\u0140",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u0142\37\uffff\1\u0142",
            "\1\u0143\37\uffff\1\u0143",
            "\1\u0144\37\uffff\1\u0144",
            "\1\u0145\37\uffff\1\u0145",
            "\1\u0146\37\uffff\1\u0146",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\1\u0148\37\uffff\1\u0148",
            "\1\55\2\uffff\12\55\7\uffff\32\55\2\uffff\1\u0149\1\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1"+
            "\uffff\u00bf\55\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff"+
            "\u5200\55\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u014c\37\uffff\1\u014c",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u014f\37\uffff\1\u014f",
            "\1\u0150\37\uffff\1\u0150",
            "\1\u0151\37\uffff\1\u0151",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "",
            "",
            "",
            "",
            "\60\u0085\12\u0155\uffc6\u0085",
            "\60\u0085\12\u0156\uffc6\u0085",
            "\1\u0157\37\uffff\1\u0157",
            "\1\u0158\37\uffff\1\u0158",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u015a",
            "\1\u015b\37\uffff\1\u015b",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "\1\u015d\37\uffff\1\u015d",
            "\1\u015e\37\uffff\1\u015e",
            "",
            "\1\u015f\37\uffff\1\u015f",
            "\1\u0160\37\uffff\1\u0160",
            "\1\u0161\37\uffff\1\u0161",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u0163\37\uffff\1\u0163",
            "",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u0165",
            "\1\u0166\37\uffff\1\u0166",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "",
            "",
            "\1\u0168\37\uffff\1\u0168",
            "\1\u0169\37\uffff\1\u0169",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "",
            "",
            "\1\u016b\37\uffff\1\u016b",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u016d\37\uffff\1\u016d",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\105\117\1\u0170\37\117\1\u0170\uff9a\117",
            "\124\117\1\u0171\37\117\1\u0171\uff8b\117",
            "\1\u0172\37\uffff\1\u0172",
            "\1\u0173\37\uffff\1\u0173",
            "",
            "\1\u0174",
            "\1\u0175\37\uffff\1\u0175",
            "\1\u0176\37\uffff\1\u0176",
            "\1\u0177\37\uffff\1\u0177",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\1\u0179\37\uffff\1\u0179",
            "",
            "",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "",
            "\55\u0085\1\u017e\uffd2\u0085",
            "\60\u0085\12\u017f\uffc6\u0085",
            "\1\u0180\37\uffff\1\u0180",
            "\1\u0181\37\uffff\1\u0181",
            "",
            "\1\u0183\20\uffff\1\u0182\16\uffff\1\u0183\20\uffff\1\u0182",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\1\u0185\37\uffff\1\u0185",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u0187\37\uffff\1\u0187",
            "\1\u0188\37\uffff\1\u0188",
            "\1\u0189\37\uffff\1\u0189",
            "",
            "\1\u018a\37\uffff\1\u018a",
            "",
            "",
            "\1\u018b\37\uffff\1\u018b",
            "",
            "\1\u018c\37\uffff\1\u018c",
            "\1\u018d\37\uffff\1\u018d",
            "",
            "\1\u018e\37\uffff\1\u018e",
            "",
            "\1\u018f\37\uffff\1\u018f",
            "",
            "",
            "\105\117\1\u0190\37\117\1\u0190\uff9a\117",
            "\105\117\1\u0191\37\117\1\u0191\uff9a\117",
            "\1\u0192\37\uffff\1\u0192",
            "\1\u0193\37\uffff\1\u0193",
            "",
            "\1\u0194\37\uffff\1\u0194",
            "\1\u0195\37\uffff\1\u0195",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "",
            "",
            "\60\u0085\2\u0198\uffce\u0085",
            "\72\u0085\1\u0199\uffc5\u0085",
            "\1\u019a\37\uffff\1\u019a",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "",
            "\1\u019c\37\uffff\1\u019c",
            "",
            "\1\u019d\37\uffff\1\u019d",
            "\1\u019e\37\uffff\1\u019e",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u01a0",
            "\1\u01a1\37\uffff\1\u01a1",
            "\1\u01a2\37\uffff\1\u01a2",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u01a4\37\uffff\1\u01a4",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\116\117\1\u01a6\37\117\1\u01a6\uff91\117",
            "\122\117\1\u01a7\37\117\1\u01a7\uff8d\117",
            "\1\55\2\uffff\12\55\7\uffff\32\55\2\uffff\1\u01a8\1\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1"+
            "\uffff\u00bf\55\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff"+
            "\u5200\55\u5900\uffff\u0200\55\u043f\uffff\1\55",
            "\1\u01aa\37\uffff\1\u01aa",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "\60\u0085\12\u01ad\uffc6\u0085",
            "\60\u0085\2\u01ae\uffce\u0085",
            "\1\u01af\37\uffff\1\u01af",
            "",
            "\1\u01b0",
            "\1\u01b1\37\uffff\1\u01b1",
            "\1\u01b2\37\uffff\1\u01b2",
            "",
            "",
            "\1\u01b3\37\uffff\1\u01b3",
            "\1\u01b4\37\uffff\1\u01b4",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "\135\117\1\u01b6\uffa2\117",
            "\0\117",
            "",
            "",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "\55\u0085\1\u01ba\uffd2\u0085",
            "\60\u0085\12\u01bb\uffc6\u0085",
            "\1\u01bc",
            "\1\u01bd\37\uffff\1\u01bd",
            "\1\u01be",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "",
            "",
            "",
            "\60\u0085\4\u01c3\uffcc\u0085",
            "\56\u0085\1\u01c4\uffd1\u0085",
            "\1\u01c5\11\uffff\1\u01c6\25\uffff\1\u01c5\11\uffff\1\u01c6",
            "\1\u01c7\37\uffff\1\u01c7",
            "",
            "",
            "",
            "",
            "",
            "\60\u0085\12\u01c8\uffc6\u0085",
            "\60\u0085\12\u01c9\uffc6\u0085",
            "",
            "",
            "\1\u01ca\37\uffff\1\u01ca",
            "\47\u0085\1\u01cb\uffd8\u0085",
            "\60\u0085\12\u01cc\uffc6\u0085",
            "\1\u01cd\37\uffff\1\u01cd",
            "\1\u01cf",
            "\60\u0085\12\u01d0\uffc6\u0085",
            "\1\55\2\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55"+
            "\105\uffff\u0190\55\u01c0\uffff\100\55\u2bf1\uffff\u00bf\55"+
            "\u00f0\uffff\20\55\u0200\uffff\u19c0\55\100\uffff\u5200\55\u5900"+
            "\uffff\u0200\55\u043f\uffff\1\55",
            "",
            "",
            "\47\u0085\1\u01d2\uffd8\u0085",
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
            return "1:1: Tokens : ( KEYWORD_102 | KEYWORD_103 | KEYWORD_101 | KEYWORD_100 | KEYWORD_99 | KEYWORD_95 | KEYWORD_96 | KEYWORD_97 | KEYWORD_98 | KEYWORD_90 | KEYWORD_91 | KEYWORD_92 | KEYWORD_93 | KEYWORD_94 | KEYWORD_82 | KEYWORD_83 | KEYWORD_84 | KEYWORD_85 | KEYWORD_86 | KEYWORD_87 | KEYWORD_88 | KEYWORD_89 | KEYWORD_74 | KEYWORD_75 | KEYWORD_76 | KEYWORD_77 | KEYWORD_78 | KEYWORD_79 | KEYWORD_80 | KEYWORD_81 | KEYWORD_69 | KEYWORD_70 | KEYWORD_71 | KEYWORD_72 | KEYWORD_73 | KEYWORD_53 | KEYWORD_54 | KEYWORD_55 | KEYWORD_56 | KEYWORD_57 | KEYWORD_58 | KEYWORD_59 | KEYWORD_60 | KEYWORD_61 | KEYWORD_62 | KEYWORD_63 | KEYWORD_64 | KEYWORD_65 | KEYWORD_66 | KEYWORD_67 | KEYWORD_68 | KEYWORD_35 | KEYWORD_36 | KEYWORD_37 | KEYWORD_38 | KEYWORD_39 | KEYWORD_40 | KEYWORD_41 | KEYWORD_42 | KEYWORD_43 | KEYWORD_44 | KEYWORD_45 | KEYWORD_46 | KEYWORD_47 | KEYWORD_48 | KEYWORD_49 | KEYWORD_50 | KEYWORD_51 | KEYWORD_52 | KEYWORD_25 | KEYWORD_26 | KEYWORD_27 | KEYWORD_28 | KEYWORD_29 | KEYWORD_30 | KEYWORD_31 | KEYWORD_32 | KEYWORD_33 | KEYWORD_34 | KEYWORD_14 | KEYWORD_15 | KEYWORD_16 | KEYWORD_17 | KEYWORD_18 | KEYWORD_19 | KEYWORD_20 | KEYWORD_21 | KEYWORD_22 | KEYWORD_23 | KEYWORD_24 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_11 | KEYWORD_12 | KEYWORD_13 | RULE_JRPARAM | RULE_JRNPARAM | RULE_STAR | RULE_INT | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_SIGNED_DOUBLE | RULE_STRING_ | RULE_STRING | RULE_DBNAME | RULE_ID | RULE_SL_COMMENT | RULE_ML_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA21_132 = input.LA(1);

                        s = -1;
                        if ( ((LA21_132>='0' && LA21_132<='9')) ) {s = 205;}

                        else if ( ((LA21_132>='\u0000' && LA21_132<='/')||(LA21_132>=':' && LA21_132<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA21_205 = input.LA(1);

                        s = -1;
                        if ( ((LA21_205>='0' && LA21_205<='9')) ) {s = 275;}

                        else if ( (LA21_205==':') ) {s = 276;}

                        else if ( ((LA21_205>='\u0000' && LA21_205<='/')||(LA21_205>=';' && LA21_205<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA21_457 = input.LA(1);

                        s = -1;
                        if ( ((LA21_457>='0' && LA21_457<='9')) ) {s = 460;}

                        else if ( ((LA21_457>='\u0000' && LA21_457<='/')||(LA21_457>=':' && LA21_457<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA21_460 = input.LA(1);

                        s = -1;
                        if ( ((LA21_460>='0' && LA21_460<='9')) ) {s = 464;}

                        else if ( ((LA21_460>='\u0000' && LA21_460<='/')||(LA21_460>=':' && LA21_460<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA21_464 = input.LA(1);

                        s = -1;
                        if ( (LA21_464=='\'') ) {s = 466;}

                        else if ( ((LA21_464>='\u0000' && LA21_464<='&')||(LA21_464>='(' && LA21_464<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA21_422 = input.LA(1);

                        s = -1;
                        if ( (LA21_422==']') ) {s = 438;}

                        else if ( ((LA21_422>='\u0000' && LA21_422<='\\')||(LA21_422>='^' && LA21_422<='\uFFFF')) ) {s = 79;}

                        else s = 439;

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA21_452 = input.LA(1);

                        s = -1;
                        if ( ((LA21_452>='0' && LA21_452<='9')) ) {s = 457;}

                        else if ( ((LA21_452>='\u0000' && LA21_452<='/')||(LA21_452>=':' && LA21_452<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA21_443 = input.LA(1);

                        s = -1;
                        if ( (LA21_443=='.') ) {s = 452;}

                        else if ( ((LA21_443>='\u0000' && LA21_443<='-')||(LA21_443>='/' && LA21_443<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA21_430 = input.LA(1);

                        s = -1;
                        if ( ((LA21_430>='0' && LA21_430<='9')) ) {s = 443;}

                        else if ( ((LA21_430>='\u0000' && LA21_430<='/')||(LA21_430>=':' && LA21_430<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA21_409 = input.LA(1);

                        s = -1;
                        if ( ((LA21_409>='0' && LA21_409<='1')) ) {s = 430;}

                        else if ( ((LA21_409>='\u0000' && LA21_409<='/')||(LA21_409>='2' && LA21_409<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA21_383 = input.LA(1);

                        s = -1;
                        if ( (LA21_383==':') ) {s = 409;}

                        else if ( ((LA21_383>='\u0000' && LA21_383<='9')||(LA21_383>=';' && LA21_383<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA21_342 = input.LA(1);

                        s = -1;
                        if ( ((LA21_342>='0' && LA21_342<='9')) ) {s = 383;}

                        else if ( ((LA21_342>='\u0000' && LA21_342<='/')||(LA21_342>=':' && LA21_342<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA21_276 = input.LA(1);

                        s = -1;
                        if ( ((LA21_276>='0' && LA21_276<='9')) ) {s = 342;}

                        else if ( ((LA21_276>='\u0000' && LA21_276<='/')||(LA21_276>=':' && LA21_276<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA21_173 = input.LA(1);

                        s = -1;
                        if ( (LA21_173=='E'||LA21_173=='e') ) {s = 245;}

                        else if ( ((LA21_173>='\u0000' && LA21_173<='D')||(LA21_173>='F' && LA21_173<='d')||(LA21_173>='f' && LA21_173<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA21_423 = input.LA(1);

                        s = -1;
                        if ( ((LA21_423>='\u0000' && LA21_423<='\uFFFF')) ) {s = 79;}

                        else s = 440;

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA21_78 = input.LA(1);

                        s = -1;
                        if ( (LA21_78=='R'||LA21_78=='r') ) {s = 173;}

                        else if ( ((LA21_78>='\u0000' && LA21_78<='Q')||(LA21_78>='S' && LA21_78<='q')||(LA21_78>='s' && LA21_78<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA21_401 = input.LA(1);

                        s = -1;
                        if ( (LA21_401=='R'||LA21_401=='r') ) {s = 423;}

                        else if ( ((LA21_401>='\u0000' && LA21_401<='Q')||(LA21_401>='S' && LA21_401<='q')||(LA21_401>='s' && LA21_401<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA21_369 = input.LA(1);

                        s = -1;
                        if ( (LA21_369=='E'||LA21_369=='e') ) {s = 401;}

                        else if ( ((LA21_369>='\u0000' && LA21_369<='D')||(LA21_369>='F' && LA21_369<='d')||(LA21_369>='f' && LA21_369<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA21_318 = input.LA(1);

                        s = -1;
                        if ( (LA21_318=='T'||LA21_318=='t') ) {s = 369;}

                        else if ( ((LA21_318>='\u0000' && LA21_318<='S')||(LA21_318>='U' && LA21_318<='s')||(LA21_318>='u' && LA21_318<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA21_204 = input.LA(1);

                        s = -1;
                        if ( ((LA21_204>='\u0000' && LA21_204<=',')||(LA21_204>='.' && LA21_204<='/')||(LA21_204>=':' && LA21_204<='@')||(LA21_204>='[' && LA21_204<='^')||LA21_204=='`'||(LA21_204>='{' && LA21_204<='\u00BF')||(LA21_204>='\u0250' && LA21_204<='\u040F')||(LA21_204>='\u0450' && LA21_204<='\u3040')||(LA21_204>='\u3100' && LA21_204<='\u31EF')||(LA21_204>='\u3200' && LA21_204<='\u33FF')||(LA21_204>='\u4DC0' && LA21_204<='\u4DFF')||(LA21_204>='\uA000' && LA21_204<='\uF8FF')||(LA21_204>='\uFB00' && LA21_204<='\uFF3E')||(LA21_204>='\uFF40' && LA21_204<='\uFFFF')) ) {s = 123;}

                        else if ( (LA21_204=='-'||(LA21_204>='0' && LA21_204<='9')||(LA21_204>='A' && LA21_204<='Z')||LA21_204=='_'||(LA21_204>='a' && LA21_204<='z')||(LA21_204>='\u00C0' && LA21_204<='\u024F')||(LA21_204>='\u0410' && LA21_204<='\u044F')||(LA21_204>='\u3041' && LA21_204<='\u30FF')||(LA21_204>='\u31F0' && LA21_204<='\u31FF')||(LA21_204>='\u3400' && LA21_204<='\u4DBF')||(LA21_204>='\u4E00' && LA21_204<='\u9FFF')||(LA21_204>='\uF900' && LA21_204<='\uFAFF')||LA21_204=='\uFF3F') ) {s = 204;}

                        else s = 45;

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA21_245 = input.LA(1);

                        s = -1;
                        if ( (LA21_245=='A'||LA21_245=='a') ) {s = 318;}

                        else if ( ((LA21_245>='\u0000' && LA21_245<='@')||(LA21_245>='B' && LA21_245<='`')||(LA21_245>='b' && LA21_245<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA21_42 = input.LA(1);

                        s = -1;
                        if ( ((LA21_42>='\u0000' && LA21_42<='\uFFFF')) ) {s = 134;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA21_408 = input.LA(1);

                        s = -1;
                        if ( ((LA21_408>='0' && LA21_408<='9')) ) {s = 429;}

                        else if ( ((LA21_408>='\u0000' && LA21_408<='/')||(LA21_408>=':' && LA21_408<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA21_382 = input.LA(1);

                        s = -1;
                        if ( ((LA21_382>='0' && LA21_382<='1')) ) {s = 408;}

                        else if ( ((LA21_382>='\u0000' && LA21_382<='/')||(LA21_382>='2' && LA21_382<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA21_341 = input.LA(1);

                        s = -1;
                        if ( (LA21_341=='-') ) {s = 382;}

                        else if ( ((LA21_341>='\u0000' && LA21_341<=',')||(LA21_341>='.' && LA21_341<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA21_275 = input.LA(1);

                        s = -1;
                        if ( ((LA21_275>='0' && LA21_275<='9')) ) {s = 341;}

                        else if ( ((LA21_275>='\u0000' && LA21_275<='/')||(LA21_275>=':' && LA21_275<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA21_456 = input.LA(1);

                        s = -1;
                        if ( (LA21_456=='\'') ) {s = 459;}

                        else if ( ((LA21_456>='\u0000' && LA21_456<='&')||(LA21_456>='(' && LA21_456<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA21_451 = input.LA(1);

                        s = -1;
                        if ( ((LA21_451>='0' && LA21_451<='9')) ) {s = 456;}

                        else if ( ((LA21_451>='\u0000' && LA21_451<='/')||(LA21_451>=':' && LA21_451<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA21_442 = input.LA(1);

                        s = -1;
                        if ( ((LA21_442>='0' && LA21_442<='3')) ) {s = 451;}

                        else if ( ((LA21_442>='\u0000' && LA21_442<='/')||(LA21_442>='4' && LA21_442<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA21_429 = input.LA(1);

                        s = -1;
                        if ( (LA21_429=='-') ) {s = 442;}

                        else if ( ((LA21_429>='\u0000' && LA21_429<=',')||(LA21_429>='.' && LA21_429<='\uFFFF')) ) {s = 133;}

                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA21_10 = input.LA(1);

                        s = -1;
                        if ( (LA21_10=='B'||LA21_10=='b') ) {s = 77;}

                        else if ( (LA21_10=='G'||LA21_10=='g') ) {s = 78;}

                        else if ( ((LA21_10>='\u0000' && LA21_10<='A')||(LA21_10>='C' && LA21_10<='F')||(LA21_10>='H' && LA21_10<='a')||(LA21_10>='c' && LA21_10<='f')||(LA21_10>='h' && LA21_10<='\uFFFF')) ) {s = 79;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA21_119 = input.LA(1);

                        s = -1;
                        if ( (LA21_119=='-'||(LA21_119>='0' && LA21_119<='9')||(LA21_119>='A' && LA21_119<='Z')||LA21_119=='_'||(LA21_119>='a' && LA21_119<='z')||(LA21_119>='\u00C0' && LA21_119<='\u024F')||(LA21_119>='\u0410' && LA21_119<='\u044F')||(LA21_119>='\u3041' && LA21_119<='\u30FF')||(LA21_119>='\u31F0' && LA21_119<='\u31FF')||(LA21_119>='\u3400' && LA21_119<='\u4DBF')||(LA21_119>='\u4E00' && LA21_119<='\u9FFF')||(LA21_119>='\uF900' && LA21_119<='\uFAFF')||LA21_119=='\uFF3F') ) {s = 204;}

                        else if ( ((LA21_119>='\u0000' && LA21_119<=',')||(LA21_119>='.' && LA21_119<='/')||(LA21_119>=':' && LA21_119<='@')||(LA21_119>='[' && LA21_119<='^')||LA21_119=='`'||(LA21_119>='{' && LA21_119<='\u00BF')||(LA21_119>='\u0250' && LA21_119<='\u040F')||(LA21_119>='\u0450' && LA21_119<='\u3040')||(LA21_119>='\u3100' && LA21_119<='\u31EF')||(LA21_119>='\u3200' && LA21_119<='\u33FF')||(LA21_119>='\u4DC0' && LA21_119<='\u4DFF')||(LA21_119>='\uA000' && LA21_119<='\uF8FF')||(LA21_119>='\uFB00' && LA21_119<='\uFF3E')||(LA21_119>='\uFF40' && LA21_119<='\uFFFF')) ) {s = 123;}

                        else s = 45;

                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA21_0 = input.LA(1);

                        s = -1;
                        if ( (LA21_0=='U'||LA21_0=='u') ) {s = 1;}

                        else if ( (LA21_0=='O'||LA21_0=='o') ) {s = 2;}

                        else if ( (LA21_0=='S'||LA21_0=='s') ) {s = 3;}

                        else if ( (LA21_0=='P'||LA21_0=='p') ) {s = 4;}

                        else if ( (LA21_0=='C'||LA21_0=='c') ) {s = 5;}

                        else if ( (LA21_0=='F'||LA21_0=='f') ) {s = 6;}

                        else if ( (LA21_0=='I'||LA21_0=='i') ) {s = 7;}

                        else if ( (LA21_0=='N'||LA21_0=='n') ) {s = 8;}

                        else if ( (LA21_0=='W'||LA21_0=='w') ) {s = 9;}

                        else if ( (LA21_0=='[') ) {s = 10;}

                        else if ( (LA21_0=='B'||LA21_0=='b') ) {s = 11;}

                        else if ( (LA21_0=='D'||LA21_0=='d') ) {s = 12;}

                        else if ( (LA21_0=='G'||LA21_0=='g') ) {s = 13;}

                        else if ( (LA21_0=='E'||LA21_0=='e') ) {s = 14;}

                        else if ( (LA21_0=='H'||LA21_0=='h') ) {s = 15;}

                        else if ( (LA21_0=='L'||LA21_0=='l') ) {s = 16;}

                        else if ( (LA21_0=='M'||LA21_0=='m') ) {s = 17;}

                        else if ( (LA21_0=='R'||LA21_0=='r') ) {s = 18;}

                        else if ( (LA21_0=='J'||LA21_0=='j') ) {s = 19;}

                        else if ( (LA21_0=='T'||LA21_0=='t') ) {s = 20;}

                        else if ( (LA21_0=='(') ) {s = 21;}

                        else if ( (LA21_0=='A'||LA21_0=='a') ) {s = 22;}

                        else if ( (LA21_0=='X'||LA21_0=='x') ) {s = 23;}

                        else if ( (LA21_0=='!') ) {s = 24;}

                        else if ( (LA21_0=='$') ) {s = 25;}

                        else if ( (LA21_0=='<') ) {s = 26;}

                        else if ( (LA21_0=='>') ) {s = 27;}

                        else if ( (LA21_0=='^') ) {s = 28;}

                        else if ( (LA21_0=='|') ) {s = 29;}

                        else if ( (LA21_0==')') ) {s = 30;}

                        else if ( (LA21_0=='+') ) {s = 31;}

                        else if ( (LA21_0==',') ) {s = 32;}

                        else if ( (LA21_0=='-') ) {s = 33;}

                        else if ( (LA21_0=='.') ) {s = 34;}

                        else if ( (LA21_0=='/') ) {s = 35;}

                        else if ( (LA21_0=='=') ) {s = 36;}

                        else if ( (LA21_0=='{') ) {s = 37;}

                        else if ( (LA21_0=='}') ) {s = 38;}

                        else if ( (LA21_0=='*') ) {s = 39;}

                        else if ( ((LA21_0>='0' && LA21_0<='9')) ) {s = 40;}

                        else if ( (LA21_0=='\'') ) {s = 41;}

                        else if ( (LA21_0=='\"') ) {s = 42;}

                        else if ( (LA21_0=='`') ) {s = 43;}

                        else if ( (LA21_0=='K'||LA21_0=='Q'||LA21_0=='V'||(LA21_0>='Y' && LA21_0<='Z')||LA21_0=='_'||LA21_0=='k'||LA21_0=='q'||LA21_0=='v'||(LA21_0>='y' && LA21_0<='z')||(LA21_0>='\u00C0' && LA21_0<='\u024F')||(LA21_0>='\u0410' && LA21_0<='\u044F')||(LA21_0>='\u3041' && LA21_0<='\u30FF')||(LA21_0>='\u31F0' && LA21_0<='\u31FF')||(LA21_0>='\u3400' && LA21_0<='\u4DBF')||(LA21_0>='\u4E00' && LA21_0<='\u9FFF')||(LA21_0>='\uF900' && LA21_0<='\uFAFF')||LA21_0=='\uFF3F') ) {s = 44;}

                        else if ( (LA21_0=='#') ) {s = 46;}

                        else if ( ((LA21_0>='\t' && LA21_0<='\n')||LA21_0=='\r'||LA21_0==' ') ) {s = 47;}

                        else if ( ((LA21_0>='\u0000' && LA21_0<='\b')||(LA21_0>='\u000B' && LA21_0<='\f')||(LA21_0>='\u000E' && LA21_0<='\u001F')||(LA21_0>='%' && LA21_0<='&')||(LA21_0>=':' && LA21_0<=';')||(LA21_0>='?' && LA21_0<='@')||(LA21_0>='\\' && LA21_0<=']')||(LA21_0>='~' && LA21_0<='\u00BF')||(LA21_0>='\u0250' && LA21_0<='\u040F')||(LA21_0>='\u0450' && LA21_0<='\u3040')||(LA21_0>='\u3100' && LA21_0<='\u31EF')||(LA21_0>='\u3200' && LA21_0<='\u33FF')||(LA21_0>='\u4DC0' && LA21_0<='\u4DFF')||(LA21_0>='\uA000' && LA21_0<='\uF8FF')||(LA21_0>='\uFB00' && LA21_0<='\uFF3E')||(LA21_0>='\uFF40' && LA21_0<='\uFFFF')) ) {s = 48;}

                        else s = 45;

                        if ( s>=0 ) return s;
                        break;
                    case 33 : 
                        int LA21_41 = input.LA(1);

                        s = -1;
                        if ( ((LA21_41>='0' && LA21_41<='9')) ) {s = 132;}

                        else if ( ((LA21_41>='\u0000' && LA21_41<='/')||(LA21_41>=':' && LA21_41<='\uFFFF')) ) {s = 133;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
                    case 34 : 
                        int LA21_172 = input.LA(1);

                        s = -1;
                        if ( (LA21_172=='T'||LA21_172=='t') ) {s = 244;}

                        else if ( ((LA21_172>='\u0000' && LA21_172<='S')||(LA21_172>='U' && LA21_172<='s')||(LA21_172>='u' && LA21_172<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 35 : 
                        int LA21_77 = input.LA(1);

                        s = -1;
                        if ( (LA21_77=='E'||LA21_77=='e') ) {s = 172;}

                        else if ( ((LA21_77>='\u0000' && LA21_77<='D')||(LA21_77>='F' && LA21_77<='d')||(LA21_77>='f' && LA21_77<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 36 : 
                        int LA21_400 = input.LA(1);

                        s = -1;
                        if ( (LA21_400=='N'||LA21_400=='n') ) {s = 422;}

                        else if ( ((LA21_400>='\u0000' && LA21_400<='M')||(LA21_400>='O' && LA21_400<='m')||(LA21_400>='o' && LA21_400<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 37 : 
                        int LA21_368 = input.LA(1);

                        s = -1;
                        if ( (LA21_368=='E'||LA21_368=='e') ) {s = 400;}

                        else if ( ((LA21_368>='\u0000' && LA21_368<='D')||(LA21_368>='F' && LA21_368<='d')||(LA21_368>='f' && LA21_368<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 38 : 
                        int LA21_43 = input.LA(1);

                        s = -1;
                        if ( ((LA21_43>='\u0000' && LA21_43<='\uFFFF')) ) {s = 79;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
                    case 39 : 
                        int LA21_317 = input.LA(1);

                        s = -1;
                        if ( (LA21_317=='E'||LA21_317=='e') ) {s = 368;}

                        else if ( ((LA21_317>='\u0000' && LA21_317<='D')||(LA21_317>='F' && LA21_317<='d')||(LA21_317>='f' && LA21_317<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
                    case 40 : 
                        int LA21_244 = input.LA(1);

                        s = -1;
                        if ( (LA21_244=='W'||LA21_244=='w') ) {s = 317;}

                        else if ( ((LA21_244>='\u0000' && LA21_244<='V')||(LA21_244>='X' && LA21_244<='v')||(LA21_244>='x' && LA21_244<='\uFFFF')) ) {s = 79;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 21, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}