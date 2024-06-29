package org.cch.router;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;

import io.fabric8.kubernetes.api.model.Node;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KubernetesNodeRouter extends RouteBuilder {

    JacksonDataFormat format = new ListJacksonDataFormat(Node.class);

    @Override
    public void configure() throws Exception {

        from("direct:node-operator")
            .routeId("Kubernetes Node Operator")
            .to("kubernetes-nodes:///?kubernetesClient=#kubernetesClient")
            .log(LoggingLevel.DEBUG, "${body}")
            .to("direct:node-result")
            .log(LoggingLevel.INFO, "result: ${body}")
            .to("reactive-streams:sink-stream")
            .end();
    }

    
}
