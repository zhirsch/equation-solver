package com.zacharyhirsch.equationsolver.parser;

import com.zacharyhirsch.equationsolver.Comparator;
import com.zacharyhirsch.equationsolver.Variable;

%%

%public
%class Lexer
%implements com.zacharyhirsch.equationsolver.parser.Symbols
%cupsym com.zacharyhirsch.equationsolver.parser.Symbols

%line
%column

%cup
%cupdebug

%{
  private java_cup.runtime.Symbol symbol(int type) {
      return new com.zacharyhirsch.equationsolver.parser.Symbol(type, yyline + 1, yycolumn + 1);
    }

    private java_cup.runtime.Symbol symbol(int type, Object value) {
      return new com.zacharyhirsch.equationsolver.parser.Symbol(type, yyline + 1, yycolumn + 1, value);
    }
%}

NUM = [:digit:]+
VAR = [x]
CMP = (<|>|<=|>=|=)

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

%%

{NUM}         { return symbol(NUM, Double.parseDouble(yytext())); }
{VAR}         { return symbol(VAR, Variable.parse(yytext())); }
{CMP}         { return symbol(CMP, Comparator.parse(yytext())); }

"*"           { return symbol(MULT); }
"+"           { return symbol(PLUS); }
"-"           { return symbol(MINUS); }

{WhiteSpace}  { /* ignore */ }

[^]           { throw new RuntimeException(String.format("Illegal character '%s' at %d:%d", yytext(), yyline, yycolumn)); }
