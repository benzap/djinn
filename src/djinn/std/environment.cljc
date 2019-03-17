(ns djinn.std.environment)


(defrecord Environment [vars])


(defn new-environment []
  (map->Environment {:vars {}}))
