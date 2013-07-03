package com.example.entity;

/**
 * Represents the resolution of the device's screen.
 * 
 * @author tomique
 * 
 */
public class Resolution {

	private int width;
	private int height;

	public Resolution(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns the width of the device's screen
	 * 
	 * @return the width of the device's screen
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the device's screen
	 * 
	 * @return the height of the device's screen
	 */
	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[").append(width).append(", ");
		sb.append(height).append("]");

		return sb.toString();
	}
}
