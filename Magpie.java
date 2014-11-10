import java.util.Random;
public class Magpie
{  
  public String getGreeting()
  {
    return "Hello, let's talk.";
  }
  String lastPhrase = "";//stores last phrase inputted
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  public String getResponse(String statement)
  {
    String response = "";
    if (statement.length() == 0)//if no input, then requests input
    {
      response = "Say something, please.";
    }
    else if (lastPhrase.equals(statement))//if same input, requests different input
    {
      response = "Say something different!";
    }
    else if (findKeyword(statement, "no") >= 0)//if no is in the input, responds
    {
      response = "Why so negative?";
    }
    else if (findKeyword(statement, "mother") >= 0//looks for keywords in input
               || findKeyword(statement, "father") >= 0//or statements
               || findKeyword(statement, "sister") >= 0
               || findKeyword(statement, "brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (findKeyword(statement,"dog") >=0//looks for keywords in the input
               || findKeyword(statement,"cat") >=0)
    {
      response = "Tell me more about your pets";
    }
    
    else if (findKeyword(statement,"Kiang") >=0//looks for keywords in input
               || findKeyword(statement,"kiang") >=0)
    {
      response = "He sounds like a good teacher";
    }
    else if (statement.trim().length() == 0)//trims spaces off the ends of the input, then compares length to 0
    {
      response = "Say something I'm giving up on you";
    }
    else if (findKeyword(statement,"happy") >=0//looks for keywords in input
               || findKeyword(statement,"Happy") >=0)
    {
      response = "Why so happy?";
    }
    else if (findKeyword(statement,"school") >=0) // If talks about school, asks about it
    {
      response = "Tell me more about school";
    }
    else if (findKeyword(statement,"love") >=0) // If talks about love, asks about it
    {
      response = "What do you think about love?";
    }
    else if (findKeyword(statement, "I want", 0) >= 0)//if input has "I want" in it, uses transform method to give reply
    {
      response = transformIWantStatement(statement);
    }  
    else if (findKeyword(statement, "Do you", 0) >=0)
    {
      response = transformDoYouStatement(statement);
    }
    
    int psn = findKeyword(statement, "I", 0);// if input has I something you in it, uses transform method to give reply
    if (psn >= 0// if position >=0 and the statement contains you
          && findKeyword(statement, "you", psn) >= 0)
    {
      response = transformIMeStatement(statement);
    }  
    else
    {
      response = getRandomResponse();
    }
    lastPhrase = statement;
    System.out.println(statement +" is statement");
    return response;
  }
  
  /**
   * Search for one word in phrase. The search is not case
   * sensitive. This method will check that the given goal
   * is not a substring of a longer string (so, for
   * example, "I know" does not contain "no").
   * 
   * @param statement
   *            the string to search
   * @param goal
   *            the string to search for
   * @param startPos
   *            the character of the string to begin the
   *            search at
   * @return the index of the first occurrence of goal in
   *         statement or -1 if it's not found
   */
  private int findKeyword(String statement, String goal,
                          int startPos)
  {
    String phrase = statement.trim();
    // The only change to incorporate the startPos is in
    // the line below
    int psn = phrase.toLowerCase().indexOf(
                                           goal.toLowerCase(), startPos);
    
    // Refinement--make sure the goal isn't part of a
    // word
    while (psn >= 0)
    {
      // Find the string of length 1 before and after
      // the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring(psn - 1, psn)
          .toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(
                                 psn + goal.length(),
                                 psn + goal.length() + 1)
          .toLowerCase();
      }
      
      // If before and after aren't letters, we've
      // found the word
      if (((before.compareTo("a") < 0) || (before
                                             .compareTo("z") > 0)) // before is not a
            // letter
            && ((after.compareTo("a") < 0) || (after
                                                 .compareTo("z") > 0)))
      {
        return psn;
      }
      
      // The last position didn't work, so let's find
      // the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(),
                           psn + 1);
      
    }
    
    return -1;
  }
  
  /**
   * Search for one word in phrase. The search is not case
   * sensitive. This method will check that the given goal
   * is not a substring of a longer string (so, for
   * example, "I know" does not contain "no"). The search
   * begins at the beginning of the string.
   * 
   * @param statement
   *            the string to search
   * @param goal
   *            the string to search for
   * @return the index of the first occurrence of goal in
   *         statement or -1 if it's not found
   */
  private int findKeyword(String statement, String goal)
  {
    return findKeyword(statement, goal, 0);
  }
  
  private String transformIWantStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement
                                            .length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement
                                        .length() - 1);
    }
    int psn = findKeyword (statement, "I want", 0);//looks for I want in statement
    String restOfStatement = statement.substring(psn + 7).trim();//the rest of the statement is statement - the first 7 characters
    return "Would you really be happy if you had " + restOfStatement + "?";//returns the rest of the statement
  }
  
  private String transformDoYouStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement
                                            .length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement
                                        .length() - 1);
    }
    int psn = findKeyword (statement, "Do you", 0);//looks for I want in statement
    String restOfStatement = statement.substring(psn + 7).trim();//the rest of the statement is statement - the first 7 characters
    return "Why do you think that I " + restOfStatement + "?";//returns the rest of the statement
  }
  
  
  private String transformIMeStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement
                                            .length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement
                                        .length() - 1);
    }
    
    int psnOfI = findKeyword (statement, "I", 0);//looks for the posiiton of I
    int psnOfMe = findKeyword (statement, "you", psnOfI + 1);// looks for the position of you ONLY if it is past I
    
    String restOfStatement = statement.substring(psnOfI + 1, psnOfMe).trim();//trims everything between the two positions
    return "Why do you " + restOfStatement + " me?";//returns
  }
  
    /**
   * Pick a default response to use if nothing else fits.
   * 
   * @return a non-committal string
   */
  private String getRandomResponse ()
  {
    Random r = new Random ();
    return randomResponses [r.nextInt(randomResponses.length)];
  }
  
  private String [] randomResponses = {"Interesting, tell me more",
    "Hmmm.",
    "Do you really think so?",
    "You don't say.",
    "Why?",
    "Are you sure?",
    "...",
    "Is that right?",
    "Why do you say that?"
  };
  
}
