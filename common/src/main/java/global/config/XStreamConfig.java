//package global.config;
//
//import com.thoughtworks.xstream.XStream;
//import global.command.ReduceStockCommand;
//import org.axonframework.serialization.xml.XStreamSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class XStreamConfig {
//
//    @Bean
//    public XStream xStream() {
//        XStream xstream = new XStream();
//        XStream.setupDefaultSecurity(xstream);
//        xstream.allowTypes(new Class[]{ReduceStockCommand.class});
//        return xstream;
//    }
//
//    @Bean
//    public XStreamSerializer xStreamSerializer(XStream xStream) {
//        return XStreamSerializer.builder().xStream(xStream).build();
//    }
//}