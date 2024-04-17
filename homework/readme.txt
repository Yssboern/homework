Given the interface Accumulator, create a Java implementation and a
reasonable amount of tests (i.e. JUnit, TestNG, Spock or Cucumber; choose freely) to prove it
correct. The implemetation should follow the guidelines described in the Javadoc of the interface.

An Accumulator maintains a running total sum of one or more variables. Updating of this total
value is done by accumulating more variables. Reading the total value is done by calling the
getTotal method. The accumulate method returns the sum of its arguments (which is added to the
total running value).