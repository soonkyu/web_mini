package model.vo;

public class BookVo {
	private int bookno;
	private String title;
	private String subtitle;
	private String bigclass;
	private String minclass;
	private String author;
	private String company;
	private int pyear;
	private String imgsource;
	
	
	
	public BookVo(int bookno, String title, String subtitle, String bigclass, String minclass, String author,
			String company, int pyear, String imgsource) {
		super();
		this.bookno = bookno;
		this.title = title;
		this.subtitle = subtitle;
		this.bigclass = bigclass;
		this.minclass = minclass;
		this.author = author;
		this.company = company;
		this.pyear = pyear;
		this.imgsource = imgsource;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookVo [bookno=");
		builder.append(bookno);
		builder.append(", title=");
		builder.append(title);
		builder.append(", subtitle=");
		builder.append(subtitle);
		builder.append(", bigclass=");
		builder.append(bigclass);
		builder.append(", minclass=");
		builder.append(minclass);
		builder.append(", author=");
		builder.append(author);
		builder.append(", company=");
		builder.append(company);
		builder.append(", pyear=");
		builder.append(pyear);
		builder.append(", imgsource=");
		builder.append(imgsource);
		builder.append("]");
		return builder.toString();
	}
	public String getImgsource() {
		return imgsource;
	}
	public void setImgsource(String imgsource) {
		this.imgsource = imgsource;
	}
	public int getBookno() {
		return bookno;
	}
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getBigclass() {
		return bigclass;
	}
	public void setBigclass(String bigclass) {
		this.bigclass = bigclass;
	}
	public String getMinclass() {
		return minclass;
	}
	public void setMinclass(String minclass) {
		this.minclass = minclass;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getPyear() {
		return pyear;
	}
	public void setPyear(int pyear) {
		this.pyear = pyear;
	}

	public BookVo() {
		// TODO Auto-generated constructor stub
	}
	

}
