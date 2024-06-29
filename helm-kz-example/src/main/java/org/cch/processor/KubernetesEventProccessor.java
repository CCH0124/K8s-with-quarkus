package org.cch.processor;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kubernetes.KubernetesConstants;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class KubernetesEventProccessor implements Processor {
    @Inject
    Logger log;


    @Override
    public void process(Exchange exchange) throws Exception {
        String namespace = System.getProperty("NAMESPACE", "default");
        String podName = System.getProperty("POD_NAME", "default");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX");
        String formattedDate = LocalDate.now().atStartOfDay().atOffset(ZoneOffset.UTC).format(formatter);
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_NAMESPACE_NAME, namespace);
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_EVENT_NAME, "get-node-status");
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_EVENT_TIME, formattedDate);
        Map<String, String> labels = new HashMap<>();
        labels.put("server", "quarkus");
        labels.put("created", "camel");
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_EVENTS_LABELS, labels);
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_EVENT_ACTION_PRODUCER, "Some Action");
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_EVENT_TYPE, "Normal");
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_EVENT_REASON, "client API trigger.");
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_EVENT_REPORTING_CONTROLLER,
        "Quarkus");
        exchange.getIn().setHeader(KubernetesConstants.KUBERNETES_EVENT_REPORTING_INSTANCE,
        podName);
    }

}
