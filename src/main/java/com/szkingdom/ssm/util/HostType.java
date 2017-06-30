package com.szkingdom.ssm.util;

public enum HostType {
	API("https://vcloud.163.com"), SMS("http://api.sms.ronghub.com");
	private String type;

	private HostType(String type) {
		this.type = type;
	}



	public static HostType getType(String state) {
		for (HostType deviceType : HostType.values()) {
			if (deviceType.type.equalsIgnoreCase(state)) {
				return deviceType;
			}
		}
		throw new RuntimeException(state + " is not a valid Host Type!");
	}

	public String getStrType() {
		return type;
	}
}