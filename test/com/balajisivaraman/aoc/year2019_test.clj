(ns com.balajisivaraman.aoc.year2019-test
  (:require [clojure.test :refer :all]
            [com.balajisivaraman.aoc.year2019 :refer :all]))

(deftest day01a-test
  (testing "sum of module fuel requirements"
    (is (= 34241.0 (day01a [12 14 1969 100756])))))
