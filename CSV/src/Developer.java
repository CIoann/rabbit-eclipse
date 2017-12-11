
public class Developer {
	    private String id;

	    private String tsStart;
	    private String tsEnd; 
	    private String sid;
	    private String filename;
	    private String type;
	    private String parent;
	   
		private String name;
	    private String duration;
	    private String category;
		public String getSid() {
			return sid;
		}
		public Developer(String id, String sid, String category, String name, String tsStart, String tsEnd,
				String duration) {
			super();
			this.id = id;
			this.sid = sid;
			this.category = category;
			this.name = name;
			this.tsStart = tsStart;
			this.tsEnd = tsEnd;
			this.duration = duration;
		}
		public void setSid(String sid) {
			this.sid = sid;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTsStart() {
			return tsStart;
		}
		public void setTsStart(String tsStart) {
			this.tsStart = tsStart;
		}
		public String getTsEnd() {
			return tsEnd;
		}
		public void setTsEnd(String tsEnd) {
			this.tsEnd = tsEnd;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		 public String getFilename() {
				return filename;
			}
			public void setFilename(String filename) {
				this.filename = filename;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public String getParent() {
				return parent;
			}
			public void setParent(String parent) {
				this.parent = parent;
			}
		public Developer(String id, String tsStart, String tsEnd, String sid, String filename, String type,
					String parent, String name, String duration) {
				super();
				this.id = id;
				this.tsStart = tsStart;
				this.tsEnd = tsEnd;
				this.sid = sid;
				this.filename = filename;
				this.type = type;
				this.parent = parent;
				this.name = name;
				this.duration = duration;
				this.category = "-1";
			}
		public Developer(String id, String tsStart, String tsEnd, String duration) {
			super();
			this.id = id;
			this.tsStart = tsStart;
			this.tsEnd = tsEnd;
			this.duration = duration;
		}

}