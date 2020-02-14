package com.fdmgroup.tdd.gradecalculator;

public class GradeCalculator implements GradeCalculatorService {

	public static final double PASS = 75;
	public static final double MERIT = 80;
	public static final double DISTINCTION = 90;

	@Override
	public String getClassification(double mark) {
		if (mark >= 0 && mark < PASS) {
			return "Failed a passing grade";
		}

		else if (mark >= PASS && mark < MERIT) {
			return "Pass achieved";
		} else if (mark >= MERIT && mark < DISTINCTION) {
			return "Merit achieved";
		} else if (mark >= DISTINCTION && mark <= 100) {
			return "Distinction achieved";
		} else if (mark < 0 || mark > 100) {
			throw new IllegalArgumentException();
		}
		return null;
	}

}
