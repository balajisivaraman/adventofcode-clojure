(defproject advent-of-code "0.1.0-SNAPSHOT"
  :description "Advent of Code solutions in rust"
  :url "http://www.github.com/balajisivaraman/adventofcode-clojure"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.match "0.3.0"]]
  :main ^:skip-aot com.balajisivaraman.aoc.main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
