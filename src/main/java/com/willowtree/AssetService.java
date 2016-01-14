package com.willowtree;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * AssetService the actually processing for the api methods.  It is using the AssetDAOMemoryImpl 
 * to as the storage for the assets.
 * 
 * @author    Rob Ridings
 * @version   1.0
 * @since     2016-01-13 
 */
public class AssetService
{
  private static Logger log = Logger.getLogger(AssetService.class);
  
  private static Gson gson = new Gson();
  
  private IAssetDAO assetDAO = AssetDAOMemoryImpl.getInstance();
  
  /**
   * Get all the assets.
   * 
   * @return list of assets.  If not found returns empty list.
   */
  public List<Asset> getAllAssets()
  {
    log.debug(Thread.currentThread().getId() + " - find all asset");
    
    return assetDAO.findAll();
  }

  /**
   * Get a specific asset
   * 
   * @param uri identifier of the asset
   * @return asset.  If not found returns null.
   */
  public Asset getAsset(String uri)
  {
    if ( uri == null )
      return null;
    
    log.debug(Thread.currentThread().getId() + " - find asset " + uri);
    
    return assetDAO.find(uri);
  }

  /**
   * Create an asset
   * 
   * @param asset
   * @return new asset
   */
  public Asset createAsset(Asset asset)
  {
    if ( asset == null )
      return null;
  
    log.debug(Thread.currentThread().getId() + " - create asset " + gson.toJson(asset));
    assetDAO.create(asset);

    return asset;
  }

  /**
   * Update an asset.  If not found creates the asset.
   * 
   * @param asset
   * @return new asset
   */
  public Asset updateAsset(Asset asset)
  {
    if ( asset == null )
      return null;
    
    Asset foundAsset = assetDAO.find(asset.getUri());
    if ( foundAsset != null )
    {
      log.debug(Thread.currentThread().getId() + " - update asset " + gson.toJson(asset));
      foundAsset.setName(asset.getName());
      foundAsset.addNote(asset.getNotes());
      assetDAO.update(asset);
    }
    else
    {
      log.debug(Thread.currentThread().getId() + " - asset not found");
    }
    
    return foundAsset;
  }

  /**
   * Delete an asset
   * 
   * @param uri identifier of the asset
   * @return the deleted asset
   */
  public Asset deleteAsset(String uri)
  {
    if ( uri == null )
      return null;
    
    log.debug(Thread.currentThread().getId() + " - delete asset " + uri);
    
    Asset asset = assetDAO.find(uri);
    if ( asset != null )
      assetDAO.delete(uri);
    
    return asset;
  }
}
