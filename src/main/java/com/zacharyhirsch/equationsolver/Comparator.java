package com.zacharyhirsch.equationsolver;

public enum Comparator {
  LT("<"),
  LE("<="),
  GT(">"),
  GE(">="),
  EQ("="),
  ;

  private final String str;

  Comparator(String str) {
    this.str = str;
  }

  public static Comparator parse(String s) {
    switch (s) {
      case "<":
        return LT;
      case "<=":
        return LE;
      case ">":
        return GT;
      case ">=":
        return GE;
      case "=":
        return EQ;
      default:
        throw new IllegalArgumentException(String.format("unknown comparator '%s'", s));
    }
  }

  @Override
  public String toString() {
    return str;
  }

  public Comparator inverse() {
    switch (this) {
      case LT:
        return GT;
      case LE:
        return GE;
      case GT:
        return LT;
      case GE:
        return LE;
      default:
        return this;
    }
  }
}
