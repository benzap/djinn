(ns djinn.commandline
  (:require
   [djinn.core :as djinn]))


(defn -main [& args]
  (djinn/eval (def x "Hello World!") (println x)))
