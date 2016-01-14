package com.willowtree;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * AssetDAOMemoryImpl is a in-memory implementation of the IAssetDAO interface.
 * Assets are stored in the java hashtable which is thread-safe. The hashtable
 * is keyed by the uri of the asset.
 * 
 * @author Rob Ridings
 * @version 1.0
 * @since 2016-01-13
 */
public class AssetDAOMemoryImpl implements IAssetDAO
{
  private static Logger log = Logger.getLogger(AssetDAOMemoryImpl.class);

  private static Gson gson = new Gson();

  private Hashtable<String, Asset>  assetMap = new Hashtable<String, Asset>();

  private static AssetDAOMemoryImpl instance;

  public static AssetDAOMemoryImpl getInstance()
  {
    if (instance == null)
    {
      instance = new AssetDAOMemoryImpl();
      log.debug(Thread.currentThread().getId() + " - create new instance");
    }

    return instance;
  }

  @Override
  public Asset create(Asset asset)
  {
    if ( asset == null )
      return null;
    
    log.debug(Thread.currentThread().getId() + " - create asset " + gson.toJson(asset));
    
    assetMap.put(asset.getUri(), asset);
    return asset;
  }

  @Override
  public Asset update(Asset asset)
  {
    if ( asset == null )
      return null;
    
    log.debug(Thread.currentThread().getId() + " - update asset " + gson.toJson(asset));
    
    assetMap.put(asset.getUri(), asset);
    return asset;
  }

  @Override
  public Asset delete(String uri)
  {
    if ( uri == null )
      return null;
    
    log.debug(Thread.currentThread().getId() + " - delete asset " + uri);
    
    return assetMap.remove(uri);
  }

  @Override
  public Asset find(String uri)
  {
    if ( uri == null )
      return null;
    
    log.debug(Thread.currentThread().getId() + " - find asset " + uri);
    
    return assetMap.get(uri);
  }

  @Override
  public List<Asset> findAll()
  {
    log.debug(Thread.currentThread().getId() + " - find all asset");
    
    return new ArrayList<Asset>(assetMap.values());
  }

}
