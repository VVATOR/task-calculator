package com.intervale.calculator.engine.operation.impls.engeneer;

import com.intervale.calculator.engine.operation.IOperation;

public class IngineerOperation implements IOperation {
	private String expression;

	public IngineerOperation(String expression) {
		this.expression = expression;
	}

	double sin(double value) {
		return Math.sin(value);
	}

	double cos(double value) {
		return Math.cos(value);
	}

	double tg(double value) {
		return Math.tan(value);
	}

	@Override
	public double execute() {
		// TODO Auto-generated method stub
		return 0;
	}
}
