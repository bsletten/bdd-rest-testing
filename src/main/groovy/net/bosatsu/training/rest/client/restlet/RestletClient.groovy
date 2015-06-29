package net.bosatsu.training.rest.client.restlet

import org.restlet.resource.ClientResource

/**
 * Created by brian on 6/27/15.
 */
class RestletClient {

    def mimeType

    RestletClient()
    {
    }

    def setMimeType(def mimeType) {
        this.mimeType = mimeType
    }

    def get(def url, def res) {
        def resp = new ClientResource(url + res).get()
        println resp
    }
}
