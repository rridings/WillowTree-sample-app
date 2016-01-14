package com.willowtree;

import java.util.List;

/**
 * Interface definition for an asset DAO implementation
 * 
 * @author    Rob Ridings
 * @version   1.0
 * @since     2016-01-13 
 */
public interface IAssetDAO
{
  /**
   * Create an asset
   * 
   * @param asset
   * @return the new asset
   */
  public Asset create(Asset asset);
  
  /**
   * Update an asset.  If the asset does not exists create it.
   * 
   * @param asset
   * @return new asset
   */
  public Asset update(Asset asset);
  
  /**
   * Delete an asset
   * 
   * @param uri The identifier for the asset
   * @return the deleted asset
   */
  public Asset delete(String uri);
  
  /**
   * Find an specific asset
   * 
   * @param uri The identifier for the asset
   * @return asset.  If not found returns null.
   */
  public Asset find(String uri);
  
  /**
   * Find all assets
   * 
   * @return list of assets. If not found returns empty list.
   */
  public List<Asset> findAll();
}
