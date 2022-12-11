public class Sum {
    public static void main (String[] args) {
		String enter = "";
		for (String part : args) {
			enter += part + " ";
		}
		int result = 0;
		int startofnumber = -1;
		for (int i = 0; i < enter.length(); i++) {
			if (!(Character.isWhitespace(enter.charAt(i)))) {
				if (startofnumber == -1) {
					startofnumber = i;
				}
			} else {
				if (startofnumber != -1) {
					result += Integer.parseInt(enter.substring(startofnumber, i));
					startofnumber = -1;
				}
			}
		}
		System.out.println(result);
    }
}