package com.intervale.calculator.engine.operation.impls.engeneer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum IngineerOperationEnum {
	SIN("sin"), COS("cos"), TG("tg");

	private static final Logger LOG = LoggerFactory.getLogger(IngineerOperationEnum.class);

	private String func;

	private IngineerOperationEnum(String simbol) {
		this.func = simbol;
	}

	public static IngineerOperationEnum getByStringSimbol(final String func) {
		IngineerOperationEnum[] list = IngineerOperationEnum.values();
		for (IngineerOperationEnum element : list) {
			
			if (func.equals(element.func)) {
				LOG.info("element --- {}", element);
				return element;
			}
		}
		return null;
		//throw new Unknownoperation();
	}
}