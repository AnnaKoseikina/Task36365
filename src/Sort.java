import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort {
	public static ArrayList<Tweet> sort(ArrayList<Tweet> timeline) {
		Collections.sort(timeline, new Comparator<Tweet>() {
			public int compare(Tweet a, Tweet b) {
				return b.getDate().getTime().compareTo(a.getDate().getTime());
			}
		});
		return timeline;
	}

}