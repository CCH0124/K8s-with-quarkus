package org.cch;

import org.apache.camel.component.reactive.streams.api.CamelReactiveStreamsService;
import org.cch.service.ConsumerService;
import org.jboss.logging.Logger;
import org.reactivestreams.Publisher;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/node")
@ApplicationScoped
public class KubernetesResource {

    @Inject
    ConsumerService consumerService;

    // @Inject
    // CamelReactiveStreamsService camelReactiveStreamsService;

    @GET
    public void getNodeInfo() {
        consumerService.getAllNode();
        // return camelReactiveStreamsService.fromStream("sink-stream", List.class);
    }

}
