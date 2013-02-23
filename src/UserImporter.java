import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserImporter {
	public static User[] loadUsers(String documentLocation) {
		User[] users = new User[getRows(documentLocation)-2];
		try {
			File file = new File(documentLocation);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("user");
			for (int s = 0; s < nodeLst.getLength(); s++) {
				Node fstNode = nodeLst.item(s);
				users[s]=new User();
				users[s].setUsername(fstNode.getTextContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public static int getRows(String filePath) {
		String line;
		int n = 0;
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			line = bufferedReader.readLine();
			while (line != null) {
				n++;
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			return n;
		} catch (IOException e) {
			System.out.println("Error");
			return 0;
		}
	}

}
