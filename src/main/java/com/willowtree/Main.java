package com.willowtree;

/**
 * Main is called by the command line and started to AssetController.
 * 
 * @author    Rob Ridings
 * @version   1.0
 * @since     2016-01-13 
 */
public class Main {
  public static void main(String[] args) {
    new AssetController(new AssetService());
  }
}