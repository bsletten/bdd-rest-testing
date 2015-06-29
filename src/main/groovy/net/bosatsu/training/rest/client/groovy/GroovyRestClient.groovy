package net.bosatsu.training.rest.client.groovy

import groovyx.net.http.RESTClient

/**
 * Created by brian on 6/27/15.
 */
class GroovyRestClient {

    def mimeType

    GroovyRestClient() {

    }

    def client(url) {
        new RESTClient(url)
    }

    def setMimeType(def mimeType) {
        this.mimeType = mimeType
    }

    def get(def url, def res) {

        def client = client(url)

        if(mimeType != null) {
            client.setHeaders(['Accept' : mimeType])
        }

        def resp = client.get( path: res )
        println resp.contentType
        assert resp.status == 200

        println resp.getData()
    }
}
