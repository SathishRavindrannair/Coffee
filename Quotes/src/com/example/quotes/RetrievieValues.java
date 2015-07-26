package com.example.quotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class RetrievieValues extends AsyncTask<String,String,ArrayList<Bitmap>> {

	    private Exception exception;

	    protected ArrayList<Bitmap> doInBackground(String... urls) {
	    	URL url;
	    	 ArrayList<Bitmap> arrayList=null;;
			try {
				url = new URL(
						"http://10.0.2.2:8080/QuotesWebDomain/quotesRest/activityimages/xml");

				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(new InputSource(url.openStream()));
				doc.getDocumentElement().normalize();
                   arrayList=new ArrayList<Bitmap>();
				NodeList nodeList = doc.getElementsByTagName("URL");
				test();
				for (int i = 0; i < nodeList.getLength(); i++) {
		            System.out.println("" + nodeList.item(i).getTextContent());
		           Bitmap bitmap=downloadBitmap(nodeList.item(i).getTextContent());
		            arrayList.add(bitmap);
		         }
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  arrayList;
				
	    }
	    protected void test(String... urls) {
			InputStream is = null;
		QuotesConstants constants=QuotesConstants.getInstance();
			try {
				System.out.println("Json*************");

				is = new URL(
						"http://10.0.2.2:8080/QuotesWebDomain/quotesRest/activityimages/json")
						.openStream();
			/*	is = new URL(
						"https://scorching-inferno-116.firebaseio.com/celebrities.json")
						.openStream();*/
				BufferedReader rd = new BufferedReader(new InputStreamReader(is,
						Charset.forName("UTF-8")));
				String jsonText = readAll(rd);
				System.out.println("Json array"+jsonText);
				JSONObject json = new JSONObject(jsonText);
				JSONArray array=json.getJSONArray("leaderHelper");
			  HashMap<String, LeaderHelper> hashMap=new HashMap<String, LeaderHelper>();
			  int id=0;
				for(int i=0;i<array.length();i++){
				  JSONObject jsonobj=(JSONObject) array.get(i);
				  String name=jsonobj.getString("leader_name");
				  String active=jsonobj.getString("active");
				  String thumbnail=jsonobj.getString("image_url");
				  System.out.println("*****"+name+"******"+active+"********"+thumbnail);
				  LeaderHelper leaderHelper=new LeaderHelper();
				  leaderHelper.setActive(jsonobj.getString("active"));
				  leaderHelper.setLeader_name(jsonobj.getString("leader_name"));
				  leaderHelper.setImage_url(downloadBitmap(jsonobj.getString("image_url")));
				  id++;
				  hashMap.put((new Integer(id)).toString(), leaderHelper);
				  
			  }
				constants.hashMap=hashMap;
				
			} catch (Exception ex) {

			} finally {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	    private static String readAll(Reader rd) throws IOException {
	        StringBuilder sb = new StringBuilder();
	        int cp;
	        while ((cp = rd.read()) != -1) {
	          sb.append((char) cp);
	        }
	        return sb.toString();
	      }


	    protected void onPostExecute(String feed) {
	        // TODO: check this.exception 
	        // TODO: do something with the feed
	    }
	    private Bitmap downloadBitmap(String url) {
	        HttpURLConnection urlConnection = null;
	        try {
	            URL uri = new URL(url);
	            urlConnection = (HttpURLConnection) uri.openConnection();
	            int statusCode = urlConnection.getResponseCode();
	            if (statusCode != HttpStatus.SC_OK) {
	                return null;
	            }

	            InputStream inputStream = urlConnection.getInputStream();
	            if (inputStream != null) {
	                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
	                return bitmap;
	            }
	        } catch (Exception e) {
	            urlConnection.disconnect();
	            Log.w("ImageDownloader", "Error downloading image from " + url);
	        } finally {
	            if (urlConnection != null) {
	                urlConnection.disconnect();
	            }
	        }
	        return null;
	    }
	
}
