package com.fdmgroup.tdd.gradecalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradeCalculatorServiceTest {

	public static final double PASS_BOUND = 75;
	public static final double MERIT_BOUND = 80;
	public static final double DISTINCTION_BOUND = 90;

	private GradeCalculator gradeCalculator;

	@Before
	public void setUp() {
		gradeCalculator = new GradeCalculator();
	}

	@After
	public void collector() {
		gradeCalculator = null;
	}

	@Test
	public void test_GetGradeReturnsFailGrade_WhenMarkIsZero() {
		String grader = gradeCalculator.getClassification(0.0);

		assertEquals("Failed a passing grade", grader);
	}

	@Test
	public void test_GetGradeReturnsFailGrade_WhenMarkIsPassBoundMinusOne() {
		String grader = gradeCalculator.getClassification(PASS_BOUND - 1.0);
		assertEquals("Failed a passing grade", grader);
	}

	@Test
	public void test_GetGradeReturnsPass_WhenMarkIsPassBound() {
		String grader = gradeCalculator.getClassification(PASS_BOUND);
		assertEquals("Pass achieved", grader);
	}

	@Test
	public void test_GetGradeReturnsPass_WhenMarkIsPassBoundPlusOne() {
		String grader = gradeCalculator.getClassification(PASS_BOUND + 1.0);
		assertEquals("Pass achieved", grader);
	}

	@Test
	public void test_GetGradesReturnsMerit_WhenMarkIsMeritBound() {
		String grader = gradeCalculator.getClassification(MERIT_BOUND);
		assertEquals("Merit achieved", grader);
	}

	@Test
	public void test_GetGradesReturnsPass_WhenMarkIsMeritBoundMinusOne() {
		String grader = gradeCalculator.getClassification(MERIT_BOUND - 1.0);
		assertEquals("Pass achieved", grader);
	}

	@Test
	public void test_GetGradesReturnsMerit_WhenMarkIsMeritBoundPlusOne() {
		String grader = gradeCalculator.getClassification(MERIT_BOUND + 1.0);
		assertEquals("Merit achieved", grader);
	}

	@Test
	public void test_GetGradeReturnsDistinction_WhenMarkIsDistinctionBound() {
		String grader = gradeCalculator.getClassification(DISTINCTION_BOUND);
		assertEquals("Distinction achieved", grader);

	}

	@Test
	public void test_GetGradeReturnMerit_WhenMarkIsDistinctionBoundMinusOne() {
		String grader = gradeCalculator.getClassification(DISTINCTION_BOUND - 1.0);
		assertEquals("Merit achieved", grader);
	}

	@Test
	public void test_GetGradeReturnsDistinction_WhenMarkIsDistinctionBoundPlusOne() {
		String grader = gradeCalculator.getClassification(DISTINCTION_BOUND + 1.0);
		assertEquals("Distinction achieved", grader);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_ThrowsAnIllegalArgumentException_WhenMarkIsANegative() {
		@SuppressWarnings("unused")
		String unusedGrader = gradeCalculator.getClassification(-1.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_ThrowsAnIllegalArgumentException_WhenMarkIsAbove100() {
		@SuppressWarnings("unused")
		String unusedGrader = gradeCalculator.getClassification(101.1);
	}

}
