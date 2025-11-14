import java.util.Scanner;

public class BurnInputStream implements BurnStream {
    Scanner scanner = new Scanner(System.in);

    @Override
    public int getNextBurn(DescentEvent status) {
        while (true) {
            try {
                System.out.print("Enter burn rate (0-200): ");
                String input = scanner.nextLine().trim();
                int burn = Integer.parseInt(input);
                if (burn >= 0 && burn <= 200) {
                    return burn;
                } else {
                    System.err.println("Burn rate must be between 0 and 200");
                }
            } catch (NumberFormatException e) {
                System.err.println("Must Enter a Number (0-200)");
            }
        }
    }
}
