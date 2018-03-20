package com.intervale.calculator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intervale.calculator.engine.Expression;

public final class OperationUtils {
	static final Logger LOG = LoggerFactory.getLogger(OperationUtils.class);

	private OperationUtils() {
		super();
	}

	public static boolean verifyCountBraces(String expression) {
		int r = 0;
		int l = 0;
		for (char element : expression.toCharArray()) {
			if (element == '(') {
				l++;
			}
			if (element == ')') {
				r++;
			}
		}
		if (r == l) {
			LOG.debug("Expression include correct count braces: left-{} right-{}", l, r);
			return true;
		}
		LOG.debug("Expression include INCORRECT count braces: left-{} right-{}", l, r);
		return false;
	}

	public static int getExpressionMaxIncludeLevel(String expression) {
		int curr = 0;
		int maxInc = 0;
		for (char element : expression.toCharArray()) {
			if (element == '(') {
				curr++;
			}
			if (element == ')') {
				curr--;
			}
			if (maxInc < curr) {
				maxInc = curr;
			}
		}
		return maxInc;
	}

	private static int stepNumber=0;
	
	private static Expression rec(String expression) {
		LOG.info("STEP #{}",++stepNumber);
		LOG.info("EXPRESSION: {}",expression);
		
		expression = bracesOffForNumber(expression);
		Expression w = new Expression(expression);
		if (w.isExpression() != true) {
			return w;
		}

		String findExpression = "";
		int max = getExpressionMaxIncludeLevel(expression);
		LOG.info("level: {}", max);
		
		int curr = 0;
		String newE = "";
		int j = 0;
		int startPos = 0;
		int endPos = 0;
		String result = "";
		for (int i = 0; i < expression.length(); i++) {
			newE += expression.charAt(i);
			if (expression.charAt(i) == '(') {
				curr++;
			}
			if (expression.charAt(i) == ')') {
				curr--;
			}
			if ((max == curr) && (i < expression.length())) {
				startPos = i + 1;
				while (expression.charAt(i) != ')') {
					i++;
				}
				endPos = i;
				findExpression = expression.substring(startPos, endPos);
				break;
			}
		}
		LOG.info("findExpression: {}", findExpression);
		result = new Expression(expression.substring(startPos, endPos)).compute() + "";
		newE = expression.substring(0, startPos) + result + expression.substring(endPos, expression.length());

		LOG.info("result: {}", result);
		LOG.info("new view: {}", newE);
		LOG.info("\n\n");

		Expression ex = new Expression(newE);
		if (ex.isExpression()) {
			LOG.debug("OPEN");
			ex = rec(newE);
		}

		LOG.debug("END");
		return ex;
	}

	public static String bracesAddForOperation(String expression) {
		LOG.info("bracesAddForOperation");
		Pattern pattern = Pattern.compile("[^)][.0-9]*\\*[.0-9]*");
		Matcher matcher = pattern.matcher(expression);
		if (matcher.find()) {

			expression = expression.substring(0, matcher.start()) + "("
					+ expression.substring(matcher.start(), matcher.end()) + ")"
					+ expression.substring(matcher.end(), expression.length());
			LOG.info("bbbbbbb {}", expression);
		}
		return expression;
	}



	public static void main(String[] args) {
		// String expression = "5 + (2 * (3 ^ 2)) + (((((2 * 3))) * 5) / 10)";
		String expression = "((5+4)+((16/2)^(1+1)))";

		expression = expression.replaceAll(" ", "");
		LOG.info("expression: {}", expression);
		verifyCountBraces(expression);

		LOG.info("\n\n");
		LOG.info("result = {}", calculate(expression));
		
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*while(true) {
			
		}
*/
	}

	public static double calculate(String expression){
		return rec(expression).compute();		
	}
	
	public static String bracesOffForNumber(String expression) {
		Pattern pattern = Pattern.compile("\\([^.][0-9.]*[^.]\\)");
		Matcher matcher = pattern.matcher(expression);
		if (matcher.find()) {
			expression = expression.substring(0, matcher.start())
					+ expression.substring(matcher.start() + 1, matcher.end() - 1)
					+ expression.substring(matcher.end(), expression.length());
			LOG.info("BRACKET OFF: {}", expression);
		}
		LOG.debug("ssssssssssssssssss {}", expression);
		return expression;
	}
}
