package com.cafe.d4jtest.more;

import java.io.File;

public class ContextInvoke {
	public static void main (String[] args) {
//		System.out.println("hello");
		ContextIter dom4jParser = new ContextIter(new File("Context.xml"));
        dom4jParser.traversalDocumentByIterator();
	}

}
