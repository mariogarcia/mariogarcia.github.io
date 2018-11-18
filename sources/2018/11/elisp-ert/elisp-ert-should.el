;;; package --- Examples on should/should-not/should-error
;;; Code:
;;; Commentary:

(require 'ert)
(require 'ert)

;;; tag::asserts-should[]
(ert-deftest test-sum-is-commutative ()
  (should (= (+ 1 2) (+ 2 1))))
;;; end::asserts-should[]

;;; tag::asserts-should-not[]
(ert-deftest test-division-not-commutative ()
  (should-not (= (/ 1 2) (/ 2 1))))
;;; end::asserts-should-not[]

;;; tag::asserts-should-error[]
(ert-deftest test-error ()
  (should-error
   (signal 'singularity-error nil)
   :type 'singularity-error))
;;; end::asserts-should-error[]

(provide 'elisp-ert-should)

;;; elisp-ert-should.el ends here
