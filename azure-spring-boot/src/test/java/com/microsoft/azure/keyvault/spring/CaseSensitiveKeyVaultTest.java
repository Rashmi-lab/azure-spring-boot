/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */
package com.microsoft.azure.keyvault.spring;

import com.azure.security.keyvault.secrets.SecretClient;
import static com.microsoft.azure.keyvault.spring.Constants.DEFAULT_REFRESH_INTERVAL_MS;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CaseSensitiveKeyVaultTest {
    @Mock
    private SecretClient keyVaultClient;

    @Test
    public void testGet() {
        final KeyVaultOperation keyVaultOperation = new KeyVaultOperation(
                keyVaultClient,
                DEFAULT_REFRESH_INTERVAL_MS,
                new ArrayList(),
                true);
        
        final LinkedHashMap<String, String> properties = new LinkedHashMap<>();
        properties.put("key1", "value1");
        properties.put("Key2", "Value2");
        keyVaultOperation.setProperties(properties);

        assertEquals("value1", keyVaultOperation.getProperty("key1"));
        assertEquals("Value2", keyVaultOperation.getProperty("Key2"));
    }
}
