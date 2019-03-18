(ns djinn.stdlib.def
  (:require
   [djinn.std.state-machine :as state]
   [djinn.std.evaluator :refer [evaluate]]
   [djinn.std.function]))
  

(defrecord Def []
  djinn.std.function/Invokable
  (invoke [_ sm decl]
    (let [var-name (first decl)
          _ (assert (symbol? var-name) "First argument to def must be a Symbol")
          decl (next decl)
          [sm var-value] (evaluate sm decl)]
      [(state/set-global-var sm var-name var-value) var-value])))
    



(defn import-stdlib-def [sm]
  (state/set-global-var sm 'def (->Def)))
