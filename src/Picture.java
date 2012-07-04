import java.awt.*;
import java.net.URL;

/**
 * A class that represents a picture.  This class inherits from SimplePicture
 * 	and allows the student to add functionality and picture effects.  
 * 
 * @author Barb Ericson (ericson@cc.gatech.edu)
 * 	(Copyright Georgia Institute of Technology 2004)
 * @author Modified by Colleen Lewis (colleenl@berkeley.edu),
 * 	Jonathan Kotker (jo_ko_berkeley@berkeley.edu),
 * 	Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 * 	and David Zeng (davidzeng@berkeley.edu), for use in CS61BL, the data
 * 	structures course at University of California, Berkeley. 
 */
public class Picture extends SimplePicture 
{

	/////////////////////////// Static Variables //////////////////////////////

	// Different axes available to flip a picture.
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final int FORWARD_DIAGONAL = 3;
	public static final int BACKWARD_DIAGONAL = 4;

	// Different Picture objects for the bitmaps used in ASCII art conversion.
	private static Picture BMP_AMPERSAND;
	private static Picture BMP_APOSTROPHE;
	private static Picture BMP_AT;
	private static Picture BMP_BAR;
	private static Picture BMP_COLON; 
	private static Picture BMP_DOLLAR; 
	private static Picture BMP_DOT; 
	private static Picture BMP_EXCLAMATION; 
	private static Picture BMP_GRAVE; 
	private static Picture BMP_HASH;
	private static Picture BMP_PERCENT; 
	private static Picture BMP_SEMICOLON; 
	private static Picture BMP_SPACE; 	

	//////////////////////////// Constructors /////////////////////////////////

	/**
	 * A constructor that takes no arguments.
	 */
	public Picture () {
		super();  
	}

	/**
	 * Creates a Picture from the file name provided.
	 * 
	 * @param fileName The name of the file to create the picture from.
	 */
	public Picture(String fileName) {
		// Let the parent class handle this fileName.
		super(fileName);
	}

	/**
	 * Creates a Picture from the width and height provided.
	 * 
	 * @param width the width of the desired picture.
	 * @param height the height of the desired picture.
	 */
	public Picture(int width, int height) {
		// Let the parent class handle this width and height.
		super(width, height);
	}

	/**
	 * Creates a copy of the Picture provided.
	 * 
	 * @param pictureToCopy Picture to be copied.
	 */
	public Picture(Picture pictureToCopy) {
		// Let the parent class do the copying.
		super(pictureToCopy);
	}

	/**
	 * Creates a copy of the SimplePicture provided.
	 * 
	 * @param pictureToCopy SimplePicture to be copied.
	 */
	public Picture(SimplePicture pictureToCopy) {
		// Let the parent class do the copying.
		super(pictureToCopy);
	}

	/////////////////////////////// Methods ///////////////////////////////////

	/**
	 * @return A string with information about the picture, such as 
	 * 	filename, height, and width.
	 */
	public String toString() {
		String output = "Picture, filename = " + this.getFileName() + "," + 
		" height = " + this.getHeight() + ", width = " + this.getWidth();
		return output;
	}

	/////////////////////// PROJECT 1 BEGINS HERE /////////////////////////////

	/* Each of the methods below is constructive: in other words, each of 
	 * 	the methods below generates a new Picture, without permanently
	 * 	modifying the original Picture. */

	//////////////////////////////// Level 1 //////////////////////////////////

	/**
	 * Converts the Picture into grayscale. Since any variation of gray
	 * 	is obtained by setting the red, green, and blue components to the same
	 * 	value, a Picture can be converted into its grayscale component
	 * 	by setting the red, green, and blue components of each pixel in the
	 * 	new picture to the same value: the average of the red, green, and blue
	 * 	components of the same pixel in the original.
	 * 
	 * @return A new Picture that is the grayscale version of this Picture.
	 */
	public Picture grayscale() {
		Picture newPicture = new Picture(this);

		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();

		for(int x = 0; x < pictureWidth; x++) {
			for(int y = 0; y < pictureHeight; y++) {
				newPicture.setPixelToGray(x, y);
			}
		}
		return newPicture;
	}

