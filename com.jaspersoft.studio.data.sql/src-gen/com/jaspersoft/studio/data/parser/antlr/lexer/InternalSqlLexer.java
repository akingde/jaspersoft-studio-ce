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
    public static final int LessThanSignGreaterThanSign=108;
    public static final int MONTH=59;
    public static final int JOIN=76;
    public static final int BETWEEN=31;
    public static final int LessThanSign=125;
    public static final int MINUS=58;
    public static final int LeftParenthesisPlusSignRightParenthesis=92;
    public static final int WHEN=89;
    public static final int QUARTER=38;
    public static final int LeftParenthesis=118;
    public static final int YEAR=91;
    public static final int ELSE=72;
    public static final int RULE_TIME=137;
    public static final int HOUR_MINUTE=10;
    public static final int PARTITION=19;
    public static final int INCLUDE=35;
    public static final int DAY_MINUTE=13;
    public static final int INNER=56;
    public static final int CAST=51;
    public static final int GreaterThanSign=127;
    public static final int LEFT=78;
    public static final int RULE_ID=143;
    public static final int IN=112;
    public static final int DISTINCT=27;
    public static final int IS=113;
    public static final int WHERE=68;
    public static final int CASE=70;
    public static final int GreaterThanSignEqualsSign=109;
    public static final int AS=110;
    public static final int RULE_DATE=136;
    public static final int TOP=102;
    public static final int VerticalLine=128;
    public static final int PlusSign=120;
    public static final int RULE_INT=134;
    public static final int RULE_ML_COMMENT=144;
    public static final int THEN=86;
    public static final int UNPIVOT=39;
    public static final int RULE_JRPARAM=130;
    public static final int MICROSECOND=12;
    public static final int VerticalLineVerticalLine=117;
    public static final int DAY_HOUR=26;
    public static final int RULE_DBNAME=142;
    public static final int GROUP=55;
    public static final int ORDER=61;
    public static final int ASC=96;
    public static final int Comma=121;
    public static final int HyphenMinus=122;
    public static final int STRAIGHT_JOIN=9;
    public static final int BY=111;
    public static final int X=106;
    public static final int OFFSET=44;
    public static final int RIGHT=65;
    public static final int VALUES=47;
    public static final int LessThanSignEqualsSign=107;
    public static final int Solidus=124;
    public static final int RightCurlyBracket=129;
    public static final int RULE_SIGNED_DOUBLE=135;
    public static final int FETCH=53;
    public static final int FullStop=123;
    public static final int RULE_UNSIGNED=133;
    public static final int SIBLINGS=28;
    public static final int GREATER=30;
    public static final int NOTIN=50;
    public static final int SECOND_MICROSECOND=5;
    public static final int FIRST=54;
    public static final int RULE_STRING_=140;
    public static final int SELECT=46;
    public static final int PRECEDING=20;
    public static final int RULE_JRNPARAM=131;
    public static final int PERCENT=37;
    public static final int ExclamationMarkEqualsSign=105;
    public static final int UNION=66;
    public static final int DAY=97;
    public static final int ALL=93;
    public static final int ONLY=82;
    public static final int FROM=73;
    public static final int DESC=71;
    public static final int MINUTE_MICROSECOND=4;
    public static final int UNBOUNDED=21;
    public static final int KW_FOLLOWING=17;
    public static final int MINUTE=43;
    public static final int RULE_STAR=132;
    public static final int HOUR_MICROSECOND=6;
    public static final int EXTRACT=34;
    public static final int NULL=81;
    public static final int DAY_MICROSECOND=7;
    public static final int LESS_1=49;
    public static final int FOR=99;
    public static final int RightParenthesis=119;
    public static final int PIVOT=63;
    public static final int EXCEPT=40;
    public static final int CURRENT=32;
    public static final int FULL=74;
    public static final int NOTEQUAL=23;
    public static final int USING=67;
    public static final int NOT=100;
    public static final int LIKE=79;
    public static final int LAST=77;
    public static final int IN_1=104;
    public static final int EXCLUDE=33;
    public static final int AND=94;
    public static final int CircumflexAccentEqualsSign=116;
    public static final int MINUTE_SECOND=8;
    public static final int YEAR_MONTH=15;
    public static final int LESS=69;
    public static final int END=98;
    public static final int ROW=101;
    public static final int HAVING=42;
    public static final int DAY_SECOND=14;
    public static final int RANGE=64;
    public static final int TIES=87;
    public static final int HOUR=75;
    public static final int LIMIT=57;
    public static final int RULE_STRING=141;
    public static final int ANY=95;
    public static final int RULE_SL_COMMENT=145;
    public static final int NATURAL=36;
    public static final int EqualsSign=126;
    public static final int SOME=85;
    public static final int NOT_1=80;
    public static final int BETWEEN_2=22;
    public static final int GREATER_1=25;
    public static final int BETWEEN_1=29;
    public static final int OUTER=62;
    public static final int WEEK=88;
    public static final int EOF=-1;
    public static final int BETWEEN_4=16;
    public static final int NULLS=60;
    public static final int BETWEEN_3=24;
    public static final int ON=114;
    public static final int OR=115;
    public static final int EXISTS=41;
    public static final int RULE_WS=146;
    public static final int EQUAL=48;
    public static final int RULE_ANY_OTHER=147;
    public static final int INTERSECT=18;
    public static final int WITH=90;
    public static final int OVER=83;
    public static final int CROSS=52;
    public static final int XML=103;
    public static final int SECOND=45;
    public static final int RULE_STRING_CORE=139;
    public static final int HOUR_SECOND=11;
    public static final int RULE_TIMESTAMP=138;
    public static final int ROWS=84;

    // delegates
    // delegators

    public InternalSqlLexer() {;} 
    public InternalSqlLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalSqlLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalSqlLexer.g"; }

    // $ANTLR start "MINUTE_MICROSECOND"
    public final void mMINUTE_MICROSECOND() throws RecognitionException {
        try {
            int _type = MINUTE_MICROSECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:19:20: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:19:22: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "MINUTE_MICROSECOND"

    // $ANTLR start "SECOND_MICROSECOND"
    public final void mSECOND_MICROSECOND() throws RecognitionException {
        try {
            int _type = SECOND_MICROSECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:21:20: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:21:22: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "SECOND_MICROSECOND"

    // $ANTLR start "HOUR_MICROSECOND"
    public final void mHOUR_MICROSECOND() throws RecognitionException {
        try {
            int _type = HOUR_MICROSECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:23:18: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:23:20: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "HOUR_MICROSECOND"

    // $ANTLR start "DAY_MICROSECOND"
    public final void mDAY_MICROSECOND() throws RecognitionException {
        try {
            int _type = DAY_MICROSECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:25:17: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:25:19: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "DAY_MICROSECOND"

    // $ANTLR start "MINUTE_SECOND"
    public final void mMINUTE_SECOND() throws RecognitionException {
        try {
            int _type = MINUTE_SECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:27:15: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:27:17: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "MINUTE_SECOND"

    // $ANTLR start "STRAIGHT_JOIN"
    public final void mSTRAIGHT_JOIN() throws RecognitionException {
        try {
            int _type = STRAIGHT_JOIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:29:15: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) '_' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:29:17: ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) '_' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "STRAIGHT_JOIN"

    // $ANTLR start "HOUR_MINUTE"
    public final void mHOUR_MINUTE() throws RecognitionException {
        try {
            int _type = HOUR_MINUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:31:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:31:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
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
    // $ANTLR end "HOUR_MINUTE"

    // $ANTLR start "HOUR_SECOND"
    public final void mHOUR_SECOND() throws RecognitionException {
        try {
            int _type = HOUR_SECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:33:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:33:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "HOUR_SECOND"

    // $ANTLR start "MICROSECOND"
    public final void mMICROSECOND() throws RecognitionException {
        try {
            int _type = MICROSECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:35:13: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:35:15: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "MICROSECOND"

    // $ANTLR start "DAY_MINUTE"
    public final void mDAY_MINUTE() throws RecognitionException {
        try {
            int _type = DAY_MINUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:37:12: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:37:14: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
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
    // $ANTLR end "DAY_MINUTE"

    // $ANTLR start "DAY_SECOND"
    public final void mDAY_SECOND() throws RecognitionException {
        try {
            int _type = DAY_SECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:39:12: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:39:14: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "DAY_SECOND"

    // $ANTLR start "YEAR_MONTH"
    public final void mYEAR_MONTH() throws RecognitionException {
        try {
            int _type = YEAR_MONTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:41:12: ( ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) )
            // InternalSqlLexer.g:41:14: ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' )
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
    // $ANTLR end "YEAR_MONTH"

    // $ANTLR start "BETWEEN_4"
    public final void mBETWEEN_4() throws RecognitionException {
        try {
            int _type = BETWEEN_4;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:43:11: ( '{' '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // InternalSqlLexer.g:43:13: '{' '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
            {
            match('{'); 
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
    // $ANTLR end "BETWEEN_4"

    // $ANTLR start "KW_FOLLOWING"
    public final void mKW_FOLLOWING() throws RecognitionException {
        try {
            int _type = KW_FOLLOWING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:45:14: ( ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:45:16: ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "KW_FOLLOWING"

    // $ANTLR start "INTERSECT"
    public final void mINTERSECT() throws RecognitionException {
        try {
            int _type = INTERSECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:47:11: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:47:13: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "INTERSECT"

    // $ANTLR start "PARTITION"
    public final void mPARTITION() throws RecognitionException {
        try {
            int _type = PARTITION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:49:11: ( ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:49:13: ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PARTITION"

    // $ANTLR start "PRECEDING"
    public final void mPRECEDING() throws RecognitionException {
        try {
            int _type = PRECEDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:51:11: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:51:13: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "PRECEDING"

    // $ANTLR start "UNBOUNDED"
    public final void mUNBOUNDED() throws RecognitionException {
        try {
            int _type = UNBOUNDED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:53:11: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:53:13: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNBOUNDED"

    // $ANTLR start "BETWEEN_2"
    public final void mBETWEEN_2() throws RecognitionException {
        try {
            int _type = BETWEEN_2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:55:11: ( '{' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // InternalSqlLexer.g:55:13: '{' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
            {
            match('{'); 
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
    // $ANTLR end "BETWEEN_2"

    // $ANTLR start "NOTEQUAL"
    public final void mNOTEQUAL() throws RecognitionException {
        try {
            int _type = NOTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:57:10: ( '{' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:57:12: '{' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            match('{'); 
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
    // $ANTLR end "NOTEQUAL"

    // $ANTLR start "BETWEEN_3"
    public final void mBETWEEN_3() throws RecognitionException {
        try {
            int _type = BETWEEN_3;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:59:11: ( '{' '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:59:13: '{' '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
            {
            match('{'); 
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
    // $ANTLR end "BETWEEN_3"

    // $ANTLR start "GREATER_1"
    public final void mGREATER_1() throws RecognitionException {
        try {
            int _type = GREATER_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:61:11: ( '{' '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:61:13: '{' '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
            {
            match('{'); 
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
    // $ANTLR end "GREATER_1"

    // $ANTLR start "DAY_HOUR"
    public final void mDAY_HOUR() throws RecognitionException {
        try {
            int _type = DAY_HOUR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:63:10: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:63:12: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' )
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
    // $ANTLR end "DAY_HOUR"

    // $ANTLR start "DISTINCT"
    public final void mDISTINCT() throws RecognitionException {
        try {
            int _type = DISTINCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:65:10: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:65:12: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "DISTINCT"

    // $ANTLR start "SIBLINGS"
    public final void mSIBLINGS() throws RecognitionException {
        try {
            int _type = SIBLINGS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:67:10: ( ( 'S' | 's' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:67:12: ( 'S' | 's' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'S' | 's' )
            {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SIBLINGS"

    // $ANTLR start "BETWEEN_1"
    public final void mBETWEEN_1() throws RecognitionException {
        try {
            int _type = BETWEEN_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:69:11: ( '{' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:69:13: '{' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
            {
            match('{'); 
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
    // $ANTLR end "BETWEEN_1"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:71:9: ( '{' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:71:11: '{' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
            {
            match('{'); 
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
    // $ANTLR end "GREATER"

    // $ANTLR start "BETWEEN"
    public final void mBETWEEN() throws RecognitionException {
        try {
            int _type = BETWEEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:73:9: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:73:11: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "BETWEEN"

    // $ANTLR start "CURRENT"
    public final void mCURRENT() throws RecognitionException {
        try {
            int _type = CURRENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:75:9: ( ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:75:11: ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CURRENT"

    // $ANTLR start "EXCLUDE"
    public final void mEXCLUDE() throws RecognitionException {
        try {
            int _type = EXCLUDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:77:9: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:77:11: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' )
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
    // $ANTLR end "EXCLUDE"

    // $ANTLR start "EXTRACT"
    public final void mEXTRACT() throws RecognitionException {
        try {
            int _type = EXTRACT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:79:9: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:79:11: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "EXTRACT"

    // $ANTLR start "INCLUDE"
    public final void mINCLUDE() throws RecognitionException {
        try {
            int _type = INCLUDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:81:9: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:81:11: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' )
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
    // $ANTLR end "INCLUDE"

    // $ANTLR start "NATURAL"
    public final void mNATURAL() throws RecognitionException {
        try {
            int _type = NATURAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:83:9: ( ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:83:11: ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
    // $ANTLR end "NATURAL"

    // $ANTLR start "PERCENT"
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:85:9: ( ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:85:11: ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' )
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
    // $ANTLR end "PERCENT"

    // $ANTLR start "QUARTER"
    public final void mQUARTER() throws RecognitionException {
        try {
            int _type = QUARTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:87:9: ( ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:87:11: ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "QUARTER"

    // $ANTLR start "UNPIVOT"
    public final void mUNPIVOT() throws RecognitionException {
        try {
            int _type = UNPIVOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:89:9: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:89:11: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' )
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
    // $ANTLR end "UNPIVOT"

    // $ANTLR start "EXCEPT"
    public final void mEXCEPT() throws RecognitionException {
        try {
            int _type = EXCEPT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:91:8: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:91:10: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' )
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
    // $ANTLR end "EXCEPT"

    // $ANTLR start "EXISTS"
    public final void mEXISTS() throws RecognitionException {
        try {
            int _type = EXISTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:93:8: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:93:10: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' )
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
    // $ANTLR end "EXISTS"

    // $ANTLR start "HAVING"
    public final void mHAVING() throws RecognitionException {
        try {
            int _type = HAVING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:95:8: ( ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:95:10: ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "HAVING"

    // $ANTLR start "MINUTE"
    public final void mMINUTE() throws RecognitionException {
        try {
            int _type = MINUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:97:8: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:97:10: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
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
    // $ANTLR end "MINUTE"

    // $ANTLR start "OFFSET"
    public final void mOFFSET() throws RecognitionException {
        try {
            int _type = OFFSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:99:8: ( ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:99:10: ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
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
    // $ANTLR end "OFFSET"

    // $ANTLR start "SECOND"
    public final void mSECOND() throws RecognitionException {
        try {
            int _type = SECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:101:8: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:101:10: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "SECOND"

    // $ANTLR start "SELECT"
    public final void mSELECT() throws RecognitionException {
        try {
            int _type = SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:103:8: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:103:10: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
    // $ANTLR end "SELECT"

    // $ANTLR start "VALUES"
    public final void mVALUES() throws RecognitionException {
        try {
            int _type = VALUES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:105:8: ( ( 'V' | 'v' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:105:10: ( 'V' | 'v' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
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

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
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
    // $ANTLR end "VALUES"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:107:7: ( '{' ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:107:9: '{' ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            match('{'); 
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
    // $ANTLR end "EQUAL"

    // $ANTLR start "LESS_1"
    public final void mLESS_1() throws RecognitionException {
        try {
            int _type = LESS_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:109:8: ( '{' ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']' )
            // InternalSqlLexer.g:109:10: '{' ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']'
            {
            match('{'); 
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
    // $ANTLR end "LESS_1"

    // $ANTLR start "NOTIN"
    public final void mNOTIN() throws RecognitionException {
        try {
            int _type = NOTIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:111:7: ( '{' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:111:9: '{' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' )
            {
            match('{'); 
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
    // $ANTLR end "NOTIN"

    // $ANTLR start "CAST"
    public final void mCAST() throws RecognitionException {
        try {
            int _type = CAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:113:6: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) '(' )
            // InternalSqlLexer.g:113:8: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) '('
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
    // $ANTLR end "CAST"

    // $ANTLR start "CROSS"
    public final void mCROSS() throws RecognitionException {
        try {
            int _type = CROSS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:115:7: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:115:9: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' )
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
    // $ANTLR end "CROSS"

    // $ANTLR start "FETCH"
    public final void mFETCH() throws RecognitionException {
        try {
            int _type = FETCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:117:7: ( ( 'F' | 'f' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) )
            // InternalSqlLexer.g:117:9: ( 'F' | 'f' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FETCH"

    // $ANTLR start "FIRST"
    public final void mFIRST() throws RecognitionException {
        try {
            int _type = FIRST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:119:7: ( ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:119:9: ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' )
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
    // $ANTLR end "FIRST"

    // $ANTLR start "GROUP"
    public final void mGROUP() throws RecognitionException {
        try {
            int _type = GROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:121:7: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) )
            // InternalSqlLexer.g:121:9: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GROUP"

    // $ANTLR start "INNER"
    public final void mINNER() throws RecognitionException {
        try {
            int _type = INNER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:123:7: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:123:9: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "INNER"

    // $ANTLR start "LIMIT"
    public final void mLIMIT() throws RecognitionException {
        try {
            int _type = LIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:125:7: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:125:9: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' | 't' )
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
    // $ANTLR end "LIMIT"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:127:7: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:127:9: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' )
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
    // $ANTLR end "MINUS"

    // $ANTLR start "MONTH"
    public final void mMONTH() throws RecognitionException {
        try {
            int _type = MONTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:129:7: ( ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) )
            // InternalSqlLexer.g:129:9: ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' )
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
    // $ANTLR end "MONTH"

    // $ANTLR start "NULLS"
    public final void mNULLS() throws RecognitionException {
        try {
            int _type = NULLS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:131:7: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:131:9: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'S' | 's' )
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
    // $ANTLR end "NULLS"

    // $ANTLR start "ORDER"
    public final void mORDER() throws RecognitionException {
        try {
            int _type = ORDER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:133:7: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:133:9: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ORDER"

    // $ANTLR start "OUTER"
    public final void mOUTER() throws RecognitionException {
        try {
            int _type = OUTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:135:7: ( ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:135:9: ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "OUTER"

    // $ANTLR start "PIVOT"
    public final void mPIVOT() throws RecognitionException {
        try {
            int _type = PIVOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:137:7: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:137:9: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' )
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
    // $ANTLR end "PIVOT"

    // $ANTLR start "RANGE"
    public final void mRANGE() throws RecognitionException {
        try {
            int _type = RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:139:7: ( ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:139:9: ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' | 'e' )
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
    // $ANTLR end "RANGE"

    // $ANTLR start "RIGHT"
    public final void mRIGHT() throws RecognitionException {
        try {
            int _type = RIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:141:7: ( ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:141:9: ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' )
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
    // $ANTLR end "RIGHT"

    // $ANTLR start "UNION"
    public final void mUNION() throws RecognitionException {
        try {
            int _type = UNION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:143:7: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:143:9: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
    // $ANTLR end "UNION"

    // $ANTLR start "USING"
    public final void mUSING() throws RecognitionException {
        try {
            int _type = USING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:145:7: ( ( 'U' | 'u' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:145:9: ( 'U' | 'u' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
            {
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
    // $ANTLR end "USING"

    // $ANTLR start "WHERE"
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:147:7: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:147:9: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' )
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
    // $ANTLR end "WHERE"

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:149:6: ( '{' ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:149:8: '{' ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' )
            {
            match('{'); 
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
    // $ANTLR end "LESS"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:151:6: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:151:8: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' )
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
    // $ANTLR end "CASE"

    // $ANTLR start "DESC"
    public final void mDESC() throws RecognitionException {
        try {
            int _type = DESC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:153:6: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // InternalSqlLexer.g:153:8: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
    // $ANTLR end "DESC"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:155:6: ( ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:155:8: ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' )
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
    // $ANTLR end "ELSE"

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:157:6: ( ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' ) )
            // InternalSqlLexer.g:157:8: ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' )
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
    // $ANTLR end "FROM"

    // $ANTLR start "FULL"
    public final void mFULL() throws RecognitionException {
        try {
            int _type = FULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:159:6: ( ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:159:8: ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "FULL"

    // $ANTLR start "HOUR"
    public final void mHOUR() throws RecognitionException {
        try {
            int _type = HOUR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:161:6: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:161:8: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' )
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
    // $ANTLR end "HOUR"

    // $ANTLR start "JOIN"
    public final void mJOIN() throws RecognitionException {
        try {
            int _type = JOIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:163:6: ( ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:163:8: ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "JOIN"

    // $ANTLR start "LAST"
    public final void mLAST() throws RecognitionException {
        try {
            int _type = LAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:165:6: ( ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:165:8: ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' )
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
    // $ANTLR end "LAST"

    // $ANTLR start "LEFT"
    public final void mLEFT() throws RecognitionException {
        try {
            int _type = LEFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:167:6: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:167:8: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' )
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
    // $ANTLR end "LEFT"

    // $ANTLR start "LIKE"
    public final void mLIKE() throws RecognitionException {
        try {
            int _type = LIKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:169:6: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:169:8: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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
    // $ANTLR end "LIKE"

    // $ANTLR start "NOT_1"
    public final void mNOT_1() throws RecognitionException {
        try {
            int _type = NOT_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:171:7: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) '\\n' )
            // InternalSqlLexer.g:171:9: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) '\\n'
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
    // $ANTLR end "NOT_1"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:173:6: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:173:8: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "ONLY"
    public final void mONLY() throws RecognitionException {
        try {
            int _type = ONLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:175:6: ( ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:175:8: ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'Y' | 'y' )
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
    // $ANTLR end "ONLY"

    // $ANTLR start "OVER"
    public final void mOVER() throws RecognitionException {
        try {
            int _type = OVER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:177:6: ( ( 'O' | 'o' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:177:8: ( 'O' | 'o' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "OVER"

    // $ANTLR start "ROWS"
    public final void mROWS() throws RecognitionException {
        try {
            int _type = ROWS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:179:6: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:179:8: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'S' | 's' )
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
    // $ANTLR end "ROWS"

    // $ANTLR start "SOME"
    public final void mSOME() throws RecognitionException {
        try {
            int _type = SOME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:181:6: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:181:8: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' )
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
    // $ANTLR end "SOME"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:183:6: ( ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:183:8: ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "THEN"

    // $ANTLR start "TIES"
    public final void mTIES() throws RecognitionException {
        try {
            int _type = TIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:185:6: ( ( 'T' | 't' ) ( 'I' | 'i' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:185:8: ( 'T' | 't' ) ( 'I' | 'i' ) ( 'E' | 'e' ) ( 'S' | 's' )
            {
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
    // $ANTLR end "TIES"

    // $ANTLR start "WEEK"
    public final void mWEEK() throws RecognitionException {
        try {
            int _type = WEEK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:187:6: ( ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'K' | 'k' ) )
            // InternalSqlLexer.g:187:8: ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'K' | 'k' )
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
    // $ANTLR end "WEEK"

    // $ANTLR start "WHEN"
    public final void mWHEN() throws RecognitionException {
        try {
            int _type = WHEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:189:6: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:189:8: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "WHEN"

    // $ANTLR start "WITH"
    public final void mWITH() throws RecognitionException {
        try {
            int _type = WITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:191:6: ( ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'H' | 'h' ) )
            // InternalSqlLexer.g:191:8: ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'H' | 'h' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WITH"

    // $ANTLR start "YEAR"
    public final void mYEAR() throws RecognitionException {
        try {
            int _type = YEAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:193:6: ( ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:193:8: ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' )
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
    // $ANTLR end "YEAR"

    // $ANTLR start "LeftParenthesisPlusSignRightParenthesis"
    public final void mLeftParenthesisPlusSignRightParenthesis() throws RecognitionException {
        try {
            int _type = LeftParenthesisPlusSignRightParenthesis;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:195:41: ( '(' '+' ')' )
            // InternalSqlLexer.g:195:43: '(' '+' ')'
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
    // $ANTLR end "LeftParenthesisPlusSignRightParenthesis"

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:197:5: ( ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:197:7: ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "ALL"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:199:5: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:199:7: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "AND"

    // $ANTLR start "ANY"
    public final void mANY() throws RecognitionException {
        try {
            int _type = ANY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:201:5: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:201:7: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' )
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
    // $ANTLR end "ANY"

    // $ANTLR start "ASC"
    public final void mASC() throws RecognitionException {
        try {
            int _type = ASC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:203:5: ( ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // InternalSqlLexer.g:203:7: ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
    // $ANTLR end "ASC"

    // $ANTLR start "DAY"
    public final void mDAY() throws RecognitionException {
        try {
            int _type = DAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:205:5: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:205:7: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' )
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
    // $ANTLR end "DAY"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:207:5: ( ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:207:7: ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
    // $ANTLR end "END"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:209:5: ( ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:209:7: ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'R' | 'r' )
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
    // $ANTLR end "FOR"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:211:5: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:211:7: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "ROW"
    public final void mROW() throws RecognitionException {
        try {
            int _type = ROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:213:5: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) )
            // InternalSqlLexer.g:213:7: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' )
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
    // $ANTLR end "ROW"

    // $ANTLR start "TOP"
    public final void mTOP() throws RecognitionException {
        try {
            int _type = TOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:215:5: ( ( 'T' | 't' ) ( 'O' | 'o' ) ( 'P' | 'p' ) )
            // InternalSqlLexer.g:215:7: ( 'T' | 't' ) ( 'O' | 'o' ) ( 'P' | 'p' )
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
    // $ANTLR end "TOP"

    // $ANTLR start "XML"
    public final void mXML() throws RecognitionException {
        try {
            int _type = XML;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:217:5: ( ( 'X' | 'x' ) ( 'M' | 'm' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:217:7: ( 'X' | 'x' ) ( 'M' | 'm' ) ( 'L' | 'l' )
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
    // $ANTLR end "XML"

    // $ANTLR start "IN_1"
    public final void mIN_1() throws RecognitionException {
        try {
            int _type = IN_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:219:6: ( '{' ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:219:8: '{' ( 'I' | 'i' ) ( 'N' | 'n' )
            {
            match('{'); 
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
    // $ANTLR end "IN_1"

    // $ANTLR start "ExclamationMarkEqualsSign"
    public final void mExclamationMarkEqualsSign() throws RecognitionException {
        try {
            int _type = ExclamationMarkEqualsSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:221:27: ( '!' '=' )
            // InternalSqlLexer.g:221:29: '!' '='
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
    // $ANTLR end "ExclamationMarkEqualsSign"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            int _type = X;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:223:3: ( '$' ( 'X' | 'x' ) )
            // InternalSqlLexer.g:223:5: '$' ( 'X' | 'x' )
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
    // $ANTLR end "X"

    // $ANTLR start "LessThanSignEqualsSign"
    public final void mLessThanSignEqualsSign() throws RecognitionException {
        try {
            int _type = LessThanSignEqualsSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:225:24: ( '<' '=' )
            // InternalSqlLexer.g:225:26: '<' '='
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
    // $ANTLR end "LessThanSignEqualsSign"

    // $ANTLR start "LessThanSignGreaterThanSign"
    public final void mLessThanSignGreaterThanSign() throws RecognitionException {
        try {
            int _type = LessThanSignGreaterThanSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:227:29: ( '<' '>' )
            // InternalSqlLexer.g:227:31: '<' '>'
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
    // $ANTLR end "LessThanSignGreaterThanSign"

    // $ANTLR start "GreaterThanSignEqualsSign"
    public final void mGreaterThanSignEqualsSign() throws RecognitionException {
        try {
            int _type = GreaterThanSignEqualsSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:229:27: ( '>' '=' )
            // InternalSqlLexer.g:229:29: '>' '='
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
    // $ANTLR end "GreaterThanSignEqualsSign"

    // $ANTLR start "AS"
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:231:4: ( ( 'A' | 'a' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:231:6: ( 'A' | 'a' ) ( 'S' | 's' )
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
    // $ANTLR end "AS"

    // $ANTLR start "BY"
    public final void mBY() throws RecognitionException {
        try {
            int _type = BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:233:4: ( ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:233:6: ( 'B' | 'b' ) ( 'Y' | 'y' )
            {
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
    // $ANTLR end "BY"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:235:4: ( ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:235:6: ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "IN"

    // $ANTLR start "IS"
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:237:4: ( ( 'I' | 'i' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:237:6: ( 'I' | 'i' ) ( 'S' | 's' )
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IS"

    // $ANTLR start "ON"
    public final void mON() throws RecognitionException {
        try {
            int _type = ON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:239:4: ( ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:239:6: ( 'O' | 'o' ) ( 'N' | 'n' )
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
    // $ANTLR end "ON"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:241:4: ( ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:241:6: ( 'O' | 'o' ) ( 'R' | 'r' )
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
    // $ANTLR end "OR"

    // $ANTLR start "CircumflexAccentEqualsSign"
    public final void mCircumflexAccentEqualsSign() throws RecognitionException {
        try {
            int _type = CircumflexAccentEqualsSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:243:28: ( '^' '=' )
            // InternalSqlLexer.g:243:30: '^' '='
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
    // $ANTLR end "CircumflexAccentEqualsSign"

    // $ANTLR start "VerticalLineVerticalLine"
    public final void mVerticalLineVerticalLine() throws RecognitionException {
        try {
            int _type = VerticalLineVerticalLine;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:245:26: ( '|' '|' )
            // InternalSqlLexer.g:245:28: '|' '|'
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
    // $ANTLR end "VerticalLineVerticalLine"

    // $ANTLR start "LeftParenthesis"
    public final void mLeftParenthesis() throws RecognitionException {
        try {
            int _type = LeftParenthesis;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:247:17: ( '(' )
            // InternalSqlLexer.g:247:19: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LeftParenthesis"

    // $ANTLR start "RightParenthesis"
    public final void mRightParenthesis() throws RecognitionException {
        try {
            int _type = RightParenthesis;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:249:18: ( ')' )
            // InternalSqlLexer.g:249:20: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RightParenthesis"

    // $ANTLR start "PlusSign"
    public final void mPlusSign() throws RecognitionException {
        try {
            int _type = PlusSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:251:10: ( '+' )
            // InternalSqlLexer.g:251:12: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PlusSign"

    // $ANTLR start "Comma"
    public final void mComma() throws RecognitionException {
        try {
            int _type = Comma;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:253:7: ( ',' )
            // InternalSqlLexer.g:253:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Comma"

    // $ANTLR start "HyphenMinus"
    public final void mHyphenMinus() throws RecognitionException {
        try {
            int _type = HyphenMinus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:255:13: ( '-' )
            // InternalSqlLexer.g:255:15: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HyphenMinus"

    // $ANTLR start "FullStop"
    public final void mFullStop() throws RecognitionException {
        try {
            int _type = FullStop;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:257:10: ( '.' )
            // InternalSqlLexer.g:257:12: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FullStop"

    // $ANTLR start "Solidus"
    public final void mSolidus() throws RecognitionException {
        try {
            int _type = Solidus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:259:9: ( '/' )
            // InternalSqlLexer.g:259:11: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Solidus"

    // $ANTLR start "LessThanSign"
    public final void mLessThanSign() throws RecognitionException {
        try {
            int _type = LessThanSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:261:14: ( '<' )
            // InternalSqlLexer.g:261:16: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LessThanSign"

    // $ANTLR start "EqualsSign"
    public final void mEqualsSign() throws RecognitionException {
        try {
            int _type = EqualsSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:263:12: ( '=' )
            // InternalSqlLexer.g:263:14: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EqualsSign"

    // $ANTLR start "GreaterThanSign"
    public final void mGreaterThanSign() throws RecognitionException {
        try {
            int _type = GreaterThanSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:265:17: ( '>' )
            // InternalSqlLexer.g:265:19: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GreaterThanSign"

    // $ANTLR start "VerticalLine"
    public final void mVerticalLine() throws RecognitionException {
        try {
            int _type = VerticalLine;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:267:14: ( '|' )
            // InternalSqlLexer.g:267:16: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VerticalLine"

    // $ANTLR start "RightCurlyBracket"
    public final void mRightCurlyBracket() throws RecognitionException {
        try {
            int _type = RightCurlyBracket;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:269:19: ( '}' )
            // InternalSqlLexer.g:269:21: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RightCurlyBracket"

    // $ANTLR start "RULE_JRPARAM"
    public final void mRULE_JRPARAM() throws RecognitionException {
        try {
            int _type = RULE_JRPARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:273:14: ( '$P{' ( options {greedy=false; } : . )* '}' )
            // InternalSqlLexer.g:273:16: '$P{' ( options {greedy=false; } : . )* '}'
            {
            match("$P{"); 

            // InternalSqlLexer.g:273:22: ( options {greedy=false; } : . )*
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
            	    // InternalSqlLexer.g:273:50: .
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
            // InternalSqlLexer.g:275:15: ( '$P!{' ( options {greedy=false; } : . )* '}' )
            // InternalSqlLexer.g:275:17: '$P!{' ( options {greedy=false; } : . )* '}'
            {
            match("$P!{"); 

            // InternalSqlLexer.g:275:24: ( options {greedy=false; } : . )*
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
            	    // InternalSqlLexer.g:275:52: .
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
            // InternalSqlLexer.g:277:11: ( '*' )
            // InternalSqlLexer.g:277:13: '*'
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
            // InternalSqlLexer.g:279:15: ( ( '0' .. '9' )+ )
            // InternalSqlLexer.g:279:17: ( '0' .. '9' )+
            {
            // InternalSqlLexer.g:279:17: ( '0' .. '9' )+
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
            	    // InternalSqlLexer.g:279:18: '0' .. '9'
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
            // InternalSqlLexer.g:281:10: ( ( '-' )? RULE_UNSIGNED )
            // InternalSqlLexer.g:281:12: ( '-' )? RULE_UNSIGNED
            {
            // InternalSqlLexer.g:281:12: ( '-' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalSqlLexer.g:281:12: '-'
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
            // InternalSqlLexer.g:283:20: ( ( '-' )? RULE_UNSIGNED ( '.' RULE_UNSIGNED )? )
            // InternalSqlLexer.g:283:22: ( '-' )? RULE_UNSIGNED ( '.' RULE_UNSIGNED )?
            {
            // InternalSqlLexer.g:283:22: ( '-' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='-') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalSqlLexer.g:283:22: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            mRULE_UNSIGNED(); 
            // InternalSqlLexer.g:283:41: ( '.' RULE_UNSIGNED )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalSqlLexer.g:283:42: '.' RULE_UNSIGNED
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
            // InternalSqlLexer.g:285:16: ( RULE_DATE ' ' RULE_TIME )
            // InternalSqlLexer.g:285:18: RULE_DATE ' ' RULE_TIME
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
            // InternalSqlLexer.g:287:11: ( '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\'' )
            // InternalSqlLexer.g:287:13: '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\''
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
            // InternalSqlLexer.g:289:11: ( '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\'' )
            // InternalSqlLexer.g:289:13: '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\''
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
            // InternalSqlLexer.g:291:14: ( '\\'' ( '\\'\\'' | RULE_STRING_CORE | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            // InternalSqlLexer.g:291:16: '\\'' ( '\\'\\'' | RULE_STRING_CORE | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
            {
            match('\''); 
            // InternalSqlLexer.g:291:21: ( '\\'\\'' | RULE_STRING_CORE | ~ ( ( '\\\\' | '\\'' ) ) )*
            loop7:
            do {
                int alt7=4;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\'') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='\'') ) {
                        alt7=1;
                    }


                }
                else if ( (LA7_0=='\\') ) {
                    alt7=2;
                }
                else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                    alt7=3;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalSqlLexer.g:291:22: '\\'\\''
            	    {
            	    match("''"); 


            	    }
            	    break;
            	case 2 :
            	    // InternalSqlLexer.g:291:29: RULE_STRING_CORE
            	    {
            	    mRULE_STRING_CORE(); 

            	    }
            	    break;
            	case 3 :
            	    // InternalSqlLexer.g:291:46: ~ ( ( '\\\\' | '\\'' ) )
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
            // InternalSqlLexer.g:293:13: ( '\"' ( RULE_STRING_CORE | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // InternalSqlLexer.g:293:15: '\"' ( RULE_STRING_CORE | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // InternalSqlLexer.g:293:19: ( RULE_STRING_CORE | ~ ( ( '\\\\' | '\"' ) ) )*
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
            	    // InternalSqlLexer.g:293:20: RULE_STRING_CORE
            	    {
            	    mRULE_STRING_CORE(); 

            	    }
            	    break;
            	case 2 :
            	    // InternalSqlLexer.g:293:37: ~ ( ( '\\\\' | '\"' ) )
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
            // InternalSqlLexer.g:295:13: ( ( '`' ( RULE_STRING_CORE | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( RULE_STRING_CORE | ~ ( ( '\\\\' | ']' ) ) )* ']' ) )
            // InternalSqlLexer.g:295:15: ( '`' ( RULE_STRING_CORE | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( RULE_STRING_CORE | ~ ( ( '\\\\' | ']' ) ) )* ']' )
            {
            // InternalSqlLexer.g:295:15: ( '`' ( RULE_STRING_CORE | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( RULE_STRING_CORE | ~ ( ( '\\\\' | ']' ) ) )* ']' )
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
                    // InternalSqlLexer.g:295:16: '`' ( RULE_STRING_CORE | ~ ( ( '\\\\' | '`' ) ) )* '`'
                    {
                    match('`'); 
                    // InternalSqlLexer.g:295:20: ( RULE_STRING_CORE | ~ ( ( '\\\\' | '`' ) ) )*
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
                    	    // InternalSqlLexer.g:295:21: RULE_STRING_CORE
                    	    {
                    	    mRULE_STRING_CORE(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalSqlLexer.g:295:38: ~ ( ( '\\\\' | '`' ) )
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
                    // InternalSqlLexer.g:295:58: '[' ( RULE_STRING_CORE | ~ ( ( '\\\\' | ']' ) ) )* ']'
                    {
                    match('['); 
                    // InternalSqlLexer.g:295:62: ( RULE_STRING_CORE | ~ ( ( '\\\\' | ']' ) ) )*
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
                    	    // InternalSqlLexer.g:295:63: RULE_STRING_CORE
                    	    {
                    	    mRULE_STRING_CORE(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalSqlLexer.g:295:80: ~ ( ( '\\\\' | ']' ) )
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

    // $ANTLR start "RULE_STRING_CORE"
    public final void mRULE_STRING_CORE() throws RecognitionException {
        try {
            // InternalSqlLexer.g:297:27: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) )
            // InternalSqlLexer.g:297:29: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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

        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING_CORE"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:299:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' ) ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )* )
            // InternalSqlLexer.g:299:11: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' ) ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )*
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u024F')||(input.LA(1)>='\u0410' && input.LA(1)<='\u044F')||(input.LA(1)>='\u3041' && input.LA(1)<='\u30FF')||(input.LA(1)>='\u31F0' && input.LA(1)<='\u31FF')||(input.LA(1)>='\u3400' && input.LA(1)<='\u4DBF')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF')||input.LA(1)=='\uFF3F' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalSqlLexer.g:299:276: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='$'||LA12_0=='-'||(LA12_0>='0' && LA12_0<='9')||(LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')||(LA12_0>='\u00C0' && LA12_0<='\u024F')||(LA12_0>='\u0410' && LA12_0<='\u044F')||(LA12_0>='\u3041' && LA12_0<='\u30FF')||(LA12_0>='\u31F0' && LA12_0<='\u31FF')||(LA12_0>='\u3400' && LA12_0<='\u4DBF')||(LA12_0>='\u4E00' && LA12_0<='\u9FFF')||(LA12_0>='\uF900' && LA12_0<='\uFAFF')||LA12_0=='\uFF3F') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalSqlLexer.g:
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
            // InternalSqlLexer.g:301:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalSqlLexer.g:301:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalSqlLexer.g:301:24: ( options {greedy=false; } : . )*
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
            	    // InternalSqlLexer.g:301:52: .
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
            // InternalSqlLexer.g:303:17: ( ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalSqlLexer.g:303:19: ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            // InternalSqlLexer.g:303:19: ( '--' | '#' | '//' )
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
                    // InternalSqlLexer.g:303:20: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // InternalSqlLexer.g:303:25: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 3 :
                    // InternalSqlLexer.g:303:29: '//'
                    {
                    match("//"); 


                    }
                    break;

            }

            // InternalSqlLexer.g:303:35: (~ ( ( '\\n' | '\\r' ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalSqlLexer.g:303:35: ~ ( ( '\\n' | '\\r' ) )
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

            // InternalSqlLexer.g:303:51: ( ( '\\r' )? '\\n' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='\n'||LA17_0=='\r') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalSqlLexer.g:303:52: ( '\\r' )? '\\n'
                    {
                    // InternalSqlLexer.g:303:52: ( '\\r' )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='\r') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // InternalSqlLexer.g:303:52: '\\r'
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
            // InternalSqlLexer.g:305:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalSqlLexer.g:305:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalSqlLexer.g:305:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    // InternalSqlLexer.g:
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
            // InternalSqlLexer.g:307:16: ( . )
            // InternalSqlLexer.g:307:18: .
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
        // InternalSqlLexer.g:1:8: ( MINUTE_MICROSECOND | SECOND_MICROSECOND | HOUR_MICROSECOND | DAY_MICROSECOND | MINUTE_SECOND | STRAIGHT_JOIN | HOUR_MINUTE | HOUR_SECOND | MICROSECOND | DAY_MINUTE | DAY_SECOND | YEAR_MONTH | BETWEEN_4 | KW_FOLLOWING | INTERSECT | PARTITION | PRECEDING | UNBOUNDED | BETWEEN_2 | NOTEQUAL | BETWEEN_3 | GREATER_1 | DAY_HOUR | DISTINCT | SIBLINGS | BETWEEN_1 | GREATER | BETWEEN | CURRENT | EXCLUDE | EXTRACT | INCLUDE | NATURAL | PERCENT | QUARTER | UNPIVOT | EXCEPT | EXISTS | HAVING | MINUTE | OFFSET | SECOND | SELECT | VALUES | EQUAL | LESS_1 | NOTIN | CAST | CROSS | FETCH | FIRST | GROUP | INNER | LIMIT | MINUS | MONTH | NULLS | ORDER | OUTER | PIVOT | RANGE | RIGHT | UNION | USING | WHERE | LESS | CASE | DESC | ELSE | FROM | FULL | HOUR | JOIN | LAST | LEFT | LIKE | NOT_1 | NULL | ONLY | OVER | ROWS | SOME | THEN | TIES | WEEK | WHEN | WITH | YEAR | LeftParenthesisPlusSignRightParenthesis | ALL | AND | ANY | ASC | DAY | END | FOR | NOT | ROW | TOP | XML | IN_1 | ExclamationMarkEqualsSign | X | LessThanSignEqualsSign | LessThanSignGreaterThanSign | GreaterThanSignEqualsSign | AS | BY | IN | IS | ON | OR | CircumflexAccentEqualsSign | VerticalLineVerticalLine | LeftParenthesis | RightParenthesis | PlusSign | Comma | HyphenMinus | FullStop | Solidus | LessThanSign | EqualsSign | GreaterThanSign | VerticalLine | RightCurlyBracket | RULE_JRPARAM | RULE_JRNPARAM | RULE_STAR | RULE_UNSIGNED | RULE_INT | RULE_SIGNED_DOUBLE | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_STRING_ | RULE_STRING | RULE_DBNAME | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt19=143;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // InternalSqlLexer.g:1:10: MINUTE_MICROSECOND
                {
                mMINUTE_MICROSECOND(); 

                }
                break;
            case 2 :
                // InternalSqlLexer.g:1:29: SECOND_MICROSECOND
                {
                mSECOND_MICROSECOND(); 

                }
                break;
            case 3 :
                // InternalSqlLexer.g:1:48: HOUR_MICROSECOND
                {
                mHOUR_MICROSECOND(); 

                }
                break;
            case 4 :
                // InternalSqlLexer.g:1:65: DAY_MICROSECOND
                {
                mDAY_MICROSECOND(); 

                }
                break;
            case 5 :
                // InternalSqlLexer.g:1:81: MINUTE_SECOND
                {
                mMINUTE_SECOND(); 

                }
                break;
            case 6 :
                // InternalSqlLexer.g:1:95: STRAIGHT_JOIN
                {
                mSTRAIGHT_JOIN(); 

                }
                break;
            case 7 :
                // InternalSqlLexer.g:1:109: HOUR_MINUTE
                {
                mHOUR_MINUTE(); 

                }
                break;
            case 8 :
                // InternalSqlLexer.g:1:121: HOUR_SECOND
                {
                mHOUR_SECOND(); 

                }
                break;
            case 9 :
                // InternalSqlLexer.g:1:133: MICROSECOND
                {
                mMICROSECOND(); 

                }
                break;
            case 10 :
                // InternalSqlLexer.g:1:145: DAY_MINUTE
                {
                mDAY_MINUTE(); 

                }
                break;
            case 11 :
                // InternalSqlLexer.g:1:156: DAY_SECOND
                {
                mDAY_SECOND(); 

                }
                break;
            case 12 :
                // InternalSqlLexer.g:1:167: YEAR_MONTH
                {
                mYEAR_MONTH(); 

                }
                break;
            case 13 :
                // InternalSqlLexer.g:1:178: BETWEEN_4
                {
                mBETWEEN_4(); 

                }
                break;
            case 14 :
                // InternalSqlLexer.g:1:188: KW_FOLLOWING
                {
                mKW_FOLLOWING(); 

                }
                break;
            case 15 :
                // InternalSqlLexer.g:1:201: INTERSECT
                {
                mINTERSECT(); 

                }
                break;
            case 16 :
                // InternalSqlLexer.g:1:211: PARTITION
                {
                mPARTITION(); 

                }
                break;
            case 17 :
                // InternalSqlLexer.g:1:221: PRECEDING
                {
                mPRECEDING(); 

                }
                break;
            case 18 :
                // InternalSqlLexer.g:1:231: UNBOUNDED
                {
                mUNBOUNDED(); 

                }
                break;
            case 19 :
                // InternalSqlLexer.g:1:241: BETWEEN_2
                {
                mBETWEEN_2(); 

                }
                break;
            case 20 :
                // InternalSqlLexer.g:1:251: NOTEQUAL
                {
                mNOTEQUAL(); 

                }
                break;
            case 21 :
                // InternalSqlLexer.g:1:260: BETWEEN_3
                {
                mBETWEEN_3(); 

                }
                break;
            case 22 :
                // InternalSqlLexer.g:1:270: GREATER_1
                {
                mGREATER_1(); 

                }
                break;
            case 23 :
                // InternalSqlLexer.g:1:280: DAY_HOUR
                {
                mDAY_HOUR(); 

                }
                break;
            case 24 :
                // InternalSqlLexer.g:1:289: DISTINCT
                {
                mDISTINCT(); 

                }
                break;
            case 25 :
                // InternalSqlLexer.g:1:298: SIBLINGS
                {
                mSIBLINGS(); 

                }
                break;
            case 26 :
                // InternalSqlLexer.g:1:307: BETWEEN_1
                {
                mBETWEEN_1(); 

                }
                break;
            case 27 :
                // InternalSqlLexer.g:1:317: GREATER
                {
                mGREATER(); 

                }
                break;
            case 28 :
                // InternalSqlLexer.g:1:325: BETWEEN
                {
                mBETWEEN(); 

                }
                break;
            case 29 :
                // InternalSqlLexer.g:1:333: CURRENT
                {
                mCURRENT(); 

                }
                break;
            case 30 :
                // InternalSqlLexer.g:1:341: EXCLUDE
                {
                mEXCLUDE(); 

                }
                break;
            case 31 :
                // InternalSqlLexer.g:1:349: EXTRACT
                {
                mEXTRACT(); 

                }
                break;
            case 32 :
                // InternalSqlLexer.g:1:357: INCLUDE
                {
                mINCLUDE(); 

                }
                break;
            case 33 :
                // InternalSqlLexer.g:1:365: NATURAL
                {
                mNATURAL(); 

                }
                break;
            case 34 :
                // InternalSqlLexer.g:1:373: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 35 :
                // InternalSqlLexer.g:1:381: QUARTER
                {
                mQUARTER(); 

                }
                break;
            case 36 :
                // InternalSqlLexer.g:1:389: UNPIVOT
                {
                mUNPIVOT(); 

                }
                break;
            case 37 :
                // InternalSqlLexer.g:1:397: EXCEPT
                {
                mEXCEPT(); 

                }
                break;
            case 38 :
                // InternalSqlLexer.g:1:404: EXISTS
                {
                mEXISTS(); 

                }
                break;
            case 39 :
                // InternalSqlLexer.g:1:411: HAVING
                {
                mHAVING(); 

                }
                break;
            case 40 :
                // InternalSqlLexer.g:1:418: MINUTE
                {
                mMINUTE(); 

                }
                break;
            case 41 :
                // InternalSqlLexer.g:1:425: OFFSET
                {
                mOFFSET(); 

                }
                break;
            case 42 :
                // InternalSqlLexer.g:1:432: SECOND
                {
                mSECOND(); 

                }
                break;
            case 43 :
                // InternalSqlLexer.g:1:439: SELECT
                {
                mSELECT(); 

                }
                break;
            case 44 :
                // InternalSqlLexer.g:1:446: VALUES
                {
                mVALUES(); 

                }
                break;
            case 45 :
                // InternalSqlLexer.g:1:453: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 46 :
                // InternalSqlLexer.g:1:459: LESS_1
                {
                mLESS_1(); 

                }
                break;
            case 47 :
                // InternalSqlLexer.g:1:466: NOTIN
                {
                mNOTIN(); 

                }
                break;
            case 48 :
                // InternalSqlLexer.g:1:472: CAST
                {
                mCAST(); 

                }
                break;
            case 49 :
                // InternalSqlLexer.g:1:477: CROSS
                {
                mCROSS(); 

                }
                break;
            case 50 :
                // InternalSqlLexer.g:1:483: FETCH
                {
                mFETCH(); 

                }
                break;
            case 51 :
                // InternalSqlLexer.g:1:489: FIRST
                {
                mFIRST(); 

                }
                break;
            case 52 :
                // InternalSqlLexer.g:1:495: GROUP
                {
                mGROUP(); 

                }
                break;
            case 53 :
                // InternalSqlLexer.g:1:501: INNER
                {
                mINNER(); 

                }
                break;
            case 54 :
                // InternalSqlLexer.g:1:507: LIMIT
                {
                mLIMIT(); 

                }
                break;
            case 55 :
                // InternalSqlLexer.g:1:513: MINUS
                {
                mMINUS(); 

                }
                break;
            case 56 :
                // InternalSqlLexer.g:1:519: MONTH
                {
                mMONTH(); 

                }
                break;
            case 57 :
                // InternalSqlLexer.g:1:525: NULLS
                {
                mNULLS(); 

                }
                break;
            case 58 :
                // InternalSqlLexer.g:1:531: ORDER
                {
                mORDER(); 

                }
                break;
            case 59 :
                // InternalSqlLexer.g:1:537: OUTER
                {
                mOUTER(); 

                }
                break;
            case 60 :
                // InternalSqlLexer.g:1:543: PIVOT
                {
                mPIVOT(); 

                }
                break;
            case 61 :
                // InternalSqlLexer.g:1:549: RANGE
                {
                mRANGE(); 

                }
                break;
            case 62 :
                // InternalSqlLexer.g:1:555: RIGHT
                {
                mRIGHT(); 

                }
                break;
            case 63 :
                // InternalSqlLexer.g:1:561: UNION
                {
                mUNION(); 

                }
                break;
            case 64 :
                // InternalSqlLexer.g:1:567: USING
                {
                mUSING(); 

                }
                break;
            case 65 :
                // InternalSqlLexer.g:1:573: WHERE
                {
                mWHERE(); 

                }
                break;
            case 66 :
                // InternalSqlLexer.g:1:579: LESS
                {
                mLESS(); 

                }
                break;
            case 67 :
                // InternalSqlLexer.g:1:584: CASE
                {
                mCASE(); 

                }
                break;
            case 68 :
                // InternalSqlLexer.g:1:589: DESC
                {
                mDESC(); 

                }
                break;
            case 69 :
                // InternalSqlLexer.g:1:594: ELSE
                {
                mELSE(); 

                }
                break;
            case 70 :
                // InternalSqlLexer.g:1:599: FROM
                {
                mFROM(); 

                }
                break;
            case 71 :
                // InternalSqlLexer.g:1:604: FULL
                {
                mFULL(); 

                }
                break;
            case 72 :
                // InternalSqlLexer.g:1:609: HOUR
                {
                mHOUR(); 

                }
                break;
            case 73 :
                // InternalSqlLexer.g:1:614: JOIN
                {
                mJOIN(); 

                }
                break;
            case 74 :
                // InternalSqlLexer.g:1:619: LAST
                {
                mLAST(); 

                }
                break;
            case 75 :
                // InternalSqlLexer.g:1:624: LEFT
                {
                mLEFT(); 

                }
                break;
            case 76 :
                // InternalSqlLexer.g:1:629: LIKE
                {
                mLIKE(); 

                }
                break;
            case 77 :
                // InternalSqlLexer.g:1:634: NOT_1
                {
                mNOT_1(); 

                }
                break;
            case 78 :
                // InternalSqlLexer.g:1:640: NULL
                {
                mNULL(); 

                }
                break;
            case 79 :
                // InternalSqlLexer.g:1:645: ONLY
                {
                mONLY(); 

                }
                break;
            case 80 :
                // InternalSqlLexer.g:1:650: OVER
                {
                mOVER(); 

                }
                break;
            case 81 :
                // InternalSqlLexer.g:1:655: ROWS
                {
                mROWS(); 

                }
                break;
            case 82 :
                // InternalSqlLexer.g:1:660: SOME
                {
                mSOME(); 

                }
                break;
            case 83 :
                // InternalSqlLexer.g:1:665: THEN
                {
                mTHEN(); 

                }
                break;
            case 84 :
                // InternalSqlLexer.g:1:670: TIES
                {
                mTIES(); 

                }
                break;
            case 85 :
                // InternalSqlLexer.g:1:675: WEEK
                {
                mWEEK(); 

                }
                break;
            case 86 :
                // InternalSqlLexer.g:1:680: WHEN
                {
                mWHEN(); 

                }
                break;
            case 87 :
                // InternalSqlLexer.g:1:685: WITH
                {
                mWITH(); 

                }
                break;
            case 88 :
                // InternalSqlLexer.g:1:690: YEAR
                {
                mYEAR(); 

                }
                break;
            case 89 :
                // InternalSqlLexer.g:1:695: LeftParenthesisPlusSignRightParenthesis
                {
                mLeftParenthesisPlusSignRightParenthesis(); 

                }
                break;
            case 90 :
                // InternalSqlLexer.g:1:735: ALL
                {
                mALL(); 

                }
                break;
            case 91 :
                // InternalSqlLexer.g:1:739: AND
                {
                mAND(); 

                }
                break;
            case 92 :
                // InternalSqlLexer.g:1:743: ANY
                {
                mANY(); 

                }
                break;
            case 93 :
                // InternalSqlLexer.g:1:747: ASC
                {
                mASC(); 

                }
                break;
            case 94 :
                // InternalSqlLexer.g:1:751: DAY
                {
                mDAY(); 

                }
                break;
            case 95 :
                // InternalSqlLexer.g:1:755: END
                {
                mEND(); 

                }
                break;
            case 96 :
                // InternalSqlLexer.g:1:759: FOR
                {
                mFOR(); 

                }
                break;
            case 97 :
                // InternalSqlLexer.g:1:763: NOT
                {
                mNOT(); 

                }
                break;
            case 98 :
                // InternalSqlLexer.g:1:767: ROW
                {
                mROW(); 

                }
                break;
            case 99 :
                // InternalSqlLexer.g:1:771: TOP
                {
                mTOP(); 

                }
                break;
            case 100 :
                // InternalSqlLexer.g:1:775: XML
                {
                mXML(); 

                }
                break;
            case 101 :
                // InternalSqlLexer.g:1:779: IN_1
                {
                mIN_1(); 

                }
                break;
            case 102 :
                // InternalSqlLexer.g:1:784: ExclamationMarkEqualsSign
                {
                mExclamationMarkEqualsSign(); 

                }
                break;
            case 103 :
                // InternalSqlLexer.g:1:810: X
                {
                mX(); 

                }
                break;
            case 104 :
                // InternalSqlLexer.g:1:812: LessThanSignEqualsSign
                {
                mLessThanSignEqualsSign(); 

                }
                break;
            case 105 :
                // InternalSqlLexer.g:1:835: LessThanSignGreaterThanSign
                {
                mLessThanSignGreaterThanSign(); 

                }
                break;
            case 106 :
                // InternalSqlLexer.g:1:863: GreaterThanSignEqualsSign
                {
                mGreaterThanSignEqualsSign(); 

                }
                break;
            case 107 :
                // InternalSqlLexer.g:1:889: AS
                {
                mAS(); 

                }
                break;
            case 108 :
                // InternalSqlLexer.g:1:892: BY
                {
                mBY(); 

                }
                break;
            case 109 :
                // InternalSqlLexer.g:1:895: IN
                {
                mIN(); 

                }
                break;
            case 110 :
                // InternalSqlLexer.g:1:898: IS
                {
                mIS(); 

                }
                break;
            case 111 :
                // InternalSqlLexer.g:1:901: ON
                {
                mON(); 

                }
                break;
            case 112 :
                // InternalSqlLexer.g:1:904: OR
                {
                mOR(); 

                }
                break;
            case 113 :
                // InternalSqlLexer.g:1:907: CircumflexAccentEqualsSign
                {
                mCircumflexAccentEqualsSign(); 

                }
                break;
            case 114 :
                // InternalSqlLexer.g:1:934: VerticalLineVerticalLine
                {
                mVerticalLineVerticalLine(); 

                }
                break;
            case 115 :
                // InternalSqlLexer.g:1:959: LeftParenthesis
                {
                mLeftParenthesis(); 

                }
                break;
            case 116 :
                // InternalSqlLexer.g:1:975: RightParenthesis
                {
                mRightParenthesis(); 

                }
                break;
            case 117 :
                // InternalSqlLexer.g:1:992: PlusSign
                {
                mPlusSign(); 

                }
                break;
            case 118 :
                // InternalSqlLexer.g:1:1001: Comma
                {
                mComma(); 

                }
                break;
            case 119 :
                // InternalSqlLexer.g:1:1007: HyphenMinus
                {
                mHyphenMinus(); 

                }
                break;
            case 120 :
                // InternalSqlLexer.g:1:1019: FullStop
                {
                mFullStop(); 

                }
                break;
            case 121 :
                // InternalSqlLexer.g:1:1028: Solidus
                {
                mSolidus(); 

                }
                break;
            case 122 :
                // InternalSqlLexer.g:1:1036: LessThanSign
                {
                mLessThanSign(); 

                }
                break;
            case 123 :
                // InternalSqlLexer.g:1:1049: EqualsSign
                {
                mEqualsSign(); 

                }
                break;
            case 124 :
                // InternalSqlLexer.g:1:1060: GreaterThanSign
                {
                mGreaterThanSign(); 

                }
                break;
            case 125 :
                // InternalSqlLexer.g:1:1076: VerticalLine
                {
                mVerticalLine(); 

                }
                break;
            case 126 :
                // InternalSqlLexer.g:1:1089: RightCurlyBracket
                {
                mRightCurlyBracket(); 

                }
                break;
            case 127 :
                // InternalSqlLexer.g:1:1107: RULE_JRPARAM
                {
                mRULE_JRPARAM(); 

                }
                break;
            case 128 :
                // InternalSqlLexer.g:1:1120: RULE_JRNPARAM
                {
                mRULE_JRNPARAM(); 

                }
                break;
            case 129 :
                // InternalSqlLexer.g:1:1134: RULE_STAR
                {
                mRULE_STAR(); 

                }
                break;
            case 130 :
                // InternalSqlLexer.g:1:1144: RULE_UNSIGNED
                {
                mRULE_UNSIGNED(); 

                }
                break;
            case 131 :
                // InternalSqlLexer.g:1:1158: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 132 :
                // InternalSqlLexer.g:1:1167: RULE_SIGNED_DOUBLE
                {
                mRULE_SIGNED_DOUBLE(); 

                }
                break;
            case 133 :
                // InternalSqlLexer.g:1:1186: RULE_TIMESTAMP
                {
                mRULE_TIMESTAMP(); 

                }
                break;
            case 134 :
                // InternalSqlLexer.g:1:1201: RULE_DATE
                {
                mRULE_DATE(); 

                }
                break;
            case 135 :
                // InternalSqlLexer.g:1:1211: RULE_TIME
                {
                mRULE_TIME(); 

                }
                break;
            case 136 :
                // InternalSqlLexer.g:1:1221: RULE_STRING_
                {
                mRULE_STRING_(); 

                }
                break;
            case 137 :
                // InternalSqlLexer.g:1:1234: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 138 :
                // InternalSqlLexer.g:1:1246: RULE_DBNAME
                {
                mRULE_DBNAME(); 

                }
                break;
            case 139 :
                // InternalSqlLexer.g:1:1258: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 140 :
                // InternalSqlLexer.g:1:1266: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 141 :
                // InternalSqlLexer.g:1:1282: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 142 :
                // InternalSqlLexer.g:1:1298: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 143 :
                // InternalSqlLexer.g:1:1306: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA19_eotS =
        "\1\uffff\5\65\1\62\21\65\1\165\2\65\1\62\1\65\1\177\1\u0081\1\62\1\u0084\3\uffff\1\u008a\1\uffff\1\u008d\3\uffff\1\u0091\4\62\4\uffff\2\65\1\uffff\12\65\7\uffff\5\65\1\u00b5\1\u00b6\7\65\1\u00c0\13\65\1\u00cf\1\65\1\u00d2\20\65\2\uffff\2\65\1\u00e8\1\65\1\uffff\1\u00ea\1\65\14\uffff\1\u00ed\11\uffff\1\u0091\5\uffff\12\65\1\u00fa\3\65\5\uffff\1\65\1\u0103\7\65\2\uffff\11\65\1\uffff\7\65\1\u011d\2\65\1\u0121\3\65\1\uffff\2\65\1\uffff\11\65\1\u0131\6\65\1\u0139\1\u013a\1\u013b\1\u013c\1\u013d\1\uffff\1\u013e\5\uffff\7\65\1\u0149\1\u014b\2\65\1\uffff\1\65\1\u0151\1\u0153\4\uffff\1\65\1\uffff\2\65\1\u015c\1\u015d\16\65\1\u016c\5\65\1\u0172\1\uffff\1\65\1\u0175\2\uffff\4\65\1\u017a\1\u017b\3\65\1\u017f\1\u0180\1\u0181\2\65\1\u0184\1\uffff\1\65\1\u0186\1\u0187\1\u0188\1\u0189\1\u018a\1\u018b\10\uffff\1\65\1\u018f\1\65\1\u0191\4\65\1\uffff\1\65\1\uffff\5\65\1\uffff\1\65\5\uffff\1\u01a1\1\65\1\u01a3\1\u01a4\2\uffff\2\65\1\u01a7\3\65\1\u01ab\2\65\1\u01ae\1\u01af\2\65\2\uffff\1\u01b2\4\65\1\uffff\1\65\1\u01b8\1\uffff\2\65\1\u01bb\1\u01bc\2\uffff\1\65\1\u01be\1\u01bf\3\uffff\1\u01c0\1\u01c1\1\uffff\1\u01c2\10\uffff\1\u01c6\1\uffff\1\65\1\uffff\1\u01c9\1\u01ca\4\65\1\u01cf\5\65\4\uffff\1\65\2\uffff\2\65\1\uffff\3\65\1\uffff\2\65\2\uffff\2\65\1\uffff\1\65\1\u01e3\1\65\1\u01e5\1\65\1\uffff\1\65\1\u01e8\2\uffff\1\u01e9\7\uffff\1\65\1\uffff\2\65\2\uffff\4\65\1\uffff\6\65\2\uffff\2\65\1\u01ff\2\65\1\u0202\1\65\1\u0204\1\u0205\1\u0206\1\u0207\1\uffff\1\u0208\1\uffff\1\u0209\1\u020a\4\uffff\5\65\1\u0212\6\65\1\u0219\1\u021a\1\65\1\uffff\1\u021e\2\65\1\uffff\2\65\1\uffff\1\65\11\uffff\5\65\1\uffff\6\65\2\uffff\1\65\1\u0233\2\uffff\1\u0234\1\u0235\1\u0236\1\u0237\1\u0238\2\uffff\11\65\1\u0244\1\u0245\1\u0246\11\uffff\2\65\1\u024b\3\65\1\u024f\1\u0250\1\65\5\uffff\2\65\1\uffff\3\65\2\uffff\1\65\1\u025a\1\uffff\1\65\1\u025e\1\65\1\u0260\2\65\3\uffff\1\65\1\uffff\1\65\1\uffff\2\65\1\u0268\3\65\1\u026c\1\uffff\2\65\1\u026f\1\uffff\2\65\1\uffff\1\u0272\1\u0273\2\uffff";
    static final String DFA19_eofS =
        "\u0274\uffff";
    static final String DFA19_minS =
        "\1\0\1\111\1\105\2\101\1\105\1\102\1\105\1\116\1\101\1\116\1\105\1\101\1\114\1\101\1\125\1\106\1\101\1\122\2\101\1\105\1\117\1\110\1\53\1\114\1\115\1\75\1\120\3\75\1\174\3\uffff\1\55\1\uffff\1\52\3\uffff\1\56\4\0\4\uffff\1\103\1\116\1\uffff\1\103\1\122\1\102\1\115\1\125\1\126\1\131\2\123\1\101\1\102\1\105\1\117\2\uffff\1\105\1\uffff\1\114\1\124\1\122\1\117\1\114\2\44\1\122\1\105\1\122\1\126\1\102\1\111\1\124\1\44\1\122\1\123\1\117\1\103\1\123\1\104\1\124\1\114\1\124\1\101\1\106\1\44\1\124\1\44\1\105\1\114\1\117\1\113\1\123\1\106\1\116\1\107\1\127\2\105\1\124\1\111\2\105\1\120\2\uffff\1\114\1\104\1\44\1\114\1\uffff\1\44\1\41\14\uffff\1\56\11\uffff\1\56\1\0\4\uffff\1\125\1\122\1\124\1\117\1\105\1\101\1\114\1\105\1\122\1\111\1\44\1\124\1\103\1\122\1\105\1\uffff\2\124\1\123\1\114\1\44\1\103\1\123\1\115\1\114\1\105\1\114\1\105\2\uffff\1\124\2\103\2\117\1\111\1\117\1\116\1\127\1\uffff\1\122\1\105\1\123\1\105\1\122\1\123\1\105\1\44\1\125\1\114\1\12\1\122\1\123\1\105\1\uffff\1\105\1\131\1\uffff\1\122\2\125\1\111\1\105\2\124\1\107\1\110\1\44\1\116\1\113\1\110\2\116\1\123\5\44\1\uffff\1\44\4\uffff\1\0\1\123\1\117\1\110\1\116\1\103\2\111\2\44\1\116\1\110\1\uffff\1\111\2\44\1\124\1\127\1\105\1\123\1\117\1\uffff\1\110\1\124\2\44\1\122\1\125\1\122\1\111\2\105\1\124\1\125\1\126\1\116\1\107\2\105\1\50\1\44\1\123\1\125\1\120\1\101\1\124\1\44\1\uffff\1\122\1\44\2\uffff\1\124\1\105\2\122\2\44\1\105\1\120\1\124\3\44\1\105\1\124\1\44\1\uffff\1\105\6\44\6\uffff\2\0\1\105\1\44\1\123\1\44\1\104\1\124\1\107\1\116\1\uffff\1\115\1\uffff\1\107\1\111\1\105\1\117\1\116\1\uffff\1\115\1\uffff\1\127\1\105\2\uffff\1\135\1\127\2\44\2\uffff\1\123\1\104\1\44\1\124\1\104\1\116\1\44\1\116\1\117\2\44\1\105\1\116\2\uffff\1\44\1\104\1\124\1\103\1\123\1\uffff\1\101\1\44\1\uffff\1\105\1\124\2\44\2\uffff\1\123\2\44\3\uffff\2\44\1\uffff\1\44\6\uffff\2\0\1\44\1\uffff\1\105\1\uffff\2\44\1\110\1\107\1\111\1\105\1\44\2\103\1\125\1\103\1\117\2\105\2\uffff\1\111\2\uffff\2\105\1\uffff\2\111\1\124\1\uffff\1\104\1\124\2\uffff\1\116\1\124\1\uffff\1\105\1\44\1\124\1\44\1\114\1\uffff\1\122\1\44\2\uffff\1\44\5\uffff\2\0\1\115\1\uffff\1\103\1\115\2\uffff\1\124\1\123\2\103\1\uffff\1\122\1\125\1\117\1\122\1\124\1\116\1\105\2\116\1\103\1\44\1\117\1\116\1\44\1\105\4\44\1\uffff\1\44\1\uffff\2\44\2\uffff\2\0\1\111\1\105\1\117\1\111\1\137\1\44\1\122\1\125\2\117\1\124\1\116\2\44\1\124\1\116\1\135\1\107\1\124\1\uffff\1\116\1\107\1\uffff\1\104\7\uffff\2\0\2\103\1\116\1\103\1\112\1\uffff\1\117\1\124\1\116\1\123\1\105\1\104\2\uffff\1\110\1\135\2\uffff\5\44\2\0\1\122\1\117\1\104\1\122\1\117\1\123\1\105\1\104\1\105\3\44\7\uffff\2\0\1\117\1\116\1\44\1\117\1\111\1\105\2\44\1\103\3\uffff\2\0\1\123\1\104\1\uffff\1\123\1\116\1\103\2\uffff\1\117\1\40\1\0\1\105\1\44\1\105\1\44\1\117\1\116\2\uffff\1\0\1\103\1\uffff\1\103\1\uffff\1\116\1\104\1\47\2\117\1\104\1\44\1\uffff\2\116\1\44\1\uffff\2\104\1\uffff\2\44\2\uffff";
    static final String DFA19_maxS =
        "\1\uffff\1\157\1\164\1\157\1\151\1\145\1\156\1\165\1\163\1\162\1\163\1\171\1\165\1\170\2\165\1\166\1\141\1\162\1\151\1\157\1\151\2\157\1\53\1\163\1\155\1\75\1\170\1\76\2\75\1\174\3\uffff\1\71\1\uffff\1\57\3\uffff\1\71\4\uffff\4\uffff\2\156\1\uffff\1\154\1\162\1\142\1\155\1\165\1\166\1\171\2\163\1\141\1\147\1\145\1\157\2\uffff\1\145\1\uffff\1\162\1\164\1\162\1\157\1\154\2\uff3f\1\162\1\145\1\162\1\166\1\160\1\151\1\164\1\uff3f\1\162\1\163\1\157\1\164\1\163\1\144\1\164\1\154\1\164\1\141\1\146\1\uff3f\1\164\1\uff3f\1\145\1\154\1\157\1\155\1\163\1\146\1\156\1\147\1\167\2\145\1\164\1\151\2\145\1\160\2\uffff\1\154\1\171\1\uff3f\1\154\1\uffff\1\uff3f\1\173\14\uffff\1\71\11\uffff\1\71\1\uffff\4\uffff\1\165\1\162\1\164\1\157\1\145\1\141\1\154\1\145\1\162\1\151\1\uff3f\1\164\1\143\1\162\1\145\1\uffff\2\164\1\163\1\154\1\uff3f\1\143\1\163\1\155\1\154\1\145\1\154\1\145\2\uffff\1\164\2\143\2\157\1\151\1\157\1\156\1\167\1\uffff\1\162\1\164\1\163\1\154\1\162\1\163\1\145\1\uff3f\1\165\1\154\1\uff3f\1\162\1\163\1\145\1\uffff\1\145\1\171\1\uffff\1\162\2\165\1\151\1\145\2\164\1\147\1\150\1\uff3f\1\162\1\153\1\150\2\156\1\163\5\uff3f\1\uffff\1\uff3f\4\uffff\1\uffff\1\164\1\157\1\150\1\156\1\143\2\151\2\uff3f\1\156\1\163\1\uffff\1\151\2\uff3f\1\164\1\167\1\151\1\163\1\157\1\uffff\1\150\1\164\2\uff3f\1\162\1\165\1\162\1\151\2\145\1\164\1\165\1\166\1\156\1\147\2\145\1\50\1\uff3f\1\163\1\165\1\160\1\141\1\164\1\uff3f\1\uffff\1\162\1\uff3f\2\uffff\1\164\1\145\2\162\2\uff3f\1\145\1\160\1\164\3\uff3f\1\145\1\164\1\uff3f\1\uffff\1\145\6\uff3f\6\uffff\2\uffff\1\145\1\uff3f\1\163\1\uff3f\1\144\1\164\1\147\1\156\1\uffff\1\163\1\uffff\1\147\1\151\1\145\1\157\1\156\1\uffff\1\155\1\uffff\1\167\1\145\2\uffff\1\135\1\167\2\uff3f\2\uffff\1\163\1\144\1\uff3f\1\164\1\144\1\156\1\uff3f\1\156\1\157\2\uff3f\1\145\1\156\2\uffff\1\uff3f\1\144\1\164\1\143\1\163\1\uffff\1\141\1\uff3f\1\uffff\1\145\1\164\2\uff3f\2\uffff\1\163\2\uff3f\3\uffff\2\uff3f\1\uffff\1\uff3f\6\uffff\2\uffff\1\uff3f\1\uffff\1\145\1\uffff\2\uff3f\1\150\1\147\1\151\1\145\1\uff3f\1\156\1\143\1\165\1\143\1\157\2\145\2\uffff\1\151\2\uffff\2\145\1\uffff\2\151\1\164\1\uffff\1\144\1\164\2\uffff\1\156\1\164\1\uffff\1\145\1\uff3f\1\164\1\uff3f\1\154\1\uffff\1\162\1\uff3f\2\uffff\1\uff3f\5\uffff\2\uffff\1\163\1\uffff\1\143\1\155\2\uffff\1\164\1\163\1\156\1\143\1\uffff\1\162\1\165\1\157\1\162\1\164\1\156\1\145\2\156\1\143\1\uff3f\1\157\1\156\1\uff3f\1\145\4\uff3f\1\uffff\1\uff3f\1\uffff\2\uff3f\2\uffff\2\uffff\1\151\1\145\1\157\1\151\1\137\1\uff3f\1\162\1\165\2\157\1\164\1\156\2\uff3f\1\164\1\156\1\135\1\147\1\164\1\uffff\1\156\1\147\1\uffff\1\144\7\uffff\2\uffff\2\143\1\156\1\143\1\152\1\uffff\1\157\1\164\1\156\1\163\1\145\1\144\2\uffff\1\150\1\135\2\uffff\5\uff3f\2\uffff\1\162\1\157\1\144\1\162\1\157\1\163\1\145\1\144\1\145\3\uff3f\7\uffff\2\uffff\1\157\1\156\1\uff3f\1\157\1\151\1\145\2\uff3f\1\143\3\uffff\2\uffff\1\163\1\144\1\uffff\1\163\1\156\1\143\2\uffff\1\157\1\47\1\uffff\1\145\1\uff3f\1\145\1\uff3f\1\157\1\156\2\uffff\1\uffff\1\143\1\uffff\1\143\1\uffff\1\156\1\144\1\47\2\157\1\144\1\uff3f\1\uffff\2\156\1\uff3f\1\uffff\2\144\1\uffff\2\uff3f\2\uffff";
    static final String DFA19_acceptS =
        "\41\uffff\1\164\1\165\1\166\1\uffff\1\170\1\uffff\1\173\1\176\1\u0081\5\uffff\1\u008b\1\u008d\1\u008e\1\u008f\2\uffff\1\u008b\15\uffff\1\33\1\55\1\uffff\1\145\55\uffff\1\131\1\163\4\uffff\1\146\2\uffff\1\150\1\151\1\172\1\152\1\174\1\161\1\162\1\175\1\164\1\165\1\166\1\u008d\1\uffff\1\167\1\170\1\u008c\1\171\1\173\1\176\1\u0081\1\u0082\1\u0084\2\uffff\1\u0088\1\u0089\1\u008a\1\u008e\17\uffff\1\26\14\uffff\1\155\1\156\11\uffff\1\154\16\uffff\1\160\2\uffff\1\157\25\uffff\1\153\1\uffff\1\147\1\177\1\u0080\1\u0083\14\uffff\1\136\10\uffff\1\140\31\uffff\1\137\2\uffff\1\115\1\141\17\uffff\1\142\7\uffff\1\143\1\132\1\133\1\134\1\135\1\144\12\uffff\1\122\1\uffff\1\110\5\uffff\1\104\1\uffff\1\130\2\uffff\1\24\1\57\4\uffff\1\106\1\107\15\uffff\1\60\1\103\5\uffff\1\105\2\uffff\1\116\4\uffff\1\117\1\120\3\uffff\1\114\1\112\1\113\2\uffff\1\121\1\uffff\1\126\1\125\1\127\1\111\1\123\1\124\3\uffff\1\67\1\uffff\1\70\16\uffff\1\56\1\102\1\uffff\1\62\1\63\2\uffff\1\65\3\uffff\1\74\2\uffff\1\77\1\100\2\uffff\1\61\5\uffff\1\71\2\uffff\1\72\1\73\1\uffff\1\64\1\66\1\75\1\76\1\101\3\uffff\1\50\2\uffff\1\52\1\53\4\uffff\1\47\23\uffff\1\45\1\uffff\1\46\2\uffff\1\51\1\54\25\uffff\1\40\2\uffff\1\42\1\uffff\1\44\1\34\1\35\1\36\1\37\1\41\1\43\7\uffff\1\31\6\uffff\1\27\1\30\2\uffff\1\23\1\32\23\uffff\1\15\1\25\1\16\1\17\1\20\1\21\1\22\13\uffff\1\12\1\13\1\14\4\uffff\1\11\3\uffff\1\7\1\10\11\uffff\1\u0086\1\u0085\2\uffff\1\5\1\uffff\1\6\7\uffff\1\u0087\3\uffff\1\4\2\uffff\1\3\2\uffff\1\1\1\2";
    static final String DFA19_specialS =
        "\1\0\52\uffff\1\30\1\25\1\26\1\27\145\uffff\1\1\131\uffff\1\2\120\uffff\1\3\1\13\113\uffff\1\4\1\14\65\uffff\1\5\1\15\45\uffff\1\6\1\16\37\uffff\1\7\1\17\27\uffff\1\10\1\20\23\uffff\1\11\1\21\14\uffff\1\12\1\22\12\uffff\1\23\10\uffff\1\24\27\uffff}>";
    static final String[] DFA19_transitionS = {
            "\11\62\2\61\2\62\1\61\22\62\1\61\1\33\1\54\1\60\1\34\2\62\1\53\1\30\1\41\1\51\1\42\1\43\1\44\1\45\1\46\12\52\2\62\1\35\1\47\1\36\2\62\1\31\1\13\1\14\1\4\1\15\1\7\1\22\1\3\1\10\1\26\1\57\1\23\1\1\1\16\1\20\1\11\1\17\1\24\1\2\1\27\1\12\1\21\1\25\1\32\1\5\1\57\1\56\2\62\1\37\1\57\1\55\1\31\1\13\1\14\1\4\1\15\1\7\1\22\1\3\1\10\1\26\1\57\1\23\1\1\1\16\1\20\1\11\1\17\1\24\1\2\1\27\1\12\1\21\1\25\1\32\1\5\1\57\1\6\1\40\1\50\102\62\u0190\57\u01c0\62\100\57\u2bf1\62\u00bf\57\u00f0\62\20\57\u0200\62\u19c0\57\100\62\u5200\57\u5900\62\u0200\57\u043f\62\1\57\u00c0\62",
            "\1\63\5\uffff\1\64\31\uffff\1\63\5\uffff\1\64",
            "\1\66\3\uffff\1\70\5\uffff\1\71\4\uffff\1\67\20\uffff\1\66\3\uffff\1\70\5\uffff\1\71\4\uffff\1\67",
            "\1\73\15\uffff\1\72\21\uffff\1\73\15\uffff\1\72",
            "\1\74\3\uffff\1\76\3\uffff\1\75\27\uffff\1\74\3\uffff\1\76\3\uffff\1\75",
            "\1\77\37\uffff\1\77",
            "\1\101\2\uffff\1\104\1\uffff\1\103\1\uffff\1\106\2\uffff\1\105\1\uffff\1\102\14\uffff\1\100\6\uffff\1\101\2\uffff\1\104\1\uffff\1\103\1\uffff\1\106\2\uffff\1\105\1\uffff\1\102",
            "\1\110\3\uffff\1\111\5\uffff\1\107\2\uffff\1\112\2\uffff\1\113\17\uffff\1\110\3\uffff\1\111\5\uffff\1\107\2\uffff\1\112\2\uffff\1\113",
            "\1\114\4\uffff\1\115\32\uffff\1\114\4\uffff\1\115",
            "\1\116\3\uffff\1\120\3\uffff\1\121\10\uffff\1\117\16\uffff\1\116\3\uffff\1\120\3\uffff\1\121\10\uffff\1\117",
            "\1\122\4\uffff\1\123\32\uffff\1\122\4\uffff\1\123",
            "\1\124\23\uffff\1\125\13\uffff\1\124\23\uffff\1\125",
            "\1\127\20\uffff\1\130\2\uffff\1\126\13\uffff\1\127\20\uffff\1\130\2\uffff\1\126",
            "\1\132\1\uffff\1\133\11\uffff\1\131\23\uffff\1\132\1\uffff\1\133\11\uffff\1\131",
            "\1\134\15\uffff\1\136\5\uffff\1\135\13\uffff\1\134\15\uffff\1\136\5\uffff\1\135",
            "\1\137\37\uffff\1\137",
            "\1\140\7\uffff\1\143\3\uffff\1\141\2\uffff\1\142\1\144\17\uffff\1\140\7\uffff\1\143\3\uffff\1\141\2\uffff\1\142\1\144",
            "\1\145\37\uffff\1\145",
            "\1\146\37\uffff\1\146",
            "\1\150\3\uffff\1\151\3\uffff\1\147\27\uffff\1\150\3\uffff\1\151\3\uffff\1\147",
            "\1\152\7\uffff\1\153\5\uffff\1\154\21\uffff\1\152\7\uffff\1\153\5\uffff\1\154",
            "\1\156\2\uffff\1\155\1\157\33\uffff\1\156\2\uffff\1\155\1\157",
            "\1\160\37\uffff\1\160",
            "\1\161\1\162\5\uffff\1\163\30\uffff\1\161\1\162\5\uffff\1\163",
            "\1\164",
            "\1\166\1\uffff\1\167\4\uffff\1\170\30\uffff\1\166\1\uffff\1\167\4\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\1\172",
            "\1\174\7\uffff\1\173\37\uffff\1\173",
            "\1\175\1\176",
            "\1\u0080",
            "\1\u0082",
            "\1\u0083",
            "",
            "",
            "",
            "\1\u0088\2\uffff\12\u0089",
            "",
            "\1\u008c\4\uffff\1\u0088",
            "",
            "",
            "",
            "\1\u0092\1\uffff\12\u0093",
            "\60\u0095\12\u0094\uffc6\u0095",
            "\0\u0096",
            "\0\u0097",
            "\0\u0097",
            "",
            "",
            "",
            "",
            "\1\u009a\12\uffff\1\u0099\24\uffff\1\u009a\12\uffff\1\u0099",
            "\1\u009b\37\uffff\1\u009b",
            "",
            "\1\u009c\10\uffff\1\u009d\26\uffff\1\u009c\10\uffff\1\u009d",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\37\uffff\1\u00a4",
            "\1\u00a5\37\uffff\1\u00a5",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\u00a7\4\uffff\1\u00a8\32\uffff\1\u00a7\4\uffff\1\u00a8",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\u00aa\37\uffff\1\u00aa",
            "",
            "",
            "\1\u00ab\37\uffff\1\u00ab",
            "",
            "\1\u00ac\5\uffff\1\u00ad\31\uffff\1\u00ac\5\uffff\1\u00ad",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "\1\u00b1\37\uffff\1\u00b1",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\2\65\1\u00b3\12\65\1\u00b4\5\65\1\u00b2\6\65\4\uffff\1\65\1\uffff\2\65\1\u00b3\12\65\1\u00b4\5\65\1\u00b2\6\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00b7\37\uffff\1\u00b7",
            "\1\u00b8\37\uffff\1\u00b8",
            "\1\u00b9\37\uffff\1\u00b9",
            "\1\u00ba\37\uffff\1\u00ba",
            "\1\u00bb\6\uffff\1\u00bd\6\uffff\1\u00bc\21\uffff\1\u00bb\6\uffff\1\u00bd\6\uffff\1\u00bc",
            "\1\u00be\37\uffff\1\u00be",
            "\1\u00bf\37\uffff\1\u00bf",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "\1\u00c3\37\uffff\1\u00c3",
            "\1\u00c4\5\uffff\1\u00c6\12\uffff\1\u00c5\16\uffff\1\u00c4\5\uffff\1\u00c6\12\uffff\1\u00c5",
            "\1\u00c7\37\uffff\1\u00c7",
            "\1\u00c8\37\uffff\1\u00c8",
            "\1\u00c9\37\uffff\1\u00c9",
            "\1\u00ca\37\uffff\1\u00ca",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\u00cc\37\uffff\1\u00cc",
            "\1\u00cd\37\uffff\1\u00cd",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\3\65\1\u00ce\26\65\4\uffff\1\65\1\uffff\3\65\1\u00ce\26\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\13\65\1\u00d1\16\65\4\uffff\1\65\1\uffff\13\65\1\u00d1\16\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\u00d4\37\uffff\1\u00d4",
            "\1\u00d5\37\uffff\1\u00d5",
            "\1\u00d7\1\uffff\1\u00d6\35\uffff\1\u00d7\1\uffff\1\u00d6",
            "\1\u00d8\37\uffff\1\u00d8",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\u00da\37\uffff\1\u00da",
            "\1\u00db\37\uffff\1\u00db",
            "\1\u00dc\37\uffff\1\u00dc",
            "\1\u00dd\37\uffff\1\u00dd",
            "\1\u00de\37\uffff\1\u00de",
            "\1\u00df\37\uffff\1\u00df",
            "\1\u00e0\37\uffff\1\u00e0",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\u00e2\37\uffff\1\u00e2",
            "\1\u00e3\37\uffff\1\u00e3",
            "",
            "",
            "\1\u00e4\37\uffff\1\u00e4",
            "\1\u00e5\24\uffff\1\u00e6\12\uffff\1\u00e5\24\uffff\1\u00e6",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\2\65\1\u00e7\27\65\4\uffff\1\65\1\uffff\2\65\1\u00e7\27\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00e9\37\uffff\1\u00e9",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00ec\131\uffff\1\u00eb",
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
            "\1\u0092\1\uffff\12\u0089",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0092\1\uffff\12\u0093",
            "\60\u0095\12\u00ee\uffc6\u0095",
            "",
            "",
            "",
            "",
            "\1\u00ef\37\uffff\1\u00ef",
            "\1\u00f0\37\uffff\1\u00f0",
            "\1\u00f1\37\uffff\1\u00f1",
            "\1\u00f2\37\uffff\1\u00f2",
            "\1\u00f3\37\uffff\1\u00f3",
            "\1\u00f4\37\uffff\1\u00f4",
            "\1\u00f5\37\uffff\1\u00f5",
            "\1\u00f6\37\uffff\1\u00f6",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\u00f8\37\uffff\1\u00f8",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u00f9\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00fb\37\uffff\1\u00fb",
            "\1\u00fc\37\uffff\1\u00fc",
            "\1\u00fd\37\uffff\1\u00fd",
            "\1\u00fe\37\uffff\1\u00fe",
            "",
            "\1\u00ff\37\uffff\1\u00ff",
            "\1\u0100\37\uffff\1\u0100",
            "\1\u0101\37\uffff\1\u0101",
            "\1\u0102\37\uffff\1\u0102",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "\1\u0106\37\uffff\1\u0106",
            "\1\u0107\37\uffff\1\u0107",
            "\1\u0108\37\uffff\1\u0108",
            "\1\u0109\37\uffff\1\u0109",
            "\1\u010a\37\uffff\1\u010a",
            "",
            "",
            "\1\u010b\37\uffff\1\u010b",
            "\1\u010c\37\uffff\1\u010c",
            "\1\u010d\37\uffff\1\u010d",
            "\1\u010e\37\uffff\1\u010e",
            "\1\u010f\37\uffff\1\u010f",
            "\1\u0110\37\uffff\1\u0110",
            "\1\u0111\37\uffff\1\u0111",
            "\1\u0112\37\uffff\1\u0112",
            "\1\u0113\37\uffff\1\u0113",
            "",
            "\1\u0114\37\uffff\1\u0114",
            "\1\u0116\16\uffff\1\u0115\20\uffff\1\u0116\16\uffff\1\u0115",
            "\1\u0117\37\uffff\1\u0117",
            "\1\u0119\6\uffff\1\u0118\30\uffff\1\u0119\6\uffff\1\u0118",
            "\1\u011a\37\uffff\1\u011a",
            "\1\u011b\37\uffff\1\u011b",
            "\1\u011c\37\uffff\1\u011c",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u011e\37\uffff\1\u011e",
            "\1\u011f\37\uffff\1\u011f",
            "\1\u0120\31\uffff\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0122\37\uffff\1\u0122",
            "\1\u0123\37\uffff\1\u0123",
            "\1\u0124\37\uffff\1\u0124",
            "",
            "\1\u0125\37\uffff\1\u0125",
            "\1\u0126\37\uffff\1\u0126",
            "",
            "\1\u0127\37\uffff\1\u0127",
            "\1\u0128\37\uffff\1\u0128",
            "\1\u0129\37\uffff\1\u0129",
            "\1\u012a\37\uffff\1\u012a",
            "\1\u012b\37\uffff\1\u012b",
            "\1\u012c\37\uffff\1\u012c",
            "\1\u012d\37\uffff\1\u012d",
            "\1\u012e\37\uffff\1\u012e",
            "\1\u012f\37\uffff\1\u012f",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\22\65\1\u0130\7\65\4\uffff\1\65\1\uffff\22\65\1\u0130\7\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0133\3\uffff\1\u0132\33\uffff\1\u0133\3\uffff\1\u0132",
            "\1\u0134\37\uffff\1\u0134",
            "\1\u0135\37\uffff\1\u0135",
            "\1\u0136\37\uffff\1\u0136",
            "\1\u0137\37\uffff\1\u0137",
            "\1\u0138\37\uffff\1\u0138",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "\60\u0095\12\u013f\1\u0140\uffc5\u0095",
            "\1\u0142\1\u0141\36\uffff\1\u0142\1\u0141",
            "\1\u0143\37\uffff\1\u0143",
            "\1\u0144\37\uffff\1\u0144",
            "\1\u0145\37\uffff\1\u0145",
            "\1\u0146\37\uffff\1\u0146",
            "\1\u0147\37\uffff\1\u0147",
            "\1\u0148\37\uffff\1\u0148",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u014a\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u014c\37\uffff\1\u014c",
            "\1\u014f\4\uffff\1\u014d\5\uffff\1\u014e\24\uffff\1\u014f\4\uffff\1\u014d\5\uffff\1\u014e",
            "",
            "\1\u0150\37\uffff\1\u0150",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u0152\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0154\37\uffff\1\u0154",
            "\1\u0155\37\uffff\1\u0155",
            "\1\u0156\3\uffff\1\u0157\33\uffff\1\u0156\3\uffff\1\u0157",
            "\1\u0158\37\uffff\1\u0158",
            "\1\u0159\37\uffff\1\u0159",
            "",
            "\1\u015a\37\uffff\1\u015a",
            "\1\u015b\37\uffff\1\u015b",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u015e\37\uffff\1\u015e",
            "\1\u015f\37\uffff\1\u015f",
            "\1\u0160\37\uffff\1\u0160",
            "\1\u0161\37\uffff\1\u0161",
            "\1\u0162\37\uffff\1\u0162",
            "\1\u0163\37\uffff\1\u0163",
            "\1\u0164\37\uffff\1\u0164",
            "\1\u0165\37\uffff\1\u0165",
            "\1\u0166\37\uffff\1\u0166",
            "\1\u0167\37\uffff\1\u0167",
            "\1\u0168\37\uffff\1\u0168",
            "\1\u0169\37\uffff\1\u0169",
            "\1\u016a\37\uffff\1\u016a",
            "\1\u016b",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u016d\37\uffff\1\u016d",
            "\1\u016e\37\uffff\1\u016e",
            "\1\u016f\37\uffff\1\u016f",
            "\1\u0170\37\uffff\1\u0170",
            "\1\u0171\37\uffff\1\u0171",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u0173\37\uffff\1\u0173",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\22\65\1\u0174\7\65\4\uffff\1\65\1\uffff\22\65\1\u0174\7\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "\1\u0176\37\uffff\1\u0176",
            "\1\u0177\37\uffff\1\u0177",
            "\1\u0178\37\uffff\1\u0178",
            "\1\u0179\37\uffff\1\u0179",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u017c\37\uffff\1\u017c",
            "\1\u017d\37\uffff\1\u017d",
            "\1\u017e\37\uffff\1\u017e",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0182\37\uffff\1\u0182",
            "\1\u0183\37\uffff\1\u0183",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u0185\37\uffff\1\u0185",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "",
            "",
            "\60\u0095\12\u018c\uffc6\u0095",
            "\60\u0095\12\u018d\uffc6\u0095",
            "\1\u018e\37\uffff\1\u018e",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0190\37\uffff\1\u0190",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0192\37\uffff\1\u0192",
            "\1\u0193\37\uffff\1\u0193",
            "\1\u0194\37\uffff\1\u0194",
            "\1\u0195\37\uffff\1\u0195",
            "",
            "\1\u0196\5\uffff\1\u0197\31\uffff\1\u0196\5\uffff\1\u0197",
            "",
            "\1\u0198\37\uffff\1\u0198",
            "\1\u0199\37\uffff\1\u0199",
            "\1\u019a\37\uffff\1\u019a",
            "\1\u019b\37\uffff\1\u019b",
            "\1\u019c\37\uffff\1\u019c",
            "",
            "\1\u019d\37\uffff\1\u019d",
            "",
            "\1\u019e\37\uffff\1\u019e",
            "\1\u019f\37\uffff\1\u019f",
            "",
            "",
            "\1\u01a0",
            "\1\u01a2\37\uffff\1\u01a2",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "\1\u01a5\37\uffff\1\u01a5",
            "\1\u01a6\37\uffff\1\u01a6",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01a8\37\uffff\1\u01a8",
            "\1\u01a9\37\uffff\1\u01a9",
            "\1\u01aa\37\uffff\1\u01aa",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01ac\37\uffff\1\u01ac",
            "\1\u01ad\37\uffff\1\u01ad",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01b0\37\uffff\1\u01b0",
            "\1\u01b1\37\uffff\1\u01b1",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01b3\37\uffff\1\u01b3",
            "\1\u01b4\37\uffff\1\u01b4",
            "\1\u01b5\37\uffff\1\u01b5",
            "\1\u01b6\37\uffff\1\u01b6",
            "",
            "\1\u01b7\37\uffff\1\u01b7",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u01b9\37\uffff\1\u01b9",
            "\1\u01ba\37\uffff\1\u01ba",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "\1\u01bd\37\uffff\1\u01bd",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "",
            "",
            "\55\u0095\1\u01c3\uffd2\u0095",
            "\60\u0095\12\u01c4\uffc6\u0095",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u01c5\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u01c7\37\uffff\1\u01c7",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u01c8\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01cb\37\uffff\1\u01cb",
            "\1\u01cc\37\uffff\1\u01cc",
            "\1\u01cd\37\uffff\1\u01cd",
            "\1\u01ce\37\uffff\1\u01ce",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01d0\12\uffff\1\u01d1\24\uffff\1\u01d0\12\uffff\1\u01d1",
            "\1\u01d2\37\uffff\1\u01d2",
            "\1\u01d3\37\uffff\1\u01d3",
            "\1\u01d4\37\uffff\1\u01d4",
            "\1\u01d5\37\uffff\1\u01d5",
            "\1\u01d6\37\uffff\1\u01d6",
            "\1\u01d7\37\uffff\1\u01d7",
            "",
            "",
            "\1\u01d8\37\uffff\1\u01d8",
            "",
            "",
            "\1\u01d9\37\uffff\1\u01d9",
            "\1\u01da\37\uffff\1\u01da",
            "",
            "\1\u01db\37\uffff\1\u01db",
            "\1\u01dc\37\uffff\1\u01dc",
            "\1\u01dd\37\uffff\1\u01dd",
            "",
            "\1\u01de\37\uffff\1\u01de",
            "\1\u01df\37\uffff\1\u01df",
            "",
            "",
            "\1\u01e0\37\uffff\1\u01e0",
            "\1\u01e1\37\uffff\1\u01e1",
            "",
            "\1\u01e2\37\uffff\1\u01e2",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01e4\37\uffff\1\u01e4",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01e6\37\uffff\1\u01e6",
            "",
            "\1\u01e7\37\uffff\1\u01e7",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "",
            "\60\u0095\2\u01ea\uffce\u0095",
            "\72\u0095\1\u01eb\uffc5\u0095",
            "\1\u01ec\5\uffff\1\u01ed\31\uffff\1\u01ec\5\uffff\1\u01ed",
            "",
            "\1\u01ee\37\uffff\1\u01ee",
            "\1\u01ef\37\uffff\1\u01ef",
            "",
            "",
            "\1\u01f0\37\uffff\1\u01f0",
            "\1\u01f1\37\uffff\1\u01f1",
            "\1\u01f2\12\uffff\1\u01f3\24\uffff\1\u01f2\12\uffff\1\u01f3",
            "\1\u01f4\37\uffff\1\u01f4",
            "",
            "\1\u01f5\37\uffff\1\u01f5",
            "\1\u01f6\37\uffff\1\u01f6",
            "\1\u01f7\37\uffff\1\u01f7",
            "\1\u01f8\37\uffff\1\u01f8",
            "\1\u01f9\37\uffff\1\u01f9",
            "\1\u01fa\37\uffff\1\u01fa",
            "\1\u01fb\37\uffff\1\u01fb",
            "\1\u01fc\37\uffff\1\u01fc",
            "\1\u01fd\37\uffff\1\u01fd",
            "\1\u01fe\37\uffff\1\u01fe",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0200\37\uffff\1\u0200",
            "\1\u0201\37\uffff\1\u0201",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0203\37\uffff\1\u0203",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "\60\u0095\12\u020b\uffc6\u0095",
            "\60\u0095\12\u020c\uffc6\u0095",
            "\1\u020d\37\uffff\1\u020d",
            "\1\u020e\37\uffff\1\u020e",
            "\1\u020f\37\uffff\1\u020f",
            "\1\u0210\37\uffff\1\u0210",
            "\1\u0211",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0213\37\uffff\1\u0213",
            "\1\u0214\37\uffff\1\u0214",
            "\1\u0215\37\uffff\1\u0215",
            "\1\u0216\37\uffff\1\u0216",
            "\1\u0217\37\uffff\1\u0217",
            "\1\u0218\37\uffff\1\u0218",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u021b\37\uffff\1\u021b",
            "\1\u021c\37\uffff\1\u021c",
            "\1\u021d",
            "\1\u021f\37\uffff\1\u021f",
            "\1\u0220\37\uffff\1\u0220",
            "",
            "\1\u0221\37\uffff\1\u0221",
            "\1\u0222\37\uffff\1\u0222",
            "",
            "\1\u0223\37\uffff\1\u0223",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\55\u0095\1\u0224\uffd2\u0095",
            "\60\u0095\12\u0225\uffc6\u0095",
            "\1\u0226\37\uffff\1\u0226",
            "\1\u0227\37\uffff\1\u0227",
            "\1\u0228\37\uffff\1\u0228",
            "\1\u0229\37\uffff\1\u0229",
            "\1\u022a\37\uffff\1\u022a",
            "",
            "\1\u022b\37\uffff\1\u022b",
            "\1\u022c\37\uffff\1\u022c",
            "\1\u022d\37\uffff\1\u022d",
            "\1\u022e\37\uffff\1\u022e",
            "\1\u022f\37\uffff\1\u022f",
            "\1\u0230\37\uffff\1\u0230",
            "",
            "",
            "\1\u0231\37\uffff\1\u0231",
            "\1\u0232",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\60\u0095\4\u0239\uffcc\u0095",
            "\56\u0095\1\u023a\uffd1\u0095",
            "\1\u023b\37\uffff\1\u023b",
            "\1\u023c\37\uffff\1\u023c",
            "\1\u023d\37\uffff\1\u023d",
            "\1\u023e\37\uffff\1\u023e",
            "\1\u023f\37\uffff\1\u023f",
            "\1\u0240\37\uffff\1\u0240",
            "\1\u0241\37\uffff\1\u0241",
            "\1\u0242\37\uffff\1\u0242",
            "\1\u0243\37\uffff\1\u0243",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\60\u0095\12\u0247\uffc6\u0095",
            "\60\u0095\12\u0248\uffc6\u0095",
            "\1\u0249\37\uffff\1\u0249",
            "\1\u024a\37\uffff\1\u024a",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u024c\37\uffff\1\u024c",
            "\1\u024d\37\uffff\1\u024d",
            "\1\u024e\37\uffff\1\u024e",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0251\37\uffff\1\u0251",
            "",
            "",
            "",
            "\47\u0095\1\u0252\uffd8\u0095",
            "\60\u0095\12\u0253\uffc6\u0095",
            "\1\u0254\37\uffff\1\u0254",
            "\1\u0255\37\uffff\1\u0255",
            "",
            "\1\u0256\37\uffff\1\u0256",
            "\1\u0257\37\uffff\1\u0257",
            "\1\u0258\37\uffff\1\u0258",
            "",
            "",
            "\1\u0259\37\uffff\1\u0259",
            "\1\u025b\6\uffff\1\u0095",
            "\60\u0095\12\u025c\uffc6\u0095",
            "\1\u025d\37\uffff\1\u025d",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u025f\37\uffff\1\u025f",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0261\37\uffff\1\u0261",
            "\1\u0262\37\uffff\1\u0262",
            "",
            "",
            "\47\u0095\1\u0263\uffd8\u0095",
            "\1\u0264\37\uffff\1\u0264",
            "",
            "\1\u0265\37\uffff\1\u0265",
            "",
            "\1\u0266\37\uffff\1\u0266",
            "\1\u0267\37\uffff\1\u0267",
            "\1\u0095",
            "\1\u0269\37\uffff\1\u0269",
            "\1\u026a\37\uffff\1\u026a",
            "\1\u026b\37\uffff\1\u026b",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u026d\37\uffff\1\u026d",
            "\1\u026e\37\uffff\1\u026e",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u0270\37\uffff\1\u0270",
            "\1\u0271\37\uffff\1\u0271",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
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
            return "1:1: Tokens : ( MINUTE_MICROSECOND | SECOND_MICROSECOND | HOUR_MICROSECOND | DAY_MICROSECOND | MINUTE_SECOND | STRAIGHT_JOIN | HOUR_MINUTE | HOUR_SECOND | MICROSECOND | DAY_MINUTE | DAY_SECOND | YEAR_MONTH | BETWEEN_4 | KW_FOLLOWING | INTERSECT | PARTITION | PRECEDING | UNBOUNDED | BETWEEN_2 | NOTEQUAL | BETWEEN_3 | GREATER_1 | DAY_HOUR | DISTINCT | SIBLINGS | BETWEEN_1 | GREATER | BETWEEN | CURRENT | EXCLUDE | EXTRACT | INCLUDE | NATURAL | PERCENT | QUARTER | UNPIVOT | EXCEPT | EXISTS | HAVING | MINUTE | OFFSET | SECOND | SELECT | VALUES | EQUAL | LESS_1 | NOTIN | CAST | CROSS | FETCH | FIRST | GROUP | INNER | LIMIT | MINUS | MONTH | NULLS | ORDER | OUTER | PIVOT | RANGE | RIGHT | UNION | USING | WHERE | LESS | CASE | DESC | ELSE | FROM | FULL | HOUR | JOIN | LAST | LEFT | LIKE | NOT_1 | NULL | ONLY | OVER | ROWS | SOME | THEN | TIES | WEEK | WHEN | WITH | YEAR | LeftParenthesisPlusSignRightParenthesis | ALL | AND | ANY | ASC | DAY | END | FOR | NOT | ROW | TOP | XML | IN_1 | ExclamationMarkEqualsSign | X | LessThanSignEqualsSign | LessThanSignGreaterThanSign | GreaterThanSignEqualsSign | AS | BY | IN | IS | ON | OR | CircumflexAccentEqualsSign | VerticalLineVerticalLine | LeftParenthesis | RightParenthesis | PlusSign | Comma | HyphenMinus | FullStop | Solidus | LessThanSign | EqualsSign | GreaterThanSign | VerticalLine | RightCurlyBracket | RULE_JRPARAM | RULE_JRNPARAM | RULE_STAR | RULE_UNSIGNED | RULE_INT | RULE_SIGNED_DOUBLE | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_STRING_ | RULE_STRING | RULE_DBNAME | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA19_0 = input.LA(1);

                        s = -1;
                        if ( (LA19_0=='M'||LA19_0=='m') ) {s = 1;}

                        else if ( (LA19_0=='S'||LA19_0=='s') ) {s = 2;}

                        else if ( (LA19_0=='H'||LA19_0=='h') ) {s = 3;}

                        else if ( (LA19_0=='D'||LA19_0=='d') ) {s = 4;}

                        else if ( (LA19_0=='Y'||LA19_0=='y') ) {s = 5;}

                        else if ( (LA19_0=='{') ) {s = 6;}

                        else if ( (LA19_0=='F'||LA19_0=='f') ) {s = 7;}

                        else if ( (LA19_0=='I'||LA19_0=='i') ) {s = 8;}

                        else if ( (LA19_0=='P'||LA19_0=='p') ) {s = 9;}

                        else if ( (LA19_0=='U'||LA19_0=='u') ) {s = 10;}

                        else if ( (LA19_0=='B'||LA19_0=='b') ) {s = 11;}

                        else if ( (LA19_0=='C'||LA19_0=='c') ) {s = 12;}

                        else if ( (LA19_0=='E'||LA19_0=='e') ) {s = 13;}

                        else if ( (LA19_0=='N'||LA19_0=='n') ) {s = 14;}

                        else if ( (LA19_0=='Q'||LA19_0=='q') ) {s = 15;}

                        else if ( (LA19_0=='O'||LA19_0=='o') ) {s = 16;}

                        else if ( (LA19_0=='V'||LA19_0=='v') ) {s = 17;}

                        else if ( (LA19_0=='G'||LA19_0=='g') ) {s = 18;}

                        else if ( (LA19_0=='L'||LA19_0=='l') ) {s = 19;}

                        else if ( (LA19_0=='R'||LA19_0=='r') ) {s = 20;}

                        else if ( (LA19_0=='W'||LA19_0=='w') ) {s = 21;}

                        else if ( (LA19_0=='J'||LA19_0=='j') ) {s = 22;}

                        else if ( (LA19_0=='T'||LA19_0=='t') ) {s = 23;}

                        else if ( (LA19_0=='(') ) {s = 24;}

                        else if ( (LA19_0=='A'||LA19_0=='a') ) {s = 25;}

                        else if ( (LA19_0=='X'||LA19_0=='x') ) {s = 26;}

                        else if ( (LA19_0=='!') ) {s = 27;}

                        else if ( (LA19_0=='$') ) {s = 28;}

                        else if ( (LA19_0=='<') ) {s = 29;}

                        else if ( (LA19_0=='>') ) {s = 30;}

                        else if ( (LA19_0=='^') ) {s = 31;}

                        else if ( (LA19_0=='|') ) {s = 32;}

                        else if ( (LA19_0==')') ) {s = 33;}

                        else if ( (LA19_0=='+') ) {s = 34;}

                        else if ( (LA19_0==',') ) {s = 35;}

                        else if ( (LA19_0=='-') ) {s = 36;}

                        else if ( (LA19_0=='.') ) {s = 37;}

                        else if ( (LA19_0=='/') ) {s = 38;}

                        else if ( (LA19_0=='=') ) {s = 39;}

                        else if ( (LA19_0=='}') ) {s = 40;}

                        else if ( (LA19_0=='*') ) {s = 41;}

                        else if ( ((LA19_0>='0' && LA19_0<='9')) ) {s = 42;}

                        else if ( (LA19_0=='\'') ) {s = 43;}

                        else if ( (LA19_0=='\"') ) {s = 44;}

                        else if ( (LA19_0=='`') ) {s = 45;}

                        else if ( (LA19_0=='[') ) {s = 46;}

                        else if ( (LA19_0=='K'||LA19_0=='Z'||LA19_0=='_'||LA19_0=='k'||LA19_0=='z'||(LA19_0>='\u00C0' && LA19_0<='\u024F')||(LA19_0>='\u0410' && LA19_0<='\u044F')||(LA19_0>='\u3041' && LA19_0<='\u30FF')||(LA19_0>='\u31F0' && LA19_0<='\u31FF')||(LA19_0>='\u3400' && LA19_0<='\u4DBF')||(LA19_0>='\u4E00' && LA19_0<='\u9FFF')||(LA19_0>='\uF900' && LA19_0<='\uFAFF')||LA19_0=='\uFF3F') ) {s = 47;}

                        else if ( (LA19_0=='#') ) {s = 48;}

                        else if ( ((LA19_0>='\t' && LA19_0<='\n')||LA19_0=='\r'||LA19_0==' ') ) {s = 49;}

                        else if ( ((LA19_0>='\u0000' && LA19_0<='\b')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\u001F')||(LA19_0>='%' && LA19_0<='&')||(LA19_0>=':' && LA19_0<=';')||(LA19_0>='?' && LA19_0<='@')||(LA19_0>='\\' && LA19_0<=']')||(LA19_0>='~' && LA19_0<='\u00BF')||(LA19_0>='\u0250' && LA19_0<='\u040F')||(LA19_0>='\u0450' && LA19_0<='\u3040')||(LA19_0>='\u3100' && LA19_0<='\u31EF')||(LA19_0>='\u3200' && LA19_0<='\u33FF')||(LA19_0>='\u4DC0' && LA19_0<='\u4DFF')||(LA19_0>='\uA000' && LA19_0<='\uF8FF')||(LA19_0>='\uFB00' && LA19_0<='\uFF3E')||(LA19_0>='\uFF40' && LA19_0<='\uFFFF')) ) {s = 50;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA19_148 = input.LA(1);

                        s = -1;
                        if ( ((LA19_148>='0' && LA19_148<='9')) ) {s = 238;}

                        else if ( ((LA19_148>='\u0000' && LA19_148<='/')||(LA19_148>=':' && LA19_148<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA19_238 = input.LA(1);

                        s = -1;
                        if ( ((LA19_238>='0' && LA19_238<='9')) ) {s = 319;}

                        else if ( (LA19_238==':') ) {s = 320;}

                        else if ( ((LA19_238>='\u0000' && LA19_238<='/')||(LA19_238>=';' && LA19_238<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA19_319 = input.LA(1);

                        s = -1;
                        if ( ((LA19_319>='0' && LA19_319<='9')) ) {s = 396;}

                        else if ( ((LA19_319>='\u0000' && LA19_319<='/')||(LA19_319>=':' && LA19_319<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA19_396 = input.LA(1);

                        s = -1;
                        if ( (LA19_396=='-') ) {s = 451;}

                        else if ( ((LA19_396>='\u0000' && LA19_396<=',')||(LA19_396>='.' && LA19_396<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA19_451 = input.LA(1);

                        s = -1;
                        if ( ((LA19_451>='0' && LA19_451<='1')) ) {s = 490;}

                        else if ( ((LA19_451>='\u0000' && LA19_451<='/')||(LA19_451>='2' && LA19_451<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA19_490 = input.LA(1);

                        s = -1;
                        if ( ((LA19_490>='0' && LA19_490<='9')) ) {s = 523;}

                        else if ( ((LA19_490>='\u0000' && LA19_490<='/')||(LA19_490>=':' && LA19_490<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA19_523 = input.LA(1);

                        s = -1;
                        if ( (LA19_523=='-') ) {s = 548;}

                        else if ( ((LA19_523>='\u0000' && LA19_523<=',')||(LA19_523>='.' && LA19_523<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA19_548 = input.LA(1);

                        s = -1;
                        if ( ((LA19_548>='0' && LA19_548<='3')) ) {s = 569;}

                        else if ( ((LA19_548>='\u0000' && LA19_548<='/')||(LA19_548>='4' && LA19_548<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA19_569 = input.LA(1);

                        s = -1;
                        if ( ((LA19_569>='0' && LA19_569<='9')) ) {s = 583;}

                        else if ( ((LA19_569>='\u0000' && LA19_569<='/')||(LA19_569>=':' && LA19_569<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA19_583 = input.LA(1);

                        s = -1;
                        if ( (LA19_583=='\'') ) {s = 594;}

                        else if ( ((LA19_583>='\u0000' && LA19_583<='&')||(LA19_583>='(' && LA19_583<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA19_320 = input.LA(1);

                        s = -1;
                        if ( ((LA19_320>='0' && LA19_320<='9')) ) {s = 397;}

                        else if ( ((LA19_320>='\u0000' && LA19_320<='/')||(LA19_320>=':' && LA19_320<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA19_397 = input.LA(1);

                        s = -1;
                        if ( ((LA19_397>='0' && LA19_397<='9')) ) {s = 452;}

                        else if ( ((LA19_397>='\u0000' && LA19_397<='/')||(LA19_397>=':' && LA19_397<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA19_452 = input.LA(1);

                        s = -1;
                        if ( (LA19_452==':') ) {s = 491;}

                        else if ( ((LA19_452>='\u0000' && LA19_452<='9')||(LA19_452>=';' && LA19_452<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA19_491 = input.LA(1);

                        s = -1;
                        if ( ((LA19_491>='0' && LA19_491<='9')) ) {s = 524;}

                        else if ( ((LA19_491>='\u0000' && LA19_491<='/')||(LA19_491>=':' && LA19_491<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA19_524 = input.LA(1);

                        s = -1;
                        if ( ((LA19_524>='0' && LA19_524<='9')) ) {s = 549;}

                        else if ( ((LA19_524>='\u0000' && LA19_524<='/')||(LA19_524>=':' && LA19_524<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA19_549 = input.LA(1);

                        s = -1;
                        if ( (LA19_549=='.') ) {s = 570;}

                        else if ( ((LA19_549>='\u0000' && LA19_549<='-')||(LA19_549>='/' && LA19_549<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA19_570 = input.LA(1);

                        s = -1;
                        if ( ((LA19_570>='0' && LA19_570<='9')) ) {s = 584;}

                        else if ( ((LA19_570>='\u0000' && LA19_570<='/')||(LA19_570>=':' && LA19_570<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA19_584 = input.LA(1);

                        s = -1;
                        if ( ((LA19_584>='0' && LA19_584<='9')) ) {s = 595;}

                        else if ( ((LA19_584>='\u0000' && LA19_584<='/')||(LA19_584>=':' && LA19_584<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA19_595 = input.LA(1);

                        s = -1;
                        if ( ((LA19_595>='0' && LA19_595<='9')) ) {s = 604;}

                        else if ( ((LA19_595>='\u0000' && LA19_595<='/')||(LA19_595>=':' && LA19_595<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA19_604 = input.LA(1);

                        s = -1;
                        if ( (LA19_604=='\'') ) {s = 611;}

                        else if ( ((LA19_604>='\u0000' && LA19_604<='&')||(LA19_604>='(' && LA19_604<='\uFFFF')) ) {s = 149;}

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA19_44 = input.LA(1);

                        s = -1;
                        if ( ((LA19_44>='\u0000' && LA19_44<='\uFFFF')) ) {s = 150;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA19_45 = input.LA(1);

                        s = -1;
                        if ( ((LA19_45>='\u0000' && LA19_45<='\uFFFF')) ) {s = 151;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA19_46 = input.LA(1);

                        s = -1;
                        if ( ((LA19_46>='\u0000' && LA19_46<='\uFFFF')) ) {s = 151;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA19_43 = input.LA(1);

                        s = -1;
                        if ( ((LA19_43>='0' && LA19_43<='9')) ) {s = 148;}

                        else if ( ((LA19_43>='\u0000' && LA19_43<='/')||(LA19_43>=':' && LA19_43<='\uFFFF')) ) {s = 149;}

                        else s = 50;

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