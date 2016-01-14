package com.willowtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;


import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AssetDAOMemoryImplTest
{
  public static AssetDAOMemoryImpl dao;
  
  @BeforeClass
  public static void beforeClass() throws InterruptedException
  {
    dao = new AssetDAOMemoryImpl();
    
    Asset asset = new Asset();
    asset.setName("Betty Rubble");
    asset.setUri("myorg://users/bettyrubble");
    dao.create(asset);
    
    asset = new Asset();
    asset.setName("Wilma Flintstone");
    asset.setUri("myorg://users/wflintstone");
    dao.create(asset);
  }

  @AfterClass
  public static void afterClass()
  {
  }
  
  @Test
  public void createTest()
  {
    Asset asset = new Asset();
    asset.setName("Barney Rubble");
    asset.setUri("myorg://users/brubble");
    Asset returnAsset = dao.create(asset);
    
    assertEquals("Barney Rubble", returnAsset.getName());
    assertEquals("myorg://users/brubble", returnAsset.getUri());    
  }
  
  @Test
  public void updateTest()
  {
    Asset asset = dao.find("myorg://users/wflintstone");
    List<String> notes = new ArrayList<String>();
    notes.add("Note 1");
    notes.add("Note 2");
    asset.setNotes(notes);
    
    Asset returnAsset1 = dao.update(asset);
    assertTrue(returnAsset1.getNotes().size() == 2);
    
    Asset missingAsset = new Asset();
    missingAsset.setName("Fred Flintstone");
    missingAsset.setUri("myorg://users/fflintstone");
    Asset returnAsset2 = dao.update(missingAsset);
    assertEquals("Fred Flintstone", returnAsset2.getName());
    assertEquals("myorg://users/fflintstone", returnAsset2.getUri());    
  }
  
  @Test
  public void deleteTest()
  {
    Asset asset1 = dao.delete("myorg://users/bettyrubble");
    assertEquals("Betty Rubble", asset1.getName());
    assertEquals("myorg://users/bettyrubble", asset1.getUri());    

    Asset findAsset = dao.find("myorg://users/bettyrubble");
    assertNull(findAsset);
    
    Asset asset2 = dao.delete("myorg://users/nobody");
    assertNull(asset2);
  }
  
  @Test
  public void findTest()
  {
    Asset asset1 = dao.find("myorg://users/wflintstone");
    assertEquals("Wilma Flintstone", asset1.getName());
    assertEquals("myorg://users/wflintstone", asset1.getUri()); 
    
    Asset asset2 = dao.find("myorg://users/nobody");
    assertNull(asset2);
  }
  
  @Test
  public void findAllTest()
  {
    List<Asset> assets = dao.findAll();
    assertTrue(assets.size() > 1 );
  }
}
