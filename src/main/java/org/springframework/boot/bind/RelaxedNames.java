package org.springframework.boot.bind;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelaxedNames implements Iterable<String> {
    private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("([^A-Z-])([A-Z])");
    private static final Pattern SEPARATED_TO_CAMEL_CASE_PATTERN = Pattern.compile("[_\\-.]");
    private final String name;
    private final Set<String> values = new LinkedHashSet<>();

    public RelaxedNames(String name) {
        this.name = name != null ? name : "";
        this.initialize(this.name, this.values);
    }

    @Override
    public Iterator<String> iterator() {
        return this.values.iterator();
    }

    private void initialize(String name, Set<String> values) {
        if (!values.contains(name)) {
            RelaxedNames.Variation[] var3 = RelaxedNames.Variation.values();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                RelaxedNames.Variation variation = var3[var5];
                RelaxedNames.Manipulation[] var7 = RelaxedNames.Manipulation.values();
                int var8 = var7.length;

                for (int var9 = 0; var9 < var8; ++var9) {
                    RelaxedNames.Manipulation manipulation = var7[var9];
                    String result = manipulation.apply(name);
                    result = variation.apply(result);
                    values.add(result);
                    this.initialize(result, values);
                }
            }

        }
    }

    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    enum Manipulation {
        NONE {
            @Override
            public String apply(String value) {
                return value;
            }
        },
        HYPHEN_TO_UNDERSCORE {
            @Override
            public String apply(String value) {
                return value.indexOf(45) != -1 ? value.replace('-', '_') : value;
            }
        },
        UNDERSCORE_TO_PERIOD {
            @Override
            public String apply(String value) {
                return value.indexOf(95) != -1 ? value.replace('_', '.') : value;
            }
        },
        PERIOD_TO_UNDERSCORE {
            @Override
            public String apply(String value) {
                return value.indexOf(46) != -1 ? value.replace('.', '_') : value;
            }
        },
        CAMELCASE_TO_UNDERSCORE {
            @Override
            public String apply(String value) {
                if (value.isEmpty()) {
                    return value;
                } else {
                    Matcher matcher = RelaxedNames.CAMEL_CASE_PATTERN.matcher(value);
                    if (!matcher.find()) {
                        return value;
                    } else {
                        matcher = matcher.reset();
                        StringBuffer result = new StringBuffer();

                        while (matcher.find()) {
                            matcher.appendReplacement(result, matcher.group(1) + '_' + StringUtils.uncapitalize(matcher.group(2)));
                        }

                        matcher.appendTail(result);
                        return result.toString();
                    }
                }
            }
        },
        CAMELCASE_TO_HYPHEN {
            @Override
            public String apply(String value) {
                if (value.isEmpty()) {
                    return value;
                } else {
                    Matcher matcher = RelaxedNames.CAMEL_CASE_PATTERN.matcher(value);
                    if (!matcher.find()) {
                        return value;
                    } else {
                        matcher = matcher.reset();
                        StringBuffer result = new StringBuffer();

                        while (matcher.find()) {
                            matcher.appendReplacement(result, matcher.group(1) + '-' + StringUtils.uncapitalize(matcher.group(2)));
                        }

                        matcher.appendTail(result);
                        return result.toString();
                    }
                }
            }
        },
        SEPARATED_TO_CAMELCASE {
            @Override
            public String apply(String value) {
                return RelaxedNames.Manipulation.separatedToCamelCase(value, false);
            }
        },
        CASE_INSENSITIVE_SEPARATED_TO_CAMELCASE {
            @Override
            public String apply(String value) {
                return RelaxedNames.Manipulation.separatedToCamelCase(value, true);
            }
        };

        private static final char[] SUFFIXES = new char[]{'_', '-', '.'};

        private Manipulation() {
        }

        public abstract String apply(String var1);

        private static String separatedToCamelCase(String value, boolean caseInsensitive) {
            if (value.isEmpty()) {
                return value;
            } else {
                StringBuilder builder = new StringBuilder();
                String[] var3 = RelaxedNames.SEPARATED_TO_CAMEL_CASE_PATTERN.split(value);
                int var4 = var3.length;

                int var5;
                for (var5 = 0; var5 < var4; ++var5) {
                    String field = var3[var5];
                    field = caseInsensitive ? field.toLowerCase(Locale.ENGLISH) : field;
                    builder.append(builder.length() != 0 ? StringUtils.capitalize(field) : field);
                }

                char lastChar = value.charAt(value.length() - 1);
                char[] var9 = SUFFIXES;
                var5 = var9.length;

                for (int var10 = 0; var10 < var5; ++var10) {
                    char suffix = var9[var10];
                    if (lastChar == suffix) {
                        builder.append(suffix);
                        break;
                    }
                }

                return builder.toString();
            }
        }
    }

    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    enum Variation {
        NONE {
            @Override
            public String apply(String value) {
                return value;
            }
        },
        LOWERCASE {
            @Override
            public String apply(String value) {
                return value.isEmpty() ? value : value.toLowerCase(Locale.ENGLISH);
            }
        },
        UPPERCASE {
            @Override
            public String apply(String value) {
                return value.isEmpty() ? value : value.toUpperCase(Locale.ENGLISH);
            }
        };

        Variation() {
        }

        public abstract String apply(String var1);
    }
}
