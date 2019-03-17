(ns djinn.std.evaluate.impl.core
  "Implementation of Evaluate protocol for clojure's base types"
  (:require
   [djinn.std.evaluate.protocol]))


(extend-protocol djinn.std.evaluate.protocol/Evaluate

  clojure.lang.Keyword
  (evaluate [this] this)

  clojure.lang.Ratio
  (evaluate [this] this)

  ;;
  ;; Base Java Data Types
  ;;

  java.lang.String
  (evaluate [this] this)

  java.lang.Long
  (evaluate [this] this)

  java.lang.Double
  (evaluate [this] this)

  java.lang.Boolean
  (evaluate [this] this)

  java.util.regex.Pattern
  (evaluate [this] this)

  ;; Use Default for everything else
  java.lang.Object
  (evaluate [this]
    (println (str "Warning: Unknown Evaluated Object [" (type this) "]"))
    this))
  
  
