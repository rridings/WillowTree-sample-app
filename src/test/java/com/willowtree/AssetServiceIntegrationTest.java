package com.willowtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class AssetServiceIntegrationTest
{
  public static AssetService service;
  @BeforeClass
  public static void beforeClass() throws InterruptedException
  {
    service = new AssetService();
    Asset a1 = new Asset();
    a1.setUri("myorg://users/bettyrubble");
    a1.setName("Betty Rubble");
    service.createAsset(a1);
    
    Asset a2 = new Asset();
    a2.setUri("myorg://users/wflintstone");
    a2.setName("Wilma Flintstone");
    service.createAsset(a2);
  }
  
  @Test
  public void getAllAssetsTest()
  {
    List<Asset> assets = service.getAllAssets(); 
    assertTrue(assets.size() > 1);
  }

  @Test
  public void getAssetTest()
  {
    Asset asset = service.getAsset("myorg://users/wflintstone");
    assertEquals("Wilma Flintstone", asset.getName());
    assertEquals("myorg://users/wflintstone", asset.getUri());
  }

  @Test
  public void createAssetTest()
  {
    Asset asset = new Asset();
    asset.setUri("myorg://users/brubble");
    asset.setName("Barney Rubble"); 
    Asset returnedAsset = service.createAsset(asset);
    assertEquals("Barney Rubble", returnedAsset.getName());
    assertEquals("myorg://users/brubble", returnedAsset.getUri()); 
  }
  
  @Test
  public void updateAssetTest()
  {
    Asset asset1 = new Asset();
    asset1.setUri("myorg://users/wflintstone");
    asset1.setName("Wilma Flintstone"); 
    asset1.addNote("Note 1");
    asset1.addNote("Note 2");
    Asset returnedAsset1 = service.updateAsset(asset1);
    assertTrue(returnedAsset1.getNotes().size() == 2);
    
    Asset asset2 = new Asset();
    asset2.setUri("myorg://users/fflintstone");
    asset2.setName("Fred Flintstone"); 
    asset2.addNote("Note 2");
    Asset returnedAsset2 = service.updateAsset(asset2);
    assertNull(returnedAsset2);    
  }

  @Test
  public void deleteAssetTest()
  {
    Asset asset1 = service.deleteAsset("myorg://users/bettyrubble");
    assertEquals("Betty Rubble", asset1.getName());
    assertEquals("myorg://users/bettyrubble", asset1.getUri());    

    Asset asset2 = service.getAsset("myorg://users/bettyrubble");
    assertNull(asset2);
    
    Asset asset3= service.deleteAsset("myorg://users/nobody");
    assertNull(asset3);
  }
}
