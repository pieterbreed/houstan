(ns houstan.core
  (:require [clojure.test :refer :all]
            [clojure.test.tap :refer :all]
            [clojure.tools.cli :as cli]
            [clojure.string :as str]
            houstan.tests)
  (:gen-class))

(def cli [])

(defn usage [options-summary]
  (->> ["Houstan. If you don't know what it does, I'm not going to tell you either."
        ""
        "Usage: houstan [options] action"
        ""
        "Options:"
        options-summary
        ""
        "Actions:"
        " accept        Checks/verify the environment"
        ""]
       (str/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (str/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (cli/parse-opts args cli)]
    ;; Handle help and error conditions
    (cond
      (:help options) (exit 0 (usage summary))
      (not= (count arguments) 1) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))
    ;; Execute program with options
    (case (first arguments)
      "accept" (do (with-tap-output
                     (run-tests 'houstan.tests))
                   (exit 0 "# done"))
      (exit 1 (usage summary)))
    ))

