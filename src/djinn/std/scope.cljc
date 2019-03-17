(ns djinn.std.scope)


(defrecord Scope [listing])


(defn new-scope []
  (->Scope [{}]))


(defn create-scope [scope]
  (update-in scope [:listing] conj {}))


(defn remove-scope [scope]
  (update-in scope [:listing] pop))


(defn set-global-var [scope name value]
  (update-in scope [:listing 0] assoc name value))


(defn set-var [scope name value]
  (let [last-index (-> scope :listing count dec)]
    (update-in scope [:listing last-index] assoc name value)))
  

(defn get-var [scope name]
  (get (reduce merge (:listing scope)) name))


#_(-> (new-scope)
      (set-var 'a 123)
      (create-scope)
      (set-var 'a 456)
      (remove-scope)
      (get-var 'a))
