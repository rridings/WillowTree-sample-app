package com.willowtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;
import spark.utils.IOUtils;

import com.google.gson.Gson;

public class AssetControllerIntegrationTest
{
  private static Gson gson = new Gson();

  @BeforeClass
  public static void beforeClass() throws InterruptedException
  {
    Main.main(null);

    // Give server a chance to start
    Thread.sleep(500);

    Asset asset1 = new Asset();
    asset1.setUri("myorg://users/bettyrubble");
    asset1.setName("Betty Rubble");
    post("/asset", asset1);

    Asset asset2 = new Asset();
    asset2.setUri("myorg://users/wflintstone");
    asset2.setName("Wilma Flintstone");
    post("/asset", asset2);
  }

  @AfterClass
  public static void afterClass()
  {
    Spark.stop();
  }

  @Test
  public void createAssetTest()
  {
    Asset asset = new Asset();
    asset.setUri("myorg://users/brubble");
    asset.setName("Barney Rubble");
    TestResponse res = post("/asset", asset);
    Map<String, String> json = res.json();

    assertEquals(200, res.status);
    assertEquals("Barney Rubble", json.get("name"));
    assertEquals("myorg://users/brubble", json.get("uri"));
  }

  @Test
  public void updateAssetTest()
  {
    Asset asset = new Asset();
    asset.setUri("myorg://users/bettyrubble");
    asset.setName("Betty Rubble");
    asset.addNote("Note1");
    TestResponse res = put("/asset", asset);
    Map<String, String> json = res.json();

    assertEquals(200, res.status);
    assertEquals("Betty Rubble", json.get("name"));
    assertEquals("myorg://users/bettyrubble", json.get("uri"));
  }

  @Test
  public void deleteAssetTest()
  {
    // Delete asset
    TestResponse res = delete("/asset/myorg://users/bettyrubble");
    Map<String, String> json = res.json();
    assertEquals(200, res.status);
    assertEquals("Betty Rubble", json.get("name"));
    assertEquals("myorg://users/bettyrubble", json.get("uri"));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void getAssetsTest()
  {
    TestResponse res = get("/asset");
    ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) new Gson()
        .fromJson(res.body, ArrayList.class);
    assertEquals(200, res.status);
    assertTrue(list.size() > 1);
  }

  @Test
  public void getAssetTest()
  {
    TestResponse res = get("/asset/myorg://users/bettyrubble");
    Map<String, String> json = res.json();
    assertEquals(200, res.status);
    assertEquals("Betty Rubble", json.get("name"));
    assertEquals("myorg://users/bettyrubble", json.get("uri"));
  }

  private static TestResponse get(String path)
  {
    HttpClient httpClient = HttpClientBuilder.create().build();

    try
    {
      HttpGet get = new HttpGet("http://localhost:4567" + path);
      get.setHeader("Content-type", "application/json");
      HttpResponse response = httpClient.execute(get);

      String body = IOUtils.toString(response.getEntity().getContent());
      return new TestResponse(response.getStatusLine().getStatusCode(), body);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }
  
  private static TestResponse delete(String path)
  {
    HttpClient httpClient = HttpClientBuilder.create().build();

    try
    {
      HttpDelete delete = new HttpDelete("http://localhost:4567" + path);
      delete.setHeader("Content-type", "application/json");
      HttpResponse response = httpClient.execute(delete);

      String body = IOUtils.toString(response.getEntity().getContent());
      return new TestResponse(response.getStatusLine().getStatusCode(), body);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }
  
  private static TestResponse post(String path, Asset asset)
  {
    HttpClient httpClient = HttpClientBuilder.create().build();

    try
    {
      HttpPost post = new HttpPost("http://localhost:4567" + path);
      post.setEntity(new StringEntity(gson.toJson(asset)));
      post.setHeader("Content-type", "application/json");
      HttpResponse response = httpClient.execute(post);

      String body = IOUtils.toString(response.getEntity().getContent());
      return new TestResponse(response.getStatusLine().getStatusCode(), body);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

  private static TestResponse put(String path, Asset asset)
  {
    try
    {
      HttpClient httpClient = HttpClientBuilder.create().build();

      HttpPut put = new HttpPut("http://localhost:4567" + path);
      put.setEntity(new StringEntity(gson.toJson(asset)));
      put.setHeader("Content-type", "application/json");
      HttpResponse response = httpClient.execute(put);

      String body = IOUtils.toString(response.getEntity().getContent());
      return new TestResponse(response.getStatusLine().getStatusCode(), body);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  private static class TestResponse
  {
    public final String body;
    public final int    status;

    public TestResponse(int status, String body)
    {
      this.status = status;
      this.body = body;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> json()
    {
      return new Gson().fromJson(body, HashMap.class);
    }
  }
}
