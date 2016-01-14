package com.willowtree;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * AssetController defines the REST API and routes.  It is based on the Spark Framework.
 * This framework starts an embedded Jetty instance to service the REST API.
 * 
 * @author    Rob Ridings
 * @version   1.0
 * @since     2016-01-13 
 */
public class AssetController
{
  private Gson gson = new Gson();
  
  private static Logger log = Logger.getLogger(AssetController.class);;
  
  /**
   * AssetController constructor.  Defines the REST API and route mapping.
   * 
   * @param assetService the AssertService responsible for processing the API requests.
   */
  public AssetController(final AssetService assetService)
  {    
    get("/asset/*//*", (request, response) -> 
    {
      String org = request.splat()[0];
      String users = request.splat()[1];
      String uri = org + "//" +users;
      log.debug(Thread.currentThread().getId() + " - GET: " + uri);
      
      Asset asset = assetService.getAsset(uri);
      if (asset != null)
      {
        return asset;
      }
      log.debug(Thread.currentThread().getId() + " - Asset not found. URI:" + uri);
      
      response.status(400);
      return new Response("No asset with uri %s found", uri);
    }, gson::toJson);
    
    get("/asset", (request, response) -> 
    {
      log.debug(Thread.currentThread().getId() + "- GET: All assets");
      
      return assetService.getAllAssets();
    }, gson::toJson);
    
    post("/asset", (request, response) -> 
    {      
      log.debug(Thread.currentThread().getId() + " - POST: " + request.body());
      
      Asset asset = gson.fromJson(request.body(), Asset.class);
      return assetService.createAsset(asset);
    }, gson::toJson);
  
    put("/asset", (request, response) ->
    {
      log.debug(Thread.currentThread().getId() + " - PUT: " + request.body());
      
      Asset asset = gson.fromJson(request.body(), Asset.class);
      return assetService.updateAsset(asset);
    }, gson::toJson);
      
    delete("/asset/*//*", (request, response) -> 
    {
      String org = request.splat()[0];
      String users = request.splat()[1];
      String uri = org + "//" +users;
      log.debug(Thread.currentThread().getId() + " - DELETE: " + uri);
      
      Asset asset =  assetService.deleteAsset(uri);
      if (asset != null)
      {
        return asset;
      }
      log.debug(Thread.currentThread().getId() + " - Asset not found. URI:" + uri);
      
      response.status(400);
      return new Response("No asset with uri '%s' found", uri);
    }, gson::toJson);
    
    after((request, response) -> 
    {
      response.type("application/json");
    });

    exception(IllegalArgumentException.class, (e, request, response) -> 
    {
      log.error(Thread.currentThread().getId() + " - " + e.getMessage());
      
      response.status(400);
      response.body(gson.toJson(new Response(e)));
    });
  }
}