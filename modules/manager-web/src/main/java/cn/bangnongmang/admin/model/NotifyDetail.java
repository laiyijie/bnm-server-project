package cn.bangnongmang.admin.model;

public class NotifyDetail {

	private String hook_name;
	private String hook_description;
	private String type_name;
	private String type_description;
	private String parameters;
	private String pattern;

	public String getHook_name() {
		return hook_name;
	}

	public void setHook_name(String hook_name) {
		this.hook_name = hook_name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getHook_description() {
		return hook_description;
	}

	public void setHook_description(String hook_description) {
		this.hook_description = hook_description;
	}

	public String getType_description() {
		return type_description;
	}

	public void setType_description(String type_description) {
		this.type_description = type_description;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
