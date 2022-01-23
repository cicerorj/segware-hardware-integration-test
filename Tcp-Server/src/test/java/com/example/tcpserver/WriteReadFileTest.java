package com.example.tcpserver;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TemporaryFolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.*;

@ExtendWith({SpringExtension.class})
public class WriteReadFileTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void test() throws IOException {
        final File newFile = temporaryFolder.newFile();

        final String data = "{\"init\": \"0x0A\",\"bytes\": \"0x09\",\"frame\": \"0x01\",\"textMessage\": \"31323334\",\"crc\": \"0xA1\",\"end\": \"0xA2\"}";

        try (final FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
            IOUtils.copy(IOUtils.toInputStream(data, "UTF-8"), fileOutputStream);
        }

        try (final InputStream inputStream = new FileInputStream(newFile)) {
            Assert.assertEquals(data, IOUtils.toString(inputStream, "UTF-8"));
        }
    }

}
