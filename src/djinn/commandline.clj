(ns djinn.commandline
  (:require
   [djinn.core :as djinn]))


(defn -main [& args]
  (djinn/eval (println "Hello World!")))
