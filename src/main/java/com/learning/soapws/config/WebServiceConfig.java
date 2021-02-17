package com.learning.soapws.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.context.ApplicationContext;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter  {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext){
		MessageDispatcherServlet servlet= new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet,"/ws/*");

	}
	@Bean(name="countries")
	public DefaultWsdl11Definition defualtwsdlDefination(XsdSchema countriesSchema)
	{
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CountriesPort");
		definition.setLocationUri("/ws");
		definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		definition.setSchema(countriesSchema);
		return definition;
	}
	
	@Bean
	public XsdSchema countriesSchema()
	{
		return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
	}

}
