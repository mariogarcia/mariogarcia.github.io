(defproject qc-clojure "0.1.0-SNAPSHOT"
  :description "Property based testing"
  :url "http://januslynd.com"
  :license {:name "Apache 2.0 License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  ;; tag::dependencies[]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/test.check "0.9.0"]]
  ;; end::dependencies[]
  :main ^:skip-aot qc.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
