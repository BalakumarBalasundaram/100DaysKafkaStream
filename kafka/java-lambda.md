interface Arithmatic {
	int add(int a, int b);
}

Arithmatic addable = new Arithmatic() {
			@Override
			public int add(int a, int b) {
				return a + b;
			}
		};
		addable.add(10, 20);

		// Lambda expression without return keyword.
		Arithmatic withLambda = (a, b) -> (a + b);


References:
https://github.com/divitngoc/spring-boot-simple-rest-api/tree/master
