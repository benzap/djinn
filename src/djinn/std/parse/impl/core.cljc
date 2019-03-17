(ns djinn.std.parse.protocol.impl.core
  "Implementation of Parser protocol for clojure's core data types."
  (:require
   [djinn.std.parse.protocol]
   [djinn.std.s-expression :as s-expression]))


(extend-protocol djinn.std.parse.protocol/Parse
  clojure.lang.PersistentList
  (parse [this *sm]
    (s-expression/->SExpression *sm (map #(djinn.std.parse.protocol/parse % *sm) this)))

  java.lang.Long
  (parse [this *sm] this))
