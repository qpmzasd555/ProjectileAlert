package com.mrbushy.client;
class Ray {
    private Vector3D origin;
    private Vector3D direction;

    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    public boolean intersectsParallelepiped(Parallelepiped parallelepiped) {
        double tMin = Double.NEGATIVE_INFINITY;
        double tMax = Double.POSITIVE_INFINITY;

        for (int i = 0; i < 3; i++) {
            double tNear = (parallelepiped.getMinPoint().get(i) - origin.get(i)) / direction.get(i);
            double tFar = (parallelepiped.getMaxPoint().get(i) - origin.get(i)) / direction.get(i);

            if (tNear > tFar) {
                double temp = tNear;
                tNear = tFar;
                tFar = temp;
            }

            if (tNear > tMin) {
                tMin = tNear;
            }

            if (tFar < tMax) {
                tMax = tFar;
            }

            if (tMin > tMax) {
                return false;
            }

            if (tMax < 0) {
                return false;
            }
        }

        return true;
    }
}

class Vector3D {
    private double x;
    private double y;
    private double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double get(int index) {
        if (index == 0) {
            return x;
        } else if (index == 1) {
            return y;
        } else if (index == 2) {
            return z;
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public Vector3D normalize() {
        double length = Math.sqrt(x * x + y * y + z * z);
        return new Vector3D(x / length, y / length, z / length);
    }
}

class Parallelepiped {
    private Vector3D minPoint;
    private Vector3D maxPoint;

    public Parallelepiped(Vector3D minPoint, Vector3D maxPoint) {
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }

    public Vector3D getMinPoint() {
        return minPoint;
    }

    public Vector3D getMaxPoint() {
        return maxPoint;
    }
}

public class Main {
    public static void main(String[] args) {
        // Example usage
        /*Vector3D rayOrigin = new Vector3D(10, 10, 10);
        Vector3D rayDirection = new Vector3D(1, 1, 1);
        Ray ray = new Ray(rayOrigin, rayDirection);

        Vector3D parallelepipedMinPoint = new Vector3D(2, 2, 2);
        Vector3D parallelepipedMaxPoint = new Vector3D(3, 3, 3);
        Parallelepiped parallelepiped = new Parallelepiped(parallelepipedMinPoint, parallelepipedMaxPoint);

        boolean intersects = ray.intersectsParallelepiped(parallelepiped);
        System.out.println("Intersects: " + intersects);*/
    }
}