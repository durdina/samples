package com.durdina.samples.concurrency;
import java.util.List;
import java.util.ArrayList;

public class Resource {

	List<Integer> nums = new ArrayList<Integer>();

	public int readHighest() {
		if (nums.size() > 0) {
			return nums.get(nums.size() - 1);
		} else {
			return 0;
		}
	}

	public void write(int i) {
		
		synchronized (nums) {
			if (nums.size() > 0) {
				int last = nums.get(nums.size() - 1);
				nums.add(last + 1);
			} else {
				nums.add(1);
			}
		}
	}

	public Integer[] readAll() {
		Integer[] a = new Integer[nums.size()];
		return nums.toArray(a);
	}

}
