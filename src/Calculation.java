import java.util.Calendar;
import java.util.Date;

public class Calculation {

	public static Date AddDays(Date date, int days) {

		return new Date(date.getTime() + (long) days * 24 * 60 * 60 * 1000);
	}

	public static Date AddMonths(Date date, int months) {

		// Break the base date into components and adjust the months
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + months;
		int year = calendar.get(Calendar.YEAR);

		// Rolled backward into previous year(s)
		while (month < 1) {
			month = month + 12;
			year = year - 1;
		}

		// Rolled forward into next year(s)
		while (month > 12) {
			month = month - 12;
			year = year + 1;
		}
		// Adjust end of month dates
		if (day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
			day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day);

		return calendar.getTime();
	}

	public static void AddInterval(Date date, Interval interval) {
		System.out.println("Trade Date: "+date.toString());
		switch (interval.getPeriod()) {
		case "D":
			date = AddDays(date, interval.getMultiplier());
			break;
		case "W":
			date = AddDays(date, 7 * interval.getMultiplier());
			break;
		case "M":
			date = AddMonths(date, interval.getMultiplier());
			break;
		case "Y":
			date = AddMonths(date, 12 * interval.getMultiplier());
			break;

		default:
			// Otherwise its an invalid period type
			System.out.println("Error.");
		}
		System.out.println("Termination Date: "+date.toString());
	}

}
