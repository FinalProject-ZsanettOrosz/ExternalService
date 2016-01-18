package motivationpic;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class InstagramClient {
	private String[] motivPics;

	public String[] getMotivPics() {
		return motivPics;
	}

	public InstagramClient() {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());

		String result = service.path("").request()
				.accept(MediaType.APPLICATION_JSON).get()
				.readEntity(String.class);

		JSONObject ja = new JSONObject(result);
		System.out.println(ja.toString(4));

		JSONArray data = ja.getJSONArray("data");
		System.out.println(data.toString(4));

		motivPics = new String[data.length()];
		for (int i = 0; i < data.length(); i++) {
			JSONObject inside = data.getJSONObject(i);
			JSONObject images = inside.getJSONObject("images");

			JSONObject standard = images.getJSONObject("standard_resolution");
			String imgUrl = standard.getString("url");
			motivPics[i] = imgUrl;

		}
		for (int i = 0; i < data.length(); i++) {
			System.err.println(motivPics[i]);
		}
	}

	private static URI getBaseURI() {
		return UriBuilder
				.fromUri(
						"https://api.instagram.com/v1/tags/fitmotivation/media/recent?access_token=1591445470.1677ed0.9fe45e269ce04fe5ba740e35dfd03ced")
				.build();
	}
}
