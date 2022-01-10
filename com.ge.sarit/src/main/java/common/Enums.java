package common;

public class  Enums {
	public enum eBrowserType {
		Chrome(1), IE(2), FIREFOX(3), Safari(4);
		private int val;

		private eBrowserType(int val) {
			this.val = val;
		}

		public int val() {
			return val;
		}
	}
	
	public static enum ConfigType {
		Icon;
	}
}
