package hello;

public class HelloWorldTemplate
{
  protected static String nl;
  public static synchronized HelloWorldTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    HelloWorldTemplate result = new HelloWorldTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + " Hello, world5!";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
