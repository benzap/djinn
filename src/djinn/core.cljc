(ns djinn.core
  (:refer-clojure :exclude [eval])
  (:require
   [djinn.std.state-machine :as std.state-machine]
   [djinn.std.ast :as std.ast]
   [djinn.std.evaluator :as std.evaluator]))


(def ^:dynamic *default-state-machine*
  (std.state-machine/new-state-machine))


(defn eval-fn
  [form-listing]
  (let [ast (std.ast/parse *default-state-machine* form-listing)]
    (std.evaluator/evaluate ast)))


;; (eval-fn '[2 [1 2 3] {:a 123}])


(defmacro eval
  [& forms]
  `(eval-fn (quote ~forms)))


;; (eval (+ 2 2))
