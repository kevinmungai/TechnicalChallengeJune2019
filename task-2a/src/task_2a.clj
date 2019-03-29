(ns task-2a
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.log :as log]
            [ring.util.response :as ring-resp]
            [com.stuartsierra.component :as component]
            [com.grzm.component.pedestal :as cp]
            ;;            [environ.core :refer [env]]
            [cheshire.core :as cheshire]
            ))



(def at-api-key "f7955e837f017c70f1fc3032343161a1ce2dd3bab3e339487e92b05898dd4e1e")

(def store (atom []))
(def foldr (atom {}))
(def calls (atom 0))

(defn hello [request]
  (ring-resp/response "Hello, World!!!"))

(defn ussd
  [{:keys [params] :as request}]
  (let [db (cp/use-component request :database)
        session-id (get params "sessionId")
        present? (filterv #(= session-id (get % "sessionId")) @store)
        session-exists? (get-in @foldr [session-id])
        text (get params "text")
        ]

    ;;    (println @foldr)

    (if (< @calls 2)
      (if session-exists?
        (do (swap! foldr assoc-in [session-id "data"] text)
            (swap! calls inc)
            (println @calls)
            (println @foldr)
            (ring-resp/response (str "CON PLEASE PUT IN THE EMAIL ADDRESS")))
        (do (swap! foldr assoc-in [session-id] {})
            (swap! calls inc)
            (println @calls)
            (println @foldr)
            (ring-resp/response (str "CON PLEASE PUT IN THE USERNAME"))))
      (ring-resp/response (str "END YOU SHALL RECIEVE AN SMS SHORTLY")))


    ;; (log/info :msg (str "Params are " session-id))
    ;; (println params)

    ;; (ring-resp/response (str "CON HOPE YOU ARE OKAY?\nAM OKAY TOO!!!"))
    
    ))

(def common-interceptors
  [(body-params/body-params) (cp/using-component :database)])

(def routes
  #{["/ussd" :post (conj common-interceptors `ussd) :route-name :ussd-route]
    ["/" :get `hello :route-name :hello-world]})

(def service
  {:env :prod
   ::http/routes routes
   ::http/type :jetty
   ::http/port 8080
   ;;   ::http/host "https://protected-tundra-41196.herokuapp.com"
   ::http/container-options {:h2c? true
                             :h2? false
                             :ssl? false}})

(defn production-pedestal
  []
  (println "CREATING PEDESTAL [PRODUCTION] SERVER ...")
  (http/default-interceptors service))

(defn production-system
  []
  (component/system-map
   :api-key at-api-key
   :database (atom [])
   :pedestal (component/using (cp/pedestal production-pedestal)
                              [:database])))

(defn -main
  [& args]
  (println "CREATING THE [PRODUCTION] SYSTEM")
  (component/start (production-system)))
