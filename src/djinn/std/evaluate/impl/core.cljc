(ns djinn.std.evaluate.impl.core
  "Implementation of Evaluate protocol for clojure's base types"
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]
   [djinn.std.evaluator :refer [evaluate-arguments]]
   [djinn.std.state-machine :as state]
   [djinn.std.scope :as scope]
   [djinn.std.function :as std.function]))


(extend-protocol djinn.std.evaluate.protocol/Evaluate

  ;;
  ;; Base Clojure Data Collections
  ;;

  clojure.lang.PersistentList
  (evaluate [this sm]
    (let [[fsym & arguments] this
          fvar (state/get-var sm fsym)]
      (cond
        (scope/undefined? fvar)
        (throw (ex-info "First argument of s-exp is not defined" {:value fsym}))
        (std.function/invokable? fvar)
        (std.function/invoke fvar sm arguments)
        (or (ifn? fvar) (fn? fvar))
        (let [[sm arguments] (evaluate-arguments sm arguments)]
          [sm (apply fvar arguments)])
        :else
        (throw (ex-info "First argument of s-exp is not invokable." {:value fsym})))))

  clojure.lang.PersistentArrayMap
  (evaluate [this sm]
    (let [*sm (atom sm)] ;; TODO: switch to loop
      (->> this
           (map (fn [[k v]] 
                  (let [[sm k] (evaluate k @*sm)
                        [sm v] (evaluate v sm)]
                    (reset! *sm sm)
                    [k v])))
           (into {})
           (conj [@*sm]))))

  clojure.lang.PersistentVector
  (evaluate [this sm]
    (let [*sm (atom sm) ;; TODO: switch to loop
          this
          (mapv (fn [elem]
                  (let [[sm value] (evaluate elem @*sm)]
                    (reset! *sm sm)
                    value)) this)]
      [@*sm this]))

  clojure.lang.PersistentHashSet
  (evaluate [this sm]
    (let [*sm (atom sm)
          this
          (map (fn [elem]
                 (let [[sm value] (evaluate elem @*sm)]
                   (reset! *sm sm)
                   value)) this)]
      [@*sm (set this)]))

  ;;
  ;; Base Clojure Data Types
  ;;

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
  
  
