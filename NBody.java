public class NBody {
    public static void main(String[] args) {

        // Step 1. Parse command-line arguments.
        double tau = Double.parseDouble(args[0]);
        double t = Double.parseDouble(args[1]);

        // Step 2. Read the universe from standard input.
        int n = StdIn.readInt();
        double radius = StdIn.readDouble();
        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];

        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();
        }

        // Step 3. Initialize standard drawing.
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);
        StdDraw.enableDoubleBuffering();

        // Step 4. Play music on standard audio.
        StdAudio.playInBackground("2001.wav");

        // Step 5. Simulate the universe (main time loop).
        for (double k = 0.0; k < tau; k += t) {

            // Step 5A. Calculate net forces.
            double[] fx = new double[n];
            double[] fy = new double[n];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        double dx = px[j] - px[i];
                        double dy = py[j] - py[i];
                        double r = Math.sqrt((dx * dx) + (dy * dy));
                        double force = (6.67e-11 * (mass[i] * mass[j])) / (r * r);
                        fx[i] += force * (dx / r);
                        fy[i] += force * (dy / r);


                    }
                }
            }

            for (int i = 0; i < n; i++) {
                double ax = fx[i] / mass[i];
                double ay = fy[i] / mass[i];

                // Step 5B. Update velocities and positions.
                vx[i] += ax * t;
                vy[i] += ay * t;
                px[i] += vx[i] * t;
                py[i] += vy[i] * t;
            }


            // Step 5C. Draw universe to standard drawing.
            StdDraw.picture(0, 0, "starfield.jpg");
            for (int i = 0; i < n; i++) {
                StdDraw.picture(px[i], py[i], image[i]);

            }
            StdDraw.show();
            StdDraw.pause(20);


        }
        // Step 6. Print universe to standard output.
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          px[i], py[i], vx[i], vy[i], mass[i], image[i]);
        }


    }
}
