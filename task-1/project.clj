(defproject task-1 "0.1.0-SNAPSHOT"
  :description "TODO"
  :url "TODO"
  :license {:name "TODO: Choose a license"
            :url "http://choosealicense.com/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.2"]
                 ;; https://mvnrepository.com/artifact/commons-io/commons-io
                 [commons-io/commons-io "2.6"]
                 [com.bhauman/rebel-readline "0.1.4"]
                 ]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [com.stuartsierra/component.repl "0.2.0"]]
                   :source-paths ["dev"]}}
  :aliases {"rebl" ["trampoline" "run" "-m" "rebel-readline.main"]}
  )
