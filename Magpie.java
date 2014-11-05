/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk.";
  }
  
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  String lastPhrase = "";
  
  public String getResponse(String statement)
  {
    String response = "";
  //  System.out.println("lp" +lastPhrase +"state" +statement);// debug
    if (statement.indexOf("no") >= 0)
    {
      response = "Why so negative?";
    }
    else if (lastPhrase.indexOf("") == statement.indexOf(""))
    {
      response = "Say something different";
    }
    else if (statement.indexOf("mother") >= 0
          || statement.indexOf("father") >= 0
          || statement.indexOf("sister") >= 0
          || statement.indexOf("brother") >= 0
          || statement.indexOf("mom") >=0
          || statement.indexOf("dad") >=0)
    {
      response = "Tell me more about your family.";
    }
    else if (statement.indexOf("dog") >=0
               || statement.indexOf("cat") >=0)
    {
      response = "Tell me more about your pets";
    }
    
    else if (statement.indexOf("Kiang") >=0
               || statement.indexOf("kiang") >=0)
    {
      response = "He sounds like a good teacher";
    }
    else if (statement.trim().length() == 0)
    {
      response = "Say something I'm giving up on you";
    }
    else if (statement.indexOf("happy") >=0
               || statement.indexOf("Happy") >=0)
    {
      response = "Why so happy?";
    }
    else if (statement.indexOf("school") >=0) // If talks about school, asks about it
    {
      response = "Tell me more about school";
    }
    else if (statement.indexOf("love") >=0) // If talks about love, asks about it
    {
      response = "What do you think about love?";
    }
    else //For anything that doesn't match, use a generic response
    {
      response = getRandomResponse();
    }
    lastPhrase = statement;
   // System.out.println(lastPhrase+" is lp"); // also debug
    return response; // Returning response
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse()
  {
    final int NUMBER_OF_RESPONSES = 6;
    double r = Math.random();
    int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
    String response = "";
    
    if (whichResponse == 0)
    {
      response = "Interesting, tell me more.";
    }
    else if (whichResponse == 1)
    {
      response = "Hmmm.";
    }
    else if (whichResponse == 2)
    {
      response = "Do you really think so?";
    }
    else if (whichResponse == 3)
    {
      response = "You don't say.";
    }
    else if (whichResponse == 4)
    {
      response = "What do you think?";
    }
    else if (whichResponse == 5)
    {
      response = "Why?";
    }
    return response;
  }
}
