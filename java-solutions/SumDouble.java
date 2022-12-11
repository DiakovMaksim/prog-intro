public class SumDouble {
    public static void main (String[] args) {
		double result = 0;
		for (String part : args) {
		int startofnumber = -1;
		    for (int i = 0; i < part.length(); i++) {
				if (!(Character.isWhitespace(part.charAt(i)))) {
					if (startofnumber == -1) {
						startofnumber = i;
					}
				if (i == part.length() - 1){
					result += Double.parseDouble(part.substring(startofnumber, i+1));
				}
			} else {
				if (startofnumber != -1) {
					result += Double.parseDouble(part.substring(startofnumber, i));
					startofnumber = -1;
				}
			}
			}
		}
		System.out.println(result);
    }
}