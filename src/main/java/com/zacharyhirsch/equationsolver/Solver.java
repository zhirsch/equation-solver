package com.zacharyhirsch.equationsolver;

public class Solver {

  public Solution solve(Equation equation) {
    Term lhs = equation.getLhs().getTerms().get(0);
    Term rhs = equation.getRhs().getTerms().get(0);
    return Solution.builder()
        .setVariable(lhs.getVariable().get())
        .setComparator(equation.getComparator())
        .setValue(rhs.getCoef() / lhs.getCoef())
        .build();
  }
}
