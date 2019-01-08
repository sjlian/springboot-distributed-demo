package org.springframework.boot.bind;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.Map;

public final class RelaxedPropertyResolver implements PropertyResolver{
    private final PropertyResolver resolver;
    private final String prefix;

    public RelaxedPropertyResolver(PropertyResolver resolver) {
        this(resolver, (String)null);
    }

    public RelaxedPropertyResolver(PropertyResolver resolver, String prefix) {
        Assert.notNull(resolver, "PropertyResolver must not be null");
        this.resolver = resolver;
        this.prefix = prefix != null ? prefix : "";
    }

    @Override
    public String getRequiredProperty(String key) throws IllegalStateException {
        return (String)this.getRequiredProperty(key, String.class);
    }
    @Override
    public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
        T value = this.getProperty(key, targetType);
        Assert.state(value != null, String.format("required key [%s] not found", key));
        return value;
    }
    @Override
    public String getProperty(String key) {
        return (String)this.getProperty(key, String.class, null);
    }
    @Override
    public String getProperty(String key, String defaultValue) {
        return (String)this.getProperty(key, String.class, defaultValue);
    }
    @Override
    public <T> T getProperty(String key, Class<T> targetType) {
        return this.getProperty(key, targetType, null);
    }
    @Override
    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        RelaxedNames prefixes = new RelaxedNames(this.prefix);
        RelaxedNames keys = new RelaxedNames(key);
        Iterator var6 = prefixes.iterator();

        while(var6.hasNext()) {
            String prefix = (String)var6.next();
            Iterator var8 = keys.iterator();

            while(var8.hasNext()) {
                String relaxedKey = (String)var8.next();
                if (this.resolver.containsProperty(prefix + relaxedKey)) {
                    return this.resolver.getProperty(prefix + relaxedKey, targetType);
                }
            }
        }

        return defaultValue;
    }


    @Override
    public boolean containsProperty(String key) {
        RelaxedNames prefixes = new RelaxedNames(this.prefix);
        RelaxedNames keys = new RelaxedNames(key);
        Iterator var4 = prefixes.iterator();

        while(var4.hasNext()) {
            String prefix = (String)var4.next();
            Iterator var6 = keys.iterator();

            while(var6.hasNext()) {
                String relaxedKey = (String)var6.next();
                if (this.resolver.containsProperty(prefix + relaxedKey)) {
                    return true;
                }
            }
        }

        return false;
    }
    @Override
    public String resolvePlaceholders(String text) {
        throw new UnsupportedOperationException("Unable to resolve placeholders with relaxed properties");
    }
    @Override
    public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unable to resolve placeholders with relaxed properties");
    }

    public Map<String, Object> getSubProperties(String keyPrefix) {
        Assert.isInstanceOf(ConfigurableEnvironment.class, this.resolver, "SubProperties not available.");
        ConfigurableEnvironment env = (ConfigurableEnvironment)this.resolver;
        return PropertySourceUtils.getSubProperties(env.getPropertySources(), this.prefix, keyPrefix);
    }

    public static RelaxedPropertyResolver ignoringUnresolvableNestedPlaceholders(Environment environment, String prefix) {
        Assert.notNull(environment, "Environment must not be null");
        PropertyResolver resolver = environment;
        if (environment instanceof ConfigurableEnvironment) {
            resolver = new PropertySourcesPropertyResolver(((ConfigurableEnvironment)environment).getPropertySources());
            ((PropertySourcesPropertyResolver)resolver).setIgnoreUnresolvableNestedPlaceholders(true);
        }

        return new RelaxedPropertyResolver((PropertyResolver)resolver, prefix);
    }
}
