package com.alipour.learning.filecopier;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileCopier {
    @Autowired
    private CamelContext camelContext;

    private String from;
    private String dist;

    public FileCopier(
            @Value("${file.copy.source.dir}") String sourceDir,
            @Value("${file.copy.dist.dir}") String distDir) {

        from = "file:" + sourceDir + "?noop=true";
        dist = "file:" + distDir;
    }

    public void copyFile() throws Exception {
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(from).to(dist);
            }
        });

        camelContext.start();
        Thread.sleep(10000);
        camelContext.stop();
    }
}
