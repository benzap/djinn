(ns djinn.stdlib.core
  (:require
   [djinn.std.state-machine :as state]))


(defn import-stdlib-core
  [sm]
  (-> sm
      (state/set-global-var '+ +)
      (state/set-global-var 'conj conj)
      (state/set-global-var 'println println)))
      