	/**
	 * Helper method for grayscale() to set a pixel at (x, y) to be gray.
	 * 
	 * @param x The x-coordinate of the pixel to be set to gray.
	 * @param y The y-coordinate of the pixel to be set to gray.
	 */
	private void setPixelToGray(int x, int y) {
		Pixel currentPixel = this.getPixel(x, y);
		int average = currentPixel.getAverage();
		currentPixel.setRed(average);
		currentPixel.setGreen(average);
		currentPixel.setBlue(average);		
	}

	/**
	 * Test method for setPixelToGray. This method is called by
	 * the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToGrayWorks() {
		Picture bg           = Picture.loadPicture("Creek.bmp");
		Pixel focalPixel     = bg.getPixel(10, 10);
		bg.setPixelToGray(10, 10);
		int goalColor        = (int) focalPixel.getAverage();
		int originalAlpha    = focalPixel.getColor().getAlpha();
		boolean redCorrect   = focalPixel.getRed() == goalColor;
		boolean greenCorrect = focalPixel.getGreen() == goalColor; 
		boolean blueCorrect  = focalPixel.getBlue() == goalColor;
		boolean alphaCorrect = focalPixel.getAlpha() == originalAlpha;
		return redCorrect && greenCorrect && blueCorrect && alphaCorrect;
	}

	/**
	 * This method provide JUnit access to the testing methods written 
	 * within Picture.java
	 */
	public static boolean helpersWork() {
		if (!Picture.setPixelToGrayWorks())
		{
			return false;
		}

		// You could put other tests here..

		return true;
	}

	/**
	 * Converts the Picture into its photonegative version. The photonegative
	 * 	version of an image is obtained by setting each of the red, green,
	 * 	and blue components of every pixel to a value that is 255 minus their
	 * 	current values.
	 * 
	 * @return A new Picture that is the photonegative version of this Picture. 
	 */
	public Picture negate() {
		Picture newPicture = new Picture(this);
		
		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();

		for(int x = 0; x < pictureWidth; x++) {
			for(int y = 0; y < pictureHeight; y++) {
				newPicture.setPixelToNegative(x, y);
			}
		}
		return newPicture;
		// REPLACE THE CODE BELOW WITH YOUR OWN.
		// return new Picture(this);
	}
	
	/**
	 * Helper method for negate(). Gets current pixel location (x, y) in
	 * argument and subtract all RGB valus from 255.
	 */
	private void setPixelToNegative(int x, int y) {
		Pixel currentPixel = this.getPixel(x, y);
		
		currentPixel.setRed(255 - currentPixel.getRed());
		currentPixel.setGreen(255 - currentPixel.getGreen());
		currentPixel.setBlue(255 - currentPixel.getBlue());
	}
	
	/**
	 * Test method for setPixelToNegative. This method is called by
	 * the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToNegativeWorks() {
		Picture bg           = Picture.loadPicture("Creek.bmp");
		Pixel focalPixel     = bg.getPixel(10, 10);
		
		//grab the original RGB values, so we can do a comparison later. 
		int originalRed = focalPixel.getRed(); 
		int originalGreen = focalPixel.getGreen(); 
		int originalBlue = focalPixel.getBlue(); 
		
		bg.setPixelToNegative(10, 10);
		//the formula for "negate" is 255-original color 
		boolean redCorrect   = focalPixel.getRed() == 255-originalRed; 
		boolean greenCorrect = focalPixel.getGreen() == originalGreen; 
		boolean blueCorrect  = focalPixel.getBlue() == originalBlue; 
		return redCorrect && greenCorrect && blueCorrect; 
	}

	/**
	 * Creates an image that is lighter than the original image. The range of
	 * each color component should be between 0 and 255 in the new image. The
	 * alpha value should not be changed.
	 * 
	 * @return A new Picture that has every color value of the Picture increased
	 *         by the lightenAmount.
	 */
	public Picture lighten(int lightenAmount) {
		Picture newPicture = new Picture(this);
		
		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();

		for(int x = 0; x < pictureWidth; x++) {
			for(int y = 0; y < pictureHeight; y++) {
				newPicture.setPixelToLighten(x, y, lightenAmount);
			}
		}
		return newPicture;
		// REPLACE THE CODE BELOW WITH YOUR OWN.
		// return new Picture(this);
	}
	
