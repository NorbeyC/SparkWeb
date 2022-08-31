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

            get("/intraday","application/json", (req, res) -> {
                String data = req.queryParams("id");
                String typ = req.queryParams("type");
                res.type("application/json");
                return HttpConnection.getHistory(data, typ);
            });
        }

        static int getPort() {
            if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080; //returns default port if heroku-port isn't set(i.e. on localhost)
        }
}