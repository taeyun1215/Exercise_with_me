package order.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    public void configure(Serializer serializer) {
        if (serializer instanceof XStreamSerializer) {
            XStream xStream = ((XStreamSerializer) serializer).getXStream();
            xStream.allowTypesByWildcard(new String[]{
                    "order.**",
                    "global.**"
            });
        }
    }
}