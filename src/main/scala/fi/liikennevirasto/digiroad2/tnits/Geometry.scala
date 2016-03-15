package fi.liikennevirasto.digiroad2.tnits

case class Vector3d(x: Double, y: Double, z: Double) {
  def dot(that: Vector3d): Double = {
    (x * that.x) + (y * that.y) + (z * that.z)
  }

  def normalize(): Vector3d = {
    scale(1 / length())
  }

  def scale(scalar: Double): Vector3d = {
    Vector3d(x * scalar, y * scalar, z * scalar)
  }

  def length(): Double = {
    Math.sqrt((x * x) + (y * y) + (z * z))
  }

  def -(that: Vector3d): Vector3d = {
    Vector3d(x - that.x, y - that.y, z - that.z)
  }
}

case class Point(x: Double, y: Double, z: Double = 0.0) {
  def distanceTo(point: Point): Double =
    Math.sqrt(Math.pow(point.x - x, 2) + Math.pow(point.y - y, 2) + Math.pow(point.z - z, 2))

  def -(that: Point): Vector3d = {
    Vector3d(x - that.x, y - that.y, z - that.z)
  }

  def -(that: Vector3d): Point = {
    Point(x - that.x, y - that.y, z - that.z)
  }

  def +(that: Vector3d): Point = {
    Point(x + that.x, y + that.y, z + that.z)
  }
}
