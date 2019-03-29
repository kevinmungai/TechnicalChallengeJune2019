(defproject task-2a "0.1.0-SNAPSHOT"
  :description "TODO"
  :url "TODO"
  :license {:name "TODO: Choose a license"
            :url "http://choosealicense.com/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [http-kit "2.3.0"]
                 [clj-http "3.9.1"]
                 [aleph "0.4.6"]
                 [com.africastalking/core "3.4.0"]
                 [cheshire "5.8.1"]
                 [org.clojure/clojure "1.10.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [io.pedestal/pedestal.service       "0.5.5"]
                 [io.pedestal/pedestal.jetty         "0.5.5"]
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.25"]
                 [org.slf4j/jcl-over-slf4j "1.7.25"]
                 [org.slf4j/log4j-over-slf4j "1.7.25"]
                 [io.pedestal/pedestal.interceptor "0.5.5"]
                 [io.pedestal/pedestal.log "0.5.5"]
                 [io.pedestal/pedestal.route "0.5.5"]
                 [com.grzm/component.pedestal "0.1.7"]
                 [environ "1.1.0"]
                 [com.bhauman/rebel-readline "0.1.4"]
                 ;; https://mvnrepository.com/artifact/commons-validator/commons-validator
                 [commons-validator/commons-validator "1.4.0"]

                 ]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [com.stuartsierra/component.repl "0.2.0"]]
                   :source-paths ["dev"]}
             :uberjar {:aot task-2}}
  :repositories [["jcenter" "https://dl.bintray.com/africastalking/java"]]
  :aliases {"rebl" ["trampoline" "run" "-m" "rebel-readline.main"]}
  :min-lein-version "2.0.0"
  :main ^{:skip-aot true} task-2a
  :repl-options {:init-ns user}
  :resource-paths ["config", "resources"]
  )
