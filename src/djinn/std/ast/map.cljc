(ns djinn.std.ast.map
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord MapExpression [map-entries]
  Evaluate
  (evaluate [_ sm]
    (let [*sm (atom sm)]
      (->> map-entries
           (map (fn [[k v]] 
                  (let [[sm k] (evaluate k @*sm)
                        [sm v] (evaluate v sm)]
                    (reset! *sm sm)
                    [k v])))
           (into {})
           (conj [@*sm])))))
