package com.tvd12.dahlia.core.setting;

import java.util.Map;

import com.tvd12.dahlia.core.data.DataType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FieldByteSetting extends FieldSetting {

	protected Byte defaultValue;
	
	@Override
	public DataType getType() {
		return DataType.BYTE;
	}
	
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> map = super.toMap();
		map.put("defaultValue", defaultValue);
		return map;
	}
	
}
