public class OnBoardComputer implements BurnStream {

    @Override
    public int getNextBurn(DescentEvent status) {
        int altitude = status.getAltitude();
        int velocity = status.getVelocity();
        
        // Physics: DeltaV = 100 - Burn
        // Goal: Land at 1-2 m/s velocity
        
        // If very close to ground, emergency braking
        if (altitude <= 10) {
            if (velocity > 2) {
                int burn = 200; // Maximum braking
                System.out.println(burn); /*hack!*/
                return burn;
            } else {
                int burn = 98 + velocity; // Fine control to maintain 1-2 m/s
                System.out.println(burn); /*hack!*/
                return burn;
            }
        }
        
        // Calculate desired velocity for current altitude
        int targetVelocity;
        if (altitude > 1000) {
            targetVelocity = 100; // Fast descent at high altitude
        } else if (altitude > 200) {
            targetVelocity = 50;  // Medium speed
        } else if (altitude > 50) {
            targetVelocity = 25;  // Slower approach
        } else {
            targetVelocity = 10;  // Very slow final approach
        }
        
        // Calculate burn needed to achieve target velocity
        int velocityError = velocity - targetVelocity;
        int burn = 100 + velocityError; // 100 maintains speed, +/- adjusts
        
        // Clamp burn to valid range (0-200)
        if (burn < 0) burn = 0;
        if (burn > 200) burn = 200;
        
        System.out.println(burn); /*hack!*/
        return burn;
    }

}
