package com.zacharyhirsch.equationsolver;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Variable {

  public static final Variable X = Variable.builder().setName("x").build();

  public abstract String getName();

  public static Builder builder() {
    return new AutoValue_Variable.Builder();
  }

  public static Variable parse(String s) {
    if (s.equals("x")) {
      return Variable.X;
    }
    throw new IllegalArgumentException(String.format("unknown variable: '%s'", s));
  }

  @Override
  public String toString() {
    return getName();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setName(String name);

    public abstract Variable build();
  }
}
