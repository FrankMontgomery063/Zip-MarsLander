public class OnBoardComputer implements BurnStream {

    @Override
    public int getNextBurn(DescentEvent status) {
        int altitude = status.getAltitude();
        int velocity = status.getVelocity();
        int fuel = status.getFuel();
        
        // Physics: DeltaV = 100 - Burn
        // Goal: Land at 1-2 m/s velocity
        
        // Fuel conservation - use less fuel at high altitudes
        if (fuel < 1000) {
            // Low fuel - be conservative
            if (altitude > 100) {
                return 0; // Free fall to conserve fuel
            }
        }
        
        // If very close to ground, emergency braking
        if (altitude <= 15) {
            if (velocity > 3) {
                int burn = 200; // Maximum braking
                System.out.println(burn); /*hack!*/
                return burn;
            } else {
                int burn = 98 + velocity; // Fine control to maintain 1-3 m/s
                System.out.println(burn); /*hack!*/
                return burn;
            }
        }
        
        // More fuel-efficient target velocities
        int targetVelocity;
        if (altitude > 2000) {
            targetVelocity = 150; // Faster descent to save fuel
        } else if (altitude > 500) {
            targetVelocity = 100; // Medium speed
        } else if (altitude > 100) {
            targetVelocity = 50;  // Slower approach
        } else {
            targetVelocity = 20;  // Slow final approach
        }
        
        // Calculate burn needed - be more conservative
        int velocityError = velocity - targetVelocity;
        int burn = 100 + (velocityError / 2); // Gentler corrections to save fuel
        
        // Clamp burn to valid range (0-200)
        if (burn < 0) burn = 0;
        if (burn > 200) burn = 200;
        
        System.out.println(burn); /*hack!*/
        return burn;
    }

}
