package ithome.cch.service;

import java.util.Map;
import java.util.Arrays;
import java.util.List;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.dns.DnsConstants;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.xbill.DNS.AAAARecord;
import org.xbill.DNS.ARecord;
import org.xbill.DNS.Record;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

@ApplicationScoped
public class ProducerService {
    @Inject
    private ProducerTemplate template;

    @Inject
    Logger log;

    @ConfigProperty(name = "dns.server", defaultValue = "8.8.8.8")
    String dnsServer; 

    public List<String> getIP(final String uri) {
        log.infof("dns server: %s", dnsServer);
        var res = template.requestBodyAndHeaders("direct:IPCheck", null, Map.of(DnsConstants.DNS_NAME, uri, DnsConstants.DNS_SERVER, dnsServer), Record[].class);
        log.infof("ip: %s", res.toString());
        return Arrays.stream(res).map(x -> {
            if (x instanceof ARecord ar) {
                return ar.getAddress().getHostAddress();
            } else if (x instanceof AAAARecord aaaar) {
                return aaaar.getAddress().getHostAddress();
            }
            return "";
        }).toList();
    }

}
