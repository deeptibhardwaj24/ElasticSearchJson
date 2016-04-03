package com.knoldus

import java.net.InetAddress

import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress

/**
  * Created by knoldus on 4/4/16.
  */


  class EsManager {

    private val port = 9300

    private val nodes = List("localhost")

    private val addresses = nodes.map { host => new InetSocketTransportAddress(InetAddress.getByName(host), port) }

    lazy private val settings = Settings.settingsBuilder()
      .put("threadpool.search.queue_size", -1)
      .put("threadpool.index.queue_size", -1)
      .put("client.transport.ping_timeout", "120s")
      .put("client.transport.nodes_sampler_interval", "60s")
      .put("cluster.name", "elasticsearch")
      .build()


    val client: Client = TransportClient.builder().settings(settings).build().addTransportAddresses(addresses: _*)

  }

  object EsClient extends EsManager
