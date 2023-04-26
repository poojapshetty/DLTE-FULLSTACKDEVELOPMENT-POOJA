package bank.project.app;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class payeeConfig {

    //payee configuration
    @Bean(name= "payee")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema payeeSchema){
        DefaultWsdl11Definition schemaObject=new DefaultWsdl11Definition();
        schemaObject.setPortTypeName("PayeePort");
        schemaObject.setTargetNamespace("http://soap.bank.project");
        schemaObject.setLocationUri("/listpayee");
        schemaObject.setSchema(payeeSchema);
        return schemaObject;
    }

    @Bean
    public XsdSchema payeeSchema(){
        return new SimpleXsdSchema(new ClassPathResource("Alpha.xsd"));
    }
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,"/listpayee/*");
    }
}
