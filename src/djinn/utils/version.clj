(ns djinn.utils.version)


(defn get-project-version []
  (System/getProperty "djinn.version"))


(defn print-project-version [])
(println (get-project-version))
