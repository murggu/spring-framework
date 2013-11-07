package com.aitormurguzur.storm.spout;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class WordGeneratorSpout extends BaseRichSpout {

	private static final long serialVersionUID = 4108189884021479989L;
	private static final String[] words = new String[] {
		"Forrest", "Roy", "Sherry", "Kirk", "Gwendolyn", "Holly", 
		"Darin", "Jennifer", "Lisa", "Bruce", "Geoffrey", "Janis"};
	
	private SpoutOutputCollector _collector;
	private Random _random;
	
	public void open(@SuppressWarnings("rawtypes") Map conf, TopologyContext context, SpoutOutputCollector collector) {
		_collector = collector;
		_random = new Random();
	}
	
	public void nextTuple() {
		Utils.sleep(500);
		String word = words[_random.nextInt(words.length)];
		_collector.emit(new Values(word));
	}
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}
}
