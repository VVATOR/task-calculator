package com.intervale.calculator.engine.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intervale.calculator.engine.operation.impls.exception.NotMatchOperation;
import com.intervale.calculator.engine.operation.impls.standard.StandardOpertion;

public final class OperationFactory {
	static final Logger LOG = LoggerFactory.getLogger(OperationFactory.class);

	private OperationFactory() {
	}

	public static double compute(final String expression) {

		IOperation operation = new StandardOpertion(expression);

		try {
			LOG.info("compute result: {}", operation.execute());
			return operation.execute();
		} catch (NotMatchOperation e) {

		}
		return 0;
		
		
	}

}
