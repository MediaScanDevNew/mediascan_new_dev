package com.pj.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public ListTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	List<Integer>pj= new ArrayList<>();
	public void  test() {
		for(int i=1; i<=10000;i++)
		{
			pj.add(i);
		}
		System.out.println(pj.size());
		
	}
}
