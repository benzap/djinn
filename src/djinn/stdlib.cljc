(ns djinn.stdlib
  (:require
   [djinn.stdlib.def :refer [import-stdlib-def]]
   [djinn.stdlib.fn :refer [import-stdlib-fn]]
   [djinn.stdlib.core :refer [import-stdlib-core]]))


(defn import-stdlib [sm]
  (-> sm
      import-stdlib-def
      import-stdlib-fn
      import-stdlib-core))
