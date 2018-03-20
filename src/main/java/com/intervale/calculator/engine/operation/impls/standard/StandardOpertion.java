package com.intervale.calculator.engine.operation.impls.standard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intervale.calculator.engine.operation.IOperation;
import com.intervale.calculator.engine.operation.impls.exception.NotMatchOperation;

public class StandardOpertion implements IOperation {
	static final Logger LOG = LoggerFactory.getLogger(StandardOpertion.class);

	private String expression;

	public StandardOpertion(String expression) {
		this.expression = expression;
	}

	public double add(double a, double b) {
		return a + b;
	}

	public double minus(double a, double b) {
		return a - b;
	}

	public double umn(double a, double b) {
		return a * b;
	}

	public double del(double a, double b) {
		return a / b;
	}

	public double proc(double a, double b) {
		return a * b / 100;
	}

	public double pow(double a, double b) {
		return Math.pow(a, b);
	}

	public static void main(String[] args) {
		StandardOpertion s = new StandardOpertion("2+1.55");

		try {
			LOG.info("result of Standard operation {}", s.execute());
		} catch (NotMatchOperation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public double execute() throws NotMatchOperation {
		Pattern p = Pattern.compile("(.*)([+,-,%,*,/,^])(.*)");
		Matcher m = p.matcher(expression);

		if (m.matches()) {
			StandartOperationEnum simbol = StandartOperationEnum.getByStringSimbol(m.group(2));

			LOG.debug("PROC");
			double operand1 = Double.parseDouble(m.group(1));
			double operand2 = Double.parseDouble(m.group(3));

			LOG.debug("operand1: {}", operand1);
			LOG.debug("simbol: {}", simbol);
			LOG.debug("operand2: {}", operand2);

			switch (simbol) {
			case PLUS:
				LOG.debug("PLUS");
				return add(operand1, operand2);
			case MINUS:
				LOG.debug("MINUS");
				return minus(operand1, operand2);
			case UMN:
				LOG.debug("UMN");
				return umn(operand1, operand2);
			case DEL:
				LOG.debug("DEL");
				return del(operand1, operand2);
			case PROC:
				LOG.debug("PROC");
				return proc(operand1, operand2);
			case POW:
				LOG.debug("POW");
				return pow(operand1, operand2);
			default:
				LOG.debug("UNKNOWN");
				return 0;
			}
		}
	throw new NotMatchOperation();
	}

}
