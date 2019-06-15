package com.zacharyhirsch.equationsolver;

import com.google.auto.value.AutoValue;
import java.text.DecimalFormat;
import java.util.Optional;

@AutoValue
public abstract class Term {

  public static final Term ZERO = Term.builder().setCoef(0).build();

  public abstract double getCoef();

  public abstract Optional<Variable> getVariable();

  public static Builder builder() {
    return new AutoValue_Term.Builder();
  }

  public abstract Builder toBuilder();

  @Override
  public String toString() {
    if (getVariable().isPresent()) {
      if (getCoef() == 1) {
        return getVariable().get().toString();
      }
      return String.format("%s%s", new DecimalFormat().format(getCoef()), getVariable().get());
    }
    return String.format("%s", new DecimalFormat().format(getCoef()));
  }

  public boolean isZero() {
    return getCoef() == 0;
  }

  public Term inverse() {
    return toBuilder().setCoef(-getCoef()).build();
  }

  public Term plus(Term rhs) {
    if (isZero()) {
      return rhs;
    }
    if (rhs.isZero()) {
      return this;
    }
    if (!getVariable().equals(rhs.getVariable())) {
      throw new IllegalArgumentException(
          String.format("can't add to terms with different variables: %s + %s", this, rhs));
    }
    return toBuilder().setCoef(getCoef() + rhs.getCoef()).build();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setCoef(double coefficient);

    public abstract Builder setVariable(Variable variable);

    public abstract Term build();
  }
}
