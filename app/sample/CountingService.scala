package sample

import javax.inject.Named
/**
 * A simple service that can increment a number.
 */
@Named("CountingService")
class CountingService {
  /**
   * Increment the given number by one.
   */
  def increment( count : Int)  = count + 1
}