package app;

import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import entities.Utilisateur;
import services.UserService;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private UserService userService = new UserService();
    private static String groupeSarah = "http://93c43331.ngrok.io/register";
    private static String groupeEdouard = "http://08004682.ngrok.io/user/create";

    @POST
    public Response createUser(Utilisateur user) throws MalformedURLException, IOException{
        URL grpeSarah = new URL(groupeSarah);
        //URL grpeEdouard = new URL(groupeEdouard);
        HttpURLConnection connection = (HttpURLConnection) grpeSarah.openConnection();
        //HttpURLConnection connection = (HttpURLConnection) grpeEdouard.openConnection();
        //Cryptage du mot ed passe
        //String mdp="";
        //user.setPassword(mdp);
        System.out.println("---Response de auth--");
        System.out.println(userService.createUser(user, connection));
        return Response.status(201).build();
    }

    @POST
    @Path("/authenticate")
    public Response authUser(Utilisateur user){
        HttpURLConnection connection = null;
        //Cryptage du mot ed passe
        String mdp="";
        user.setPassword(mdp);
        userService.authUser(user.getEmail(), user.getPassword(), connection);
        return Response.status(201).entity("ça marche").build();
    }
}
