package com.zacharyhirsch.equationsolver;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

@AutoValue
public abstract class Expression {

  public abstract ImmutableList<Term> getTerms();

  public static Builder builder() {
    return new AutoValue_Expression.Builder();
  }

  public abstract Builder toBuilder();

  @Override
  public String toString() {
    if (getTerms().isEmpty()) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    builder.append(getTerms().get(0).toString());
    for (int i = 1; i < getTerms().size(); i++) {
      if (getTerms().get(i).getCoef() < 0) {
        builder.append(" - ");
        builder.append(getTerms().get(i).inverse().toString());
      } else {
        builder.append(" + ");
        builder.append(getTerms().get(i).toString());
      }
    }
    return builder.toString();
  }

  public Expression inverse() {
    return Expression.builder()
        .setTerms(getTerms().stream().map(Term::inverse).collect(toImmutableList()))
        .build();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public Builder addTerm(Term term) {
      termsBuilder().add(term);
      return this;
    }

    public abstract Builder setTerms(ImmutableList<Term> collect);

    public abstract Expression build();

    abstract ImmutableList.Builder<Term> termsBuilder();
  }
}
