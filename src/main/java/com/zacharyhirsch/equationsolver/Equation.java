package com.zacharyhirsch.equationsolver;

import com.google.auto.value.AutoValue;
import java.util.stream.Stream;

@AutoValue
public abstract class Equation {

  public abstract Expression getLhs();

  public abstract Expression getRhs();

  public abstract Comparator getComparator();

  public static Builder builder() {
    return new AutoValue_Equation.Builder();
  }

  public abstract Builder toBuilder();

  public Equation normalize() {
    Expression lhs =
        Expression.builder()
            .addTerm(
                Stream.concat(
                        getLhs().getTerms().stream().filter(x -> x.getVariable().isPresent()),
                        getRhs().getTerms().stream()
                            .filter(x -> x.getVariable().isPresent())
                            .map(Term::inverse))
                    .reduce(Term.ZERO, Term::plus))
            .build();
    Expression rhs =
        Expression.builder()
            .addTerm(
                Stream.concat(
                        getLhs().getTerms().stream()
                            .filter(x -> x.getVariable().isEmpty())
                            .map(Term::inverse),
                        getRhs().getTerms().stream().filter(x -> x.getVariable().isEmpty()))
                    .reduce(Term.ZERO, Term::plus))
            .build();
    if (lhs.getTerms().get(0).getCoef() < 0) {
      return toBuilder()
          .setLhs(lhs.inverse())
          .setRhs(rhs.inverse())
          .setComparator(getComparator().inverse())
          .build();
    }
    return toBuilder().setLhs(lhs).setRhs(rhs).build();
  }

  @Override
  public String toString() {
    return String.format("%s %s %s", getLhs(), getComparator(), getRhs());
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setLhs(Expression lhs);

    public abstract Builder setRhs(Expression rhs);

    public abstract Builder setComparator(Comparator comparator);

    public abstract Equation build();
  }
}