	/**
	 * Helper method for lighten(). Gets current pixel location (x, y) and 
	 * lighten amount in argument and adds lighten amount to RGB values. 
	 * New RGB values cap at 0 to 255 using else if statements.
	 */
	private void setPixelToLighten(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		
		int newRed = currentPixel.getRed() + amount;
		int newGreen = currentPixel.getGreen() + amount;
		int newBlue = currentPixel.getBlue() + amount;
		
		if(newRed < 0){
			newRed = 0;
		}
		else if(newRed > 255){
			newRed = 255;
		}
		
		if(newGreen < 0){
			newGreen = 0;
		}
		else if(newGreen > 255){
			newRed = 255;
		}
		
		if(newBlue < 0){
			newBlue = 0;
		}
		else if(newBlue > 255){
			newRed = 255;
		}
		
		currentPixel.setRed(newRed);
		currentPixel.setGreen(newGreen);
		currentPixel.setBlue(newBlue);
	}
	
	/**
	 * Test method for setPixelToLighten. This method is called by
	 * the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToLightenWorks() {
		
		
		
	}

	/**
	 * Creates an image that is darker than the original image.The range of
	 * each color component should be between 0 and 255 in the new image. The
	 * alpha value should not be changed.
	 * 
	 * @return A new Picture that has every color value of the Picture decreased
	 *         by the darkenenAmount.
	 */
	public Picture darken(int darkenAmount) {
		Picture newPicture = new Picture(this);
		
		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();

		for(int x = 0; x < pictureWidth; x++) {
			for(int y = 0; y < pictureHeight; y++) {
				newPicture.setPixelToDarken(x, y, darkenAmount);
			}
		}
		return newPicture;
		// REPLACE THE CODE BELOW WITH YOUR OWN.
		// return new Picture(this);
	}
	
	/**
	 * Helper method for darken(). Gets current pixel location (x, y) and 
	 * darken amount in argument and subtracts darken amount to RGB values. 
	 * New RGB values cap at 0 to 255 using else if statements.
	 */
	private void setPixelToDarken(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		
		int newRed = currentPixel.getRed() - amount;
		int newGreen = currentPixel.getGreen() - amount;
		int newBlue = currentPixel.getBlue() - amount;
		
		if(newRed < 0){
			newRed = 0;
		}
		else if(newRed > 255){
			newRed = 255;
		}
		
		if(newGreen < 0){
			newGreen = 0;
		}
		else if(newGreen > 255){
			newRed = 255;
		}
		
		if(newBlue < 0){
			newBlue = 0;
		}
		else if(newBlue > 255){
			newRed = 255;
		}
		
		currentPixel.setRed(newRed);
		currentPixel.setGreen(newGreen);
		currentPixel.setBlue(newBlue);
	}
	
	/**
	 * Test method for setPixelToDarken. This method is called by
	 * the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToDarkenWorks() {
	}

	/**
	 * Creates an image where the blue value has been increased by amount.The range of
	 * each color component should be between 0 and 255 in the new image. The
	 * alpha value should not be changed.
	 * 
	 * @return A new Picture that has every blue value of the Picture increased
	 *         by amount.
	 */
	public Picture addBlue(int amount) {
		Picture newPicture = new Picture(this);
		
		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();

		for(int x = 0; x < pictureWidth; x++) {
			for(int y = 0; y < pictureHeight; y++) {
				newPicture.setPixelToAddBlue(x, y, amount);
			}
		}
		return newPicture;
		// REPLACE THE CODE BELOW WITH YOUR OWN.
		// return new Picture(this);
	}
	
	/**
	 * Helper method for addBlue(). Gets current pixel location (x, y) and 
	 * blue amount in argument and adds value to blue value. 
	 * New blue values cap at 0 to 255 using else if statements.
	 */
	private void setPixelToAddBlue(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		
		int newBlue = currentPixel.getBlue() + amount;
		
		if(newBlue < 0){
			newBlue = 0;
		}
		else if(newBlue > 255){
			newBlue = 255;
		}
		
		currentPixel.setBlue(newBlue);
	}
	
