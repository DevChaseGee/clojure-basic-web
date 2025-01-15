(ns web.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer [routes POST GET ANY]]
            [clojure.tools.logging :as log]))

(def catsHTML "
<!DOCTYPE html>
<html lang=\"en\">
<head>
    <meta charset=\"UTF-8\">
    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">
    <title>Full-Screen Cat Image</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
        }

        .full-screen-image {
            background-image: url('https://cdn.britannica.com/34/235834-050-C5843610/two-different-breeds-of-cats-side-by-side-outdoors-in-the-garden.jpg');
            background-size: cover;
            background-position: center;
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <div class=\"full-screen-image\"></div>
</body>
</html>
")

(def homeHTML "
<!DOCTYPE html>
<html lang=\"en\">
<head>
    <meta charset=\"UTF-8\">
    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">
    <title>Full-Screen Cat Image</title>
</head>
<body>
    <div>
        <h1>Welcome to the home page! Thanks for your visit!</h1>
        <a href=\"/cats\">Click here to see a full-screen cat image</a>
    </div>
</body>
</html>
")

(defn handler [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body homeHTML})

(defn cats-handler [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body catsHTML})

(def app
  (routes
   (GET "/" [] handler)
   (GET "/cats" [] cats-handler)))

(def server-instance (atom nil))

(defn start-server []
  (let [port 8000]
    (log/info (str "Starting server on port " port))
    (reset! server-instance (server/run-server app {:port port}))
    (log/info "Server started")))

(defn stop-server []
  (when @server-instance
    (log/info "Stopping server")
    (@server-instance :timeout 100)
    (reset! server-instance nil)
    (log/info "Server stopped")))

(defn restart-server []
  (stop-server)
  (start-server))