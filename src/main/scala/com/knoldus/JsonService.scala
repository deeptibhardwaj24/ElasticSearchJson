package com.knoldus

import java.io.{BufferedWriter, File, FileWriter}

import org.elasticsearch.action.bulk.BulkRequestBuilder
import org.elasticsearch.client.Client

import scala.io.Source

/**
  * Created by knoldus on 4/4/16.
  */


trait JsonServiceApi{

  val client : Client = EsClient.client
  def readDataFromFile(path:String)={

    val content = Source.fromFile(path).getLines().toList

    val bulkRequest : BulkRequestBuilder  = client.prepareBulk()

    for (i <- 1 to content.size-1) {

      bulkRequest.add(client.prepareIndex("employeedb", "EmployeeJson",i.toString()).setSource(content(i)))
    }
    bulkRequest.execute().actionGet()
  }


  def writeDataToFile(path:String)={

    val data = client.prepareSearch("employeedb").setTypes("EmployeeJson").execute().actionGet();
    val filewriter = new FileWriter(new File(path));
    val bufferedwriter  = new BufferedWriter(filewriter);
    bufferedwriter.write(data.toString());
    bufferedwriter.close();
   // println(data)

  }
}
class JsonService extends JsonServiceApi