	/**
	 * Test method for setPixelToAddBlue. This method is called by
	 * the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToAddBlueWorks() {
	}
	
	/**
	 * Creates an image where the red value has been increased by amount. The range of
	 * each color component should be between 0 and 255 in the new image. The
	 * alpha value should not be changed.
	 * 
	 * @return A new Picture that has every red value of the Picture increased
	 *         by amount.
	 */
	public Picture addRed(int amount) {
		Picture newPicture = new Picture(this);
		
		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();

		for(int x = 0; x < pictureWidth; x++) {
			for(int y = 0; y < pictureHeight; y++) {
				newPicture.setPixelToAddRed(x, y, amount);
			}
		}
		return newPicture;
		// REPLACE THE CODE BELOW WITH YOUR OWN.
		// return new Picture(this);
	}
	
	/**
	 * Helper method for addRed(). Gets current pixel location (x, y) and 
	 * red amount in argument and adds value to red value. 
	 * New red values cap at 0 to 255 using else if statements.
	 */
	private void setPixelToAddRed(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		
		int newRed = currentPixel.getRed() + amount;
		
		if(newRed < 0){
			newRed = 0;
		}
		else if(newRed > 255){
			newRed = 255;
		}
		
		currentPixel.setRed(newRed);
	}
	
	/**
	 * Test method for setPixelToAddRed. This method is called by
	 * the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToAddRedWorks() {
	}
	
	/**
	 * Creates an image where the green value has been increased by amount. The range of
	 * each color component should be between 0 and 255 in the new image. The
	 * alpha value should not be changed.
	 * 
	 * @return A new Picture that has every green value of the Picture increased
	 *         by amount.
	 */
	public Picture addGreen(int amount) {
		Picture newPicture = new Picture(this);
		
		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();

		for(int x = 0; x < pictureWidth; x++) {
			for(int y = 0; y < pictureHeight; y++) {
				newPicture.setPixelToAddGreen(x, y, amount);
			}
		}
		return newPicture;
		// REPLACE THE CODE BELOW WITH YOUR OWN.
		// return new Picture(this);
	}
	
	/**
	 * Helper method for addGreen(). Gets current pixel location (x, y) and 
	 * green amount in argument and adds value to green value. 
	 * New green values cap at 0 to 255 using else if statements.
	 */
	private void setPixelToAddGreen(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		
		int newGreen = currentPixel.getGreen() + amount;
		
		if(newGreen < 0){
			newGreen = 0;
		}
		else if(newGreen > 255){
			newGreen = 255;
		}
		
		currentPixel.setGreen(newGreen);
	}
	
	/**
	 * Test method for setPixelToAddBlue. This method is called by
	 * the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToAddGreenWorks() {
	}
	
	/** 
	 * @param x x-coordinate of the pixel currently selected.
	 * @param y y-coordinate of the pixel currently selected.
	 * @param background Picture to use as the background.
	 * @param threshold Threshold within which to replace pixels.
	 * 
	 * @return A new Picture where all the pixels in the original Picture,
	 * 	which differ from the currently selected pixel within the provided
	 * 	threshold (in terms of color distance), are replaced with the
	 * 	corresponding pixels in the background picture provided.
	 * 
	 * 	If the two Pictures are of different dimensions, the new Picture will
	 * 	have length equal to the smallest of the two Pictures being combined,
	 * 	and height equal to the smallest of the two Pictures being combined.
	 * 	In this case, the Pictures are combined as if they were aligned at
	 * 	the top left corner (0, 0).
	 */
	public Picture chromaKey(int xRef, int yRef, Picture background, int threshold) {
		int newPictureHeight = min(this.getHeight(), background.getHeight());
		int newPictureWidth = min(this.getWidth(), background.getWidth());
		
		Picture newPicture = new Picture(newPictureWidth, newPictureHeight);
		Pixel refPixel = this.getPixel(xRef, yRef);
		Color refColor = refPixel.getColor();
		
		for(int x = 0; x < newPictureWidth; x++) {
			for(int y = 0; y < newPictureHeight; y++) {
				Pixel backgroundPixel = background.getPixel(x, y);
				Color backgroundColor = backgroundPixel.getColor();
				newPicture.setPixelToChromaKey(x, y, threshold, refColor, backgroundColor);
			}
		}
		return newPicture;
		/* REPLACE THE CODE BELOW WITH YOUR OWN. */	
		// return new Picture(this);
	}
	
