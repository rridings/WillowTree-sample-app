package com.willowtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Asset is the pojo for assets
 * 
 * @author    Rob Ridings
 * @version   1.0
 * @since     2016-01-13 
 */
public class Asset
{
  private String uri;
  
  private String name;

  private List<String> notes;
  
  public String getUri()
  {
    return uri;
  }

  public void setUri(String uri)
  {
    this.uri = uri;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
  
  public List<String> getNotes()
  {
    return notes;
  }

  public void setNotes(List<String> notes)
  {
    this.notes = notes;
  }
  
  public void addNote(String note)
  {
    if ( notes == null )
      notes = new ArrayList<String>();
    
    this.notes.add(note);
  }
  
  public void addNote(List<String> list)
  {
    if ( notes == null )
      notes = new ArrayList<String>();
    
    this.notes.addAll(list);
  }
}
