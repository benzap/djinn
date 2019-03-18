(ns djinn.std.evaluate.impl.core
  "Implementation of Evaluate protocol for clojure's base types"
  (:require
   [djinn.std.evaluate.protocol]
   [djinn.std.state-machine :as state]
   [djinn.std.scope :as scope]))


(extend-protocol djinn.std.evaluate.protocol/Evaluate

  clojure.lang.Keyword
  (evaluate [this sm] [sm this])

  clojure.lang.Ratio
  (evaluate [this sm] [sm this])

  clojure.lang.Symbol
  (evaluate [this sm]
    (let [val (state/get-var sm this)]
      (if-not (scope/undefined? val)
        [sm val]
        (throw (ex-info (str "Unable to resolve symbol: " this " in this context") {:symbol this})))))

  ;;
  ;; Base Java Data Types
  ;;

  java.lang.String
  (evaluate [this sm] [sm this])

  java.lang.Long
  (evaluate [this sm] [sm this])

  java.lang.Double
  (evaluate [this sm] [sm this])

  java.lang.Boolean
  (evaluate [this sm] [sm this])

  java.util.regex.Pattern
  (evaluate [this sm] [sm this])

  ;; Use Default for everything else
  java.lang.Object
  (evaluate [this sm]
    (println (str "Warning: Unknown Evaluated Object [" (type this) "]"))
    [sm this]))
  
  
