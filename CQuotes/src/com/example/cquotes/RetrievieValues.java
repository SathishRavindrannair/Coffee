package com.example.cquotes;

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

public class RetrievieValues extends AsyncTask<String, String, String> {

	private Exception exception;

	@SuppressWarnings("finally")
	protected String doInBackground(String... urls) {
		String status = "failure";
		InputStream is = null;
		QuotesConstants constants = QuotesConstants.getInstance();

		// ********Modified on 20-07-2015*****************//*
		String jsonText = "";
		BufferedReader rd = null;

		try {
			
			try {
				// **********************************For Old Era*******************************//*
				{
					try {
						is = new URL(
								"https://scorching-inferno-116.firebaseio.com/leaderHelper.json").openStream();
						rd = new BufferedReader(new InputStreamReader(is,
								Charset.forName("UTF-8")));
						jsonText = readAll(rd);
						System.out.println("Json array" + jsonText);
						JSONArray array = new JSONArray(jsonText);

						HashMap<String, LeaderHelper> hashMap = new HashMap<String, LeaderHelper>();
						int id = 0;
						for (int i = 0; i < array.length(); i++) {
							JSONObject jsonobj = (JSONObject) array.get(i);
							String name = jsonobj.getString("leader_name");
							String active = jsonobj.getString("active");
							String thumbnail = jsonobj.getString("image_url");
							System.out.println("*****" + name + "******"+ active + "********" + thumbnail);
							LeaderHelper leaderHelper = new LeaderHelper();
							leaderHelper.setActive(jsonobj.getString("active"));
							leaderHelper.setLeader_name(jsonobj.getString("leader_name"));
							leaderHelper.setImage_url(downloadBitmap(jsonobj.getString("image_url").replaceAll("\\/","/")));
							leaderHelper.setId(jsonobj.getString("id"));
							leaderHelper.setContent(jsonobj.getString("content"));
							JSONArray msg = (JSONArray) jsonobj.getJSONArray("quotes");
							String[] quotes = new String[msg.length()];
							for (int j = 0; j < msg.length(); j++) {
								quotes[j] = msg.getString(j);

							}
							System.out.println("*****" + name + "******"+ active + "********" + thumbnail + "*****"+ quotes.toString());
							leaderHelper.setQuotes(quotes);
							id++;
							hashMap.put((new Integer(id)).toString(),
									leaderHelper);

						}
						constants.hashMap = hashMap;
					} catch (Exception ex) {
						System.out.println("Exception in 90" + ex.toString());
					}
				}
				// **********************************For Modern Era*******************************//*
				{
					try {
						is = new URL(
								"https://scorching-inferno-116.firebaseio.com/leaderHelper20.json")
								.openStream();
						rd = new BufferedReader(new InputStreamReader(is,
								Charset.forName("UTF-8")));
						jsonText = readAll(rd);
						System.out.println("Json array" + jsonText);
						JSONArray array = new JSONArray(jsonText);
						HashMap<String, LeaderHelper> hashMap = new HashMap<String, LeaderHelper>();
						int id = 0;
						for (int i = 0; i < array.length(); i++) {
							JSONObject jsonobj = (JSONObject) array.get(i);
							String name = jsonobj.getString("leader_name");
							String active = jsonobj.getString("active");
							String thumbnail = jsonobj.getString("image_url");
							System.out.println("*****" + name + "******"
									+ active + "********" + thumbnail);
							LeaderHelper leaderHelper = new LeaderHelper();
							leaderHelper.setActive(jsonobj.getString("active"));
							leaderHelper.setLeader_name(jsonobj
									.getString("leader_name"));
							leaderHelper.setImage_url(downloadBitmap(jsonobj
									.getString("image_url").replaceAll("\\/",
											"/")));
							leaderHelper.setId(jsonobj.getString("id"));
							leaderHelper.setContent(jsonobj
									.getString("content"));
							JSONArray msg = (JSONArray) jsonobj
									.getJSONArray("quotes");
							String[] quotes = new String[msg.length()];
							for (int j = 0; j < msg.length(); j++) {
								quotes[j] = msg.getString(j);

							}
							System.out.println("*****" + name + "******"
									+ active + "********" + thumbnail + "*****"
									+ quotes.toString());
							leaderHelper.setQuotes(quotes);
							id++;
							hashMap.put((new Integer(id)).toString(),
									leaderHelper);

						}
						constants.hashMap20 = hashMap;
					} catch (Exception ex) {
						System.out.println("Exception in 20" + ex.toString());
					}
				}

				// **********************************For Filmy
				// Era*******************************//*
				{
					try {
						is = new URL(
								"https://scorching-inferno-116.firebaseio.com/filmyHelpers.json")
								.openStream();
						rd = new BufferedReader(new InputStreamReader(is,
								Charset.forName("UTF-8")));
						jsonText = readAll(rd);
						System.out.println("Json array" + jsonText);
						JSONArray array = new JSONArray(jsonText);
						HashMap<String, FilmyHelper> hashMap = new HashMap<String, FilmyHelper>();
						int id = 0;
						for (int i = 0; i < array.length(); i++) {
							JSONObject jsonobj = (JSONObject) array.get(i);
							String movie_name = jsonobj.getString("movie_name");
							String starring = jsonobj.getString("starring");
							String thumbnail = jsonobj.getString("image_url");
							System.out.println("*****" + movie_name + "******"
									+ starring + "********" + thumbnail);
							FilmyHelper filmHelper = new FilmyHelper();
							filmHelper.setStarring(jsonobj
									.getString("starring"));
							filmHelper.setMovie_name(jsonobj
									.getString("movie_name"));
							filmHelper.setImage_url(downloadBitmap(jsonobj
									.getString("image_url").replaceAll("\\/",
											"/")));
							filmHelper.setId(jsonobj.getString("id"));
							filmHelper.setContent(jsonobj.getString("content"));
							JSONArray msg = (JSONArray) jsonobj
									.getJSONArray("quotes");
							String[] quotes = new String[msg.length()];
							for (int j = 0; j < msg.length(); j++) {
								quotes[j] = msg.getString(j);

							}
							System.out.println("*****" + movie_name + "******"
									+ starring + "********" + thumbnail
									+ "*****" + quotes.toString());
							filmHelper.setQuotes(quotes);
							id++;
							hashMap.put((new Integer(id)).toString(),
									filmHelper);

						}
						constants.hashMapfilmy = hashMap;

					} catch (Exception ex) {
						System.out.println("Exception In Film" + ex.toString());

					}
				}
				status = "success";
			} catch (Exception ex) {
				System.out.println("exception" + ex.toString());
			}
			return status;

		} catch (Exception ex) {

		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return status;
		}

	}

