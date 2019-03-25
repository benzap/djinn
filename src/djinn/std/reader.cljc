(ns djinn.std.reader
  (:require
   [clojure.tools.reader.edn :as edn]
   [clojure.string :as str]))


(defn read-string
  [s]
  (-> (str "\n[\n" s "\n]\n")
      (str/replace #"`" "djinn.core/unquote")
      edn/read-string))
      



;;(read-string "`(do a b)")
