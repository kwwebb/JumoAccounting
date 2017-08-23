package world.jumo.accounting.parser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class StringCSVParserTest {

    StringCSVParser parser;

    @Mock
    File file;

    @Before
    public void setup() {
        parser = new StringCSVParser('"', ';');
    }

    @Test
    public void parseLineTest() {

        List<String> stringList = parser.parseLine("1;\"Hello World\";2014-10-01\r\n2423;bob;\"2015-01-01\"");

        assertNotNull(stringList);
        assertEquals(6, stringList.size());
        assertEquals("1", stringList.get(0));
        assertEquals("Hello World", stringList.get(1));
        assertEquals("2014-10-01", stringList.get(2));
    }

    @Test
    public void parseNullLineTest() {

        List<String> stringList = parser.parseLine(null);

        assertNull(stringList);
    }

    @Test
    public void parseEmptyLineTest() {

        List<String> stringList = parser.parseLine("");

        assertNotNull(stringList);
        assertEquals(0, stringList.size());
    }

}
