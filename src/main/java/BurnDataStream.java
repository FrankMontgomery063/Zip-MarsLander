public class BurnDataStream implements BurnStream {
    // these are the series of burns made each 10 secs by the lander.
    // change them to see if you can get the lander to make a soft landing.
    // burns are between 0 and 200. This burn array should achieve soft landing.
    // Physics: DeltaV = 100 - Burn (0=+100 m/s, 100=0 m/s, 200=-100 m/s)
    // Goal: Land at 1-2 m/s for success

    int burnArray[] = {0, 0, 0, 0, 50, 75, 90, 95, 100, 105, 110, 120, 140, 180, 198};
    int burnIdx = -1;

    public BurnDataStream() { }
    public BurnDataStream(int[] burns) {
        this.burnArray = burns;
    }
    @Override
    public int getNextBurn(DescentEvent status) {
        if (burnIdx < burnArray.length - 1) {
            burnIdx++;
            System.out.println(burnArray[burnIdx]); /*hack!*/
            return burnArray[burnIdx];
        }
        return 0;
    }
}
