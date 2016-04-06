(ns 
  ^{:doc "Cortex core library"
    :author "Eemil Väisänen"}
  cortex.core
  (:require [cortex.activation :as act])
  (:gen-class))

(defrecord Neuron [weights])
(defrecord Layer [neurons act])
(defrecord Net [layers])

(defn listmult
  [s1 s2]
  (map * s1 s2))

(defn apply-weights
  [^Neuron n inputs]
  (listmult inputs (:weights n)))

(defn activate-neuron
 [^Neuron n inputs]
 (reduce + (apply-weights n inputs)))

(defn activate-layer
  "Activates a layer of neurons."
  [^Layer layer inputs]
  (println "Layer got" inputs)
  (def act  (:act layer))
  (act (map #(activate-neuron % inputs) layer)))

(defn activate-net
  [^Net net inputs]
  (def layers  (:layers net))
  (map #(activate-layer % inputs) layers))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Namespace:" (:doc (meta (find-ns 'cortex.activation))))
  (println "Author:" (:author (meta (find-ns 'cortex.activation))))

  (def z '(0.5 0 -3))
  (def n (Neuron. '(0.1 0.2 0.3)))

  (def il [
    (Neuron. '(1 2 3))
    (Neuron. '(2 3 4))
    ])

  (def oz '(0.1 -0.8))
  (def ol [
    (Neuron. '(5 5))
    ])

  (println "Neuron response" (activate (activate il z act/sigmoid) oz act/binary-threshold))
  (println " - inputs " z)
  (println " - weights" (:weights n))

  (println "Activation for" z)
  (println " - linear    " (act/linear z))
  (println " - binary    " (act/binary-threshold z))
  (println " - bipolar   " (act/bipolar-threshold z))
  (println " - sigmoid   " (act/sigmoid z))
  (println " - hyberbolic" (act/hyberbolic-tangent z)))
