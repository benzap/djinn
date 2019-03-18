(ns djinn.stdlib
  (:require
   [djinn.stdlib.def :refer [import-stdlib-def]]))


(defn import-stdlib [sm]
  (-> sm
      (import-stdlib-def)))
