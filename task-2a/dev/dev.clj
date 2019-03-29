(ns dev
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application.

  Call `(reset)` to reload modified code and (re)start the system.

  The system under development is `system`, referred from
  `com.stuartsierra.component.repl/system`.

  See also https://github.com/stuartsierra/component.repl"
  (:require
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer [javadoc]]
   [clojure.pprint :refer [pprint]]
   [clojure.reflect :refer [reflect]]
   [clojure.repl :refer [apropos dir doc find-doc pst source]]
   [clojure.set :as set]
   [clojure.string :as string]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer [refresh refresh-all clear]]
   [com.stuartsierra.component :as component]
   [com.stuartsierra.component.repl :refer [reset set-init start stop system]]
   [task-2a :as task]
   [io.pedestal.http :as http]
   [com.grzm.component.pedestal :as cp]))

;; Do not try to load source code from 'resources' directory
(clojure.tools.namespace.repl/set-refresh-dirs "dev" "src" "test")

(defn dev-pedestal
  []
  (println "STARTING YOUR [DEVELOPMENT] SERVER ...")
  (-> task/service
      (merge {:env :dev
              ::http/join? false})
      http/default-interceptors
      http/dev-interceptors))

(defn dev-system
  "Constructs a system map suitable for interactive development."
  []
  (component/system-map
   :api-key task/at-api-key
   :database (atom {})
   :pedestal (component/using (cp/pedestal dev-pedestal)
                              [:database])
   ))


;; (defn dev-system
;;   "Constructs a system map suitable for interactive development."
;;   []
;;   (component/system-map
;;    ;; TODO
;;    ))

(set-init (fn [_] (dev-system)))
