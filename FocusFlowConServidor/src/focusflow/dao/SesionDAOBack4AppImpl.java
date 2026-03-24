/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focusflow.dao;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import focusflow.models.SesionEstudio;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response; 

public class SesionDAOBack4AppImpl implements ISesionDAO{
    private final String API_URL = "https://parseapi.back4app.com/classes/SesionEstudio";
    private final String APPLICATION_ID = "h6lniNE1cXU2UnDw92VjAtfmBUYfdsMGdxvMqpmE";
    private final String REST_API_KEY = "OkQszHeQxF9sj6iuAb3s1q8falGTBjQIp2biKdAi";

    @Override
    public void guardarSesion(SesionEstudio sesion){
        
        // 2. Regla de exclusión: Le decimos a Gson que NO envíe nuestro campo "id" a la nube, 
        // porque Back4App generará su propio "objectId" automáticamente.
        ExclusionStrategy skipIdStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                // Si en tu clase SesionEstudio la variable se llama "id", ponemos "id". 
                // Si se llama "objectId", ponemos "objectId".
                return fa.getName().equals("id"); 
            }
        };
        // 3. Convertimos nuestro objeto Java a texto JSON aplicando la regla
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(skipIdStrategy)
                .create();
        
        String jsonBody = gson.toJson(sesion);

        // 4. Preparamos el cliente HTTP y la petición POST
        try {
            OkHttpClient client = new OkHttpClient();
            
            RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
            
            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            
            if (response.isSuccessful()) {
                System.out.println("[NUBE] -> Sesión guardada correctamente en Back4App.");
            } else {
                System.out.println("[ERROR] -> Fallo al guardar en la nube: " + response.code());
            }

        } catch (IOException e) {
            System.out.println("[ERROR DE RED] -> " + e.getMessage());
        }
    }

    @Override
    public List<SesionEstudio> listarSesiones(String usuarioId){
        List<SesionEstudio> historial = new java.util.ArrayList<>();
        
        try {
            // CONSTRUIMOS LA URL CON EL FILTRO WHERE
            okhttp3.HttpUrl httpUrl = new okhttp3.HttpUrl.Builder()
                .scheme("https").host("parseapi.back4app.com")
                .addPathSegment("classes").addPathSegment("SesionEstudio")
                .addQueryParameter("where", "{\"usuarioId\":\"" + usuarioId + "\"}")
                .build();
            
            OkHttpClient client = new OkHttpClient();
   
            // Hacemos la petición GET (descargar)
            Request request = new Request.Builder()
                    .url(httpUrl)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            
            if (response.isSuccessful() && response.body() != null) {
                String responseJson = response.body().string();
                
                // 1. Parseamos la respuesta a un objeto JSON genérico
                com.google.gson.JsonObject jsonObject = new Gson().fromJson(responseJson, com.google.gson.JsonObject.class);
                
                // 2. Extraemos solo el array llamado "results" (que es donde Back4App mete los datos)
                com.google.gson.JsonArray jsonArray = jsonObject.getAsJsonArray("results");
                
                // 3. Convertimos ese array JSON en nuestra lista de objetos Java
                java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<java.util.ArrayList<SesionEstudio>>() {}.getType();
                historial = new Gson().fromJson(jsonArray, listType);
                
            } else {
                System.out.println("[ERROR] -> No se pudo descargar el historial: " + response.code());
            }

        } catch (IOException e) {
            System.out.println("[ERROR DE RED] -> " + e.getMessage());
        }
        
        return historial;
    }
    
}
