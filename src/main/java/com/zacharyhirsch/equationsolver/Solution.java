package com.zacharyhirsch.equationsolver;

import com.google.auto.value.AutoValue;
import java.text.DecimalFormat;

@AutoValue
public abstract class Solution {

  public abstract Variable getVariable();

  public abstract Comparator getComparator();

  public abstract double getValue();

  public static Builder builder() {
    return new AutoValue_Solution.Builder();
  }

  @Override
  public String toString() {
    return String.format(
        "%s %s %s", getVariable(), getComparator(), new DecimalFormat().format(getValue()));
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setVariable(Variable variable);

    public abstract Builder setComparator(Comparator comparator);

    public abstract Builder setValue(double value);

    public abstract Solution build();
  }
}
