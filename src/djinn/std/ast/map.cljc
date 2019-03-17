(ns djinn.std.ast.map
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord MapExpression [*sm map-entries]
  Evaluate
  (evaluate [_]
    (println map-entries)
    (into {} (map (fn [[k v]] [(evaluate k) (evaluate v)]) map-entries))))
