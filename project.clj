(defproject houstan "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha10"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.clojure/core.async "0.2.385"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/test.check "0.9.0"]
                 [clj-time "0.12.0"]
                 [environ "1.0.3"]
                 [me.raynes/conch "0.8.0"]]
  :main ^:skip-aot houstan.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
