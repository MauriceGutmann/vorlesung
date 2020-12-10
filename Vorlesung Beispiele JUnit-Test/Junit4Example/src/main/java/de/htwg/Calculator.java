package de.htwg;

public class Calculator {
	
	public float add(float x, float y) {
		return x+y;
	}
	
	public float div(float x, float y) throws MyException {
		if(y == 0) {
			throw new MyException("Division by zero!");
		}
		return x/y;
	}

}
