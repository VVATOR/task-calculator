package com.intervale.calculator.engine.operation;

import com.intervale.calculator.engine.operation.impls.exception.NotMatchOperation;

public interface IOperation {
	double execute() throws NotMatchOperation ;
}
