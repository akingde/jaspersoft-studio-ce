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
    public static final int RULE_SIGNED_DOUBLE=135;
    public static final int KEYWORD_67=67;
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
    public static final int KEYWORD_30=100;
    public static final int KEYWORD_105=29;
    public static final int KEYWORD_106=21;
    public static final int KEYWORD_107=22;
    public static final int KEYWORD_108=23;
    public static final int KEYWORD_34=104;
    public static final int KEYWORD_109=24;
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
    public static final int RULE_TIME=137;
    public static final int KEYWORD_28=98;
    public static final int KEYWORD_29=99;
    public static final int RULE_TIMESTAMP=138;
    public static final int RULE_INT=134;
    public static final int KEYWORD_24=116;
    public static final int RULE_DBNAME=141;
    public static final int KEYWORD_25=95;
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
    public static final int KEYWORD_3=119;
    public static final int KEYWORD_70=70;
    public static final int KEYWORD_2=118;
    public static final int RULE_SL_COMMENT=144;
    public static final int RULE_STAR=132;
    public static final int KEYWORD_84=41;
    public static final int KEYWORD_85=42;
    public static final int KEYWORD_82=39;
    public static final int KEYWORD_83=40;
    public static final int KEYWORD_88=45;
    public static final int RULE_JRPARAM=130;
    public static final int KEYWORD_89=46;
    public static final int KEYWORD_86=43;
    public static final int KEYWORD_87=44;
    public static final int KEYWORD_81=56;
    public static final int KEYWORD_80=55;
    public static final int RULE_WS=145;

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

    // $ANTLR start "KEYWORD_125"
    public final void mKEYWORD_125() throws RecognitionException {
        try {
            int _type = KEYWORD_125;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:19:13: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:19:15: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "KEYWORD_125"

    // $ANTLR start "KEYWORD_126"
    public final void mKEYWORD_126() throws RecognitionException {
        try {
            int _type = KEYWORD_126;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:21:13: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:21:15: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "KEYWORD_126"

    // $ANTLR start "KEYWORD_123"
    public final void mKEYWORD_123() throws RecognitionException {
        try {
            int _type = KEYWORD_123;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:23:13: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:23:15: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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

            match('_'); 
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
    // $ANTLR end "KEYWORD_123"

    // $ANTLR start "KEYWORD_124"
    public final void mKEYWORD_124() throws RecognitionException {
        try {
            int _type = KEYWORD_124;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:25:13: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:25:15: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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

            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
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

            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
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
    // $ANTLR end "KEYWORD_124"

    // $ANTLR start "KEYWORD_122"
    public final void mKEYWORD_122() throws RecognitionException {
        try {
            int _type = KEYWORD_122;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:27:13: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'S' | 's' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'S' | 's' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:27:15: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'S' | 's' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'S' | 's' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_122"

    // $ANTLR start "KEYWORD_121"
    public final void mKEYWORD_121() throws RecognitionException {
        try {
            int _type = KEYWORD_121;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:29:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:29:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
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

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
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
    // $ANTLR end "KEYWORD_121"

    // $ANTLR start "KEYWORD_120"
    public final void mKEYWORD_120() throws RecognitionException {
        try {
            int _type = KEYWORD_120;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:31:13: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:31:15: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
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

            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
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
    // $ANTLR end "KEYWORD_120"

    // $ANTLR start "KEYWORD_118"
    public final void mKEYWORD_118() throws RecognitionException {
        try {
            int _type = KEYWORD_118;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:33:13: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:33:15: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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

            match('_'); 
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
    // $ANTLR end "KEYWORD_118"

    // $ANTLR start "KEYWORD_119"
    public final void mKEYWORD_119() throws RecognitionException {
        try {
            int _type = KEYWORD_119;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:35:13: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) '_' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:35:15: ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) '_' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_119"

    // $ANTLR start "KEYWORD_117"
    public final void mKEYWORD_117() throws RecognitionException {
        try {
            int _type = KEYWORD_117;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:37:13: ( ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:37:15: ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_117"

    // $ANTLR start "KEYWORD_110"
    public final void mKEYWORD_110() throws RecognitionException {
        try {
            int _type = KEYWORD_110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:39:13: ( ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ' ' ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:39:15: ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ' ' ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' )
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
    // $ANTLR end "KEYWORD_110"

    // $ANTLR start "KEYWORD_111"
    public final void mKEYWORD_111() throws RecognitionException {
        try {
            int _type = KEYWORD_111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:41:13: ( ( 'F' | 'f' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) ' ' ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:41:15: ( 'F' | 'f' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) ' ' ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_111"

    // $ANTLR start "KEYWORD_112"
    public final void mKEYWORD_112() throws RecognitionException {
        try {
            int _type = KEYWORD_112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:43:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:43:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
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

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_112"

    // $ANTLR start "KEYWORD_113"
    public final void mKEYWORD_113() throws RecognitionException {
        try {
            int _type = KEYWORD_113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:45:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:45:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
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

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
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
    // $ANTLR end "KEYWORD_113"

    // $ANTLR start "KEYWORD_114"
    public final void mKEYWORD_114() throws RecognitionException {
        try {
            int _type = KEYWORD_114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:47:13: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:47:15: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_114"

    // $ANTLR start "KEYWORD_115"
    public final void mKEYWORD_115() throws RecognitionException {
        try {
            int _type = KEYWORD_115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:49:13: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:49:15: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "KEYWORD_115"

    // $ANTLR start "KEYWORD_116"
    public final void mKEYWORD_116() throws RecognitionException {
        try {
            int _type = KEYWORD_116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:51:13: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:51:15: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_116"

    // $ANTLR start "KEYWORD_106"
    public final void mKEYWORD_106() throws RecognitionException {
        try {
            int _type = KEYWORD_106;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:53:13: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:53:15: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
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

            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_106"

    // $ANTLR start "KEYWORD_107"
    public final void mKEYWORD_107() throws RecognitionException {
        try {
            int _type = KEYWORD_107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:55:13: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:55:15: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
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

            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
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
    // $ANTLR end "KEYWORD_107"

    // $ANTLR start "KEYWORD_108"
    public final void mKEYWORD_108() throws RecognitionException {
        try {
            int _type = KEYWORD_108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:57:13: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:57:15: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_108"

    // $ANTLR start "KEYWORD_109"
    public final void mKEYWORD_109() throws RecognitionException {
        try {
            int _type = KEYWORD_109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:59:13: ( ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:59:15: ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' )
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
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

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_109"

    // $ANTLR start "KEYWORD_101"
    public final void mKEYWORD_101() throws RecognitionException {
        try {
            int _type = KEYWORD_101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:61:13: ( ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:61:15: ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "KEYWORD_101"

    // $ANTLR start "KEYWORD_102"
    public final void mKEYWORD_102() throws RecognitionException {
        try {
            int _type = KEYWORD_102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:63:13: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:63:15: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_102"

    // $ANTLR start "KEYWORD_103"
    public final void mKEYWORD_103() throws RecognitionException {
        try {
            int _type = KEYWORD_103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:65:13: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:65:15: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "KEYWORD_103"

    // $ANTLR start "KEYWORD_104"
    public final void mKEYWORD_104() throws RecognitionException {
        try {
            int _type = KEYWORD_104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:67:13: ( ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'H' | 'h' ) ' ' ( 'T' | 't' ) ( 'I' | 'i' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:67:15: ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'H' | 'h' ) ' ' ( 'T' | 't' ) ( 'I' | 'i' ) ( 'E' | 'e' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_104"

    // $ANTLR start "KEYWORD_105"
    public final void mKEYWORD_105() throws RecognitionException {
        try {
            int _type = KEYWORD_105;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:69:13: ( '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:69:15: '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
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
    // $ANTLR end "KEYWORD_105"

    // $ANTLR start "KEYWORD_92"
    public final void mKEYWORD_92() throws RecognitionException {
        try {
            int _type = KEYWORD_92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:71:12: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:71:14: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
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
    // $ANTLR end "KEYWORD_92"

    // $ANTLR start "KEYWORD_93"
    public final void mKEYWORD_93() throws RecognitionException {
        try {
            int _type = KEYWORD_93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:73:12: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:73:14: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
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

            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('_'); 
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
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
    // $ANTLR end "KEYWORD_93"

    // $ANTLR start "KEYWORD_94"
    public final void mKEYWORD_94() throws RecognitionException {
        try {
            int _type = KEYWORD_94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:75:12: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:75:14: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_94"

    // $ANTLR start "KEYWORD_95"
    public final void mKEYWORD_95() throws RecognitionException {
        try {
            int _type = KEYWORD_95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:77:12: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:77:14: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_95"

    // $ANTLR start "KEYWORD_96"
    public final void mKEYWORD_96() throws RecognitionException {
        try {
            int _type = KEYWORD_96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:79:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:79:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_96"

    // $ANTLR start "KEYWORD_97"
    public final void mKEYWORD_97() throws RecognitionException {
        try {
            int _type = KEYWORD_97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:81:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:81:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_97"

    // $ANTLR start "KEYWORD_98"
    public final void mKEYWORD_98() throws RecognitionException {
        try {
            int _type = KEYWORD_98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:83:12: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:83:14: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_98"

    // $ANTLR start "KEYWORD_99"
    public final void mKEYWORD_99() throws RecognitionException {
        try {
            int _type = KEYWORD_99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:85:12: ( '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:85:14: '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_99"

    // $ANTLR start "KEYWORD_100"
    public final void mKEYWORD_100() throws RecognitionException {
        try {
            int _type = KEYWORD_100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:87:13: ( '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:87:15: '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_100"

    // $ANTLR start "KEYWORD_82"
    public final void mKEYWORD_82() throws RecognitionException {
        try {
            int _type = KEYWORD_82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:89:12: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:89:14: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_82"

    // $ANTLR start "KEYWORD_83"
    public final void mKEYWORD_83() throws RecognitionException {
        try {
            int _type = KEYWORD_83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:91:12: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:91:14: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_83"

    // $ANTLR start "KEYWORD_84"
    public final void mKEYWORD_84() throws RecognitionException {
        try {
            int _type = KEYWORD_84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:93:12: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:93:14: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_84"

    // $ANTLR start "KEYWORD_85"
    public final void mKEYWORD_85() throws RecognitionException {
        try {
            int _type = KEYWORD_85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:95:12: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:95:14: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_85"

    // $ANTLR start "KEYWORD_86"
    public final void mKEYWORD_86() throws RecognitionException {
        try {
            int _type = KEYWORD_86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:97:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:97:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_86"

    // $ANTLR start "KEYWORD_87"
    public final void mKEYWORD_87() throws RecognitionException {
        try {
            int _type = KEYWORD_87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:99:12: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:99:14: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_87"

    // $ANTLR start "KEYWORD_88"
    public final void mKEYWORD_88() throws RecognitionException {
        try {
            int _type = KEYWORD_88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:101:12: ( ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:101:14: ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_88"

    // $ANTLR start "KEYWORD_89"
    public final void mKEYWORD_89() throws RecognitionException {
        try {
            int _type = KEYWORD_89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:103:12: ( ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:103:14: ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_89"

    // $ANTLR start "KEYWORD_90"
    public final void mKEYWORD_90() throws RecognitionException {
        try {
            int _type = KEYWORD_90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:105:12: ( ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:105:14: ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
            {
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
    // $ANTLR end "KEYWORD_90"

    // $ANTLR start "KEYWORD_91"
    public final void mKEYWORD_91() throws RecognitionException {
        try {
            int _type = KEYWORD_91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:107:12: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:107:14: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_91"

    // $ANTLR start "KEYWORD_74"
    public final void mKEYWORD_74() throws RecognitionException {
        try {
            int _type = KEYWORD_74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:109:12: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:109:14: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_74"

    // $ANTLR start "KEYWORD_75"
    public final void mKEYWORD_75() throws RecognitionException {
        try {
            int _type = KEYWORD_75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:111:12: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:111:14: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_75"

    // $ANTLR start "KEYWORD_76"
    public final void mKEYWORD_76() throws RecognitionException {
        try {
            int _type = KEYWORD_76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:113:12: ( ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:113:14: ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "KEYWORD_76"

    // $ANTLR start "KEYWORD_77"
    public final void mKEYWORD_77() throws RecognitionException {
        try {
            int _type = KEYWORD_77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:115:12: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:115:14: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:117:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:117:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_78"

    // $ANTLR start "KEYWORD_79"
    public final void mKEYWORD_79() throws RecognitionException {
        try {
            int _type = KEYWORD_79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:119:12: ( ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:119:14: ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_79"

    // $ANTLR start "KEYWORD_80"
    public final void mKEYWORD_80() throws RecognitionException {
        try {
            int _type = KEYWORD_80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:121:12: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:121:14: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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

            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
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
    // $ANTLR end "KEYWORD_80"

    // $ANTLR start "KEYWORD_81"
    public final void mKEYWORD_81() throws RecognitionException {
        try {
            int _type = KEYWORD_81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:123:12: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:123:14: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_81"

    // $ANTLR start "KEYWORD_57"
    public final void mKEYWORD_57() throws RecognitionException {
        try {
            int _type = KEYWORD_57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:125:12: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) '(' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:125:14: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) '('
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
    // $ANTLR end "KEYWORD_57"

    // $ANTLR start "KEYWORD_58"
    public final void mKEYWORD_58() throws RecognitionException {
        try {
            int _type = KEYWORD_58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:127:12: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:127:14: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_58"

    // $ANTLR start "KEYWORD_59"
    public final void mKEYWORD_59() throws RecognitionException {
        try {
            int _type = KEYWORD_59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:129:12: ( ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:129:14: ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_59"

    // $ANTLR start "KEYWORD_60"
    public final void mKEYWORD_60() throws RecognitionException {
        try {
            int _type = KEYWORD_60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:131:12: ( ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:131:14: ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_60"

    // $ANTLR start "KEYWORD_61"
    public final void mKEYWORD_61() throws RecognitionException {
        try {
            int _type = KEYWORD_61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:133:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:133:14: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_61"

    // $ANTLR start "KEYWORD_62"
    public final void mKEYWORD_62() throws RecognitionException {
        try {
            int _type = KEYWORD_62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:135:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:135:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']'
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
    // $ANTLR end "KEYWORD_62"

    // $ANTLR start "KEYWORD_63"
    public final void mKEYWORD_63() throws RecognitionException {
        try {
            int _type = KEYWORD_63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:137:12: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:137:14: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_63"

    // $ANTLR start "KEYWORD_64"
    public final void mKEYWORD_64() throws RecognitionException {
        try {
            int _type = KEYWORD_64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:139:12: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:139:14: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_64"

    // $ANTLR start "KEYWORD_65"
    public final void mKEYWORD_65() throws RecognitionException {
        try {
            int _type = KEYWORD_65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:141:12: ( ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:141:14: ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:143:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:143:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_66"

    // $ANTLR start "KEYWORD_67"
    public final void mKEYWORD_67() throws RecognitionException {
        try {
            int _type = KEYWORD_67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:145:12: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:145:14: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_67"

    // $ANTLR start "KEYWORD_68"
    public final void mKEYWORD_68() throws RecognitionException {
        try {
            int _type = KEYWORD_68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:147:12: ( ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:147:14: ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_68"

    // $ANTLR start "KEYWORD_69"
    public final void mKEYWORD_69() throws RecognitionException {
        try {
            int _type = KEYWORD_69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:149:12: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:149:14: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_69"

    // $ANTLR start "KEYWORD_70"
    public final void mKEYWORD_70() throws RecognitionException {
        try {
            int _type = KEYWORD_70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:151:12: ( ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:151:14: ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_70"

    // $ANTLR start "KEYWORD_71"
    public final void mKEYWORD_71() throws RecognitionException {
        try {
            int _type = KEYWORD_71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:153:12: ( ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:153:14: ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_71"

    // $ANTLR start "KEYWORD_72"
    public final void mKEYWORD_72() throws RecognitionException {
        try {
            int _type = KEYWORD_72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:155:12: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:155:14: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_72"

    // $ANTLR start "KEYWORD_73"
    public final void mKEYWORD_73() throws RecognitionException {
        try {
            int _type = KEYWORD_73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:157:12: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:157:14: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_73"

    // $ANTLR start "KEYWORD_36"
    public final void mKEYWORD_36() throws RecognitionException {
        try {
            int _type = KEYWORD_36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:159:12: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:159:14: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_36"

    // $ANTLR start "KEYWORD_37"
    public final void mKEYWORD_37() throws RecognitionException {
        try {
            int _type = KEYWORD_37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:161:12: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:161:14: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
    // $ANTLR end "KEYWORD_37"

    // $ANTLR start "KEYWORD_38"
    public final void mKEYWORD_38() throws RecognitionException {
        try {
            int _type = KEYWORD_38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:163:12: ( ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:163:14: ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_38"

    // $ANTLR start "KEYWORD_39"
    public final void mKEYWORD_39() throws RecognitionException {
        try {
            int _type = KEYWORD_39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:12: ( ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:165:14: ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' )
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
    // $ANTLR end "KEYWORD_39"

    // $ANTLR start "KEYWORD_40"
    public final void mKEYWORD_40() throws RecognitionException {
        try {
            int _type = KEYWORD_40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:167:12: ( ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:167:14: ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_40"

    // $ANTLR start "KEYWORD_41"
    public final void mKEYWORD_41() throws RecognitionException {
        try {
            int _type = KEYWORD_41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:169:12: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:169:14: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
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
    // $ANTLR end "KEYWORD_41"

    // $ANTLR start "KEYWORD_42"
    public final void mKEYWORD_42() throws RecognitionException {
        try {
            int _type = KEYWORD_42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:12: ( ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:171:14: ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_42"

    // $ANTLR start "KEYWORD_43"
    public final void mKEYWORD_43() throws RecognitionException {
        try {
            int _type = KEYWORD_43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:12: ( ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:173:14: ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_43"

    // $ANTLR start "KEYWORD_44"
    public final void mKEYWORD_44() throws RecognitionException {
        try {
            int _type = KEYWORD_44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:175:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:175:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' )
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
    // $ANTLR end "KEYWORD_44"

    // $ANTLR start "KEYWORD_45"
    public final void mKEYWORD_45() throws RecognitionException {
        try {
            int _type = KEYWORD_45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:177:12: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:177:14: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_45"

    // $ANTLR start "KEYWORD_46"
    public final void mKEYWORD_46() throws RecognitionException {
        try {
            int _type = KEYWORD_46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:179:12: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:179:14: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_46"

    // $ANTLR start "KEYWORD_47"
    public final void mKEYWORD_47() throws RecognitionException {
        try {
            int _type = KEYWORD_47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:181:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) '\\n' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:181:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) '\\n'
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
    // $ANTLR end "KEYWORD_47"

    // $ANTLR start "KEYWORD_48"
    public final void mKEYWORD_48() throws RecognitionException {
        try {
            int _type = KEYWORD_48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:183:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:183:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' '
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
    // $ANTLR end "KEYWORD_48"

    // $ANTLR start "KEYWORD_49"
    public final void mKEYWORD_49() throws RecognitionException {
        try {
            int _type = KEYWORD_49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:185:12: ( ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:185:14: ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'Y' | 'y' )
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
    // $ANTLR end "KEYWORD_49"

    // $ANTLR start "KEYWORD_50"
    public final void mKEYWORD_50() throws RecognitionException {
        try {
            int _type = KEYWORD_50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:187:12: ( ( 'O' | 'o' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:187:14: ( 'O' | 'o' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_50"

    // $ANTLR start "KEYWORD_51"
    public final void mKEYWORD_51() throws RecognitionException {
        try {
            int _type = KEYWORD_51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:189:12: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:189:14: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'S' | 's' )
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
    // $ANTLR end "KEYWORD_51"

    // $ANTLR start "KEYWORD_52"
    public final void mKEYWORD_52() throws RecognitionException {
        try {
            int _type = KEYWORD_52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:191:12: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:191:14: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' )
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
    // $ANTLR end "KEYWORD_52"

    // $ANTLR start "KEYWORD_53"
    public final void mKEYWORD_53() throws RecognitionException {
        try {
            int _type = KEYWORD_53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:193:12: ( ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:193:14: ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_53"

    // $ANTLR start "KEYWORD_54"
    public final void mKEYWORD_54() throws RecognitionException {
        try {
            int _type = KEYWORD_54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:195:12: ( ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'K' | 'k' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:195:14: ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'K' | 'k' )
            {
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

            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:197:12: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:197:14: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "KEYWORD_55"

    // $ANTLR start "KEYWORD_56"
    public final void mKEYWORD_56() throws RecognitionException {
        try {
            int _type = KEYWORD_56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:199:12: ( ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:199:14: ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
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
    // $ANTLR end "KEYWORD_56"

    // $ANTLR start "KEYWORD_25"
    public final void mKEYWORD_25() throws RecognitionException {
        try {
            int _type = KEYWORD_25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:201:12: ( '(' '+' ')' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:201:14: '(' '+' ')'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:203:12: ( ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:203:14: ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:205:12: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:205:14: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:207:12: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:207:14: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:209:12: ( ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:209:14: ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:211:12: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:211:14: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
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
    // $ANTLR end "KEYWORD_30"

    // $ANTLR start "KEYWORD_31"
    public final void mKEYWORD_31() throws RecognitionException {
        try {
            int _type = KEYWORD_31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:213:12: ( ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:213:14: ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "KEYWORD_31"

    // $ANTLR start "KEYWORD_32"
    public final void mKEYWORD_32() throws RecognitionException {
        try {
            int _type = KEYWORD_32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:215:12: ( ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:215:14: ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'R' | 'r' )
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
    // $ANTLR end "KEYWORD_32"

    // $ANTLR start "KEYWORD_33"
    public final void mKEYWORD_33() throws RecognitionException {
        try {
            int _type = KEYWORD_33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:217:12: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:217:14: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' )
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
    // $ANTLR end "KEYWORD_33"

    // $ANTLR start "KEYWORD_34"
    public final void mKEYWORD_34() throws RecognitionException {
        try {
            int _type = KEYWORD_34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:219:12: ( ( 'T' | 't' ) ( 'O' | 'o' ) ( 'P' | 'p' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:219:14: ( 'T' | 't' ) ( 'O' | 'o' ) ( 'P' | 'p' )
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
    // $ANTLR end "KEYWORD_34"

    // $ANTLR start "KEYWORD_35"
    public final void mKEYWORD_35() throws RecognitionException {
        try {
            int _type = KEYWORD_35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:221:12: ( ( 'X' | 'x' ) ( 'M' | 'm' ) ( 'L' | 'l' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:221:14: ( 'X' | 'x' ) ( 'M' | 'm' ) ( 'L' | 'l' )
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
    // $ANTLR end "KEYWORD_35"

    // $ANTLR start "KEYWORD_14"
    public final void mKEYWORD_14() throws RecognitionException {
        try {
            int _type = KEYWORD_14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:223:12: ( '!' '=' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:223:14: '!' '='
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:225:12: ( '$' ( 'X' | 'x' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:225:14: '$' ( 'X' | 'x' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:227:12: ( '<' '=' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:227:14: '<' '='
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:229:12: ( '<' '>' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:229:14: '<' '>'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:231:12: ( '>' '=' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:231:14: '>' '='
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:233:12: ( ( 'A' | 'a' ) ( 'S' | 's' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:233:14: ( 'A' | 'a' ) ( 'S' | 's' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:235:12: ( ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:235:14: ( 'I' | 'i' ) ( 'N' | 'n' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:237:12: ( ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:237:14: ( 'O' | 'o' ) ( 'N' | 'n' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:239:12: ( ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:239:14: ( 'O' | 'o' ) ( 'R' | 'r' )
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:241:12: ( '^' '=' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:241:14: '^' '='
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:243:12: ( '|' '|' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:243:14: '|' '|'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:245:11: ( '(' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:245:13: '('
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:247:11: ( ')' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:247:13: ')'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:249:11: ( '+' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:249:13: '+'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:251:11: ( ',' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:251:13: ','
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:253:11: ( '-' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:253:13: '-'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:255:11: ( '.' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:255:13: '.'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:257:11: ( '/' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:257:13: '/'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:259:11: ( '<' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:259:13: '<'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:261:11: ( '=' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:261:13: '='
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:263:12: ( '>' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:263:14: '>'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:265:12: ( '{' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:265:14: '{'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:267:12: ( '|' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:267:14: '|'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:269:12: ( '}' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:269:14: '}'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:273:14: ( '$P{' ( options {greedy=false; } : . )* '}' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:273:16: '$P{' ( options {greedy=false; } : . )* '}'
            {
            match("$P{"); 

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:273:22: ( options {greedy=false; } : . )*
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
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:273:50: .
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:275:15: ( '$P!{' ( options {greedy=false; } : . )* '}' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:275:17: '$P!{' ( options {greedy=false; } : . )* '}'
            {
            match("$P!{"); 

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:275:24: ( options {greedy=false; } : . )*
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
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:275:52: .
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:277:11: ( '*' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:277:13: '*'
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

    // $ANTLR start "RULE_UNSIGNED"
    public final void mRULE_UNSIGNED() throws RecognitionException {
        try {
            int _type = RULE_UNSIGNED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:279:15: ( ( '0' .. '9' )+ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:279:17: ( '0' .. '9' )+
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:279:17: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:279:18: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_UNSIGNED"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:281:10: ( ( '-' )? RULE_UNSIGNED )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:281:12: ( '-' )? RULE_UNSIGNED
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:281:12: ( '-' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:281:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            mRULE_UNSIGNED(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_SIGNED_DOUBLE"
    public final void mRULE_SIGNED_DOUBLE() throws RecognitionException {
        try {
            int _type = RULE_SIGNED_DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:283:20: ( ( '-' )? RULE_UNSIGNED ( '.' RULE_UNSIGNED )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:283:22: ( '-' )? RULE_UNSIGNED ( '.' RULE_UNSIGNED )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:283:22: ( '-' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='-') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:283:22: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            mRULE_UNSIGNED(); 
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:283:41: ( '.' RULE_UNSIGNED )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:283:42: '.' RULE_UNSIGNED
                    {
                    match('.'); 
                    mRULE_UNSIGNED(); 

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

    // $ANTLR start "RULE_TIMESTAMP"
    public final void mRULE_TIMESTAMP() throws RecognitionException {
        try {
            int _type = RULE_TIMESTAMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:285:16: ( RULE_DATE ' ' RULE_TIME )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:285:18: RULE_DATE ' ' RULE_TIME
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:287:11: ( '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\'' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:287:13: '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\''
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:289:11: ( '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\'' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:289:13: '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\''
            {
            match('\''); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            match(':'); 
            matchRange('0','9'); 
            matchRange('0','9'); 
            match(':'); 
            matchRange('0','9'); 
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

    // $ANTLR start "RULE_STRING_"
    public final void mRULE_STRING_() throws RecognitionException {
        try {
            int _type = RULE_STRING_;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:291:14: ( '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:291:16: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
            {
            match('\''); 
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:291:21: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
            loop7:
            do {
                int alt7=3;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\\') ) {
                    alt7=1;
                }
                else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                    alt7=2;
                }


                switch (alt7) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:291:22: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:291:67: ~ ( ( '\\\\' | '\\'' ) )
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
            	    break loop7;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:293:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:293:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:293:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
            loop8:
            do {
                int alt8=3;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='\\') ) {
                    alt8=1;
                }
                else if ( ((LA8_0>='\u0000' && LA8_0<='!')||(LA8_0>='#' && LA8_0<='[')||(LA8_0>=']' && LA8_0<='\uFFFF')) ) {
                    alt8=2;
                }


                switch (alt8) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:293:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:293:65: ~ ( ( '\\\\' | '\"' ) )
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
            	    break loop8;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:13: ( ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' ) )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:15: ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' )
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:15: ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='`') ) {
                alt11=1;
            }
            else if ( (LA11_0=='[') ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:16: '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`'
                    {
                    match('`'); 
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )*
                    loop9:
                    do {
                        int alt9=3;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0=='\\') ) {
                            alt9=1;
                        }
                        else if ( ((LA9_0>='\u0000' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='_')||(LA9_0>='a' && LA9_0<='\uFFFF')) ) {
                            alt9=2;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:66: ~ ( ( '\\\\' | '`' ) )
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
                    	    break loop9;
                        }
                    } while (true);

                    match('`'); 

                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:86: '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']'
                    {
                    match('['); 
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:90: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )*
                    loop10:
                    do {
                        int alt10=3;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0=='\\') ) {
                            alt10=1;
                        }
                        else if ( ((LA10_0>='\u0000' && LA10_0<='[')||(LA10_0>='^' && LA10_0<='\uFFFF')) ) {
                            alt10=2;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:91: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:295:136: ~ ( ( '\\\\' | ']' ) )
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
                    	    break loop10;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:297:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' ) ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )* )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:297:11: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' ) ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )*
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u024F')||(input.LA(1)>='\u0410' && input.LA(1)<='\u044F')||(input.LA(1)>='\u3041' && input.LA(1)<='\u30FF')||(input.LA(1)>='\u31F0' && input.LA(1)<='\u31FF')||(input.LA(1)>='\u3400' && input.LA(1)<='\u4DBF')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF')||input.LA(1)=='\uFF3F' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:297:276: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='$'||LA12_0=='-'||(LA12_0>='0' && LA12_0<='9')||(LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')||(LA12_0>='\u00C0' && LA12_0<='\u024F')||(LA12_0>='\u0410' && LA12_0<='\u044F')||(LA12_0>='\u3041' && LA12_0<='\u30FF')||(LA12_0>='\u31F0' && LA12_0<='\u31FF')||(LA12_0>='\u3400' && LA12_0<='\u4DBF')||(LA12_0>='\u4E00' && LA12_0<='\u9FFF')||(LA12_0>='\uF900' && LA12_0<='\uFAFF')||LA12_0=='\uFF3F') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:
            	    {
            	    if ( input.LA(1)=='$'||input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u024F')||(input.LA(1)>='\u0410' && input.LA(1)<='\u044F')||(input.LA(1)>='\u3041' && input.LA(1)<='\u30FF')||(input.LA(1)>='\u31F0' && input.LA(1)<='\u31FF')||(input.LA(1)>='\u3400' && input.LA(1)<='\u4DBF')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF')||input.LA(1)=='\uFF3F' ) {
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

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:299:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:299:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:299:24: ( options {greedy=false; } : . )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0=='*') ) {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1=='/') ) {
                        alt13=2;
                    }
                    else if ( ((LA13_1>='\u0000' && LA13_1<='.')||(LA13_1>='0' && LA13_1<='\uFFFF')) ) {
                        alt13=1;
                    }


                }
                else if ( ((LA13_0>='\u0000' && LA13_0<=')')||(LA13_0>='+' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:299:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop13;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:17: ( ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:19: ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:19: ( '--' | '#' | '//' )
            int alt14=3;
            switch ( input.LA(1) ) {
            case '-':
                {
                alt14=1;
                }
                break;
            case '#':
                {
                alt14=2;
                }
                break;
            case '/':
                {
                alt14=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:20: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:25: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:29: '//'
                    {
                    match("//"); 


                    }
                    break;

            }

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:35: (~ ( ( '\\n' | '\\r' ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:35: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop15;
                }
            } while (true);

            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:51: ( ( '\\r' )? '\\n' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='\n'||LA17_0=='\r') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:52: ( '\\r' )? '\\n'
                    {
                    // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:52: ( '\\r' )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='\r') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:301:52: '\\r'
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:303:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:303:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:303:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>='\t' && LA18_0<='\n')||LA18_0=='\r'||LA18_0==' ') ) {
                    alt18=1;
                }


                switch (alt18) {
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
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
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
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:305:16: ( . )
            // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:305:18: .
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
        // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:8: ( KEYWORD_125 | KEYWORD_126 | KEYWORD_123 | KEYWORD_124 | KEYWORD_122 | KEYWORD_121 | KEYWORD_120 | KEYWORD_118 | KEYWORD_119 | KEYWORD_117 | KEYWORD_110 | KEYWORD_111 | KEYWORD_112 | KEYWORD_113 | KEYWORD_114 | KEYWORD_115 | KEYWORD_116 | KEYWORD_106 | KEYWORD_107 | KEYWORD_108 | KEYWORD_109 | KEYWORD_101 | KEYWORD_102 | KEYWORD_103 | KEYWORD_104 | KEYWORD_105 | KEYWORD_92 | KEYWORD_93 | KEYWORD_94 | KEYWORD_95 | KEYWORD_96 | KEYWORD_97 | KEYWORD_98 | KEYWORD_99 | KEYWORD_100 | KEYWORD_82 | KEYWORD_83 | KEYWORD_84 | KEYWORD_85 | KEYWORD_86 | KEYWORD_87 | KEYWORD_88 | KEYWORD_89 | KEYWORD_90 | KEYWORD_91 | KEYWORD_74 | KEYWORD_75 | KEYWORD_76 | KEYWORD_77 | KEYWORD_78 | KEYWORD_79 | KEYWORD_80 | KEYWORD_81 | KEYWORD_57 | KEYWORD_58 | KEYWORD_59 | KEYWORD_60 | KEYWORD_61 | KEYWORD_62 | KEYWORD_63 | KEYWORD_64 | KEYWORD_65 | KEYWORD_66 | KEYWORD_67 | KEYWORD_68 | KEYWORD_69 | KEYWORD_70 | KEYWORD_71 | KEYWORD_72 | KEYWORD_73 | KEYWORD_36 | KEYWORD_37 | KEYWORD_38 | KEYWORD_39 | KEYWORD_40 | KEYWORD_41 | KEYWORD_42 | KEYWORD_43 | KEYWORD_44 | KEYWORD_45 | KEYWORD_46 | KEYWORD_47 | KEYWORD_48 | KEYWORD_49 | KEYWORD_50 | KEYWORD_51 | KEYWORD_52 | KEYWORD_53 | KEYWORD_54 | KEYWORD_55 | KEYWORD_56 | KEYWORD_25 | KEYWORD_26 | KEYWORD_27 | KEYWORD_28 | KEYWORD_29 | KEYWORD_30 | KEYWORD_31 | KEYWORD_32 | KEYWORD_33 | KEYWORD_34 | KEYWORD_35 | KEYWORD_14 | KEYWORD_15 | KEYWORD_16 | KEYWORD_17 | KEYWORD_18 | KEYWORD_19 | KEYWORD_20 | KEYWORD_21 | KEYWORD_22 | KEYWORD_23 | KEYWORD_24 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_11 | KEYWORD_12 | KEYWORD_13 | RULE_JRPARAM | RULE_JRNPARAM | RULE_STAR | RULE_UNSIGNED | RULE_INT | RULE_SIGNED_DOUBLE | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_STRING_ | RULE_STRING | RULE_DBNAME | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt19=143;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:10: KEYWORD_125
                {
                mKEYWORD_125(); 

                }
                break;
            case 2 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:22: KEYWORD_126
                {
                mKEYWORD_126(); 

                }
                break;
            case 3 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:34: KEYWORD_123
                {
                mKEYWORD_123(); 

                }
                break;
            case 4 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:46: KEYWORD_124
                {
                mKEYWORD_124(); 

                }
                break;
            case 5 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:58: KEYWORD_122
                {
                mKEYWORD_122(); 

                }
                break;
            case 6 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:70: KEYWORD_121
                {
                mKEYWORD_121(); 

                }
                break;
            case 7 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:82: KEYWORD_120
                {
                mKEYWORD_120(); 

                }
                break;
            case 8 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:94: KEYWORD_118
                {
                mKEYWORD_118(); 

                }
                break;
            case 9 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:106: KEYWORD_119
                {
                mKEYWORD_119(); 

                }
                break;
            case 10 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:118: KEYWORD_117
                {
                mKEYWORD_117(); 

                }
                break;
            case 11 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:130: KEYWORD_110
                {
                mKEYWORD_110(); 

                }
                break;
            case 12 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:142: KEYWORD_111
                {
                mKEYWORD_111(); 

                }
                break;
            case 13 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:154: KEYWORD_112
                {
                mKEYWORD_112(); 

                }
                break;
            case 14 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:166: KEYWORD_113
                {
                mKEYWORD_113(); 

                }
                break;
            case 15 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:178: KEYWORD_114
                {
                mKEYWORD_114(); 

                }
                break;
            case 16 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:190: KEYWORD_115
                {
                mKEYWORD_115(); 

                }
                break;
            case 17 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:202: KEYWORD_116
                {
                mKEYWORD_116(); 

                }
                break;
            case 18 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:214: KEYWORD_106
                {
                mKEYWORD_106(); 

                }
                break;
            case 19 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:226: KEYWORD_107
                {
                mKEYWORD_107(); 

                }
                break;
            case 20 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:238: KEYWORD_108
                {
                mKEYWORD_108(); 

                }
                break;
            case 21 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:250: KEYWORD_109
                {
                mKEYWORD_109(); 

                }
                break;
            case 22 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:262: KEYWORD_101
                {
                mKEYWORD_101(); 

                }
                break;
            case 23 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:274: KEYWORD_102
                {
                mKEYWORD_102(); 

                }
                break;
            case 24 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:286: KEYWORD_103
                {
                mKEYWORD_103(); 

                }
                break;
            case 25 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:298: KEYWORD_104
                {
                mKEYWORD_104(); 

                }
                break;
            case 26 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:310: KEYWORD_105
                {
                mKEYWORD_105(); 

                }
                break;
            case 27 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:322: KEYWORD_92
                {
                mKEYWORD_92(); 

                }
                break;
            case 28 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:333: KEYWORD_93
                {
                mKEYWORD_93(); 

                }
                break;
            case 29 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:344: KEYWORD_94
                {
                mKEYWORD_94(); 

                }
                break;
            case 30 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:355: KEYWORD_95
                {
                mKEYWORD_95(); 

                }
                break;
            case 31 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:366: KEYWORD_96
                {
                mKEYWORD_96(); 

                }
                break;
            case 32 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:377: KEYWORD_97
                {
                mKEYWORD_97(); 

                }
                break;
            case 33 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:388: KEYWORD_98
                {
                mKEYWORD_98(); 

                }
                break;
            case 34 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:399: KEYWORD_99
                {
                mKEYWORD_99(); 

                }
                break;
            case 35 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:410: KEYWORD_100
                {
                mKEYWORD_100(); 

                }
                break;
            case 36 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:422: KEYWORD_82
                {
                mKEYWORD_82(); 

                }
                break;
            case 37 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:433: KEYWORD_83
                {
                mKEYWORD_83(); 

                }
                break;
            case 38 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:444: KEYWORD_84
                {
                mKEYWORD_84(); 

                }
                break;
            case 39 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:455: KEYWORD_85
                {
                mKEYWORD_85(); 

                }
                break;
            case 40 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:466: KEYWORD_86
                {
                mKEYWORD_86(); 

                }
                break;
            case 41 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:477: KEYWORD_87
                {
                mKEYWORD_87(); 

                }
                break;
            case 42 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:488: KEYWORD_88
                {
                mKEYWORD_88(); 

                }
                break;
            case 43 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:499: KEYWORD_89
                {
                mKEYWORD_89(); 

                }
                break;
            case 44 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:510: KEYWORD_90
                {
                mKEYWORD_90(); 

                }
                break;
            case 45 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:521: KEYWORD_91
                {
                mKEYWORD_91(); 

                }
                break;
            case 46 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:532: KEYWORD_74
                {
                mKEYWORD_74(); 

                }
                break;
            case 47 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:543: KEYWORD_75
                {
                mKEYWORD_75(); 

                }
                break;
            case 48 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:554: KEYWORD_76
                {
                mKEYWORD_76(); 

                }
                break;
            case 49 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:565: KEYWORD_77
                {
                mKEYWORD_77(); 

                }
                break;
            case 50 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:576: KEYWORD_78
                {
                mKEYWORD_78(); 

                }
                break;
            case 51 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:587: KEYWORD_79
                {
                mKEYWORD_79(); 

                }
                break;
            case 52 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:598: KEYWORD_80
                {
                mKEYWORD_80(); 

                }
                break;
            case 53 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:609: KEYWORD_81
                {
                mKEYWORD_81(); 

                }
                break;
            case 54 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:620: KEYWORD_57
                {
                mKEYWORD_57(); 

                }
                break;
            case 55 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:631: KEYWORD_58
                {
                mKEYWORD_58(); 

                }
                break;
            case 56 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:642: KEYWORD_59
                {
                mKEYWORD_59(); 

                }
                break;
            case 57 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:653: KEYWORD_60
                {
                mKEYWORD_60(); 

                }
                break;
            case 58 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:664: KEYWORD_61
                {
                mKEYWORD_61(); 

                }
                break;
            case 59 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:675: KEYWORD_62
                {
                mKEYWORD_62(); 

                }
                break;
            case 60 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:686: KEYWORD_63
                {
                mKEYWORD_63(); 

                }
                break;
            case 61 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:697: KEYWORD_64
                {
                mKEYWORD_64(); 

                }
                break;
            case 62 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:708: KEYWORD_65
                {
                mKEYWORD_65(); 

                }
                break;
            case 63 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:719: KEYWORD_66
                {
                mKEYWORD_66(); 

                }
                break;
            case 64 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:730: KEYWORD_67
                {
                mKEYWORD_67(); 

                }
                break;
            case 65 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:741: KEYWORD_68
                {
                mKEYWORD_68(); 

                }
                break;
            case 66 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:752: KEYWORD_69
                {
                mKEYWORD_69(); 

                }
                break;
            case 67 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:763: KEYWORD_70
                {
                mKEYWORD_70(); 

                }
                break;
            case 68 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:774: KEYWORD_71
                {
                mKEYWORD_71(); 

                }
                break;
            case 69 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:785: KEYWORD_72
                {
                mKEYWORD_72(); 

                }
                break;
            case 70 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:796: KEYWORD_73
                {
                mKEYWORD_73(); 

                }
                break;
            case 71 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:807: KEYWORD_36
                {
                mKEYWORD_36(); 

                }
                break;
            case 72 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:818: KEYWORD_37
                {
                mKEYWORD_37(); 

                }
                break;
            case 73 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:829: KEYWORD_38
                {
                mKEYWORD_38(); 

                }
                break;
            case 74 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:840: KEYWORD_39
                {
                mKEYWORD_39(); 

                }
                break;
            case 75 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:851: KEYWORD_40
                {
                mKEYWORD_40(); 

                }
                break;
            case 76 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:862: KEYWORD_41
                {
                mKEYWORD_41(); 

                }
                break;
            case 77 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:873: KEYWORD_42
                {
                mKEYWORD_42(); 

                }
                break;
            case 78 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:884: KEYWORD_43
                {
                mKEYWORD_43(); 

                }
                break;
            case 79 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:895: KEYWORD_44
                {
                mKEYWORD_44(); 

                }
                break;
            case 80 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:906: KEYWORD_45
                {
                mKEYWORD_45(); 

                }
                break;
            case 81 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:917: KEYWORD_46
                {
                mKEYWORD_46(); 

                }
                break;
            case 82 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:928: KEYWORD_47
                {
                mKEYWORD_47(); 

                }
                break;
            case 83 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:939: KEYWORD_48
                {
                mKEYWORD_48(); 

                }
                break;
            case 84 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:950: KEYWORD_49
                {
                mKEYWORD_49(); 

                }
                break;
            case 85 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:961: KEYWORD_50
                {
                mKEYWORD_50(); 

                }
                break;
            case 86 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:972: KEYWORD_51
                {
                mKEYWORD_51(); 

                }
                break;
            case 87 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:983: KEYWORD_52
                {
                mKEYWORD_52(); 

                }
                break;
            case 88 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:994: KEYWORD_53
                {
                mKEYWORD_53(); 

                }
                break;
            case 89 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1005: KEYWORD_54
                {
                mKEYWORD_54(); 

                }
                break;
            case 90 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1016: KEYWORD_55
                {
                mKEYWORD_55(); 

                }
                break;
            case 91 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1027: KEYWORD_56
                {
                mKEYWORD_56(); 

                }
                break;
            case 92 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1038: KEYWORD_25
                {
                mKEYWORD_25(); 

                }
                break;
            case 93 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1049: KEYWORD_26
                {
                mKEYWORD_26(); 

                }
                break;
            case 94 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1060: KEYWORD_27
                {
                mKEYWORD_27(); 

                }
                break;
            case 95 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1071: KEYWORD_28
                {
                mKEYWORD_28(); 

                }
                break;
            case 96 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1082: KEYWORD_29
                {
                mKEYWORD_29(); 

                }
                break;
            case 97 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1093: KEYWORD_30
                {
                mKEYWORD_30(); 

                }
                break;
            case 98 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1104: KEYWORD_31
                {
                mKEYWORD_31(); 

                }
                break;
            case 99 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1115: KEYWORD_32
                {
                mKEYWORD_32(); 

                }
                break;
            case 100 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1126: KEYWORD_33
                {
                mKEYWORD_33(); 

                }
                break;
            case 101 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1137: KEYWORD_34
                {
                mKEYWORD_34(); 

                }
                break;
            case 102 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1148: KEYWORD_35
                {
                mKEYWORD_35(); 

                }
                break;
            case 103 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1159: KEYWORD_14
                {
                mKEYWORD_14(); 

                }
                break;
            case 104 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1170: KEYWORD_15
                {
                mKEYWORD_15(); 

                }
                break;
            case 105 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1181: KEYWORD_16
                {
                mKEYWORD_16(); 

                }
                break;
            case 106 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1192: KEYWORD_17
                {
                mKEYWORD_17(); 

                }
                break;
            case 107 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1203: KEYWORD_18
                {
                mKEYWORD_18(); 

                }
                break;
            case 108 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1214: KEYWORD_19
                {
                mKEYWORD_19(); 

                }
                break;
            case 109 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1225: KEYWORD_20
                {
                mKEYWORD_20(); 

                }
                break;
            case 110 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1236: KEYWORD_21
                {
                mKEYWORD_21(); 

                }
                break;
            case 111 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1247: KEYWORD_22
                {
                mKEYWORD_22(); 

                }
                break;
            case 112 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1258: KEYWORD_23
                {
                mKEYWORD_23(); 

                }
                break;
            case 113 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1269: KEYWORD_24
                {
                mKEYWORD_24(); 

                }
                break;
            case 114 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1280: KEYWORD_1
                {
                mKEYWORD_1(); 

                }
                break;
            case 115 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1290: KEYWORD_2
                {
                mKEYWORD_2(); 

                }
                break;
            case 116 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1300: KEYWORD_3
                {
                mKEYWORD_3(); 

                }
                break;
            case 117 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1310: KEYWORD_4
                {
                mKEYWORD_4(); 

                }
                break;
            case 118 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1320: KEYWORD_5
                {
                mKEYWORD_5(); 

                }
                break;
            case 119 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1330: KEYWORD_6
                {
                mKEYWORD_6(); 

                }
                break;
            case 120 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1340: KEYWORD_7
                {
                mKEYWORD_7(); 

                }
                break;
            case 121 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1350: KEYWORD_8
                {
                mKEYWORD_8(); 

                }
                break;
            case 122 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1360: KEYWORD_9
                {
                mKEYWORD_9(); 

                }
                break;
            case 123 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1370: KEYWORD_10
                {
                mKEYWORD_10(); 

                }
                break;
            case 124 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1381: KEYWORD_11
                {
                mKEYWORD_11(); 

                }
                break;
            case 125 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1392: KEYWORD_12
                {
                mKEYWORD_12(); 

                }
                break;
            case 126 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1403: KEYWORD_13
                {
                mKEYWORD_13(); 

                }
                break;
            case 127 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1414: RULE_JRPARAM
                {
                mRULE_JRPARAM(); 

                }
                break;
            case 128 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1427: RULE_JRNPARAM
                {
                mRULE_JRNPARAM(); 

                }
                break;
            case 129 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1441: RULE_STAR
                {
                mRULE_STAR(); 

                }
                break;
            case 130 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1451: RULE_UNSIGNED
                {
                mRULE_UNSIGNED(); 

                }
                break;
            case 131 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1465: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 132 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1474: RULE_SIGNED_DOUBLE
                {
                mRULE_SIGNED_DOUBLE(); 

                }
                break;
            case 133 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1493: RULE_TIMESTAMP
                {
                mRULE_TIMESTAMP(); 

                }
                break;
            case 134 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1508: RULE_DATE
                {
                mRULE_DATE(); 

                }
                break;
            case 135 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1518: RULE_TIME
                {
                mRULE_TIME(); 

                }
                break;
            case 136 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1528: RULE_STRING_
                {
                mRULE_STRING_(); 

                }
                break;
            case 137 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1541: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 138 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1553: RULE_DBNAME
                {
                mRULE_DBNAME(); 

                }
                break;
            case 139 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1565: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 140 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1573: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 141 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1589: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 142 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1605: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 143 :
                // ../com.jaspersoft.studio.data.sql/src-gen/com/jaspersoft/studio/data/parser/antlr/lexer/InternalSqlLexer.g:1:1613: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA19_eotS =
        "\1\uffff\15\63\1\61\10\63\1\154\2\63\1\61\1\63\1\166\1\170\1\61"+
        "\1\173\3\uffff\1\u0081\1\uffff\1\u0084\4\uffff\1\u0089\3\61\4\uffff"+
        "\1\63\1\uffff\5\63\1\u009b\2\63\1\u009f\23\63\1\u00b7\7\63\3\uffff"+
        "\20\63\2\uffff\2\63\1\u00da\1\63\1\uffff\1\u00dc\1\63\14\uffff\1"+
        "\u00df\11\uffff\1\u0089\5\uffff\13\63\1\uffff\3\63\1\uffff\3\63"+
        "\1\u00f3\13\63\1\u0100\3\63\1\uffff\3\63\1\uffff\7\63\2\uffff\10"+
        "\63\1\u011e\10\63\1\u0128\2\63\1\u012b\1\u012c\1\u012d\1\u012e\1"+
        "\u012f\1\uffff\1\u0130\5\uffff\11\63\1\u013d\3\63\1\u0141\1\u0142"+
        "\1\u0144\2\63\1\uffff\1\63\1\u014a\6\63\1\u0151\3\63\1\uffff\1\63"+
        "\1\u0156\1\u0157\1\uffff\3\63\1\u0161\2\63\1\uffff\2\63\1\u0167"+
        "\2\63\1\u016a\1\u016b\2\uffff\10\63\1\u0176\1\uffff\1\63\1\u0179"+
        "\1\u017a\1\63\1\u017c\1\u017d\2\63\1\u0180\1\uffff\1\u0181\1\u0182"+
        "\10\uffff\2\63\1\u0187\1\63\1\u0189\1\63\1\u018b\3\63\1\uffff\2"+
        "\63\1\u0191\2\uffff\1\63\1\uffff\5\63\1\uffff\3\63\1\u019c\1\63"+
        "\2\uffff\1\u019e\2\63\1\u01a1\4\uffff\2\63\1\u01a4\5\uffff\1\63"+
        "\1\u01a6\1\63\1\u01a8\1\63\2\uffff\1\u01aa\4\uffff\7\63\1\u01b4"+
        "\1\uffff\1\63\3\uffff\1\u01b6\2\uffff\1\u01b7\1\u01b8\5\uffff\2"+
        "\63\1\uffff\1\u01be\1\uffff\1\63\1\uffff\1\u01c1\1\u01c2\1\63\1"+
        "\uffff\1\u01c6\1\uffff\2\63\1\u01c9\7\63\1\uffff\1\63\2\uffff\1"+
        "\63\1\uffff\2\63\1\uffff\1\63\1\uffff\1\63\1\uffff\1\63\3\uffff"+
        "\1\63\1\uffff\2\63\1\u01de\1\63\1\u01e0\1\uffff\1\63\5\uffff\1\63"+
        "\1\u01e5\1\63\1\uffff\2\63\2\uffff\1\63\3\uffff\2\63\1\uffff\7\63"+
        "\1\u01f5\3\63\1\u01f9\1\63\1\u01fb\1\63\2\uffff\1\u0200\1\u0201"+
        "\1\u0202\1\uffff\1\u0203\1\uffff\1\u0204\2\uffff\1\63\1\uffff\13"+
        "\63\1\u0213\1\u0214\2\63\2\uffff\2\63\1\uffff\1\u0219\1\uffff\1"+
        "\63\1\u021c\1\u021d\10\uffff\14\63\2\uffff\1\63\1\u022d\1\u022e"+
        "\1\u022f\1\uffff\1\63\6\uffff\11\63\1\u023f\1\u0240\4\uffff\1\u0241"+
        "\5\uffff\2\63\1\u0246\3\63\1\u024a\1\u024b\1\63\5\uffff\2\63\1\uffff"+
        "\3\63\2\uffff\1\63\1\u0255\1\uffff\1\63\1\u0259\1\63\1\u025b\2\63"+
        "\3\uffff\1\63\1\uffff\1\63\1\uffff\2\63\1\uffff\3\63\1\u0267\1\uffff"+
        "\2\63\1\u026a\1\uffff\2\63\1\uffff\1\u026d\1\u026e\2\uffff";
    static final String DFA19_eofS =
        "\u026f\uffff";
    static final String DFA19_minS =
        "\1\0\1\116\1\111\1\105\1\106\4\101\1\105\1\116\1\101\2\105\1\0\1"+
        "\105\1\122\1\114\1\125\2\101\1\117\1\110\1\53\1\114\1\115\1\75\1"+
        "\120\3\75\1\174\3\uffff\1\55\1\uffff\1\52\4\uffff\1\56\3\0\4\uffff"+
        "\1\102\1\uffff\1\103\1\116\1\103\1\122\1\115\1\44\1\106\1\124\1"+
        "\44\1\105\1\125\1\126\1\131\2\123\1\122\1\105\1\122\1\126\1\122"+
        "\1\123\1\117\1\124\1\114\1\122\1\117\1\114\1\40\1\44\2\124\1\114"+
        "\1\101\1\124\2\105\2\0\1\uffff\1\124\1\105\1\103\1\125\1\123\1\104"+
        "\1\101\1\106\1\113\1\123\1\116\1\107\1\127\1\111\1\105\1\120\2\uffff"+
        "\1\114\1\104\1\44\1\114\1\uffff\1\44\1\41\14\uffff\1\56\11\uffff"+
        "\1\56\1\uffff\1\0\3\uffff\1\117\1\111\1\117\1\125\1\122\1\124\1"+
        "\117\1\105\1\101\2\105\1\uffff\1\123\1\105\1\131\1\uffff\2\122\1"+
        "\111\1\44\1\124\1\103\1\124\2\103\1\117\1\122\1\105\1\123\1\103"+
        "\1\114\1\44\1\123\1\115\1\114\1\116\1\105\1\114\1\105\1\uffff\1"+
        "\12\1\125\1\114\1\122\1\110\1\116\1\113\2\0\1\127\1\125\1\101\1"+
        "\105\1\122\1\123\1\101\1\105\1\44\1\122\1\123\1\124\1\111\1\105"+
        "\1\124\1\107\1\110\1\44\2\116\5\44\1\uffff\1\44\4\uffff\1\0\1\125"+
        "\1\126\1\116\1\123\1\117\1\110\1\116\1\103\1\111\1\44\1\122\1\105"+
        "\1\122\3\44\1\116\1\110\1\uffff\1\111\1\44\1\111\2\105\1\124\1\105"+
        "\1\50\1\44\1\123\1\110\1\117\1\uffff\1\124\2\44\1\117\1\122\1\125"+
        "\1\122\1\102\1\121\1\116\1\uffff\1\122\1\123\1\44\1\40\1\105\2\44"+
        "\2\0\1\105\1\120\1\124\1\125\1\120\1\101\1\124\1\114\1\44\1\uffff"+
        "\1\124\2\44\1\124\2\44\1\105\1\124\1\44\1\uffff\2\44\6\uffff\2\0"+
        "\1\116\1\117\1\44\1\105\1\44\1\123\1\44\1\104\1\124\1\107\1\uffff"+
        "\1\40\1\124\1\44\2\uffff\1\115\1\uffff\1\107\1\111\1\105\1\117\1"+
        "\116\1\uffff\1\124\1\104\1\116\1\44\1\116\2\uffff\1\44\1\40\1\127"+
        "\1\44\4\uffff\1\123\1\104\1\44\5\uffff\1\125\1\44\1\101\1\44\1\115"+
        "\2\uffff\1\44\2\uffff\2\0\1\105\1\40\1\105\1\104\1\124\1\103\1\123"+
        "\1\44\1\uffff\1\105\3\uffff\1\44\2\uffff\2\44\3\uffff\2\0\1\104"+
        "\1\124\1\uffff\1\44\1\uffff\1\105\1\uffff\2\44\1\110\1\102\1\44"+
        "\1\uffff\1\111\1\105\1\44\2\103\1\125\1\103\2\111\1\124\1\uffff"+
        "\1\124\2\uffff\1\111\1\uffff\2\105\1\uffff\1\101\1\uffff\1\114\1"+
        "\uffff\1\117\1\uffff\2\0\1\116\1\uffff\1\122\1\105\1\44\1\124\1"+
        "\44\1\uffff\1\122\3\uffff\2\0\1\105\1\44\1\115\1\uffff\1\103\1\115"+
        "\2\uffff\1\124\3\uffff\2\103\1\uffff\1\122\1\125\1\117\1\122\1\124"+
        "\1\117\1\116\1\44\1\40\1\116\1\103\1\44\1\114\1\44\1\116\2\0\3\44"+
        "\1\uffff\1\44\1\uffff\1\44\2\0\1\104\1\uffff\1\111\1\105\1\117\1"+
        "\111\1\137\1\122\1\125\2\117\1\124\1\116\2\44\1\116\1\107\2\uffff"+
        "\1\107\1\124\1\uffff\1\44\1\uffff\1\124\2\0\6\uffff\2\0\1\40\2\103"+
        "\1\116\1\103\1\112\1\117\1\124\1\116\1\123\1\105\1\104\2\uffff\1"+
        "\40\3\44\1\uffff\1\110\3\uffff\2\0\1\106\1\122\1\117\1\104\1\122"+
        "\1\117\1\123\1\105\1\104\1\105\2\44\4\uffff\1\44\1\uffff\2\0\2\uffff"+
        "\1\117\1\116\1\44\1\117\1\111\1\105\2\44\1\103\3\uffff\2\0\1\123"+
        "\1\104\1\uffff\1\123\1\116\1\103\2\uffff\1\117\1\40\1\0\1\105\1"+
        "\44\1\105\1\44\1\117\1\116\2\uffff\1\0\1\103\1\uffff\1\103\1\uffff"+
        "\1\116\1\104\1\uffff\2\117\1\104\1\44\1\uffff\2\116\1\44\1\uffff"+
        "\2\104\1\uffff\2\44\2\uffff";
    static final String DFA19_maxS =
        "\1\uffff\1\156\1\157\1\164\1\166\1\157\1\151\1\162\2\165\1\163\1"+
        "\165\1\145\1\151\1\uffff\1\145\1\162\1\170\1\165\1\151\3\157\1\53"+
        "\1\163\1\155\1\75\1\170\1\76\2\75\1\174\3\uffff\1\71\1\uffff\1\57"+
        "\4\uffff\1\71\3\uffff\4\uffff\1\160\1\uffff\2\156\1\154\1\162\1"+
        "\155\1\uff3f\1\146\1\164\1\uff3f\1\145\1\165\1\166\1\171\2\163\1"+
        "\162\1\145\1\162\1\166\1\162\1\163\1\157\1\164\2\162\1\157\1\154"+
        "\1\40\1\uff3f\2\164\1\154\1\141\1\164\2\145\2\uffff\1\uffff\1\164"+
        "\1\157\1\164\1\165\1\163\1\144\1\141\1\163\1\155\1\163\1\156\1\147"+
        "\1\167\1\151\1\145\1\160\2\uffff\1\154\1\171\1\uff3f\1\154\1\uffff"+
        "\1\uff3f\1\173\14\uffff\1\71\11\uffff\1\71\1\uffff\1\uffff\3\uffff"+
        "\1\157\1\151\1\157\1\165\1\162\1\164\1\157\1\145\1\141\2\145\1\uffff"+
        "\1\163\1\145\1\171\1\uffff\2\162\1\151\1\uff3f\1\164\1\143\1\164"+
        "\2\143\1\157\1\162\1\164\1\163\1\143\1\154\1\uff3f\1\163\1\155\1"+
        "\154\1\156\1\145\1\154\1\145\1\uffff\1\151\1\165\1\154\1\162\1\150"+
        "\1\162\1\153\2\uffff\1\167\1\165\1\141\1\154\1\162\1\163\1\141\1"+
        "\145\1\uff3f\1\162\1\163\1\164\1\151\1\145\1\164\1\147\1\150\1\uff3f"+
        "\2\156\5\uff3f\1\uffff\1\uff3f\4\uffff\1\uffff\1\165\1\166\1\156"+
        "\1\164\1\157\1\150\1\156\1\143\1\151\1\uff3f\1\162\1\145\1\162\3"+
        "\uff3f\1\156\1\163\1\uffff\1\151\1\uff3f\1\151\2\145\1\164\1\145"+
        "\1\50\1\uff3f\1\163\1\150\1\157\1\uffff\1\164\2\uff3f\1\165\1\162"+
        "\1\165\1\162\1\154\1\161\1\156\1\uffff\1\162\1\163\1\uff3f\1\40"+
        "\1\145\2\uff3f\2\uffff\1\145\1\160\1\164\1\165\1\160\1\141\1\164"+
        "\1\154\1\uff3f\1\uffff\1\164\2\uff3f\1\164\2\uff3f\1\145\1\164\1"+
        "\uff3f\1\uffff\2\uff3f\6\uffff\2\uffff\1\156\1\157\1\uff3f\1\145"+
        "\1\uff3f\1\163\1\uff3f\1\144\1\164\1\147\1\uffff\1\40\1\164\1\uff3f"+
        "\2\uffff\1\163\1\uffff\1\147\1\151\1\145\1\157\1\156\1\uffff\1\164"+
        "\1\144\1\156\1\uff3f\1\156\2\uffff\1\uff3f\1\40\1\167\1\uff3f\4"+
        "\uffff\1\163\1\144\1\uff3f\5\uffff\1\165\1\uff3f\1\141\1\uff3f\1"+
        "\155\2\uffff\1\uff3f\2\uffff\2\uffff\1\145\1\40\1\145\1\144\1\164"+
        "\1\143\1\163\1\uff3f\1\uffff\1\145\3\uffff\1\uff3f\2\uffff\2\uff3f"+
        "\3\uffff\2\uffff\1\144\1\164\1\uffff\1\uff3f\1\uffff\1\145\1\uffff"+
        "\2\uff3f\1\150\1\163\1\uff3f\1\uffff\1\151\1\145\1\uff3f\1\156\1"+
        "\143\1\165\1\143\2\151\1\164\1\uffff\1\164\2\uffff\1\151\1\uffff"+
        "\2\145\1\uffff\1\141\1\uffff\1\154\1\uffff\1\157\1\uffff\2\uffff"+
        "\1\156\1\uffff\1\162\1\145\1\uff3f\1\164\1\uff3f\1\uffff\1\162\3"+
        "\uffff\2\uffff\1\145\1\uff3f\1\163\1\uffff\1\143\1\155\2\uffff\1"+
        "\164\3\uffff\1\156\1\143\1\uffff\1\162\1\165\1\157\1\162\1\164\1"+
        "\157\1\156\1\uff3f\1\40\1\156\1\143\1\uff3f\1\154\1\uff3f\1\156"+
        "\2\uffff\3\uff3f\1\uffff\1\uff3f\1\uffff\1\uff3f\2\uffff\1\144\1"+
        "\uffff\1\151\1\145\1\157\1\151\1\137\1\162\1\165\2\157\1\164\1\156"+
        "\2\uff3f\1\156\1\147\2\uffff\1\147\1\164\1\uffff\1\uff3f\1\uffff"+
        "\1\164\2\uffff\6\uffff\2\uffff\1\40\2\143\1\156\1\143\1\152\1\157"+
        "\1\164\1\156\1\163\1\145\1\144\2\uffff\1\40\3\uff3f\1\uffff\1\150"+
        "\3\uffff\2\uffff\1\160\1\162\1\157\1\144\1\162\1\157\1\163\1\145"+
        "\1\144\1\145\2\uff3f\4\uffff\1\uff3f\1\uffff\2\uffff\2\uffff\1\157"+
        "\1\156\1\uff3f\1\157\1\151\1\145\2\uff3f\1\143\3\uffff\2\uffff\1"+
        "\163\1\144\1\uffff\1\163\1\156\1\143\2\uffff\1\157\1\40\1\uffff"+
        "\1\145\1\uff3f\1\145\1\uff3f\1\157\1\156\2\uffff\1\uffff\1\143\1"+
        "\uffff\1\143\1\uffff\1\156\1\144\1\uffff\2\157\1\144\1\uff3f\1\uffff"+
        "\2\156\1\uff3f\1\uffff\2\144\1\uffff\2\uff3f\2\uffff";
    static final String DFA19_acceptS =
        "\40\uffff\1\163\1\164\1\165\1\uffff\1\167\1\uffff\1\172\1\174\1"+
        "\176\1\u0081\4\uffff\1\u008b\1\u008d\1\u008e\1\u008f\1\uffff\1\u008b"+
        "\46\uffff\1\u008a\20\uffff\1\134\1\162\4\uffff\1\147\2\uffff\1\151"+
        "\1\152\1\171\1\153\1\173\1\160\1\161\1\175\1\163\1\164\1\165\1\u008d"+
        "\1\uffff\1\166\1\167\1\u008c\1\170\1\172\1\174\1\176\1\u0081\1\u0082"+
        "\1\uffff\1\u0084\1\uffff\1\u0088\1\u0089\1\u008e\13\uffff\1\157"+
        "\3\uffff\1\156\27\uffff\1\155\42\uffff\1\154\1\uffff\1\150\1\177"+
        "\1\u0080\1\u0083\23\uffff\1\141\14\uffff\1\143\12\uffff\1\122\22"+
        "\uffff\1\142\11\uffff\1\144\2\uffff\1\145\1\135\1\136\1\137\1\140"+
        "\1\146\14\uffff\1\127\3\uffff\1\124\1\125\1\uffff\1\114\5\uffff"+
        "\1\110\5\uffff\1\66\1\107\4\uffff\1\112\1\113\1\17\1\51\3\uffff"+
        "\1\21\1\24\1\37\1\62\1\123\5\uffff\1\133\1\31\1\uffff\1\132\1\131"+
        "\12\uffff\1\111\1\uffff\1\73\1\120\1\117\1\uffff\1\121\1\116\2\uffff"+
        "\1\126\1\115\1\130\4\uffff\1\105\1\uffff\1\75\1\uffff\1\76\5\uffff"+
        "\1\101\12\uffff\1\102\1\uffff\1\67\1\14\1\uffff\1\71\2\uffff\1\72"+
        "\1\uffff\1\77\1\uffff\1\100\1\uffff\1\106\3\uffff\1\36\5\uffff\1"+
        "\70\1\uffff\1\74\1\103\1\104\5\uffff\1\61\2\uffff\1\64\1\65\1\uffff"+
        "\1\5\1\41\1\63\2\uffff\1\60\24\uffff\1\56\1\uffff\1\57\4\uffff\1"+
        "\55\17\uffff\1\53\1\13\2\uffff\1\50\1\uffff\1\52\3\uffff\1\33\1"+
        "\44\1\47\1\45\1\46\1\54\16\uffff\1\34\1\35\4\uffff\1\40\1\uffff"+
        "\1\32\1\42\1\43\16\uffff\1\12\1\30\1\26\1\27\1\uffff\1\32\2\uffff"+
        "\1\1\1\2\11\uffff\1\22\1\23\1\25\4\uffff\1\20\3\uffff\1\15\1\16"+
        "\11\uffff\1\u0086\1\u0085\2\uffff\1\10\1\uffff\1\11\2\uffff\1\u0087"+
        "\4\uffff\1\u0087\3\uffff\1\7\2\uffff\1\6\2\uffff\1\3\1\4";
    static final String DFA19_specialS =
        "\1\21\15\uffff\1\10\34\uffff\1\40\1\41\1\20\52\uffff\1\36\1\27\62"+
        "\uffff\1\34\62\uffff\1\37\1\33\37\uffff\1\35\62\uffff\1\26\1\31"+
        "\34\uffff\1\7\1\44\71\uffff\1\23\1\24\25\uffff\1\6\1\45\46\uffff"+
        "\1\32\1\22\14\uffff\1\5\1\42\36\uffff\1\30\1\25\7\uffff\1\4\1\43"+
        "\31\uffff\1\11\1\46\6\uffff\1\3\1\16\27\uffff\1\2\1\17\22\uffff"+
        "\1\1\1\14\16\uffff\1\0\1\15\12\uffff\1\12\10\uffff\1\13\27\uffff}>";
    static final String[] DFA19_transitionS = {
            "\11\61\2\60\2\61\1\60\22\61\1\60\1\32\1\54\1\57\1\33\2\61\1"+
            "\53\1\27\1\40\1\51\1\41\1\42\1\43\1\44\1\45\12\52\2\61\1\34"+
            "\1\46\1\35\2\61\1\30\1\17\1\10\1\6\1\21\1\11\1\20\1\5\1\12\1"+
            "\25\1\56\1\23\1\2\1\13\1\4\1\7\1\22\1\24\1\3\1\26\1\1\1\56\1"+
            "\15\1\31\1\14\1\56\1\16\2\61\1\36\1\56\1\55\1\30\1\17\1\10\1"+
            "\6\1\21\1\11\1\20\1\5\1\12\1\25\1\56\1\23\1\2\1\13\1\4\1\7\1"+
            "\22\1\24\1\3\1\26\1\1\1\56\1\15\1\31\1\14\1\56\1\47\1\37\1\50"+
            "\102\61\u0190\56\u01c0\61\100\56\u2bf1\61\u00bf\56\u00f0\61"+
            "\20\56\u0200\61\u19c0\56\100\61\u5200\56\u5900\61\u0200\56\u043f"+
            "\61\1\56\u00c0\61",
            "\1\62\37\uffff\1\62",
            "\1\64\5\uffff\1\65\31\uffff\1\64\5\uffff\1\65",
            "\1\66\11\uffff\1\70\4\uffff\1\67\20\uffff\1\66\11\uffff\1\70"+
            "\4\uffff\1\67",
            "\1\72\7\uffff\1\74\3\uffff\1\71\2\uffff\1\73\1\75\17\uffff"+
            "\1\72\7\uffff\1\74\3\uffff\1\71\2\uffff\1\73\1\75",
            "\1\77\15\uffff\1\76\21\uffff\1\77\15\uffff\1\76",
            "\1\100\3\uffff\1\102\3\uffff\1\101\27\uffff\1\100\3\uffff\1"+
            "\102\3\uffff\1\101",
            "\1\103\3\uffff\1\105\3\uffff\1\106\10\uffff\1\104\16\uffff"+
            "\1\103\3\uffff\1\105\3\uffff\1\106\10\uffff\1\104",
            "\1\110\20\uffff\1\111\2\uffff\1\107\13\uffff\1\110\20\uffff"+
            "\1\111\2\uffff\1\107",
            "\1\112\3\uffff\1\114\5\uffff\1\113\2\uffff\1\115\2\uffff\1"+
            "\116\17\uffff\1\112\3\uffff\1\114\5\uffff\1\113\2\uffff\1\115"+
            "\2\uffff\1\116",
            "\1\120\4\uffff\1\117\32\uffff\1\120\4\uffff\1\117",
            "\1\122\15\uffff\1\121\5\uffff\1\123\13\uffff\1\122\15\uffff"+
            "\1\121\5\uffff\1\123",
            "\1\124\37\uffff\1\124",
            "\1\127\2\uffff\1\126\1\125\33\uffff\1\127\2\uffff\1\126\1\125",
            "\102\132\1\130\4\132\1\131\32\132\1\130\4\132\1\131\uff98\132",
            "\1\133\37\uffff\1\133",
            "\1\134\37\uffff\1\134",
            "\1\137\1\uffff\1\140\2\uffff\1\136\6\uffff\1\135\23\uffff\1"+
            "\137\1\uffff\1\140\2\uffff\1\136\6\uffff\1\135",
            "\1\141\37\uffff\1\141",
            "\1\144\3\uffff\1\142\3\uffff\1\143\27\uffff\1\144\3\uffff\1"+
            "\142\3\uffff\1\143",
            "\1\145\7\uffff\1\146\5\uffff\1\147\21\uffff\1\145\7\uffff\1"+
            "\146\5\uffff\1\147",
            "\1\150\37\uffff\1\150",
            "\1\151\6\uffff\1\152\30\uffff\1\151\6\uffff\1\152",
            "\1\153",
            "\1\155\1\uffff\1\156\4\uffff\1\157\30\uffff\1\155\1\uffff\1"+
            "\156\4\uffff\1\157",
            "\1\160\37\uffff\1\160",
            "\1\161",
            "\1\163\7\uffff\1\162\37\uffff\1\162",
            "\1\164\1\165",
            "\1\167",
            "\1\171",
            "\1\172",
            "",
            "",
            "",
            "\1\177\2\uffff\12\u0080",
            "",
            "\1\u0083\4\uffff\1\177",
            "",
            "",
            "",
            "",
            "\1\u008b\1\uffff\12\u008a",
            "\60\u008d\12\u008c\uffc6\u008d",
            "\0\u008e",
            "\0\132",
            "",
            "",
            "",
            "",
            "\1\u0090\6\uffff\1\u0092\6\uffff\1\u0091\21\uffff\1\u0090\6"+
            "\uffff\1\u0092\6\uffff\1\u0091",
            "",
            "\1\u0094\12\uffff\1\u0093\24\uffff\1\u0094\12\uffff\1\u0093",
            "\1\u0095\37\uffff\1\u0095",
            "\1\u0096\10\uffff\1\u0097\26\uffff\1\u0096\10\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\3\63\1\u009a\26\63"+
            "\4\uffff\1\63\1\uffff\3\63\1\u009a\26\63\105\uffff\u0190\63"+
            "\u01c0\uffff\100\63\u2bf1\uffff\u00bf\63\u00f0\uffff\20\63\u0200"+
            "\uffff\u19c0\63\100\uffff\u5200\63\u5900\uffff\u0200\63\u043f"+
            "\uffff\1\63",
            "\1\u009c\37\uffff\1\u009c",
            "\1\u009d\37\uffff\1\u009d",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\13\63\1\u009e\16"+
            "\63\4\uffff\1\63\1\uffff\13\63\1\u009e\16\63\105\uffff\u0190"+
            "\63\u01c0\uffff\100\63\u2bf1\uffff\u00bf\63\u00f0\uffff\20\63"+
            "\u0200\uffff\u19c0\63\100\uffff\u5200\63\u5900\uffff\u0200\63"+
            "\u043f\uffff\1\63",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\37\uffff\1\u00a4",
            "\1\u00a5\37\uffff\1\u00a5",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\u00a7\37\uffff\1\u00a7",
            "\1\u00a8\37\uffff\1\u00a8",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab\37\uffff\1\u00ab",
            "\1\u00ac\37\uffff\1\u00ac",
            "\1\u00ad\37\uffff\1\u00ad",
            "\1\u00ae\5\uffff\1\u00af\31\uffff\1\u00ae\5\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "\1\u00b1\37\uffff\1\u00b1",
            "\1\u00b2\37\uffff\1\u00b2",
            "\1\u00b3",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\2\63\1\u00b5\12\63"+
            "\1\u00b6\5\63\1\u00b4\6\63\4\uffff\1\63\1\uffff\2\63\1\u00b5"+
            "\12\63\1\u00b6\5\63\1\u00b4\6\63\105\uffff\u0190\63\u01c0\uffff"+
            "\100\63\u2bf1\uffff\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0"+
            "\63\100\uffff\u5200\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u00b8\37\uffff\1\u00b8",
            "\1\u00b9\37\uffff\1\u00b9",
            "\1\u00ba\37\uffff\1\u00ba",
            "\1\u00bb\37\uffff\1\u00bb",
            "\1\u00bc\37\uffff\1\u00bc",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "\105\132\1\u00bf\37\132\1\u00bf\uff9a\132",
            "\122\132\1\u00c0\37\132\1\u00c0\uff8d\132",
            "",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c3\11\uffff\1\u00c2\25\uffff\1\u00c3\11\uffff\1\u00c2",
            "\1\u00c4\5\uffff\1\u00c6\12\uffff\1\u00c5\16\uffff\1\u00c4"+
            "\5\uffff\1\u00c6\12\uffff\1\u00c5",
            "\1\u00c7\37\uffff\1\u00c7",
            "\1\u00c8\37\uffff\1\u00c8",
            "\1\u00c9\37\uffff\1\u00c9",
            "\1\u00ca\37\uffff\1\u00ca",
            "\1\u00cc\14\uffff\1\u00cb\22\uffff\1\u00cc\14\uffff\1\u00cb",
            "\1\u00ce\1\uffff\1\u00cd\35\uffff\1\u00ce\1\uffff\1\u00cd",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\u00d2\37\uffff\1\u00d2",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\u00d4\37\uffff\1\u00d4",
            "\1\u00d5\37\uffff\1\u00d5",
            "",
            "",
            "\1\u00d6\37\uffff\1\u00d6",
            "\1\u00d7\24\uffff\1\u00d8\12\uffff\1\u00d7\24\uffff\1\u00d8",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\2\63\1\u00d9\27\63"+
            "\4\uffff\1\63\1\uffff\2\63\1\u00d9\27\63\105\uffff\u0190\63"+
            "\u01c0\uffff\100\63\u2bf1\uffff\u00bf\63\u00f0\uffff\20\63\u0200"+
            "\uffff\u19c0\63\100\uffff\u5200\63\u5900\uffff\u0200\63\u043f"+
            "\uffff\1\63",
            "\1\u00db\37\uffff\1\u00db",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u00de\131\uffff\1\u00dd",
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
            "\1\u008b\1\uffff\12\u0080",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u008b\1\uffff\12\u008a",
            "",
            "\60\u008d\12\u00e0\uffc6\u008d",
            "",
            "",
            "",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\u00e2\37\uffff\1\u00e2",
            "\1\u00e3\37\uffff\1\u00e3",
            "\1\u00e4\37\uffff\1\u00e4",
            "\1\u00e5\37\uffff\1\u00e5",
            "\1\u00e6\37\uffff\1\u00e6",
            "\1\u00e7\37\uffff\1\u00e7",
            "\1\u00e8\37\uffff\1\u00e8",
            "\1\u00e9\37\uffff\1\u00e9",
            "\1\u00ea\37\uffff\1\u00ea",
            "\1\u00eb\37\uffff\1\u00eb",
            "",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\u00ed\37\uffff\1\u00ed",
            "\1\u00ee\37\uffff\1\u00ee",
            "",
            "\1\u00ef\37\uffff\1\u00ef",
            "\1\u00f0\37\uffff\1\u00f0",
            "\1\u00f1\37\uffff\1\u00f1",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\u00f2"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u00f4\37\uffff\1\u00f4",
            "\1\u00f5\37\uffff\1\u00f5",
            "\1\u00f6\37\uffff\1\u00f6",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\u00f8\37\uffff\1\u00f8",
            "\1\u00f9\37\uffff\1\u00f9",
            "\1\u00fa\37\uffff\1\u00fa",
            "\1\u00fc\16\uffff\1\u00fb\20\uffff\1\u00fc\16\uffff\1\u00fb",
            "\1\u00fd\37\uffff\1\u00fd",
            "\1\u00fe\37\uffff\1\u00fe",
            "\1\u00ff\37\uffff\1\u00ff",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u0101\37\uffff\1\u0101",
            "\1\u0102\37\uffff\1\u0102",
            "\1\u0103\37\uffff\1\u0103",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "\1\u0106\37\uffff\1\u0106",
            "\1\u0107\37\uffff\1\u0107",
            "",
            "\1\u010b\25\uffff\1\u0108\44\uffff\1\u0109\3\uffff\1\u010a"+
            "\33\uffff\1\u0109\3\uffff\1\u010a",
            "\1\u010c\37\uffff\1\u010c",
            "\1\u010d\37\uffff\1\u010d",
            "\1\u010e\37\uffff\1\u010e",
            "\1\u010f\37\uffff\1\u010f",
            "\1\u0111\3\uffff\1\u0110\33\uffff\1\u0111\3\uffff\1\u0110",
            "\1\u0112\37\uffff\1\u0112",
            "\124\132\1\u0113\37\132\1\u0113\uff8b\132",
            "\105\132\1\u0114\37\132\1\u0114\uff9a\132",
            "\1\u0115\37\uffff\1\u0115",
            "\1\u0116\37\uffff\1\u0116",
            "\1\u0117\37\uffff\1\u0117",
            "\1\u0119\6\uffff\1\u0118\30\uffff\1\u0119\6\uffff\1\u0118",
            "\1\u011a\37\uffff\1\u011a",
            "\1\u011b\37\uffff\1\u011b",
            "\1\u011c\37\uffff\1\u011c",
            "\1\u011d\37\uffff\1\u011d",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u011f\37\uffff\1\u011f",
            "\1\u0120\37\uffff\1\u0120",
            "\1\u0121\37\uffff\1\u0121",
            "\1\u0122\37\uffff\1\u0122",
            "\1\u0123\37\uffff\1\u0123",
            "\1\u0124\37\uffff\1\u0124",
            "\1\u0125\37\uffff\1\u0125",
            "\1\u0126\37\uffff\1\u0126",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\22\63\1\u0127\7\63"+
            "\4\uffff\1\63\1\uffff\22\63\1\u0127\7\63\105\uffff\u0190\63"+
            "\u01c0\uffff\100\63\u2bf1\uffff\u00bf\63\u00f0\uffff\20\63\u0200"+
            "\uffff\u19c0\63\100\uffff\u5200\63\u5900\uffff\u0200\63\u043f"+
            "\uffff\1\63",
            "\1\u0129\37\uffff\1\u0129",
            "\1\u012a\37\uffff\1\u012a",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "",
            "",
            "\60\u008d\12\u0131\1\u0132\uffc5\u008d",
            "\1\u0133\37\uffff\1\u0133",
            "\1\u0134\37\uffff\1\u0134",
            "\1\u0135\37\uffff\1\u0135",
            "\1\u0137\1\u0136\36\uffff\1\u0137\1\u0136",
            "\1\u0138\37\uffff\1\u0138",
            "\1\u0139\37\uffff\1\u0139",
            "\1\u013a\37\uffff\1\u013a",
            "\1\u013b\37\uffff\1\u013b",
            "\1\u013c\37\uffff\1\u013c",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u013e\37\uffff\1\u013e",
            "\1\u013f\37\uffff\1\u013f",
            "\1\u0140\37\uffff\1\u0140",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\u0143"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u0145\37\uffff\1\u0145",
            "\1\u0148\4\uffff\1\u0146\5\uffff\1\u0147\24\uffff\1\u0148\4"+
            "\uffff\1\u0146\5\uffff\1\u0147",
            "",
            "\1\u0149\37\uffff\1\u0149",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u014b\37\uffff\1\u014b",
            "\1\u014c\37\uffff\1\u014c",
            "\1\u014d\37\uffff\1\u014d",
            "\1\u014e\37\uffff\1\u014e",
            "\1\u014f\37\uffff\1\u014f",
            "\1\u0150",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u0152\37\uffff\1\u0152",
            "\1\u0153\37\uffff\1\u0153",
            "\1\u0154\37\uffff\1\u0154",
            "",
            "\1\u0155\37\uffff\1\u0155",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u0158\5\uffff\1\u0159\31\uffff\1\u0158\5\uffff\1\u0159",
            "\1\u015a\37\uffff\1\u015a",
            "\1\u015b\37\uffff\1\u015b",
            "\1\u015c\37\uffff\1\u015c",
            "\1\u015d\2\uffff\1\u015e\3\uffff\1\u0160\2\uffff\1\u015f\25"+
            "\uffff\1\u015d\2\uffff\1\u015e\3\uffff\1\u0160\2\uffff\1\u015f",
            "\1\u0162\37\uffff\1\u0162",
            "\1\u0163\37\uffff\1\u0163",
            "",
            "\1\u0164\37\uffff\1\u0164",
            "\1\u0165\37\uffff\1\u0165",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\u0166"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u0168",
            "\1\u0169\37\uffff\1\u0169",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\127\132\1\u016c\37\132\1\u016c\uff88\132",
            "\101\132\1\u016d\37\132\1\u016d\uff9e\132",
            "\1\u016e\37\uffff\1\u016e",
            "\1\u016f\37\uffff\1\u016f",
            "\1\u0170\37\uffff\1\u0170",
            "\1\u0171\37\uffff\1\u0171",
            "\1\u0172\37\uffff\1\u0172",
            "\1\u0173\37\uffff\1\u0173",
            "\1\u0174\37\uffff\1\u0174",
            "\1\u0175\37\uffff\1\u0175",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u0177\37\uffff\1\u0177",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\2\uffff\1\u0178"+
            "\1\uffff\1\63\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100"+
            "\63\u2bf1\uffff\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0"+
            "\63\100\uffff\u5200\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u017b\37\uffff\1\u017b",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u017e\37\uffff\1\u017e",
            "\1\u017f\37\uffff\1\u017f",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "",
            "",
            "",
            "",
            "\60\u008d\12\u0183\uffc6\u008d",
            "\60\u008d\12\u0184\uffc6\u008d",
            "\1\u0185\37\uffff\1\u0185",
            "\1\u0186\37\uffff\1\u0186",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u0188\37\uffff\1\u0188",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u018a\37\uffff\1\u018a",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u018c\37\uffff\1\u018c",
            "\1\u018d\37\uffff\1\u018d",
            "\1\u018e\37\uffff\1\u018e",
            "",
            "\1\u018f",
            "\1\u0190\37\uffff\1\u0190",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "\1\u0192\5\uffff\1\u0193\31\uffff\1\u0192\5\uffff\1\u0193",
            "",
            "\1\u0194\37\uffff\1\u0194",
            "\1\u0195\37\uffff\1\u0195",
            "\1\u0196\37\uffff\1\u0196",
            "\1\u0197\37\uffff\1\u0197",
            "\1\u0198\37\uffff\1\u0198",
            "",
            "\1\u0199\37\uffff\1\u0199",
            "\1\u019a\37\uffff\1\u019a",
            "\1\u019b\37\uffff\1\u019b",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u019d\37\uffff\1\u019d",
            "",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u019f",
            "\1\u01a0\37\uffff\1\u01a0",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "",
            "",
            "\1\u01a2\37\uffff\1\u01a2",
            "\1\u01a3\37\uffff\1\u01a3",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "",
            "",
            "",
            "\1\u01a5\37\uffff\1\u01a5",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01a7\37\uffff\1\u01a7",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01a9\37\uffff\1\u01a9",
            "",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "\105\132\1\u01ab\37\132\1\u01ab\uff9a\132",
            "\124\132\1\u01ac\37\132\1\u01ac\uff8b\132",
            "\1\u01ad\37\uffff\1\u01ad",
            "\1\u01ae",
            "\1\u01af\37\uffff\1\u01af",
            "\1\u01b0\37\uffff\1\u01b0",
            "\1\u01b1\37\uffff\1\u01b1",
            "\1\u01b2\37\uffff\1\u01b2",
            "\1\u01b3\37\uffff\1\u01b3",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u01b5\37\uffff\1\u01b5",
            "",
            "",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "",
            "\55\u008d\1\u01b9\uffd2\u008d",
            "\60\u008d\12\u01ba\uffc6\u008d",
            "\1\u01bb\37\uffff\1\u01bb",
            "\1\u01bc\37\uffff\1\u01bc",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\u01bd"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u01bf\37\uffff\1\u01bf",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\u01c0"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01c3\37\uffff\1\u01c3",
            "\1\u01c5\20\uffff\1\u01c4\16\uffff\1\u01c5\20\uffff\1\u01c4",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u01c7\37\uffff\1\u01c7",
            "\1\u01c8\37\uffff\1\u01c8",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01ca\12\uffff\1\u01cb\24\uffff\1\u01ca\12\uffff\1\u01cb",
            "\1\u01cc\37\uffff\1\u01cc",
            "\1\u01cd\37\uffff\1\u01cd",
            "\1\u01ce\37\uffff\1\u01ce",
            "\1\u01cf\37\uffff\1\u01cf",
            "\1\u01d0\37\uffff\1\u01d0",
            "\1\u01d1\37\uffff\1\u01d1",
            "",
            "\1\u01d2\37\uffff\1\u01d2",
            "",
            "",
            "\1\u01d3\37\uffff\1\u01d3",
            "",
            "\1\u01d4\37\uffff\1\u01d4",
            "\1\u01d5\37\uffff\1\u01d5",
            "",
            "\1\u01d6\37\uffff\1\u01d6",
            "",
            "\1\u01d7\37\uffff\1\u01d7",
            "",
            "\1\u01d8\37\uffff\1\u01d8",
            "",
            "\105\132\1\u01d9\37\132\1\u01d9\uff9a\132",
            "\105\132\1\u01da\37\132\1\u01da\uff9a\132",
            "\1\u01db\37\uffff\1\u01db",
            "",
            "\1\u01dc\37\uffff\1\u01dc",
            "\1\u01dd\37\uffff\1\u01dd",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01df\37\uffff\1\u01df",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u01e1\37\uffff\1\u01e1",
            "",
            "",
            "",
            "\60\u008d\2\u01e2\uffce\u008d",
            "\72\u008d\1\u01e3\uffc5\u008d",
            "\1\u01e4\37\uffff\1\u01e4",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01e6\5\uffff\1\u01e7\31\uffff\1\u01e6\5\uffff\1\u01e7",
            "",
            "\1\u01e8\37\uffff\1\u01e8",
            "\1\u01e9\37\uffff\1\u01e9",
            "",
            "",
            "\1\u01ea\37\uffff\1\u01ea",
            "",
            "",
            "",
            "\1\u01eb\12\uffff\1\u01ec\24\uffff\1\u01eb\12\uffff\1\u01ec",
            "\1\u01ed\37\uffff\1\u01ed",
            "",
            "\1\u01ee\37\uffff\1\u01ee",
            "\1\u01ef\37\uffff\1\u01ef",
            "\1\u01f0\37\uffff\1\u01f0",
            "\1\u01f1\37\uffff\1\u01f1",
            "\1\u01f2\37\uffff\1\u01f2",
            "\1\u01f3\37\uffff\1\u01f3",
            "\1\u01f4\37\uffff\1\u01f4",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01f6",
            "\1\u01f7\37\uffff\1\u01f7",
            "\1\u01f8\37\uffff\1\u01f8",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01fa\37\uffff\1\u01fa",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u01fc\37\uffff\1\u01fc",
            "\116\132\1\u01fd\37\132\1\u01fd\uff91\132",
            "\122\132\1\u01fe\37\132\1\u01fe\uff8d\132",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\2\uffff\1\u01ff"+
            "\1\uffff\1\63\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100"+
            "\63\u2bf1\uffff\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0"+
            "\63\100\uffff\u5200\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\60\u008d\12\u0205\uffc6\u008d",
            "\60\u008d\12\u0206\uffc6\u008d",
            "\1\u0207\37\uffff\1\u0207",
            "",
            "\1\u0208\37\uffff\1\u0208",
            "\1\u0209\37\uffff\1\u0209",
            "\1\u020a\37\uffff\1\u020a",
            "\1\u020b\37\uffff\1\u020b",
            "\1\u020c",
            "\1\u020d\37\uffff\1\u020d",
            "\1\u020e\37\uffff\1\u020e",
            "\1\u020f\37\uffff\1\u020f",
            "\1\u0210\37\uffff\1\u0210",
            "\1\u0211\37\uffff\1\u0211",
            "\1\u0212\37\uffff\1\u0212",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u0215\37\uffff\1\u0215",
            "\1\u0216\37\uffff\1\u0216",
            "",
            "",
            "\1\u0217\37\uffff\1\u0217",
            "\1\u0218\37\uffff\1\u0218",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u021a\37\uffff\1\u021a",
            "\135\132\1\u021b\uffa2\132",
            "\0\132",
            "",
            "",
            "",
            "",
            "",
            "",
            "\55\u008d\1\u021e\uffd2\u008d",
            "\60\u008d\12\u021f\uffc6\u008d",
            "\1\u0220",
            "\1\u0221\37\uffff\1\u0221",
            "\1\u0222\37\uffff\1\u0222",
            "\1\u0223\37\uffff\1\u0223",
            "\1\u0224\37\uffff\1\u0224",
            "\1\u0225\37\uffff\1\u0225",
            "\1\u0226\37\uffff\1\u0226",
            "\1\u0227\37\uffff\1\u0227",
            "\1\u0228\37\uffff\1\u0228",
            "\1\u0229\37\uffff\1\u0229",
            "\1\u022a\37\uffff\1\u022a",
            "\1\u022b\37\uffff\1\u022b",
            "",
            "",
            "\1\u022c",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u0230\37\uffff\1\u0230",
            "",
            "",
            "",
            "\60\u008d\4\u0232\uffcc\u008d",
            "\56\u008d\1\u0233\uffd1\u008d",
            "\1\u0234\11\uffff\1\u0235\25\uffff\1\u0234\11\uffff\1\u0235",
            "\1\u0236\37\uffff\1\u0236",
            "\1\u0237\37\uffff\1\u0237",
            "\1\u0238\37\uffff\1\u0238",
            "\1\u0239\37\uffff\1\u0239",
            "\1\u023a\37\uffff\1\u023a",
            "\1\u023b\37\uffff\1\u023b",
            "\1\u023c\37\uffff\1\u023c",
            "\1\u023d\37\uffff\1\u023d",
            "\1\u023e\37\uffff\1\u023e",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "",
            "",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\60\u008d\12\u0242\uffc6\u008d",
            "\60\u008d\12\u0243\uffc6\u008d",
            "",
            "",
            "\1\u0244\37\uffff\1\u0244",
            "\1\u0245\37\uffff\1\u0245",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u0247\37\uffff\1\u0247",
            "\1\u0248\37\uffff\1\u0248",
            "\1\u0249\37\uffff\1\u0249",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u024c\37\uffff\1\u024c",
            "",
            "",
            "",
            "\47\u008d\1\u024d\uffd8\u008d",
            "\60\u008d\12\u024e\uffc6\u008d",
            "\1\u024f\37\uffff\1\u024f",
            "\1\u0250\37\uffff\1\u0250",
            "",
            "\1\u0251\37\uffff\1\u0251",
            "\1\u0252\37\uffff\1\u0252",
            "\1\u0253\37\uffff\1\u0253",
            "",
            "",
            "\1\u0254\37\uffff\1\u0254",
            "\1\u0256",
            "\60\u008d\12\u0257\uffc6\u008d",
            "\1\u0258\37\uffff\1\u0258",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u025a\37\uffff\1\u025a",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\u025c\37\uffff\1\u025c",
            "\1\u025d\37\uffff\1\u025d",
            "",
            "",
            "\47\u008d\1\u025e\uffd8\u008d",
            "\1\u025f\37\uffff\1\u025f",
            "",
            "\1\u0260\37\uffff\1\u0260",
            "",
            "\1\u0261\37\uffff\1\u0261",
            "\1\u0262\37\uffff\1\u0262",
            "",
            "\1\u0264\37\uffff\1\u0264",
            "\1\u0265\37\uffff\1\u0265",
            "\1\u0266\37\uffff\1\u0266",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u0268\37\uffff\1\u0268",
            "\1\u0269\37\uffff\1\u0269",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            "\1\u026b\37\uffff\1\u026b",
            "\1\u026c\37\uffff\1\u026c",
            "",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "\1\63\10\uffff\1\63\2\uffff\12\63\7\uffff\32\63\4\uffff\1\63"+
            "\1\uffff\32\63\105\uffff\u0190\63\u01c0\uffff\100\63\u2bf1\uffff"+
            "\u00bf\63\u00f0\uffff\20\63\u0200\uffff\u19c0\63\100\uffff\u5200"+
            "\63\u5900\uffff\u0200\63\u043f\uffff\1\63",
            "",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( KEYWORD_125 | KEYWORD_126 | KEYWORD_123 | KEYWORD_124 | KEYWORD_122 | KEYWORD_121 | KEYWORD_120 | KEYWORD_118 | KEYWORD_119 | KEYWORD_117 | KEYWORD_110 | KEYWORD_111 | KEYWORD_112 | KEYWORD_113 | KEYWORD_114 | KEYWORD_115 | KEYWORD_116 | KEYWORD_106 | KEYWORD_107 | KEYWORD_108 | KEYWORD_109 | KEYWORD_101 | KEYWORD_102 | KEYWORD_103 | KEYWORD_104 | KEYWORD_105 | KEYWORD_92 | KEYWORD_93 | KEYWORD_94 | KEYWORD_95 | KEYWORD_96 | KEYWORD_97 | KEYWORD_98 | KEYWORD_99 | KEYWORD_100 | KEYWORD_82 | KEYWORD_83 | KEYWORD_84 | KEYWORD_85 | KEYWORD_86 | KEYWORD_87 | KEYWORD_88 | KEYWORD_89 | KEYWORD_90 | KEYWORD_91 | KEYWORD_74 | KEYWORD_75 | KEYWORD_76 | KEYWORD_77 | KEYWORD_78 | KEYWORD_79 | KEYWORD_80 | KEYWORD_81 | KEYWORD_57 | KEYWORD_58 | KEYWORD_59 | KEYWORD_60 | KEYWORD_61 | KEYWORD_62 | KEYWORD_63 | KEYWORD_64 | KEYWORD_65 | KEYWORD_66 | KEYWORD_67 | KEYWORD_68 | KEYWORD_69 | KEYWORD_70 | KEYWORD_71 | KEYWORD_72 | KEYWORD_73 | KEYWORD_36 | KEYWORD_37 | KEYWORD_38 | KEYWORD_39 | KEYWORD_40 | KEYWORD_41 | KEYWORD_42 | KEYWORD_43 | KEYWORD_44 | KEYWORD_45 | KEYWORD_46 | KEYWORD_47 | KEYWORD_48 | KEYWORD_49 | KEYWORD_50 | KEYWORD_51 | KEYWORD_52 | KEYWORD_53 | KEYWORD_54 | KEYWORD_55 | KEYWORD_56 | KEYWORD_25 | KEYWORD_26 | KEYWORD_27 | KEYWORD_28 | KEYWORD_29 | KEYWORD_30 | KEYWORD_31 | KEYWORD_32 | KEYWORD_33 | KEYWORD_34 | KEYWORD_35 | KEYWORD_14 | KEYWORD_15 | KEYWORD_16 | KEYWORD_17 | KEYWORD_18 | KEYWORD_19 | KEYWORD_20 | KEYWORD_21 | KEYWORD_22 | KEYWORD_23 | KEYWORD_24 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_11 | KEYWORD_12 | KEYWORD_13 | RULE_JRPARAM | RULE_JRNPARAM | RULE_STAR | RULE_UNSIGNED | RULE_INT | RULE_SIGNED_DOUBLE | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_STRING_ | RULE_STRING | RULE_DBNAME | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA19_578 = input.LA(1);

                        s = -1;
                        if ( (LA19_578=='\'') ) {s = 589;}

                        else if ( ((LA19_578>='\u0000' && LA19_578<='&')||(LA19_578>='(' && LA19_578<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA19_562 = input.LA(1);

                        s = -1;
                        if ( ((LA19_562>='0' && LA19_562<='9')) ) {s = 578;}

                        else if ( ((LA19_562>='\u0000' && LA19_562<='/')||(LA19_562>=':' && LA19_562<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA19_542 = input.LA(1);

                        s = -1;
                        if ( ((LA19_542>='0' && LA19_542<='3')) ) {s = 562;}

                        else if ( ((LA19_542>='\u0000' && LA19_542<='/')||(LA19_542>='4' && LA19_542<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA19_517 = input.LA(1);

                        s = -1;
                        if ( (LA19_517=='-') ) {s = 542;}

                        else if ( ((LA19_517>='\u0000' && LA19_517<=',')||(LA19_517>='.' && LA19_517<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA19_482 = input.LA(1);

                        s = -1;
                        if ( ((LA19_482>='0' && LA19_482<='9')) ) {s = 517;}

                        else if ( ((LA19_482>='\u0000' && LA19_482<='/')||(LA19_482>=':' && LA19_482<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA19_441 = input.LA(1);

                        s = -1;
                        if ( ((LA19_441>='0' && LA19_441<='1')) ) {s = 482;}

                        else if ( ((LA19_441>='\u0000' && LA19_441<='/')||(LA19_441>='2' && LA19_441<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA19_387 = input.LA(1);

                        s = -1;
                        if ( (LA19_387=='-') ) {s = 441;}

                        else if ( ((LA19_387>='\u0000' && LA19_387<=',')||(LA19_387>='.' && LA19_387<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA19_305 = input.LA(1);

                        s = -1;
                        if ( ((LA19_305>='0' && LA19_305<='9')) ) {s = 387;}

                        else if ( ((LA19_305>='\u0000' && LA19_305<='/')||(LA19_305>=':' && LA19_305<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA19_14 = input.LA(1);

                        s = -1;
                        if ( (LA19_14=='B'||LA19_14=='b') ) {s = 88;}

                        else if ( (LA19_14=='G'||LA19_14=='g') ) {s = 89;}

                        else if ( ((LA19_14>='\u0000' && LA19_14<='A')||(LA19_14>='C' && LA19_14<='F')||(LA19_14>='H' && LA19_14<='a')||(LA19_14>='c' && LA19_14<='f')||(LA19_14>='h' && LA19_14<='\uFFFF')) ) {s = 90;}

                        else s = 49;

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA19_509 = input.LA(1);

                        s = -1;
                        if ( (LA19_509==']') ) {s = 539;}

                        else if ( ((LA19_509>='\u0000' && LA19_509<='\\')||(LA19_509>='^' && LA19_509<='\uFFFF')) ) {s = 90;}

                        else s = 540;

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA19_590 = input.LA(1);

                        s = -1;
                        if ( ((LA19_590>='0' && LA19_590<='9')) ) {s = 599;}

                        else if ( ((LA19_590>='\u0000' && LA19_590<='/')||(LA19_590>=':' && LA19_590<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA19_599 = input.LA(1);

                        s = -1;
                        if ( (LA19_599=='\'') ) {s = 606;}

                        else if ( ((LA19_599>='\u0000' && LA19_599<='&')||(LA19_599>='(' && LA19_599<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA19_563 = input.LA(1);

                        s = -1;
                        if ( ((LA19_563>='0' && LA19_563<='9')) ) {s = 579;}

                        else if ( ((LA19_563>='\u0000' && LA19_563<='/')||(LA19_563>=':' && LA19_563<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA19_579 = input.LA(1);

                        s = -1;
                        if ( ((LA19_579>='0' && LA19_579<='9')) ) {s = 590;}

                        else if ( ((LA19_579>='\u0000' && LA19_579<='/')||(LA19_579>=':' && LA19_579<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA19_518 = input.LA(1);

                        s = -1;
                        if ( ((LA19_518>='0' && LA19_518<='9')) ) {s = 543;}

                        else if ( ((LA19_518>='\u0000' && LA19_518<='/')||(LA19_518>=':' && LA19_518<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA19_543 = input.LA(1);

                        s = -1;
                        if ( (LA19_543=='.') ) {s = 563;}

                        else if ( ((LA19_543>='\u0000' && LA19_543<='-')||(LA19_543>='/' && LA19_543<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA19_45 = input.LA(1);

                        s = -1;
                        if ( ((LA19_45>='\u0000' && LA19_45<='\uFFFF')) ) {s = 90;}

                        else s = 49;

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA19_0 = input.LA(1);

                        s = -1;
                        if ( (LA19_0=='U'||LA19_0=='u') ) {s = 1;}

                        else if ( (LA19_0=='M'||LA19_0=='m') ) {s = 2;}

                        else if ( (LA19_0=='S'||LA19_0=='s') ) {s = 3;}

                        else if ( (LA19_0=='O'||LA19_0=='o') ) {s = 4;}

                        else if ( (LA19_0=='H'||LA19_0=='h') ) {s = 5;}

                        else if ( (LA19_0=='D'||LA19_0=='d') ) {s = 6;}

                        else if ( (LA19_0=='P'||LA19_0=='p') ) {s = 7;}

                        else if ( (LA19_0=='C'||LA19_0=='c') ) {s = 8;}

                        else if ( (LA19_0=='F'||LA19_0=='f') ) {s = 9;}

                        else if ( (LA19_0=='I'||LA19_0=='i') ) {s = 10;}

                        else if ( (LA19_0=='N'||LA19_0=='n') ) {s = 11;}

                        else if ( (LA19_0=='Y'||LA19_0=='y') ) {s = 12;}

                        else if ( (LA19_0=='W'||LA19_0=='w') ) {s = 13;}

                        else if ( (LA19_0=='[') ) {s = 14;}

                        else if ( (LA19_0=='B'||LA19_0=='b') ) {s = 15;}

                        else if ( (LA19_0=='G'||LA19_0=='g') ) {s = 16;}

                        else if ( (LA19_0=='E'||LA19_0=='e') ) {s = 17;}

                        else if ( (LA19_0=='Q'||LA19_0=='q') ) {s = 18;}

                        else if ( (LA19_0=='L'||LA19_0=='l') ) {s = 19;}

                        else if ( (LA19_0=='R'||LA19_0=='r') ) {s = 20;}

                        else if ( (LA19_0=='J'||LA19_0=='j') ) {s = 21;}

                        else if ( (LA19_0=='T'||LA19_0=='t') ) {s = 22;}

                        else if ( (LA19_0=='(') ) {s = 23;}

                        else if ( (LA19_0=='A'||LA19_0=='a') ) {s = 24;}

                        else if ( (LA19_0=='X'||LA19_0=='x') ) {s = 25;}

                        else if ( (LA19_0=='!') ) {s = 26;}

                        else if ( (LA19_0=='$') ) {s = 27;}

                        else if ( (LA19_0=='<') ) {s = 28;}

                        else if ( (LA19_0=='>') ) {s = 29;}

                        else if ( (LA19_0=='^') ) {s = 30;}

                        else if ( (LA19_0=='|') ) {s = 31;}

                        else if ( (LA19_0==')') ) {s = 32;}

                        else if ( (LA19_0=='+') ) {s = 33;}

                        else if ( (LA19_0==',') ) {s = 34;}

                        else if ( (LA19_0=='-') ) {s = 35;}

                        else if ( (LA19_0=='.') ) {s = 36;}

                        else if ( (LA19_0=='/') ) {s = 37;}

                        else if ( (LA19_0=='=') ) {s = 38;}

                        else if ( (LA19_0=='{') ) {s = 39;}

                        else if ( (LA19_0=='}') ) {s = 40;}

                        else if ( (LA19_0=='*') ) {s = 41;}

                        else if ( ((LA19_0>='0' && LA19_0<='9')) ) {s = 42;}

                        else if ( (LA19_0=='\'') ) {s = 43;}

                        else if ( (LA19_0=='\"') ) {s = 44;}

                        else if ( (LA19_0=='`') ) {s = 45;}

                        else if ( (LA19_0=='K'||LA19_0=='V'||LA19_0=='Z'||LA19_0=='_'||LA19_0=='k'||LA19_0=='v'||LA19_0=='z'||(LA19_0>='\u00C0' && LA19_0<='\u024F')||(LA19_0>='\u0410' && LA19_0<='\u044F')||(LA19_0>='\u3041' && LA19_0<='\u30FF')||(LA19_0>='\u31F0' && LA19_0<='\u31FF')||(LA19_0>='\u3400' && LA19_0<='\u4DBF')||(LA19_0>='\u4E00' && LA19_0<='\u9FFF')||(LA19_0>='\uF900' && LA19_0<='\uFAFF')||LA19_0=='\uFF3F') ) {s = 46;}

                        else if ( (LA19_0=='#') ) {s = 47;}

                        else if ( ((LA19_0>='\t' && LA19_0<='\n')||LA19_0=='\r'||LA19_0==' ') ) {s = 48;}

                        else if ( ((LA19_0>='\u0000' && LA19_0<='\b')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\u001F')||(LA19_0>='%' && LA19_0<='&')||(LA19_0>=':' && LA19_0<=';')||(LA19_0>='?' && LA19_0<='@')||(LA19_0>='\\' && LA19_0<=']')||(LA19_0>='~' && LA19_0<='\u00BF')||(LA19_0>='\u0250' && LA19_0<='\u040F')||(LA19_0>='\u0450' && LA19_0<='\u3040')||(LA19_0>='\u3100' && LA19_0<='\u31EF')||(LA19_0>='\u3200' && LA19_0<='\u33FF')||(LA19_0>='\u4DC0' && LA19_0<='\u4DFF')||(LA19_0>='\uA000' && LA19_0<='\uF8FF')||(LA19_0>='\uFB00' && LA19_0<='\uFF3E')||(LA19_0>='\uFF40' && LA19_0<='\uFFFF')) ) {s = 49;}

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA19_428 = input.LA(1);

                        s = -1;
                        if ( (LA19_428=='E'||LA19_428=='e') ) {s = 474;}

                        else if ( ((LA19_428>='\u0000' && LA19_428<='D')||(LA19_428>='F' && LA19_428<='d')||(LA19_428>='f' && LA19_428<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA19_364 = input.LA(1);

                        s = -1;
                        if ( (LA19_364=='E'||LA19_364=='e') ) {s = 427;}

                        else if ( ((LA19_364>='\u0000' && LA19_364<='D')||(LA19_364>='F' && LA19_364<='d')||(LA19_364>='f' && LA19_364<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA19_365 = input.LA(1);

                        s = -1;
                        if ( (LA19_365=='T'||LA19_365=='t') ) {s = 428;}

                        else if ( ((LA19_365>='\u0000' && LA19_365<='S')||(LA19_365>='U' && LA19_365<='s')||(LA19_365>='u' && LA19_365<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA19_474 = input.LA(1);

                        s = -1;
                        if ( (LA19_474=='R'||LA19_474=='r') ) {s = 510;}

                        else if ( ((LA19_474>='\u0000' && LA19_474<='Q')||(LA19_474>='S' && LA19_474<='q')||(LA19_474>='s' && LA19_474<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA19_275 = input.LA(1);

                        s = -1;
                        if ( (LA19_275=='W'||LA19_275=='w') ) {s = 364;}

                        else if ( ((LA19_275>='\u0000' && LA19_275<='V')||(LA19_275>='X' && LA19_275<='v')||(LA19_275>='x' && LA19_275<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA19_89 = input.LA(1);

                        s = -1;
                        if ( (LA19_89=='R'||LA19_89=='r') ) {s = 192;}

                        else if ( ((LA19_89>='\u0000' && LA19_89<='Q')||(LA19_89>='S' && LA19_89<='q')||(LA19_89>='s' && LA19_89<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA19_473 = input.LA(1);

                        s = -1;
                        if ( (LA19_473=='N'||LA19_473=='n') ) {s = 509;}

                        else if ( ((LA19_473>='\u0000' && LA19_473<='M')||(LA19_473>='O' && LA19_473<='m')||(LA19_473>='o' && LA19_473<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA19_276 = input.LA(1);

                        s = -1;
                        if ( (LA19_276=='A'||LA19_276=='a') ) {s = 365;}

                        else if ( ((LA19_276>='\u0000' && LA19_276<='@')||(LA19_276>='B' && LA19_276<='`')||(LA19_276>='b' && LA19_276<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA19_427 = input.LA(1);

                        s = -1;
                        if ( (LA19_427=='E'||LA19_427=='e') ) {s = 473;}

                        else if ( ((LA19_427>='\u0000' && LA19_427<='D')||(LA19_427>='F' && LA19_427<='d')||(LA19_427>='f' && LA19_427<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA19_192 = input.LA(1);

                        s = -1;
                        if ( (LA19_192=='E'||LA19_192=='e') ) {s = 276;}

                        else if ( ((LA19_192>='\u0000' && LA19_192<='D')||(LA19_192>='F' && LA19_192<='d')||(LA19_192>='f' && LA19_192<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA19_140 = input.LA(1);

                        s = -1;
                        if ( ((LA19_140>='0' && LA19_140<='9')) ) {s = 224;}

                        else if ( ((LA19_140>='\u0000' && LA19_140<='/')||(LA19_140>=':' && LA19_140<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA19_224 = input.LA(1);

                        s = -1;
                        if ( ((LA19_224>='0' && LA19_224<='9')) ) {s = 305;}

                        else if ( (LA19_224==':') ) {s = 306;}

                        else if ( ((LA19_224>='\u0000' && LA19_224<='/')||(LA19_224>=';' && LA19_224<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA19_88 = input.LA(1);

                        s = -1;
                        if ( (LA19_88=='E'||LA19_88=='e') ) {s = 191;}

                        else if ( ((LA19_88>='\u0000' && LA19_88<='D')||(LA19_88>='F' && LA19_88<='d')||(LA19_88>='f' && LA19_88<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA19_191 = input.LA(1);

                        s = -1;
                        if ( (LA19_191=='T'||LA19_191=='t') ) {s = 275;}

                        else if ( ((LA19_191>='\u0000' && LA19_191<='S')||(LA19_191>='U' && LA19_191<='s')||(LA19_191>='u' && LA19_191<='\uFFFF')) ) {s = 90;}

                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA19_43 = input.LA(1);

                        s = -1;
                        if ( ((LA19_43>='0' && LA19_43<='9')) ) {s = 140;}

                        else if ( ((LA19_43>='\u0000' && LA19_43<='/')||(LA19_43>=':' && LA19_43<='\uFFFF')) ) {s = 141;}

                        else s = 49;

                        if ( s>=0 ) return s;
                        break;
                    case 33 : 
                        int LA19_44 = input.LA(1);

                        s = -1;
                        if ( ((LA19_44>='\u0000' && LA19_44<='\uFFFF')) ) {s = 142;}

                        else s = 49;

                        if ( s>=0 ) return s;
                        break;
                    case 34 : 
                        int LA19_442 = input.LA(1);

                        s = -1;
                        if ( (LA19_442==':') ) {s = 483;}

                        else if ( ((LA19_442>='\u0000' && LA19_442<='9')||(LA19_442>=';' && LA19_442<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 35 : 
                        int LA19_483 = input.LA(1);

                        s = -1;
                        if ( ((LA19_483>='0' && LA19_483<='9')) ) {s = 518;}

                        else if ( ((LA19_483>='\u0000' && LA19_483<='/')||(LA19_483>=':' && LA19_483<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 36 : 
                        int LA19_306 = input.LA(1);

                        s = -1;
                        if ( ((LA19_306>='0' && LA19_306<='9')) ) {s = 388;}

                        else if ( ((LA19_306>='\u0000' && LA19_306<='/')||(LA19_306>=':' && LA19_306<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 37 : 
                        int LA19_388 = input.LA(1);

                        s = -1;
                        if ( ((LA19_388>='0' && LA19_388<='9')) ) {s = 442;}

                        else if ( ((LA19_388>='\u0000' && LA19_388<='/')||(LA19_388>=':' && LA19_388<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 38 : 
                        int LA19_510 = input.LA(1);

                        s = -1;
                        if ( ((LA19_510>='\u0000' && LA19_510<='\uFFFF')) ) {s = 90;}

                        else s = 541;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 19, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}