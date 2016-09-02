(ns houstan.vagrant
  (:require [me.raynes.conch :as sh]))

;; ----------------------------------------

;; this baby imports shell applications into the current namespace
;; window-dressing them to look like functions in clojure
(sh/programs vagrant)