	/**
	 * Helper method for chromaKey(). Gets current pixel location (x, y) and 
	 * a threshold and a refernce color. Method returns a boolean type. If
	 * color distance between current pixel and reference color is within 
	 * threshold, return true. else false.
	 */
	private void setPixelToChromaKey(int x, int y, int threshold, Color refColor, Color bgColor) {
		Pixel currentPixel = this.getPixel(x, y);
		double distance = currentPixel.colorDistance(refColor, bgColor);
		
		if((int) distance <= threshold){
			currentPixel.setColor(bgColor);
		}
		else{
			currentPixel.setColor(refColor);
		}
	}
	
	/**
	 * Test method for setPixelToChromaKey. This method is called by
	 * the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToChromaKeyWorks() {
	}

	//////////////////////////////// Level 2 //////////////////////////////////

	/**
	 * Rotates this Picture by the integer multiple of 90 degrees provided.
	 * 	If the number of rotations provided is positive, then the picture
	 * 	is rotated clockwise; else, the picture is rotated counterclockwise.
	 * 	Multiples of four rotations (including zero) correspond to no
	 * 	rotation at all.
	 * 
	 * @param rotations The number of 90-degree rotations to rotate this
	 * 	image by.
	 * 
	 * @return A new Picture that is the rotated version of this Picture.
	 */
	public Picture rotate(int rotations) {
		//if case using modulus to check how it is rotated. only 2
		//possble conformations.
		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();
		
		if (rotations % 2 == 0){
			Picture rotatedPicture = new Picture(pictureWidth, pictureHeight);
		}
		else{
			Picture rotatedPicture = new Picture(pictureHeight, pictureWidth);
		}

		if(rotations >= 0){
			for(int x = 0; x < pictureWidth; x++) {
				for(int y = 0; y < pictureHeight; y++) {
					rotatedPicture.applyRotateTransformationMatrix(x, y, this, false);
				}
			}
		}
		else{
			for(int x = 0; x < pictureWidth; x++) {
				for(int y = 0; y < pictureHeight; y++) {
					rotatedPicture.applyRotateTransformationMatrix(x, y, this, true);
				}
			}
		}
		// REPLACE THE CODE BELOW WITH YOUR OWN.
		// return new Picture(this);
	}
	
	/**
	 * Helper method that takes in (x, y) location of this picture and the
	 * object reference to the new picture. Uses transformation matrix 
	 * concept. Source: http://en.wikipedia.org/wiki/Transformation_matrix
	 * New x, y is produced and a new pixel is added to that location
	 */
	public void applyRotateTransformationMatrix(int x, int y, Picture originalPicture, boolean reverse){
		Pixel currentPixel = originalPicture.getPixel(x, y);
		Color currentColor = currentPixel
		this.setPixel
	}

	/**
	 * Flips this Picture about the given axis. The axis can be one of
	 * 	four static integer constants:
	 * 
	 * 	(a) Picture.HORIZONTAL: The picture should be flipped about
	 * 		a horizontal axis passing through the center of the picture.
	 * 	(b) Picture.VERTICAL: The picture should be flipped about
	 * 		a vertical axis passing through the center of the picture.
	 * 	(c) Picture.FORWARD_DIAGONAL: The picture should be flipped about
	 * 		an axis that passes through the north-east and south-west
	 * 		corners of the picture.
	 * 	(d) Picture.BACKWARD_DIAGONAL: The picture should be flipped about
	 * 		an axis that passes through the north-west and south-east
	 * 		corners of the picture.
	 * 
	 * @param axis Axis about which to flip the Picture provided.
	 * 
	 * @return A new Picture flipped about the axis provided.
	 */
	public Picture flip(int axis) {
		// REPLACE THE CODE BELOW WITH YOUR OWN.
		return new Picture(this);
	}

