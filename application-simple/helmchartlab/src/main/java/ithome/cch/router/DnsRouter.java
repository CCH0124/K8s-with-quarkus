package ithome.cch.router;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DnsRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:IPCheck")
            .to("dns:lookup");
    }
    
}
