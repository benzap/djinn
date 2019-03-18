(ns djinn.core
  (:refer-clojure :exclude [eval])
  (:require
   [djinn.std.state-machine :as std.state-machine]
   [djinn.std.evaluator :as std.evaluator]
   [djinn.stdlib :refer [import-stdlib]]))


(def ^:dynamic *default-state-machine*
  (-> (std.state-machine/new-state-machine)
      (import-stdlib)))


(defn eval-fn
  [form-listing]
  (second (std.evaluator/evaluate *default-state-machine* form-listing)))


;; (eval-fn '[2 [1 2 3] {:a 123}])


(defmacro eval
  [& forms]
  `(eval-fn (quote ~forms)))


;; (eval 1 2 3)
;; (eval (def x 10) (def y 11) x {:a x})
