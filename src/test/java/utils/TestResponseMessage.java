package utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import application.utils.ResponseMessage;

/**
 * Test class for ResponseMessage utils
 */
@DisplayName("ResponseMessage Utils")
@ExtendWith(MockitoExtension.class)
public class TestResponseMessage {
    /**
     * Test method for printMethodErrorString method.
     */
    @Test
    @DisplayName("Ensure `printMethodErrorString` works")
    void testPrintMethodErrorString() throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        String mockMessage = "Ensure `printMethodErrorString` works";
        Exception e = new Exception(mockMessage);

        ResponseMessage.printMethodErrorString(this.getClass(), e);

        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        assertTrue(allWrittenLines.contains(this.getClass().getName())
                && allWrittenLines.contains(e.getStackTrace()[0].getMethodName())
                && allWrittenLines.contains(mockMessage));
    }
}
