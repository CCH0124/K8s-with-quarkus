package org.cch.service;

import java.util.List;
import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.kubernetes.KubernetesConstants;
import org.cch.dto.NodeDTO;
import org.jboss.logging.Logger;

import io.fabric8.kubernetes.api.model.Node;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ConsumerService {
    @Inject
    Logger log;

    @Inject
    ProducerTemplate producerTemplate;


    public void getAllNode() {
        producerTemplate.requestBodyAndHeaders("direct:node-operator", null, Map.of(KubernetesConstants.KUBERNETES_OPERATION, "listNodes"));
    }



    @Consume("direct:node-result")
    public void getAllNodeStatus(@Body final List<Node> body, Exchange exchange) {
        var res = body.stream().map(x -> new NodeDTO(x.getMetadata().getName(), x.getStatus().getNodeInfo().getKubeletVersion(), x.getStatus().getNodeInfo().getArchitecture())).toList();
        log.infof("node-result: %s ", res);
        exchange.getMessage().setBody(res);
    }


}
