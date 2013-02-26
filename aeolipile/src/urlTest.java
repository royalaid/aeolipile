import java.net.*;
import java.util.ArrayList;
import java.io.*;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;



public class urlTest {
	private static SteamUser su;
	
	/**
	 * @param <steamFriend>
	 * @param args
	 */
	public static <steamFriend> void main(String[] args){
		URL meSteam;
		JsonReader reader;
		su = new SteamUser(0);
		ArrayList<SteamFriend> sf = new ArrayList<SteamFriend>();
		
		try {
			meSteam = new URL("http://api.steampowered.com/ISteamUser/" +
					"GetFriendList/v0001/?key=26A0BE6F08077299B964BBEFBAEE5AA0" +
					"&relationship=friend&format=json&steamid=76561197968613153");
			try {
				reader = new JsonReader(new InputStreamReader(meSteam.openStream()));
				
				reader.beginObject();
				System.out.println(reader.nextName());
				
				reader.beginObject();
				System.out.println(reader.nextName());
				
				reader.beginArray();
				
				while(reader.hasNext()){
					reader.beginObject(); //for each steam id get the 3 objects
					String id = null;
					String relationship = null;
					int friend_since = 0;
					while (reader.hasNext()){
						reader.nextName();
						id = reader.nextString();
						reader.nextName();
						relationship = reader.nextString();
						reader.nextName();
						friend_since =Integer.parseInt(reader.nextString());
					};
					System.out.println("steamid: "+ id);
					System.out.println("relationship: "+relationship);
					System.out.println("friend_since: "+friend_since);
					
					System.out.println();
					SteamFriend data = new SteamFriend(id, relationship, friend_since);
					sf.add(data);
					//SteamFriend.add();
					reader.endObject();
				};
			}
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	}

}
