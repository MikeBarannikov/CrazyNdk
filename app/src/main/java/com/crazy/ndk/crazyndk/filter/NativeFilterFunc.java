package com.crazy.ndk.crazyndk.filter;

public class NativeFilterFunc {
	public static native int[] discreteGaussianBlur(int[] pixels, int width, int height, double sigma);
	public static native int[] sketchFilter(int[] pixels, int width, int height);
}
