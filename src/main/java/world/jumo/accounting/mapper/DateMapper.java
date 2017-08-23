package world.jumo.accounting.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
public class DateMapper {

    private static final String DATE_FORMAT = "dd-MMM-yyyy";

    public String asString(Date date) {
        return date != null ? new SimpleDateFormat(DATE_FORMAT).format(date) : null;
    }

    public Date asDate(String date) {
        try {
            return date != null ? new SimpleDateFormat(DATE_FORMAT).parse(date) : null;
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
