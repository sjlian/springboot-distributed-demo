package org.springframework.boot.bind;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertySourceUtils {
    public PropertySourceUtils() {
    }

    public static Map<String, Object> getSubProperties(PropertySources propertySources, String rootPrefix, String keyPrefix) {
        RelaxedNames keyPrefixes = new RelaxedNames(keyPrefix);
        Map<String, Object> subProperties = new LinkedHashMap();
        Iterator var5 = propertySources.iterator();

        while(true) {
            PropertySource source;
            do {
                if (!var5.hasNext()) {
                    return Collections.unmodifiableMap(subProperties);
                }

                source = (PropertySource)var5.next();
            } while(!(source instanceof EnumerablePropertySource));

            String[] var7 = ((EnumerablePropertySource)source).getPropertyNames();
            int var8 = var7.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                String name = var7[var9];
                String key = getSubKey(name, rootPrefix, keyPrefixes);
                if (key != null && !subProperties.containsKey(key)) {
                    subProperties.put(key, source.getProperty(name));
                }
            }
        }
    }

    private static String getSubKey(String name, String rootPrefixes, RelaxedNames keyPrefix) {
        rootPrefixes = rootPrefixes != null ? rootPrefixes : "";
        Iterator var3 = (new RelaxedNames(rootPrefixes)).iterator();

        while(var3.hasNext()) {
            String rootPrefix = (String)var3.next();
            Iterator var5 = keyPrefix.iterator();

            while(var5.hasNext()) {
                String candidateKeyPrefix = (String)var5.next();
                if (name.startsWith(rootPrefix + candidateKeyPrefix)) {
                    return name.substring((rootPrefix + candidateKeyPrefix).length());
                }
            }
        }

        return null;
    }
}
