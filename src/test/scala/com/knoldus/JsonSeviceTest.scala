package com.knoldus

import org.scalatest.FunSuite

/**
  * Created by knoldus on 4/4/16.
  */
class JsonSeviceTest extends FunSuite with JsonServiceApi {
  val jsonservice = new JsonService
  test("read data from json file")
  {
    val result = jsonservice.readDataFromFile("/home/knoldus/ElasticSearchJson/inputJson.json")
    assert(result.hasFailures === false)
  }
  test("writing to a json file")
  {
    val result = jsonservice.writeDataToFile("/home/knoldus/ElasticSearchJson/newfile.json")
    assert("" === "")
  }

}
