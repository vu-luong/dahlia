package com.tvd12.dahlia.core.entity;

import com.tvd12.dahlia.core.btree.BTree;
import com.tvd12.dahlia.core.function.RecordConsumer;
import com.tvd12.dahlia.core.setting.CollectionSetting;
import com.tvd12.dahlia.core.tree.Tree;
import com.tvd12.dahlia.core.tree.Tree.Entry;
import com.tvd12.dahlia.core.tree.TreeWalker;

import lombok.Getter;

public class Collection {

	@Getter
	protected long dataSize;
	@Getter
	protected final Indexes indexes;
	@Getter
	protected final CollectionSetting setting;
	protected final Tree<Comparable, Record> indexById;
	
	public Collection(CollectionSetting setting) {
		this.setting = setting;
		this.indexes = new Indexes();
		this.indexById = new BTree<>();
	}
	
	public Record findById(Comparable id) {
		Record record = indexById.get(id);
		return record;
	}
	
	public void insert(Record record) {
		this.indexById.put(record.getId(), record);
	}
	
	public Record update(Record record) {
		return this.indexById.put(record.getId(), record);
	}
	
	public Record remove(Comparable id) {
		return this.indexById.remove(id);
	}
	
	public void forEach(RecordConsumer consumer) {
		this.indexById.walk(new TreeWalker<Comparable, Record>() {
			@Override
			public void accept(Entry<Comparable, Record> entry) {
				consumer.accept(entry.getValue());
			}
			@Override
			public boolean next() {
				return consumer.next();
			}
		});
	}
	
	public long increaseDataSize() {
		this.dataSize += setting.getRecordSize();
		return dataSize;
	}
	
	public int getId() {
		return setting.getCollectionId();
	}
	
	public String getName() {
		return setting.getCollectionName();
	}
	
	public int size() {
		return indexById.size();
	}
	
	public Index getIndex(Object key) {
		return indexes.getIndex(key);
	}
	
	public void addIndex(Index index) {
		this.indexes.addIndex(index);
	}
	
	@Override
	public String toString() {
		return setting.getCollectionName();
	}

}