	/**
	 * @param threshold
	 *            Threshold to use to determine the presence of edges.
	 * 
	 * @return A new Picture that contains only the edges of this Picture. For
	 *         each pixel, we separately consider the color distance between
	 *         that pixel and the one pixel to its left, and also the color
	 *         distance between that pixel and the one pixel to the north, where
	 *         applicable. As an example, we would compare the pixel at (3, 4)
	 *         with the pixels at (3, 3) and the pixels at (2, 4). Also, since
	 *         the pixel at (0, 4) only has a pixel to its north, we would only
	 *         compare it to that pixel. If either of the color distances is
	 *         larger than the provided color threshold, it is set to black
	 *         (with an alpha of 255); otherwise, the pixel is set to white
	 *         (with an alpha of 255). The pixel at (0, 0) will always be set to
	 *         white.
	 */
	public Picture showEdges(int threshold) {
		/* REPLACE THE CODE BELOW WITH YOUR OWN. */
		return new Picture(this);
	}

	//////////////////////////////// Level 3 //////////////////////////////////

	/**
	 * @return A new Picture that is the ASCII art version of this Picture. To
	 *         implement this, the Picture is first converted into its grayscale
	 *         equivalent. Then, starting from the top left, the average color
	 *         of every chunk of 10 pixels wide by 20 pixels tall is computed.
	 *         Based on the average value obtained, this chunk will be replaced
	 *         by the corresponding ASCII character specified by the table
	 *         below.
	 *
	 *	       The ASCII characters to be used are available as Picture objects,
	 * 	       also of size 10 pixels by 20 pixels. The following characters
	 * 	       should be used, depending on the average value obtained:
	 * 	
	 * 	0 to 18: # (Picture.BMP_POUND)
	 * 	19 to 37: @ (Picture.BMP_AT)
	 * 	38 to 56: & (Picture.BMP_AMPERSAND)
	 * 	57 to 75: $ (Picture.BMP_DOLLAR)
	 * 	76 to 94: % (Picture.BMP_PERCENT)
	 * 	95 to 113: | (Picture.BMP_BAR)
	 * 	114 to 132: ! (Picture.BMP_EXCLAMATION)
	 * 	133 to 151: ; (Picture.BMP_SEMICOLON)
	 * 	152 to 170: : (Picture.BMP_COLON)
	 * 	171 to 189: ' (Picture.BMP_APOSTROPHE)
	 * 	190 to 208: ` (Picture.BMP_GRAVE)
	 * 	209 to 227: . (Picture.BMP_DOT)
	 * 	228 to 255: (Picture.BMP_SPACE)
	 * 
	 * We provide a getAsciiPic method to obtain the Picture object 
	 * 	corresponding to a character, given any of the static Strings
	 * 	mentioned above.
	 * 
	 * Note that the resultant Picture should be the exact same size
	 * 	as the original Picture; this might involve characters being
	 * 	partially copied to the final Picture. 
	 */
	public Picture convertToAscii() {
		/* REPLACE THE CODE BELOW WITH YOUR OWN. */
		return new Picture(this);
	}

	/**
	 * Blurs this Picture. To achieve this, the algorithm takes a pixel, and
	 * sets it to the average value of all the pixels in a square of side (2 *
	 * blurThreshold) + 1, centered at that pixel. For example, if blurThreshold
	 * is 2, and the current pixel is at location (8, 10), then we will consider
	 * the pixels in a 5 by 5 square that has corners at pixels (6, 8), (10, 8),
	 * (6, 12), and (10, 12). If there are not enough pixels available -- if the
	 * pixel is at the edge, for example, or if the threshold is larger than the
	 * image -- then the missing pixels are ignored, and the average is taken
	 * only of the pixels available.
	 * 
	 * The red, blue, green and alpha values should each be averaged separately.
	 * 
	 * @param blurThreshold
	 *            Size of the blurring square around the pixel.
	 * 
	 * @return A new Picture that is the blurred version of this Picture, using
	 *         a blurring square of size (2 * threshold) + 1.
	 */
	public Picture blur(int blurThreshold) {
		/* REPLACE THE CODE BELOW WITH YOUR OWN. */
		return new Picture(this);
	}


