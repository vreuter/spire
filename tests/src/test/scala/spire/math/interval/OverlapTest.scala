package spire.math.interval

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{Matchers, PropSpec}
import spire.laws.arb._
import spire.math.{Interval, Rational}
import spire.math.interval.Overlap._
import spire.syntax.eq._

class OverlapTest extends PropSpec with Matchers with GeneratorDrivenPropertyChecks {

  property("Overlap equality") {
    forAll { (x: Interval[Rational], y: Interval[Rational]) =>
      whenever(x =!= y) {
        import spire.algebra.Eq

        val eq = Eq[Overlap[Rational]]

        eq.eqv(Equal(), Equal()) shouldBe true

        eq.eqv(Disjoint(x, y), Disjoint(x, y)) shouldBe true
        eq.eqv(Disjoint(x, y), Disjoint(y, x)) shouldBe false

        eq.eqv(PartialOverlap(x, y), PartialOverlap(x, y)) shouldBe true
        eq.eqv(PartialOverlap(x, y), PartialOverlap(y, x)) shouldBe false

        eq.eqv(Subset(x, y), Subset(x, y)) shouldBe true
        eq.eqv(Subset(x, y), Subset(y, x)) shouldBe false
      }
    }
  }
}
