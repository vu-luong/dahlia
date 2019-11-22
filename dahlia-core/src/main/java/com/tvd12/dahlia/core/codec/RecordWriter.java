package com.tvd12.dahlia.core.codec;

import java.io.IOException;
import java.util.Map;

import com.tvd12.dahlia.core.entity.Record;
import com.tvd12.dahlia.core.io.FileProxy;
import com.tvd12.dahlia.core.setting.FieldSetting;
import com.tvd12.ezyfox.entity.EzyObject;

public class RecordWriter {

	protected final FileProxy file;
	protected final FieldWriters fieldWriters;
	
	public RecordWriter(FileProxy file) {
		this.file = file;
		this.fieldWriters = new FieldSimpleWriters();
	}
	
	public void write(
			Record record, 
			Map<String, FieldSetting> settings, EzyObject data) {
		try {
			file.seek(record.getPosition());
			writeRecordHeader(record);
			if(record.isAlive())
				fieldWriters.write(file, settings, data);
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	protected void writeRecordHeader(Record record) throws IOException {
		byte header = 0;
		if(record.isAlive())
			header |= 1 << 0;
		file.write(header);
	}
	
}