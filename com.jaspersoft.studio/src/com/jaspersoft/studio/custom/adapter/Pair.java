package com.jaspersoft.studio.custom.adapter;
public class Pair{
		
		private String packageName;
		
		private String className;
		
		public Pair(String packageName, String className){
			this.packageName = packageName;
			this.className = className;
		}
		
		public String getPackageName() {
			return packageName;
		}
		
		public String getClassName() {
			return className;
		}
		
		@Override
		public String toString() {
			return packageName + "." + className;
		}
	}