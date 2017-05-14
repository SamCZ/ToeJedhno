package cz.tische.game.math;

public class SMPLNoise {
	
	public static float scaled_ridged_octave_noise_2d(float octaves, float persistence, float scale, float loBound, float hiBound, float x, float y ) {
		return Math.abs(octave_noise_2d(octaves, persistence, scale, x, y) * (hiBound - loBound) / 2 + (hiBound + loBound) / 2);
	}
	
	public static float scaled_octave_noise_2d(float octaves, float persistence, float scale, float loBound, float hiBound, float x, float y ) {
		return octave_noise_2d(octaves, persistence, scale, x, y) * (hiBound - loBound) / 2 + (hiBound + loBound) / 2;
	}

	public static float octave_noise_2d(float octaves, float persistence, float scale, float x, float y) {
		float total = 0;
	    float frequency = scale;
	    float amplitude = 1;

	    // We have to keep track of the largest possible amplitude,
	    // because each octave adds more, and we need a value in [-1, 1].
	    float maxAmplitude = 0;
		
		int i;
	    for( i=0; i < octaves; i++ ) {
	        total += SimplexNoise.noise( x * frequency, y * frequency ) * amplitude;

	        frequency *= 2;
	        maxAmplitude += amplitude;
	        amplitude *= persistence;
	    }

	    return total / maxAmplitude;
	}

	public static float scaled_reverse_ridged_octave_noise_2d(float octaves, float persistence, float scale, float loBound, float hiBound, float x, float y) {
		return Math.abs(octave_noise_2d(octaves, persistence, scale, x, y) * (hiBound - loBound) / 2 + (hiBound + loBound) / 2);
	}
	
}
