package edu.escuelaing.arep;

import static spark.Spark.*;
import java.net.URL;

public class SparkWeb {
    
        /**
         * @param args
         */
        public static void main(String[] args) {
            staticFiles.location("/webapp");
            port(getPort());
            get("/hello", (req, res) -> {
                String name = req.queryParams("name"); 
                URL url = new URL(name);
                return "Hello "+ name;
                });

            get("/intraday", (req, res) -> {
                String data = req.queryParams("name");
                res.type("/application.json");
                return HttpConnection.getIntraDay();
            });
        }

        static int getPort() {
            if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080; //returns default port if heroku-port isn't set(i.e. on localhost)
        }
}