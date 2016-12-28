class DOB implements Comparable<DOB>{
	public String dob;
    public int day,month,year;

    DOB(){
        day = 0;
        month = 0;
        year = 0;
        dob = "";
    }

    DOB(String str){
        // str has dob in mm/dd/yyyy format
        String[] split = str.split("/", -1);
        month = Integer.parseInt(split[0].trim());
        day = Integer.parseInt(split[1].trim());
        year = Integer.parseInt(split[2].trim());
        dob = str;
    }

    public int compareTo(DOB that){
        if (year < that.year)
            return -1;
        else if (year > that.year)
            return 1;
        else if (year == that.year)
        {
            if (month < that.month)
                return -1;
            else if (month > that.month)
                return 1;
            else if (month == that.month)
            {
                return this.day.compareTo(that.day);
            }	// end of day
        }// end of month
    }
}   // end of DOB class