package com.hp.java8.hi.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lambdas playing.
 *
 * @author durdina
 *
 */
public class Lambdas {

    private List<String> data;
    private List<String> convertedData;
    
    public Lambdas(List<String> data) {
        super();
        this.data = data;
        this.convertedData = new ArrayList<>(this.data.size());
    }

    public void work(Operation op) {
        for (int i = 0; i < data.size(); i++) {
            try {
				convertedData.add(op.operation(data.get(i), i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public List<String> getData() {
        return Collections.unmodifiableList(convertedData);
    }
    
    public static String someMethodThatTakesString(String s) {
        System.out.println("someMethodThatTakesString called");
        return s;
    }

	public void work(Object op) {
		// TODO Auto-generated method stub
		
	}
    
}
