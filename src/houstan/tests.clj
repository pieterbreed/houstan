(ns houstan.tests
  (:require [clojure.test :refer :all]
            [clojure.test.tap :refer :all]
            [clojure.string :as str]
            [environ.core :refer [env]]
            [houstan.vagrant :refer [vagrant]]
            [houstan.ansible :refer [ansible]]
            [houstan.git :refer [git]]))

;; I use this to verify if the tooling behaves like expected
(deftest this-time-its-serious
  (is true))
;;(is false))


;; ----------------------------------------

(deftest vagrant_1_8-is-installed
  (is (re-find #"Vagrant 1\.8\.."
               (vagrant "-v"))))

(deftest ansible_2_1-is-installed
  (is (re-find #"ansible 2\.1\..\.."
               (ansible "--version"))))

(deftest git_2-is-installed
  (is (re-find #"git version 2\."
               (git "version"))))

(deftest houstan-env-var-points-to-folder
  (let [H (env :houstan)]
    (is H "The houstan env variable needs to be set")
    (when H
      (let [f (clojure.java.io/file H)]
        (is (.exists f)
            "The houstan environment variable must point to a file that exists")))))



