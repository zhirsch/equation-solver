package com.zacharyhirsch.equationsolver;

import com.zacharyhirsch.equationsolver.parser.Parser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Application {

  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    for (String s = reader.readLine(); s != null; s = reader.readLine()) {
      Parser parser = new Parser(new StringReader(s));
      Equation equation = (Equation) parser.parse().value;

      System.out.println(String.format("Equation: %s", equation));
      System.out.println(String.format("Solution: %s", new Solver().solve(equation.normalize())));
    }
  }
}
