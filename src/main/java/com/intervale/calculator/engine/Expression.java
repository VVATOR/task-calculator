package com.intervale.calculator.engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intervale.calculator.engine.operation.OperationFactory;
import com.intervale.calculator.utils.OperationUtils;

public class Expression {
	static final Logger LOG = LoggerFactory.getLogger(Expression.class);
	private String expression = "";

	private Expression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expression(String expression) {
		this.expression = expression;
	}

	public double compute() {
		if (this.isExpression()) {
			double result = OperationFactory.compute(expression);
			LOG.debug("compute result: {}", result);
			return result;
		}
		
		return Double.parseDouble(OperationUtils.bracesOffForNumber(expression));
	}

	@Override
	public String toString() {
		return "Expression [expression=" + expression + "]";
	}

	public String getExpression() {
		return expression;
	}

	public boolean isExpression() {
		Pattern p = Pattern.compile("[+,-,%,*,/,^]");
		Matcher m = p.matcher(expression);
		if (m.find()) {
			return true;
		}	
		return false;
	}

}
