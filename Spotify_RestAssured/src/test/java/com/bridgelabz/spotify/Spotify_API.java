package com.bridgelabz.spotify;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Spotify_API {
	public String token;
	String userId;
	String playlistId= "4BMQuCrpSLFS20mqqfjOuL";
	String trackId = "5Gy67YHzPZTk9Q0D73LAGH";
	
	
	@BeforeTest
	public void getToken() {
		token = "Bearer BQApxPh0qUmDb5pB2oih8lxuknce0pnZoUOsZC-ekvtTpFjsag3gYnt16n509SkaM5HVAKneO6YzROvRDe66DbwWgodrRnDMxP-oS2zvrPISbfpp9qVXZ82fKdNWKXFMFM0RrYlqconAFi79DvSJGCR9XZfmS7fIiw4B8zFMdJOqovVGKGYIKd72Wpl60sxlBbB9GD9ZEsOJXlJzSLnhtOl57jNATvdTprofmRxc1tqcd9Vhk5nfUk507WcPHDXL2QckpWgEJQdMOlaEMYbl_SfJIVADbNQkpsDr9MUTU6t8r7YD-RiG4QEeVLkNrrv9HjzjotEQ";
	}
	
	@Test (priority = 0)
	public void testGet_Current_Profile() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		userId = response.path("id");
		System.out.println("USer Id is "+userId);
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(userId, "9qdavk7tbrovob7x86ab0643a");
	}
	
	@Test (priority = 1)
	public void testGet_UserProfile() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userId+"");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		System.out.println("status code " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
//	@Test (priority = 2)
//	public void createPlaylist() {
//		Response response = RestAssured.given().contentType(ContentType.JSON)
//				.accept(ContentType.JSON)
//				.header("Authorization",token)
//				.body("{\r\n"
//						+ "  \"name\": \"Automated Playlist\",\r\n"
//						+ "  \"description\": \"New playlist description\",\r\n"
//						+ "  \"public\": false\r\n"
//						+ "}")
//				.when()
//				.post("https://api.spotify.com/v1/users/"+userId+"/playlists");
//		response.prettyPrint();
//		playlistId = response.path("id");
//		System.out.println("Playlist Id is "+playlistId);
//		response.then().assertThat().statusCode(201);
//	}
	
	@Test (priority = 2)
	public void GetUserPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userId+"/playlists");
		response.prettyPrint();
		playlistId = response.path("id");
		System.out.println("Playlist Id is "+playlistId);
		response.then().assertThat().statusCode(200);
	}
	
	
	@Test (priority = 3)
		public void GetPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userId+"/playlists");
		response.prettyPrint();
		playlistId = response.path("id");
		System.out.println("Playlist Id is "+playlistId);
		response.then().assertThat().statusCode(200);
	}

	@Test (priority = 4)
	public void AddItemsToPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
//				.queryParam("position", "0")
//				.queryParam("uris","spotify:track:42yP2Fd4GIcIDABKs5JBAq")
//				.queryParam("position", "1")
//				.queryParam("uris","spotify:track:1OeX1bIG5kIHoPDnaNnSx5")
//				.queryParam("position", "2")
//				.queryParam("uris", "spotify:track:0tpwqQhdfJXRcUDSNRjb9O")
//				.queryParam("position", "2")
//				.queryParam("uris", "spotify:track:6cZER0XaxSRdwBxebDINsk")
				.queryParam("position", "3")
				.queryParam("uris", "spotify:track:5Gy67YHzPZTk9Q0D73LAGH")
				.when()
				.post("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
		response.prettyPrint();
		playlistId = response.path("id");
		System.out.println("Playlist Id is "+playlistId);
		response.then().assertThat().statusCode(201);
	}
	
	@Test (priority = 5)
	public void GetPlaylistItem() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority = 6)
	public void GetCurrentUsersPlaylists() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get(" 	https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}	
	
	@Test (priority = 7)
	public void GetPlaylistCoverImage() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get(" 	https://api.spotify.com/v1/playlists/"+playlistId+"/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority = 8)
	public void ChangePlaylistDetails () {
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization",token)
					.body("{\r\n"
							+ "  \"name\": \"API playlist\",\r\n"
							+ "  \"description\": \"Updated playlist description\",\r\n"
							+ "  \"public\": false\r\n"
							+ "}")
					.when()
					.put("https://api.spotify.com/v1/playlists/"+playlistId+"");
			response.prettyPrint();
			response.then().assertThat().statusCode(200);
		}
	
	@Test (priority = 9)
	public void UpdatePlaylistItem () {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.body("{\r\n"
						+ "  \"range_start\": 1,\r\n"
						+ "  \"insert_before\": 0,\r\n"
						+ "  \"range_length\": 3\r\n"
						+ "}")	
				.when()
				.put("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	@Test (priority = 10)
	public void RemovePlaylistItem () {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
//				.queryParam("uris","spotify:track:1OeX1bIG5kIHoPDnaNnSx5")
				.body("{\r\n"
						+ "    \"tracks\": [{\"uri\": \"spotify:track:1OeX1bIG5kIHoPDnaNnSx5\"}]\r\n"
						+ "}")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}	
	
	@Test (priority = 11)
	public void SearchForItem() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.queryParam("q", "Arijit singh")
				.queryParam("type", "track"	)
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority = 12)
	public void GetTrackAudioAnalysis () {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/audio-analysis/"+trackId+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority = 13)
	public void GetTrackAudioFeature () {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/"+trackId+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority = 14)
	public void GetTrackAudioFeatures() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	@Test (priority = 15)
	public void GetSeveralTrack () {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/"+trackId+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority = 16)
	public void GetTrack() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/tracks/"+trackId+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
}

