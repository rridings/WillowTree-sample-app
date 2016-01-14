package com.willowtree;

/**
 * Response is a helper class to standardized formatted messages and exception messages.
 * 
 * @author    Rob Ridings
 * @version   1.0
 * @since     2016-01-13 
 */
public class Response
{
  private String message;

  public Response(String message, Object... args)
  {
    this.message = String.format(message, args);
  }

  public Response(Exception e)
  {
    this.message = e.getMessage();
  }

  public String getMessage()
  {
    return this.message;
  }
}
