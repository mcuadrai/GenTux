package hello;

public class HelloRun {

	public static void main(String[] args) {
		HelloWorldTemplate helloworld = new HelloWorldTemplate();
		 String result = helloworld.generate(null);
		 System.out.println(result);
	}

}
