package world.jumo.accounting.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DateMapperTest {

    DateMapper dateMapper;

    @Before
    public void setup() {
        dateMapper = new DateMapper();
    }

    @Test
    public void dateStringToDateTest() {

        // given
        String dateString = getDateString();

        // when
        Date mappedDate = dateMapper.asDate(dateString);

        // then
        assertNotNull(mappedDate);

        Date date = getDate();
        assertEquals(date, mappedDate);
    }

    @Test
    public void dateToDateStringTest() {

        // given
        Date date = getDate();

        // when
        String mappedDateString = dateMapper.asString(date);

        // then
        assertNotNull(mappedDateString);

        String dateString = getDateString();
        assertEquals(dateString, mappedDateString);
    }

    static String getDateString() {
        return "12-Mar-2016";
    }

    static Date getDate() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DATE, 12);

        return clearTime(cal.getTime());
    }

    static Date clearTime(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);

        return cal.getTime();
    }

}
