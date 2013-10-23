package src;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import sun.util.calendar.LocalGregorianCalendar.Date;

import com.google.gson.stream.JsonReader;

public class SteamGetter {

	//TODO Need an interpriter of PersonaState
	//TODO Need to implement an ArrayList to store all of the data
	//TODO Need to implement the friendlist getter
	String SteamID;
	String personaname;
	String profileURL;
	String avatar32;
	String avatar64;
	String avatar184;
	int personaState;
	JsonReader reader;

	ArrayList<SteamProfile> friends;

	public SteamGetter(){}


	public void getSteamUser(long id) throws IOException {		
		playerSummary(reader = new JsonReader(new InputStreamReader(
				connect(Global.USERDATAURL,id).openStream())));
	}


	private URL connect(String URL, long id) throws MalformedURLException{
		URL url = new URL(URL+id);
		return url;
	}


	public void playerSummary(JsonReader reader) throws IOException{
		//TODO set up to handle private information
		//TODO clean up to improve efficiency.	
		//Gets only public variables at this point

		//"response"
		reader.beginObject(); 

		//"Players"
		reader.nextName();
		reader.beginObject(); 
		reader.nextName();

		//"["
		reader.beginArray();

		//"{" Player Object
		reader.beginObject(); 
		System.out.println("New User");

		//"SteamID"
		reader.nextName(); 
		SteamID = reader.nextString(); //SteamID Value 
		System.out.print("ID: ");
		System.out.println(SteamID);

		//"ComVisState"
		System.out.println(reader.nextName()); 
		reader.nextString(); //ComVisState Value

		//"ProfState"
		System.out.println(reader.nextName()); 
		reader.nextString(); //ProfState Value

		//"PersonaName"
		reader.nextName(); 
		personaname = reader.nextString(); //PersonaName Value
		System.out.print("Name: ");
		System.out.println(personaname);

		//"LastLogOff"
		System.out.println(reader.nextName()); 
		System.out.println(reader.nextString());//LastLog Value

		//"ProfileURL"
		reader.nextName();
		profileURL = reader.nextString(); //ProfileURL Value
		System.out.print("URL: ");
		System.out.println(profileURL);

		//"Avatar"
		reader.nextName();
		avatar32 = reader.nextString(); //"Avatar Value
		System.out.print("32x32 Avatar: "); 
		System.out.println(avatar32);

		//"AvatarMedium"
		reader.nextName(); 
		avatar64 = reader.nextString();//AvatarMedium Value
		System.out.print("64x64 Avatar: ");
		System.out.println(avatar64);

		//"AvatarFull"
		reader.nextName(); 
		avatar184 = reader.nextString(); //AvatarFull Value
		System.out.print("184x184 Avatar: ");
		System.out.println(avatar184);

		//"PersonnaState"
		System.out.print(reader.nextName()+": ");
		personaState = Integer.parseInt(reader.nextString());
		System.out.println(personaState);

		/*
		 * Any additional information is private
		 */
		//while(reader.hasNext()){}; //Pops Additional Info
		reader.close();
	}


	/**
	 * Gets a user's friends list and saves to an arraylist
	 * @param b 
	 * @param reader 
	 * @param sf 
	 * 
	 * @param id - the user id
	 * @throws IOException 
	 */
	//TODO Impliment this method to work properly
	public ArrayList<SteamUser> userFriendData(SteamUser u) throws IOException {

		SteamUser su = u;
		URL meSteam;
		JsonReader reader;
		ArrayList<SteamUser> sf = new ArrayList<SteamUser>();
		SteamGetter getter = new SteamGetter();

		try {
			meSteam = new URL(Global.FRIENDLISTURL + su.getID());
			try {
				//TODO use the new SteamGetter class to read through JSON
				reader = new JsonReader(new InputStreamReader(meSteam.openStream()));
				reader.beginObject();//JSON Object
				System.out.println(reader.nextName());
				reader.beginObject();//FriendList Object
				System.out.println(reader.nextName());
				reader.beginArray();//FriendList Array
				String id = null;
				String relationship = null;
				int friend_since = 0;
				while (reader.hasNext()){//Read Fields from Object
					reader.beginObject();
					reader.nextName();
					id = reader.nextString();
					reader.nextName();
					relationship = reader.nextString();
					reader.nextName();
					friend_since = Integer.parseInt(reader.nextString());

					System.out.println("steamid: "+ id);
					System.out.println("relationship: "+relationship);
					System.out.println("friend_since: "+friend_since+"\n");

					SteamUser data = new SteamUser(Long.parseLong(id));
					sf.add(data);
					reader.endObject();
				};
				reader.close();
			}catch (IOException e){
				System.out.println(e.getMessage());
				System.out.println(e.hashCode());
				//e.printStackTrace();
			} 
		}catch (MalformedURLException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//want a finnaly statement
		return sf;
	}


	/**
	 * Gets the steam user's game data
	 * 
	 * @param userID
	 * @param gameID
	 */
	//TODO Impliment the method
	public void getSteamUserGameData(long userID, long gameID){}

	/**
	 * sets a unix system date to gregorian date
	 * @return
	 */
	//TODO Low priority shift unix date to gregorian Date
	private Date toDate(){
		return null;
	}




}
