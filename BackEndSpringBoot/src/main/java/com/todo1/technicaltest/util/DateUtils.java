package com.todo1.technicaltest.util;

import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	/**
	 * Julian Valencia
	 * @return
	 */
    public static Date getDateUTCFormat(){

    	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    	Date dateUTC = new Date();
        return dateUTC;
        
    }
}
