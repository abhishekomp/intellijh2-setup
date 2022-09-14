package org.abhishek.om.jenkins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sca820 on 03 sep., 2022
 */
public class Parameters {
    private final Map<String, List<String>> parameters = new HashMap<>();

    public Parameters add(String name, String value) {
        List<String> values = parameters.getOrDefault(name, new ArrayList<>());
        values.add(value);
        parameters.put(name, values);

        return this;
    }

    public Parameters add(String name, String argument, String value) {
        String formattedValue;

        if (value.contains(" ")) {
            formattedValue = argument + "='" + value + "'";
        } else {
            formattedValue = argument + "=" + value;
        }

        return add(name, formattedValue);
    }

    public Map<String, Object> build() {
        Map<String, Object> result = new HashMap<>();
        for (String key : parameters.keySet()) {
            List<String> values = parameters.get(key);

            StringBuilder parameter = new StringBuilder();
            for (String value : values) {
                parameter.append(value).append(" ");
            }

            String value = parameter.toString().trim();
            result.put(key, value);
        }

        return result;
    }
}
