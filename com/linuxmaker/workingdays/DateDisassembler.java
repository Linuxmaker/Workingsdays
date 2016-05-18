package com.linuxmaker.workingdays;

/**
 * Created by @author Andreas Günther, IT-LINUXMAKER on 15.05.16.
 */
public class DateDisassembler {
    /**
	 * @param date
	 * The parameter date will disassemble to day(0), to month(1) and to year(2).
	 * It returns an array variable that contains these three values​​.
	 */

    public int[] disassemble(String date) {
        int dateShare[] = {1,2,3};
		String delimiter = ".";
/*		if ((date.charAt(date.length()-5)).) {

		} else {

		}*/
		dateShare[0] = Integer.parseInt(StringUtils.substringBefore(date, delimiter));
		dateShare[1] = Integer.parseInt(StringUtils.substringBefore(StringUtils.substringAfter(date, delimiter), delimiter));
		dateShare[2] = Integer.parseInt(StringUtils.substringAfter(StringUtils.substringAfter(date, delimiter),delimiter));
		return dateShare;
    }
}
