package net.bosatsu.training.rest.raml

import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder

public class RamlParse {

    public RamlParse() {

    }

    def static main(args) {
        Raml raml = new RamlDocumentBuilder().build("petstore.raml")
        println raml.title

        def resMap = raml.resources
        resMap.each { k, v ->
            println k
            println v
        }
    }
}