import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Tweet {
	private String date_posted;
	private String text;
	private String name;
	private String screenName;
	private String retweeted;
	private GregorianCalendar date;

	public String getDate_posted() {
		return date_posted;
	}

	public void setDate_posted(String date_posted) {
		this.date_posted = date_posted;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public Tweet(String date_posted, String text, String name,
			String screenName, String retweeted) {
		this.date_posted = date_posted;
		this.text = text;
		this.name = name;
		this.screenName = screenName;
		this.retweeted = retweeted;
		this.date = convertStringToDate(date_posted);
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getRetweeted() {
		return retweeted;
	}

	public void setRetweeted(String retweeted) {
		this.retweeted = retweeted;
	}

	public String toString() {
		// GregorianCalendar date = null;
		date = convertStringToDate(date_posted);
		date_posted = convertDateToString(date);
		String twi = name + "(@" + screenName + "):" + '\n' + text + '\n'
				+ date_posted + " ~ " + retweeted + " retweets" + '\n';
		return twi;
	}

	private GregorianCalendar convertStringToDate(String createdAtText) {
		GregorianCalendar calendar = new GregorianCalendar();
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy",
				Locale.ENGLISH);
		try {
			date = (Date) formatter.parse(createdAtText);
			calendar.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	private static String convertDateToString(GregorianCalendar date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"MMMM d, yyyy - h:ma", Locale.ENGLISH);
		return simpleDateFormat.format(date.getTime());
	}

}
