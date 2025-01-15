(defproject web "0.1.0-SNAPSHOT"
  :description "Clojure basic web server with routing"
  :url "https://your-url.com"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [http-kit "2.4.0"]
                 [compojure "1.6.2"]
                 [org.clojure/tools.logging "1.2.4"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler web.core/handler}
  :repl-options {:init-ns web.core})
