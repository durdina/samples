package com.hp.java8.hi.lambda;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LambdasTest {
	
	private String myString;

	@Test
	public void workLambda() {
		Lambdas lambdas = new Lambdas(Arrays.asList("A", "B", "C"));
		
		lambdas.work((item, index) ->  "**" + item + index + "**");
		
		List<String> data = lambdas.getData();

		data.stream().forEach((a) -> System.out.print(a));

		assertEquals(Arrays.asList("**A0**", "**B1**", "**C2**"), data);
		
	}

	@Test
	public void workStaticReference() {
		Lambdas lambdas = new Lambdas(Arrays.asList("A", "B", "C"));
		
		lambdas.work(LambdasTest::myMethod);

		List<String> data = lambdas.getData();
		data.stream().forEach(System.out::println);

		assertEquals(Arrays.asList("**A0**", "**B1**", "**C2**"), data);
	}
	
	@Test
	public void workMemberReference() {
		List<String> asList = Arrays.asList("A", "B", "C");
		asList.stream().map(String::toLowerCase).forEach(System.out::println);
	}

	private static String myMethod(String item, int index) {
		return "**" + item + index + "**";
	}

}
