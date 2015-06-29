package net.bosatsu.training.rest

import net.bosatsu.training.rest.client.groovy.GroovyRestClient
import net.bosatsu.training.rest.client.restlet.RestletClient

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

class CustomWorld {
    def client = new GroovyRestClient()
    def host = 'localhost'
    def port = '8080'
    def uri
    def resourceType
    def mimeType

    def resourceMap
    def mimeTypeMap

    CustomWorld() {
    }

    def setClientType(def type) {
        switch(type) {
            case 'GroovyRestClient' :
                client = new GroovyRestClient()
                break;
            case 'RestletClient' :
                client = new RestletClient()
                break;
            default :
                break;
        }
    }

    def setResources(def resMap) {
        resourceMap = resMap.asMap(String,String)
    }

    def setMimeTypes(def mimeTypeMap) {
        this.mimeTypeMap = mimeTypeMap.asMap(String, String)
    }

    def setMimeType(def mimeType) {
        this.mimeType = mimeTypeMap[mimeType]

        println this.mimeType
    }

    def setHost(def host) {
        this.host = host
    }

    def setPort(def port) {
        this.port = port
    }

    def setResourceType(def resType) {
        resourceType = resType
    }

    def requestResource() {
        def res = resourceMap[resourceType]
        def portStr = ''
        def resStr = ''

        if(port != '80') {
            portStr = ":${port}"
        }

        if(res.startsWith('/')) {
            resStr = res
        } else {
            resStr = "/{$res}"
        }


        def url = "http://$host$portStr"

        if(mimeType) {
            client.setMimeType(mimeTypeMap[mimeType])
        }

        def resp = client.get(url, resStr)
        println resp
    }
}

World {
    new CustomWorld()
}

Before("@restlet") {
    setClientType("RestletClient")
}

Before("@groovyrest") {
   setClientType("GroovyRestClient")
}

Given(~/the server knows about the following resources$/) {  dt ->
    setResources(dt)
}

And(~/the server knows about the following mime types$/) { dt ->
    setMimeTypes(dt)
}

When(~/the server wants to connect to (\w+) on port (\d+)/ ) { host, port ->
  setHost(host)
  setPort(port)
}

Given(~/I am awesome/) {  ->
  println "I AM AWESOME"
}

Given(~/I want to interact with a[n]? (\w+) resource/) { res ->
    setResourceType(res)
}

Then(~/I can do cool things/) {  ->
    println "DOING COOL THINGS"
}

Then(~/I can request it( as (\w+))?/) { foo, mimeType ->
    requestResource()

    if(mimeType != null) {
        setMimeType(mimeType)
    }
}

