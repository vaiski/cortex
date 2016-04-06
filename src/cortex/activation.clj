(ns 
  ^{:doc "Cortex activation functions"
    :author "Eemil Väisänen"}
  cortex.activation
  (:require [clojure.math.numeric-tower :as math]))

(def e Math/E)

(defn linear
  "Linear activation function."
  [z]
  z)

; Step functions

(defn binary-threshold
  "Binary threshold activation function."
  [z]
  (map #(if (>= % 0) 1 0) z))

(defn bipolar-threshold
  "Bipolar threshold activation function."
  [z]
  (map #(if (>= % 0) 1 -1) z))

; Continuous functions

(defn sigmoid
  "Sigmoid activation function."
  [z]
  (map #(/ 1.0 (+ 1.0 (math/expt e (- 0 %)))) z))

(defn hyberbolic-tangent
  "Hyberbolic tangent activation function."
  [z]
  (map #(Math/tanh %) z))
