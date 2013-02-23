import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class T_Twitter {

	public static void main(String[] args) throws IOException {
		User [] users=UserImporter.loadUsers("Users.xml");
		ArrayList<Tweet> timeline=new ArrayList<Tweet>();
		for (int i=0; i<users.length; i++){
			Tweet[] time=null;
			try {
				time=TwitterImporter.loadTimeline(users[i].getUsername());
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			for (int j=0; j<time.length; j++){
				timeline.add(time[j]);
			}
		}
		timeline=Sort.sort(timeline);
		for (int i=0; i<timeline.size(); i++){
			System.out.println(timeline.get(i).toString());
		}
	}

}
