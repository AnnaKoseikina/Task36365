import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
//import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TwitterImporter {
	public static Tweet[] loadTimeline(String screenName) throws IOException,
			ParserConfigurationException, SAXException {
		DataGrabber d = new DataGrabber();
		d.makeConnection(
				"https://twitter.com/statuses/user_timeline.xml?screen_name="
						+ screenName, screenName + "T.xml");
		String pathF = new String(screenName + "T.xml");
		File file = new File(pathF);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nodeLst = doc.getElementsByTagName("statuses_count");
		Tweet[] tweets = new Tweet[nodeLst.getLength()];
		NodeList created = doc.getElementsByTagName("created_at");
		NodeList text = doc.getElementsByTagName("text");
		NodeList names = doc.getElementsByTagName("name");
		NodeList screens = doc.getElementsByTagName("screen_name");
		NodeList re = doc.getElementsByTagName("retweet_count");
		for (int i = 0; i < tweets.length; i++) {
			Node dateTwi = created.item(2*i);
			Node textTwi = text.item(i);
			Node username = names.item(i);
			Node screenname = screens.item(i);
			Node retwi = re.item(i);
			tweets[i] = new Tweet(dateTwi.getTextContent(),
					textTwi.getTextContent(), username.getTextContent(),
					screenname.getTextContent(), retwi.getTextContent());

		}
		file.deleteOnExit();
		return tweets;
	}

}
