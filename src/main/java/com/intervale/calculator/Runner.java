package com.intervale.calculator;

import com.intervale.calculator.engine.Expression;

public class Runner {

	public static void main(String[] args) {

		
		System.out.println(Boolean.parseBoolean("false"));
		System.out.println(Boolean.parseBoolean("true"));
		System.out.println(Boolean.parseBoolean("False"));
		System.out.println(Boolean.parseBoolean("True"));
		System.out.println(Boolean.parseBoolean("yes"));
		System.out.println(Boolean.parseBoolean(null));
		
		//Expression expression = new Expression("5 + (2 * (3 ^ 2)) + (((2 ^ 3) * 5) / 10)");

	}

}
