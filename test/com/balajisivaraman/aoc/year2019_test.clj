(ns com.balajisivaraman.aoc.year2019-test
  (:require [clojure.test :refer :all]
            [com.balajisivaraman.aoc.year2019 :refer :all]))

(deftest day01a-test
  (testing "sum of module fuel requirements"
    (is (= 34241.0 (day01a [12 14 1969 100756])))))

(deftest day01b-test
  (testing "sum of module fuel requirements with additional fuel"
    (is (= 51314.0 (day01b [14 1969 100756])))))

(deftest day02a-test
  (testing "sum of module fuel requirements with additional fuel"
    (is (= [30,1,1,4,2,5,6,0,99] (day02a [1,1,1,4,99,5,6,0,99])))))

(deftest day03a-test
  (testing "manhattan distance of the closest point of wire crossing"
    (is (= 159.0 (day03a ["R75","D30","R83","U83","L12","D49","R71","U7","L72"] ["U62","R66","U55","R34","D71","R55","D58","R83"])))))
