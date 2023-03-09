package com.lgu.ccss.carpush.model;

import java.util.Date;

public class ConnDeviceVO {
	public static final String VEHICLE_MODEL_AM = "AM";
	
	public static final String USE_Y = "Y";
	public static final String USE_N = "N";
	
	private String connDeviceId;
	private String membId;
	private String vehicleModelId;
	private String deviceModelId;
	private String deviceType;
	private String useYn;
	private String deviceCtn;
	private String deviceEsn;
	private String usimModel;
	private String usimSn;
	private String deviceLoginDt;
	private String devicePushClientId;
	private String devicePushConnStatus;
	private String devicePushConnDt;
	private String firmwareInfo;
	private String jsonSetInfo;
	private String uiccId;
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	
	
	public String getConnDeviceId() {
		return connDeviceId;
	}
	public void setConnDeviceId(String connDeviceId) {
		this.connDeviceId = connDeviceId;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getVehicleModelId() {
		return vehicleModelId;
	}
	public void setVehicleModelId(String vehicleModelId) {
		this.vehicleModelId = vehicleModelId;
	}
	public String getDeviceModelId() {
		return deviceModelId;
	}
	public void setDeviceModelId(String deviceModelId) {
		this.deviceModelId = deviceModelId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDeviceCtn() {
		return deviceCtn;
	}
	public void setDeviceCtn(String deviceCtn) {
		this.deviceCtn = deviceCtn;
	}
	public String getDeviceEsn() {
		return deviceEsn;
	}
	public void setDeviceEsn(String deviceEsn) {
		this.deviceEsn = deviceEsn;
	}
	public String getUsimModel() {
		return usimModel;
	}
	public void setUsimModel(String usimModel) {
		this.usimModel = usimModel;
	}
	public String getUsimSn() {
		return usimSn;
	}
	public void setUsimSn(String usimSn) {
		this.usimSn = usimSn;
	}
	public String getDeviceLoginDt() {
		return deviceLoginDt;
	}
	public void setDeviceLoginDt(String deviceLoginDt) {
		this.deviceLoginDt = deviceLoginDt;
	}
	public String getDevicePushClientId() {
		return devicePushClientId;
	}
	public void setDevicePushClientId(String devicePushClientId) {
		this.devicePushClientId = devicePushClientId;
	}
	public String getDevicePushConnStatus() {
		return devicePushConnStatus;
	}
	public void setDevicePushConnStatus(String devicePushConnStatus) {
		this.devicePushConnStatus = devicePushConnStatus;
	}
	public String getDevicePushConnDt() {
		return devicePushConnDt;
	}
	public void setDevicePushConnDt(String devicePushConnDt) {
		this.devicePushConnDt = devicePushConnDt;
	}
	public String getFirmwareInfo() {
		return firmwareInfo;
	}
	public void setFirmwareInfo(String firmwareInfo) {
		this.firmwareInfo = firmwareInfo;
	}
	public String getJsonSetInfo() {
		return jsonSetInfo;
	}
	public void setJsonSetInfo(String jsonSetInfo) {
		this.jsonSetInfo = jsonSetInfo;
	}
	public String getUiccId() {
		return uiccId;
	}
	public void setUiccId(String uiccId) {
		this.uiccId = uiccId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("====================== ConnDeviceVO ======================").append("\r\n");
	    sb.append("connDeviceId          :").append(connDeviceId).append("\r\n");
	    sb.append("membId                :").append(membId).append("\r\n");
	    sb.append("vehicleModelId        :").append(vehicleModelId).append("\r\n");
	    sb.append("deviceModelId         :").append(deviceModelId).append("\r\n");
	    sb.append("deviceType            :").append(deviceType).append("\r\n");
	    sb.append("useYn                 :").append(useYn).append("\r\n");
	    sb.append("deviceCtn             :").append(deviceCtn).append("\r\n");
	    sb.append("deviceEsn             :").append(deviceEsn).append("\r\n");
	    sb.append("usimModel             :").append(usimModel).append("\r\n");
	    sb.append("usimSn                :").append(usimSn).append("\r\n");
	    sb.append("deviceLoginDt         :").append(deviceLoginDt).append("\r\n");
	    sb.append("devicePushClientId    :").append(devicePushClientId).append("\r\n");
	    sb.append("devicePushConnStatus  :").append(devicePushConnStatus).append("\r\n");
	    sb.append("devicePushConnDt      :").append(devicePushConnDt).append("\r\n");
	    sb.append("firmwareInfo          :").append(firmwareInfo).append("\r\n");
	    sb.append("jsonSetInfo           :").append(jsonSetInfo).append("\r\n");
	    sb.append("uiccId                :").append(uiccId).append("\r\n");
	    sb.append("regId                 :").append(regId).append("\r\n");
	    sb.append("regDt                 :").append(regDt).append("\r\n");
		sb.append("updId                 :").append(updId).append("\r\n");
		sb.append("updDt                 :").append(updDt).append("\r\n");
		return sb.toString();	
	}
}
