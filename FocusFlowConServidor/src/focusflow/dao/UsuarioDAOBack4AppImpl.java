package focusflow.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import focusflow.models.Usuario;
import okhttp3.*;

public class UsuarioDAOBack4AppImpl implements IUsuarioDAO {

    private final String API_URL = "https://parseapi.back4app.com/classes/Usuario";
    private final String APPLICATION_ID = "h6lniNE1cXU2UnDw92VjAtfmBUYfdsMGdxvMqpmE";
    private final String REST_API_KEY = "OkQszHeQxF9sj6iuAb3s1q8falGTBjQIp2biKdAi";

    @Override
    public Usuario getUsuarioPorNombre(String nombre) {
        Usuario usuarioEncontrado = null;
        try {
            // Construimos la URL con la query "where" para filtrar por nombre
            HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https").host("parseapi.back4app.com")
                .addPathSegment("classes").addPathSegment("Usuario")
                .addQueryParameter("where", "{\"nombre\":\"" + nombre + "\"}")
                .build();
            
            Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .get().build();
            
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
             
            if(response.isSuccessful() && response.body() != null) {
                JsonObject jsonObject = new Gson().fromJson(response.body().string(), JsonObject.class);
                JsonArray jsonArray = jsonObject.getAsJsonArray("results");
                
                // Si el array tiene algo, es que hemos encontrado al usuario
                if (jsonArray.size() > 0) {
                    usuarioEncontrado = new Gson().fromJson(jsonArray.get(0), Usuario.class);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return usuarioEncontrado;
    }

    @Override
    public String insertarUsuario(Usuario usuario) {
        String newObjectId = null;
        Gson gson = new Gson();
        String jsonUsuario = gson.toJson(usuario);
        
        try {
            Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .post(RequestBody.create(MediaType.parse("application/json"), jsonUsuario))
                .build();
            
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            
            if(response.isSuccessful() && response.body() != null) {
                // Parseamos la respuesta para obtener el objectId que nos da Back4App
                Usuario newUsuarioRespuesta = gson.fromJson(response.body().string(), Usuario.class);
                newObjectId = newUsuarioRespuesta.getId(); 
            }
        } catch (IOException e) {
           System.out.println("Error insertando usuario: " + e.getMessage());
        }
        return newObjectId;
    }
}