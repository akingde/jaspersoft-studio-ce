/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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
    public static final int CAST=58;
    public static final int PIVOT=70;
    public static final int RULE_ID=145;
    public static final int WEEK=95;
    public static final int RULE_JRNPARAM=134;
    public static final int RULE_DATE=139;
    public static final int RightParenthesis=121;
    public static final int ROW=106;
    public static final int RULE_ANY_OTHER=149;
    public static final int CASE=76;
    public static final int LeftParenthesis=120;
    public static final int DAY=103;
    public static final int NOT=87;
    public static final int Solidus=126;
    public static final int EXCEPT=49;
    public static final int EOF=-1;
    public static final int FullStop=125;
    public static final int MONTH=66;
    public static final int NOTEQUAL=35;
    public static final int NOTLIKE=34;
    public static final int GREATER=42;
    public static final int FULL=80;
    public static final int QUARTER=47;
    public static final int USING=74;
    public static final int CircumflexAccentEqualsSign=118;
    public static final int LessThanSign=127;
    public static final int INCLUDE=43;
    public static final int PRECEDING=27;
    public static final int LESS=85;
    public static final int HOUR_MICROSECOND=9;
    public static final int RULE_SIGNED_DOUBLE=138;
    public static final int NOT_1=88;
    public static final int NOTIN_1=53;
    public static final int LAST=83;
    public static final int SELECT=56;
    public static final int GROUPBY=33;
    public static final int DAY_MICROSECOND=10;
    public static final int SECOND=55;
    public static final int ASC=102;
    public static final int NULL=89;
    public static final int ELSE=78;
    public static final int ON=116;
    public static final int LessThanSignEqualsSign=111;
    public static final int DAY_MINUTE=21;
    public static final int LeftCurlyBracket=130;
    public static final int CURRENTROW=14;
    public static final int HOUR_SECOND=17;
    public static final int STRAIGHT_JOIN=12;
    public static final int X=110;
    public static final int RULE_ML_COMMENT=146;
    public static final int INTERSECT=26;
    public static final int RULE_STRING=143;
    public static final int ORDERSIBLINGSBY=8;
    public static final int VerticalLine=131;
    public static final int OR=117;
    public static final int END=104;
    public static final int FROM=79;
    public static final int DISTINCT=32;
    public static final int XML=108;
    public static final int BETWEEN_3=29;
    public static final int BETWEEN_2=37;
    public static final int BETWEEN_1=30;
    public static final int RightCurlyBracket=132;
    public static final int NOTIN=67;
    public static final int OVER=91;
    public static final int WHERE=75;
    public static final int VerticalLineVerticalLine=119;
    public static final int HyphenMinus=124;
    public static final int INNER=62;
    public static final int YEAR=97;
    public static final int RULE_UNSIGNED=136;
    public static final int MICROSECOND=19;
    public static final int LIMIT=64;
    public static final int ONLY=90;
    public static final int UNPIVOT=48;
    public static final int ISNULL=44;
    public static final int FOR=105;
    public static final int ORDERBY=36;
    public static final int RULE_STRING_=142;
    public static final int LessThanSignGreaterThanSign=112;
    public static final int AND=100;
    public static final int NOTEXISTS=23;
    public static final int GreaterThanSign=129;
    public static final int CROSS=59;
    public static final int SECOND_MICROSECOND=7;
    public static final int YEAR_MONTH=24;
    public static final int LESS_1=63;
    public static final int AS=114;
    public static final int DAY_HOUR=31;
    public static final int IN=115;
    public static final int THEN=94;
    public static final int FETCHFIRST=15;
    public static final int OFFSET=54;
    public static final int LEFT=84;
    public static final int SOME=93;
    public static final int ALL=99;
    public static final int EQUAL=60;
    public static final int RULE_TIME=140;
    public static final int RULE_TIMESTAMP=141;
    public static final int RULE_INT=137;
    public static final int RULE_DBNAME=144;
    public static final int EXISTS=50;
    public static final int MINUTE_SECOND=11;
    public static final int EXTRACT=41;
    public static final int WITHTIES=28;
    public static final int EXCLUDE=40;
    public static final int LIKE=86;
    public static final int ExclamationMarkEqualsSign=109;
    public static final int OUTER=69;
    public static final int PARTITIONBY=13;
    public static final int PERCENT=46;
    public static final int KW_FOLLOWING=25;
    public static final int UNBOUNDEDFOLLOWING=4;
    public static final int GREATER_1=38;
    public static final int MINUTE_MICROSECOND=6;
    public static final int VALUES=57;
    public static final int RANGE=71;
    public static final int RIGHT=72;
    public static final int HAVING=51;
    public static final int MINUS=65;
    public static final int HOUR=81;
    public static final int RULE_SL_COMMENT=147;
    public static final int JOIN=82;
    public static final int UNION=73;
    public static final int NOTBETWEEN=20;
    public static final int NULLS=68;
    public static final int ANY=101;
    public static final int PlusSign=122;
    public static final int RULE_STAR=135;
    public static final int DAY_SECOND=22;
    public static final int UNBOUNDEDPRECEDING=5;
    public static final int ISNOTNULL=18;
    public static final int WHEN=96;
    public static final int RULE_JRPARAM=133;
    public static final int ROWS=92;
    public static final int HOUR_MINUTE=16;
    public static final int GreaterThanSignEqualsSign=113;
    public static final int NATURAL=45;
    public static final int LeftParenthesisPlusSignRightParenthesis=98;
    public static final int DESC=77;
    public static final int RULE_WS=148;
    public static final int MINUTE=52;
    public static final int TOP=107;
    public static final int EqualsSign=128;
    public static final int Comma=123;
    public static final int BETWEEN=39;
    public static final int FIRST=61;

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

    // $ANTLR start "UNBOUNDEDFOLLOWING"
    public final void mUNBOUNDEDFOLLOWING() throws RecognitionException {
        try {
            int _type = UNBOUNDEDFOLLOWING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:19:20: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:19:22: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "UNBOUNDEDFOLLOWING"

    // $ANTLR start "UNBOUNDEDPRECEDING"
    public final void mUNBOUNDEDPRECEDING() throws RecognitionException {
        try {
            int _type = UNBOUNDEDPRECEDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:21:20: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:21:22: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ' ' ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
    // $ANTLR end "UNBOUNDEDPRECEDING"

    // $ANTLR start "MINUTE_MICROSECOND"
    public final void mMINUTE_MICROSECOND() throws RecognitionException {
        try {
            int _type = MINUTE_MICROSECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:23:20: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:23:22: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
            // InternalSqlLexer.g:25:20: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:25:22: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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

    // $ANTLR start "ORDERSIBLINGSBY"
    public final void mORDERSIBLINGSBY() throws RecognitionException {
        try {
            int _type = ORDERSIBLINGSBY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:27:17: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'S' | 's' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'S' | 's' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:27:19: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'S' | 's' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'S' | 's' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "ORDERSIBLINGSBY"

    // $ANTLR start "HOUR_MICROSECOND"
    public final void mHOUR_MICROSECOND() throws RecognitionException {
        try {
            int _type = HOUR_MICROSECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:29:18: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:29:20: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
            // InternalSqlLexer.g:31:17: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:31:19: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
            // InternalSqlLexer.g:33:15: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:33:17: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
            // InternalSqlLexer.g:35:15: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) '_' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:35:17: ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) '_' ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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

    // $ANTLR start "PARTITIONBY"
    public final void mPARTITIONBY() throws RecognitionException {
        try {
            int _type = PARTITIONBY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:37:13: ( ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:37:15: ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "PARTITIONBY"

    // $ANTLR start "CURRENTROW"
    public final void mCURRENTROW() throws RecognitionException {
        try {
            int _type = CURRENTROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:39:12: ( ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ' ' ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) )
            // InternalSqlLexer.g:39:14: ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ' ' ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' )
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
    // $ANTLR end "CURRENTROW"

    // $ANTLR start "FETCHFIRST"
    public final void mFETCHFIRST() throws RecognitionException {
        try {
            int _type = FETCHFIRST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:41:12: ( ( 'F' | 'f' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) ' ' ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:41:14: ( 'F' | 'f' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) ' ' ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' )
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
    // $ANTLR end "FETCHFIRST"

    // $ANTLR start "HOUR_MINUTE"
    public final void mHOUR_MINUTE() throws RecognitionException {
        try {
            int _type = HOUR_MINUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:43:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:43:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
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
            // InternalSqlLexer.g:45:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:45:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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

    // $ANTLR start "ISNOTNULL"
    public final void mISNOTNULL() throws RecognitionException {
        try {
            int _type = ISNOTNULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:47:11: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:47:13: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "ISNOTNULL"

    // $ANTLR start "MICROSECOND"
    public final void mMICROSECOND() throws RecognitionException {
        try {
            int _type = MICROSECOND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:49:13: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:49:15: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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

    // $ANTLR start "NOTBETWEEN"
    public final void mNOTBETWEEN() throws RecognitionException {
        try {
            int _type = NOTBETWEEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:51:12: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:51:14: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "NOTBETWEEN"

    // $ANTLR start "DAY_MINUTE"
    public final void mDAY_MINUTE() throws RecognitionException {
        try {
            int _type = DAY_MINUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:53:12: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:53:14: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
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
            // InternalSqlLexer.g:55:12: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:55:14: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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

    // $ANTLR start "NOTEXISTS"
    public final void mNOTEXISTS() throws RecognitionException {
        try {
            int _type = NOTEXISTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:57:11: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:57:13: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' )
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
    // $ANTLR end "NOTEXISTS"

    // $ANTLR start "YEAR_MONTH"
    public final void mYEAR_MONTH() throws RecognitionException {
        try {
            int _type = YEAR_MONTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:59:12: ( ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) )
            // InternalSqlLexer.g:59:14: ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) '_' ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' )
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

    // $ANTLR start "KW_FOLLOWING"
    public final void mKW_FOLLOWING() throws RecognitionException {
        try {
            int _type = KW_FOLLOWING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:61:14: ( ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:61:16: ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
            // InternalSqlLexer.g:63:11: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:63:13: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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

    // $ANTLR start "PRECEDING"
    public final void mPRECEDING() throws RecognitionException {
        try {
            int _type = PRECEDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:65:11: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:65:13: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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

    // $ANTLR start "WITHTIES"
    public final void mWITHTIES() throws RecognitionException {
        try {
            int _type = WITHTIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:67:10: ( ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'H' | 'h' ) ' ' ( 'T' | 't' ) ( 'I' | 'i' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:67:12: ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'H' | 'h' ) ' ' ( 'T' | 't' ) ( 'I' | 'i' ) ( 'E' | 'e' ) ( 'S' | 's' )
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
    // $ANTLR end "WITHTIES"

    // $ANTLR start "BETWEEN_3"
    public final void mBETWEEN_3() throws RecognitionException {
        try {
            int _type = BETWEEN_3;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:69:11: ( '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // InternalSqlLexer.g:69:13: '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
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
    // $ANTLR end "BETWEEN_3"

    // $ANTLR start "BETWEEN_1"
    public final void mBETWEEN_1() throws RecognitionException {
        try {
            int _type = BETWEEN_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:71:11: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']' )
            // InternalSqlLexer.g:71:13: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ']'
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
    // $ANTLR end "BETWEEN_1"

    // $ANTLR start "DAY_HOUR"
    public final void mDAY_HOUR() throws RecognitionException {
        try {
            int _type = DAY_HOUR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:73:10: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:73:12: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) '_' ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' )
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
            // InternalSqlLexer.g:75:10: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:75:12: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' )
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

    // $ANTLR start "GROUPBY"
    public final void mGROUPBY() throws RecognitionException {
        try {
            int _type = GROUPBY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:77:9: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:77:11: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' | 'p' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "GROUPBY"

    // $ANTLR start "NOTLIKE"
    public final void mNOTLIKE() throws RecognitionException {
        try {
            int _type = NOTLIKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:79:9: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:79:11: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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
    // $ANTLR end "NOTLIKE"

    // $ANTLR start "NOTEQUAL"
    public final void mNOTEQUAL() throws RecognitionException {
        try {
            int _type = NOTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:81:10: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:81:12: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
    // $ANTLR end "NOTEQUAL"

    // $ANTLR start "ORDERBY"
    public final void mORDERBY() throws RecognitionException {
        try {
            int _type = ORDERBY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:83:9: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:83:11: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ' ' ( 'B' | 'b' ) ( 'Y' | 'y' )
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
    // $ANTLR end "ORDERBY"

    // $ANTLR start "BETWEEN_2"
    public final void mBETWEEN_2() throws RecognitionException {
        try {
            int _type = BETWEEN_2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:85:11: ( '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:85:13: '[' ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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
    // $ANTLR end "BETWEEN_2"

    // $ANTLR start "GREATER_1"
    public final void mGREATER_1() throws RecognitionException {
        try {
            int _type = GREATER_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:87:11: ( '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:87:13: '[' ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "GREATER_1"

    // $ANTLR start "BETWEEN"
    public final void mBETWEEN() throws RecognitionException {
        try {
            int _type = BETWEEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:89:9: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:89:11: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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

    // $ANTLR start "EXCLUDE"
    public final void mEXCLUDE() throws RecognitionException {
        try {
            int _type = EXCLUDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:91:9: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:91:11: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' )
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
            // InternalSqlLexer.g:93:9: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:93:11: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' )
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

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:95:9: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:95:11: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
    // $ANTLR end "GREATER"

    // $ANTLR start "INCLUDE"
    public final void mINCLUDE() throws RecognitionException {
        try {
            int _type = INCLUDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:97:9: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:97:11: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'D' | 'd' ) ( 'E' | 'e' )
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

    // $ANTLR start "ISNULL"
    public final void mISNULL() throws RecognitionException {
        try {
            int _type = ISNULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:99:8: ( ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:99:10: ( 'I' | 'i' ) ( 'S' | 's' ) ' ' ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
    // $ANTLR end "ISNULL"

    // $ANTLR start "NATURAL"
    public final void mNATURAL() throws RecognitionException {
        try {
            int _type = NATURAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:101:9: ( ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:101:11: ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
            // InternalSqlLexer.g:103:9: ( ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:103:11: ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:105:9: ( ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:105:11: ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
            // InternalSqlLexer.g:107:9: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:107:11: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:109:8: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:109:10: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:111:8: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:111:10: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'S' | 's' )
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
            // InternalSqlLexer.g:113:8: ( ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:113:10: ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
            // InternalSqlLexer.g:115:8: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:115:10: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' )
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

    // $ANTLR start "NOTIN_1"
    public final void mNOTIN_1() throws RecognitionException {
        try {
            int _type = NOTIN_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:117:9: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:117:11: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "NOTIN_1"

    // $ANTLR start "OFFSET"
    public final void mOFFSET() throws RecognitionException {
        try {
            int _type = OFFSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:119:8: ( ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:119:10: ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:121:8: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:121:10: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
            // InternalSqlLexer.g:123:8: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:123:10: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:125:8: ( ( 'V' | 'v' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:125:10: ( 'V' | 'v' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' )
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

    // $ANTLR start "CAST"
    public final void mCAST() throws RecognitionException {
        try {
            int _type = CAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:127:6: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) '(' )
            // InternalSqlLexer.g:127:8: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) '('
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
            // InternalSqlLexer.g:129:7: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:129:9: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'S' | 's' )
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

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:131:7: ( ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:131:9: ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
    // $ANTLR end "EQUAL"

    // $ANTLR start "FIRST"
    public final void mFIRST() throws RecognitionException {
        try {
            int _type = FIRST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:133:7: ( ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:133:9: ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'S' | 's' ) ( 'T' | 't' )
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

    // $ANTLR start "INNER"
    public final void mINNER() throws RecognitionException {
        try {
            int _type = INNER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:135:7: ( ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:135:9: ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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

    // $ANTLR start "LESS_1"
    public final void mLESS_1() throws RecognitionException {
        try {
            int _type = LESS_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:137:8: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']' )
            // InternalSqlLexer.g:137:10: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) ']'
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
    // $ANTLR end "LESS_1"

    // $ANTLR start "LIMIT"
    public final void mLIMIT() throws RecognitionException {
        try {
            int _type = LIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:139:7: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:139:9: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:141:7: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:141:9: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'S' | 's' )
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
            // InternalSqlLexer.g:143:7: ( ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) )
            // InternalSqlLexer.g:143:9: ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' )
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

    // $ANTLR start "NOTIN"
    public final void mNOTIN() throws RecognitionException {
        try {
            int _type = NOTIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:145:7: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:145:9: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
    // $ANTLR end "NOTIN"

    // $ANTLR start "NULLS"
    public final void mNULLS() throws RecognitionException {
        try {
            int _type = NULLS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:147:7: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:147:9: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'S' | 's' )
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

    // $ANTLR start "OUTER"
    public final void mOUTER() throws RecognitionException {
        try {
            int _type = OUTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:149:7: ( ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:149:9: ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
            // InternalSqlLexer.g:151:7: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:151:9: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'V' | 'v' ) ( 'O' | 'o' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:153:7: ( ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:153:9: ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' | 'e' )
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
            // InternalSqlLexer.g:155:7: ( ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:155:9: ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'G' | 'g' ) ( 'H' | 'h' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:157:7: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:157:9: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
            // InternalSqlLexer.g:159:7: ( ( 'U' | 'u' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
            // InternalSqlLexer.g:159:9: ( 'U' | 'u' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
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
            // InternalSqlLexer.g:161:7: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:161:9: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' )
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

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:163:6: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:163:8: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' )
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
            // InternalSqlLexer.g:165:6: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // InternalSqlLexer.g:165:8: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
            // InternalSqlLexer.g:167:6: ( ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:167:8: ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' )
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
            // InternalSqlLexer.g:169:6: ( ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' ) )
            // InternalSqlLexer.g:169:8: ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' )
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
            // InternalSqlLexer.g:171:6: ( ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:171:8: ( 'F' | 'f' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
            // InternalSqlLexer.g:173:6: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:173:8: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'R' | 'r' )
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
            // InternalSqlLexer.g:175:6: ( ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:175:8: ( 'J' | 'j' ) ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'N' | 'n' )
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
            // InternalSqlLexer.g:177:6: ( ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:177:8: ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'T' | 't' )
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
            // InternalSqlLexer.g:179:6: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' ) )
            // InternalSqlLexer.g:179:8: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'T' | 't' )
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

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:181:6: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:181:8: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' )
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
    // $ANTLR end "LESS"

    // $ANTLR start "LIKE"
    public final void mLIKE() throws RecognitionException {
        try {
            int _type = LIKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:183:6: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:183:8: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
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

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:185:5: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) '\\n' )
            // InternalSqlLexer.g:185:7: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) '\\n'
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
    // $ANTLR end "NOT"

    // $ANTLR start "NOT_1"
    public final void mNOT_1() throws RecognitionException {
        try {
            int _type = NOT_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:187:7: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' ' )
            // InternalSqlLexer.g:187:9: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) ' '
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
    // $ANTLR end "NOT_1"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:189:6: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:189:8: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
            // InternalSqlLexer.g:191:6: ( ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:191:8: ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'Y' | 'y' )
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
            // InternalSqlLexer.g:193:6: ( ( 'O' | 'o' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:193:8: ( 'O' | 'o' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' )
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
            // InternalSqlLexer.g:195:6: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:195:8: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) ( 'S' | 's' )
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
            // InternalSqlLexer.g:197:6: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) )
            // InternalSqlLexer.g:197:8: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' )
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
            // InternalSqlLexer.g:199:6: ( ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:199:8: ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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

    // $ANTLR start "WEEK"
    public final void mWEEK() throws RecognitionException {
        try {
            int _type = WEEK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:201:6: ( ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'K' | 'k' ) )
            // InternalSqlLexer.g:201:8: ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'K' | 'k' )
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
            // InternalSqlLexer.g:203:6: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:203:8: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'N' | 'n' )
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

    // $ANTLR start "YEAR"
    public final void mYEAR() throws RecognitionException {
        try {
            int _type = YEAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:205:6: ( ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:205:8: ( 'Y' | 'y' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' )
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
            // InternalSqlLexer.g:207:41: ( '(' '+' ')' )
            // InternalSqlLexer.g:207:43: '(' '+' ')'
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
            // InternalSqlLexer.g:209:5: ( ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:209:7: ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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
            // InternalSqlLexer.g:211:5: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:211:7: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
            // InternalSqlLexer.g:213:5: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:213:7: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'Y' | 'y' )
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
            // InternalSqlLexer.g:215:5: ( ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
            // InternalSqlLexer.g:215:7: ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' )
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
            // InternalSqlLexer.g:217:5: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) )
            // InternalSqlLexer.g:217:7: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'Y' | 'y' )
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
            // InternalSqlLexer.g:219:5: ( ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'D' | 'd' ) )
            // InternalSqlLexer.g:219:7: ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'D' | 'd' )
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
            // InternalSqlLexer.g:221:5: ( ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:221:7: ( 'F' | 'f' ) ( 'O' | 'o' ) ( 'R' | 'r' )
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

    // $ANTLR start "ROW"
    public final void mROW() throws RecognitionException {
        try {
            int _type = ROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:223:5: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' ) )
            // InternalSqlLexer.g:223:7: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'W' | 'w' )
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
            // InternalSqlLexer.g:225:5: ( ( 'T' | 't' ) ( 'O' | 'o' ) ( 'P' | 'p' ) )
            // InternalSqlLexer.g:225:7: ( 'T' | 't' ) ( 'O' | 'o' ) ( 'P' | 'p' )
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
            // InternalSqlLexer.g:227:5: ( ( 'X' | 'x' ) ( 'M' | 'm' ) ( 'L' | 'l' ) )
            // InternalSqlLexer.g:227:7: ( 'X' | 'x' ) ( 'M' | 'm' ) ( 'L' | 'l' )
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

    // $ANTLR start "ExclamationMarkEqualsSign"
    public final void mExclamationMarkEqualsSign() throws RecognitionException {
        try {
            int _type = ExclamationMarkEqualsSign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:229:27: ( '!' '=' )
            // InternalSqlLexer.g:229:29: '!' '='
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
            // InternalSqlLexer.g:231:3: ( '$' ( 'X' | 'x' ) )
            // InternalSqlLexer.g:231:5: '$' ( 'X' | 'x' )
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
            // InternalSqlLexer.g:233:24: ( '<' '=' )
            // InternalSqlLexer.g:233:26: '<' '='
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
            // InternalSqlLexer.g:235:29: ( '<' '>' )
            // InternalSqlLexer.g:235:31: '<' '>'
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
            // InternalSqlLexer.g:237:27: ( '>' '=' )
            // InternalSqlLexer.g:237:29: '>' '='
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
            // InternalSqlLexer.g:239:4: ( ( 'A' | 'a' ) ( 'S' | 's' ) )
            // InternalSqlLexer.g:239:6: ( 'A' | 'a' ) ( 'S' | 's' )
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

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:241:4: ( ( 'I' | 'i' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:241:6: ( 'I' | 'i' ) ( 'N' | 'n' )
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

    // $ANTLR start "ON"
    public final void mON() throws RecognitionException {
        try {
            int _type = ON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:243:4: ( ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // InternalSqlLexer.g:243:6: ( 'O' | 'o' ) ( 'N' | 'n' )
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
            // InternalSqlLexer.g:245:4: ( ( 'O' | 'o' ) ( 'R' | 'r' ) )
            // InternalSqlLexer.g:245:6: ( 'O' | 'o' ) ( 'R' | 'r' )
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
            // InternalSqlLexer.g:247:28: ( '^' '=' )
            // InternalSqlLexer.g:247:30: '^' '='
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
            // InternalSqlLexer.g:249:26: ( '|' '|' )
            // InternalSqlLexer.g:249:28: '|' '|'
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
            // InternalSqlLexer.g:251:17: ( '(' )
            // InternalSqlLexer.g:251:19: '('
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
            // InternalSqlLexer.g:253:18: ( ')' )
            // InternalSqlLexer.g:253:20: ')'
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
            // InternalSqlLexer.g:255:10: ( '+' )
            // InternalSqlLexer.g:255:12: '+'
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
            // InternalSqlLexer.g:257:7: ( ',' )
            // InternalSqlLexer.g:257:9: ','
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
            // InternalSqlLexer.g:259:13: ( '-' )
            // InternalSqlLexer.g:259:15: '-'
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
            // InternalSqlLexer.g:261:10: ( '.' )
            // InternalSqlLexer.g:261:12: '.'
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
            // InternalSqlLexer.g:263:9: ( '/' )
            // InternalSqlLexer.g:263:11: '/'
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
            // InternalSqlLexer.g:265:14: ( '<' )
            // InternalSqlLexer.g:265:16: '<'
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
            // InternalSqlLexer.g:267:12: ( '=' )
            // InternalSqlLexer.g:267:14: '='
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
            // InternalSqlLexer.g:269:17: ( '>' )
            // InternalSqlLexer.g:269:19: '>'
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

    // $ANTLR start "LeftCurlyBracket"
    public final void mLeftCurlyBracket() throws RecognitionException {
        try {
            int _type = LeftCurlyBracket;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:271:18: ( '{' )
            // InternalSqlLexer.g:271:20: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LeftCurlyBracket"

    // $ANTLR start "VerticalLine"
    public final void mVerticalLine() throws RecognitionException {
        try {
            int _type = VerticalLine;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSqlLexer.g:273:14: ( '|' )
            // InternalSqlLexer.g:273:16: '|'
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
            // InternalSqlLexer.g:275:19: ( '}' )
            // InternalSqlLexer.g:275:21: '}'
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
            // InternalSqlLexer.g:279:14: ( '$P{' ( options {greedy=false; } : . )* '}' )
            // InternalSqlLexer.g:279:16: '$P{' ( options {greedy=false; } : . )* '}'
            {
            match("$P{"); 

            // InternalSqlLexer.g:279:22: ( options {greedy=false; } : . )*
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
            	    // InternalSqlLexer.g:279:50: .
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
            // InternalSqlLexer.g:281:15: ( '$P!{' ( options {greedy=false; } : . )* '}' )
            // InternalSqlLexer.g:281:17: '$P!{' ( options {greedy=false; } : . )* '}'
            {
            match("$P!{"); 

            // InternalSqlLexer.g:281:24: ( options {greedy=false; } : . )*
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
            	    // InternalSqlLexer.g:281:52: .
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
            // InternalSqlLexer.g:283:11: ( '*' )
            // InternalSqlLexer.g:283:13: '*'
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
            // InternalSqlLexer.g:285:15: ( ( '0' .. '9' )+ )
            // InternalSqlLexer.g:285:17: ( '0' .. '9' )+
            {
            // InternalSqlLexer.g:285:17: ( '0' .. '9' )+
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
            	    // InternalSqlLexer.g:285:18: '0' .. '9'
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
            // InternalSqlLexer.g:287:10: ( ( '-' )? RULE_UNSIGNED )
            // InternalSqlLexer.g:287:12: ( '-' )? RULE_UNSIGNED
            {
            // InternalSqlLexer.g:287:12: ( '-' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalSqlLexer.g:287:12: '-'
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
            // InternalSqlLexer.g:289:20: ( ( '-' )? RULE_UNSIGNED ( '.' RULE_UNSIGNED )? )
            // InternalSqlLexer.g:289:22: ( '-' )? RULE_UNSIGNED ( '.' RULE_UNSIGNED )?
            {
            // InternalSqlLexer.g:289:22: ( '-' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='-') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalSqlLexer.g:289:22: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            mRULE_UNSIGNED(); 
            // InternalSqlLexer.g:289:41: ( '.' RULE_UNSIGNED )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalSqlLexer.g:289:42: '.' RULE_UNSIGNED
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
            // InternalSqlLexer.g:291:16: ( RULE_DATE ' ' RULE_TIME )
            // InternalSqlLexer.g:291:18: RULE_DATE ' ' RULE_TIME
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
            // InternalSqlLexer.g:293:11: ( '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\'' )
            // InternalSqlLexer.g:293:13: '\\'' '0' .. '9' '0' .. '9' '0' .. '9' '0' .. '9' '-' '0' .. '1' '0' .. '9' '-' '0' .. '3' '0' .. '9' '\\''
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
            // InternalSqlLexer.g:295:11: ( '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\'' )
            // InternalSqlLexer.g:295:13: '\\'' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' ':' '0' .. '9' '0' .. '9' '.' '0' .. '9' '0' .. '9' '0' .. '9' '\\''
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
            // InternalSqlLexer.g:297:14: ( '\\'' ( '\\'\\'' | '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            // InternalSqlLexer.g:297:16: '\\'' ( '\\'\\'' | '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
            {
            match('\''); 
            // InternalSqlLexer.g:297:21: ( '\\'\\'' | '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
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
            	    // InternalSqlLexer.g:297:22: '\\'\\''
            	    {
            	    match("''"); 


            	    }
            	    break;
            	case 2 :
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
            	    break;
            	case 3 :
            	    // InternalSqlLexer.g:297:74: ~ ( ( '\\\\' | '\\'' ) )
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
            // InternalSqlLexer.g:299:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // InternalSqlLexer.g:299:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // InternalSqlLexer.g:299:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
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
            	    // InternalSqlLexer.g:299:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
            	    // InternalSqlLexer.g:299:65: ~ ( ( '\\\\' | '\"' ) )
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
            // InternalSqlLexer.g:301:13: ( ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' ) )
            // InternalSqlLexer.g:301:15: ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' )
            {
            // InternalSqlLexer.g:301:15: ( '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`' | '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']' )
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
                    // InternalSqlLexer.g:301:16: '`' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )* '`'
                    {
                    match('`'); 
                    // InternalSqlLexer.g:301:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '`' ) ) )*
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
                    	    // InternalSqlLexer.g:301:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // InternalSqlLexer.g:301:66: ~ ( ( '\\\\' | '`' ) )
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
                    // InternalSqlLexer.g:301:86: '[' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )* ']'
                    {
                    match('['); 
                    // InternalSqlLexer.g:301:90: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | ']' ) ) )*
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
                    	    // InternalSqlLexer.g:301:91: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // InternalSqlLexer.g:301:136: ~ ( ( '\\\\' | ']' ) )
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
            // InternalSqlLexer.g:303:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' ) ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )* )
            // InternalSqlLexer.g:303:11: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' ) ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )*
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u024F')||(input.LA(1)>='\u0410' && input.LA(1)<='\u044F')||(input.LA(1)>='\u3041' && input.LA(1)<='\u30FF')||(input.LA(1)>='\u31F0' && input.LA(1)<='\u31FF')||(input.LA(1)>='\u3400' && input.LA(1)<='\u4DBF')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF')||input.LA(1)=='\uFF3F' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalSqlLexer.g:303:276: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00FF' | '\\u0100' .. '\\u017F' | '\\u0180' .. '\\u024F' | '\\u0410' .. '\\u044F' | '_' | '-' | '$' | '\\u3041' .. '\\u309F' | '\\u30A0' .. '\\u30FF' | '\\u31F0' .. '\\u31FF' | '\\u4E00' .. '\\u9FFF' | '\\u6B74' .. '\\u3059' | '\\u30A2' .. '\\u30F3' | '\\uF900' .. '\\uFAFF' | '\\u3400' .. '\\u4DBF' | '\\uFF3F' | '0' .. '9' )*
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
            // InternalSqlLexer.g:305:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalSqlLexer.g:305:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalSqlLexer.g:305:24: ( options {greedy=false; } : . )*
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
            	    // InternalSqlLexer.g:305:52: .
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
            // InternalSqlLexer.g:307:17: ( ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalSqlLexer.g:307:19: ( '--' | '#' | '//' ) (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            // InternalSqlLexer.g:307:19: ( '--' | '#' | '//' )
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
                    // InternalSqlLexer.g:307:20: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // InternalSqlLexer.g:307:25: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 3 :
                    // InternalSqlLexer.g:307:29: '//'
                    {
                    match("//"); 


                    }
                    break;

            }

            // InternalSqlLexer.g:307:35: (~ ( ( '\\n' | '\\r' ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalSqlLexer.g:307:35: ~ ( ( '\\n' | '\\r' ) )
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

            // InternalSqlLexer.g:307:51: ( ( '\\r' )? '\\n' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='\n'||LA17_0=='\r') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalSqlLexer.g:307:52: ( '\\r' )? '\\n'
                    {
                    // InternalSqlLexer.g:307:52: ( '\\r' )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='\r') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // InternalSqlLexer.g:307:52: '\\r'
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
            // InternalSqlLexer.g:309:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalSqlLexer.g:309:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalSqlLexer.g:309:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            // InternalSqlLexer.g:311:16: ( . )
            // InternalSqlLexer.g:311:18: .
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
        // InternalSqlLexer.g:1:8: ( UNBOUNDEDFOLLOWING | UNBOUNDEDPRECEDING | MINUTE_MICROSECOND | SECOND_MICROSECOND | ORDERSIBLINGSBY | HOUR_MICROSECOND | DAY_MICROSECOND | MINUTE_SECOND | STRAIGHT_JOIN | PARTITIONBY | CURRENTROW | FETCHFIRST | HOUR_MINUTE | HOUR_SECOND | ISNOTNULL | MICROSECOND | NOTBETWEEN | DAY_MINUTE | DAY_SECOND | NOTEXISTS | YEAR_MONTH | KW_FOLLOWING | INTERSECT | PRECEDING | WITHTIES | BETWEEN_3 | BETWEEN_1 | DAY_HOUR | DISTINCT | GROUPBY | NOTLIKE | NOTEQUAL | ORDERBY | BETWEEN_2 | GREATER_1 | BETWEEN | EXCLUDE | EXTRACT | GREATER | INCLUDE | ISNULL | NATURAL | PERCENT | QUARTER | UNPIVOT | EXCEPT | EXISTS | HAVING | MINUTE | NOTIN_1 | OFFSET | SECOND | SELECT | VALUES | CAST | CROSS | EQUAL | FIRST | INNER | LESS_1 | LIMIT | MINUS | MONTH | NOTIN | NULLS | OUTER | PIVOT | RANGE | RIGHT | UNION | USING | WHERE | CASE | DESC | ELSE | FROM | FULL | HOUR | JOIN | LAST | LEFT | LESS | LIKE | NOT | NOT_1 | NULL | ONLY | OVER | ROWS | SOME | THEN | WEEK | WHEN | YEAR | LeftParenthesisPlusSignRightParenthesis | ALL | AND | ANY | ASC | DAY | END | FOR | ROW | TOP | XML | ExclamationMarkEqualsSign | X | LessThanSignEqualsSign | LessThanSignGreaterThanSign | GreaterThanSignEqualsSign | AS | IN | ON | OR | CircumflexAccentEqualsSign | VerticalLineVerticalLine | LeftParenthesis | RightParenthesis | PlusSign | Comma | HyphenMinus | FullStop | Solidus | LessThanSign | EqualsSign | GreaterThanSign | LeftCurlyBracket | VerticalLine | RightCurlyBracket | RULE_JRPARAM | RULE_JRNPARAM | RULE_STAR | RULE_UNSIGNED | RULE_INT | RULE_SIGNED_DOUBLE | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_STRING_ | RULE_STRING | RULE_DBNAME | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt19=146;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // InternalSqlLexer.g:1:10: UNBOUNDEDFOLLOWING
                {
                mUNBOUNDEDFOLLOWING(); 

                }
                break;
            case 2 :
                // InternalSqlLexer.g:1:29: UNBOUNDEDPRECEDING
                {
                mUNBOUNDEDPRECEDING(); 

                }
                break;
            case 3 :
                // InternalSqlLexer.g:1:48: MINUTE_MICROSECOND
                {
                mMINUTE_MICROSECOND(); 

                }
                break;
            case 4 :
                // InternalSqlLexer.g:1:67: SECOND_MICROSECOND
                {
                mSECOND_MICROSECOND(); 

                }
                break;
            case 5 :
                // InternalSqlLexer.g:1:86: ORDERSIBLINGSBY
                {
                mORDERSIBLINGSBY(); 

                }
                break;
            case 6 :
                // InternalSqlLexer.g:1:102: HOUR_MICROSECOND
                {
                mHOUR_MICROSECOND(); 

                }
                break;
            case 7 :
                // InternalSqlLexer.g:1:119: DAY_MICROSECOND
                {
                mDAY_MICROSECOND(); 

                }
                break;
            case 8 :
                // InternalSqlLexer.g:1:135: MINUTE_SECOND
                {
                mMINUTE_SECOND(); 

                }
                break;
            case 9 :
                // InternalSqlLexer.g:1:149: STRAIGHT_JOIN
                {
                mSTRAIGHT_JOIN(); 

                }
                break;
            case 10 :
                // InternalSqlLexer.g:1:163: PARTITIONBY
                {
                mPARTITIONBY(); 

                }
                break;
            case 11 :
                // InternalSqlLexer.g:1:175: CURRENTROW
                {
                mCURRENTROW(); 

                }
                break;
            case 12 :
                // InternalSqlLexer.g:1:186: FETCHFIRST
                {
                mFETCHFIRST(); 

                }
                break;
            case 13 :
                // InternalSqlLexer.g:1:197: HOUR_MINUTE
                {
                mHOUR_MINUTE(); 

                }
                break;
            case 14 :
                // InternalSqlLexer.g:1:209: HOUR_SECOND
                {
                mHOUR_SECOND(); 

                }
                break;
            case 15 :
                // InternalSqlLexer.g:1:221: ISNOTNULL
                {
                mISNOTNULL(); 

                }
                break;
            case 16 :
                // InternalSqlLexer.g:1:231: MICROSECOND
                {
                mMICROSECOND(); 

                }
                break;
            case 17 :
                // InternalSqlLexer.g:1:243: NOTBETWEEN
                {
                mNOTBETWEEN(); 

                }
                break;
            case 18 :
                // InternalSqlLexer.g:1:254: DAY_MINUTE
                {
                mDAY_MINUTE(); 

                }
                break;
            case 19 :
                // InternalSqlLexer.g:1:265: DAY_SECOND
                {
                mDAY_SECOND(); 

                }
                break;
            case 20 :
                // InternalSqlLexer.g:1:276: NOTEXISTS
                {
                mNOTEXISTS(); 

                }
                break;
            case 21 :
                // InternalSqlLexer.g:1:286: YEAR_MONTH
                {
                mYEAR_MONTH(); 

                }
                break;
            case 22 :
                // InternalSqlLexer.g:1:297: KW_FOLLOWING
                {
                mKW_FOLLOWING(); 

                }
                break;
            case 23 :
                // InternalSqlLexer.g:1:310: INTERSECT
                {
                mINTERSECT(); 

                }
                break;
            case 24 :
                // InternalSqlLexer.g:1:320: PRECEDING
                {
                mPRECEDING(); 

                }
                break;
            case 25 :
                // InternalSqlLexer.g:1:330: WITHTIES
                {
                mWITHTIES(); 

                }
                break;
            case 26 :
                // InternalSqlLexer.g:1:339: BETWEEN_3
                {
                mBETWEEN_3(); 

                }
                break;
            case 27 :
                // InternalSqlLexer.g:1:349: BETWEEN_1
                {
                mBETWEEN_1(); 

                }
                break;
            case 28 :
                // InternalSqlLexer.g:1:359: DAY_HOUR
                {
                mDAY_HOUR(); 

                }
                break;
            case 29 :
                // InternalSqlLexer.g:1:368: DISTINCT
                {
                mDISTINCT(); 

                }
                break;
            case 30 :
                // InternalSqlLexer.g:1:377: GROUPBY
                {
                mGROUPBY(); 

                }
                break;
            case 31 :
                // InternalSqlLexer.g:1:385: NOTLIKE
                {
                mNOTLIKE(); 

                }
                break;
            case 32 :
                // InternalSqlLexer.g:1:393: NOTEQUAL
                {
                mNOTEQUAL(); 

                }
                break;
            case 33 :
                // InternalSqlLexer.g:1:402: ORDERBY
                {
                mORDERBY(); 

                }
                break;
            case 34 :
                // InternalSqlLexer.g:1:410: BETWEEN_2
                {
                mBETWEEN_2(); 

                }
                break;
            case 35 :
                // InternalSqlLexer.g:1:420: GREATER_1
                {
                mGREATER_1(); 

                }
                break;
            case 36 :
                // InternalSqlLexer.g:1:430: BETWEEN
                {
                mBETWEEN(); 

                }
                break;
            case 37 :
                // InternalSqlLexer.g:1:438: EXCLUDE
                {
                mEXCLUDE(); 

                }
                break;
            case 38 :
                // InternalSqlLexer.g:1:446: EXTRACT
                {
                mEXTRACT(); 

                }
                break;
            case 39 :
                // InternalSqlLexer.g:1:454: GREATER
                {
                mGREATER(); 

                }
                break;
            case 40 :
                // InternalSqlLexer.g:1:462: INCLUDE
                {
                mINCLUDE(); 

                }
                break;
            case 41 :
                // InternalSqlLexer.g:1:470: ISNULL
                {
                mISNULL(); 

                }
                break;
            case 42 :
                // InternalSqlLexer.g:1:477: NATURAL
                {
                mNATURAL(); 

                }
                break;
            case 43 :
                // InternalSqlLexer.g:1:485: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 44 :
                // InternalSqlLexer.g:1:493: QUARTER
                {
                mQUARTER(); 

                }
                break;
            case 45 :
                // InternalSqlLexer.g:1:501: UNPIVOT
                {
                mUNPIVOT(); 

                }
                break;
            case 46 :
                // InternalSqlLexer.g:1:509: EXCEPT
                {
                mEXCEPT(); 

                }
                break;
            case 47 :
                // InternalSqlLexer.g:1:516: EXISTS
                {
                mEXISTS(); 

                }
                break;
            case 48 :
                // InternalSqlLexer.g:1:523: HAVING
                {
                mHAVING(); 

                }
                break;
            case 49 :
                // InternalSqlLexer.g:1:530: MINUTE
                {
                mMINUTE(); 

                }
                break;
            case 50 :
                // InternalSqlLexer.g:1:537: NOTIN_1
                {
                mNOTIN_1(); 

                }
                break;
            case 51 :
                // InternalSqlLexer.g:1:545: OFFSET
                {
                mOFFSET(); 

                }
                break;
            case 52 :
                // InternalSqlLexer.g:1:552: SECOND
                {
                mSECOND(); 

                }
                break;
            case 53 :
                // InternalSqlLexer.g:1:559: SELECT
                {
                mSELECT(); 

                }
                break;
            case 54 :
                // InternalSqlLexer.g:1:566: VALUES
                {
                mVALUES(); 

                }
                break;
            case 55 :
                // InternalSqlLexer.g:1:573: CAST
                {
                mCAST(); 

                }
                break;
            case 56 :
                // InternalSqlLexer.g:1:578: CROSS
                {
                mCROSS(); 

                }
                break;
            case 57 :
                // InternalSqlLexer.g:1:584: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 58 :
                // InternalSqlLexer.g:1:590: FIRST
                {
                mFIRST(); 

                }
                break;
            case 59 :
                // InternalSqlLexer.g:1:596: INNER
                {
                mINNER(); 

                }
                break;
            case 60 :
                // InternalSqlLexer.g:1:602: LESS_1
                {
                mLESS_1(); 

                }
                break;
            case 61 :
                // InternalSqlLexer.g:1:609: LIMIT
                {
                mLIMIT(); 

                }
                break;
            case 62 :
                // InternalSqlLexer.g:1:615: MINUS
                {
                mMINUS(); 

                }
                break;
            case 63 :
                // InternalSqlLexer.g:1:621: MONTH
                {
                mMONTH(); 

                }
                break;
            case 64 :
                // InternalSqlLexer.g:1:627: NOTIN
                {
                mNOTIN(); 

                }
                break;
            case 65 :
                // InternalSqlLexer.g:1:633: NULLS
                {
                mNULLS(); 

                }
                break;
            case 66 :
                // InternalSqlLexer.g:1:639: OUTER
                {
                mOUTER(); 

                }
                break;
            case 67 :
                // InternalSqlLexer.g:1:645: PIVOT
                {
                mPIVOT(); 

                }
                break;
            case 68 :
                // InternalSqlLexer.g:1:651: RANGE
                {
                mRANGE(); 

                }
                break;
            case 69 :
                // InternalSqlLexer.g:1:657: RIGHT
                {
                mRIGHT(); 

                }
                break;
            case 70 :
                // InternalSqlLexer.g:1:663: UNION
                {
                mUNION(); 

                }
                break;
            case 71 :
                // InternalSqlLexer.g:1:669: USING
                {
                mUSING(); 

                }
                break;
            case 72 :
                // InternalSqlLexer.g:1:675: WHERE
                {
                mWHERE(); 

                }
                break;
            case 73 :
                // InternalSqlLexer.g:1:681: CASE
                {
                mCASE(); 

                }
                break;
            case 74 :
                // InternalSqlLexer.g:1:686: DESC
                {
                mDESC(); 

                }
                break;
            case 75 :
                // InternalSqlLexer.g:1:691: ELSE
                {
                mELSE(); 

                }
                break;
            case 76 :
                // InternalSqlLexer.g:1:696: FROM
                {
                mFROM(); 

                }
                break;
            case 77 :
                // InternalSqlLexer.g:1:701: FULL
                {
                mFULL(); 

                }
                break;
            case 78 :
                // InternalSqlLexer.g:1:706: HOUR
                {
                mHOUR(); 

                }
                break;
            case 79 :
                // InternalSqlLexer.g:1:711: JOIN
                {
                mJOIN(); 

                }
                break;
            case 80 :
                // InternalSqlLexer.g:1:716: LAST
                {
                mLAST(); 

                }
                break;
            case 81 :
                // InternalSqlLexer.g:1:721: LEFT
                {
                mLEFT(); 

                }
                break;
            case 82 :
                // InternalSqlLexer.g:1:726: LESS
                {
                mLESS(); 

                }
                break;
            case 83 :
                // InternalSqlLexer.g:1:731: LIKE
                {
                mLIKE(); 

                }
                break;
            case 84 :
                // InternalSqlLexer.g:1:736: NOT
                {
                mNOT(); 

                }
                break;
            case 85 :
                // InternalSqlLexer.g:1:740: NOT_1
                {
                mNOT_1(); 

                }
                break;
            case 86 :
                // InternalSqlLexer.g:1:746: NULL
                {
                mNULL(); 

                }
                break;
            case 87 :
                // InternalSqlLexer.g:1:751: ONLY
                {
                mONLY(); 

                }
                break;
            case 88 :
                // InternalSqlLexer.g:1:756: OVER
                {
                mOVER(); 

                }
                break;
            case 89 :
                // InternalSqlLexer.g:1:761: ROWS
                {
                mROWS(); 

                }
                break;
            case 90 :
                // InternalSqlLexer.g:1:766: SOME
                {
                mSOME(); 

                }
                break;
            case 91 :
                // InternalSqlLexer.g:1:771: THEN
                {
                mTHEN(); 

                }
                break;
            case 92 :
                // InternalSqlLexer.g:1:776: WEEK
                {
                mWEEK(); 

                }
                break;
            case 93 :
                // InternalSqlLexer.g:1:781: WHEN
                {
                mWHEN(); 

                }
                break;
            case 94 :
                // InternalSqlLexer.g:1:786: YEAR
                {
                mYEAR(); 

                }
                break;
            case 95 :
                // InternalSqlLexer.g:1:791: LeftParenthesisPlusSignRightParenthesis
                {
                mLeftParenthesisPlusSignRightParenthesis(); 

                }
                break;
            case 96 :
                // InternalSqlLexer.g:1:831: ALL
                {
                mALL(); 

                }
                break;
            case 97 :
                // InternalSqlLexer.g:1:835: AND
                {
                mAND(); 

                }
                break;
            case 98 :
                // InternalSqlLexer.g:1:839: ANY
                {
                mANY(); 

                }
                break;
            case 99 :
                // InternalSqlLexer.g:1:843: ASC
                {
                mASC(); 

                }
                break;
            case 100 :
                // InternalSqlLexer.g:1:847: DAY
                {
                mDAY(); 

                }
                break;
            case 101 :
                // InternalSqlLexer.g:1:851: END
                {
                mEND(); 

                }
                break;
            case 102 :
                // InternalSqlLexer.g:1:855: FOR
                {
                mFOR(); 

                }
                break;
            case 103 :
                // InternalSqlLexer.g:1:859: ROW
                {
                mROW(); 

                }
                break;
            case 104 :
                // InternalSqlLexer.g:1:863: TOP
                {
                mTOP(); 

                }
                break;
            case 105 :
                // InternalSqlLexer.g:1:867: XML
                {
                mXML(); 

                }
                break;
            case 106 :
                // InternalSqlLexer.g:1:871: ExclamationMarkEqualsSign
                {
                mExclamationMarkEqualsSign(); 

                }
                break;
            case 107 :
                // InternalSqlLexer.g:1:897: X
                {
                mX(); 

                }
                break;
            case 108 :
                // InternalSqlLexer.g:1:899: LessThanSignEqualsSign
                {
                mLessThanSignEqualsSign(); 

                }
                break;
            case 109 :
                // InternalSqlLexer.g:1:922: LessThanSignGreaterThanSign
                {
                mLessThanSignGreaterThanSign(); 

                }
                break;
            case 110 :
                // InternalSqlLexer.g:1:950: GreaterThanSignEqualsSign
                {
                mGreaterThanSignEqualsSign(); 

                }
                break;
            case 111 :
                // InternalSqlLexer.g:1:976: AS
                {
                mAS(); 

                }
                break;
            case 112 :
                // InternalSqlLexer.g:1:979: IN
                {
                mIN(); 

                }
                break;
            case 113 :
                // InternalSqlLexer.g:1:982: ON
                {
                mON(); 

                }
                break;
            case 114 :
                // InternalSqlLexer.g:1:985: OR
                {
                mOR(); 

                }
                break;
            case 115 :
                // InternalSqlLexer.g:1:988: CircumflexAccentEqualsSign
                {
                mCircumflexAccentEqualsSign(); 

                }
                break;
            case 116 :
                // InternalSqlLexer.g:1:1015: VerticalLineVerticalLine
                {
                mVerticalLineVerticalLine(); 

                }
                break;
            case 117 :
                // InternalSqlLexer.g:1:1040: LeftParenthesis
                {
                mLeftParenthesis(); 

                }
                break;
            case 118 :
                // InternalSqlLexer.g:1:1056: RightParenthesis
                {
                mRightParenthesis(); 

                }
                break;
            case 119 :
                // InternalSqlLexer.g:1:1073: PlusSign
                {
                mPlusSign(); 

                }
                break;
            case 120 :
                // InternalSqlLexer.g:1:1082: Comma
                {
                mComma(); 

                }
                break;
            case 121 :
                // InternalSqlLexer.g:1:1088: HyphenMinus
                {
                mHyphenMinus(); 

                }
                break;
            case 122 :
                // InternalSqlLexer.g:1:1100: FullStop
                {
                mFullStop(); 

                }
                break;
            case 123 :
                // InternalSqlLexer.g:1:1109: Solidus
                {
                mSolidus(); 

                }
                break;
            case 124 :
                // InternalSqlLexer.g:1:1117: LessThanSign
                {
                mLessThanSign(); 

                }
                break;
            case 125 :
                // InternalSqlLexer.g:1:1130: EqualsSign
                {
                mEqualsSign(); 

                }
                break;
            case 126 :
                // InternalSqlLexer.g:1:1141: GreaterThanSign
                {
                mGreaterThanSign(); 

                }
                break;
            case 127 :
                // InternalSqlLexer.g:1:1157: LeftCurlyBracket
                {
                mLeftCurlyBracket(); 

                }
                break;
            case 128 :
                // InternalSqlLexer.g:1:1174: VerticalLine
                {
                mVerticalLine(); 

                }
                break;
            case 129 :
                // InternalSqlLexer.g:1:1187: RightCurlyBracket
                {
                mRightCurlyBracket(); 

                }
                break;
            case 130 :
                // InternalSqlLexer.g:1:1205: RULE_JRPARAM
                {
                mRULE_JRPARAM(); 

                }
                break;
            case 131 :
                // InternalSqlLexer.g:1:1218: RULE_JRNPARAM
                {
                mRULE_JRNPARAM(); 

                }
                break;
            case 132 :
                // InternalSqlLexer.g:1:1232: RULE_STAR
                {
                mRULE_STAR(); 

                }
                break;
            case 133 :
                // InternalSqlLexer.g:1:1242: RULE_UNSIGNED
                {
                mRULE_UNSIGNED(); 

                }
                break;
            case 134 :
                // InternalSqlLexer.g:1:1256: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 135 :
                // InternalSqlLexer.g:1:1265: RULE_SIGNED_DOUBLE
                {
                mRULE_SIGNED_DOUBLE(); 

                }
                break;
            case 136 :
                // InternalSqlLexer.g:1:1284: RULE_TIMESTAMP
                {
                mRULE_TIMESTAMP(); 

                }
                break;
            case 137 :
                // InternalSqlLexer.g:1:1299: RULE_DATE
                {
                mRULE_DATE(); 

                }
                break;
            case 138 :
                // InternalSqlLexer.g:1:1309: RULE_TIME
                {
                mRULE_TIME(); 

                }
                break;
            case 139 :
                // InternalSqlLexer.g:1:1319: RULE_STRING_
                {
                mRULE_STRING_(); 

                }
                break;
            case 140 :
                // InternalSqlLexer.g:1:1332: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 141 :
                // InternalSqlLexer.g:1:1344: RULE_DBNAME
                {
                mRULE_DBNAME(); 

                }
                break;
            case 142 :
                // InternalSqlLexer.g:1:1356: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 143 :
                // InternalSqlLexer.g:1:1364: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 144 :
                // InternalSqlLexer.g:1:1380: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 145 :
                // InternalSqlLexer.g:1:1396: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 146 :
                // InternalSqlLexer.g:1:1404: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA19_eotS =
        "\1\uffff\15\65\1\62\11\65\1\157\2\65\1\62\1\65\1\171\1\173\1\62\1\176\3\uffff\1\u0084\1\uffff\1\u0087\4\uffff\1\u008c\3\62\4\uffff\2\65\1\uffff\5\65\1\u009f\2\65\1\u00a3\23\65\1\u00bb\7\65\3\uffff\21\65\2\uffff\2\65\1\u00df\1\65\1\uffff\1\u00e1\1\65\14\uffff\1\u00e4\11\uffff\1\u008c\5\uffff\14\65\1\uffff\3\65\1\uffff\3\65\1\u00f9\13\65\1\u0106\3\65\1\uffff\3\65\1\uffff\7\65\2\uffff\10\65\1\u0124\11\65\1\u012f\2\65\1\u0132\1\u0133\1\u0134\1\u0135\1\u0136\1\uffff\1\u0137\5\uffff\12\65\1\u0145\3\65\1\u0149\1\u014a\1\u014c\2\65\1\uffff\1\65\1\u0152\6\65\1\u0159\3\65\1\uffff\1\65\1\u015e\1\u015f\1\uffff\3\65\1\u0169\2\65\1\uffff\1\65\1\u016e\1\u0170\2\65\1\u0173\1\u0174\2\uffff\10\65\1\u017f\1\uffff\2\65\1\u0183\1\u0184\1\65\1\u0186\1\u0187\2\65\1\u018a\1\uffff\1\u018b\1\u018c\10\uffff\2\65\1\u0191\1\u0192\1\65\1\u0194\1\65\1\u0196\3\65\1\uffff\2\65\1\u019c\2\uffff\1\65\1\uffff\5\65\1\uffff\3\65\1\u01a7\1\65\2\uffff\1\u01a9\2\65\1\u01ac\4\uffff\2\65\1\u01af\5\uffff\1\65\1\u01b1\1\65\1\u01b3\1\uffff\1\65\2\uffff\1\u01b5\4\uffff\7\65\1\u01bf\1\uffff\2\65\3\uffff\1\u01c2\2\uffff\1\u01c3\1\u01c4\5\uffff\2\65\2\uffff\1\u01ca\1\uffff\1\65\1\uffff\1\u01cd\1\u01ce\1\65\1\uffff\1\u01d2\1\uffff\2\65\1\u01d5\7\65\1\uffff\1\65\2\uffff\1\65\1\uffff\2\65\1\uffff\1\65\1\uffff\1\65\1\uffff\1\65\3\uffff\1\65\1\uffff\2\65\1\u01ea\1\65\1\u01ec\1\uffff\1\65\1\u01ee\5\uffff\1\65\1\u01f2\1\65\1\uffff\2\65\2\uffff\1\65\3\uffff\2\65\1\uffff\7\65\1\u0202\3\65\1\u0206\1\65\1\u0208\1\65\2\uffff\1\u020d\1\u020e\1\u020f\1\uffff\1\u0210\1\uffff\1\u0211\3\uffff\1\65\1\uffff\13\65\1\u0220\1\u0221\2\65\2\uffff\2\65\1\uffff\1\u0226\1\uffff\1\65\1\u0229\1\u022a\10\uffff\14\65\2\uffff\1\65\1\u023a\1\u023b\1\u023c\1\uffff\1\65\6\uffff\11\65\1\u024c\1\u024d\4\uffff\1\u024e\5\uffff\2\65\1\u0253\3\65\1\u0257\1\u0258\1\65\5\uffff\2\65\1\uffff\3\65\2\uffff\1\65\1\u0262\1\uffff\1\65\1\u0266\1\65\1\u0268\2\65\3\uffff\1\65\1\uffff\1\65\1\uffff\2\65\1\u0270\3\65\1\u0274\1\uffff\2\65\1\u0277\1\uffff\2\65\1\uffff\1\u027a\1\u027b\2\uffff";
    static final String DFA19_eofS =
        "\u027c\uffff";
    static final String DFA19_minS =
        "\1\0\1\116\1\111\1\105\1\106\4\101\1\105\1\116\1\101\2\105\1\0\1\105\1\122\1\114\1\125\3\101\1\117\1\110\1\53\1\114\1\115\1\75\1\120\3\75\1\174\3\uffff\1\55\1\uffff\1\52\4\uffff\1\56\3\0\4\uffff\1\102\1\111\1\uffff\1\103\1\116\1\103\1\122\1\115\1\44\1\106\1\124\1\44\1\105\1\125\1\126\1\131\2\123\1\122\1\105\1\122\1\126\1\122\1\123\1\117\1\124\1\114\1\122\1\117\1\114\1\40\1\44\2\124\1\114\1\101\1\124\2\105\2\0\1\uffff\1\124\1\105\1\103\1\125\1\123\1\104\1\101\1\114\1\106\1\113\1\123\1\116\1\107\1\127\1\111\1\105\1\120\2\uffff\1\114\1\104\1\44\1\114\1\uffff\1\44\1\41\14\uffff\1\56\11\uffff\1\56\1\uffff\1\0\3\uffff\1\117\1\111\1\117\1\116\1\125\1\122\1\124\1\117\1\105\1\101\2\105\1\uffff\1\123\1\105\1\131\1\uffff\2\122\1\111\1\44\1\124\1\103\1\124\2\103\1\117\1\122\1\105\1\123\1\103\1\114\1\44\1\123\1\115\1\114\1\116\1\105\1\114\1\105\1\uffff\1\12\1\125\1\114\1\122\1\110\1\116\1\113\2\0\1\127\1\125\1\101\1\105\1\122\1\123\1\101\1\105\1\44\1\122\1\125\1\123\1\124\1\111\1\105\1\124\1\107\1\110\1\44\2\116\5\44\1\uffff\1\44\4\uffff\1\0\1\125\1\126\1\116\1\107\1\123\1\117\1\110\1\116\1\103\1\111\1\44\1\122\1\105\1\122\3\44\1\116\1\110\1\uffff\1\111\1\44\1\111\2\105\1\124\1\105\1\50\1\44\1\123\1\110\1\117\1\uffff\1\124\2\44\1\117\1\122\1\125\1\122\1\102\1\121\1\116\1\uffff\1\122\2\44\1\40\1\105\2\44\2\0\1\105\1\120\1\124\1\125\1\120\1\101\1\124\1\114\1\44\1\uffff\1\124\1\105\2\44\1\124\2\44\1\105\1\124\1\44\1\uffff\2\44\6\uffff\2\0\1\116\1\117\2\44\1\105\1\44\1\123\1\44\1\104\1\124\1\107\1\uffff\1\40\1\124\1\44\2\uffff\1\115\1\uffff\1\107\1\111\1\105\1\117\1\116\1\uffff\1\124\1\104\1\116\1\44\1\116\2\uffff\1\44\1\40\1\127\1\44\4\uffff\1\123\1\104\1\44\5\uffff\1\125\1\44\1\101\1\44\1\uffff\1\115\2\uffff\1\44\2\uffff\2\0\1\105\1\40\1\105\1\104\1\124\1\103\1\123\1\44\1\uffff\1\105\1\123\3\uffff\1\44\2\uffff\2\44\3\uffff\2\0\1\104\1\124\2\uffff\1\44\1\uffff\1\105\1\uffff\2\44\1\110\1\102\1\44\1\uffff\1\111\1\105\1\44\2\103\1\125\1\103\2\111\1\124\1\uffff\1\124\2\uffff\1\111\1\uffff\2\105\1\uffff\1\101\1\uffff\1\114\1\uffff\1\117\1\uffff\2\0\1\116\1\uffff\1\122\1\105\1\44\1\124\1\44\1\uffff\1\122\1\44\3\uffff\2\0\1\105\1\44\1\115\1\uffff\1\103\1\115\2\uffff\1\124\3\uffff\2\103\1\uffff\1\122\1\125\1\117\1\122\1\124\1\117\1\116\1\44\1\40\1\116\1\103\1\44\1\114\1\44\1\116\2\0\3\44\1\uffff\1\44\1\uffff\1\44\1\uffff\2\0\1\104\1\uffff\1\111\1\105\1\117\1\111\1\137\1\122\1\125\2\117\1\124\1\116\2\44\1\116\1\107\2\uffff\1\107\1\124\1\uffff\1\44\1\uffff\1\124\2\0\6\uffff\2\0\1\40\2\103\1\116\1\103\1\112\1\117\1\124\1\116\1\123\1\105\1\104\2\uffff\1\40\3\44\1\uffff\1\110\3\uffff\2\0\1\106\1\122\1\117\1\104\1\122\1\117\1\123\1\105\1\104\1\105\2\44\4\uffff\1\44\1\uffff\2\0\2\uffff\1\117\1\116\1\44\1\117\1\111\1\105\2\44\1\103\3\uffff\2\0\1\123\1\104\1\uffff\1\123\1\116\1\103\2\uffff\1\117\1\40\1\0\1\105\1\44\1\105\1\44\1\117\1\116\2\uffff\1\0\1\103\1\uffff\1\103\1\uffff\1\116\1\104\1\47\2\117\1\104\1\44\1\uffff\2\116\1\44\1\uffff\2\104\1\uffff\2\44\2\uffff";
    static final String DFA19_maxS =
        "\1\uffff\1\163\1\157\1\164\1\166\1\157\1\151\1\162\2\165\1\163\1\165\1\145\1\151\1\uffff\1\145\1\162\1\170\1\165\1\141\1\151\3\157\1\53\1\163\1\155\1\75\1\170\1\76\2\75\1\174\3\uffff\1\71\1\uffff\1\57\4\uffff\1\71\3\uffff\4\uffff\1\160\1\151\1\uffff\2\156\1\154\1\162\1\155\1\uff3f\1\146\1\164\1\uff3f\1\145\1\165\1\166\1\171\2\163\1\162\1\145\1\162\1\166\1\162\1\163\1\157\1\164\2\162\1\157\1\154\1\40\1\uff3f\2\164\1\154\1\141\1\164\2\145\2\uffff\1\uffff\1\164\1\157\1\164\1\165\1\163\1\144\1\141\1\154\1\163\1\155\1\163\1\156\1\147\1\167\1\151\1\145\1\160\2\uffff\1\154\1\171\1\uff3f\1\154\1\uffff\1\uff3f\1\173\14\uffff\1\71\11\uffff\1\71\1\uffff\1\uffff\3\uffff\1\157\1\151\1\157\1\156\1\165\1\162\1\164\1\157\1\145\1\141\2\145\1\uffff\1\163\1\145\1\171\1\uffff\2\162\1\151\1\uff3f\1\164\1\143\1\164\2\143\1\157\1\162\1\164\1\163\1\143\1\154\1\uff3f\1\163\1\155\1\154\1\156\1\145\1\154\1\145\1\uffff\1\151\1\165\1\154\1\162\1\150\1\162\1\153\2\uffff\1\167\1\165\1\141\1\154\1\162\1\163\1\141\1\145\1\uff3f\1\162\1\165\1\163\1\164\1\151\1\145\1\164\1\147\1\150\1\uff3f\2\156\5\uff3f\1\uffff\1\uff3f\4\uffff\1\uffff\1\165\1\166\1\156\1\147\1\164\1\157\1\150\1\156\1\143\1\151\1\uff3f\1\162\1\145\1\162\3\uff3f\1\156\1\163\1\uffff\1\151\1\uff3f\1\151\2\145\1\164\1\145\1\50\1\uff3f\1\163\1\150\1\157\1\uffff\1\164\2\uff3f\1\165\1\162\1\165\1\162\1\154\1\161\1\156\1\uffff\1\162\2\uff3f\1\40\1\145\2\uff3f\2\uffff\1\145\1\160\1\164\1\165\1\160\1\141\1\164\1\154\1\uff3f\1\uffff\1\164\1\145\2\uff3f\1\164\2\uff3f\1\145\1\164\1\uff3f\1\uffff\2\uff3f\6\uffff\2\uffff\1\156\1\157\2\uff3f\1\145\1\uff3f\1\163\1\uff3f\1\144\1\164\1\147\1\uffff\1\40\1\164\1\uff3f\2\uffff\1\163\1\uffff\1\147\1\151\1\145\1\157\1\156\1\uffff\1\164\1\144\1\156\1\uff3f\1\156\2\uffff\1\uff3f\1\40\1\167\1\uff3f\4\uffff\1\163\1\144\1\uff3f\5\uffff\1\165\1\uff3f\1\141\1\uff3f\1\uffff\1\155\2\uffff\1\uff3f\2\uffff\2\uffff\1\145\1\40\1\145\1\144\1\164\1\143\1\163\1\uff3f\1\uffff\1\145\1\163\3\uffff\1\uff3f\2\uffff\2\uff3f\3\uffff\2\uffff\1\144\1\164\2\uffff\1\uff3f\1\uffff\1\145\1\uffff\2\uff3f\1\150\1\163\1\uff3f\1\uffff\1\151\1\145\1\uff3f\1\156\1\143\1\165\1\143\2\151\1\164\1\uffff\1\164\2\uffff\1\151\1\uffff\2\145\1\uffff\1\141\1\uffff\1\154\1\uffff\1\157\1\uffff\2\uffff\1\156\1\uffff\1\162\1\145\1\uff3f\1\164\1\uff3f\1\uffff\1\162\1\uff3f\3\uffff\2\uffff\1\145\1\uff3f\1\163\1\uffff\1\143\1\155\2\uffff\1\164\3\uffff\1\156\1\143\1\uffff\1\162\1\165\1\157\1\162\1\164\1\157\1\156\1\uff3f\1\40\1\156\1\143\1\uff3f\1\154\1\uff3f\1\156\2\uffff\3\uff3f\1\uffff\1\uff3f\1\uffff\1\uff3f\1\uffff\2\uffff\1\144\1\uffff\1\151\1\145\1\157\1\151\1\137\1\162\1\165\2\157\1\164\1\156\2\uff3f\1\156\1\147\2\uffff\1\147\1\164\1\uffff\1\uff3f\1\uffff\1\164\2\uffff\6\uffff\2\uffff\1\40\2\143\1\156\1\143\1\152\1\157\1\164\1\156\1\163\1\145\1\144\2\uffff\1\40\3\uff3f\1\uffff\1\150\3\uffff\2\uffff\1\160\1\162\1\157\1\144\1\162\1\157\1\163\1\145\1\144\1\145\2\uff3f\4\uffff\1\uff3f\1\uffff\2\uffff\2\uffff\1\157\1\156\1\uff3f\1\157\1\151\1\145\2\uff3f\1\143\3\uffff\2\uffff\1\163\1\144\1\uffff\1\163\1\156\1\143\2\uffff\1\157\1\47\1\uffff\1\145\1\uff3f\1\145\1\uff3f\1\157\1\156\2\uffff\1\uffff\1\143\1\uffff\1\143\1\uffff\1\156\1\144\1\47\2\157\1\144\1\uff3f\1\uffff\2\156\1\uff3f\1\uffff\2\144\1\uffff\2\uff3f\2\uffff";
    static final String DFA19_acceptS =
        "\41\uffff\1\166\1\167\1\170\1\uffff\1\172\1\uffff\1\175\1\177\1\u0081\1\u0084\4\uffff\1\u008e\1\u0090\1\u0091\1\u0092\2\uffff\1\u008e\46\uffff\1\u008d\21\uffff\1\137\1\165\4\uffff\1\152\2\uffff\1\154\1\155\1\174\1\156\1\176\1\163\1\164\1\u0080\1\166\1\167\1\170\1\u0090\1\uffff\1\171\1\172\1\u008f\1\173\1\175\1\177\1\u0081\1\u0084\1\u0085\1\uffff\1\u0087\1\uffff\1\u008b\1\u008c\1\u0091\14\uffff\1\162\3\uffff\1\161\27\uffff\1\160\43\uffff\1\157\1\uffff\1\153\1\u0082\1\u0083\1\u0086\24\uffff\1\144\14\uffff\1\146\12\uffff\1\124\22\uffff\1\145\12\uffff\1\147\2\uffff\1\150\1\140\1\141\1\142\1\143\1\151\15\uffff\1\132\3\uffff\1\127\1\130\1\uffff\1\116\5\uffff\1\112\5\uffff\1\67\1\111\4\uffff\1\114\1\115\1\17\1\51\3\uffff\1\21\1\24\1\37\1\62\1\125\4\uffff\1\126\1\uffff\1\136\1\31\1\uffff\1\135\1\134\12\uffff\1\113\2\uffff\1\74\1\122\1\121\1\uffff\1\123\1\120\2\uffff\1\131\1\117\1\133\4\uffff\1\106\1\107\1\uffff\1\76\1\uffff\1\77\5\uffff\1\102\12\uffff\1\103\1\uffff\1\70\1\14\1\uffff\1\72\2\uffff\1\73\1\uffff\1\100\1\uffff\1\101\1\uffff\1\110\3\uffff\1\36\5\uffff\1\71\2\uffff\1\75\1\104\1\105\5\uffff\1\61\2\uffff\1\64\1\65\1\uffff\1\5\1\41\1\63\2\uffff\1\60\24\uffff\1\56\1\uffff\1\57\1\uffff\1\66\3\uffff\1\55\17\uffff\1\53\1\13\2\uffff\1\50\1\uffff\1\52\3\uffff\1\33\1\44\1\47\1\45\1\46\1\54\16\uffff\1\34\1\35\4\uffff\1\40\1\uffff\1\32\1\42\1\43\16\uffff\1\12\1\30\1\26\1\27\1\uffff\1\32\2\uffff\1\1\1\2\11\uffff\1\22\1\23\1\25\4\uffff\1\20\3\uffff\1\15\1\16\11\uffff\1\u0089\1\u0088\2\uffff\1\10\1\uffff\1\11\7\uffff\1\u008a\3\uffff\1\7\2\uffff\1\6\2\uffff\1\3\1\4";
    static final String DFA19_specialS =
        "\1\3\15\uffff\1\7\35\uffff\1\4\1\0\1\12\53\uffff\1\6\1\44\63\uffff\1\2\63\uffff\1\11\1\46\40\uffff\1\1\63\uffff\1\10\1\45\35\uffff\1\16\1\34\73\uffff\1\14\1\42\26\uffff\1\21\1\36\47\uffff\1\13\1\41\15\uffff\1\22\1\35\36\uffff\1\15\1\43\10\uffff\1\17\1\40\31\uffff\1\26\1\5\6\uffff\1\20\1\37\27\uffff\1\24\1\31\22\uffff\1\25\1\32\16\uffff\1\23\1\27\12\uffff\1\30\10\uffff\1\33\27\uffff}>";
    static final String[] DFA19_transitionS = {
            "\11\62\2\61\2\62\1\61\22\62\1\61\1\33\1\55\1\60\1\34\2\62\1\54\1\30\1\41\1\52\1\42\1\43\1\44\1\45\1\46\12\53\2\62\1\35\1\47\1\36\2\62\1\31\1\17\1\10\1\6\1\21\1\11\1\20\1\5\1\12\1\26\1\57\1\24\1\2\1\13\1\4\1\7\1\22\1\25\1\3\1\27\1\1\1\23\1\15\1\32\1\14\1\57\1\16\2\62\1\37\1\57\1\56\1\31\1\17\1\10\1\6\1\21\1\11\1\20\1\5\1\12\1\26\1\57\1\24\1\2\1\13\1\4\1\7\1\22\1\25\1\3\1\27\1\1\1\23\1\15\1\32\1\14\1\57\1\50\1\40\1\51\102\62\u0190\57\u01c0\62\100\57\u2bf1\62\u00bf\57\u00f0\62\20\57\u0200\62\u19c0\57\100\62\u5200\57\u5900\62\u0200\57\u043f\62\1\57\u00c0\62",
            "\1\63\4\uffff\1\64\32\uffff\1\63\4\uffff\1\64",
            "\1\66\5\uffff\1\67\31\uffff\1\66\5\uffff\1\67",
            "\1\70\11\uffff\1\72\4\uffff\1\71\20\uffff\1\70\11\uffff\1\72\4\uffff\1\71",
            "\1\74\7\uffff\1\76\3\uffff\1\73\2\uffff\1\75\1\77\17\uffff\1\74\7\uffff\1\76\3\uffff\1\73\2\uffff\1\75\1\77",
            "\1\101\15\uffff\1\100\21\uffff\1\101\15\uffff\1\100",
            "\1\102\3\uffff\1\104\3\uffff\1\103\27\uffff\1\102\3\uffff\1\104\3\uffff\1\103",
            "\1\105\3\uffff\1\107\3\uffff\1\110\10\uffff\1\106\16\uffff\1\105\3\uffff\1\107\3\uffff\1\110\10\uffff\1\106",
            "\1\112\20\uffff\1\113\2\uffff\1\111\13\uffff\1\112\20\uffff\1\113\2\uffff\1\111",
            "\1\114\3\uffff\1\116\5\uffff\1\115\2\uffff\1\117\2\uffff\1\120\17\uffff\1\114\3\uffff\1\116\5\uffff\1\115\2\uffff\1\117\2\uffff\1\120",
            "\1\122\4\uffff\1\121\32\uffff\1\122\4\uffff\1\121",
            "\1\124\15\uffff\1\123\5\uffff\1\125\13\uffff\1\124\15\uffff\1\123\5\uffff\1\125",
            "\1\126\37\uffff\1\126",
            "\1\131\2\uffff\1\130\1\127\33\uffff\1\131\2\uffff\1\130\1\127",
            "\102\134\1\132\4\134\1\133\32\134\1\132\4\134\1\133\uff98\134",
            "\1\135\37\uffff\1\135",
            "\1\136\37\uffff\1\136",
            "\1\141\1\uffff\1\142\2\uffff\1\140\6\uffff\1\137\23\uffff\1\141\1\uffff\1\142\2\uffff\1\140\6\uffff\1\137",
            "\1\143\37\uffff\1\143",
            "\1\144\37\uffff\1\144",
            "\1\147\3\uffff\1\145\3\uffff\1\146\27\uffff\1\147\3\uffff\1\145\3\uffff\1\146",
            "\1\150\7\uffff\1\151\5\uffff\1\152\21\uffff\1\150\7\uffff\1\151\5\uffff\1\152",
            "\1\153\37\uffff\1\153",
            "\1\154\6\uffff\1\155\30\uffff\1\154\6\uffff\1\155",
            "\1\156",
            "\1\160\1\uffff\1\161\4\uffff\1\162\30\uffff\1\160\1\uffff\1\161\4\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "\1\164",
            "\1\166\7\uffff\1\165\37\uffff\1\165",
            "\1\167\1\170",
            "\1\172",
            "\1\174",
            "\1\175",
            "",
            "",
            "",
            "\1\u0082\2\uffff\12\u0083",
            "",
            "\1\u0086\4\uffff\1\u0082",
            "",
            "",
            "",
            "",
            "\1\u008e\1\uffff\12\u008d",
            "\60\u0090\12\u008f\uffc6\u0090",
            "\0\u0091",
            "\0\134",
            "",
            "",
            "",
            "",
            "\1\u0093\6\uffff\1\u0095\6\uffff\1\u0094\21\uffff\1\u0093\6\uffff\1\u0095\6\uffff\1\u0094",
            "\1\u0096\37\uffff\1\u0096",
            "",
            "\1\u0098\12\uffff\1\u0097\24\uffff\1\u0098\12\uffff\1\u0097",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\10\uffff\1\u009b\26\uffff\1\u009a\10\uffff\1\u009b",
            "\1\u009c\37\uffff\1\u009c",
            "\1\u009d\37\uffff\1\u009d",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\3\65\1\u009e\26\65\4\uffff\1\65\1\uffff\3\65\1\u009e\26\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\13\65\1\u00a2\16\65\4\uffff\1\65\1\uffff\13\65\1\u00a2\16\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
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
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "\1\u00b1\37\uffff\1\u00b1",
            "\1\u00b2\5\uffff\1\u00b3\31\uffff\1\u00b2\5\uffff\1\u00b3",
            "\1\u00b4\37\uffff\1\u00b4",
            "\1\u00b5\37\uffff\1\u00b5",
            "\1\u00b6\37\uffff\1\u00b6",
            "\1\u00b7",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\2\65\1\u00b9\12\65\1\u00ba\5\65\1\u00b8\6\65\4\uffff\1\65\1\uffff\2\65\1\u00b9\12\65\1\u00ba\5\65\1\u00b8\6\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00bc\37\uffff\1\u00bc",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "\1\u00bf\37\uffff\1\u00bf",
            "\1\u00c0\37\uffff\1\u00c0",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "\105\134\1\u00c3\37\134\1\u00c3\uff9a\134",
            "\122\134\1\u00c4\37\134\1\u00c4\uff8d\134",
            "",
            "\1\u00c5\37\uffff\1\u00c5",
            "\1\u00c7\11\uffff\1\u00c6\25\uffff\1\u00c7\11\uffff\1\u00c6",
            "\1\u00c8\5\uffff\1\u00ca\12\uffff\1\u00c9\16\uffff\1\u00c8\5\uffff\1\u00ca\12\uffff\1\u00c9",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\u00cc\37\uffff\1\u00cc",
            "\1\u00cd\37\uffff\1\u00cd",
            "\1\u00ce\37\uffff\1\u00ce",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d1\14\uffff\1\u00d0\22\uffff\1\u00d1\14\uffff\1\u00d0",
            "\1\u00d3\1\uffff\1\u00d2\35\uffff\1\u00d3\1\uffff\1\u00d2",
            "\1\u00d4\37\uffff\1\u00d4",
            "\1\u00d5\37\uffff\1\u00d5",
            "\1\u00d6\37\uffff\1\u00d6",
            "\1\u00d7\37\uffff\1\u00d7",
            "\1\u00d8\37\uffff\1\u00d8",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\u00da\37\uffff\1\u00da",
            "",
            "",
            "\1\u00db\37\uffff\1\u00db",
            "\1\u00dc\24\uffff\1\u00dd\12\uffff\1\u00dc\24\uffff\1\u00dd",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\2\65\1\u00de\27\65\4\uffff\1\65\1\uffff\2\65\1\u00de\27\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00e0\37\uffff\1\u00e0",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00e3\131\uffff\1\u00e2",
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
            "\1\u008e\1\uffff\12\u0083",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u008e\1\uffff\12\u008d",
            "",
            "\60\u0090\12\u00e5\uffc6\u0090",
            "",
            "",
            "",
            "\1\u00e6\37\uffff\1\u00e6",
            "\1\u00e7\37\uffff\1\u00e7",
            "\1\u00e8\37\uffff\1\u00e8",
            "\1\u00e9\37\uffff\1\u00e9",
            "\1\u00ea\37\uffff\1\u00ea",
            "\1\u00eb\37\uffff\1\u00eb",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\u00ed\37\uffff\1\u00ed",
            "\1\u00ee\37\uffff\1\u00ee",
            "\1\u00ef\37\uffff\1\u00ef",
            "\1\u00f0\37\uffff\1\u00f0",
            "\1\u00f1\37\uffff\1\u00f1",
            "",
            "\1\u00f2\37\uffff\1\u00f2",
            "\1\u00f3\37\uffff\1\u00f3",
            "\1\u00f4\37\uffff\1\u00f4",
            "",
            "\1\u00f5\37\uffff\1\u00f5",
            "\1\u00f6\37\uffff\1\u00f6",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u00f8\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u00fa\37\uffff\1\u00fa",
            "\1\u00fb\37\uffff\1\u00fb",
            "\1\u00fc\37\uffff\1\u00fc",
            "\1\u00fd\37\uffff\1\u00fd",
            "\1\u00fe\37\uffff\1\u00fe",
            "\1\u00ff\37\uffff\1\u00ff",
            "\1\u0100\37\uffff\1\u0100",
            "\1\u0102\16\uffff\1\u0101\20\uffff\1\u0102\16\uffff\1\u0101",
            "\1\u0103\37\uffff\1\u0103",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0107\37\uffff\1\u0107",
            "\1\u0108\37\uffff\1\u0108",
            "\1\u0109\37\uffff\1\u0109",
            "\1\u010a\37\uffff\1\u010a",
            "\1\u010b\37\uffff\1\u010b",
            "\1\u010c\37\uffff\1\u010c",
            "\1\u010d\37\uffff\1\u010d",
            "",
            "\1\u0111\25\uffff\1\u010e\44\uffff\1\u010f\3\uffff\1\u0110\33\uffff\1\u010f\3\uffff\1\u0110",
            "\1\u0112\37\uffff\1\u0112",
            "\1\u0113\37\uffff\1\u0113",
            "\1\u0114\37\uffff\1\u0114",
            "\1\u0115\37\uffff\1\u0115",
            "\1\u0117\3\uffff\1\u0116\33\uffff\1\u0117\3\uffff\1\u0116",
            "\1\u0118\37\uffff\1\u0118",
            "\124\134\1\u0119\37\134\1\u0119\uff8b\134",
            "\105\134\1\u011a\37\134\1\u011a\uff9a\134",
            "\1\u011b\37\uffff\1\u011b",
            "\1\u011c\37\uffff\1\u011c",
            "\1\u011d\37\uffff\1\u011d",
            "\1\u011f\6\uffff\1\u011e\30\uffff\1\u011f\6\uffff\1\u011e",
            "\1\u0120\37\uffff\1\u0120",
            "\1\u0121\37\uffff\1\u0121",
            "\1\u0122\37\uffff\1\u0122",
            "\1\u0123\37\uffff\1\u0123",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0125\37\uffff\1\u0125",
            "\1\u0126\37\uffff\1\u0126",
            "\1\u0127\37\uffff\1\u0127",
            "\1\u0128\37\uffff\1\u0128",
            "\1\u0129\37\uffff\1\u0129",
            "\1\u012a\37\uffff\1\u012a",
            "\1\u012b\37\uffff\1\u012b",
            "\1\u012c\37\uffff\1\u012c",
            "\1\u012d\37\uffff\1\u012d",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\22\65\1\u012e\7\65\4\uffff\1\65\1\uffff\22\65\1\u012e\7\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0130\37\uffff\1\u0130",
            "\1\u0131\37\uffff\1\u0131",
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
            "\60\u0090\12\u0138\1\u0139\uffc5\u0090",
            "\1\u013a\37\uffff\1\u013a",
            "\1\u013b\37\uffff\1\u013b",
            "\1\u013c\37\uffff\1\u013c",
            "\1\u013d\37\uffff\1\u013d",
            "\1\u013f\1\u013e\36\uffff\1\u013f\1\u013e",
            "\1\u0140\37\uffff\1\u0140",
            "\1\u0141\37\uffff\1\u0141",
            "\1\u0142\37\uffff\1\u0142",
            "\1\u0143\37\uffff\1\u0143",
            "\1\u0144\37\uffff\1\u0144",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0146\37\uffff\1\u0146",
            "\1\u0147\37\uffff\1\u0147",
            "\1\u0148\37\uffff\1\u0148",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u014b\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u014d\37\uffff\1\u014d",
            "\1\u0150\4\uffff\1\u014e\5\uffff\1\u014f\24\uffff\1\u0150\4\uffff\1\u014e\5\uffff\1\u014f",
            "",
            "\1\u0151\37\uffff\1\u0151",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0153\37\uffff\1\u0153",
            "\1\u0154\37\uffff\1\u0154",
            "\1\u0155\37\uffff\1\u0155",
            "\1\u0156\37\uffff\1\u0156",
            "\1\u0157\37\uffff\1\u0157",
            "\1\u0158",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u015a\37\uffff\1\u015a",
            "\1\u015b\37\uffff\1\u015b",
            "\1\u015c\37\uffff\1\u015c",
            "",
            "\1\u015d\37\uffff\1\u015d",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0160\5\uffff\1\u0161\31\uffff\1\u0160\5\uffff\1\u0161",
            "\1\u0162\37\uffff\1\u0162",
            "\1\u0163\37\uffff\1\u0163",
            "\1\u0164\37\uffff\1\u0164",
            "\1\u0165\2\uffff\1\u0166\3\uffff\1\u0168\2\uffff\1\u0167\25\uffff\1\u0165\2\uffff\1\u0166\3\uffff\1\u0168\2\uffff\1\u0167",
            "\1\u016a\37\uffff\1\u016a",
            "\1\u016b\37\uffff\1\u016b",
            "",
            "\1\u016c\37\uffff\1\u016c",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\22\65\1\u016d\7\65\4\uffff\1\65\1\uffff\22\65\1\u016d\7\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u016f\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0171",
            "\1\u0172\37\uffff\1\u0172",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\127\134\1\u0175\37\134\1\u0175\uff88\134",
            "\101\134\1\u0176\37\134\1\u0176\uff9e\134",
            "\1\u0177\37\uffff\1\u0177",
            "\1\u0178\37\uffff\1\u0178",
            "\1\u0179\37\uffff\1\u0179",
            "\1\u017a\37\uffff\1\u017a",
            "\1\u017b\37\uffff\1\u017b",
            "\1\u017c\37\uffff\1\u017c",
            "\1\u017d\37\uffff\1\u017d",
            "\1\u017e\37\uffff\1\u017e",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u0180\37\uffff\1\u0180",
            "\1\u0181\37\uffff\1\u0181",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\2\uffff\1\u0182\1\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0185\37\uffff\1\u0185",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0188\37\uffff\1\u0188",
            "\1\u0189\37\uffff\1\u0189",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "",
            "",
            "\60\u0090\12\u018d\uffc6\u0090",
            "\60\u0090\12\u018e\uffc6\u0090",
            "\1\u018f\37\uffff\1\u018f",
            "\1\u0190\37\uffff\1\u0190",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0193\37\uffff\1\u0193",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0195\37\uffff\1\u0195",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0197\37\uffff\1\u0197",
            "\1\u0198\37\uffff\1\u0198",
            "\1\u0199\37\uffff\1\u0199",
            "",
            "\1\u019a",
            "\1\u019b\37\uffff\1\u019b",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "\1\u019d\5\uffff\1\u019e\31\uffff\1\u019d\5\uffff\1\u019e",
            "",
            "\1\u019f\37\uffff\1\u019f",
            "\1\u01a0\37\uffff\1\u01a0",
            "\1\u01a1\37\uffff\1\u01a1",
            "\1\u01a2\37\uffff\1\u01a2",
            "\1\u01a3\37\uffff\1\u01a3",
            "",
            "\1\u01a4\37\uffff\1\u01a4",
            "\1\u01a5\37\uffff\1\u01a5",
            "\1\u01a6\37\uffff\1\u01a6",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01a8\37\uffff\1\u01a8",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01aa",
            "\1\u01ab\37\uffff\1\u01ab",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "\1\u01ad\37\uffff\1\u01ad",
            "\1\u01ae\37\uffff\1\u01ae",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "",
            "\1\u01b0\37\uffff\1\u01b0",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01b2\37\uffff\1\u01b2",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u01b4\37\uffff\1\u01b4",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "\105\134\1\u01b6\37\134\1\u01b6\uff9a\134",
            "\124\134\1\u01b7\37\134\1\u01b7\uff8b\134",
            "\1\u01b8\37\uffff\1\u01b8",
            "\1\u01b9",
            "\1\u01ba\37\uffff\1\u01ba",
            "\1\u01bb\37\uffff\1\u01bb",
            "\1\u01bc\37\uffff\1\u01bc",
            "\1\u01bd\37\uffff\1\u01bd",
            "\1\u01be\37\uffff\1\u01be",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u01c0\37\uffff\1\u01c0",
            "\1\u01c1\37\uffff\1\u01c1",
            "",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "\55\u0090\1\u01c5\uffd2\u0090",
            "\60\u0090\12\u01c6\uffc6\u0090",
            "\1\u01c7\37\uffff\1\u01c7",
            "\1\u01c8\37\uffff\1\u01c8",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u01c9\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u01cb\37\uffff\1\u01cb",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\u01cc\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01cf\37\uffff\1\u01cf",
            "\1\u01d1\20\uffff\1\u01d0\16\uffff\1\u01d1\20\uffff\1\u01d0",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u01d3\37\uffff\1\u01d3",
            "\1\u01d4\37\uffff\1\u01d4",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01d6\12\uffff\1\u01d7\24\uffff\1\u01d6\12\uffff\1\u01d7",
            "\1\u01d8\37\uffff\1\u01d8",
            "\1\u01d9\37\uffff\1\u01d9",
            "\1\u01da\37\uffff\1\u01da",
            "\1\u01db\37\uffff\1\u01db",
            "\1\u01dc\37\uffff\1\u01dc",
            "\1\u01dd\37\uffff\1\u01dd",
            "",
            "\1\u01de\37\uffff\1\u01de",
            "",
            "",
            "\1\u01df\37\uffff\1\u01df",
            "",
            "\1\u01e0\37\uffff\1\u01e0",
            "\1\u01e1\37\uffff\1\u01e1",
            "",
            "\1\u01e2\37\uffff\1\u01e2",
            "",
            "\1\u01e3\37\uffff\1\u01e3",
            "",
            "\1\u01e4\37\uffff\1\u01e4",
            "",
            "\105\134\1\u01e5\37\134\1\u01e5\uff9a\134",
            "\105\134\1\u01e6\37\134\1\u01e6\uff9a\134",
            "\1\u01e7\37\uffff\1\u01e7",
            "",
            "\1\u01e8\37\uffff\1\u01e8",
            "\1\u01e9\37\uffff\1\u01e9",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01eb\37\uffff\1\u01eb",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u01ed\37\uffff\1\u01ed",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "\60\u0090\2\u01ef\uffce\u0090",
            "\72\u0090\1\u01f0\uffc5\u0090",
            "\1\u01f1\37\uffff\1\u01f1",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u01f3\5\uffff\1\u01f4\31\uffff\1\u01f3\5\uffff\1\u01f4",
            "",
            "\1\u01f5\37\uffff\1\u01f5",
            "\1\u01f6\37\uffff\1\u01f6",
            "",
            "",
            "\1\u01f7\37\uffff\1\u01f7",
            "",
            "",
            "",
            "\1\u01f8\12\uffff\1\u01f9\24\uffff\1\u01f8\12\uffff\1\u01f9",
            "\1\u01fa\37\uffff\1\u01fa",
            "",
            "\1\u01fb\37\uffff\1\u01fb",
            "\1\u01fc\37\uffff\1\u01fc",
            "\1\u01fd\37\uffff\1\u01fd",
            "\1\u01fe\37\uffff\1\u01fe",
            "\1\u01ff\37\uffff\1\u01ff",
            "\1\u0200\37\uffff\1\u0200",
            "\1\u0201\37\uffff\1\u0201",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0203",
            "\1\u0204\37\uffff\1\u0204",
            "\1\u0205\37\uffff\1\u0205",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0207\37\uffff\1\u0207",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0209\37\uffff\1\u0209",
            "\116\134\1\u020a\37\134\1\u020a\uff91\134",
            "\122\134\1\u020b\37\134\1\u020b\uff8d\134",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\2\uffff\1\u020c\1\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\60\u0090\12\u0212\uffc6\u0090",
            "\60\u0090\12\u0213\uffc6\u0090",
            "\1\u0214\37\uffff\1\u0214",
            "",
            "\1\u0215\37\uffff\1\u0215",
            "\1\u0216\37\uffff\1\u0216",
            "\1\u0217\37\uffff\1\u0217",
            "\1\u0218\37\uffff\1\u0218",
            "\1\u0219",
            "\1\u021a\37\uffff\1\u021a",
            "\1\u021b\37\uffff\1\u021b",
            "\1\u021c\37\uffff\1\u021c",
            "\1\u021d\37\uffff\1\u021d",
            "\1\u021e\37\uffff\1\u021e",
            "\1\u021f\37\uffff\1\u021f",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0222\37\uffff\1\u0222",
            "\1\u0223\37\uffff\1\u0223",
            "",
            "",
            "\1\u0224\37\uffff\1\u0224",
            "\1\u0225\37\uffff\1\u0225",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u0227\37\uffff\1\u0227",
            "\135\134\1\u0228\uffa2\134",
            "\0\134",
            "",
            "",
            "",
            "",
            "",
            "",
            "\55\u0090\1\u022b\uffd2\u0090",
            "\60\u0090\12\u022c\uffc6\u0090",
            "\1\u022d",
            "\1\u022e\37\uffff\1\u022e",
            "\1\u022f\37\uffff\1\u022f",
            "\1\u0230\37\uffff\1\u0230",
            "\1\u0231\37\uffff\1\u0231",
            "\1\u0232\37\uffff\1\u0232",
            "\1\u0233\37\uffff\1\u0233",
            "\1\u0234\37\uffff\1\u0234",
            "\1\u0235\37\uffff\1\u0235",
            "\1\u0236\37\uffff\1\u0236",
            "\1\u0237\37\uffff\1\u0237",
            "\1\u0238\37\uffff\1\u0238",
            "",
            "",
            "\1\u0239",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u023d\37\uffff\1\u023d",
            "",
            "",
            "",
            "\60\u0090\4\u023f\uffcc\u0090",
            "\56\u0090\1\u0240\uffd1\u0090",
            "\1\u0241\11\uffff\1\u0242\25\uffff\1\u0241\11\uffff\1\u0242",
            "\1\u0243\37\uffff\1\u0243",
            "\1\u0244\37\uffff\1\u0244",
            "\1\u0245\37\uffff\1\u0245",
            "\1\u0246\37\uffff\1\u0246",
            "\1\u0247\37\uffff\1\u0247",
            "\1\u0248\37\uffff\1\u0248",
            "\1\u0249\37\uffff\1\u0249",
            "\1\u024a\37\uffff\1\u024a",
            "\1\u024b\37\uffff\1\u024b",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "",
            "",
            "",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\60\u0090\12\u024f\uffc6\u0090",
            "\60\u0090\12\u0250\uffc6\u0090",
            "",
            "",
            "\1\u0251\37\uffff\1\u0251",
            "\1\u0252\37\uffff\1\u0252",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0254\37\uffff\1\u0254",
            "\1\u0255\37\uffff\1\u0255",
            "\1\u0256\37\uffff\1\u0256",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0259\37\uffff\1\u0259",
            "",
            "",
            "",
            "\47\u0090\1\u025a\uffd8\u0090",
            "\60\u0090\12\u025b\uffc6\u0090",
            "\1\u025c\37\uffff\1\u025c",
            "\1\u025d\37\uffff\1\u025d",
            "",
            "\1\u025e\37\uffff\1\u025e",
            "\1\u025f\37\uffff\1\u025f",
            "\1\u0260\37\uffff\1\u0260",
            "",
            "",
            "\1\u0261\37\uffff\1\u0261",
            "\1\u0263\6\uffff\1\u0090",
            "\60\u0090\12\u0264\uffc6\u0090",
            "\1\u0265\37\uffff\1\u0265",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0267\37\uffff\1\u0267",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "\1\u0269\37\uffff\1\u0269",
            "\1\u026a\37\uffff\1\u026a",
            "",
            "",
            "\47\u0090\1\u026b\uffd8\u0090",
            "\1\u026c\37\uffff\1\u026c",
            "",
            "\1\u026d\37\uffff\1\u026d",
            "",
            "\1\u026e\37\uffff\1\u026e",
            "\1\u026f\37\uffff\1\u026f",
            "\1\u0090",
            "\1\u0271\37\uffff\1\u0271",
            "\1\u0272\37\uffff\1\u0272",
            "\1\u0273\37\uffff\1\u0273",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u0275\37\uffff\1\u0275",
            "\1\u0276\37\uffff\1\u0276",
            "\1\65\10\uffff\1\65\2\uffff\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65\105\uffff\u0190\65\u01c0\uffff\100\65\u2bf1\uffff\u00bf\65\u00f0\uffff\20\65\u0200\uffff\u19c0\65\100\uffff\u5200\65\u5900\uffff\u0200\65\u043f\uffff\1\65",
            "",
            "\1\u0278\37\uffff\1\u0278",
            "\1\u0279\37\uffff\1\u0279",
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
            return "1:1: Tokens : ( UNBOUNDEDFOLLOWING | UNBOUNDEDPRECEDING | MINUTE_MICROSECOND | SECOND_MICROSECOND | ORDERSIBLINGSBY | HOUR_MICROSECOND | DAY_MICROSECOND | MINUTE_SECOND | STRAIGHT_JOIN | PARTITIONBY | CURRENTROW | FETCHFIRST | HOUR_MINUTE | HOUR_SECOND | ISNOTNULL | MICROSECOND | NOTBETWEEN | DAY_MINUTE | DAY_SECOND | NOTEXISTS | YEAR_MONTH | KW_FOLLOWING | INTERSECT | PRECEDING | WITHTIES | BETWEEN_3 | BETWEEN_1 | DAY_HOUR | DISTINCT | GROUPBY | NOTLIKE | NOTEQUAL | ORDERBY | BETWEEN_2 | GREATER_1 | BETWEEN | EXCLUDE | EXTRACT | GREATER | INCLUDE | ISNULL | NATURAL | PERCENT | QUARTER | UNPIVOT | EXCEPT | EXISTS | HAVING | MINUTE | NOTIN_1 | OFFSET | SECOND | SELECT | VALUES | CAST | CROSS | EQUAL | FIRST | INNER | LESS_1 | LIMIT | MINUS | MONTH | NOTIN | NULLS | OUTER | PIVOT | RANGE | RIGHT | UNION | USING | WHERE | CASE | DESC | ELSE | FROM | FULL | HOUR | JOIN | LAST | LEFT | LESS | LIKE | NOT | NOT_1 | NULL | ONLY | OVER | ROWS | SOME | THEN | WEEK | WHEN | YEAR | LeftParenthesisPlusSignRightParenthesis | ALL | AND | ANY | ASC | DAY | END | FOR | ROW | TOP | XML | ExclamationMarkEqualsSign | X | LessThanSignEqualsSign | LessThanSignGreaterThanSign | GreaterThanSignEqualsSign | AS | IN | ON | OR | CircumflexAccentEqualsSign | VerticalLineVerticalLine | LeftParenthesis | RightParenthesis | PlusSign | Comma | HyphenMinus | FullStop | Solidus | LessThanSign | EqualsSign | GreaterThanSign | LeftCurlyBracket | VerticalLine | RightCurlyBracket | RULE_JRPARAM | RULE_JRNPARAM | RULE_STAR | RULE_UNSIGNED | RULE_INT | RULE_SIGNED_DOUBLE | RULE_TIMESTAMP | RULE_DATE | RULE_TIME | RULE_STRING_ | RULE_STRING | RULE_DBNAME | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA19_45 = input.LA(1);

                        s = -1;
                        if ( ((LA19_45>='\u0000' && LA19_45<='\uFFFF')) ) {s = 145;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA19_229 = input.LA(1);

                        s = -1;
                        if ( ((LA19_229>='0' && LA19_229<='9')) ) {s = 312;}

                        else if ( (LA19_229==':') ) {s = 313;}

                        else if ( ((LA19_229>='\u0000' && LA19_229<='/')||(LA19_229>=';' && LA19_229<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA19_143 = input.LA(1);

                        s = -1;
                        if ( ((LA19_143>='0' && LA19_143<='9')) ) {s = 229;}

                        else if ( ((LA19_143>='\u0000' && LA19_143<='/')||(LA19_143>=':' && LA19_143<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
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

                        else if ( (LA19_0=='V'||LA19_0=='v') ) {s = 19;}

                        else if ( (LA19_0=='L'||LA19_0=='l') ) {s = 20;}

                        else if ( (LA19_0=='R'||LA19_0=='r') ) {s = 21;}

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

                        else if ( (LA19_0=='{') ) {s = 40;}

                        else if ( (LA19_0=='}') ) {s = 41;}

                        else if ( (LA19_0=='*') ) {s = 42;}

                        else if ( ((LA19_0>='0' && LA19_0<='9')) ) {s = 43;}

                        else if ( (LA19_0=='\'') ) {s = 44;}

                        else if ( (LA19_0=='\"') ) {s = 45;}

                        else if ( (LA19_0=='`') ) {s = 46;}

                        else if ( (LA19_0=='K'||LA19_0=='Z'||LA19_0=='_'||LA19_0=='k'||LA19_0=='z'||(LA19_0>='\u00C0' && LA19_0<='\u024F')||(LA19_0>='\u0410' && LA19_0<='\u044F')||(LA19_0>='\u3041' && LA19_0<='\u30FF')||(LA19_0>='\u31F0' && LA19_0<='\u31FF')||(LA19_0>='\u3400' && LA19_0<='\u4DBF')||(LA19_0>='\u4E00' && LA19_0<='\u9FFF')||(LA19_0>='\uF900' && LA19_0<='\uFAFF')||LA19_0=='\uFF3F') ) {s = 47;}

                        else if ( (LA19_0=='#') ) {s = 48;}

                        else if ( ((LA19_0>='\t' && LA19_0<='\n')||LA19_0=='\r'||LA19_0==' ') ) {s = 49;}

                        else if ( ((LA19_0>='\u0000' && LA19_0<='\b')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\u001F')||(LA19_0>='%' && LA19_0<='&')||(LA19_0>=':' && LA19_0<=';')||(LA19_0>='?' && LA19_0<='@')||(LA19_0>='\\' && LA19_0<=']')||(LA19_0>='~' && LA19_0<='\u00BF')||(LA19_0>='\u0250' && LA19_0<='\u040F')||(LA19_0>='\u0450' && LA19_0<='\u3040')||(LA19_0>='\u3100' && LA19_0<='\u31EF')||(LA19_0>='\u3200' && LA19_0<='\u33FF')||(LA19_0>='\u4DC0' && LA19_0<='\u4DFF')||(LA19_0>='\uA000' && LA19_0<='\uF8FF')||(LA19_0>='\uFB00' && LA19_0<='\uFF3E')||(LA19_0>='\uFF40' && LA19_0<='\uFFFF')) ) {s = 50;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA19_44 = input.LA(1);

                        s = -1;
                        if ( ((LA19_44>='0' && LA19_44<='9')) ) {s = 143;}

                        else if ( ((LA19_44>='\u0000' && LA19_44<='/')||(LA19_44>=':' && LA19_44<='\uFFFF')) ) {s = 144;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA19_523 = input.LA(1);

                        s = -1;
                        if ( ((LA19_523>='\u0000' && LA19_523<='\uFFFF')) ) {s = 92;}

                        else s = 554;

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA19_90 = input.LA(1);

                        s = -1;
                        if ( (LA19_90=='E'||LA19_90=='e') ) {s = 195;}

                        else if ( ((LA19_90>='\u0000' && LA19_90<='D')||(LA19_90>='F' && LA19_90<='d')||(LA19_90>='f' && LA19_90<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA19_14 = input.LA(1);

                        s = -1;
                        if ( (LA19_14=='B'||LA19_14=='b') ) {s = 90;}

                        else if ( (LA19_14=='G'||LA19_14=='g') ) {s = 91;}

                        else if ( ((LA19_14>='\u0000' && LA19_14<='A')||(LA19_14>='C' && LA19_14<='F')||(LA19_14>='H' && LA19_14<='a')||(LA19_14>='c' && LA19_14<='f')||(LA19_14>='h' && LA19_14<='\uFFFF')) ) {s = 92;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA19_281 = input.LA(1);

                        s = -1;
                        if ( (LA19_281=='W'||LA19_281=='w') ) {s = 373;}

                        else if ( ((LA19_281>='\u0000' && LA19_281<='V')||(LA19_281>='X' && LA19_281<='v')||(LA19_281>='x' && LA19_281<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA19_195 = input.LA(1);

                        s = -1;
                        if ( (LA19_195=='T'||LA19_195=='t') ) {s = 281;}

                        else if ( ((LA19_195>='\u0000' && LA19_195<='S')||(LA19_195>='U' && LA19_195<='s')||(LA19_195>='u' && LA19_195<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA19_46 = input.LA(1);

                        s = -1;
                        if ( ((LA19_46>='\u0000' && LA19_46<='\uFFFF')) ) {s = 92;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA19_438 = input.LA(1);

                        s = -1;
                        if ( (LA19_438=='E'||LA19_438=='e') ) {s = 485;}

                        else if ( ((LA19_438>='\u0000' && LA19_438<='D')||(LA19_438>='F' && LA19_438<='d')||(LA19_438>='f' && LA19_438<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA19_373 = input.LA(1);

                        s = -1;
                        if ( (LA19_373=='E'||LA19_373=='e') ) {s = 438;}

                        else if ( ((LA19_373>='\u0000' && LA19_373<='D')||(LA19_373>='F' && LA19_373<='d')||(LA19_373>='f' && LA19_373<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA19_485 = input.LA(1);

                        s = -1;
                        if ( (LA19_485=='N'||LA19_485=='n') ) {s = 522;}

                        else if ( ((LA19_485>='\u0000' && LA19_485<='M')||(LA19_485>='O' && LA19_485<='m')||(LA19_485>='o' && LA19_485<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA19_312 = input.LA(1);

                        s = -1;
                        if ( ((LA19_312>='0' && LA19_312<='9')) ) {s = 397;}

                        else if ( ((LA19_312>='\u0000' && LA19_312<='/')||(LA19_312>=':' && LA19_312<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA19_495 = input.LA(1);

                        s = -1;
                        if ( ((LA19_495>='0' && LA19_495<='9')) ) {s = 530;}

                        else if ( ((LA19_495>='\u0000' && LA19_495<='/')||(LA19_495>=':' && LA19_495<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA19_530 = input.LA(1);

                        s = -1;
                        if ( (LA19_530=='-') ) {s = 555;}

                        else if ( ((LA19_530>='\u0000' && LA19_530<=',')||(LA19_530>='.' && LA19_530<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA19_397 = input.LA(1);

                        s = -1;
                        if ( (LA19_397=='-') ) {s = 453;}

                        else if ( ((LA19_397>='\u0000' && LA19_397<=',')||(LA19_397>='.' && LA19_397<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA19_453 = input.LA(1);

                        s = -1;
                        if ( ((LA19_453>='0' && LA19_453<='1')) ) {s = 495;}

                        else if ( ((LA19_453>='\u0000' && LA19_453<='/')||(LA19_453>='2' && LA19_453<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA19_591 = input.LA(1);

                        s = -1;
                        if ( (LA19_591=='\'') ) {s = 602;}

                        else if ( ((LA19_591>='\u0000' && LA19_591<='&')||(LA19_591>='(' && LA19_591<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA19_555 = input.LA(1);

                        s = -1;
                        if ( ((LA19_555>='0' && LA19_555<='3')) ) {s = 575;}

                        else if ( ((LA19_555>='\u0000' && LA19_555<='/')||(LA19_555>='4' && LA19_555<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA19_575 = input.LA(1);

                        s = -1;
                        if ( ((LA19_575>='0' && LA19_575<='9')) ) {s = 591;}

                        else if ( ((LA19_575>='\u0000' && LA19_575<='/')||(LA19_575>=':' && LA19_575<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA19_522 = input.LA(1);

                        s = -1;
                        if ( (LA19_522==']') ) {s = 552;}

                        else if ( ((LA19_522>='\u0000' && LA19_522<='\\')||(LA19_522>='^' && LA19_522<='\uFFFF')) ) {s = 92;}

                        else s = 553;

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA19_592 = input.LA(1);

                        s = -1;
                        if ( ((LA19_592>='0' && LA19_592<='9')) ) {s = 603;}

                        else if ( ((LA19_592>='\u0000' && LA19_592<='/')||(LA19_592>=':' && LA19_592<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA19_603 = input.LA(1);

                        s = -1;
                        if ( ((LA19_603>='0' && LA19_603<='9')) ) {s = 612;}

                        else if ( ((LA19_603>='\u0000' && LA19_603<='/')||(LA19_603>=':' && LA19_603<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA19_556 = input.LA(1);

                        s = -1;
                        if ( (LA19_556=='.') ) {s = 576;}

                        else if ( ((LA19_556>='\u0000' && LA19_556<='-')||(LA19_556>='/' && LA19_556<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA19_576 = input.LA(1);

                        s = -1;
                        if ( ((LA19_576>='0' && LA19_576<='9')) ) {s = 592;}

                        else if ( ((LA19_576>='\u0000' && LA19_576<='/')||(LA19_576>=':' && LA19_576<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA19_612 = input.LA(1);

                        s = -1;
                        if ( (LA19_612=='\'') ) {s = 619;}

                        else if ( ((LA19_612>='\u0000' && LA19_612<='&')||(LA19_612>='(' && LA19_612<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA19_313 = input.LA(1);

                        s = -1;
                        if ( ((LA19_313>='0' && LA19_313<='9')) ) {s = 398;}

                        else if ( ((LA19_313>='\u0000' && LA19_313<='/')||(LA19_313>=':' && LA19_313<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA19_454 = input.LA(1);

                        s = -1;
                        if ( (LA19_454==':') ) {s = 496;}

                        else if ( ((LA19_454>='\u0000' && LA19_454<='9')||(LA19_454>=';' && LA19_454<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA19_398 = input.LA(1);

                        s = -1;
                        if ( ((LA19_398>='0' && LA19_398<='9')) ) {s = 454;}

                        else if ( ((LA19_398>='\u0000' && LA19_398<='/')||(LA19_398>=':' && LA19_398<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA19_531 = input.LA(1);

                        s = -1;
                        if ( ((LA19_531>='0' && LA19_531<='9')) ) {s = 556;}

                        else if ( ((LA19_531>='\u0000' && LA19_531<='/')||(LA19_531>=':' && LA19_531<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA19_496 = input.LA(1);

                        s = -1;
                        if ( ((LA19_496>='0' && LA19_496<='9')) ) {s = 531;}

                        else if ( ((LA19_496>='\u0000' && LA19_496<='/')||(LA19_496>=':' && LA19_496<='\uFFFF')) ) {s = 144;}

                        if ( s>=0 ) return s;
                        break;
                    case 33 : 
                        int LA19_439 = input.LA(1);

                        s = -1;
                        if ( (LA19_439=='E'||LA19_439=='e') ) {s = 486;}

                        else if ( ((LA19_439>='\u0000' && LA19_439<='D')||(LA19_439>='F' && LA19_439<='d')||(LA19_439>='f' && LA19_439<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 34 : 
                        int LA19_374 = input.LA(1);

                        s = -1;
                        if ( (LA19_374=='T'||LA19_374=='t') ) {s = 439;}

                        else if ( ((LA19_374>='\u0000' && LA19_374<='S')||(LA19_374>='U' && LA19_374<='s')||(LA19_374>='u' && LA19_374<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 35 : 
                        int LA19_486 = input.LA(1);

                        s = -1;
                        if ( (LA19_486=='R'||LA19_486=='r') ) {s = 523;}

                        else if ( ((LA19_486>='\u0000' && LA19_486<='Q')||(LA19_486>='S' && LA19_486<='q')||(LA19_486>='s' && LA19_486<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 36 : 
                        int LA19_91 = input.LA(1);

                        s = -1;
                        if ( (LA19_91=='R'||LA19_91=='r') ) {s = 196;}

                        else if ( ((LA19_91>='\u0000' && LA19_91<='Q')||(LA19_91>='S' && LA19_91<='q')||(LA19_91>='s' && LA19_91<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 37 : 
                        int LA19_282 = input.LA(1);

                        s = -1;
                        if ( (LA19_282=='A'||LA19_282=='a') ) {s = 374;}

                        else if ( ((LA19_282>='\u0000' && LA19_282<='@')||(LA19_282>='B' && LA19_282<='`')||(LA19_282>='b' && LA19_282<='\uFFFF')) ) {s = 92;}

                        if ( s>=0 ) return s;
                        break;
                    case 38 : 
                        int LA19_196 = input.LA(1);

                        s = -1;
                        if ( (LA19_196=='E'||LA19_196=='e') ) {s = 282;}

                        else if ( ((LA19_196>='\u0000' && LA19_196<='D')||(LA19_196>='F' && LA19_196<='d')||(LA19_196>='f' && LA19_196<='\uFFFF')) ) {s = 92;}

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
