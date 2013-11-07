package com.aitormurguzur.storm.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component("defaultRoute")
public final class DefaultRoute extends RouteBuilder {

	@Override
	public final void configure() throws Exception {
		from("activemq:my-queue").bean(TestBean.class, "printPayload(*)");
	}
}
