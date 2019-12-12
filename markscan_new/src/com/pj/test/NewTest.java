package com.pj.test;

public class NewTest {

	public NewTest() {
		// TODO Auto-generated constructor stub
	}

	public String execute() {
		System.out.println("page id =  " + pageid);
		return "perror";
	}

	int pageid;

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

	public static void main(String... ar) {
		String s1 = "http://hdvidz.co/video/file/Naa-Peru-Meenakshi-%7C-11th?id=rj5e--8vQb4";
		String s2 = "Naa Peru Meenakshi";
		String splitStringS2[] = s2.split(" ");// using blank space to split
		int i = 0;
		for (String a : splitStringS2) {
			if (s1.contains(a)) {
				i = i + 1;
			} else {
				System.out.println("break perform");
				i = 0;
				break;
			}
		}
		System.out.println("value of i===  " + i);
	}
}
