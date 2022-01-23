package com.example.tcpserver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.tcpserver.utils.CRC8.crc8;
import static com.example.tcpserver.utils.CRC8.hexToAscii;

@ExtendWith({SpringExtension.class})
public class CRCTest {

    @Test
    public void checkCRC(){
        String stringCrcEnviadoEmail = "090131323334";

        var asciiValue = hexToAscii(stringCrcEnviadoEmail);
        var crcValue = crc8(asciiValue.getBytes());
        var value = String.format("0x%02x", crcValue);

        Assertions.assertThat(value).isEqualTo("0xc6");
    }
}
