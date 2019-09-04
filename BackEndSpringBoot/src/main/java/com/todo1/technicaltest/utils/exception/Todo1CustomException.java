package com.todo1.technicaltest.utils.exception;

/**
 * 
 * @author Julian Valencia
 * 29/08/2019
 */
public class Todo1CustomException extends Exception {

	private static final long serialVersionUID = -940541417432082672L;

	public Todo1CustomException(String message) {
        super(message);
    }
    
    public Todo1CustomException(Throwable throwable){
    	super(throwable);
    }
    
    public Todo1CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
