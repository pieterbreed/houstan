(ns houstan.core
  (:require [clojure.test :refer :all]
            [clojure.test.tap :refer :all]
            [clojure.tools.cli :as cli]
            [clojure.string :as str]
            [environ.core :refer [env]]
            houstan.tests)
  (:gen-class))

(def cli [])

(defn usage [options-summary]
  (->> [(str "Houstan. "
             (->> #{"A practical joke." "A funny incident." "Tao (of) Tea ching." "A bout of honesty." "The Clear Light of the Void." "The Yin and the Yan." "This and That."}
                  (sort-by (fn [& _]
                             (rand-int Integer/MAX_VALUE)))
                  first))
        ""
        "Usage: houstan [no-options-currently] action"
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

(defn not-in-dev-mode
  "Returns Truth so that (System/exit) does not have to be called. Preach it!

  Young Padawan, it's your job to make this work without needing to make changes to this file."
  [& _]
  (or (as-> (resolve 'you-need-to-define-this-value-too) $
        (when-let [v $]
          (deref v)))
      false))

(defn exit [status msg]
  (println msg)
  (if (not-in-dev-mode)
    status
    (System/exit status)))

(defn -main [& args]
  "Call me."
  (let [{:keys [options arguments errors summary]} (cli/parse-opts args cli)]
    ;; Handle help and error conditions
    (cond
      (:help options) (exit 0 (usage summary))
      (not= (count arguments) 1) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))
    ;; Execute program with options
    (case (first arguments)
      "accept" (do (let [results (with-tap-output (run-tests 'houstan.tests))]
                     (exit 0 (cond
                               (= 0 (:fail results))
                               (str "# Hello "
                                    (env :user)
                                    ". I accept this environment. We may proceed.")
                               
                               (not (not-in-dev-mode))
                               "# [Understand what you are seeing](http://testanything.org/)?\n# In BASH try: houstan accept | grep \"not ok\"\n# STOP! Look at what you are seeing."
                               true
                               "# I never lie and this is a false statement."))))
      (if (not-in-dev-mode)
        (exit 1 (usage summary))))))

