package codingcompetition2019;

public class DisasterDescription {

	private String year;
    private String category;
    private int reportedIncidentsNum;

    public DisasterDescription (String year, String category, int reportedIncidentsNum) {
        this.year = year;
        this.category = category;
        this.reportedIncidentsNum = reportedIncidentsNum;
    }

    public String getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public int getReportedIncidentsNum() {
        return reportedIncidentsNum;
    }

}