	protected void test(String... urls) {
		InputStream is = null;
		QuotesConstants constants = QuotesConstants.getInstance();
		try {
			System.out.println("Json*************");

			is = new URL(
					"http://10.0.2.2:8080/QuotesWebDomain/quotesRest/activityimages/json")
					.openStream();
			is = new URL(
					"https://scorching-inferno-116.firebaseio.com/celebrities.json")
					.openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			System.out.println("Json array" + jsonText);
			JSONObject json = new JSONObject(jsonText);
			JSONArray array = json.getJSONArray("leaderHelper");
			HashMap<String, LeaderHelper> hashMap = new HashMap<String, LeaderHelper>();
			int id = 0;
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonobj = (JSONObject) array.get(i);
				String name = jsonobj.getString("leader_name");
				String active = jsonobj.getString("active");
				String thumbnail = jsonobj.getString("image_url");
				System.out.println("*****" + name + "******" + active
						+ "********" + thumbnail);
				LeaderHelper leaderHelper = new LeaderHelper();
				leaderHelper.setActive(jsonobj.getString("active"));
				leaderHelper.setLeader_name(jsonobj.getString("leader_name"));
				leaderHelper.setImage_url(downloadBitmap(jsonobj
						.getString("image_url")));
				id++;
				hashMap.put((new Integer(id)).toString(), leaderHelper);

			}
			constants.hashMap = hashMap;

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

// package com.example.coffeequotes;
//
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.Reader;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.URL;
// import java.nio.charset.Charset;
// import java.util.ArrayList;
// import java.util.HashMap;
//
// import javax.xml.parsers.DocumentBuilder;
// import javax.xml.parsers.DocumentBuilderFactory;
// import javax.xml.parsers.ParserConfigurationException;
//
// import org.apache.http.HttpStatus;
// import org.json.JSONArray;
// import org.json.JSONObject;
// import org.w3c.dom.Document;
// import org.w3c.dom.NodeList;
// import org.xml.sax.InputSource;
// import org.xml.sax.SAXException;
//
// import android.graphics.Bitmap;
// import android.graphics.BitmapFactory;
// import android.os.AsyncTask;
// import android.util.Log;
//
// public class RetrievieValues extends AsyncTask<String,String,String> {
//
// private Exception exception;
//
// @SuppressWarnings("finally")
// protected String doInBackground(String... urls) {
// String status="failure";
// InputStream is = null;
// QuotesConstants constants=QuotesConstants.getInstance();
// try {
// System.out.println("Json*************");
//
// is = new
// URL("http://10.0.2.2:8080/QuotesWebDomain/quotesRest/activityimages/json").openStream();
// /* is = new URL(
// "https://scorching-inferno-116.firebaseio.com/celebrities.json")
// .openStream();*/
// BufferedReader rd = new BufferedReader(new InputStreamReader(is,
// Charset.forName("UTF-8")));
// String jsonText = readAll(rd);
// System.out.println("Json array"+jsonText);
// try{
// JSONObject json = new JSONObject(jsonText);
// /**********************************For Old
// Era*******************************/
// {
// JSONArray array=json.getJSONArray("leaderHelper");
//
// HashMap<String, LeaderHelper> hashMap=new HashMap<String, LeaderHelper>();
// int id=0;
// for(int i=0;i<array.length();i++){
// JSONObject jsonobj=(JSONObject) array.get(i);
// String name=jsonobj.getString("leader_name");
// String active=jsonobj.getString("active");
// String thumbnail=jsonobj.getString("image_url");
// System.out.println("*****"+name+"******"+active+"********"+thumbnail);
// LeaderHelper leaderHelper=new LeaderHelper();
// leaderHelper.setActive(jsonobj.getString("active"));
// leaderHelper.setLeader_name(jsonobj.getString("leader_name"));
// leaderHelper.setImage_url(downloadBitmap(jsonobj.getString("image_url")));
// leaderHelper.setId(jsonobj.getString("id"));
// leaderHelper.setContent(jsonobj.getString("content"));
// JSONArray msg = (JSONArray) jsonobj.getJSONArray("quotes");
// String[] quotes=new String[msg.length()];
// for(int j=0;j<msg.length();j++)
// {
// quotes[j]=msg.getString(j);
//
// }
// System.out.println("*****"+name+"******"+active+"********"+thumbnail+"*****"+quotes.toString());
// leaderHelper.setQuotes(quotes);
// id++;
// hashMap.put((new Integer(id)).toString(), leaderHelper);
//
// }
// constants.hashMap=hashMap;
// }
// /**********************************For Modern
// Era*******************************/
// {
// JSONArray array=json.getJSONArray("leaderHelper20");
// HashMap<String, LeaderHelper> hashMap=new HashMap<String, LeaderHelper>();
// int id=0;
// for(int i=0;i<array.length();i++){
// JSONObject jsonobj=(JSONObject) array.get(i);
// String name=jsonobj.getString("leader_name");
// String active=jsonobj.getString("active");
// String thumbnail=jsonobj.getString("image_url");
// System.out.println("*****"+name+"******"+active+"********"+thumbnail);
// LeaderHelper leaderHelper=new LeaderHelper();
// leaderHelper.setActive(jsonobj.getString("active"));
// leaderHelper.setLeader_name(jsonobj.getString("leader_name"));
// leaderHelper.setImage_url(downloadBitmap(jsonobj.getString("image_url")));
// leaderHelper.setId(jsonobj.getString("id"));
// leaderHelper.setContent(jsonobj.getString("content"));
// JSONArray msg = (JSONArray) jsonobj.getJSONArray("quotes");
// String[] quotes=new String[msg.length()];
// for(int j=0;j<msg.length();j++)
// {
// quotes[j]=msg.getString(j);
//
// }
// System.out.println("*****"+name+"******"+active+"********"+thumbnail+"*****"+quotes.toString());
// leaderHelper.setQuotes(quotes);
// id++;
// hashMap.put((new Integer(id)).toString(), leaderHelper);
//
// }
// constants.hashMap20=hashMap;
//
// }
//
// /**********************************For Filmy
// Era*******************************/
// {
// JSONArray array=json.getJSONArray("filmyHelpers");
// HashMap<String, FilmyHelper> hashMap=new HashMap<String, FilmyHelper>();
// int id=0;
// for(int i=0;i<array.length();i++){
// JSONObject jsonobj=(JSONObject) array.get(i);
// String movie_name=jsonobj.getString("movie_name");
// String starring=jsonobj.getString("starring");
// String thumbnail=jsonobj.getString("image_url");
// System.out.println("*****"+movie_name+"******"+starring+"********"+thumbnail);
// FilmyHelper filmHelper=new FilmyHelper();
// filmHelper.setStarring(jsonobj.getString("starring"));
// filmHelper.setMovie_name(jsonobj.getString("movie_name"));
// filmHelper.setImage_url(downloadBitmap(jsonobj.getString("image_url")));
// filmHelper.setId(jsonobj.getString("id"));
// filmHelper.setContent(jsonobj.getString("content"));
// JSONArray msg = (JSONArray) jsonobj.getJSONArray("quotes");
// String[] quotes=new String[msg.length()];
// for(int j=0;j<msg.length();j++)
// {
// quotes[j]=msg.getString(j);
//
// }
// System.out.println("*****"+movie_name+"******"+starring+"********"+thumbnail+"*****"+quotes.toString());
// filmHelper.setQuotes(quotes);
// id++;
// hashMap.put((new Integer(id)).toString(), filmHelper);
//
// }
// constants.hashMapfilmy=hashMap;
//
// }
// status="success";
// }catch(Exception ex){System.out.println("exception"+ex.toString());}
// return status;
//
// } catch (Exception ex) {
//
// } finally {
// try {
// is.close();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// return status;
// }
//
//
//
// }
// protected void test(String... urls) {
// InputStream is = null;
// QuotesConstants constants=QuotesConstants.getInstance();
// try {
// System.out.println("Json*************");
//
// is = new URL(
// "http://10.0.2.2:8080/QuotesWebDomain/quotesRest/activityimages/json")
// .openStream();
// /* is = new URL(
// "https://scorching-inferno-116.firebaseio.com/celebrities.json")
// .openStream();*/
// BufferedReader rd = new BufferedReader(new InputStreamReader(is,
// Charset.forName("UTF-8")));
// String jsonText = readAll(rd);
// System.out.println("Json array"+jsonText);
// JSONObject json = new JSONObject(jsonText);
// JSONArray array=json.getJSONArray("leaderHelper");
// HashMap<String, LeaderHelper> hashMap=new HashMap<String, LeaderHelper>();
// int id=0;
// for(int i=0;i<array.length();i++){
// JSONObject jsonobj=(JSONObject) array.get(i);
// String name=jsonobj.getString("leader_name");
// String active=jsonobj.getString("active");
// String thumbnail=jsonobj.getString("image_url");
// System.out.println("*****"+name+"******"+active+"********"+thumbnail);
// LeaderHelper leaderHelper=new LeaderHelper();
// leaderHelper.setActive(jsonobj.getString("active"));
// leaderHelper.setLeader_name(jsonobj.getString("leader_name"));
// leaderHelper.setImage_url(downloadBitmap(jsonobj.getString("image_url")));
// id++;
// hashMap.put((new Integer(id)).toString(), leaderHelper);
//
// }
// constants.hashMap=hashMap;
//
// } catch (Exception ex) {
//
// } finally {
// try {
// is.close();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
//
// }
// private static String readAll(Reader rd) throws IOException {
// StringBuilder sb = new StringBuilder();
// int cp;
// while ((cp = rd.read()) != -1) {
// sb.append((char) cp);
// }
// return sb.toString();
// }
//
//
// protected void onPostExecute(String feed) {
// // TODO: check this.exception
// // TODO: do something with the feed
// }
// private Bitmap downloadBitmap(String url) {
// HttpURLConnection urlConnection = null;
// try {
// URL uri = new URL(url);
// urlConnection = (HttpURLConnection) uri.openConnection();
// int statusCode = urlConnection.getResponseCode();
// if (statusCode != HttpStatus.SC_OK) {
// return null;
// }
//
// InputStream inputStream = urlConnection.getInputStream();
// if (inputStream != null) {
// Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
// return bitmap;
// }
// } catch (Exception e) {
// urlConnection.disconnect();
// Log.w("ImageDownloader", "Error downloading image from " + url);
// } finally {
// if (urlConnection != null) {
// urlConnection.disconnect();
// }
// }
// return null;
// }
//
// }
