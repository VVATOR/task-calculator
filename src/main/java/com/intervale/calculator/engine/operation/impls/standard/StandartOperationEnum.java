package com.intervale.calculator.engine.operation.impls.standard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum StandartOperationEnum {

	PLUS("+"), MINUS("-"), DEL("/"), UMN("*"), PROC("%"), POW("^"), EMPTY("");

	private static final Logger LOG = LoggerFactory.getLogger(StandartOperationEnum.class);

	private String simbol;

	private StandartOperationEnum(String simbol) {
		this.simbol = simbol;
	}

	public static StandartOperationEnum getByStringSimbol(String simbol) {
		if (simbol == null) {
			return EMPTY;
		}

		StandartOperationEnum[] list = StandartOperationEnum.values();
		for (StandartOperationEnum element : list) {

			if (simbol.equals(element.simbol)) {
				LOG.debug("operation: {}", element);
				return element;
			}
		}
		return null;
		// throw new Unknownoperation();
	}

}