	/**
	 * @param x x-coordinate of the pixel currently selected.
	 * @param y y-coordinate of the pixel currently selected.
	 * @param threshold Threshold within which to delete pixels.
	 * @param newColor New color to color pixels.
	 * 
	 * @return A new Picture where all the pixels connected to the currently
	 * 	selected pixel, and which differ from the selected pixel within the
	 * 	provided threshold (in terms of color distance), are colored with
	 * 	the new color provided. 
	 */
	public Picture paintBucket(int x, int y, int threshold, Color newColor) {
		/* REPLACE THE CODE BELOW WITH YOUR OWN. */
		return new Picture(this);
	}

	///////////////////////// PROJECT 1 ENDS HERE /////////////////////////////

	public boolean equals(Object obj) {
		if (!(obj instanceof Picture)) {
			return false;
		}

		Picture p = (Picture) obj;
		// Check that the two pictures have the same dimensions.
		if ((p.getWidth() != this.getWidth()) ||
				(p.getHeight() != this.getHeight())) {
			return false;
		}

		// Check each pixel.
		for (int x = 0; x < this.getWidth(); x++) {
			for(int y = 0; y < this.getHeight(); y++) {
				if (!this.getPixel(x, y).equals(p.getPixel(x, y))) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Helper method for loading a picture in the current directory.
	 */
	protected static Picture loadPicture(String pictureName) {
		URL url = Picture.class.getResource(pictureName);
		return new Picture(url.getFile().replaceAll("%20", " "));
	}

	/**
	 * Helper method for loading the pictures corresponding to each character
	 * 	for the ASCII art conversion.
	 */
	private static Picture getAsciiPic(int grayValue) {
		int asciiIndex = (int) grayValue / 19;

		if (BMP_AMPERSAND == null) {
			BMP_AMPERSAND = Picture.loadPicture("ampersand.bmp");
			BMP_APOSTROPHE = Picture.loadPicture("apostrophe.bmp");
			BMP_AT = Picture.loadPicture("at.bmp");
			BMP_BAR = Picture.loadPicture("bar.bmp");
			BMP_COLON = Picture.loadPicture("colon.bmp");
			BMP_DOLLAR = Picture.loadPicture("dollar.bmp");
			BMP_DOT = Picture.loadPicture("dot.bmp");
			BMP_EXCLAMATION = Picture.loadPicture("exclamation.bmp");
			BMP_GRAVE = Picture.loadPicture("grave.bmp");
			BMP_HASH = Picture.loadPicture("hash.bmp");
			BMP_PERCENT = Picture.loadPicture("percent.bmp");
			BMP_SEMICOLON = Picture.loadPicture("semicolon.bmp");
			BMP_SPACE = Picture.loadPicture("space.bmp");
		}

		switch(asciiIndex) {
		case 0:
			return Picture.BMP_HASH;
		case 1:
			return Picture.BMP_AT;
		case 2:
			return Picture.BMP_AMPERSAND;
		case 3:
			return Picture.BMP_DOLLAR;
		case 4: 
			return Picture.BMP_PERCENT;
		case 5:
			return Picture.BMP_BAR;
		case 6: 
			return Picture.BMP_EXCLAMATION;
		case 7:
			return Picture.BMP_SEMICOLON;
		case 8:
			return Picture.BMP_COLON;
		case 9: 
			return Picture.BMP_APOSTROPHE;
		case 10:
			return Picture.BMP_GRAVE;
		case 11:
			return Picture.BMP_DOT;
		default:
			return Picture.BMP_SPACE;
		}
	}

	public static void main(String[] args) {
		Picture initialPicture = new Picture(
				FileChooser.pickAFile(FileChooser.OPEN));
		initialPicture.explore();
	}

} // End of Picture class
