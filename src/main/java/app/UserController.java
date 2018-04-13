package app;

import entities.Utilisateur;
import security.CryptageAes;
import services.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private UserService userService = new UserService();
    private static String groupeSarah = "http://93c43331.ngrok.io/";


    @POST
    public Response createUser(Utilisateur user) throws MalformedURLException, IOException{
        URL grpeSarah = new URL(groupeSarah);
        HttpURLConnection connection = (HttpURLConnection) grpeSarah.openConnection();
        //Cryptage du mot de passe

        String mdp="";
        CryptageAes cAes = new CryptageAes();
        mdp = cAes.encrypt(mdp);
        user.setPassword(mdp);
        userService.createUser(user, connection);
        return Response.status(201).entity("ça marche").build();
    }

    @POST
    @Path("/authenticate")
    public Response authUser(Utilisateur user){
        HttpURLConnection connection = null;
        //Cryptage du mot de passe
        String mdp="";
        CryptageAes cAes = new CryptageAes();
        mdp = cAes.encrypt(mdp);
        user.setPassword(mdp);
        userService.authUser(user.getEmail(), user.getPassword(), connection);
        return Response.status(201).entity("ça marche").build();
    }
}
